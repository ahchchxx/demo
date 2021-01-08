package com.example.strategy;

public class app {

    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("2 + 3 = " + context.doOperate(2, 3));

        context = new Context(new OperationMulti());
        System.out.println("2 * 3 = " + context.doOperate(2, 3));
    }

}
