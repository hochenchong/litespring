package hochenchong.litespring.beans.factory.support;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.factory.BeanCreationException;
import hochenchong.litespring.beans.factory.BeanDefinitionStoreException;
import hochenchong.litespring.beans.factory.config.ConfigurableBeanFactory;
import hochenchong.litespring.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private ClassLoader beanClassLoader;

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

        if (beanDefinition.isSingleton()) {
            Object bean = this.getSingleton(beanId);
            if (bean == null) {
                bean = createBean(beanDefinition);
                this.registerSingleton(beanId, bean);
            }
            return bean;
        }

        return createBean(beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        ClassLoader classLoader = this.getBeanClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = classLoader.loadClass(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }
}
