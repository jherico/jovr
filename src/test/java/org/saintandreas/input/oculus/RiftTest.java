package org.saintandreas.input.oculus;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.saintandreas.input.hid.HidFeatureReport;
import org.saintandreas.oculus.RiftTracker;
import org.saintandreas.oculus.hid.DisplayInfo;
import org.saintandreas.oculus.hid.SensorConfig;
import org.saintandreas.oculus.hid.SensorRange;
import org.saintandreas.oculus.tracker.TrackerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codeminders.hidapi.HIDDevice;
import com.google.common.base.Predicate;

// 75962593 HidD_SetFeature 0000000000000224
// Buffer length: 7
// {
// 02 00 00 20 13 E8 03
// or
// 02 00 00 20 01 E8 03
// }

// 75986437 HidD_SetFeature 0000000000000224
// Buffer length: 6
// {
// 11 00 00 0B 10 27
// }

// 75987578 HidD_SetFeature 0000000000000224
// Buffer length: 13
// {
// 0C 00 00 00 07 00 5E 01 1A 41 00 00 7F
// or
// 0C 00 00 00 0D 00 5E 01 1A 41 00 00 7F
// or
// 0C 00 00 FF 04 00 5E 01 1A 41 00 00 7F 
// or
// 0C 00 00 FF 05 00 5E 01 1A 41 00 00 7F 
// }

public class RiftTest {
//  private static final byte[] FEATURE_2_1 = new byte[] {
//    0x02, 0x00, 0x00, 0x20, 0x13, (byte)0xE8, 0x03
//  };
//
//  private static final byte[] FEATURE_2_2 = new byte[] {
//    0x02, 0x00, 0x00, 0x20, 0x01, (byte)0xE8, 0x03
//  };

  private static final byte[] FEATURE_13_1 = new byte[] {
    0x0C, 0x00, 0x00, 0x00, 0x07, 0x00, 0x5E, 0x01, 0x1A, 0x41, 0x00, 0x00, 0x7F
  };
  
  private static final byte[] FEATURE_13_2 = new byte[] {
    0x0C, 0x00, 0x00, (byte)0xFF, 0x05, 0x00, 0x5E, 0x01, 0x1A, 0x41, 0x00, 0x00, 0x7F
  };
  
  private static final byte[] FEATURE_13_3 = new byte[] {
    0x0C, 0x00, 0x00, (byte)0xFF, 0x04, 0x00, 0x5E, 0x01, 0x1A, 0x41, 0x00, 0x00, 0x7F
  };

  private static final byte[] FEATURE_13_4 = new byte[] {
    0x0C, 0x00, 0x00, 0x00, 0x0D, 0x00, 0x5E, 0x01, 0x1A, 0x41, 0x00, 0x00, 0x7F
  };
 


//  private static final byte[] FEATURE_17 = new byte[] {
//    0x11, 0x00, 0x00, 0x0B, 0x10, 0x27
//  };
  

  private static final byte[][] FEATURES = new byte[][] {
//    FEATURE_2_1,
//    FEATURE_2_2,
    FEATURE_13_1,
    FEATURE_13_2,
    FEATURE_13_3,
    FEATURE_13_4,
//    FEATURE_17,
  };

  private static final Logger LOG         = LoggerFactory.getLogger(RiftTest.class);

  public static class Dk2KeepAlive extends HidFeatureReport {
    public static final byte FEATURE_ID   = 0x11;
    public static final int  FEATURE_SIZE = 7;

    public Dk2KeepAlive() {
      super(FEATURE_ID, FEATURE_SIZE);
    }

    @Override
    protected void parse(ByteBuffer buffer) {
      throw new IllegalStateException("Write only type");
    }

    @Override
    protected void pack(ByteBuffer buffer) {
      // 11 00 00 0B 10 27
      buffer.putShort((short) 0);
      buffer.put((byte) 0x0b);
      buffer.putShort((short) 10000);
    }
  }

  public static class Unknown extends HidFeatureReport {
    public static final byte FEATURE_ID   = 2;
    public static final int  FEATURE_SIZE = 7;

    public byte[]            data;

    public Unknown(HIDDevice device) throws IOException {
      super(FEATURE_ID, FEATURE_SIZE, device);
    }

    @Override
    protected void parse(ByteBuffer buffer) {
      buffer.position(1);
      data = new byte[6];
      buffer.get(data);
    }

    @Override
    protected void pack(ByteBuffer buffer) {
      throw new IllegalStateException("Read only type");
    }
  }

  private static ByteBuffer allocate(int size) {
    return ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN);
  }

  public static void main(String... args) throws IOException, InterruptedException {
    final ObjectMapper mapper = new ObjectMapper();
    final ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
    RiftTracker tracker = RiftTracker.getInstance();
//    Unknown report = new Unknown(tracker.device);
//    new Dk2KeepAlive().write(tracker.device);
    for (int i = 0; i < 100; ++i) {
      tracker.device.write(FEATURE_13_2);
      Thread.sleep(5000);
    }
    LOG.info("Done");
    // DisplayInfo di = tracker.getDisplayInfo();
    // LOG.info(writer.writeValueAsString(di));
    // SensorConfig sc = tracker.getSensorConfig();
    // LOG.info(writer.writeValueAsString(sc));
    // SensorRange sr = tracker.getSensorRange();
    // LOG.info(writer.writeValueAsString(sr));
    // RiftTracker.startListening(new Predicate<TrackerMessage>() {
    // @Override
    // public boolean apply(TrackerMessage message) {
    // try {
    // // Just enough data to see we're getting stuff
    // LOG.info("Sample count: " + message.sampleCount);
    // LOG.info("Sample 0: " +
    // mapper.writeValueAsString(message.samples.get(0)));
    // } catch (Exception e) {
    // LOG.error("Failed to serialize sample data", e);
    // }
    // return true;
    // }
    // });
  }

  /**
   * This is a tool I used to fix my display info report after I accidentally
   * trashed it by runnig example code here:
   * http://lxr.free-electrons.com/source/samples/hidraw/hid-example.c -- note
   * the sample code writes to feature report 9 without actually doing any
   * verification that that's a good idea.
   * 
   * No guaratee that this will not destroy your device. No warranty is
   * implied or provided.
   * 
   * @param device
   * @throws IOException
   */
  public static void fixDisplayInfo(HIDDevice device) throws IOException {
    DisplayInfo di = new DisplayInfo();
    // These values are from an earlier dump of my display Info. I have no
    // idea if they're valid.
    di.distortion = 1;
    di.xres = 1280;
    di.yres = 800;
    di.xsize = 149760;
    di.ysize = 93600;
    di.center = 46800;
    di.sep = 63500;
    di.zeye = new int[] { 49800, 49800 };
    di.distortionCoefficients = new float[] {
      Float.NaN,
      1.5946803e-27f,
      Float.NaN,
      3.4119901E16f,
      4.6665167E-38f,
      -9.6494493E15f
    };
    di.write(device);
  }
}
