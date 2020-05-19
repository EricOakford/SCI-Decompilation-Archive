;;; Sierra Script 1.0 - (do not remove this comment)
(script# GOAT)
(include game.sh)
(use Main)
(use Intrface)
(use TurnLooper)
(use LoadMany)
(use Follow)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use System)

(public
	rgGoat 0
	goatLooper 1
	killGoat 2
)

(local
	local0
	goatWontEnterWater
	goatCantSeeEgo
)
(instance goatLooper of TurnLooper
	(properties)
)

(instance rgGoat of Region
	(properties)
	
	(method (init)
		(LoadMany VIEW 165 167 166)
		(LoadMany SCRIPT AVOIDER FOLLOW SIGHT)
		(super init: &rest)
		(goatLooper viewChange: 167)
		(theGoat
			view: 165
			ignoreHorizon: TRUE
			illegalBits: -7618
			setLoop: -1
			setPri: -1
			looper: goatLooper
			setAvoider:
				(cond 
					((OneOf curRoomNum 10 11 15 16 23) 0)
					((OneOf curRoomNum 25 39 41) (Avoider offScreenOK: FALSE))
					(else (Avoider offScreenOK: TRUE))
				)
			setCycle: Walk
			setMotion: Follow ego 60
			init:
		)
		(switch prevRoomNum
			((curRoom west?)
				(switch prevRoomNum
					(15
						(theGoat
							posn: -20 (proc0_17 90 (theGoat y?) (+ (curRoom horizon?) 2))
						)
					)
					(else 
						(theGoat posn: (- (ego x?) 30) (+ (ego y?) 2))
					)
				)
			)
			((curRoom east?)
				(switch prevRoomNum
					(16
						(theGoat
							posn: 340 (proc0_17 90 (theGoat y?) (+ (curRoom horizon?) 2))
						)
					)
					(11
						(if (<= (ego y?) 77)
							(theGoat posn: (+ (ego x?) 20) (- (ego y?) 2))
						else
							(theGoat posn: (+ (ego x?) 20) (+ (ego y?) 2))
						)
					)
					(else 
						(theGoat posn: (+ (ego x?) 30) (+ (ego y?) 2))
					)
				)
			)
			((curRoom north?)
				(switch prevRoomNum
					(14
						(theGoat posn: (ego x?) (curRoom horizon?))
					)
					(30
						(theGoat posn: (- (ego x?) 25) 50)
					)
					(32
						(theGoat
							posn: (proc0_17 309 (- (ego x?) 25) 240) (ego y?)
						)
					)
					(else 
						(if (< (ego x?) 160)
							(theGoat posn: (- (ego x?) 25) (ego y?))
						else
							(theGoat posn: (+ (ego x?) 25) (ego y?))
						)
					)
				)
			)
			((curRoom south?)
				(if (> (ego x?) 160)
					(theGoat posn: (+ (ego x?) 5) (+ (ego y?) 30))
				else
					(theGoat posn: (- (ego x?) 5) (+ (ego y?) 30))
				)
			)
		)
		(= roomWithLiveGoat curRoomNum)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (Btst fGoatFollows) local0 (> (ego distanceTo: theGoat) 120))
			(if (and egoInWater (& (ego onControl: origin) $059e))
				(Print 600 0)
			else
				(Print 600 1)
			)
			(= local0 0)
			(Bclr fGoatFollows)
			(theGoat setScript: goatBobs)
		)
		(if
			(and
				egoInWater
				(Btst fGoatFollows)
				(not local0)
				(& (ego onControl: origin) cBLUE)
			)
			(= local0 1)
			(theGoat setMotion: 0)
			(if (not goatWontEnterWater)
				(= goatWontEnterWater TRUE)
				(Print 600 2)
			)
		)
		(if
			(and
				(> (theGoat y?) (curRoom horizon?))
				(theGoat ignoreHorizon:)
			)
			(theGoat ignoreHorizon: 0)
		)
		(if (and (not egoInWater) local0 (Btst fGoatFollows))
			(= local0 0)
			(theGoat setMotion: Follow ego 60)
		)
		(if
			(and
				(Btst fInvisible)
				(OneOf curRoomNum 25 39 41)
				(!= (curRoom script?) goatLeaves)
				(Btst fGoatFollows)
			)
			(= goatCantSeeEgo TRUE)
			(curRoom setScript: goatLeaves)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((Said 'attack,kick/goat')
				(if roomWithDeadGoat
					(Print 600 4)
				else
					(Print 600 5)
				)
			)
			((Said 'look,check/goat')
				(cond 
					((Btst fGoatFollows)
						(Print 600 6)
					)
					((== (theGoat script?) goatBobs)
						(Print 600 7)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((or (Said 'kill/goat/shot') (Said 'shoot/goat'))
				(cond 
					((and (ego has: iSlingshot) (ego has: iPebbles))
						(Print 600 8)
					)
					((or (not (ego has: iSlingshot)) (not (ego has: iPebbles)))
						(Print 600 9)
					)
				)
			)
			((Said 'capture,pull,pull,get,take/goat')
				(if (Btst fGoatFollows)
					(Print 600 10)
				else
					(Print 600 11)
				)
			)
			((Said 'stab,kill/goat')
				(cond 
					(roomWithDeadGoat
						(Print 600 12)
					)
					((curRoom script?)
						(CantDo)
					)
					((> (ego distanceTo: theGoat) 35)
						(Print 600 13)
					)
					((not (ego has: iDagger))
						(Print 600 14)
					)
					(else
						(curRoom setScript: killGoat)
					)
				)
			)
			((or (Said 'feed,give/carrot[/goat]') (Said 'feed,give/goat[/carrot]'))
				(cond 
					((not (ego has: iCarrot))
						(DontHave)
					)
					((== (theGoat view?) 166)
						(cond 
							((< (theGoat loop?) loopS)
								(Print 600 15)
							)
							((> (theGoat loop?) loopW)
								(Print 600 16)
							)
						)
					)
					((curRoom script?)
						(CantDo)
					)
					((> (GetDistance (ego x?) (ego y?) (theGoat x?) (theGoat y?)) 26)
						(CantReach)
					)
					((Btst fInvisible)
						(Print 600 17)
					)
					((== (theGoat script?) goatBobs)
						(if (ego has: iCarrot)
							(Print 600 18)
						else
							(Print 600 19)
						)
					)
					((ego has: iCarrot)
						((ScriptID 0 21) number: 44 init: play:)
						(Bclr fGoatFollows)
						(ego put: iCarrot)
						(theGame changeScore: -2)
						(if (OneOf curRoomNum 25 39 41)
							(curRoom setScript: goatLeaves)
						else
							(Print 600 20)
							(theGoat setScript: goatBobs)
						)
					)
					(else
						(Print 600 19)
					)
				)
			)
			((or (Said 'show/carrot') (Said 'coax,coax/goat') (Said 'show/goat/carrot') (Said 'coax,coax/goat/carrot'))
				(cond 
					((not (ego has: iCarrot))
						(Print 600 19)
					)
					((> (GetDistance (ego x?) (ego y?) (theGoat x?) (theGoat y?)) 50)
						(CantReach)
					)
					((Btst fGoatFollows)
						(Print 600 21)
					)
					((Btst fInvisible)
						(Print 600 17)
					)
					(else
						(Print 600 22)
					)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if egoInWater
			(Print 600 3)
			(Bclr fGoatFollows)
		)
		(super newRoom: &rest)
	)
)

(instance killGoat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 600 23)
				(goatKill init: play:)
				(theGoat
					view: 166
					setCel: 0
					setLoop: (if (< (theGoat heading?) 180) 0 else 1)
					setMotion: 0
					setScript: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(goatKill stop:)
				(= roomWithDeadGoat curRoomNum)
				(= deadGoatX (theGoat x?))
				(= deadGoatY (theGoat y?))
				(= deadGoatLoop (theGoat loop?))
				(ego put: iDagger)
				(theGame changeScore: -5)
				(Bclr fGoatFollows)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goatBobs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGoat
					setAvoider: 0
					illegalBits: -16322
					setMotion:
						MoveTo
						(Random 10 310)
						(Random (+ (curRoom horizon?) 2) 180)
				)
				(= cycles (Random 20 40))
			)
			(1 (self changeState: 0))
		)
	)
)

(instance goatLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr 2)
				(if goatCantSeeEgo
					(Print 600 24)
				else
					(Print 600 25)
				)
				(switch curRoomNum
					(25
						(theGoat setMotion: MoveTo (theGoat x?) 220 self)
					)
					(41
						(theGoat setMotion: MoveTo 340 101 self)
					)
					(39
						(theGoat setMotion: MoveTo 340 (theGoat y?) self)
					)
				)
			)
			(1
				(theGoat dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goatKill of Sound
	(properties
		number 104
		priority 10
	)
)
