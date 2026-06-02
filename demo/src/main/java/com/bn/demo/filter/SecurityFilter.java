package com.bn.demo.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// fala pro spring q essa classe é de configuração, tipo "ei, lê essa classe pq tem coisa importante aqui"
@EnableWebSecurity
// ativa o sistema de segurança do spring na aplicação, sem isso ele nem liga pra nada disso aqui
public class SecurityFilter {

    @Bean
    // registra esse método como um objeto gerenciado pelo spring, ele vai criar e cuidar dessa parada pra você
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        return httpSecurity.csrf(csrf -> csrf.disable())
                // desativa proteção contra CSRF, isso é mais usado em formulários web, em API REST não precisa não
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // fala q a api não vai guardar sessão de ninguém, cada requisição chega do zero, sem lembrar nada
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.GET, "/produtos").permitAll()
                        // GET em /produtos? tá liberado, qualquer um pode chamar
                            .requestMatchers(HttpMethod.POST, "/produtos").permitAll()
                        // POST em /produtos? também tá liberado, pode criar produto à vontade
                            .requestMatchers(HttpMethod.DELETE, "/produtos/**").permitAll()
                        // DELETE em qualquer /produtos/qualquercoisa? liberado também
                            .requestMatchers(HttpMethod.GET, "/produtos/**").permitAll()
                        // GET em /produtos/qualquercoisa? tá bom, pode
                            .anyRequest().authenticated()
                        // qualquer outra rota que não foi listada acima, tem que tá autenticado pra acessar
                )
                .build();
    }

}