class Solution:
    def checkValidString(self, s: str) -> bool:
        c_min = c_max = 0
        
        for ch in s:
            if ch == '(':
                c_min += 1
                c_max += 1
            elif ch == ')':
                c_min -= 1
                c_max -= 1
            else:  # ch == '*'
                c_min -= 1
                c_max += 1
            
            if c_max < 0:
                return False
            
            c_min = max(c_min, 0)
            
        return c_min == 0