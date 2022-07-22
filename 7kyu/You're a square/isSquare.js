var isSquare = function (n) {
  let result = false;

  if (n < 0) result = false;
  else if (n == 0) result = true;
  else {
    const squareRoot = parseInt(Math.sqrt(n));
    if (squareRoot ** 2 === n) result = true;
  }

  return result; // fix me
};

// 한 줄로 끝낼 수 있다.
// function isSquare(n) {
//   return Math.sqrt(n) % 1 === 0;
// }
