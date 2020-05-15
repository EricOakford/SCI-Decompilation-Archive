;;; Sierra Script 1.0 - (do not remove this comment)
(script# rmEveCloseup) ;385
(include game.sh)
(use Main)
(use Intrface)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm385 0
)

(local
	talkCount
	lookCount
	eyeCycles
	mouthTimer
)
(procedure (BubblesOn)
	(chestBubbles init: cycleSpeed: howFast setCycle: Forward)
	(leftRim init: cycleSpeed: howFast setCycle: Forward)
	(bubble1 init: cycleSpeed: howFast setCycle: Forward)
	(bubble2 init: cycleSpeed: howFast setCycle: Forward)
	(bubble3 init: cycleSpeed: howFast setCycle: Forward)
	(smallBubble init: cycleSpeed: howFast setCycle: Forward)
)

(procedure (BubblesOff)
	(chestBubbles dispose:)
	(leftRim dispose:)
	(bubble1 dispose:)
	(bubble2 dispose:)
	(bubble3 dispose:)
	(smallBubble dispose:)
)

(instance rm385 of Room
	(properties
		picture rmEveCloseup
	)
	
	(method (init)
		(LoadMany VIEW 385 386 387)
		(herEyes init: cycleSpeed: (+ 1 howFast))
		(eyeRight init: cycleSpeed: (+ 1 howFast))
		(eyeLeft init: cycleSpeed: (+ 1 howFast))
		(herMouth init: cycleSpeed: (+ 1 howFast))
		(fEveHead init:)
		(fBoobs init:)
		(fEveArms init:)
		(fTowel init:)
		(ego init: normal: 0 z: 1000)
		(aSpaButton init: stopUpd:)
		(super init:)
		(curRoom setScript: sWanderEyes)
		(if (Btst fBubblesOff) (BubblesOff) else (BubblesOn))
		(theMusic send: 4 78 0 send: 9 78 0 send: 5 78 1)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (>= mouthTimer 5) (= eyeCycles 1) else (= eyeCycles 0))
		(cond 
			((== (curRoom script?) 0) (curRoom setScript: sWanderEyes))
			((ego mover?) (curRoom newRoom: 380))
		)
	)
	
	(method (doVerb theVerb theItem)
		(return
			(switch theVerb
				(verbLook
					(switch (++ lookCount)
						(1 (Print 385 0 #at -1 20))
						(2 (Print 385 1 #at -1 20))
						(else 
							(Print 385 2)
							(Print 385 3)
						)
					)
					(if (!= (curRoom script?) sEveHappy)
						(curRoom setScript: sEveHappy)
					)
					(return TRUE)
				)
				(verbDo
					(Print 385 4)
					(if (!= (curRoom script?) sEveHappy)
						(curRoom setScript: sEveHappy)
					)
					(return TRUE)
				)
				(verbTalk
					(switch (++ talkCount)
						(1
							(Print 385 5 #at -1 20)
							(Print 385 6 #at -1 20)
						)
						(2
							(Print 385 7 #at -1 20)
							(Print 385 8 #at -1 20)
						)
						(3
							(Print 385 9 #at -1 20)
							(Print 385 10 #at -1 20)
						)
						(4
							(Print 385 11 #at -1 20)
							(Print 385 12 #at -1 20)
						)
						(else  (Print 385 13 #at -1 20))
					)
					(if (!= (curRoom script?) sEveHappy)
						(curRoom setScript: sEveHappy)
					)
					(return TRUE)
				)
				(verbTaste
					(Print 385 14)
					(Print 385 15)
					(if (!= (curRoom script?) sEveHappy)
						(curRoom setScript: sEveHappy)
					)
					(return TRUE)
				)
				(verbZipper
					(Print 385 16)
					(Print 385 17 #at -1 140)
					(if (!= (curRoom script?) sEveAngry)
						(curRoom setScript: sEveAngry)
					)
					(return TRUE)
				)
				(verbUse
					(switch theItem
						(iWatch
							(Print 385 18)
							(curRoom setScript: sEveAngry)
						)
						(iApple
							(HandsOff)
							(curRoom setScript: sEveEatsApple)
							(SolvePuzzle fGaveEveApple 15)
							(Bset fFollowingEve)
						)
						(iDiscoPass
							(Print 385 19)
							(curRoom setScript: sEveAngry)
						)
						(iWhiskey
							(if (!= (curRoom script?) sEveAngry)
								(curRoom setScript: sEveAngry)
							)
							(Print 385 20)
							(Print 385 21)
						)
						(iRemoteControl (Print 385 22))
						(iLubber
							(if (!= (curRoom script?) sEveHappy)
								(curRoom setScript: sEveHappy)
							)
							(Print 385 23)
						)
						(iHammer (Print 385 24))
						(iPocketKnife
							(if (!= (curRoom script?) sEveAngry)
								(curRoom setScript: sEveAngry)
							)
							(Print 385 25)
						)
						(else 
							(Print 385 26)
							(Print 385 27 #at -1 140)
						)
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb theItem)
				)
			)
		)
	)
)

(instance sWanderEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= mouthTimer (Random 0 10))
				(herMouth stopUpd:)
				(= seconds (Random 4 10))
			)
			(1
				(cond 
					((== eyeCycles 0) (herEyes init: setLoop: 8 setCel: 0 setCycle: EndLoop))
					((== eyeCycles 1) (herEyes init: setLoop: 7 setCel: 0 setCycle: EndLoop))
				)
				(= seconds (Random 2 6))
			)
			(2
				(= mouthTimer (Random 0 10))
				(herEyes setCycle: CycleTo 0 -1)
				(= seconds (Random 2 6))
			)
			(3
				(cond 
					((== eyeCycles 1) (herEyes init: setLoop: 8 setCel: 0 setCycle: EndLoop))
					((== eyeCycles 0) (herEyes init: setLoop: 7 setCel: 0 setCycle: EndLoop))
				)
				(= seconds (Random 2 6))
			)
			(4
				(herEyes setCycle: CycleTo 0 -1)
				(= seconds (Random 2 6))
			)
			(5 (self init:))
		)
	)
)

(instance sEveAngry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(herEyes setLoop: 2 setCel: 0 setCycle: EndLoop)
				(herMouth setLoop: 4 setCel: 0 setCycle: EndLoop)
				(= seconds (Random 4 9))
			)
			(1
				(herEyes setCycle: BegLoop self)
				(herMouth setCycle: BegLoop self)
			)
			(2 0)
			(3 (self dispose:))
		)
	)
)

(instance sWink_Pucker of Script
	(properties
		name "sWink&Pucker"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(eyeLeft setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(herMouth setLoop: 5 setCel: 0 setCycle: EndLoop)
				(eyeLeft setCycle: BegLoop)
				(= seconds 2)
			)
			(2
				(herMouth setCycle: BegLoop self)
			)
			(3 (self dispose:))
		)
	)
)

(instance sEveHappy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(herEyes init: setLoop: 1 setCel: 0 setCycle: BegLoop)
				(herMouth setLoop: 3 setCel: 0 setCycle: EndLoop)
				(= seconds (Random 4 9))
			)
			(1
				(herMouth setCycle: BegLoop self)
			)
			(2 (self dispose:))
		)
	)
)

(instance sEveEatsApple of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic send: 6 78 0)
				(herEyes dispose:)
				(herMouth setLoop: 3 setCel: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(1
				(Print 385 28)
				(ego put: iApple)
				(= seconds 3)
			)
			(2
				(Print 385 29)
				(Print 385 30 #at -1 140)
				(= seconds 3)
			)
			(3
				(Print 385 31 #at -1 20)
				(curRoom newRoom: 380)
			)
		)
	)
)

(instance sButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aSpaButton setCel: 0 setCycle: EndLoop self)
			)
			(1
				(if (Btst fBubblesOff)
					(Print 385 32)
					(BubblesOff)
				else
					(Print 385 33)
					(BubblesOn)
				)
				(aSpaButton setCycle: BegLoop self)
			)
			(2
				(aSpaButton stopUpd:)
				(if (!= (curRoom script?) sEveHappy)
					(curRoom setScript: sEveHappy)
				)
				(self dispose:)
			)
		)
	)
)

