package nextstep.study.di.stage4.annotations;

import java.util.Set;
import org.reflections.Reflections;

public class ClassPathScanner {

    public static Set<Class<?>> getAllClassesInPackage(final String packageName) {
        final Reflections reflections = new Reflections(packageName);
        final Set<Class<?>> repositoryClasses = reflections.getTypesAnnotatedWith(Repository.class);
        final Set<Class<?>> serviceClasses = reflections.getTypesAnnotatedWith(Service.class);
        repositoryClasses.addAll(serviceClasses);
        return repositoryClasses;
    }
}
