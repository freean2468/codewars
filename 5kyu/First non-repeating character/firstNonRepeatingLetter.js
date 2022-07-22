function firstNonRepeatingLetter(s) {
  const maps = new Map();

  [...s.toLowerCase()].forEach((v, i) => {
    if (maps.get(v) === undefined) {
      maps.set(v, [i]);
    } else {
      maps.get(v).push(i);
    }
  });

  const not_repeated = [];

  maps.forEach((v) => {
    if (v.length === 1) {
      not_repeated.push(...v);
    }
  });

  return not_repeated.length === 0 ? "" : s[not_repeated[0]];
}
