function rgb(r, g, b) {
  return rgbToHex(r) + rgbToHex(g) + rgbToHex(b);
}

function rgbToHex(decimal) {
  if (decimal < 0) decimal = 0;
  if (decimal > 255) decimal = 255;
  hexa_decimal = decimal.toString(16).toUpperCase();
  return hexa_decimal.split("").length < 2 ? "0" + hexa_decimal : hexa_decimal;
}
