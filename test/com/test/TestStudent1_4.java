package com.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pojo.Students;
import com.util.HibernateSessionFactory;


public class TestStudent1_4 {
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init() {
		
//		会话对象
		session =  HibernateSessionFactory.getSession();
//		用这个工具比较好，哪里出错，有提示，而其他的方法没有他好用
		
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
