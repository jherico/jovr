package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class OvrVector2f extends Structure implements Structure.ByValue {
  public float x = 0;
  public float y = 0;

  public OvrVector2f() {
    super();
  }

  public OvrVector2f(float x, float y) {
    super();
    this.x = x;
    this.y = y;
  }

  public OvrVector2f(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("x", "y");
  }

}
