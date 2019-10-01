package ${package};

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangeProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static net.logstash.logback.argument.StructuredArguments.*;

import java.util.Date;

/**
 * A sample transform
 */
@Component
public class EventLogger {

    private static final Logger logger = LoggerFactory.getLogger(EventLogger.class);

    public void logStartAndBody(@ExchangeProperty(value = Exchange.CREATED_TIMESTAMP) Date startDate, @Body Object body) {
        
        Date logDate = new Date();
        logger.info("Route has started",
        kv("state","started"),
        kv("startDate",startDate),
        kv("logDate",logDate),
        kv("elapsed", logDate.getTime() - startDate.getTime()),
        kv("body",body)
        );
    }

    public void logElapsedTimeAndBody(@ExchangeProperty(value = Exchange.CREATED_TIMESTAMP) Date startDate, @ExchangeProperty(value= Exchange.EXCEPTION_CAUGHT) Exception exception, @Body Object body) {
        
        Date logDate = new Date();
        
        if (exception==null){
                logger.info(null,
                kv("state","success"),
                kv("startDate",startDate),
                kv("logDate",logDate),
                kv("elapsed", logDate.getTime() - startDate.getTime()),
                kv("body",body)
                );
            }
        else
            {
                logger.error(exception.getMessage(),
                kv("state","error"),
                kv("startDate",startDate),
                kv("logDate",logDate),
                kv("elapsed", logDate.getTime() - startDate.getTime()),
                kv("body",body)
                );
            }


    }
    
}