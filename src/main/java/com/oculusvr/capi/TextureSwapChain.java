package com.oculusvr.capi;

import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.ptr.IntByReference;

public class TextureSwapChain extends PointerType {
    private final Hmd hmd;

    public TextureSwapChain() {
        hmd = new Hmd(Pointer.NULL);
    }

    public TextureSwapChain(Hmd hmd, Pointer peer) {
        super(peer);
        this.hmd = hmd;
    }

    public int getCurrentIndex() {
        IntByReference intByReference = new IntByReference();
        OvrLibrary.INSTANCE.ovr_GetTextureSwapChainCurrentIndex(hmd, getPointer(), intByReference);
        return intByReference.getValue();
    }
    
    public int getTextureId(int index) {
        IntByReference intByReference = new IntByReference();
        OvrLibrary.INSTANCE.ovr_GetTextureSwapChainBufferGL(hmd, getPointer(), index, intByReference);
        return intByReference.getValue();
    }

    public int commit() {
        return OvrLibrary.INSTANCE.ovr_CommitTextureSwapChain(hmd, getPointer());
    }
            
    public void destroy() {
      OvrLibrary.INSTANCE.ovr_DestroyTextureSwapChain(hmd, getPointer());
    }
}
