package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class OvrVector2i extends Structure implements Structure.ByValue {
  public static final int SIZE = 8;

  public int x = 0;
  public int y = 0;

  public OvrVector2i() {
    super();
  }

  public OvrVector2i(int x, int y) {
    super();
    this.x = x;
    this.y = y;
  }

  public OvrVector2i(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("x", "y");
  }

}
