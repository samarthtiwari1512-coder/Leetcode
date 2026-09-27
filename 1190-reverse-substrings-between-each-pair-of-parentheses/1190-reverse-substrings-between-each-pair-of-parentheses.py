class Solution:
    def reverseParentheses(self, s: str) -> str:
        n = len(s)
        pair = [0] * n
        stack = []
        
        # Step 1: Pair matching parentheses
        for i, ch in enumerate(s):
            if ch == '(':
                stack.append(i)
            elif ch == ')':
                open_idx = stack.pop()
                pair[open_idx] = i
                pair[i] = open_idx
                
        # Step 2: Traverse string with directional jumps
        res = []
        i = 0
        direction = 1
        
        while i < n:
            ch = s[i]
            if ch in '()':
                i = pair[i]
                direction = -direction
            else:
                res.append(ch)
            i += direction
            
        return "".join(res)