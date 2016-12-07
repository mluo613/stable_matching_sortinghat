/*
 * Allison Chow (ac5gq) & Mengjia Luo (ml6uk)
 * 
 * Consulted RosettaCode for the Stable-Matching-Algorithm
 */



import java.util.*;
 
public class Stable {
    public static Scanner scan = new Scanner(System.in);	
    
    
    static List<String> guys = Arrays.asList(
            new String[]{
        "Malfoy", "Potter", "Diggory", "Lovegood"});
    static List<String> girls = Arrays.asList(
            new String[]{
		"Slytherin", "Ravenclaw", "Hufflepuff", "Gryffindor"});
    static Map<String, List<String>> guyPrefers =
            new HashMap<String, List<String>>(){{
        put("Malfoy",
            Arrays.asList("Slytherin", "Ravenclaw", "Hufflepuff", "Gryffindor"));
        put("Potter",
            Arrays.asList("Gryffindor", "Ravenclaw", "Hufflepuff", "Slytherin"));
        put("Diggory",
            Arrays.asList("Hufflepuff", "Gryffindor", "Ravenclaw", "Slyterin"));
        put("Lovegood",
            Arrays.asList("Ravenclaw", "Hufflepuff", "Gryffindor", "Slytherin"));
    }};
    static Map<String, List<String>> girlPrefers =
            new HashMap<String, List<String>>(){{
        put("Slytherin",
            Arrays.asList("Malfoy", "Potter", "Diggory", "Lovegood"));
        put("Gryffindor",
            Arrays.asList("Potter", "Diggory", "Lovegood", "Malfoy"));
        put("Ravenclaw",
            Arrays.asList("Lovegood", "Diggory", "Potter", "Malfoy"));
        put("Hufflepuff",
            Arrays.asList("Diggory", "Lovegood", "Potter", "Malfoy"));
    }};

    
    
    public static void main(String[] args){
        Map<String, String> matches = match(guys, guyPrefers, girlPrefers);
	for(Map.Entry<String, List<String>> couple:guyPrefers.entrySet()){
            System.out.println(couple.getKey() + " prefers " + couple.getValue());
	}
	System.out.println();
	for(Map.Entry<String, List<String>> couple:girlPrefers.entrySet()){
            System.out.println(couple.getKey() + " prefers " + couple.getValue());
	}
	System.out.println();
	for(Map.Entry<String, String> couple:matches.entrySet()){
            System.out.println(
                    couple.getKey() + " takes " + couple.getValue());
        }
        if(checkMatches(guys, girls, matches, guyPrefers, girlPrefers)){
            System.out.println("Sortings are stable");
        }else{
            System.out.println("Sortings are unstable");
        }
	System.out.println();
	
	List<String> users = new ArrayList<String>();
	Map<String, List<String>> pref = new HashMap<String, List<String>>();
	Map<String, List<String>> prefh = new HashMap<String, List<String>>();
	//First Person
	System.out.println("What is your name? ");
	String s1 = scan.next();
	users.add(s1);
	getPref(s1, pref);
	Person p1 = getHousePref(s1);
	System.out.println();
	//Second Person
	System.out.println("What is your name? ");
	String s2 = scan.next();
	users.add(s2);
	getPref(s2, pref);
	Person p2 = getHousePref(s2);
	System.out.println();
	//Third person
	System.out.println("What is your name? ");
	String s3 = scan.next();
	users.add(s3);
	getPref(s3, pref);
	Person p3 = getHousePref(s3);
	System.out.println();
	//Fourth person
	System.out.println("What is your name? ");	
	String s4 = scan.next();
	users.add(s4);
	getPref(s4, pref);
	Person p4 = getHousePref(s4);
	System.out.println();
	
	List<Person> people = new LinkedList<Person>();
	people.add(p1);
	people.add(p2);
	people.add(p3);
	people.add(p4);
	Collections.sort(people, new CmpByGryf());
	String a = people.get(3).getName();
	List<String> gry = new LinkedList<String>(Arrays.asList(a));
	prefh.put("Gryffindor", gry);
	for (int i = 2; i >= 0; i--) {
		String temp = people.get(i).getName();
		prefh.get("Gryffindor").add(temp);
	}
	Collections.sort(people, new CmpByHuffle());
	a = people.get(3).getName();
	List<String> huf = new LinkedList<String>(Arrays.asList(a));
	prefh.put("Hufflepuff", huf);
	for (int i = 2; i >= 0; i--) {
		String temp = people.get(i).getName();
		prefh.get("Hufflepuff").add(temp);
	}
	Collections.sort(people, new CmpBySlyth());
	a = people.get(3).getName();
	List<String> sly = new LinkedList<String>(Arrays.asList(a));
	prefh.put("Slytherin", sly);
	for (int i = 2; i >= 0; i--) {
		String temp = people.get(i).getName();
		prefh.get("Slytherin").add(temp);
	}
	Collections.sort(people, new CmpByRaven());
	a = people.get(3).getName();
	List<String> rav = new LinkedList<String>(Arrays.asList(a));
	prefh.put("Ravenclaw", rav);
	for (int i = 2; i >= 0; i--) {
		String temp = people.get(i).getName();
		prefh.get("Ravenclaw").add(temp);
	}
	
	

	Map<String, String> sorts = match(users, pref, prefh);
	for(Map.Entry<String, List<String>> couple:pref.entrySet()){
            System.out.println(couple.getKey() + " prefers " + couple.getValue());
	}
	System.out.println();
	for(Map.Entry<String, List<String>> couple:prefh.entrySet()){
            System.out.println(couple.getKey() + " prefers " + couple.getValue());
	}
	System.out.println();
	for(Map.Entry<String, String> couple:sorts.entrySet()){
            System.out.println(
                    couple.getKey() + " takes " + couple.getValue());
        }

	if(checkMatches(users, girls, sorts, pref, prefh)){
            System.out.println("Sortings are stable");
        }else{
            System.out.println("Sortings are unstable");
        }
	
	
	/*
String tmp = matches.get(girls.get(0));
        matches.put(girls.get(0), matches.get(girls.get(1)));
        matches.put(girls.get(1), tmp);
System.out.println(
                girls.get(0) +" and " + girls.get(1) + " have switched preferences");
        if(checkMatches(guys, girls, matches, guyPrefers, girlPrefers)){
            System.out.println("Sortings are stable");
        }else{
            System.out.println("Sortings are unstable");
	    }*/
    }
 
