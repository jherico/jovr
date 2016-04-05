package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class TrackingState extends Structure implements Structure.ByValue {
  public PoseStatef HeadPose;
  public int StatusFlags;
  public PoseStatef[] HandPoses = new PoseStatef[2];
  /// Hand status flags described by ovrStatusBits.
  /// Only ovrStatus_OrientationTracked and ovrStatus_PositionTracked are reported.
  public int[] HandStatusFlags= new int[2];
  public Posef CalibratedOrigin;

  public TrackingState() {
    super();
  }

  public TrackingState(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("HeadPose", "StatusFlags", 
            "HandPoses", "HandStatusFlags", "CalibratedOrigin");
  }

}
