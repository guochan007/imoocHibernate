package com.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pojo.Address;
import com.pojo.Students;
import com.pojo.Students1;


public class TestStudent2 {
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
		transaction=session.beginTransaction();	
	}

	@After
	public void destory() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}

	@Test
	public void testSaveStudent(){
		Students1 students=new Students1();
		students.setSname("你妹妹");
		
		Address address=new Address("11","11","11");
		students.setAddress(address);
		session.save(students);
	}
}
