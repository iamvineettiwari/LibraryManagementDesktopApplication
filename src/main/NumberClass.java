/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manas
 */
@XmlRootElement
public class NumberClass implements Serializable {

    private int number_one;
    private int number_two;
    
    public NumberClass() {}

    public NumberClass(int num1, int num2) {
        number_one = num1;
        number_two = num2;
    }

    public int getNumber_one() {
        return number_one;
    }

    public void setNumber_one(int number_one) {
        this.number_one = number_one;
    }

    public int getNumber_two() {
        return number_two;
    }

    public void setNumber_two(int number_two) {
        this.number_two = number_two;
    }

}
