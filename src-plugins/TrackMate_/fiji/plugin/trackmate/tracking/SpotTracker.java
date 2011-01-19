package fiji.plugin.trackmate.tracking;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import fiji.plugin.trackmate.Logger;
import fiji.plugin.trackmate.Spot;
import mpicbg.imglib.algorithm.Algorithm;

/**
 * This interface should be used when creating algorithms for linking objects across
 * multiple frames in time-lapse images.
 * 
 * @author Nicholas Perry
 *
 */
public interface SpotTracker extends Algorithm {

	/**
	 * Returns the final tracks computed, as a directed Graph of spots.
	 */
	public SimpleWeightedGraph<Spot, DefaultEdge> getTrackGraph();

	/**
	 * Set the logger used to echo log messages.
	 */
	void setLogger(Logger logger);
	
}