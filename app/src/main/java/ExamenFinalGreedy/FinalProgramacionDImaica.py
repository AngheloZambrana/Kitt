def max_meals(N, M, meals):
    dp = [[0] * (M + 1) for _ in range(N + 1)]

    for i in range(1, N + 1):
        for j in range(1, M + 1):
            if meals[i - 1] <= j:
                dp[i][j] = max(dp[i - 1][j], 1 + dp[i - 1][j - meals[i - 1]])
            else:
                dp[i][j] = dp[i - 1][j]

    return dp[N][M]


# Sample Input
N, M = map(int, input().split())
meals = list(map(int, input().split()))

# Output
print(max_meals(N, M, meals))
