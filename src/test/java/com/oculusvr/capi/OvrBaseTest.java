package com.oculusvr.capi;

import org.junit.After;
import org.junit.Before;

import com.sun.jna.Pointer;

public abstract class OvrBaseTest {

  @Before
  public void setup() {
    OvrLibrary.INSTANCE.ovr_Initialize(new InitParams(Pointer.NULL));
  }


  @After
  public void shutdown() {
    OvrLibrary.INSTANCE.ovr_Shutdown();
  }
}
