package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class Posef extends Structure implements Structure.ByValue {
  public static final int SIZE = OvrQuaternionf.SIZE + OvrVector3f.SIZE;  
  public OvrQuaternionf Orientation;
  public OvrVector3f Position;

  public Posef() {
    super();
  }

  public Posef(OvrQuaternionf orientation, OvrVector3f position) {
    super();
    this.Orientation = orientation;
    this.Position = position;
  }

  public Posef(Pointer peer) {
    super(peer);
  }

  public static Posef[] buildPair() {
    return (Posef[]) new Posef(new Memory(SIZE * 2)).toArray(2);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Orientation", "Position");
  }
  
  public OvrVector3f apply(OvrVector3f v) {
    OvrVector3f result = Orientation.multiply(v);
    result.x += Position.x;
    result.y += Position.y;
    result.z += Position.z;
    return result;
  }
  
}
