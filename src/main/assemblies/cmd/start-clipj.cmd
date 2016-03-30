@Echo OFF
Echo.

Echo LAUNCH DIRECTORY
Echo ----------------
Echo "%~dp0"
Echo. 

Echo CURRENT DIRECTORY
Echo -----------------
Echo "%CD%"
Echo. 

Set PD=%~dp0..
Set JAVA_BIN=javaw.exe
Echo JAVA BIN
Echo --------
Echo "%JAVA_BIN%"
Echo. 

Echo JAVA VERSION
Echo ------------
%JAVA_BIN% -version
Echo.

Echo START CLIPJ
Echo -----------
start %JAVA_BIN% -jar %PD%\lib\${project.build.finalName}.jar