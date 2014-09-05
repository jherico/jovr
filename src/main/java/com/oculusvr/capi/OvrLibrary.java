package com.oculusvr.capi;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

/**
 * JNA Wrapper for library <b>com.oculusvr.capi</b><br>
 * This file was autogenerated by <a
 * href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a
 * href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few
 * opensource projects.</a>.<br>
 * For help, please visit <a
 * href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a
 * href="http://rococoa.dev.java.net/">Rococoa</a>, or <a
 * href="http://jna.dev.java.net/">JNA</a>.
 */
public interface OvrLibrary extends Library {
  
  public static final String JNA_LIBRARY_NAME = "OVR_C";
  // Used for testing debug builds
  // public static final String JNA_LIBRARY_NAME = "C:/Users/bdavis/Git/OculusRiftExamples/build64/output/OVR_Cd.dll";
  // public static final String JNA_LIBRARY_NAME = "/Users/bdavis/git/OculusRiftExamples/build/output/libOVR_Cd.dylib";
  public static final NativeLibrary JNA_NATIVE_LIB = NativeLibrary.getInstance(OvrLibrary.JNA_LIBRARY_NAME);
  public static final OvrLibrary INSTANCE = (OvrLibrary) Native.loadLibrary(OvrLibrary.JNA_LIBRARY_NAME,
      OvrLibrary.class);

  /** enum values */
  public static interface ovrHmdType {
    /** <i>native declaration : line 154</i> */
    public static final int ovrHmd_None = 0;
    /** <i>native declaration : line 155</i> */
    public static final int ovrHmd_DK1 = 3;
    /** <i>native declaration : line 156</i> */
    public static final int ovrHmd_DKHD = 4;
    /** <i>native declaration : line 157</i> */
    public static final int ovrHmd_DK2 = 6;
    /**
     * Some HMD other then the one in the enumeration.<br>
     * <i>native declaration : line 158</i>
     */
    public static final int ovrHmd_Other = 7;
  };

  /** enum values */
  public static interface ovrHmdCaps {
    /**
     * The HMD is plugged in and detected by the system.<br>
     * <i>native declaration : line 165</i>
     */
    public static final int ovrHmdCap_Present = 0x0001;
    /**
     * The HMD and its sensor is available for ownership use.<br>
     * <i>native declaration : line 166</i>
     */
    public static final int ovrHmdCap_Available = 0x0002;
    /**
     * Set to 'true' if we captured ownership of this HMD.<br>
     * <i>native declaration : line 168</i>
     */
    public static final int ovrHmdCap_Captured = 0x0004;
    /**
     * (read only) Means the display driver is in compatibility mode.<br>
     * <i>native declaration : line 171</i>
     */
    public static final int ovrHmdCap_ExtendDesktop = 0x0008;
    /**
     * Disables mirroring of HMD output to the window. This may improve<br>
     * <i>native declaration : line 174</i>
     */
    public static final int ovrHmdCap_NoMirrorToWindow = 0x2000;
    /**
     * Turns off HMD screen and output (only if 'ExtendDesktop' is off).<br>
     * <i>native declaration : line 176</i>
     */
    public static final int ovrHmdCap_DisplayOff = 0x0040;
    /**
     * HMD supports low persistence mode.<br>
     * <i>native declaration : line 178</i>
     */
    public static final int ovrHmdCap_LowPersistence = 0x0080;
    /**
     * Adjust prediction dynamically based on internally measured latency.<br>
     * <i>native declaration : line 179</i>
     */
    public static final int ovrHmdCap_DynamicPrediction = 0x0200;
    /** <i>native declaration : line 181</i> */
    public static final int ovrHmdCap_NoVSync = 0x1000;
    /** <i>native declaration : line 184</i> */
    public static final int ovrHmdCap_Writable_Mask = 0x33F0;
    /** <i>native declaration : line 186</i> */
    public static final int ovrHmdCap_Service_Mask = 0x23F0;
  };

  /** enum values */
  public static interface ovrTrackingCaps {
    /**
     * Supports orientation tracking (IMU).<br>
     * <i>native declaration : line 194</i>
     */
    public static final int ovrTrackingCap_Orientation = 0x0010;
    /**
     * Supports yaw drift correction via a magnetometer or other means.<br>
     * <i>native declaration : line 195</i>
     */
    public static final int ovrTrackingCap_MagYawCorrection = 0x0020;
    /**
     * Supports positional tracking.<br>
     * <i>native declaration : line 196</i>
     */
    public static final int ovrTrackingCap_Position = 0x0040;
    /** <i>native declaration : line 200</i> */
    public static final int ovrTrackingCap_Idle = 0x0100;
  };

