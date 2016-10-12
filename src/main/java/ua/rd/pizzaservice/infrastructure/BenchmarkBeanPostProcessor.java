package ua.rd.pizzaservice.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BenchmarkBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        Method[] methods = bean.getClass().getMethods();
//        if (isBenchmarkAnnotatationPresent(methods, Benchmark.class)) {
//            Class<?> clazz = bean.getClass();
//            Class<?>[] interfaces = getAllDeclaredInterfaces(bean);
//            ClassLoader loader = clazz.getClassLoader();
//
//
//
//
//            Object proxy = Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
//                private final Object targetBean = bean;
//
//                @Override
//                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                    Class<?> targetClass = bean.getClass();
//                    String methodName = method.getName();
//                    Class<?>[] parameterTypes = method.getParameterTypes();
//
//                    Method targetMethod = targetClass.getMethod(methodName, parameterTypes);
//
//                    long start = System.nanoTime();
//                    Object o = targetMethod.invoke(targetBean, args);
//                    long end = System.nanoTime();
//
//                    long time = end - start;
//                    System.out.println("Time for method " + methodName + ": " + time);
//
//                    return o;
//                }
//            });
//            return proxy;
//        }

        return bean;
    }


    private Class<?>[] getAllDeclaredInterfaces(Object o) {
        List<Class<?>> interfaces = new ArrayList<>();
        Class<?> klazz = o.getClass();
        while (klazz != Object.class) {
            Collections.addAll(interfaces, klazz.getInterfaces());
            klazz = klazz.getSuperclass();
        }
        return interfaces.stream().toArray(Class<?>[]::new);
    }

    private boolean isBenchmarkAnnotatationPresent(Method[] methods) {
        for (Method method : methods) {
            if (method.isAnnotationPresent(Benchmark.class)) {
                Benchmark annotation = method.getAnnotation(Benchmark.class);
                if (annotation.value()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getMethods();
        if (isBenchmarkAnnotatationPresent(methods) ) {
            Class<?> clazz = bean.getClass();
            Class<?>[] interfaces = getAllDeclaredInterfaces(bean);
            ClassLoader loader = clazz.getClassLoader();




            Object proxy = Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
                private final Object targetBean = bean;

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Class<?> targetClass = bean.getClass();
                    String methodName = method.getName();
                    Class<?>[] parameterTypes = method.getParameterTypes();

                    Method targetMethod = targetClass.getMethod(methodName, parameterTypes);

                    long start = System.nanoTime();
                    Object o = targetMethod.invoke(targetBean, args);
                    long end = System.nanoTime();

                    long time = end - start;
                    System.out.println("Time for method " + methodName + ": " + time);

                    return o;
                }
            });
            return proxy;
        }

        return bean;
    }
}
