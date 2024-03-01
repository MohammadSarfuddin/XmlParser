package com.web.application;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	@Autowired
	private XmlService1 XMLService1;
	@Autowired
	private XmlFileUploadBean XmlFileUploadBean;

	@GetMapping(value = "/home")
	public String home(Model model) {
		return "xml file-upload-download";
	}

	@PostMapping(value = "/upload-xml-file")
	public String uploadFileHandler(Model model, @ModelAttribute("fileUploadModel") XmlFileUploadBean fileUploadMode,
			BindingResult bindingResult) {
		String UPLOAD_PATH = "";
		MultipartFile file = fileUploadMode.getFile();
		if (bindingResult.hasErrors()) {
			return "upload-xml-file";
		}
		String fileName = file.getOriginalFilename();
		try {
			byte[] bytes = file.getBytes();
			File files = new File(UPLOAD_PATH + File.separator + fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(files));
			stream.write(bytes);
			parsingXmlFile(files);
			stream.close();
			return "redirect:/";
		} catch (Exception e) {
			return "redirect:/";
		}
	}

	public void parsingXmlFile(File file) {
		XMLService1.parsingXmlFile(file);
	}
}
