;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room18 0
)
(synonyms
	(kiss kiss embrace)
	(ghoul ghoul ghoul man ghoul ghoul)
)

(local
	z4
	z5
	z1
	hole
	local4
	local5
	local6
	local7
	local8
)

(enum ;crypt door states
	doorLocked
	doorClosed
	doorOpen
)

(instance zTheme of Sound
	(properties
		number 20
		priority 1
		loop -1
	)
)

(instance doorSound of Sound
	(properties
		number 300
		priority 3
	)
)

(instance door of Prop
	(properties)
	
	(method (cue)
		(self ignoreActors:)
		(curRoom newRoom: 69)
	)
)

(instance Room18 of Room
	(properties
		picture 18
		horizon 85
		north 12
		south 24
		west 17
	)
	
	(method (init)
		(= isIndoors FALSE)
		(Load VIEW 47)
		(ego edgeHit: 0 observeControl: 16384)
		(= numZombies 0)
		(super init:)
		(if isNightTime (curRoom overlay: 118))
		(self setRegions: CEMETERY MOUNTAIN)
		(if isNightTime
			(Load VIEW 261)
			(Load VIEW 267)
			(Load VIEW 269)
			(Load VIEW 36)
			(Load VIEW 46)
			(Load VIEW 45)
			(Load VIEW 21)
			(= z1 (Actor new:))
			(= z4 (Actor new:))
			(= z5 (Actor new:))
			(z1
				view: 260
				loop: 0
				cel: 0
				xStep: 2
				yStep: 1
				posn: 25 156
				setScript: z1Actions
				init:
				hide:
			)
			(z4
				view: 266
				loop: 0
				cel: 0
				xStep: 2
				yStep: 1
				posn: 109 131
				setScript: z4Actions
				init:
				hide:
			)
			(z5
				view: 268
				loop: 0
				cel: 0
				xStep: 2
				yStep: 1
				posn: 220 165
				setScript: z5Actions
				init:
				hide:
			)
		)
		(if (== cryptDoorState doorOpen)
			(ego ignoreControl: 16384)
			(door
				posn: 273 148
				view: 611
				loop: 0
				cel: 4
				setPri: 0
				ignoreActors:
				init:
				stopUpd:
			)
		else
			(door
				posn: 273 148
				view: 611
				loop: 0
				cel: 0
				setPri: 0
				init:
				stopUpd:
			)
		)
		(switch prevRoomNum
			(west (ego posn: 2 (ego y?)))
			(north
				(ego x: 45 y: (+ horizon (ego yStep?) 1))
			)
			(south (ego posn: 172 188))
			(69
				(ego x: 266 y: 153 xStep: 3 yStep: 2 ignoreControl: 16384)
				(door
					posn: 273 148
					view: 611
					loop: 0
					cel: 4
					setPri: 0
					ignoreActors:
					init:
					stopUpd:
				)
			)
			(0 (ego x: 180 y: 188))
		)
		(= currentStatus egoNormal)
		(ego view: 2 init:)
		(= local6 1)
		(while (<= local6 timesUsedShovel)
			(if
				(==
					[gNewPropX (= local5 (* (- local6 1) 3))]
					curRoomNum
				)
				((View new:)
					view: 528
					loop: 0
					cel: 6
					posn: [gNewPropX (+ local5 1)] [gNewPropX (+ local5 2)]
					ignoreActors:
					setPri: 0
					addToPic:
					ignoreActors:
				)
			)
			(++ local6)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $4000) (curRoom newRoom: 69))
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'climb/boulder') (Print 18 0))
					((Said 'open,enter/crypt') (if (== cryptDoorState doorOpen) (Print 18 1) else (Print 18 2)))
					((Said 'look>')
						(cond 
							((Said '/boulder') (Print 18 3))
							((Said '/grass') (Print 18 4))
							((Said '/crypt') (Print 18 5))
							((Said '/cliff') (Print 18 6))
							((Said '/gravestone') (Print 18 7))
							((Said '/monument,lion') (Print 18 8))
							((Said '/door')
								(if (== (door cel?) 0)
									(Print 18 9)
								else
									(Print 18 10)
								)
							)
							((Said '/hole')
								(= local7 1)
								(while (<= local7 timesUsedShovel)
									(if
										(and
											(== [gNewPropX (= local5 (* (- local7 1) 3))] 18)
											(<
												(egoDist
													doit: [gNewPropX (+ local5 1)] [gNewPropX (+ local5 2)]
												)
												20
											)
										)
										(= local8 1)
									)
									(++ local7)
								)
								(if (>= timesUsedShovel 0)
									(if local8 (Print 18 11) else (Print 800 1))
								else
									(Print 18 12)
								)
								(= local8 0)
							)
							((Said '[<around][/room]') (Print 18 13) (Print 18 14))
						)
					)
				)
				(cond 
					((Said '/door>')
						(cond 
							((Said 'break') (Print 18 15))
							((Said 'bang')
								(if (ego inRect: 262 149 292 157)
									(Print 18 16)
								else
									(Print 800 1)
								)
							)
							((Said 'open')
								(if (ego inRect: 262 149 292 157)
									(cond 
										((== cryptDoorState doorClosed)
											(doorSound play: door)
											(ego setMotion: 0 ignoreControl: 16384)
											(HandsOff)
											(= cryptDoorState doorOpen)
											(= inCutscene TRUE)
											(door setCycle: EndLoop door)
										)
										((== cryptDoorState doorLocked) (Print 18 17))
										(else (Print 18 18))
									)
								else
									(Print 800 1)
								)
							)
							((Said 'close')
								(if (== (door cel?) 0)
									(Print 18 19)
								else
									(Print 18 20)
									(door setCycle: BegLoop)
									(ego observeControl: 16384)
									(= cryptDoorState doorClosed)
									(doorSound play:)
								)
							)
							((Said 'latch')
								(if ((Inventory at: iSkeletonKey) ownedBy: ego)
									(if (ego inRect: 262 149 292 157)
										(cond 
											((== cryptDoorState doorClosed)
												(if
												(and ((Inventory at: iPandorasBox) ownedBy: 69) (== gamePhase endGame))
													(Print 18 21 #draw)
													(theGame changeScore: 2)
													(ego put: iSkeletonKey 18)
													(= cryptDoorState doorLocked)
													(ego observeControl: 16384)
												else
													(Print 18 22)
													(= cryptDoorState doorLocked)
												)
											)
											((== cryptDoorState doorOpen) (Print 18 23))
											((== cryptDoorState doorLocked) (Print 18 24))
										)
									else
										(Print 800 1)
									)
								else
									(Print 18 25)
								)
							)
							((Said 'unlatch')
								(if (ego inRect: 262 149 292 157)
									(cond 
										((== cryptDoorState doorLocked)
											(if ((Inventory at: iSkeletonKey) ownedBy: ego)
												(Print 18 26)
												(if (not global187)
													(theGame changeScore: 3)
													(++ global187)
												)
												(= cryptDoorState doorClosed)
											else
												(Print 18 27)
											)
										)
										((== cryptDoorState doorOpen) (Print 18 28))
										((== cryptDoorState doorClosed) (Print 18 29))
									)
								else
									(Print 800 1)
								)
							)
						)
					)
					((Said 'read,look/epitaph,gravestone,boulder')
						(cond 
							((& (ego onControl: 0) $0004) (Print 18 30 #mode teJustCenter #at -1 15 #width 290))
							((& (ego onControl: 0) $0080) (Print 18 31 #mode teJustCenter #at -1 15 #width 290))
							((& (ego onControl: 0) $0008) (Print 18 32 #mode teJustCenter #at -1 15 #width 290))
							((& (ego onControl: 0) $0020) (Print 18 33 #mode teJustCenter #at -1 15 #width 290))
							((& (ego onControl: 0) $1000) (Print 18 34 #mode teJustCenter #at -1 15 #width 290))
							((& (ego onControl: 0) $0002) (Print 18 35 #mode teJustCenter #at -1 15 #width 290))
							((& (ego onControl: 0) $0010) (Print 18 36 #mode teJustCenter #at -1 15 #width 290))
							((& (ego onControl: 0) $0100) (Print 18 37 #mode teJustCenter #at -1 15 #width 290))
							(else (Print 800 1))
						)
					)
					((Said 'dig[/hole,grave]')
						(if
						(and (ego has: iShovel) (== 0 ((Inventory at: iShovel) loop?)))
							(if (> mansionPhase 0)
								(if (& (ego onControl: 0) $7fff)
									(ego setScript: digging)
									(digging changeState: 1)
								else
									(Print 18 38)
								)
							else
								(Print 18 39)
							)
						else
							(Print 18 40)
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(HandsOn)
		(= noWearCrown 0)
		(if (== (ego view?) 2) (super newRoom: newRoomNumber))
	)
)

(instance z4Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(= noWearCrown 1)
				(zTheme loop: -1 play:)
				(++ numZombies)
				(z4 show: setCycle: EndLoop self)
				((View new:)
					view: 266
					posn: (z4 x?) (z4 y?)
					ignoreActors:
					addToPic:
				)
			)
			(2
				(if (== currentStatus egoNormal)
					(z4
						view: 267
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Chase ego 12 self
					)
				else
					(z4
						view: 267
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Wander 50
					)
				)
			)
			(3
				(cond 
					((ego has: iObsidianScarab) (self changeState: 4))
					((== currentStatus egoNormal) (self changeState: 10))
				)
			)
			(4
				(z4
					setMotion: 0
					view: 266
					cel: 0
					setCycle: BegLoop self
				)
				(Print 18 41)
				(if (== (-- numZombies) 0)
					(zTheme loop: 1 changeState:)
				)
			)
			(5
				(z4 ignoreActors: addToPic:)
			)
			(10
				(HandsOff)
				(= currentStatus egoZombie)
				(zTheme dispose: number: 21 play:)
				(ego view: 36 cel: 255 setCycle: EndLoop self)
				(if
					(and
						(cast contains: z1)
						(== (z1 mover?) Chase)
					)
					(z1 setMotion: Wander)
				)
				(if
					(and
						(cast contains: z5)
						(== (z5 mover?) Chase)
					)
					(z5 setMotion: Wander)
				)
				(if (cast contains: z1) (z1 setMotion: Wander))
				(z4 setMotion: Wander)
			)
			(11
				(ego view: 46 cel: 255 setCycle: EndLoop self)
			)
			(12
				(= timedMessage (Print 18 42 #at -1 0 #dispose))
				(ego view: 45 setCycle: Walk setMotion: Wander)
				(= seconds 12)
			)
			(13
				(cls)
				(zTheme stop:)
				(= dead TRUE)
			)
		)
	)
)

(instance z5Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 8))
			(1
				(++ numZombies)
				(if (== (zTheme state?) 0) (zTheme loop: -1 play:))
				(if (== (zTheme loop?) 1)
					(zTheme loop: -1 changeState:)
				)
				(z5 show: setCycle: EndLoop self)
				((View new:)
					view: 268
					cel: 0
					posn: (z5 x?) (z5 y?)
					ignoreActors:
					addToPic:
				)
			)
			(2
				(if (== currentStatus 0)
					(z5
						view: 269
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Chase ego 12 self
					)
				else
					(z5
						view: 269
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Wander 50 self
					)
				)
			)
			(3
				(cond 
					((ego has: 7) (self changeState: 4))
					((== currentStatus 0) (self changeState: 10))
				)
			)
			(4
				(Print 18 41)
				(if (== (-- numZombies) 0)
					(zTheme loop: 1 changeState:)
				)
				(z5
					setMotion: 0
					view: 268
					cel: 0
					setCycle: BegLoop self
				)
			)
			(5
				(z5 ignoreActors: addToPic:)
			)
			(10
				(HandsOff)
				(= currentStatus 17)
				(zTheme dispose: number: 21 play:)
				(ego view: 36 cel: 255 setMotion: 0 setCycle: EndLoop self)
				(z5 setMotion: Wander)
				(if
					(and
						(cast contains: z1)
						(== (z1 mover?) Chase)
					)
					(z1 setMotion: Wander)
				)
				(if
					(and
						(cast contains: z5)
						(== (z4 mover?) Chase)
					)
					(z4 setMotion: Wander)
				)
			)
			(11
				(ego view: 46 cel: 255 setCycle: EndLoop self)
			)
			(12
				(= timedMessage (Print 18 42 #at -1 0 #dispose))
				(ego view: 45 setCycle: Walk setMotion: Wander)
				(= seconds 12)
			)
			(13
				(cls)
				(zTheme stop:)
				(= dead 1)
			)
		)
	)
)

(instance z1Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(if (== (zTheme state?) 0) (zTheme loop: -1 play:))
				(if (== (zTheme loop?) 1)
					(zTheme loop: -1 changeState:)
				)
				(++ numZombies)
				(= noWearCrown 1)
				(z1 show: setCycle: EndLoop self)
				((View new:)
					view: 260
					posn: (z1 x?) (z1 y?)
					ignoreActors:
					addToPic:
				)
			)
			(2
				(if (== currentStatus 0)
					(z1
						view: 261
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Chase ego 12 self
					)
				else
					(z1
						view: 261
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Wander 50
					)
				)
			)
			(3
				(cond 
					((ego has: 7) (self changeState: 4))
					((== currentStatus 0) (self changeState: 10))
				)
			)
			(4
				(Print 18 41)
				(if (== (-- numZombies) 0)
					(zTheme loop: 1 changeState:)
				)
				(z1 setMotion: 0 view: 260 cel: 0 setCycle: BegLoop self)
			)
			(5
				(z1 ignoreActors: addToPic:)
			)
			(10
				(HandsOff)
				(= currentStatus 17)
				(zTheme dispose: number: 21 play:)
				(ego view: 36 cel: 255 setMotion: 0 setCycle: EndLoop self)
				(z1 setMotion: Wander)
				(if
					(and
						(cast contains: z5)
						(== (z5 mover?) Chase)
					)
					(z5 setMotion: Wander)
				)
				(if
					(and
						(cast contains: z4)
						(== (z4 mover?) Chase)
					)
					(z4 setMotion: Wander)
				)
			)
			(11
				(ego view: 46 cel: 255 setCycle: EndLoop self)
			)
			(12
				(= timedMessage (Print 18 42 #at -1 0 #dispose))
				(ego view: 45 setCycle: Walk setMotion: Wander)
				(= seconds 12)
			)
			(13
				(cls)
				(zTheme stop:)
				(= dead 1)
			)
		)
	)
)

(instance holeActions of Script
	(properties)
	
	(method (cue &tmp temp0)
		(= temp0 (hole cel?))
		(++ temp0)
		(if (>= state 0) (hole cel: temp0) else (++ state))
	)
)

(instance digging of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego
					view: 47
					loop: (& (ego loop?) $0001)
					cel: 255
					setCycle: EndLoop self
				)
				(= hole (Prop new:))
				(hole
					posn:
						(if (== (ego loop?) 1)
							(- (ego x?) 16)
						else
							(+ (ego x?) 16)
						)
						(+ (ego y?) 4)
					view: 528
					cel: 0
					setPri: 0
					ignoreActors:
					init:
				)
			)
			(2
				(ego cel: 255 setCycle: EndLoop self)
			)
			(3
				(if (>= timesUsedShovel 5)
					(Print 18 43)
					(ego view: 2 setCycle: Walk)
					((Inventory at: iShovel) loop: 1)
					(self changeState: 0)
					(HandsOn)
				else
					(ego cel: 255 setCycle: EndLoop self)
				)
			)
			(4
				(ego cel: 255 setCycle: EndLoop self)
			)
			(5
				(holeActions cue:)
				(if (< (hole cel?) 6)
					(self changeState: 2)
				else
					(self changeState: 6)
				)
			)
			(6
				(= [gNewPropX (= local5 (* (- (++ timesUsedShovel) 1) 3))]
					curRoomNum
				)
				(= [gNewPropX (+ local5 1)] (hole x?))
				(= [gNewPropX (+ local5 2)] (hole y?))
				(ego view: 2 setCycle: Walk)
				(HandsOn)
				(cond 
					(
						(and
							(& (ego onControl: FALSE) $0020)
							(== mansionPhase mansionBOY)
							((Inventory at: iToyHorse) ownedBy: 88)
						)
						(hole setLoop: 1 cel: 4)
						(Print 18 44 #draw)
						(ego setScript: getItems)
						(ego get: iToyHorse)
						(getItems changeState: 1)
						(theGame changeScore: 3)
					)
					(
						(and
							(& (ego onControl: FALSE) $0004)
							(== mansionPhase mansionLADY)
							((Inventory at: iLocket) ownedBy: 88)
						)
						(hole setLoop: 1 cel: 0)
						(Print 18 45 #draw)
						(ego setScript: getItems)
						(getItems changeState: 1)
						(ego get: iLocket)
						(theGame changeScore: 3)
					)
					(else (Print 18 46))
				)
				(HandsOn)
			)
		)
	)
)

(instance getItems of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego view: 21 cel: 255 setCycle: EndLoop self)
			)
			(2
				(hole setLoop: 0 cel: 6)
				(= gotItem 1)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego view: 2 setCycle: Walk)
				(Print 18 47)
				(HandsOn)
			)
		)
	)
)

(instance egoDist of Code
	(properties)
	
	(method (doit param1 param2 &tmp temp0)
		(= temp0
			(Sqrt
				(+
					(*
						(Abs (- (ego y?) param2))
						(Abs (- (ego y?) param2))
					)
					(*
						(Abs (- (ego x?) param1))
						(Abs (- (ego x?) param1))
					)
				)
			)
		)
	)
)