  /** enum values */
  public static interface ovrDistortionCaps {
    /**
     * Supports chromatic aberration correction.<br>
     * <i>native declaration : line 207</i>
     */
    public static final int ovrDistortionCap_Chromatic = 0x01;
    /**
     * Supports timewarp.<br>
     * <i>native declaration : line 208</i>
     */
    public static final int ovrDistortionCap_TimeWarp = 0x02;
    /**
     * Supports vignetting around the edges of the view.<br>
     * <i>native declaration : line 209</i>
     */
    public static final int ovrDistortionCap_Vignette = 0x08;
    /**
     * Do not save and restore the graphics state when rendering distortion.<br>
     * <i>native declaration : line 210</i>
     */
    public static final int ovrDistortionCap_NoRestore = 0x10;
    /**
     * Flip the vertical texture coordinate of input images.<br>
     * <i>native declaration : line 211</i>
     */
    public static final int ovrDistortionCap_FlipInput = 0x20;
    /**
     * Assume input images are in sRGB gamma-corrected color space.<br>
     * <i>native declaration : line 212</i>
     */
    public static final int ovrDistortionCap_SRGB = 0x40;
    /**
     * Overdrive brightness transitions to reduce artifacts on DK2+ displays<br>
     * <i>native declaration : line 213</i>
     */
    public static final int ovrDistortionCap_Overdrive = 0x80;
  
    /**
     * High-quality sampling of distortion buffer for anti-aliasing<br>
     */
    public static final int ovrDistortionCap_HqDistortion = 0x100;

    /**
     * Use when profiling with timewarp to remove false positives<br>
     * <i>native declaration : line 215</i>
     */
    public static final int ovrDistortionCap_ProfileNoTimewarpSpinWaits = 0x10000;
  };

  /** enum values */
  public static interface ovrEyeType {
    /** <i>native declaration : line 224</i> */
    public static final int ovrEye_Left = 0;
    /** <i>native declaration : line 225</i> */
    public static final int ovrEye_Right = 1;
    /** <i>native declaration : line 226</i> */
    public static final int ovrEye_Count = 2;
  };

  /** enum values */
  public static interface ovrStatusBits {
    /**
     * Orientation is currently tracked (connected and in use).<br>
     * <i>native declaration : line 299</i>
     */
    public static final int ovrStatus_OrientationTracked = 0x0001;
    /**
     * Position is currently tracked (false if out of range).<br>
     * <i>native declaration : line 300</i>
     */
    public static final int ovrStatus_PositionTracked = 0x0002;
    /**
     * Camera pose is currently tracked.<br>
     * <i>native declaration : line 301</i>
     */
    public static final int ovrStatus_CameraPoseTracked = 0x0004;
    /**
     * Position tracking hardware is connected.<br>
     * <i>native declaration : line 302</i>
     */
    public static final int ovrStatus_PositionConnected = 0x0020;
    /**
     * HMD Display is available and connected.<br>
     * <i>native declaration : line 303</i>
     */
    public static final int ovrStatus_HmdConnected = 0x0080;
  };

  /** enum values */
  public static interface ovrRenderAPIType {
    /** <i>native declaration : line 402</i> */
    public static final int ovrRenderAPI_None = 0;
    /** <i>native declaration : line 403</i> */
    public static final int ovrRenderAPI_OpenGL = 1;
    /** <i>native declaration : line 404</i> */
    public static final int ovrRenderAPI_Android_GLES = 2;
    /** <i>native declaration : line 405</i> */
    public static final int ovrRenderAPI_D3D9 = 3;
    /** <i>native declaration : line 406</i> */
    public static final int ovrRenderAPI_D3D10 = 4;
    /** <i>native declaration : line 407</i> */
    public static final int ovrRenderAPI_D3D11 = 5;
    /** <i>native declaration : line 408</i> */
    public static final int ovrRenderAPI_Count = 6;
  };

