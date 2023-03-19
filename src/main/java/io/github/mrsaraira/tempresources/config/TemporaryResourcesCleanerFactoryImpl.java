package io.github.mrsaraira.tempresources.config;

import io.github.mrsaraira.tempresources.TemporaryResourceLifetimeScope;
import io.github.mrsaraira.tempresources.TemporaryResourcesCleaner;
import io.github.mrsaraira.tempresources.TemporaryResourcesCleanerFactory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.inject.Provider;

import static io.github.mrsaraira.tempresources.config.TemporaryResourcesCleanerConfiguration.APPLICATION_TEMP_RESOURCES_CLEANER_PROVIDER;
import static io.github.mrsaraira.tempresources.config.TemporaryResourcesCleanerConfiguration.REQUEST_TEMPORARY_RESOURCES_CLEANER_PROVIDER;
import static io.github.mrsaraira.tempresources.config.TemporaryResourcesCleanerConfiguration.SESSION_TEMPORARY_RESOURCES_CLEANER_PROVIDER;

/**
 * Temporary resources cleaner factory implementation to create Spring-managed temporary resources cleaners with pre-defined scope.
 *
 * @author Takhsin Saraira
 * @see TemporaryResourcesCleaner
 * @see TemporaryResourceLifetimeScope
 */
@Slf4j
@Component
class TemporaryResourcesCleanerFactoryImpl implements TemporaryResourcesCleanerFactory {

    private final Provider<TemporaryResourcesCleaner> applicationTemporaryResourcesCleanerProvider;

    private final Provider<TemporaryResourcesCleaner> sessionTemporaryResourcesCleanerProvider;

    private final Provider<TemporaryResourcesCleaner> requestTemporaryResourcesCleanerProvider;

    TemporaryResourcesCleanerFactoryImpl(@Qualifier(APPLICATION_TEMP_RESOURCES_CLEANER_PROVIDER) Provider<TemporaryResourcesCleaner> applicationTemporaryResourcesCleanerProvider,
                                         @Qualifier(SESSION_TEMPORARY_RESOURCES_CLEANER_PROVIDER) Provider<TemporaryResourcesCleaner> sessionTemporaryResourcesCleanerProvider,
                                         @Qualifier(REQUEST_TEMPORARY_RESOURCES_CLEANER_PROVIDER) Provider<TemporaryResourcesCleaner> requestTemporaryResourcesCleanerProvider) {
        this.applicationTemporaryResourcesCleanerProvider = applicationTemporaryResourcesCleanerProvider;
        this.sessionTemporaryResourcesCleanerProvider = sessionTemporaryResourcesCleanerProvider;
        this.requestTemporaryResourcesCleanerProvider = requestTemporaryResourcesCleanerProvider;
    }

    @Override
    public TemporaryResourcesCleaner getTemporaryResourcesCleaner(@NonNull TemporaryResourceLifetimeScope scope) {
        switch (scope) {
            case APPLICATION:
                return applicationTemporaryResourcesCleanerProvider.get();
            case SESSION:
                return sessionTemporaryResourcesCleanerProvider.get();
            case REQUEST:
                return requestTemporaryResourcesCleanerProvider.get();
            default:
                log.error("Unsupported Temporary resource lifetime scope: {}", scope);
                throw new IllegalArgumentException("Unsupported Temporary resource lifetime scope");
        }
    }

}
