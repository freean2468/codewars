# https://www.codewars.com/kata/52685f7382004e774f0001f7/python
def make_readable(seconds: int)->str:
    s = seconds % 60
    h = seconds / (60 * 60)
    m = seconds / 60 % 60
    
    return "%02d:%02d:%02d" % (h, m, s)

# def make_readable(s):
#     return '{:02}:{:02}:{:02}'.format(s / 3600, s / 60 % 60, s % 60)