;;; Sierra Script 1.0 - (do not remove this comment)
(script# 305)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Invent)
(use System)

(public
	shootBow 0
)

(local
	saveViewer
)
(instance shootBow of Script
	(method (init)
		(= isHandsOff TRUE)
		(Load VIEW 31)
		(Load VIEW 68)
		(super init:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= oldEgoView (ego view?))
				(= oldEgoBaseSetter (ego baseSetter?))
				(= saveViewer (ego viewer?))
				(ego
					viewer: 0
					baseSetter: (ScriptID 0 1)
					view: (if (== oldEgoView 2) 31 else 68)
					setMotion: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(ego view: oldEgoView setCycle: Walk)
				(= isHandsOff FALSE)
				((Inventory at: iCupidBow) loop: (+ ((Inventory at: iCupidBow) loop?) 1))
				(= globalPrint (Print 305 0 #at -1 10 #dispose))
				(Timer setReal: self 4)
			)
			(2
				(cls)
				(Print 305 1)
				(ego
					viewer: saveViewer
					baseSetter: oldEgoBaseSetter
					script: oldEgoScript
				)
				(HandsOn)
				(DisposeScript 305)
			)
		)
	)
)
