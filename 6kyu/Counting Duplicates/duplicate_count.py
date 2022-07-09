# https://www.codewars.com/kata/54bf1c2cd5b56cc47f0007a1/python
def duplicate_count(text: str):
    set = {}
    text = text.lower()
    for i, ch in enumerate(text):
        if text.index(ch) != i:
            set[ch] = ch
            
    return len(set)