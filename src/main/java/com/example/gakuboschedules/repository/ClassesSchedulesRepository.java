package com.example.gakuboschedules.repository;

import java.util.List;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.gakuboschedules.entity.ClassesSchedules;

@Repository
public class ClassesSchedulesRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClassesSchedulesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 全ての列を取得
    public List<ClassesSchedules> findAll() {
        String sql = "SELECT grade, hour, school_subject FROM classes_schedules";
        RowMapper<ClassesSchedules> classRowMapper = (resultSet, rowNum) -> {
             ClassesSchedules classesSchedules = new ClassesSchedules();
             classesSchedules.setGrade(resultSet.getInt("grade"));
             classesSchedules.setHour(resultSet.getInt("hour"));
             classesSchedules.setSchoolSubject(resultSet.getString("school_subject"));
             return classesSchedules;
        };

        return jdbcTemplate.query(sql, classRowMapper); 
    }

    // 
    public List<ClassesSchedules> findById(ClassesSchedules classesSchedules) {
        String sql = "SELECT * FROM classes_schedules WHERE grade=? AND hour=? ORDER BY grade ASC";
        List<ClassesSchedules> classesSchedulesList = jdbcTemplate.query(sql, new DataClassRowMapper<>(ClassesSchedules.class), classesSchedules.getGrade(), classesSchedules.getHour());
        return classesSchedulesList;
    }

    public void createById(ClassesSchedules classesSchedules) {
        String sql = "INSERT INTO classes_schedules VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, classesSchedules.getGrade(), classesSchedules.getHour(), classesSchedules.getSchoolSubject());
    }

    public void updateById(ClassesSchedules classesSchedules) {
        String sql = "UPDATE classes_schedules SET school_subject = ? WHERE grade = ? AND hour = ?";
        jdbcTemplate.update(sql, classesSchedules.getSchoolSubject(), classesSchedules.getGrade(), classesSchedules.getHour());
    }

    public void deleteById(ClassesSchedules classesSchedules) {
        String sql = "DELETE FROM classes_schedules WHERE grade = ? AND hour = ?";
        jdbcTemplate.update(sql, classesSchedules.getGrade(), classesSchedules.getHour());
    }
}
