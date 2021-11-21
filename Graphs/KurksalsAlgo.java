import java.util.*;

public class KurksalsAlgo{
	static class Node{
		private int val;
		private int weight;

		Node(int val, int weight){
			this.val = val;
			this.weight = weight;
		}

		public int getVal(){
			return val;
		}

		public int getWeight(){
			return weight;
		}
	}

	static class Dsu{
		private int[] parent;
		private int[] size;

		Dsu(int n){
			parent = new int[n];
			size = new int[n];

			//initialization of arrays
			for(int i = 0; i < n; i++){
				parent[i] = i;
				size[i] = 1;
			}
		}
		/*
			please have a look that the find_set is used to find the ultimate parent of a node as well as does the path compression
			so that the TC remains constant
		*/
		public int find_set(int v) {
		    if (v == parent[v])
		        return v;
		    //Here we recursively keep going up untll we find the ultimate parent
		    //once we are done with that, we keep setting the ultimate parent of all the nodes to the correct ultimate parent
		    return parent[v] = find_set(parent[v]);
		}

		public void union(int u, int v){
			// we had to write the find parent code as we neeedded to do the path compression and also check/get
			//the ultimate parents without calling union again
			// int pu = parent[u];
			// int pv = parent[v];
			int pu = find_set(u);
			int pv = find_set(v);

			if(pu == pv)
				return;
			if(size[pu] >= size[pv]){
				parent[pv] = pu;
				size[pu] += size[pv];
			}
			else{
				parent[pu] = pv;
				size[pv] += size[pu];
			}
		}

		public void getParentArray(){
			System.out.println(Arrays.toString(parent));
		}
	}

	private static void getMST(List<List<Node>> adj){
		int n = adj.size();
		List<int[]> edges = new ArrayList<>();
		//Firstly store the edges info in a linear datastructure
		for(int i = 0; i < n; i++){
			for(Node neighbour: adj.get(i))
				edges.add(new int[]{neighbour.getWeight(), i, neighbour.getVal()});
		}
		//Then sort the datastructure on the basis of the weights of all the edges
		Collections.sort(edges, (a,b)->{return a[0]-b[0];});

		//initialsize the dsu datastructure 
		Dsu set = new Dsu(n);
		int e = edges.size();
		//this will be our final mst
		List<int[]> mst = new ArrayList<>();
		//run a loop over the edges in our edges data structre that we sorted
		for(int i = 0; i < e; i++){
			//prints parent array
			set.getParentArray();

			int u = edges.get(i)[1];
			int v = edges.get(i)[2];
			int wt = edges.get(i)[0];

			int pu = set.find_set(u);
			int pv = set.find_set(v);

			if(pu == pv)	//check if they have same parent then skip the current edge
				continue;
			//if the edges were a part of two different component then we need to make a union of them
			// and then add it into our mst data structre
			set.union(u,v);
			mst.add(new int[]{u,v,wt});
		}
		System.out.println("MST:");
		for(int[] arr: mst){
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void main(String[] args) {
		List<List<Node>> adj = new ArrayList<>();
		for(int i = 0; i < 4; i++)
			adj.add(new ArrayList<>());
		adj.get(0).add(new Node(1,1));
		adj.get(0).add(new Node(3,5));

		adj.get(1).add(new Node(0,1));
		adj.get(1).add(new Node(2,3));
		adj.get(1).add(new Node(3,9));

		adj.get(2).add(new Node(3,7));
		adj.get(2).add(new Node(1,3));

		adj.get(3).add(new Node(0,5));
		adj.get(3).add(new Node(2,7));

		getMST(adj);
	}
}