package pl.sda.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.sda.todoapp.model.TodoDto;
import pl.sda.todoapp.service.TodoService;

@Controller
public class MainController {

    @Autowired
    private TodoService todoService;

    // akcja wyświetla listę zadań do wykonania
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("tasks", todoService.findAll());

        return "index";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public String todo(Model model) {

        model.addAttribute("todo", new TodoDto());

        return "todo";
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public String todo(@PathVariable("id") long id, Model model) {

        model.addAttribute("todo", todoService.findTodoById(id));

        return "todo";
    }

    @RequestMapping(value = "/todo", method = RequestMethod.POST)
    public String todo(@ModelAttribute("todo") TodoDto todoDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "todo";
        }

        todoService.saveTodo(todoDto);

        return "redirect:/";
    }
}
