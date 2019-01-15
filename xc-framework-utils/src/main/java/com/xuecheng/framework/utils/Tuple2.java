package com.xuecheng.framework.utils;

import java.util.Objects;

public final class Tuple2<T0, T1> {
    public T0 _0;
    public T1 _1;

    public Tuple2(T0 _0, T1 _1) {
        this._0 = _0;
        this._1 = _1;
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

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof Tuple2)) {
            return false;
        } else {
            Tuple2 t = (Tuple2)o;
            return Objects.equals(t._0, this._0) && Objects.equals(t._1, this._1);
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this._0, this._1});
    }

    public String toString() {
        return "(" + this._0 + ", " + this._1 + ")";
    }
}
