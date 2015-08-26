package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class FrameTiming extends Structure implements Structure.ByValue {
  public double DisplayMidpointSeconds;
  public double FrameIntervalSeconds;
  public int AppFrameIndex;
  public int DisplayFrameIndex;

  public FrameTiming() {
    super();
  }

  public FrameTiming(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("DisplayMidpointSeconds", "FrameIntervalSeconds", "AppFrameIndex", "DisplayFrameIndex");
  }
}
