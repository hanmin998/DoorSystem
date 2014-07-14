package com.example.door.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.example.door.entity.Admin;
import com.example.door.entity.Employee;
import com.example.door.entity.Manager;
import com.example.door.entity.User;


/**
 * 工具类,操作XML文件
 * @author Administrator
 *
 */
public final class XMLTools {
	private XMLTools(){}
	public static boolean writeXML(String path,ArrayList<User> users) {
		File file=new File(path);
	//	boolean flag=false;
		//XML写入器
		XmlSerializer xmlSerializer=Xml.newSerializer();
		//写入器向文件写入
		try {
			xmlSerializer.setOutput(new FileOutputStream(file),"utf-8");
			//开始写
			xmlSerializer.startDocument("utf-8", true);
			xmlSerializer.startTag(null, "users");
			//遍历集合
			for (User user : users) {
				if (user instanceof Admin) {
					Admin admin=(Admin) user;
					xmlSerializer.startTag(null, "admin");
					xmlSerializer.attribute(null, "name", admin.getName());
					xmlSerializer.attribute(null, "pwd", admin.getPassword());
					xmlSerializer.endTag(null, "admin");
				}
				if (user instanceof Employee) {
					Employee employee=(Employee) user;
					xmlSerializer.startTag(null, "employee");
					xmlSerializer.attribute(null, "name", employee.getName());
					xmlSerializer.attribute(null, "cardNo",employee.getCardNo() );
					xmlSerializer.attribute(null, "photo",employee.getPhoto());
					xmlSerializer.attribute(null, "havaCard",employee.isCardHave()+" ");
					xmlSerializer.endTag(null, "employee");
				}
				if (user instanceof Manager) {
					Manager manager=(Manager) user;
					xmlSerializer.startTag(null, "manager");
					xmlSerializer.attribute(null, "name", manager.getName());
					xmlSerializer.attribute(null, "figure",manager.getFigureNo() );
					xmlSerializer.endTag(null, "manager");
				}
			}
			xmlSerializer.endTag(null, "users");
			xmlSerializer.endDocument();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	public static ArrayList<User> readXML(String path){
		ArrayList<User> users=null;
		User user=null;
		File file=new File(path);
		//拿到xml解析器
		XmlPullParser xmlPullParser=Xml.newPullParser();
		try {
			xmlPullParser.setInput(new FileInputStream(file),"utf-8");
			//获取事件的类型
			int type=xmlPullParser.getEventType();
			//先循环读出，指导文件结束
			while (type!=xmlPullParser.END_DOCUMENT) {
				//根据不同事件进行相应的处理
				switch (type) {
				case XmlPullParser.START_TAG:
					if ("users".equals(xmlPullParser.getName())) {
						users=new ArrayList<User>();
					}else if("admin".equals(xmlPullParser.getName())){
						user=new Admin(xmlPullParser.getAttributeValue(0),xmlPullParser.getAttributeValue(1));
					}else if("employee".equals(xmlPullParser.getName())){
						user=new Employee(xmlPullParser.getAttributeValue(0),xmlPullParser.getAttributeValue(1),xmlPullParser.getAttributeValue(2),xmlPullParser.getAttributeValue(3).equals("true")?true:false);
					}else if("manager".equals(xmlPullParser.getName())){
						user=new Manager(xmlPullParser.getAttributeValue(0),xmlPullParser.getAttributeValue(1));
					}
					break;
				case XmlPullParser.END_TAG:
					if(!"users".equals(xmlPullParser.getName())){
						users.add(user);
					}
					break;

				}
				
				//读取下一事件
				type=xmlPullParser.next();
				
			}
			
			return users;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
