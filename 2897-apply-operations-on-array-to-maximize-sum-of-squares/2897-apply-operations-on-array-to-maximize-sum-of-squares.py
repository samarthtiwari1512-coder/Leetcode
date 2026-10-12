class Solution:
    def maxSum(self, nums: list[int], k: int) -> int:
        MOD = 1_000_000_007
        bit_counts = [0] * 31
        
        for x in nums:
            for b in range(31):
                if (x >> b) & 1:
                    bit_counts[b] += 1
                    
        ans = 0
        for i in range(k):
            val = 0
            for b in range(31):
                if bit_counts[b] > i:
                    val |= 1 << b
            ans = (ans + val * val) % MOD
            
        return ans