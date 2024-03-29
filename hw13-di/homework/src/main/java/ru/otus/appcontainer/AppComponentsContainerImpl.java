package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        List<Object> componentList = appComponents.stream()
                .filter(c -> componentClass.isAssignableFrom(c.getClass()))
                .toList();
        var componentListSize = componentList.size();
        if (componentListSize > 1) {
            throw new RuntimeException(String.format("More than one component of class %s found", componentClass));
        }
        if (componentListSize == 0) {
            throw new RuntimeException(String.format("No component of class %s found", componentClass));
        }
        return (C) componentList.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        if (!appComponentsByName.containsKey(componentName)) {
            throw new RuntimeException(String.format("No component by name %s found", componentName));
        }
        return (C) appComponentsByName.get(componentName);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);

        Object objectConfiguration = null;
        try {
            objectConfiguration = configClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Method[] declaredMethods = configClass.getDeclaredMethods();
        Arrays.sort(declaredMethods, Comparator.comparing(o -> o.getAnnotation(AppComponent.class).order()));
        for (Method method : declaredMethods) {
            var nameComponent = method.getAnnotation(AppComponent.class).name();
            if (appComponentsByName.containsKey(nameComponent)) {
                throw new RuntimeException();
            }
            int paramsCount = method.getParameterCount();
            Parameter[] methodParameters = method.getParameters();
            Object[] objects = new Object[paramsCount];
            for (int i = 0; i < paramsCount; i++) {
                objects[i] = getAppComponent(methodParameters[i].getType());
            }
            method.setAccessible(true);
            Object component = null;
            try {
                component = method.invoke(objectConfiguration, objects);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            appComponentsByName.putIfAbsent(nameComponent, component);
            appComponents.add(component);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }
}
