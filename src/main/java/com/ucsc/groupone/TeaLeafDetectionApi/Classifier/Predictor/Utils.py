def calculate_ready_leaves_for_video(classes, scores, ready_objects):
    for index, value in enumerate(classes):
        if scores[0, index] > 0.95 and value == 1.0:
            ready_objects.append((scores[0, index]).item())
    return ready_objects


def calculate_ready_leaves_for_image(classes, scores, image_path, ready_objects):
    ready_objects_for_image = []
    for index, value in enumerate(classes):
        if scores[index] > 0.7 and value == 1:
            ready_objects_for_image.append((scores[index]).item())
    image_data = {
        "image_path": image_path,
        "image_data_values": ready_objects_for_image
    }
    ready_objects.append(image_data)
    return ready_objects
