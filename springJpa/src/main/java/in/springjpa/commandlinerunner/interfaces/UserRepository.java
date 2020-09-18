package in.springjpa.commandlinerunner.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import in.springjpa.entity.User;

public interface UserRepository extends JpaRepository<User,Long >{

}
