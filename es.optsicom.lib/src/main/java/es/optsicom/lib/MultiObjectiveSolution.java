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
	protected boolean minimize = true;
	
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
	
	
	/**
	 * Comparation based on dominance: lower is better (minimizing)
	 */
	public int dominanceCompareTo(MultiObjectiveSolution<I> s2) {
		MultiObjectiveSolution<I> s1 = this;
		if (s2 == null) {
			return -1;
		}
		int n = Math.min(s1.numObjectives, s2.numObjectives);

		boolean bigger = false;
		boolean smaller = false;
		boolean indiff = false;
		for (int i = 0; !(indiff) && i < n; i++) {
			if (s1.getObjective(i) > s2.getObjective(i)) {
				bigger = true;
			}
			if (s1.getObjective(i) < s2.getObjective(i)) {
				smaller = true;
			}
			indiff = (bigger && smaller);
		}

		if (smaller && !bigger) {
			if (minimize) {
				return -1;
			} else {
				return 1;
			}
		} else if (bigger && !smaller) {
			if (minimize) {
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}
	
	
	@Override
	public boolean isBetterThan(Solution<I> aSolution) {
		if (aSolution instanceof MultiObjectiveSolution) {
			MultiObjectiveSolution<I> aSol = (MultiObjectiveSolution<I>) aSolution;
			// Uses the dominance comparison.
			return (this.dominanceCompareTo(aSol) == -1);

		} else {
			System.err.println("Trying to compare dominance between a bi-objective solution with a single-objective solution");
			return false;
		}
	}
}
