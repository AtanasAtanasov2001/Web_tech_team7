package com.chess.chess.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{


    private final LoginCustomerDetails loginCustomerDetails;
    private final Filter jwtFilter;

    public SecurityConfiguration(LoginCustomerDetails loginCustomerDetails, Filter jwtFilter)
    {
        this.loginCustomerDetails = loginCustomerDetails;
        this.jwtFilter = jwtFilter;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(loginCustomerDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST,"/registration/**").permitAll()
                .antMatchers(HttpMethod.GET,"/registration/**").permitAll()
                .antMatchers(HttpMethod.POST, "/customer-login/**").permitAll()
                .antMatchers(HttpMethod.GET, "/customer/**").permitAll()
                .antMatchers(HttpMethod.GET, "/state/**").permitAll()
                .antMatchers(HttpMethod.POST, "/state/**").permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


}
