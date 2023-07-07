;;; Sierra Script 1.0 - (do not remove this comment)
(script# 225)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm225 0
)

(local
	local0
	carDoor
	local2
	local3
	local4
	local5
	egoX
	local7
	local8
	local9
	local10
	local11
	local12
	trunk
	[carDoorRect 4]
	[trunkRect 4]
	[carDoorPosn 2]
	[trunkPosn 2]
)
(procedure (EnterCar)
	(return
		(switch currentCar
			(carWork
				(cond 
					(
						(not
							(ego
								inRect: [carDoorRect 0] [carDoorRect 1] [carDoorRect 2] [carDoorRect 3]
							)
						)
						(Print 225 0)
					)
					((== workCarLocked TRUE)
						(Print 225 1)
						(return FALSE)
					)
					(workCarTrunkOpened
						(Print 225 2)
						(return FALSE)
					)
					((ego has: iFieldKit)
						(Print 225 3)
						(return FALSE)
					)
					(else
						(carScript changeState: 4)
						(return TRUE)
					)
				)
			)
			(carPersonal
				(cond 
					(
						(not
							(ego
								inRect: [carDoorRect 0] [carDoorRect 1] [carDoorRect 2] [carDoorRect 3]
							)
						)
						(Print 225 0)
					)
					((== personalCarLocked TRUE)
						(Print 225 1)
					)
					(else
						(carScript changeState: 4)
					)
				)
			)
		)
	)
)

(instance rm225 of Room
	(properties
		picture 25
		style HSHUTTER
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(= local2
			(if (== prevRoomNum carPersonal)
			else
				(== prevRoomNum carWork)
			)
		)
		(= [trunkPosn 0] 142)
		(= [trunkPosn 1] 193)
		(if local2
			(HandsOff)
			(= workCarTrunkOpened FALSE)
		else
			(HandsOn)
			(if workCarTrunkOpened
				((= trunk (Prop new:))
					view: 51
					loop: 5
					cel: 2
					posn: [trunkPosn 0] [trunkPosn 1]
					setPri: 15
					init:
				)
			)
		)
		(Load VIEW 0)
		(Load VIEW 20)
		(Load VIEW 54)
		(Load VIEW 51)
		(Load VIEW 251)
		(Load VIEW 50)
		(Load VIEW 999)
		(Load VIEW 123)
		(Load VIEW 112)
		(if (== currentCar 13)
			(= local11 180)
			(= local12 189)
			(= [carDoorRect 0] 160)
			(= [carDoorRect 1] 140)
			(= [carDoorRect 2] 180)
			(= [carDoorRect 3] 193)
			(= [carDoorPosn 0] 198)
			(= [carDoorPosn 1] 184)
			(= [trunkRect 0] 106)
			(= [trunkRect 1] 174)
			(= [trunkRect 2] 140)
			(= [trunkRect 3] 189)
			(= egoX 161)
		else
			(= local11 192)
			(= local12 189)
			(= [carDoorRect 0] 160)
			(= [carDoorRect 1] 165)
			(= [carDoorRect 2] 185)
			(= [carDoorRect 3] 179)
			(= [carDoorPosn 0] (- local11 1))
			(= [carDoorPosn 1] (- local12 2))
			(= egoX 175)
		)
		(= local9 0)
		(= local4 (- local11 30))
		(= local5 local11)
		(= local7 26)
		(= local10 0)
		(= local3
			(if (== roomCarParked curRoomNum)
				(!= prevRoomNum local7)
			else
				0
			)
		)
		(self setScript: rm225Script)
	)
	
	(method (dispose)
		(carScript dispose:)
		(super dispose:)
	)
)

