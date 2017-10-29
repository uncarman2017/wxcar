package com.kxcar.wxcar.util.config;

import com.google.common.base.Optional;

/**
 * Created by jiaweiyu on 2016/12/5.
 */
public abstract class Tuple {

//    public static void main (String[] args) {
//        Tuple tuple2 = Tuple.<String, Integer>of("hello", 1);
//        Tuple tuple3 = Tuple.<String, Integer, String>of("hello", 1, "hi");
//        System.out.println(tuple2._1().get() + "|" + tuple2._2().get());
//        System.out.println(tuple3._1().get() + "|" + tuple3._2().get() + "|" + tuple3._3().get());
//
//    }

    public static  <E, T> Tuple of (E e, T t) {
        return new Tuple2(e, t);
    }

    public static  <E, T, K> Tuple of (E e, T t, K k) {
        return new Tuple3(e, t, k);
    }

    public abstract <E> Optional<E> _1 ();

    public abstract <E> Optional<E> _2 ();

    public abstract <E> Optional<E> _3 ();

}

class Tuple2<E, T> extends Tuple {
    private E e;
    private T t;

    Tuple2 (E e, T t) {
        this.e = e;
        this.t = t;
    }

    @Override
    public Optional<E> _1 () {
        return Optional.of(e);
    }

    @Override
    public Optional<T> _2 () {
        return Optional.of(t);
    }

    @Override
    public <E> Optional<E> _3() {
        return Optional.absent();
    }
}

class Tuple3<E, T, K> extends Tuple {
    private E e;
    private T t;
    private K k;

    Tuple3 (E e, T t, K k) {
        this.e = e;
        this.t = t;
        this.k = k;
    }

    public Optional<E> _1 () {
        return Optional.of(e);
    }

    public Optional<T> _2 () {
        return Optional.of(t);
    }

    public Optional<K> _3 () {
        return Optional.of(k);
    }
}