  public static final float OVR_DEFAULT_NECK_TO_EYE_VERTICAL = 0.075f;
  public static final String OVR_KEY_PLAYER_HEIGHT = "PlayerHeight";
  public static final float OVR_DEFAULT_PLAYER_HEIGHT = 1.778f;
  public static final float OVR_DEFAULT_IPD = 0.064f;
  public static final String OVR_KEY_NECK_TO_EYE_DISTANCE = "NeckEyeDistance";
  public static final String OVR_KEY_EYE_HEIGHT = "EyeHeight";
  public static final float OVR_DEFAULT_NECK_TO_EYE_HORIZONTAL = 0.0805f;
  public static final String OVR_KEY_USER = "User";
  public static final String OVR_KEY_NAME = "Name";
  public static final float OVR_DEFAULT_EYE_HEIGHT = 1.675f;
  public static final String OVR_DEFAULT_GENDER = "Unknown";
  public static final String OVR_KEY_GENDER = "Gender";
  public static final String OVR_KEY_IPD = "IPD";
  public static final int OVR_DEFAULT_EYE_RELIEF_DIAL = 3;

  /**
   * Initializes all Oculus functionality.<br>
   * Original signature : <code>ovrBool ovr_Initialize()</code><br>
   * <i>native declaration : line 480</i>
   */
  byte ovr_Initialize();

  /**
   * ovr_InitializeRenderingShim initializes the rendering shim appart from everything
   * else in LibOVR. This may be helpful if the application prefers to avoid
   * creating any OVR resources (allocations, service connections, etc) at this point.
   * ovr_InitializeRenderingShim does not bring up anything within LibOVR except the
   * necessary hooks to enable the Direct-to-Rift functionality.
   *
   * Either ovr_InitializeRenderingShim() or ovr_Initialize() must be called before any
   * Direct3D or OpenGL initilization is done by applictaion (creation of devices, etc).
   * ovr_Initialize() must still be called after to use the rest of LibOVR APIs.
   */
  void ovr_InitializeRenderingShim();

  /**
   * Shuts down all Oculus functionality.<br>
   * Original signature : <code>void ovr_Shutdown()</code><br>
   * <i>native declaration : line 482</i>
   */
  void ovr_Shutdown();

  /**
   * string remains valid for app lifespan<br>
   * Original signature : <code>char* ovr_GetVersionString()</code><br>
   * <i>native declaration : line 486</i>
   */
  Pointer ovr_GetVersionString();

  /**
   * Users can get information about each HMD by calling ovrHmd_Create with an
   * index.<br>
   * Original signature : <code>int ovrHmd_Detect()</code><br>
   * <i>native declaration : line 492</i>
   */
  int ovrHmd_Detect();

  /**
   * If not null, then the returned handle must be freed with ovrHmd_Destroy.<br>
   * Original signature : <code>ovrHmd ovrHmd_Create(int)</code><br>
   * <i>native declaration : line 498</i>
   */
  Hmd ovrHmd_Create(int index);

  /**
   * Original signature : <code>void ovrHmd_Destroy(ovrHmd)</code><br>
   * <i>native declaration : line 499</i>
   */
  void ovrHmd_Destroy(Hmd hmd);

  /**
   * but may be used to debug some of the related rendering.<br>
   * Original signature : <code>ovrHmd ovrHmd_CreateDebug(ovrHmdType)</code><br>
   * <i>native declaration : line 503</i>
   */
  Hmd ovrHmd_CreateDebug(int type);

  /**
   * Pass null hmd to get global errors (during create etc).<br>
   * Original signature : <code>char* ovrHmd_GetLastError(ovrHmd)</code><br>
   * <i>native declaration : line 509</i>
   */
  String ovrHmd_GetLastError(Hmd hmd);

  /**
   * @note Source and dest mirror rects are not yet implemented.<br>
   *       Original signature :
   *       <code>ovrBool ovrHmd_AttachToWindow(ovrHmd, void*, const ovrRecti*, const ovrRecti*)</code>
   * <br>
   *       <i>native declaration : line 518</i>
   */
  byte ovrHmd_AttachToWindow(Hmd hmd, Pointer window, OvrRecti destMirrorRect, OvrRecti sourceRenderTargetRect);

  /**
   * capabilities are available for that HMD.<br>
   * Original signature : <code>int ovrHmd_GetEnabledCaps(ovrHmd)</code><br>
   * <i>native declaration : line 527</i>
   */
  int ovrHmd_GetEnabledCaps(Hmd hmd);

  /**
   * such as ovrHmd_LowPersistance.<br>
   * Original signature :
   * <code>void ovrHmd_SetEnabledCaps(ovrHmd, unsigned int)</code><br>
   * <i>native declaration : line 531</i>
   */
  void ovrHmd_SetEnabledCaps(Hmd hmd, int hmdCaps);

