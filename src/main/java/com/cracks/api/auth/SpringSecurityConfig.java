package com.cracks.api.auth;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {


    private PuntoDeEntrada authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // @Bean

    // public PasswordEncoder passwordEncoder() {
    //     return new PasswordEncoder() {
    //         @Override
    //         public String encode(CharSequence rawPassword) {
    //             return rawPassword.toString(); // Devuelve la contraseña sin modificar
    //         }

    //         @Override
    //         public boolean matches(CharSequence rawPassword, String encodedPassword) {
    //             return rawPassword.toString().equals(encodedPassword); // Compara la contraseña sin modificar
    //         }
    //     };
    // }

    @SuppressWarnings("removal")
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                    authorize.requestMatchers("/exportar","/**","/index.html","/aa","/monitor.html", "/main.js","/cracks.html","/consulta","/public.pem","auth.html","/cracks.zip","/encriptar").permitAll();
                    authorize.requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll();
                    //Guarda, despues cambiar a no - autorizado!!!
                    authorize.requestMatchers(HttpMethod.GET, "/pullUserActivities/**","/pullEventActivities/**","/pullGoals/**","/pullSports/**").permitAll();
                   authorize.requestMatchers(HttpMethod.GET, "/pullUserInformation/**","/pullSession/**").permitAll();
                   authorize.requestMatchers(HttpMethod.GET, "/pullAllEvents/**","/pullEvents/**","/pullPassedEventsByUser/**","/pullEventById/**").permitAll();
                   
                   authorize.requestMatchers("/postEvent","/eventPicture").permitAll();
                   authorize.requestMatchers("/goal","/sport").permitAll();
                   authorize.requestMatchers(HttpMethod.GET,"/goal/**","/sport/**").permitAll();

                   authorize.requestMatchers(HttpMethod.GET, "/eventPicture/**").permitAll();
                //    authorize.requestMatchers(HttpMethod.GET, "/eventPicture/**").hasRole("SUPER_ADMIN");
                   
                   authorize.requestMatchers(HttpMethod.GET, "/pullParticipantsEventById/**").permitAll();
                   authorize.requestMatchers("/pushParticipant").permitAll();

                    // websecurity.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**");
                    //                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
                //    authorize.requestMatchers(HttpMethod.GET, "/registro").permitAll();
                    authorize.requestMatchers("/login","/login2","/registro").permitAll();
                    authorize.requestMatchers("/h2-console/**").permitAll();
                    authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults())
                .headers(headers -> headers.frameOptions().disable());

        http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
    //     builder.inMemoryAuthentication()
    //            .withUser("user")
    //            .password("{noop}password") // Aquí usamos {noop} para indicar que la contraseña está en texto plano
    //            .roles("USER");
    //     return builder.build();
    // }

   

}