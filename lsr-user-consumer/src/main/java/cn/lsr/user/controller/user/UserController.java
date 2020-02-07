package cn.lsr.user.controller.user;

import cn.lsr.redis.utils.RedisResult;
import cn.lsr.redis.utils.UrlUtils;
import cn.lsr.user.entity.user.User;
import cn.lsr.user.mapper.user.UserMapper;
import cn.lsr.utils.PageUtils;
import cn.lsr.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * = =  用户控制器
 *
 * @Version: 1.0
 * @Author: Hacker_lsr@126.com
 */
@Api(tags = "用户信息控制器")
@Controller
public class UserController {
    //远程调用的consul 注册的服务名
    @Value("${remote.server.name}")
    private String remote;
    @Autowired
    private RestTemplate restTemplate;
    // 获取consul 服务信息
    @Autowired
    private DiscoveryClient discoveryClient;
    /**
     * 功能描述: <br>
     * 〈〉用户mapper
     * @Param:
     * @Return:
     * @Author: Hacker_lsr@126.com
     */
    @Resource
    private UserMapper userMapper;
    private static String s = "user";
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    /**
     * 功能描述: <br>
     * 〈〉open 用户列表页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Hacker_lsr@126.com
     */
    @RequestMapping("/toUser")
    private String toUser(){
        return s+"/user_find";
    }
    /**
     * 功能描述: <br>
     * 〈〉查看用户信息
     * @Param: []
     * @Return: com.lsr.common.utils.PageUtils
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("select")
    @RequestMapping("/userlist")
    @ResponseBody
    public PageUtils listUser(@RequestParam Map<String, Object> params){
        int pageBNum = Integer.parseInt(params.get("page").toString());
        int pageSize = Integer.parseInt(params.get("limit").toString());
        //获取consul 上的服务
        List<ServiceInstance> redislock = discoveryClient.getInstances("lsr-redis-lock");
        String servername;
        if (redislock != null && redislock.size()>0) {
            servername = redislock.get(0).getUri().toString();
            System.out.println("获取到的服务地址："+servername);
            String s = System.currentTimeMillis()+"";
            ResponseEntity<RedisResult> entity = restTemplate.getForEntity(UrlUtils.remouldUrl(servername+"/getRedisLock", "3", s), RedisResult.class);
            if (entity.getBody().getStatus()==200){
                System.out.println("============开始业务逻辑");
            }else{
                throw new RuntimeException(entity.getBody().getMessages());
            }
            ResponseEntity<RedisResult> out =restTemplate.getForEntity(UrlUtils.remouldUrl(servername+"/unRedisLock","3",s),RedisResult.class);
        }
        Page page = PageHelper.startPage(pageBNum,pageSize);
        List<User> list = userMapper.selectAll();
        return  new PageUtils(list,page.getTotal());
    }

    /**
     * 功能描述: <br>
     * 〈〉open 新增页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("admin")
    @RequestMapping("/user/add")
    public String toadd(){
        return s+"/user_add";
    }
    /**
     * 功能描述: <br>
     * 〈〉新增
     * @Param: [user]
     * @Return: com.lsr.common.utils.Result
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("insert")
    @RequestMapping("/add/user")
    @ResponseBody
    public Result addUser(User user){

        user.setUid(UUID.randomUUID().toString());
        int s = userMapper.insertSelective(user);
        if(s!=0){
            return Result.success("操作成功！");
        }
        return Result.error("操作失败！");
    }
    /**
     * 功能描述: <br>
     * 〈〉修改更新
     * @Param: [user]
     * @Return: com.lsr.common.utils.Result
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("update")
    @RequestMapping("/updateUser")
    @ResponseBody
    public Result updateUser(User user){
        userMapper.updateByPrimaryKeySelective(user);
        return Result.success("操作成功！");
    }
    /**
     * 功能描述: <br>
     * 〈〉根据主键删除
     * @Param: [uid]
     * @Return: com.lsr.common.utils.Result
     * @Author: Hacker_lsr@126.com
     */
    @RequiresPermissions("delete")
    @RequestMapping("/delete/user")
    @ResponseBody
    public Result deleteUser(String uid){
        userMapper.deleteByPrimaryKey(uid);
        return Result.success("操作成功");
    }

}
