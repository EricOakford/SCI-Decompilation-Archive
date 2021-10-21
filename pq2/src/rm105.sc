;;; Sierra Script 1.0 - (do not remove this comment)
(script# 105)
(include sci.sh)
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

(instance rm105 of Rm
	(properties
		picture 104
		style $0007
	)
	
	(method (init)
		(Load rsVIEW 266)
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
			(0 (= cycles 2))
			(1
				(switch dateState
					(0 (self changeState: 2))
					(1 (self changeState: 4))
					(2 (self changeState: 5))
					(3 (self changeState: 6))
					(4 (self changeState: 7))
					(5 (self changeState: 8))
				)
			)
			(2
				(Display
					105
					0
					dsFONT
					1
					dsWIDTH
					300
					dsCOORD
					45
					63
					dsCOLOR
					15
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(4
				(Display
					105
					1
					dsFONT
					1
					dsWIDTH
					300
					dsCOORD
					14
					43
					dsCOLOR
					15
				)
				((= heart (Prop new:))
					view: 285
					loop: 0
					cel: 0
					posn: 255 175
					setPri: -1
					init:
					setCycle: Fwd
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
					(Display
						105
						2
						dsFONT
						1
						dsWIDTH
						300
						dsCOORD
						14
						43
						dsCOLOR
						15
					)
				else
					(Display
						105
						3
						dsFONT
						1
						dsWIDTH
						300
						dsCOORD
						14
						43
						dsCOLOR
						15
					)
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(6
				(Display
					105
					4
					dsFONT
					1
					dsWIDTH
					300
					dsCOORD
					30
					63
					dsCOLOR
					15
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(7
				(if (> dollars mealPrice)
					(= dollars (- dollars mealPrice))
					(SolvePuzzle 2)
					(Display
						105
						5
						dsFONT
						1
						dsWIDTH
						300
						dsCOORD
						14
						43
						dsCOLOR
						15
					)
				else
					(Display
						105
						3
						dsFONT
						1
						dsWIDTH
						300
						dsCOORD
						14
						43
						dsCOLOR
						15
					)
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(8
				(Display
					105
					6
					dsFONT
					1
					dsWIDTH
					300
					dsCOORD
					14
					43
					dsCOLOR
					15
				)
				(localproc_000c)
				(self changeState: 9)
			)
			(9
				(curRoom drawPic: 104 7)
				(= cycles 1)
			)
			(10
				(Display
					105
					7
					dsFONT
					0
					dsWIDTH
					150
					dsCOORD
					114
					95
					dsCOLOR
					15
				)
				(DrawCel 999 0 0 138 128 15)
				(= cycles 1)
			)
			(11
				(= isOnDuty 0)
				(= gamePhase 8)
				(= captainWarningTimer 600)
				(= currentCar 33)
				(= roomCarParked 105)
				(= global160 0)
				(HandsOn)
				(curRoom newRoom: 1)
			)
		)
	)
)