    private static Map<String, String> match(List<String> guys,
					     Map<String, List<String>> guyPrefers,
					     Map<String, List<String>> girlPrefers){
        Map<String, String> engagedTo = new TreeMap<String, String>();
        List<String> freeGuys = new LinkedList<String>();
        freeGuys.addAll(guys);
        while(!freeGuys.isEmpty()){
            String thisGuy = freeGuys.remove(0); //get a load of THIS guy
            List<String> thisGuyPrefers = guyPrefers.get(thisGuy);
            for(String girl:thisGuyPrefers){
                if(engagedTo.get(girl) == null){//girl is free
                    engagedTo.put(girl, thisGuy); //awww
                    break;
                }else{
                    String otherGuy = engagedTo.get(girl);
                    List<String> thisGirlPrefers = girlPrefers.get(girl);
                    if(thisGirlPrefers.indexOf(thisGuy) <
                            thisGirlPrefers.indexOf(otherGuy)){
                        //this girl prefers this guy to the guy she's engaged to
                        engagedTo.put(girl, thisGuy);
                        freeGuys.add(otherGuy);
                        break;
                    }//else no change...keep looking for this guy
                }
            }
        }
        return engagedTo;
    }
 
    private static boolean checkMatches(List<String> guys, List<String> girls,
            Map<String, String> matches, Map<String, List<String>> guyPrefers,
            Map<String, List<String>> girlPrefers) {
        if(!matches.keySet().containsAll(girls)){
            return false;
        }
 
        if(!matches.values().containsAll(guys)){
            return false;
        }
 
        Map<String, String> invertedMatches = new TreeMap<String, String>();
        for(Map.Entry<String, String> couple:matches.entrySet()){
            invertedMatches.put(couple.getValue(), couple.getKey());
        }
 
        for(Map.Entry<String, String> couple:matches.entrySet()){
            List<String> shePrefers = girlPrefers.get(couple.getKey());
            List<String> sheLikesBetter = new LinkedList<String>();
            sheLikesBetter.addAll(shePrefers.subList(0, shePrefers.indexOf(couple.getValue())));
            List<String> hePrefers = guyPrefers.get(couple.getValue());
            List<String> heLikesBetter = new LinkedList<String>();
            heLikesBetter.addAll(hePrefers.subList(0, hePrefers.indexOf(couple.getKey())));
 
            for(String guy : sheLikesBetter){
                String guysFinace = invertedMatches.get(guy);
                List<String> thisGuyPrefers = guyPrefers.get(guy);
                if(thisGuyPrefers.indexOf(guysFinace) >
                        thisGuyPrefers.indexOf(couple.getKey())){
                    System.out.printf("Because %s likes %s better than %s and %s"
                            + " likes %s better than their current partner\n",
                       couple.getKey(), guy, couple.getValue(),
                       guy, couple.getKey());
                    return false;
                }
            }
 
            for(String girl : heLikesBetter){
                String girlsFinace = matches.get(girl);
                List<String> thisGirlPrefers = girlPrefers.get(girl);
                if(thisGirlPrefers.indexOf(girlsFinace) >
                        thisGirlPrefers.indexOf(couple.getValue())){
                    System.out.printf("Because %s likes %s better than %s and %s"
                            + " likes %s better than their current partner\n",
                       couple.getValue(), girl, couple.getKey(),
                       girl, couple.getValue());
                    return false;
                }
            }
        }
        return true;
    }

