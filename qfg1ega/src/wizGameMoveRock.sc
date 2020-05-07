;;; Sierra Script 1.0 - (do not remove this comment)
(script# 241)
(include game.sh)
(use WizardGame)

(public
	WizGameMoveRock 0
)

(procedure (WizGameMoveRock param1 &tmp temp0 temp1 temp2)
	(if (= temp1 (WizGameGetRock param1))
		(repeat
			(= temp2 (Random 0 7))
			(if (not (WizGameGetRock temp2)) (break))
		)
		(WizGameGetRock temp2 temp1)
		(temp1 listIndex: temp2)
		(WizGameGetRock param1 0)
		(= temp0 (+ temp2 temp2 temp2))
		(temp1
			posn: (WizGameGetRock (+ 32 temp0)) (WizGameGetRock (+ 33 temp0))
		)
	)
	(DisposeScript 241)
)
