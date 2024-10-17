package zucisystems.EcommerceApplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role", indexes = @Index(columnList = "role_name"))
public class Role {
    @Id
    private String roleName;
    private String roleDescription;
    @ManyToMany(mappedBy = "role")
    Set<User> userSet;

}
