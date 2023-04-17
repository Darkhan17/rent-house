package kz.kbtu.renthouse.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public interface IAwsService {

    public URL putObj(MultipartFile file, String userId) throws IOException;
    public void deleteObj(String fileName);

    public List<String> getListObj();

    public List<URL> getObjUrls();

    public URL getUrlByName(String name);

    List<URL> getObjects(String userId, String workspaceId);
}
