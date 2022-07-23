function sumIntervals(intervals) {
  const new_intervals = [];
  const lefts = intervals.map((v) => v[0]).sort((a, b) => a - b);
  const rights = intervals.map((v) => v[1]).sort((a, b) => a - b);
  intervals = lefts.map((v, i) => [v, rights[i]]);

  let i = 0;

  while (i < intervals.length) {
    let j = i + 1;
    const interval = [intervals[i][0], intervals[i][1]];
    let next = intervals[j];

    if (next === undefined) {
      new_intervals.push(interval);
      break;
    }

    while (j < intervals.length && next[0] <= interval[1]) {
      interval[1] = next[1];
      j += 1;
      next = intervals[j];
    }

    new_intervals.push(interval);
    i = j;
  }

  return new_intervals.reduce((prev, next) => prev + (next[1] - next[0]), 0);
}
