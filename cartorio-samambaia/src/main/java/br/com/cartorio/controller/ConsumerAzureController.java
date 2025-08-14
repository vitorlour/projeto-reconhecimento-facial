package br.com.cartorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import br.com.cartorio.entity.Imagem;
import br.com.cartorio.http.request.HttpRequestApi;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class ConsumerAzureController {

    private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/png");
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

    @Autowired
    private HttpRequestApi httpResquestApi;

    @PostMapping("/reconhecimento/")
    public ModelAndView reconhecimento(@RequestParam("fileUpload") MultipartFile fileUpload) throws IOException {
        if (fileUpload.isEmpty()) {
            // return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
            // Returning a model and view with an error message is more appropriate for this application
            ModelAndView mv = new ModelAndView("reconhecimentofacial");
            mv.addObject("errorMessage", "Please select a file to upload.");
            return mv;
        }

        if (!ALLOWED_CONTENT_TYPES.contains(fileUpload.getContentType())) {
            ModelAndView mv = new ModelAndView("reconhecimentofacial");
            mv.addObject("errorMessage", "Only JPEG and PNG images are allowed.");
            return mv;
        }

        if (fileUpload.getSize() > MAX_FILE_SIZE) {
            ModelAndView mv = new ModelAndView("reconhecimentofacial");
            mv.addObject("errorMessage", "File size must be less than 10MB.");
            return mv;
        }

        ModelAndView mv = new ModelAndView("pessoasidentificadas");
        Imagem imagem = new Imagem(fileUpload.getBytes());
        mv.addObject("pessoasIdentificadas", httpResquestApi.reconhecimentoFacial(imagem));
        return mv;
    }
}
