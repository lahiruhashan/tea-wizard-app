import os

from flask import Flask, request, jsonify

from Classifier.Predictor.TeaPredictor import TeaPredictor
from Classifier.Predictor.VideoPredictor import VideoPredictor

app = Flask(__name__)


# train classifier
@app.route('/train', methods=['POST'])
def train_classifier():
    main_path = os.getcwd() + "/Classifier/ObjectDetectionApi/research/"
    file_path = os.getcwd() + "/Classifier/ObjectDetectionApi/research/object_detection/"
    print(os.getcwd())
    os.system("protoc " + file_path + "/protos/*.proto --python_out=.")

    os.environ[
        'PYTHONPATH'] += ':' + main_path + ':' + main_path + 'slim/'
    os.environ[
        'PYTHONPATH'] += ':' + main_path + ':' + main_path + 'object_detection/'

    # os.system(
    #     "python3 ./legacy/train.py --logtostderr --train_dir=training/ --pipeline_config_path=training/faster_rcnn_resnet101_coco.config  --image_dir=images/")
    os.system(
        "python3 " + file_path + "model_main.py --alsologtostderr --model_dir=training/ --pipeline_config_path=training/faster_rcnn_resnet101_coco.config  --num_train_steps=50000 --num_eval_steps=2000")

    return file_path


# predict for images
@app.route('/predict/image', methods=['POST'])
def predict_image():
    frozen_graph = request.json['frozen_graph']
    labels_file = request.json['labels_file']
    test_image_directory = request.json['test_image_directory']
    predictor = TeaPredictor(frozen_graph, labels_file, test_image_directory)
    ready_objects, image_output_folder = predictor.predict()
    response = {
        "class": "ready",
        "data_values": ready_objects,
        "length": len(ready_objects),
        "output_directory": image_output_folder
    }
    return jsonify(response)


# predict for saved video file
@app.route('/predict/video', methods=['POST'])
def predict_video():
    frozen_graph_path = request.json['frozen_graph']
    label_map_path = request.json['labels_file']
    video_path = request.json['test_video_file']
    classes = request.json['num_of_classes']
    video_predictor = VideoPredictor(frozen_graph_path, label_map_path, video_path, classes)
    ready_objects = video_predictor.display_and_save()
    response = {
        "class": "ready",
        "data_values": ready_objects,
        "length": len(ready_objects),
    }
    return jsonify(response)


if __name__ == '__main__':
    app.run()
