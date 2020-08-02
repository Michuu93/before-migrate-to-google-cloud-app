package pl.michal.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DataController {

    private final DataService dataService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Data save(@RequestBody @Valid Data data) {
        log.debug("> save [data={}]", data);
        Data savedData = dataService.saveInQueue(data);
        log.debug("< save [savedData={}]", savedData);
        return savedData;
    }

    @GetMapping("/{uuid}")
    public Data getById(@PathVariable String uuid) {
        log.debug("> getById [uuid={}]", uuid);
        Data dataFromDb = dataService.findByUuid(uuid);
        log.debug("< getById [dataFromDb={}]", dataFromDb);
        return dataFromDb;
    }

    @GetMapping
    public List<Data> getAll() {
        log.debug("> getAll");
        List<Data> dataFromDb = dataService.findAll();
        log.debug("< getAll [resultSize={}]", dataFromDb.size());
        return dataFromDb;
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteAll() {
        log.debug("> deleteAll");
        dataService.deleteAll();
        log.debug("< deleteAll");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
