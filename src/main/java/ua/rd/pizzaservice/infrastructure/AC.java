//package ua.rd.pizzaservice.infrastructure;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//import java.util.HashMap;
//import java.util.Map;
//
//public class AC implements Context {
//
//    private final Config config;
//    private Map<String, Object> beans = new HashMap<>();
//
//    public AC(Config config) {
//        this.config = config;
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public <T> T getBean(String beanName) {
//
//        if (beans.containsKey(beanName)) {
//            return (T) beans.get(beanName);
//        }
//
//        Class<?> type = config.getImpl(beanName);
//
//        BeanBuilder<T> builder = new BeanBuilder<>(type);
//        builder.createBean();
//
//
//        builder.callInitMethod();
//        builder.callPostCreateMehod();
//        builder.createBeanProxy();
//
//        T tmpBean = builder.build();
//
//        beans.put(beanName, tmpBean);
//        return tmpBean;
//
//
//    }
//
//
//    class BeanBuilder<T> {
//
//        private final Class<?> type;
//        private T bean;
//
//        public BeanBuilder(Class<?> type) {
//            this.type = type;
//        }
//
//        public void callInitMethod() {
//
//            try {
//                bean.getClass().getMethod("init").invoke(bean);
//            } catch (Exception e) {
//                return;
//            }
//        }
//
//        public void callPostCreateMehod() {
//            Method[] methods = bean.getClass().getDeclaredMethods();
//            for (Method m : methods) {
//                if (m.getAnnotation(PostCreate.class) != null) {
//                    try {
//                        m.invoke(bean);
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//
//            }
//        }
//
//        @SuppressWarnings("unchecked")
//        public void createBeanProxy() {
//            Method[] methods = bean.getClass().getDeclaredMethods();
//            for (Method m : methods) {
//                Benchmark benchmark = m.getAnnotation(Benchmark.class);
//                if (benchmark != null && benchmark.enabled()) {
//                    T targenBean = bean;
//                    bean = (T) Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
//
//                        @Override
//                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                            long startTime = System.nanoTime();
//                            Object object = method.invoke(targenBean, args);
//                            long endTime = System.nanoTime();
//
//                            System.out.println("Total execution time= " + (endTime - startTime));
//                            return object;
//                        }
//                    });
//
//
//                }
//            }
//        }
//
//        @SuppressWarnings("unchecked")
//        public void createBean() {
//
//            Constructor<?> constructor = type.getConstructors()[0];
//
//            try {
//
//                if (constructor.getParameterCount() == 0) {
//                    this.bean = (T) type.newInstance();
//                } else {
//                    this.bean = (T) constructor.newInstance(createDependencyBeans(constructor));
//                }
//
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        public T build() {
//            return bean;
//        }
//
//        private Object[] createDependencyBeans(Constructor<?> constructor) {
//            Class<?>[] parameterTypes = constructor.getParameterTypes();
//            Object[] params = new Object[constructor.getParameterCount()];
//
//            for (int i = 0; i < constructor.getParameterCount(); i++) {
//                String tmpBeanName = convertTypeToBeanName(parameterTypes[i]);
//                params[i] = getBean(tmpBeanName);
//            }
//
//            return params;
//        }
//
//        private String convertTypeToBeanName(Class<?> paramClass) {
//            StringBuilder builder = new StringBuilder(paramClass.getSimpleName());
//            builder.setCharAt(0, Character.toLowerCase(builder.charAt(0)));
//            return builder.toString();
//        }
//
//    }
//}