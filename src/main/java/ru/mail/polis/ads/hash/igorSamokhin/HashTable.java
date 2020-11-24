package ru.mail.polis.ads.hash.igorSamokhin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTable<Key, Value> implements ru.mail.polis.ads.hash.HashTable<Key, Value> {

    private int capacity = 16;
    private int size = 0;
    private final double loadFactor = 0.75;

    private class Node<Key, Value> {
        Key key;
        Value value;
        Node<Key, Value> next;

        public Node(@NotNull Key key, Value value, Node<Key, Value> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    transient private Node[] arr;

    private int hash(@NotNull Key key) {
        int hash = 0;
        return (hash = key.hashCode()) ^ (hash >>> 16);
    }

    private int getIndex(@NotNull Key key) {
        return hash(key) & (capacity - 1);
    }

    private void resize() {
        if (((double) size / (double) capacity) <= loadFactor) {
            return;
        }

        Node[] newArr = arr;
        arr = new Node[capacity * 2];
        for (int i = 0; i < capacity; ++i) {
            Node temp = arr[i];
            while (temp != null) {
                this.put((Key) temp.key, (Value) temp.value);
                temp = temp.next;
            }
        }
        capacity *= 2;
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        if (arr == null) {
            return null;
        }

        int index = getIndex(key);

        Node temp = arr[index];
        while (temp != null) {
            if (temp.key.equals(key)) {
                return (Value) temp.value;
            }
            temp = temp.next;
        }

        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (arr == null) {
            arr = new Node[capacity];
            for (int i = 0; i < capacity; ++i) {
                arr[i] = null;
            }
        }

        int index = getIndex(key);

        if (arr[index] == null) {
            arr[index] = new Node(key, value, null);
            ++size;
            resize();
        } else {
            Node temp = arr[index];
            while (temp.next != null) {
                if (arr[index].key.equals(key)) {
                    break;
                }
                temp = temp.next;
            }

            if (arr[index].key.equals(key)) {
                arr[index].value = value;
            } else {
                arr[index].next = new Node(key, value, null);
                ++size;
                resize();
            }
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        if (arr == null) {
            return null;
        }

        int index = getIndex(key);
        if (arr[index] == null) {
            return null;
        }

        Node temp = arr[index];
        if (temp.key.equals(key)) {
            arr[index] = temp.next;
            --size;
            return (Value) temp.value;
        }

        while (temp.next != null && temp.next.key != key) {
            temp = temp.next;
        }

        Value ret = null;
        if (temp.next != null) {
            ret = (Value) temp.next.value;
            temp.next = temp.next.next;
            --size;
        }
        return ret;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
