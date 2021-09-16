;;; Sierra Script 1.0 - (do not remove this comment)
(script# TROLL_CAVE)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	regTroll 0
)
(synonyms
	(dirt dirt dirt dirt dirt)
	(kiss kiss embrace)
	(troll troll troll troll troll man)
)

(local
	troll
	local1
	local2
	trollGotHer
)
(instance trollCaveMusic of Sound
	(properties
		number 66
		priority -1
	)
)

(instance caughtMusic of Sound
	(properties
		number 38
		priority 3
	)
)

(instance trollMusic of Sound
	(properties
		number 37
		priority 2
		loop -1
	)
	
	(method (play)
		(trollCaveMusic client: 0 stop:)
		(super play: &rest)
	)
)

(instance regTroll of Region
	(method (init)
		(if initialized (return))
		(= keep TRUE)
		(if (ego has: iLantern)
			(Load VIEW 967)
		)
		(Load VIEW 904)
		(Load VIEW 190)
		(super init:)
		(= noWearCrown TRUE)
		(= local2 0)
		(= trollAttacks FALSE)
		(= local1 0)
		(= trollGotHer 0)
		(trollCaveMusic owner: self init:)
		(trollMusic owner: self init:)
		(caughtMusic owner: self init:)
		(doMusic cue:)
	)
	
	(method (doit)
		(super doit:)
		(if (== script gotchaScript) (return))
		(if
			(and
				(not (LanternIsOn))
				(not (& (ego onControl: 0) cBLUE))
			)
			(ego dispose:)
			(RedrawCast)
			(Print 605 1)
			(self setScript: gotchaScript)
			(return)
		)
		(if (and (not (cast contains: troll)) (== trollAttacks TRUE))
			(trollScript changeState: 1)
		)
		(if (not local1)
			(= local1 1)
			(if
				(and
					(not trollAttacks)
					(< (Random 0 100) 50)
					(!= curRoomNum 76)
					(!= curRoomNum 73)
				)
				(trollScript start: 0)
				(self setScript: trollScript)
			)
		)
	)
	
	(method (dispose)
		(if (== keep FALSE)
			(= noWearCrown FALSE)
			(super dispose:)
		)
	)
	
	(method (handleEvent event &tmp i temp1 lampLit)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(= lampLit
					(if (LanternIsOn) (ego has: iLantern)
					else
						FALSE
					)
				)
				(cond 
					((and (ego has: iLantern) (Said '/chandelier,lantern[<oil]>'))
						(cond 
							((Said 'extinguish,(turn<off)')
								(self notify: FALSE)
							)
							((Said 'light,ignite,(turn<on)')	;EO: Fixed said spec
								(self notify: TRUE)
							)
						)
					)
					((Said 'look>')
						(cond 
							((Said '<out[/cave]')
								(Print 605 2)
							)
							((and lampLit (Said '[<in,at]/cave[<troll,dark]'))
								(Print 605 3)
							)
							((and lampLit (or (Said '/dirt') (Said '<down')))
								(Print 605 4)
							)
							((and lampLit (Said '/passageway'))
								(Print 605 5)
							)
							((Said '/troll')
								(Print 605 6)
							)
							((inventory saidMe:)
								(event claimed: FALSE)
							)
							(else
								(Print 605 7)
								(event claimed: TRUE)
							)
						)
					)
					((or (Said 'climb/boulder') (Said 'get/boulder'))
						(Print 605 8)
					)
					((Said 'find/troll')
						(Print 605 6)
					)
					((cast contains: troll)
						(cond 
							((Said 'converse/troll')
								(Print 605 9)
							)
							((Said 'kill/troll')
								(Print 605 10)
							)
							((Said 'get,capture/troll')
								(Print 605 11)
							)
							((or (Said 'kiss/troll') (Said 'kiss[/noword]'))
								(Print 605 12)
							)
							(
								(and
									(Said 'deliver>')
									(= i (inventory saidMe:))
									(ego has: (inventory indexOf: i))
								)
								(Print 605 13)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(= local1 0)
		(= trollGotHer 0)
		(cls)
		(if
			(and
				(!= n 77)
				(!= n 70)
				(not (cast contains: troll))
			)
			(doMusic cue:)
		)
		(super newRoom: n)
	)
	
	(method (notify param1)
		(switch param1
			(2
				(ego view: 904)
				(theLight dispose:)
			)
			(3
				(ego view: 967)
				(theLight setLoop: 4 init:)
			)
			(1
				(ego view: 967)
				(theLight setLoop: 4 init:)
				(RedrawCast)
				(LanternIsOn TRUE)
			)
			(0
				(ego view: 904)
				(theLight dispose:)
				(cond 
					(trollGotHer (ego dispose:)
						(RedrawCast)
					)
					((& (ego onControl: 0) cBLUE)
						(LanternIsOn FALSE)
					)
					(else
						(ego dispose:)
						(RedrawCast)
						(Print 605 0)
						(self setScript: gotchaScript)
					)
				)
			)
			(4
				(trollMusic client: 0 stop:)
				(if (IsObject troll)
					(troll setMotion: 0 setScript: 0)
				)
			)
		)
	)
)

(instance trollScript of Script
	(method (doit)
		(if
			(and
				(cast contains: troll)
				(< (self state?) 5)
				(< (ego distanceTo: troll) 5)
			)
			(self changeState: 5)
			(return)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not local2)
					(= seconds 2)
				)
			)
			(1
				(if
					(and
						(not trollAttacks)
						(or
							(== curRoomNum 71)
							(== curRoomNum 72)
							(== curRoomNum 74)
							(== curRoomNum 75)
						)
					)
					(Print 605 14 #at 200 40 #dispose)
					(= trollAttacks TRUE)
					((= troll (Actor new:))
						view: 190
						setAvoider: Avoider 1
						illegalBits: 0
						xStep: 5
						yStep: 2
						init:
						setCycle: Walk
					)
					(switch curRoomNum
						(71 (troll posn: 230 96))
						(72 (troll posn: 190 180))
						(74 (troll posn: 73 166))
						(75 (troll posn: 107 89))
					)
					(trollCaveMusic client: 0 stop:)
					(trollMusic stop: loop: 1 play:)
				else
					(if (or (!= trollAttacks 1) (== curRoomNum 73)) (return))
					((= troll (Actor new:))
						view: 190
						setAvoider: Avoider 1
						setCycle: Walk
						illegalBits: 0
						ignoreActors: 1
						xStep: 6
						yStep: 2
						init:
					)
					(switch curRoomNum
						(71
							(switch prevRoomNum
								(74
									(troll posn: 213 210)
								)
								(72
									(troll posn: 250 101)
								)
								(else
									(troll posn: 179 196)
								)
							)
						)
						(72
							(switch prevRoomNum
								(71
									(troll posn: 20 92)
								)
								(75
									(troll posn: 180 220)
								)
								(else
									(troll posn: 178 154)
								)
							)
						)
						(74
							(switch prevRoomNum
								(71
									(troll posn: 114 -20)
								)
								(75
									(troll posn: 340 170)
								)
								(else
									(troll posn: 158 132)
								)
							)
						)
						(75
							(switch prevRoomNum
								(74
									(troll posn: -20 170)
								)
								(76
									(troll posn: 340 170)
								)
								(72
									(troll posn: 140 -20)
								)
								(else
									(troll posn: 148 118)
								)
							)
						)
						(76
							(troll posn: -40 170)
						)
					)
					(if (== curRoomNum 76)
						(switch (Random 0 1)
							(0
								(troll xStep: 6 yStep: 3)
								(sounds eachElementDo: #stop 0)
								(trollMusic loop: 1 play:)
							)
							(1
								(troll hide:)
								(= local2 1)
								(self changeState: 7)
								(return)
							)
						)
					else
						(trollCaveMusic client: 0 stop:)
						(trollMusic client: 0 stop: loop: 1 play:)
					)
					(RedrawCast)
				)
				(if (not local2)
					(self cue:)
					(return)
				)
			)
			(2
				(if (cast contains: troll)
					(troll
						moveSpeed: 1
						cycleSpeed: 1
						setMotion: Chase ego 55 self
					)
				else
					(self cue:)
				)
			)
			(3
				(Print 605 15 #time 4 #at 200 10 #dispose)
				(self cue:)
			)
			(4
				(if (cast contains: troll)
					(troll setMotion: Chase ego 4 self)
				else
					(self cue:)
				)
			)
			(5
				(gotchaScript start: 1)
				(curRoom setScript: gotchaScript)
			)
		)
	)
)

(instance gotchaScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cls)
				(trollCaveMusic client: 0 stop:)
				(trollMusic play:)
				(= seconds 5)
			)
			(1
				(HandsOff)
				(trollCaveMusic client: 0 stop:)
				(trollMusic client: 0 stop:)
				(caughtMusic play:)
				(cls)
				(Print 605 16 #at -1 10)
				(= trollGotHer TRUE)
				(regTroll notify: 0)
				(Print 605 17 #at -1 10)
				(= seconds 4)
			)
			(2
				(= dead TRUE)
			)
		)
	)
)

(instance theLight of Prop
	(properties
		view 967
		loop 4
	)
	
	(method (init)
		(self
			posn: (ego x?) (- (ego y?) 3)
			ignoreActors: 1
			setCycle: Forward
			priority: (- (ego priority?) 1)
		)
		(super init:)
	)
	
	(method (doit)
		(self
			posn: (ego x?) (- (ego y?) 3)
			priority: (- (ego priority?) 1)
		)
		(super doit:)
	)
)

(instance doMusic of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Timer setReal: self 2)
			)
			(1
				(trollCaveMusic loop: 1 playMaybe:)
			)
		)
	)
)
