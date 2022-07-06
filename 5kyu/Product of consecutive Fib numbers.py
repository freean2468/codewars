# https://www.codewars.com/kata/5541f58a944b85ce6d00006a/java

def productFib(prod):
    left = 0 
    right = 1
    while (left * right < prod):
        temp = right
        right = left + right
        left = temp
    return [left, right, True] if left * right == prod else [left, right, False]