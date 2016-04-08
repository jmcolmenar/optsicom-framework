package es.optsicom.lib;

/**
 * Multi-objetive support for solutions.
 * 
 * @author J. Manuel Colmenar
 *
 * @param <I>
 */
public abstract class MultiObjectiveSolution<I extends Instance> extends Solution<I> {

	protected int numObjectives = 1;
	protected double[] objectiveValues;
	
	/**
	 * It is required to specify the number of objectives.
	 * 
	 * @param numberOfObjectives
	 */
	public MultiObjectiveSolution(int numberOfObjectives) {
		this.numObjectives = numberOfObjectives;
		objectiveValues = new double[numberOfObjectives];
	}
	
	@Override
	public double getWeight() {
		// Returns the first objective
		return getObjective(0);
	}
	
	/**
	 * Obtains the objective values.
	 * 
	 * @param objectiveNumber
	 * 
	 * @return Value of the objective id passed as a parameter, considering that
	 * 		   objective ids are integer values that begin in 0.
	 */
	public double getObjective(int objectiveNumber) {
		return this.objectiveValues[objectiveNumber];
	}
}
