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

// function firstNonRepeatingLetter(s) {
//   for (var i in s) {
//     if (s.match(new RegExp(s[i], "gi")).length === 1) {
//       return s[i];
//     }
//   }
//   return "";
// }
