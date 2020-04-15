import org.springframework.web.bind.annotation.*

@RestController
class OportunidadeController {

    @GetMapping("/hello")
    String index() {
        return "Hello World"
    }

}