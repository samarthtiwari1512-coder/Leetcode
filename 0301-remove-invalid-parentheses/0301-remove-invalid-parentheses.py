from collections import deque

class Solution:
    def removeInvalidParentheses(self, s: str) -> list[str]:
        def is_valid(string: str) -> bool:
            count = 0
            for ch in string:
                if ch == '(':
                    count += 1
                elif ch == ')':
                    count -= 1
                    if count < 0:
                        return False
            return count == 0

        queue = deque([s])
        visited = {s}
        result = []
        found = False

        while queue:
            curr = queue.popleft()

            if is_valid(curr):
                result.append(curr)
                found = True

            # If valid strings have been found at this level, don't generate deeper strings
            if found:
                continue

            for i, ch in enumerate(curr):
                if ch not in ('(', ')'):
                    continue
                nxt = curr[:i] + curr[i + 1:]
                if nxt not in visited:
                    visited.add(nxt)
                    queue.append(nxt)

        return result