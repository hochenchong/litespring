package hochenchong.litespring.beans.factory.config;

import hochenchong.litespring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void setBeanClassLoader(ClassLoader beanClassLoader);

    ClassLoader getBeanClassLoader();
}
