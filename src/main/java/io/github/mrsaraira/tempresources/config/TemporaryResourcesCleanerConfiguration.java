package io.github.mrsaraira.tempresources.config;

import io.github.mrsaraira.tempresources.TemporaryResourceLifetimeScope;
import io.github.mrsaraira.tempresources.TemporaryResourcesCleaner;
import io.github.mrsaraira.tempresources.TemporaryResourcesCleanerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.RequestContextListener;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

/**
 * Configuration defining temporary resources cleaner beans to be created by temporary resources cleaner factory
 *
 * @author Takhsin Saraira
 * @see TemporaryResourcesCleanerFactory
 */
@ConditionalOnProperty(value = "temporary-resources.enabled", matchIfMissing = true)
@ConditionalOnWebApplication
@ComponentScan("io.github.mrsaraira.tempresources")
@Configuration
class TemporaryResourcesCleanerConfiguration {

    static final String APPLICATION_TEMP_RESOURCES_CLEANER_PROVIDER = "applicationTemporaryResourcesCleanerProvider";
    static final String SESSION_TEMPORARY_RESOURCES_CLEANER_PROVIDER = "sessionTemporaryResourcesCleanerProvider";
    static final String REQUEST_TEMPORARY_RESOURCES_CLEANER_PROVIDER = "requestTemporaryResourcesCleanerProvider";


    @Bean(APPLICATION_TEMP_RESOURCES_CLEANER_PROVIDER)
    @Scope(SCOPE_SINGLETON)
    TemporaryResourcesCleaner createApplicationTemporaryResourcesCleaner() {
        return new TemporaryResourcesCleanerImpl(TemporaryResourceLifetimeScope.APPLICATION);
    }

    @Bean(SESSION_TEMPORARY_RESOURCES_CLEANER_PROVIDER)
    @SessionScope
    TemporaryResourcesCleaner createSessionTemporaryResourceCleaner() {
        return new TemporaryResourcesCleanerImpl(TemporaryResourceLifetimeScope.SESSION);
    }

    @Bean(REQUEST_TEMPORARY_RESOURCES_CLEANER_PROVIDER)
    @RequestScope
    TemporaryResourcesCleaner createRequestTemporaryResourceCleaner() {
        return new TemporaryResourcesCleanerImpl(TemporaryResourceLifetimeScope.REQUEST);
    }

    @ConditionalOnMissingBean
    @Bean
    RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
