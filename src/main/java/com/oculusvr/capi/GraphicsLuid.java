package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class GraphicsLuid extends Structure {
  public byte[] Reserved = new byte[8];

  public GraphicsLuid() {
    super();
  }

  public GraphicsLuid(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Reserved");
  }
}
