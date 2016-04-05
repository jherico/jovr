package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/// Specifies status information for the current session.
///
/// \see ovr_GetSessionStatus
///

public class SessionStatus extends Structure implements Structure.ByValue {
  public byte IsVisible;///< True if the process has VR focus and thus is visible in the HMD.
  public byte HmdPresent;///< True if an HMD is present.
  public byte HmdMounted;   ///< True if the HMD is on the user's head.
  public byte DisplayLost;  ///< True if the session is in a display-lost state. See ovr_SubmitFrame.
  public byte ShouldQuit;   ///< True if the application should initiate shutdown.    
  public byte ShouldRecenter;  ///< True if UX has requested re-centering. Must call ovr_ClearShouldRecenterFlag or ovr_RecenterTrackingOrigin.

  public SessionStatus() {
    super();
  }

  public SessionStatus(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList(
            "IsVisible", "HmdPresent", "HmdMounted", "DisplayLost", "ShouldQuit", "ShouldRecenter");
  }
  
}
