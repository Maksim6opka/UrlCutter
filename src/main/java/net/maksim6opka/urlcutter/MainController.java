package net.maksim6opka.urlcutter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public ApiResponse mainController() {
        return new ApiResponse("success", "Main controller initialized");
    }

    public record ApiResponse(String status, String message) {}

}
