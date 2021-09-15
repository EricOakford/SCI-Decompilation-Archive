;;; Sierra Script 1.0 - (do not remove this comment)
(script# CARVING)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Invent)
(use System)

(public
	CarvingScript 0
)

(local
	oldIllBits
)
(instance CarvingScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= currentStatus egoNORMAL)
					(= cycles 1)
				else
					(HandsOff)
					(= saveSpeed (theGame setSpeed: 6))
					(= oldIllBits (ego illegalBits?))
					(ego view: 711 loop: 0 cel: 0 setCycle: EndLoop self)
				)
				(Print 43 0 #icon 3 0 0)
			)
			(1
				(if (!= currentStatus egoNORMAL)
					(= cycles 1)
				else
					(ego loop: 1 cel: 0 setCycle: Forward)
					(= cycles (* 10 (NumCels ego)))
				)
			)
			(2
				((Inventory at: iWood) view: (if (>= filthLevel 2) 22 else 34))
				(Format ((Inventory at: iWood) name?) 43 1)
				(theGame changeScore: 50)
				(if (== currentStatus egoNORMAL)
					(HandsOn)
					(NormalEgo loopE)
					(ego illegalBits: oldIllBits)
					(theGame setSpeed: saveSpeed)
				)
				(theGame setScript: 0)
				(DisposeScript CARVING)
			)
		)
	)
)
