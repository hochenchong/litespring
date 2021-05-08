package hochenchong.litespring.beans.factory;

import hochenchong.litespring.beans.BeanDefinition;

public interface BeanFactory {
    BeanDefinition getBeanDefinition(String beanId);

    Object getBean(String beanId);
}
