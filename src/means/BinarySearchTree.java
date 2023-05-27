package means;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<T> {

    protected Node<T> root;
    private Comparator<T> comparator;

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void add(T data) {
        root = addRecursive(data, root);
    }

    protected Node<T> addRecursive(T data, Node<T> node) {
        if (node == null) {
            node = new Node<T>(data);
        } else if (comparator.compare(data, node.getData()) < 0) {
            node.setLeft(addRecursive(data, node.getLeft()));
        } else if (comparator.compare(data, node.getData()) > 0) {
            node.setRight(addRecursive(data, node.getRight()));
        } else {
        	throw new IllegalArgumentException("AVL Tree already contains a node with key " + data);
        }
        return node;
    }

    public void remove(T value) {
        root = removeRecursive(root, value);
    }

    protected Node<T> removeRecursive(Node<T> current, T value) {
        if (current == null) {
            return null;
        }

        if (comparator.compare(value, current.getData()) == 0) {
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            if (current.getRight() == null) {
                return current.getLeft();
            }

            if (current.getLeft() == null) {
                return current.getRight();
            }

            T smallestValue = findSmallestValue(current.getRight());
            current.setData(smallestValue);
            current.setRight(removeRecursive(current.getRight(), smallestValue));
            return current;
        }
        if (comparator.compare(value, current.getData()) < 0) {
            current.setLeft(removeRecursive(current.getLeft(), value));
            return current;
        }
        current.setRight(removeRecursive(current.getRight(), value));
        return current;
    }

    public T findSmallestValue() {
        return findSmallestValue(root);
    }

    public T findMaxValue() {
        return findMaxValue(root);
    }

    private T findSmallestValue(Node<T> root) {
        return root.getLeft() == null ? root.getData() : findSmallestValue(root.getLeft());
    }

    private T findMaxValue(Node<T> root) {
        return root.getRight() == null ? root.getData() : findMaxValue(root.getRight());
    }

    public Node<T> searchNode(T data) {
        return searchNode(data, root);
    }

    private Node<T> searchNode(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(data, node.getData()) == 0) {
            return node;
        } else if (comparator.compare(data, node.getData()) < 0) {
            return searchNode(data, node.getLeft());
        } else {
            return searchNode(data, node.getRight());
        }
    }

    public ArrayList<T> inOrder() {
    	ArrayList<T> sheet =  new ArrayList<T>();
        return inOrder(root,sheet);
    }

    private ArrayList<T> inOrder(Node<T> node,ArrayList<T> sheet ) {
    	ArrayList<T> sheet1 = sheet;
        if (node != null) {
            inOrder(node.getLeft(), sheet1);
            sheet1.add(node.getData());
            inOrder(node.getRight(), sheet1);
        }
        return sheet1;
    }

    public String preOrder() {
        return preOrder(root, new StringBuilder()).toString();
    }

    private StringBuilder preOrder(Node<T> node, StringBuilder str) {
        if (node != null) {
            str.append(node.getData()).append(" ");
            preOrder(node.getLeft(), str);
            preOrder(node.getRight(), str);
        }
        return str;
    }

    public String postOrder() {
        return postOrder(root, new StringBuilder()).toString();
    }

    public StringBuilder postOrder(Node<T> node, StringBuilder str) {
        if (node != null) {
            postOrder(node.getLeft(), str);
            postOrder(node.getRight(), str);
            str.append(node.getData()).append(" ");
        }
        return str;
    }

    public int getNumberOfNodes() {
        return getNumberOfNodes(root);
    }

    public  int getNumberOfNodes(Node<T> node) {
        if (null == node ) return 0;
        return 1 + getNumberOfNodes( node.getLeft() ) + getNumberOfNodes( node.getRight() );
    }

    public boolean isEmpty() {
        return root == null;
    }
}
