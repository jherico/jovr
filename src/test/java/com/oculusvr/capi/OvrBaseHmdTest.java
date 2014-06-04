package com.oculusvr.capi;

import org.junit.After;
import org.junit.Before;

public abstract class OvrBaseHmdTest extends OvrBaseTest {

  protected Hmd hmd;
  
  @Before
  @Override
  public void setup() {
    super.setup();
    hmd = Hmd.create(0); 
//    hmd = Hmd.createDebug(ovrHmdType.ovrHmd_DK2);
    assert(null != hmd);
  }


  @After
  @Override
  public void shutdown() {
    hmd.destroy();
    hmd = null;
    super.shutdown();
  }
}
