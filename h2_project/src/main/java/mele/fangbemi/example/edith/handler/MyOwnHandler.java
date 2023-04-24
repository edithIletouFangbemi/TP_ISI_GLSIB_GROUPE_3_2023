package mele.fangbemi.example.edith.handler;
import mele.fangbemi.example.edith.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyOwnHandler {
    //private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @ExceptionHandler(CompteNotFoundException.class)
    public ResponseEntity<String> CompteNotFoundException(CompteNotFoundException exception){
        return  new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SoldeInsuffisantException.class)
    public ResponseEntity<String> SoldeInsuffisantException(SoldeInsuffisantException exception){
        return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> ClientNotFoundException(ClientNotFoundException exception){
        return  new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NbrCompteSuffisant.class)
    public ResponseEntity<String> NbrCompteSuffisant(NbrCompteSuffisant exception){
        return  new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<String> EmailException(EmailException exception){
        return  new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TelException.class)
    public ResponseEntity<String> TelException(TelException exception){
        return  new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
