package com.target.service.service.upgrade;

import com.target.service.util.ResponseModel;

import java.util.List;

public interface UpgradeService {

    ResponseModel recommendUpgradation(Long projectId);
}
