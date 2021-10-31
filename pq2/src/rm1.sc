;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1)
(include game.sh)
(use Main)
(use Intrface)
(use AutoDoor)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm1 0
)

(local
	walkingOffGrounds
	officeDoor
	nearUnmarkedCar
	randCarExists
	;local4 unused
	carPullingIn
	local6
	local7
	carX
	carY
	printObj
)
(procedure (EnterCar)
	(cond 
		((ego inRect: 93 144 114 154)
			(cond 
				((== workCarLocked TRUE)
					(Print 1 77)
				)
				(workCarTrunkOpened
					(Print 1 78)
				)
				((ego has: iFieldKit)
					(Print 1 79)
				)
				(else
					(= currentCar carWork)
					(rm1 setScript: enterScript)
				)
			)
		)
		((ego inRect: 259 140 279 148)
			(if (== personalCarLocked TRUE)
				(Print 1 80)
			else
				(= currentCar carPersonal)
				(rm1 setScript: enterScript)
			)
		)
		(else
			(NotClose)
		)
	)
)

(instance carDoor of Prop)

(instance unTrunk of Prop)

(instance flag of Prop)

(instance egosCar of View)

(instance unmarked of View)

(instance ourCar of Actor)

(instance car1Block of Block
	(properties
		top 131
		left 54
		bottom 147
		right 165
	)
)

(instance car2Block of Block
	(properties
		top 151
		left 46
		bottom 161
		right 129
	)
)

(instance car3Block of Block
	(properties
		top 169
		left 29
		bottom 184
		right 117
	)
)

(instance car4Block of Block
	(properties
		top 133
		left 216
		bottom 139
		right 288
	)
)

(instance car6Block of Block
	(properties
		top 172
		left 228
		bottom 181
		right 316
	)
)

