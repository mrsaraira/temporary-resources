package io.github.mrsaraira.tempresources.files;

import io.github.mrsaraira.tempresources.TemporaryResourceLifetimeScope;
import io.github.mrsaraira.tempresources.TemporaryResourcesCleanerFactory;
import io.github.mrsaraira.tempresources.config.TestConfiguration;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
@SpringJUnitWebConfig(classes = {TestConfiguration.class})
public class TemporaryFilesCleanerServiceTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private TemporaryFilesCleanerService temporaryFilesCleanerService;
    @Autowired
    private TemporaryResourcesCleanerFactory temporaryResourcesCleanerFactory;

    @Test
    public void getInstanceTest() {
        Assertions.assertNotNull(TemporaryFilesCleanerService.getInstance());
    }

    @Test
    public void registerFileTest() {
        for (TemporaryResourceLifetimeScope scope : TemporaryResourceLifetimeScope.values()) {
            final File tempFile = createTempFile(RandomStringUtils.randomAlphanumeric(6));
            TemporaryFile temporaryFile = temporaryFilesCleanerService.register(tempFile, scope);
            Assertions.assertEquals(scope, temporaryFile.getScope());
            Assertions.assertEquals(temporaryFile.getResource(), tempFile);
            context.getAutowireCapableBeanFactory().destroyBean(temporaryResourcesCleanerFactory.getTemporaryResourcesCleaner(scope));
            Assertions.assertFalse(tempFile.exists());
        }
    }

    @Test
    public void registerFileDefaultScopeTest() {
        TemporaryResourceLifetimeScope defaultScope = TemporaryResourceLifetimeScope.APPLICATION;

        final File tempFile = createTempFile(RandomStringUtils.randomAlphanumeric(6));
        TemporaryFile temporaryFile = temporaryFilesCleanerService.register(tempFile);
        Assertions.assertEquals(defaultScope, temporaryFile.getScope());
        Assertions.assertEquals(tempFile, temporaryFile.getResource());
        context.getAutowireCapableBeanFactory().destroyBean(temporaryResourcesCleanerFactory.getTemporaryResourcesCleaner(defaultScope));
        Assertions.assertFalse(tempFile.exists());
    }

    @SneakyThrows
    private File createTempFile(String prefix) {
        Path tempFilePath = Files.createTempFile(prefix, null);
        File tempFile = tempFilePath.toFile();
        Assertions.assertTrue(tempFile.exists(), "Failed to create temporary file");
        return tempFile;
    }

}
