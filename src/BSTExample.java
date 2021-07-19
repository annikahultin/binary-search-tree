/**
* An example class to test a Binary Search Tree
* implementation.
*
* @Author: Marcus Hill
*/

public class BSTExample
{
    public static void main(String[] args)
    {
        BST tree = new BST();
        tree.insert(4);
        tree.insert(5);
        tree.insert(2);
        tree.insert(9);
        tree.insert(6);
        tree.insert(11);
        tree.insert(10);
        tree.insert(8);
        tree.insert(1);
        tree.insert(14);

        System.out.println("Example 1: preorder traversal");
        System.out.println("Expected:\t" + "4 2 1 5 9 6 8 11 10 14");
        System.out.print("Your Tree:\t");
        tree.preorder();

        System.out.println("\nExample 2: postorder traversal");
        System.out.println("Expected:\t" + "1 2 8 6 10 14 11 9 5 4");
        System.out.print("Your Tree:\t");
        tree.postorder();

        System.out.println("\nExample 3: inorder traversal");
        System.out.println("Expected:\t" + "1 2 4 5 6 8 9 10 11 14");
        System.out.print("Your Tree:\t");
        tree.inorder();

        tree.delete(1);
        System.out.println("\nExample 4: preorder traversal after a deleting a "
                           + "leaf");
        System.out.println("Expected:\t" + "4 2 5 9 6 8 11 10 14");
        System.out.print("Your Tree:\t");
        tree.preorder();

        tree.delete(6);
        System.out.println("\nExample 5: preorder traversal after a deleting a "
                           + "node with one child");
        System.out.println("Expected:\t" + "4 2 5 9 8 11 10 14");
        System.out.print("Your Tree:\t");
        tree.preorder();

        tree.delete(9);
        System.out.println("\nExample 6: preorder traversal after a deleting a "
                           + "node with two children");
        System.out.println("Expected:\t" + "4 2 5 8 11 10 14");
        System.out.print("Your Tree:\t");
        tree.preorder();

        System.out.println("\nExample 7: size of the tree after insertions and "
                           + "deletions");
        System.out.println("Expected:\t" + "7");
        System.out.println("Your Tree:\t" + tree.size());
    }
}
