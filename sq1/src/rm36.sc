;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm36 0
)

(local
	local0
	local1
	eMsg
	local3
	local4
	local5
	local6
	skippedArcade
	local8
	local9
	local10
	local11
	gEgoCel
	oldEgoSpeed
	local14
)
(procedure (localproc_000e)
	(return (if (< local5 160) rock1 else rock2))
)

(procedure (DisableIconBar)
	(theIconBar disable:
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_SMELL
		ICON_TASTE
		ICON_ITEM
		ICON_INVENTORY
	)
)

(procedure (StartArcade)
	(if
		(= skippedArcade
			(Print 36 0 #button { Play_} 0 #button { Skip_} 1)
		)
		(= local10 (= local11 100))
		(rock1 maxRand: (rock1 minRand?))
		(rock2 minRand: (rock2 maxRand?))
		(HandsOff)
	else
		(= local10 (= local11 750))
		(DisableIconBar)
	)
)

(procedure (localproc_0093)
	(if (!= (flyingEgo x?) 160)
		(= local14 (/ (- 160 (flyingEgo x?)) 3))
	else
		(= local14 0)
	)
)

(class Rock of Actor
	(properties
		moveSpeed 4
		startX 0
		minRand 0
		maxRand 0
		fatal 0
		goForEgo 0
	)
	
	(method (doit)
		(super doit:)
		(if (and local3 (not (& signal ignrAct)))
			(self ignoreActors: TRUE)
		)
	)
)

(class SMover of Motion
	(properties
		incStep 1
		maxStep 10
	)
	
	(method (init)
		(super init: &rest)
		(motorSound pause:)
		(skimmerBanks loop: 1 play:)
		(if (client cycler?)
			((client cycler?) curX: (self x?))
		)
	)
	
	(method (doit)
		(super doit:)
		(if (== (skimmerBanks prevSignal?) -1)
			(skimmerBanks prevSignal: 0)
			(motorSound pause: 0)
		)
		(if (not (client isBlocked:))
			(if (and b-xAxis incStep (< (Abs dx) maxStep))
				(if (> x (client x?))
					(= dx (+ dx incStep))
				else
					(= dx (- dx incStep))
				)
			)
		else
			(self moveDone:)
			(return)
		)
	)
	
	(method (moveDone)
		(if (not (OneOf (ego loop?) 3 4)) (motorSound play:))
		(skimmerBanks stop:)
		(client mover: 0)
		(self dispose:)
	)
)

(class SCycler of Forward
	(properties
		cycle2 1
		curX 0
		lastX 0
	)
	
	(method (doit &tmp temp0 clientLastCel)
		(cond 
			(
			(and (client mover?) ((client mover?) b-xAxis?))
				(cond 
					((or (not (client loop?)) (!= curX lastX))
						(= lastX curX)
						(client
							setLoop: (if (< ((client mover?) x?) (client x?)) 1 else 2)
							cel: 0
						)
					)
					((!= (client cel?) cycle2) (client cel: (self nextCel:)))
				)
			)
			(
				(and
					(client loop?)
					(== (client cel?) (= clientLastCel (client lastCel:)))
				)
				(client setLoop: 0 cel: 0)
			)
			(else (super doit:))
		)
	)
)

(class RockCycler of Cycle
	(properties
		firstCycle2 2
		secondCycle2 6
		endCel 0
		inc 2
		newCelHorizon 55
	)
	
	(method (init)
		(super init: &rest)
		(client ignoreActors: 1)
		(= endCel (client lastCel:))
	)
	
	(method (doit)
		(if (>= (client y?) newCelHorizon)
			(if (!= (client cel?) endCel)
				(client cel: (self nextCel:))
			)
			(if (== inc 16) (client ignoreActors: 0))
			(= newCelHorizon (+ newCelHorizon inc))
			(= inc (* inc 2))
		)
	)
)

(instance rm36 of SQRoom
	(properties
		picture 36
		north 41
	)
	
	(method (init)
		(Load VIEW 136)
		(LoadMany SOUND 437 438 439 440 441 442 443 444 445 446)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
		(= oldEgoSpeed (theGame egoMoveSpeed?))
		(theGame egoMoveSpeed: 0)
		((theIconBar at: ICON_WALK) cursor: 26)
		(HandsOff)
		(addToPics add: mtn1 mtn2 mtn3 eachElementDo: #init doit:)
		(ego
			init:
			view: 26
			setLoop: 0
			cel: 0
			x: 160
			y: 225
			xStep: 3
			yStep: 3
			illegalBits: cYELLOW
			ignoreActors: 0
			setPri: 10
			normal: 0
			setCycle: SCycler
			baseSetter: skimmerBase
			setScript: egoHandleEvent
		)
		(city init: stopUpd:)
		(shadow init: setCycle: Reverse)
		(self setScript: comeOnScreen)
		(= local4 (GetTime SYSTIME1))
	)
	
	(method (doit &tmp temp0 temp1)
		(super doit:)
		(if (and local6 (== (-- local10) 0) (not local3))
			(if (== (city cel?) 3)
				(= local9 1)
			else
				(= local10 local11)
				(city cel: (+ (city cel?) 1) forceUpd:)
			)
		)
		(if
			(and
				local9
				(not (rock1 script?))
				(not (rock2 script?))
			)
			(cond 
				(skippedArcade (Print 36 1))
				((not local0) (Print 36 2))
				(else (Print 36 3))
			)
			(theMusic fade:)
			(self newRoom: 41)
		)
		(= temp0 (GetTime SYSTIME1))
		(if
			(and
				(not local3)
				(not (curRoom script?))
				(not (ego mover?))
				(>= (- temp0 local4) 3)
			)
			(= local4 temp0)
			(if (!= (ego x?) 160)
				(= local5
					(+
						(/ -13400 (= temp1 (/ -9000 (- (ego x?) 160))))
						160
					)
				)
			else
				(= local5
					(if (> (ego x?) 160)
						(rock2 minRand?)
					else
						(rock1 maxRand?)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(if (== (theGame egoMoveSpeed?) 0)
			(theGame egoMoveSpeed: oldEgoSpeed)
		)
		((theIconBar at: ICON_WALK) cursor: 6)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (== (event type?) mouseDown)
			(if
				(and
					(User controls?)
					(not script)
					(== (event type?) mouseDown)
				)
				(ego setMotion: theSMover (event x?) (ego y?))
				(event claimed: 1)
			)
		else
			(super handleEvent: event)
		)
	)
)

(instance egoHandleEvent of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((& (event type?) direction)
				(switch (event message?)
					(dirE
						(if
							(or
								(!= (event message?) eMsg)
								(not (ego mover?))
							)
							(ego setMotion: theSMover 400 (ego y?))
						else
							(ego setMotion: 0)
						)
					)
					(dirW
						(if
							(or
								(!= (event message?) eMsg)
								(not (ego mover?))
							)
							(ego setMotion: theSMover -400 (ego y?))
						else
							(ego setMotion: 0)
						)
					)
					(dirStop
						(ego setMotion: 0)
					)
				)
				(= eMsg (event message?))
				(event claimed: TRUE)
			)
			(
				(and
					(User controls?)
					(== (event type?) keyDown)
					(== (event message?) ENTER)
				)
				(ego setMotion: theSMover (event x?) (ego y?))
				(event claimed: 1)
			)
			(else (super handleEvent: event))
		)
	)
)

(instance comeOnScreen of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(motorSound play: hold: 1)
				(ego setMotion: MoveTo 160 145 self)
			)
			(1
				(rock1
					init:
					setPri: 2
					baseSetter: RockBase
					setScript: (Clone rockAttack)
				)
				(rock2
					init:
					setPri: 2
					baseSetter: RockBase
					setScript: (Clone rockAttack)
				)
				(damageGauge init: stopUpd:)
				(HandsOn)
				(= local6 1)
				(StartArcade)
				(= cycles 1)
			)
			(2
				(theMusic number: 437 loop: -1 play:)
				(self dispose:)
			)
		)
	)
)

