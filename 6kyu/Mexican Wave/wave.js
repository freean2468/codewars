function wave(str) {
  const result = [];

  [...str.toLowerCase()].forEach((v, i) => {
    if (
      v.charCodeAt(0) >= "a".charCodeAt(0) &&
      v.charCodeAt(0) <= "z".charCodeAt(0)
    ) {
      const copy = [...str.toLowerCase()];
      copy[i] = copy[i].toUpperCase();
      result.push(copy.join(""));
    }
  });

  return result;
}
