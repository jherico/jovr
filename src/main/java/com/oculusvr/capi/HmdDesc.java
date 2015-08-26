package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class HmdDesc extends Structure implements Structure.ByValue {
  public int Type;
  public byte[] padding1 = new byte[4];
  public byte[] ProductName = new byte[64];
  public byte[] Manufacturer = new byte[64];
  public short VendorId;
  public short ProductId;
  public byte[] SerialNumber = new byte[24];
  public short FirmwareMajor;
  public short FirmwareMinor;
  public float CameraFrustumHFovInRadians;
  public float CameraFrustumVFovInRadians;
  public float CameraFrustumNearZInMeters;
  public float CameraFrustumFarZInMeters;
  public int AvailableHmdCaps;
  public int DefaultHmdCaps;
  public int AvailableTrackingCaps;
  public int DefaultTrackingCaps;
  public FovPort[] DefaultEyeFov = new FovPort[2];
  public FovPort[] MaxEyeFov = new FovPort[2];
  public OvrSizei Resolution;
  public float DisplayRefreshRate;
  public byte[] padding2 = new byte[4];

  public HmdDesc() {
    super();
  }

  public HmdDesc(Pointer peer) {
    super(peer);
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Type", "padding1", "ProductName", "Manufacturer", "VendorId", "ProductId", "SerialNumber",
        "FirmwareMajor", "FirmwareMinor", "CameraFrustumHFovInRadians", "CameraFrustumVFovInRadians",
        "CameraFrustumNearZInMeters", "CameraFrustumFarZInMeters", "AvailableHmdCaps", "DefaultHmdCaps",
        "AvailableTrackingCaps", "DefaultTrackingCaps", "DefaultEyeFov", "MaxEyeFov", "Resolution",
        "DisplayRefreshRate", "padding2");
  }
}
