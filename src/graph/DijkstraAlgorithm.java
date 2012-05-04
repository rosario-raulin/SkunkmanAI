package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Hashtable;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Comparator;

// FIXME: check this implementation, I expect it to be wrong!

public final class DijkstraAlgorithm<E> implements IPathFinder<E> {

	private final static Integer INFINITY = Integer.MAX_VALUE;
	
	private final IGraph<E> graph;
	private final Map<E, E> preds;
	private final Map<E, Integer> distances;
	private E lastStartingPoint;

	
	public DijkstraAlgorithm(IGraph<E> graph) {
		this.graph = graph;
		this.preds = new HashMap<E, E>(graph.size());
		this.distances = new Hashtable<E, Integer>(graph.size());
	}
	
	private Stack<E> reconstructShortestPath(E target) {
		final Stack<E> path = new Stack<E>();
		
		E entry = target;
		while (entry != null) {
			path.push(entry);
			entry = preds.get(entry);
		}
		
		return path;
	}
	
	private void init(E start, PriorityQueue<E> nodes) {
		lastStartingPoint = start;
		preds.clear();
		distances.clear();
		
		for (final E node : graph) {
			distances.put(node, INFINITY);
			preds.put(node, null);
		}
		distances.put(start, 0);
		nodes.addAll(graph.getNodes());
	}
	
	@Override
	public Stack<E> findPath(E from, E to) {
		if (!from.equals(lastStartingPoint)) {
			PriorityQueue<E> nodes
				= new PriorityQueue<E>(graph.size(), new Comparator<E>() {
				@Override
				public int compare(E o1, E o2) {
					return distances.get(o1) - distances.get(o2);
				}
			});
			
			init(from, nodes);
			
			while (!nodes.isEmpty()) {
				E next = nodes.poll();
				
				for (final E succ : graph.getSuccessors(next)) {
					if (nodes.contains(succ)) {
						final int alternative
							= distances.get(next) +	graph.getWeight(next, succ);
						if (alternative < distances.get(succ)) {
							distances.put(succ, alternative);
							preds.put(succ, next);
						}
					}
				}
			}
		}
		
		return reconstructShortestPath(to);
	}

}
