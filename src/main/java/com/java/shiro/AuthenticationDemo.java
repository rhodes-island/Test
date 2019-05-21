package com.java.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import java.util.logging.Logger;

/**
 * @author 李畅
 * @date 2019/5/20
 */
public class AuthenticationDemo {
    public static void main(String[] args) {
        //1、创建SecurityManager工厂 读取配置文件
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、SecurityManager工厂获取SecurityManager的实例
        SecurityManager securityManager = factory.getInstance();
        //3、将SecurityManager对象 设置到运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //4、通过SecurityUtil获取主体Subject
        Subject subject = SecurityUtils.getSubject();
        //5、假如登录的用户名zhangsan和111,这个地方的zhangsan和111表示用户登录时输入的信息
        //而shiro.ini文件中信息 相当于数据库中存放的用户信息
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "1111");
        //6、进行用户身份验证
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        //7、通过subject来判断用户是否通过验证
        if (subject.isAuthenticated()) {
            System.out.println("用户登录成功");
        } else {
            System.out.println("用户名或密码不正确");
        }

    }
}
