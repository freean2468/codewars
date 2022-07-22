function isPangram(string) {
  return (
    new Set([...string.toLowerCase().match(/[a-z]/g)]).size ===
    "z".charCodeAt(0) - "a".charCodeAt(0) + 1
  );
}
