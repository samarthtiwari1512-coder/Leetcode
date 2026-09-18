class Solution:
    def maxNumOfSubstrings(self, s: str) -> list[str]:
        first = {}
        last = {}
        
        for i, ch in enumerate(s):
            if ch not in first:
                first[ch] = i
            last[ch] = i
            
        intervals = []
        for ch in first:
            left = first[ch]
            right = last[ch]
            valid = True
            
            j = left
            while j <= right:
                c = s[j]
                if first[c] < left:
                    valid = False
                    break
                right = max(right, last[c])
                j += 1
                
            if valid:
                intervals.append((left, right))
                
        # Sort by end coordinate
        intervals.sort(key=lambda x: x[1])
        
        result = []
        prev_end = -1
        for left, right in intervals:
            if left > prev_end:
                result.append(s[left:right + 1])
                prev_end = right
                
        return result