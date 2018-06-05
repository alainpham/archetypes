# Red Hat JBoss Fuse 7 Camel Spring Boot DSL Archetype 

This is a Red Hat JBoss Fuse 7 Camel Spring DSL Archetype that uses the parent BOM to handle dependency versions. It allows to generate an OSGI bundle that can be deployed on the Fuse Karaf Server.

To install the archetype run

	mvn install

To use it do

	mvn archetype:generate -DarchetypeGroupId=org.apache.camel -DarchetypeArtifactId=camel-archetype-spring-boot-fuse-7-bom -DarchetypeVersion=7.0.0

## Running the project

Once you project created you can run it with

	mvn camel:run

You can create a standalone runnable all in one zip package with the runnable profile. It uses hawtapp maven plugin to achieve this.

	mvn package -Prunnable

Find your .zip package or hawtapp folder and run the run.sh script to launch your project.
