package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Date;

import org.hibernate.Hibernate;
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


public class BlobTest {
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
	
	public void testWriteBlob() throws Exception{
		Students students=new Students(2, "你哥", "男", new Date(), "北京");
		File f=new File("d:"+File.separator+"xx.jpg");
		InputStream inputStream=new FileInputStream(f);
//		创建blob对象
		Blob blob=Hibernate.getLobCreator(session).createBlob(inputStream,inputStream.available());
		students.setxx(blob);
		session.save(students);
		
	}
	
	public void testReadBlob() throws Exception{
		Students students=(Students)session.get(Students.class, 1);
		Blob blob=students.getxx();
		InputStream inputStream=blob.getBinaryStream();
		File f=new File("d:"+File.separator+"xx1.jpg");
		OutputStream outputStream=new FileOutputStream(f);
		byte[] buff=new byte[inputStream.available()];
		inputStream.read(buff);
		outputStream.write(buff);
		inputStream.close();
		outputStream.close();
		
	}
}