// https://www.codewars.com/kata/5506b230a11c0aeab3000c1f/typescript

export function evaporator(
  content: number,
  evapPerDay: number,
  threshold: number
): number {
  // your code
  let rest = content;
  const thresholdInMl = (content * threshold) / 100;
  let nth = 1;

  while (true) {
    rest -= (rest * evapPerDay) / 100;

    if (rest < thresholdInMl) {
      break;
    }

    nth += 1;
  }

  return nth;
}

// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/log
// log를 활용해 푸는 접근법이 정말 신선하다
// export function evaporator(
//   content: number,
//   evap_per_day: number,
//   threshold: number
// ): number {
//   return Math.ceil(
//     Math.log(threshold / 100) / Math.log(1 - evap_per_day / 100)
//   );
// }
