package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class OvrSizei extends Structure implements Structure.ByValue {
  public static final int SIZE = 8;
  public int w = 0;
  public int h = 0;

  public OvrSizei() {
    super();
  }

  public OvrSizei(int w, int h) {
    super();
    this.w = w;
    this.h = h;
  }

  public OvrSizei(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("w", "h");
  }
}
