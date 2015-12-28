package com.oculusvr.capi;

import java.nio.FloatBuffer;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

public interface OvrLibrary extends Library {
  public static final String OVR_KEY_USER = "User";
  public static final String OVR_KEY_NAME = "Name";
  public static final String OVR_KEY_GENDER = "Gender";
  public static final String OVR_KEY_PLAYER_HEIGHT = "PlayerHeight";
  public static final String OVR_KEY_EYE_HEIGHT = "EyeHeight";
  public static final String OVR_KEY_IPD = "IPD";
  public static final String OVR_KEY_NECK_TO_EYE_DISTANCE = "NeckEyeDistance";
  public static final String OVR_KEY_EYE_RELIEF_DIAL = "EyeReliefDial";
  public static final String OVR_KEY_EYE_TO_NOSE_DISTANCE = "EyeToNoseDist";
  public static final String OVR_KEY_MAX_EYE_TO_PLATE_DISTANCE = "MaxEyeToPlateDist";
  public static final String OVR_KEY_EYE_CUP = "EyeCup";
  public static final String OVR_KEY_CUSTOM_EYE_RENDER = "CustomEyeRender";
  public static final String OVR_KEY_CAMERA_POSITION_1 = "CenteredFromWorld";
  public static final String OVR_KEY_CAMERA_POSITION_2 = "CenteredFromWorld2";
  public static final String OVR_KEY_CAMERA_POSITION = OVR_KEY_CAMERA_POSITION_2;

  public static final String OVR_DEFAULT_GENDER = "Unknown";
  public static final float OVR_DEFAULT_NECK_TO_EYE_VERTICAL = 0.075f;
  public static final float OVR_DEFAULT_PLAYER_HEIGHT = 1.778f;
  public static final float OVR_DEFAULT_IPD = 0.064f;
  public static final float OVR_DEFAULT_NECK_TO_EYE_HORIZONTAL = 0.0805f;
  public static final float OVR_DEFAULT_EYE_HEIGHT = 1.675f;
  public static final int OVR_DEFAULT_EYE_RELIEF_DIAL = 3;
  public static final String OVR_PERF_HUD_MODE = "PerfHudMode";
  public static final String OVR_DEBUG_HUD_STEREO_MODE = "DebugHudStereoMode";
  public static final String OVR_DEBUG_HUD_STEREO_GUIDE_SIZE = "DebugHudStereoGuideSize2f";
  public static final String OVR_DEBUG_HUD_STEREO_GUIDE_POSITION = "DebugHudStereoGuidePosition3f";
  public static final String OVR_DEBUG_HUD_STEREO_GUIDE_YAWPITCHROLL = "DebugHudStereoGuideYawPitchRoll3f";
  public static final String OVR_DEBUG_HUD_STEREO_GUIDE_COLOR = "DebugHudStereoGuideColor4f";

  public static final int PRODUCT_VERSION = 0;
  public static final int MAJOR_VERSION = 6;
  public static final String BIT_DEPTH = System.getProperty("sun.arch.data.model");
  public static final String LIBRARY_NAME = String.format("LibOVRRT%s_%d_%d.dll", BIT_DEPTH, PRODUCT_VERSION,
      MAJOR_VERSION);
  public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(LIBRARY_NAME);
  public static final OvrLibrary INSTANCE = (OvrLibrary) Native.loadLibrary(LIBRARY_NAME, OvrLibrary.class);

  public static interface ovrSuccessType {
    public static final int ovrSuccess = 0;
    public static final int ovrSuccess_NotVisible = 1000;
    public static final int ovrSuccess_HMDFirmwareMismatch = 4100;
    public static final int ovrSuccess_TrackerFirmwareMismatch = 4101;
  }

  public static interface ovrErrorType {
    public static final int ovrError_MemoryAllocationFailure = -1000;
    public static final int ovrError_SocketCreationFailure = -1001;
    public static final int ovrError_InvalidHmd = -1002;
    public static final int ovrError_Timeout = -1003;
    public static final int ovrError_NotInitialized = -1004;
    public static final int ovrError_InvalidParameter = -1005;
    public static final int ovrError_ServiceError = -1006;
    public static final int ovrError_NoHmd = -1007;

    public static final int ovrError_AudioReservedBegin = -2000;
    public static final int ovrError_AudioReservedEnd = -2999;

