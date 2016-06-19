package com.lokimora.mvcapp.controllers;

import com.lokimora.mvcapp.models.Category;
import com.lokimora.mvcapp.models.News;
import com.lokimora.mvcapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by lokimora on 6/19/2016.
 */
@Controller
public class CategoryController {

    public CategoryController(){}

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/add", method = RequestMethod.GET)
    public String add(){


        return "addcategory";
    }

    @RequestMapping(value = "/category/add", method = RequestMethod.POST)
    public String add(@RequestParam(value="name")String name){

        categoryService.add(name);

        return "redirect:/news";
    }

}
