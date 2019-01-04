package com.moodshop.service;

import com.moodshop.comm.pojo.EasyUIDataGridResult;
import com.moodshop.comm.pojo.MSResult;
import com.moodshop.pojo.TbAdmin;

public interface AdminService {
	//获取管理员列表
	EasyUIDataGridResult getAdminList(int page,int rows);
	//更新管理员
	MSResult updateAdmin(TbAdmin admin);
	//新增管理员
	MSResult addAdmin(TbAdmin admin);
}
