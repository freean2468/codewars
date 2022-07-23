function add(a, b) {
  const max_length = Math.max(a.split("").length, b.split("").length);
  const list_a = fill_list(max_length, a);
  const list_b = fill_list(max_length, b);
  const list_sum = new Array(Math.max(max_length + 1)).fill(0);

  for (let i = 0; i < max_length + 1; i += 1) {
    const sum = Number(list_a[i]) + Number(list_b[i]);
    if (String(sum).length > 1) {
      list_sum[i + 1] = 1;
    }
    list_sum[i] = list_sum[i] + (sum % 10);
    if (list_sum[i] >= 10) {
      list_sum[i + 1] += 1;
      list_sum[i] %= 10;
    }
  }

  if (list_sum[max_length] === 0) {
    list_sum.pop();
  }

  return list_sum.reverse().join("");
}

function fill_list(max_length, num) {
  const list = new Array(max_length + 1);
  const num_list = String(num).split("");
  const num_length = num_list.length;
  const diff = max_length - num_length;

  for (let i = 0, j = 0; i < max_length + 1; i += 1) {
    if (diff - i >= 0) {
      list[i] = 0;
    } else {
      list[i] = num_list[j++];
    }
  }

  return list.reverse();
}

// function add (a, b) {
//   var res = '', c = 0
//   a = a.split('')
//   b = b.split('')
//   while (a.length || b.length || c) {
//     c += ~~a.pop() + ~~b.pop()
//     res = c % 10 + res
//     c = c > 9
//   }
//   return res
// }
