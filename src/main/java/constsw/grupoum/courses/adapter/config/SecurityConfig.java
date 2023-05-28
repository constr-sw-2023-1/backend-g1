package constsw.grupoum.courses.adapter.config;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                           @Qualifier("customAuthenticationEntryPoint") RestAuthenticationEntryPoint authEntryPoint) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/actuator/**")
                .permitAll()
                .requestMatchers("/swagger-ui/**")
                .permitAll()
                .requestMatchers("/swagger-ui.html")
                .permitAll()
                .requestMatchers("/v3/api-docs/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
                .httpBasic().and()
                .cors().and().csrf().disable();

        httpSecurity.oauth2ResourceServer()
                .jwt()
                .and()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint);

        return httpSecurity.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverterForKeycloak() {
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = jwt -> {
            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
            Collection<String> roles = realmAccess.get("roles");
            return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
        };
    
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    
        return jwtAuthenticationConverter;
    }
}

