package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class OvrMatrix4f extends Structure implements Structure.ByValue {
  /** C type : float[4][4] */
  public float[] M = { 
      1, 0, 0, 0,
      0, 1, 0, 0,
      0, 0, 1, 0, 
      0, 0, 0, 1,  
  };

  public OvrMatrix4f() {
    super();
  }

  public OvrMatrix4f(float M[]) {
    super();
    if ((M.length != this.M.length))
      throw new IllegalArgumentException("Wrong array size !");
    this.M = M;
  }

  public OvrMatrix4f(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("M");
  }

}
