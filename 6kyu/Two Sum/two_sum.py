def two_sum(numbers: [int], target: int):
    for i, num1 in enumerate(numbers):
        for j, num2 in enumerate(numbers):
            if num1 + num2 == target and i != j:
                return [i, j]

    return []
