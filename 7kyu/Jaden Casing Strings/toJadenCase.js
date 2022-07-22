String.prototype.toJadenCase = function () {
  return this.split(" ")
    .map((v) => v[0].toUpperCase() + v.slice(1))
    .join(" ");
};
