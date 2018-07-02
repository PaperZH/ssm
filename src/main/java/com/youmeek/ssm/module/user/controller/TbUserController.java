package com.youmeek.ssm.module.user.controller;

import com.youmeek.ssm.module.user.dto.ResultCode;
import com.youmeek.ssm.module.user.dto.ResultData;
import com.youmeek.ssm.module.user.pojo.TbUser;
import com.youmeek.ssm.module.user.service.TbUserService;
import com.youmeek.ssm.module.user.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping
public class TbUserController {

    private static final Logger log =  LoggerFactory.getLogger(TbUserController.class);

    @Autowired
    TbUserService tbUserService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResultData login(@RequestParam(required = false,defaultValue = "")String username, @RequestParam(required = false,defaultValue = "")String password){
        TbUser tbUser = new TbUser();
        tbUser.setUserName(username);
        tbUser.setUserPwd(password);
        log.debug("the user login !--name:"+tbUser.getUserName()+"--pwd:"+tbUser.getUserPwd());
        TbUser loginUser = tbUserService.userLogin(tbUser);
        if(loginUser!=null){
            System.out.println("success");
            return ResultData.ok().Add("user",loginUser)
                    .Add("total",tbUserService.selectTotalPageByUserName(username))
                    .Add("token",JwtUtil.getToken("1001"));

        }else{
            System.out.println("fail");
            return ResultData.fail("error", ResultCode.PARAM_ERROR_CODE.getCode());
        }

    }

    @RequestMapping("/listUsers")
    @ResponseBody
    public ResultData listUsers(  @RequestParam(required = false,defaultValue = "1")int page, @RequestParam(required = false,defaultValue = "10")int pageSize, @RequestParam(required = false,defaultValue = "")String userName){
        log.debug(" get users' list ------------------------------->"+userName+userName.equals(""));

        if(userName!=null&&!userName.equals("")){
            TbUser tbUser = new TbUser();
            tbUser.setUserName(userName);
            return ResultData.ok("list",tbUserService.getUserByCondition(tbUser));
        }
        int startPage = page==0?0:(page-1)*pageSize;
        return ResultData.ok("list",tbUserService.getUserByPageCondition(startPage,pageSize)).Add("total",tbUserService.selectTotalPage());
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public ResultData insertUser(String userName, String userPwd){
        log.debug("insert into users");
        TbUser tbUser = new TbUser();
        tbUser.setUserName(userName);
        tbUser.setUserPwd(userPwd);
        return ResultData.ok("insertInt",tbUserService.insertUser(tbUser));
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResultData deleteUser(@RequestParam(required = false,defaultValue = "-1") String userId){
        log.debug("delete  user");
        System.out.println(userId);

        return ResultData.ok("deleteInt",tbUserService.deleteUser(Integer.parseInt(userId)));
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultData updateUser(TbUser tbUser){
        log.debug("insert into users");
        return ResultData.ok("insertInt",tbUserService.updateUser(tbUser));
    }

    @RequestMapping("/uploadFile")
    @ResponseBody
    public ResultData uploadFile(TbUser tbUser){
        log.debug("");
        return ResultData.ok("insertInt",tbUserService.insertUser(tbUser));
    }

    @RequestMapping("/downloadFile")
    @ResponseBody
    public ResultData downloadFile(TbUser tbUser){
        log.debug("");
        return ResultData.ok("insertInt",tbUserService.insertUser(tbUser));
    }

    @RequestMapping("/uploadImg")
    @ResponseBody
    public ResultData uploadImg( @RequestParam(value="file") MultipartFile file, @RequestParam(value ="userName") String userName){
        log.debug("upload img message!");
        //定义图片上传路劲
        String imgPath = "D:\\workspace\\img\\";
        String imgName = String.valueOf(System.currentTimeMillis());

        File fileDir = new File(imgPath);

        if(!fileDir.exists()){
            fileDir.mkdir();
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //获得拓展名
        String extensionName = fileName.substring(fileName.lastIndexOf(".")+1); 
        try{
            imgName = imgName+"."+extensionName;
            FileOutputStream out = new FileOutputStream(imgPath+imgName);
            out.write(file.getBytes());
            out.flush();
            out.close();
            tbUserService.updateUserImgByName(userName,imgName);
            return ResultData.ok("添加成功！");
        }catch(Exception e){
            e.printStackTrace();
        }

        return ResultData.fail("添加失败！");
    }

    @RequestMapping("/downloadImg")
    @ResponseBody
    public ResultData downloadImg(TbUser tbUser){
        log.debug("insert into users");
        return ResultData.ok("insertInt",tbUserService.insertUser(tbUser));
    }
}
