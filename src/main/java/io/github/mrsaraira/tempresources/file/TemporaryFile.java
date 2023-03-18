package io.github.mrsaraira.tempresources.file;

import io.github.mrsaraira.tempresources.TemporaryResource;
import io.github.mrsaraira.tempresources.TemporaryResourceLifetimeScope;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Implementation of {@link TemporaryResource} to manage encapsulated file resource deletion.
 * <br>
 * If temporary file clean fails, it will be re-registered to
 * {@link TemporaryResourceLifetimeScope#APPLICATION APPLICATION} scope.
 *
 * @author Takhsin Saraira
 * @see TemporaryResource
 * @see TemporaryFilesCleanerService
 * @see TemporaryResourceLifetimeScope
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public class TemporaryFile implements TemporaryResource<File> {

    private final File resource;

    @Getter(AccessLevel.PACKAGE)
    private final TemporaryResourceLifetimeScope scope;

    @Override
    public String getIdentifier() {
        return resource.getAbsolutePath();
    }

    @Override
    public void clean() {
        String filePath = getIdentifier();
        try {
            if (resource.exists()) {
                log.info("Deleting temporary file: {}", filePath);
                FileUtils.forceDelete(resource);
            }
        } catch (IOException e) {
            log.error("Unable to delete temporary file {}", filePath, e);
            if (TemporaryResourceLifetimeScope.APPLICATION != scope) {
                log.info("Re-registering temporary file: {} to scope: {}", getIdentifier(), TemporaryResourceLifetimeScope.APPLICATION);
                TemporaryFilesCleanerService.getInstance().register(resource, TemporaryResourceLifetimeScope.APPLICATION);
            }
        }
    }

}
