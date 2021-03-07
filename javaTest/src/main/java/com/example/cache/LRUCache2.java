package com.example.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRUCache example
 */
class LRUCache2 {
    // HashMap: <key = Question, value = ListNode>
    // LinkedList: <Answer>
    public static void main(String[] args) {
        LRUCache2 obj = new LRUCache2(6);
        obj.put(2, 123);
        System.out.println(obj.get(2));
    }

    public static class Node {
        int key, val;
        Node next, prev;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Map<Integer, Node> map = new HashMap<>();
    private Node head, tail;
    private int cap;

    public LRUCache2(int capacity) {
        cap = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            remove(node);
            appendHead(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        // 先 check 有没有这个 key
        Node node = map.get(key);
        if (node != null) {
            node.val = value;
            // 把这个node放在最前面去
            remove(node);
            appendHead(node);
        } else {
            node = new Node(key, value);
            if (map.size() < cap) {
                appendHead(node);
                map.put(key, node);
            } else {
                // 踢走老的
                map.remove(tail.key);
                remove(tail);
                appendHead(node);
                map.put(key, node);
            }
        }
    }

    private void appendHead(Node node) {
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    private void remove(Node node) {
        // 这里我写的可能不是最 elegant 的，但是是很 readable 的
        if (head == tail) { // there is only one node: head==tail==node
            head = tail = null;
        } else {
            if (head == node) { // node is in head first
                head = head.next;
                node.next = null;
            } else if (tail == node) { // node is in tail
                tail = tail.prev;
                tail.next = null;
                node.prev = null;
            } else { // in middle position
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.prev = null;
                node.next = null;
            }
        }
    }
}