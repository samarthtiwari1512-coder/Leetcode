class Solution:
    def resultArray(self, nums: list[int], k: int) -> list[int]:
        result = [0] * k
        dp = [0] * k  # dp[r] is the count of subarrays ending at current index with product % k == r

        for num in nums:
            next_dp = [0] * k
            mod_val = num % k

            # Subarray consisting of just [num]
            next_dp[mod_val] += 1

            # Extend previous subarrays
            for r in range(k):
                if dp[r]:
                    new_rem = (r * mod_val) % k
                    next_dp[new_rem] += dp[r]

            # Accumulate into overall result
            for r in range(k):
                result[r] += next_dp[r]

            dp = next_dp

        return result