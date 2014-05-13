@echo off

REM
REM Bootstrap Script for Windows
REM

REM
REM %~dp0 is expanded pathname of the current script under NT
set MATRIX_HOME=%~dp0..
set MATRIX_EXEC_ARGS=%*
set JAVA_CMD="java.exe"
set MATRIX_LAUNCHER=MatrixCalculator.jar
set CLASSPATH="."

"%JAVA_CMD%" "-Dmatrix.home=%MATRIX_HOME%" -cp "%CLASSPATH%" -jar "%MATRIX_HOME%\lib\%MATRIX_LAUNCHER%" %MATRIX_EXEC_ARGS%

exit
