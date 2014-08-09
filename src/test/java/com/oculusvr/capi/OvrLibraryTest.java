package com.oculusvr.capi;

import static org.junit.Assert.*;

import org.junit.Test;

import com.oculusvr.capi.OvrLibrary.ovrHmdType;

public class OvrLibraryTest extends OvrBaseTest {

  @Test
  public void testInitHmd() {
    Hmd hmd = OvrLibrary.INSTANCE.ovrHmd_CreateDebug(ovrHmdType.ovrHmd_DK1);
    assertNotNull("Creating a debug HMD results in a non-null object", hmd);
    hmd.destroy();
  }

}
