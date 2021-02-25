package com.example.Composite2;

/**
 * 实现文件节点
 */
public class Document extends ANode {
    //通过构造器为文件节点命名
    public Document(String name) {
        super(name);
    }
    //显示文件节点
    @Override
    public void display() {
        System.out.println(name);
    }
}
