package com.htjy.redblacktree;

/**
 * Created By willz
 * Date : 2020/3/16
 * Time: 17:29
 */
public class RedBlackTree {

   public  RedBlackTree() {

    }

}

class TreeNode<V> {
    private volatile V value;
    public TreeNode(V value){this.value = value;}
    TreeNode(){}
}