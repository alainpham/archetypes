package ${package};


import java.io.InputStream;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import org.apache.camel.cdi.CdiRouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.servlet.ServletComponent;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestDefinition;
import org.apache.camel.model.rest.RestsDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@ContextName("${artifactId}")
public class CamelConfigurator extends CdiRouteBuilder {

	final Logger logger = LoggerFactory.getLogger(CamelConfigurator.class);


	@Override
	public void configure() throws Exception {
        
        ServletComponent  servletComponent = getContext().getComponent("servlet",ServletComponent.class);
        servletComponent.setServletName("CamelServlet-${artifactId}");

		restConfiguration()
		.component("servlet")
		.bindingMode(RestBindingMode.json)
		.contextPath("/${artifactId}/camel")
		.port(getContext().resolvePropertyPlaceholders("{{sys:jboss.http.port:8080}}"))
		.apiContextPath("/api-docs")
		.host(getContext().resolvePropertyPlaceholders("{{sys:service.advertise.hostname:localhost}}"))
        .dataFormatProperty("prettyPrint", "true")
        .apiProperty("api.title", "${artifactId}")
        .apiProperty("api.version", "${version}")
        .apiProperty("cors", "true");
		
        InputStream is = getClass().getResourceAsStream("/camel-rest/rests.xml");
        if (is != null) {
            RestsDefinition r = getContext().loadRestsDefinition(is);
            getContext().addRestDefinitions(r.getRests());
            for (final RestDefinition restDefinition : r.getRests()) {
                List<RouteDefinition> routeDefinitions = restDefinition.asRouteDefinition(getContext());
                getContext().addRouteDefinitions(routeDefinitions);
            }
        }
	}

}
