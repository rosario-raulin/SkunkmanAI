package graph;

import java.util.List;

public interface IGraph<E extends INode> extends Iterable<E> {
	void addNode(E node);
	void removeNode(E node);
	void addEdge(E from, E to, int weight);
	void removeEdge(E from, E to);
	void changeWeight(E from, E to, int weight);
	
	int getWeight(E from, E to);
	List<E> getSuccessors(E node);
}
