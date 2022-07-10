# https://www.codewars.com/kata/54b724efac3d5402db00065e/python
def decode_morse(morse_code: str) -> str:
    MORSE_CODE['_'] = ' '
    manipulated = morse_code.strip().replace('   ', ' _ ').split(' ')
    return ''.join(list(map(lambda v: MORSE_CODE[v], manipulated)))
