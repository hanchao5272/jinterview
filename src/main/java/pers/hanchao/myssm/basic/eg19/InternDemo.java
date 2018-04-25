package pers.hanchao.myssm.basic.eg19;

/**
 * <p>String：常量池、intern方法和堆栈对象</p>
 *
 * @author hanchao 2018/4/25 21:26
 **/
public class InternDemo {
    /**
     * <p>String s = "Hello World";//s指向常量池</p>
     * <p>String s = new String("Hello World");//s指向堆栈对象</p>
     * <p>StringBuilder.append()指向堆栈对象</p>
     * <p>"S"+"T";指向常量池</p>
     * <p>s1+s2;指向堆栈对象</p>
     * <p>https://blog.csdn.net/soonfly/article/details/70147205</p>
     *
     * @author hanchao 2018/4/25 21:26
     **/
    public static void main(String[] args) {
        //创建了s1对象，它指向常量池中的"Hello World"
        String s1 = "Hello World";
        //创建了s2对象，它指向堆栈中的"Hello World"对象
        String s2 = new String("Hello World");

        //创建了s3对象，它指向常量池中的"Hello "
        String s3 = "Hello ";
        //创建了s4对象，它指向常量池中的"World"
        String s4 = "World";
        //创建了s44对象，它指向堆栈中的"World"对象
        String s44 = new String("World");
        //编译阶段已完成拼接，所以s5="Hello World"，指向常量池中的"Hello World"
        String s5 = "Hello " + "World";
        //相当于stringBuilder.append()，会创建新的对象
        String s6 = s3 + s4;
        //相当于stringBuilder.append()，会创建新的对象
        String s56 = s3 + s44;

        //s1指向常量池，s2指向堆栈对象，不相等
        System.out.println(s1 == s2);
        //两个都指向常量池，相等
        System.out.println(s1 == s5);
        //s1指向常量池，s6指向堆栈对象，不相等
        System.out.println(s1 == s6);
        //s1指向常量池，s56指向堆栈对象，不相等
        System.out.println(s1 == s56);
        //s1本身就指向向常量池，s6.intern()之后也会指向常量池，相等
        System.out.println(s1 == s6.intern());
        //s2本身指向一个堆栈对象，s2.intern()之后指向常量池，不相等
        System.out.println(s2 == s2.intern());
    }
}
