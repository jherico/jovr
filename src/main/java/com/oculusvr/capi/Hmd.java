package com.oculusvr.capi;

import static com.oculusvr.capi.OvrLibrary.ovrProjectionModifier.*;

import java.nio.FloatBuffer;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.sun.jna.Pointer;
import com.sun.jna.PointerType;
import com.sun.jna.ptr.PointerByReference;

public class Hmd extends PointerType {
  public static Hmd create() {
    PointerByReference hmdParam = new PointerByReference();
    PointerByReference luidParam = new PointerByReference();
    int result = OvrLibrary.INSTANCE.ovr_Create(hmdParam, luidParam);
    if (0 > result) {
      throw new IllegalStateException("Unable to create HMD");
    }
    return new Hmd(hmdParam.getValue());
  }

  public Hmd() {
  }

  public Hmd(Pointer peer) {
    super(peer);
  }

  public static void initialize() {
    if (0 > OvrLibrary.INSTANCE.ovr_Initialize(Pointer.NULL)) {
      throw new IllegalStateException("Unable to initialize Oculus SDK");
    }
  }

  public static void shutdown() {
    OvrLibrary.INSTANCE.ovr_Shutdown();
  }

  public void destroy() {
    OvrLibrary.INSTANCE.ovr_Destroy(this);
  }

  public HmdDesc getDesc() {
    return OvrLibrary.INSTANCE.ovr_GetHmdDesc(this);
  }

  public float getFloat(@Nonnull String propertyName, float defaultVal) {
    return OvrLibrary.INSTANCE.ovr_GetFloat(this, propertyName, defaultVal);
  }

  public byte setFloat(@Nonnull String propertyName, float value) {
    return OvrLibrary.INSTANCE.ovr_SetFloat(this, propertyName, value);
  }

  public float[] getFloatArray(@Nonnull String propertyName, int arraySize) {
    FloatBuffer buffer = FloatBuffer.allocate(arraySize);
    int result = OvrLibrary.INSTANCE.ovr_GetFloatArray(this, propertyName, buffer, arraySize);
    if (0 == result) {
      return null;
    }
    return buffer.array();
  }

  public byte setFloatArray(@Nonnull String propertyName, @Nonnull float[] values) {
    return setFloatArray(propertyName, FloatBuffer.wrap(values), values.length);
  }

  public int getFloatArray(@Nonnull String propertyName, @Nonnull FloatBuffer values, int arraySize) {
    return OvrLibrary.INSTANCE.ovr_GetFloatArray(this, propertyName, values, arraySize);
  }

  public byte setFloatArray(@Nonnull String propertyName, @Nonnull FloatBuffer values, int arraySize) {
    return OvrLibrary.INSTANCE.ovr_SetFloatArray(this, propertyName, values, arraySize);
  }

  public String getString(@Nonnull String propertyName, String defaultVal) {
    return OvrLibrary.INSTANCE.ovr_GetString(this, propertyName, defaultVal);
  }

  private static class ScaleAndOffset2D {
    OvrVector2f Scale = new OvrVector2f();
    OvrVector2f Offset = new OvrVector2f();
  }

  private static ScaleAndOffset2D CreateNDCScaleAndOffsetFromFov(FovPort tanHalfFov) {
    float projXScale = 2.0f / (tanHalfFov.LeftTan + tanHalfFov.RightTan);
    float projXOffset = (tanHalfFov.LeftTan - tanHalfFov.RightTan) * projXScale * 0.5f;
    float projYScale = 2.0f / (tanHalfFov.UpTan + tanHalfFov.DownTan);
    float projYOffset = (tanHalfFov.UpTan - tanHalfFov.DownTan) * projYScale * 0.5f;
    ScaleAndOffset2D result = new ScaleAndOffset2D();
    result.Scale = new OvrVector2f(projXScale, projYScale);
    result.Offset = new OvrVector2f(projXOffset, projYOffset);
    return result;
  }

