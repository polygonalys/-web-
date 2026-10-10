package com.example.gakuboschedules.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gakuboschedules.entity.ClassesSchedules;
import com.example.gakuboschedules.repository.ClassesSchedulesRepository;

@Service
public class GakuboSchedulesService {
    private final ClassesSchedulesRepository classesSchedulesRepository;

    public GakuboSchedulesService(ClassesSchedulesRepository classesSchedulesRepository) {
        this.classesSchedulesRepository = classesSchedulesRepository;
    }

    public List<ClassesSchedules> searchAll() {
        List<ClassesSchedules> list = classesSchedulesRepository.findAll();
        
        return list;
    }

     public List<ClassesSchedules> serachById(ClassesSchedules classesSchedules) {
        List<ClassesSchedules> list = classesSchedulesRepository.findById(classesSchedules);
        return list;
    }

    public void createSchedules(ClassesSchedules classesSchedules) {
        classesSchedulesRepository.createById(classesSchedules);
    }

    public void updateSchedules(ClassesSchedules classesSchedules) {
        classesSchedulesRepository.updateById(classesSchedules);
    }

    public void deleteSchedules(ClassesSchedules classesSchedules) {
        classesSchedulesRepository.deleteById(classesSchedules);
    }
}
