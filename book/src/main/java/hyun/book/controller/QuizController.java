package hyun.book.controller;

//import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuizController {
    @GetMapping("/quiz")
    public ResponseEntity<String> quiz(@RequestParam("code") int code) {
        return switch (code) {
            case 1 -> ResponseEntity.created(null).body("Created!");
            case 2 -> ResponseEntity.badRequest().body("Bad Request!");
            default -> ResponseEntity.ok().body("Ok!");
        };
    }

    @PostMapping("/quiz")
    public ResponseEntity<String> quiz2(@RequestBody Code code){
        if (code.value() == 1) {
            return ResponseEntity.status(403).body("Forbidden!");
        }
        return ResponseEntity.ok().body("Ok!");
    }
}

record Code(int value){}