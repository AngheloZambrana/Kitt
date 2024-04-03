def max_meals(N, M, meals):
    meals.sort()
    eaten = 0
    for meal_time in meals:
        if meal_time <= M:
            M -= meal_time
            eaten += 1
        else:
            break
    return eaten


# Sample Input
N, M = map(int, input().split())
meals = list(map(int, input().split()))

# Output
print(max_meals(N, M, meals))
