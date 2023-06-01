package ru.otus;


import annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;


public class Ioc {

    private Ioc() {
    }

    public static TestLoggingInterface createLogClass() {
        InvocationHandler handler = new LoggingInvocationHandler(new TestLogging());

        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class LoggingInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLoggingInterface;
        private final Set<String> stringSet;

        LoggingInvocationHandler(TestLoggingInterface loggingClass) {
            this.testLoggingInterface = loggingClass;
            this.stringSet = getAllMethods(loggingClass);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (stringSet.contains(getMethodDescriptions(method))) {
                System.out.println("execute method with: " + method.getName() +" " + getParams(args));
            }
            return method.invoke(testLoggingInterface, args);
        }

        private String getParams(Object[] args) {
            int counter = 1;
            if (args != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Object arg : args) {
                    stringBuilder.append(", params").append(" ").append(counter++).append(" = ").append(arg);
                }
                return stringBuilder.toString();
            }
            return "no params";
        }

        private Set<String> getAllMethods(TestLoggingInterface testLogging) {
            return Arrays.stream(testLogging.getClass().getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(Log.class))
                    .map(this::getMethodDescriptions)
                    .collect(Collectors.toSet());
        }

        private String getMethodDescriptions(Method method) {
            return method.getName() + Arrays.toString(method.getParameters());
        }
    }
}