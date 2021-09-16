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
	aZombie1
	aZombie2
	aZombie3
	aHole
	local4
	local5
	local6
	lookHoleIndex
	holeEmpty
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
		(ego edgeHit: 0 observeControl: cYELLOW)
		(= numZombies 0)
		(super init:)
		(if isNightTime
			(curRoom overlay: 118)
		)
		(self setRegions: CEMETERY MOUNTAIN)
		(if isNightTime
			(Load VIEW 261)
			(Load VIEW 267)
			(Load VIEW 269)
			(Load VIEW 36)
			(Load VIEW 46)
			(Load VIEW 45)
			(Load VIEW 21)
			(= aZombie3 (Actor new:))
			(= aZombie1 (Actor new:))
			(= aZombie2 (Actor new:))
			(aZombie3
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
			(aZombie1
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
			(aZombie2
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
			(ego ignoreControl: cYELLOW)
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
			(west
				(ego posn: 2 (ego y?))
			)
			(north
				(ego x: 45 y: (+ horizon (ego yStep?) 1))
			)
			(south
				(ego posn: 172 188)
			)
			(69
				(ego x: 266 y: 153 xStep: 3 yStep: 2 ignoreControl: cYELLOW)
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
			(0
				(ego x: 180 y: 188)
			)
		)
		(= currentStatus egoNormal)
		(ego view: 2 init:)
		(for ((= local6 1)) (<= local6 timesUsedShovel) ((++ local6))
			(if
				(==
					[aDugHole (= local5 (* (- local6 1) 3))]
					curRoomNum
				)
				((View new:)
					view: 528
					loop: 0
					cel: 6
					posn: [aDugHole (+ local5 1)] [aDugHole (+ local5 2)]
					ignoreActors:
					setPri: 0
					addToPic:
					ignoreActors:
				)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) cYELLOW)
			(curRoom newRoom: 69)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'climb/boulder')
						(Print 18 0)
					)
					((Said 'open,enter/crypt')
						(if (== cryptDoorState doorOpen)
							(Print 18 1)
						else
							(Print 18 2)
						)
					)
					((Said 'look>')
						(cond 
							((Said '/boulder')
								(Print 18 3)
							)
							((Said '/grass')
								(Print 18 4)
							)
							((Said '/crypt')
								(Print 18 5)
							)
							((Said '/cliff')
								(Print 18 6)
							)
							((Said '/gravestone')
								(Print 18 7)
							)
							((Said '/monument,lion')
								(Print 18 8)
							)
							((Said '/door')
								(if (== (door cel?) 0)
									(Print 18 9)
								else
									(Print 18 10)
								)
							)
							((Said '/hole')
								(for ((= lookHoleIndex 1)) (<= lookHoleIndex timesUsedShovel) ((++ lookHoleIndex))
									(if
										(and
											(== [aDugHole (= local5 (* (- lookHoleIndex 1) 3))] 18)
											(<
												(egoDist
													doit: [aDugHole (+ local5 1)] [aDugHole (+ local5 2)]
												)
												20
											)
										)
										(= holeEmpty TRUE)
									)
								)
								(if (>= timesUsedShovel 0)
									(if holeEmpty
										(Print 18 11)
									else
										(Print 800 1)
									)
								else
									(Print 18 12)
								)
								(= holeEmpty FALSE)
							)
							((Said '[<around][/room]')
								(Print 18 13)
								(Print 18 14)
							)
						)
					)
				)
				(cond 
					((Said '/door>')
						(cond 
							((Said 'break')
								(Print 18 15)
							)
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
											(ego setMotion: 0 ignoreControl: cYELLOW)
											(HandsOff)
											(= cryptDoorState doorOpen)
											(= inCutscene TRUE)
											(door setCycle: EndLoop door)
										)
										((== cryptDoorState doorLocked)
											(Print 18 17)
										)
										(else
											(Print 18 18)
										)
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
									(ego observeControl: cYELLOW)
									(= cryptDoorState doorClosed)
									(doorSound play:)
								)
							)
							((Said 'latch')
								(if ((Inventory at: iSkeletonKey) ownedBy: ego)
									(if (ego inRect: 262 149 292 157)
										(cond 
											((== cryptDoorState doorClosed)
												(if (and ((Inventory at: iPandorasBox) ownedBy: 69) (== gamePhase endGame))
													(Print 18 21 #draw)
													(theGame changeScore: 2)
													(ego put: iSkeletonKey 18)
													(= cryptDoorState doorLocked)
													(ego observeControl: cYELLOW)
												else
													(Print 18 22)
													(= cryptDoorState doorLocked)
												)
											)
											((== cryptDoorState doorOpen)
												(Print 18 23)
											)
											((== cryptDoorState doorLocked)
												(Print 18 24)
											)
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
												(if (not unlockedCryptDoor)
													(theGame changeScore: 3)
													(++ unlockedCryptDoor)
												)
												(= cryptDoorState doorClosed)
											else
												(Print 18 27)
											)
										)
										((== cryptDoorState doorOpen)
											(Print 18 28)
										)
										((== cryptDoorState doorClosed)
											(Print 18 29)
										)
									)
								else
									(Print 800 1)
								)
							)
						)
					)
					((Said 'read,look/epitaph,gravestone,boulder')
						(cond 
							((& (ego onControl: 0) cGREEN)
								(Print 18 30
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cLGREY)
								(Print 18 31
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cCYAN)
								(Print 18 32
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cMAGENTA)
								(Print 18 33
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cLRED)
								(Print 18 34
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cBLUE)
								(Print 18 35
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cRED)
								(Print 18 36
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cGREY)
								(Print 18 37
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							(else
								(Print 800 1)
							)
						)
					)
					((Said 'dig[/hole,grave]')
						(if (and (ego has: iShovel) (== 0 ((Inventory at: iShovel) loop?)))
							(if (> mansionPhase 0)
								(if (& (ego onControl: 0) (- cWHITE cBLACK))
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
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(HandsOn)
		(= noWearCrown FALSE)
		(if (== (ego view?) 2)
			(super newRoom: n)
		)
	)
)

(instance z4Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(= noWearCrown TRUE)
				(zTheme loop: -1 play:)
				(++ numZombies)
				(aZombie1 show: setCycle: EndLoop self)
				((View new:)
					view: 266
					posn: (aZombie1 x?) (aZombie1 y?)
					ignoreActors:
					addToPic:
				)
			)
			(2
				(if (== currentStatus egoNormal)
					(aZombie1
						view: 267
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Chase ego 12 self
					)
				else
					(aZombie1
						view: 267
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Wander 50
					)
				)
			)
			(3
				(cond 
					((ego has: iScarab)
						(self changeState: 4)
					)
					((== currentStatus egoNormal)
						(self changeState: 10)
					)
				)
			)
			(4
				(aZombie1
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
				(aZombie1 ignoreActors: addToPic:)
			)
			(10
				(HandsOff)
				(= currentStatus egoZombie)
				(zTheme dispose: number: 21 play:)
				(ego view: 36 cel: 255 setCycle: EndLoop self)
				(if
					(and
						(cast contains: aZombie3)
						(== (aZombie3 mover?) Chase)
					)
					(aZombie3 setMotion: Wander)
				)
				(if
					(and
						(cast contains: aZombie2)
						(== (aZombie2 mover?) Chase)
					)
					(aZombie2 setMotion: Wander)
				)
				(if (cast contains: aZombie3)
					(aZombie3 setMotion: Wander)
				)
				(aZombie1 setMotion: Wander)
			)
			(11
				(ego view: 46 cel: 255 setCycle: EndLoop self)
			)
			(12
				(= underBits (Print 18 42 #at -1 0 #dispose))
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
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 8)
			)
			(1
				(++ numZombies)
				(if (== (zTheme state?) 0)
					(zTheme loop: -1 play:)
				)
				(if (== (zTheme loop?) 1)
					(zTheme loop: -1 changeState:)
				)
				(aZombie2 show: setCycle: EndLoop self)
				((View new:)
					view: 268
					cel: 0
					posn: (aZombie2 x?) (aZombie2 y?)
					ignoreActors:
					addToPic:
				)
			)
			(2
				(if (== currentStatus egoNormal)
					(aZombie2
						view: 269
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Chase ego 12 self
					)
				else
					(aZombie2
						view: 269
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Wander 50 self
					)
				)
			)
			(3
				(cond 
					((ego has: iScarab)
						(self changeState: 4)
					)
					((== currentStatus egoNormal)
						(self changeState: 10)
					)
				)
			)
			(4
				(Print 18 41)
				(if (== (-- numZombies) 0)
					(zTheme loop: 1 changeState:)
				)
				(aZombie2
					setMotion: 0
					view: 268
					cel: 0
					setCycle: BegLoop self
				)
			)
			(5
				(aZombie2 ignoreActors: addToPic:)
			)
			(10
				(HandsOff)
				(= currentStatus egoZombie)
				(zTheme dispose: number: 21 play:)
				(ego view: 36 cel: 255 setMotion: 0 setCycle: EndLoop self)
				(aZombie2 setMotion: Wander)
				(if
					(and
						(cast contains: aZombie3)
						(== (aZombie3 mover?) Chase)
					)
					(aZombie3 setMotion: Wander)
				)
				(if
					(and
						(cast contains: aZombie2)
						(== (aZombie1 mover?) Chase)
					)
					(aZombie1 setMotion: Wander)
				)
			)
			(11
				(ego view: 46 cel: 255 setCycle: EndLoop self)
			)
			(12
				(= underBits (Print 18 42 #at -1 0 #dispose))
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

(instance z1Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 10)
			)
			(1
				(if (== (zTheme state?) 0)
					(zTheme loop: -1 play:)
				)
				(if (== (zTheme loop?) 1)
					(zTheme loop: -1 changeState:)
				)
				(++ numZombies)
				(= noWearCrown TRUE)
				(aZombie3 show: setCycle: EndLoop self)
				((View new:)
					view: 260
					posn: (aZombie3 x?) (aZombie3 y?)
					ignoreActors:
					addToPic:
				)
			)
			(2
				(if (== currentStatus egoNormal)
					(aZombie3
						view: 261
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Chase ego 12 self
					)
				else
					(aZombie3
						view: 261
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Wander 50
					)
				)
			)
			(3
				(cond 
					((ego has: iScarab)
						(self changeState: 4)
					)
					((== currentStatus egoNormal)
						(self changeState: 10)
					)
				)
			)
			(4
				(Print 18 41)
				(if (== (-- numZombies) 0)
					(zTheme loop: 1 changeState:)
				)
				(aZombie3 setMotion: 0 view: 260 cel: 0 setCycle: BegLoop self)
			)
			(5
				(aZombie3 ignoreActors: addToPic:)
			)
			(10
				(HandsOff)
				(= currentStatus egoZombie)
				(zTheme dispose: number: 21 play:)
				(ego view: 36 cel: 255 setMotion: 0 setCycle: EndLoop self)
				(aZombie3 setMotion: Wander)
				(if
					(and
						(cast contains: aZombie2)
						(== (aZombie2 mover?) Chase)
					)
					(aZombie2 setMotion: Wander)
				)
				(if
					(and
						(cast contains: aZombie1)
						(== (aZombie1 mover?) Chase)
					)
					(aZombie1 setMotion: Wander)
				)
			)
			(11
				(ego view: 46 cel: 255 setCycle: EndLoop self)
			)
			(12
				(= underBits (Print 18 42 #at -1 0 #dispose))
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

(instance holeActions of Script
	(method (cue &tmp theCel)
		(= theCel (aHole cel?))
		(++ theCel)
		(if (>= state 0)
			(aHole cel: theCel)
		else
			(++ state)
		)
	)
)

(instance digging of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego
					view: 47
					loop: (& (ego loop?) 1)
					cel: 255
					setCycle: EndLoop self
				)
				(= aHole (Prop new:))
				(aHole
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
				(if (< (aHole cel?) 6)
					(self changeState: 2)
				else
					(self changeState: 6)
				)
			)
			(6
				(= [aDugHole (= local5 (* (- (++ timesUsedShovel) 1) 3))]
					curRoomNum
				)
				(= [aDugHole (+ local5 1)] (aHole x?))
				(= [aDugHole (+ local5 2)] (aHole y?))
				(ego view: 2 setCycle: Walk)
				(HandsOn)
				(cond 
					(
						(and
							(& (ego onControl: 0) cMAGENTA)
							(== mansionPhase mansionBOY)
							((Inventory at: iToyHorse) ownedBy: 88)
						)
						(aHole setLoop: 1 cel: 4)
						(Print 18 44 #draw)
						(ego setScript: getItems)
						(ego get: iToyHorse)
						(getItems changeState: 1)
						(theGame changeScore: 3)
					)
					(
						(and
							(& (ego onControl: 0) cGREEN)
							(== mansionPhase mansionLADY)
							((Inventory at: iLocket) ownedBy: 88)
						)
						(aHole setLoop: 1 cel: 0)
						(Print 18 45 #draw)
						(ego setScript: getItems)
						(getItems changeState: 1)
						(ego get: iLocket)
						(theGame changeScore: 3)
					)
					(else
						(Print 18 46)
					)
				)
				(HandsOn)
			)
		)
	)
)

(instance getItems of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego view: 21 cel: 255 setCycle: EndLoop self)
			)
			(2
				(aHole setLoop: 0 cel: 6)
				(= gotItem TRUE)
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
