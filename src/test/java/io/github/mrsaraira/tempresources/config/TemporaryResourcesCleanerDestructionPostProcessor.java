package io.github.mrsaraira.tempresources.config;

import io.github.mrsaraira.tempresources.TemporaryResourcesCleaner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

// Pre-destroy workaround for non-singleton scope beans.
@Component
public class TemporaryResourcesCleanerDestructionPostProcessor implements DestructionAwareBeanPostProcessor {

    public boolean requiresDestruction(Object bean) {
        return bean instanceof TemporaryResourcesCleaner;
    }

    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        ((TemporaryResourcesCleaner) bean).clean();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
