package cloud.dmytrominochkin.mvc.controller;

import cloud.dmytrominochkin.mvc.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private BookService bookService;

    @Autowired
    public HomeController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/", "/home"})
    public String home(){
        return "home";
    }

    @GetMapping("/all")
    public String home(Model model) {

        model.addAttribute("books", bookService.getAllBooks());

        return "all";
    }

    @GetMapping("/non")
    public String absent(Model model){

        model.addAttribute("books", bookService.getAllChecked());

        return "absent";
    }
}
