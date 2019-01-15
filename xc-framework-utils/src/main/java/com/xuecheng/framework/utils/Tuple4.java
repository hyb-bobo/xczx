package com.xuecheng.framework.utils;

import java.util.Objects;

public final class Tuple4<T0, T1, T2, T3> {
    public T0 _0;
    public T1 _1;
    public T2 _2;
    public T3 _3;

    public Tuple4(T0 _0, T1 _1, T2 _2, T3 _3) {
        this._0 = _0;
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
    }

    public T0 get_0() {
        return this._0;
    }

    public void set_0(T0 _0) {
        this._0 = _0;
    }

    public T1 get_1() {
        return this._1;
    }

    public void set_1(T1 _1) {
        this._1 = _1;
    }

    public T2 get_2() {
        return this._2;
    }

    public void set_2(T2 _2) {
        this._2 = _2;
    }

    public T3 get_3() {
        return this._3;
    }

    public void set_3(T3 _3) {
        this._3 = _3;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof Tuple4)) {
            return false;
        } else {
            Tuple4 t = (Tuple4)o;
            return Objects.equals(t._0, this._0) && Objects.equals(t._1, this._1) && Objects.equals(t._2, this._2) && Objects.equals(t._3, this._3);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this._0, this._1, this._2, this._3});
    }

    public String toString() {
        return "(" + this._0 + ", " + this._1 + ", " + this._2 + ", " + this._3 + ")";
    }
}