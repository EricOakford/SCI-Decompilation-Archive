;;; Sierra Script 1.0 - (do not remove this comment)
(script# 397)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Narrator)
(use Sq4Feature)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Motion)
(use System)

(public
	rm397 0
)

(local
	firstWarning
	secondWarning
	[local2 2]
	paidForHintbook
	[local5 2]
	oldSortedFeatures
	smelledWall
	[str 300]
)
(instance rm397 of SQRoom
	(properties
		picture 397
		style FADEOUT
		south 395
		lookStr 1
	)
	
	(method (init)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189 0 0 319 0 319 189 306 189 298 180 197 176
						181 145 297 145 269 113 254 113 223 117 93 117
						66 112 41 112 31 144 133 144 114 178 17 178 7 189
					yourself:
				)
		)
		(music number: 0 stop:)
		(globalSound vol: 127 changeState:)
		(HandsOn)
		(clerkHead posn: 161 70 setPri: 8 init:)
		(clerk posn: 160 85 init:) 
		(super init:)
		(self setRegions: MALL)
		(birdyFeature init:) ;easter egg restore
		(displayCase init:)
		(shelf1 init:)
		(shelf2 init:)
		(bin1 init:)
		(bin2 init:)
		(box init:) ;easter egg restore
		(ego x: 160 y: 180 init:)
		(if (== prevRoomNum 398) (ego posn: 127 163))
		(if (== prevRoomNum 395)
			(curRoom setScript: walkIn)
		else
			(curRoom setScript: clerkScript)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl: origin) cYELLOW)
				(!= heldBox 0)
				(!= firstWarning 1)
				(not (Btst fBoughtHintbook))
			)
			(= firstWarning 1)
			(curRoom setScript: yell)
		)
		(if
			(and
				(& (ego onControl: origin) cLMAGENTA)
				(!= heldBox 0)
				(!= secondWarning 1)
				(not (Btst fBoughtHintbook))
			)
			(= secondWarning 1)
			(curRoom setScript: yell)
		)
		(if
			(and
				(& (ego onControl: origin) cLBLUE)
				(not paidForHintbook)
				(!= heldBox 0)
				(not (Btst fBoughtHintbook))
			)
			(= paidForHintbook TRUE)
			(curRoom setScript: buyBox)
		)
		(if
		(and (not (& (ego onControl: origin) cLBLUE)) (== paidForHintbook TRUE))
			(= paidForHintbook FALSE)
		)
	)
	
	(method (dispose)
		(= useSortedFeatures oldSortedFeatures)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 1))
			;(V_SMELL (VerbFail)) ;don't block the easter eggs
			;(V_TASTE (VerbFail))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance walkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 160 200)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 160 170 self)
			)
			(2
				(HandsOn)
				(client setScript: clerkScript)
				(self dispose:)
			)
		)
	)
)

(instance clerkScript of Script
	(properties)
	
	(method (changeState newState &tmp case)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 1))
			(1
				(cond 
					(
						(and
							(not (Btst fBoughtHintbook))
							(!= heldBox 0)
							(== prevRoomNum 398)
						)
						(tCLERK say: 5 self)
					)
					((and (== prevRoomNum 398) (Btst fBoughtHintbook)) (tCLERK say: 6 self))
					((== prevRoomNum 398) (tCLERK say: 7 self))
					(else (tCLERK say: 8 self))
				)
			)
			(2
				(if (!= prevRoomNum 398)
					(= case (Random 0 2))
					(tCLERK say:
					(switch case
						(0 9)
						(1 10)
						(2 11)
					) self)
					(= seconds 30)
				else
					(self cue:)
				)
			)
			(3
				(HandsOn)
				(clerkHead setCycle: 0 stopUpd: cel: 0)
				(clerk setCycle: 0 cel: 0 setScript: 0)
				(client setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance buyBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tCLERK say: 1 self)
			)
			(1
				(HandsOn)
				(clerkHead setCycle: 0 cel: 0)
				(clerk setCycle: 0 cel: 0 setScript: 0)
				(client setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance yell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(HandsOff)
				(cond 
					((and (== firstWarning 1) (== secondWarning 1)) (tCLERK say: 2 self))
					((OneOf (ego view?) 373 374) (tCLERK say: 3 self))
					(else (tCLERK say: 4 self))
				)
			)
			(1
				(clerk setCycle: 0 cel: 0)
				(clerkHead setCycle: 0 cel: 0)
				(HandsOn)
				(client setScript: 0)
				(self dispose:)
			)
		)
	)
)

(instance talk2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 160 160 self)
			)
			(1
				(otherGuy
					init:
					loop: 1
					illegalBits: 0
					posn: 250 130
					setStep: 4 2
					setCycle: Forward
					setMotion: MoveTo 10 130
				)
				(= cycles 10)
			)
			(2
				(myGuy
					init:
					loop: 2
					illegalBits: 0
					posn: 300 115
					setCycle: Forward
					setMotion: MoveTo 120 115 self
				)
			)
			(3
				(otherGuy setCycle: 0)
				(myGuy setMotion: 0 view: 409 loop: 1 setCycle: Forward)
				(= seconds 4)
			)
			(4
				(myGuy
					view: 408
					loop: 1
					setMotion: MoveTo 0 115 self
					setCycle: Forward
				)
			)
			(5 (HandsOn) (self dispose:))
		)
	)
)

