import java.util.*;

public class TopoSort{
	public static void main(String[] args) {
				
	}

	private static void topoDFS(List<List<Integer>> adj, boolean[] visited, int curr, Stack<Integer> stack){
		visited[curr] = true;
		for(int neighbour : adj.get(curr)){
			if(!visited[neighbour])
				topoDFS(adj, visited, neighbour, stack);
		}
		stack.push(curr);
		// When dfs is done, our stack will contain the toposort
	}

	private static void kahnsAlgo(List<List<Integer>> adj){
		int nodes = adj.size();
		int[] indegrees = new int[nodes];

		for(int i = 0; i < nodes; i++){
			for(int neighbour: adj.get(i))
				indegrees[neighbour]++;
		}

		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < nodes; i++){
			if(indegrees[i] == 0)
				queue.offer(i);
		}

		while(!queue.isEmpty()){
			int curr = queue.poll();
			for(int neighbour: adj.get(curr)){
				indegrees[neighbour]--;
				if(indegrees[neighbour] == 0)
					queue.offer(neighbour);
			}
			// System.out.print(curr + " ");
		}
	}
}