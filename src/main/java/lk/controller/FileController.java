package lk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * 文件上传下载
 *
 * @author likeLove
 * @time 2020-08-02  17:01
 */
@Controller
@RequestMapping ("/file")
public class FileController {
    private static final String path = "D:\\Java\\project\\SSMBuild\\web\\upload";

    @RequestMapping ("/upload")
    public String fileUpload(@RequestParam ("file") CommonsMultipartFile file, HttpServletRequest req) throws Exception {
        System.out.println(11111);
        String upLoadFileName = file.getOriginalFilename();
        if (!"".equals(upLoadFileName)) {
            System.out.println("上传了：" + upLoadFileName);
            /*String path = req.getServletContext().getRealPath("/upload");*/
            File f = new File(path);
            if (!f.exists()) {
                f.mkdir();
            }
            System.out.println("保存在了:" + f);
            InputStream is = file.getInputStream();
            FileOutputStream os = new FileOutputStream(new File(f, upLoadFileName));
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
                os.flush();
            }
            os.close();
            is.close();
        }
        return "../../index";
    }

    @RequestMapping ("/upload2")
    public String fileUpload2(@RequestParam ("file") CommonsMultipartFile file, HttpServletRequest req) throws Exception {
       /* String path = req.getServletContext().getRealPath("/upload");*/
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        file.transferTo(new File(f + "/" + file.getOriginalFilename()));
        return "../../index";
    }

    @RequestMapping ("/download")
    public String fileDownload(HttpServletRequest req, HttpServletResponse resp, @RequestParam ("fileName") String fileName) throws Exception {
    /*    String path = req.getServletContext().getRealPath("/upload");*/
        System.out.println(fileName);
        resp.reset();
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition",
                "attachment;fileName=" + URLEncoder.encode(fileName, "utf-8"));
        File file = new File(path, fileName);
        FileInputStream is = new FileInputStream(file);
        ServletOutputStream os = resp.getOutputStream();
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = is.read(bys)) != -1) {
            os.write(bys, 0, len);
            os.flush();
        }
        os.close();
        is.close();
        return "../../index";
    }
}
