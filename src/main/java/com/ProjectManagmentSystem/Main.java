package com.ProjectManagmentSystem;

import com.ProjectManagmentSystem.dao.DeveloperRepository;
import com.ProjectManagmentSystem.dto.DeveloperDTO;
import com.ProjectManagmentSystem.dto.Sex;
import com.ProjectManagmentSystem.jdbc.config.DatabaseConnectionManager;
import com.ProjectManagmentSystem.service.DeveloperService;

public class Main {
    public static void main(String[] args) {
        DatabaseConnectionManager cm = new DatabaseConnectionManager("localhost", "homework_3",
                "postgres", "1234");
        DeveloperDTO dto = new DeveloperDTO(100L, "Illya", "Muromec", 33, Sex.MALE,
                "Fable character", 40000);

        DeveloperRepository devRep = new DeveloperRepository(cm);
        DeveloperService service = new DeveloperService(devRep);

        DeveloperDTO dto1 = service.create(dto);
        System.out.println(dto1);
        System.out.println("------------------------------");
        System.out.println("UPDATED");
        dto.setFirstName("Mykolaj");
        DeveloperDTO update = service.update(dto);
        System.out.println(update);

        service.delete(100L);
    }
}
