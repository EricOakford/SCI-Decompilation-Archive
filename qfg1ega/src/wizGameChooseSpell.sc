;;; Sierra Script 1.0 - (do not remove this comment)
(script# 239)
(include game.sh)
(use Main)
(use Intrface)
(use KeyCursor)
(use System)

(public
	chooseSpell 0
)

(local
	[local0 4] = [48 122 198 274]
)
(procedure (localproc_000c param1 param2 param3 param4 param5 param6)
	(return
		(if
			(and
				(<= param3 param1)
				(< param1 param5)
				(<= param4 param2)
			)
			(< param2 param6)
		else
			0
		)
	)
)

(procedure (localproc_002a)
	(button1 init:)
	((ScriptID 32 1) add: button1)
	(button2 init:)
	((ScriptID 32 1) add: button2)
	(button3 init:)
	((ScriptID 32 1) add: button3)
	(button4 init:)
	((ScriptID 32 1) add: button4)
)

(instance chooseSpell of Script
	(properties)
	
	(method (dispose)
		((ScriptID 32 1) empty:)
		(super dispose:)
		(DisposeScript 239)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SetCursor theCursor 1 [local0 magesMazeButtonIndex] 23)
				((ScriptID 32 1) empty:)
				(localproc_002a)
			)
		)
	)

	(method (handleEvent event &tmp [temp0 3] temp3 temp4)
		(if
			(and
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(event type: 1)
		)
		(cond 
			((super handleEvent: event))
			((== (event type?) direction) ((ScriptID 32 1) handleEvent: event))
			((== (event type?) mouseDown)
				(= temp3 (event x?))
				(= temp4 (event y?))
				(event claimed: TRUE)
				(cond 
					((localproc_000c temp3 temp4 17 18 79 29)
						(= magesMazeButtonIndex 0)
						(= magesMazeCommand mmazeFETCH)
						(self dispose:)
					)
					((localproc_000c temp3 temp4 91 18 153 29)
						(= magesMazeButtonIndex 1)
						(= magesMazeCommand mmazeOPEN)
						(self dispose:)
					)
					((localproc_000c temp3 temp4 167 18 229 29)
						(= magesMazeButtonIndex 2)
						(= magesMazeCommand mmazeTRIGGER)
						(self dispose:)
					)
					((localproc_000c temp3 temp4 243 18 305 29)
						(= magesMazeButtonIndex 3)
						(= magesMazeCommand mmazeFLAME)
						(self dispose:)
					)
					(else (event claimed: FALSE))
				)
			)
			((!= (event type?) saidEvent))
			((Said 'drink') (event claimed: FALSE))
			((or (Said 'cast/fetch') (Said 'fetch,get'))
				(= magesMazeButtonIndex 0)
				(= magesMazeCommand mmazeFETCH)
				(self dispose:)
			)
			((or (Said 'cast/open') (Said 'open'))
				(= magesMazeButtonIndex 1)
				(= magesMazeCommand mmazeOPEN)
				(self dispose:)
			)
			((or (Said 'cast/trigger') (Said '/trigger'))
				(= magesMazeButtonIndex 2)
				(= magesMazeCommand mmazeTRIGGER)
				(self dispose:)
			)
			(
				(or
					(Said 'cast/dart,flame,fire,fireball')
					(Said '/flame,fire,torch,fireball')
				)
				(= magesMazeButtonIndex 3)
				(= magesMazeCommand mmazeFLAME)
				(self dispose:)
			)
			(
			(or (Said 'resign,quit,done,cease') (Said '/bye[<bye]'))
				(= magesMazeCommand mmazeNOTHING)
				(Bset GIVE_UP_MAZE_GAME)
				((ScriptID 32 0) cue:)
				(self dispose:)
			)
			((Said 'throw')
				(Print 239 0)
				; Your bug doesn't have any hands.  This makes throwing anything out of the question.
			)
			((Said 'run,sneak') (Print 239 1) ; Looks like all you can do is wriggle.
			)
			((Said 'drink') (event claimed: FALSE))
			(else
				(Print 239 2)
				(event claimed: TRUE)
				; Better concentrate on the game.
			)
		)
	)
)

(instance button1 of CursorCoords
	(properties
		cursorX 48
		cursorY 23
	)
)

(instance button2 of CursorCoords
	(properties
		cursorX 122
		cursorY 23
	)
)

(instance button3 of CursorCoords
	(properties
		cursorX 198
		cursorY 23
	)
)

(instance button4 of CursorCoords
	(properties
		cursorX 274
		cursorY 23
	)
)
