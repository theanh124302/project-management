package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectRepository extends JpaRepository<UserProject,Long> {

}
