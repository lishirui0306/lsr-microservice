package cn.lsr.core.ftp.config;

/**
 * @Description: ftp 配置类
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public class FtpConfig {
    /**
     * ftp服务器地址
     */
    public String hostname;
    /**
     * ftp服务器端口号默认为21
     */
    public Integer port = 21 ;
    /**
     * ftp登录账号
     */
    public String username ;
    /**
     * ftp登录密码
     */
    public String password ;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
