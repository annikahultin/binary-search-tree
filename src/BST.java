public class BST {

    private class Node {

        private int key;
        private Node parent;
        private Node leftChild;
        private Node rightChild;

        public Node() {
            this.key = -1;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        } //Node

        public Node(int key) {
            this.key = key;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        } //Node

        public Node(int key, Node parent, Node leftChild, Node rightChild) {
            this.key = key;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        } //Node

        public int getKey() {
            return key;
        } //getKey

        public void setKey(int key) {
            if (key > -1) {
                this.key = key;
            } //if
        } //setKey

        public Node getParent() {
            return parent;
        } //getParent

        public void setParent(Node parent) {
            this.parent = parent;
        } //setParent

        public Node getLeftChild() {
            return leftChild;
        } //getLeftChild

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        } //setLeftChild

        public Node getRightChild() {
            return rightChild;
        } //getRightChild

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        } //setRightChild

    } //Node

    private Node root;
    private int counter;

    public BST() {
        this.root = null;
        this.counter = 0;
    }//BST

    /**
     * Returns number of elements stored in the tree.
     */
    public int size() {
        return counter;
    } //size

    /**
     * Finds the corresponding element in the tree.
     */
    public int findElement(int element) {
        Node current = root;
        while (current != null) {
            if (current.getKey() == element) {
                return current.getKey();
            } else if (current.getKey() > element) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            } //if
        } //while
        return -1;
    } //findElement

    /**
     * Inserts a new element into the tree.
     */
    public void insert(int element) {
        if (element > 0) {
            Node inserted = new Node(element);
            if (size() == 0) {
                root = inserted;
                counter ++;
            } else if (findElement(element) == -1) {
                Node current = root;
                boolean stillInserting = true; //tracks whether node is still be inserted
                while (stillInserting) {
                    if (current.getKey() > element) { //determines which subtree to go down
                        if (current.getLeftChild() != null) {
                            current = current.getLeftChild();
                        } else { //inserts the element into the tree
                            current.setLeftChild(inserted);
                            inserted.setParent(current);
                            stillInserting = false;
                            counter ++;
                        } //if
                    } else {
                        if (current.getRightChild() != null) {
                            current = current.getRightChild();
                        } else { //inserts the element into the tree
                            current.setRightChild(inserted);
                            inserted.setParent(current);
                            stillInserting = false;
                            counter ++;
                        } //if
                    } //if
                } //while
            } else { //no duplicates
                System.out.println("Element is already in the tree!");
            } //if
        } else {
            System.out.println("This tree only holds positive integers!");
        } //if
    } //insert

    /**
     * Finds the node with the corresponding element.
     */
    private Node findNode(int element) {
        Node current = root;
        while (current != null) {
            if (current.getKey() == element) {
                return current;
            } else if (current.getKey() > element) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            } //if
        } //while
        return null;
    } //findNode

    /**
     * Determines if the node is a left child.
     */
    private boolean isLeftChild(Node current) {
        Node parent = current.getParent();
        if (parent.getLeftChild() == current) {
            return true;
        } else {
            return false;
        } //if
    } //isLeftChild

    /**
     * Determines if a node only has one child
     */
    private boolean hasOneChild(Node current) {
        if (current.getLeftChild() == null && current.getRightChild() != null) {
            return true;
        } else if (current.getLeftChild() != null && current.getRightChild() == null) {
            return true;
        } else {
            return false;
        } //if
    } //hasOneChild

    /**
     * Handles deleting the root node
     */
    private void deleteRoot() {
        if (size() == 1) { //if root is only node
            root = null;
            counter --;
        } else if (root.getLeftChild() != null && root.getRightChild() == null) { //one left child
            root = root.getLeftChild();
            root.setParent(null);
            counter --;
        } else if (root.getLeftChild() == null && root.getRightChild() != null) { //one right child
            root = root.getRightChild();
            root.setParent(null);
            counter --;
        } else if (root.getLeftChild() != null && root.getRightChild() != null) { //two children
            Node replacement = root.getLeftChild();
            while (replacement.getRightChild() != null) { //finds the immediate predecessor
                replacement = replacement.getRightChild();
            } //while
            if (replacement != root.getLeftChild()) { //if replacement is not roots left child
                replacement.setLeftChild(root.getLeftChild());
                root.getLeftChild().setParent(replacement);
                replacement.getParent().setRightChild(null);
            } //if
            replacement.setRightChild(root.getRightChild());
            root.getRightChild().setParent(replacement);
            root = replacement; //reassigns the root
            root.setParent(null);
            counter --;
        } //if
    } //deleteRoot

    /**
     * Deletes an element from the tree
     */
    public void delete(int element) {
        if (findElement(element) == -1) { //checks if element is in tree
            System.out.println("Element not found!");
        } else {
            Node toDelete = findNode(element);
            if (toDelete == root) { //deleting the root
                deleteRoot();
            } else {
                if (toDelete.getLeftChild() == null && toDelete.getRightChild() == null) { //leaf
                    if (isLeftChild(toDelete)) { //left leaf
                        toDelete.getParent().setLeftChild(null);
                        counter --;
                    } else { //right leaf
                        toDelete.getParent().setRightChild(null);
                        counter --;
                    } //if
                } else if (hasOneChild(toDelete)) { //one child
                    if (toDelete.getLeftChild() != null) { //one left child
                        if (isLeftChild(toDelete)) { //node to be deleted is left child
                            toDelete.getParent().setLeftChild(toDelete.getLeftChild());
                            toDelete.getLeftChild().setParent(toDelete.getParent());
                            counter --;
                        } else { //node to be deleted is right child
                            toDelete.getParent().setRightChild(toDelete.getLeftChild());
                            toDelete.getLeftChild().setParent(toDelete.getParent());
                            counter --;
                        } //if
                    } else { //one right child
                        if (isLeftChild(toDelete)) { //node to be deleted is left child
                            toDelete.getParent().setLeftChild(toDelete.getRightChild());
                            toDelete.getRightChild().setParent(toDelete.getParent());
                            counter --;
                        } else { //node being deleted is right child
                            toDelete.getParent().setRightChild(toDelete.getRightChild());
                            toDelete.getRightChild().setParent(toDelete.getParent());
                            counter --;
                        } //if
                    } //if
                } else { //two children
                    Node leftChild = toDelete.getLeftChild();
                    Node replacement = leftChild;
                    while (replacement.getRightChild() != null) { //finds immediate predecessor
                        replacement = replacement.getRightChild();
                    } //while
                    if (isLeftChild(toDelete)) { //node to be deleted is left child
                        if (toDelete.getLeftChild() != replacement) { //left child is not replacer
                            replacement.setLeftChild(toDelete.getLeftChild());
                            toDelete.getLeftChild().setParent(replacement);
                            replacement.getParent().setRightChild(null);
                        } //if
                        toDelete.getParent().setLeftChild(replacement);
                        replacement.setParent(toDelete.getParent());
                        replacement.setRightChild(toDelete.getRightChild());
                        toDelete.getRightChild().setParent(replacement);
                        counter --;
                    } else { //node to be deleted is right child
                        if (toDelete.getLeftChild() != replacement) { //left child is not replacer
                            replacement.setLeftChild(toDelete.getLeftChild());
                            toDelete.getLeftChild().setParent(replacement);
                            replacement.getParent().setRightChild(null);
                        } //if
                        toDelete.getParent().setRightChild(replacement);
                        replacement.setParent(toDelete.getParent());
                        replacement.setRightChild(toDelete.getRightChild());
                        toDelete.getRightChild().setParent(replacement);
                        counter --;
                    } //if
                } //if
            } //if
        } //if
    } //delete

    /**
     * Performs the preorder method
     */
    private void preorderHelper(Node current) {
        if (current == null) {
            return;
        } else {
            System.out.print(current.getKey() + " ");
            preorderHelper(current.getLeftChild());
            preorderHelper(current.getRightChild());
        }
    } //preorderHelper

    /**
     * Prints out all the elements corresponding to their preorder traversal
     */
    public void preorder() {
        if (size() == 0) {
            System.out.println("null");
        } else {
            preorderHelper(root);
            System.out.print("\n");
        } //if
    } //preorder

    /**
     * Performs the postorder method
     */
    private void postorderHelper(Node current) {
        if (current == null) {
            return;
        } else {
            postorderHelper(current.getLeftChild());
            postorderHelper(current.getRightChild());
            System.out.print(current.getKey() + " ");
        } //if
    } //postorderHelper

    /**
     * Prints out all the elements according to their postorder traversal
     */
    public void postorder() {
        if (size() == 0) {
            System.out.println("null");
        } else {
            postorderHelper(root);
            System.out.print("\n");
        } //if
    } //postorder

    /**
     * Performs the inorder method
     */
    private void inorderHelper(Node current) {
        if (current == null) {
            return;
        } else {
            inorderHelper(current.getLeftChild());
            System.out.print(current.getKey() + " ");
            inorderHelper(current.getRightChild());
        } //if
    } //inorderHelper

    /**
     * Prints out all the elements according to their inorder traversal
     */
    public void inorder() {
        if (size() == 0) {
            System.out.println("null");
        } else {
            inorderHelper(root);
            System.out.print("\n");
        } //if
    } //inorder

} //BST
