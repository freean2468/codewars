// https://www.codewars.com/kata/54b724efac3d5402db00065e/typescript
import { MORSE_CODE } from "./preloaded";

export function decodeMorse(morseCode: string): string {
  let result: string = "";
  const morseCodeCopy: string[] = morseCode
    .replace(/(^\s{3,})|(\s{3,}$)/gi, "")
    .replace(/\s{3,}/gi, ` s `)
    .split(` `)
    .filter((v) => v !== "");
  morseCodeCopy.map((v) => (result += v === "s" ? " " : MORSE_CODE[v]));

  return result;
}

// trim을 사용하면 regex를 활용한 replace를 대신할 수 있다.
// import { MORSE_CODE } from "./preloaded";
// MORSE_CODE["_"] = " ";
// export function decodeMorse(morseCode: string): string {
//   return morseCode
//     .trim()
//     .replace(/   /g, " _ ")
//     .split(" ")
//     .map((w) => MORSE_CODE[w])
//     .join("");
// }
