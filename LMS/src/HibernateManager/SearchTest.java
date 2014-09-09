package HibernateManager;

import java.util.Scanner;

/**
 * Created by denislavrov on 9/4/14.
 */
public class SearchTest {
    public static void main(String[] args) {
        SearchManager sm = new SearchManager(HibernateManager.getSession());

        /*

        for (Object b : sm.fuzzySearch(Book.class, "Aaron, Jason", "author")) {
            System.out.println(b);
        }

        */



        Scanner s = new Scanner(System.in);
        System.out.println("You can input now:");
        String ui = s.nextLine();
        StringBuilder sb = new StringBuilder();

        while(!ui.isEmpty()){
            sb.append(ui);
            sm.bookSuggestions(sb.toString(), 5).forEach(System.out::println);
            ui = s.nextLine();
        }
        System.out.println("Input over");

    }

}
