package com.bank.knab.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.bank.knab.entity.ImageModel;

public interface FileService {
	
	public ImageModel uploadImage(MultipartFile file) throws IOException;
	
	public InputStream downloadImage(String path,String name) throws FileNotFoundException ;

}
