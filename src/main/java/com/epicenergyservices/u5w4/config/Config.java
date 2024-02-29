package com.epicenergyservices.u5w4.config;

import com.cloudinary.Cloudinary;
import com.epicenergyservices.u5w4.security.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Config {

  @Autowired
  private JWTFilter jwtFilter;


  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.formLogin(AbstractHttpConfigurer::disable);
    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


    httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").permitAll());

    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(11);
  }
  @Bean
  public Cloudinary cloudinary(@Value("${cloudinary.name}") String name, @Value("${cloudinary.key}") String key,
                               @Value("${cloudinary.secret}") String secret) {
    Map<String, String> config = new HashMap<>();

    config.put("cloud_name", name);
    config.put("api_key", key);
    config.put("api_secret", secret);

    return new Cloudinary(config);
  }
}
