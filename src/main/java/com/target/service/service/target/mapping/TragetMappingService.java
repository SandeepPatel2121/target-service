package com.target.service.service.target.mapping;

import com.target.service.entity.target.TargetMapping;
import com.target.service.request.target.TargetNodeRequest;
import com.target.service.util.ResponseModel;

import java.util.Optional;

public interface TragetMappingService {

    Optional<TargetMapping> findByNode(String str);

    ResponseModel createTargetNode(TargetNodeRequest targetNodeRequest);

    ResponseModel updateNodeMapping(Long nodeMappingId, TargetNodeRequest targetNodeRequest);

    ResponseModel findNodeDetailsByNode(String node);
}
