package test.managers;

import Persistance.Book;
import Persistance.Magazine;
import Persistance.MagazineEdition;
import managers.HibernateManager;
import managers.HibernateManager.AutoSession;
import managers.HibernateManager.AutoTransaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertArrayEquals;

/**
 * HibernateManager Tester.
 *
 * @author <Authors name>
 * @since <pre>Sep 14, 2014</pre>
 * @version 1.0
 */
public class HibernateManagerTest {
    HibernateManager hm;

    @Before
    public void before() throws Exception {
        hm = new HibernateManager();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testListEntities() throws Exception{
       assert !hm.listEntities(Magazine.class).isEmpty();
    }

    @Test
    public void testAddImage() throws Exception{
        File testFile = new File("/Users/denislavrov/Documents/Developing/Java/Library-Management-System/LMS/resources/test.jpeg");
        byte[] oImage = new byte[(int) testFile.length()];
        try(FileInputStream fis = new FileInputStream(testFile)){
            fis.read(oImage);
        }
        byte[] nImage;
        try(AutoTransaction at = hm.newAutoTransaction()){
            Book b = (Book) at.session.get(Book.class, 85965);
            b.imageToDatabase(testFile);
            at.session.saveOrUpdate(b);
            at.tx.commit();
        }
        try(AutoSession as = hm.newAutoSession()){
            nImage = ((Book) as.session.get(Book.class, 85965)).getImage();
        }

        assertArrayEquals("Image didn’t load or save correctly", oImage, nImage);
    }

    /**
     *
     * Method: getSessionFactory()
     *
     */
    @Test
    public void testGetSessionFactory() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: getSession()
     *
     */
    @Test
    public void testGetSession() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: TaskRunner(Runnable task)
     *
     */
    @Test
    public void testTaskRunner() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: addEntity(Object object)
     *
     */
    @Test
    public void testAddEntity() throws Exception {
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
        Serializable key = hm.addEntity(mag);
        assert hm.getEntity(Magazine.class, key).equals(mag);
    }

    /**
     *
     * Method: deleteEntity(Object object)
     *
     */
    @Test
    public void testDeleteEntity() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: newAutoSession()
     *
     */
    @Test
    public void testNewAutoSession() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: newAutoTransaction()
     *
     */
    @Test
    public void testNewAutoTransaction() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: close()
     *
     */
    @Test
    public void testClose() throws Exception {
//TODO: Test goes here...
    }


}
