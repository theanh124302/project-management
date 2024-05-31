package com.example.projectmanagement.repository;

import com.example.projectmanagement.entity.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Query("SELECT f FROM Folder f WHERE lower(f.name) LIKE lower(concat('%', :name, '%'))")
    Page<Folder> findByName(String name, Pageable pageable);
//    Page<Folder> findByParentId(Long parentId, Pageable pageable);
    Page<Folder> findByProjectId(Long projectId, Pageable pageable);
}
