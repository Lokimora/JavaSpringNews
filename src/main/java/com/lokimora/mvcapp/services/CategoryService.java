package com.lokimora.mvcapp.services;

import com.lokimora.mvcapp.models.Category;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lokimora on 6/18/2016.
 */
@Transactional
@Service("categoryService")
public class CategoryService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Category> getAll(){
        return sessionFactory.getCurrentSession().createQuery("from Category").list();
    }

    public void add(String name){

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Category WHERE name =\'" + name + "\'");

        Category existCategory = (Category)query.uniqueResult();

        if(existCategory == null){
            Category category = new Category();
            category.setName(name);
            session.save(category);
        }

    }

    public Category findById(Integer id){
        return (Category)sessionFactory.getCurrentSession().createQuery("from Category where id = " + id).uniqueResult();
    }

    public List<Category> findByIds(List<Long> ids){
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Category WHERE id IN(:ids)");
        query.setParameterList("ids", ids);

        List<Category> categories = query.list();

        return categories;

    }
}
