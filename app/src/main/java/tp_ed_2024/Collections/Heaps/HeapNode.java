package tp_ed_2024.Collections.Heaps;

import tp_ed_2024.Collections.Trees.BinaryTreeNode;

public class HeapNode<T> extends BinaryTreeNode<T> {

    protected HeapNode<T> parent;

    HeapNode(T obj) {
        super(obj);
        parent = null;
    }

    public HeapNode<T> getParent() {
        return parent;
    }

    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }
}
