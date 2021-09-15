;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Invent)
(use System)

(public
	WeavingScript 0
)

(local
	oldIllBits
)
(instance WeavingScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveSpeed (theGame setSpeed: 6))
				(= oldIllBits (ego illegalBits?))
				(ego
					view: 717
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(Print 44 0 #icon 4 0 0)
			)
			(1
				(ego loop: 1 cel: 0 setCycle: Forward)
				(= seconds 3)
			)
			(2
				(ego loop: 2)
				(= seconds 2)
			)
			(3
				(ego loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(4
				((Inventory at: iGrass) view: 23)
				(Format ((Inventory at: iGrass) name?) 44 1)
				(theGame changeScore: 30)
				(NormalEgo loopE)
				(ego illegalBits: oldIllBits)
				(theGame setScript: 0 setSpeed: saveSpeed)
				(DisposeScript WEAVING)
			)
		)
	)
)
