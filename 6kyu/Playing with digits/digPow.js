function digPow(n, p) {
  const acc = [...n.toString().split("")].reduce(
    (prev, next, i) => prev + next ** (p + i),
    0
  );
  const k = acc / n;
  return k == parseInt(k) ? k : -1;
}
