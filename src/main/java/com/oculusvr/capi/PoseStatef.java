package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class PoseStatef extends Structure implements Structure.ByValue {
  public Posef Pose;
  public OvrVector3f AngularVelocity;
  public OvrVector3f LinearVelocity;
  public OvrVector3f AngularAcceleration;
  public OvrVector3f LinearAcceleration;
  public byte[] padding = new byte[4];
  public double TimeInSeconds;

  public PoseStatef() {
    super();
  }

  public PoseStatef(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Pose", "AngularVelocity", "LinearVelocity", "AngularAcceleration", "LinearAcceleration",
        "padding", "TimeInSeconds");
  }
}
