# https://www.codewars.com/kata/514b92a657cdc65150000006/solutions/python

def solution(number:int):
    sum = 0
    
    for x in range (1, number):
        sum += x if x % 3 == 0 or x % 5 == 0 else 0
    
    return sum
  