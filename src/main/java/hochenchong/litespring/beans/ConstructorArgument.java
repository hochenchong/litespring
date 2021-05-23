package hochenchong.litespring.beans;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// 类似于 org.springframework.beans.factory.config.ConstructorArgumentValues
public class ConstructorArgument {

    private final List<ValueHolder> argumentValues = new LinkedList<>();

    public ConstructorArgument() {
    }

    public void addArgumentValue(Object value, String type) {
        this.argumentValues.add(new ValueHolder(value, type));
    }

    public void addArgumentValue(ValueHolder valueHolder) {
        this.argumentValues.add(valueHolder);
    }

    // org.springframework.beans.factory.config.ConstructorArgumentValues#getGenericArgumentValues
    public List<ValueHolder> getArgumentValues() {
        // return this.argumentValues;
        return Collections.unmodifiableList(this.argumentValues);
    }

    public int getArgumentCount() {
        return this.argumentValues.size();
    }

    // 来自于 org.springframework.beans.factory.config.ConstructorArgumentValues.ValueHolder
    public static class ValueHolder {
        private Object value;

        private String type;

        private String name;

        public ValueHolder(Object value) {
            this.value = value;
        }

        public ValueHolder(Object value, String type) {
            this.value = value;
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
