
package javaff.search;

import javaff.planning.State;
import javaff.planning.Filter;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Comparator;
import java.math.BigDecimal;

import java.util.Hashtable;
import java.util.Iterator;

public class HillClimbingSearch extends Search
{
	protected BigDecimal bestHValue;

	protected Hashtable closed;
	protected LinkedList open;
	protected Filter filter = null;
	
	public HillClimbingSearch(State s)
	{
		this(s, new HValueComparator());
	}

	public HillClimbingSearch(State s, Comparator c)
	{
		super(s);
		setComparator(c);
		
		closed = new Hashtable();
		open = new LinkedList();
	}

	public void setFilter(Filter f)
	{
		filter = f;
	}

	public State removeNext()
	{
			
		return (State) ((LinkedList) open).removeFirst();
	}
	
	public boolean needToVisit(State s) {
		Integer Shash = new Integer(s.hashCode()); // compute hash for state
		State D = (State) closed.get(Shash); // see if its on the closed list
		
		if (closed.containsKey(Shash) && D.equals(s)) return false;  // if it is return false
		
		closed.put(Shash, s); // otherwise put it on
		return true; // and return true
	}
	
	public State search() {
		
		if (start.goalReached()) { // wishful thinking
			return start;
		}

		needToVisit(start); // dummy call (adds start to the list of 'closed' states so we don't visit it again
		
		open.add(start); // add it to the open list
		

		
		
		while (!open.isEmpty()) // whilst still states to consider
		{	
		
			State s = removeNext(); // get the next one
			
			Set successors = s.getNextStates(filter.getActions(s)); // and find its neighbourhood
			
			Iterator succItr = successors.iterator();
			bestHValue = start.getHValue(); // and take its heuristic value as the best so far	
					
			javaff.JavaFF.infoOutput.println(bestHValue);
			
			while (succItr.hasNext()) {
				State succ = (State) succItr.next(); // next successor
				
				
			
				if (needToVisit(succ)) {
					if (succ.goalReached()) { // if we've found a goal state - return it as the solution
						return succ;
					} else if (succ.getHValue().compareTo(bestHValue) < 0) {
						// if we've found a state with a better heuristic value than the best seen so far
						
						bestHValue = succ.getHValue(); // note the new best value
						javaff.JavaFF.infoOutput.println(bestHValue);
						bestSuccessor = new LinkedList();
						bestSuccessor.add(succ);
					} else {
						bestSucessor.add(succ); // otherwise, add to the open list
					}
				if (bestSuccessor = null){
				open = new LinkedList();
				}else{
				int tempNum = javaff.JavaFF.generator.nextInt(bestsuccessor.size());
				State = temp = (State) bestSuccessor.get(tempNum);
				open = new LinkedList();
				
				open.add(temp);				
				
				
				}
				
				
				}
			}
			
			
		}
		return null;
	}
}
