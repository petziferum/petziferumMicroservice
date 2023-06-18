package com.petziferum.productservice.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

@Service
public class ProductService {

    @Value("classpath:datafolder/data.txt")
    Resource resource;

    @Value("classpath")
    Resource resource2;

    @Autowired
    private ResourcePatternResolver res;



    public ArrayList<FileData> readData() throws Exception {
        ArrayList<FileData> fileDataArrayList = new ArrayList<FileData>();
        try {
            Resource[] resources = res.getResources("classpath:datafolder/*");
            int fileCount = resources.length;
            System.out.println("Number of files in the resources folder: " + fileCount + "\n");

            for (Resource r : resources) {
                File file = r.getFile();
                String fileContent = Files.readString(file.toPath());
                FileData f = FileData.builder().name(r.getFilename()).content(fileContent).build();

                System.out.println("\n-----------------------------------");
                System.out.println("Name: " + f.getName() + "\nContent: " + f.getContent());
                System.out.println("-----------------------------------\n");

                fileDataArrayList.add(f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("fileDataArrayList: " + fileDataArrayList);
        return fileDataArrayList;
    }
}
