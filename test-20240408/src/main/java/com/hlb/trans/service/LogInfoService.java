package com.hlb.trans.service;

import com.hlb.trans.mapper.LogInfoMapper;
import com.hlb.trans.model.LogInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: com.hlb.trans.service
 * @CreateTime : 2024/4/9 9:31
 * @Description: LogInfoService
 * @Author: code_hlb
 */
@Slf4j
@Service
public class LogInfoService {
    @Autowired
    private LogInfoMapper logInfoMapper;

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional(propagation = Propagation.NEVER)
    @Transactional(propagation = Propagation.NESTED)
    public Integer insert(LogInfo logInfo) {
//        int sum = 10/0;
        int res = logInfoMapper.insert(logInfo);
        try {
            // 构造异常
            int sum = 10 / 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            // 设置当前事务回滚, 当前事务传播级别为NESTED时，可以只回滚当前事务，
            // 但如果是REQUIRED，就会发 生全部回滚，因为它们属于同一个事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return res;
    }
}
