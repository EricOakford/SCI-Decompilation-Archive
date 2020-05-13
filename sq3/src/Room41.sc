;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	Room41 0
)

(instance Room41 of Room
	(properties
		picture 41
	)
	
	(method (init)
		(= east 42)
		(= south 45)
		(= west 44)
		(= north 54)
		(= horizon 85)
		(super init:)
		(= global109 0)
		(= global591 0)
		(ego init:)
		(switch prevRoomNum
			(54
				(NormalEgo)
				(ego view: 0 posn: (ego x?) (+ horizon 2))
			)
			(45
				(NormalEgo)
				(= global104 0)
				(ego view: 0 posn: (ego x?) 188)
			)
			(42
				(NormalEgo)
				(ego view: 0 posn: 318 (ego y?))
			)
			(44
				(NormalEgo)
				(ego view: 0 posn: 1 (ego y?))
			)
			(else 
				(ego view: 0 posn: 160 188)
			)
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
					(terminator posn: 160 (+ horizon 2))
				)
			)
			(= terminatorState 1)
			((ScriptID DUNE 0) notify: 1)
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
			(if
			(and (== (event type?) saidEvent) (Said 'look>'))
				(cond 
					(
						(or
							(Said '/area')
							(Said '/around')
							(Said '[<around][/!*]')
						)
						(Print 41 0)
					)
					((Said '/dune') (Print 41 1))
				)
			else
				FALSE
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
