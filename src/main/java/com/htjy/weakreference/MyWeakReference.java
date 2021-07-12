package com.htjy.weakreference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class MyWeakReference {
    public static void main(String[] args) throws Exception{
        S s = new S();
        s.name="ffffffffffffffffffffff";
        ReferenceQueue<S> rq = new ReferenceQueue<>();
        WeakReference ref = new WeakReference<S>(s, rq);
        WeakHashMap wh = new WeakHashMap();
        wh.put(s,"dddd");
        S s1 = new S();
        s1.name="ggggggggggggggg";
        wh.put(s1,"ddddddddff");

        System.out.println(ref.get()); // java.lang.Object@f9f9d8
        System.out.println(rq.poll());// null
        s = null;
        System.gc();
        System.out.println(ref.get());

        Thread.sleep(200);
        System.out.println(rq.poll() == ref);
        S s2 = new S();
        s2.name="ggggggggggggggg";
        wh.put(s2,"ddddddddff");
    }

}
class S{
    public String name;
}
