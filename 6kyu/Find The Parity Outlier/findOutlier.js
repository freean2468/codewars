function findOutlier(integers) {
  const evens = integers.filter((v) => v % 2 == 0);
  const odds = integers.filter((v) => v % 2 != 0);

  return evens.length > odds.length ? odds[0] : evens[0];
}
