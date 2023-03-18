package io.github.mrsaraira.tempresources;

/**
 * This class describes a wrapper of some resource which will be eventually cleaned
 * using {@link TemporaryResource#clean()} logic.
 * The clean logic is invoked by {@link TemporaryResourcesCleaner} at some time depending on the defined lifetime
 * scope on registration.
 *
 * @param <T> wrapped resource type example: File
 * @author Takhsin Saraira
 * @see TemporaryResourceLifetimeScope
 * @see TemporaryResourcesCleaner
 */
public interface TemporaryResource<T> {

    /**
     * Identifier of the encapsulated resource (used for logging).
     *
     * @return resource identifier
     */
    String getIdentifier();

    /**
     * The encapsulated resource that will be later cleaned
     *
     * @return encapsulated resource
     */
    T getResource();

    /**
     * Clean logic for the encapsulated resource
     */
    void clean();

}
