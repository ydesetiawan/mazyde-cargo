package com.mazyde.cargo.common.vo;


public class Tuple<T, U> {

    private final T t;

    private final U u;

    public Tuple(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public Tuple withTuppleT(T t) {
        return new Tuple(t, this.u);
    }

    public Tuple withTuppleU(U u) {
        return new Tuple(this.t, u);
    }

    public T getT() {
        return t;
    }

    public U getU() {
        return u;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (t != null ? !t.equals(tuple.t) : tuple.t != null) return false;
        return u != null ? u.equals(tuple.u) : tuple.u == null;

    }

    @Override
    public int hashCode() {
        int result = t != null ? t.hashCode() : 0;
        result = 31 * result + (u != null ? u.hashCode() : 0);
        return result;
    }
}
