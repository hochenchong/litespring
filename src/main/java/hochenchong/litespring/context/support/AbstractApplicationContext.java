package hochenchong.litespring.context.support;

import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import hochenchong.litespring.context.ApplicationContext;
import hochenchong.litespring.core.io.Resource;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory beanFactory;

    public AbstractApplicationContext(String configFile) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = this.getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return this.beanFactory.getBean(beanId);
    }

    protected abstract Resource getResourceByPath(String path);
}
