package com.ProjectManagementSystem;

import com.ProjectManagementSystem.dao.CustomersRepository;
import com.ProjectManagementSystem.dao.DeveloperRepository;
import com.ProjectManagementSystem.dao.Repository;
import com.ProjectManagementSystem.dao.model.DeveloperDAO;
import com.ProjectManagementSystem.dto.DataTransferObject;
import com.ProjectManagementSystem.dto.DeveloperDTO;
import com.ProjectManagementSystem.dto.enums.Sex;
import com.ProjectManagementSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagementSystem.service.Service;
import com.ProjectManagementSystem.service.converter.Converter;
import com.ProjectManagementSystem.service.converter.DeveloperConverter;

public class Main {
    public static void main(String[] args) {
        System.out.println("BEGIN");
        DatabaseConnectionManager cm = new DatabaseConnectionManager("localhost", "homework_3",
                "postgres", "1234");
        DeveloperDTO dto = new DeveloperDTO("Illya", "Muromec", 33, Sex.MALE,
                "Fable character", 40000);

        Repository devRep = new DeveloperRepository(cm);
        Service service = new Service(devRep);

        DataTransferObject dto1 = service.create(dto);
        System.out.println(dto1);
        System.out.println("------------------------------");
        System.out.println("UPDATED");
        dto.setFirstName("Mykolaj");
        DataTransferObject update = service.update(dto1);
        System.out.println(update);
//
//        service.delete(100L);
Converter<DeveloperDAO, DeveloperDTO> converter = new DeveloperConverter();


        Repository cusRep = new CustomersRepository(cm);
        Service service1 = new Service(cusRep);
        System.out.println(cusRep.findById(1));
        System.out.println("END");

    }
}
