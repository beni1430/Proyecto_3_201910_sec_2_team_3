package model.utils;

import model.data_structures.*;
import java.util.Iterator;

public class BFS <K extends Comparable<K>>
{
	private LinearHash<K,Boolean> marked;
	private LinearHash<K,K> edgeTo;
	private K s;

	public BFS(Graph G, K s) 
	{
		marked = new  LinearHash<K,Boolean>(G.V()) ;
		edgeTo = new LinearHash<K,K>(G.V());
		this.s=s;
		bfs(G, s);
	}

	private void bfs(Graph G, K s) 
	{
		Queue<K> q = new Queue<K>();
		marked.put(s, true);
		edgeTo.put(s, null);
		q.enqueue(s);

		while (!q.isEmpty())
		{
			K v = q.dequeue();
			Iterator i= G.adja(v);
			while(i.hasNext())
			{
				K w=(K)i.next();
				if (marked.get(w)==null) 
				{
					edgeTo.put(w, v);
					marked.put(w, true);
					q.enqueue(w);
				}
			}
		}
	}

	public boolean hasPathTo(K v) 
	{
		return marked.get(v)!=null;
	}

	public ArregloDinamico<K> pathTo(K v) 
	{
		if (!hasPathTo(v)) return null;
		ArregloDinamico<K> path = new ArregloDinamico<K>(10);
		for (K x = v; x!=s ;x = edgeTo.get(v))
		{
			path.agregar(x);
			v=x;
		}
		path.agregar(s);
		return path;
	}
}