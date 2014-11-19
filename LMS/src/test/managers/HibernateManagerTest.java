package test.managers;

import managers.HibernateManager;
import managers.HibernateManager.AutoSession;
import managers.HibernateManager.AutoTransaction;
import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistance.*;
import persistance.base.Edition;
import persistance.base.Lease;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
       assert !hm.listEntities(Member.class).isEmpty();
    }

    @Test
    public void testAddImage() throws Exception{
        final int BOOK = 85967;

        byte[] oImage = IOUtils.toByteArray(HibernateManagerTest.class.getClassLoader().getResourceAsStream("test.jpeg"));
        try(AutoTransaction at = hm.newAutoTransaction()){
            Book b = (Book) at.session.get(Book.class, BOOK);
            b.getImage().setImage(oImage);
            at.session.saveOrUpdate(b);
            at.tx.commit();
        }
        byte[] nImage;
        try(AutoSession as = hm.newAutoSession()){
            nImage = ((Book) as.session.get(Book.class, BOOK)).getImage().getImage();
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
        Collection<Edition<Magazine>> magz= mag.getMagazineEditions();
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


    @Test
    public void testInheritance() throws Exception {
        try(AutoTransaction at = hm.newAutoTransaction()) {
            Book book = new Book();
            BookEntity bookEntity = new BookEntity();
            bookEntity.setAcquisitionDate(new Date());
            bookEntity.setAbstractItem(book);

            book.setTitle("Hello Inheritance");
            at.session.save(book);
            at.session.save(bookEntity);
            at.tx.commit();

        }
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

    @Test
    public void testTopRated(){
        try(AutoSession as = hm.newAutoSession()){
            Query query = as.session.createQuery("from Book order by rating desc");
            query.setMaxResults(10);
            List<Book> top = query.list();
            System.out.println(top);
        }
    }

    @Test
    public void testLogin() throws Exception {
        try(AutoSession as = hm.newAutoSession()){
            Query query = as.session.createQuery("from Member where username = 'bahus' and password='1234'");
            List<Member> top = query.list();
            System.out.println(top.size()==1);
        }

    }

    @Test
    public void testTitles() throws Exception {
        try(AutoSession as = hm.newAutoSession()){
            Member member = hm.getEntity(Member.class, 1);
            as.session.saveOrUpdate(member);
            Collection<Lease> top = member.getLeases();
            top.forEach(lease -> System.out.println(((Book) lease.getLeasableEntity().getAbstractItem()).getTitle()));
        }

    }

    @Test
    public void testMembers() throws Exception {
        try(AutoSession as = hm.newAutoSession()){
            String email = "bahus.vel@gmail.com";
            Query query = as.session.createQuery("from Member as m where m.email = :email");
            query.setParameter("email", email);
            List<Member> result = query.list();
            result.forEach(res -> System.out.println(res.getUsername()));
        }

    }
}
