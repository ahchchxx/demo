package com.example.Composite2;

public class app {
    public static void main(String[] args) {
        Node node = new Node("folderParent");
        node.addNode(new Document("file1"));
        node.addNode(new Document("file2"));

        node.addNode(new Node("folderSon1"));

        node.display();
    }
}