  public static OvrMatrix4f getPerspectiveProjection(@Nonnull FovPort tanHalfFov, float zNear, float zFar,
      int projectionModFlags) {
    boolean rightHanded = (projectionModFlags & ovrProjection_RightHanded) > 0;
    boolean flipZ = (projectionModFlags & ovrProjection_FarLessThanNear) > 0;
    boolean farAtInfinity = (projectionModFlags & ovrProjection_FarClipAtInfinity) > 0;
    boolean isOpenGL = (projectionModFlags & ovrProjection_ClipRangeOpenGL) > 0;
    if (!flipZ && farAtInfinity) {
      farAtInfinity = false;
    }

    // A projection matrix is very like a scaling from NDC, so we can start with
    // that.
    ScaleAndOffset2D scaleAndOffset = CreateNDCScaleAndOffsetFromFov(tanHalfFov);

    float handednessScale = rightHanded ? -1.0f : 1.0f;

    OvrMatrix4f projection = new OvrMatrix4f();
    // Produces X result, mapping clip edges to [-w,+w]
    projection.M[0] = scaleAndOffset.Scale.x;
    projection.M[1] = 0.0f;
    projection.M[2] = handednessScale * scaleAndOffset.Offset.x;
    projection.M[3] = 0.0f;

    // Produces Y result, mapping clip edges to [-w,+w]
    // Hey - why is that YOffset negated?
    // It's because a projection matrix transforms from world coords with Y=up,
    // whereas this is derived from an NDC scaling, which is Y=down.
    projection.M[4 + 0] = 0.0f;
    projection.M[4 + 1] = scaleAndOffset.Scale.y;
    projection.M[4 + 2] = handednessScale * -scaleAndOffset.Offset.y;
    projection.M[4 + 3] = 0.0f;

    // Produces Z-buffer result - app needs to fill this in with whatever Z
    // range it wants.
    // We'll just use some defaults for now.
    projection.M[8 + 0] = 0.0f;
    projection.M[8 + 1] = 0.0f;

    if (farAtInfinity) {
      if (isOpenGL) {
        // It's not clear this makes sense for OpenGL - you don't get the same
        // precision benefits you do in D3D.
        projection.M[8 + 2] = -handednessScale;
        projection.M[8 + 3] = 2.0f * zNear;
      } else {
        projection.M[8 + 2] = 0.0f;
        projection.M[8 + 3] = zNear;
      }
    } else {
      if (isOpenGL) {
        // Clip range is [-w,+w], so 0 is at the middle of the range.
        projection.M[8 + 2] = -handednessScale * (flipZ ? -1.0f : 1.0f) * (zNear + zFar) / (zNear - zFar);
        projection.M[8 + 3] = 2.0f * ((flipZ ? -zFar : zFar) * zNear) / (zNear - zFar);
      } else {
        // Clip range is [0,+w], so 0 is at the start of the range.
        projection.M[8 + 2] = -handednessScale * (flipZ ? -zNear : zFar) / (zNear - zFar);
        projection.M[8 + 3] = ((flipZ ? -zFar : zFar) * zNear) / (zNear - zFar);
      }
    }

    // Produces W result (= Z in)
    projection.M[12 + 0] = 0.0f;
    projection.M[12 + 1] = 0.0f;
    projection.M[12 + 2] = handednessScale;
    projection.M[12 + 3] = 0.0f;

    return projection;
  }

  public static double getTimeInSeconds() {
    return OvrLibrary.INSTANCE.ovr_GetTimeInSeconds();
  }

  // public String getLastError() {
  // return OvrLibrary.INSTANCE.ovr_GetLastError(this);
  // }
  //
  public int getEnabledCaps() {
    return OvrLibrary.INSTANCE.ovr_GetEnabledCaps(this);
  }

