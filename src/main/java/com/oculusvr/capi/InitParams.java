/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 *
 * @author hcram
 */
public class InitParams extends Structure {

    /// Flags from ovrInitFlags to override default behavior.
    /// Use 0 for the defaults.
    public short        Flags;

    /// Requests a specific minor version of the LibOVR runtime.
    /// Flags must include ovrInit_RequestVersion or this will be ignored and OVR_MINOR_VERSION 
    /// will be used. If you are directly calling the LibOVRRT version of ovr_Initialize
    /// in the LibOVRRT DLL then this must be valid and include ovrInit_RequestVersion.
    public short        RequestedMinorVersion;

    /// User-supplied log callback function, which may be called at any time
    /// asynchronously from multiple threads until ovr_Shutdown completes.
    /// Use NULL to specify no log callback.
    public Pointer LogCallback;

    /// User-supplied data which is passed as-is to LogCallback. Typically this
    /// is used to store an application-specific pointer which is read in the
    /// callback function.
    public Pointer      UserData;

    /// Relative number of milliseconds to wait for a connection to the server
    /// before failing. Use 0 for the default timeout.
    public short        ConnectionTimeoutMS;

    public byte[] padding1 = new byte[4]; ///< \internal  
    
  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Flags", "RequestedMinorVersion", "LogCallback", "UserData", "ConnectionTimeoutMS", "padding1");  
  }
  
}
