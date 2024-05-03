package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace,Long>{
}
