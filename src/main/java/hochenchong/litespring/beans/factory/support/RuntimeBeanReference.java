package hochenchong.litespring.beans.factory.support;

import hochenchong.litespring.util.Assert;

public class RuntimeBeanReference {
    private final String beanName;

    private final boolean toParent;

    private Object source;

    /**
     * Create a new RuntimeBeanReference to the given bean name,
     * without explicitly marking it as reference to a bean in
     * the parent factory.
     * @param beanName name of the target bean
     */
    public RuntimeBeanReference(String beanName) {
        this(beanName, false);
    }

    /**
     * Create a new RuntimeBeanReference to the given bean name,
     * with the option to mark it as reference to a bean in
     * the parent factory.
     * @param beanName name of the target bean
     * @param toParent whether this is an explicit reference to
     * a bean in the parent factory
     */
    public RuntimeBeanReference(String beanName, boolean toParent) {
        Assert.hasText(beanName, "'beanName' must not be empty");
        this.beanName = beanName;
        this.toParent = toParent;
    }


    public String getBeanName() {
        return this.beanName;
    }

    /**
     * Return whether this is an explicit reference to a bean
     * in the parent factory.
     */
    public boolean isToParent() {
        return this.toParent;
    }

    /**
     * Set the configuration source {@code Object} for this metadata element.
     * <p>The exact type of the object will depend on the configuration mechanism used.
     */
    public void setSource(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return this.source;
    }
}
