package io.github.mrsaraira.tempresources;

/**
 * Temporary resources cleaner describing the generic registrar and cleaner of Temporary resources
 *
 * @author Takhsin Saraira
 * @see TemporaryResource
 */
public interface TemporaryResourcesCleaner {

    <T> TemporaryResource<T> register(TemporaryResource<T> resource);

    void clean();

}
