package pl.sda.carrental.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/home","/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/home", "/home/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/home", "/home/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/reservation", "/reservation/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/reservation", "/reservation/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/makeReservation", "/makeReservation/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/makeReservation", "/makeReservation/**").permitAll()
                        .requestMatchers("/error").permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home")
                        .failureForwardUrl("/error")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf((csrf) -> csrf.disable())
                .cors((cors) -> cors.disable())
                .headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.disable()));
        return http.build();
    }
}