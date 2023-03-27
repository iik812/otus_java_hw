import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;


public class TestHelper {
    private static int all = 0;
    private static int passed = 0;
    private static int failed = 0;


    public static void start(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getMethods();
            TestHelper.runTestsModule(methods, clazz);

        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void runTestsModule(Method[] methods, Class<?> clazz) {

        Method[] before = getMethodsType(methods, Before.class);
        Method[] tests = getMethodsType(methods, Test.class);
        Method[] after = getMethodsType(methods, After.class);

        for (Method test : tests) {
            all++;
            System.out.println("--------------------------------------------");
            System.out.println("Started test " + test.getName() + " ");
            try {
                Object newInstance = clazz.getConstructor().newInstance();
                startTest(newInstance, before, after, test);
                System.out.println("PASSED ");
                passed++;
            } catch (Exception ex) {
                System.out.println("FAILED " + ex.getMessage());
                failed++;

            }
        }

        System.out.println("Tests Result:  " + " Success: " + passed + " " + " Failed: " + failed + " All: " + all);

    }

    private static void startTest(Object newInstance, Method[] before, Method[] after, Method test) {

        try {
            executeBeforeAnnotation(newInstance, before);
            executeTestAnnotation(newInstance, test);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            executeAfterAnnotation(newInstance, after);
        }
    }

    private static void executeTestAnnotation(Object newInstance, Method test) {
        try {

            test.setAccessible(true);
            test.invoke(newInstance);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void executeBeforeAnnotation(Object newInstance, Method[] before) {
        try {
            for (Method method : before) {
                method.setAccessible(true);
                method.invoke(newInstance);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void executeAfterAnnotation(Object newInstance, Method[] after) {
        try {
            for (Method method : after) {
                method.setAccessible(true);
                method.invoke(newInstance);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static Method[] getMethodsType(Method[] methods, Class<? extends Annotation> annotationClass) {
        List<Method> list = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotationClass)) {
                list.add(method);
            }
        }
        return list.toArray(new Method[0]);
    }

}
