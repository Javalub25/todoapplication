package pl.sda.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.sda.todoapp.entity.TodoEntity;
import pl.sda.todoapp.entity.UserEntity;
import pl.sda.todoapp.model.TodoDto;
import pl.sda.todoapp.repository.TodoRepository;
import pl.sda.todoapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TodoDto> findAll() {

        UserEntity userEntity = getCurrentUser();

        List<TodoDto> tasks = new ArrayList<>();

        for (TodoEntity todoEntity: todoRepository.findAllByUser(userEntity)) {
            tasks.add(new TodoDto(todoEntity.getId(), todoEntity.getTask(), todoEntity.getCreateDate().toString()));
        }

        return tasks;
    }

    public void saveTodo(TodoDto todoDto) {

        UserEntity userEntity = getCurrentUser();

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(todoDto.getId());
        todoEntity.setTask(todoDto.getTask());
        todoEntity.setCreateDate(new Date());
        todoEntity.setUser(userEntity);

        todoRepository.save(todoEntity);
    }

    public TodoDto findTodoById(long id) {

        Optional<TodoEntity> todoEntity = todoRepository.findById(id);
        if (todoEntity.isPresent()) {
            return new TodoDto(todoEntity.get().getId(), todoEntity.get().getTask(), todoEntity.get().getCreateDate().toString());
        }

        throw new IllegalArgumentException();
    }

    private UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserEntity userEntity = userRepository.findByUsername(username);

        return userEntity;
    }
}
