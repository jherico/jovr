package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class OvrQuaternionf extends Structure implements Structure.ByValue {
  public static int SIZE = 16;
  public float x = 0;
  public float y = 0;
  public float z = 0;
  public float w = 1;

  public OvrQuaternionf() {
    super();
  }

  public OvrQuaternionf(float x, float y, float z, float w) {
    super();
    this.x = x;
    this.y = y;
    this.z = z;
    this.w = w;
  }

  public OvrQuaternionf(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("x", "y", "z", "w");
  }

  public OvrVector3f multiply(OvrVector3f v) {
    float tempX = w * w * v.x + 2 * y * w * v.z - 2 * z * w * v.y + x * x * v.x
        + 2 * y * x * v.y + 2 * z * x * v.z - z * z * v.x - y * y * v.x;
    float tempY = 2 * x * y * v.x + y * y * v.y + 2 * z * y * v.z + 2 * w * z
        * v.x - z * z * v.y + w * w * v.y - 2 * x * w * v.z - x * x * v.y;
    float tempZ = 2 * x * z * v.x + 2 * y * z * v.y + z * z * v.z - 2 * w * y
        * v.x - y * y * v.z + 2 * w * x * v.y - x * x * v.z + w * w * v.z;
    return new OvrVector3f(tempX, tempY, tempZ);
  }


}
