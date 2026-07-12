@echo off
setlocal EnableExtensions
cd /d "%~dp0"

if not exist "dist" mkdir dist

echo [build] autologin...
call gradlew.bat createReleaseJar --no-daemon
if errorlevel 1 (
  echo [build] gradle failed
  exit /b 1
)

if not exist "build\libs\autologin-release.jar" (
  echo [build] jar not found
  exit /b 1
)

copy /y "build\libs\autologin-release.jar" "dist\autologin.jar" >nul
echo [build] done: dist\autologin.jar
dir "dist\autologin.jar"
exit /b 0
