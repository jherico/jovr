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
        "LastCameraFrameCounter", "padding");
  }

}
