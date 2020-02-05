package lavka;

import dao.*;
import models.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class lavka {
    private ProductsDao productsDao = new ProductsDao();
    private TagDao tagDao = new TagDao();
    private ListDao listDao = new ListDao();

    public lavka(){}

    public void showMenu() {
        System.out.println("1. Просмотр списка сувениров");
        System.out.println("2. Добавление нового сувенира");
        System.out.println("3. Добавление ключевых значений");
        System.out.println("4. Редактирование сувенира");
        System.out.println("5. Удаление сувенира");
        System.out.println("6. Поиск по критерию N одинаковых сувениров на сумму не более M рублей");
        System.out.println("7. Подбор по набору ключевых слов");
        System.out.println("8. Просмотр таблицы ключевых значений");
        System.out.println("0. Выход");
        System.out.println("Любое дугое число - вывод данного меню");
    }

    //  1. Просмотр списка сувениров
    public void showList() {
        List<Products> products = productsDao.showAllProducts();
           for (Products p: products) {
               System.out.println("=================================");
               System.out.println(p);
           }
    }

    //  2. Добавление ключевых значений
    public void addProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("name:  ");
        String name = reader.readLine();
        System.out.println("price: ");
        int price = Integer.parseInt(reader.readLine());
        Products p=new Products(name,price);
        productsDao.addProduct(p);
    }

    //  3. Добавление значения тега
    public void addTags() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("id_product: ");
        int id_product = Integer.parseInt(reader.readLine());
        System.out.println("tag: ");
        String tag = reader.readLine();
        System.out.println("value: ");
        String value = reader.readLine();
        Tag t=tagDao.findTagByTitle(tag);
        int id_tag=t.getId();
        System.out.println(id_tag);
        listDao.addTag(id_product,id_tag,value);
    }

    //  4. Обновить
    public void updateProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("id_product: ");
        int id_product = Integer.parseInt(reader.readLine());
        System.out.println("name: ");
        String name = reader.readLine();
        System.out.println("price: ");
        int price = Integer.parseInt(reader.readLine());
        productsDao.updateProduct(id_product, name, price);
    }

    //  5. Удаление
    public void removeProduct() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("id_product: ");
        int id_product = Integer.parseInt(reader.readLine());
        productsDao.removeProduct(id_product);
    }

    //  6.Поиск по критерию N одинаковых сувениров на сумму не более M рублей
    public void search()  throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("N: ");
        int n = Integer.parseInt(reader.readLine());
        System.out.println("M: ");
        int m = Integer.parseInt(reader.readLine());
        List<Products> products = productsDao.search(n,m);
        for (Products p: products) {
            System.out.println("=================================");
            System.out.println(p);
        }
    }

    //  7. Поиск по параметрам
    public void searchlist() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("tag: ");
        String tag = reader.readLine();
        System.out.println("value: ");
        String value = reader.readLine();
        System.out.println("tag: ");
        String tag2 = reader.readLine();
        System.out.println("value: ");
        String value2 = reader.readLine();
        Tag t=tagDao.findTagByTitle(tag);
        int id_tag=t.getId();
        t=tagDao.findTagByTitle(tag2);
        int id_tag2=t.getId();
        List<list> products = listDao.searchlist(id_tag,value, id_tag2, value2);
        for (list p: products) {
            System.out.println("=================================");
            System.out.println(p);
        }

      /*  boolean exit = false;
        int r;
        System.out.println("1-продолжить");
        System.out.println("0-выход");
        while (!exit) {

            r = Integer.parseInt(reader.readLine());
            switch (r) {
                case 0: {
                    exit = true;
                    break;
                }
                case 1: {
                    System.out.println("tag: ");
                    tag = reader.readLine();
                    System.out.println("value: ");
                    value = reader.readLine();
                    products=listDao.searchListTags(products,id_tag,value);
                    for (list p: products) {
                        System.out.println("=================================");
                        System.out.println(p);
                    }
                    break;
                }
            }
        }*/
    }

    //  8. Просмотр таблицы ключевых значений
    public void showListTags() throws IOException {
        List<list> list = listDao.showAllList();
        for (list l: list) {
            System.out.println("=================================");
            System.out.println(l);
        }
    }

    //  9. Добавление нового тега в таблицу тегов
    public void addTag() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("title: ");
        String name = reader.readLine();
        Tag t=new Tag(name);
        tagDao.addTag(t);
    }

    //  10. Просмотр списка тегов
    public void showTags() {
        List<Tag> tags = tagDao.showAllTags();
        for (Tag t: tags) {
            System.out.println("=================================");
            System.out.println(t);
        }
    }
}
