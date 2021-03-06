package com.xuecheng.framework.utils;

import java.util.Objects;

public final class Tuple3<T0, T1, T2> {
    public T0 _0;
    public T1 _1;
    public T2 _2;

    public Tuple3(T0 _0, T1 _1, T2 _2) {
        this._0 = _0;
        this._1 = _1;
        this._2 = _2;
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

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof Tuple3)) {
            return false;
        } else {
            Tuple3 t = (Tuple3)o;
            return Objects.equals(t._0, this._0) && Objects.equals(t._1, this._1) && Objects.equals(t._2, this._2);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this._0, this._1, this._2});
    }

    public String toString() {
        return "(" + this._0 + ", " + this._1 + ", " + this._2 + ")";
    }
}