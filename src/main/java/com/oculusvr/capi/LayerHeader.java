package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByValue;

public class LayerHeader extends Structure implements ByValue {
  public static final int SIZE = 8;
  public int Type;
  public int Flags = OvrLibrary.ovrLayerFlags.ovrLayerFlag_TextureOriginAtBottomLeft;


  public LayerHeader() {
    super();
  }

  public LayerHeader(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Type", "Flags");
  }
}
