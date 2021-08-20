;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Menu)
(use System)

(public
	rm062 0
)

(instance rm062 of Room
	(properties
		picture 62
		south 67
		west 61
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(Load VIEW 63)
		(super init:)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(switch prevRoomNum
			(61
				(ego setStep: 4 3 view: 63 posn: 2 (ego y?) init:)
			)
			(67
				(ego setStep: 4 3 view: 63 posn: (ego x?) 187 init:)
			)
			(else 
				(Load SOUND 71)
				(self setScript: landScript)
			)
		)
	)
	
	(method (doit &tmp egoOnControl)
		(super doit:)
		(if (> ortegaPostBeamRooms 15)
			(self setScript: CrackUp)
		)
		(if (== (curRoom script?) 0)
			(if (== (= egoOnControl (ego onControl:)) 16385)
				(curRoom newRoom: 14)
			)
			(cond 
				((== egoOnControl 3)
					(ego setPri: 7 posn: (+ (ego x?) 6) (ego y?))
					(= fallingIntoLava TRUE)
				)
				(
					(or
						(== egoOnControl 5)
						(== egoOnControl 7)
						(== egoOnControl 13)
					)
					(ego
						setPri: 5
						posn:
							(if (< (ego x?) 100)
								(- (ego x?) 6)
							else
								(+ (ego x?) 6)
							)
							(ego y?)
					)
					(= fallingIntoLava TRUE)
				)
				((== egoOnControl 9)
					(ego setPri: 3 posn: (ego x?) (- (ego y?) 6))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 17) (== egoOnControl 19))
					(ego setPri: 9 posn: (+ (ego x?) 12) (ego y?))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 33) (== egoOnControl 37))
					(ego
						setPri: 5
						posn:
							(if (< (ego x?) 100)
								(- (ego x?) 6)
							else
								(+ (ego x?) 18)
							)
							(ego y?)
					)
					(= fallingIntoLava TRUE)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(switch (event type?)
			(saidEvent
				(if
					(or
						(Said 'enter,go,get[<in]/craft,ramp,door')
						(Said 'climb/ramp')
					)
					(Print 62 0)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== script 0)
			(ego setStep: 3 2)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(if (> ortegaPostBeamRooms 15)
				(self setScript: CrackUp)
			else
				(super newRoom: newRoomNumber)
			)
		)
	)
)

(instance landScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setStep: 4 3
					view: 63
					setLoop: 2
					posn: 164 142
					setPri: -1
					illegalBits: 0
					edgeHit: 0
					init:
				)
				(= seconds 2)
			)
			(1
				(ego setMotion: MoveTo 151 162 self)
			)
			(2
				(ego setLoop: -1 setPri: -1 illegalBits: -32768)
				(HandsOn)
				(if wearingUnderwear
					(Print 62 1)
					(= fryToDeathTimer 0)
				else
					(Print 62 2)
					(= fryToDeathTimer 10)
				)
				(theMusic number: 71 loop: -1 priority: 0 play:)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance CrackUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ortegaPostBeamRooms 0)
				(HandsOff)
				(ShakeScreen 30 3)
				(ego dispose:)
				(RedrawCast)
				(Print 62 3)
				(EgoDead 0 0 1 2)
			)
		)
	)
)
