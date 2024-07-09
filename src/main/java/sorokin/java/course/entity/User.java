package sorokin.java.course.entity;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age should not be less than 1")
    @Max(value = 100, message = "Age should not be greater than 100")
    private Integer age;

    private List<Pet> pets;


}
