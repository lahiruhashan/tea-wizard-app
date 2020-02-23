import os

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
