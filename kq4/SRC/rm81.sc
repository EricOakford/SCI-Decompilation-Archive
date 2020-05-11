;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
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
	Room81 0
)

(local
	roomDialog
	h1
	h2
	door
	edgar
	nightWindow1
	nightWindow2
	local7
	doorLocked
	stolenInventory
	gInvFirst
)
(instance theMusic of Sound
	(properties
		number 43
	)
)

(instance doorOpenMusic of Sound
	(properties
		number 300
	)
)

(instance Room81 of Room
	(properties
		picture 81
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(super init:)
		(self setRegions: LOLOTTE)
		((View new:)
			view: 633
			loop: 2
			cel: 0
			posn: 122 70
			setPri: 3
			addToPic:
		)
		((View new:)
			view: 633
			loop: 2
			cel: 0
			posn: 196 71
			setPri: 3
			addToPic:
		)
		(if isNightTime
			((= nightWindow1 (Prop new:))
				view: 646
				loop: 1
				cel: 0
				posn: 225 94
				init:
				stopUpd:
			)
			((= nightWindow2 (Prop new:))
				view: 646
				loop: 0
				cel: 0
				posn: 91 91
				init:
				stopUpd:
			)
			((View new:)
				view: 633
				loop: 0
				cel: 3
				posn: 20 179
				setPri: 14
				addToPic:
			)
			((Prop new:)
				view: 633
				loop: 0
				cel: 4
				posn: 122 60
				setPri: 2
				init:
				setCycle: Forward
			)
			((Prop new:)
				view: 633
				loop: 1
				cel: 3
				posn: 197 61
				setPri: 2
				init:
				setCycle: Forward
			)
		)
		(= local7 0)
		(= doorLocked FALSE)
		(theMusic init:)
		(doorOpenMusic init:)
		((= door (Prop new:))
			view: 604
			posn: 58 151
			cel: 0
			ignoreActors:
			setPri: 12
			init:
			stopUpd:
		)
		(if (or (== prevRoomNum 85) (== prevRoomNum 0))
			(ego
				posn: 68 163
				view: 4
				setStep: 4 2
				illegalBits: -16384
				init:
			)
		)
		(if (and (== prevRoomNum 92) (not henchChasingEgo))
			(User canControl: FALSE canInput: FALSE)
			(ego
				posn: 83 165
				view: 4
				xStep: 4
				yStep: 2
				setPri: -1
				illegalBits: 0
				ignoreActors: TRUE
				init:
				setCycle: Walk
				setMotion: 0
			)
			((= h1 (Actor new:))
				view: 141
				posn: 98 175
				setStep: 4 2
				ignoreActors: TRUE
				init:
				setCycle: Walk
			)
			((= h2 (Actor new:))
				view: 141
				posn: 115 181
				setStep: 4 2
				ignoreActors: 1
				init:
				setCycle: Walk
			)
			(door cel: 4)
			(self setScript: lockUp)
		)
		(if henchChasingEgo
			(= henchChasingEgo FALSE)
			(User canControl: FALSE canInput: FALSE)
			(ego
				posn: 83 165
				view: 4
				illegalBits: -16384
				setStep: 4 2
				setPri: -1
				init:
				setCycle: Walk
			)
			((= h1 (Actor new:))
				view: 141
				posn: 115 181
				setStep: 4 2
				ignoreActors: 1
				illegalBits: 0
				init:
				setCycle: Walk
			)
			(door cel: 4)
			(self setScript: lockUpLast)
		)
		(= inCutscene FALSE)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $0040) (curRoom newRoom: 85))
		(if
			(and
				(ego inRect: 61 144 84 157)
				((inventory at: 34) ownedBy: 81)
			)
			(Print 81 0 #font smallFont #icon 409 0 0 #at -1 20)
			(= gotItem TRUE)
			(ego get: iRose)
		)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						((not (& (ego onControl: 0) $0800))
							(cond 
								(
									(or
										(Said 'look[<around][/!*]')
										(Said 'look/castle,tower,room,bedroom')
									)
									(Print 81 1)
								)
								((Said 'look>')
									(cond 
										((Said '<under/bed') (Print 81 2))
										((Said '/bed') (Print 81 3))
										((Said '/window')
											(if
												(or
													(ego inRect: 201 117 238 126)
													(ego inRect: 79 113 116 123)
												)
												(Print 81 4)
											else
												(Print 800 1)
											)
										)
										(
											(or
												(Said '<in/chest,dresser')
												(Said '<in/drawer[<chest]')
											)
											(Print 81 5)
										)
										((Said '/chest,dresser') (Print 81 6))
										((or (Said '/dirt') (Said '<down'))
											(if (== ((inventory at: iRose) owner?) 81)
												(Print 81 7)
											else
												(Print 81 8)
											)
										)
										((Said '/mirror')
											(if (ego inRect: 218 135 272 154)
												(Print 81 9)
											else
												(Print 800 1)
											)
										)
										((Said '/carpet') (Print 81 10))
										((Said '/stair') (Print 81 11))
										((Said '/door') (Print 81 12))
										((Said '/key')
											(if (and (ego has: iRose) (not (ego has: iGoldKey)))
												(Print 81 13)
											else
												(event claimed: FALSE)
											)
										)
									)
								)
								(
								(and (cast contains: edgar) (Said 'converse')) (Print 81 14))
								(
									(and
										((inventory at: iRose) ownedBy: 81)
										(Said 'get/rose')
									)
									(NotClose)
								)
								(
									(or
										(Said 'lay,sleep,get/bed')
										(Said 'sleep')
										(Said 'lay<down,sleep')
									)
									(Print 81 15)
								)
								(
								(or (Said 'open/chest') (Said 'open/drawer[<chest]')) (Print 81 5))
								((Said 'open/window') (Print 81 16))
								((Said 'break/window') (Print 81 17))
								((Said '[use]/key<skeleton')
									(cond 
										((not (ego has: iSkeletonKey)) (DontHave))
										((not doorLocked) (Print 81 18))
										(else (Print 81 19))
									)
								)
								((Said 'unlatch/door')
									(cond 
										((== prevRoomNum 85) (Print 81 20))
										((not doorLocked) (Print 81 21))
										((not (ego inRect: 70 150 84 157)) (Print 800 1))
										((ego has: iGoldKey) (= doorLocked FALSE) (Print 81 22) (theGame changeScore: 2))
										(else (Print 81 23))
									)
								)
								((or (Said '[use]/key<gold') (Said 'gold'))
									(cond 
										((not (ego inRect: 70 150 84 157)) (Print 800 1))
										((not (ego has: iGoldKey)) (Print 800 2))
										((not doorLocked) (Print 81 24))
										((or local7 (not (ego has: iSkeletonKey))) (Print 81 22) (= doorLocked FALSE) (theGame changeScore: 2))
										(else (Print 81 25))
									)
								)
								((Said 'open/door')
									(cond 
										((not (ego inRect: 70 150 84 157)) (Print 800 1))
										(doorLocked (Print 81 26))
										((== (door cel?) 0)
											(door startUpd: setCycle: EndLoop doDoor)
											(User canControl: FALSE canInput: FALSE)
											(ego posn: (ego x?) 146 illegalBits: cWHITE)
											(doorOpenMusic play:)
										)
										(else (Print 81 27))
									)
								)
								((Said 'close/door')
									(if (== (door cel?) 0)
										(Print 81 21)
									else
										(Print 81 28)
									)
								)
								((Said 'get,untie,detach/key')
									(if (and (ego has: iRose) (not (ego has: iGoldKey)))
										(Print 81 29)
										(= gotItem TRUE)
										(ego get: iGoldKey)
										(theGame changeScore: 2)
										((inventory at: iRose)
											description: {What a beautiful red rose!}
											loop: 1
											cel: 0
										)
									else
										(event claimed: FALSE)
									)
								)
							)
						)
						(
						(or (Said 'look[<around][/!*]') (Said 'look/room')) (Print 81 30))
						((Said 'look/stair') (Print 81 31))
						((Said 'look/door') (Print 81 12))
						((Said 'look/wall') (Print 81 32))
						((Said 'close/door') (Print 81 28))
						((Said 'open/door')
							(cond 
								((not (ego inRect: 57 155 84 165)) (Print 800 1))
								((== (door cel?) 0)
									(door startUpd: setCycle: EndLoop doDoor)
									(User canControl: FALSE canInput: FALSE)
									(ego illegalBits: cWHITE setMotion: 0)
									(doorOpenMusic play:)
								)
								(else (Print 81 33))
							)
						)
						((Said 'unlatch/*') (Print 81 34))
					)
				)
			)
		)
	)
)

