package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.UserDTO;
import com.example.projectmanagement.dto.UserInProjectDTO;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.entity.UserProject;
import com.example.projectmanagement.entity.UserTask;
import com.example.projectmanagement.repository.UserProjectRepository;
import com.example.projectmanagement.repository.UserRepository;
import com.example.projectmanagement.repository.UserTaskRepository;
import com.example.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;

    @Override
    public UserDTO update(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAge(userDTO.getAge());
        existingUser.setName(userDTO.getName());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setAvatar(userDTO.getAvatar());
        return convertToDTO(userRepository.save(existingUser));
    }

    @Override
    public UserDTO delete(UserDTO userDTO) {
        User existingUser = userRepository.findById(userDTO.getId()).orElse(null);
        if (existingUser == null) {
            return null;
        }
        userRepository.delete(existingUser);
        return convertToDTO(existingUser);
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent().stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<UserDTO> findByName(String name, Pageable pageable) {
        return userRepository.findByName(name, pageable).getContent().stream().map(this::convertToDTO).toList();
    }

    @Override
    public List<UserInProjectDTO> findByProjectId(Long projectId, Pageable pageable) {
        List< UserProject > userProjects = userProjectRepository.findByProjectId(projectId);
        return userProjects.stream().map(userProject -> {
            User user = userRepository.findById(userProject.getUserId()).orElse(null);
            if (user == null) {
                return null;
            }
            else {
                return new UserInProjectDTO(user.getId(), user.getName(), user.getPhoneNumber(), user.getAge(), user.getEmail(), user.getUsername(), user.getAvatar(), userProject.getRole().toString());
            }
        }).toList();
    }

    @Override
    public List<UserDTO> findByTaskId(Long taskId, Pageable pageable) {
        List<UserTask> userTasks = userTaskRepository.findByTaskId(taskId);
        return userTasks.stream().map(userTask -> {
            User user = userRepository.findById(userTask.getUserId()).orElse(null);
            if (user == null) {
                return null;
            }
            else {
                return convertToDTO(user);
            }
        }).toList();
    }


    @Override
    public UserDTO findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public UserDTO findByPhoneNumber(String phoneNumber) {
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        User user = userOptional.orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public UserDTO findByEmailOrUsername(String email, String username) {
        Optional<User> userOptional = userRepository.findByEmailOrUsername(email, username);
        User user = userOptional.orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setName(user.getName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAvatar(user.getAvatar());
        return userDTO;
    }
}

