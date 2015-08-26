package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.ByReference;

public class ViewScaleDesc extends Structure implements ByReference {
  public static final int SIZE = OvrVector3f.SIZE * 2 + 4;
  public OvrVector3f HmdToEyeViewOffset_left;
  public OvrVector3f HmdToEyeViewOffset_right;
  public float HmdSpaceToWorldScaleInMeters;       

  public ViewScaleDesc() {
    super();
  }

  public ViewScaleDesc(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("HmdToEyeViewOffset_left", "HmdToEyeViewOffset_right", "HmdSpaceToWorldScaleInMeters");
  }

}
