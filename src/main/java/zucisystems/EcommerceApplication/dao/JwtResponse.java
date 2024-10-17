package zucisystems.EcommerceApplication.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zucisystems.EcommerceApplication.entity.User;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private User user;
    private String jwtToken;

}
