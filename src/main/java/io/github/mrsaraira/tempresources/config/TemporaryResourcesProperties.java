package io.github.mrsaraira.tempresources.config;

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
     * Set Temporary resources library configuration enabled
     */
    private Boolean enabled;

    /**
     * Set Temporary files cleaner service enabled
     */
    private Boolean temporaryFilesCleanerServiceEnabled;

}
