package com.oculusvr.capi;

import org.junit.Test;

public class OvrLibraryTest {

  @Test
  public void testInitHmd() throws InterruptedException {
    Hmd.initialize();
    Hmd hmd = Hmd.create();
    HmdDesc hmdDesc = hmd.getDesc();
    System.out.println(hmdDesc.Type);
    System.out.println(hmdDesc.Resolution.w + " " + hmdDesc.Resolution.h);
    System.out.println(hmdDesc.DisplayRefreshRate);
    
    hmd.configureTracking();
    for (int i = 0; i < 10; ++i) {
      TrackingState trackingState = hmd.getTrackingState(0);
      OvrVector3f position = trackingState.HeadPose.Pose.Position;
      position.x *= 100.0f;
      position.y *= 100.0f;
      position.z *= 100.0f;
      System.out.println((int)position.x + ", " + (int)position.y + " " + (int)position.z);
      Thread.sleep(1000);
    }
    hmd.destroy();
    hmd = null;
    Hmd.shutdown();
  }

}
