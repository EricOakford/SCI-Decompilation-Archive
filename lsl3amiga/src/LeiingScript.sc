;;; Sierra Script 1.0 - (do not remove this comment)
(script# LEIING)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Invent)
(use System)

(public
	LeiingScript 0
)

(local
	oldIllBits
)
(instance LeiingScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveSpeed (theGame setSpeed: 6))
				(= oldIllBits (ego illegalBits?))
				(ego
					view: 710
					loop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(Print 42 0 #icon 11 0 0)
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
				((Inventory at: iOrchids) view: 26)
				(Format ((Inventory at: iOrchids) name?) 42 1)
				(if (not (Btst fMadeLei))
					(Bset fMadeLei)
					(theGame changeScore: 50)
					(Print 42 2 #at -1 144)
				else
					(Print 42 3 #at -1 144)
				)
				(NormalEgo loopE)
				(ego illegalBits: oldIllBits)
				(theGame setScript: 0 setSpeed: saveSpeed)
				(DisposeScript LEIING)
			)
		)
	)
)
