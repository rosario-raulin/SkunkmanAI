import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import apoSkunkman.ai.ApoSkunkmanAILevel;

public final class Map implements Iterable<AbstractWO> {

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
		return asGraph(EMapMode.NORMAL);
	}
	
	public IGraph<AbstractWO> asGraph(EMapMode mode) {
		final int graphSize = (4*fields.length)/3;
		return asGraph(new AdjacencyList<AbstractWO>(graphSize), mode);
	}
	
	public IGraph<AbstractWO> asGraph(final IGraph<AbstractWO> graph,
			EMapMode mode) {
		for (final AbstractWO[] f : fields) {
			for (final AbstractWO obj : f) {
				graph.addNode(obj);
				
				List<AbstractWO> succs = getSuccessors(obj);
				if (mode.equals(EMapMode.STRICT)) {
					final List<AbstractWO> filteredSuccs =
							new LinkedList<AbstractWO>();
					for (final AbstractWO succ : succs) {
						if (!succ.isBlocked()) {
							filteredSuccs.add(succ);
						}
					}
					succs = filteredSuccs;
				}
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

	@Override
	public Iterator<AbstractWO> iterator() {
		return new Iterator<AbstractWO>() {

			private int x = 0;
			private int y = 0;
			
			@Override
			public boolean hasNext() {
				return !(x == (fields.length - 1) && y == (fields.length - 1));
			}

			@Override
			public AbstractWO next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				} else {
					if (y == (fields.length - 1)) {
						++x;
						y = 0;
					}
					return fields[x][y++];
				}
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public boolean containsSkunkman(Point position) {
		return getField(position) instanceof Skunkman;
	}
}
