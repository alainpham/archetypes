package ${package};

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.apache.camel.CamelContext;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.ImportResource;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.sql.SqlComponent;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

@Named("${artifactId}App")
@ImportResource("camel/routes.xml")
public class Application{

    @Inject
    @ContextName("${artifactId}")
    CamelContext context;

    @Resource(mappedName = "java:/TransactionManager")
    private TransactionManager transactionManager;
    
    @Resource(mappedName = "java:/JmsConnectionFactory")
    private ConnectionFactory connectionFactory;

    // @Resource 
    // private UserTransaction userTransaction;

    //change this to be able to use database connections
    @Resource(mappedName = "java:jboss/datasources/postgresqlds") 
    private DataSource dataSource;

    @Produces
    @Named("sql")
    public SqlComponent createSqlComponent(){
        SqlComponent sql= new SqlComponent(context);
        sql.setDataSource(dataSource);
        return sql;
    }

    @Produces
    @Named("jms")
    public JmsComponent createJmsComponent() {
        // return JmsComponent.jmsComponent(connectionFactory);
        return JmsComponent.jmsComponentTransacted(connectionFactory);
    }

    // @Produces
    // @Named("transactionManager")
    // public PlatformTransactionManager createTransactionManager() {
    //     JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
    //     jtaTransactionManager.setUserTransaction(userTransaction);
    //     jtaTransactionManager.setTransactionManager(transactionManager);
    //     jtaTransactionManager.afterPropertiesSet();
    //     return jtaTransactionManager;
    // }



}