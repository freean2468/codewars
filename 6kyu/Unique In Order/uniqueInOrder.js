var uniqueInOrder = function (iterable) {
  return [...iterable].filter((v, i, a) => v !== a[i - 1]);
};
