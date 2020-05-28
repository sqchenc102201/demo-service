package com.it.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * TODO
 *
 * @author chenshaoqi
 * @since 2020/5/28
 */
public interface TestService {

    void uploadFile(MultipartFile file) throws IOException;
}
