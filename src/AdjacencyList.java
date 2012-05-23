

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
	
	private final Hashtable<E, List<Edge<E>>> nodes;
	
	public AdjacencyList() {
		nodes = new Hashtable<E, List<Edge<E>>>();
	}
	
	public AdjacencyList(int initialCapacity) {
		nodes = new Hashtable<E, List<Edge<E>>>(initialCapacity);
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
			nodes.put(node, new LinkedList<Edge<E>>());
		}
	}

	@Override
	public void removeNode(E node) {
		nodes.remove(node);
	}

	private List<Edge<E>> getEdges(E from) {
		final List<Edge<E>> edges = nodes.get(from);
		if (edges == null) {
			throw new RuntimeException("Node not in graph: " + from);
		} else {
			return edges;
		}
	}
	
	private int indexOfEdge(E from, E to) {
		final int index = getSuccessors(from).indexOf(to);
		if (index == -1) {
			throw new RuntimeException("Edge not found: from " + from + " to " + to);
		} else {
			return index;
		}
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
		final List<E> succs = new ArrayList<E>();
		
		for (final Edge<E> e : getEdges(node)) {
			succs.add(e.getTo());
		}
		
		return succs;
	}

	@Override
	public int size() {
		return nodes.size();
	}

	@Override
	public Set<E> getNodes() {
		return Collections.unmodifiableSet(nodes.keySet());
	}

}
