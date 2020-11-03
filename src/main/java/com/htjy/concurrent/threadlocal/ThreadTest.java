package com.htjy.concurrent.threadlocal;

public class ThreadTest {
    private static ThreadLocal<User> t_user= new ThreadLocal();
    private static ThreadLocal<Cat> t_cat = new ThreadLocal();
    public static void main(String[] args) {
        User u = new User();
        u.name = "zhangsan";
        Cat c = new Cat();
        c.name = "kitty";
        t_user.set(u);
        t_cat.set(c);
        Cat d = new Cat();
        d.name = "jake";
        t_cat.set(d);
        t_cat.remove(); // 防止内存泄漏， 因为key弱引用被回收， value还会再， 所以一定要用remove
        t_cat = null;
        Thread t = Thread.currentThread();
    }
}
class User{
    public String name;
}

class Cat {
    public String name;
}