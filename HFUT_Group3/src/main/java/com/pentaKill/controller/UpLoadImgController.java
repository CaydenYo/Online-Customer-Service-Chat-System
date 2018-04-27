package com.pentaKill.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UpLoadImgController {
	@RequestMapping("/uploadImg.action")
	@ResponseBody
	public String uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
		// System.out.println("adasas");
		String filePath = "D:\\jisuanke\\HFUT_Group3\\HFUT_Group3(Front-end)\\cs-vue-project\\static";
		String originalFilename = file.getOriginalFilename();
		String newFilename = UUID.randomUUID() + originalFilename;
		System.out.println(newFilename);
		File targetFile = new File(filePath, newFilename);
		file.transferTo(targetFile);
		return newFilename;
	}
}
