def max_pieces_of_chocolate_greedy(N):
    if N == 1:
        return 1
    pieces = 0
    i = 2
    while N > 0:
        pieces += 1
        N -= i
        i += 1
    return pieces

# Example usage:
print(max_pieces_of_chocolate_greedy(8))  # Output: 3
print(max_pieces_of_chocolate_greedy(12)) # Output: 4
