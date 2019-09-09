package treesAndGraphs;

public class BinaryTree {
	Node root;

	public BinaryTree() {
		root = null;
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);

		System.out.println("Level order traversal of binary tree is ");
		tree.printLevelOrder();

	}

	private void printLevelOrder() {
		int treeHeight = findHeight(root);
		System.out.println("height is " + treeHeight);

		for (int i = 0; i < treeHeight; i++) {
			System.out.println("**************");
			printIthLevel(root, i);
		}

	}

	private void printIthLevel(Node root, int level) {
		if (root == null)
			return;
		if (level == 0) {
			System.out.print(root.data + " ");
		} else if (level > 0) {
			printIthLevel(root.left, level - 1);
			printIthLevel(root.right, level - 1);
		}
	}

	private int findHeight(Node root) {
		if (root == null) {
			return 0;
		} else {
			int leftHeight = findHeight(root.left);
			int rightHeight = findHeight(root.right);
			if (leftHeight > rightHeight) {
				return leftHeight + 1;
			} else {
				return rightHeight + 1;
			}
		}

	}
}

class Node {
	int data;
	Node left;
	Node right;

	public Node(int data) {
		this.data = data;
		right = left = null;
	}
}