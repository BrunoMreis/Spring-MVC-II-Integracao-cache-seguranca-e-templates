package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.casadocodigo.loja.dao.UsuarioDAO;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UsuarioDAO usuarioDAO;

    public SecurityConfiguration(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(usuarioDAO);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig,
            DaoAuthenticationProvider authProvider) throws Exception {

        ProviderManager manager = (ProviderManager) authConfig.getAuthenticationManager();
        manager.getProviders().add(0, authProvider);
        return manager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/produtos/form").hasRole("ADMIN")
                        .requestMatchers("/carrinho/**", "/resources/**", "/pagamento/**", "/").permitAll()
                        .requestMatchers("/login/form", "/login").permitAll()
                        .requestMatchers("/produtos/**", "/produtos").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login/form")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/produtos")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll());

        return http.build();
    }
}