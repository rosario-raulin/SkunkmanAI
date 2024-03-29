package world;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import apoSkunkman.ai.ApoSkunkmanAILevel;
import graph.AdjacencyList;
import graph.IGraph;

public final class Map {

	private final AbstractWO[][] fields;
	
	public Map(final ApoSkunkmanAILevel level) {
		final byte[][] field = level.getLevelAsByte();
		fields = new AbstractWO[field[0].length][field.length];
		
		for (int x = 0; x < field[0].length; ++x) {
			for (int y = 0; y < field.length; ++y) {
				fields[x][y] = WOFactory.createWorldObject(field[y][x], x, y);
			}
		}
	}
	
	public AbstractWO getField(final int x, final int y) {
		return fields[x][y];
	}
	
	public AbstractWO getField(final Point pos) {
		return getField(pos.x, pos.y);
	}
	
	public IGraph<AbstractWO> asGraph() {
		final int graphSize = (4*fields.length)/3;
		return asGraph(new AdjacencyList<AbstractWO>(graphSize));
	}
	
	public IGraph<AbstractWO> asGraph(final IGraph<AbstractWO> graph) {
		for (final AbstractWO[] f : fields) {
			for (final AbstractWO obj : f) {
				graph.addNode(obj);
				
				List<AbstractWO> succs = getSuccessors(obj);
				// System.out.println("Successors of " + obj.getPosition());
				for (final AbstractWO succ : succs) {
					// System.out.println(succ.getPosition());
					graph.addEdge(obj, succ, succ.rating().getValue());
				}
			}
		}
				
		return graph;
	}

	private List<AbstractWO> getSuccessors(final AbstractWO obj) {
		final List<AbstractWO> succs = new LinkedList<AbstractWO>();
		final int x = obj.getX();
		final int y = obj.getY();
	
		if (y + 1 < fields[0].length && fields[x][y + 1].isWalkable()) {
			succs.add(fields[x][y + 1]); // down
		}
		if (y - 1 >= 0 && fields[x][y - 1].isWalkable()) {
			succs.add(fields[x][y - 1]); // up
		}
		if (x + 1 < fields.length && fields[x + 1][y].isWalkable()) {
			succs.add(fields[x + 1][y]); // right
		}
		if (x - 1 >= 0 && fields[x - 1][y].isWalkable()) {
			succs.add(fields[x - 1][y]); // left
		}
		
		return succs;
	}
}
