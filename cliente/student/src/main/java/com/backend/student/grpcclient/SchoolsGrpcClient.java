package com.backend.student.grpcclient;


import com.backend.student.dto.CompleteStudentInfoDto;
import com.backend.student.model.School;
import com.backend.student.model.Student;
import com.backend.student.repository.StudentRepository;
import com.schoolsservicegrpc.grpc.SchoolObject;
import com.schoolsservicegrpc.grpc.id;
import com.schoolsservicegrpc.grpc.schoolServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolsGrpcClient {

    @Autowired
    private StudentRepository studentRepository;

    ManagedChannel channel = NettyChannelBuilder.forTarget("dns:///localhost:9090").usePlaintext().build();


    public CompleteStudentInfoDto getCompleteStudentInfo(int studentId) {
        Student student = studentRepository.findById(studentId).get();
        schoolServiceGrpc.schoolServiceBlockingStub stub = schoolServiceGrpc.newBlockingStub(channel);
        SchoolObject retrievedSchool = stub.getOneById(id.newBuilder().setId(student.getIdSchool()).build());

        School school = new School();
        school.setId(retrievedSchool.getId());
        school.setName(retrievedSchool.getName());
        school.setLocation(retrievedSchool.getLocation());

        //channel.shutdown();

        CompleteStudentInfoDto completeStudentInfoDto = new CompleteStudentInfoDto();
        completeStudentInfoDto.setId(student.getId());
        completeStudentInfoDto.setName(student.getName());
        completeStudentInfoDto.setLastname(student.getLastname());
        completeStudentInfoDto.setSchool(school);

        return completeStudentInfoDto;

    }

}
