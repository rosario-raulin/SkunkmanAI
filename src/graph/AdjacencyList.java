package graph;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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
	
	private final Vector<Vector<Edge<E>>> nodes;
	private final Hashtable<E, Integer> ids;
	
	public AdjacencyList() {
		nodes = new Vector<Vector<Edge<E>>>();
		ids = new Hashtable<E, Integer>();
	}
	
	private int getId(E node) {
		Integer id = ids.get(node);
		if (id == null) {
			throw new RuntimeException("Node not in graph");
		} else {
			return id;
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		return ids.keySet().iterator();
	}

	@Override
	public void addNode(E node) {
		if (ids.containsKey(node)) {
			throw new RuntimeException("Node already in graph");
		} else {
			ids.put(node, nodes.size());
		}
	}

	@Override
	public void removeNode(E node) {
		int id = getId(node);
		ids.remove(node);
		nodes.remove(id);
		// We need to update the key IDs since they are shifted left
		for (E key : ids.keySet()) {
			int old = ids.get(key);
			if (old > id) {
				ids.put(key, old - 1);
			}
		}
	}
	
	@Override
	public void addEdge(E from, E to, int weight) {
		int id = getId(from);
		
		if (nodes.size() <= id) {
			nodes.add(new Vector<Edge<E>>());
		}
		nodes.get(id).add(new Edge<E>(to, weight));		
	}

	@Override
	public void removeEdge(E from, E to) {
		// FIXME: This will not work!
		nodes.get(getId(from)).remove(to);
	}

	@Override
	public void changeWeight(E from, E to, int weight) {
		removeEdge(from, to);
		addEdge(from, to, weight);
	}

	@Override
	public int getWeight(E from, E to) {
		// FIXME: This will not work!
		int index = nodes.get(getId(from)).indexOf(to);
		if (index == -1) {
			throw new RuntimeException("Edge does not exist");
		} else {
			return nodes.get(getId(from)).get(index).getWeight();
		}
	}

	@Override
	public List<E> getSuccessors(E node) {
		List<E> successors = new Vector<E>();
		
		for (Edge<E> e : nodes.get(getId(node))) {
			successors.add(e.getTo());
		}
		
		return successors;
	}

}
