package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;

public class SwapTextureSet extends Structure implements ByReference {
  public Pointer Textures;
  public int TextureCount;
  public int CurrentIndex;

  public SwapTextureSet() {
    super();
  }

  public SwapTextureSet(Pointer peer) {
    super(peer);
  }
  
  public GLTexture getTexture(int index) {
    GLTexture result = new GLTexture(Textures.share(index * GLTexture.SIZE));
    result.read();
    return result;
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Textures", "TextureCount", "CurrentIndex");
  }
}
