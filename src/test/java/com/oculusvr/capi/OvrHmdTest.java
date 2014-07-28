package com.oculusvr.capi;

import static org.junit.Assert.*;

import org.junit.Test;

import com.oculusvr.capi.OvrLibrary.ovrEyeType;

public class OvrHmdTest extends OvrBaseHmdTest {

  @Test
  public void testDistortionMesh() {
    DistortionMesh mesh = hmd.createDistortionMesh(ovrEyeType.ovrEye_Left, hmd.DefaultEyeFov[ovrEyeType.ovrEye_Left], 0);
    assertNotNull(mesh);
  }

//  @SuppressWarnings("unused")
//  @Test
//  public void testGet() {
//    hmd.beginFrame(0);
//  }
}
