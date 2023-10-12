package rodhis.java.api.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import rodhis.java.api.config.SecurityDatabaseService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityDatabaseService securityService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll() //permite qualquer autenticação acessar a rota
                .antMatchers(HttpMethod.POST,"/login").permitAll() //para esta rota somente pode ter requisições POST
                .antMatchers("/managers").hasAnyRole("MANAGERS") //somente essas autenticações podem acessar a roda
                .antMatchers("/users").hasAnyRole("USERS","MANAGERS")
                .anyRequest().authenticated().and().httpBasic(); //também tem formLogin(), acessa a tela do security
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
