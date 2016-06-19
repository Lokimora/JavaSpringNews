package com.lokimora.mvcapp.controllers;


import com.lokimora.mvcapp.models.Category;
import com.lokimora.mvcapp.services.CategoryService;
import com.lokimora.mvcapp.services.NewsService;
import com.lokimora.mvcapp.models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@SessionAttributes("categories")
public class NewsController {

    public NewsController(){}


    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/news")
    public ModelAndView getNews(Model model){

            List<News> news = newsService.getAll();

        model.addAttribute("news", news);
        return new ModelAndView("list");
    }

    @RequestMapping(value = "/news/add", method = RequestMethod.GET)
    public ModelAndView getAdd(Model model){

        List<Category> categories = categoryService.getAll();

        model.addAttribute("newsAttribute", new News());
        model.addAttribute("categories", categories);

        return new ModelAndView("addpage");
    }

    @RequestMapping(value = "/news/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("newsAttribute")News news, @RequestParam(value="tags") List<Long> tags){


        List<Category> existingCategories = categoryService.findByIds(tags);
        news.setCategories(existingCategories);

        newsService.add(news);

        return "redirect:/news";
    }

    @RequestMapping(value = "/news/delete", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam(value = "id") Integer id, Model model){
        newsService.delete(id);

        model.addAttribute("id", id);

        return new ModelAndView("deletedpage");
    }

    @ModelAttribute("categories")
    public List<Category> initializeCategories(){
        return categoryService.getAll();
    }

}
