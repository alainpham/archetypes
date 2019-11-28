package ${package};

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.apache.camel.CamelContext;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.ImportResource;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Named("${artifactId}App")
@ImportResource("camel-context.xml")
public class Application{

    @Inject
    @ContextName("${artifactId}")
    CamelContext context;

    @Resource(mappedName = "java:/TransactionManager")
    private TransactionManager transactionManager;
    
    @Resource(mappedName = "java:/RemoteJmsConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource 
    private UserTransaction userTransaction;

    @Produces
    @Named("jms")
    public JmsComponent createJmsComponent(PlatformTransactionManager transactionManager) {
        return JmsComponent.jmsComponentTransacted(connectionFactory, transactionManager);
    }

    @Produces
    @Named("transactionManager")
    public PlatformTransactionManager createTransactionManager() {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setUserTransaction(userTransaction);
        jtaTransactionManager.setTransactionManager(transactionManager);
        jtaTransactionManager.afterPropertiesSet();
        return jtaTransactionManager;
    }


}