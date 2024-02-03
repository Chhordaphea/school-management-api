package com.example.finalprojectapi.service.file;

import com.example.finalprojectapi.common.api.StatusCode;
import com.example.finalprojectapi.component.FileManager;
import com.example.finalprojectapi.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public Object uploadImage(MultipartFile fileData) throws Exception {
        if(fileData.isEmpty()) throw new BusinessException(StatusCode.IMAGE_CANNOT_BE_EMPTY);

        String imageUrl =  FileManager.storeImage(fileData);

        Map<String, String> data = new HashMap<>();
        data.put("image_url",imageUrl);

        return data;
    }
}
