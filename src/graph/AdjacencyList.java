package graph;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public final class AdjacencyList<E> implements IGraph<E> {

	private final static class Edge<E> {
		private final E target;
		private final int weight;
		
		public Edge(final E to, final int weight) {
			this.target = to;
			this.weight = weight;
		}
		
		public E getTo() {
			return target;
		}
		
		public int getWeight() {
			return weight;
		}
	}
	
	private final Hashtable<E, ArrayList<Edge<E>>> nodes;
	
	public AdjacencyList() {
		nodes = new Hashtable<E, ArrayList<Edge<E>>>();
	}
	
	@Override
	public Iterator<E> iterator() {
		return nodes.keySet().iterator();
	}

	@Override
	public void addNode(E node) {
		if (nodes.containsKey(node)) {
			throw new RuntimeException("Node already in Graph");
		} else {
			nodes.put(node, new ArrayList<Edge<E>>());
		}
	}

	@Override
	public void removeNode(E node) {
		nodes.remove(node);
	}

	private ArrayList<Edge<E>> getEdges(E from) {
		ArrayList<Edge<E>> edges = nodes.get(from);
		if (edges == null) {
			throw new RuntimeException("Node not in graph: " + from);
		} else {
			return edges;
		}
	}
	
	private int indexOfEdge(E from, E to) {
		ArrayList<Edge<E>> edges = getEdges(from);
		for (int i = 0; i < edges.size(); ++i) {
			if (edges.get(i).getTo() == to) {
				return i;
			}
		}
		throw new RuntimeException("Edge not found: from " + from + " to " + to); 
	}
	
	@Override
	public void addEdge(E from, E to, int weight) {
		try {
			indexOfEdge(from, to);
		}
		catch (RuntimeException e) {
			getEdges(from).add(new Edge<E>(to, weight));
		}
	}

	@Override
	public void removeEdge(E from, E to) {
		getEdges(from).remove(indexOfEdge(from, to));
	}

	@Override
	public void changeWeight(E from, E to, int weight) {
		removeEdge(from, to);
		addEdge(from, to, weight);
	}

	@Override
	public int getWeight(E from, E to) {
		return getEdges(from).get(indexOfEdge(from, to)).getWeight();
	}

	@Override
	public List<E> getSuccessors(E node) {
		List<E> succs = new ArrayList<E>();
		
		for (Edge<E> e : getEdges(node)) {
			succs.add(e.getTo());
		}
		
		return succs;
	}

}
