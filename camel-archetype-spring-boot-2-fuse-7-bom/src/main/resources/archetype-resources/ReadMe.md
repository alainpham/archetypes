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

make sure you have the image streams deployed

```
BASEURL=https://raw.githubusercontent.com/jboss-fuse/application-templates/application-templates-${fuse-app-template-version}

oc create -n openshift -f ${BASEURL}/fis-image-streams.json
oc replace -n openshift -f ${BASEURL}/fis-image-streams.json
```

```
mvn -P ocp fabric8:deploy fabric8:build
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
docker stop ${artifactId}
docker rm ${artifactId}
docker rmi ${artifactId}
docker build -t ${artifactId} .
docker run -d --net primenet --ip 172.18.0.10 --name ${artifactId} ${artifactId}
```

Stop or launch multple instaces

```
NB_CONTAINERS=2
for (( i=0; i<$NB_CONTAINERS; i++ ))
do
   docker stop ${artifactId}-$i
   docker rm ${artifactId}-$i
done

docker rmi ${artifactId}
docker build -t ${artifactId} .

for (( i=0; i<$NB_CONTAINERS; i++ ))
do
    docker run -d --net primenet --ip 172.18.0.1$i --name ${artifactId}-$i -e SPRING_PROFILES_ACTIVE=dev ${artifactId}
done
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
oc start-build ${artifactId}-s2i --from-dir=deploy --follow
```