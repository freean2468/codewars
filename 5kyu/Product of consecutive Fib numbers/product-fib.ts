const fibonacciTable: number[] = ((): number[] => {
  const arr: number[] = new Array<number>();

  arr.push(0);
  arr.push(1);
  for (let index = 2; index < 100; index += 1) {
    arr.push(arr[index - 2] + arr[index - 1]);
  }
  return arr;
})();

export const productFib = (prod: number): [number, number, boolean] => {
  for (let i = 0; true; i += 1) {
    const left = fibonacciTable[i];
    const right = fibonacciTable[i + 1];
    const this_prod = left * right;

    if (this_prod === prod) {
      return [left, right, true];
    } else if (this_prod < prod) {
      continue;
    } else {
      return [left, right, false];
    }
  }
};
