package com.ant.java8.ch1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by timch on 2016/9/21.
 */
public class ApplePredicate {




    public List<Apple> filterApple(List<Apple> inventory, Predicate<Apple> predicate){
        List<Apple> chosenApples = new ArrayList<>();
        for(Apple apple : inventory){
            if(predicate.test(apple)){
                chosenApples.add(apple);
            }
        }
        return chosenApples;
    }


    public static boolean isGreenApple(Apple apple) {
        return Apple.GREEN.equals(apple.getColor());
    }

    public static boolean isRedApple(Apple apple) {
        return Apple.RED.equals(apple.getColor());
    }
}
