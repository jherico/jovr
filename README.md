Java bindings for using the Oculus Rift head tracker

Changes

0.4.3.0

* Initial support for Oculus SDK 0.4.3
* Linux support added to SDK, linux binary added

0.4.2.0

* Initial support for Oculus SDK 0.4.2
* Exposing high quality rendering flag (no effect on GL) 

0.4.1.2

* Exposing HSW render disable method 

0.4.1.1

* updating binaries

0.4.1.0

* Initial support for Oculus SDK 0.4.1
* OSX support added to SDK, OSX binary added

0.4.0.1

* Adding some annotations and missing functions

0.4.0.0

* Initial support for Oculus SDK 0.4.0
* Removed swapbuffer callback mechanism

0.3.2.5

* Adding swapbuffer callback

0.3.2.4

* Adding static init and shutdown methods to convenience class

0.3.2.3

* Added Guava dependency
* Added some argument checking with more informative errors than a crash inside the binary would provide
* Added JSR305 dependency
* Updated some Pointer types to String
* Moved OvrLibrary.ovrHmd type to Hmd
* Removed the need to expose the .ByValue types to clients
* Added some static methods to Hmd to remove the need to call OvrLibrary.INSTANCE directly
* Changed some functions which had output arguments to return the output instead:  i.e. 'void foo(String[] results)' becomes 'String [] foo()'
* Defaulted all API values to OpenGL.
* Added some unit tests   

0.3.2.2

* Updated SDK binary.  
* Added the ovrDistortionCap_NoSwapBuffers flag for preventing SDK side buffer swapping 

0.3.2.1

* Fixed incorrect Linux binary.


0.3.2.0

* Initial release of Java bindings for Oculus SDK 0.3.2
* Renamed artifact from Jocular to JOVR to reset numbering


Modified the OculusSDK to include a new value for ovrDistortionCaps: ovrDistortionCap_NoSwapBuffers

If this value isn't specified in ovrHmd_ConfigureRendering then the Oculus SDK will revert to it's default behavior of performing the SwapBuffers() call inside the SDK. However, if you include this value when configuring rendering, then the SDK will continue to exhibit the modified behavior I've introduced, and NOT perform the SwapBuffers() call in the SDK. 

Most Java applications will want to include this new distortion cap value, since the parameters required for buffer swapping are not normally exposed to Java client applications.

Update 5/26:
Updated to 0.3.2.1. The Linux binary was a 32 bit shared object, instead of 64 bit. 

Update 5/24:
I've built a new release of the Maven jars to connect with the Oculus SDK version 0.3.2. Like my previous releases, I've disabled the buffer swapping in the SDK, which means you don't need to pass any window information to the SDK in the configureRendering call. 

I've also renamed the Artifact so that I can reset the versioning and match the Java jar version to the Oculus SDK version. Adding a dependency on the Java bindings should now look like this.

CODE: SELECT ALL
    <dependency>
      <groupId>org.saintandreas</groupId>
      <artifactId>jovr</artifactId>
      <version>[0.3.2.0, 0.3.3)</version>
    </dependency>


The Jar no longer depends on another jar for the binaries. Rather, the binary shared library files are baked into the main jar. The only dependency of this jar is the JNA library, which I have bumped up to 4.1.

Since the SDK now supports Linux natively, I'm using their Linux code. I've also added the OSX binary as well. Both Linux and OSX are 64-bit only. Windows still has 32 and 64 bit jars built in. 

Update 5/21:
I've migrated my releases to the Maven central repository. You can now use the Oculus SDK simply by adding the following into your maven project file:

CODE: SELECT ALL
    <dependency>
      <groupId>org.saintandreas</groupId>
      <artifactId>jocular</artifactId>
      <version>[2.0.2, 3)</version>
    </dependency>


My example project has been updated to reflect the latest version of the code and the central repository hosting. 

The Java example project can be found independently in it's own repository here: https://github.com/jherico/jocular-examples

The main branch of the examples project consists of only three files. The Maven project file, a RiftApp base class for creating simple applications for the Rift, and a single RiftDemo class which actually draws a simple scene with a skybox and number of cubes surrounding the user.

Update 5/4:

As I mentioned in this thread, I've refactored my Github copy of the Oculus SDK to extract the C API out of the main body of code and made an individual project out of it, which produces a standalone DLL. I've now created a JNA binding for that DLL that will let you use the C API from Java. 

The Java project for the bindings can be found here: https://github.com/jherico/jocular <--- new URL

The URL has changed from the old location in my OculusSDK repository, because I forgot how annoying it is to try to release a Maven project from within a larger repository. 