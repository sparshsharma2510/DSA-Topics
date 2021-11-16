import java.util.*;

class Node implements Comparable<Node>{
	private int val;
	private int weight;
	
	Node(int val ,int weight){
		this.val = val;
		this.weight = weight;
	}

	public int getV(){
		return val;
	}

	public int getWeight(){
		return weight;
	}

	@Override
	public int compareTo(Node obj){
		return this.weight-obj.weight;
	}
}

public class PrimsAlgo{
	public static void main(String[] args) {
		
	}

	private static void getMST(List<List<Node>> adj){
		int n = adj.size();
		//For prims algo we need three arrays
		//first array will be a distance array from parent. Please note that it is not the dist from source
		int[] dist = new int[n];
		//second array is a boolean array that will tell that this node has formed an edge in our answering mst or not
		boolean[] mst = new boolean[n];
		//this will contain the parents of the nodes
		int[] parent = new int[n];

		//initializing the arrays
		for(int i = 0; i < n; i++){
			dist[i] = (int)1e9+7;
			parent[i] = -1;	
		}
		dist[0] = 0;
		/*
			Now the main algo is:
			That from the distance array, we will find the node with minimum distance
			then, we will set mst true for that particular node
			once we have done that, we can explore the neighbours who are not a part of mst
			and update the distance as well as parent of nodes whose initial distance was bigger
		*/
		Queue<Node> pqueue = new PriorityQueue<>();	
		//we used pqueue (so its efficient) otherwise iterate the array to get minimums in interview(BRUTE)
		pqueue.offer(new Node(0,0));

		for(int i = 0; i < n-1; i++){
			Node minNode = pqueue.poll();
			int key = minNode.getV();
			mst[key] = true;

			for(Node neighbourNode: adj.get(key)){
				int neighbour = neighbourNode.getV();
				int weight = neighbourNode.getWeight();
				
				if(!mst[neighbour] && dist[neighbour] > weight){
					dist[neighbour] = weight;
					parent[neighbour] = key;
					pqueue.offer(new Node(neighbour, weight));
				}
			}
		}

		for(int i = 1; i < n; i++)
			System.out.println(parent[i]+" ---- "+ i);

		//TC : O(nlogn)(approx)
	}
}