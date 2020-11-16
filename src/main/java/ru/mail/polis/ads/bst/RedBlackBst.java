package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node head = null;
    private int size = 0;

    private class Node {
        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }

        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node temp = head;

        while (temp != null) {
            int compResult = key.compareTo(temp.key);
            if (compResult > 0) {
                temp = temp.right;
            } else if (compResult < 0) {
                temp = temp.left;
            } else {
                return temp.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        head = put(head, key, value);
        head.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if (head == null) {
            return null;
        }
        Value temp = get(key);
        if(temp != null) {
            head = delete(head, key);
            --size;
        }
        return temp;
    }

    public Node minNode(Node x) {
        Node temp = x;
        while (temp != null && temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    @Nullable
    @Override
    public Key min() {
        Node n = minNode(head);
        return n == null ? null : n.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node n = minNode(head);
        return n == null ? null : n.value;
    }

    public Node maxNode(Node x) {
        Node temp = head;
        while (temp != null && temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    @Nullable
    @Override
    public Key max() {
        Node n = maxNode(head);
        return n == null ? null : n.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node n = maxNode(head);
        return n == null ? null : n.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node temp = floor(head, key);
        return temp == null ? null : temp.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        Node temp = node;

        int compResult = key.compareTo(temp.key);
        if (compResult > 0) {
            if ((temp.right != null) && ((key.compareTo(temp.right.key) >= 0) || ((temp.right.left != null) && temp.right.left.color))) {
                temp = floor(temp.right, key);
            }
            return temp;
        } else if (compResult < 0) {
            temp = floor(temp.left, key);
        }

        return temp;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node temp = ceil(head, key);
        if (temp == null || temp.key.compareTo(key) < 0) {
            return null;
        }
        return temp.key;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        Node temp = node;

        int compResult = key.compareTo(temp.key);
        if ((compResult > 0) && (temp.right != null)) {
            temp = ceil(temp.right, key);
        } else if ((compResult < 0) && (temp.left != null)) {
            temp = ceil(temp.left, key);
        }

        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            ++size;
            return new Node(key, value, RED);
        }

        int compResult = key.compareTo(x.key);
        if (compResult < 0) {
            x.left = put(x.left, key, value);
        } else if (compResult > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }

        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    public void deleteMin() {
        head = deleteMin(head);
        head.color = BLACK;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = deleteMax(x.right);
        return fixUp(x);
    }

    public void deleteMax() {
        head = deleteMax(head);
        head.color = BLACK;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int compResult = key.compareTo(x.key);
        if (compResult < 0) {
            //delete left
            if (x.left != null) {
                if (!isRed(x.left) && !isRed((x.left.left))) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else {
            //delete right
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (compResult == 0 && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                //delete under invariant
                if (x.key == key) {
                    Node min = minNode(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = delete(x.right, key);
                }
            }
        }
        return fixUp(x);
    }
}
