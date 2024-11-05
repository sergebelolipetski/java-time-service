# Time-service

The application written in Java to demonstrate various aspects of deployment in Ambient service mesh facilitated by Istio in Kubernetes cluster.

## Configure the build

See defaults in [build.properties](build.properties). To adjust those, create `.env` file and override credentials and URL to docker repository if needed.

## Build

```shell
./build.sh
```

At the end of the build, the produced image will be pushed to the configured docker repository. Please, note that the target cluster must be able to pull images from that repo.


## Deploy

Deployment is done to the currently available cluster. Use `KUBECONFIG` variable to switch clusters.

```shell
./deploy.sh
```


## Call the deployed service

See [readme.md](specs/app/readme.md) for details.

