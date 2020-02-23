
import airsim
import sys
import time

client = airsim.MultirotorClient()
def dronePreSetup(client):
    client.confirmConnection()
    client.enableApiControl(True)
    print("arming the drone...")
    client.armDisarm(True)

    # state = client.getMultirotorState()
    # if state.landed_state == airsim.LandedState.Landed:
    #     print("taking off...")
    #     client.takeoffAsync().join()
    # else:
    #     client.hoverAsync().join()
    # time.sleep(1)
    # state = client.getMultirotorState()
    # if state.landed_state == airsim.LandedState.Landed:
    #     print("take off failed...")
    #     sys.exit(1)
    # print("make sure we are hovering at 2 meters...")
    # client.moveToZAsync(altitude, 1).join()
    # print("flying on the given path...")
    # client.moveOnPathAsync(coord_array,
    #                     12, 120,
    #                     airsim.DrivetrainType.ForwardOnly, airsim.YawMode(False,0), 20, 1).join()
    # time.sleep(5)
    # client.hoverAsync().join()
    #
    # print("landing...")
    # client.landAsync().join()
    # print("disarming...")
    # #client.armDisarm(False)
    # client.enableApiControl(False)
    # print("done.")

def handleTakeoff(client):
    state = client.getMultirotorState()
    if state.landed_state == airsim.LandedState.Landed:
        print("taking off...")
        client.takeoffAsync().join()
    else:
        client.hoverAsync().join()
    time.sleep(1)
    state = client.getMultirotorState()
    if state.landed_state == airsim.LandedState.Landed:
        print("take off failed...")
        sys.exit(1)
def adjustAltitude(client, altitude):
    print("make sure we are hovering at 2 meters...")
    client.moveToZAsync(altitude, 1).join()

def flyOnPath(client, coord_array):
    print("flying on the given path...")
    client.moveOnPathAsync(coord_array,
                        5, 120,
                        airsim.DrivetrainType.MaxDegreeOfFreedom, airsim.YawMode(False,0), 20, 1).join()

def land(client):
    time.sleep(5)
    client.hoverAsync().join()
    client.moveToPositionAsync(0,0,-2,1).join()
    print("landing...")
    client.landAsync().join()
    print("disarming...")
    client.armDisarm(False)
    client.enableApiControl(False)
    print("done.")
def createCoordArray(input_array):
    coord_array = []
    for i in range(len(input_array)):
        x = input_array[i][0]
        y = input_array[i][1]
        z = input_array[i][2]
        coord_array.append(airsim.Vector3r(x,y,z))
    return coord_array

client = airsim.MultirotorClient()
dronePreSetup(client)
handleTakeoff(client)
adjustAltitude(client, -2)
coord_array = createCoordArray([[-10,5,-2],[-30,25,-2], [0,0,-2]])
flyOnPath(client, coord_array)
land(client)

##########################################################################################################################
# client = airsim.MultirotorClient()
# client.confirmConnection()
# client.enableApiControl(True)
#
# print("arming the drone...")
# client.armDisarm(True)
#
# state = client.getMultirotorState()
# if state.landed_state == airsim.LandedState.Landed:
#     print("taking off...")
#     client.takeoffAsync().join()
# else:
#     client.hoverAsync().join()
#
# time.sleep(1)
#
# state = client.getMultirotorState()
# if state.landed_state == airsim.LandedState.Landed:
#     print("take off failed...")
#     sys.exit(1)
#
# # z of -7 is 7 meters above the original launch point.
# z = -2
# print("make sure we are hovering at 2 meters...")
# client.moveToZAsync(z, 1).join()
#
# pos_init = client.getPosition()
# print("homepos is: ",pos_init)
#
#
#
# coord_array = []
# nuberofcoordinates = input("enter the number of coordinates to input:")
#
# for i in range(int(nuberofcoordinates)):
#     x = float(input("coord_x :"))
#     y = float(input("coord_y :"))
#     coord_array.append(airsim.Vector3r(x,y,z))
#
# print("flying on the given path...")
# result = client.moveOnPathAsync(coord_array,
#                         12, 120,
#                         airsim.DrivetrainType.ForwardOnly, airsim.YawMode(False,0), 20, 1).join()
#
# pos_init = client.getPosition()
# print("nxt is: ",pos_init)
# #drone will over-shoot so we bring it back to the start point before landing.
# client.moveToPositionAsync(0,0,z,1).join()
# print("landing...")
# client.landAsync().join()
# print("disarming...")
# client.armDisarm(False)
# client.enableApiControl(False)
# print("done.")
