function sumDigPow(a, b) {
  const hits = [];

  for (let i = a; i <= b; i += 1) {
    if (
      i ==
      String(i)
        .split("")
        .reduce((p, n, i) => p + n ** (i + 1), 0)
    ) {
      hits.push(i);
    }
  }

  return hits;
}
