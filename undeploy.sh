#!/bin/bash

CWD=$(pwd)

cd $CWD/specs/www
./uninstall_www.sh

cd $CWD/specs/app
./uninstall_app.sh
