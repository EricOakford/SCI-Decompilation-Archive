### Space Quest 6 Demo (SQ6DEMO)
  Game version 1.0
  
  Interpreter version 2.001.002

The game has been tested to completion.

All system scripts are based on the newest SCI32 source from 10-12-1995, with the exceptions of:
	ACTOR.SC (06-28-1995)
	TALKER.SC (modified 6-28-1995)
	MESSAGER.SC (modified 10-12-1995)
	PLANE.SC (modified 10-12-1995)
	DTEXT.SC (06-28-1995)
	STYLER.SC (06-28-1995)
	FILE.SC (06-28-1995)
	INSET.SC (06-28-1995)

Known issues
	The SQIconItem script has intentionally been made uncompilable, because of a decompiler error that causes the icon bar to stop working after exiting the game's control panel. The specific problem area is the icon bar's doit, which was undecompilable and therefore is represented in assembly.