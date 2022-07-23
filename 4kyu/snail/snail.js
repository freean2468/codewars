snail = function (array) {
  const result = [];
  let n = array[0].length;

  if (n == 0) {
    return result;
  }

  let sign = 1;
  let until = n;
  let to_x = false; // or to_y
  let x = 0;
  let y = 0;

  for (let i = 0; i < until; i += 1) {
    result.push(array[y][x++]);
  }

  x -= 1;
  until -= 1;

  for (let i = n, j = 0, k = 0, l = 1; i < n ** 2; i += 1, j += 1) {
    if (0 < j && j % until == 0) {
      j = 0;
      to_x = !to_x;
      k += 1;
      l += 1;
    }

    if (0 < l && l % 2 == 0) {
      sign *= -1;
      l = 0;
    }

    if (k == Math.min(until, 2)) {
      until -= 1;
      k = 0;
    }

    if (to_x) {
      x += 1 * sign;
    } else {
      y += 1 * sign;
    }

    result.push(array[y][x]);
  }

  return result;
};

// function snail(array) {
//   var vector = [];
//   while (array.length) {
//     vector.push(...array.shift());
//     array.map((row) => vector.push(row.pop()));
//     array.reverse().map((row) => row.reverse());
//   }
//   return vector;
// }
