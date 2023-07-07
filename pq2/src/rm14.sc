;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include system.sh)
(include keys.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm14 0
)

(local
	local0
	local1
	local2
	local3
	[local4 4] ;sonny's car rect
	local8
	nextRoom
	local10
	[local11 2]
	local13
)
(procedure (LocPrint)
	(Print &rest #at -1 15)
)

(procedure (localproc_19fc)
	(cond 
		(
			(and (!= global169 0) (ego inRect: 240 180 287 192))
				(if (>= global169 2)
					(LocPrint 14 63)
					(= perspective 0)
					(curRoom newRoom: 104)
				else
					(Print 14 73)
				)
			)
		(
		(and
			(!= global169 0)
			(ego inRect: 223 156 291 190)
		)
			(if (>= global169 2)
				(LocPrint 14 64)
			else
				(Print 14 73)
			)
		)
		(else
			(switch currentCar
				(13
					(cond 
						(
							(not
								(ego inRect: [local4 0] [local4 1] [local4 2] [local4 3])
							)
							(LocPrint 14 74)
						)
						(workCarLocked
							(LocPrint 14 75)
						)
						(workCarTrunkOpened
							(LocPrint 14 76)
						)
						((ego has: 10)
							(LocPrint 14 77)
						)
						(else
							(= currentCar carWork)
							(carScript changeState: 4)
						)
					)
				)
				(33
					(cond 
						(
							(not
								(ego inRect: [local4 0] [local4 1] [local4 2] [local4 3])
							)
							(LocPrint 14 74)
						)
						((== personalCarLocked 1)
							(LocPrint 14 75)
						)
						(else
							(carScript changeState: 4)
						)
					)
				)
			)
		)
	)
)

(instance ourCarBlock of Block
	(properties
		top 133
		left 162
		bottom 143
		right 256
	)
)

(instance nose of Actor
	(properties)
)

(instance tail of Actor
	(properties)
)

(instance plane of Actor
	(properties)
)

(instance lax of View
	(properties)
)

(instance door of View
	(properties)
)

(instance SIGN of View
	(properties)
)

(instance bench of View
	(properties)
)

(instance smallGuy4 of View
	(properties)
)

(instance smallGuy5 of View
	(properties)
)

(instance windSock of Prop
	(properties)
)

(instance littleCarParked of View
	(properties)
)

(instance smallGuy1 of Actor
	(properties)
)

(instance smallGuy2 of Actor
	(properties)
)

(instance smallGuy3 of Actor
	(properties)
)

(instance ourCar of Actor
	(properties)
)

(instance carDoor of Prop
	(properties)
)

(instance unTrunk of Prop
	(properties)
)

(instance rm14 of Room
	(properties
		picture 14
		style VSHUTTER
	)
	
	(method (init)
		(super init:)
		(= perspective 70)
		(Load VIEW 0)
		(Load VIEW 20)
		(Load VIEW 74)
		(Load VIEW 75)
		(Load VIEW 54)
		(Load VIEW 51)
		(= gunNotNeeded TRUE)
		(= gunFireState gunPROHIBITED)
		(self setLocales: 153)
		(= local0 (or (== prevRoomNum 33) (== prevRoomNum 13)))
		(if (== prevRoomNum 33)
			(Bclr 40)
		)
		(= local10
			(if
				(and
					(> global169 0)
					(!= prevRoomNum 13)
					(!= prevRoomNum 104)
					(== currentCar 13)
				)
				(Btst 118)
			)
		)
		(= local13 global169)
		(if (or (>= gamePhase 8) local10)
			(= global169 0)
		)
		(if local0
			(HandsOff)
		else
			(HandsOn)
		)
		(if (!= global169 0)
			((View new:)
				view: 94
				loop: 1
				cel: 0
				posn: 248 186
				init:
				stopUpd:
				addToPic:
			)
		)
		(if local0
			(= workCarTrunkOpened 0)
		else
			(ego observeBlocks: ourCarBlock)
			(if (and workCarTrunkOpened (== currentCar 13))
				(unTrunk view: 51 loop: 5 cel: 2 setPri: 10 posn: 178 144 init:)
			)
		)
		(windSock
			view: 74
			setLoop: 7
			posn: 174 43
			cycleSpeed: 3
			setCycle: Forward
			setPri: 6
			init:
			setScript: backScript
		)
		(if (and (or (== gamePhase 6) (== gamePhase 7)) (!= prevRoomNum 15))
			(Bclr 40)
			(= global111 1)
		else
			(= global111 0)
		)
		(= [local4 1] 128)
		(= [local4 3] 136)
		(switch currentCar
			(33
				(ourCarBlock left: 180)
				(= [local4 0] 176)
				(= [local4 2] 213)
			)
			(13
				(= [local4 0] 200)
				(= [local4 2] 215)
			)
		)
		(= local8 340)
		(= local2 198)
		(= local3 216)
		(= nextRoom 15)
		((View new:)
			view: 75
			loop: 1
			cel: 2
			posn: 32 141
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 54
			loop: 0
			cel: 4
			posn: 14 186
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 75
			loop: 2
			cel: 0
			posn: 233 162
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 54
			loop: 1
			cel: 2
			posn: 308 126
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 75
			loop: 2
			cel: 1
			posn: 323 141
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 75
			loop: 2
			cel: 2
			posn: 341 162
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 54
			loop: 0
			cel: 0
			posn: 357 184
			init:
			stopUpd:
			addToPic:
		)
		(= local1
			(if (== roomCarParked curRoomNum)
				(or (== prevRoomNum 33) (== prevRoomNum 13))
			)
		)
		(if local0
			(= global132 1)
		)
		(ourCar
			view: 54
			setStep: 2
			setLoop: 1
			setCel: (if (== currentCar 13) 1 else 5)
			setCycle: 0
			posn:
				(if (and (or (== prevRoomNum 13) (== prevRoomNum 33)) (not local1))
					local2
				else
					local3
				)
				140
			illegalBits: 0
			ignoreActors:
			init:
		)
		(self setScript: rm14Script)
	)

	
	(method (dispose)
		(ourCarBlock dispose:)
		(carScript dispose:)
		(super dispose:)
	)
)

(instance rm14Script of Script
	(properties)
	
	(method (doit)
		(cond 
			((<= (ego y?) 116)
				(if
					(and
						(== currentCar carWork)
						(> (keith x?) 8)
					)
					(switch global169
						(1
							(LocPrint 14 0)
						)
						(2
							(if (not (Btst fFoundWomanStolenCar))
								(LocPrint 14 1)
								(Bset fFoundWomanStolenCar)
							)
						)
					)
				)
				(cond 
					((< (ego x?) 58)
						(= gGEgoX -10)
					)
					((> (ego x?) 161)
						(= gGEgoX 327)
					)
					(else
						(= gGEgoX (ego x?))
					)
				)
				(= perspective 0)
				(curRoom newRoom: nextRoom)
			)
			(
				(and
					(== global132 local0)
					(== local0 1)
					(not (cast contains: ourCar))
				)
				(= global132 0)
				(carScript changeState: 0)
			)
			(
				(and
					(== global132 (not local0))
					(== (not local0) 1)
				)
				(= global132 0)
				(localproc_19fc)
			)
			(
				(and
					(or (< (ego x?) -12) (> (ego x?) 332))
					(not local0)
				)
				(if
				(and
					(cast contains: keith)
					(< (keith x?) 10)
				)
					(LocPrint 14 2)
				else
					(LocPrint 14 3)
				)
				(ego setMotion: MoveTo 125 (ego y?))
			)
			(
				(and
					(> (ego y?) 240)
					(not local0)
				)
				(switch (Random 0 3)
					(0
						(LocPrint 14 4)
					)
					(1
						(LocPrint 14 5)
					)
					(2
						(LocPrint 14 6)
					)
					(3
						(LocPrint 14 7)
					)
				)
				(ego setMotion: MoveTo (ego x?) 150)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: (if gunDrawn 6 else 0) init: setMotion: 0)
				(switch prevRoomNum
					(104
						(ego posn: 260 188 loop: 3)
						(= global169 2)
					)
					(15
						(ego posn: (+ (/ gGEgoX 3) 57) 124)
					)
					(13
						(ego posn: local8 239)
					)
					(33
						(ego posn: local8 239)
					)
				)
				(if (== currentCar carWork)
					((= keith (Actor new:))
						view: 20
						posn: (- 38 (ego x?)) (ego y?)
						setCycle: Walk
						observeBlocks: ourCarBlock
						init:
					)
					(switch prevRoomNum
						(104
							(keith
								posn: 224 190
								setMotion: Follow ego 34
								setAvoider: (Avoider new:)
							)
						)
						(nextRoom
							(cond 
								((Btst fKeithFollows)
									(keith
										posn: (- (ego x?) 32) (- (ego y?) 8)
										loop: 2
										setMotion: Follow ego 500
										setAvoider: (Avoider new:)
									)
								)
								(
									(and
										(not (Btst fStolenCarTowed))
										(== global169 1)
									)
									(keith posn: -14 128)
								)
								(else
									(keith
										posn: 153 142
										loop: 3
										startUpd:
										setMotion: Follow ego 500
									)
								)
							)
						)
					)
				)
				(= roomCarParked curRoomNum)
				(ourCar setMotion: MoveTo local3 140 self)
			)
			(1
				(ourCar ignoreActors: 0 addToPic:)
				(= cycles 1)
			)
			(2
				(if (== currentCar carWork)
					(switch prevRoomNum
						(104
							(LocPrint 14 8 83)
						)
						(nextRoom
							(if (Btst fKeithFollows)
								(LocPrint 14 9 83)
							else
								(switch local13
									(0
										(LocPrint 14 10 83)
									)
									(1
										(if local10
											(LocPrint 14 11 83)
											(LocPrint 14 12)
										else
											(LocPrint 14 13)
										)
									)
									(2
										(if (not (Btst fStolenCarTowed))
											(LocPrint 14 14)
										else
											(LocPrint 14 15)
										)
									)
									(3
										(if (Btst fStolenCarTowed)
											(LocPrint 14 16 83)
										else
											(LocPrint 14 14)
										)
									)
									(else 
										(LocPrint 14 17 83)
									)
								)
							)
						)
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'close/door')
						(LocPrint 14 18)
					)
					(
						(or
							(Said 'enter/auto')
							(Said 'open/auto,door')
							(Said 'get,look<in,in[/auto]')
						)
						(localproc_19fc)
					)
					(
						(or
							(Said 'look>')
							(Said 'check[<out]>')
						)
						(cond 
							((Said '/trunk')
								(if
									(and
										(ego inRect: 146 132 172 142)
										(cast contains: unTrunk)
									)
									(inventory
										carrying: {The car's trunk contains_}
										empty: {The car's trunk is empty.}
										showSelf: 13
									)
								else
									(LocPrint 14 19)
								)
							)
							((Said '/building,airport,terminal')
								(LocPrint 14 20)
							)
							((Said '/entrance')
								(LocPrint 14 21)
							)
							((Said '/lot')
								(LocPrint 14 22)
							)
							((Said '[<at,around][/!*,chamber]')
								(LocPrint 14 23)
							)
							((Said '/tower')
								(LocPrint 14 24)
							)
							((Said '/sock,wind')
								(LocPrint 14 25)
							)
							((Said '/airplane[<air]')
								(LocPrint 14 26)
							)
							(
								(or
									(Said '/air')
									(Said '<up')
								)
								(LocPrint 14 27)
							)
							((Said '/cloud')
								(LocPrint 14 28)
							)
							((Said '/dude,broad,men,women,crowd')
								(LocPrint 14 29)
							)
							((Said '/vin,badge')
								(LocPrint 14 30)
							)
							((Said '<below/auto')
								(LocPrint 14 31)
							)
							(
								(or
									(Said '/dirt')
									(Said '<down,ave,bridge')
								)
								(LocPrint 14 32)
							)
							((Said '/auto')
								(if (!= global169 0)
									(cond 
										((not (Btst fReadJailerVIN))
											(cond 
												((ego inRect: 130 163 315 200)
													(LocPrint 14 33)
													(= global169 2)
												)
												((== global169 2)
													(LocPrint 14 34)
												)
												(else
													(LocPrint 14 35)
												)
											)
										)
										((ego inRect: 130 163 315 200)
											(LocPrint 14 36)
										)
										(else
											(LocPrint 14 34)
										)
									)
								else
									(LocPrint 14 37)
								)
							)
							((Said '/license')
								(if (!= global169 0)
									(cond 
										((ego inRect: 162 163 208 189)
											(LocPrint 14 38)
										)
										((ego inRect: 295 159 322 190)
											(LocPrint 14 39)
											(= global169 2)
											(SolvePuzzle 1 fReadJailerVIN)
											(if
												(and
													(== currentCar carWork)
													(cast contains: keith)
													(< (keith x?) 50)
												)
												(keith
													posn: 5 164
													setMotion: Follow ego 36
													setAvoider: (Avoider new:)
												)
												(LocPrint 14 40 83)
											)
										)
										(else (LocPrint 14 41))
									)
								else
									(LocPrint 14 42)
								)
							)
						)
					)
					((Said 'drive')
						(if local0
							(LocPrint 14 43)
						else
							(LocPrint 14 44)
						)
					)
					((Said 'deposit,place/briefcase')
						(if (ego inRect: 146 132 172 142)
							(if workCarTrunkOpened
								(if (ego has: iFieldKit)
									(LocPrint 14 45)
									(PutInRoom iFieldKit 13)
									(if (IsObject theFieldKit) (theFieldKit dispose:))
									(= fieldKitOpen 0)
								else
									(LocPrint 14 46)
								)
							else
								(LocPrint 14 47)
							)
						else
							(LocPrint 14 48)
						)
					)
					((Said 'remove,get/briefcase')
						(if (ego inRect: 146 132 172 142)
							(if workCarTrunkOpened
								(if (== ((inventory at: iFieldKit) owner?) 13)
									(LocPrint 14 49)
									(ego get: iFieldKit)
								else
									(LocPrint 14 50)
								)
							else
								(LocPrint 14 47)
							)
						else
							(LocPrint 14 48)
						)
					)
					(
					(or (Said 'unlock/door,auto') (Said 'unlock/door<auto'))
						(if (ego inRect: [local4 0] [local4 1] [local4 2] [local4 3])
							(cond 
								((and (== currentCar carWork) (ego has: 3))
									(if workCarLocked
										(= workCarLocked 0)
										(LocPrint 14 51)
									else
										(LocPrint 14 52)
									)
								)
								((== currentCar carWork) (LocPrint 14 53))
							)
							(cond 
								((and (== currentCar carPersonal) (ego has: 2))
									(if personalCarLocked
										(= personalCarLocked 0)
										(LocPrint 14 51)
									else
										(LocPrint 14 52)
									)
								)
								((== currentCar carPersonal) (LocPrint 14 53))
							)
						else
							(LocPrint 14 54)
						)
					)
					(
					(or (Said 'lock/door,auto') (Said 'lock/door<auto'))
						(if
						(ego inRect: [local4 0] [local4 1] [local4 2] [local4 3])
							(if (== currentCar carWork)
								(cond 
									((not workCarLocked)
										(= workCarLocked 1)
										(LocPrint 14 55)
									)
									((ego inRect: 223 156 291 190)
										(LocPrint 14 56)
									)
									(else
										(LocPrint 14 57)
									)
								)
							)
							(if (== currentCar 33)
								(if (not personalCarLocked)
									(= personalCarLocked 1)
									(LocPrint 14 55)
								else
									(LocPrint 14 57)
								)
							)
						else
							(LocPrint 14 54)
						)
					)
					((Said 'open,unlock/trunk')
						(if (== currentCar 13)
							(if (ego inRect: 146 132 172 142)
								(if (ego has: iUnmarkedCarKeys)
									(if workCarTrunkOpened
										(Print 14 58)
									else
										(carScript changeState: 10)
									)
								else
									(LocPrint 14 59)
								)
							else
								(LocPrint 14 60)
							)
						else
							(LocPrint 14 61)
						)
					)
					((Said 'close,lock/trunk')
						(if (== currentCar 13)
							(if (ego inRect: 146 132 172 142)
								(if workCarTrunkOpened
									(carScript changeState: 12)
								else
									(Print 14 62)
								)
							else
								(NotClose)
							)
						else
							(LocPrint 14 61)
						)
					)
					(
					(or (Said 'frisk/auto') (Said 'check[<out]/auto'))
						(cond 
							(
								(and
									(!= global169 0)
									(ego inRect: 240 180 287 192)
								)
								(LocPrint 14 63 25 4)
								(= perspective 0)
								(curRoom newRoom: 104)
							)
							(
							(and
								(!= global169 0)
								(ego inRect: 223 156 291 190))
								(LocPrint 14 64)
							)
							((ego inRect: [local4 0] [local4 1] [local4 2] [local4 3])
								(LocPrint 14 65)
							)
							(else
								(LocPrint 14 66)
							)
						)
					)
				)
			)
		)
	)
)

(instance carScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setStep: 1 2
					posn: 206 142
					setPri: 9
					setLoop: 0
					setCycle: 0
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo 203 132 self
				)
				(if (== currentCar 13)
					(keith posn: 216 149 loop: 0 cel: 5 startUpd:)
					(carDoor
						view: 51
						loop: 3
						cel: 0
						posn: 230 135
						setPri: 10
						init:
						setCycle: EndLoop
					)
				)
			)
			(1
				(= local0 0)
				(if (== currentCar 13)
					(= workCarLocked 0)
				else
					(= personalCarLocked 0)
				)
				(ego
					setStep: 3 2
					setCycle: Walk
					setLoop: -1
					setPri: -1
					ignoreActors: 0
					illegalBits: -32768
					observeBlocks: ourCarBlock
				)
				(self cue:)
			)
			(2
				(HandsOn)
				(if (== currentCar 13)
					(carDoor dispose:)
					(if (== global169 1)
						(keith setMotion: MoveTo -14 149)
						(LocPrint 14 67 25 7)
						(LocPrint 14 68 83 25 5)
					else
						(keith setMotion: MoveTo 160 146 self)
					)
				)
			)
			(3
				(keith cel: 0)
				(cond 
					((and local1 (== global169 3)) (LocPrint 14 69))
					((== gamePhase 13) (LocPrint 14 70))
					((not local1) (LocPrint 14 71))
				)
			)
			(4
				(HandsOff)
				(if (== currentCar 13)
					(LocPrint 14 72)
					(if (< (keith y?) 134)
						(keith
							setAvoider: 0
							ignoreActors:
							illegalBits: 0
							setMotion: MoveTo 145 128 self
						)
					else
						(self cue:)
					)
				else
					(self changeState: 8)
				)
			)
			(5
				(keith
					setMotion: MoveTo (if (< (keith x?) 145) 145 else (keith x?)) 145 self
				)
			)
			(6
				(keith setMotion: MoveTo 216 146 self)
			)
			(7
				(carDoor
					view: 51
					loop: 3
					cel: 0
					posn: 230 135
					setPri: 10
					init:
					setCycle: EndLoop self
				)
			)
			(8
				(ego
					ignoreActors: 1
					illegalBits: 0
					ignoreBlocks: ourCarBlock
					setPri: 9
					setLoop: 0
					setCycle: 0
					setStep: 1 2
					setMotion: MoveTo 206 144 self
				)
			)
			(9
				(= perspective 0)
				(curRoom newRoom: currentCar)
			)
			(10
				(= workCarTrunkOpened 1)
				(unTrunk
					view: 51
					loop: 5
					cel: 0
					posn: 178 144
					setPri: 10
					init:
					setCycle: EndLoop self
				)
			)
			(11
				(unTrunk stopUpd:)
			)
			(12
				(= workCarTrunkOpened 0)
				(unTrunk
					view: 51
					loop: 5
					cel: 2
					posn: 178 144
					startUpd:
					setCycle: CycleTo 0 -1 self
				)
			)
			(13 (unTrunk dispose:))
		)
	)
)

