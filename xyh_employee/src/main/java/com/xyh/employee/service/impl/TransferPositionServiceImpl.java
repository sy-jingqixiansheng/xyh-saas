package com.xyh.employee.service.impl;

import com.xyh.employee.dao.EmployeeTransferPositionMapper;
import com.xyh.employee.service.TransferPositionService;
import cpm.xyh.entity.employee.EmployeeTransferPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author SQ
 * @Date 2020/8/17 0017 9:00
 * @Version 1.0
 */
@Service
public class TransferPositionServiceImpl implements TransferPositionService {

    @Autowired
    private EmployeeTransferPositionMapper transferPositionMapper;
    @Override
    public void save(EmployeeTransferPosition transferPosition) {
        transferPositionMapper.insert(transferPosition);
    }
}
