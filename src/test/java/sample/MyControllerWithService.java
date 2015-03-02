package sample;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyControllerWithService {

    private MyResponseService myResponseService;

    @RequestMapping(value = "/json", method = RequestMethod.GET, consumes = "application/json", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> json() {
        return myResponseService.getResponse();
    }

    public MyControllerWithService withMyResponseService(MyResponseService myResponseService) {
        this.myResponseService = myResponseService;
        return this;
    }
}
