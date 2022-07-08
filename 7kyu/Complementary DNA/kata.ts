// https://www.codewars.com/kata/554e4a2f232cdd87d9000038/typescript
export class Kata {
  static complementTable: Record<string, string> = {
    A: "T",
    T: "A",
    C: "G",
    G: "C",
  };

  static dnaStrand(dna: string) {
    let dnaComplements: string = "";
    for (const ch of dna) {
      dnaComplements += Kata.complementTable[ch];
    }
    return dnaComplements;
  }
}

// 정규표현식 접근법
// export class Kata {
//   static dnaStrand(dna: string) {
//     return dna.replace(/./g, (c) => ({ A: "T", T: "A", G: "C", C: "G" }[c]));
//   }
// }
