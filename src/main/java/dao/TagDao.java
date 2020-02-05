package dao;

import models.Products;
import models.Tag;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import utils.HibernateSessionFactoryUtil;

public class TagDao {
    public void addTag(Tag tag) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(tag);
        tx1.commit();
        session.close();
    }

    public Tag findTagById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Tag.class, id);
    }

    public Tag findTagByTitle(String title) {
        String query;
        query = "from Tag as t where t.title = '" + title + "'";
        List<Tag> tags = (List<Tag>)HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(query).list();
        Tag t=tags.get(0);
        return t;
    }

    public List<Tag> showAllTags() {
        List<Tag> tags = (List<Tag>)HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Tag ").list();
        return tags;
    }
}
