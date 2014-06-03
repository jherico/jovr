package com.oculusvr.capi;

import org.junit.After;
import org.junit.Before;

import com.oculusvr.capi.OvrLibrary;

public abstract class OvrBaseTest {

  @Before
  public void setup() {
    OvrLibrary.INSTANCE.ovr_Initialize();
  }


  @After
  public void shutdown() {
    OvrLibrary.INSTANCE.ovr_Shutdown();
  }
}
