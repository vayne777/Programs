package example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }
    ArrayList arrayList;
}


final class Pair<K, V> {
    private final K key;
    private final V value;

    private Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return key.hashCode() + value.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        Pair other = (Pair)obj;


        if (Objects.equals(this.getFirst(), other.getFirst()) && Objects.equals(this.getSecond(), other.getSecond()))
            return true;
        return false;
    }


    public K getFirst() {
        return key;
    }
    public V getSecond() {
        return value;
    }
    public static <K,V>Pair<K,V> of(K key, V value) {
        return new Pair(key,value);
    }
}