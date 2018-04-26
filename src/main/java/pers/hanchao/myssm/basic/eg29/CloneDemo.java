package pers.hanchao.myssm.basic.eg29;

import java.io.*;

/**
 * <p>深克隆与浅克隆</p>
 *
 * @author hanchao 2018/4/26 22:59
 **/
public class CloneDemo {
    /////////////////////////////////浅克隆：实现Cloneable接口，重写clone()方法////////////////////////////////

    /**
     * <p>武器</p>
     *
     * @author hanchao 2018/4/26 23:07
     **/
    static class Weapon {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Weapon(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Weapon{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


    /**
     * <p>战士(可以克隆)</p>
     * <p>Object自带的clone()方法时protected的native方法。</p>
     * <p>想要实现clone()方法需要实现Cloneable接口,否则报错CloneNotSupportedException</p>
     *
     * @author hanchao 2018/4/26 23:08
     **/
    static class Warrior implements Cloneable {
        String name;
        int age;
        Weapon weapon;

        public void setWeapon(Weapon weapon) {
            this.weapon = weapon;
        }

        public Weapon getWeapon() {
            return weapon;
        }

        @Override
        public String toString() {
            return "Warrior{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", weapon=" + weapon +
                    '}';
        }

        public Warrior(String name, int age, Weapon weapon) {
            this.name = name;
            this.age = age;
            this.weapon = weapon;
        }

        public Object clone() throws CloneNotSupportedException {

            return super.clone();
        }

    }

    /////////////////////////////////////深克隆：实现Serializable接口，编写deepClone()方法//////////////////////////////////

    /**
     * <p>武器</p>
     * <p>被引用的对象也要实现Serializable接口</p>
     *
     * @author hanchao 2018/4/26 23:28
     **/
    static class Arm implements Serializable {
        String name;

        public Arm(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Arm{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * <p>战士(可复制)</p>
     * <p>深克隆：实现Serializable接口</p>
     *
     * @author hanchao 2018/4/26 23:29
     **/
    static class Soldier implements Serializable {
        String name;
        int age;
        Arm Arm;

        @Override
        public String toString() {
            return "Soldier{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", Arm=" + Arm +
                    '}';
        }

        public CloneDemo.Arm getArm() {
            return Arm;
        }

        public void setArm(CloneDemo.Arm arm) {
            Arm = arm;
        }

        public Soldier(String name, int age, CloneDemo.Arm arm) {
            this.name = name;
            this.age = age;
            Arm = arm;
        }

        public Object deepClone() throws IOException, ClassNotFoundException {
            //定义byte数组输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //定义对象输出流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            //输出当前对象
            oos.writeObject(this);

            //定义byte数组输入流
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            //定义对象输入流
            ObjectInputStream ois = new ObjectInputStream(bais);
            //读取流中的对象
            return ois.readObject();
        }
    }

    /**
     * <p>浅克隆与深克隆</p>
     *
     * @author hanchao 2018/4/26 23:09
     **/
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        //实现Cloneable接口是浅克隆：仅仅克隆对象本身，而不克隆对象引用的对象
        Warrior warrior = new Warrior("张三", 18, new Weapon("斧子"));

        //无论浅克隆还是深克隆，克隆的对象值时一样的
        System.out.println("warrior : " + warrior);
        Warrior warrior1 = (Warrior) warrior.clone();
        System.out.println("warrior1 : " + warrior1);

        //被克隆的对象不是同一对象
        System.out.println("warrior == warrior1 : " + (warrior == warrior1));
        //被浅克隆的对象的引用对象还是同一对象
        System.out.println("warrior.getWeapon() == warrior1.getWeapon() : " + (warrior.getWeapon() == warrior1.getWeapon()));
        //修改被浅克隆的对象的引用对象，也会造成原对象的引用对象的编号
        warrior.getWeapon().setName("砍刀");
        System.out.println("warrior : " + warrior);
        System.out.println("warrior1 : " + warrior1);

        System.out.println();
        //实现Serializable接口的是深克隆：不仅仅克隆对象本身，对象内部的引用也被克隆
        Soldier soldier = new Soldier("李四", 20, new Arm("小米步枪"));
        System.out.println("soldier : " + soldier);

        //深克隆
        Soldier soldier1 = (Soldier) soldier.deepClone();
        //无论浅克隆还是深克隆，克隆的对象值时一样的
        System.out.println("soldier1 : " + soldier1);

        //被克隆的对象不是同一对象
        System.out.println("soldier == soldier1 : " + (soldier1 == soldier1));
        //被深克隆的对象的引用对象还是同一对象
        System.out.println("soldier.getArm() == soldier1.getArm() : " + (soldier.getArm() == soldier1.getArm()));
        //修改被深克隆的对象的引用对象，不会造成原对象的引用对象的编号
        soldier.getArm().setName("飞机大炮");
        System.out.println("soldier : " + soldier);
        System.out.println("soldier1 : " + soldier1);

        //其他类似的克隆
        //1.繁琐的深克隆：重写clone()方法时，也调用引用对象的clone()方法[前提：被应用类也实现了clone()方法]。
        //2.变相的深克隆：将对象转化成JSON串，再将JSON串转化成一个新的对象。
    }
}
