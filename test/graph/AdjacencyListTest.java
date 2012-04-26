package graph;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class AdjacencyListTest {
	
	@Test
	public void test() {
		IGraph<String> graph = new AdjacencyList<String>();
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		
		graph.addEdge("A", "B", 10);
		graph.addEdge("C", "A", 5);
		graph.addEdge("C", "B", 20);
		
		assertTrue(graph.getSuccessors("B").isEmpty());
		
		List<String> cSucc = graph.getSuccessors("C");
		assertTrue(cSucc.contains("A"));
		assertTrue(cSucc.contains("B"));
		
		graph.removeEdge("C", "A");
		cSucc = graph.getSuccessors("C");
		assertFalse(cSucc.contains("A"));
		assertTrue(cSucc.contains("B"));
		
		assertTrue(graph.getWeight("A", "B") == 10);
		assertTrue(graph.getWeight("C", "B") == 20);
		
		graph.removeNode("C");
		try {
			graph.getSuccessors("C");
			assertTrue(false);
		} catch (RuntimeException e) {
		}
	}

}
