package com.it.demo.service.impl;

import com.it.demo.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.util.concurrent.Future;

/**
 * TODO
 *
 * @author chenshaoqi
 * @since 2020/5/28
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        AsynchronousFileChannel fc = null;
        try {
            String fileName = file.getOriginalFilename();
            byte[] fileBytes = file.getBytes();
            int fileSize = fileBytes.length;
            String uploadPath = this.getUploadPathAndCreate(fileName);
            // 文件上传
            Path path = Paths.get(uploadPath);
            fc = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
            Future<Integer> f = fc.write(ByteBuffer.wrap(file.getBytes()), 0);
            if (!f.isDone()) {
                System.out.println("success");
            }
            System.out.println("success");
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (fc != null) {
                try {
                    fc.close();
                } catch (IOException e) {
                    System.out.println("uploadFile fc close error:" + e);
                }
            }
        }
    }

    /**
     * 创建文件并返回文件路径
     * @param fileName fileName
     * @return  保存文件路径
     * @throws IOException 创建文件异常
     */
    private String getUploadPathAndCreate(String fileName) throws IOException {
        String uploadDir = "C:\\temp\\upload\\demo\\" + UUID.randomUUID().toString();
        File uploadFile = new File(uploadDir);
        boolean makeFlag = uploadFile.mkdir();
        if (!makeFlag) {
            System.out.println("创建目录失败");
            return null;
        }
        String uploadFilePath = uploadDir + "//" + fileName;
        boolean created = new File(uploadFilePath).createNewFile();
        if (!created) {
            return null;
        }
        return uploadFilePath;
    }
}
