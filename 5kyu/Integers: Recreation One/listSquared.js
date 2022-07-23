function listSquared(m, n) {
  const result = [];

  for (let i = m; i < n; i += 1) {
    const divisors = new Set();
    for (let j = 1; j <= i; j += 1) {
      if (i % j == 0) {
        divisors.add(j);
      }
    }
    const sum = [...divisors]
      .map((v) => v ** 2)
      .reduce((prev, next) => prev + next, 0);
    if (Math.sqrt(sum) === ~~Math.sqrt(sum)) {
      result.push([i, sum]);
    }
  }

  return result;
}