  /**
   * - Pass 0 for both supportedTrackingCaps and requiredTrackingCaps to disable
   * tracking.<br>
   * Original signature :
   * <code>ovrBool ovrHmd_ConfigureTracking(ovrHmd, unsigned int, unsigned int)</code>
   * <br>
   * <i>native declaration : line 549</i>
   */
  byte ovrHmd_ConfigureTracking(Hmd hmd, int supportedTrackingCaps, int requiredTrackingCaps);

  /**
   * component of orientation.<br>
   * Original signature : <code>void ovrHmd_RecenterPose(ovrHmd)</code><br>
   * <i>native declaration : line 555</i>
   */
  void ovrHmd_RecenterPose(Hmd hmd);

  /**
   * This may also be used for more refined timing of FrontBuffer rendering
   * logic, etc.<br>
   * Original signature :
   * <code>ovrTrackingState ovrHmd_GetTrackingState(ovrHmd, double)</code><br>
   * <i>native declaration : line 562</i>
   */
  com.oculusvr.capi.TrackingState.ByValue ovrHmd_GetTrackingState(Hmd hmd, double absTime);

  /**
   * values can improve performance.<br>
   * Original signature :
   * <code>ovrSizei ovrHmd_GetFovTextureSize(ovrHmd, ovrEyeType, ovrFovPort, float)</code>
   * <br>
   * <i>native declaration : line 575</i>
   */
  OvrSizei.ByValue ovrHmd_GetFovTextureSize(Hmd hmd, int eye, FovPort.ByValue fov,
      float pixelsPerDisplayPixel);

  /**
   * Original signature :
   * <code>ovrBool ovrHmd_ConfigureRendering(ovrHmd, const ovrRenderAPIConfig*, unsigned int, const ovrFovPort[2], ovrEyeRenderDesc[2])</code>
   * <br>
   * <i>native declaration : line 614</i>
   */
  byte ovrHmd_ConfigureRendering(Hmd hmd, RenderAPIConfig apiConfig, int distortionCaps,
      FovPort eyeFovIn[], EyeRenderDesc eyeRenderDescOut[]);

  /**
   * Pass 0 for the frame index if not using ovrHmd_GetFrameTiming.<br>
   * Original signature :
   * <code>ovrFrameTiming ovrHmd_BeginFrame(ovrHmd, unsigned int)</code><br>
   * <i>native declaration : line 624</i>
   */
  com.oculusvr.capi.FrameTiming.ByValue ovrHmd_BeginFrame(Hmd hmd, int frameIndex);

  /**
   * - *** This Function will call Present/SwapBuffers and potentially wait for
   * GPU Sync ***.<br>
   * Original signature :
   * <code>void ovrHmd_EndFrame(ovrHmd, const ovrPosef[2], const ovrTexture[2])</code>
   * <br>
   * <i>native declaration : line 634</i>
   */
  void ovrHmd_EndFrame(Hmd hmd, Posef renderPose[], Texture eyeTexture[]);

  /**
   * - If the pose is used for rendering the eye, it should be passed to
   * ovrHmd_EndFrame.<br>
   * Original signature :
   * <code>ovrPosef ovrHmd_GetEyePose(ovrHmd, ovrEyeType)</code><br>
   * <i>native declaration : line 642</i>
   */
  Posef.ByValue ovrHmd_GetEyePose(Hmd hmd, int eye);

  /**
   * setup for client rendered distortion.<br>
   * Original signature :
   * <code>ovrEyeRenderDesc ovrHmd_GetRenderDesc(ovrHmd, ovrEyeType, ovrFovPort)</code>
   * <br>
   * <i>native declaration : line 668</i>
   */
  EyeRenderDesc.ByValue ovrHmd_GetRenderDesc(Hmd hmd, int eyeType, FovPort.ByValue fov);

  /**
   * or overriden here.<br>
   * Original signature :
   * <code>ovrBool ovrHmd_CreateDistortionMesh(ovrHmd, ovrEyeType, ovrFovPort, unsigned int, ovrDistortionMesh*)</code>
   * <br>
   * <i>native declaration : line 706</i>
   */
  byte ovrHmd_CreateDistortionMesh(Hmd hmd, int eyeType, FovPort.ByValue fov, int distortionCaps,
      DistortionMesh meshData);

  /**
   * are set to null and zeroes after the call.<br>
   * Original signature :
   * <code>void ovrHmd_DestroyDistortionMesh(ovrDistortionMesh*)</code><br>
   * <i>native declaration : line 713</i>
   */
  void ovrHmd_DestroyDistortionMesh(DistortionMesh meshData);

