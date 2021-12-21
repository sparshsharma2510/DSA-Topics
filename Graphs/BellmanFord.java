import java.util.*;

class Node{
	int u, v;
	int weight;

	Node(int u, int v, int weight){
		this.u = u;
		this.v = v;
		this.weight = weight;
	}
}

public class BellmanFord{
	public static void main(String[] args) {
		
	}
	/*
		Bellman ford algorithm is used to calculate the shortest distance to all the nodes.
		In this algorithm we work on the edges itself. We relax the given edges for V-1 times
		then after this we relax all the edges one more time and if the distance of any nodes still 
		reduces, we can gurantee that there must be a negative node.

		This is becuase it is practically impossible that for positive weights, the node will be
		relaxed even after relaxing for v-1 times. Think of this as a DRV algo. Each router is
		initially at INF and then when the process starts, the src router send its distance info 
		to its neighbouring router and then that router sends his caculated distance to its neighbour
		and so on. So Now, if there is no negative cycle and there are V-1 routers, this algo will at max
		update distances of routers for V-1 times. But if it further continues to do so then it must be due 
		to a negative cycle!
	*/
	private static void detectNegativeCycle(List<Node> edges, int V, int src){
		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		for(int i = 0; i < V; i++){	//Relax for V-1 times
			for(Node node: edges){
				if(dist[node.u] + node.weight < dist[node.v])
					dist[node.v] = dist[node.u]+node.weight;
			}
		}
		boolean negativeCycle = false;
		for(Node node: edges){
			if(dist[node.u] + node.weight < dist[node.v]){
				negativeCycle = true;
				break;
			}	
		}

		if(negativeCycle)
			System.out.println("Negative Cycle Exists");
		else
			System.out.println(Arrays.toString(dist));	
	}
}