    public static final int ovrError_Initialize = -3000;
    public static final int ovrError_LibLoad = -3001;
    public static final int ovrError_LibVersion = -3002;
    public static final int ovrError_ServiceConnection = -3003;
    public static final int ovrError_ServiceVersion = -3004;
    public static final int ovrError_IncompatibleOS = -3005;
    public static final int ovrError_DisplayInit = -3006;
    public static final int ovrError_ServerStart = -3007;
    public static final int ovrError_Reinitialization = -3008;
    public static final int ovrError_MismatchedAdapters = -3009;
    public static final int ovrError_LeakingResources = -3010;
    public static final int ovrError_ClientVersion = -3011;

    public static final int ovrError_InvalidBundleAdjustment = -4000;
    public static final int ovrError_USBBandwidth = -4001;
    public static final int ovrError_USBEnumeratedSpeed = -4002;
    public static final int ovrError_ImageSensorCommError = -4003;
    public static final int ovrError_GeneralTrackerFailure = -4004;
    public static final int ovrError_ExcessiveFrameTruncation = -4005;
    public static final int ovrError_ExcessiveFrameSkipping = -4006;
    public static final int ovrError_SyncDisconnected = -4007;
    public static final int ovrError_TrackerMemoryReadFailure = -4008;
    public static final int ovrError_TrackerMemoryWriteFailure = -4009;
    public static final int ovrError_TrackerFrameTimeout = -4010;
    public static final int ovrError_TrackerTruncatedFrame = -4011;

    public static final int ovrError_HMDFirmwareMismatch = -4100;
    public static final int ovrError_TrackerFirmwareMismatch = -4101;
    public static final int ovrError_BootloaderDeviceDetected = -4102;
    public static final int ovrError_TrackerCalibrationError = -4103;
    public static final int ovrError_Incomplete = -5000;
    public static final int ovrError_Abandoned = -5001;
    public static final int ovrError_DisplayLost = -6000;
  }

  public static interface ovrHmdType {
    public static final int ovrHmd_None = 0;
    public static final int ovrHmd_DK1 = 3;
    public static final int ovrHmd_DKHD = 4;
    public static final int ovrHmd_DK2 = 6;
    public static final int ovrHmd_CB = 8;
    public static final int ovrHmd_Other = 9;
    public static final int ovrHmd_E3_2015 = 10;
    public static final int ovrHmd_ES06 = 11;
  };

  public static interface ovrHmdCaps {
    public static final int ovrHmdCap_DebugDevice = 0x0001;
    public static final int ovrHmdCap_Writable_Mask = 0x0000;
    public static final int ovrHmdCap_Service_Mask = 0x0000;
  };

  public static interface ovrTrackingCaps {
    public static final int ovrTrackingCap_Orientation = 0x0010;
    public static final int ovrTrackingCap_MagYawCorrection = 0x0020;
    public static final int ovrTrackingCap_Position = 0x0040;
    public static final int ovrTrackingCap_Idle = 0x0100;
  };

  public static interface ovrDistortionCaps {
    public static final int ovrDistortionCap_Chromatic = 0x01;
    public static final int ovrDistortionCap_TimeWarp = 0x02;
    public static final int ovrDistortionCap_Vignette = 0x08;
    public static final int ovrDistortionCap_NoRestore = 0x10;
    public static final int ovrDistortionCap_FlipInput = 0x20;
    public static final int ovrDistortionCap_SRGB = 0x40;
    public static final int ovrDistortionCap_Overdrive = 0x80;
    public static final int ovrDistortionCap_HqDistortion = 0x100;
    public static final int ovrDistortionCap_LinuxDevFullscreen = 0x200;
    public static final int ovrDistortionCap_ComputeShader = 0x400;
    public static final int ovrDistortionCap_ProfileNoTimewarpSpinWaits = 0x10000;
  };

  public static interface ovrEyeType {
    public static final int ovrEye_Left = 0;
    public static final int ovrEye_Right = 1;
    public static final int ovrEye_Count = 2;
  };

  public static interface ovrStatusBits {
    public static final int ovrStatus_OrientationTracked = 0x0001;
    public static final int ovrStatus_PositionTracked = 0x0002;
    public static final int ovrStatus_CameraPoseTracked = 0x0004;
    public static final int ovrStatus_PositionConnected = 0x0020;
    public static final int ovrStatus_HmdConnected = 0x0080;
  };

