package com.moodshop.service;

import org.springframework.web.multipart.MultipartFile;

import com.moodshop.comm.pojo.PictureResult;

/**
 * 图片上传接口
 * @author Administrator
 *
 */
public interface PictureService {

	PictureResult uploadPic(MultipartFile picFile);
}
