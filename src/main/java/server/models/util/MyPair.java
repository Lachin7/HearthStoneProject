package server.models.util;

import java.io.Serializable;

public class MyPair<U,V> implements Serializable{
    public U key;
    public V value;

    public MyPair(U key, V value) {
        this.key = key;
        this.value = value;
    }

    public U getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MyPair<?, ?> pair = (MyPair<?, ?>) o;
        if (!key.equals(pair.key))
            return false;
        return value.equals(pair.value);
    }

    @Override
    public int hashCode() {
        return 31 * key.hashCode() + value.hashCode();
    }

    @Override
    public String toString() {
        return key + ":" +value;
    }

}

