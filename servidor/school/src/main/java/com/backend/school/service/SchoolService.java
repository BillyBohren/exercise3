package com.backend.school.service;


import com.backend.school.model.School;
import com.backend.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Transactional
    public School getSchool(int id) {
        return schoolRepository.getOne(id);
    }

    @Transactional
    public void addSchool(School school) {
        schoolRepository.save(school);
    }

    @Transactional
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @Transactional
    public Optional<School> getSchoolById(int id) {
        return schoolRepository.findById(id);
    }

}
