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
import com.util.MySessionFactory;


public class TestStudent1_3 {
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init() {
		
//		会话对象
		session = MySessionFactory.getSessionFactory().openSession();
//		开启事务
		transaction=session.beginTransaction();	
	}

	@After
	public void destory() {
		transaction.commit();
		session.close();
	}

	@Test
	public void testSaveStudent(){
		Students students=new Students(1, "你哥", "男", new Date(), "北京");
		session.save(students);
	}
}
