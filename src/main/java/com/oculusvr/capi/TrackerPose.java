package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class TrackerPose extends Structure implements Structure.ByValue {
    public int TrackerFlags;  
    public Posef Pose;
    public Posef LeveledPose;
    public byte[] padding = new byte[4];
    
    public TrackerPose() {
        super();
    }

    public TrackerPose(Pointer peer) {
        super(peer);
    }

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList("TrackerFlags", "Pose", "LeveledPose", "padding");
    }

}
