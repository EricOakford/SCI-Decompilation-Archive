;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room22 0
)

(local
	kickedOut
	ripple2
	ripple3
	ripple4
	ripple5
	ripple1
)
(instance doorSound of Sound
	(properties)
)

(instance door of Prop
	(properties)
	
	(method (cue)
		(if (!= (door cel?) (door lastCel:))
			(self setCycle: EndLoop self)
		else
			(HandsOn)
			(curRoom newRoom: 54)
		)
	)
)

(instance Room22 of Room
	(properties
		picture 22
	)
	
	(method (init)
		(= north 16)
		(= south 28)
		(= east 23)
		(= west 21)
		(= horizon 75)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime (curRoom overlay: 122))
		(self setRegions: WATER RIVER FOREST)
		(Load VIEW 612)
		(= ripple1 (Prop new:))
		(ripple1
			isExtra: TRUE
			view: 627
			loop: 0
			cel: 3
			posn: 158 25
			setPri: 0
			cycleSpeed: 2
			setCycle: Forward
			ignoreActors:
			init:
		)
		(= ripple2 (Prop new:))
		(= ripple3 (Prop new:))
		(= ripple4 (Prop new:))
		(= ripple5 (Prop new:))
		(ripple2
			isExtra: 1
			view: 656
			loop: 0
			cel: 2
			posn: 23 110
			setPri: 0
			setCycle: Forward
			ignoreActors:
			init:
		)
		(ripple3
			isExtra: 1
			view: 656
			loop: 1
			cel: 1
			posn: 309 156
			setPri: 0
			setCycle: Forward
			ignoreActors:
			init:
		)
		(ripple4
			isExtra: 1
			view: 656
			loop: 2
			cel: 2
			posn: 226 168
			setPri: 0
			setCycle: Forward
			ignoreActors:
			init:
		)
		(ripple5
			isExtra: 1
			view: 656
			loop: 3
			cel: 1
			posn: 41 125
			setPri: 0
			setCycle: Forward
			ignoreActors:
			init:
		)
		(door
			view: 612
			loop: 0
			cel: 0
			posn: 179 111
			setPri: 6
			stopUpd:
			init:
		)
		(if isNightTime (door loop: 1))
		(switch prevRoomNum
			(21
				(if (<= (ego y?) horizon) (ego posn: 1 (+ horizon 2)))
				(if (and (>= (ego y?) 126) (< (ego y?) 130))
					(ego posn: 1 127)
				else
					(ego posn: 1 (ego y?))
				)
			)
			(16
				(ego x: 27 y: (+ horizon (ego yStep?) 1))
			)
			(28 (ego x: 171 y: 188))
			(23 (ego posn: 317 (ego y?)))
			(53
				(ego view: 2 loop: 2 x: 188 y: 114)
				(if (== dwarfHouseState houseKICKEDOUT) (curRoom setScript: doorActions))
			)
			(54
				(if (== dwarfHouseState houseKICKEDOUT) (curRoom setScript: doorActions))
				(ego view: 2 x: 188 y: 114)
			)
			(0 (ego x: 318 y: 123))
		)
		(ego xStep: 3 yStep: 2 init:)
		(HandsOn)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (ego inRect: 167 140 201 149) (!= (ego view?) 2))
			(ego setPri: 11)
		else
			(ego setPri: -1)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look/cottage') (Print 22 0))
					((Said 'look/window')
						(if
							(or
								(ego inRect: 140 112 170 124)
								(ego inRect: 170 112 202 120)
							)
							(Print 22 1)
						else
							(Print 800 1)
						)
					)
					((Said 'look/blossom') (Print 22 2))
					((Said 'get/blossom') (Print 22 3))
					((Said 'look/door') (Print 22 4))
					((Said 'break/window') (Print 22 5))
					((Said 'break/door') (Print 22 6))
					((Said 'open/window') (Print 22 7))
					((Said 'open/door')
						(if (ego inRect: 170 112 202 120)
							(cond 
								((== kickedOut 1) (Print 22 8))
								((not isNightTime)
									(HandsOff)
									(ego loop: 3)
									(doorSound number: 300 play: door)
									(door setCycle: EndLoop)
								)
								(else (Print 22 9))
							)
						else
							(Print 800 1)
						)
					)
					((Said 'unlatch/door')
						(if (ego inRect: 170 112 202 120)
							(if (not isNightTime) (Print 22 10) else (Print 22 11))
						else
							(Print 800 1)
						)
					)
					((Said 'bang/door')
						(if (ego inRect: 170 112 202 120)
							(cond 
								((== kickedOut TRUE) (Print 22 12))
								(isNightTime (Print 22 13))
								(else (Print 22 14))
							)
						else
							(Print 800 1)
						)
					)
					((Said 'look/wheel') (Print 22 15))
					((Said 'look[<around][/room]') (Print 22 16))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(User canControl: TRUE canInput: TRUE)
		(super newRoom: newRoomNumber)
	)
)

(instance doorActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= dwarfHouseState houseDIRTY)
				(= kickedOut TRUE)
				(door ignoreActors: 0 setCycle: BegLoop self)
			)
			(1 (Print 22 17))
		)
	)
)
