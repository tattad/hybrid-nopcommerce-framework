@echo off
title Starting nopCommerce
cd /d "D:\Automation Testing\01-Software\nopCommerce_4.90.4_NoSource_win_x64"
echo Running server nopCommerce... Please wait!
echo.

dotnet Nop.Web.dll --urls "http://localhost:5000"

pause