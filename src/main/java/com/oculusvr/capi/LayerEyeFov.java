package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByValue;

public class LayerEyeFov extends Structure implements ByValue {
  public static final int SIZE = 8;
  public LayerHeader Header;
  public SwapTextureSet[] ColorTexure = new SwapTextureSet[2];
  public OvrRecti[] Viewport = OvrRecti.buildPair(); 
  public FovPort[] Fov = FovPort.buildPair();
  public Posef[] RenderPose = Posef.buildPair();

  public LayerEyeFov() {
    super();
  }

  public LayerEyeFov(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Header", "ColorTexure", "Viewport", "Fov", "RenderPose");
  }
}
