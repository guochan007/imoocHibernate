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

import com.pojo.Students;


public class TestStudent1_1 {
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
		Students students=new Students(1, "你哥", "男", new Date(), "北京");
		session.save(students);
	}
	
	@Test
	public void testGetStudent(){
		Students students=(Students)session.get(Students.class, 1);//主键
		System.out.println(students);
		System.out.println(students.getClass().getName());//真正的对象
	}
	
	@Test
	public void testLoadStudent(){
		Students students=(Students)session.load(Students.class, 1);//主键
		System.out.println(students);
		System.out.println(students.getClass().getName());//代理对象
	}
	
	@Test
	public void testUpdateStudent(){
		Students students=(Students)session.load(Students.class, 1);//主键
		students.setGender("女");
		session.update(students);
	}
	
	@Test
	public void testDeleteStudent(){
		Students students=(Students)session.load(Students.class, 1);//主键
		session.delete(students);
	}
}
