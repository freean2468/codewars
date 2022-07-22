function getSum(a, b) {
  const left = a < b ? a : b;
  const right = left == a ? b : a;
  let sum = 0;

  for (let i = left; i <= right; i += 1) {
    sum += i;
  }

  return sum;
}

// 수학적 사고 접근법(https://en.wikipedia.org/wiki/Arithmetic_progression#Sum)
// const GetSum = (a, b) => {
//   let min = Math.min(a, b),
//     max = Math.max(a, b);
//   return ((max - min + 1) * (min + max)) / 2;
// };
