package cs2020.experiment04.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEmailDTO {
    private String email;
    private String verCode;
}
