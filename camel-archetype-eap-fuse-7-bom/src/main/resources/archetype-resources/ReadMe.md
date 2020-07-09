#[[# Red Hat Fuse on EAP Spring XML Web Application]]# ${artifactId}

#[[## Deploy]]#
Deploy on EAP server
Make sure to configure wildfly plugin in pom.xml file with credentials for dev server.

```
mvn clean package wildfly:deploy
```

Acces to swagger def

http://eap:8080/${artifactId}/webjars/swagger-ui/3.28.0/index.html?url=/${artifactId}/camel/api-docs


For testing

```
curl http://eap:8080/${artifactId}/camel/api-docs
curl http://eap:8080/${artifactId}/camel/ping
```

#[[## Undeploying the application]]#
---------------------------

```
mvn wildfly:undeploy
```
