#!/bin/bash

source ../../build.properties
if [[ -f ../../.env ]]; then source ../../.env; fi

NAME=sample
NAMESPACE=sample
TARGET_FOLDER=$(pwd)/.target
mkdir -p $TARGET_FOLDER

REFINED_DOMAIN_NAME=$(echo $DOMAIN_NAME | sed 's/\./-/g')

cat <<EOF | tee $TARGET_FOLDER/kustomization.yaml >/dev/null
apiVersion: kustomize.config.k8s.io/v1beta1
kind: Kustomization
namespace: $NAMESPACE
resources:
  - ../base
patches:
  - target:
      kind: Deployment
    patch: |-
      - op: replace
        path: /spec/template/spec/containers/0/image
        value: $DOCKER_IMAGE_PREFIX/java-time-service:0.0.1
  - target:
      kind: HTTPRoute
    patch: |-
      - op: replace
        path: /spec/hostnames/0
        value: www.$DOMAIN_NAME
      - op: replace
        path: /spec/parentRefs/0/name
        value: www-$REFINED_DOMAIN_NAME-gateway
EOF

# doing this two-step procedure just for the transparency of the changes, to allow post-reviewing
kubectl kustomize $TARGET_FOLDER | tee $TARGET_FOLDER/manifest.yaml
kubectl apply -f $TARGET_FOLDER/manifest.yaml

