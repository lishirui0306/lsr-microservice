package cn.lsr.user.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 无权限跳转页面设置
 * @ProjectName: lsr-boot-cloud
 * @ClassName: ErrorController
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Controller
public class ErrorController {
    /**
     * 功能描述: <br>
     * 〈〉无权限跳转页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Hacker_lsr@126.com
     */
    @RequestMapping("/403")
    public String to403(){
        return "error/403";
    }
}
