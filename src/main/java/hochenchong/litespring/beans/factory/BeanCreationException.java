package hochenchong.litespring.beans.factory;

import hochenchong.litespring.beans.BeansException;

public class BeanCreationException extends BeansException {
    private String beanName;

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a new BeanCreationException.
     * @param beanName the name of the bean requested
     * @param msg the detail message
     */
    public BeanCreationException(String beanName, String msg) {
        super("Error creating bean with name '" + beanName + "': " + msg);
        this.beanName = beanName;
    }

    /**
     * Create a new BeanCreationException.
     * @param beanName the name of the bean requested
     * @param msg the detail message
     * @param cause the root cause
     */
    public BeanCreationException(String beanName, String msg, Throwable cause) {
        this(beanName, msg);
        initCause(cause);
    }

    /**
     * Return the name of the bean requested, if any.
     */
    public String getBeanName() {
        return this.beanName;
    }
}
