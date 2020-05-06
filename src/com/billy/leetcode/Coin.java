package com.billy.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Billy
 * @description: 凑硬币
 *  有 k 种面值的硬币，面值分别为 c1, c2 ... ck，每种硬币的数量无限，再给一个总金额 amount，
 *  问最少需要几枚硬币凑出这个金额，如果不可能凑出，算法返回 -1
 *
 *  分析：
 *      该题属于动态规划问题，具有【最优子结构】
 *   函数签名：
 *      int coinChange(int[] coins, int amount)
 * @date 2020/4/30 11:04
 */
public class Coin {

    public static void main(String[] args) {

        int[] coins = {1, 2, 5};
        int dp = new Coin().dp(coins, 100);
        System.out.println(dp);


    }


    /**
     *
     * 如何列出状态转移方程
     *      先确定状态，总金额 amount
     *      其次，确定 dp 函数
     *      最后，确定 选择并择优
     */
    int coinChange(int[] coins, int amount){

        return dp(coins, amount);
    }
    private Map<Integer, Integer> memo = new HashMap<>();

    /**
     *  自上而下
     * @param coins
     * @param n
     * @return
     */
    private int dp(int[] coins, int n) {

        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        if (n == 0) {
            return 0;
        }
        if (n < 0) {
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dp(coins,n - coin);
            if (sub == -1) {
                continue;
            }
            res = Math.min(res, 1 + sub);
        }
        memo.put(n, res != Integer.MAX_VALUE ? res: -1);
        return memo.get(n);
    }


    /**
     * 自下而上
     * @param coins
     * @param amount
     * @return
     * 1, 2, 5
     *  11
     */
    private int coinChangeDown(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {

            for (int coin : coins) {
                if (i < coin) {
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];

    }

}