(instance birdy of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (!= (ego loop?) 2)
			(clerk loop: 0 cel: 0)
			(clerkHead loop: 2 setCycle: RandCycle cycleSpeed: 20)
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(clerkHead setCycle: 0 loop: 3 cel: 1)
				(clerk setCycle: 0 loop: 3 cel: 0)
				(= seconds 3)
			)
			(1
				(clerkHead loop: 4 cycleSpeed: 0 setCycle: Forward)
				(clerk loop: 0 cel: 0)
				(= cycles (Random 20 40))
			)
			(2
				(clerkHead loop: 2 setCycle: RandCycle cycleSpeed: 20)
				(= seconds 10)
			)
			(3 (self dispose:))
		)
	)
)

(instance sayThankYou of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tCLERK say: 12 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance clerk of Sq4Prop
	(properties
		view 397
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (self script?))
				(== (clerkHead loop?) 2)
				(== (ego loop?) 2)
				(== (Random 0 100) 1)
				smelledWall
			)
			(if (not (curRoom script?)) 
				(curRoom setScript: birdy)
			)
		)
	)
		
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_TALK
				(curRoom setScript: clerkScript)
			)
			(V_BUCKAZOID
				(cond 
					((not heldBox) (narrator say: 2))
					((Btst fBoughtHintbook) (narrator say: 3))
					((< buckazoids 5) (curRoom setScript: buyBox))
					(else
						(self setScript: sayThankYou)
						(if (< (= buckazoids (- buckazoids 5)) 1)
							(ego put: iBuckazoid)
						)
						(SolvePuzzle fPayForHintbook 5)
						(Bset fBoughtHintbook)
					)
				)
			)
			(V_LOOK (narrator say: 4))
			(V_SMELL (narrator say: 5))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance clerkHead of Sq4Prop
	(properties
		view 397
		loop 1
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((or (clerk script?) (curRoom script?)) 0)
			((> (ego y?) 168) (self setCycle: 0 loop: 1 cel: 11))
			(
			(and (not (& (ego onControl: origin) cLBLUE)) (not cycler)) (self setCycle: RandCycle loop: 2 cycleSpeed: 126))
			((& (ego onControl: origin) cLBLUE) (self setCycle: 0 loop: 1 cel: 0))
		)
	)
	
	(method (doVerb theVerb theItem)
		(clerk doVerb: theVerb theItem)
	)
)

(instance goBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 135 162 self)
			)
			(1 (ego loop: 5) (= cycles 5))
			(2 (curRoom newRoom: 398))
		)
	)
)

(instance displayCase of Sq4Feature
	(properties
		x 157
		y 94
		nsTop 80
		nsLeft 45
		nsBottom 109
		nsRight 269
		sightAngle 90
		lookStr 6
	)
)

(instance bin1 of Sq4Feature
	(properties
		x 89
		y 135
		nsTop 129
		nsLeft 68
		nsBottom 141
		nsRight 111
		sightAngle 90
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (curRoom setScript: goBox))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bin2 of Sq4Feature
	(properties
		x 78
		y 144
		nsTop 141
		nsLeft 49
		nsBottom 148
		nsRight 107
		sightAngle 90
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (curRoom setScript: goBox))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance shelf1 of Sq4Feature
	(properties
		x 161
		y 58
		nsTop 42
		nsLeft 63
		nsBottom 74
		nsRight 260
		sightAngle 90
		lookStr 6
	)
)

(instance otherGuy of Sq4Actor
	(properties
		view 382
	)
)

(instance myGuy of Sq4Actor
	(properties
		view 408
	)
)

(instance shelf2 of Sq4Feature
	(properties
		x 53
		y 63
		nsTop 51
		nsLeft 45
		nsBottom 76
		nsRight 61
		sightAngle 90
		lookStr 6
	)
)

(instance birdyFeature of Sq4Feature 
	(properties
		x 9
		y 33
		nsTop 28
		nsBottom 38
		nsRight 19
		noun NARRATOR
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SMELL
				(if (not (curRoom script?))
					(narrator say: 8)
					(++ smelledWall)
					(self dispose:)
				)
			)
		)
	)
)

(instance tROG of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1008
		talkerNum ROGER
		mouthOffsetX 22
		mouthOffsetY 31
		eyeOffsetX 27
		eyeOffsetY 21
	)
)

(instance tCLERK of Sq4Talker
	(properties
		z 400
		noun CLERK
		view 1708
		talkerNum CLERK
		mouthOffsetX 21
		mouthOffsetY 36
		eyeOffsetX 25
		eyeOffsetY 18
	)
)

(instance box of Sq4Feature
	(properties
		x 307
		y 35
		nsTop 30
		nsLeft 296
		nsBottom 40
		nsRight 319
		noun NARRATOR
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE
				(if (not (ego script?)) ;set script on ego, not room to avoid lockup- DL
					(ego setScript: talk2)
				)
			)
		)
	)
)