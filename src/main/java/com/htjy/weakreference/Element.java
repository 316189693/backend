package com.htjy.weakreference;

public class Element {
    private String ident;

    public Element(String id) {
        ident = id;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    public int hashCode() {
        return ident.hashCode();
    }

    @Override
    public boolean equals(Object r) {
        return r instanceof Element && ident.equals(((Element) r).ident);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalizing "+getClass().getSimpleName()+" "+ident);
    }

}
