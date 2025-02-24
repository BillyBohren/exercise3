package com.backend.school.rest;


import com.backend.school.dto.Mensaje;
import com.backend.school.dto.SchoolDto;
import com.backend.school.model.School;
import com.backend.school.service.SchoolService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school")
@CrossOrigin(origins = "*")
public class SchoolREST {

    @Autowired
    private SchoolService schoolService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SchoolDto schoolDto){
        if(StringUtils.isBlank(schoolDto.getName()))
            return new ResponseEntity(new Mensaje("El nombre  es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isEmpty(schoolDto.getLocation()))
            return  new ResponseEntity(new Mensaje("El Loacation es obligatorio"),HttpStatus.BAD_REQUEST);

        School school = new School(schoolDto.getName(),schoolDto.getLocation());
        try {
            schoolService.addSchool(school);
            return new ResponseEntity(new Mensaje("El school ha sido agregado"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listaescuelas")
    public ResponseEntity<List<School>> getAllSchools(){
        List<School> list;
        list = schoolService.getAllSchools();
        if(list.isEmpty()){
            return new ResponseEntity(new Mensaje("Lista Vacia"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(list,HttpStatus.OK);
        }
    }

    @GetMapping("/escuela/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable("id") int id){
        Optional<School> student;
        student = schoolService.getSchoolById(id);
        if(student.isEmpty()){
            return new ResponseEntity(new Mensaje("No se encontro la escuela"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(student,HttpStatus.OK);
        }
    }

}
