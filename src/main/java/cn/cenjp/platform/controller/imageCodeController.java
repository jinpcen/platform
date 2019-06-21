package cn.cenjp.platform.controller;


import cn.cenjp.platform.utils.CodeUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

@RestController
public class imageCodeController {


    @RequestMapping("/getImage")
    public void doGet(HttpServletResponse resp){

        Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
        Cookie cookie = new Cookie("code",codeMap.get("code").toString());

        resp.addCookie(cookie);
        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
