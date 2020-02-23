from flask import Flask, render_template_string, Response

import airsim
import cv2
import numpy as np
import base64
from imageio import imread
import io

client = airsim.MultirotorClient()
def checkOntheFly(client):
    if (client.simGetGroundTruthKinematics().linear_velocity.x_val != 0 or
            client.simGetGroundTruthKinematics().linear_velocity.y_val != 0 or
            client.simGetGroundTruthKinematics().linear_velocity.z_val != 0):
        return True

def createImgArray(client, CAMERA_NAME):
    # if CAMERA_NAME == None:
    #     CAMERA_NAME = 0;
    IMAGE_TYPE = airsim.ImageType.Scene
    DECODE_EXTENSION = '.jpg'
    idx = 0
    img_array = []
    while (checkOntheFly(client)):
        print('Collecting images.....')
        response_image = client.simGetImage(CAMERA_NAME, IMAGE_TYPE)
        np_response_image = np.asarray(bytearray(response_image), dtype="uint8")
        decoded_frame = cv2.imdecode(np_response_image, cv2.IMREAD_COLOR)
        if not decoded_frame == None:
            ret, encoded_jpeg = cv2.imencode(DECODE_EXTENSION, decoded_frame)
        else:
            continue
        idx+=1

        b64_bytes = base64.b64encode(encoded_jpeg)
        b64_string = b64_bytes.decode()

        img = imread(io.BytesIO(base64.b64decode(b64_string)))
        cv2_img = cv2.cvtColor(img, cv2.COLOR_RGB2BGR)
        img_array.append(cv2_img)
    return img_array

def writeToVideo(img_array):
    print('Creating video.....')
    # fourcc = cv2.cv.CV_FOURCC(*'mp4v')
    fourcc = cv2.VideoWriter_fourcc(*'mp4v')
    # fourcc = cv2.cv.CV_FOURCC(*'XVID')
    out = cv2.VideoWriter('drone_ved.avi', fourcc, 5, (256, 144))
    # out = cv2.VideoWriter('drone_ved.avi', cv2.VideoWriter_fourcc('M','J', 'P', 'G'),2, (256, 144))
    for i in range(len(img_array)):
        out.write(img_array[i])
    out.release()

img_array = createImgArray(client, 2)
writeToVideo(img_array)

# CAMERA_NAME = '0'
# IMAGE_TYPE = airsim.ImageType.Scene
# DECODE_EXTENSION = '.jpg'
# idx = 0
#
# img_array = []
# while (client.simGetGroundTruthKinematics().linear_velocity.x_val != 0 or client.simGetGroundTruthKinematics().linear_velocity.y_val != 0 or client.simGetGroundTruthKinematics().linear_velocity.z_val != 0):
#     response_image = client.simGetImage(CAMERA_NAME, IMAGE_TYPE)
#
#     np_response_image = np.asarray(bytearray(response_image), dtype="uint8")
#     decoded_frame = cv2.imdecode(np_response_image, cv2.IMREAD_COLOR)
#     ret, encoded_jpeg = cv2.imencode(DECODE_EXTENSION, decoded_frame)
#     frame = encoded_jpeg.tobytes()
#     idx+=1
#
#     b64_bytes = base64.b64encode(encoded_jpeg)
#     b64_string = b64_bytes.decode()
#
#     img = imread(io.BytesIO(base64.b64decode(b64_string)))
#     cv2_img = cv2.cvtColor(img, cv2.COLOR_RGB2BGR)
#     img_