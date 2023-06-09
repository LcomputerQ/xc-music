package com.example.yin.controller;

import com.example.yin.dao.DynamicsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 文件上传
 */
@RestController
public class FileController {
    @Autowired
    private DynamicsMapper dynamicsMapper;

    @ResponseBody
    @RequestMapping("file/add")
    public String uploadFile(HttpServletRequest request) {
//         获取用户id
        Integer id = Integer.parseInt(request.getParameter("id"));

//         用户头像
        String uimg = request.getParameter("uimg");

//         内容
        String content = request.getParameter("content");

//         type
        String type = request.getParameter("type");

//        话题id
        Integer topicid = Integer.parseInt(request.getParameter("topicid"));

        String flag = null;
        //获得文件
        MultipartHttpServletRequest mh = (MultipartHttpServletRequest) request;
        MultipartFile mf = mh.getFile("file");
        try {
            //判断文件是否存在,且文件内容有值
            if (null != mf && mf.getSize() > 0) {

                //获取项目所在地址(绝对路径)
                // request.getSession().getServletContext()获取上下文路径
//              String basePath=  request.getSession().getServletContext().getRealPath("/");
                //创建一个文件夹  存放文件
//                String basePath="C:\\Users\\WXY\\Desktop\\xc-music\\music-server\\src\\main\\resources\\public\\uimg";
                String basePath = System.getProperty("user.dir") + File.separator + "public" + File.separator + "uimg";
                System.out.println("basePath = " + basePath);
                File ypload = new File(basePath);
                //判断文件是否存在
                if (!(ypload.exists())) {
                    //文件夹不存在,创建文件夹
                    ypload.mkdir();
                }
                //设置文件名称  系统当前时间毫米数 命名上传文件
                //            mf.getOriginalFilename()文件原始文件名
                //            mf.getOriginalFilename().lastIndexOf(".")获取文件后缀，截取以点后的字符串
                String fileName = System.currentTimeMillis() +
                        mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
                String contentUrl = File.separator + "uimg" + File.separator + fileName;
                System.out.println("contentUrl = " + contentUrl);
                dynamicsMapper.addDynamic(id, uimg, content, type, topicid, contentUrl);
                //文件拷贝
                mf.transferTo(new File(ypload, fileName));
                flag = "文件上传成功!";
            } else {
                flag = "文件上传失败!";
                dynamicsMapper.addDynamic(id, uimg, content, type, topicid, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
