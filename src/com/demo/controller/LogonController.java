package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.demo.entity.Student;
import com.demo.entity.WhoLogin;
import com.demo.services.ILoginService;
import com.demo.services.IStudentService;
import com.demo.util.GetVisitorSystemUtil;
import com.demo.util.JiaMiPWD;
import com.demo.util.JsonView;

@Controller
@Scope("prototype")
@RequestMapping("/logon.do")
public class LogonController {
	@Autowired
	private IStudentService service;
	@Autowired
	private ILoginService loginservice;
	private String old_path;
	private String n_user_photo;

	@SuppressWarnings("static-access")
	@RequestMapping(params = "action=logon")
	public JsonView logon(HttpServletRequest request,
			HttpServletResponse response, String number, String password) throws Exception {
		JsonView view = new JsonView();
		System.out.println(number);
		password = JiaMiPWD.encryptSHA(password);
		if (service.StudentValidate(request, number, password)) {
			GetVisitorSystemUtil information = new GetVisitorSystemUtil();
			WhoLogin pojo = new WhoLogin();
			pojo.setLogin_name(number);
			pojo.setLogin_time(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			pojo.setLogin_ip(information.getClientIp(request));
			pojo.setLogin_system(information.getRequestSystemInfo(request));
			pojo.setLogin_browser(information.getRequestBrowserInfo(request));
			System.out.println(pojo.getLogin_name()+pojo.getLogin_ip()+pojo.getLogin_browser()+pojo.getLogin_system());
			loginservice.insertOne(pojo);
			view.setProperty("result", "ok");
			view.setProperty("message", "成功");
			System.out.println("******logon ok******");
		} else {
			view.setProperty("result", "error");
			view.setProperty("message", "不成功");
		}
		
		return view;
	}
	
	@RequestMapping(params = "action=main")
	public ModelAndView main(HttpServletRequest request,ModelMap map,Student student) throws IOException{
		int id=service.getLoginId();
		System.out.println(id);
		student=service.findStudentById(id);
		map.put("student", student);
		return new ModelAndView("main");
	}
	
	
	@RequestMapping(params = "action=editSaveUser")
	public JsonView editSaveUser(HttpServletRequest request,ModelMap model,Student student)throws Exception{
		JsonView view =new JsonView();
		try {
			service.editStudent( student);
			System.out.println("*****update_login_user******");
			view.setProperty("result", "succ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			view.setProperty("result","error");
		}
		return view;
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(params = "action=modify_login_photo")
	public String springUpload(HttpServletRequest request,String p_address,int id,String op) throws IllegalStateException, IOException{
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");  
		java.util.Date currentTime = new java.util.Date();//得到当前系统时间  
		String str_date1 = formatter.format(currentTime); //将日期时间格式化  
		int max=9999;
		int min=1000;
		int rand=new java.util.Random().nextInt(max)%(max-min+1) + min;
		String file_name=str_date1+rand; 
		
		long  startTime=System.currentTimeMillis();
	         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request)){
	            //将request变成多部分request
	            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	           //获取multiRequest 中所有的文件名
	            Iterator iter=multiRequest.getFileNames();
	             
	            while(iter.hasNext()){
	                //一次遍历所有文件
	                MultipartFile file=multiRequest.getFile(iter.next().toString());
	                if(file!=null){
	                	String[] file_end=file.getOriginalFilename().split("\\.");
	                	String path=Thread.currentThread().getContextClassLoader().getResource("").getPath();//获取项目路径
	        			path = URLDecoder.decode(path,"utf-8").replace("\\", "/");//将【\】转换成【/】
	        			path = path.substring(1, path.lastIndexOf("/"));//得到除去最后一个【/】的字符串地址
	        			path = path.substring(0, path.lastIndexOf("/"));//去除项目路径倒数第二个【/】后的字符串获得最终路径
	        			path = path.substring(0, path.lastIndexOf("/"));//去除项目路径倒数第二个【/】后的字符串获得最终路径
	        			old_path= path+"/images/"+p_address+"/";
	                	//old_path="D:/ClassManage/ClassManage/WebContent/images/"+p_address+"/";
	                	n_user_photo=file_name+"."+file_end[1];
	                    String path1=old_path+n_user_photo;
	                    //上传
	                    file.transferTo(new File(path1));
	                    System.out.println(path1);
	                }
	                 
	            }
	           
	        }
	        long  endTime=System.currentTimeMillis();
	        
	        if(p_address.equals("student_img")){
	        	System.out.println("new_photo:"+n_user_photo);
	        	String old_photo=service.findStudentById(id).getSphoto();
	        	service.modifyStudentPhoto(id,n_user_photo);
	        	System.out.println("old_photo:"+old_photo);
	        	if(old_photo=="withoutphoto.png"||old_photo.equals("withoutphoto.png"))
	        		System.out.println("old_photo:"+old_photo);
	        	else
	        		service.deleteFile(new File(old_path+old_photo));
	        }
	        
	        System.out.println("方法的运行时间："+String.valueOf(endTime-startTime)+"ms");
	    return "/success";
	    }
	
	
}
