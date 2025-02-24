package com.backend.school.server;

import com.backend.school.model.School;
import com.backend.school.service.SchoolService;
import com.schoolsservicegrpc.grpc.Empty;
import com.schoolsservicegrpc.grpc.SchoolObject;
import com.schoolsservicegrpc.grpc.id;
import com.schoolsservicegrpc.grpc.schoolServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@GrpcService
public class GrpcSchoolsServiceImpl extends schoolServiceGrpc.schoolServiceImplBase {
    @Autowired
    private SchoolService schoolService;

    @Override
    public void getOneById(id request, StreamObserver<SchoolObject> responseObserver) {

        Optional<School> foundSchool = schoolService.getSchoolById(request.getId());

        SchoolObject objectSchoolToReturn = SchoolObject.newBuilder()
                .setId(foundSchool.get().getId())
                .setName(foundSchool.get().getName())
                .setLocation(foundSchool.get().getLocation())
                .build();

        responseObserver.onNext(objectSchoolToReturn);
        responseObserver.onCompleted();
    }

    @Override
    public void getAll(Empty request, StreamObserver<com.schoolsservicegrpc.grpc.SchoolsList> responseObserver) {

        List<School> foundSchools = schoolService.getAllSchools();
        List<SchoolObject> schoolsGrpcList = new ArrayList<>();
        for (int i = 0; i < foundSchools.size(); i++) {
            SchoolObject schoolToAdd = SchoolObject.newBuilder()
                    .setId(foundSchools.get(i).getId())
                    .setName(foundSchools.get(i).getName())
                    .setLocation(foundSchools.get(i).getLocation())
                    .build();
            schoolsGrpcList.add(schoolToAdd);
        }
        com.schoolsservicegrpc.grpc.SchoolsList.Builder listToReturn = com.schoolsservicegrpc.grpc.SchoolsList.newBuilder();

        for(SchoolObject eachSchool : schoolsGrpcList) {
            listToReturn.addSchool(eachSchool);
        }

        responseObserver.onNext(listToReturn.build());
        responseObserver.onCompleted();

    }
}
