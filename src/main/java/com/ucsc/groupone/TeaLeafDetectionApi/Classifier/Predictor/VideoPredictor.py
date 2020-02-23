import os
import cv2
import numpy as np
import tensorflow as tf
import Classifier.Predictor.Utils as utils
from Classifier.ObjectDetectionApi.research.object_detection.utils import label_map_util
from Classifier.ObjectDetectionApi.research.object_detection.utils import visualization_utils as vis_util


class VideoPredictor:
    frozen_graph_path = ""
    label_map_path = ""
    video_path = ""
    classes = ""
    label_map = ""
    sess = ""
    image_tensor = ""
    detection_boxes = ""
    detection_scores = ""
    detection_classes = ""
    num_detections = ""
    category_index = ""

    def __init__(self, frozen_graph_path, label_map_path, video_path, classes):
        self.frozen_graph_path = frozen_graph_path
        self.label_map_path = label_map_path
        self.video_path = video_path
        self.classes = classes

        self.label_map = label_map_util.load_labelmap(label_map_path)
        categories = label_map_util.convert_label_map_to_categories(self.label_map, max_num_classes=classes,
                                                                    use_display_name=True)
        self.category_index = label_map_util.create_category_index(categories)
        detection_graph = tf.Graph()
        with detection_graph.as_default():
            od_graph_def = tf.compat.v1.GraphDef()
            with tf.compat.v2.io.gfile.GFile(frozen_graph_path, 'rb') as fid:
                serialized_graph = fid.read()
                od_graph_def.ParseFromString(serialized_graph)
                tf.import_graph_def(od_graph_def, name='')

            self.sess = tf.compat.v1.Session(graph=detection_graph)
        self.image_tensor = detection_graph.get_tensor_by_name('image_tensor:0')
        self.detection_boxes = detection_graph.get_tensor_by_name('detection_boxes:0')
        self.detection_scores = detection_graph.get_tensor_by_name('detection_scores:0')
        self.detection_classes = detection_graph.get_tensor_by_name('detection_classes:0')
        self.num_detections = detection_graph.get_tensor_by_name('num_detections:0')

    def display_and_save(self):
        ready_objects = []
        video = cv2.VideoCapture(self.video_path)
        out = cv2.VideoWriter('tea-new-2-classified.avi',
                              cv2.VideoWriter_fourcc('M', 'J', 'P', 'G'),
                              10,
                              (int(video.get(3)), int(video.get(4))))

        while video.isOpened():
            ret, frame = video.read()

            # save
            if ret:
                frame_expanded = np.expand_dims(frame, axis=0)
                # Perform the actual detection by running the model with the image as input
                (boxes, scores, classes, num) = self.sess.run(
                    [self.detection_boxes, self.detection_scores, self.detection_classes, self.num_detections],
                    feed_dict={self.image_tensor: frame_expanded})

                # print(scores)
                # print(len(scores))
                # print(num)

                ready_objects = utils.calculate_ready_leaves_for_video(classes[0], scores, ready_objects)

                # Draw the results of the detection (aka 'visualize the results')
                vis_util.visualize_boxes_and_labels_on_image_array(
                    frame,
                    np.squeeze(boxes),
                    np.squeeze(classes).astype(np.int32),
                    np.squeeze(scores),
                    self.category_index,
                    use_normalized_coordinates=True,
                    line_thickness=8,
                    min_score_thresh=0.60)
                out.write(frame)
                cv2.namedWindow('Object detector', cv2.WINDOW_NORMAL)
                cv2.resizeWindow('Object detector', 800, 600)
                cv2.imshow('Object detector', frame)

                # Press 'q' to quit
                if cv2.waitKey(1) == ord('q'):
                    break
            else:
                break
        cv2.destroyAllWindows()
        video.release()
        out.release()
        return ready_objects

