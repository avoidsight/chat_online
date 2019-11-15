package com.example.demojpa.model;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author: zp
 * @Date: 2019-10-16 16:51
 * @Description:
 */
@Data
public class Adult {
    @Min(value = 18,message = "成人必须大于18岁")
    Integer age;

    public static void main(String[] args) {
        Adult a = new Adult();
        a.setAge(12);
        System.out.println("a.toString() = " + a.toString());
    }
}
