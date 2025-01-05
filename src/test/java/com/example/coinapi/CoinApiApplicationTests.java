package com.example.coinapi;

import com.example.coinapi.controller.CoinController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CoinApiApplicationTests {

    @Autowired
    private CoinController coinController;

    @Test
    public void testCalculateMinimumCoins_Example1() {
        // 测试示例 1
        double targetAmount = 7.03;
        List<Double> denominations = new ArrayList<>(Arrays.asList(0.01, 0.5, 1.0, 5.0, 10.0)); // 使用可变列表
        ResponseEntity<List<Double>> response = coinController.calculateMinimumCoins(targetAmount, denominations);

        // 期望的输出
        List<Double> expected = Arrays.asList(0.01, 0.01, 0.01, 1.0, 1.0, 5.0);

        assertEquals(expected, response.getBody());
    }

    @Test
    public void testCalculateMinimumCoins_Example2() {
        // 测试示例 2
        double targetAmount = 103.0;
        List<Double> denominations = new ArrayList<>(Arrays.asList(1.0, 2.0, 50.0)); // 使用可变列表
        ResponseEntity<List<Double>> response = coinController.calculateMinimumCoins(targetAmount, denominations);

        // 期望的输出
        List<Double> expected = Arrays.asList(1.0, 2.0, 50.0, 50.0);

        assertEquals(expected, response.getBody());
    }

    @Test
    public void testCalculateMinimumCoins_InvalidTargetAmount() {
        // 测试无效目标金额
        double targetAmount = -5.0;
        List<Double> denominations = new ArrayList<>(Arrays.asList(0.01, 0.5, 1.0, 5.0)); // 使用可变列表
        ResponseEntity<List<Double>> response = coinController.calculateMinimumCoins(targetAmount, denominations);

        // 期望返回空列表
        assertEquals(Arrays.asList(), response.getBody());
    }

    @Test
    public void testCalculateMinimumCoins_EmptyDenominations() {
        // 测试面值为空
        double targetAmount = 7.03;
        List<Double> denominations = new ArrayList<>(); // 使用可变列表
        ResponseEntity<List<Double>> response = coinController.calculateMinimumCoins(targetAmount, denominations);

        // 期望返回空列表
        assertEquals(Arrays.asList(), response.getBody());
    }

    @Test
    public void testCalculateMinimumCoins_InvalidDenominations() {
        // 测试无效面值
        double targetAmount = 7.03;
        List<Double> denominations = new ArrayList<>(Arrays.asList(0.03, 0.1, 1.0)); // 使用可变列表
        ResponseEntity<List<Double>> response = coinController.calculateMinimumCoins(targetAmount, denominations);

        // 期望返回空列表
        assertEquals(Arrays.asList(), response.getBody());
    }
}
