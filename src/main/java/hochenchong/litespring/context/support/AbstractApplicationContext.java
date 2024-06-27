package hochenchong.litespring.context.support;

import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import hochenchong.litespring.context.ApplicationContext;
import hochenchong.litespring.core.io.Resource;
import hochenchong.litespring.util.ClassUtils;

/**
 * 抽象 ApplicationContext
 * 使用设计模式：模板方法。将重复的代码使用该抽象类实现
 * 实现类实现该抽象类的抽象方法：{@link AbstractApplicationContext#getResourceByPath(String)}
 *     从不同的来源获取 xml
 */
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
