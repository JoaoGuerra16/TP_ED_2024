package tp_ed_2024.Collections.Ficha9;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // Criando uma árvore binária e inserindo elementos
        ArrayBinaryTree<Integer> binaryTree = new ArrayBinaryTree<>(10);

        // Adicionando elementos manualmente
        binaryTree.tree[1] = 5;   // Filho esquerdo do 10
        binaryTree.tree[2] = 15;  // Filho direito do 10
        binaryTree.tree[3] = 3;   // Filho esquerdo do 5
        binaryTree.tree[4] = 7;   // Filho direito do 5
        binaryTree.tree[5] = 12;  // Filho esquerdo do 15
        binaryTree.tree[6] = 18;  // Filho direito do 15
        
        // Atualizando a contagem de elementos
        binaryTree.count = 7;

        // Verificando o tamanho da árvore
        System.out.println("Tamanho da árvore: " + binaryTree.size());

        // Testando a busca de elementos
        try {
            System.out.println("Buscando elemento 7: " + binaryTree.find(7));
            System.out.println("Buscando elemento 20: " + binaryTree.find(20)); // Este lançará exceção
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Testando iteração em ordem
        System.out.println("Elementos em ordem (in-order):");
        Iterator<Integer> inOrderIterator = binaryTree.iteratorInOrder();
        while (inOrderIterator.hasNext()) {
            System.out.print(inOrderIterator.next() + " ");
        }
        System.out.println();

        // Testando iteração pré-ordem
        System.out.println("Elementos em pré-ordem (pre-order):");
        Iterator<Integer> preOrderIterator = binaryTree.iteratorPreOrder();
        while (preOrderIterator.hasNext()) {
            System.out.print(preOrderIterator.next() + " ");
        }
        System.out.println();

        // Testando iteração pós-ordem
        System.out.println("Elementos em pós-ordem (post-order):");
        Iterator<Integer> postOrderIterator = binaryTree.iteratorPostOrder();
        while (postOrderIterator.hasNext()) {
            System.out.print(postOrderIterator.next() + " ");
        }
        System.out.println();

        // Testando iteração por nível (level-order)
        System.out.println("Elementos em nível (level-order):");
        Iterator<Integer> levelOrderIterator = binaryTree.iteratorLevelOrder();
        while (levelOrderIterator.hasNext()) {
            System.out.print(levelOrderIterator.next() + " ");
        }
        System.out.println();
    }
}