    private static void getPref(String person, Map<String, List<String>> mymap) {
	System.out.println("Enter your housing preferences in order from most preferred to least preferred one at a time: Gryffindor, Slytherin, Hufflepuff, Ravenclaw (Cannot choose the same house twice!)");
	String n1 = scan.next();
	String n2 = scan.next();
	String n3 = scan.next();
	String n4 = scan.next();
	List<String> l = new LinkedList<String>(Arrays.asList(new String[]{n1, n2, n3, n4}));
	mymap.put(person, l);
    }

    private static Person getHousePref(String n) {
    	int score1 = 0;
       	int score2 = 0;
       	int score3 = 0;
      	int score4 = 0;
	
	System.out.println("Which of the following would you most hate people to call you?");
	System.out.println("1  Selfish");
	System.out.println("2  Cowardly");
	System.out.println("3  Ordinary");
	System.out.println("4  Ignorant");
	int ans1 = scan.nextInt();
       	if(ans1 == 1)
	    score1++;
	else if(ans1 == 2)
	    score2++;
	else if(ans1 == 3)
	    score3++;
	else if(ans1 == 4)
	    score4++;
	
	System.out.println("After you have died, what would you most like people to do when they hear your name?");
	System.out.println("1 Miss you, but smile");
	System.out.println("2 Think with admiration of your achievements");
	System.out.println("3 I don't care what people think of me after I'm dead; it's what they think when I'm alive that counts");
	System.out.println("4 Ask for more stories about your adventures");
	int ans2 = scan.nextInt();
	if(ans2 == 1)
	    score1++;
	else if(ans2 == 2)
	    score2++;
	else if(ans2 == 3)
	    score3++;
	else if(ans2 == 4)
	    score4++;
	
	System.out.println("Given the choice, would you rather invent a potion that would guarantee you:");
	System.out.println("1 Love?");
	System.out.println("2 Glory?");
	System.out.println("3 Power?");
	System.out.println("4 Wisdom?");
	int ans3 = scan.nextInt();
        if(ans3 == 1)
	    score1++;
	else if(ans3 == 2)
	    score2++;
	else if(ans3 == 3)
	    score3++;
	else if(ans3 == 4)
	    score4++;
	
	System.out.println("How would you like to be known to history?");
	System.out.println("1 The Good");
	System.out.println("2 The Bold");
	System.out.println("3 The Great");
	System.out.println("4 The Wise");
	int ans4 = scan.nextInt();
        if(ans4 == 1)
	    score1++;
	else if(ans4 == 2)
	    score2++;
	else if(ans4 == 3)
	    score3++;
	else if(ans4 == 4)
	    score4++;
    
    Person p = new Person(n, score1, score2, score3, score4);
    return p;
    }
        /*
    Map<String, Integer> housepts = new HashMap<String,Integer>();
    housepts.put("Hufflepuff", score1);
    housepts.put("Gryffindor", score2);
    housepts.put("Slytherin", score3);
    housepts.put("Ravenclaw", score4);
    Map<String, Integer> sortedHouses = sortByValue(housepts);
    Object[] houses = sortedHouses.keySet().toArray();
    String topHouse = houses[0].toString();
	String secondHouse = houses[1].toString();
	String thirdHouse = houses[2].toString();
	String lastHouse = houses[3].toString();
	
	mymap.put(person, Arrays.asList(topHouse, secondHouse, thirdHouse, lastHouse));
    
    Person p = new Person(score1, score2, score3, score4);
    return p;
    }
    
    public static Map<String, Integer> sortByValue(Map<String, Integer> map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
    class Person{
    	
    	private int scoreGryf, scoreSlyth, scoreHuffle, scoreRaven;
    	
    	Person(int score1, int score2, int score3, int score4) {
    		scoreGryf = score1;
    		scoreSlyth = score2;
    		scoreHuffle = score3;
    		scoreRaven = score4;
    	}
    	
    	public int getScoreGryf() {
    		return scoreGryf;
    	}
    	
    	public int getScoreSlyth() {
    		return scoreSlyth;
    	}
    	
    	public int getScoreHuffle() {
    		return scoreHuffle;
    	}
    	
    	public int getScoreRaven() {
    		return scoreRaven;
    	}
    	
    }
    
    class CmpByGryf implements Comparator<Person> {
    	public int compare(Person p1, Person p2) {
    		int retVal = (int) p1.getScoreGryf() - p2.getScoreGryf();
    		return retVal;
    	}
    }
    
    class CmpBySlyth implements Comparator<Person> {
    	public int compare(Person p1, Person p2) {
    		int retVal = (int) p1.getScoreSlyth() - p2.getScoreSlyth();
    		return retVal;
    	}
    }
    
    class CmpByHuffle implements Comparator<Person> {
    	public int compare(Person p1, Person p2) {
    		int retVal = (int) p1.getScoreHuffle() - p2.getScoreHuffle();
    		return retVal;
    	}
    }
    
    class CmpByRaven implements Comparator<Person> {
    	public int compare(Person p1, Person p2) {
    		int retVal = (int) p1.getScoreRaven() - p2.getScoreRaven();
    		return retVal;
    	}
    }
    */
}
