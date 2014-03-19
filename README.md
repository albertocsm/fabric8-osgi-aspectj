fabric8-osgi-aspectj-demo
======================

Fabric8 + OSGi + AspectJ demonstration code.

# Pre-requisites

* JDK 7 (don't try JDK 8 as aspects won't compile!!)
* Maven 3.1.0 or newer

# Build and install

```
mvn clean install
```

# Provisioning

## Installation and initial configuration

* Download [latest build](https://repository.jboss.org/nexus/content/repositories/ea/io/fabric8/fabric8-karaf/) for ```fabric-karaf``` and extract it.
*(tested on fabric8-karaf-1.0.0.redhat-368)*
* Extract it
* ```cd``` to the newly extracted folder
* Define default administrative user (login: **admin**, password:**admin**) by uncommenting the last line of ```etc/user.properties```
* Start Fabric
```no-highlight
bin/fusefabric
```

If everything goes well, you should get a Fabric shell that looks like this:

```
Please wait while Fabric8 is loading...
100% [========================================================================]

______    _          _      _____
|  ___|  | |        (_)    |  _  |
| |_ __ _| |__  _ __ _  ___ \ V /
|  _/ _` | '_ \| '__| |/ __|/ _ \
| || (_| | |_) | |  | | (__| |_| |
\_| \__,_|_.__/|_|  |_|\___\_____/
  Fabric8 Container (1.0.0.redhat-368)
  http://fabric8.io/

Type 'help' to get started
and 'help [cmd]' for help on a specific command.
Hit '<ctrl-d>' or 'osgi:shutdown' to shutdown this container.

Open a browser to http://localhost:8181 to access the management console

Create a new Fabric via 'fabric:create'
or join an existing Fabric via 'fabric:join [someUrls]'

Fabric8:karaf@root>
```

## Start Fabric Ensemble
```
fabric:create --clean --wait-for-provisioning
```

## Define our own profile
```
profile-create --parents feature-dosgi osgi-aspect-example
profile-edit --features fabric-cxf --features fabric-cxf-registry --features swagger osgi-aspect-example
profile-edit --bundles mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.aspectj/1.7.4_1 osgi-aspect-example
profile-edit --bundles mvn:com.github.pires.example/aspect-aspects/0.1-SNAPSHOT osgi-aspect-example
profile-edit --bundles mvn:com.github.pires.example/aspect-service/0.1-SNAPSHOT osgi-aspect-example
profile-edit --bundles mvn:com.github.pires.example/aspect-rest/0.1-SNAPSHOT osgi-aspect-example
```

## Create and run new container with newly created profile

```
container-create-child --profile osgi-aspect-example root test1
```

# Testing

In Hawt.io UI, go to ```API``` tab (in the parent container), check the host and port where ```MyServiceEndpoint``` is available and point it down. Test the REST endpoint as you wish!

## REST API

Simple test
```
GET /demo/
```

Check the container logs and you shall see the aspect output :-)
