package com.oculusvr.capi;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class SensorData extends Structure {
  public OvrVector3f Accelerometer;
  public OvrVector3f Gyro;
  public OvrVector3f Magnetometer;
  public float Temperature;
  public float TimeInSeconds;

  public SensorData() {
    super();
  }

  @Override
  protected List<?> getFieldOrder() {
    return Arrays.asList("Accelerometer", "Gyro", "Magnetometer", "Temperature", "TimeInSeconds");
  }

  public SensorData(OvrVector3f Accelerometer, OvrVector3f Gyro, OvrVector3f Magnetometer, float Temperature,
      float TimeInSeconds) {
    super();
    this.Accelerometer = Accelerometer;
    this.Gyro = Gyro;
    this.Magnetometer = Magnetometer;
    this.Temperature = Temperature;
    this.TimeInSeconds = TimeInSeconds;
  }

  public SensorData(Pointer peer) {
    super(peer);
  }

  public static class ByReference extends SensorData implements Structure.ByReference {

  };

  public static class ByValue extends SensorData implements Structure.ByValue {

  };
}
