package com.oculusvr.capi;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : /usr/include/stdint.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class EyeRenderDesc extends Structure {
	/**
	 * @see ovrEyeType<br>
	 * C type : ovrEyeType
	 */
	public int Eye;
	/** C type : ovrFovPort */
	public FovPort Fov;
	/**
	 * Distortion viewport<br>
	 * C type : ovrRecti
	 */
	public OvrRecti DistortedViewport;
	/**
	 * How many display pixels will fit in tan(angle) = 1.<br>
	 * C type : ovrVector2f
	 */
	public OvrVector2f PixelsPerTanAngleAtCenter;
	/**
	 * Translation to be applied to view matrix.<br>
	 * C type : ovrVector3f
	 */
	public OvrVector3f ViewAdjust;
	public EyeRenderDesc() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("Eye", "Fov", "DistortedViewport", "PixelsPerTanAngleAtCenter", "ViewAdjust");
	}
	/**
	 * @param Eye @see ovrEyeType<br>
	 * C type : ovrEyeType<br>
	 * @param Fov C type : ovrFovPort<br>
	 * @param DistortedViewport Distortion viewport<br>
	 * C type : ovrRecti<br>
	 * @param PixelsPerTanAngleAtCenter How many display pixels will fit in tan(angle) = 1.<br>
	 * C type : ovrVector2f<br>
	 * @param ViewAdjust Translation to be applied to view matrix.<br>
	 * C type : ovrVector3f
	 */
	public EyeRenderDesc(int Eye, FovPort Fov, OvrRecti DistortedViewport, OvrVector2f PixelsPerTanAngleAtCenter, OvrVector3f ViewAdjust) {
		super();
		this.Eye = Eye;
		this.Fov = Fov;
		this.DistortedViewport = DistortedViewport;
		this.PixelsPerTanAngleAtCenter = PixelsPerTanAngleAtCenter;
		this.ViewAdjust = ViewAdjust;
	}
	public EyeRenderDesc(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends EyeRenderDesc implements Structure.ByReference {
		
	};
	public static class ByValue extends EyeRenderDesc implements Structure.ByValue {
		
	};
}