  public void configureTracking(int supportedSensorCaps, int requiredSensorCaps) {
    if (0 > OvrLibrary.INSTANCE.ovr_ConfigureTracking(this, supportedSensorCaps, requiredSensorCaps)) {
      throw new IllegalStateException("Unable to configure tracking");
    }
  }

  public void configureTracking(int supportedSensorCaps) {
    configureTracking(supportedSensorCaps, 0);
  }

  public void configureTracking() {
    configureTracking(OvrLibrary.ovrTrackingCaps.ovrTrackingCap_Orientation
        | OvrLibrary.ovrTrackingCaps.ovrTrackingCap_Position);
  }

  public void recenterPose() {
    OvrLibrary.INSTANCE.ovr_RecenterPose(this);
  }

  public TrackingState getTrackingState(double absTime) {
    return OvrLibrary.INSTANCE.ovr_GetTrackingState(this, absTime);
  }

  public OvrSizei getFovTextureSize(int eye, FovPort fov, float pixelsPerDisplayPixel) {
    return OvrLibrary.INSTANCE.ovr_GetFovTextureSize(this, eye, fov, pixelsPerDisplayPixel);
  }

  @Nonnull
  public EyeRenderDesc getRenderDesc(int eyeType, @Nonnull FovPort fov) {
    Preconditions.checkNotNull(fov);
    return OvrLibrary.INSTANCE.ovr_GetRenderDesc(this, eyeType, fov);
  }

  @Nonnull
  public FrameTiming getFrameTiming(int frameIndex) {
    return OvrLibrary.INSTANCE.ovr_GetFrameTiming(this, frameIndex);
  }

  Posef[] CalcEyePoses(Posef headPose, OvrVector3f[] hmdToEyeViewOffset) {
    return new Posef[]{
        new Posef(headPose.Orientation, headPose.apply(hmdToEyeViewOffset[0])),
        new Posef(headPose.Orientation, headPose.apply(hmdToEyeViewOffset[1])),
    };
  }

  public Posef[] getEyePoses(int frameIndex, OvrVector3f hmdToEyeViewOffsets[]) {
    FrameTiming timing = getFrameTiming(frameIndex);
    TrackingState trackingState = getTrackingState(timing.DisplayMidpointSeconds);
    return CalcEyePoses(trackingState.HeadPose.Pose, hmdToEyeViewOffsets);
  }

  public SwapTextureSet createSwapTexture(OvrSizei size, int format) {
    PointerByReference texturePointer = new PointerByReference();
    int callResult = OvrLibrary.INSTANCE.ovr_CreateSwapTextureSetGL(this, format, size.w, size.h, texturePointer);
    if (0 > callResult) {
      throw new IllegalStateException("Could not create swap texture set");
    }
    SwapTextureSet result = new SwapTextureSet(texturePointer.getValue());
    result.read();
    return result;
  }

  public void destroySwapTexture(SwapTextureSet set) {
    OvrLibrary.INSTANCE.ovr_DestroySwapTextureSet(this, set.getPointer());
  }

  public GLTexture createMirrorTexture(OvrSizei size, int format) {
    PointerByReference texturePointer = new PointerByReference();
    int callResult = OvrLibrary.INSTANCE.ovr_CreateMirrorTextureGL(this, format, size.w, size.h, texturePointer);
    if (0 > callResult) {
      throw new IllegalStateException("Could not create swap texture set");
    }
    GLTexture result = new GLTexture(texturePointer.getValue());
    result.read();
    return result;
  }

  public void destroyMirrorTexture(GLTexture texture) {
    OvrLibrary.INSTANCE.ovr_DestroyMirrorTexture(this, texture.getPointer());
  }

  public int submitFrame(int frameIndex, LayerEyeFov layer) {
    layer.write();
    PointerByReference p = new PointerByReference();
    p.setValue(layer.getPointer());
    return OvrLibrary.INSTANCE.ovr_SubmitFrame(this, frameIndex, Pointer.NULL, p, 1);
  }
}
