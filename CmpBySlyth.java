import java.util.Comparator;

public class CmpBySlyth implements Comparator<Person>{

	@Override
	public int compare(Person p1, Person p2) {
		int retVal = (int) p1.getScoreSlyth() - p2.getScoreSlyth();
		return retVal;
	}

}
