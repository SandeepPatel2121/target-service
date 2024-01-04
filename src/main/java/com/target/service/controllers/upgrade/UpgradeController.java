package com.target.service.controllers.upgrade;

import com.target.service.service.upgrade.UpgradeService;
import com.target.service.util.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/upgrade")
public class UpgradeController {

    @Autowired
    private UpgradeService upgradeService;

    @RequestMapping(value = "/recommendation/{projectId}", method = RequestMethod.GET)
    private ResponseEntity<ResponseModel> recommendUpgradation(@PathVariable(value = "projectId") Long projectId) {
        ResponseModel rs = upgradeService.recommendUpgradation(projectId);
        return new ResponseEntity<>(rs, rs.getHttpStatus());
    }

}
