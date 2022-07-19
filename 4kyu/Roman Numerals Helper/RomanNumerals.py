# https://www.codewars.com/kata/51b66044bce5799a7f000003/python
import math

roman_table = {"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M":1000 }
roman_table_keys = list(roman_table)

class RomanNumerals:
    def to_roman(val: int) -> str:
        roman_num = ''
        max_digits = 10**int(math.log10(val))

        while max_digits >= 1:
            quotient = int(val / max_digits) % 10
            quotient_clone = quotient
            digits = int(math.log10(val))+1
            roman_table_index = 0 + ((digits - 1) * 2)

            if quotient <= 3:
                while quotient > 0:
                    roman_num += roman_table_keys[roman_table_index]
                    quotient -= 1
            elif quotient == 4:
                roman_num += roman_table_keys[roman_table_index] + roman_table_keys[roman_table_index + 1]
            elif quotient <= 8:
                roman_num += roman_table_keys[roman_table_index + 1]
                quotient -= 5
                while quotient > 0:
                    roman_num += roman_table_keys[roman_table_index]
                    quotient -= 1
            else:
                roman_num += roman_table_keys[roman_table_index] + roman_table_keys[roman_table_index + 2]

            val -= quotient_clone * max_digits
            
            if val == 0:
                break
            max_digits /= 10

        return roman_num

    def from_roman(roman_num: str) -> int:
        sum = 0
        while len(roman_num) > 1:
            left = roman_table[roman_num[0]]
            right = roman_table[roman_num[1]]
            sum += left if left >= right else -left
            roman_num = roman_num[1:]

        sum += roman_table[roman_num[0]]

        return sum

# import string
# from collections import OrderedDict

# class RomanNumerals:
#   @classmethod
#   def to_roman(self, num):
#     conversions = OrderedDict([('M',1000), ('CM',900), ('D', 500), ('CD',400), ('C',100), ('XC',90), ('L',50), ('XL',40),
#                                ('X',10), ('IX',9), ('V',5), ('IV',4), ('I',1)])
#     out = ''
#     for key, value in conversions.iteritems():
#       while num >= value:
#         out += key
#         num -= value
#     return out
  
#   @classmethod
#   def from_roman(self, roman):
#     conversions = OrderedDict([('CM',900), ('CD',400), ('XC',90), ('XL',40), ('IX',9), ('IV',4), ('M',1000), ('D',500),
#                                ('C',100), ('L',50), ('X',10), ('V',5), ('I',1)])
#     out = 0
#     for key, value in conversions.iteritems():
#       out += value * roman.count(key)
#       roman = string.replace(roman, key, "")
#     return out