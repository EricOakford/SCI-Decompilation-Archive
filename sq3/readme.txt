### Space Quest III (SQ3)
  Game version 1.018
  Interpreter version 0.000.685

  Several said specs did not decompile right, giving wrong words. Using the German Amiga version for reference, I found that its vocab has a dummy entry at 93b, which allowed it to decompile the correct words.
  Otherwise tested to completion with no other known issues.


Fixed said specs
	tackle > cruncher (incorrect spec is unused)
	cruncher > snake	(correct spec only used in Room 53)
	snake > tongue	(both correct and incorrect specs only used in Room 53)
	tongue > overhang
	s(outh) > toe
	e(ast) > s(outh)
	north > e(ast)
	leech > north
	lawn > devil
	pass > lawn (debug code was already correct, strangely)
	devil > jam (correct spec was unused)

NOTE: Of the four compass directions, w(est) is correct, as it's not in the mismatched range. It'll be expanded to the full word for clarity and for comparison with the German Amiga version.
