package to.garazuj.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import to.garazuj.exception.FileStorageException;
import to.garazuj.exception.MyFileNotFoundException;
import to.garazuj.model.Car;
import to.garazuj.model.DBFile;
import to.garazuj.repository.DBFileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    @Transactional
    public DBFile storeFile(MultipartFile file, Car car) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
                if(!car.equals(null))
                    dbFile.setCar(car);

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Transactional
    public DBFile storeFile(MultipartFile file) {
        return storeFile(file,null);
    }

    @Transactional(readOnly = true)
    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}