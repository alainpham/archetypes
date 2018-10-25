package ${package};

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

/**
 * A unit test to verify the Camel route works as designed.
 */
public class BlueprintTest extends CamelBlueprintTestSupport {

	// Expected message bodies
	protected String expectedBodies = "hello"; // empty to start

	// Templates to send to input endpoints
	@Produce(uri = "direct:test")
	protected ProducerTemplate inputEndpoint;

	// Mock endpoints used to consume messages from the output endpoints and
	// then perform assertions
	@EndpointInject(uri = "mock:out")
	protected MockEndpoint out;

	@Test
	public void testCamelRoute() throws Exception {
		// Create routes from the output endpoints to our mock endpoints so we
		// can assert expectations
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:test").to("direct:dummy").to(out);
			}
		});


		out.expectedBodiesReceived(expectedBodies);

		// Send some messages to input endpoints
		inputEndpoint.sendBody(null);

		// Validate our expectations
		assertMockEndpointsSatisfied();
	}

	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}

	/*
	 * Pull source from a text file.
	 * 
	 * @param path of the file.
	 * 
	 * @return string matching the contents of the file
	 * 
	 * @throws Exception any exception encountered
	 */
	private String getFileContents(String path) throws Exception {
		Path filePath = new File(path).toPath();
		return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
	}
}
