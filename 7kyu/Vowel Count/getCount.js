function getCount(str) {
  return [...str].filter((v) => ["a", "e", "i", "o", "u"].indexOf(v) >= 0)
    .length;
}

// function getCount(str) {
//   return (str.match(/[aeiou]/gi) || []).length;
// }
