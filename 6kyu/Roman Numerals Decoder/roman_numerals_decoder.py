# https://www.codewars.com/kata/51b6249c4612257ac0000005/python

roman_table = {"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M":1000 }

def solution(roman: str) -> int:
    sum = 0
    while len(roman) > 1:
        left = roman_table[roman[0]]
        right = roman_table[roman[1]]
        sum += left if left >= right else -left
        roman = roman[1:]
        
    sum += roman_table[roman[0]]
    
    return sum
    