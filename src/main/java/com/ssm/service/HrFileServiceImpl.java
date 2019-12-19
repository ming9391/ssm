package com.ssm.service;

import org.jumpmind.symmetric.csv.CsvReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ssm.mapper.HrFileMapper;
import com.ssm.model.HrEmail;
import com.ssm.model.HrFile;
import com.ssm.utils.EmailUtil;
import com.ssm.utils.IEmailUtil;
import com.ssm.utils.TxtUtil;

import java.io.File;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.MessagingException;

@Service
public class HrFileServiceImpl implements HrFileService{

    @Autowired
    private HrFileMapper hrFileMapper;

    @Override
    public void hrFile(){
    	// 入库报文
    	String rs_email = "";
    	// 邮件配置-收件人
    	List<HashMap<String,String>> parm = hrFileMapper.getHrEmailConfig();
    	String sendTo = resolveListMap(parm);
    	try {
        // 获取最新的csv文件
	        String filePath = "F:/csv";
	        String newFilePath = readNewFileName(filePath);
	        // 读取文件内容
	        ArrayList<String[]> list = csvReaderFile(newFilePath);
	        if (list != null) {
	        	hrFileMapper.deleteFile();
	            ArrayList<HrFile> hrFiles = readFileToEntity(list);
	            Integer insertFile = hrFileMapper.insertFile(hrFiles);
	            // 更新user信息
	            hrFileMapper.updateHrUser();
	            
	            // 获取匹配不上职位等信息发送邮件
	            List<HashMap<String,String>> position = hrFileMapper.getNotExtsisPosition();
	            String rs_position = resolvePosition(position);
	            List<HashMap<String, String>> newUserInfo = hrFileMapper.getNewUserInfo();
	            String newUser = resolveListMap(newUserInfo);
	            
	            rs_email += " 1、本次共获取："+insertFile+" 条记录\r\n\r\n";
	            rs_email += " 2、本次新增用户信息到佣金系统："+newUserInfo.size()+" 条记录，请在佣金系统->系统关联->用户管理界面：修改以下工号的 DS_CODE、TEAM、职位等有效信息\r\n   ";
	            rs_email += newUser+"\r\n\r\n";
	            rs_email += rs_position;
	            
	            // 生成txt附件
	            TxtUtil.writeTxt("F:/csv/log/","hr.txt",rs_email);
	            System.out.println("hr信息入库完毕！");
	            
	            String rs_content = "";
	            rs_content += "<p>Dear All,</p>";
	            rs_content += "<p>&nbsp;&nbsp;以下是hr信息同步结果，请查看附件</p>";
	            rs_content += "<p>1、本次共获取："+insertFile+" 条记录 </p>";
	            rs_content += "<p>2、本次新增用户信息到佣金系统："+newUserInfo.size()+" 条记录，请在佣金系统->系统关联->用户管理界面：修改 DS_CODE、TEAM、职位等有效信息：</p>";
	            
	            //发送邮件
    			EmailUtil.sendEmail("3337028770@qq.com","etgzxjeepxncdbcj",sendTo,"hr入库报文",rs_content,"F:/csv/log/hr.txt","hr_message.txt");
	        }
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    }
    
    // 解析职位
    private static String resolveListMap(List<HashMap<String,String>> parm){
    	String rs="";
    	if(parm!=null && parm.size()>0) {
    		for (Map<String, String> m : parm){ 
    		      for (String k : m.keySet()){ 
    		        rs +=m.get(k)+","; // System.out.println(k + " : " + m.get(k)); 
    		      } 
    			}
    		rs=rs.substring(0, rs.length() - 1);
    	}
    	return rs;
    }
    
    // 解析收件人
    private static String resolvePosition(List<HashMap<String,String>> parm){
    	String sendTo="";
    	if(parm!=null && parm.size()>0) {
    		sendTo = " 3、以下工号匹配不上佣金系统的职位信息，请检查\r\n   ";
    		for (Map<String, String> m : parm){ 
    		      for (String k : m.keySet()){ 
    		    	 if(k.equalsIgnoreCase("employee_no")) {
    		    		 sendTo +="工号："+m.get(k);
    		    	 }
    		    	 if(k.equalsIgnoreCase("department")) {
    		    		 sendTo +="，部门："+m.get(k);
    		    	 }
    		    	 if(k.equalsIgnoreCase("position")) {
    		    		 sendTo +="，职位："+m.get(k);
    		    	 }
    		      }
    		      sendTo += "\r\n   ";
    			}
    	}
    	return sendTo;
    }
    
    // 文件内容封装到实体
    private static ArrayList<HrFile> readFileToEntity(ArrayList<String[]> list) {
    	DateFormat df=new SimpleDateFormat("MM-dd-yyyy");
        ArrayList<HrFile> hrFilesList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String[] strings = list.get(i);
            int length = 0;
            HrFile hrFile = new HrFile();
            hrFile.setEmployee_no(strings[length]);
            hrFile.setGiven_name(strings[++length]);
            hrFile.setSurname(strings[++length]);
            hrFile.setChinese_name(strings[++length]);
            hrFile.setAlias_name(strings[++length]);
            hrFile.setEmail(strings[++length]);
            hrFile.setEmployee_status(strings[++length]);
            try {
                hrFile.setEmployee_status_date(df.parse(strings[++length]));
            } catch (Exception e) {
               // System.out.println("第 " + i + " 行，第 " + length + " 列，错误：" + e.getMessage());
            }
            hrFile.setDivision(strings[++length]);
            hrFile.setSection(strings[++length]);
            hrFile.setDepartment(strings[++length]);
            hrFile.setPosition(strings[++length]);
            try {
                hrFile.setDate_join(df.parse(strings[++length]));
            } catch (Exception e) {
              //  System.out.println("第 " + i + " 行，第 " + length + " 列，错误：" + e.getMessage());
            }
            hrFile.setStaff_grade_code(strings[++length]);
            hrFile.setStaff_grade_description(strings[++length]);
            hrFilesList.add(hrFile);
        }
        return hrFilesList;
    }
    // 读取文件内容
    public static ArrayList<String[]> csvReaderFile(String newFilePath) {
        try {
            ArrayList<String[]> list = new ArrayList<>();
            CsvReader csvReader = new CsvReader(newFilePath, ',', Charset.forName("UTF-8"));
            // 读取表头
            csvReader.readHeaders();
            //String[] headArray = csvReader.getHeaders();
            while (csvReader.readRecord()) {
                String rawRecord = csvReader.getRawRecord();
                String[] rowRecord = rawRecord.split("\\|");
                list.add(rowRecord);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    // 获取最新的csv文件
    private static String readNewFileName(String filePath) {
        File dir = new File(filePath);
        if (dir.exists()) {
            File[] fileList = dir.listFiles();
            List<String> strList = new ArrayList<String>();
            for (File f : fileList) {
                if ((f.isFile())
                        && (".csv".equals(f.getName().substring(f.getName().lastIndexOf("."), f.getName().length())))) {
                    strList.add(f.getAbsolutePath());
                }
            }
            Map<Object, String> map = new HashMap<Object, String>();
            for (String path : strList) {
                File f = new File(path);
                map.put(f.lastModified(), path);
            }
            Object[] obj = map.keySet().toArray();
            Arrays.sort(obj);
            return map.get(obj[obj.length - 1]);
        }
        return null;
    }

}