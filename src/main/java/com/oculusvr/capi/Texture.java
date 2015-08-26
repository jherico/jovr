package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class Texture extends Structure {
  static final boolean is64Bit;
  static {
    is64Bit = "64".equalsIgnoreCase(System.getProperty("sun.arch.data.model"));
  }
  public static final int SIZE = TextureHeader.SIZE + (is64Bit ? 4 : 0) + (Pointer.SIZE * 8);
  public TextureHeader Header;
  public byte[] padding = new byte[is64Bit ? 4 : 0];
  public Pointer PlatformData[] = new Pointer[8];

  public Texture() {
    super();
  }

  public Texture(TextureHeader Header) {
    super();
    for (int i = 0; i < PlatformData.length; ++i) {
      PlatformData[i] = Pointer.NULL;
    }
    this.Header = Header;
  }

  public Texture(Pointer peer) {
    super(peer);
  }

  public static class ByReference extends Texture implements Structure.ByReference {

  };

  public static class ByValue extends Texture implements Structure.ByValue {

  };

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Header", "padding", "PlatformData");
  }

}
