package test;

/**
 * @ClassName TestService
 * @Description TODO
 * @Author hasee
 * @Date 2018-07-04 10:38
 * Version 1.0
 */
public class TestService {
    private String name;

    public void hello(){
        System.out.println("hello " + getName());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
