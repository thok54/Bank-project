package ejercicio.banco.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping
    public String sayHello() {
        return "Hello World!";
    }

    @GetMapping("/1")
    public String sayHello1() {
        return "Hello World - 1!";
    }

    @GetMapping("/2")
    public String sayHello2() {
        return "Hello World - 2!";
    }
}