(instance herEyes of Prop
	(properties
		x 159
		y 192
		z 144
		description {her eyes}
		sightAngle 180
		lookStr {Her eyes are a deep loamy brown.}
		view 385
		loop 8
		cycleSpeed 2
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo (Print 385 34))
			(else 
				(curRoom doVerb: theVerb theItem)
			)
		)
	)
)

(instance eyeLeft of Prop
	(properties
		x 159
		y 192
		z 144
		description {her eyes}
		sightAngle 180
		lookStr {Her eyes are a deep loamy brown.}
		view 385
		loop 1
	)
	
	(method (doVerb theVerb theItem)
		(herEyes doVerb: theVerb theItem)
	)
)

(instance eyeRight of Prop
	(properties
		x 159
		y 192
		z 144
		description {her eyes}
		sightAngle 180
		lookStr {Her eyes are a deep loamy brown.}
		view 385
		cycleSpeed 2
	)
	
	(method (doVerb theVerb theItem)
		(herEyes doVerb: theVerb theItem)
	)
)

(instance herMouth of Prop
	(properties
		x 156
		y 68
		description {her mouth}
		sightAngle 180
		lookStr {Her mouth drives you crazy.}
		view 385
		loop 3
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem)
	)
)

(instance aSpaButton of Prop
	(properties
		x 310
		y 189
		z 85
		description {the button}
		lookStr {There's a control button built into the surface of the spa's decking.}
		view 386
		loop 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(self setScript: sButton)
				(sButton init:)
				(Btoggle fBubblesOff)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chestBubbles of Prop
	(properties
		x 119
		y 154
		description {the bubbles}
		view 386
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Print 385 35))
			(verbDo (Print 385 36))
			(verbTalk (Print 385 37))
			(verbZipper (Print 385 38))
			(verbTaste (Print 385 39))
			(verbUse (Print 385 40))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance leftRim of Prop
	(properties
		x 40
		y 134
		description {the bubbles}
		view 386
		loop 3
		cel 1
	)
	
	(method (doVerb theVerb theItem)
		(chestBubbles doVerb: theVerb theItem)
	)
)

