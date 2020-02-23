import os
import sys
import tensorflow as tf
import subprocess


# os.system("pip3 install lxml")
# # os.system("pip3 install Cython")
# # os.system("pip3 install contextlib2")
# # os.system("pip3 install jupyter")
# # os.system("pip3 install matplotlib")
# # os.system("pip3 install pandas")
# # os.system("pip3 install opencv-python")
# os.system("sudo apt install protobuf-compiler")


os.chdir("ObjectDetectionApi/research")
os.system("protoc object_detection/protos/*.proto --python_out=.")

# os.system("export PYTHONPATH=/home/hashan/PycharmProjects/Framework/Classifier/ObjectDetectionApi:/home/hashan/PycharmProjects/Framework/Classifier/ObjectDetectionApi/research:/home/hashan/PycharmProjects/Framework/Classifier/ObjectDetectionApi/research/slim")
os.system("export PYTHONPATH=$PYTHONPATH:/home/hashan/PycharmProjects/Framework/Classifier/ObjectDetectionApi")
os.system("export PYTHONPATH=$PYTHONPATH:/home/hashan/PycharmProjects/Framework/Classifier/ObjectDetectionApi/research")
os.system("export PYTHONPATH=$PYTHONPATH:/home/hashan/PycharmProjects/Framework/Classifier/ObjectDetectionApi/research/slim")
# os.system("sudo python3 setup.py build")
# os.system("sudo python3 setup.py build")

os.chdir("object_detection")
os.system("pwd")

# print(os.system("$PYTHONPATH"))

# print(os.system("python3 legacy/train.py --logtostderr --train_dir=training/ --pipeline_config_path=training/faster_rcnn_resnet101_coco.config --image_dir=images/"))


# print(test)
# print(test4)
