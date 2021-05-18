package hochenchong.litespring.beans;

import hochenchong.litespring.beans.factory.config.ConfigurableBeanFactory;

import java.util.List;

public interface BeanDefinition {
    String SCOPE_DEFAULT = "";

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    String getBeanClassName();

    boolean isSingleton();

    boolean isPrototype();

    void setScope(String scope);

    String getScope();

    List<PropertyValue> getPropertyValues();
}
