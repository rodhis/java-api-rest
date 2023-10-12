package rodhis.java.api.security;


//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.h2.server.web.WebServlet;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//    referente ao método 1
//    @Autowired
//    private SecurityDatabaseService securityService;

//    @Autowired
//    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
private static final String[] SWAGGER_WHITELIST = {
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**"
};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable()
                .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(SWAGGER_WHITELIST).permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers(HttpMethod.POST,"/users").permitAll()
                .antMatchers(HttpMethod.GET,"/users").hasAnyRole("USERS","MANAGERS")
                .antMatchers("/managers").hasAnyRole("MANAGERS")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    @Bean //para acessar o h2 na web
    public ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/h2-console/*");
        return registrationBean;
    }

    //método 1, sem uso de DBs
   // @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("rodhis")
//                .password("{noop}rodhis123")
//                .roles("USERS")
//                .and() //adiciona outro usuário
//                .withUser("admin")
//                .password("{noop}master123")
//                .roles("MANAGERS");
//    }
}
