package com.lokimora.mvcapp.services;

import com.lokimora.mvcapp.models.Category;
import com.lokimora.mvcapp.models.News;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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

    public News findById(Long id){
        return (News)sessionFactory.getCurrentSession().createQuery("from News where id =" + id).uniqueResult();
    }

    public void add(News news){
        Session session = sessionFactory.getCurrentSession();

        news.setDate(new Date());

        session.save(news);
    }

    public void edit(News news){

        News oldNews = findById(news.getid());

        oldNews.setTitle(news.getTitle());
        oldNews.setContent(news.getContent());
        oldNews.setCategories(news.getCategories());
    }

    public void delete(Long id){
        Session session = sessionFactory.getCurrentSession();

        News news = (News)session.get(News.class, id);

        session.delete(news);
    }

    public List<News> getByCategory(Long categoryId){

        Session session = sessionFactory.getCurrentSession();

        String strSql = "select n from News n " +
                "join n.categories c " +
                "where c.id = :catId";

        Query query = session.createQuery(strSql);
        query.setParameter("catId", categoryId);

        List<News> news = query.list();

        return news;

    }

    public List<News> getAll(String q){

        if(q != null && !q.isEmpty()) {
            q = q.toLowerCase();

            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("from News where title LIKE LOWER(:keyword) or content LIKE LOWER(:keyword)");
            query.setParameter("keyword", "%"+q+"%");

            List<News> news = query.list();

            return news;

        }

        return sessionFactory.getCurrentSession().createQuery("from News").list();
    }



}