class Solution:
    def removeOuterParentheses(self, s: str) -> str:
        res = []
        opened = 0
        
        for ch in s:
            if ch == '(':
                if opened > 0:
                    res.append(ch)
                opened += 1
            else:
                opened -= 1
                if opened > 0:
                    res.append(ch)
                    
        return "".join(res)