package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class TrackerDesc extends Structure implements Structure.ByValue {
  public float FrustumHFovInRadians;
  public float FrustumVFovInRadians;
  public float FrustumNearZInMeters;
  public float FrustumFarZInMeters;

  public TrackerDesc() {
    super();
  }

  public TrackerDesc(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("FrustumHFovInRadians", "FrustumVFovInRadians",
        "FrustumNearZInMeters", "FrustumFarZInMeters");
  }
}
