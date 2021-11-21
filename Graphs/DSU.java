import java.util.*;

public class DSU{
	static class dsu{
		private int[] parent;
		private int[] size;

		dsu(){
			
		}

		dsu(int n){
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
	public static void main(String[] args) {
		//this code below will help you understand the use of find_set
		dsu obj = new dsu(4);
		obj.getParentArray();
		obj.union(0,1);
		obj.getParentArray();
		obj.union(2,3);
		obj.getParentArray();
		obj.union(0,2);
		obj.getParentArray();
		System.out.println(obj.find_set(3) == obj.find_set(1));
		obj.getParentArray();
	}
}