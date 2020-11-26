package tests;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Test;

import dao.HibernateUtil;
import dao.HouseDAO;
import dao.UserDAO;
import po.Street;
import po.Type;
import po.User;

public class TestFun {
	//
	@Test
	public void test1() {
		UserDAO dao=new UserDAO();
		User u=new User();
		u.setName("admin");
		u.setPassword("123");
		List<User> list = dao.findByExample(u);
		if(list.size()==0) {System.out.print("用户名或密码错误");}
		if(list.size()==1) {System.out.print("登录成功");}
	}
	
	@Test
	public void test2() {
		String str1="";
		String str2="  ";
		String str3="a";		
	}
	
	//findByCriteria
	//测试组合条件查询 
	@Test
	public void test3() {
		HouseDAO hdao=new HouseDAO();
		Street street=new Street();
		street.setId(1000d);
		Type type=new Type();
		type.setId(1001d);
		User user=new User();
		user.setId(1000d);
		
		hdao.findByCriteria(user, null, null, null, street, type, null, null);
	}
}
