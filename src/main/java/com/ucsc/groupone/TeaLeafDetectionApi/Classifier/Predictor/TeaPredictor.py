from Classifier.Predictor.AbstractPredictor import AbstractPredictor
from PIL import Image
from matplotlib import pyplot as plt
import numpy as np
from Classifier.ObjectDetectionApi.research.object_detection.utils import visualization_utils as vis_util
import Classifier.Predictor.Utils as utils
import time
import cv2
import os


class TeaPredictor(AbstractPredictor):

    def __init__(self, frozen_graph, labels_file, test_image_directory):
        super().__init__(frozen_graph, labels_file, test_image_directory)

    def get_output_dict(self, image_path, image_np):
        image_np_expanded = np.expand_dims(image_np, axis=0)
        output_dict = self.run_inference_for_image(image_np_expanded, self.detection_graph)
        return output_dict

    def visualize_output(self, image_np, output_dict, output_folder, min_score_threshold=.5, line_thickness=8):
        vis_util.visualize_boxes_and_labels_on_image_array(
            image_np,
            output_dict['detection_boxes'],
            output_dict['detection_classes'],
            output_dict['detection_scores'],
            self.category_index,
            instance_masks=output_dict.get('detection_masks'),
            use_normalized_coordinates=True,
            min_score_thresh=min_score_threshold,
            line_thickness=line_thickness)
        plt.figure(figsize=(self.output_image_height, self.output_image_width))
        plt.imshow(image_np)
        image_name = str(time.time())
        cv2.imwrite(output_folder + image_name + ".jpg", image_np)

    def predict(self):
        ready_objects = []
        output_folder = "output/images/" + str(time.time())
        os.mkdir(output_folder)
        for image_path in self.test_image_paths:
            image = Image.open(image_path)
            image_np = self.load_images_into_numpy_array(image)
            output_dict = self.get_output_dict(image_path, image_np)
            # Visualization of the results of a detection.
            self.visualize_output(image_np, output_dict, output_folder + "/", min_score_threshold=.7, line_thickness=10)
            ready_objects = utils.calculate_ready_leaves_for_image(output_dict['detection_classes'],
                                                                   output_dict['detection_scores'],
                                                                   image_path,
                                                                   ready_objects)
        return ready_objects, output_folder
