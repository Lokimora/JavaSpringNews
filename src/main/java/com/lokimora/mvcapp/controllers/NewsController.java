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
public class NewsController {

    public NewsController(){}


    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;



    @RequestMapping(value={"/", "news"})
    public ModelAndView getNews(@RequestParam(value = "q", required = false) String q, Model model){

        List<News> news = newsService.getAll(q);

        model.addAttribute("news", news);
        return new ModelAndView("list");
    }

    @RequestMapping(value = "/findByCategory")
    public ModelAndView getByCategory(@RequestParam(value = "id") Long id, Model model){

        List<News> news =  newsService.getByCategory(id);

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

    @RequestMapping(value = "/news/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id") Long id, Model model)
    {
        List<Category> categories = categoryService.getAll();

        model.addAttribute("newsAttribute", newsService.findById(id));
        model.addAttribute("categories", categories);

        return "editpage";
    }


    @RequestMapping(value = "/news/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute(value = "newsAttribute") News news,
                       @RequestParam(value = "id") Long id,
                       @RequestParam(value  = "tags") List<Long> tags){


        List<Category> existingCategories = categoryService.findByIds(tags);

        news.setCategories(existingCategories);
        news.setId(id);

        newsService.edit(news);

        return "redirect:/news";

    }

    @RequestMapping(value = "/news/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") Long id, Model model){
        newsService.delete(id);

        model.addAttribute("id", id);

        return "redirect:/news";
    }



}
