package hochenchong.litespring.beans.factory.config;

import hochenchong.litespring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {

    void setBeanClassLoader(ClassLoader beanClassLoader);

    ClassLoader getBeanClassLoader();
}
