import java.util.*;

public class CmpByRaven implements Comparator<Person> {
	public int compare(Person p1, Person p2) {
		int retVal = (int) p1.getScoreRaven() - p2.getScoreRaven();
		return retVal;
	}
}