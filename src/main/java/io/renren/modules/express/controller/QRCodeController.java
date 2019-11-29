package io.renren.modules.express.controller;

import io.renren.common.utils.R;
import io.renren.modules.express.service.TaskService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("express/qrcode")
public class QRCodeController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/print")
    @RequiresPermissions("express:qrcode:print")
    public R list(@RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException, DocumentException {

        String ww= (String) params.get("key");

        String qrCodeFilePath= taskService.PrintQRCode();

        return R.ok().put("filepath", qrCodeFilePath);
    }
}
