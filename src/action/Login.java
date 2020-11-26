package action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.config.entities.ActionConfig;

import dao.UserDAO;
import po.User;

public class Login {
	private String username;
	private String password;
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//检查登录
	public String login() {		
		//登录失败,提示“用户名或密码错误”
		UserDAO dao=new UserDAO();
		User u=new User(username,password);
		List<User> list = dao.findByExample(u);
		if(list.size()==0) {
			ActionContext.getContext().getValueStack().set("message", "用户名或密码错误");
			return "error";
		}
		
		//登录成功，跳转到manage页面，显示该user的所有house的信息
		ServletActionContext.getRequest().getSession().setAttribute("currentUser", list.get(0));
		return "ok";		
	}
}
