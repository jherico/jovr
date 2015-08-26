package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Union;

public class Layer extends Union {
  public static final int SIZE = 176;
  public LayerHeader Header;
  public LayerEyeFov EyeFov;
  // public LayerEyeFovDepth EyeFovDepth;
  // public LayerQuad Quad;
  // public LayerDirect Direct;

  public Layer() {
    super();
  }

  public Layer(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Header", "EyeFov"); // "EyeFovDepth", "Quad",
                                              // "Direct");
  }
}
