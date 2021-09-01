;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include game.sh) (include maze.sh)
(use Main)
(use WizardGame)
(use Motion)
(use System)

(public
	doASpell 0
	doFlame 1
)

(local
	local0 = [0 0 10 10 10 0 -10 -10 -10]
	local9 = [6 -6 -6 0 6 6 6 0 -6]
)
(instance doASpell of Script
	(method (dispose)
		((ScriptID 32 1) empty:)
		(super dispose:)
		(DisposeScript 240)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (WizGameTryCastSpell register)
					(switch register
						(OPEN
							(proc32_12 0 8)
							(= register (ScriptID 32 3))
						)
						(FETCH
							(proc32_12 8 32)
							(= register (ScriptID 32 4))
						)
						(TRIGGER
							(= register (ScriptID 32 5))
						)
					)
					(register setCycle: EndLoop self)
				else
					(= magesMazeCommand mmazeCHOOSE)
					(self dispose:)
				)
			)
			(1
				(register stopUpd:)
				(if (== register (ScriptID 32 5))
					((ScriptID 32 6) changeForm:)
					(proc32_11)
					(= magesMazeCommand mmazeCHOOSE)
					(self dispose:)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 5])
		(cond 
			((super handleEvent: event))
			((== (event type?) direction) ((ScriptID 32 1) handleEvent: event))
		)
	)
)

(instance doFlame of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 240)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (WizGameTryCastSpell 23)
					(HandsOff)
					(= wizGameSpellTime 0)
					(theGame setCursor: normalCursor 1)
					((ScriptID 32 8) setCycle: EndLoop self)
				else
					(= magesMazeCommand mmazeCHOOSE)
					(self dispose:)
				)
			)
			(1
				((ScriptID 32 8) stopUpd:)
				(HandsOn)
				((ScriptID 32 7) posn: 274 23 setCycle: Forward)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2 temp3)
		(cond 
			((super handleEvent: event))
			((== (event type?) direction)
				(= temp1 [local0 (= temp3 (event message?))])
				(= temp2 [local9 temp3])
				(cond 
					(
					(and (<= ((ScriptID 32 7) x?) 0) (< temp1 0)) (= temp1 0))
					(
					(and (<= 319 ((ScriptID 32 7) x?)) (< 0 temp1)) (= temp1 0))
					(
					(and (<= ((ScriptID 32 7) y?) 0) (< temp2 0)) (= temp2 0))
					(
					(and (<= 189 ((ScriptID 32 7) y?)) (< 0 temp2)) (= temp2 0))
				)
				((ScriptID 32 7)
					posn: (+ ((ScriptID 32 7) x?) temp1) (+ ((ScriptID 32 7) y?) temp2)
				)
				(event claimed: TRUE)
			)
			((== (event type?) mouseDown)
				(event claimed: TRUE)
				(= wizGameSpellTime (+ 4 (/ [egoStats FLAMEDART] 5)))
				((ScriptID 32 7)
					posn: (event x?) (event y?)
					setCycle: Forward
				)
				(= magesMazeCommand mmazeCHOOSE)
				(self dispose:)
			)
			(
				(and
					(== (event message?) ENTER)
					(== (event type?) keyDown)
				)
				(event claimed: TRUE)
				(= wizGameSpellTime (+ 4 (/ [egoStats FLAMEDART] 5)))
				(= magesMazeCommand mmazeCHOOSE)
				(self dispose:)
			)
		)
	)
)
