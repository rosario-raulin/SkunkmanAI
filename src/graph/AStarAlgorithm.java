package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public final class AStarAlgorithm<E> implements IPathFinder<E> {

	private final IGraph<E> graph;
	
	public AStarAlgorithm(final IGraph<E> graph) {
		this.graph = graph;
	}
	
	@Override
	public Stack<E> findPath(E from, E to) throws NoPathFoundException {
		final Map<E, Integer> fValues = new HashMap<E, Integer>(graph.size());
		final Map<E, Integer> gValues = new HashMap<E, Integer>(graph.size());
		final Map<E, E> preds = new HashMap<E, E>(graph.size());
		
		final Set<E> closedList = new HashSet<E>(graph.size());
		final PriorityQueue<E> openList = new PriorityQueue<E>(graph.size(),
				new Comparator<E>() {
					@Override
					public int compare(final E o1, final E o2) {
						return fValues.get(o1) - fValues.get(o2);
					}
		});
		
		gValues.put(from, 0);
		openList.add(from);
		
		do {
			E current = openList.poll();
			if (current.equals(to)) {
				return reconstructPath(to, preds);
			} else {
				for (final E succ : graph.getSuccessors(current)) {
					if (!closedList.contains(succ)) {
						final int newGValue = gValues.get(current) +
								graph.getWeight(current, succ);

						final boolean containsSucc = openList.contains(succ);
						if (!containsSucc || newGValue < gValues.get(succ)) {
							preds.put(succ, current);
							gValues.put(succ, newGValue);

							fValues.put(succ, newGValue + h(succ, to));
							if (containsSucc) {
								openList.remove(succ);
							}
							openList.add(succ);
						}

					}
				}
			}
			closedList.add(current);
		} while (!openList.isEmpty());

		throw new NoPathFoundException(to);
	}
	
	private Stack<E> reconstructPath(final E to, final Map<E,E> preds) {
		final Stack<E> way = new Stack<E>();
		
		E current = to;
		while (preds.get(current) != null) {
			way.push(current);
			current = preds.get(current);
		}
		
		return way;
	}

	private final int h(final E which, final E to) {
		return 1; // TODO: implement better heuristic
	}
}