;;; Sierra Script 1.0 - (do not remove this comment)
(script# 535)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm535 0
)

(local
	local0
	thisTime
	local2
	[local3 2]
	local5
	michelleTalking
	local7
)
(procedure (MichellePleased)
	(localproc_141f)
	(mEyes hide:)
	(switch (++ local5)
		(1
			(if (or (ego has: iMoney) (ego has: iCreditCards) (ego has: iDayTrotter))
				(curRoom setScript: sSuckFinger)
			else
				(curRoom setScript: sStartGoDown)
			)
		)
		(2
			(if (or (ego has: iMoney) (ego has: iCreditCards) (ego has: iDayTrotter))
				(curRoom setScript: sCherry)
			else
				(curRoom setScript: sStartGoDown)
			)
		)
		(3
			(curRoom setScript: sStartGoDown)
		)
	)
)

(procedure (localproc_141f)
	(= michelleTalking 1)
	(= local7 0)
)

(instance rm535 of LLRoom
	(properties
		lookStr {You are sitting with the gorgeous Michelle Milken in an exclusive private booth in the exclusive Herman Hollerith Room in the exclusive Hard Disk Cafe in exclusive New York City, trying hard not to stare through her see-through blouse.}
		picture 535
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(michelle init: stopUpd: setScript: sConversation)
		(if (not (Btst fHardDiskCafeDone))
			(Load SOUND 536)
			(mEyes init: setScript: sBlink)
			(mMouth init: hide:)
			(iceCream init:)
			(cherry init:)
			(HandsOn)
			(theIconBar disable: ICON_WALK)
			(User canControl: FALSE)
		else
			(michelle view: 540)
			(iceCream init:)
			(mMouth init: hide:)
			(LoadMany SOUND 538 537)
			(if (not (Btst fMichelleAteCherry))
				(cherry init:)
			)
			(self setScript: sGoDown)
		)
		(face init:)
		(tits init:)
	)
	
	(method (doit)
		(super doit:)
		(if (and (not script) (!= thisTime (GetTime SYSTIME1)))
			(= thisTime (GetTime SYSTIME1))
			(if (== (++ local7) 90)
				(localproc_141f)
				(HandsOff)
				(self setScript: sLoser)
			)
			(if (and (not michelleTalking) (== (++ local2) 60))
				(TimePrint 535 0)
			)
		)
	)
)

(instance sLoser of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say Michelle_Milken 535 1 #dispose #caller self)
			)
			(1
				(DrawPic 1 IRISIN)
				(michelle hide:)
				(Say ego 535 2 #at -1 185)
				(= seconds 3)
			)
			(2
				(TimePrint 535 3)
				(= seconds 3)
			)
			(3
				(TimePrint 535 4)
				(curRoom newRoom: 510)
			)
		)
	)
)

(instance sGoDown of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== (theMusic number?) 538)
				(== (theMusic prevSignal?) 10)
			)
			(theMusic number: 538 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(michelle
					view: 540
					setLoop: 0
					setCel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(iceCream hide:)
				(if (IsObject cherry) (cherry hide:))
				(michelle setCycle: EndLoop self)
			)
			(3 (= seconds 2))
			(4
				(soundFX number: 537 play:)
				(= seconds 3)
			)
			(5
				(soundFX stop:)
				(michelle setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(6 (= seconds 3))
			(7
				(mMouth
					view: 540
					setLoop: 3
					setCel: 0
					x: (- (mMouth x?) 1)
					setPri: 14
					show:
					setCycle: EndLoop self
				)
			)
			(8 (= seconds 2))
			(9
				(mMouth hide:)
				(michelle
					view: 541
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
				(iceCream
					setCel: 1
					x: (michelle x?)
					y: (michelle y?)
					show:
				)
			)
			(10 (= seconds 3))
			(11
				(michelle hide:)
				(iceCream dispose:)
				(cherry dispose:)
				(DrawPic 1 IRISIN)
				(= ticks 34)
			)
			(12
				(michelle
					view: 545
					posn: 192 119
					setLoop: 0
					setCel: 0
					show:
				)
				(DrawPic 535 HMIRROR)
				(= seconds 4)
			)
			(13
				(michelle setCel: 1)
				(TimePrint 535 5)
				(if (>= ((Inventory at: iCamcorder) state?) 100)
					(CheckTapeState tapeMICHELLE)
					(SolvePuzzle 20 fRecordedMichelle)
					(TimePrint 535 6)
				else
					(TimePrint 535 7)
				)
				(= seconds 4)
			)
			(14
				(michelle setLoop: 1 setCel: 0)
				(= seconds 4)
			)
			(15
				(michelle setCel: 1)
				(= seconds 4)
			)
			(16
				(michelle setCel: 2)
				(= seconds 4)
			)
			(17
				(DrawPic 1 IRISIN)
				(michelle hide:)
				(= seconds 3)
			)
			(18
				(if (ego has: iMoney)
					(ego put: iMoney)
					(TimePrint 535 8)
				)
				(if (ego has: iCreditCards)
					(ego put: iCreditCards)
					(TimePrint 535 9)
				)
				(if (ego has: iDayTrotter)
					(ego put: iDayTrotter)
					(TimePrint 535 10)
				)
				(SolvePuzzle 40 fAfterMichelle)
				(TimePrint 535 11)
				(= cycles 2)
			)
			(19 (curRoom newRoom: 510))
		)
	)
)

(instance sBlink of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= seconds (Random 1 4)))
			(1 (mEyes setCycle: EndLoop self))
			(2 (= cycles (Random 1 3)))
			(3 (mEyes setCycle: BegLoop self))
			(4 (self init:))
		)
	)
)

