package com.gupaoedu.mybatis.plugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xcao
 * @Description:
 * @Date:Create in 15:32 2018/4/10
 * @Modified By:
 */
public class MyInterceptorChain {

    private final List<MyInterceptor> interceptors = new ArrayList<>();

    public Object pluginAll(Object target) throws Exception {
        for (MyInterceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(MyInterceptor interceptor) {
        interceptors.add(interceptor);
    }

    public List<MyInterceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }
}
