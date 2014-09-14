package managers;

/**
 * Created by denislavrov on 9/2/14.
 */
public class HibernateTest {
    /**
     * BIG DEAL !!!!
     * methods in Session take arguments of serializable
     * which really is just the primary key in the database.
     * so just supplying the index is fine.
     */


    public static void main(String[] args) {
        HibernateManager hm = new HibernateManager();

    }
}
