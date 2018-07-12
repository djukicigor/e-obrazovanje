package vp.spring.rcs.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vp.spring.rcs.model.Student_documents;
import vp.spring.rcs.model.user.Student;
import vp.spring.rcs.service.StudentDocumentsService;
import vp.spring.rcs.service.StudentService;

@Controller
public class FileUploadController {

	@Autowired
	StudentDocumentsService studentDocumentsService;
	@Autowired
	StudentService studentService;

	//destination folder to upload the files
	private static String UPLOAD_FOLDER = Paths.get("").toAbsolutePath().toString() + "//documents//";

	@RequestMapping("/api/students/upload/{id}")
	public ModelAndView showUpload() {
		return new ModelAndView("upload");
	}

	@PostMapping("/api/students/upload/{id}")
	public ModelAndView fileUpload(@PathVariable Long id, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		System.out.println(UPLOAD_FOLDER);
		if (file.isEmpty()) {
			return new ModelAndView("status", "message", "Please select a file and try again");
		}

		try {
			// read and write the file to the selected location-
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			Student student = studentService.findOne(id);
			Student_documents document = new Student_documents(file.getOriginalFilename(), student);
			studentDocumentsService.save(document);

			student.addStudent_documents(document);
			studentService.save(student);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("status", "message", "File Uploaded sucessfully");
	}

}