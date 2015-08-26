package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class OvrRecti extends Structure implements Structure.ByValue {
  public static final int SIZE = OvrVector2i.SIZE + OvrSizei.SIZE;
  public OvrVector2i Pos;
  public OvrSizei Size;

  public OvrRecti() {
    super();
  }

  public OvrRecti(OvrVector2i Pos, OvrSizei Size) {
    super();
    this.Pos = Pos;
    this.Size = Size;
  }

  public OvrRecti(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Pos", "Size");
  }

  public static OvrRecti[] buildPair() {
    return (OvrRecti[]) new OvrRecti(new Memory(SIZE * 2)).toArray(2);
  }
}
