package cn.nicecoder.barbersys.controller;

import cn.hutool.core.lang.UUID;
import cn.nicecoder.barbersys.entity.comm.Resp;
import cn.nicecoder.barbersys.enums.CommonEnum;
import cn.nicecoder.barbersys.exception.ServiceException;
import cn.nicecoder.barbersys.util.FileUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 文件上传
 * @author: xxxxx
 * @date: 2021/4/20 下午6:54
 */
@RestController
@Api(tags = "文件上传接口")
@RequestMapping("/file/")
public class FileController {

    @Value("${file.common.filePathWindow}")
    private String filePathWindow;
    @Value("${file.common.uploadLinux}")
    private String filePathLinux;

    private final static String resourceLocation = "/images/";

    @PostMapping(value = "upload")
    public Resp upload(@RequestParam("file") MultipartFile file) {
        String os = System.getProperty("os.name");
        try {
            if (file.isEmpty()) {
                throw new ServiceException(CommonEnum.RESP_LAYUI_FAIL.getCode(),"文件为空");
            }
            if(FileUtil.checkFileSize(file, 5, "M")){
                throw new ServiceException(CommonEnum.RESP_LAYUI_FAIL.getCode(),"超过文件大小限制");
            }
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            String newFileName = UUID.randomUUID() + suffixName;
            if (os.toLowerCase().startsWith("win")) {
                file.transferTo(new File(filePathWindow + newFileName));
            } else {
                file.transferTo(new File(filePathLinux + newFileName));
            }
            JSONObject src = new JSONObject();
            src.put("src", resourceLocation + newFileName);
            return Resp.success(src);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Resp.fail("上传失败");
    }

    /**
     * 批量上传（文件重命名）
     * @author: xxxxx
     * @Param: [request]
     * @return: com.talkweb.ssop.common.response.Result<java.util.List<com.talkweb.ssop.storage.sdk.domain.StorageFileDO>>
     * @date: 2021/4/21 下午4:16
     */
    @PostMapping("batch")
    public Resp handleFileUpload(HttpServletRequest request) {
        String os = System.getProperty("os.name");
        List<String> pathArr = new ArrayList();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    if(FileUtil.checkFileSize(file, 5, "M")){
                        throw new ServiceException(CommonEnum.RESP_LAYUI_FAIL.getCode(),"超过文件大小限制");
                    }
                    // 获取文件名
                    String fileName = file.getOriginalFilename();
                    // 获取后缀名
                    String suffixName = fileName.substring(fileName.lastIndexOf("."));
                    String newFileName = UUID.randomUUID() + suffixName;
                    if (os.toLowerCase().startsWith("win")) {
                        file.transferTo(new File(filePathWindow + newFileName));
                    } else {
                        file.transferTo(new File(filePathLinux + newFileName));
                    }
                    pathArr.add(resourceLocation + newFileName);
                } catch (Exception e) {
                    return Resp.fail("第 " + i + " 个文件上传失败");
                }
            } else {
                return Resp.fail("第 " + i + " 个文件上传失败因为文件为空");
            }
        }
        return Resp.success(pathArr);
    }

}