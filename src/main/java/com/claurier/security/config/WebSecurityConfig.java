package com.claurier.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.claurier.security.JwtAuthenticationEntryPoint;
import com.claurier.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
@EnableWebSecurity                                      // à conserver
@EnableGlobalMethodSecurity(prePostEnabled = true)   // à conserver
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value(value="${jwt.route.authentication.path}")
    private String loginPath;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(this.authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter(){

            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(WebSecurityConfig.this.loginPath + "/**").allowedMethods(new String[]{"POST", "OPTIONS", "GET"});
                registry.addMapping("/api/**").allowedMethods(new String[]{"GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"});
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests()
                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // allow anonymous resource requests
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();

        // Custom JWT based security filter
        httpSecurity
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        httpSecurity.headers().cacheControl();
    }

   /* protected void configure(HttpSecurity httpSecurity) throws Exception {
        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)((HttpSecurity)httpSecurity.csrf().disable()).exceptionHandling().authenticationEntryPoint((AuthenticationEntryPoint)this.unauthorizedHandler).and()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()).authorizeRequests().antMatchers(HttpMethod.OPTIONS, new String[]{"/**"})).permitAll().antMatchers(new String[]{this.loginPath + "/**"})).permitAll().antMatchers(new String[]{"/img/**"})).permitAll().anyRequest()).authenticated();
        httpSecurity.addFilterBefore((Filter)this.authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    } */

}