package zucisystems.EcommerceApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zucisystems.EcommerceApplication.entity.User;

public interface UserRepository extends JpaRepository<User,String> {
}
