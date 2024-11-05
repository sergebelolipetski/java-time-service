#!/bin/bash

source build.properties
if [[ -f .env ]]; then source .env; fi

VERSION=0.0.1
TAG=$DOCKER_IMAGE_PREFIX/java-time-service:$VERSION

docker login $DOCKER_REGISTRY_URL -u $DOCKER_REGISTRY_USER -p $DOCKER_REGISTRY_PASSWORD >/dev/null
docker build -t $TAG .
docker push $TAG
docker logout $DOCKER_REGISTRY_URL
