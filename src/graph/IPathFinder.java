package graph;

import java.util.Stack;

public interface IPathFinder<E> {
	Stack<E> findPath(E from, E to);
}
