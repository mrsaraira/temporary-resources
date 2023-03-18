package io.github.mrsaraira.tempresources;

import javax.validation.constraints.NotNull;

/**
 * Abstract service defining all needed methods for implementations to work with temporary resources i.e.
 * registering temporary resources with pre-defined scope and getting {@link TemporaryResourcesCleaner}
 * <br><br>
 *
 * @param <T> encapsulated resource type
 * @author Takhsin Saraira
 * @see TemporaryResourcesCleaner
 */
public abstract class AbstractTemporaryResourcesCleanerService<T> {

    protected abstract TemporaryResource<T> register(@NotNull T resource, @NotNull TemporaryResourceLifetimeScope scope);

    protected abstract TemporaryResourcesCleaner getTemporaryResourceCleaner(@NotNull TemporaryResourceLifetimeScope scope);

}
