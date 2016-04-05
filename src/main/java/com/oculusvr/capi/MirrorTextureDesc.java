package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;

public class MirrorTextureDesc extends Structure implements ByReference {

    public int Format;

    public int Width;
    public int Height;
    public int MiscFlags = 0;      ///< ovrTextureMiscFlags. Not used for GL.

    public MirrorTextureDesc() {
        super();
    }

    public MirrorTextureDesc(Pointer peer) {
        super(peer);
    }

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("Format", "Width", "Height", "MiscFlags");
    }
}
