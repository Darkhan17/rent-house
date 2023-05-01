package kz.kbtu.renthouse.controller;

import kz.kbtu.renthouse.service.IAwsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.List;


@RestController
@RequestMapping("aws")
@RequiredArgsConstructor
public class AwsController {

    private final IAwsService awsService;

    @DeleteMapping()
    public void deleteFile(@RequestParam(required = true) String fileName) throws Exception {
        awsService.deleteObj(fileName);
    }

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity<URL> uploadFile(@RequestPart(value = "file") MultipartFile file,
                                          @RequestParam(required = false) String folderName) throws IOException {
        return new ResponseEntity<>(awsService.putObj(file, folderName), HttpStatus.OK);
    }


    @GetMapping("/urls/list")
    public ResponseEntity<List<URL>> getUrls(@RequestParam(required = false) String folderName,
                                             @RequestParam(required = false) String workspaceId) {
        return new ResponseEntity<>(awsService.getObjects(folderName, workspaceId), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> filesNameList() {
        return new ResponseEntity<>(awsService.getListObj(), HttpStatus.OK);
    }

    @GetMapping("/urls")
    public ResponseEntity<List<URL>> urlList() {
        return new ResponseEntity<>(awsService.getObjUrls(), HttpStatus.OK);
    }

    @GetMapping("/link")
    public URL urlByFileName(@RequestParam String name) {
        return awsService.getUrlByName(name);
    }

}
