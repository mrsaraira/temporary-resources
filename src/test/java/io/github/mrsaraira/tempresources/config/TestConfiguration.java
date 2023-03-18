package io.github.mrsaraira.tempresources.config;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;

import java.util.HashMap;
import java.util.Map;

@ComponentScan("io.github.mrsaraira.tempresources")
@Configuration
public class TestConfiguration {

    /**
     * Makes possible to register other bean scopes in tests
     */
    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();

        Map<String, Object> scopes = new HashMap<>();
        scopes.put("session", new SimpleThreadScope());
        scopes.put("request", new SimpleThreadScope());
        scopeConfigurer.setScopes(scopes);
        return scopeConfigurer;
    }

}
