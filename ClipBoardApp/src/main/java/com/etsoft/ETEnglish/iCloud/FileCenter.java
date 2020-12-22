package com.etsoft.ETEnglish.iCloud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("iCloud")
public class FileCenter {
	String appName = "ClipBoardApp";

	String userDir = System.getProperty("user.dir");
	String clipboardFile = userDir + "/" + appName + "/files/a-clipboard.txt";// /target/classes/static/
	//String ip = "http://localhost:8080/";
	String fileListPath = userDir + "/" + appName + "/files/";// /target/classes/static/

	@RequestMapping("/fileList")
	public List<String> fileList() {
        File fileDir = new File(fileListPath);
		if (!fileDir.exists() || !fileDir.isDirectory()) {
			try {
				fileDir.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		File[] listFiles = fileDir.listFiles();
		List<String> list = new ArrayList<String>();
		for (File file : listFiles) {
			if (file.isDirectory()) {
				continue;
			}
			list.add(file.getName());
		}
		return list;
	}

	@RequestMapping("/upfile")
	@ResponseBody
	public Msg upfile(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
		if (file.isEmpty()) {
			// return "上传失败，请选择文件";
			return new Msg(0, "no file found");
		}
		String fileName = file.getOriginalFilename();
		File dest = new File(fileListPath + fileName);
		try {
			file.transferTo(dest);
			// response.sendRedirect("/FileCenter.html");
			// return "上传成功";

			return new Msg(1, "upload successfully");
		} catch (IOException e) {
			
		}
		//return "上传失败！";
		return new Msg(0, "upload error");
	}
	@RequestMapping("/del")// 删除文件
	public void del(@RequestParam String fileName, HttpServletResponse response) {
		if (fileName == null || fileName.equals("") || fileName.contains("/")|| fileName.contains("\\")) {
			return;
		}
        File file = new File(fileListPath + fileName);
		if (file.exists() && !file.isDirectory()) {
			file.delete();
			try {
				response.sendRedirect("/FileCenter.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		return;
	}
    @RequestMapping("getFile")  
	public ResponseEntity<InputStreamResource> downloadFile(String fileName, HttpServletRequest request) throws IOException {
		if (fileName == null || fileName.equals("") || fileName.contains("/")|| fileName.contains("\\")) {
			return null;
		}
		String filePath = fileListPath + fileName;
		FileSystemResource file = new FileSystemResource(filePath);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", String.format("attachment; %s", encodeFileName(request, file.getFilename())));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");

		return ResponseEntity.ok().headers(headers).contentLength(file.contentLength())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(file.getInputStream()));
	}
	
	// 剪贴板
	@RequestMapping("/clipboard")
	public String clipboard() {
		File file = new File(clipboardFile);
		if (!file.exists()) {
			File fileDir = new File(fileListPath);
			if (!fileDir.exists() || !fileDir.isDirectory()) {
				try {
					fileDir.mkdir();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "new file created";//"file not exists";
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempStr;
			StringBuilder sb = new StringBuilder();
			// 一次读入一行，直到读入null为文件结束  
            while ((tempStr = reader.readLine()) != null) { 
            	sb.append(tempStr);
            	sb.append('\n');
            }
			reader.close();
            return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	@RequestMapping("/save")
	public String save(@RequestParam String content) {
        try {
    		File file = new File(clipboardFile);
    		if (!file.exists()) {
				file.createNewFile();
    		}
    		
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件  
            FileWriter writer = new FileWriter(clipboardFile);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return "1";
	}

	public String encodeFileName(HttpServletRequest request, String fileName) {  
		String userAgent = request.getHeader("User-Agent");
		String rtn = "";
		try {
			String newFilename = URLEncoder.encode(fileName, "UTF8");
			// 如果没有UA，则默认使用IE的方式进行编码，因为毕竟IE还是占多数的
			rtn = "filename=\"" + newFilename + "\"";
			if (userAgent != null) {
				userAgent = userAgent.toLowerCase();
				// IE浏览器，只能采用URLEncoder编码
				if (userAgent.indexOf("msie") != -1) {
					rtn = "filename=\"" + newFilename + "\"";
				}
				// FireFox浏览器，可以使用MimeUtility或filename*或ISO编码的中文输出
				else if (userAgent.indexOf("mozilla") != -1) {
					rtn = "filename*=UTF-8''" + newFilename;
				}
				// Chrome浏览器，只能采用MimeUtility编码或ISO编码的中文输出
				else if (userAgent.indexOf("applewebkit") != -1) {
					rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
				}
//				// Opera浏览器只能采用filename*
//				else if (userAgent.indexOf("opera") != -1) {
//					rtn = "filename*=UTF-8''" + newFilename;
//				}
//				// Safari浏览器，只能采用ISO编码的中文输出
//				else if (userAgent.indexOf("safari") != -1) {
//					rtn = "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\"";
//				}
			}
		} catch (UnsupportedEncodingException e) {
		}
		return rtn;
	}
}
