package hochenchong.litespring.context.support;

import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import hochenchong.litespring.context.ApplicationContext;
import hochenchong.litespring.core.io.FileSystemResource;
import hochenchong.litespring.core.io.Resource;

public class FileSystemXmlApplicationContext implements ApplicationContext {
    private DefaultBeanFactory beanFactory;

    public FileSystemXmlApplicationContext(String path) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        Resource resource = new FileSystemResource(path);
        reader.loadBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return this.beanFactory.getBean(beanId);
    }
}
