package cn.lsr.user.controller.orther;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * = = 其他  图标===
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Controller
public class OrtherController {
    private  String  s = "orther";
    private static final Logger log = LoggerFactory.getLogger(OrtherController.class);
    @RequestMapping("/find/icons")
    public String toIcons(){
        return s+"/icons";
    }
}
