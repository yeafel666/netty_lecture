package com.yeafel.protobuf;

/**
 * @author Yeafel
 * 2019/7/28 15:02
 * Do or Die,To be a better man!
 */
public class ProtoBufTest {
    public static void main(String[] args) throws Exception{
        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("张三").setAge(20).setAddress("北京").build();

        byte[] student2ByteArray = student.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());

    }
}