(instance sSuckFinger of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mEyes hide:)
				(michelle view: 536 setCel: 0 cycleSpeed: 16 setLoop: 0)
				(= ticks 123)
			)
			(1
				(michelle setCycle: CycleTo 7 1 self)
			)
			(2 (= seconds 5))
			(3
				(michelle setCycle: EndLoop self)
			)
			(4
				(michelle view: 535 setLoop: 0 cycleSpeed: 10 stopUpd:)
				(= seconds 2)
			)
			(5
				(Say Michelle_Milken 535 12 #dispose #caller self)
				(mEyes show:)
				(HandsOn)
				(theIconBar disable: ICON_WALK)
				(self dispose:)
			)
		)
	)
)

(instance sCherry of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mEyes hide:)
				(michelle view: 535 setLoop: 0 stopUpd:)
				(mMouth show: setLoop: 5 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(soundFX number: 536 loop: 1 play:)
				(= ticks 30)
			)
			(2
				(cherry setLoop: 7 setCycle: EndLoop cherry)
				(mMouth setCycle: BegLoop self)
			)
			(3
				(cherry hide:)
				(mMouth hide:)
				(= seconds 2)
			)
			(4
				(Bset fMichelleAteCherry)
				(Say ego 535 13 #at -1 185)
				(mEyes show:)
				(HandsOn)
				(theIconBar disable: ICON_WALK)
				(self dispose:)
			)
		)
	)
)

(instance sStartGoDown of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SetFFRoom 510)
				(Say Michelle_Milken 535 14 #dispose #caller self)
			)
			(1 (= seconds 2))
			(2
				(TimePrint 535 15 #at -1 185)
				(= seconds 2)
			)
			(3
				(Say Michelle_Milken 535 16 #dispose #caller self)
			)
			(4 (= seconds 2))
			(5
				(Say Michelle_Milken 535 17 #dispose #caller self)
			)
			(6 (curRoom newRoom: 530))
		)
	)
)

(instance sTrotter of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say ego 535 18)
				(Say Michelle_Milken 535 19 #dispose #caller self)
			)
			(1
				(ego put: 11)
				(SolvePuzzle 5 fGiveMichelleDayTrotter)
				(Say Michelle_Milken 535 20 #dispose #caller self)
			)
			(2 (MichellePleased))
		)
	)
)

(instance sCreditCards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say ego 535 21)
				(ego put: iCreditCards)
				(SolvePuzzle 5 fGiveMichelleCreditCards)
				(Say Michelle_Milken 535 22 #dispose #caller self)
			)
			(1 (MichellePleased))
		)
	)
)

(instance sMoney of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say ego 535 23)
				(ego put: iMoney)
				(SolvePuzzle 5 fGiveMichelleMoney)
				(Say Michelle_Milken 535 24 #dispose #caller self)
			)
			(1 (MichellePleased))
		)
	)
)

