class Solution:
    def reverseDegree(self, s: str) -> int:
        total_sum = 0
        for i, ch in enumerate(s):
            char_val = 26 - (ord(ch) - ord('a'))
            total_sum += char_val * (i + 1)
        return total_sum