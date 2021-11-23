import java.util.*;

public class ArticulationPoint{
	//Articulation point is a POINT/NODE on the removal of which, the graph splits into two or more components

	public static void main(String[] args) {
		//fake data
		int[] lowTime = new int[5];
		int[] time = new int[5];
		boolean[] visited = new boolean[5];
		boolean[] isArticulation = new boolean[5];
		time[0] = 1;
		lowTime[0] = 1;
		visited[0] = true;
	}

	private static void vertexCutting(List<List<Integer>> adj, Integer parent,boolean[] isArticulation, boolean[] visited, int[] lowTime, int[] time, int curr){
		visited[curr] = true;
		int child = 0;
		if(parent != null){
			time[curr] = time[parent]+1;
			lowTime[curr] = lowTime[parent]+1;
		}

		for(int neighbour: adj.get(curr)){
			if(parent != null && parent == neighbour)
				continue;
			if(!visited[neighbour]){
				vertexCutting(adj, curr, isArticulation, visited, lowTime, time, neighbour);
				lowTime[curr] = Math.min(lowTime[neighbour], lowTime[curr]);
				//This condition is slighlty different than that of the bridge check algo.
				
				//When the low time of neighbour is greater than or equal to the time of insertion of the current node, then we can infer
				//that for the neighbour node it is not possible to reach the parents of the current node, thus is the current
				//node was removed, the neighbours would not remain in contact with the parent of the current node
				//thus current node is an articulation point. 
				//for more descriptive intuition listen to the audio(will be attached soon).
				if(lowTime[neighbour] >= time[curr] && parent != null)
					isArticulation[curr] = true;
			}
			else
				lowTime[curr] = Math.min(lowTime[neighbour], lowTime[curr]);
		}
		//check for the root node. i.e if the root node has more than 1 child 
		//(or more than one child which are not connected with each other) then the root node can be the articulation point
		if(parent == null && child > 1)
			isArticulation[curr] = true;
	}
}