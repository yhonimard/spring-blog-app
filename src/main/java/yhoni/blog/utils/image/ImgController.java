package yhoni.blog.utils.image;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/image")
public class ImgController {
    @Autowired
    private ImgService imgService;

    @Autowired
    private ImgRepository imgRepository;

    @PostMapping("/save")
    public String upload(@RequestParam("file") MultipartFile file,
                         @RequestParam("name") String name
    ) throws IOException {
        return imgService.uploadImg(file, name);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id) {
        Optional<Img> byId = imgRepository.findById(id);

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.IMAGE_PNG).body(byId.get().getImage());
    }

}
