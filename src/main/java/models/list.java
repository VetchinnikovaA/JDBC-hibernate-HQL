package models;

import javax.persistence.*;
import java.util.List;

@Entity
public class list {
    private int id;
    private String value;
    private Products productsByIdProduct;
    private Tag tagByIdTag;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value", nullable = false, length = -1)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        list list = (models.list) o;

        if (id != list.id) return false;
        if (value != null ? !value.equals(list.value) : list.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "Tags{" +
                "id=" + id +
                ", id_product=" + productsByIdProduct +
                ", id_tags=" + tagByIdTag +
                ", value=" + value +
                "}";
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product", referencedColumnName = "id", nullable = false)
    public Products getProductsByIdProduct() {
        return productsByIdProduct;
    }

    public void setProductsByIdProduct(Products productsByIdProduct) {
        this.productsByIdProduct = productsByIdProduct;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tag", referencedColumnName = "id", nullable = false)
    public Tag getTagByIdTag() {
        return tagByIdTag;
    }

    public void setTagByIdTag(Tag tagByIdTag) {
        this.tagByIdTag = tagByIdTag;
    }

    public list(){}

    public list(Products productsByIdProduct, Tag tagByIdTag, String value){
        this.productsByIdProduct = productsByIdProduct;
        this.tagByIdTag = tagByIdTag;
        this.value = value;
    }
}
