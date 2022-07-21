function alphabetPosition(text) {
  const result = [];
  text = text.toLowerCase();

  for (let letter of text) {
    if (
      letter.charCodeAt(0) < "a".charCodeAt(0) ||
      letter.charCodeAt(0) > "z".charCodeAt(0)
    ) {
      continue;
    }

    const position = getPosition(letter);

    result.push(position);
  }

  return result.join(" ");
}

function getPosition(alphabet) {
  return alphabet.charCodeAt(0) - "a".charCodeAt(0) + 1;
}

// 함수형으로 단 한 번의 chaining으로 끝낼 수 있다.
// function alphabetPosition(text) {
//   return text
//     .toUpperCase()
//     .match(/[a-z]/gi)
//     .map((c) => c.charCodeAt() - 64)
//     .join(" ");
// }
