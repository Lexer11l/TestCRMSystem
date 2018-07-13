package kirill.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan("kirill")
@PropertySource({"classpath:persistence-mysql.properties",
        "classpath:security-persistence-mysql.properties"})
public class DemoAppConfig implements WebMvcConfigurer {

  @Autowired
  private Environment environment;

  private static final Logger LOGGER = Logger.getLogger(DemoAppConfig.class);

  @Bean
  public ViewResolver viewResolver() {

    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

    viewResolver.setPrefix("/WEB-INF/view/");
    viewResolver.setSuffix(".jsp");

    return viewResolver;
  }

  @Bean
  public DataSource myDataSource(){
    ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
    try{
      LOGGER.info("JDBC URL :" + getProperty("jdbc.driver"));
      LOGGER.info("JDBC USER :" + getProperty("jdbc.user"));

      securityDataSource.setDriverClass(getProperty("jdbc.driver"));
      securityDataSource.setJdbcUrl(getProperty("jdbc.url"));
      securityDataSource.setUser(getProperty("jdbc.user"));
      securityDataSource.setPassword(getProperty("jdbc.password"));

      securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
      securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
      securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
      securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

    } catch (PropertyVetoException e) {
      e.printStackTrace();
    }

    return securityDataSource;
  }

  private int getIntProperty(String key){
    return Integer.parseInt(getProperty(key));
  }

  private String getProperty(String key){
    return environment.getProperty(key);
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory(){
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(myDataSource());
    sessionFactory.setPackagesToScan(getProperty("hibernate.packagesToScan"));
    sessionFactory.setHibernateProperties(getHibernateProperties());
    return sessionFactory;
  }

  private Properties getHibernateProperties() {
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", getProperty("hibernate.dialect"));
    props.setProperty("hibernate.show_sql", getProperty("hibernate.show_sql"));
    return props;
  }

  @Bean
  public DataSource securityDataSource() {
    ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

    try {
      securityDataSource.setDriverClass(getProperty("security.jdbc.driver"));
    } catch (PropertyVetoException exc) {
      throw new RuntimeException(exc);
    }

    LOGGER.info(">>> security.jdbc.url=" + getProperty("security.jdbc.url"));
    LOGGER.info(">>> security.jdbc.user=" + getProperty("security.jdbc.user"));

    securityDataSource.setJdbcUrl(getProperty("security.jdbc.url"));
    securityDataSource.setUser(getProperty("security.jdbc.user"));

    securityDataSource.setPassword(getProperty("security.jdbc.password"));

    securityDataSource.setInitialPoolSize(getIntProperty("security.connection.pool.initialPoolSize"));
    securityDataSource.setMinPoolSize(getIntProperty("security.connection.pool.minPoolSize"));
    securityDataSource.setMaxPoolSize(getIntProperty("security.connection.pool.maxPoolSize"));
    securityDataSource.setMaxIdleTime(getIntProperty("security.connection.pool.maxIdleTime"));

    return securityDataSource;
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);
    return txManager;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
  }
}
