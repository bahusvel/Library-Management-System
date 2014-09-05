package HibernateManager;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;

/**
 * Created by denislavrov on 9/4/14.
 */
public class SearchManager {
    Session session;
    FullTextSession fullTextSession;
    public SearchManager(Session session){
        this.session = session;
        try {
            initIndex();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initIndex() throws InterruptedException {
        fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
    }


    public List search(Class entity, String input, String... fields){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .onFields(fields)
                .matching(input)
                .createQuery();
        org.hibernate.Query ftq = fullTextSession.createFullTextQuery(query);
        return ftq.list();
    }

    public List fuzzySearch(Class entity, String input, String... fields){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .fuzzy()
                .withThreshold(.7f)
                .onFields(fields)
                .matching(input)
                .createQuery();
        org.hibernate.Query ftq = fullTextSession.createFullTextQuery(query,entity);
        return ftq.list();
    }

    public List wildcardSearch(Class entity, String input, String field){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .wildcard()
                .onField(field)
                .matching(input)
                .createQuery();
        org.hibernate.Query ftq = fullTextSession.createFullTextQuery(query,entity);
        return ftq.list();
    }

    public List phraseSearch(Class entity, String input, String field, int sloppiness){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .phrase()
                .withSlop(sloppiness)
                .onField(field)
                .sentence(input)
                .createQuery();
        org.hibernate.Query ftq = fullTextSession.createFullTextQuery(query,entity);
        return ftq.list();
    }

}
