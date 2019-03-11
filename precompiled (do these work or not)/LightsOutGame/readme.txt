Do not distribute without this readme file!

The LightsOutGame.jar is written by Sherman Ying in September 2011. The code contained within the java archive is property of Sherman Ying. The concept of "Lights Out" is from a 1995 game by Tiger Toys.


Light chasing problem solving method
1. Start from the top row, disabling all of the lights in that row by toggling the lights directly below the active lights.
2. Repeat 1 for the second, third, and fourth rows.
3. Depending on the layout of the bottom row, trigger the following lights in the top row, marked with a O.
Bottom row	Top row
OxxxO		OOxxx
xOxOx		OxxOx
OOOxx		xOxxx
xxOOO		xxxOx
OxOOx		xxxxO
xOOxO		Oxxxx
OOxOO		xxOxx
4. Repeat 1-3 until the puzzle is solved.
Note: Generally the algorithm will complete the puzzle in two passes or less. Also, this only works with the 5x5 grid.