(instance rm1 of Room
	(properties
		picture 1
		style IRISIN
	)
	
	(method (init)
		(Load VIEW 0)
		(Load VIEW 8)
		(Load VIEW 51)
		(Load VIEW 55)
		(Load VIEW 20)
		(Load VIEW 54)
		(super init:)
		(self setLocales: regFieldKit)
		(= gunFireState gunUSELESS)
		(User canInput: TRUE canControl: TRUE)
		(ego view: (if (not gunDrawn) 0 else 6) init:)
		(if (== gamePhase 7)
			(= workCarLocked TRUE)
		)
		((= keith (Actor new:))
			view: 20
			posn: 0 0
			setCycle: Walk
			setAvoider: (Avoider new:)
			init:
			stopUpd:
		)
		((= officeDoor (AutoDoor new:))
			entranceTo: 2
			facingLoop: -1
			locked: TRUE
			stopUpd:
			view: 8
			posn: 132 100
			init:
		)
		(flag
			view: 55
			posn: 172 14
			cycleSpeed: 2
			setCycle: Forward
			init:
		)
		(if (< howFast 30)
			(flag stopUpd:)
		)
		(if workCarTrunkOpened
			(unTrunk
				view: 51
				loop: 4
				cel: 2
				posn: 148 148
				setPri: 10
				ignoreActors:
				init:
			)
		)
		(if
			(= carPullingIn
				(if (or (== prevRoomNum 33) (== prevRoomNum 13))
				else
					(== global160 0)
				)
			)
			(HandsOff)
		else
			(HandsOn)
		)
		(= global132
			(if
				(= local6
					(if (== roomCarParked curRoomNum) (!= prevRoomNum 2))
				)
				carPullingIn
			else
				0
			)
		)
		(= local7 (if (== currentCar carWork) 118 else 247))
		(= carX (if (== currentCar carWork) 110 else 257))
		(= carY (if (== currentCar carWork) 144 else 141))
		(if (or (== prevRoomNum 2) (== currentCar carPersonal))
			(unmarked
				view: 54
				loop: 0
				setCel: 1
				posn: 110 144
				setPri: 10
				init:
				ignoreActors:
				addToPic:
			)
			(ego observeBlocks: car1Block)
		)
		(if (== (Random 0 3) 1)
			((View new:)
				view: 54
				loop: 0
				setCel: 4
				posn: 89 162
				setPri: 12
				init:
				ignoreActors:
				addToPic:
			)
			(= randCarExists 1)
			(ego observeBlocks: car2Block)
		)
		(if (Btst fDocBookingEvidence)
			((View new:)
				view: 54
				loop: 0
				setCel: 3
				posn: 64 180
				setPri: 14
				init:
				ignoreActors:
				addToPic:
			)
			(ego observeBlocks: car3Block)
		)
		(if (or (== prevRoomNum 2) (== currentCar carWork))
			(egosCar
				view: 54
				loop: 0
				cel: 0
				posn: 257 141
				ignoreActors:
				init:
				addToPic:
			)
			(ego observeBlocks: car4Block)
		)
		(if (!= (Random 0 3) 1)
			((View new:)
				view: 54
				loop: 1
				cel: 2
				posn: 278 182
				init:
				ignoreActors:
				addToPic:
			)
			(ego observeBlocks: car6Block)
			(= nearUnmarkedCar TRUE)
		)
		(= roomCarParked curRoomNum)
		(if (!= prevRoomNum 2)
			(= carPullingIn TRUE)
			(ourCar
				view: 54
				setStep: 2
				setLoop: 0
				setCel: (if (== currentCar carWork) 1 else 0)
				ignoreActors:
				illegalBits: 0
				setCycle: 0
				setMotion: 0
				posn: (if local6 carX else local7) carY
				init:
			)
		else
			(= carPullingIn 0)
		)
		(if (== prevRoomNum 2)
			(ego 
				posn: 131 110
				setMotion: MoveTo 131 400
			)
		else
			(self setScript: driveUpScript)
		)
	)
	
	(method (doit)
		(cond 
			((ego inRect: 11 122 18 126)
				(Print 1 0)
				(ego setMotion: MoveTo 38 124)
			)
			((and (< 188 (ego y?)) (>= 91 (ego x?)))
				(ego x: 91)
			)
			((and (< 189 (ego y?)) (<= 235 (ego x?)))
				(ego x: 235)
			)
			((== (officeDoor doorState?) 2)
				(curRoom newRoom: 2)
			)
			(
				(and
					(== captainWarningTimer 450)
					(== gamePhase 0)
					(not (ego has: iKeyRing))
				)
					(Print 1 1)
					(= captainWarningTimer 700)
			)
		)
		(cond 
			(
				(and
					(<= 220 (ego y?))
					(!= walkingOffGrounds 1)
					(!= walkingOffGrounds 2)
				)
					(= walkingOffGrounds 1)
					(Print 1 2)
			)
			((and (>= 210 (ego y?)) (== walkingOffGrounds 1)) (= walkingOffGrounds 0)
				(Print 1 3)
			)
			((and (<= 255 (ego y?)) (!= walkingOffGrounds 2))
				(= walkingOffGrounds 2)
				(Print 1 4)
				(= printObj (Print 1 5 #dispose))
				(ShakeScreen 15)
				(cls)
				(ego dispose:)
				(Print 1 6)
				(EgoDead 1 7)
			)
			(
			(and (== global132 carPullingIn) (== carPullingIn TRUE))
				(if (not (cast contains: ourCar))
					(= global132 0)
					(self setScript: exitScript)
				)
			)
			(
				(and
					(== global132 (not carPullingIn))
					(== (not carPullingIn) 1)
				)
					(= global132 0)
					(EnterCar)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					(
						(or
							(Said 'remove/cloth')
							(Said 'get<[off]/cloth<[off]')
							(Said 'undress')
							(Said 'get/naked')
							(Said 'piss')
							(Said 'take/piss')
						)
						(Print 1 8)
					)
					((Said 'drive')
						(if carPullingIn
							(Print 1 9)
						else
							(Print 1 10)
						)
					)
					((Said 'deposit,place/briefcase')
						(if (ego inRect: 151 129 182 148)
							(if workCarTrunkOpened
								(if (ego has: iFieldKit)
									(Print 1 11)
									(PutInRoom iFieldKit carWork)
									(if (IsObject theFieldKit)
										(theFieldKit dispose:)
									)
									(= fieldKitOpen FALSE)
								else
									(Print 1 12)
								)
							else
								(Print 1 13)
							)
						else
							(Print 1 14)
						)
					)
					((Said 'remove,get/briefcase')
						(if (ego inRect: 151 129 182 148)
							(if workCarTrunkOpened
								(if (== ((inventory at: iFieldKit) owner?) carWork)
									(Print 1 15)
									(ego get: iFieldKit)
								else
									(Print 1 16)
								)
							else
								(Print 1 13)
							)
						else
							(Print 1 14)
						)
					)
					((Said 'enter[/auto]')
						(EnterCar)
					)
					((or (Said 'exit[/auto]') (Said 'get<out'))
						(if (== carPullingIn TRUE)
							(= global132 1)
						else
							(Print 1 17)
						)
					)
					(
					(or (Said 'enter,open[/door]') (Said 'get<in'))
						(cond 
							(carPullingIn
								(= global132 1)
							)
							((ego inRect: 126 102 150 106)
								(if (not (ego has: iKeyRing))
									(Print 1 18)
								else
									(Print 1 19)
									(officeDoor locked: FALSE)
								)
							)
							((ego inRect: 93 144 114 154)
								(if (not workCarLocked)
									(EnterCar)
								else
									(Print 1 20)
								)
							)
							((ego inRect: 259 140 279 148)
								(if (not personalCarLocked)
									(EnterCar)
								else
									(Print 1 20)
								)
							)
							((> (ego y?) 120)
								(if
									(or
										(ego inRect: 73 149 128 188)
										(ego inRect: 205 154 313 185)
									)
									(Print 1 21)
								else
									(Print 1 22)
								)
							)
							(else
								(Print 1 23)
							)
						)
					)
					((or (Said 'unlock[/door,auto]') (Said 'use/key'))
						(cond 
							((ego inRect: 126 102 150 106)
								(if (not (ego has: iKeyRing))
									(Print 1 24)
								else
									(Print 1 19 #at -1 45)
									(officeDoor locked: FALSE)
								)
							)
							((ego inRect: 93 144 114 154)
								(if (ego has: iUnmarkedCarKeys)
									(if (== workCarLocked TRUE)
										(= workCarLocked FALSE)
										(Print 1 25)
									else
										(Print 1 26)
									)
								else
									(Print 1 24)
								)
							)
							((ego inRect: 259 140 279 148)
								(if (ego has: iKeyRing)
									(if (== personalCarLocked TRUE)
										(= personalCarLocked FALSE)
										(Print 1 25)
									else
										(Print 1 26)
									)
								else
									(Print 1 24)
								)
							)
							((ego inRect: 75 112 288 134)
								(Print 1 27)
							)
							(
								(or
									(and
										(ego inRect: 73 149 128 188)
										(or
											(Btst fDocBookingEvidence)
											randCarExists
										)
									)
									(and
										(ego inRect: 205 154 313 185)
										nearUnmarkedCar
									)
								)
								(Print 1 28)
							)
							((> (ego y?) 120)
								(Print 1 29)
							)
							(else
								(Print 1 23)
							)
						)
					)
					((Said 'lock[/door,auto]')
						(cond 
							((ego inRect: 126 102 150 106)
								(Print 1 30)
							)
							((ego inRect: 93 144 114 154)
								(if (== workCarLocked FALSE)
									(= workCarLocked TRUE)
									(Print 1 31)
								else
									(Print 1 32)
								)
							)
							((ego inRect: 259 140 279 148)
								(if (== personalCarLocked FALSE)
									(if (ego has: iKeyRing)
										(= personalCarLocked TRUE)
										(Print 1 31)
									else
										(Print 1 24)
									)
								else
									(Print 1 32)
								)
							)
							(else
								(NotClose)
							)
						)
					)
					((Said 'open,unlock/trunk')
						(cond 
							((ego inRect: 151 129 182 148)
								(cond 
									(workCarTrunkOpened (Print 1 33))
									((ego has: iUnmarkedCarKeys)
										(= workCarTrunkOpened TRUE)
										(unTrunk
											view: 51
											loop: 4
											cel: 0
											posn: 148 148
											setPri: 10
											ignoreActors:
											init:
											setCycle: EndLoop
										)
									)
									(else
										(Print 1 24)
									)
								)
							)
							((ego inRect: 264 120 320 145)
								(Print 1 34)
							)
							(else
								(Print 1 35)
							)
						)
					)
					((Said 'close,lock/trunk')
						(cond 
							((ego inRect: 151 129 182 148)
								(if workCarTrunkOpened
									(self setScript: trunkScript)
									(= workCarTrunkOpened FALSE)
								else
									(Print 1 36)
								)
							)
							((ego inRect: 264 120 320 145)
								(Print 1 34)
							)
							(else
								(Print 1 37)
							)
						)
					)
					((Said 'open/hood')
						(if (!= isOnDuty 1)
							(Print 1 38)
						else
							(Print 1 39)
						)
					)
					((Said 'look,read/sign,letter,word')
						(Print 1 40)
					)
					((Said 'look,frisk>')
						(cond 
							((and (not global160) (Said '/wrist,clock'))
								(Print 1 41)
							)
							((Said '/tire,wheel')
								(Print 1 42)
							)
							((Said '/auto<unmarked')
								(Print 1 43)
							)
							((Said '/auto<blue')
								(Print 1 44)
							)
							((Said '/auto,cruiser<red')
								(if nearUnmarkedCar
									(Print 1 45)
								)
							)
							((Said '/auto,cruiser<patrol,police,marked')
								(if (Btst 10)
									(Print 1 46)
								else
									(Print 1 47)
								)
							)
							((Said '<in/auto')
								(Print 1 48)
							)
							((or (Said '/lot') (Said '/auto'))
								(Print 1 49)
							)
							((Said '/trunk<tree')
								(Print 1 50)
								)
							((Said '/trunk')
								(if
									(and
										(ego inRect: 151 129 182 148)
										(cast contains: unTrunk)
									)
									(inventory
										carrying: {The car's trunk contains:}
										empty: {The car's trunk is empty.}
										showSelf: 13
									)
								else
									(Print 1 51)
								)
							)
							((Said '[<at,down][/dirt]')
								(Print 1 52)
							)
							((Said '[<at,up][/air,cloud]')
								(Print 1 53)
							)
							((Said '/ave')
								(Print 1 54)
							)
							((Said '/sidewalk')
								(if (< (ego y?) 142)
									(Print 1 55)
								else
									(NotClose)
								)
							)
							((Said '/stair')
								(if (< (ego y?) 142)
									(Print 1 56)
								else
									(NotClose)
								)
							)
							((Said '/tree')
								(Print 1 57)
								(Print 1 58)
							)
							((Said '/bush')
								(Print 1 59)
							)
							((Said '/fence')
								(Print 1 60)
							)
							((Said '/flag')
								(Print 1 61)
							)
							((Said '/pane')
								(if (> 128 (ego y?))
									(ego loop: 3 setMotion: 0)
									(Print 1 62 #draw)
									(Print 1 63)
								else
									(Print 1 64)
								)
							)
							((Said '/door')
								(if (ego inRect: 126 102 150 106)
									(Print 1 65)
								else
									(Print 1 66)
								)
							)
							((Said '/mirror')
								(Print 1 67)
							)
							((Said '[<around,at][/(noword,area,chamber,building,barn)]')
								(Print 1 68)
							)
						)
					)
					((Said 'salute/flag')
						(Print 1 69)
					)
					((Said '/hello[/noword]')
						(if (> (keith x?) 1)
							(Print 1 70)
						else
							(Print 1 71)
						)
					)
					((Said 'climb/auto')
						(Print 1 72)
					)
					((Said 'climb')
						(Print 1 73)
					)
					((Said 'sat')
						(Print 1 73)
					)
					((Said 'open/pane')
						(if (and (> 128 (ego y?)) (> (ego y?) 122))
							(ego loop: 3 setMotion: 0)
							(Print 1 74 #draw)
						else
							(NotClose)
						)
					)
				)
			)
		)
	)
)

(instance trunkScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(unTrunk
					view: 51
					loop: 4
					cel: 2
					posn: 148 148
					setPri: 10
					ignoreActors:
					setCycle: CycleTo 0 -1 self
				)
			)
			(1
				(unTrunk dispose:)
			)
		)
	)
)

(instance exitScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (== currentCar carWork) (!= prevRoomNum 2))
					(keith
						setStep: 1 3
						setPri: 7
						posn: 119 142
						setLoop: 1
						setCel: 1
						illegalBits: 0
						ignoreActors: TRUE
						startUpd:
						setMotion: MoveTo 122 134
					)
				)
				(ego setPri: -1)
				(carDoor
					view: 51
					loop: (if (== currentCar carWork) 2 else 0)
					cel: 0
					ignoreActors:
					init:
					setPri: 10
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				(if (== currentCar carWork)
					(carDoor posn: 93 139)
				else
					(carDoor posn: 261 137)
				)
			)
			(1
				(ego
					posn:
						(if (== currentCar carWork) 104 else 272)
						(if (== currentCar carWork) 145 else 142)
					loop: 1
					cel: 1
					setPri: -1
				)
				(= carPullingIn FALSE)
				(if (== currentCar carWork)
					(= workCarLocked FALSE)
				else
					(= personalCarLocked FALSE)
				)
				(if (== currentCar carWork)
					(keith
						setStep: 3 1
						setCycle: Walk
						setLoop: -1
						setPri: -1
						ignoreActors: 0
						illegalBits: cWHITE
					)
				)
				(carDoor dispose:)
				(if (== currentCar carPersonal)
					(HandsOn)
				else
					(rm1 setScript: awayScript)
				)
			)
		)
	)
)

