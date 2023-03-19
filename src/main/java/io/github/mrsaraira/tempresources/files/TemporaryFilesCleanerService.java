package io.github.mrsaraira.tempresources.files;

import io.github.mrsaraira.tempresources.BaseTemporaryResourcesCleanerService;
import io.github.mrsaraira.tempresources.TemporaryResource;
import io.github.mrsaraira.tempresources.TemporaryResourceLifetimeScope;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Service to automatically clean temporary files with pre-defined lifetime scope.
 * <br>
 * Temporary files - are files that eventually will be deleted. These files don't have to be placed in temp/ directory.
 * This service registers temporary files and directories by default in
 * {@link TemporaryResourceLifetimeScope#APPLICATION APPLICATION} scope, or by predefining temporary
 * file lifetime scope after which it will be removed.
 * <br><br>
 *
 * <b>NOTE:</b> Once the temporary file is registered for deletion, <b>it cannot be undone</b>.<br>
 * <b>NOTE:</b> This service works for both files and directories.
 *
 * @author Takhsin Saraira
 * @see TemporaryResourceLifetimeScope
 * @see TemporaryResource
 */
@ConditionalOnProperty(value = "temporary-resources.files.enabled", matchIfMissing = true)
@Slf4j
@Service
public class TemporaryFilesCleanerService extends BaseTemporaryResourcesCleanerService<File> {

    /**
     * Makes possible to get instance of this Spring bean in non-bean classes
     */
    @Getter
    private static TemporaryFilesCleanerService instance;

    private TemporaryFilesCleanerService() {
        log.info("Initializing {} ...", TemporaryFilesCleanerService.class.getSimpleName());
        instance = this;
    }

    /**
     * Register file to be removed with default scope {@link TemporaryResourceLifetimeScope#APPLICATION APPLICATION}
     *
     * @param file file to register
     * @return temporary file
     */
    public TemporaryFile register(@NonNull File file) {
        return register(file, TemporaryResourceLifetimeScope.APPLICATION);
    }

    /**
     * Register file to be removed with pre-defined lifetime scope.
     *
     * @param file  file to register
     * @param scope temporary file lifetime scope after which the file will be removed
     * @return temporary file
     * @see TemporaryResourceLifetimeScope
     */
    @Override
    public TemporaryFile register(@NonNull File file, @NonNull TemporaryResourceLifetimeScope scope) {
        TemporaryFile temporaryFile = new TemporaryFile(file, scope);
        return (TemporaryFile) register(temporaryFile, scope);

    }

}
