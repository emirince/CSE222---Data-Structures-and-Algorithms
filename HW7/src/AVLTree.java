public class AVLTree {
    private class Node {
        Stock stock;
        Node left, right;
        int height;

        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }
    }

    private Node root;

    // Get the height of a node
    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    // Get the balance factor of a node
    private int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    // Right rotate subtree rooted with y
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotate subtree rooted with x
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Insert a stock into the AVL tree
    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    private Node insert(Node node, Stock stock) {
        // Perform normal BST insertion
        if (node == null) {
            return new Node(stock);
        }

        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0) {
            node.left = insert(node.left, stock);
        } else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0) {
            node.right = insert(node.right, stock);
        } else {
            // Duplicate keys are not allowed in BST
            // Update the existing node
            node.stock.setPrice(stock.getPrice());
            node.stock.setVolume(stock.getVolume());
            node.stock.setMarketCap(stock.getMarketCap());
            return node;
        }

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) < 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) > 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // Delete a stock from the AVL tree
    public void delete(String symbol) {
        root = delete(root, symbol);
    }

    private Node delete(Node root, String symbol) {
        // Perform standard BST delete
        if (root == null) {
            return root;
        }

        // If the symbol to be deleted is smaller than the root's symbol,
        // then it lies in left subtree
        if (symbol.compareTo(root.stock.getSymbol()) < 0) {
            root.left = delete(root.left, symbol);
        } else if (symbol.compareTo(root.stock.getSymbol()) > 0) {
            root.right = delete(root.right, symbol);
        } else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else { // One child case
                    root = temp; // Copy the contents of the non-empty child
                }
            } else {
                // Node with two children: Get the inorder successor (smallest
                // in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.stock = temp.stock;

                // Delete the inorder successor
                root.right = delete(root.right, temp.stock.getSymbol());
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            return root;
        }

        // Update height of the current node
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // Get the balance factor of this node (to check whether this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // Get the node with the minimum value
    private Node minValueNode(Node node) {
        Node current = node;

        // Loop down to find the leftmost leaf
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    // Search for a stock by its symbol
    public Stock search(String symbol) {
        return search(root, symbol);
    }

    private Stock search(Node root, String symbol) {
        if (root == null) {
            return null;
        }

        if (symbol.equals(root.stock.getSymbol())) {
            return root.stock;
        }

        if (symbol.compareTo(root.stock.getSymbol()) < 0) {
            return search(root.left, symbol);
        }

        return search(root.right, symbol);
    }

    // In-order traversal
    public void inOrderTraversal() {
        inOrderRec(root);
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.stock.getSymbol());
            inOrderRec(node.right);
        }
    }

    // Pre-order traversal
    public void preOrderTraversal() {
        preOrderRec(root);
    }

    private void preOrderRec(Node node) {
        if (node != null) {
            System.out.println(node.stock.getSymbol());
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    // Post-order traversal
    public void postOrderTraversal() {
        postOrderRec(root);
    }

    private void postOrderRec(Node node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.println(node.stock.getSymbol());
        }
    }
}
