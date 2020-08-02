package pl.michal.backend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;

@Document
@lombok.Data
public class Data implements Serializable {
    @Id
    @Null
    private String id;

    @Null
    private LocalDateTime timestamp;

    @NotBlank(message = "Manufacturer may not be blank")
    private String manufacturer;

    @NotBlank(message = "Model may not be blank")
    private String model;
}
