package com.target.service.service.upgrade;

import com.target.service.dao.project.ProjectDao;
import com.target.service.dao.technologies.TechnologiesDao;
import com.target.service.entity.target.TargetMapping;
import com.target.service.service.target.mapping.TragetMappingService;
import com.target.service.util.ResponseMessage;
import com.target.service.util.ResponseModel;
import com.target.service.util.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class UpgradeServiceImpl implements UpgradeService {

    @Autowired
    private TechnologiesDao technologiesDao;

    @Autowired
    private TragetMappingService tragetMappingService;

    @Override
    public ResponseModel recommendUpgradation(Long projectId) {
        ResponseModel rs = null;
        try {
            rs = ResponseStatus.success(ResponseMessage.RECOMMENDATION_LOADED_SUCCESS,
                    technologiesDao.findByProjectId(projectId),
                    HttpStatus.OK);
        } catch (Exception ex) {
            rs = ResponseStatus.error(ResponseMessage.SOMETHING_WENTS_WRONG,
                    ex.getMessage(),
                    HttpStatus.OK);
        }
        return rs;
    }
}
