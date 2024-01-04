package com.target.service.service.target.mapping;

import com.target.service.custom.exception.ResourceAlreadyInUseException;
import com.target.service.custom.exception.ResourceNotAvailableException;
import com.target.service.dao.target.mapping.TargetMappingDao;
import com.target.service.entity.target.TargetMapping;
import com.target.service.enums.component.WebComponent;
import com.target.service.request.target.TargetNodeRequest;
import com.target.service.util.ResponseMessage;
import com.target.service.util.ResponseModel;
import com.target.service.util.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TragetMappingServiceImpl implements TragetMappingService {

    @Autowired
    private TargetMappingDao targetMappingDao;

    @Override
    public Optional<TargetMapping> findByNode(String node) {
        return targetMappingDao.findByNode(node);
    }

    @Override
    public ResponseModel createTargetNode(TargetNodeRequest targetNodeRequest) {
        ResponseModel rs = null;
        try {
            TargetMapping targetMapping = targetMappingDao.findByNode(targetNodeRequest.getNode())
                    .orElseThrow(() -> new ResourceAlreadyInUseException("TargetNode", "Node", targetNodeRequest.getNode()));

            targetMapping.setNode(targetNodeRequest.getNode());
            targetMapping.setDescription(targetNodeRequest.getDescription());
            targetMapping.setTarget(targetNodeRequest.getTarget());
            targetMapping.setRecommendation(targetNodeRequest.getRecommendation());
            targetMapping.setAlternative(targetNodeRequest.getAlternative());
            targetMapping.setComponent(WebComponent.valueOf(targetNodeRequest.getComponent()));

            rs = ResponseStatus.success(ResponseMessage.TARGET_NODE_CREATED_SUCCESS,
                    targetMappingDao.save(targetMapping),
                    HttpStatus.OK);
        } catch (ResourceAlreadyInUseException ex) {
            rs = ResponseStatus.error(ResponseMessage.RESOURCE_IN_USE,
                    ex.getMessage(),
                    HttpStatus.CONFLICT);
        } catch (Exception ex) {
            rs = ResponseStatus.error(ResponseMessage.TARGET_NODE_CREATED_FAILED,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return rs;
    }

    @Override
    public ResponseModel updateNodeMapping(Long nodeMappingId, TargetNodeRequest targetNodeRequest) {
        ResponseModel rs = null;
        try {
            Optional<TargetMapping> targetMappingOpt = targetMappingDao.findByNode(targetNodeRequest.getNode());
            if (targetMappingOpt.isPresent() && !targetMappingOpt.get().getId().equals(nodeMappingId)) {
                throw new ResourceAlreadyInUseException("TargetNode", "Node", targetNodeRequest.getNode());
            } else {
                TargetMapping targetMapping = targetMappingDao.findById(nodeMappingId)
                        .orElseThrow(() -> new ResourceNotAvailableException("TargetNode", "ID", nodeMappingId));

                targetMapping.setNode(targetNodeRequest.getNode());
                targetMapping.setDescription(targetNodeRequest.getDescription());
                targetMapping.setTarget(targetNodeRequest.getTarget());
                targetMapping.setRecommendation(targetNodeRequest.getRecommendation());
                targetMapping.setAlternative(targetNodeRequest.getAlternative());
                targetMapping.setComponent(WebComponent.valueOf(targetNodeRequest.getComponent()));

                rs = ResponseStatus.success(ResponseMessage.TARGET_NODE_CREATED_SUCCESS,
                        targetMappingDao.save(targetMapping),
                        HttpStatus.OK);
            }
        } catch (ResourceAlreadyInUseException ex) {
            rs = ResponseStatus.error(ResponseMessage.RESOURCE_IN_USE,
                    ex.getMessage(),
                    HttpStatus.CONFLICT);
        } catch (Exception ex) {
            rs = ResponseStatus.error(ResponseMessage.TARGET_NODE_CREATED_FAILED,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return rs;
    }

    @Override
    public ResponseModel findNodeDetailsByNode(String node) {
        ResponseModel rs = null;
        try {
            TargetMapping targetMapping = targetMappingDao.findByNode(node)
                    .orElseThrow(() -> new ResourceNotAvailableException("TargetNode", "Node", node));

            rs = ResponseStatus.success(ResponseMessage.TARGET_NODE_FOUND_SUCCESS,
                    targetMapping,
                    HttpStatus.OK);
        } catch (ResourceNotAvailableException ex) {
            rs = ResponseStatus.error(ResponseMessage.RESOURCE_IN_USE,
                    ex.getMessage(),
                    HttpStatus.CONFLICT);
        } catch (Exception ex) {
            rs = ResponseStatus.error(ResponseMessage.TARGET_NODE_CREATED_FAILED,
                    ex.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return rs;
    }
}