(instance sConversation of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(Say ego 535 25 #at -1 185)
				(Say Michelle_Milken 535 26 108 #at -1 185)
			)
			(2
				(Say ego 535 27 #at -1 185)
				(Say Michelle_Milken 535 28 #dispose #caller self #at -1 185)
			)
			(3
				(Say ego 535 29 #at -1 185)
				(Say Michelle_Milken 535 30 108 #at -1 185)
			)
			(4
				(Say ego 535 31 #at -1 185)
				(Say Michelle_Milken 535 32 #dispose #caller self #at -1 185)
			)
			(5
				(Say ego 535 33 #at -1 185)
				(Say Michelle_Milken 535 34 108 #at -1 185)
			)
			(6
				(Say ego 535 35 #at -1 185)
				(Say Michelle_Milken 535 36 #dispose #caller self #at -1 185)
			)
			(7
				(Say Michelle_Milken 535 37 #dispose #caller self #at -1 185)
			)
			(8 (Say ego 535 38 #at -1 185))
			(9
				(Say ego 535 39 #at -1 185)
				(Say Michelle_Milken 535 40 #dispose #caller self #at -1 185)
			)
			(10
				(SolvePuzzle 5 fMichelleGetsItOn)
				(Say ego 535 41 #at -1 185)
				(HandsOff)
				(curRoom setScript: sStartGoDown)
			)
		)
	)
)

(instance michelle of Actor
	(properties
		x 150
		y 105
		description {Michelle Milken}
		sightAngle 90
		yStep 1
		view 535
		priority 3
		signal (| ignrAct fixedCel fixPriOn)
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 535 42)
				(TimePrint 535 43 #at -1 185)
			)
			(verbDo
				(TimePrint 535 44)
			)
			(verbZipper
				(TimePrint 535 45)
			)
			(verbUse
				(switch theItem
					(iMoney
						(HandsOff)
						(localproc_141f)
						(curRoom setScript: sMoney)
					)
					(iCreditCards
						(HandsOff)
						(localproc_141f)
						(curRoom setScript: sCreditCards)
					)
					(iDayTrotter
						(HandsOff)
						(localproc_141f)
						(curRoom setScript: sTrotter)
					)
					(iNapkin
						(TimePrint 535 46)
					)
					(else
						(TimePrint 535 47)
					)
				)
			)
			(verbTalk
				(= michelleTalking TRUE)
				(sConversation cue:)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance mEyes of Prop
	(properties
		x 153
		y 65
		description {her eyes}
		sightAngle 90
		lookStr {Her sultry eyes burn into your soul.}
		view 535
		loop 2
		priority 13
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(michelle doVerb: theVerb theItem)
	)
)

(instance mMouth of Prop
	(properties
		x 153
		y 68
		description {her mouth}
		sightAngle 90
		lookStr {Her lips are moist and inviting.}
		view 535
		loop 4
		priority 14
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
	
	(method (doVerb theVerb theItem)
		(michelle doVerb: theVerb theItem)
	)
)

(instance iceCream of View
	(properties
		x 151
		y 126
		nsTop 118
		nsLeft 137
		nsBottom 142
		nsRight 165
		description {the ice cream}
		sightAngle 90
		lookStr {You love ice cream. Especially ice cream that's sitting where THAT ice cream is!}
		view 535
		loop 6
		signal ignrAct
	)
)

(instance cherry of Prop
	(properties
		x 148
		y 101
		description {her cherry}
		sightAngle 90
		lookStr {Her cherry looks soft, red, ripe and ready for the picking!}
		view 535
		loop 9
		cel 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (cue)
		(super cue:)
		(self dispose:)
	)
)

(instance tits of Feature
	(properties
		x 151
		y 130
		nsTop 107
		nsLeft 130
		nsBottom 127
		nsRight 172
		description {her breasts}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 535 48)
			)
			(verbDo
				(TimePrint 535 49)
			)
			(else 
				(michelle doVerb: theVerb theItem)
			)
		)
	)
)

(instance face of Feature
	(properties
		x 155
		y 130
		nsTop 49
		nsLeft 132
		nsBottom 93
		nsRight 179
		description {her face}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 535 50)
			)
			(verbDo
				(Say Michelle_Milken 535 51 #dispose)
			)
			(else 
				(michelle doVerb: theVerb theItem)
			)
		)
	)
)

(instance Michelle_Milken of Talker
	(properties
		x 8
		y 5
		nsTop 58
		nsLeft 142
		view 1535
		loop 1
		talkWidth 300
		name "Michelle Milken"
	)
	
	(method (init)
		(= eyes MMEyes)
		(= mouth MMMouth)
		(super init: &rest)
	)
)

(instance MMEyes of Prop
	(properties
		nsLeft 1
		view 1535
		loop 2
		signal ignrAct
		cycleSpeed 25
	)
)

(instance MMMouth of Prop
	(properties
		nsTop 10
		nsLeft 7
		view 1535
		signal ignrAct
	)
)

(instance soundFX of Sound
	(properties
		flags mNOPAUSE
		loop -1
	)
)
