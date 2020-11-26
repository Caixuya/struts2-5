package action;

import java.sql.Timestamp;//时间戳
import java.util.Date;//日期
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import po.House;
import po.Street;
import po.Type;
import po.User;
import dao.*;

public class HouseAction {
	private String description;
	private Double price;
	private Double street_id;
	private Double type_id;
	private Double floorage;
	private String title;	
	private String contact;
		
	private Double lowPrice;//查询条件
	private Double highPrice;
	private Double lowFloorage;
	private Double highFloorage;
							//查询条件+user,+title
	
	private Double id;  //房屋id, 用于房屋详情
	//显示房屋详情
	public String detail() {
		HouseDAO hdao=new HouseDAO();
		House h = hdao.findById(id);
		System.out.println(h.toString());
		ServletActionContext.getRequest().setAttribute("result", h);
		return "ok";
	}

	//搜索房子
	public String search() {
		try {
			//一、接收查询条件 title price street_id type_id floorage 
			//二、调用houseDAO进行查询
			HouseDAO hdao = new HouseDAO();
			
			Street street = new Street();
			if(street_id!=null) {
				street.setId(street_id);
			}else {
				street=null;
			}
			Type type=new Type();
			if(type_id!=null) {
				
				type.setId(type_id);
			}else{
				type = null;
			}
			
			if(title.trim().equals("")) {title=null;}
			System.out.println("接收到search请求："+title+","+lowPrice+","+highPrice+","+lowFloorage+","+highFloorage+","+street_id+","+type_id);

			User user = (User)ServletActionContext.getRequest().getSession().getAttribute("currentUser");

			List<House> list = hdao.findByCriteria(user, title, lowPrice, highPrice, street, type, lowFloorage, highFloorage);
			System.out.println("search到了"+list.size()+"条房屋信息");
			
			ActionContext.getContext().getValueStack().set("houses", list);
			//三、在manage页显示查询结果
			
			return "ok";
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("HouseAction.search时发生错误");
			ActionContext.getContext().getValueStack().set("message","HouseAction.search时发生错误");
			return "error";
		}
		
	}
		
		//显示用户发布的所有房子
		public String show() {			
			User user=(User)ServletActionContext.getRequest().getSession().getAttribute("currentUser");
			HouseDAO hdao=new HouseDAO();
			List<House> list = hdao.findByCriteria(user,null,null,null,null,null,null,null);
			System.out.println("查找到"+user.getName()+"发布的信息"+list.size()+"条");
			ActionContext.getContext().getValueStack().set("houses", list);
			return "ok";
		}
		//添加房屋
		public String add() {
			//验证数据-封装到house中-调用save方法将在表中新增记录
			//(1) title  contact 不能为空(
			//(2) pirce, street_id,type_id,floorage 不能为空
			if(title.trim().equals("")||contact.trim().equals("")) {
				ActionContext.getContext().getValueStack().set("message", "添加失败，title、联系电话不能为空");
				return "error";
			}						
			if(price==null||street_id==null||type_id==null||floorage==null) {
				ActionContext.getContext().getValueStack().set("message", "价格、面积、所在区、所在街道不能为空");
				return "error";
			}
			
			//*封装数据*
			//获取当前用户					
			House h=new House();
			Timestamp ts=new Timestamp(new Date().getTime());
			User user = (User)ServletActionContext.getRequest().getSession().getAttribute("currentUser");
			Type type=new Type();
			type.setId(type_id);
			Street str=new Street();
			str.setId(street_id);
			
			h.setUser(user);	
			h.setTitle(title);
			h.setType(type);
			h.setStreet(str);
			h.setContact(contact);
			h.setDescription(description);
			h.setFloorage(floorage);
			h.setPrice(price);
			h.setPubdate(ts);	
			
			System.out.println("即将发布house:"+h.toString());
			//*将house保存到对应的表中*
			HouseDAO hdao=new HouseDAO();
			hdao.save(h);
			ActionContext.getContext().getValueStack().set("message", "房屋发布成功:"+h.toString());
			return "ok";
		}

		public void setStreet_id(Double street_id) {
			this.street_id = street_id;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public void setContact(String contact) {
			this.contact = contact;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public void setType_id(Double type_id) {
			this.type_id = type_id;
		}

		public void setFloorage(Double floorage) {
			this.floorage = floorage;
		}

		public void setLowPrice(Double lowPrice) {
			this.lowPrice = lowPrice;
		}

		public void setHighPrice(Double highPrice) {
			this.highPrice = highPrice;
		}

		public void setLowFloorage(Double lowFloorage) {
			this.lowFloorage = lowFloorage;
		}

		public void setHighFloorage(Double highFloorage) {
			this.highFloorage = highFloorage;
		}

		public void setId(Double id) {
			this.id = id;
		}
	
}