(instance enterScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego stopUpd:)
				(if (== currentCar carWork)
					(if (< (keith y?) 90)
						(Print 1 75 #at -1 35)
						(keith ignoreActors: illegalBits: 0 posn: 131 106)
						(officeDoor startUpd: setCycle: BegLoop)
					)
					(keith setMotion: MoveTo 131 130 self)
				else
					(self changeState: 2)
				)
			)
			(1
				(keith setMotion: MoveTo 121 134 self)
			)
			(2
				(officeDoor stopUpd:)
				(ego setPri: 11)
				(carDoor
					view: 51
					loop: (if (== currentCar carWork) 2 else 0)
					cel: 0
					ignoreActors:
					init:
					setPri: 10
					setCycle: EndLoop self
				)
				(if (== currentCar carWork)
					(carDoor posn: 93 139)
				else
					(carDoor posn: 261 137)
				)
			)
			(3
				(if (== currentCar carWork)
					(keith
						ignoreActors: TRUE
						illegalBits: 0
						setPri: 8
						setLoop: 1
						setCycle: 0
						setStep: 1 2
						setMotion: MoveTo 118 142 self
					)
				else
					(self cue:)
				)
			)
			(4
				(if (and (== gamePhase 6) (== isOnDuty 1))
					(= dateState dateNOCALL)
					(PutInRoom iKeyRing 0)
					(curRoom newRoom: 105)
				else
					(curRoom newRoom: currentCar)
				)
			)
		)
	)
)

(instance driveUpScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 0 0)
				(User canControl: FALSE)
				(if (== currentCar carWork)
					(= workCarTrunkOpened FALSE)
				)
				(ourCar setMotion: MoveTo carX carY self)
			)
			(1
				(= global132 1)
				(User canControl: TRUE)
				(if global160
					(ourCar addToPic:)
					(ego observeBlocks: car1Block car4Block)
					(if local6
						(= global132 1)
					)
				else
					(= global160 1)
					(curRoom newRoom: 33)
				)
			)
		)
	)
)

(instance awayScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(User canControl: TRUE)
				(Print 1 76 #draw #at -1 30)
				(keith setMotion: MoveTo 131 110 self)
			)
			(1
				(officeDoor startUpd: setCycle: EndLoop self)
				(keith
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 131 108
				)
			)
			(2
				(officeDoor setCycle: BegLoop self)
				(keith posn: 1000 0 stopUpd:)
			)
			(3
				(HandsOn)
				(officeDoor stopUpd:)
			)
		)
	)
)