(instance rockAttack of Script
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not local3)
				(not local9)
				(client isBlocked:)
				(not (curRoom script?))
			)
			(curRoom setScript: hitRock 0 client)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client hide: setPri: 2 setLoop: (Random 1 4))
				(= cycles (Random 3 30))
			)
			(1
				(if (and (not local9) (not local3))
					(client
						show:
						cel: 0
						posn: (client startX?) 55
						fatal: (if (== (client loop?) 1) 1 else 0)
						ignoreActors: 0
						setCycle: RockCycler
						setMotion:
							MoveTo
							(if
								(and
									(not skippedArcade)
									local5
									(== client (localproc_000e))
									(not local8)
									(= local8 1)
									(client goForEgo: 1)
								)
								local5
							else
								(Random (client minRand?) (client maxRand?))
							)
							189
							self
					)
				else
					(self dispose:)
				)
			)
			(2
				(if (client goForEgo?)
					(client goForEgo: 0)
					(= local8 0)
					(= local5 0)
				)
				(self changeState: 0)
			)
		)
	)
)

(instance tossEgo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_0093)
				(flyingEgo
					setLoop: 5
					cycleSpeed: 5
					setCycle: Forward
					setStep: 5 13
					setMotion: MoveTo (+ (flyingEgo x?) local14) -25 self
				)
			)
			(1 (= ticks 50))
			(2
				(flyingEgo
					setLoop: 7
					cycleSpeed: 3
					setStep: 2 5
					setPri: -1
					posn: (+ (flyingEgo x?) (/ local14 2)) -10
					setMotion: MoveTo (+ (flyingEgo x?) local14) 62 self
				)
			)
			(3
				(flyingEgo
					setLoop: 8
					cel: 0
					cycleSpeed: 7
					setCycle: EndLoop self
				)
			)
			(4 (= seconds 3))
			(5
				(if ((client register?) fatal?)
					(EgoDead 26 5 1 36 4)
				else
					(EgoDead 26 5 1 36 5)
				)
			)
		)
	)
)

