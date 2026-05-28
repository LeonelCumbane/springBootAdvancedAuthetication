package netcusoft.com.aula.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ResponseEntity<Object> handleException(RecursoNaoEncontrado ex ) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("timestamp", LocalDateTime.now().toString());
        map.put("message", ex.getMessage());
        map.put("Error", "Recurso nao encontrado!");
        map.put("status:", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptionInternalServer(Exception e) {
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("timestamp: ", LocalDateTime.now().toString());
        map.put("message: ", e.getMessage());
        map.put("status: ", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("error:", "Erro interno do servidor!");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
    }
}
