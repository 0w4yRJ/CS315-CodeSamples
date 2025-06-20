package Advance.Model;
/* -------------------------------
   AccountBST: A simple Binary Search Tree
   for storing accounts by owner name.
   ------------------------------- */
public class AccountBST {
    private Node root;

    private class Node {
        Account account;
        Node left, right;
        Node(Account account) { this.account = account; }
    }

    public void insert(Account account) {
        root = insertRec(root, account);
    }

    private Node insertRec(Node root, Account account) {
        if (root == null)
            return new Node(account);
        if (account.getOwner().compareToIgnoreCase(root.account.getOwner()) < 0)
            root.left = insertRec(root.left, account);
        else
            root.right = insertRec(root.right, account);
        return root;
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.account.getDetails());
            inOrderRec(node.right);
        }
    }
}