(instance rm225Script of Script
	(method (doit)
		(cond 
			((and (== global132 local2) (== local2 1))
				(if (not (cast contains: local0))
					(= global132 0)
					(carScript changeState: 0)
				)
			)
			((and (== global132 (not local2)) (== (not local2) 1))
				(= global132 0)
				(EnterCar)
			)
		)
		(if
			(or
				(ego inRect: 152 77 176 109)
				(ego inRect: 159 109 184 140)
			)
			(ego setPri: 10 illegalBits: cLMAGENTA)
			(if (== currentCar carWork)
				(keith stopUpd:)
			)
		else
			(if (< (ego y?) 83)
				(ego setPri: 7)
				(if (== currentCar carWork)
					(keith stopUpd:)
				)
			else
				(ego setPri: -1)
				(if (== currentCar carWork)
					(keith startUpd:)
				)
			)
			(ego xStep: 3 yStep: 2 illegalBits: cWHITE)
		)
		(if
			(and
				(<= (ego y?) 130)
				(or (< (ego x?) 0) (> (ego x?) 320))
			)
			(ego y: 132)
		)
		(if
			(and
				(== local2 0)
				(or
					(<= (ego x?) -30)
					(>= (ego x?) 380)
					(>= (ego y?) 230)
				)
			)
			(rm225Script changeState: 2)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= local0 (Actor new:))
					view: 54
					setLoop: 1
					setCel: (if (== currentCar 13) 1 else 5)
					setStep: 3 3
					setCycle: 0
					setMotion: 0
					posn:
						(if
							(and
								(or (== prevRoomNum 13) (== prevRoomNum 33))
								(not local3)
							)
							local4
						else
							local5
						)
						local12
					init:
					illegalBits: 0
				)
				((View new:)
					view: 251
					loop: 6
					cel: 0
					setPri: 3
					posn: 104 111
					init:
					addToPic:
					stopUpd:
				)
				((View new:)
					view: 251
					setLoop: 9
					setCel: 5
					setPri: 9
					posn: 261 156
					init:
					addToPic:
				)
				((View new:)
					view: 251
					setLoop: 2
					setCel: 0
					setPri: 7
					posn: 104 73
					init:
					addToPic:
				)
				((View new:)
					view: 251
					setLoop: 0
					setCel: 2
					setPri: 10
					posn: 167 139
					ignoreActors:
					init:
					addToPic:
				)
				((View new:)
					view: 251
					setLoop: 0
					setCel: 0
					setPri: 0
					posn: 67 8
					init:
					ignoreActors:
					addToPic:
				)
				(if (Btst 14)
					((View new:)
						view: 251
						setLoop: 9
						setCel: 0
						posn: 228 128
						setPri: 9
						init:
						ignoreActors:
						addToPic:
					)
				)
				((View new:)
					view: 251
					setLoop: 9
					setCel: 2
					posn: 270 164
					setPri: 12
					init:
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 251
					setLoop: 9
					setCel: 2
					posn: 205 166
					setPri: 11
					init:
					ignoreActors: 0
					addToPic:
				)
				((View new:)
					view: 251
					setLoop: 9
					setCel: 2
					posn: 189 137
					setPri: 9
					init:
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 251
					setLoop: 9
					setCel: 3
					posn: 238 159
					setPri: 12
					init:
					ignoreActors: 0
					addToPic:
				)
				((View new:)
					view: 251
					setLoop: 9
					setCel: 4
					posn: 196 149
					setPri: 10
					init:
					ignoreActors: 0
					addToPic:
				)
				((View new:)
					view: 50
					setLoop: 2
					setCel: 0
					posn: 227 155
					setPri: 10
					init:
					ignoreActors:
					addToPic:
				)
				(ego
					view: 0
					posn:
						(if (== prevRoomNum local7) 240 else 340)
						(if (== prevRoomNum local7) 190 else 300)
					init:
					setMotion: 0
				)
				(if (== currentCar 13)
					((= keith (Actor new:))
						view: 20
						posn: 190 195
						setCycle: Walk
						setAvoider: (Avoider new:)
						illegalBits: cWHITE
					)
				)
				(cond
					((!= roomCarParked curRoomNum)
						(= roomCarParked curRoomNum)
						(= global132 1)
					)
					((== local2 1)
						(= global132 1)
					)
				)
				(if (!= prevRoomNum local7)
					(local0 setMotion: MoveTo local5 local12 self)
				else
					(self cue:)
				)
			)
			(1
				(local0 addToPic: ignoreActors: 0 stopUpd:)
			)
			((and 2 (not local2))
				(Print 225 4) ; "You will need your car in order to leave the area."
				(cond
					((<= (ego x:) 0)
						(ego setMotion: MoveTo 10 (ego y:))
					)
					((>= (ego x:) 320)
						(ego setMotion: MoveTo 300 (ego y:))
					)
					((>= (ego y:) 230)
						(ego setMotion: MoveTo (ego x:) 200)
					)
				)
			)
			(3
				(switch (Random 0 2)
					(0
						(Print 225 5) ; "The motel manager is fuming when he says... "You dudes aren't gonna get away with this! You're gonna pay for that damage!!""
					)
					(1
						(Print 225 6) ; "I'm telling you!" says the manager, "This just hasn't been my year."
					)
					(2
						(Print 225 7) ; "You guys did a bang-up job of ruining my day!" the manager says. "Now how about getting lost?"
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(if (== (ego onControl: origin) cLRED)
					(cond 
						((or (Said '/hello') (Said 'chat[/dude,manager]'))
							(rm225Script changeState: 3)
						)
						((Said 'display[/badge,badge]')
							(if (ego has: 7)
								(Print 225 8 #icon 999 0 0)
								(= showedBadgeToMotelManager TRUE)
							else
								(Print 225 9)
							)
						)
						((Said 'rent/chamber')
							(switch (Random 0 2)
								(0
									(Print 225 10)
								)
								(1
									(Print 225 11)
								)
								(2
									(Print 225 12)
								)
							)
						)
						((Said 'display[/painting,mugshot]')
							(cond 
								((ego has: iNewMugShot)
									(Print 225 13
										#icon 112
									)
									(if (== currentCar carWork)
										(Print 225 14)
									)
								)
								((ego has: iOldMugShot)
									(Print 225 15
										#icon 123
									)
								)
								(else
									(Print 225 16)
								)
							)
						)
						((Said '[ask]/list')
							(Print 225 17)
						)
						((Said '[ask]/bains')
							(Print 225 18)
						)
						((Said '[ask]/bill,cole,cole')
							(Print 225 19)
						)
						((Said '[ask,get]/key')
							(Print 225 20)
						)
						((Said 'arrest/dude')
							(Print 225 21)
						)
					)
				)
				(cond 
					((Said 'look>')
						(cond 
							((Said '[<at,around][/noword,building,chamber]')
								(Print 225 22)
							)
							((Said '/pane')
								(cond 
									((== (ego onControl: origin) cYELLOW)
										(Print 225 23)
									)
									((== (ego onControl: origin) cLRED)
										(Print 225 24)
									)
									(else
										(Print 225 25)
									)
								)
							)
							((Said '/door')
								(cond 
									(
										(or
											(== (ego onControl: origin) cLGREEN)
											(== (ego onControl: origin) cLBLUE)
											(== (ego onControl: origin) cLCYAN)
										)
										(Print 225 26)
									)
									((== (ego onControl: origin) cGREY)
										(Print 225 27)
									)
									(
										(ego
											inRect: [carDoorRect 0] [carDoorRect 1] [carDoorRect 2] [carDoorRect 3]
										)
										(Print 225 26)
									)
									(else
										(Print 225 28)
									)
								)
							)
							((Said '/briefcase')
								(if (ego has: iFieldKit)
									(Print 225 29)
								else
									(Print 225 16)
								)
							)
							((Said '/trunk')
								(if (== currentCar carWork)
									(if
										(and
											(ego
												inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3]
											)
											(cast contains: trunk)
										)
										(inventory
											carrying: {The car's trunk contains:}
											empty: {The car's trunk is empty.}
											showSelf: 13
										)
									else
										(Print 225 30)
									)
								else
									(Print 225 31)
								)
							)
						)
					)
					((or (Said 'break/door') (Said 'beat/door'))
						(if
							(or
								(== (ego onControl: origin) cLGREEN)
								(== (ego onControl: origin) cLBLUE)
								(== (ego onControl: origin) cLCYAN)
							)
							(Print 225 32)
						else
							(Print 225 28)
						)
					)
					((or (Said '/hello') (Said 'chat[/dude,cop]'))
						(if (ego inRect: 180 148 288 171)
							(Print 225 33)
							(if (== currentCar carWork)
								(Print 225 34)
							else
								(Print 225 35)
							)
						else
							(Print 225 36)
						)
					)
					((or (Said 'use/key') (Said 'open/door'))
						(cond 
							(
								(or
									(== (ego onControl: origin) cLGREEN)
									(== (ego onControl: origin) cLCYAN)
									(== (ego onControl: origin) cGREY)
									(== (ego onControl: origin) cLBLUE)
								)
								(Print 225 37)
							)
							(
								(ego
									inRect: [carDoorRect 0] [carDoorRect 1] [carDoorRect 2] [carDoorRect 3]
								)
								(EnterCar)
							)
							((ego inRect: 180 187 200 197)
								(Print 225 38)
							)
							(else
								(Print 225 28)
							)
						)
					)
					((or (Said 'get/out') (Said 'exit/chamber'))
						(if (== local10 1)
							(rm225Script changeState: 6)
							(= local10 0)
						else
							(Print 225 39)
						)
					)
					((Said 'enter/auto')
						(EnterCar)
					)
					((Said 'exit/auto')
						(= global132 1)
					)
					((Said 'unlock/door')
						(if
							(or
								(ego
									inRect: [carDoorRect 0] [carDoorRect 1] [carDoorRect 2] [carDoorRect 3]
								)
								(ego inRect: 180 187 200 197)
							)
							(cond 
								((and (== currentCar carWork) (ego has: iUnmarkedCarKeys))
									(if (== workCarLocked TRUE)
										(= workCarLocked FALSE)
										(Print 225 40)
									else
										(Print 225 41)
									)
								)
								((== currentCar carWork)
									(Print 225 42)
								)
							)
							(cond 
								((and (== currentCar carPersonal) (ego has: iKeyRing))
									(if (== personalCarLocked TRUE)
										(= personalCarLocked FALSE)
										(Print 225 40)
									else
										(Print 225 41)
									)
								)
								((== currentCar carPersonal)
									(Print 225 42)
								)
							)
						else
							(Print 225 43)
						)
					)
					((Said 'lock/door')
						(if
							(or
								(ego
									inRect: [carDoorRect 0] [carDoorRect 1] [carDoorRect 2] [carDoorRect 3]
								)
								(ego inRect: 180 187 200 197)
							)
							(if (== currentCar carWork)
								(if (== workCarLocked FALSE)
									(= workCarLocked TRUE)
									(Print 225 44)
								else
									(Print 225 45)
								)
							)
							(if (== currentCar carPersonal)
								(if (== personalCarLocked FALSE)
									(= personalCarLocked TRUE)
									(Print 225 44)
								else
									(Print 225 45)
								)
							)
						else
							(Print 225 43)
						)
					)
					((or (Said 'knock') (Said 'knock/door'))
						(if
							(or
								(== (ego onControl: origin) cLGREEN)
								(== (ego onControl: origin) cLBLUE)
								(== (ego onControl: origin) cLCYAN)
								(== (ego onControl: origin) cGREY)
							)
							(Print 225 46)
						else
							(Print 225 47)
						)
					)
					((Said 'open/police')
						(if
							(or
								(== (ego onControl: origin) cLGREEN)
								(== (ego onControl: origin) cLBLUE)
								(== (ego onControl: origin) cGREY)
								(== (ego onControl: origin) cLCYAN)
							)
							(Print 225 48)
							(Print 225 49)
						else
							(Print 225 28)
						)
					)
					((Said 'display[/badge,badge]')
						(if (ego inRect: 180 148 288 171)
							(if (ego has: iWallet)
								(Print 225 50 #icon 999 0 0)
							else
								(Print 225 51)
							)
						else
							(Print 225 52)
						)
					)
					((Said 'open,unlock/trunk')
						(if (== currentCar carWork)
							(if
								(ego
									inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3]
								)
								(cond 
									(workCarTrunkOpened
										(Print 225 53)
									)
									((ego has: iUnmarkedCarKeys)
										(carScript changeState: 9)
									)
									(else
										(Print 225 54)
									)
								)
							else
								(NotClose)
							)
						else
							(Print 225 31)
						)
					)
					((Said 'close,lock/trunk')
						(if (== currentCar carWork)
							(if
								(ego
									inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3]
								)
								(if workCarTrunkOpened
									(carScript changeState: 11)
								else
									(Print 225 55)
								)
							else
								(NotClose)
							)
						else
							(Print 225 31)
						)
					)
					((Said 'deposit,place/briefcase')
						(if
							(ego
								inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3]
							)
							(if workCarTrunkOpened
								(if (ego has: iFieldKit)
									(Print 225 56)
									(PutInRoom iFieldKit carWork)
								else
									(Print 225 57)
								)
							else
								(Print 225 58)
							)
						else
							(Print 225 59)
						)
					)
					((Said 'remove,get/briefcase')
						(if
							(ego
								inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3]
							)
							(if workCarTrunkOpened
								(if (InRoom iFieldKit carWork)
									(Print 225 60)
									(ego get: iFieldKit)
								else
									(Print 225 61)
								)
							else
								(Print 225 58)
							)
						else
							(Print 225 59)
						)
					)
				)
			)
		)
	)
)

