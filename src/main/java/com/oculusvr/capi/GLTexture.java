package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Union;

public class GLTexture extends Union {
  // Do not use this field
  public Texture texture;
  // Use this one!
  public GLTextureData ogl;
  
  public GLTexture() {
    super();
    this.setTypedValue(ogl);
  }
  
  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("texture", "ogl");
  }

}
