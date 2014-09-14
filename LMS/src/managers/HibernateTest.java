package managers;

import Persistance.Magazine;
import Persistance.MagazineEdition;

import java.util.Collection;
import java.util.Date;

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

        //hm.listEntities(Magazine.class).forEach(mag -> System.out.println(mag.getMagazineEditions()));


        Magazine mag = new Magazine();
        mag.setTitle("Bastards on the moon");
        MagazineEdition mage1 = new MagazineEdition();
        MagazineEdition mage2 = new MagazineEdition();
        mage1.setEditionDate(new Date());
        mage1.setEditionTitle("First");
        mage2.setEditionDate(new Date());
        mage2.setEditionTitle("Second");
        Collection<MagazineEdition> magz= mag.getMagazineEditions();
        magz.add(mage1);
        magz.add(mage2);
        mag.setMagazineEditions(magz);
        hm.addEntity(mag);


    }
}
