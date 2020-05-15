;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmFaithCloseup) ;355
(include game.sh)
(use Main)
(use Intrface)
(use LLRoom)
(use Feature)
(use LoadMany)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm355 0
)

(local
	lookCount
	talkCount
	mouthTimer
)
(instance rm355 of LLRoom
	(properties
		picture rmFaithCloseup
	)
	
	(method (init)
		(LoadMany VIEW 355)
		(LoadMany SOUND 352)
		(ego init: hide:)
		(super init:)
		(herEyes init: cycleSpeed: egoSpeed setScript: sBlink)
		(herMouth init: cycleSpeed: egoSpeed)
		(if (and (not spraySeconds) (Btst fMouthSmellsBad))
			(= mouthTimer 60)
			(herMouth setScript: sFrown)
		)
		(hair init:)
		(theBreasts init:)
		(faithF init:)
		(neck init:)
		(necklace init:)
		(face init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and mouthTimer (== (-- mouthTimer) 1))
			(Print 355 0 #at -1 140)
		)
		(cond 
			(script)
			((ego mover?) (curRoom newRoom: rmSecurityDesk))
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 355 1)
			)
			(verbUse
				(switch theItem
					(iBreathSpray
						(= mouthTimer 0)
						(herEyes setScript: sFlap)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance sFaithLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 352 play:)
				(herEyes setScript: sFlap)
				(herMouth setScript: sSmile 0 1)
				(ego put: iPills 350)
				(SolvePuzzle fGaveFaithPills 5)
				(= seconds 2)
			)
			(1 (herEyes setCycle: EndLoop self))
			(2
				(Print 355 2)
				(Print 355 3)
				(herEyes setScript: sFlap)
				(= seconds 4)
			)
			(3 (herEyes setCycle: EndLoop self))
			(4
				(Print 355 4)
				(Print 355 5 #at -1 140)
				(= seconds 4)
			)
			(5 (curRoom newRoom: rmSecurityDesk))
		)
	)
)

(instance sFrown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(herMouth setLoop: 2 setCel: 0)
				(herEyes setScript: 0 setLoop: 1 setCel: 0)
				(= seconds 5)
			)
			(1
				(herMouth setLoop: 4 setCel: 0)
				(herEyes setLoop: 0 setCel: 0 setScript: sBlink)
				(self dispose:)
			)
		)
	)
)

(instance sSmile of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= (curRoom script?) sFaithLeaves)
					(herEyes setLoop: 0 setCel: 0 setScript: sBlink)
				)
				(herMouth setLoop: 5 setCel: 0)
				(= seconds register)
			)
			(1
				(herMouth setLoop: 4 setCel: 0)
				(if (> register 3) (self dispose:) else (self init:))
			)
		)
	)
)

(instance sFlap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(herEyes setLoop: 0 setCycle: Forward)
				(= seconds 5)
			)
			(1
				(if (== (curRoom script?) sFaithLeaves)
					(self init:)
				else
					(herEyes setCycle: BegLoop self)
				)
			)
			(2
				(herEyes setScript: sBlink)
				(self dispose:)
			)
		)
	)
)

(instance sBlink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 4)))
			(1 (herEyes setCycle: BegLoop self))
			(2 (self init:))
		)
	)
)

(instance herEyes of Prop
	(properties
		x 122
		y 71
		z 28
		description {her eyes}
		view 355
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 355 6)
			)
			(else 
				(faithF doVerb: theVerb theItem)
			)
		)
	)
)

(instance herMouth of Prop
	(properties
		x 128
		y 71
		description {her mouth}
		view 355
		loop 4
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 355 7)
			)
			(verbTaste
				(herMouth setScript: sSmile 0 5)
			)
			(else 
				(faithF doVerb: theVerb theItem)
			)
		)
	)
)

(instance face of Feature
	(properties
		x 131
		y 53
		nsTop 37
		nsLeft 105
		nsBottom 81
		nsRight 157
		description {her face}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 355 8)
			)
			(else 
				(faithF doVerb: theVerb theItem)
			)
		)
	)
)

