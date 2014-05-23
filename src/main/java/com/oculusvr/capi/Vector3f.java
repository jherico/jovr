package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class Vector3f extends Structure {
  public float x;
  public float y;
  public float z;

  public Vector3f() {
    super();
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("x", "y", "z");
  }

  public Vector3f(float x, float y, float z) {
    super();
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector3f(Pointer peer) {
    super(peer);
  }

  public static class ByReference extends Vector3f implements Structure.ByReference {

  };

  public static class ByValue extends Vector3f implements Structure.ByValue {

  };

}
