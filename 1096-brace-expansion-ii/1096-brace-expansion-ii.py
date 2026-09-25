class Solution:
    def braceExpansionII(self, expression: str) -> list[str]:
        i = 0
        n = len(expression)

        def parse_expr() -> set[str]:
            nonlocal i
            current = {""}
            while i < n and expression[i] not in "},":
                nxt = parse_factor()
                current = {a + b for a in current for b in nxt}
            return current

        def parse_factor() -> set[str]:
            nonlocal i
            if expression[i] == '{':
                i += 1  # consume '{'
                union_set = set()
                while True:
                    union_set |= parse_expr()
                    if i < n and expression[i] == ',':
                        i += 1  # consume ','
                    else:
                        break
                i += 1  # consume '}'
                return union_set
            else:
                start = i
                while i < n and expression[i].isalpha():
                    i += 1
                return {expression[start:i]}

        return sorted(list(parse_expr()))