import java.util.*;

public class Korsaraju{
	public static void main(String[] args) {
		List<List<Integer>> adj = new ArrayList<>();
		for(int i = 0; i < 6; i++)
			adj.add(new ArrayList<>());

		adj.get(0).add(1);
		adj.get(1).add(2);
		adj.get(2).add(0);

		adj.get(3).add(4);
		adj.get(4).add(5);
		adj.get(5).add(3);

		adj.get(1).add(3);


		getStronglyConnectedComponents(adj);
	}

	/*
		Korsaraju's algo is like a normal dfs but on a leave time sorted transposed graph
		There are three simple steps involved to get all the SCCs in a given graph.
		1. Get the topoSort of the graph
		2. Make a new transposed graph
		3. Make dfs call to visit the entire graph using the topo sort nodes
	*/

	private static void getStronglyConnectedComponents(List<List<Integer>> adj){
		int n = adj.size();

		//Step 1: Get the toposort
		Deque<Integer> stack = new ArrayDeque<>();
		boolean[] visited = new boolean[n];

		for(int i = 0; i < n; i++){
			if(!visited[i])
				getTopoSort(adj, stack, visited, i);
		}

		//Step 2: Get the Transposed graph
		List<List<Integer>> transposed = getTransposedGraph(adj);
		//Reset the visited nodes back to unvisited
		visited = new boolean[n];
		
		//Step 3: On the tranposed graph, apply dfs using toposort
		while(!stack.isEmpty()){
			int curr = stack.pop();
			if(!visited[curr]){
				printSCCs(transposed, visited, curr);
				System.out.println("");
			}
		}
	}

	private static void getTopoSort(List<List<Integer>> adj, Deque<Integer> stack, boolean[] visited, int curr){
		visited[curr] = true;
		for(int neighbour: adj.get(curr)){
			if(!visited[neighbour])
				getTopoSort(adj, stack, visited, neighbour);
		}
		stack.push(curr);
	}

	private static List<List<Integer>> getTransposedGraph(List<List<Integer>> adj){
		List<List<Integer>> newAdj = new ArrayList<>();
		int n = adj.size();

		for(int i = 0; i < n; i++)
			newAdj.add(new ArrayList<>());

		for(int i = 0; i < n; i++){
			for(int neighbour: adj.get(i))
				newAdj.get(neighbour).add(i);
		}

		return newAdj;
	}

	private static void printSCCs(List<List<Integer>> adj, boolean[] visited, int curr){
		System.out.print(curr + " ");
		visited[curr] = true;

		for(int neighbour: adj.get(curr)){
			if(!visited[neighbour])
				printSCCs(adj, visited, neighbour);
		}
	}
}