(instance hitRock of Script
	
	(method (doit)
		(super doit:)
		(if
			(and
				local3
				(OneOf (ego cel?) 4 6 8 11 14)
				(!= gEgoCel (ego cel?))
			)
			(if (== (ego cel?) 4)
				(soundFx number: 441 loop: 1 play:)
			else
				(skimmerCrash
					number: (if (== (ego cel?) 6)
						442
					else
						(+ (skimmerCrash number?) 1)
					)
					loop: 1
					play:
				)
			)
		)
		(= gEgoCel (ego cel?))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(register ignoreActors: 1 setPri: -1)
				(if (register fatal?) (= local0 4))
				(soundFx number: 438 loop: 1 play:)
				(if (<= (++ local0) 4)
					(ego
						ignoreActors: 1
						setMotion: 0
						setCycle: 0
						setLoop: 3
						cel: 0
						cycleSpeed: 5
						setCycle: EndLoop self
					)
				else
					(= local3 1)
					(theMusic stop:)
					(motorSound stop:)
					(shadow dispose:)
					(flyingEgo
						init:
						setPri: (+ (mtn1 priority?) 1)
						posn: (ego x?) (- (ego y?) 24)
					)
					(ego
						setMotion: 0
						setCycle: 0
						setLoop: 4
						cel: 0
						cycleSpeed: 8
						setCycle: EndLoop self
						setMotion: MoveTo (ego x?) (+ (ego y?) 20)
					)
					(self setScript: tossEgo)
				)
				(damageGauge cel: local0 forceUpd:)
			)
			(1
				(if (<= local0 4)
					(if (== local0 4) (soundFx number: 446 loop: 4 play:))
					(ego
						ignoreActors: 0
						setLoop: 0
						cel: 0
						cycleSpeed: 5
						setCycle: SCycler
					)
					(HandsOn)
					(DisableIconBar)
					(self dispose:)
				else
					(= local0 0)
					(ego setMotion: 0)
					(rock1 setMotion: 0 setScript: 0 setCycle: 0)
					(rock2 setMotion: 0 setScript: 0 setCycle: 0)
				)
			)
		)
	)
)

(instance theSMover of SMover)

(instance mtn1 of PicView
	(properties
		x 38
		y 55
		view 238
		loop 1
		cel 2
		priority 0
		signal fixPriOn
	)
)

(instance mtn2 of PicView
	(properties
		x 247
		y 56
		view 238
		priority 0
		signal fixPriOn
	)
)

(instance mtn3 of PicView
	(properties
		x 282
		y 56
		view 238
		loop 3
		cel 2
		priority 0
		signal fixPriOn
	)
)

(instance city of View
	(properties
		x 160
		y 55
		view 136
		loop 5
		signal (| ignrAct ignrHrz fixedLoop)
	)
)

(instance damageGauge of View
	(properties
		x 293
		y 183
		description {gauge}
		lookStr {This gauge tells the amount of damage done to the skimmer.}
		view 136
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance shadow of Prop
	(properties
		view 26
		loop 6
		priority 1
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 2
	)
	
	(method (init)
		(self posn: (+ (ego x?) 3) (+ (ego y?) 3))
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(self x: (+ (ego x?) 3) y: (+ (ego y?) 3))
	)
)

(instance flyingEgo of Actor
	(properties
		view 26
		loop 5
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 8
		illegalBits $0000
		moveSpeed 2
	)
)

(instance rock1 of Rock
	(properties
		yStep 6
		view 136
		illegalBits $0000
		xStep 5
		startX 155
		minRand 4
		maxRand 114
	)
)

(instance rock2 of Rock
	(properties
		yStep 6
		view 136
		illegalBits $0000
		xStep 5
		startX 165
		minRand 211
		maxRand 316
	)
)

(instance skimmerBase of Code
	(properties)
	
	(method (doit param1)
		(param1
			brTop: (- (param1 y?) 13)
			brLeft: (- (param1 x?) 25)
			brRight: (+ (param1 x?) 25)
			brBottom: (param1 y?)
		)
	)
)

(instance RockBase of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1 temp2 temp3)
		(= temp0 (= temp1 (= temp2 (= temp3 0))))
		(switch (param1 loop?)
			(1
				(= temp0 7)
				(= temp1 10)
				(= temp2 10)
			)
			(2 (= temp0 3) (= temp2 8))
			(3
				(= temp0 3)
				(= temp1 7)
				(= temp2 9)
			)
			(4
				(= temp0 3)
				(= temp1 8)
				(= temp2 16)
				(= temp3 1)
			)
		)
		(param1
			brTop: (- (param1 y?) temp0)
			brLeft: (- (param1 x?) temp1)
			brRight: (+ (param1 x?) temp2)
			brBottom: (- (param1 y?) temp3)
		)
	)
)

(instance motorSound of Sound
	(properties
		number 439
		loop -1
	)
)

(instance skimmerCrash of Sound
	(properties
		number 442
	)
)

(instance skimmerBanks of Sound
	(properties
		number 440
	)
)
