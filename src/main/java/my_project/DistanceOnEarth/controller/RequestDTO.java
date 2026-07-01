package my_project.DistanceOnEarth.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestDTO {
    @NotBlank(message = "Адрес не может быть пустым")
    @Size(max = 200, message = "Адрес не может быть длиннее 200 символов")
    private String address;
}
