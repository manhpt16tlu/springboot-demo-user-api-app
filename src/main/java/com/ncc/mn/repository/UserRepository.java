package com.ncc.mn.repository;

import com.ncc.mn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findByUserId(String userId);
    @Query("select \n" +
            "case \n" +
            "    when count(u)>5 then true\n" +
            "    else false\n" +
            "end as test_case\n" +
            "from User u")
    Boolean checkNumberOfRecord();



}
