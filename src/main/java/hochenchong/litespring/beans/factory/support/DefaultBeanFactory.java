package hochenchong.litespring.beans.factory.support;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.factory.BeanCreationException;
import hochenchong.litespring.beans.factory.BeanDefinitionStoreException;
import hochenchong.litespring.beans.factory.BeanFactory;
import hochenchong.litespring.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory() {
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = this.getBeanDefinition(beanId);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = classLoader.loadClass(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }
}
