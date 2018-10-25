# Red Hat JBoss Fuse 7 Camel Blueprint DSL Archetype

This is a Red Hat JBoss Fuse 7.1 Camel Blueprint DSL Archetype that uses the parent BOM to handle dependency versions. It allows to generate an OSGI bundle that can be deployed on the Fuse Karaf Server.

To install the archetype run

	mvn install

To use it do

	mvn archetype:generate -DarchetypeGroupId=org.apache.camel -DarchetypeArtifactId=camel-archetype-karaf-fuse-7-bom -DarchetypeVersion=7.1.0

## Running the project

Once you project created you can run it with

	mvn package camel:run



