package ua.rd.pizzaservice.infrastructure;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContext implements Context {
    private final Config config;
    private final static Map<String, Object> beans = new HashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public <T> T getBean(String beanName) {
        Class<?> type = config.getImpl(beanName);
        T bean = (T) beans.get(beanName);
        if (bean != null) {
            return bean;
        }

        BeanBuilder builder = new BeanBuilder<T>(type);
        builder.createBean();
        //builder.callPostCreateMethod();
        builder.createBeanProxy();
        builder.callInitMethod();

        bean = (T) builder.build();

        beans.put(beanName, bean);


        return bean;
    }

    private class BeanBuilder<T> {
        private Class<?> type;
        private T bean;
        private T targetBean;

        private BeanBuilder(Class<?> type) {
            this.type = type;
        }

        private void createBean() {
            try {
                Constructor<?> constructor = type.getConstructors()[0];
                if (constructor.getParameterCount() == 0) {
                    bean = (T) type.newInstance();
                } else {
                    bean = (T) createBeanWithMultiParameters(constructor);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException();
            }
        }

        private Object createBeanWithMultiParameters(Constructor<?> constructor) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            int parameterCount = constructor.getParameterCount();
            Object[] params = new Object[parameterCount];

            for (int i = 0; i < parameterCount; i++) {
                String beanName = convertTypeToBeanName(parameterTypes[i]);
                Object param = getBean(beanName);
                params[i] = param;
            }

            return constructor.newInstance(params);
        }

        private String convertTypeToBeanName(Class<?> type) {
            String input = type.getSimpleName();
            return input.substring(0, 1).toLowerCase() + input.substring(1);
        }

        private T build() {
            return bean;
        }

        public void callInitMethod() {
            try {
                Class<?> clazz = bean.getClass();
                Method init = clazz.getMethod("init"); // TODO: 05-Oct-16 can't save method
                init.invoke(bean, null);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                return;
            }
        }

        public void createBeanProxy() {
            if (isAnyMethodAnnotatedWith(Benchmark.class)) {


                ClassLoader loader = bean.getClass().getClassLoader();
                Class<?>[] interfaces = bean.getClass().getInterfaces();


                Object o = Proxy.newProxyInstance(loader, interfaces, new InvocationHandler() {
                    T targetBean = bean;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String name = method.getName();
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        Method beanMethod = targetBean.getClass().getMethod(name, parameterTypes);
//                        Method beanMethod = method;
//                        String name = method.getName();
//                        Method beanMethod = targetBean.getClass().getMethod(name);
//
//                        System.out.println(method);
                        Object o;
                        if (beanMethod.isAnnotationPresent(Benchmark.class)) {
                            long a = System.nanoTime();
                            o = method.invoke(targetBean, args);
                            System.out.println(System.nanoTime() - a);
                        } else {
                            o = method.invoke(targetBean, args);

                        }

//                        method.invoke(targetBean, args);
//                        if (beanMethod.getAnnotation(Benchmark.class) != null) {
//                            System.out.println("after!");
//                        }
                        return o;
//                        String methodName = method.getName();
//                        Method methodOfBean = bean.getClass().getMethod(methodName);
//                        return methodOfBean.invoke(bean, args);
                    }
                });

//                for (Class<?> aClass : o.getClass().getInterfaces()) {
//                    System.out.println(aClass);
//                }

                bean = (T) o;
            }
        }

        private boolean isAnyMethodAnnotatedWith(Class<? extends Annotation> annotation) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(annotation)) {

                    return true;
                }
            }
            return false;
        }
    }
}
