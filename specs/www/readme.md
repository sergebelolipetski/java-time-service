# www-gateway

This is a set of manifests kustomized during installation, to create or update www-gateway used for exposing applications using Istio Ambient service mesh gateway.

## Install 

```shell
./install_www.sh
```

After the installation, there will be a folder `.target` created, where you can find the `kustomization.yaml` generated for producing `manifest.yaml`, which eventually goes to the cluster - the files kept on the filesystem just for the purpose of reviewing, and that can be safely removed.

## Uninstall

```shell
./uninstall_www.sh
```
