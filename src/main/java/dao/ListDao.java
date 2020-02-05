package dao;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;
import utils.HibernateSessionFactoryUtil;

public class ListDao {

    public void addTag(int id_product, int id_tag, String value) {
        ProductsDao productsDAO = new ProductsDao();
        Products product = productsDAO.findProductById(id_product);
        if(product == null){
            return;
        }
        TagDao tagDAO = new TagDao();
        Tag tag = tagDAO.findTagById(id_tag);
        if(tag!=null){
            list list = new list(product, tag,value) ;
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction tx1 = session.beginTransaction();
            session.save(list);
            tx1.commit();
            session.close();
            System.out.println("Successfully added");
        }else {
            System.out.println("No such item tags");
        }
    }


    public List<list> listplus(List<list> l1, List<list> l2){
        List<list> l= l1;
        for (list p: l2) {
            l.add(p);
        }
        return l;
    }

    public List<list> search(int id, String value) {
        String query;
        query = "from list as l where l.tagByIdTag.id = :id and l.value=:value";
        List<list> list = (List<list>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(query).setParameter( "id", id).setParameter( "value", value).list();
        return list;
    }

    public List<list> searchlist(int id, String value, int id2, String value2) {
        List<list> l1= search(id,value);
        List<list> l2= search(id2,value2);
        List<list> l=listplus(l1,l2);
        return l;
    }


    public List<list> showAllList() {
        List<list> tags = (List<list>)HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From list ").list();
        return tags;
    }

    public List<list> searchListTags(List l,int id, String value) {
        List<list> newList = null;
        List<list> tags = l;
        for (list lists : tags) {
            if (lists.getTagByIdTag().getId() == id & lists.getValue() == value)
                newList.add(lists);
        }
        return newList;
    }
}
