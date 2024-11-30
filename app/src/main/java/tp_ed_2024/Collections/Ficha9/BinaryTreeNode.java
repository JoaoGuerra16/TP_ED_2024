package tp_ed_2024.Collections.Ficha9;

public class BinaryTreeNode<T> {

    private BinaryTreeNode<T> right, left;
    private T element;

    public BinaryTreeNode(T element) {
        this.right = null;
        this.left = null;
        this.element = element;

    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public int numChildren() {
        int children = 0;
        if (left != null)
        children = 1 + left.numChildren();
        if (right != null)
        children = children + 1 + right.numChildren();
        return children;
        }
       }

