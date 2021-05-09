package hochenchong.litespring.beans.factory.support;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.factory.BeanDefinitionStoreException;

public interface BeanDefinitionRegistry {
    /**
     * Return the BeanDefinition for the given bean name.
     * @param beanName name of the bean to find a definition for
     * @return the BeanDefinition for the given name (never {@code null})
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * Register a new bean definition with this registry.
     * Must support RootBeanDefinition and ChildBeanDefinition.
     * @param beanName the name of the bean instance to register
     * @param beanDefinition definition of the bean instance to register
     * @throws BeanDefinitionStoreException if the BeanDefinition is invalid
     * or if there is already a BeanDefinition for the specified bean name
     * (and we are not allowed to override it)
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionStoreException;
}
