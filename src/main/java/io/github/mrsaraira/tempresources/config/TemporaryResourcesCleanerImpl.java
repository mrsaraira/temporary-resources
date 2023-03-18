package io.github.mrsaraira.tempresources.config;

import io.github.mrsaraira.tempresources.TemporaryResource;
import io.github.mrsaraira.tempresources.TemporaryResourceLifetimeScope;
import io.github.mrsaraira.tempresources.TemporaryResourcesCleaner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * Main implementation of Temporary resources cleaner. Works with any Temporary Resource.
 *
 * @author Takhsin Saraira
 * @see TemporaryResourcesCleaner
 * @see TemporaryResource
 */
@Slf4j
@RequiredArgsConstructor
class TemporaryResourcesCleanerImpl implements TemporaryResourcesCleaner {

    private final List<TemporaryResource<?>> resources = new ArrayList<>();

    private final TemporaryResourceLifetimeScope scope;

    @PostConstruct
    private void init() {
        log.info("Initializing {} Temporary resource cleaner...", scope);
    }

    @Override
    public <T> TemporaryResource<T> register(TemporaryResource<T> resource) {
        log.info("Registering {} scope Temporary resource: {}", scope, resource.getIdentifier());
        resources.add(resource);
        return resource;
    }

    @PreDestroy
    public void clean() {
        if (resources.isEmpty()) {
            return;
        }

        log.info("Cleaning {} scope Temporary resources...", scope);
        for (TemporaryResource<?> resource : resources) {
            try {
                resource.clean();
            } catch (Exception e) {
                log.error("Unable to clean Temporary resource: {}", resource.getIdentifier(), e);
            }
        }
    }

}
