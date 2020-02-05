import lavka.lavka;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class Main {

    public static void main(final String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.err.close();
        lavka lavka = new lavka();

        boolean exit = false;
        int r;

        lavka.showMenu();
        while (!exit) {

            r = Integer.parseInt(reader.readLine());
            switch (r) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    lavka.showList();
                    break;
                }
                case 2: {
                    lavka.addProduct();
                    break;
                }
                case 3: {
                    lavka.addTags();
                    break;
                }
                case 4: {
                    lavka.updateProduct();
                    break;
                }
                case 5: {
                    lavka.removeProduct();
                    break;
                }
                case 6: {
                    lavka.search();
                    break;
                }
                case 7: {
                    lavka.searchlist();
                    break;
                }
                case 8: {
                    lavka.showListTags();
                    break;
                }
                case 9: {
                    lavka.addTag();
                    break;
                }
                case 10: {
                    lavka.showTags();
                    break;
                }
                default:{
                    lavka.showMenu();
                    break;
                }
            }
        }
    }
}