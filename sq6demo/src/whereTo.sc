;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include game.sh) (include "24.shm")
(use Main)
(use String)
(use Array)
(use Print)
(use Game)

(public
	whereTo 0
)

(instance whereTo of Room
	(properties)
	
	(method (init &tmp str temp1 temp2 temp3)
		(= str (String newWith: 10 {}))
		(= temp1 (String newWith: 3 {}))
		(= temp2 (IntArray with: 0 0 0 0))
		(super init:)
		(theGame setCursor: normalCursor TRUE)
		(KText TextSize (temp2 data?) {A} userFont 320)
		(= startingRoom 0)
		(= startingRoom
			(Print
				addTitle: {Space Quest 6 Demo}
				addText: {Where to, Space Nut?} 0 3
				addEdit: str 3 104 0
				addButton: -360 {Bridge} 0 13
				addButton: -480 {Shuttle} 0 26
				addButton: -430 {8 Rear} 0 39
				addButton: -390 {Roger's Quarters} 45 13
				addButton: -450 {Shuttle Bay} 45 26
				addButton: 0 {Intro Cartoon} 45 39
				addButton: -999 {Restore} 45 54
				init:
			)
		)
		(theGame setCursor: waitCursor 1)
		(switch startingRoom
			(-360 (= startingRoom 360))
			(-450 (= startingRoom 450))
			(-480 (= startingRoom 480))
			(-390 (= startingRoom 390))
			(-430 (= startingRoom 430))
			(-999 (theGame restore:))
			(else 
				(if (str size:)
					(= startingRoom (str asInteger:))
				else
					(= startingRoom 100)
					(Bset fInIntro)
				)
			)
		)
		(str dispose:)
		(temp1 dispose:)
		(temp2 dispose:)
		(self newRoom: startingRoom)
	)
)
