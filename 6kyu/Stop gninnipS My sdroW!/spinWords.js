function spinWords(string) {
  return string
    .split(" ")
    .map((v) => (v.length >= 5 ? v.split("").reverse().join("") : v))
    .join(" ");
}
