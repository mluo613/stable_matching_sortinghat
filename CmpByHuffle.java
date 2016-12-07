import java.util.*;


public class CmpByHuffle implements Comparator<Person> {
    	public int compare(Person p1, Person p2) {
    		int retVal = (int) p1.getScoreHuffle() - p2.getScoreHuffle();
    		return retVal;
    	}
    }