package io.github.mrsaraira.tempresources;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base service defining all needed methods for implementations to work with temporary resources i.e.
 * registering temporary resources with pre-defined scope
 * <br><br>
 * <b>NOTE:</b> Implementation of this abstraction have to be a Spring component.
 *
 * @param <T> encapsulated resource type
 * @author Takhsin Saraira
 * @see TemporaryResourcesCleanerFactory
 * @see TemporaryResourcesCleaner
 * @see AbstractTemporaryResourcesCleanerService
 */
public abstract class BaseTemporaryResourcesCleanerService<T> extends AbstractTemporaryResourcesCleanerService<T> {

    @Autowired
    private TemporaryResourcesCleanerFactory temporaryResourcesCleanerFactory;

    protected final TemporaryResource<T> register(@NonNull TemporaryResource<T> temporaryResource, @NonNull TemporaryResourceLifetimeScope scope) {
        return getTemporaryResourceCleaner(scope).register(temporaryResource);
    }

    @Override
    protected final TemporaryResourcesCleaner getTemporaryResourceCleaner(@NonNull TemporaryResourceLifetimeScope scope) {
        return temporaryResourcesCleanerFactory.getTemporaryResourcesCleaner(scope);
    }

}
