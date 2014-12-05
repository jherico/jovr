package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class GLTextureData extends Structure {
  public TextureHeader Header;
  public int TexId;

  public GLTextureData() {
    super();
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Header", "TexId");
  }

  public GLTextureData(TextureHeader Header) {
    super();
    this.Header = Header;
  }

  public GLTextureData(Pointer peer) {
    super(peer);
  }

  public static class ByReference extends Texture implements Structure.ByReference {
  };

  public static class ByValue extends Texture implements Structure.ByValue {
  };
}
