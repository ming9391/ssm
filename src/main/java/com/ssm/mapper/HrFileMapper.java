package com.ssm.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ssm.model.HrFile;

public interface HrFileMapper {

	List<HashMap<String,String>> getHrEmailConfig();
	
	Integer deleteFile();
	
    Integer insertFile(ArrayList<HrFile> hrFiles);
    
    Integer updateHrUser();
    
    List<HashMap<String,String>> getNotExtsisPosition();
    
    List<HashMap<String,String>> getNewUserInfo();

}
