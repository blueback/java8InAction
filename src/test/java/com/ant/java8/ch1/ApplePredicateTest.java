package com.ant.java8.ch1; 




import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/** 
* ApplePredicate Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 21, 2016</pre> 
* @version 1.0 
*/ 
public class ApplePredicateTest { 

    List<Apple> inventory = new ArrayList<>();


    ApplePredicate applePredicate;

    @Before
    public void BeforeAll() throws Exception {
        inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        applePredicate = new ApplePredicate();
    }

    @Test
    public void testFilterGreenApplesByFun(){
        List<Apple> greenApples = applePredicate.filterApple(inventory, ApplePredicate::isGreenApple);
        Assert.assertNotNull(greenApples);
    }

    @Test
    public void testFilterRedAppleByFun(){
        List<Apple> redApples = applePredicate.filterApple(inventory,ApplePredicate::isRedApple);
        Assert.assertNotNull(redApples);
        Assert.assertEquals(Apple.RED,redApples.get(0).getColor());
    }

    @Test
    public void testFilterGreenAppleByLambda(){
        List<Apple> greenApples = applePredicate.filterApple(inventory,(Apple a)->Apple.GREEN.equals(a.getColor()));
        Assert.assertNotNull(greenApples);
        Assert.assertEquals(Apple.GREEN,greenApples.get(0).getColor());
    }

    @Test
    public void testFilterRedAppleByLambda(){
        List<Apple> redApples = applePredicate.filterApple(inventory,(Apple a)->Apple.RED.equals(a.getColor()));
        Assert.assertNotNull(redApples);
        Assert.assertEquals(Apple.RED,redApples.get(0).getColor());
    }

    @Test
    public void testFilterForSyncStream(){
        List<Apple> filterInventory = new ArrayList<>();
        int i = 0;
        while (i<10000){
            int heavy =(int)(Math.random()*100);
            filterInventory.add(new Apple(heavy,i%2==0?Apple.RED:Apple.GREEN));
            i++;
        }
        long currTime = System.currentTimeMillis();
        List<Apple> heavyApples = filterInventory.stream().filter((Apple a) -> a.getWeight()>5).collect(Collectors.toList());
        System.out.println(heavyApples.size());
        System.out.println(System.currentTimeMillis()-currTime);

        currTime = System.currentTimeMillis();
        heavyApples = filterInventory.parallelStream().filter((Apple a)->a.getWeight()>5).collect(Collectors.toList());
        System.out.println(heavyApples.size());
        System.out.println(System.currentTimeMillis()-currTime);
    }

} 
