package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/// Specifies status information for the current session.
///
/// \see ovr_GetSessionStatus
///

public class SessionStatus extends Structure implements Structure.ByValue {
  public byte HasVrFocus;///< True if the process has VR focus and thus is visible in the HMD.
  public byte HmdPresent;///< True if an HMD is present.

  public SessionStatus() {
    super();
  }

  public SessionStatus(byte HasVrFocus, byte HmdPresent) {
    super();
    this.HasVrFocus = HasVrFocus;
    this.HmdPresent = HmdPresent;
  }

  public SessionStatus(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("HasVrFocus", "HmdPresent");
  }
  
}
