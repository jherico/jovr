package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class TextureHeader extends Structure {
  public static final int SIZE = 12; 
  public int API = OvrLibrary.ovrRenderAPIType.ovrRenderAPI_OpenGL;;
  public OvrSizei TextureSize;

  public TextureHeader() {
    super();
  }

  public TextureHeader(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("API", "TextureSize");
  }


  public static class ByReference extends TextureHeader implements Structure.ByReference {

  };

  public static class ByValue extends TextureHeader implements Structure.ByValue {

  };
}
