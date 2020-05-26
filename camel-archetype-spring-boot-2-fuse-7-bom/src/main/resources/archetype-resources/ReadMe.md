#[[# Spring Boot with camel and other useful things]]# ${artifactId} 

#[[## To build this project use]]#

```
mvn install
```

#[[## To run this project with Maven use]]#

```
mvn spring-boot:run
```

#[[## To deploy directly on openshift]]#

```
mvn -P ocp fabric8:deploy
```

#[[## For testing]]#

```
curl http://localhost:8090/camel/api-docs
curl http://localhost:8090/camel/ping
```


#[[## Acces Swagger UI with definition]]#

```
http://localhost:8090/webjars/swagger-ui/index.html?url=/camel/api-docs
```

#[[## Call the ping rest operation]]#
```
curl http://localhost:8090/camel/restsvc/ping
```

#[[## Run local container with specific network and IP address]]#


```
docker build -t ${artifactId} .
docker run -d --net primenet --ip 172.18.0.10 --name ${artifactId} ${artifactId}
```

#[[## To release without deploying straight to an ocp cluster]]#

```
mvn  -P ocp package
```

#[[## To deploy using binary build on ocp]]#

```
tar xzvf ${artifactId}-ocp.tar.gz
cd ${artifactId}
oc apply -f openshift.yml
oc start-build ${artifactId} --from-file=${artifactId}.jar --follow
```