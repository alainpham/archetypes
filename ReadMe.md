# Bunch of useful archetypes mainly for Red Hat Fuse and other Java Projects


## Install these archetypes

```
mvn install
```

## Deploy to local nexus

```
mvn deploy -DaltDeploymentRepository=nexus::default::http://nexus:8081/repository/maven-releases
```

## camel-archetype-spring-boot-2-fuse-7-bom

Fuse 7 on Spring Boot 2 for Openshift archetype. 

Includes prometheus jmx exporter config for performance monitoring.
See this [repo](https://github.com/alainpham/app-archetypes) for more details on how to setup monitoring with Prometheus and Grafana.

Demo video of monitoring (here http://www.youtube.com/watch?v=0LDgv1nIk-Y

To create a project :

```
mvn archetype:generate -DarchetypeGroupId=org.apache.camel -DarchetypeArtifactId=camel-archetype-spring-boot-2-fuse-7-bom -DarchetypeVersion=7
```

## camel-archetype-eap-fuse-7-bom

Fuse 7 archetype using CDI for EAP / Wildfly

For montitoring with Prometheus & Grafana, we can setup something similar to the Spring Boot 2 flavor.
See this [repo](https://github.com/alainpham/dev-env-scripts/tree/master/eap) for instructions (in Dockerfile) for setting up EAP server with JMX Exporter.


To create a project :

```
mvn archetype:generate -DarchetypeGroupId=org.apache.camel -DarchetypeArtifactId=camel-archetype-spring-boot-2-fuse-7-bom -DarchetypeVersion=7
```
