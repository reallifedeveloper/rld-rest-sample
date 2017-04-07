RLD-REST-SAMPLE
===============

A sample project demonstrating a simple way to create a test version of a REST client service that reads JSON
from a file instead of through HTTP.

Build using the command: `mvn -DcheckAll clean install`

Run using the command: `java -Dserver.port=8081 -jar target/rld-rest-sample-1.0.jar`

Test using the URLs <http://localhost:8081/countries> and <http://localhost:8081/countries/IND>

See <http://reallifedeveloper.com/reading-json-files-to-create-test-versions-of-rest-clients/> for
more information.
