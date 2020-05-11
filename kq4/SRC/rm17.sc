;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room17 0
)
(synonyms
	(kiss kiss embrace)
	(ghoul ghoul ghoul man ghoul ghoul)
)

(local
	z6
)
(instance zTheme of Sound
	(properties
		number 21
	)
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
			(curRoom newRoom: 68)
		)
	)
)

(instance Room17 of Room
	(properties
		picture 17
	)
	
	(method (init)
		(= north 11)
		(= south 23)
		(= east 18)
		(= west 16)
		(= horizon 100)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(self setRegions: FOREST)
		(super init:)
		(if isNightTime (curRoom overlay: 117))
		(if (and isNightTime (not (ego has: iObsidianScarab)))
			(Load VIEW 270)
			(Load VIEW 36)
			(Load VIEW 35)
			(= z6 (Actor new:))
			(z6
				view: 270
				cel: 0
				loop: 0
				posn: 146 145
				setPri: 10
				illegalBits: 0
				ignoreActors:
				setScript: z6Actions
				init:
				hide:
			)
		)
		(door
			view: 610
			loop: 0
			cel: 0
			posn: 147 144
			stopUpd:
			init:
		)
		(switch prevRoomNum
			(16
				(if (<= (ego y?) 120)
					(ego x: 1 y: 120)
				else
					(ego x: 1)
				)
			)
			(18
				(if (<= (ego y?) 120)
					(ego x: 318 y: 120)
				else
					(ego x: 318)
				)
			)
			(23 (ego posn: (ego x?) 188))
			(11
				(if (< (ego x?) 160)
					(ego x: 42 y: (+ horizon (ego yStep?) 1))
				else
					(ego x: 267 y: (+ horizon (ego yStep?) 1))
				)
			)
			(68 (ego loop: 2 x: 161 y: 151))
			(0 (ego x: 290 y: 160))
		)
		(ego setPri: -1 view: 2 xStep: 3 yStep: 2 init:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					(
						(or
							(Said 'look/around')
							(Said 'look/room')
							(Said 'look[<around][/!*]')
						)
						(Print 17 0)
					)
					((Said 'look>')
						(cond 
							((Said '/grave,gravestone,gravestone') (Print 17 1))
							((Said '/fence,wall') (Print 17 2))
							((Said '/cottage') (Print 17 3))
							((Said '/cemetery') (Print 17 4))
							((Said '/door') (Print 17 5))
							((Said '/window')
								(cond 
									(
										(or
											(ego inRect: 96 142 128 148)
											(ego inRect: 194 142 229 148)
										)
										(Print 17 6)
									)
									(
										(or
											(ego inRect: 70 151 124 157)
											(ego inRect: 193 154 260 159)
										)
										(Print 17 7)
									)
									(else (Print 800 1))
								)
							)
							((Said '/stair') (Print 17 8))
							((or (Said 'look/bush') (Said 'look/bush')) (Print 17 9))
						)
					)
					((Said 'break/window') (Print 17 10))
					((Said 'break/door') (Print 17 11))
					((Said 'open/door')
						(if (ego inRect: 139 140 173 153)
							(HandsOff)
							(ego loop: 3 setPri: 11)
							(doorSound number: 300 loop: 1 play: door)
							(door setCycle: EndLoop)
						else
							(Print 800 1)
						)
					)
					((Said 'close/door') (Print 17 12))
					((Said 'open/window') (Print 17 13))
					((Said 'unlatch/door') (Print 17 14))
					((Said 'bang/door')
						(if (ego inRect: 139 140 173 153)
							(Print 17 15)
						else
							(Print 800 1)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== (ego view?) 2)
			(HandsOn)
			(ego setPri: -1)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance z6Actions of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (ego inRect: 141 146 175 149) (== state 0))
			(HandsOff)
			(z6Actions changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(z6 show: setLoop: 0 cel: 255 setCycle: EndLoop self)
			)
			(2
				(z6 hide:)
				(zTheme play: self)
				(ego view: 35 cel: 255 setCycle: EndLoop self)
			)
			(3
				(z6 view: 270 show: cycleSpeed: 1 setCycle: BegLoop)
				(ego view: 36 cel: 255 setMotion: 0 setCycle: EndLoop self)
			)
			(4
				(ego view: 46 cel: 255 setCycle: EndLoop self)
			)
			(5
				(z6 hide:)
				(= timedMessage (Print 17 16 #at -1 20 #dispose))
				(ego
					view: 45
					x: 160
					setCycle: Walk
					setMotion: MoveTo 160 230
				)
			)
			(6 (= dead TRUE))
		)
	)
)
