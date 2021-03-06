package com.gupaoedu.mybatis.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.lang.reflect.Proxy;

/**
 * @Author: xcao
 * @Description:
 * @Date:Create in 17:07 2018/4/10
 * @Modified By:
 */
public class MyPlugin implements InvocationHandler {
    private MyInterceptor interceptor;
    private Object object;
    private Map<Class<?>, Set<Method>> signatureMap;

    public MyPlugin(Object object, MyInterceptor interceptor, Map<Class<?>, Set<Method>> signatureMap) {
        this.interceptor = interceptor;
        this.object = object;
        this.signatureMap = signatureMap;
    }

    /**
     * 代理方法，调用被代理方法时，会直接执行这个方法，
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Set<Method> methods = this.signatureMap.get(method.getDeclaringClass());
        return methods != null && methods.contains(method)
                ? this.interceptor.intercept(new MyInvocation(this.object, method, args))
                : method.invoke(this.object, args);
    }


    public static Object wrap(Object target, MyInterceptor interceptor) throws Exception {
        Map<Class<?>, Set<Method>> signatureMap = getSignatureMap(interceptor);
        Class<?> type = target.getClass();
        Class<?>[] interfaces = getAllInterfaces(type, signatureMap);
        return interfaces.length > 0
                ? Proxy.newProxyInstance(type.getClassLoader(), interfaces, new MyPlugin(target, interceptor, signatureMap))
                : target;
    }

    private static Map<Class<?>, Set<Method>> getSignatureMap(MyInterceptor interceptor) throws Exception {
        PluginAnnotation interceptsAnnotation = (PluginAnnotation) interceptor.getClass().getAnnotation(PluginAnnotation.class);
        if (interceptsAnnotation == null) {
            throw new Exception("No @Intercepts annotation was found in interceptor " + interceptor.getClass().getName());
        } else {
            MySignature[] sigs = interceptsAnnotation.value();
            Map<Class<?>, Set<Method>> signatureMap = new HashMap();
            int sigLen = sigs.length;

            for (int i = 0; i < sigLen; ++i) {
                MySignature sig = sigs[i];
                Set<Method> methods = (Set) signatureMap.get(sig.type());
                if (methods == null) {
                    methods = new HashSet();
                    signatureMap.put(sig.type(), methods);
                }

                try {
                    Method method = sig.type().getMethod(sig.method(), sig.args());
                    ((Set) methods).add(method);
                } catch (NoSuchMethodException e) {
                    throw new Exception("Could not find method on " + sig.type() + " named " + sig.method() + ". Cause: " + e);
                }
            }

            return signatureMap;
        }
    }

    private static Class<?>[] getAllInterfaces(Class<?> type, Map<Class<?>, Set<Method>> signatureMap) {
        HashSet interfaces;
        for (interfaces = new HashSet(); type != null; type = type.getSuperclass()) {
            Class[] typeInterfaces = type.getInterfaces();
            int length = typeInterfaces.length;

            for (int i = 0; i < length; ++i) {
                Class<?> c = typeInterfaces[i];
                if (signatureMap.containsKey(c)) {
                    interfaces.add(c);
                }
            }
        }

        return (Class[]) interfaces.toArray(new Class[interfaces.size()]);
    }
}
