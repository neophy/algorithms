import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

    public class Node {
        // Index of the array
        int value;
        // It's a binary tree so two children will be there.
        // child-0 will be the left node
        // child-1 will be the right node
        // Making children as list and not leftNode, rightNode as this saves us from doing extra work of checking if left node exists or not
        List<Node> children = new ArrayList<>();

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }
    }

	public class TreeHeight {
		int n;
		int parent[];
        Map<Integer, Node> nodeMap = new HashMap<>();
        Node rootNode;
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];

			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
                // Very first step: Build all nodes
                // Note that we are not linking the nodes here but just building them.
                Node node = new Node();
                node.setValue(i);
                nodeMap.put(i, node);
                if (parent[i] == -1) {
                    // Root node
                    rootNode = node;
                }
			}
		}

        // Sol-1: STARTER SOlUTION
		int computeHeight() {
//         Replace this code with a faster implementation
			int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			return maxHeight;
		}

        int mySolutionToComputeHeight() {
            //Step-1: Build the tree
            buildTree(nodeMap, parent);
            //Step-2: Recursion to calculate tree height
            return maxHeight(rootNode);
        }

        void buildTree(Map<Integer, Node> nodeMap, int parent[]) {
            int childValue;
            int parentValue;

            for (int i = 0 ; i < n; i++) {
                // parent[i] is the parent of index 'i'
                childValue = i;
                parentValue = parent[i];

                if (parentValue == -1) {
                    continue;
                }
                // Get the parent Node
                Node parentNode = nodeMap.get(parentValue);
                // Get the child node
                Node childNode = nodeMap.get(childValue);
                // Add child node to this parent node (create a link b/w parent-child)
                parentNode.getChildren().add(childNode);
            }
        }

        int maxHeight(Node node) {
            //Base case: Leaf nodes
            if (node.getChildren().isEmpty()) {
                return 1;
            }

            Node leftNode = node.getChildren().get(0);
            Node rightNode = node.getChildren().get(1);
            int leftMaxHeight;
            int rightMaxHeight;

            if (leftNode == null) {
                leftMaxHeight = 0;
            } else {
                leftMaxHeight = maxHeight(leftNode);
            }
            if (rightNode == null) {
                rightMaxHeight = 0;
            } else {
                rightMaxHeight = maxHeight(rightNode);
            }
            return Math.max(leftMaxHeight, rightMaxHeight) + 1;
        }
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
//		System.out.println(tree.computeHeight());
		System.out.println(tree.mySolutionToComputeHeight());
	}
}
