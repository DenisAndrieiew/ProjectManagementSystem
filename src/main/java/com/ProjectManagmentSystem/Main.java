package com.ProjectManagmentSystem;

import com.ProjectManagmentSystem.dao.CustomersRepository;
import com.ProjectManagmentSystem.dao.DeveloperRepository;
import com.ProjectManagmentSystem.dao.Repository;
import com.ProjectManagmentSystem.dao.model.CustomersDAO;
import com.ProjectManagmentSystem.dao.model.DataAccessObject;
import com.ProjectManagmentSystem.dao.model.DeveloperDAO;
import com.ProjectManagmentSystem.dto.DataTransferObject;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.dto.enums.Sex;
import com.ProjectManagmentSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagmentSystem.service.Service;
import com.ProjectManagmentSystem.service.converter.Converter;
import com.ProjectManagmentSystem.service.converter.DeveloperConverter;

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
        devRep.findByString("first_name", "Anton").stream().forEach(System.out::println);
        devRep.findByNumber("salary", 40000).stream().forEach(System.out::println);

        Repository cusRep = new CustomersRepository(cm);
        Service service1 = new Service(cusRep);
        System.out.println(cusRep.findById(1));
        System.out.println("END");

    }
}
