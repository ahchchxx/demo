package com.example.Composite2;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现目录节点
 */
public class Node extends ANode {
    List<ANode> nodeList = new ArrayList<ANode>();//内部节点列表（包括文件和下级目录）

    //通过构造器为当前目录节点赋名
    public Node(String name) {
        super(name);
    }

    //新增节点
    public void addNode(ANode node) {
        nodeList.add(node);
    }

    //递归循环显示下级节点
    @Override
    void display() {
        System.out.println(name);
        for (ANode node : nodeList) {
            node.display();
        }
    }
}