;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmFawnCloseup) ;615
(include game.sh)
(use Main)
(use Intrface)
(use Feature)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm615 0
)

(local
	lookCount
	fawnTalkCount
	moneyTimer
)
(instance rm615 of Room
	(properties
		picture rmFawnCloseup
	)
	
	(method (init)
		(ego init: hide:)
		(Bset fLookedAtFawn)
		(herMouth cycleSpeed: (+ 2 howFast) init:)
		(rightEye cycleSpeed: (+ 2 howFast) init:)
		(leftEye cycleSpeed: (+ 2 howFast) init:)
		(angryEyes cycleSpeed: (+ 2 howFast) init:)
		(fawnHead init:)
		(fawnBody init:)
		(fawnNeck init:)
		(super init:)
		(if debugging (Bset fGaveFawnRose) (Bset fGaveFawnCandy) (Bset fGaveFawnRing) (Bset fDancedWithFawn))
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
			(and (Btst fGaveFawnRose) (Btst fGaveFawnCandy) (Btst fGaveFawnRing) (not (Btst fDancedWithFawn)))
				(Print rmFawnCloseup 0)
				(SolvePuzzle fFawnDancePoints 5)
				(Print rmFawnCloseup 1)
				(Bset fMakeFawnDance)
				(HandsOff)
				(curRoom newRoom: rmInsideDisco)
			)
			(
				(and
					(Btst fGaveFawnRose)
					(Btst fGaveFawnCandy)
					(Btst fGaveFawnRing)
					(Btst fDancedWithFawn)
					(not (Btst fAskedForMoney))
				)
				(= moneyTimer 400)
				(Bset fAskedForMoney)
				(Print rmFawnCloseup 2)
				(Print rmFawnCloseup 3)
				(Print rmFawnCloseup 4)
			)
			((== moneyTimer 1) (-- moneyTimer) (Print rmFawnCloseup 5))
			(moneyTimer (-- moneyTimer))
			((ego mover?) (HandsOff) (curRoom newRoom: rmInsideDisco))
		)
	)
)

(instance sSmile of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(angryEyes dispose:)
				(herMouth setLoop: 3 setCycle: EndLoop self)
			)
			(1
				(rightEye setCycle: BegLoop)
				(leftEye setCycle: BegLoop self)
			)
			(2 (= seconds 10))
			(3
				(herMouth setCycle: BegLoop self)
			)
			(4 (self dispose:))
		)
	)
)

(instance sFrown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(angryEyes
					init:
					cycleSpeed: howFast
					setCel: 0
					setCycle: EndLoop
				)
				(herMouth setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(1 (= seconds 10))
			(2
				(herMouth setCycle: BegLoop self)
				(angryEyes setCycle: BegLoop)
			)
			(3
				(angryEyes dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sWink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(angryEyes dispose:)
				(leftEye setCel: 0 setCycle: BegLoop)
				(herMouth setLoop: 5 setCel: 0 setCycle: BegLoop self)
			)
			(1 (self dispose:))
		)
	)
)

(instance herMouth of Prop
	(properties
		x 174
		y 72
		view rmFawnCloseup
		loop 3
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(fawnHead doVerb: theVerb theItem)
	)
)

(instance rightEye of Prop
	(properties
		x 178
		y 47
		view rmFawnCloseup
		loop 1
		signal ignrAct
		cycleSpeed 1
	)
	
	(method (doVerb theVerb theItem)
		(fawnHead doVerb: theVerb theItem)
	)
)

(instance leftEye of Prop
	(properties
		x 178
		y 47
		view rmFawnCloseup
		signal ignrAct
		cycleSpeed 1
	)
	
	(method (doVerb theVerb theItem)
		(fawnHead doVerb: theVerb theItem)
	)
)

(instance angryEyes of Prop
	(properties
		x 178
		y 47
		lookStr {Her lips are moist and inviting, but remember: you also thought that about old Mrs. Bradley, your homeroom teacher!}
		view rmFawnCloseup
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(fawnHead doVerb: theVerb theItem)
	)
)

