package com.gupaoedu.mybatis.config;

import com.gupaoedu.mybatis.executor.Executor;
import com.gupaoedu.mybatis.plugin.MyInterceptor;
import com.gupaoedu.mybatis.plugin.MyInterceptorChain;

import java.io.IOException;

/**
 * Created by James on 2017-07-01.
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 */
public class GpConfiguration {

    // public static void main(String[] args) throws Exception {
    //     new GpConfiguration().scanPath("com/gupaoedu/mybatis/config/mappers").build();
    // }

    protected final MyInterceptorChain interceptorChain = new MyInterceptorChain();
    private String inteceptor = null;
    {
        inteceptor = "com.gupaoedu.mybatis.my.MyInterceptorTest";
    }
    private String scanPath;
    private MapperRegistory mapperRegistory = new MapperRegistory();

    public Object newExecutor(Object object) throws Exception {
        return (Object) interceptorChain.pluginAll(object);
    }

    // public GpConfiguration scanPath(String scanPath) {
    //     this.scanPath = scanPath;
    //     return this;
    // }

    public void build() throws Exception {
        if (null == scanPath || scanPath.length() < 1) {
            throw new RuntimeException("scan path is required.");
        }
        if (inteceptor != null) {
            // 加载自定义的插件
            MyInterceptor myInterceptor = (MyInterceptor)Class.forName(inteceptor).newInstance();
            addInterceptor(myInterceptor);
        }
    }

    public void addInterceptor(MyInterceptor interceptor) {
        this.interceptorChain.addInterceptor(interceptor);
    }

    public String getScanPath() {
        return scanPath;
    }
    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public MapperRegistory getMapperRegistory() {
        return mapperRegistory;
    }
    public void setMapperRegistory(MapperRegistory mapperRegistory) {
        this.mapperRegistory = mapperRegistory;
    }

    @Override
    public String toString() {
        return "GpConfiguration{" +
                "scanPath='" + scanPath + '\'' +
                ", mapperRegistory=" + mapperRegistory +
                '}';
    }
}
