package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private class Node {
        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }

        Key key;
        Value value;
        Node left;
        Node right;
        int height = 1;
    }

    private Node head = null;
    private int size = 0;

    @Override
    public Value get(@NotNull Key key) {
        Node temp = head;

        while (temp != null) {
            if (key.compareTo(temp.key) > 0) {
                temp = temp.right;
            } else if (key.compareTo(temp.key) < 0) {
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
    }

    private Node put(@NotNull Node x, @NotNull Key key, @NotNull Value value) {
        if (x == null) {
            ++size;
            return new Node(key, value, 1);
        }

        if (x.key.compareTo(key) > 0) {
            x.left = put(x.left, key, value);
        } else if (x.key.compareTo(key) < 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        fixHeight(x);
        x = balance(x);
        return x;
    }

    @Override
    public Value remove(@NotNull Key key) {
        if (head == null) {
            return null;
        }
        Value temp = get(key);
        head = delete(head, key);
        return temp;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        if (x.key.compareTo(key) > 0) {
            x.left = delete(x.left, key);
        } else if (x.key.compareTo(key) < 0) {
            x.right = delete(x.right, key);
        } else {
            x = innerDelete(x);
        }
        return x;
    }

    private Node innerDelete(Node x) {
        --size;
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node temp = x;
        x = minNode(temp.right);
        x.right = deleteMin(temp.right);
        x.left = temp.left;

        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    public Node minNode(Node x) {
        Node temp = x;
        while (temp != null && temp.left != null) {
            temp = temp.left;
        }
        return temp;
    }

    @Override
    public Key min() {
        Node n = minNode(head);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    @Override
    public Value minValue() {
        Node n = minNode(head);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    public Node maxNode(Node x) {
        Node temp = head;
        while (temp != null && temp.right != null) {
            temp = temp.right;
        }
        return temp;
    }

    @Override
    public Key max() {
        Node n = maxNode(head);
        if (n == null) {
            return null;
        }
        return n.key;
    }

    @Override
    public Value maxValue() {
        Node n = maxNode(head);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node temp = floor(head, key);
        if (temp == null) {
            return null;
        }
        return temp.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        Node temp = node;

        if (key.compareTo(temp.key) > 0) {
            if ((temp.right != null) && (key.compareTo(temp.right.key) >= 0)) {
                temp = floor(temp.right, key);
            }
            return temp;
        } else if (key.compareTo(temp.key) < 0) {
            temp = floor(temp.left, key);
        }

        return temp;
    }

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

        if ((key.compareTo(temp.key) > 0) && (temp.right != null)) {
            temp = ceil(temp.right, key);
        } else if ((key.compareTo(temp.key) < 0) && (temp.left != null)) {
            temp = ceil(temp.left, key);
        }

        return temp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(head);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;

        fixHeight(y);
        fixHeight(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;

        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }
}
