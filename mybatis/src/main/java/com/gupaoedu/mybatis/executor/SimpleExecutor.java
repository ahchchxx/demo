package com.gupaoedu.mybatis.executor;

import com.gupaoedu.mybatis.config.GpConfiguration;
import com.gupaoedu.mybatis.config.MapperRegistory;
import com.gupaoedu.mybatis.statement.StatementHandler;

public class SimpleExecutor implements Executor {
    private GpConfiguration configuration;

    public SimpleExecutor(GpConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> E query(MapperRegistory.MapperData mapperData, Object parameter) throws Exception {
        //初始化StatementHandler --> ParameterHandler --> ResultSetHandler
        StatementHandler handler = new StatementHandler(configuration);
        // this.configuration.newExecutor(this);
        return (E) handler.query(mapperData, parameter);
    }

    public GpConfiguration getConfiguration() {
        return configuration;
    }
    public void setConfiguration(GpConfiguration configuration) {
        this.configuration = configuration;
    }
}