// https://www.codewars.com/kata/51b6249c4612257ac0000005/train/typescript

type RomanNumeral = {
  I: number;
  V: number;
  X: number;
  L: number;
  C: number;
  D: number;
  M: number;
};

const table: RomanNumeral = {
  I: 1,
  V: 5,
  X: 10,
  L: 50,
  C: 100,
  D: 500,
  M: 1000,
};

export function solution(roman: string): number {
  // complete the solution by transforming the
  // string roman numeral into an integer
  const left: keyof RomanNumeral | null =
    (roman[0] as keyof RomanNumeral) ?? null;
  const right: keyof RomanNumeral | null =
    (roman[1] as keyof RomanNumeral) ?? null;

  if (left === null) {
    return 0;
  }

  if (right === null) {
    return table[left];
  }

  if (table[left] < table[right]) {
    return table[right] - table[left] + solution(roman.substring(2));
  }

  return table[left] + solution(roman.substring(1));
}

// Record를 사ㅇ하면 더 간단히 구현할 수 있다.
// const values: Record<string, number> = {
//   I: 1,
//   V: 5,
//   X: 10,
//   L: 50,
//   C: 100,
//   D: 500,
//   M: 1000,
// }

// export function solution(roman: string): number {
//   let value = 0
//   for (let i = 0; i < roman.length; i++) {
//     const current = values[roman[i]]
//     const next = values[roman[i + 1]] || 0
//     if (current < next) {
//       value -= current
//     } else {
//       value += current
//     }
//   }
//   return value
// }
