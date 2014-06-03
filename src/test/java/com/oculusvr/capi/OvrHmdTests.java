package com.oculusvr.capi;

import static org.junit.Assert.*;

import org.junit.Test;

import com.oculusvr.capi.OvrLibrary.ovrEyeType;

public class OvrHmdTests extends OvrBaseHmdTest {

  @Test
  public void testGetDesc() {
    HmdDesc desc = hmd.getDesc();
    assertNotNull(desc);
  }

  @Test
  public void testGet() {
    HmdDesc desc = hmd.getDesc();
    DistortionMesh mesh = hmd.createDistortionMesh(ovrEyeType.ovrEye_Left, desc.DefaultEyeFov[ovrEyeType.ovrEye_Left], 0);
    assertNotNull(mesh);
  }

}
