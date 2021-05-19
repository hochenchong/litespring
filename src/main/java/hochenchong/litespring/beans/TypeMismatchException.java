package hochenchong.litespring.beans;

import hochenchong.litespring.util.ClassUtils;

public class TypeMismatchException extends Exception {
    private transient Object value;

    private Class requiredType;

    public TypeMismatchException(Object value, Class requiredType) {
        this(value, requiredType, null);
    }

    public TypeMismatchException(Object value, Class requiredType, Throwable cause) {
        super("Failed to convert value of type '" + ClassUtils.getDescriptiveType(value) + "'" +
                        (requiredType != null ? " to required type '" + ClassUtils.getQualifiedName(requiredType) + "'" : ""),
                cause);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue() {
        return value;
    }

    public Class getRequiredType() {
        return requiredType;
    }
}
