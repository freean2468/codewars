// https://www.codewars.com/kata/514b92a657cdc65150000006/solutions/typescript

export class Challenge {
  static solution(number: number) {
    let sum = 0;
    
    for (let i = 1; i < number; i += 1) {
      const this_number = number - i;
      
      if (((this_number % 3) === 0) || ((this_number % 5) === 0)) {
        sum += this_number;
      }
    }
    
    return sum; //change this
  }
}