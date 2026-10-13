class Solution:
    def maxAlternatingSum(self, nums: list[int]) -> int:
        squares = sorted([x * x for x in nums], reverse=True)
        pos_count = (len(nums) + 1) // 2
        
        return sum(squares[:pos_count]) - sum(squares[pos_count:])