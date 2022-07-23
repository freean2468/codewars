function generateDiagonal(n, l) {
  const triangle = [[1], [1, 1]];

  for (let i = 1; i <= n + l; i += 1) {
    const row = [1];
    for (let j = 1; j <= i; j += 1) {
      row.push(triangle[i][j - 1] + triangle[i][j]);
    }
    row.push(1);
    triangle.push(row);
  }

  const result = [];

  for (let i = n; i < n + l; i += 1) {
    result.push(triangle[i][n]);
  }

  return result;
}
