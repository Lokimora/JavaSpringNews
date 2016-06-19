package com.lokimora.mvcapp.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lokimora on 6/17/2016.
 */
@Entity
@Table(name = "categories", schema = "public")
public class Category implements Serializable{

    public Category(long id){
        this.id = id;
    }

    public Category(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    public List<News> news;

    public void setId(long id){
        this.id = id;
    }

    public long getId(){return id;}

    public void setName(String name){
        this.name = name;
    }

    public String getName(){return name;}

    public void setNews(List<News> news){
        this.news = news;
    }

    public List<News> getNews(){
        if(news == null){
            news = new ArrayList<News>();
        }
        return news;
    }

    @Override
    public String toString(){
        return id + name;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Category)){
            return false;
        }

        Category another = (Category) obj;

        if(id == null){
            if(another.id != null){
                return false;
            }

        }else if(!id.equals(another.id)){
            return false;
        }
        return true;
    }


}
