package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class OvrVector3f extends Structure implements Structure.ByValue {
  public static int SIZE = 12;
  public float x = 0;
  public float y = 0;
  public float z = 0;

  public OvrVector3f() {
    super();
  }

  public OvrVector3f(float x, float y, float z) {
    super();
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public OvrVector3f(Pointer peer) {
    super(peer);
  }

  public static OvrVector3f[] buildPair() {
    return (OvrVector3f[]) new OvrVector3f(new Memory(SIZE * 2)).toArray(2);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("x", "y", "z");
  }
}
