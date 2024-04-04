package ru.mirea.task13;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValuePrinter {
    @Value("${student.name}")
    private String name;
    @Value("${student.last_name}")
    private String surname;
    @Value("${student.group}")
    private String group;

    @PostConstruct
    public void printValues() {
        System.out.println(name);
        System.out.println(surname);
        System.out.println(group);
    }
}
