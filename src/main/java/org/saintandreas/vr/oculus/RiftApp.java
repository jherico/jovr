package org.saintandreas.vr.oculus;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Rectangle;

import org.saintandreas.gl.FrameBuffer;
import org.saintandreas.gl.MatrixStack;
import org.saintandreas.gl.app.LwjglApp;
import org.saintandreas.math.Matrix4f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oculusvr.capi.EyeDesc;
import com.oculusvr.capi.EyeRenderDesc;
import com.oculusvr.capi.FovPort;
import com.oculusvr.capi.GLConfig;
import com.oculusvr.capi.GLTextureData;
import com.oculusvr.capi.HmdDesc;
import com.oculusvr.capi.OvrLibrary;
import com.oculusvr.capi.OvrLibrary.ovrHmd;
import com.oculusvr.capi.Posef;
import com.oculusvr.capi.RenderAPIConfig;
import com.oculusvr.capi.Texture;
import com.oculusvr.capi.Vector2i;

public abstract class RiftApp extends LwjglApp {
  @SuppressWarnings("unused")
  private static final Logger LOG = LoggerFactory.getLogger(RiftApp.class);
  protected static final OvrLibrary OVR = OvrLibrary.INSTANCE;
  protected float ipd = OvrLibrary.OVR_DEFAULT_IPD;
  private EyeRenderDesc eyeRenderDescs[] = (EyeRenderDesc[]) new EyeRenderDesc().toArray(2);
  private Texture eyeTextures[] = (Texture[]) new Texture().toArray(2);
  protected final ovrHmd hmd;
  protected final HmdDesc hmdDesc;

  private int frameCount = -1;
  private FrameBuffer frameBuffers[] = new FrameBuffer[2];
  private Matrix4f projections[] = new Matrix4f[2];

  public RiftApp() {
    OVR.ovr_Initialize();
    ovrHmd hmd = ovrHmd.create(0);
    if (null == hmd) {
      hmd = ovrHmd.createDebug(OvrLibrary.ovrHmdType.ovrHmd_DK1);
    }
    if (null == hmd) {
      throw new IllegalStateException("Unable to initialize HMD");
    }
    this.hmd = hmd;
    hmdDesc = hmd.getDesc();
    hmd.startSensor(0, 0);
    ipd = hmd.getFloat(OvrLibrary.OVR_KEY_IPD, OvrLibrary.OVR_DEFAULT_IPD);
  }

  @Override
  protected void initGl() {
    super.initGl();

    EyeDesc eyeDescs[] = (EyeDesc[]) new EyeDesc().toArray(2);
    for (int eye = 0; eye < 2; ++eye) {
      EyeDesc eyeDesc = eyeDescs[eye];

      eyeDesc.Eye = eye;
      {
        FovPort.ByValue fovPort = new FovPort.ByValue();
        fovPort.DownTan = hmdDesc.DefaultEyeFov[eye].DownTan;
        fovPort.UpTan = hmdDesc.DefaultEyeFov[eye].UpTan;
        fovPort.LeftTan = hmdDesc.DefaultEyeFov[eye].LeftTan;
        fovPort.RightTan = hmdDesc.DefaultEyeFov[eye].RightTan;
        projections[eye] = new Matrix4f(OVR.ovrMatrix4f_Projection(fovPort, 0.01f, 100000f, (byte) 1).M).transpose();
        eyeDesc.Fov = fovPort;
        eyeDesc.TextureSize = hmd.getFovTextureSize(eye, fovPort, 1.0f);
      }

      eyeDesc.RenderViewport.Size = eyeDesc.TextureSize;
      eyeDesc.RenderViewport.Pos = new Vector2i(0, 0);

      frameBuffers[eye] = new FrameBuffer(eyeDesc.TextureSize.w, eyeDesc.TextureSize.h);

      // JNA weirdness to deal with the union type.
      {
        // Create the GLTextureData type pointing to the same memory as the
        // eyeTexture
        GLTextureData eyeTexture = new GLTextureData(eyeTextures[eye].getPointer());
        eyeTexture.Header.API = OvrLibrary.ovrRenderAPIType.ovrRenderAPI_OpenGL;
        eyeTexture.Header.RenderViewport = eyeDesc.RenderViewport;
        eyeTexture.Header.TextureSize = eyeDesc.TextureSize;
        eyeTexture.TexId = frameBuffers[eye].getTexture().id;
        // Write out the structure to native memory
        eyeTexture.write();
        // Read it back into the other type
        eyeTextures[eye].read();
      }
    };

    GLConfig rc = new GLConfig();
    rc.Config = new RenderAPIConfig();
    rc.Config.Header.API = OvrLibrary.ovrRenderAPIType.ovrRenderAPI_OpenGL;
    rc.Config.Header.Multisample = 1;
    rc.Config.Header.RTSize = hmdDesc.Resolution;
    int distortionCaps = OvrLibrary.ovrDistortionCaps.ovrDistortion_Chromatic
        | OvrLibrary.ovrDistortionCaps.ovrDistortion_TimeWarp;
    int renderCaps = 0;
    if (0 == hmd.configureRendering(rc.Config, distortionCaps, renderCaps, eyeDescs, eyeRenderDescs)) {
      throw new IllegalStateException("Unable to configure rendering");
    }
  }

  @Override
  public final void drawFrame() {
    glViewport(0, 0, width, height);
    glClear(GL_COLOR_BUFFER_BIT);
    hmd.beginFrame(++frameCount);
    for (int eye = 0; eye < 2; ++eye) {
      MatrixStack.PROJECTION.set(projections[eye]);
      Posef.ByValue pose = hmd.beginEyeRender(eye);
      MatrixStack m = MatrixStack.MODELVIEW;
      m.push(); {
        m.preTranslate(pose.Position.toVector3f().mult(-1));
        m.preRotate(pose.Orientation.toQuaternion().inverse());
        float eyeOffset = (eye == 0 ? 1.0f : -1.0f) * (ipd / 2.0f);
        m.preTranslate(eyeOffset);
        frameBuffers[eye].activate();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        renderScene();
        frameBuffers[eye].deactivate();
      };
      m.pop();
      hmd.endEyeRender(eye, pose, eyeTextures[eye]);
    };
    glDisable(GL_DEPTH_TEST);
    glDisable(GL_CULL_FACE);
    hmd.endFrame();
  }

  @Override
  protected void onDestroy() {
    hmd.destroy();
  }

  @Override
  protected final void setupDisplay() {
    System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
    Rectangle targetRect = new Rectangle(
        hmdDesc.WindowsPos.x, hmdDesc.WindowsPos.y, 
        hmdDesc.Resolution.w, hmdDesc.Resolution.h
    );
    setupDisplay(targetRect);
  }

  protected abstract void renderScene();
}
