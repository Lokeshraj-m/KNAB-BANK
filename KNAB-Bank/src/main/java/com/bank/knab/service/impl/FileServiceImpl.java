package com.bank.knab.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bank.knab.entity.ImageModel;
import com.bank.knab.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public InputStream downloadImage(String path, String name) throws FileNotFoundException {
		String fullPath = path+File.separator+name;
		InputStream response = new FileInputStream(fullPath);
		return response;
	}

	@Override
	public ImageModel uploadImage(MultipartFile file) throws IOException {
		String name = file.getOriginalFilename();
		String randomId = UUID.randomUUID().toString();
		name = randomId.concat(name.substring(name.lastIndexOf(".")));
		ImageModel imageModel = new ImageModel(0,name,file.getContentType(),file.getBytes());
		return imageModel;
	}

}
