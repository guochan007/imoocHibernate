package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

//单态 不让别人继承 使用hibernate时 请一定保证连接一个数据库只有一个sessionfactory
final public class MySessionFactory {

	private static SessionFactory sessionFactory=null;
//	让外面不能实例化它
	private MySessionFactory(){
		
	}
	
	static{
		// 1 创建configuration 该对象用于读取xml 并完成初始化
		Configuration configuration = new Configuration().configure();
		// 2 创建sessionfactory回话工厂 重量级的对象 耗资源 一般只要一个SessionFactory
		// sessionFactory=configuration.buildSessionFactory();//build方法过时了
		ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(sr);
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
