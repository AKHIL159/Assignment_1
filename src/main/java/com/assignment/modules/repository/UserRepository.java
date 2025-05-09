package com.assignment.modules.repository;

import com.assignment.modules.dto.UserDTO;
import com.assignment.modules.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{}", fields = "{ 'name' : 1, 'email' : 1 }")
    List<UserDTO> findAllProjectedBy();

    @Query(value = "{email: ?0}", fields = "{name: 1, email: 1}")
    UserDTO findProjectedByEmail(String email);

    User findByEmail(String email);
    void deleteByEmail(String email);

//    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
