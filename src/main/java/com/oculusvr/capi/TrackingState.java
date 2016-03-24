package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class TrackingState extends Structure implements Structure.ByValue {
  public PoseStatef HeadPose;
  public Posef CameraPose;
  public Posef LeveledCameraPose;
  public PoseStatef[] HandPoses = new PoseStatef[2];
  public SensorData RawSensorData;
  public int StatusFlags;
  
  /// Hand status flags described by ovrStatusBits.
  /// Only ovrStatus_OrientationTracked and ovrStatus_PositionTracked are reported.
  public int[] HandStatusFlags= new int[2];

  public int LastCameraFrameCounter;
  public byte[] padding = new byte[4];

  public TrackingState() {
    super();
  }

  public TrackingState(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("HeadPose", "CameraPose", "LeveledCameraPose", "HandPoses", "RawSensorData", "StatusFlags",
        "HandStatusFlags", "LastCameraFrameCounter", "padding");
  }

}
