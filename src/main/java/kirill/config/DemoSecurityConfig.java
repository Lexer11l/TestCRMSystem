package kirill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.inject.Inject;
import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

  @Inject
  private DataSource securityDataSource;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception{
    auth.jdbcAuthentication().dataSource(securityDataSource);
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception{

    http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/customers").hasRole("EMPLOYEE")
            .antMatchers(HttpMethod.GET, "/api/customers/*").hasRole("EMPLOYEE")
            .antMatchers(HttpMethod.POST, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/customers/*").hasRole("ADMIN")
            .antMatchers("/customer/showForm*").hasAnyRole("MANAGER", "ADMIN")
            .antMatchers("/customer/save*").hasAnyRole("MANAGER", "ADMIN")
            .antMatchers("/customer/delete").hasRole("ADMIN")
            .antMatchers("/customer/**").hasRole("EMPLOYEE")
            .antMatchers("/resources/**").permitAll()
            .and()
            .httpBasic()
            .and().csrf().disable()
            .formLogin()
            .loginPage("/showMyLoginPage")
            .loginProcessingUrl("/authenticatedUser")
            .permitAll()
            .and()
            .logout().permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/access-denied");

  }

  @Bean
  public UserDetailsManager userDetailsManager() {
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
    jdbcUserDetailsManager.setDataSource(securityDataSource);
    return jdbcUserDetailsManager;
  }
}
