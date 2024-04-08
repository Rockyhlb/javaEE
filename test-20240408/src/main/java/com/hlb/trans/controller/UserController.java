package com.hlb.trans.controller;

import com.hlb.trans.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: com.hlb.trans.controller
 * @CreateTime : 2024/4/8 13:51
 * @Description: 手动式操作事务
 * @Author: code_hlb
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    // DataSourceTransactionManager：事务管理器，用于获取事务(开启事务)，提交事务，回滚事务
    private DataSourceTransactionManager dataSourceTransactionManager;
    @Autowired
    // TransactionDefinition：事务属性，获取事务时需要将TransactionDefinition 传递进去，从而获得一个事务
    private TransactionDefinition transactionDefinition;

    @RequestMapping("/register")
    public String register(String userName, String password) {
        /**
         * 事务: 一组操作的集合，要么一次全部成功，要么一次全部失败
         * 在数据库中，事务的操作主要有三步：
         * 1、开启事务: start transaction (在开始执行一组操作前，开启事务)
         * 2、提交事务: commit (这组操作全部成功，提交事务)
         * 3、回滚事务: rollback (这组操作全部失败，回滚事务)
         * <p>
         * Spring 也对事务进行了实现，Spring中的事务操作主要分为两类：
         * 1、编程式(手动式)事务: 传统方式
         * 2、声明式(注解式)事务: 推荐方式
         */
        // 获取事务
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        Integer result = userInfoService.register(userName, password);
        log.info("插入数据成功，res:{}", result);
        // 回滚事务
//        dataSourceTransactionManager.rollback(transaction);
        // 提交事务
        dataSourceTransactionManager.commit(transaction);
        return "注册成功..";
    }
}
