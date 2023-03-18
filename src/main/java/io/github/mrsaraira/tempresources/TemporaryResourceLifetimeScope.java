package io.github.mrsaraira.tempresources;

/**
 * Temporary resource lifetime scopes after which the resource will be cleaned.
 * <br> {@link #APPLICATION} - Application shutdown
 * <br> {@link #SESSION}     - Servlet HTTP session
 * <br> {@link #REQUEST}     - Servlet request / thread-bounded
 *
 * @author Takhsin Saraira
 * @see TemporaryResourcesCleanerFactory
 */
public enum TemporaryResourceLifetimeScope {

    APPLICATION,
    SESSION,
    REQUEST

}
