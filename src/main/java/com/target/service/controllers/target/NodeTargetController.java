package com.target.service.controllers.target;

import com.target.service.request.project.ProjectRequest;
import com.target.service.request.target.TargetNodeRequest;
import com.target.service.service.target.mapping.TragetMappingService;
import com.target.service.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NodeTargetController {

    @Autowired
    private TragetMappingService targetMappingService;

    @RequestMapping(value = "/node", method = RequestMethod.POST)
    private ResponseEntity<ResponseModel> createTargetMapping(@RequestBody TargetNodeRequest targetNodeRequest) {
        ResponseModel rs = targetMappingService.createTargetNode(targetNodeRequest);
        return new ResponseEntity<>(rs, rs.getHttpStatus());
    }

    @RequestMapping(value = "/nodes/{id}", method = RequestMethod.PUT)
    private ResponseEntity<ResponseModel> updateNodeMapping(@PathVariable(value = "id") Long nodeMappingId, @RequestBody TargetNodeRequest targetNodeRequest) {
        ResponseModel rs = targetMappingService.updateNodeMapping(nodeMappingId, targetNodeRequest);
        return new ResponseEntity<>(rs, rs.getHttpStatus());
    }

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    private ResponseEntity<ResponseModel> findById(@RequestParam(value = "node") String node) {
        ResponseModel rs = targetMappingService.findNodeDetailsByNode(node);
        return new ResponseEntity<>(rs, rs.getHttpStatus());
    }

}
