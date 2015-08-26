package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Union;

public class GLTexture extends Union {
  public static final int SIZE = Texture.SIZE;
  // Do not use this field
  @Deprecated public Texture texture;
  // Use this one!
  public GLTextureData ogl;
  
  public GLTexture() {
    super();
    this.setTypedValue(ogl);
  }

  public GLTexture(Pointer peer) {
    super(peer);
    this.setTypedValue(ogl);
  }

  public static GLTexture[] buildPair() {
    return (GLTexture[]) new GLTexture(new Memory(SIZE * 2)).toArray(2);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("texture", "ogl");
  }

}
