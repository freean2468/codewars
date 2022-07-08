# https://www.codewars.com/kata/554e4a2f232cdd87d9000038/python
def DNA_strand(dna:str) -> str:
    complements = []
    for ch in dna:
        if ch == 'A':
            complements.append('T')
        elif ch == 'T':
            complements.append('A')
        elif ch == 'C':
            complements.append('G')
        elif ch == 'G':
            complements.append('C')
            
    return ''.join(complements)


# translate 기능이 있는 건 처음 알았다.
# import string
# def DNA_strand(dna):
#     return dna.translate(string.maketrans("ATCG","TAGC"))
            