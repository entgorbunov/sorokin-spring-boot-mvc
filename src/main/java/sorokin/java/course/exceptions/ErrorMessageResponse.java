package sorokin.java.course.exceptions;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorMessageResponse {
    private String message;
    private String details;
}
