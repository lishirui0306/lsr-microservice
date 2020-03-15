package cn.lsr.user.test;

/**
 * @Description:
 * @Package: lsr-microservice
 * @author: Hacker_lsr@126.com
 **/
public  enum test {
    none("none","none","c"),
    NONE("none","NONE","全局缓存"),
    TRANS("trans","trans","全局缓存"),
    TS("t","t","全局缓存");
    private String id ;
    private String value;
    private String name;
    private final static test[] values = test.values();
    test(String id ,String value,String name){
        this.id = id;
        this.value = value;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        test[] values = test.values();
        for (int i = 0; i < test.values().length; i++) {
            System.out.println(values[i]);
        }
        System.out.println();
        System.out.println(test.valueOf("NONE"));
    }
}