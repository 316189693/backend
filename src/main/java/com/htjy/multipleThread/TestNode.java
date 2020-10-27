package com.htjy.multipleThread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created By willz
 * Date : 2020/10/14
 * Time: 11:26
 */
public class TestNode {
    public static void main(String[] args) {
        Node node1= new Node();
        Node node2= new Node();
        System.out.println(Node.SHARED);
    }

    static final class Node{
        static final Node SHARED = new Node();
        public String name;
        Node() {}
    }
}

