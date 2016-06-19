package com.lokimora.mvcapp.services;

import com.lokimora.mvcapp.models.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

/**
 * Created by lokimora on 6/17/2016.
 */
@Service("newsService")
@Transactional
public class NewsService  {

    @Autowired
    private SessionFactory sessionFactory;


    public void add(News news){
        Session session = sessionFactory.getCurrentSession();

        news.setDate(new Date());

        session.save(news);
    }

    public void delete(Integer id){
        Session session = sessionFactory.getCurrentSession();

        News news = (News)session.get(News.class, id);

        session.delete(news);
    }

    public List<News> getAll(){
        return sessionFactory.getCurrentSession().createQuery("from News").list();
    }

}