  public static interface ovrRenderAPIType {
    public static final int ovrRenderAPI_None = 0;
    public static final int ovrRenderAPI_OpenGL = 1;
    public static final int ovrRenderAPI_Android_GLES = 2;
    public static final int ovrRenderAPI_D3D11 = 5;
  };

  public static interface ovrLayerType {
    public static final int ovrLayerType_Disabled = 0;
    public static final int ovrLayerType_EyeFov = 1;
    public static final int ovrLayerType_EyeFovDepth = 2;
    public static final int ovrLayerType_QuadInWorld = 3;
    public static final int ovrLayerType_QuadHeadLocked = 4;
    public static final int ovrLayerType_Direct = 6;
  };

  public static interface ovrLayerFlags {
    public static final int ovrLayerFlag_HighQuality = 0x01;
    public static final int ovrLayerFlag_TextureOriginAtBottomLeft = 0x02;
  };

  public static interface ovrProjectionModifier {
    public static final int ovrProjection_None = 0x00;
    public static final int ovrProjection_RightHanded = 0x01;
    public static final int ovrProjection_FarLessThanNear = 0x02;
    public static final int ovrProjection_FarClipAtInfinity = 0x04;
    public static final int ovrProjection_ClipRangeOpenGL = 0x08;
  };

  int ovr_Initialize(Pointer p);

  void ovr_Shutdown();

  int ovrHmd_Create(int index, PointerByReference hmd);
  
  int ovrHmd_CreateDebug(int type, PointerByReference hmd);

  void ovrHmd_Destroy(Hmd hmd);

  Pointer ovr_GetVersionString();

  int ovrHmd_GetEnabledCaps(Hmd hmd);

  void ovrHmd_SetEnabledCaps(Hmd hmd, int hmdCaps);

  int ovrHmd_ConfigureTracking(Hmd hmd, int supportedTrackingCaps, int requiredTrackingCaps);

  void ovrHmd_RecenterPose(Hmd hmd);

  // String ovr_GetLastError(Hmd hmd);

  TrackingState ovrHmd_GetTrackingState(Hmd hmd, double absTime);

  OvrSizei ovrHmd_GetFovTextureSize(Hmd hmd, int eye, FovPort fov, float pixelsPerDisplayPixel);

  EyeRenderDesc ovrHmd_GetRenderDesc(Hmd hmd, int eyeType, FovPort fov);

  FrameTiming ovrHmd_GetFrameTiming(Hmd hmd, int frameIndex);

  double ovr_GetTimeInSeconds();

  byte ovrHmd_GetBool(Hmd hmd, String propertyName, byte defaultVal);

  byte ovrHmd_SetBool(Hmd hmd, String propertyName, byte value);

  int ovrHmd_GetInt(Hmd hmd, String propertyName, int defaultVal);

  byte ovrHmd_SetInt(Hmd hmd, String propertyName, int value);

  float ovrHmd_GetFloat(Hmd hmd, String propertyName, float defaultVal);

  byte ovrHmd_SetFloat(Hmd hmd, String propertyName, float value);

  int ovrHmd_GetFloatArray(Hmd hmd, String propertyName, FloatBuffer values, int arraySize);

  byte ovrHmd_SetFloatArray(Hmd hmd, String propertyName, FloatBuffer values, int arraySize);

  String ovrHmd_GetString(Hmd hmd, String propertyName, String defaultVal);

  byte ovrHmd_SetString(Hmd hmddesc, String propertyName, String value);

  OvrMatrix4f ovrMatrix4f_Projection(FovPort fov, float znear, float zfar, byte rightHanded);

  int ovrHmd_CreateSwapTextureSetGL(Hmd hmd, int format, int width, int height, PointerByReference pointer);

  void ovrHmd_DestroySwapTextureSet(Hmd hmd, Pointer textureSet);

  int ovrHmd_CreateMirrorTextureGL(Hmd hmd, int format, int width, int height, PointerByReference pointer);

  void ovrHmd_DestroyMirrorTexture(Hmd hmd, Pointer mirrorTexture);

  int ovrHmd_SubmitFrame(Hmd hmd, int frameIndex, Pointer viewScaleDesc, PointerByReference layers, int layerCount);
  
 
}
