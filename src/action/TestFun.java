package action;

import org.junit.Test;

import com.opensymphony.xwork2.ActionContext;

public class TestFun {
	public String show() {
		ActionContext.getContext().getValueStack().set("message", "这条消息来自TestFun.show()");
		return "ok";
	}
	
	public String edit() {
		ActionContext.getContext().getValueStack().set("message", "这条消息来自TestFun.edit()");
		return "ok";
	}
	
	public String add() {
		ActionContext.getContext().getValueStack().set("message", "这条消息来自TestFun.add()");
		return "ok";
	}
	
	public String delete() {
		ActionContext.getContext().getValueStack().set("message", "这条消息来自TestFun.delete()");
		return "ok";
	}
	
	//
	@Test
	public void test() {
		String title="   ";
		System.out.println(title.equals(""));
		System.out.println(title.trim().equals(""));
	}
}
