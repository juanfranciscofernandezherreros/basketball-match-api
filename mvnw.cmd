@echo off
setlocal
set BASE_DIR=%~dp0
set MAVEN_VERSION=3.9.11
set MAVEN_HOME=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%
if not exist "%MAVEN_HOME%\bin\mvn.cmd" (
  echo Maven Wrapper bootstrap on Windows requires Maven %MAVEN_VERSION% to be preinstalled or the distribution to be provisioned.
  mvn %*
  exit /b %ERRORLEVEL%
)
"%MAVEN_HOME%\bin\mvn.cmd" %*
