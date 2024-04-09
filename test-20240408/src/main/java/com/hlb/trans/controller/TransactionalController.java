package com.hlb.trans.controller;

import com.hlb.trans.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @BelongsProject: test-20240408
 * @BelongsPackage: com.hlb.trans.controller
 * @CreateTime : 2024/4/8 14:46
 * @Description: 声明式操作事务
 * @Author: code_hlb
 */
@Slf4j
@RestController
@RequestMapping("/trans")
public class TransactionalController {
    @Autowired
    private UserInfoService userInfoService;

    @Transactional
    @RequestMapping("/r1")
    public String r1(String userName, String password) {
        // 注解 @Transactional，进入方法时自动开启事务，方法执行完后提交事务，当发生未处理的异常时进行事务的回滚
        // 该注解，也可以用来修饰方法 和 类，当修饰方法时，只有修饰public方法才会生效
        Integer result = userInfoService.register(userName, password);
        log.info("插入数据成功，res:{}", result);
        // 构造异常，由于没有处理异常，此时发生事务的回滚
        int excep = 10 / 0;
        return "注册成功..";
        // 我们一般会把事务的控制放在业务逻辑层，因为业务逻辑层中的一个业务功能，可能包含着多个数据访问的操作，
        // 因此就可以将多个数据访问操作控制在一个事务中
    }

    @Transactional
    @RequestMapping("/r2")
    public String r2(String userName, String password) {
        Integer result = userInfoService.register(userName, password);
        log.info("插入数据成功，res:{}", result);
        // 对异常进行处理，此时方法被认为是可执行的，因此事务被提交
        try {
            int excep = 10 / 0;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "注册成功..";
    }

    @Transactional
    @RequestMapping("/r3")
    public String r3(String userName, String password) throws IOException {
        Integer result = userInfoService.register(userName, password);
        log.info("插入数据成功，res:{}", result);
        if (true) {
            throw new IOException();   // 事务被提交
        }
        return "注册成功..";
    }

    @Transactional
    @RequestMapping("/r4")
    public String r4(String userName, String password) {
        // Spring的事务管理中，默认只在遇见 运行时异常RuntimeException 和 error 时才会进行回滚
        // RuntimeException:
        // 1、NullPointerException;
        // 2、IndexOutOfBoundsException;
        // 3、ArithmeticException：算数运算异常
        // 4、ClassCastException：类型转换异常
        // 5、IllegalArgumentException：非法参数异常
        // 6、NoSuchBeanDefinitionException：通常发生在尝试获取一个未被定义的Bean时抛出
        Integer result = userInfoService.register(userName, password);
        log.info("插入数据成功，res:{}", result);
        if (true) {
            throw new RuntimeException();   // 事务被回滚
        }
        return "注册成功..";
    }

    // rollbackFor = {Exception.class} : 指定回滚的异常类型
    @Transactional(rollbackFor = {Exception.class})
    @RequestMapping("/r5")
    public String r5(String userName, String password) throws IOException {
        Integer result = userInfoService.register(userName, password);
        log.info("插入数据成功，res:{}", result);
        if (true) {
            throw new IOException();   // 事务被回滚
        }
        return "注册成功..";
    }

    @Transactional
    @RequestMapping("/r7")
    public String r7(String userName, String password) {
        Integer result = userInfoService.register(userName, password);
        log.info("插入数据成功，res:{}", result);
        // 由于异常被捕获了后事务就会被默认提交
        // 因此如果发生异常，又想要事务进行回滚，有两种方法：
        // 1、继续把异常抛出去
        // 2、手动设置回滚
        try {
            int excep = 10 / 0;
        } catch (Exception e) {
            log.error("发生异常，e:{}", e.getMessage());
            throw e;  // 抛出异常，事务回滚
        }
        return "注册成功..";
    }

    /**
     * SQL标准定义了四种隔离级别：
     * 1、读未提交(READ UNCOMMITTED): 可以读取到其它事务未提交的内容，因此容易发生‘脏读’问题
     * 2、读提交(READ COMMITTED): 可以读取到已经提交事务的数据，解决了‘脏读’问题，
     * 但是发生了‘不可重复读问题’(由于当前事务对其它事务的增删改操作都能感知到，因此不同的时间相同的查询可能会得到不同的结果)
     * 3、可重复读(REPEATABLE READ): 是Mysql的默认事务隔离级别，事务不会读到其它事务对数据的修改(即使已提交)，也就可以保证每次查询的结果一致，
     * 解决了‘不可重复度’问题，但是由于可以感知到其它事务新插入的数据，因此也就发生了‘幻读’问题
     * 4、串行化(SERIALIZABLE): 序列化，事务最高隔离级别，强制事务排序，使其不会发生冲突，从而解决了脏读，不可重复读，幻读问题，但是执行效率最低
     * <p>
     * Spring 中事务隔离级别总共有5种:
     * 1、Isolation.DEFAULT: 默认以连接的数据库事务隔离级别为主
     * 2、Isolation.READ_UNCOMMITTED: 读未提交，对应SQL标准中的 READ_UNCOMMITTED
     * 3、Isolation.READ_COMMITTED: 读已提交
     * 4、Isolation.REPEATABLE_READ: 可重复读
     * 5、Isolation.SERIALIZABLE: 串行化
     * Spring 中事务隔离级别可以通过 @Transactional 中的 isolation 属性进⾏设置
     */
    @Transactional(isolation = Isolation.DEFAULT)   // 指定默认的事务隔离级别
    @RequestMapping("/r8")
    public String r8(String userName, String password) {
        Integer result = userInfoService.register(userName, password);
        log.info("插入数据成功，res:{}", result);
        try {
            int excep = 10 / 0;
        } catch (Exception e) {
            log.error("发生异常，e:{}", e.getMessage());
            // 将当前事务设置为回滚状态
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "注册成功..";
    }
}
