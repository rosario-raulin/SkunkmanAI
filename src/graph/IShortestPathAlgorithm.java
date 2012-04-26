package graph;

import java.util.Stack;

public interface IShortestPathAlgorithm<E> {
	Stack<E> findPath(E from, E to);
}
