package hochenchong.litespring.beans.factory.support;

import hochenchong.litespring.beans.BeanDefinition;
import hochenchong.litespring.beans.ConstructorArgument;
import hochenchong.litespring.beans.SimpleTypeConverter;
import hochenchong.litespring.beans.factory.BeanCreationException;
import hochenchong.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.List;

public class ConstructorResolver {
    private static final Logger logger = LoggerFactory.getLogger(ConstructorResolver.class);

    private final ConfigurableBeanFactory beanFactory;

    public ConstructorResolver(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    // org.springframework.beans.factory.support.ConstructorResolver#autowireConstructor
    public Object autowireConstructor(BeanDefinition beanDefinition) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;

        Class<?> beanClass = null;
        try {
            beanClass = this.beanFactory.getBeanClassLoader().loadClass(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(beanDefinition.getID(), "Instantiation of bean failed, can't resolve class", e);
        }
        // 获取这个类的所有构造方法
        Constructor<?>[] candidates = beanClass.getConstructors();

        BeanDefinitionValueResolver valueResolver =
                new BeanDefinitionValueResolver(this.beanFactory);
        ConstructorArgument cargs = beanDefinition.getConstructorArgument();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();

        for (int i = 0; i < candidates.length; i++) {
            Class<?>[] parameterTypes = candidates[i].getParameterTypes();
            // 判断构造方法参数个数是否与 bean 定义中的构造器参数个数一致
            if (parameterTypes.length != cargs.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];
            boolean result = this.valuesMatchTypes(parameterTypes,
                    cargs.getArgumentValues(),
                    argsToUse,
                    valueResolver,
                    typeConverter);
            if (result) {
                constructorToUse = candidates[i];
                break;
            }
        }

        // 找不到一个合适的构造函数
        if (constructorToUse == null) {
            throw new BeanCreationException(beanDefinition.getID(), "can't find a apporiate constructor");
        }

        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(beanDefinition.getID(), "can't find a create instance using " + constructorToUse);
        }
    }

    private boolean valuesMatchTypes(Class<?>[] parameterTypes,
                                     List<ConstructorArgument.ValueHolder> valueHolders,
                                     Object[] argsToUse,
                                     BeanDefinitionValueResolver valueResolver,
                                     SimpleTypeConverter typeConverter) {


        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);
            // 获取参数的值，可能是 TypedStringValue, 也可能是 RuntimeBeanReference
            Object originalValue = valueHolder.getValue();

            try {
                // 获得真正的值
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                // 如果参数类型是 int, 但是值是字符串,例如 "3"，还需要转型
                // 如果转型失败，则抛出异常。说明这个构造函数不可用
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                // 转型成功，记录下来
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                logger.error(String.valueOf(e));
                return false;
            }
        }
        return true;
    }
}
