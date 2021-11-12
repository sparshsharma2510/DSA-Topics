import java.util.*;

class Node implements Comparable<Node>{
	int node;
	int weight;

	Node(int node, int weight){
		this.node = node;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node obj){
		return this.weight-obj.weight;
	}
}

public class ShortestPathAlgos{
	public static void main(String[] args) {
		
	}
	/*
		Dijkstra's algo is used to find the shortest path in both directed and undirected
		graphs provided that the edge weights should not be negative. The graph may or may
		not contain cycle

		TIME: 
	*/
	private static void dijkstra(List<List<Node>> adj, int src){
		int n = adj.size();
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		Queue<Node> pqueue = new PriorityQueue<>();
		pqueue.offer(new Node(src, 0));

		while(!pqueue.isEmpty()){
			Node topNode = pqueue.poll();
			int node = topNode.node;

			for(Node neighbourNode: adj.get(node)){
				int neighbour = neighbourNode.node;
				int wt = neighbourNode.weight;
				if(dist[neighbour] > dist[node]+wt){
					dist[neighbour] = dist[node]+wt;
					pqueue.offer(new Node(neighbour,dist[neighbour]));
				}
			}
		}

		for(int el: dist)
			System.out.print((el == Integer.MAX_VALUE? "INF":el)+" ");
	}

	/*
		Shortest Path In A DAG 
	*/
	private static void shortestPathInDAG(List<List<Node>> adj, int src){
		Stack<Integer> stack = new Stack<>();
		int n = adj.size();
		boolean[] visited = new boolean[n];

		for(int i = 0; i < n; i++){
			if(!visited[i])
				topoDFS(adj, visited, i, stack);
		}

		int dist[] = new int[n]; 
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		while(!stack.isEmpty()){
			int curr = stack.pop();
			if(dist[curr] != Integer.MAX_VALUE){
				for(Node neighbourNode: adj.get(curr)){
					int wt = neighbourNode.weight;
					int neighbour = neighbourNode.node;

					if(dist[neighbour] > dist[curr]+wt)
						dist[neighbour] = dist[curr]+wt;
				}
			}
		}

		for(int d: dist){
			if(d == Integer.MAX_VALUE)
				System.out.print("INF ");
			else
				System.out.print(d + " ");
		}
	}

	private static void topoDFS(List<List<Node>> adj, boolean[] visited, int curr, Stack<Integer> stack){
		visited[curr] = true;
		for(Node neighbourNode : adj.get(curr)){
			int neighbour = neighbourNode.node;
			if(!visited[neighbour])
				topoDFS(adj, visited, neighbour, stack);
		}
		stack.push(curr);
	}
}