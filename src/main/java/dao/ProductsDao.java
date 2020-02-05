package dao;

import models.Products;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import utils.HibernateSessionFactoryUtil;

public class ProductsDao {

    public void addProduct(Products product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(product);
        tx1.commit();
        session.close();
    }

    public Products findProductById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Products.class, id);
    }

    public List<Products> showAllProducts() {
        List<Products> products = (List<Products>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Products ").list();
        return products;
    }

    public void removeProduct(int id_product) {
        Products product = findProductById(id_product);
        if (product != null) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            String query="delete from Products as p where p.id=:id";
            Transaction tx1 = session.beginTransaction();
            session.createQuery(query).setParameter( "id", id_product).executeUpdate();
            tx1.commit();
            session.close();
            System.out.println("Product removed");
        } else {
            System.out.println("No such item in products");
        }
    }

    public List<Products> search(int n, int m) {
        float k=Integer.valueOf(m/n);
        String query;
        query = "from Products as p where p.price <= " + k + "";
        List<Products> products = (List<Products>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(query).list();
        return products;
    }

    public void updateProduct(int id, String name, int price) {
        Products product = findProductById(id);
        if (product != null) {
            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            String query="update Products as p set p.name=:name, p.price=:price where p.id=:id";
            Transaction tx1 = session.beginTransaction();
            session.createQuery(query).setParameter( "name", name).setParameter( "price", price).setParameter( "id", id).executeUpdate();
            tx1.commit();
            session.close();
            System.out.println("Product update");
        } else {
            System.out.println("No such item in products");
        }
    }
}