(instance neck of Feature
	(properties
		x 152
		y 54
		nsTop 82
		nsLeft 131
		nsBottom 114
		nsRight 174
		description {her neck}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 355 9)
			)
			(verbTaste
				(Print 355 10 #at -1 140)
				(herMouth setScript: sSmile 0 5)
			)
			(else 
				(faithF doVerb: theVerb theItem)
			)
		)
	)
)

(instance necklace of Feature
	(properties
		x 151
		y 55
		nsTop 115
		nsLeft 129
		nsBottom 141
		nsRight 174
		description {her necklace}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 355 11)
			)
			(verbDo
				(Print 355 12)
				(Print 355 13)
				(Print 355 14)
				(Print 355 15)
				(herMouth setScript: sSmile 0 5)
			)
			(else 
				(faithF doVerb: theVerb theItem)
			)
		)
	)
)

(instance hair of Feature
	(properties
		x 151
		y 52
		nsTop 2
		nsLeft 83
		nsBottom 103
		nsRight 219
		description {her hair}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 355 16)
			)
			(verbDo
				(Print 355 17)
				(Print 355 18)
			)
			(verbTaste
				(Print 355 19)
				(Print 355 20)
			)
			(else 
				(faithF doVerb: theVerb theItem)
			)
		)
	)
)

(instance theBreasts of Feature
	(properties
		x 146
		y 170
		nsTop 152
		nsLeft 77
		nsBottom 189
		nsRight 215
		description {her breasts}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Print 355 21)
			)
			(verbDo
				(Print 355 22)
			)
			(verbTaste
				(Print 355 23)
			)
			(else 
				(faithF doVerb: theVerb theItem)
			)
		)
	)
)

(instance faithF of Feature
	(properties
		x 166
		y 24
		nsTop 2
		nsLeft 74
		nsBottom 189
		nsRight 259
		description {Faith}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(switch (++ lookCount)
					(1 (Print 355 1))
					(2 (Print 355 24))
					(else 
						(Print 355 25)
						(herMouth setScript: sSmile 0 5)
					)
				)
			)
			(verbDo
				(Print 355 26 #at -1 140)
			)
			(verbTalk
				(switch (++ talkCount)
					(1
						(Print 355 27)
						(herMouth setScript: sSmile 0 5)
						(Print 355 28 #at -1 140)
					)
					(2
						(Print 355 29)
						(Print 355 30 #at -1 140)
						(herMouth setScript: sSmile 0 5)
					)
					(3
						(Print 355 31)
						(Print 355 32 #at -1 140)
					)
					(4
						(Print 355 33)
						(Print 355 14 #at -1 140)
						(Print 355 15)
					)
					(5
						(Print 355 34)
						(Print 355 35 #at -1 140)
						(Print 355 36 #at -1 140)
						(herMouth setScript: sSmile 0 5)
					)
					(6
						(Print 355 37)
						(Print 355 38 #at -1 140)
					)
					(7
						(Print 355 39)
						(Print 355 40 #at -1 140)
					)
					(else  (Print 355 41))
				)
			)
			(verbZipper
				(Print 355 42)
				(Print 355 43 #at -1 140)
				(herMouth setScript: sFrown)
			)
			(verbTaste
				(Print 355 44)
			)
			(verbUse
				(switch theItem
					(iWallet
						(Print 355 45)
						(herMouth setScript: sFrown)
					)
					(iWatch
						(Print 355 46)
						(Print 355 47)
					)
					(iApple
						(Print 355 48)
					)
					(iRing
						(Print 355 49)
					)
					(iWhiskey
						(Print 355 50)
					)
					(iRemoteControl
						(Print 355 51)
						(Print 355 52)
					)
					(iRose
						(Print 355 53)
					)
					(iLubber
						(Print 355 54)
					)
					(iCandy
						(Print 355 55)
						(Print 355 56)
					)
					(iDiscoPass
						(Print 355 57)
						(ego put: iDiscoPass)
					)
					(iPocketKnife
						(Print 355 58)
					)
					(iWine
						(Print 355 50)
					)
					(iMagazine
						(Print 355 59)
					)
					(iHammer
						(Print 355 60)
					)
					(iPills
						(HandsOff)
						(curRoom setScript: sFaithLeaves)
					)
					(else 
						(Printf 355 61 ((Inventory at: theItem) description?))
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)
