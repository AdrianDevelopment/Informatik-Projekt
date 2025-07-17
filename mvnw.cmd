@echo off
setlocal

set "MAVEN_PROJECT_BASEDIR=%~dp0"
if "%MAVEN_PROJECT_BASEDIR:~-1%"=="\" set "MAVEN_PROJECT_BASEDIR=%MAVEN_PROJECT_BASEDIR:~0,-1%"

set "MAVEN_HOME=%MAVEN_PROJECT_BASEDIR%\.mvn"
set "MAVEN_WRAPPER_JAR=%MAVEN_HOME%\wrapper\maven-wrapper.jar"
set "MAVEN_WRAPPER_PROPERTIES=%MAVEN_HOME%\wrapper\maven-wrapper.properties"

set MAVEN_OPTS=
if exist "%MAVEN_PROJECT_BASEDIR%\.mvn\maven.config" (
  for /F "usebackq tokens=*" %%a in ("%MAVEN_PROJECT_BASEDIR%\.mvn\maven.config") do set "MAVEN_OPTS=%MAVEN_OPTS% %%a"
)

if not exist "%MAVEN_WRAPPER_JAR%" (
  echo Downloading maven-wrapper.jar...
  powershell -Command "$props = convertfrom-stringdata (get-content -raw -path \"%MAVEN_WRAPPER_PROPERTIES%\"); $url = $props.wrapperUrl; $outFile = \"%MAVEN_WRAPPER_JAR%\"; $ProgressPreference = 'SilentlyContinue'; Invoke-WebRequest -Uri $url -OutFile $outFile"
)

set "MAVEN_CMD_LINE_ARGS=%*"
set "JAVA_EXE=%JAVA_HOME%\bin\java.exe"

"%JAVA_EXE%" -Dmaven.multiModuleProjectDirectory="%MAVEN_PROJECT_BASEDIR%" %MAVEN_OPTS% -cp "%MAVEN_WRAPPER_JAR%" org.apache.maven.wrapper.MavenWrapperMain %MAVEN_CMD_LINE_ARGS%
endlocal
exit /B %ERRORLEVEL%