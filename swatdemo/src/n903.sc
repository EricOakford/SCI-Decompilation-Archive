;;; Sierra Script 1.0 - (do not remove this comment)
(script# 903)
(include game.sh)
(use Main)

(public
	proc903_0 0
	proc903_1 1
	proc903_2 2
)

(procedure (proc903_0 param1 param2 param3 &tmp temp0)
	(asm
		lsg      gameTime
		lap      param2
		add     
		sat      temp0
code_001d:
		lsg      gameTime
		lat      temp0
		lt?     
		bnt      code_0095
		lsg      tickOffset
		pushi    0
		callk    GetTime,  0
		add     
		sag      gameTime
		lap      param1
		not     
		bnt      code_0078
		pushi    #eachElementDo
		pushi    1
		pushi    69
		lag      timers
		send     6
		pushi    #doit
		pushi    0
		lag      cast
		send     4
		pushi    0
		callk    FrameOut,  0
		lag      cuees
		bnt      code_0070
		pushi    #eachElementDo
		pushi    1
		pushi    69
		send     6
		pushi    #isEmpty
		pushi    0
		lag      cuees
		send     4
		bnt      code_0070
		pushi    #dispose
		pushi    0
		lag      cuees
		send     4
		ldi      0
		sag      cuees
code_0070:
		pushi    #doit
		pushi    0
		lag      theDoits
		send     4
code_0078:
		pushi    #curEvent
		pushi    0
		lag      user
		send     4
		bnt      code_001d
		pushi    #dispose
		pushi    0
		pushi    #curEvent
		pushi    0
		lag      user
		send     4
		send     4
		jmp      code_001d
code_0095:
		lap      param3
		bnt      code_00a9
		lsp      argc
		ldi      2
		gt?     
		bnt      code_00a9
		pushi    #cue
		pushi    0
		lap      param3
		send     4
code_00a9:
		ret     
	)
)

(procedure (proc903_1)
	(return 0)
)

(procedure (proc903_2)
	(return 0)
)