  /**
   * viewport changes after the fact. This can be used to adjust render size
   * every frame if desired.<br>
   * Original signature :
   * <code>void ovrHmd_GetRenderScaleAndOffset(ovrFovPort, ovrSizei, ovrRecti, ovrVector2f[2])</code>
   * <br>
   * <i>native declaration : line 717</i>
   */
  void ovrHmd_GetRenderScaleAndOffset(FovPort.ByValue fov, OvrSizei.ByValue textureSize,
      OvrRecti.ByValue renderViewport, OvrVector2f uvScaleOffsetOut[]);

  /**
   * rendering thread.<br>
   * Original signature :
   * <code>ovrFrameTiming ovrHmd_GetFrameTiming(ovrHmd, unsigned int)</code><br>
   * <i>native declaration : line 725</i>
   */
  com.oculusvr.capi.FrameTiming.ByValue ovrHmd_GetFrameTiming(Hmd hmd, int frameIndex);

  /**
   * pass the same frame index as was used for GetFrameTiming on the main
   * thread.<br>
   * Original signature :
   * <code>ovrFrameTiming ovrHmd_BeginFrameTiming(ovrHmd, unsigned int)</code><br>
   * <i>native declaration : line 730</i>
   */
  com.oculusvr.capi.FrameTiming.ByValue ovrHmd_BeginFrameTiming(Hmd hmd, int frameIndex);

  /**
   * important before this call to reduce latency and ensure proper timing.<br>
   * Original signature : <code>void ovrHmd_EndFrameTiming(ovrHmd)</code><br>
   * <i>native declaration : line 735</i>
   */
  void ovrHmd_EndFrameTiming(Hmd hmd);

  /**
   * isn't called. Resets internal frame index to the specified number.<br>
   * Original signature :
   * <code>void ovrHmd_ResetFrameTiming(ovrHmd, unsigned int)</code><br>
   * <i>native declaration : line 740</i>
   */
  void ovrHmd_ResetFrameTiming(Hmd hmd, int frameIndex);

  /**
   * Must be called on the same thread as ovrHmd_BeginFrameTiming.<br>
   * Original signature :
   * <code>void ovrHmd_GetEyeTimewarpMatrices(ovrHmd, ovrEyeType, ovrPosef, ovrMatrix4f[2])</code>
   * <br>
   * <i>native declaration : line 748</i>
   */
  void ovrHmd_GetEyeTimewarpMatrices(Hmd hmd, int eye, Posef.ByValue renderPose, OvrMatrix4f twmOut[]);

  /**
   * Used to generate projection from ovrEyeDesc::Fov.<br>
   * Original signature :
   * <code>ovrMatrix4f ovrMatrix4f_Projection(ovrFovPort, float, float, ovrBool)</code>
   * <br>
   * <i>native declaration : line 756</i>
   */
  OvrMatrix4f.ByValue ovrMatrix4f_Projection(FovPort.ByValue fov, float znear, float zfar, byte rightHanded);

  /**
   * orthoDistance = distance from camera, such as 0.8m<br>
   * Original signature :
   * <code>ovrMatrix4f ovrMatrix4f_OrthoSubProjection(ovrMatrix4f, ovrVector2f, float, float)</code>
   * <br>
   * <i>native declaration : line 762</i>
   */
  OvrMatrix4f.ByValue ovrMatrix4f_OrthoSubProjection(OvrMatrix4f.ByValue projection, OvrVector2f.ByValue orthoScale,
      float orthoDistance, float eyeViewAdjustX);

  /**
   * value as used in sensor messages.<br>
   * Original signature : <code>double ovr_GetTimeInSeconds()</code><br>
   * <i>native declaration : line 767</i>
   */
  double ovr_GetTimeInSeconds();

  /**
   * Waits until the specified absolute time.<br>
   * Original signature : <code>double ovr_WaitTillTime(double)</code><br>
   * <i>native declaration : line 770</i>
   */
  double ovr_WaitTillTime(double absTime);

  /**
   * be used to clear the screen.<br>
   * Original signature :
   * <code>ovrBool ovrHmd_ProcessLatencyTest(ovrHmd, unsigned char[3])</code><br>
   * <i>native declaration : line 779</i>
   */
  byte ovrHmd_ProcessLatencyTest(Hmd hmd, ByteBuffer rgbColorOut);

  /**
   * Buffer is valid until next call.<br>
   * Original signature : <code>char* ovrHmd_GetLatencyTestResult(ovrHmd)</code><br>
   * <i>native declaration : line 783</i>
   */
  Pointer ovrHmd_GetLatencyTestResult(Hmd hmd);

