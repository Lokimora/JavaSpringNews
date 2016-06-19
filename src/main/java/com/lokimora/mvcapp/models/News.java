package com.lokimora.mvcapp.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "news", schema = "public")
public class News implements Serializable{

    public News(){
        categories = new ArrayList<Category>();
    }

    @Id
    @SequenceGenerator(name = "news_seq", sequenceName = "news_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_seq")
    private Long id;

    private String title;

    private String content;

    private Date date;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Category.class, cascade = { CascadeType.ALL })
    @JoinTable(name = "news_categories", schema = "public",
            joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    public List<Category> categories;

    public void setId(long id){
        this.id = id;
    }

    public long getid(){return id;}

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){return title;}

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){return content;}

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate(){return date;}

    public void setCategories(List<Category> categories){
        this.categories = categories;
    }

    public List<Category> getCategories(){
        if(categories == null){
            categories = new ArrayList<Category>();
        }

        return categories;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(!(obj instanceof News)){
            return false;
        }

        News another = (News) obj;

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
