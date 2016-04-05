package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;

public class TextureSwapChainDesc extends Structure implements ByReference {

    public int Type;
    public int Format;

    public int ArraySize = 1;      ///< Only supported with ovrTexture_2D. Not supported on PC at this time.
    public int Width;
    public int Height;
    public int MipLevels = 1;
    public int SampleCount = 1;    ///< Current only supported on depth textures
    public boolean StaticImage;    ///< Not buffered in a chain. For images that don't change
    public int MiscFlags = 0;      ///< ovrTextureMiscFlags. Not used for GL.
    public int BindFlags = 0;      ///< ovrTextureBindFlags. Not used for GL.

    public TextureSwapChainDesc() {
        super();
    }

    public TextureSwapChainDesc(Pointer peer) {
        super(peer);
    }

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("Type", "Format", "ArraySize", "Width", "Height", "MipLevels", "SampleCount", "StaticImage", "MiscFlags", "BindFlags");
    }
}
