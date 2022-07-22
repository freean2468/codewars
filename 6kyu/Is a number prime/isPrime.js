function isPrime(num) {
  if (num <= 1) return false;
  let flag = true;

  for (let i = 2; i <= Math.sqrt(num); i += 1) {
    if (num % i == 0) {
      flag = false;
      break;
    }
  }

  return flag;
}
