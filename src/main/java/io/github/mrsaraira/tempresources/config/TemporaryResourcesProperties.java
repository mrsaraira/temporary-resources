package io.github.mrsaraira.tempresources.config;

import io.github.mrsaraira.tempresources.files.TemporaryFilesCleanerService;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Temporary resources properties. Generates Spring configuration metadata.
 *
 * @author Takhsin Saraira
 */
@Data
@ConfigurationProperties(prefix = "temporary-resources")
public class TemporaryResourcesProperties {

    /**
     * Set temporary resources library configuration enabled
     */
    private Boolean enabled;

    /**
     * Set temporary files cleaner service enabled
     *
     * @see TemporaryFilesCleanerService
     */
    private Boolean temporaryFilesCleanerServiceEnabled;

}
