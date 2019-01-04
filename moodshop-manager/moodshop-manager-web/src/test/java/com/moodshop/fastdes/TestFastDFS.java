package com.moodshop.fastdes;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class TestFastDFS {
	
//	@Test
//	public void testUpload() throws Exception{
//		//初始化全局配置
//		ClientGlobal.init("F:\\bishe_workspace\\moodshop-manager\\moodshop-manager-web\\src\\main\\resources\\properties\\client.conf");
//		//创建一个TrackerClient
//		TrackerClient trackerClient=new TrackerClient();
//		//创建一个TrackerServer
//		TrackerServer trackerServer=trackerClient.getConnection();
//		//创建storageServer对象
//		StorageServer storageServer=null;
//		//获得storageClient对象
//		StorageClient storageClient=new StorageClient(trackerServer,storageServer);
//		//调用方法上传图片
//		String[] strings=storageClient.upload_file("F:\\img.jpg", "jpg", null);
//		for(String string:strings){
//			System.out.println(string);
//		}
//	}
//	@Test
//	public void testFastDfsClient() throws Exception {
//		FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
//		String uploadFile = client.uploadFile("F:\\img.jpg", "jpg");
//		System.out.println(uploadFile);
//	}
}
