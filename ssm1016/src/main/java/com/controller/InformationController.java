package com.controller;

import com.bean.Information;
import com.github.pagehelper.PageInfo;
import com.service.InformationService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Controller
public class InformationController {
    @Autowired
    private InformationService informationService;

    @RequestMapping("/book/getinfolist")
    public String getbooklist(Integer typeid, String informationname,
                              @RequestParam(value = "index", defaultValue = "1") int pageindex, ModelMap map,
                              @RequestParam(value = "size", defaultValue = "5") int size){
        PageInfo pageInfo = informationService.getallinfo(typeid, informationname, pageindex, size);
        map.put("pi",pageInfo);
        map.put("typeid",typeid);
        map.put("iname",informationname);
        map.put("size",size);

        return "book/list_ziliao";
    }
    //跳转到新增页面
    @RequestMapping("/book/direct")
    public String direct(){


        return "/book/add";
    }


    //新增
    /*@RequestMapping("/book/addinfo")
    public String addinfo(Information information){

         informationService.insert(information);
        return "direct:/book/list-ziliao";
    }*/

    @RequestMapping("/book/upload")
    public String upload(@RequestParam("myfile") MultipartFile myfile, HttpServletRequest request,Information information){

        informationService.insert(information);

        String path = request.getRealPath("uploadfile");

        try {
            myfile.transferTo(new File(path+"/"+myfile.getOriginalFilename()));//复制
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.setAttribute("filename",myfile);

        return "redirect:/book/getinfolist";

    }



    @RequestMapping("/study/getdownlist")
    public String getdownlist(Integer typeid, String informationname,
                              @RequestParam(value = "index", defaultValue = "1") int pageindex, ModelMap map,
                              @RequestParam(value = "size", defaultValue = "5") int size){
        PageInfo pageInfo = informationService.getallinfo(typeid, informationname, pageindex, size);
        map.put("pi",pageInfo);
        map.put("typeid",typeid);
        map.put("iname",informationname);
        map.put("size",size);

        return "/study/StudentMaterial";
    }

    @RequestMapping("/study/download")
    public ResponseEntity<byte[]> down(String fname,HttpServletRequest request){

        //下载的文件
        String path=request.getRealPath("/uploadimage");
        File f=new File(path+"/"+fname);
        ResponseEntity<byte[]> rsp= null;
        try {
            //设置响应的头信息
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode(fname,"utf-8"));

            rsp = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f),httpHeaders, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsp;
    }

}
