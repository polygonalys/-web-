package com.example.gakuboschedules.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.gakuboschedules.entity.ClassesSchedules;
import com.example.gakuboschedules.service.GakuboSchedulesService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class GakuboController {
    private final GakuboSchedulesService gakuboSchedulesService;

    public GakuboController(GakuboSchedulesService gakuboSchedulesService) {
        this.gakuboSchedulesService = gakuboSchedulesService;
    }

    // HTMLビューに遷移
    @GetMapping("/viewRead")
    public String showViewRead(Model model) {
        model.addAttribute("classesSchedules", new ClassesSchedules());
        return "user/viewRead";
    }

    @GetMapping("/viewCreate")
    public String showViewCreate(Model model) {
        model.addAttribute("classesSchedules", new ClassesSchedules());
        return "user/viewCreate";
    }

    @GetMapping("/viewUpdate")
    public String showViewUpdate(Model model) {
        model.addAttribute("classesSchedules", new ClassesSchedules());
        return "user/viewUpdate";
    }

    @GetMapping("/viewDelete")
    public String showViewDelete(Model model) {
        model.addAttribute("classesSchedules", new ClassesSchedules());
        return "user/viewDelete";
    }
    

    @GetMapping(value = "/read", params = "findById")
    public String getReadSchedules(@Validated @ModelAttribute("classesSchedules") ClassesSchedules classesSchedules, BindingResult rs, Model model) {
        if(rs.hasErrors()) {
            return "user/viewRead";
        }
        List<ClassesSchedules> classesSchedulesList = gakuboSchedulesService.serachById(classesSchedules);
        model.addAttribute("classesSchedulesList", classesSchedulesList);

        return "dispatcher/viewReadDisp";
    }

    @GetMapping(value = "/read", params = "findAll")
    public String getReadAllSchedules(Model model) {
        List<ClassesSchedules> classesSchedulesList = gakuboSchedulesService.searchAll();
        model.addAttribute("classesSchedulesList", classesSchedulesList);
        
        return "dispatcher/viewReadAllDisp";
    }
    
    @PostMapping("/create")
    public String postCreateSchedules(@Valid @ModelAttribute("classesSchedules") ClassesSchedules classesSchedules, BindingResult rs, Model model) {
        if(rs.hasErrors()){
            return "user/viewCreate";
        }
        
        gakuboSchedulesService.createSchedules(classesSchedules);
        return "dispatcher/viewCreateDisp";
    }
    
    @PostMapping("/update")
    public String postUpdateSchedules(@Valid @ModelAttribute("classesSchedules") ClassesSchedules classesSchedules, BindingResult rs) {
        if(rs.hasErrors()) {
            return "user/viewUpdate";
        }

        gakuboSchedulesService.updateSchedules(classesSchedules);
        return "dispatcher/viewUpdateDisp";
    }
    
    @PostMapping("/delete")
    public String postDeleteSchedules(@Valid @ModelAttribute("classesSchedules") ClassesSchedules classesSchedules, BindingResult rs) {
        if(rs.hasErrors()) {
            return "user/viewUpdate";
        }

        gakuboSchedulesService.deleteSchedules(classesSchedules);
        return "dispatcher/viewDeleteDisp";
    }
}
