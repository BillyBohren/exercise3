package com.backend.student.rest;


import com.backend.student.dto.CompleteStudentInfoDto;
import com.backend.student.dto.Mensaje;
import com.backend.student.dto.StudentDto;
import com.backend.student.grpcclient.SchoolsGrpcClient;
import com.backend.student.model.Student;
import com.backend.student.service.StudentService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentREST {

    @Autowired
    private StudentService studentService;

    @Autowired
    SchoolsGrpcClient schoolsGrpcClient;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody StudentDto studentDto){
        if(StringUtils.isBlank(studentDto.getName()))
            return new ResponseEntity(new Mensaje("El nombre  es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isEmpty(studentDto.getLastname()))
            return  new ResponseEntity(new Mensaje("El LastName es obligatorio"),HttpStatus.BAD_REQUEST);
        if(!Optional.ofNullable(studentDto.getIdSchool()).isPresent())
            return  new ResponseEntity(new Mensaje("El idSchool es obligatorio"),HttpStatus.BAD_REQUEST);

        Student student = new Student(studentDto.getName(),studentDto.getLastname(),studentDto.getIdSchool());
        try {
            studentService.addStudent(student);
            return new ResponseEntity(new Mensaje("El student ha sido agregado"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listaestudiantes")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> list;
        list = studentService.getAllStudents();
        if(list.isEmpty()){
            return new ResponseEntity(new Mensaje("Lista Vacia"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(list,HttpStatus.OK);
        }
    }

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id){
        Optional<Student> student;
        student = studentService.getStudentById(id);
        if(student.isEmpty()){
            return new ResponseEntity(new Mensaje("No se encontro al estudiante"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(student,HttpStatus.OK);
        }
    }

    @GetMapping("/estudiantecompleto/{id}")
    public ResponseEntity<CompleteStudentInfoDto> getStudentCompleteById(@PathVariable("id") int id){
        CompleteStudentInfoDto student;
        student = schoolsGrpcClient.getCompleteStudentInfo(id);
        if(student == null){
            return new ResponseEntity(new Mensaje("No se encontro al estudiante"), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(student,HttpStatus.OK);
        }
    }


}
