# Getting started with maven-wsdl-builder-plugin

This archive contains a complete Maven project, which uses the maven-wsdl-builder-plugin. After extracting the archive, you can launch a local Jetty instance by running mvn jetty:run-war.

If the server has started up, two webservices are accessible on localhost:

 * http://localhost:8080/jaxws-server-example/Products
 * http://localhost:8080/jaxws-server-example/Monitor

The first is an examplary service for finding products of a shop. The latter one returns the number of calls for the two services.
