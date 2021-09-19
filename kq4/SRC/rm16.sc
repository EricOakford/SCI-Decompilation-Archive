;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
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
	Room16 0
)
(synonyms
	(kiss kiss embrace)
	(ghoul ghoul ghoul ghoul man ghoul ghoul)
)

(local
	zombie1
	zombie2
	zombie3
	aHole
	local4
	holeXY
	i
	lookHoleIndex
	holeEmpty
)
(instance zTheme of Sound
	(properties
		number 20
		priority 1
		loop -1
	)
)

(instance Room16 of Room
	(properties
		picture 16
	)
	
	(method (init)
		(= north 10)
		(= south 22)
		(= east 17)
		(= west 15)
		(= horizon 80)
		(= isIndoors FALSE)
		(= numZombies 0)
		(Load VIEW 47)
		(if (<= (ego y?) (+ horizon 1))
			(ego y: (+ horizon 1))
		)
		(if (== prevRoomNum 22) (ego x: 158))
		(super init:)
		(if isNightTime
			(curRoom overlay: 116)
		)
		(self setRegions: CEMETERY)
		(if isNightTime
			(Load VIEW 261)
			(Load VIEW 263)
			(Load VIEW 265)
			(Load VIEW 36)
			(Load VIEW 46)
			(Load VIEW 45)
			(Load VIEW 21)
			(= zombie1 (Actor new:))
			(= zombie2 (Actor new:))
			(= zombie3 (Actor new:))
			(zombie1
				view: 260
				loop: 0
				cel: 0
				xStep: 2
				yStep: 1
				posn: 250 116
				setScript: z1Actions
				init:
				hide:
			)
			(zombie2
				view: 262
				loop: 0
				cel: 0
				xStep: 2
				yStep: 1
				posn: 70 123
				setScript: z2Actions
				init:
				hide:
			)
			(zombie3
				view: 264
				loop: 0
				cel: 0
				xStep: 2
				yStep: 1
				posn: 103 172
				setScript: z3Actions
				init:
				hide:
			)
		)
		(ego view: 2 observeControl: cYELLOW init:)
		(= currentStatus egoNormal)
		(curRoom setScript: holeActions)
		(for ((= i 1)) (<= i timesUsedShovel) ((++ i))
			(if
				(==
					[aDugHole (= holeXY (* (- i 1) 3))]
					curRoomNum
				)
				((View new:)
					view: 528
					loop: 0
					cel: 6
					posn: [aDugHole (+ holeXY 1)] [aDugHole (+ holeXY 2)]
					setPri: 0
					addToPic:
					ignoreActors:
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look/gravestone')
						(Print 16 0)
					)
					((Said 'read,look/epitaph,gravestone,boulder')
						(cond 
							((& (ego onControl: 0) cCYAN)
								(Print 16 1
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cMAGENTA)
								(Print 16 2 
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cGREY)
								(Print 16 3
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cLBLUE)
								(Print 16 4
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cGREEN)
								(Print 16 5
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cBLUE)
								(Print 16 6
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cLGREY)
								(Print 16 7
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cBROWN)
								(Print 16 8
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cRED)
								(Print 16 9
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cLGREEN)
								(Print 16 10
									#mode teJustCenter
									#at -1 15
									#width 290
								)
							)
							((& (ego onControl: 0) cLRED)
								(Print 16 11
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
					((Said 'look>')
						(cond 
							((or (Said '<in/forest') (Said '/forest<hole'))
								(if (ego inRect: 103 115 226 177)
									(Print 16 12)
								else
									(Print 800 1)
								)
							)
							((Said '/cross')
								(Print 16 13)
							)
							((Said '/forest')
								(Print 16 14)
							)
							((Said '/hole')
								(for ((= lookHoleIndex 1)) (<= lookHoleIndex timesUsedShovel) ((++ lookHoleIndex))
									(if
										(and
											(== [aDugHole (= holeXY (* (- lookHoleIndex 1) 3))] 16)
											(<
												(egoDist
													doit: [aDugHole (+ holeXY 1)] [aDugHole (+ holeXY 2)]
												)
												20
											)
										)
										(= holeEmpty TRUE)
									)
								)
								(cond 
									(holeEmpty
										(Print 16 15)
									)
									((ego inRect: 103 115 226 177)
										(Print 16 12)
									)
									(else
										(Print 800 1)
									)
								)
								(= holeEmpty FALSE)
							)
							((Said '[<around][/room]')
								(Print 16 16)
							)
						)
					)
					((Said 'dig[/grave,hole]')
						(if (and (ego has: iShovel) (== 0 ((Inventory at: iShovel) loop?)))
							(if (> mansionPhase 0)
								(if (& (ego onControl: 0) (- cWHITE cBLACK))
									(ego setScript: digging)
									(digging changeState: 1)
								else
									(Print 16 17)
								)
							else
								(Print 16 18)
							)
						else
							(Print 16 19)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(ego illegalBits: cWHITE)
		(= noWearCrown FALSE)
		(if (== (ego view?) 2)
			(super newRoom: n)
		)
	)
)

(instance z1Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 4)
			)
			(1
				(= noWearCrown TRUE)
				(zTheme play:)
				(zombie1 show: setCycle: EndLoop self)
				(++ numZombies)
				((View new:)
					view: 260
					posn: (zombie1 x?) (zombie1 y?)
					ignoreActors:
					addToPic:
				)
			)
			(2
				(if (== currentStatus egoNormal)
					(zombie1
						view: 261
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Chase ego 12 self
					)
				else
					(zombie1
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
				(Print 16 20 #at -1 20)
				(if (== (-- numZombies) 0)
					(zTheme loop: 1 changeState:)
				)
				(zombie1 setMotion: 0 view: 260 cel: 0 setCycle: BegLoop self)
			)
			(5
				(zombie1 ignoreActors: addToPic:)
			)
			(10
				(HandsOff)
				(= currentStatus egoZombie)
				(zTheme stop:)
				(zTheme number: 21 loop: 1 play:)
				(ego view: 36 cel: 0 setCycle: EndLoop self)
				(zombie1 setMotion: Wander)
				(if
					(and
						(cast contains: zombie2)
						(== (zombie2 mover?) Chase)
					)
					(zombie2 setMotion: Wander)
				)
				(if
					(and
						(cast contains: zombie3)
						(== (zombie3 mover?) Chase)
					)
					(zombie3 setMotion: Wander)
				)
			)
			(11
				(ego view: 46 cel: 0 setCycle: EndLoop self)
			)
			(12
				(= globalPrint (Print 16 21 #at -1 20 #dispose))
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

(instance z2Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 10)
			)
			(1
				(if (== (zTheme state?) 0) (zTheme play:))
				(if (== (zTheme loop?) 1)
					(zTheme loop: -1 changeState:)
				)
				(++ numZombies)
				(zombie2 show: setCycle: EndLoop self)
				((View new:)
					view: 262
					cel: 0
					posn: (zombie2 x?) (zombie2 y?)
					ignoreActors:
					addToPic:
				)
			)
			(2
				(if (== currentStatus egoNormal)
					(zombie2
						view: 263
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Chase ego 12 self
					)
				else
					(zombie2
						view: 263
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
				(zombie2
					setMotion: 0
					view: 262
					cel: 0
					setCycle: BegLoop self
				)
				(Print 16 20 #at -1 20)
				(if (== (-- numZombies) 0)
					(zTheme loop: 1 changeState:)
				)
			)
			(5
				(zombie2 ignoreActors: addToPic:)
			)
			(10
				(HandsOff)
				(= currentStatus egoZombie)
				(zTheme stop:)
				(zTheme number: 21 loop: 1 play:)
				(ego view: 36 cel: 0 setMotion: 0 setCycle: EndLoop self)
				(zombie2 setMotion: Wander)
				(if
					(and
						(cast contains: zombie1)
						(== (zombie1 mover?) Chase)
					)
					(zombie1 setMotion: Wander)
				)
				(if
					(and
						(cast contains: zombie3)
						(== (zombie3 mover?) Chase)
					)
					(zombie3 setMotion: Wander)
				)
			)
			(11
				(ego view: 46 cel: 0 setCycle: EndLoop self)
			)
			(12
				(= globalPrint (Print 16 21 #at -1 20 #dispose))
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

(instance z3Actions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 15)
			)
			(1
				(if (== (zTheme state?) 0) (zTheme play:))
				(if (== (zTheme loop?) 1)
					(zTheme loop: -1 changeState:)
				)
				(++ numZombies)
				(zombie3 show: setCycle: EndLoop self)
				((View new:)
					view: 264
					posn: (zombie3 x?) (zombie3 y?)
					ignoreActors:
					addToPic:
				)
			)
			(2
				(if (== currentStatus egoNormal)
					(zombie3
						view: 265
						setCycle: Walk
						setAvoider: (Avoider new:)
						setMotion: Chase ego 12 self
					)
				else
					(zombie3
						view: 265
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
				(Print 16 20 #at -1 20)
				(if (== (-- numZombies) 0)
					(zTheme loop: 1 changeState:)
				)
				(zombie3
					setMotion: 0
					view: 264
					cel: 0
					setCycle: BegLoop self
				)
			)
			(5
				(zombie3 ignoreActors: addToPic:)
			)
			(10
				(HandsOff)
				(= currentStatus egoZombie)
				(zTheme stop:)
				(zTheme number: 21 loop: 1 play:)
				(ego view: 36 cel: 0 setMotion: 0 setCycle: EndLoop self)
				(zombie3 setMotion: Wander)
				(if
					(and
						(cast contains: zombie1)
						(== (zombie1 mover?) Chase)
					)
					(zombie1 setMotion: Wander)
				)
				(if
					(and
						(cast contains: zombie2)
						(== (zombie2 mover?) Chase)
					)
					(zombie2 setMotion: Wander)
				)
			)
			(11
				(ego view: 46 cel: 0 setCycle: EndLoop self)
			)
			(12
				(= globalPrint (Print 16 21 #at -1 20 #dispose))
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
					cel: 0
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
				(ego cel: 0 setCycle: EndLoop self)
			)
			(3
				(if (>= timesUsedShovel 5) ;after 5 uses, the shovel breaks
					(Print 16 22)
					(ego view: 2 setCycle: Walk)
					((Inventory at: iShovel) loop: 1)
					(self changeState: 0)
					(HandsOn)
				else
					(ego cel: 0 setCycle: EndLoop self)
				)
			)
			(4
				(ego cel: 0 setCycle: EndLoop self)
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
				(= [aDugHole (= holeXY (* (- (++ timesUsedShovel) 1) 3))]
					curRoomNum
				)
				(= [aDugHole (+ holeXY 1)] (aHole x?))
				(= [aDugHole (+ holeXY 2)] (aHole y?))
				(ego view: 2 setCycle: Walk)
				(HandsOn)
				(cond 
					(
						(and
							(& (ego onControl: 0) cCYAN)
							(== mansionPhase mansionMISER)
							((Inventory at: iGoldCoins) ownedBy: 88)
						)
						(aHole setLoop: 1 cel: 2)
						(Print 16 23 #at -1 20 #draw)
						((Inventory at: iGoldCoins) moveTo: 16)
						(ego setScript: getItems)
						(getItems changeState: 1)
						(ego get: iGoldCoins)
						(theGame changeScore: 3)
					)
					(
						(and
							(& (ego onControl: 0) cGREY)
							(== mansionPhase mansionBABY)
							((Inventory at: iRattle) ownedBy: 88)
						)
						(aHole setLoop: 1 cel: 1)
						(Print 16 24 #at -1 20 #draw)
						(ego setScript: getItems)
						(getItems changeState: 1)
						(theGame changeScore: 3)
						(ego get: 12)
					)
					(
						(and
							(& (ego onControl: 0) cGREEN)
							(== mansionPhase mansionLORD)
							((Inventory at: iMedal) ownedBy: 88)
						)
						(aHole setLoop: 1 cel: 3)
						(Print 16 25 #at -1 20 #draw)
						((Inventory at: iMedal) moveTo: 16)
						(ego setScript: getItems)
						(getItems changeState: 1)
						(ego get: iMedal)
						(theGame changeScore: 3)
					)
					(else (HandsOn) (Print 16 26 #at -1 20))
				)
			)
		)
	)
)

(instance getItems of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Face ego aHole)
				(ego view: 21 cel: 255 setCycle: EndLoop self)
			)
			(2
				(aHole setLoop: 0 cel: 6)
				(= gotItem TRUE)
				(ego setCycle: BegLoop self)
			)
			(3
				(= cycles 6)
			)
			(4
				(Print 16 27)
				(ego view: 2 setCycle: Walk)
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
