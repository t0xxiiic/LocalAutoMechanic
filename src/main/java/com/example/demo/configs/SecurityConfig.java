package com.example.demo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password(encoder.encode("password"))
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password(encoder.encode("admin"))
//                .roles("USER", "ADMIN");
//    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/**")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*")
//                        .allowedOrigins("http://172.16.0.11:3000");
//            }
//        };
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/test-ip").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .httpBasic();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOriginPatterns(List.of("http://172.16.0.11:[*]", "http://localhost:[*]"));
//        configuration.setAllowedMethods(Collections.singletonList("*"));
//        configuration.setAllowedHeaders(List.of("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/graphql/**", configuration);
//        source.registerCorsConfiguration("/graphql/", configuration);
//        source.registerCorsConfiguration("/graphql", configuration);
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

//    @Value("${graphql.url:/graphql}")
//    private String graphqlurl;

//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration(graphqlurl, config);
//        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://172.16.0.11:3000", "http://localhost:3000", "http://172.16.0.11:3000/", "http://localhost:3000/").allowedMethods("POST", "GET", "PUT");
//                registry.addMapping("/").allowedOrigins("http://172.16.0.11:3000", "http://localhost:3000", "http://172.16.0.11:3000/", "http://localhost:3000/").allowedMethods("POST", "GET", "PUT");
//                registry.addMapping("/graphql/").allowedOrigins("http://172.16.0.11:3000", "http://localhost:3000", "http://172.16.0.11:3000/", "http://localhost:3000/").allowedMethods("POST", "GET", "PUT");
//                registry.addMapping("/gui").allowedOrigins("http://172.16.0.11:3000", "http://localhost:3000", "http://172.16.0.11:3000/", "http://localhost:3000/").allowedMethods("POST", "GET", "PUT");
//                registry.addMapping("/graphql/**").allowedOrigins("http://172.16.0.11:3000", "http://localhost:3000", "http://172.16.0.11:3000/", "http://localhost:3000/").allowedMethods("POST", "GET", "PUT");
//                registry.addMapping("/graphql/**").allowedOrigins("http://172.16.0.11:3000", "http://localhost:3000", "http://172.16.0.11:3000/", "http://localhost:3000/").allowedMethods("POST", "GET", "PUT");
//            }
//        };
//    }
}
