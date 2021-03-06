import java.util.*;

public class DetectCycleInGraph{
	//Pair class definition for BFS UDG
	static class Pair{
		int curr;
		Integer parent;

		Pair(int curr, Integer parent){
			this.curr = curr;
			this.parent = parent;
		}
	}

	public static void main(String[] args) {
  //	   Psuedo Driver Code
  // 	   boolean[] visited = new boolean[n];
  //       for(int i = 0; i < n; i++){
  //           if(!visited[i]){
  //               if(...Any Cycle Check Function with apt. arguments)
  //                   System.out.println("Cycle exists in graph");
  //           }
  //       }
  //       System.out.println("No Cycle found");
	}

	//Check Cycle in Undirected Graph(UDG)
	//in UDG Cycle detection we have to use the concept of parents as the edges are bi directional
	//thus the neighbour group will contain the parent of the node also 
	//resulting in a fake loop if parents concept was not implemented.
	private static boolean isCycleDfsUDG(List<List<Integer>> adj, boolean[] visited, int curr, Integer parent){
		visited[curr] = true;

		for(int neighbour: adj.get(curr)){
			if(parent != null && neighbour == parent)
				continue;
			if(!visited[neighbour]){
				if(isCycleDfsUDG(adj,visited,neighbour,curr))
					return true;
			}
			else
				return true;
		}
		return false;
	}

	private static boolean isCycleBfsUDG(List<List<Integer>> adj){
		int nodes = adj.size();
		boolean visited[] = new boolean[nodes];
		Queue<Pair> queue = new LinkedList<>();

		for(int i = 0; i < nodes; i++){
			if(!visited[i]){
				queue.offer(new Pair(i,null));
				visited[i] = true;

				while(!queue.isEmpty()){
					Pair pair = queue.poll();
					int curr = pair.curr;

					for(int neighbour: adj.get(curr)){
						if(pair.parent != null && pair.parent == neighbour)
							continue;
						if(!visited[neighbour]){
							queue.offer(new Pair(neighbour, curr));
							visited[neighbour] = true;
						}
						else
							return true;
					}
				}
			}
		}
		return false;
    }

	//Check Cycle in Directed Graph(DG)
	private static boolean isCycleDfsDG(List<List<Integer>> adj, boolean[] visited, boolean[] dfsVisited, int curr){
        visited[curr] = true;
        dfsVisited[curr] = true;
        
        for(int neighbour: adj.get(curr)){
            if(!visited[neighbour]){
                if(isCycleDfsDG(adj, visited, dfsVisited, neighbour))
                    return true;
            }
            else if(dfsVisited[neighbour])
                return true;
        }
        return false;
    }

    private static boolean isCycleBfsDG(List<List<Integer>> adj){
    	//This is based on the fact that if we can't generate the topological sort,
    	//then the graph must contain a cycle
    	int n = adj.size();
    	//Step 1: Build Indegree
    	int[] indegree = new int[n];
    	for(int i = 0; i < n; i++){
    		for(int neighbour: adj.get(i))
    			indegree[neighbour]++;
    	}

    	Queue<Integer> queue = new LinkedList<>();
    	for(int i = 0; i < n; i++){
    		if(indegree[i] == 0)
    			queue.offer(i);
    	}
    	int nodesWhoseIndegreeIsZero = 0;
    	while(!queue.isEmpty()){
    		int curr = queue.poll();
    		nodesWhoseIndegreeIsZero++;
    		for(int neighbour: adj.get(curr)){
    			indegree[neighbour]--;
    			if(indegree[neighbour] == 0)
    				queue.offer(neighbour);
    		}
    	}
    	if(nodesWhoseIndegreeIsZero == n)
    		return false;
    	return true;
    	//OR WE CAN DO THE BELOW CHECK
    	// for(int el: indegree){
    	// 	if(el > 0)
    	// 		return true;
    	// }
    	// return false;
    }
}