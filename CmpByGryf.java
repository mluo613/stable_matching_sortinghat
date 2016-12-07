import java.util.Comparator;

public class CmpByGryf implements Comparator<Person>{

	@Override
		public int compare(Person p1, Person p2) {
    		int retVal = (int) p1.getScoreGryf() - p2.getScoreGryf();
    		return retVal;
    	}
	
}
