package main.service;

import main.exception.ToDoNotFoundException;
import main.model.Image;
import main.model.ToDo;
import main.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    private final ImageRepository imageRepository;

    @Value("${upload.path}")
    public String uploadDir;

    public FileService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void uploadFile(MultipartFile file) {
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }

    public void addImage(MultipartFile file, ToDo toDo) {

        Path copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));


        String fileName =
                "attach_" + toDo.getName() + "_" + file.getOriginalFilename().toLowerCase().replaceAll(" ", "-");
        try {
            file.transferTo(new File(copyLocation+"/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image attachment = new Image(fileName, "/images/get/" + toDo.getName() + "/" + fileName);

        toDo.addImage(attachment);
        imageRepository.save(attachment);
    }
}
