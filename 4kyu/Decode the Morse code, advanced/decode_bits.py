import sys
import re

def decode_bits(bits: str) -> str:
    trimed = re.sub(r'^0+|0+$', '', bits)
    found:[str] = re.findall(r'1+|0+', trimed)
    unit = sys.maxsize
    for set in found:
        unit = len(set) if len(set) < unit else unit
        
    duplicatesRemoved = re.sub('0{'+str(unit)+'}', '0', re.sub('1{'+str(unit)+'}', '1', bits))
    spaceReplaced = re.sub(r'0{7}', '   ', duplicatesRemoved)
    dashReplaced = re.sub(r'1{3}', '-', spaceReplaced)
    pauseReplaced = re.sub(r'0{3}', ' ', dashReplaced)
    dotReplaced = re.sub(r'1{1}', '.', pauseReplaced)
    blankReplaced = re.sub(r'0{1}', '', dotReplaced)
    
    return blankReplaced


def decode_morse(morseCode: str) -> str:
    MORSE_CODE['_'] = ' '
    manipulated = morseCode.strip().replace('   ', ' _ ').split(' ')
    return ''.join(list(map(lambda v: MORSE_CODE[v], manipulated)))


# strip과 replace로 간편하게 구현 가능.
# double colons(::)로 hopping through list도 신박하다.
# def decodeBits(bits):
#     import re
    
#     # remove trailing and leading 0's
#     bits = bits.strip('0')
    
#     # find the least amount of occurrences of either a 0 or 1, and that is the time hop
#     time_unit = min(len(m) for m in re.findall(r'1+|0+', bits))
    
#     # hop through the bits and translate to morse
#     return bits[::time_unit].replace('111', '-').replace('1','.').replace('0000000','   ').replace('000',' ').replace('0','')

# def decodeMorse(morseCode):
#     return ' '.join(''.join(MORSE_CODE[l] for l in w.split()) for w in morseCode.split('   '))