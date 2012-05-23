

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public final class DijkstraAlgorithm<E> implements IPathFinder<E> {

	private final Integer INFINITY = Integer.MAX_VALUE;
	private final IGraph<E> graph;
	private final Map<E,E> preds;
	private E lastStartingPoint;
	
	public DijkstraAlgorithm(IGraph<E> graph) {
		this.graph = graph;
		this.preds = new HashMap<E,E>(graph.size());
		this.lastStartingPoint = null;
	}
	
	@Override
	public Stack<E> findPath(E from, E to) throws NoPathFoundException {
		if (lastStartingPoint == null || !lastStartingPoint.equals(from)) {
			lastStartingPoint = from;
			preds.clear();
			final int mapSize = (4*graph.size())/3;
			final Map<E,Integer> dists = new HashMap<E,Integer>(mapSize);
			final PriorityQueue<E> pq = new PriorityQueue<E>(mapSize,
					new Comparator<E>() {
						@Override
						public int compare(E o1, E o2) {
							return dists.get(o1) - dists.get(o2);
						}});
			
			for (E node : graph) {
				dists.put(node, INFINITY);
			}
			dists.put(from, 0);
			pq.add(from);
			
			while(!pq.isEmpty()) {
				relax(dists, pq.poll(), pq);
			}
		}
		
		final Stack<E> way = pathTo(to);
		if(way.isEmpty()) {
			throw new NoPathFoundException(to);
		} else {
			return way;
		}	
	}

	private void relax(Map<E, Integer> dists, E next, PriorityQueue<E> pq) {
		for (final E succ : graph.getSuccessors(next)) {
			Integer alternative = dists.get(next) + graph.getWeight(next, succ);
			if (alternative < dists.get(succ)) {
				dists.put(succ, alternative);
				preds.put(succ, next);
				
				pq.remove(succ);
				pq.add(succ);
			}
		}
	}

	private Stack<E> pathTo(E to) {
		final Stack<E> way = new Stack<E>();
		
		E current = to;
		while (preds.get(current) != null) {
			way.push(current);
			current = preds.get(current);
		}
		
		return way;
	}
}
