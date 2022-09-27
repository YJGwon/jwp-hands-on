package nextstep.study.di.stage3.context;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 스프링의 BeanFactory, ApplicationContext에 해당되는 클래스
 */
class DIContainer {

    private final Set<Object> beans;

    public DIContainer(final Set<Class<?>> classes) {
        this.beans = instantiateClasses(classes);
        assignFieldToBeans(beans);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(final Class<T> aClass) {
        return (T) beans.stream()
                .filter(bean -> aClass.isAssignableFrom(bean.getClass()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 bean을 찾지 못했습니다: " + aClass.getName()));
    }

    private Set<Object> instantiateClasses(final Set<Class<?>> classes) {
        return classes.stream()
                .map(this::instantiateClass)
                .collect(Collectors.toSet());
    }

    private <T> T instantiateClass(final Class<T> clazz) {
        try {
            final Constructor<T> defaultConstructor = clazz.getDeclaredConstructor();
            defaultConstructor.setAccessible(true);
            return defaultConstructor.newInstance();
        } catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException("bean을 초기화할 수 없습니다: " + clazz.getName());
        }
    }

    private void assignFieldToBeans(final Set<Object> beans) {
        beans.forEach(bean -> assignFieldsToBean(bean, bean.getClass().getDeclaredFields()));
    }

    private void assignFieldsToBean(final Object bean, final Field[] fields) {
        for (Field field : fields) {
            assignFieldToBean(bean, field);
        }
    }

    private void assignFieldToBean(final Object bean, final Field field) {
        field.setAccessible(true);
        try {
            setField(field, bean);
        } catch (IllegalAccessException e) {
            throw new UnsupportedOperationException("bean의 필드를 초기화하지 못했습니다: " + field.getName());
        }
    }

    private void setField(final Field field, final Object target) throws IllegalAccessException {
        if (field.get(target) == null) {
            field.set(target, getBean(field.getType()));
        }
    }
}
