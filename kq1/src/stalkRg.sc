;;; Sierra Script 1.0 - (do not remove this comment)
(script# 607)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use DPath)
(use Motion)
(use Game)
(use System)

(public
	stalkRg 0
)

(instance stalkRg of Region
	(properties)
	
	(method (init)
		(Load VIEW 19)
		(Load PICTURE 270)
		(self keep: FALSE)
		(super init: &rest)
		(if (ego has: iBeans)
			(ego put: iBeans)
		else
			(curRoom overlay: 270 PLAIN)
		)
		(if (== prevRoomNum 70)
			(curRoom setScript: climbDownStalk)
		)
		(bigStalk init:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'climb,scale')
				(cond 
					((not (& (ego onControl: origin) cLGREEN))
						(Print 607 0)
					)
					((Btst fInvisible)
						(Print 607 1)
					)
					((and (curRoom script?) (== (curRoom script?) climbUpStalk))
						(Print 607 2)
					)
					((curRoom script?)
						(Print 607 3)
					)
					(else
						(curRoom setScript: climbUpStalk)
					)
				)
			)
		)
	)
)

(instance climbUpStalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst fGoatFollows)
					(theGoat setMotion: 0)
					(DisposeScript FOLLOW)
					(theGoat setMotion: MoveTo 165 180 self)
				else
					(self cue:)
				)
			)
			(1
				(HandsOff)
				(ego ignoreActors: ignoreHorizon: illegalBits: 0)
				(cond 
					((> (ego y?) 141) (self changeState: 4))
					((< (ego x?) 126) (self changeState: 3))
					((> (ego x?) 220) (self changeState: 3))
					(else (self cue:))
				)
			)
			(2
				(ego setMotion: MoveTo 122 (ego y?) self)
			)
			(3
				(ego setMotion: MoveTo (ego x?) 148 self)
			)
			(4
				(ego setMotion: MoveTo 162 147 self)
			)
			(5
				(Bset fClimbing)
				(ego
					posn: 162 145
					view: 19
					setLoop: 0
					cel: 0
					setPri: 14
					setStep: 4 3
					moveSpeed: 1
					cycleSpeed: 1
					setCycle: SyncWalk
					setMotion: DPath 174 118 175 100 134 53 136 31 145 1 self
				)
			)
			(6 (ego cel: 0) (= cycles 6))
			(7
				(HandsOn)
				(curRoom newRoom: 70)
			)
		)
	)
)

(instance climbDownStalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr fGoatFollows)
				(Bclr fLittleEgo)
				(ego
					illegalBits: 0
					ignoreActors:
					posn: 144 2
					view: 19
					setLoop: 0
					cel: 0
					setPri: 14
					setStep: 4 3
					moveSpeed: 1
					cycleSpeed: 1
					setCycle: SyncWalk
					setMotion: DPath 136 31 134 53 175 100 174 118 162 145 self
				)
			)
			(1
				(Bclr fClimbing)
				(NormalEgo)
				(ego posn: 162 147 loop: 3)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bigStalk of NewFeature
	(properties
		x 159
		y 19
		noun '/beanstalk'
		nsLeft 120
		nsBottom 143
		nsRight 196
		description {stalk}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This mighty beanstalk stretches up so high, it vanishes into the clouds above.}
	)
	
	(method (init)
		(super init: &rest)
		(features delete: self)
		(features addToFront: self)
	)
)
