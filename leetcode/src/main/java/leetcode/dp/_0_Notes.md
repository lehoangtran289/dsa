- If the problem involves two (or more) sequences (type (C)) like LCS, we may need two (or
  more) index parameters for different prefixes (or suffixes) of those sequences.
- For problems like subset sum or knapsack (type (E)), we may need a parameter for the target
  integer (leading to so-called “pseudo-polynomial” algorithms only).
- For problems where the input is a tree (type (F)), a standard trick is to use a tree node v as
  a parameter and restrict the input to the subtree rooted at v.
- For problems of type (G), we often need to restrict to a contiguous block of the input rather
  than a prefix or suffix, and so use 2 parameters for the start and end indices.