;;; Sierra Script 1.0 - (do not remove this comment)
(script# RIVER)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use System)

(public
	riverReg 0
)

(define riverPri 60)	;(| cGREEN cCYAN cRED cMAGENTA)

(local
	oldEgoLoop
)
(instance riverReg of Region
	(properties)
	
	(method (init)
		(LoadMany VIEW 54 14)
		(Load SOUND 14)
		(self keep: FALSE)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl: origin) riverPri)
				(not (curRoom script?))
				(== newRoomNum curRoomNum)
			)
			(curRoom setScript: intoRiver)
		)
	)
	
	(method (handleEvent event)
		(if (or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((Said 'dive/')
				(cond 
					((and (== (curRoom script?) intoRiver) (>= (intoRiver state?) 5))
						(Print 608 0)
					)
					((!= (curRoom script?) intoRiver)
						(Print 608 1)
					)
					(else
						(Print 608 2)
					)
				)
			)
			((and (or (Said 'swim/') (Said 'go,enter/water,brook,brook,brook')) (!= curRoomNum 42))
				(if (!= (curRoom script?) intoRiver)
					(Print 608 3)
				else
					(Print 608 4)
				)
			)
			((or (Said 'drink/water') (Said 'get,take/drink'))
				(Print 608 5)
			)
			((or (Said 'get,take/water') (Said 'fill/bucket'))
				(cond 
					((== (curRoom script?) intoRiver)
						(Print 608 6)
					)
					((ego has: iWaterBucket)
						(Print 608 7)
					)
					(else
						(Print 608 8)
					)
				)
			)
			(else
				(event claimed: FALSE)
			)
		)
	)
)

(instance intoRiver of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (== state 0)
			(cond 
				(
					(or
						(& (ego onControl: origin) cLBLUE)
						(> (ego x?) 326)
						(< (ego x?) -6)
						(> (ego y?) 196)
					)
					((ScriptID 0 21) number: 14 loop: 1 play:)
					(self cue:)
				)
				((< (ego yStep?) 9) (ego setStep: -1 (+ (ego yStep?) 2)))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 17 init: loop: 1 play:)
				(= egoInWater 6)
				(ego
					view: 54
					illegalBits: 0
					setCycle: 0
					cel: 0
					ignoreActors:
					ignoreHorizon:
					setStep: 6 3
				)
				(switch (ego onControl: origin)
					(cGREEN
						(ego
							setLoop: 1
							setMotion: MoveTo (- (ego x?) 200) (+ (ego y?) 140)
						)
					)
					(cMAGENTA
						(ego yStep: 1 setLoop: 2 setMotion: MoveTo (ego x?) 190)
					)
					(cRED
						(ego setLoop: 3 setMotion: MoveTo (ego x?) 0)
					)
					(cCYAN
						(ego
							setLoop: 0
							setMotion: MoveTo (+ (ego x?) 200) (+ (ego y?) 140)
						)
					)
				)
				(if (cast contains: theGoat)
					(Face theGoat ego)
					(theGoat setMotion: 0)
				)
				(ego
					setPri:
						(switch curRoomNum
							(25 3)
							(39
								(if (< (ego y?) 107) 2 else -1)
							)
							(41 7)
							(26
								(if (& (ego onControl: origin) cGREEN) 1 else 8)
							)
							(42
								(cond 
									((and (> (ego y?) 150) (< (ego x?) 50)) 13)
									((> (ego y?) 83) 4)
									(else 1)
								)
							)
							(32
								(if (== (ego loop?) 3) 8 else 5)
							)
							(34 3)
							(47
								(if (< (ego x?) 159)
									(if (< (ego y?) 170) 7 else 9)
								else
									-1
								)
							)
							(else  -1)
						)
				)
			)
			(1
				(User canInput: TRUE)
				(if (Btst fInvisible)
					(Print 608 9)
					(theGame changeScore: -3)
				)
				(ego setLoop: 4 setMotion: 0 cycleSpeed: 1 setCycle: EndLoop)
				(if (== curRoomNum 17)
					(self changeState: 6)
				else
					(= seconds 2)
				)
			)
			(2
				(User canControl: FALSE)
				((ScriptID 0 21) number: 96 play:)
				(= oldEgoLoop (ego loop?))
				(ego
					view: 14
					cycleSpeed: 2
					setMotion: 0
					setLoop: 0
					cel: 5
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3
				(ego cel: 5 setCycle: EndLoop self)
			)
			(4
				(ego cel: 5 setCycle: EndLoop self)
			)
			(5
				(ego hide:)
				(= seconds 4)
			)
			(6
				(EgoDead
					{The raging current pulls you under, never to be found.}
				)
			)
		)
	)
)
