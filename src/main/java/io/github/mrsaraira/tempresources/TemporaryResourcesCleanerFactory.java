package io.github.mrsaraira.tempresources;

import javax.validation.constraints.NotNull;

/**
 * Temporary resources cleaner factory to create temporary resources cleaners by pre-defined scope.
 *
 * @author Takhsin Saraira
 * @see TemporaryResourcesCleaner
 * @see TemporaryResourceLifetimeScope
 */
public interface TemporaryResourcesCleanerFactory {

    /**
     * Create temporary resources cleaner with pre-defined scope.
     *
     * @param scope temporary resource lifetime scope
     * @return temporary resources cleaner
     */
    TemporaryResourcesCleaner getTemporaryResourcesCleaner(@NotNull TemporaryResourceLifetimeScope scope);

}
