// https://www.codewars.com/kata/54bf1c2cd5b56cc47f0007a1/typescript
export function duplicateCount(text: string): number {
  let occurrences: Record<string, number> = {};

  for (const t of text) {
    let lower: string = "";

    if (typeof t == "number") {
      lower = t;
    } else {
      lower = t.toLowerCase();
    }

    if (occurrences[lower] === undefined) {
      occurrences[lower] = 1;
    } else {
      occurrences[lower] += 1;
    }
  }

  let occurrencesCount = 0;

  for (const oc in occurrences) {
    if (occurrences[oc] > 1) {
      occurrencesCount += 1;
    }
  }

  return occurrencesCount;
}

// Set를 활용해 중복을 없애고 Big(n)의 속도로 한 번에 처리해 깔끔하다.
// export function duplicateCount(text: string): number {
//   let array = text.toLowerCase().split("");
//   return [...new Set(array.filter((e, i) => array.indexOf(e) !== i))].length;
// }
