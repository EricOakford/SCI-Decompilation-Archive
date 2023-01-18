;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4)
(include game.sh)
(use Main)
(use Game)
(use Actor)
(use System)

(public
	demoRoom4 0
)

(instance demoRoom4 of Room
	(properties
		picture 118
		style HSHUTTER
	)
	
	(method (init)
		(Load PICTURE 118)
		(Load VIEW 118)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(method (doit)
		(super doit:)
		(if state
			(Palette PALCycle 8 15 -1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(DoDisplay {Larry's in the entertainment industry now,}
					#at -1 15
					#color colYellow
					#font 2510
					#mode teJustCenter
				)
				(DoDisplay {working on a new syndicated}
					#at -1 30
					#color colYellow
					#font 2510
					#mode teJustCenter
				)
				(DoDisplay {television show...}
					#at -1 45
					#color colYellow
					#font 2510
					#mode teJustCenter
				)
				(= cycles 10)
			)
			(1
				((View new:)
					view: 118
					x: 136
					y: 169
					init:
				)
				((View new:)
					view: 118
					cel: 1
					x: 261
					y: 127
					init:
				)
				(= cycles 32)
			)
			(2
				(UnLoad PICTURE 118)
				(UnLoad VIEW 118)
				(curRoom newRoom: 5)
			)
		)
	)
)