(instance fawnBody of Feature
	(properties
		x 157
		y 155
		nsTop 122
		nsLeft 75
		nsBottom 189
		nsRight 240
		description {her}
		sightAngle 40
		lookStr {"Please," she says coyly, "stop staring there."}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch (++ lookCount)
					(1
						(Print rmFawnCloseup 6)
						(Print rmFawnCloseup 7 #at -1 140)
					)
					(2 (Print rmFawnCloseup 8))
					(else 
						(if (!= (curRoom script?) sSmile)
							(curRoom setScript: sSmile)
						)
						(Print rmFawnCloseup 9)
					)
				)
			)
			(verbTalk
				(switch (++ fawnTalkCount)
					(1
						(SolvePuzzle fTalkedToFawn 1)
						(Print rmFawnCloseup 10)
						(Print rmFawnCloseup 11)
						(if (!= (curRoom script?) sFrown)
							(curRoom setScript: sFrown)
						)
					)
					(2
						(Print rmFawnCloseup 12)
						(Print rmFawnCloseup 13)
						(if (!= (curRoom script?) sFrown)
							(curRoom setScript: sFrown)
						)
					)
					(3
						(Print rmFawnCloseup 14)
						(Print rmFawnCloseup 15)
						(if (!= (curRoom script?) sSmile)
							(curRoom setScript: sSmile)
						)
					)
					(4
						(Print rmFawnCloseup 16)
						(Print rmFawnCloseup 17)
						(Print rmFawnCloseup 18)
						(if (!= (curRoom script?) sSmile)
							(curRoom setScript: sSmile)
						)
					)
					(else 
						(if (Btst fAskedForMoney)
							(Print rmFawnCloseup 19)
						else
							(Print rmFawnCloseup 20)
							(Print rmFawnCloseup 21)
							(Print rmFawnCloseup 22 #at -1 140)
							(Print rmFawnCloseup 23)
							(Print rmFawnCloseup 24 #at -1 140)
							(if (!= (curRoom script?) sSmile)
								(curRoom setScript: sSmile)
							)
						)
					)
				)
			)
			(3
				(if (Btst fDancedWithFawn)
					(Print rmFawnCloseup 25)
				else
					(Bset fMakeFawnDance)
					(if (!= (curRoom script?) sSmile)
						(curRoom setScript: sSmile)
					)
					(Print rmFawnCloseup 26)
					(SolvePuzzle fFawnDancePoints 5)
					(Print rmFawnCloseup 27)
					(HandsOff)
					(curRoom newRoom: 610)
				)
			)
			(verbTaste
				(Print rmFawnCloseup 28)
				(if (!= (curRoom script?) sWink)
					(curRoom setScript: sWink)
				)
			)
			(verbZipper
				(Print rmFawnCloseup 29)
				(if (!= (curRoom script?) sWink)
					(curRoom setScript: sWink)
				)
			)
			(verbUse
				(switch theItem
					(iWallet
						(cond 
							((not (Btst fAskedForMoney))
								(Print rmFawnCloseup 30)
								(if (!= (curRoom script?) sFrown)
									(curRoom setScript: sFrown)
								)
							)
							((and (Btst fAskedForMoney) (<= dollars 200))
								(= moneyTimer 0)
								(Printf rmFawnCloseup 31 dollars)
								(Print rmFawnCloseup 32)
								(Print rmFawnCloseup 33)
								(Print rmFawnCloseup 34)
								(Print rmFawnCloseup 35)
								(Print rmFawnCloseup 36)
							)
							((and (Btst fAskedForMoney) (> dollars 200))
								(SolvePuzzle fDiscoCompleted 7)
								(Print rmFawnCloseup 37)
								(Print rmFawnCloseup 38)
								(Bset fGaveFawnEverything)
								(Bset fMakeFawnSplit)
								(if (!= (curRoom script?) sSmile)
									(curRoom setScript: sSmile)
								)
								(= dollars (- dollars 200))
								(HandsOff)
								(curRoom newRoom: 610)
							)
						)
					)
					(iRose
						(Print rmFawnCloseup 39)
						(ego put: iRose)
						(if (!= (curRoom script?) sSmile)
							(curRoom setScript: sSmile)
						)
						(SolvePuzzle fGaveFawnRose 5)
					)
					(iCandy
						(Print rmFawnCloseup 40)
						(ego put: iCandy)
						(if (!= (curRoom script?) sSmile)
							(curRoom setScript: sSmile)
						)
						(SolvePuzzle fGaveFawnCandy 5)
					)
					(iRing
						(Print rmFawnCloseup 41)
						(ego put: iRing)
						(if (!= (curRoom script?) sSmile)
							(curRoom setScript: sSmile)
						)
						(SolvePuzzle fGaveFawnRing 5)
					)
					(iBreathSpray
						(Print rmFawnCloseup 42)
						(if (!= (curRoom script?) sFrown)
							(curRoom setScript: sFrown)
						)
					)
					(iWatch (Print rmFawnCloseup 43))
					(iRemoteControl
						(Print rmFawnCloseup 44)
						(if (!= (curRoom script?) sSmile)
							(curRoom setScript: sSmile)
						)
					)
					(iLubber (Print rmFawnCloseup 45))
					(iDiscoPass (Print rmFawnCloseup 46))
					(iPocketKnife (Print rmFawnCloseup 47))
					(iMagazine (Print rmFawnCloseup 48))
					(iHammer
						(Print rmFawnCloseup 49)
						(if (!= (curRoom script?) sSmile)
							(curRoom setScript: sSmile)
						)
					)
					(iGraffiti
						(Print rmFawnCloseup 50)
						(if (!= (curRoom script?) sSmile)
							(curRoom setScript: sSmile)
						)
					)
					(else 
						(Printf rmFawnCloseup 51 ((Inventory at: theItem) description?))
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fawnHead of Feature
	(properties
		x 149
		y 56
		nsTop 12
		nsLeft 88
		nsBottom 101
		nsRight 210
		description {her head}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print rmFawnCloseup 52))
			(else 
				(fawnBody doVerb: theVerb theItem)
			)
		)
	)
)

(instance fawnNeck of Feature
	(properties
		x 172
		y 111
		nsTop 102
		nsLeft 136
		nsBottom 120
		nsRight 208
		description {her neck}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print rmFawnCloseup 53))
			(verbDo (Print rmFawnCloseup 54))
			(else 
				(fawnBody doVerb: theVerb theItem)
			)
		)
	)
)
