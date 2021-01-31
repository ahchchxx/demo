package com.example.threads.example1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<ParamVo> splitList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            splitList.add(new ParamVo());
        }

        MultiThread<ParamVo, ResultVo> multiThread = new MultiThread<ParamVo, ResultVo>(splitList) {
            @Override
            public ResultVo outExecute(int currentThread, ParamVo data) {
                System.out.println("当前线程号=" + currentThread + " data=" + data);
                return new ResultVo();
            }
        };

        //获取每一批次处理结果
        List<ResultVo> list = multiThread.getResult();
        System.out.println("获取处理结果........................");
        for (ResultVo vo : list) {
            System.out.println(vo);
        }
    }

}
