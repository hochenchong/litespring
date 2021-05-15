package hochenchong.litespring.context.support;

import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import hochenchong.litespring.context.ApplicationContext;
import hochenchong.litespring.core.io.Resource;
import hochenchong.litespring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory beanFactory;

    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = this.getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
        beanFactory.setBeanClassLoader(this.getBeanClassLoader());
    }

    @Override
    public Object getBean(String beanId) {
        return this.beanFactory.getBean(beanId);
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }

    protected abstract Resource getResourceByPath(String path);
}
