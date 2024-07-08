package sorokin.java.course.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@EqualsAndHashCode
public class PetDto {
    @NotBlank(message = "Pet name is required")
    @Size(min = 2, max = 50, message = "Pet name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be a positive number")
    private Long userId;
}
