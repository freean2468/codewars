// https://www.codewars.com/kata/54b72c16cd7f5154e9000457/typescript
import { MORSE_CODE } from "./preloaded";

const removeDuplicates = (bits: string): string => {
  const oneSequence = new RegExp("1{1,}", "g");
  const zeroSequence = new RegExp("0{1,}", "g");
  const oneMatch = [...(bits.match(oneSequence) ?? [])];
  const zeroMatch = [...(bits.match(zeroSequence) ?? [])];
  let oneMinLength = 10000;
  let zeroMinLength = 10000;

  for (const seq of oneMatch) {
    oneMinLength = oneMinLength < seq.length ? oneMinLength : seq.length;
  }

  for (const seq of zeroMatch) {
    zeroMinLength = zeroMinLength < seq.length ? zeroMinLength : seq.length;
  }

  const minLength = oneMinLength < zeroMinLength ? oneMinLength : zeroMinLength;

  const oneReg = new RegExp(`1{${minLength}}`, "g");
  const zeroReg = new RegExp(`0{${minLength}}`, "g");

  return bits.replace(oneReg, "1").replace(zeroReg, "0");
};

export const decodeBits = (bits: string) => {
  return removeDuplicates(bits.replace(/^0{1,}|0{1,}$/gm, ""))
    .replace(/0{7}/gm, "   ")
    .replace(/1{3}/gm, "-")
    .replace(/0{3}/gm, " ")
    .replace(/1{1}/gm, ".")
    .replace(/0{1}/gm, "");
};

export const decodeMorse = (morseCode: string) => {
  MORSE_CODE["_"] = " ";
  let result: string = "";
  const morseCodeCopy: string[] = morseCode
    .trim()
    .replace(/\s{3}/gi, " _ ")
    .split(` `);
  morseCodeCopy.map((v) => (result += MORSE_CODE[v]));

  return result;
};

// 1과 0을 구분하지 않고 한 번에 unit 길이를 알아내 더 깔끔하다.
// export const decodeBits = (bits: string) => {
//   bits = bits.replace(/(^0+|0+$)/g,'');
//   var sets:string[] =  bits.match(/(1+|0+)/g);
//   var unit:number = sets[0].length;
//   for(let i:number = 0; i < sets.length -1; i++){
//     if(sets[i].length > sets[i+1].length){
//       unit = sets[i+1].length;
//       break;
//     }
//   }
//   return bits.replace(new RegExp('1{'+unit*3+'}','g'),'-')
//              .replace(new RegExp('1{'+unit+'}','g'),'.')
//              .replace(new RegExp('0{'+unit*7+'}','g'),'   ')
//              .replace(new RegExp('0{'+unit*3+'}','g'),' ')
//              .replace(/0+/g,'');
// };

// export const decodeMorse = (morseCode: string) => {
//   return morseCode.replace(/\s{3}/g,' | ')
//                    .split(' ')
//                    .map((v,i,a)=>v==='|'? ' ':MORSE_CODE[v])
//                    .join('');
// };
