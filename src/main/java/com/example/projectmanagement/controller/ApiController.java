package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ApiDTO;
import com.example.projectmanagement.service.ApiService;
import com.example.projectmanagement.template.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @PostMapping("/create")
    public ResponseEntity<ResponseTemplate<ApiDTO>> createApi(@RequestBody ApiDTO apiDTO) {
        ApiDTO createdApi = apiService.create(apiDTO);
        if (createdApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.CREATED)
                    .message("Api created successfully")
                    .data(createdApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("Failed to create api")
                    .build());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateApi(@RequestBody ApiDTO apiDTO) {
        ApiDTO updatedApi = apiService.update(apiDTO);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateSourceCode")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateSourceCode(@RequestParam Long id,
                                                                    @RequestParam String sourceCode) {
        ApiDTO updatedApi = apiService.updateSourceCode(id, sourceCode);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateNameAndDescriptionAndStatus")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateNameAndDescriptionAndStatus(@RequestParam Long id,
                                                                                      @RequestParam String name,
                                                                                      @RequestParam String description,
                                                                                      @RequestParam String status) {
        ApiDTO updatedApi = apiService.updateNameAndDescriptionAndStatus(id, name, description, status);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateUserRequirementsAndTechnicalRequirementsAndBusinessProcess")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateUserRequirementsAndTechnicalRequirementsAndBusinessProcess(@RequestParam Long id,
                                                                                                                  @RequestParam String userRequirements,
                                                                                                                  @RequestParam String technicalRequirements,
                                                                                                                  @RequestParam String businessProcess) {
        ApiDTO updatedApi = apiService.updateUserRequirementsAndTechnicalRequirementsAndBusinessProcess(id, userRequirements, technicalRequirements, businessProcess);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateNameAndDescriptionAndStatusAndUserRequirementsAndTechnicalRequirementsAndBusinessProcess")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateNameAndDescriptionAndStatusAndUserRequirementsAndTechnicalRequirementsAndBusinessProcess(@RequestBody ApiDTO apiDTO) {
        ApiDTO updatedApi = apiService.updateNameAndDescriptionAndStatusAndUserRequirementsAndTechnicalRequirementsAndBusinessProcess(apiDTO.getId(), apiDTO.getName(), apiDTO.getDescription(), apiDTO.getStatus(), apiDTO.getUserRequirements(), apiDTO.getTechnicalRequirements(), apiDTO.getBusinessProcess());
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateUrlAndMethod")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateUrlAndMethod(@RequestParam Long id,
                                                                       @RequestParam String url,
                                                                       @RequestParam String method) {
        ApiDTO updatedApi = apiService.updateUrlAndMethod(id, url, method);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateParametersAndBodyAndTokenAndHeader")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateParametersAndBodyAndToken(@RequestParam Long id,
                                                                                    @RequestParam(required = false) String parameters,
                                                                                    @RequestParam(required = false) String body,
                                                                                    @RequestParam(required = false) String token,
                                                                                    @RequestParam(required = false) String header) {
        ApiDTO updatedApi = apiService.updateParametersAndBodyAndTokenAndHeader(id, parameters, body, token, header);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateUseCaseDiagramAndSequenceDiagramAndActivityDiagramAndClassDiagram")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateUseCaseDiagramAndSequenceDiagramAndActivityDiagramAndClassDiagram(@RequestParam Long id,
                                                                                                                               @RequestParam(required = false) String useCaseDiagram,
                                                                                                                               @RequestParam(required = false) String sequenceDiagram,
                                                                                                                               @RequestParam(required = false) String activityDiagram,
                                                                                                                               @RequestParam(required = false) String classDiagram) {
        ApiDTO updatedApi = apiService.updateUseCaseDiagramAndSequenceDiagramAndActivityDiagramAndClassDiagram(id, useCaseDiagram, sequenceDiagram, activityDiagram, classDiagram);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateUseCaseDiagramAndSequenceDiagramAndActivityDiagramAndClassDiagramAndSolutionDocument")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateUseCaseDiagramAndSequenceDiagramAndActivityDiagramAndClassDiagramAndSolutionDocument(@RequestParam Long id,
                                                                                                                                             @RequestParam(required = false) String useCaseDiagram,
                                                                                                                                             @RequestParam(required = false) String sequenceDiagram,
                                                                                                                                             @RequestParam(required = false) String activityDiagram,
                                                                                                                                             @RequestParam(required = false) String classDiagram,
                                                                                                                                             @RequestParam(required = false) String solutionDocument) {
        ApiDTO updatedApi = apiService.updateUseCaseDiagramAndSequenceDiagramAndActivityDiagramAndClassDiagramAndSolutionDocument(id, useCaseDiagram, sequenceDiagram, activityDiagram, classDiagram, solutionDocument);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateTechnicalRequirementsAndBusinessProcessAndUserRequirements")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateTechnicalRequirementsAndBusinessProcessAndUserRequirements(@RequestParam Long id,
                                                                                                                     @RequestParam(required = false) String technicalRequirements,
                                                                                                                     @RequestParam(required = false) String businessProcess,
                                                                                                                     @RequestParam(required = false) String userRequirements) {
        ApiDTO updatedApi = apiService.updateTechnicalRequirementsAndBusinessProcessAndUserRequirements(id, technicalRequirements, businessProcess, userRequirements);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateInstallationGuide")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateInstallationGuide(@RequestParam Long id,
                                                                            @RequestParam(required = false) String installationGuide) {
        ApiDTO updatedApi = apiService.updateInstallationGuide(id, installationGuide);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateTestCasesAndTestScenariosAndTestScriptsAndInstallationGuide")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateTestCasesAndTestScenariosAndTestScriptsAndInstallationGuide(@RequestParam Long id,
                                                                                                                           @RequestParam(required = false) String testCases,
                                                                                                                           @RequestParam(required = false) String testScenarios,
                                                                                                                           @RequestParam(required = false) String testScripts,
                                                                                                                           @RequestParam(required = false) String installationGuide) {
        ApiDTO updatedApi = apiService.updateTestCasesAndTestScenariosAndTestScriptsAndInstallationGuide(id, testCases, testScenarios, testScripts, installationGuide);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateEnvironmentId")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateEnvironmentId(@RequestParam Long id,
                                                                        @RequestParam Long environmentId) {
        ApiDTO updatedApi = apiService.updateEnvironmentId(id, environmentId);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @PostMapping("/updateSolutionDocument")
    public ResponseEntity<ResponseTemplate<ApiDTO>> updateSolutionDocument(@RequestParam Long id,
                                                                           @RequestParam(required = false) String solutionDocument) {
        ApiDTO updatedApi = apiService.updateSolutionDocument(id, solutionDocument);
        if (updatedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api updated successfully")
                    .data(updatedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseTemplate<ApiDTO>> deleteApi(@RequestBody ApiDTO apiDTO) {
        ApiDTO deletedApi = apiService.delete(apiDTO);
        if (deletedApi != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api deleted successfully")
                    .data(deletedApi)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseTemplate<List<ApiDTO>>> getAllApis(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ApiDTO> apis = apiService.getAllApis(pageable);

        long totalItems = apiService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        return ResponseEntity.ok(ResponseTemplate.<List<ApiDTO>>builder()
                .status(HttpStatus.OK)
                .message("Apis found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(apis)
                .build());
    }

    @GetMapping("/findByName")
    public ResponseEntity<ResponseTemplate<List<ApiDTO>>> findApisByName(@RequestParam String name,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ApiDTO> apis = apiService.findByName(name, pageable);
        long totalItems = apiService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<ApiDTO>>builder()
                .status(HttpStatus.OK)
                .message("Apis found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(apis)
                .build());
    }

    @GetMapping("/findByProjectId")
    public ResponseEntity<ResponseTemplate<List<ApiDTO>>> findApisByProjectId(@RequestParam Long projectId,
                                                                              @RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ApiDTO> apis = apiService.findByProjectId(projectId, pageable);
        long totalItems = apiService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<ApiDTO>>builder()
                .status(HttpStatus.OK)
                .message("Apis found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(apis)
                .build());
    }

    @GetMapping("/findByFolderId")
    public ResponseEntity<ResponseTemplate<List<ApiDTO>>> findApisByFolderId(@RequestParam Long folderId,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ApiDTO> apis = apiService.findByFolderId(folderId, pageable);
        long totalItems = apiService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<ApiDTO>>builder()
                .status(HttpStatus.OK)
                .message("Apis found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(apis)
                .build());
    }

    @GetMapping("/findByProjectIdAndStatus")
    public ResponseEntity<ResponseTemplate<List<ApiDTO>>> findApisByProjectIdAndStatus(@RequestParam Long projectId,
                                                                                       @RequestParam String status,
                                                                                       @RequestParam(defaultValue = "0") int page,
                                                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<ApiDTO> apis = apiService.findByProjectIdAndStatus(projectId, status, pageable);
        long totalItems = apiService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        return ResponseEntity.ok(ResponseTemplate.<List<ApiDTO>>builder()
                .status(HttpStatus.OK)
                .message("Apis found successfully")
                .page(page)
                .size(size)
                .totalItems(totalItems)
                .totalPages(totalPages)
                .data(apis)
                .build());
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseTemplate<ApiDTO>> findApiById(@RequestParam Long id) {
        ApiDTO api = apiService.findById(id);
        if (api != null) {
            return ResponseEntity.ok(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.OK)
                    .message("Api found successfully")
                    .data(api)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseTemplate.<ApiDTO>builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Api not found")
                    .build());
        }
    }

    @GetMapping("/countByProjectId")
    public ResponseEntity<ResponseTemplate<Long>> countApisByProjectId(@RequestParam Long projectId) {
        Long count = apiService.countByProjectId(projectId);
        return ResponseEntity.ok(ResponseTemplate.<Long>builder()
                .status(HttpStatus.OK)
                .message("Counted successfully")
                .data(count)
                .build());
    }

    @GetMapping("/count")
    public ResponseEntity<ResponseTemplate<Long>> countApis() {
        Long count = apiService.count();
        return ResponseEntity.ok(ResponseTemplate.<Long>builder()
                .status(HttpStatus.OK)
                .message("Counted successfully")
                .data(count)
                .build());
    }
}
