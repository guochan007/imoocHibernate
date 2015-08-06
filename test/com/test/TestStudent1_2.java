package com.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pojo.Students;

public class TestStudent1_2 {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	public void init() {
		// 创建配置对象
		Configuration configuration = new Configuration().configure();
		// 创建服务注册对象
//		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
//				.applySettings(configuration.getProperties())
//				.buildServiceRegistry();
		
//		hibernate 高级版本
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		// 创建会话工厂对象
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);// error
		// 会话对象
		session = sessionFactory.openSession();
		// 开启事务
		transaction = session.beginTransaction();
	}

	@After
	public void destory() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	@Test
	public void testSaveStudent() {
		Students students = new Students(1, "你咩", "男", new Date(), "太原");
		session.save(students);
	}
}
