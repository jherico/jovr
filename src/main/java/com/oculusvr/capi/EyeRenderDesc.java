package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class EyeRenderDesc extends Structure implements Structure.ByValue {
  public int Eye;
  public FovPort Fov;
  public OvrRecti DistortedViewport;
  public OvrVector2f PixelsPerTanAngleAtCenter;
  public OvrVector3f HmdToEyeViewOffset;

  public EyeRenderDesc() {
    super();
  }

  public EyeRenderDesc(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Eye", "Fov", "DistortedViewport", "PixelsPerTanAngleAtCenter", "HmdToEyeViewOffset");
  }
}
