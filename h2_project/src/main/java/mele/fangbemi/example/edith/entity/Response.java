package mele.fangbemi.example.edith.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
@SuperBuilder
public class Response {
    protected LocalDateTime timestamp;
    protected String message;
    protected HttpStatus status;
    protected int statusCode;
    protected Map<?,?> data;
}
