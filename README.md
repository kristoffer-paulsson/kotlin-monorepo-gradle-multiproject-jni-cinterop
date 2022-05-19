# Kotlin Monorepo Gradle Multiproject JNI cinterop

A living example of Kotlin/Multiplatform with Java JNI integration and cinterop native integration.

## NOTE: for Windows use
**Kotlin/Multiplatform doesn't run well with Visual C++.**

_Therefore Windows users need to download and setup either Cygwin or MSYS2, here comes a brief instruction on how to setup MSYS2 with GIT, OpenJDK and Gradle._

_This brief instruction does not tell how to make Intellij IDEA work internally with MSYS2._

1. Download the Microsoft build of [OpenJDK](https://docs.microsoft.com/en-us/java/openjdk/download) and install to `C:\Users\Forename Surname\.jdks`
   
   Presumably [microsoft-jdk-16.0.2.7.1-windows-x64](https://aka.ms/download-jdk/microsoft-jdk-16.0.2.7.1-windows-x64.msi).

2. Download [MSYS2](https://www.msys2.org/) and install on your Windows computer. 
3. Then open `MSYS2 MinGW x64` from your start menu. From within the MSYS2 Terminal, do the following:
   1. Install Git `pacman -S git`.
   2. Install Gradle `pacman -S gradle`.
   3. Install a toolchain `pacman -S mingw-w64-x86_64-toolchain`.
   4. Go to your KMP project folder ` cd /c/Users/Forename\ Surname/IdeaProjects/kotlin-monorepo-gradle-multiproject-jni-cinterop/`.
   5. Create the JAVA_HOME environment variable for Gradle `export JAVA_HOME="C:\Users\Forename Surname\.jdks\openjdk-16+36_windows-x64_bin\jdk-16"`
   6. Run Gradle commands `./gradlew <command>`.