;;; Sierra Script 1.0 - (do not remove this comment)
(script# 105)
(include system.sh)
(include keys.sh)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm105 0
)

(local
	mealPrice
	[local1 3]
	heart
	[local5 2]
)
(procedure (localproc_000c &tmp temp0)
	(while (not ((= temp0 (Event new: 5)) type?))
		(temp0 dispose:)
	)
	(temp0 dispose:)
)

(instance rm105 of Room
	(properties
		picture 104
		style IRISOUT ;$0007
	)
	
	(method (init)
		(Load VIEW 266)
		(HandsOff)
		(= mealPrice 32)
		(super init:)
		(self setScript: rm105Script)
	)
)

(instance rm105Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 2)
			)
			(1
				(switch dateState
					(0
						(self changeState: 2)
					)
					(1
						(self changeState: 4)
					)
					(2
						(self changeState: 5)
					)
					(3
						(self changeState: 6)
					)
					(4
						(self changeState: 7)
					)
					(5
						(self changeState: 8)
					)
				)
			)
			(2
				(Display 105 0
					p_font 1
					p_width 300
					p_at 45 63
					p_color 15
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(4
				(Display 105 1
					p_font 1
					p_width 300
					p_at 14 43
					p_color 15
				)
				((= heart (Prop new:))
					view: 285
					loop: 0
					cel: 0
					posn: 255 175
					setPri: -1
					init:
					setCycle: Forward
					startUpd:
				)
				(localproc_000c)
				(heart hide:)
				(self changeState: 9)
			)
			(5
				(if (> dollars mealPrice)
					(= dollars (- dollars mealPrice))
					(SolvePuzzle 2)
					(Display 105 2
						p_font 1
						p_width 300
						p_at 14 43
						p_color 15
					)
				else
					(Display 105 3
						p_font 1
						p_width 300
						p_at 14 43
						p_color 15
					)
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(6
				(Display 105 4
					p_font 1
					p_width 300
					p_at 30 63
					p_color 15
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(7
				(if (> dollars mealPrice)
					(= dollars (- dollars mealPrice))
					(SolvePuzzle 2)
					(Display 105 5
						p_font 1
						p_width 300
						p_at 14 43
						p_color 15
					)
				else
					(Display 105 3
						p_font 1
						p_width 300
						p_at 14 43
						p_color 15
					)
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(8
				(Display 105 6
					p_font 1
					p_width 300
					p_at 14 43
					p_color 15
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(9
				(curRoom drawPic: 104 7)
				(= cycles 1)
			)
			(10
				(Display 105 7
					p_font 0
					p_width 150
					p_at 114 95
					p_color 15
				)
				(DrawCel 999 0 0 138 128 15)
				(= cycles 1)
			)
			(11
				(= isOnDuty 0)
				(= gamePhase 8)
				(= captainWarningTimer 600)
				(= currentCar carPersonal)
				(= roomCarParked 105)
				(= global160 0)
				(HandsOn)
				(curRoom newRoom: 1)
			)
		)
	)
)