  /**
   * }<br>
   * Original signature :
   * <code>void ovrHmd_GetHSWDisplayState(ovrHmd, ovrHSWDisplayState*)</code><br>
   * <i>native declaration : line 819</i>
   */
  void ovrHmd_GetHSWDisplayState(Hmd hmd, HSWDisplayState hasWarningState);

  /**
   * }<br>
   * Original signature : <code>ovrBool ovrHmd_DismissHSWDisplay(ovrHmd)</code><br>
   * <i>native declaration : line 839</i>
   */
  byte ovrHmd_DismissHSWDisplay(Hmd hmd);

  void ovrhmd_EnableHSWDisplaySDKRender(Hmd hmd, byte enabled);
  
  /**
   * Returns defaultValue if property doesn't exist.<br>
   * Original signature :
   * <code>ovrBool ovrHmd_GetBool(ovrHmd, const char*, ovrBool)</code><br>
   * <i>native declaration : line 883</i>
   */
  byte ovrHmd_GetBool(Hmd hmd, String propertyName, byte defaultVal);

  /**
   * Modify bool property; false if property doesn't exist or is readonly.<br>
   * Original signature :
   * <code>ovrBool ovrHmd_SetBool(ovrHmd, const char*, ovrBool)</code><br>
   * <i>native declaration : line 886</i>
   */
  byte ovrHmd_SetBool(Hmd hmd, String propertyName, byte value);

  /**
   * Returns defaultValue if property doesn't exist.<br>
   * Original signature :
   * <code>int ovrHmd_GetInt(ovrHmd, const char*, int)</code><br>
   * <i>native declaration : line 890</i>
   */
  int ovrHmd_GetInt(Hmd hmd, String propertyName, int defaultVal);

  /**
   * Modify integer property; false if property doesn't exist or is readonly.<br>
   * Original signature :
   * <code>ovrBool ovrHmd_SetInt(ovrHmd, const char*, int)</code><br>
   * <i>native declaration : line 893</i>
   */
  byte ovrHmd_SetInt(Hmd hmd, String propertyName, int value);

  /**
   * Returns defaultValue if property doesn't exist.<br>
   * Original signature :
   * <code>float ovrHmd_GetFloat(ovrHmd, const char*, float)</code><br>
   * <i>native declaration : line 897</i>
   */
  float ovrHmd_GetFloat(Hmd hmd, String propertyName, float defaultVal);

  /**
   * Modify float property; false if property doesn't exist or is readonly.<br>
   * Original signature :
   * <code>ovrBool ovrHmd_SetFloat(ovrHmd, const char*, float)</code><br>
   * <i>native declaration : line 900</i>
   */
  byte ovrHmd_SetFloat(Hmd hmd, String propertyName, float value);

  /**
   * Maximum of arraySize elements will be written.<br>
   * Original signature :
   * <code>int ovrHmd_GetFloatArray(ovrHmd, const char*, float[], unsigned int)</code>
   * <br>
   * <i>native declaration : line 904</i>
   */
  int ovrHmd_GetFloatArray(Hmd hmd, String propertyName, FloatBuffer values, int arraySize);

  /**
   * Modify float[] property; false if property doesn't exist or is readonly.<br>
   * Original signature :
   * <code>ovrBool ovrHmd_SetFloatArray(ovrHmd, const char*, float[], unsigned int)</code>
   * <br>
   * <i>native declaration : line 908</i>
   */
  byte ovrHmd_SetFloatArray(Hmd hmd, String propertyName, FloatBuffer values, int arraySize);

  /**
   * String memory is guaranteed to exist until next call to GetString or
   * GetStringArray, or HMD is destroyed.<br>
   * Original signature :
   * <code>char* ovrHmd_GetString(ovrHmd, const char*, const char*)</code><br>
   * <i>native declaration : line 914</i>
   */
  String ovrHmd_GetString(Hmd hmd, String propertyName, String defaultVal);

  /**
   * Set string property<br>
   * Original signature :
   * <code>ovrBool ovrHmd_SetString(ovrHmd, const char*, const char*)</code><br>
   * <i>native declaration : line 918</i>
   */
  byte ovrHmd_SetString(Hmd hmddesc, String propertyName, String value);

  public static class ovrHmdStruct extends PointerType {
    public ovrHmdStruct(Pointer address) {
      super(address);
    }

    public ovrHmdStruct() {
      super();
    }
  };
}
