package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class HmdDesc extends Structure {

  public Pointer Handle;
  /**
   * @see ovrHmdType
   */
  public int Type;
  public byte[] padding1 = new byte[4];
  public Pointer ProductName;
  public Pointer Manufacturer;
  public short VendorId;
  public short ProductId;
  public byte[] SerialNumber = new byte[24];
  public short FirmwareMajor;
  public short FirmwareMinor;
  public float CameraFrustumHFovInRadians;
  public float CameraFrustumVFovInRadians;
  public float CameraFrustumNearZInMeters;
  public float CameraFrustumFarZInMeters;
  public int HmdCaps;
  public int TrackingCaps;
  public FovPort[] DefaultEyeFov = new FovPort[2];
  public FovPort[] MaxEyeFov = new FovPort[2];
  public int[] EyeRenderOrder = new int[2];
  public OvrSizei Resolution;

  public HmdDesc() {
  }

  public HmdDesc(Pointer peer) {
    super(peer);
    read();
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Handle", "Type", "padding1", "ProductName", "Manufacturer", "VendorId", "ProductId", "SerialNumber",
        "FirmwareMajor", "FirmwareMinor", "CameraFrustumHFovInRadians", "CameraFrustumVFovInRadians",
        "CameraFrustumNearZInMeters", "CameraFrustumFarZInMeters", "HmdCaps", "TrackingCaps", "DefaultEyeFov",
        "MaxEyeFov", "EyeRenderOrder", "Resolution");
  }
}
