package com.usmobile.usermanagment.repository;

import com.usmobile.usermanagment.DAO.UserDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserDAO, String> {
}
