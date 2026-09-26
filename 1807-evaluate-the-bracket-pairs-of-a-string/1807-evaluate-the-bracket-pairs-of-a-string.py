class Solution:
    def evaluate(self, s: str, knowledge: list[list[str]]) -> str:
        d = {k: v for k, v in knowledge}
        
        res = []
        key = []
        inside = False
        
        for ch in s:
            if ch == '(':
                inside = True
                key = []
            elif ch == ')':
                inside = False
                res.append(d.get("".join(key), '?'))
            else:
                if inside:
                    key.append(ch)
                else:
                    res.append(ch)
                    
        return "".join(res)