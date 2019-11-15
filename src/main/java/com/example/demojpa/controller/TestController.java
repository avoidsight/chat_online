package com.example.demojpa.controller;

import com.example.demojpa.dao.BlogIpPageableRepository;
import com.example.demojpa.dao.BlogIpRepository;
import com.example.demojpa.dao.BlogIpSpecificationRepository;
import com.example.demojpa.enums.ErrorType;
import com.example.demojpa.exception.ServiceException;
import com.example.demojpa.model.BlogIp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author: WangQiMing
 * @Date: 2019-07-17 15:26
 * @Description:
 */
@Api(tags = {"博客api","sss"})
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    BlogIpRepository blogIpRepository;

    @Resource
    BlogIpPageableRepository blogIpPageableRepository;

    @Resource
    BlogIpSpecificationRepository blogIpSpecificationRepository;

    @Value("${user.avatar.url}")
    String avatarUrl;

    @ApiOperation("根据id读取数据")
    @ApiImplicitParam(value = "用户id",name="id",required = true,dataType = "String",paramType = "path")
    @RequestMapping(value = "/read0/{id}",method = RequestMethod.GET)
    public BlogIp read0(@PathVariable("id") int id){
        return blogIpRepository.findById(id);
    }

    @RequestMapping("/read")
    public List<BlogIp> test(){
        Pageable pageable = PageRequest.of(1,2);
        return blogIpPageableRepository.findByCity("222",pageable);
    }

    @RequestMapping("/read1")
    public List<BlogIp> test0(){
        return blogIpPageableRepository.findByIp("333", Sort.by(Sort.Direction.DESC,"id"));
    }

    @RequestMapping("/read2")
    public List<BlogIp> test11(){
        return blogIpPageableRepository.findAll(PageRequest.of(1,2,Sort.by(Sort.Direction.DESC,"id"))).getContent();
    }

    @ApiOperation("读取上海市IP")
    @RequestMapping(value = "/read3",method = RequestMethod.GET)
    public List<BlogIp> read3() {
        List<BlogIp> list = blogIpSpecificationRepository.findAll((Specification<BlogIp>) (root, query, criteriaBuilder) -> {
            Predicate p1 = criteriaBuilder.like(root.get("city"),"上海市%");
            Predicate p2 = criteriaBuilder.greaterThan(root.get("ip"),"111");
            return criteriaBuilder.and(p1,p2);
        });
        return list;
    }

    @ApiOperation("保存数据")
    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public BlogIp test1(){
        Pageable page = PageRequest.of(1,3);
        BlogIp blogIp = new BlogIp();
        blogIp.setIp("111");
        blogIp.setCity("上海市");
        return blogIpRepository.save(blogIp);
    }

    @ApiOperation("删除数据")
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public void test2(){
        blogIpRepository.deleteById(1);
    }

    @ApiOperation("更新数据")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public BlogIp test3(){
        BlogIp blogIp = blogIpRepository.getOne(1);
        blogIp.setCity("高河镇");
        return blogIpRepository.save(blogIp);
    }

    @ApiOperation("读取上海的数据")
    @RequestMapping(value = "/readAll",method = RequestMethod.GET)
    public List<BlogIp> getAll(){
        List<BlogIp> list = blogIpRepository.findAll(Specification.where((Specification<BlogIp>) (root, query, criteriaBuilder) -> {
            Predicate p1 = criteriaBuilder.like(root.get("city"),"上%");
            Predicate p2 = criteriaBuilder.lessThan(root.get("ip"),"888");
            return criteriaBuilder.and(p1,p2);
        }).or((Specification<BlogIp>) (root, query, criteriaBuilder) -> {
            Predicate p1 = criteriaBuilder.like(root.get("city"),"高%");
            Predicate p2 = criteriaBuilder.lessThan(root.get("ip"),"888");
            return criteriaBuilder.and(p1,p2);
        }),PageRequest.of(0,10,Sort.by(Sort.Direction.DESC,"id"))).getContent();
        return list;
    }

    @GetMapping("/exception/{msg}")
    public String exception(@PathVariable String msg){
        if("1".equals(msg)){
            throw new ServiceException(250,"瞧你那傻样！");
        }else if("2".equals(msg)) {
            throw new ServiceException(ErrorType.OBJECT_NOT_FOUND);
        }else if("3".equals(msg)) {
            throw new RuntimeException("未知错误");
        }else {
            throw new RuntimeException();
        }
    }

    @GetMapping("/new")
    public BlogIp newObject(@Valid BlogIp ip){
        blogIpRepository.save(ip);
        return ip;
    }

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam(value = "file") MultipartFile file, ModelMap model, HttpServletRequest request) {
        if (file.isEmpty()) {
            System.out.println("文件为空!");
        }
        String fileName = updateFile(request, file);
        model.put("fileName",fileName);
        return "update";
    }

    /**
     *上传到服务器的方法
     */
    private String updateFile(HttpServletRequest request,MultipartFile files) {
        MultipartFile file = files;
        if (!file.isEmpty()){
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                String filePath = avatarUrl + file.getOriginalFilename();;

                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists()){
                    saveDir.getParentFile().mkdirs();
                }
                // 转存文件 保存到服务器地址上，路径在filePath
                file.transferTo(saveDir);
                /**请求图片服务器 返回字符串
                 * */
                //得到图片全名
                String originalFilename = file.getOriginalFilename();
                int i1 = originalFilename.indexOf(".");
                String suffix = "";
                if (i1 != 0){
                    suffix = originalFilename.substring(i1+1);
                }else {
                    suffix = "jpg";
                }

                String absolutePath = saveDir.getAbsolutePath();
                String canonicalPath = saveDir.getCanonicalPath();
                System.out.println(absolutePath+"---"+canonicalPath);
                // absolutePath是文件的绝对路径，下面jsp中，预览头像时，需要回传到getUserLogo 接口中
                return absolutePath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @PostMapping("/fileUp")
    public String update(HttpServletRequest request, MultipartFile file, Model model){
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        // 文件名
        String fileName = file.getOriginalFilename();
        // 后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 上传后的路径
        String filePath = avatarUrl;
        // 新文件名
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/avatars/" + fileName;
        model.addAttribute("filename", filename);
        return "redirect:index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 获取头像
     * */
    @RequestMapping("/getUserLogo")
    public void getUserLogo(HttpServletRequest request,
                            HttpServletResponse response, String path) {
        // 设置返回内容格式
        response.setContentType("image/jpeg");
        // 括号里参数为文件图片路径
        File file = new File(avatarUrl+path);
        // 如果文件存在
        if (file.exists()) {
            InputStream in;
            try {
                in = new FileInputStream(file);
                // 创建输出流
                OutputStream os = response.getOutputStream();
                byte[] b = new byte[1024];
                while (in.read(b) != -1) {
                    os.write(b);
                }
                in.close();
                os.flush();
                os.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/file")
    public String file(){
        return "websocket";
    }

    @GetMapping("/getUrl")
    public String getAvatarUrl(){
        return avatarUrl;
    }
}
