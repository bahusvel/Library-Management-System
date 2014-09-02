package SessionFactory;

import Persistance.Magazine;


/**
 * Created by denislavrov on 9/2/14.
 */
public class Test {
    public static void main(String[] args) {
        HibernateManager hm = new HibernateManager();
        Magazine magazine = new Magazine();
        magazine.setTitle("Test");
        hm.addEntity(magazine);

    }
}
