package tp_ed_2024.Collections.Ficha9;

import java.util.Iterator;
import tp_ed_2024.Collections.Ficha4.LinkedQueue;
import tp_ed_2024.Collections.Ficha5.UnorderedArrayList;
import tp_ed_2024.Collections.Interfaces.*;

public class ArrayBinaryTree<T extends Comparable<T>> implements BinaryTreeADT<T> {
    protected int count;
    protected T[] tree;
    private final int CAPACITY = 50;

    @SuppressWarnings("unchecked")
    public ArrayBinaryTree(T element) {
        count = 1;
        // Cria um array genérico corretamente
        tree = (T[]) java.lang.reflect.Array.newInstance(element.getClass(), CAPACITY);
        tree[0] = element;
    }

    // O resto da sua implementação permanece inalterado
    public T find(T targetElement) throws ElementNotFoundException {
        T temp = null;
        boolean found = false;

        for (int ct = 0; ct < count && !found; ct++)
            if (targetElement.equals(tree[ct])) {
                found = true;
                temp = tree[ct];
            }
        if (!found)
            throw new ElementNotFoundException("binary tree");
        return temp;
    }

    @Override
    public T getRoot() {
        return tree[0];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean contains(T targetElement) {
        for (int i = 0; i < count; i++) {
            if (tree[i] != null && tree[i].equals(targetElement)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        UnorderedArrayList<T> templist = new UnorderedArrayList<T>();
        inorder(0, templist);
        return templist.iterator();
    }

    protected void inorder(int node, UnorderedArrayList<T> templist) {
        if (node < count) {
            if (tree[node] != null) {
                inorder(node * 2 + 1, templist);
                templist.addToRear(tree[node]);
                inorder((node + 1) * 2, templist);
            }
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        UnorderedArrayList<T> templist = new UnorderedArrayList<T>();
        preorder(0, templist);
        return templist.iterator();
    }

    protected void preorder(int node, UnorderedArrayList<T> templist) {
        if (node < count) {
            if (tree[node] != null) {
                templist.addToRear(tree[node]);
                preorder(node * 2 + 1, templist);
                preorder((node + 1) * 2, templist);
            }
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        UnorderedArrayList<T> templist = new UnorderedArrayList<T>();
        postorder(0, templist);
        return templist.iterator();
    }

    protected void postorder(int node, UnorderedArrayList<T> templist) {
        if (node < count) {
            if (tree[node] != null) {
                postorder(node * 2 + 1, templist);
                postorder((node + 1) * 2, templist);
                templist.addToRear(tree[node]);
            }
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        UnorderedArrayList<T> templist = new UnorderedArrayList<T>();
        levelorder(0, templist);
        return templist.iterator();
    }

    protected void levelorder(int node, UnorderedArrayList<T> templist) {
        if (isEmpty()) {
            return;
        }
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(0);

        while (!queue.isEmpty()) {
            int index = queue.dequeue();

            if (tree[index] != null) {
                templist.addToRear(tree[index]);
                queue.enqueue(index * 2 + 1);
                queue.enqueue(index * 2 + 2);
            }
        }
    }
}
