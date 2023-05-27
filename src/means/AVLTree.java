package means;

import java.util.Comparator;

import static java.lang.Math.max;

public class AVLTree<T> extends BinarySearchTree<T> { 
	
    public AVLTree(Comparator<T> comparator) {
        super(comparator);
    }

    //Metodos de equilibrio
    private int height(Node<T> node) {
        return node != null ? node.getHeight() : -1;
    }

    private void updateHeight(Node<T> node) {
        int leftChildHeight = height(node.getLeft());
        int rightChildHeight = height(node.getRight());
        node.setHeight(max(leftChildHeight, rightChildHeight) + 1);
    }

    private int balanceFactor(Node<T> node) {
        return height(node.getRight()) - height(node.getLeft());
    }

    //Metodos de rotacion
    private Node<T> rotateRight(Node<T> node) {
        Node<T> leftChild = node.getLeft();
        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);
        updateHeight(node);
        updateHeight(leftChild);
        return leftChild;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightChild = node.getRight();
        node.setRight(rightChild.getLeft());
        rightChild.setLeft(node);
        updateHeight(node);
        updateHeight(rightChild);
        return rightChild;
    }

    //Metodos de balanceo
    private Node<T> rebalance(Node<T> node) {
        int balanceFactor = balanceFactor(node);
        // Left-heavy?
        if (balanceFactor < -1) {
            if (balanceFactor(node.getLeft()) <= 0) {    // Case 1
                // Rotate right
                node = rotateRight(node);
            } else {                                // Case 2
                // Rotate left-right
                node.setLeft(rotateLeft(node.getLeft()));
                node = rotateRight(node);
            }
        }
        // Right-heavy?
        if (balanceFactor > 1) {
            if (balanceFactor(node.getRight()) >= 0) {    // Case 3
                // Rotate left
                node = rotateLeft(node);
            } else {                                 // Case 4
                // Rotate right-left
                node.setRight(rotateRight(node.getRight()));
                node = rotateLeft(node);
            }
        }
        return node;
    }

    @Override
    protected Node<T> addRecursive(T data, Node<T> node) {
        node = super.addRecursive(data,node);
        updateHeight(node);
        return rebalance(node);
    }

    @Override
    protected Node<T> removeRecursive(Node<T> current, T value) {
        current = super.removeRecursive(current,value);
        if(current == null){
            return null;
        }
        updateHeight(current);
        return rebalance(current);
    }

    
    

}
