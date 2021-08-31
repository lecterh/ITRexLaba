import java.util.*;

public class StringTask {

    public static List<Integer> indexToDelete = new ArrayList<>();

    //Store index of article to delete and start
    public static String runner(String input) {
        List<String> list = new ArrayList<>(Arrays.asList(input.split(" ")));

        for (String str : list) {
            if (str.equalsIgnoreCase("a")) {
                indexToDelete.add(list.indexOf(str));
            }
            if (str.equalsIgnoreCase("the")) {
                indexToDelete.add(list.indexOf(str));
            }
            if (str.equalsIgnoreCase("an")) {
                indexToDelete.add(list.indexOf(str));
            }
            Collections.sort(indexToDelete);
            Collections.reverse(indexToDelete);

        }
        return firstTask(input);
    }


    /*  If the text contains “ci” and “ce”, change it to “si” and “se”.
        If “ck” then change it to “k”.
        In the other case replace “c” with “k”.
        All the changes should be made in a strong order left-to-right. */
    public static String firstTask(String input) {

        String tmp;
        if (input.contains("ci")) {
            tmp = input.replaceAll("ci", "si");
            input = tmp;
        }

        if (input.contains("ce")) {
            tmp = input.replaceAll("ce", "se");
            input = tmp;
        }

        if (input.contains("ck")) {
            tmp = input.replaceAll("ck", "k");
            input = tmp;
        }
        if (input.contains("c")) {
            tmp = input.replaceAll("c", "k");
            input = tmp;
        }

        return secondTask(input);
    }

    /* If the text contains “ee” then replace it by simple “i”.
       If “oo” then change it by “u”.
       In the other case any double letter should be changed by one letter.*/
    public static String secondTask(String input) {
        List<String> list = new ArrayList<>();
        String tmp;
        for (String s : input.split(" ")) {
            if (s.contains("ee")) {
                tmp = s.replaceAll("ee", "i");
                s = tmp;
            }
            if (s.contains("oo")) {
                tmp = s.replaceAll("oo", "u");
                s = tmp;
            }
            list.add(s.replaceAll("(.)\\1", "$1"));

        }
        return thirdTask(String.join(" ", list));
    }

    /*Remove the letter “e” at the end of each word if the word length > 1.*/
    public static String thirdTask(String input) {
        String tmp;
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(input);
        while (st.hasMoreTokens()) {
            tmp = st.nextToken();
            if (tmp.length() > 1 && tmp.endsWith("e")) {
                int index = tmp.lastIndexOf('e');
                list.add(tmp.substring(0, index));
            } else {
                list.add(tmp);
            }
        }
        return fourthTask(String.join(" ", list));
    }

    /*  Remove the articles “a”, “an” or “the” from the text.
        They should be removed only if they were the words a, an, the in the original text.*/
    public static String fourthTask(String input) {
        List<String> list = new ArrayList<>(Arrays.asList(input.split(" ")));
        for (int i : indexToDelete) {
            list.remove(i);
        }
        return String.join(" ", list);
    }

    public static void main(String[] args) {

        String inputText = "Success a coffee I want too the homecc, andce ckoro buudduu an";
        System.out.println(inputText);
        System.out.println(runner(inputText));

    }

}
