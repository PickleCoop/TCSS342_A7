/**
 * @author Evan Cooper
 * @version 5/6/2023
 */
public class MyBinarySearchTree<Type extends Comparable<Type>> {
    //Fields
    protected Node root;
    protected int size;
    public long comparisons;
    public Integer rotations;
    protected boolean balancing;

    //Methods


    public MyBinarySearchTree() {
        balancing = false;
        rotations = 0;
    }

    public MyBinarySearchTree(boolean balancing) {
        balancing = true;
        rotations = 0;
    }

    //Add Functions
    public void add(Type item) {
        root = add(item, root);
        size++;
    }

    protected Node add(Type item, Node subTree) {
        if (subTree == null) {
            return new Node(item);
        } else {
            if (subTree.item.compareTo(item) < 0) {
                subTree.right = add(item, subTree.right);
            } else {
                subTree.left = add(item, subTree.left);
            }
        }
        updateHeight(subTree);
        if(Math.abs(subTree.balanceFactor) > 1) {
            subTree = rebalance(subTree);
        }
        updateHeight(subTree);
        return subTree;
    }

    //Remove Functions
    public void remove(Type item) {
        root = remove(item, root);
        size--;
    }

    protected Node remove(Type item, Node subTree) {
        if (subTree == null) {
            size++;
            return null;
        } else {
            if (subTree.item.compareTo(item) < 0) {
                subTree.right = remove(item, subTree.right);
            } else if (subTree.item.compareTo(item) > 0) {
                subTree.left = remove(item, subTree.left);
            } else {
                // No children
                if (subTree.left == null && subTree.right == null) {
                    subTree = null;
                }
                //1 Child
                else if (subTree.left == null) {
                    subTree = subTree.right;
                } else if (subTree.right == null) {
                    subTree = subTree.left;
                }
                // 2 Children
                else {
                    // Traverse right once
                    Node temp = subTree.right;
                    // Traverse left til min is found
                    while (temp.left != null) {
                        temp = temp.left;
                    }
                    // Set the removed node equal to min
                    subTree.item = temp.item;
                    // Remove the duplicate
                    subTree.right = remove(temp.item, subTree.right);
                }
            }
            updateHeight(subTree);
            if(subTree != null && Math.abs(subTree.balanceFactor) > 1) {
                subTree = rebalance(subTree);
            }

            return subTree;
        }
    }

    //Find Functions
    public Type find(Type item) {
        return find(item, root);
    }

    protected Type find(Type item, Node subTree) {
        comparisons++;
        if(subTree != null) {
            if (subTree.item.compareTo(item) == 0) {
                return item;
            } else if (subTree.item.compareTo(item) < 0) {
                return find(item, subTree.right);
            } else if (subTree.item.compareTo(item) > 0) {
                return find(item, subTree.left);
            } else {
                return null;
            }
        }
        return null;
    }

    //Other Smaller Functions
    public int height() {
        if(!isEmpty()) {
            return root.height;
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected void updateHeight(Node node) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (node == null) {
            return;
        }
        if (node.left != null) {
            leftHeight = node.left.height;
        } else {
            leftHeight = -1;
        }
        if (node.right != null) {
            rightHeight = node.right.height;
        } else {
            rightHeight = -1;
        }
        node.height = Math.max(leftHeight, rightHeight) + 1;
        node.balanceFactor = leftHeight - rightHeight;
    }

    protected Node rotateRight(Node node){
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        updateHeight(node);
        updateHeight(temp);
        rotations++;
        return temp;
    }

    protected Node rotateLeft(Node node){
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        updateHeight(node);
        updateHeight(temp);
        rotations++;
        return temp;
    }

    protected Node rebalance(Node node){
        if (node.balanceFactor > 1) {
            if (node.left.balanceFactor < 0) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        } else if (node.balanceFactor < -1) {
            if (node.right.balanceFactor > 0) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        }
        updateHeight(node);
        return node;
    }

    //toString with traversal
    public String toString() {
        StringBuilder toString = new StringBuilder();
        toString.append("[");
        if (root != null) {
            toStringTraversal(toString, root);
            toString.setLength(toString.length() - 2);
        }
        toString.append("]");
        return toString.toString();
    }

    protected void toStringTraversal(StringBuilder toString, Node subTree){
        if(subTree.left != null){
            toStringTraversal(toString, subTree.left);
        }
        toString.append(subTree).append(", ");
        if(subTree.right != null){
            toStringTraversal(toString, subTree.right);
        }
    }

    //Protected Node Class
    class Node {
        public Type item;
        public Node left;
        public Node right;
        public int height;
        public int balanceFactor;
        public Node(Type item) {
            this.item = item;
        }
        public String toString() {
            return this.item + ":H" + this.height + ":B" + this.balanceFactor;
        }
    }

}
