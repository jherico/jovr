package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByValue;

public class FovPort extends Structure implements ByValue{
  public static final int SIZE = 16;
  public float UpTan;
  public float DownTan;
  public float LeftTan;
  public float RightTan;

  public FovPort() {
    super();
  }

  public FovPort(float UpTan, float DownTan, float LeftTan, float RightTan) {
    super();
    this.UpTan = UpTan;
    this.DownTan = DownTan;
    this.LeftTan = LeftTan;
    this.RightTan = RightTan;
  }

  public FovPort(Pointer peer) {
    super(peer);
  }
  
  public static FovPort[] buildPair() {
    return (FovPort[]) new FovPort(new Memory(SIZE * 2)).toArray(2);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("UpTan", "DownTan", "LeftTan", "RightTan");
  }
}
