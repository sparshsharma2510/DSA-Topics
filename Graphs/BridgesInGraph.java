import java.util.*;
/*
	Bridge: A bridge is just an edge which when removed splits the graph into two different components
*/
public class BridgesInGraph{
	private static int[] lowTime;
	private static int[] time;
	private static boolean[] visited;
	public static void main(String[] args) {
		lowTime = new int[5];
		time = new int[5];
		visited = new boolean[5];
		time[0] = 1;
		lowTime[0] = 1;
		visited[0] = true;
		// getBridges(adj,null);
	}
	/*
		This algo is just a normal dfs with a logic to check if the edge between a node and its neighbour forms a bridge or not

		we firstly do a dfs on the neighbours of the node.
		then after we are done with dfs, we minimise the lowtime by taking it from the neighbours low time
		after that we check that is the low time for the neighbour greater than the time of insertion of the current node
		i.e is it possible for the neighbour node to be reached in a time lesser than or equal to the insertion of the current node.
		if yes then we know that this can't be a bridge as there exists another way also to reach the neigbour or vice-versa

		but if the lowtime of neighbour is greater this means that it is not possible to reach neighbour node in a time
		lesser than the insertion of the current node thus we can gurantee that this edge is a bridge
	*/
	private static void getBridges(List<List<Integer>> adj, int curr, Integer parent){
		visited[curr] = true;
		
		if(parent != null){
			time[curr] = time[parent]+1;
			lowTime[curr] = lowTime[parent]+1;
		}

		for(int neighbour: adj.get(curr)){
			if(parent != null && parent == neighbour)
				continue;
			if(!visited[neighbour]){
				getBridges(adj, neighbour, curr);
				lowTime[curr] = Math.min(lowTime[neighbour], lowTime[curr]);
				if(lowTime[neighbour] > time[curr])
					System.out.println(curr+" -> "+neighbour);
			}
			else
				lowTime[curr] = Math.min(lowTime[neighbour], lowTime[curr]);
		}
	}
}