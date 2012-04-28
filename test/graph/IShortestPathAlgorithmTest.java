package graph;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class IShortestPathAlgorithmTest {

	@Test
	public void test() {
		IGraph<String> graph = new AdjacencyList<String>();
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addNode("D");
		graph.addNode("E");
		graph.addNode("F");
		
		graph.addEdge("A", "B", 10);
		graph.addEdge("A", "C", 10);
		
		graph.addEdge("B", "E", 10);
		graph.addEdge("E", "F", 3);
		
		graph.addEdge("C", "D", 5);
		graph.addEdge("D", "F", 12);
		
		IPathFinder<String> pathfinder = new DijkstraAlgorithm<String>(graph);

		Stack<String> path = pathfinder.findPath("A", "F");
		assertTrue(path.pop().equals("B"));
		assertTrue(path.pop().equals("E"));
		assertTrue(path.pop().equals("F"));
	}

}
