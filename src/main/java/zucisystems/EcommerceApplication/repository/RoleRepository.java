package zucisystems.EcommerceApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zucisystems.EcommerceApplication.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    public Role findByRoleName(String roleName);
}
