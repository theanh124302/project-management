package com.example.projectmanagement.service.impl;

import com.example.projectmanagement.dto.BarChartDTO;
import com.example.projectmanagement.dto.TaskDTO;
import com.example.projectmanagement.dto.ChartDTO;
import com.example.projectmanagement.dto.EventDTO;
import com.example.projectmanagement.entity.Task;
import com.example.projectmanagement.entity.User;
import com.example.projectmanagement.entity.UserTask;
import com.example.projectmanagement.enums.LifeCycle;
import com.example.projectmanagement.enums.TaskStatus;
import com.example.projectmanagement.repository.*;
import com.example.projectmanagement.service.EmailService;
import com.example.projectmanagement.service.EventService;
import com.example.projectmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskRepository userTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

    @Override
    public TaskDTO create(TaskDTO taskDTO) {
        Task task = taskRepository.save(convertToEntity(taskDTO));
        taskDTO.setId(task.getId());
        eventService.create(convertToEvent(taskDTO));
        return convertToDTO(task);
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskDTO.getId());
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            existingTask.setProjectId(taskDTO.getProjectId());
            existingTask.setApiId(taskDTO.getApiId());
            existingTask.setIssueId(taskDTO.getIssueId());
            existingTask.setReviewerId(taskDTO.getReviewerId());
            existingTask.setName(taskDTO.getName());
            existingTask.setDescription(taskDTO.getDescription());
            existingTask.setStatus(taskDTO.getStatus());
            existingTask.setLifeCycle(taskDTO.getLifeCycle());
            existingTask.setPriority(taskDTO.getPriority());
            existingTask.setType(taskDTO.getType());
            existingTask.setStartDate(taskDTO.getStartDate());
            existingTask.setEndDate(taskDTO.getEndDate());
            existingTask.setDueDate(taskDTO.getDueDate());
            existingTask.setCreatedBy(taskDTO.getCreatedBy());
            EventDTO eventDTO = convertToEvent(taskDTO);
            eventDTO.setId(eventService.findByTaskId(taskDTO.getId()).getId());
            eventService.update(eventDTO);
            return convertToDTO(taskRepository.save(existingTask));
        } else {
            return null;
        }
    }

    @Override
    public TaskDTO delete(TaskDTO taskDTO) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskDTO.getId());
        if (existingTaskOptional.isPresent()) {
            Task existingTask = existingTaskOptional.get();
            eventRepository.deleteByTaskId(taskDTO.getId());
            taskRepository.delete(existingTask);
            return convertToDTO(existingTask);
        } else {
            return null;
        }
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> existingTaskOptional = taskRepository.findById(id);
        return existingTaskOptional.map(this::convertToDTO).orElse(null);
    }

    @Override
    public TaskDTO assignTask(Long taskId, Long userId, Long assignerId) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingTaskOptional.isPresent() && existingUserOptional.isPresent()) {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("hoangtheanhc2lp@gmail.com");
            simpleMailMessage.setTo(existingUserOptional.get().getEmail());
            simpleMailMessage.setSubject("Task Assignment");
            String taskName = existingTaskOptional.get().getName();
            String projectName = projectRepository.findById(existingTaskOptional.get().getProjectId()).orElseThrow().getName();
            String assignerName = userRepository.findById(assignerId).orElseThrow().getUsername();
            LocalDateTime dueDate = existingTaskOptional.get().getDueDate().toLocalDateTime();
            String emailText = String.format(
                    "Dear Team Member,\n\n" +
                            "You have been assigned a new task. Please find the details below:\n\n" +
                            "Task: %s\n" +
                            "Project: %s\n" +
                            "Assigned by: %s\n" +
                            "Due date: %s\n\n" +
                            "Please make sure to complete the task by the due date.\n\n" +
                            "Best regards,\n" +
                            "Project Management Team",
                    taskName, projectName, assignerName, dueDate
            );
            simpleMailMessage.setText(emailText);
            emailService.sendEmail(simpleMailMessage);
            if(assignerId.equals(projectRepository.findById(existingTaskOptional.get().getProjectId()).orElseThrow().getLeaderId())) {
                UserTask userTask = userTaskRepository.findByUserIdAndTaskId(userId, taskId).orElse(null);
                if(userTask != null) {
                    userTask.setId(userTask.getId());
                    userTask.setUserId(userId);
                    userTask.setTaskId(taskId);
                    userTaskRepository.save(userTask);
                } else {
                    UserTask newUserTask = new UserTask();
                    newUserTask.setUserId(userId);
                    newUserTask.setTaskId(taskId);
                    userTaskRepository.save(newUserTask);
                }
            }
            return convertToDTO(existingTaskOptional.get());
        } else {
            return null;
        }
    }

    @Override
    public TaskDTO assignTaskByUsername(Long taskId, String username, Long assignerId) {
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        return assignTask(taskId, existingUserOptional.orElseThrow().getId(), assignerId);
    }

    @Override
    public TaskDTO unassignTask(Long taskId, Long userId, Long unAssignerId) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingTaskOptional.isPresent() && existingUserOptional.isPresent()) {
            if(unAssignerId.equals(projectRepository.findById(existingTaskOptional.get().getProjectId()).orElseThrow().getLeaderId())) {
                UserTask userTask = userTaskRepository.findByUserIdAndTaskId(userId, taskId).orElse(null);
                System.out.println("_________________________");
                System.out.println(taskId);
                System.out.println(userId);
                System.out.println(userTask.getId());
                System.out.println("_________________________");
                userTaskRepository.deleteById(userTask.getId());
            }
            return convertToDTO(existingTaskOptional.get());
        } else {
            return null;
        }
    }

    @Override
    public TaskDTO unassignTaskByUsername(Long taskId, String username, Long unAssignerId) {
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        return unassignTask(taskId, existingUserOptional.orElseThrow().getId(), unAssignerId);
    }

    @Override
    public TaskDTO addReviewer(Long taskId, Long userId, Long adderId) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingTaskOptional.isPresent() && existingUserOptional.isPresent() && adderId.equals(projectRepository.findById(existingTaskOptional.get().getProjectId()).orElseThrow().getLeaderId())) {
                existingTaskOptional.get().setReviewerId(userId);
                return convertToDTO(taskRepository.save(existingTaskOptional.get()));
            }
        return null;
    }

    @Override
    public TaskDTO addReviewerByUsername(Long taskId, String username, Long adderId) {
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        return addReviewer(taskId, existingUserOptional.orElseThrow().getId(), adderId);
    }

    @Override
    public TaskDTO removeReviewer(Long taskId, Long removerId) {
        Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
        if (existingTaskOptional.isPresent()) {
            existingTaskOptional.get().setReviewerId(null);
            return convertToDTO(taskRepository.save(existingTaskOptional.get()));
        }
        return null;
    }

    @Override
    public TaskDTO removeReviewerByUsername(Long taskId, String username) {
        Optional<User> existingUserOptional = userRepository.findByUsername(username);
        return removeReviewer(taskId, existingUserOptional.orElseThrow().getId());
    }

    @Override
    public List<TaskDTO> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> findByName(String name, Pageable pageable) {
        return taskRepository.findByName(name, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> findByProjectId(Long projectId, Pageable pageable) {
        return taskRepository.findByProjectId(projectId, pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> findByProjectIdAndStatus(Long projectId, String status, Pageable pageable) {
        return taskRepository.findByProjectIdAndStatus(projectId, TaskStatus.valueOf(status), pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> findByApiIdAndLifeCycle(Long apiId, String lifeCycle, Pageable pageable) {
        return taskRepository.findByApiIdAndLifeCycle(apiId, LifeCycle.valueOf(lifeCycle), pageable).getContent().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Long count() {
        return taskRepository.count();
    }

    @Override
    public Long countByProjectIdAndStatus(Long projectId, String status) {
        return taskRepository.countByProjectIdAndStatus(projectId, TaskStatus.valueOf(status));
    }

    @Override
    public Long countByProjectId(Long projectId) {
        return taskRepository.countByProjectId(projectId);
    }

    @Override
    public List<ChartDTO> countByProjectIdGroupByStatus(Long projectId) {
        List<ChartDTO> chartDTOList = new ArrayList<>();
        Long countTask = taskRepository.countByProjectId(projectId);
        for (TaskStatus taskStatus : TaskStatus.values()) {
            Long count = taskRepository.countByProjectIdAndStatus(projectId, taskStatus);
            chartDTOList.add(new ChartDTO(taskStatus.toString(), count, (double) count/countTask));
        }
        return chartDTOList;
    }

    @Override
    public List<BarChartDTO> countDueDateByDay(Long projectId) {
        List<BarChartDTO> chartDTOList = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            Long count = taskRepository.countByProjectIdAndDueDate(projectId, i);
            chartDTOList.add(new BarChartDTO(i, count));
        }
        return chartDTOList;
    }

    @Override
    public List<BarChartDTO> countDueDateByDayAndUserId(Long userId) {
        List<BarChartDTO> chartDTOList = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            Long count = taskRepository.countByUserIdAndDueDate(userId, i);
            chartDTOList.add(new BarChartDTO(i, count));
        }
        return chartDTOList;
    }

    @Override
    public List<BarChartDTO> countDueDateByMonth(Long projectId) {
        List<BarChartDTO> chartDTOList = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            Long count = taskRepository.countByProjectIdAndDueMonth(projectId, i);
            chartDTOList.add(new BarChartDTO(i, count));
        }
        return chartDTOList;
    }

    private TaskDTO convertToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setApiId(task.getApiId());
        taskDTO.setIssueId(task.getIssueId());
        taskDTO.setProjectId(task.getProjectId());
        taskDTO.setReviewerId(task.getReviewerId());
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setLifeCycle(task.getLifeCycle());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setType(task.getType());
        taskDTO.setStartDate(task.getStartDate());
        taskDTO.setEndDate(task.getEndDate());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setCreatedBy(task.getCreatedBy());

        return taskDTO;
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setApiId(taskDTO.getApiId());
        task.setIssueId(taskDTO.getIssueId());
        task.setProjectId(taskDTO.getProjectId());
        task.setReviewerId(taskDTO.getReviewerId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setLifeCycle(taskDTO.getLifeCycle());
        task.setPriority(taskDTO.getPriority());
        task.setType(taskDTO.getType());
        task.setStartDate(taskDTO.getStartDate());
        task.setEndDate(taskDTO.getEndDate());
        task.setDueDate(taskDTO.getDueDate());
        task.setCreatedBy(taskDTO.getCreatedBy());
        return task;
    }

    private EventDTO convertToEvent(TaskDTO taskDTO) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setProjectId(taskDTO.getProjectId());
        eventDTO.setTaskId(taskDTO.getId());
        eventDTO.setName(taskDTO.getName());
        eventDTO.setDescription(taskDTO.getDescription());
        eventDTO.setPriority(taskDTO.getPriority());
        eventDTO.setStartDate(taskDTO.getStartDate());
        eventDTO.setEndDate(taskDTO.getDueDate());
        eventDTO.setStatus(String.valueOf(taskDTO.getStatus()));
        return eventDTO;
    }
}
