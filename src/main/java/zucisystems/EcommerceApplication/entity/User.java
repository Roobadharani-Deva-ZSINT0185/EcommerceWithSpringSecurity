package zucisystems.EcommerceApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userName;
    private String emailId;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "userRole",
    joinColumns = @JoinColumn(name="user_name"),
    inverseJoinColumns = @JoinColumn(name="role_name"))
    private Set<Role> role;
}
