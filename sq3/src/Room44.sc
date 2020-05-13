;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room44 0
)

(instance lightning of Prop
	(properties)
)

(instance thunder of Sound
	(properties)
)

(instance Room44 of Room
	(properties
		picture 44
	)
	
	(method (init)
		(= east 41)
		(= south 48)
		(= west 43)
		(= north 54)
		(= horizon 85)
		(= global591 0)
		(= global109 0)
		(Load SOUND 31)
		(Load SOUND 32)
		(super init:)
		(lightning
			view: 69
			loop: 6
			cel: 0
			posn: 291 59
			setScript: flash
			init:
		)
		(NormalEgo)
		(ego looper: 0)
		(switch prevRoomNum
			(48
				(NormalEgo)
				(= global104 0)
				(ego view: 0 posn: (ego x?) 188 setStep: 3 2)
			)
			(43 (ego posn: 1 (ego y?)))
			(41
				(if (< (ego y?) (+ horizon 2))
					(ego posn: 318 (+ horizon 2))
				else
					(ego posn: 318 (ego y?))
				)
			)
			(54
				(ego view: 0 posn: (ego x?) (+ horizon 2) setStep: 3 2)
			)
			(else  (ego view: 0))
		)
		(ego init:)
		(self setRegions: DUNE)
		(switch terminatorState
			(terminatorMEET
				(if (== (Random 1 4) 3)
					((= terminator (Actor new:)) posn: 1000 1000 init:)
					(= notifyCountdown (Random 2 12))
				)
			)
			(terminatorSEARCHING
				((= terminator (Actor new:)) posn: 1000 1000 init:)
				(= notifyCountdown 3)
			)
		)
		(if (cast contains: terminator)
			(terminator
				view: 106
				setCycle: Walk
				observeControl: 2
				posn: 1000 1000
				init:
			)
			(switch prevRoomNum
				(east
					(terminator posn: 359 130)
				)
				(west
					(terminator posn: -40 130)
				)
				(south
					(terminator posn: 160 249)
				)
				(north
					(terminator posn: 120 (+ horizon 2))
				)
			)
			(= terminatorState terminatorMEET)
			((ScriptID 501 0) notify: 1)
		else
			((ScriptID DUNE 0) notify: 4)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'read/sign')
						(if (== (ego onControl: origin) 8192)
							(if (or (== (ego loop?) 1) (== (ego loop?) 3))
								(Print 44 0)
							else
								(Print 44 1)
							)
						else
							(Print 44 2)
						)
					)
					((Said 'look/sign') (Print 44 3))
					(
						(and
							(Said 'look>')
							(or
								(Said '/area')
								(Said '/around')
								(Said '[<around][/!*]')
							)
						)
						(Print 44 4)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				(cast contains: terminator)
				(< (ego distanceTo: terminator) 120)
			)
			(= terminatorState terminatorSEARCHING)
		)
		(if (not isHandsOff) (super newRoom: newRoomNumber))
	)
)

(instance flash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 8 30)))
			(1
				(lightning cel: 255 setCycle: EndLoop self)
			)
			(2
				(lightning cel: 0)
				(= seconds (Random 2 5))
			)
			(3
				(thunder priority: 2 number: (Random 31 32) play: self)
			)
			(4 (= state -1))
		)
	)
)
