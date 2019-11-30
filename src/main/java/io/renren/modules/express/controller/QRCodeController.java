package io.renren.modules.express.controller;

import com.aliyun.oss.common.utils.LogUtils;
import io.renren.common.utils.R;
import io.renren.modules.express.service.TaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("express/qrcode")
public class QRCodeController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/print",method = RequestMethod.POST)
    @RequiresPermissions("express:qrcode:print")
    public void list(@RequestParam Map<String, Object> paramsMap,HttpServletResponse response) throws IOException, DocumentException {

        String ww= (String) paramsMap.get("key");

        String qrCodeFilePath= taskService.PrintQRCode();

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream osOut = null;

        try {
            //读取文件
            File file =new File(qrCodeFilePath);
            if(file.exists()){

                response.setContentType("application/pdf;charset=UTF-8;");
                response.addHeader("Content-Disposition", "attachment;fileName=QRCode.pdf");
                ByteArrayOutputStream by = new ByteArrayOutputStream();
                osOut = response.getOutputStream();

                byte buf[] = new byte[1024];
                //将读取的文件放到 字节流
                fis = new FileInputStream(file);
                //将字节流 放到缓存中
                bis = new BufferedInputStream(fis);

                // 从缓存中读取 字节流
                int i = bis.read(buf);
                while(i!=-1){

                    // 将指定的字节写入此输出流
                    osOut.write(buf,0,i);
                    i = bis.read(buf);
                }
                // 刷新此输出流并强制将所有缓冲的输出字节被写出
                osOut.flush();

            }else{
                System.out.println("您下载的资源已经不存在了");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭流
            osOut.close();
            bis.close();
            fis.close();

            File file =new File(qrCodeFilePath);
            if(file.exists()){
                file.delete();
            }
        }
    }
}
