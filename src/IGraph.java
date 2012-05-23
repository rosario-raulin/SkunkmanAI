

import java.util.List;
import java.util.Set;

public interface IGraph<E> extends Iterable<E> {
	void addNode(E node);
	void removeNode(E node);
	void addEdge(E from, E to, int weight);
	void removeEdge(E from, E to);
	void changeWeight(E from, E to, int weight);
	
	int getWeight(E from, E to);
	Set<E> getNodes();
	List<E> getSuccessors(E node);
	
	int size();
}