(instance carScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: egoX 178
					loop: 0
					cel: 0
					setCycle: Walk
					setPri: 13
					startUpd:
				)
				(if (== currentCar carWork)
					((= carDoor (Prop new:))
						view: 51
						loop: 3
						cel: 0
						posn: [carDoorPosn 0] [carDoorPosn 1]
						setPri: 15
						init:
						setCycle: EndLoop
					)
				)
				(self cue:)
			)
			(1
				(= local2 0)
				(if (== currentCar carWork)
					(= workCarLocked FALSE)
				else
					(= personalCarLocked FALSE)
				)
				(if (== currentCar carWork)
					(carDoor dispose:)
					(keith
						posn: 200 196
						setStep: 1 2
						setLoop: 0
						setCel: 0
						setPri: 15
						ignoreActors: TRUE
						illegalBits: 0
						init:
					)
					(self cue:)
				)
				(HandsOn)
			)
			(2
				(if (== currentCar carWork)
					(keith
						setStep: 3 2
						setCycle: Walk
						setLoop: -1
						setPri: -1
						ignoreActors: FALSE
						illegalBits: cWHITE
						setMotion: MoveTo 180 197 self
					)
				)
			)
			(3
				(keith setMotion: Follow ego 500)
				(Print 225 62 #draw)
			)
			(4
				(HandsOff)
				(if (== currentCar carWork)
					(Print 225 63)
					(cond 
						((> (keith y?) 189)
							(keith
								ignoreActors:
								illegalBits: cWHITE
								setMotion: MoveTo 185 (keith y?) self
							)
						)
						((< (keith x?) 110)
							(keith
								ignoreActors:
								illegalBits: cWHITE
								setMotion: MoveTo 110 (keith y?) self
							)
						)
						(else
							(keith
								ignoreActors:
								illegalBits: cWHITE
								setMotion: MoveTo 105 (keith y?) self
							)
						)
					)
				else
					(self changeState: 7)
				)
			)
			(5
				(cond 
					((== (keith x?) 110)
						(keith
							ignoreActors:
							illegalBits: cWHITE
							setMotion: MoveTo 110 195 self
						)
					)
					((== (keith x?) 105)
						(keith
							ignoreActors:
							illegalBits: cWHITE
							setMotion: MoveTo 105 195 self
						)
					)
					(else (self cue:))
				)
			)
			(6
				(keith setMotion: MoveTo 190 195 self)
			)
			(7
				(ego setPri: 12)
				(if (== currentCar carWork)
					((= carDoor (Prop new:))
						view: 51
						loop: (if (== currentCar 13) 3 else 0)
						cel: 0
						posn: [carDoorPosn 0] [carDoorPosn 1]
						setPri: 15
						init:
						setCycle: EndLoop
					)
					(keith
						ignoreActors: 1
						illegalBits: 0
						setLoop: 0
						setStep: 1 2
						setPri: 15
						setMotion: MoveTo 190 195
					)
				)
				(self cue:)
			)
			(8
				(= newRoomNum currentCar)
			)
			(9
				((= trunk (Prop new:))
					view: 51
					loop: 5
					cel: 0
					posn: [trunkPosn 0] [trunkPosn 1]
					setPri: 15
					init:
					setCycle: EndLoop self
				)
			)
			(10
				(= workCarTrunkOpened TRUE)
				(trunk stopUpd:)
			)
			(11
				(trunk startUpd: setCycle: BegLoop self)
			)
			(12
				(= workCarTrunkOpened FALSE)
				(trunk dispose:)
			)
		)
	)
)

;;;(instance stopScript of Script
;;;	(method (changeState newState)
;;;		(switch (= state newState)
;;;			(0
;;;				(ego stopUpd:)
;;;				(if (== currentCar carWork)
;;;					(keith stopUpd:)
;;;				)
;;;			)
;;;			(1
;;;				(local0 ignoreActors: FALSE stopUpd: addToPic:)
;;;			)
;;;		)
;;;	)
;;;)
