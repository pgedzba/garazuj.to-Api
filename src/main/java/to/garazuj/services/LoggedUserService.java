package to.garazuj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import to.garazuj.converter.ImgResizer;
import to.garazuj.exception.FileResizeException;
import to.garazuj.exception.FileStorageException;
import to.garazuj.message.request.EditUserForm;
import to.garazuj.model.User;
import to.garazuj.repository.UserRepository;
import to.garazuj.security.SecurityUtils;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;

@Service
public class LoggedUserService {

    private UserRepository userRepository;

    @Autowired
    public LoggedUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void editUser(EditUserForm form){
        User user = SecurityUtils.getCurrentUser();
        if(!form.getFirstName().isEmpty())
            user.setFirstName(form.getFirstName());
        if(!form.getLastName().isEmpty())
            user.setLastName(form.getLastName());
        userRepository.save(user);
    }

    public void deleteAvatar(){
        User user = SecurityUtils.getCurrentUser();
        user.setProfileImage(null);
        userRepository.save(user);
    }

    public void addAvatar(MultipartFile file){
        User user = SecurityUtils.getCurrentUser();
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());

        byte[] imageDataBytes;
        try {
            imageDataBytes = file.getBytes();
        } catch (IOException e){
            throw new FileStorageException("Could not store file " +file.getName() + ". Please try again!", e);
        }

        try {
            user.setProfileImage(ImgResizer.resize(imageDataBytes, ext));
        }
        catch (IOException e){
            throw new FileResizeException("Could not resize " + file.getName() + ". Please try again!", e);
        }
        userRepository.save(user);
    }
}