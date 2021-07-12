package com.htjy.weakreference;

import java.util.Set;
import java.util.WeakHashMap;

public class CannonicalMapping {
    public static void main(String[] args) {
            int size=10;
            if(args.length>0){
                size=new Integer(args[0]);
            }
            Key[] keys=new Key[size];
            WeakHashMap<Key, Value> map=new WeakHashMap<Key, Value>();
            for(int i=0;i<size;i++){
                Key k=new Key(Integer.toString(i));
                Value v=new Value(Integer.toString(i));
                if(i%3==0){
                    keys[i]=k;
                }
                map.put(k, v);
            }
        System.out.println("start GC....");
            System.gc();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Set<Key> _keys=map.keySet();
            for(Key key:_keys){
                System.out.println("key :"+key+";value :"+map.get(key));
            }

    }
}
