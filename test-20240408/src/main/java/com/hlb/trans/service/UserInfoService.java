package com.hlb.trans.service;

import com.hlb.trans.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: com.hlb.trans.service
 * @CreateTime : 2024/4/8 13:51
 * @Description: UserInfoService
 * @Author: code_hlb
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional(propagation = Propagation.NEVER)
    @Transactional(propagation = Propagation.NESTED)
    public Integer register(String userName, String password) {
        return userInfoMapper.insertUser(userName, password);
    }
}
