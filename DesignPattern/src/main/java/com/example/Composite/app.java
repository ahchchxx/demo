package com.example.Composite;

/**
 * 组合模式，就是在一个对象中包含其他对象，这些被包含的对象可能是终点对象（不再包含别的对象），
 * 也有可能是非终点对象（其内部还包含其他对象，或叫组对象），
 * 我们将对象称为节点，即一个根节点包含许多子节点，这些子节点有的不再包含子节点，
 * 而有的仍然包含子节点，以此类推。
 *
 * 很明显，这是树形结构，终结点叫叶子节点，非终节点（组节点）叫树枝节点，第一个节点叫根节点。
 */
public class app {
    public static void main(String[] args) {
        Entity boss = new Entity("Boss", 68);

        Entity assistant = new Entity("Assistant", 18);
        Entity dc_header = new Entity("DC Header", 36);
        Entity dc1 = new Entity("DC1", 25);

        boss.add(assistant);
        boss.add(dc_header);
        dc_header.add(dc1);

        System.out.println(boss);
        for (Entity e : boss.getList()) {
            System.out.println(e);
            for (Entity e1 : e.getList()) {
                System.out.println(e1);
            }
        }
    }
}
