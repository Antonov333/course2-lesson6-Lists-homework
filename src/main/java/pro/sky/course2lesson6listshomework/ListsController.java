package pro.sky.course2lesson6listshomework;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListsController {

    ListsService listsService = new ListsService();

    @GetMapping()
    public String welcome() {
        return listsService.welcome();
    }

}
