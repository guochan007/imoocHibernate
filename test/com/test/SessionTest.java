package com.test;

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

public class SessionTest {

	@Test
	public void testOpenSession(){
//		创建配置对象
		Configuration configuration = new Configuration().configure();
//		创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();
//		创建会话工厂对象
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		会话对象  该方法在事务提交或回滚之后 不会自动关闭session对象
		Session session=sessionFactory.openSession();
		Session session2=sessionFactory.openSession();
		if(session!=null){
			System.out.println("session创建成功");
		}
		System.out.println(session==session2);
		System.out.println("session hashcode"+session.hashCode());
		System.out.println("session2 hashcode"+session2.hashCode());
//		如果不关的话，最后连接池可能就不够用了，造成溢出
	}
	
	@Test
	public void testGetCurrentSession(){
//		创建配置对象
		Configuration configuration = new Configuration().configure();
//		创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();
//		创建会话工厂对象
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		会话对象  这种方法要在xml中配置current_session_context_class
//		该方法在事务提交或回滚之后 会自动关闭session对象，不会造成连接资源枯竭
		Session session=sessionFactory.getCurrentSession();
		if(session!=null){
			System.out.println("session创建成功");
		}
		Session session2=sessionFactory.getCurrentSession();
		System.out.println(session==session2);
		System.out.println("session hashcode"+session.hashCode());
		System.out.println("session2 hashcode"+session2.hashCode());
		
	}
}