(instance doDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door stopUpd:)
				(User canControl: TRUE canInput: TRUE)
			)
		)
	)
)

(instance lockUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setMotion: 0
					setLoop: -1
					setMotion: MoveTo 68 165 self
				)
				(h1 setMotion: Follow ego 10)
				(h2 setMotion: Follow ego 25)
			)
			(1
				(h1 setMotion: 0)
				(h2 setMotion: 0)
				(ego setMotion: MoveTo 70 150 self)
			)
			(2
				(ego setMotion: MoveTo 106 139 self)
				(= doorLocked TRUE)
				(door setCycle: BegLoop)
			)
			(3
				(ego illegalBits: -16384)
				(h2 illegalBits: 0 setMotion: MoveTo 145 235 self)
				(h1 illegalBits: 0 setMotion: MoveTo 145 239)
			)
			(4
				(User canControl: TRUE canInput: TRUE)
				(door stopUpd:)
				(h1 dispose:)
				(h2 dispose:)
				((ScriptID 0 4) setReal: self 2)
			)
			(5
				(Print 81 35 #at -1 30 #time 5)
				((ScriptID 0 4) setReal: self 30)
			)
			(6
				(theMusic play:)
				((= edgar (Actor new:))
					view: 131
					loop: 1
					posn: 158 215
					illegalBits: 0
					init:
					setCycle: Walk
					setMotion: MoveTo 68 162 self
				)
			)
			(7
				(= roomDialog (Print 81 36 #at -1 25 #dispose))
				(edgar
					cycleSpeed: 7
					setLoop: 0
					cel: 255
					setCycle: CycleTo 10 1
				)
				((ScriptID 0 4) setReal: self 3)
			)
			(8
				(edgar setCycle: EndLoop self)
			)
			(9
				(cls)
				((inventory at: iRose) moveTo: 81)
				(edgar
					view: 130
					setLoop: -1
					illegalBits: 0
					setCycle: Walk
					cycleSpeed: 0
					setMotion: MoveTo 190 249 self
				)
			)
			(10
				(edgar dispose:)
				(theMusic client: 0 stop:)
				(= seconds 120)
			)
			(11
				(HandsOff)
				(ego loop: 0)
				(if isNightTime
					(nightWindow1 cycleSpeed: 7 cel: 255 setCycle: EndLoop self)
					(nightWindow2 cycleSpeed: 7 cel: 255 setCycle: EndLoop)
				)
			)
			(12
				(Print 81 37 #at -1 10 #width 260)
				(= seconds 12)
			)
			(13
				(= isHandsOff FALSE)
				(= inCutscene TRUE)
				((ScriptID LOLOTTE) keep: FALSE)
				(curRoom newRoom: 692)
			)
		)
	)
)

(instance lockUpLast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= gInvFirst (inventory first:))
				(while gInvFirst
					(if
						(and
							(= stolenInventory (NodeValue gInvFirst))
							(== (stolenInventory owner?) ego)
						)
						(stolenInventory owner: 89)
					)
					(= gInvFirst (inventory next: gInvFirst))
				)
				(ego setMotion: 0 illegalBits: 0)
				(h1 setMotion: Follow ego 10)
				(ego setLoop: -1 setMotion: MoveTo 68 165 self)
			)
			(1
				(h1 setMotion: 0)
				(ego setMotion: MoveTo 70 150 self)
			)
			(2
				(ego setMotion: MoveTo 106 139 self)
				(= doorLocked TRUE)
				(door setCycle: BegLoop)
			)
			(3
				(h1 ignoreActors: 1 setMotion: MoveTo 150 225 self)
			)
			(4
				(ego setMotion: MoveTo 182 147 self)
				(door stopUpd:)
				(h1 dispose:)
			)
			(5
				(ego setMotion: MoveTo 216 124 self)
			)
			(6
				(ego loop: 0)
				(if isNightTime
					(nightWindow1 cycleSpeed: 7 cel: 255 setCycle: EndLoop self)
					(nightWindow2 cycleSpeed: 7 cel: 255 setCycle: EndLoop)
				)
			)
			(7
				(Print 81 38 #at -1 10 #width 260 #time 10)
				(ego setMotion: MoveTo 160 149 self)
			)
			(8 (ego loop: 2) (= seconds 3))
			(9
				(= inCutscene TRUE)
				(= isHandsOff FALSE)
				((ScriptID LOLOTTE) keep: 0)
				(curRoom newRoom: 692)
				(= noWearCrown FALSE)
			)
		)
	)
)
