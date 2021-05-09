package hochenchong.litespring.context.support;

import hochenchong.litespring.beans.factory.support.DefaultBeanFactory;
import hochenchong.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import hochenchong.litespring.context.ApplicationContext;

public class ClassPathXmlApplicationContext implements ApplicationContext {
    private DefaultBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String configFile) {
        beanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(configFile);
    }

    @Override
    public Object getBean(String beanId) {
        return this.beanFactory.getBean(beanId);
    }
}
