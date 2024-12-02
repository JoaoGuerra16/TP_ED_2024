package tp_ed_2024.Collections.Trees;

import java.util.Iterator;

import tp_ed_2024.Collections.Exceptions.EmptyCollectionException;
import tp_ed_2024.Collections.Exceptions.UnsupportedOperationException;
import tp_ed_2024.Collections.Interfaces.*;
import tp_ed_2024.Collections.Listas.UnorderedArrayList;
import tp_ed_2024.Collections.Queues.LinkedQueue;

public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    protected int count;
    protected BinaryTreeNode<T> root;

    public LinkedBinaryTree() {
        count = 0;
        root = null;
    }

    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    @Override
    public T getRootElement()  throws EmptyCollectionException {
        return (T) root;

    }

    @Override
    public boolean isEmpty() {
        return root == null;

    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean contains(T targetElement) {
        return contains(root, targetElement);
    }

    private boolean contains(BinaryTreeNode<T> node, T targetElement) {
        // Se o nó for nulo, o elemento não foi encontrado
        if (node == null) {
            return false;
        }

        // Verifica se o elemento atual é o que estamos procurando
        if (node.getElement().equals(targetElement)) {
            return true;
        }

        // Faz a busca recursiva nos filhos esquerdo e direito
        return contains(node.getLeft(), targetElement) || contains(node.getRight(), targetElement);
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findAgain(targetElement, root);

        if (current == null)
            throw new ElementNotFoundException("binary tree");

        return (current.getElement());
    }

    private BinaryTreeNode<T> findAgain(T targetElement,
            BinaryTreeNode<T> next) {
        if (next == null)
            return null;

        if (next.getElement().equals(targetElement))
            return next;

        BinaryTreeNode<T> temp = findAgain(targetElement, next.getLeft());

        if (temp == null)
            temp = findAgain(targetElement, next.getRight());

        return temp;
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedArrayList<T> tempList = new UnorderedArrayList<T>();
        inorder(root, tempList);

        return tempList.iterator();
    }

    protected void inorder(BinaryTreeNode<T> node,
            UnorderedArrayList<T> tempList) {
        if (node != null) {
            inorder(node.getLeft(), tempList);
            tempList.addToRear(node.getElement());
            inorder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedArrayList<T> tempList = new UnorderedArrayList<T>();
        preorder(root, tempList);

        return tempList.iterator();
    }

    protected void preorder(BinaryTreeNode<T> node,
            UnorderedArrayList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.getElement());
            preorder(node.getLeft(), tempList);
            preorder(node.getRight(), tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedArrayList<T> tempList = new UnorderedArrayList<>();
        postorder(root, tempList);
        return tempList.iterator();
    }

    protected void postorder(BinaryTreeNode<T> node,
            UnorderedArrayList<T> tempList) {
        if (node != null) {
            postorder(node.getLeft(), tempList);
            postorder(node.getRight(), tempList);
            tempList.addToRear(node.getElement());
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        LinkedQueue<T> tempList = new LinkedQueue<>();
        levelorder(root, tempList);

        return tempList.iterator();
    }

    protected void levelorder(BinaryTreeNode<T> node,
            LinkedQueue<T> tempList) {
        if (node == null) {
            return;
        }
        LinkedQueue<BinaryTreeNode<T>> queue = new LinkedQueue<>();
        queue.enqueue(node);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> currentNode = queue.dequeue();
            tempList.enqueue(currentNode.getElement());

            if (currentNode.getLeft() != null) {
                queue.enqueue(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.enqueue(currentNode.getRight());
            }
        }

    }

    @Override
    public BinaryTreeNode<T> getRootNode() throws EmptyCollectionException, UnsupportedOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRootNode'");
    }

}