(instance smallBubble of Prop
	(properties
		x 264
		y 173
		description {the bubbles}
		view 386
		loop 1
		cel 2
	)
	
	(method (doVerb theVerb theItem)
		(chestBubbles doVerb: theVerb theItem)
	)
)

(instance bubble1 of Prop
	(properties
		x 243
		y 142
		description {the bubbles}
		view 386
		loop 5
	)
	
	(method (doVerb theVerb theItem)
		(chestBubbles doVerb: theVerb theItem)
	)
)

(instance bubble2 of Prop
	(properties
		x 285
		y 140
		description {the bubbles}
		view 386
		loop 6
		cel 1
	)
	
	(method (doVerb theVerb theItem)
		(chestBubbles doVerb: theVerb theItem)
	)
)

(instance bubble3 of Prop
	(properties
		x 256
		y 166
		description {the bubbles}
		view 386
		loop 7
		cel 2
	)
	
	(method (doVerb theVerb theItem)
		(chestBubbles doVerb: theVerb theItem)
	)
)

(instance fTowel of Feature
	(properties
		x 42
		y 70
		nsTop 51
		nsBottom 89
		nsRight 85
		description {her towel}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook (Bset fEveNameKnown) (Print 385 41))
			(verbTaste
				(Printf 385 42 (if (Btst fEveNameKnown) {Eve's} else {Her}))
			)
			(verbDo (Print 385 43))
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fEveHead of Feature
	(properties
		x 158
		y 190
		nsTop 32
		nsLeft 105
		nsBottom 110
		nsRight 211
		description {her face}
		sightAngle 180
		lookStr {What a fabulous face!}
	)
	
	(method (doVerb theVerb theItem)
		(curRoom doVerb: theVerb theItem &rest)
	)
)

(instance fEveArms of Feature
	(properties
		x 158
		y 116
		nsTop 96
		nsLeft 9
		nsBottom 137
		nsRight 292
		description {her body}
		sightAngle 180
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Printf 385 44 (if (Btst fEveNameKnown) {Eve's} else {her}))
			)
			(verbDo
				(if (!= (curRoom script?) sEveHappy)
					(curRoom setScript: sEveHappy)
				)
				(curRoom doVerb: theVerb theItem &rest)
			)
			(else 
				(curRoom doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fBoobs of Feature
	(properties
		x 140
		y 185
		z 40
		nsTop 125
		nsLeft 80
		nsBottom 155
		nsRight 201
		description {her breasts}
		sightAngle 180
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst 17)
					(Print 385 45)
				else
					(Printf 385 46 (if (Btst 18) {Eve's} else {her}))
				)
			)
			(verbDo
				(Print 385 47)
				(if (!= (curRoom script?) sEveHappy)
					(curRoom setScript: sEveHappy)
				)
			)
			(verbZipper
				(Print 385 48)
				(if (!= (curRoom script?) sEveAngry)
					(curRoom setScript: sEveAngry)
				)
			)
			(else 
				(curRoom doVerb: theVerb theItem &rest)
			)
		)
	)
)
