package hochenchong.litespring.beans;

import hochenchong.litespring.beans.factory.config.ConfigurableBeanFactory;

public interface BeanDefinition {
    String SCOPE_DEFAULT = "";

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    String getBeanClassName();

    boolean isSingleton();

    boolean isPrototype();

    void setScope(String scope);

    String getScope();
}
