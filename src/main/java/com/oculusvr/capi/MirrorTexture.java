package com.oculusvr.capi;

import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.ptr.IntByReference;

public class MirrorTexture extends PointerType {
    private final Hmd hmd;

    public MirrorTexture(Hmd hmd, Pointer peer) {
        super(peer);
        this.hmd = hmd;
    }

    public int getTextureId() {
        IntByReference intByReference = new IntByReference();
        OvrLibrary.INSTANCE.ovr_GetMirrorTextureBufferGL(hmd, getPointer(), intByReference);
        
        return intByReference.getValue();
    }
    
    public void destroy() {
      OvrLibrary.INSTANCE.ovr_DestroyMirrorTexture(hmd, getPointer());
    }
}
