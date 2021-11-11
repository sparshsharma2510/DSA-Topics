import java.util.*;

public class Bipartite{
	public static void main(String[] args) {
		
	}

	private static boolean dfsCheckBipartite(List<List<Integer>> adj, int curr, int[] color, int currColor){
		int toPassColor = (currColor == 1? 2:1);
		color[curr] = toPassColor;

		for(int neighbour: adj.get(curr)){
			if(color[neighbour] == 0){
				if(!dfsCheckBipartite(adj, neighbour, color, toPassColor))
					return false;
			}
			else if(color[neighbour] == color[curr])
				return false;
		}
		return true;
	}

	private static boolean bfsCheckBipartite(List<List<Integer>> adj){
		Queue<Integer> queue = new LinkedList<>();
		int n = adj.size();
		int[] color = new int[n];
		for(int i = 0; i < n; i++){
			if(color[i] == 0){
				queue.offer(i);
				color[i] = 1;

				while(!queue.isEmpty()){
					int curr = queue.poll();
					for(int neighbour: adj.get(curr)){
						if(color[neighbour] == 0){
							color[neighbour] = (color[curr] == 1? 2:1);
							queue.offer(neighbour);
						}
						else if(color[neighbour] == color[curr])
							return false;
					}
				}
			}
		}
		return true;
	}
}