(instance backScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(lax
					view: 75
					loop: 0
					cel: 0
					posn: 98 69
					setPri: 6
					init:
					addToPic:
				)
				(door
					view: 75
					loop: 3
					posn: 99 96
					init:
					addToPic:
				)
				(SIGN
					view: 74
					loop: 0
					cel: 5
					posn: 140 98
					setPri: 7
					init:
					addToPic:
				)
				(bench
					view: 74
					loop: 0
					cel: 4
					posn: 127 98
					setPri: 7
					init:
					addToPic:
				)
				(littleCarParked
					view: 75
					loop: 4
					cel: 0
					posn: 63 104
					setPri: 7
					init:
					addToPic:
				)
				(smallGuy4
					view: 74
					loop: 8
					cel: 0
					posn: 132 98
					setPri: 8
					init:
					stopUpd:
					addToPic:
				)
				(smallGuy5
					view: 74
					loop: 8
					cel: 1
					posn: 125 98
					setPri: 8
					init:
					stopUpd:
					addToPic:
				)
				(smallGuy1
					view: 74
					posn: 210 98
					setLoop: 1
					setStep: 1
					setCycle: Walk
					setMotion: MoveTo 324 98
					ignoreActors:
					illegalBits: 0
					init:
				)
				(smallGuy3
					view: 74
					posn: 40 96
					setLoop: 5
					cel: 3
					setStep: 1
					setCycle: Walk
					ignoreActors:
					illegalBits: 0
					init:
					stopUpd:
				)
				(if (< howFast 30)
					(smallGuy1 stopUpd:)
					(smallGuy2 stopUpd:)
					(smallGuy3 stopUpd:)
					(windSock stopUpd:)
					(client setScript: 0)
					(return)
				)
				(plane
					view: 74
					posn: 226 36
					setLoop: 0
					setCel: 0
					setPri: 1
					init:
					setCycle: 0
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 330 40 self
				)
			)
			(1
				(plane stopUpd:)
				(smallGuy1
					setLoop: 2
					setMotion: MoveTo 180 99
				)
				(smallGuy2
					view: 74
					posn: 324 99
					setLoop: 4
					xStep: 1
					setCycle: Walk
					setMotion: MoveTo 185 100
					ignoreActors:
					illegalBits: 0
					init:
				)
				(= cycles 80)
			)
			(2
				(plane
					setCel: 1
					posn: 335 20
					setMotion: MoveTo 90 70 self
					init:
					startUpd:
				)
				(smallGuy1 stopUpd:)
			)
			(3
				(= cycles 80)
				(smallGuy1 startUpd:)
				(plane stopUpd:)
			)
			(4
				(nose
					view: 74
					posn: 160 77
					setLoop: 0
					setCel: 3
					setPri: 4
					illegalBits: 0
					ignoreActors:
					init:
					setCycle: 0
					setMotion: MoveTo 200 77
				)
				(tail
					view: 74
					setLoop: 0
					setCel: 2
					posn: -25 47
					setPri: 1
					illegalBits: 0
					ignoreActors:
					init:
					setCycle: 0
					setMotion: MoveTo 15 47 self
				)
			)
			(5
				(tail addToPic:)
				(nose addToPic:)
				(smallGuy3
					startUpd:
					setMotion: MoveTo 168 96
					init:
				)
				(smallGuy1
					startUpd:
					setLoop: 2
					setMotion: MoveTo 175 99
					init:
				)
				(smallGuy2
					startUpd:
					setLoop: 4
					setMotion: MoveTo 182 98
				)
			)
		)
	)
)
