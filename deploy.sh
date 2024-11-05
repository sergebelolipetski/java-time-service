#!/bin/bash

CWD=$(pwd)

cd $CWD/specs/www
./install_www.sh

cd $CWD/specs/app
./install_app.sh

# forced restart of the deployments
kubectl rollout restart deployments -l app.kubernetes.io/part-of=sample -n sample
