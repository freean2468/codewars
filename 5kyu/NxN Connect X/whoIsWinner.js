const columns = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split(
  ""
);

function whoIsWinner(moves, connect, size) {
  const ground = new Array();

  for (let i = 0; i < size; i += 1) {
    ground[i] = new Array();
  }

  for (let i = 0; i < moves.length; i += 1) {
    const [pos, player] = moves[i].split("_");
    const index = posToIndex(pos);
    const column = ground[index].push(player) - 1;

    // check win
    for (let j = 0; j < size; j += 1) {
      for (let k = 0; k < size; k += 1) {
        const isWin =
          checkWin(ground, player, connect, j, k, -1, 1) ||
          checkWin(ground, player, connect, j, k, 0, 1) ||
          checkWin(ground, player, connect, j, k, 1, 1) ||
          checkWin(ground, player, connect, j, k, -1, 0) ||
          checkWin(ground, player, connect, j, k, 1, 0) ||
          checkWin(ground, player, connect, j, k, -1, -1) ||
          checkWin(ground, player, connect, j, k, 0, -1) ||
          checkWin(ground, player, connect, j, k, 1, -1);

        if (isWin) {
          return player;
        }
      }
    }
  }

  return "Draw";
}

function posToIndex(pos) {
  return columns.indexOf(pos);
}

function checkWin(ground, player, connect, row, column, row_diff, column_diff) {
  // right
  let connection = 0;
  for (let i = 0; i < connect; i += 1) {
    const ref = ground[row + row_diff * i];
    if (ref === undefined) break;
    if (ground[row + row_diff * i][column + column_diff * i] === player) {
      connection += 1;
      if (connection >= connect) {
        return true;
      }
    } else {
      break;
    }
  }

  return false;
}
