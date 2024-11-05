#!/bin/bash

source ../../build.properties
if [[ -f ../../.env ]]; then source ../../.env; fi

NAME=www
NAMESPACE=www
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
      kind: Certificate
    patch: |-
      - op: replace
        path: /spec/commonName
        value: www.$DOMAIN_NAME
      - op: replace
        path: /spec/dnsNames/0
        value: www.$DOMAIN_NAME
      - op: replace
        path: /metadata/name
        value: $NAME-$REFINED_DOMAIN_NAME-cert
      - op: replace
        path: /spec/secretName
        value: $NAME-$REFINED_DOMAIN_NAME-tls
  - target:
      kind: Gateway
    patch: |-
      - op: replace
        path: /metadata/name
        value: $NAME-$REFINED_DOMAIN_NAME-gateway
      - op: replace
        path: /spec/listeners/0/hostname
        value: www.$DOMAIN_NAME
      - op: replace
        path: /spec/listeners/1/hostname
        value: www.$DOMAIN_NAME
      - op: replace
        path: /spec/listeners/1/tls/certificateRefs/0/name
        value: $NAME-$REFINED_DOMAIN_NAME-tls
EOF

# doing this two-step procedure just for the transparency of the changes, to allow post-reviewing
kubectl kustomize $TARGET_FOLDER | tee $TARGET_FOLDER/manifest.yaml
kubectl apply -f $TARGET_FOLDER/manifest.yaml

