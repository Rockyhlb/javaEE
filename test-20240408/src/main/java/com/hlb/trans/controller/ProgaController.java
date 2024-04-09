package com.hlb.trans.controller;

import com.hlb.trans.model.LogInfo;
import com.hlb.trans.service.LogInfoService;
import com.hlb.trans.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: com.hlb.trans.controller
 * @CreateTime : 2024/4/9 9:33
 * @Description: 事务传播机制
 * @Author: code_hlb
 */
@Slf4j
@RestController
@RequestMapping("/proga")  // propagate：传播
public class ProgaController {
    /**
     * 事务传播机制：多个事务方法存在调用关系时，事务之间的传播关系
     * 事务隔离级别解决的是 多个事务同时调用一个数据库 的问题
     * 事务传播机制解决的是 一个事务在多个节点(方法)中传播 的问题
     * `@Transactional 通过 propagation 属性来指定传播机制.
     * <p>
     * Spring 支持 7 种事务传播机制：
     * 1、Propagation.REQUIRED: 默认事务传播级别,如果当前存在事务,则加入该事务. 如果当前没有事务, 则创建⼀个新的事务.
     * 2、Propagation.SUPPORTS: 如果当前存在事务, 则加入该事务. 如果当前没有事务, 则以非事务的方法继续运⾏.
     * 3、Propagation.MANDATORY: 强制性. 如果当前存在事务, 则加入该事务. 如果当前没有事务, 则抛出异常.
     * 4、Propagation.REQUIRES_NEW: 创建⼀个新的事务. 如果当前存在事务, 则把当前事务挂起，且新开启的事务相互独⽴, 互不干扰.
     * 5、Propagation.NOT_SUPPORTED: 以非事务方式运⾏, 如果当前存在事务, 则把当前事务挂起(不用)
     * 6、Propagation.NEVER: 以非事务方式运行, 如果当前存在事务, 则抛出异常.
     * 7、Propagation.NESTED : 如果当前存在事务, 则创建一个事务作为当前事务的嵌套事务来运⾏.
     * 如果当前没有事务, 则该取值等价于 PROPAGATION_REQUIRED
     */
    @Autowired
    private LogInfoService logInfoService;
    @Autowired
    private UserInfoService userInfoService;

    /**
     * Propagation.REQUIRED：两个事务全部成功则成功，一个失败则全部失败
     * Propagation.REQUIRES_NEW：两个事务成功则 全部成功，失败的回滚，事务之间不互相影响
     * Propagation.NEVER：只要发现当前有事务，则抛出异常
     * Propagation.NESTED：两个事务全部成功则成功，一个失败则全部失败
     * <p>
     * REQUIRED 和 NESTED 区别：
     * NESTED 可以实现部分回滚(由于是嵌套事务，因此就可以达到回滚子事务，而不影响整体事务的目的)
     * REQUIRED 只能整体回滚(由于是加入事务,因此所有事务都属于同一个事务，一个回滚则全部回滚)
     */
    @RequestMapping("/p1")
//    @Transactional
    @Transactional(propagation = Propagation.REQUIRED)
    public String p1(String userName, String password) {
        userInfoService.register("wangwu", "1234");
        LogInfo logInfo = new LogInfo();
        logInfo.setUserName(userName);
        logInfo.setOp("用户注册");
        logInfoService.insert(logInfo);
        return "用户注册成功";
    }
}
