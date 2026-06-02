@echo off
title Starting nopCommerce
echo Running server nopCommerce... Please wait!
echo.

dotnet Nop.Web.dll --urls "http://localhost:5050"

pause