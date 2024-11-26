@echo off
setlocal enabledelayedexpansion

:: Verifica se foi passado pelo menos o argumento da porta
if "%~1"=="" (
    echo Uso: LaunchNode.bat ^<porta^> [caminho para ficheiro]
    exit /b 1
)

:: Define variáveis
set ADDRESS=localhost
set PORT=%1
set FILE_PATH=%~2
set COSTANTE=8084
set /a COSTANTE-=PORT

:: Compila os arquivos Java
set SOURCE_FILES=
for /r src %%f in (*.java) do (
    set SOURCE_FILES=!SOURCE_FILES! %%f
)
javac -d bin %SOURCE_FILES%

:: Verifica se a compilação foi bem-sucedida
if %errorlevel% neq 0 (
    echo Erro ao compilar o código Java.
    exit /b %errorlevel%
)

:: Se o caminho do ficheiro for passado, verifica se ele existe
if not "%FILE_PATH%"=="" (
    if not exist "%FILE_PATH%" (
        echo O arquivo especificado nao existe: %FILE_PATH%
        exit /b 1
    )
)

:: Executa o NodeStarter com o endereço, porta e, opcionalmente, o caminho do ficheiro
if "%FILE_PATH%" equ [] (
    java -cp bin IscTorrent.NodeStarter %ADDRESS% %COSTANTE%
) else (
    java -cp bin IscTorrent.NodeStarter %ADDRESS% %COSTANTE% "%FILE_PATH%"
)

endlocal
pause