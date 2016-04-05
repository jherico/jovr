package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByValue;

public class LayerEyeFov extends Structure implements ByValue {
  public static final int SIZE = 8;
  public LayerHeader Header;
  public Pointer[] ColorTexure = new Pointer[2];
  public OvrRecti[] Viewport = OvrRecti.buildPair(); 
  public FovPort[] Fov = FovPort.buildPair();
  public Posef[] RenderPose = Posef.buildPair();
  
    /// Specifies the timestamp when the source ovrPosef (used in calculating RenderPose)
    /// was sampled from the SDK. Typically retrieved by calling ovr_GetTimeInSeconds
    /// around the instant the application calls ovr_GetTrackingState
    /// The main purpose for this is to accurately track app tracking latency.
  public double SensorSampleTime;
  
  public LayerEyeFov() {
    super();
  }

  public LayerEyeFov(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Header", "ColorTexure", "Viewport", "Fov", "RenderPose", "SensorSampleTime");
  }
}
