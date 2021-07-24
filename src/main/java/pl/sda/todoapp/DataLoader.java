package pl.sda.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.sda.todoapp.entity.TodoEntity;
import pl.sda.todoapp.entity.UserEntity;
import pl.sda.todoapp.repository.TodoRepository;
import pl.sda.todoapp.repository.UserRepository;

import java.util.Date;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setFirstname("admin");
        admin.setLastname("admin");

        admin = userRepository.save(admin);

        for (int i = 1; i <= 10; i++) {
            TodoEntity entity = new TodoEntity();
            entity.setTask("Zadanie nr " + i);
            entity.setCreateDate(new Date());
            entity.setUser(admin);

            todoRepository.save(entity);
        }

        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setFirstname("user");
        user.setLastname("user");

        user = userRepository.save(user);

        for (int i = 11; i <= 20; i++) {
            TodoEntity entity = new TodoEntity();
            entity.setTask("Zadanie nr " + i);
            entity.setCreateDate(new Date());
            entity.setUser(user);

            todoRepository.save(entity);
        }
    }
}
