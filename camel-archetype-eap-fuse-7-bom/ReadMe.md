# Red Hat JBoss Fuse 7 Camel EAP Archetype 

This is a Red Hat JBoss Fuse 7 Camel EAP Spring DSL Archetype that uses the parent BOM to handle dependency versions. It allows to generate a War file to be deployed on EAP

To install the archetype run

	mvn install

To use it do

	mvn archetype:generate -DarchetypeGroupId=org.apache.camel -DarchetypeArtifactId=camel-archetype-eap-fuse-7-bom -DarchetypeVersion=7.1.0

## Running the project

Once you project created you can run it with (need a local EAP instance running)

	mvn clean package wildfly:run
