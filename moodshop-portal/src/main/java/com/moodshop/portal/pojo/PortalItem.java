package com.moodshop.portal.pojo;

import com.moodshop.pojo.TbItem;

public class PortalItem extends TbItem {
	public String[] getImages(){
		String images=this.getImage();
		if(images!=null&&!images.equals("")){
			String [] imgs=images.split(",");
			return imgs;
		}
		return null;
	}
}
