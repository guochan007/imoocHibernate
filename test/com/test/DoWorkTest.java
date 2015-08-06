package com.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pojo.Students;


public class DoWorkTest {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init() {
//		创建配置对象
		Configuration configuration = new Configuration().configure();
//		创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();
//		创建会话工厂对象
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);//error
//		查google发现junit4和jdk6也没多大关系   我自己也实际切换jdk进行了验证
		
//		会话对象
		session=sessionFactory.openSession();
//		开启事务
//		transaction=session.beginTransaction();
//		不用事务 改用dowork
	}

	@After
	public void destory() {
//		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	@Test
	public void testSaveStudent(){
		Students students=new Students(1, "你哥", "男", new Date(), "北京");
		session.doWork(new Work(){

			public void execute(Connection connection) throws SQLException {
//				自动提交
				connection.setAutoCommit(true);
			}
			
		});
		session.save(students);
		session.flush();
	}
}
