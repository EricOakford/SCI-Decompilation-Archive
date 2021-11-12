;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm22 0
)

(local
	door
	camera
	car
	carDoor
	[carDoorPos 2]
	local6 ;which car?
	local7
	local8 
	local9
	local10
	room23
	pressedBuzzer
	dustyCornerMessageToggle
	gunStolenFromLocker
	;local15 unused
	cameraIsOscillating
	;local17 set in cameraScript, but never used
	lockerUnlocked
)
(procedure (LocPrint)
	(Print &rest #at -1 130)
)

(procedure (EnterCar)
	(return
		(switch currentCar
			(carWork
				(cond 
					((not (ego inRect: 230 118 250 127))
						(LocPrint 22 103)
					)
					((== workCarLocked 1)
						(LocPrint 22 104)
						(return 0)
					)
					(workCarTrunkOpened
						(LocPrint 22 105)
						(return 0)
					)
					((ego has: iFieldKit)
						(LocPrint 22 106)
						(return 0)
					)
					(else
						(carScript changeState: 8)
						(return 1)
					)
				)
			)
			(carPersonal
				(cond 
					((not (ego inRect: 230 118 250 127))
						(LocPrint 22 107)
					)
					(personalCarLocked
						(LocPrint 22 104)
					)
					(else
						(carScript changeState: 8)
					)
				)
			)
		)
	)
)

(instance unTrunk of Prop
	(properties)
)

(instance rm22 of Room
	(properties
		picture 22
		style DISSOLVE
	)
	
	(method (init)
		(super init:)
		(User canInput: 1 canControl: 1)
		(if
			(= local6
				(if (== prevRoomNum 33) else (== prevRoomNum 13))
			)
			(HandsOff)
		else
			(HandsOn)
		)
		(if (== currentCar carWork) (= gunFireState 3))
		(if jailLockerOpen
			(= lockerUnlocked 1)
		)
		(if
			(and
				jailLockerOpen
				(== ((inventory at: iHandGun) owner?) 22)
			)
			(= lockerUnlocked 1)
			((inventory at: 0) moveTo: 0)
			(= gunStolenFromLocker 1)
		)
		(Load VIEW 0)
		(Load VIEW 20)
		(Load VIEW 54)
		(Load VIEW 51)
		(= [carDoorPos 0] 265)
		(= [carDoorPos 1] 129)
		(= local10 240)
		(= local8 238)
		(= local9 247)
		(= room23 23)
		((View new:)
			view: 54
			loop: 0
			cel: 3
			posn: 20 153
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: 54
			loop: 0
			cel: 3
			posn: 35 135
			init:
			stopUpd:
			addToPic:
		)
		(if (== currentCar carPersonal)
			((View new:)
				view: 54
				loop: 1
				cel: 3
				posn: 272 154
				init:
				stopUpd:
				addToPic:
			)
		)
		((View new:)
			view: 211
			loop: 3
			cel: 0
			posn: 159 66
			init:
			stopUpd:
			addToPic:
		)
		((View new:) ;fence
			view: 211
			loop: 1
			cel: 0
			posn: 262 100
			init:
			stopUpd:
			addToPic:
		)
		((= door (Prop new:))
			view: 211
			posn: 178 99
			init:
			stopUpd:
		)
		((= camera (Prop new:))
			view: 211
			loop: 2
			cel: 0
			posn: 148 69
			cycleSpeed: 6
			init:
			startUpd:
		)
		(= local7 
			(if (== roomCarParked curRoomNum)
				(!= prevRoomNum room23)
			else
				0
			)
		)
		(if (!= prevRoomNum room23)
			(= local6 1)
			(User canControl: 0)
		else
			(= local6 0)
		)
		((= car (Actor new:))
			view: 54
			setStep: 1
			setLoop: 1
			setCel: (if (== currentCar carWork) 1 else 5)
			setCycle: 0
			setMotion: 0
			illegalBits: 0
			posn:
				(if
					(and
						(!= prevRoomNum room23)
						(not local7)
					)
					local8
				else
					local9
				)
				134
			init:
		)
		(self setLocales: regFieldKit)
		(self setScript: rm22Script)
	)
)

(instance rm22Script of Script
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(!= (mod (ego view?) 2) 0)
					(<= (ego y?) 122)
				)
				(ego view: (- (ego view?) 1)))
			(
				(and
					(!= (mod (ego view?) 2) 1)
					(> (ego y?) 122)
				)
				(ego view: (+ (ego view?) 1))
			)
		)
		(cond 
			(
				(and
					(== global132 local6)
					(== local6 1)
					(not (cast contains: car))
				)
				(= global132 0)
				(carScript changeState: 0)
			)
			(
				(and
					(== global132 (not local6))
					(== (not local6) 1)
				)
				(= global132 0)
				(EnterCar)
			)
		)
		(cond 
			(
				(and
					(or
						(< (ego x?) 15)
						(> (ego x?) 296)
					)
					(> (ego y?) 155)
					(not dustyCornerMessageToggle)
				)
				(LocPrint 22 0)
				(= dustyCornerMessageToggle 1)
			)
			(
				(and
					dustyCornerMessageToggle
					(< (ego x?) 295)
					(> (ego x?) 16)
				)
				(= dustyCornerMessageToggle 0)
			)
			(
				(and
					(ego inRect: 145 99 210 108)
					cameraIsOscillating
				)
				(cameraScript changeState: 4))
			(
			(and
				(not (ego inRect: 145 99 210 108))
				(not cameraIsOscillating)
			)
			(cameraScript changeState: 0))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 0
					posn:
						(if (== prevRoomNum room23)
							179
						else
							0
						)
						(if (== prevRoomNum room23)
							106
						else
							0
						)
					init:
					setMotion: 0
				)
				(if (== currentCar 13)
					((= keith (Actor new:))
						view: 20
						illegalBits: cWHITE ;-16384
						posn:
							(if (!= prevRoomNum room23) (- (ego x?) 4) else 300)
							(if (!= prevRoomNum room23) (- (ego y?) 20) else 157)
						setCycle: Walk
						init:
					)
				)
				(if (!= prevRoomNum room23)
					(= workCarTrunkOpened 0)
					(stopScript init:)
					(car setMotion: MoveTo local9 134 stopScript)
				else
					(if (and workCarTrunkOpened (== currentCar 13))
						(unTrunk
							view: 51
							loop: 5
							cel: 2
							posn: 209 138
							setPri: 9
							init:
						)
					)
					(car addToPic:)
				)
				(cond 
					((!= roomCarParked curRoomNum)
						(Bclr fKeithWaitsAtJail)
						(= roomCarParked curRoomNum)
					)
					(local6
						(= global132 1)
					)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((Said '/jailer')
								(LocPrint 22 1)
							)
							((Said '/friend')
								(if
									(and
										(== currentCar 13)
										(< (keith y?) 10)
									)
									(if (ego inRect: 235 102 300 158)
										(LocPrint 22 2)
									else
										(LocPrint 22 3)
									)
								else
									(event claimed: 0)
								)
							)
							((Said '[<at,around][/building,garage,chamber]')
								(Print 22 4 #width 260 #at -1 120)
								(if (>= gamePhase 1)
									(Print 22 5 #width 280 #at -1 120)
								)
							)
							((Said '[<at,up][/ceiling,roof]')
								(LocPrint 22 6)
							)
							((Said '[<at,down][/floor,dirt]')
								(LocPrint 22 7)
							)
							((Said '/auto<blue')
								(if (== currentCar 33)
									(LocPrint 22 8)
								else
									(LocPrint 22 9)
								)
							)
							((Said '/key[<locker]')
								(if lockerUnlocked
									(LocPrint 22 10)
								else
									(LocPrint 22 11)
								)
							)
							((Said '<below/auto')
								(LocPrint 22 12)
							)
							((Said '/auto')
								(if (ego inRect: 150 0 250 146)
									(if (== currentCar 13)
										(LocPrint 22 13)
									else
										(LocPrint 22 14)
									)
								else
									(LocPrint 22 15)
								)
							)
							((Said '/cruiser')
								(LocPrint 22 16)
							)
							((Said '/trunk')
								(if
									(and
										(ego inRect: 176 123 206 135)
										(cast contains: unTrunk)
									)
									(inventory
										carrying: {The car's trunk contains:}
										empty: {The car's trunk is empty.}
										showSelf: 13
									)
								else
									(LocPrint 22 17)
								)
							)
							((Said '/sign,gate,fence')
								(if (> (ego x?) 235)
									(LocPrint 22 18)
								else
									(LocPrint 22 19)
								)
							)
							((Said '/wall')
								(LocPrint 22 20)
								(LocPrint 22 21 70 260)
							)
							((Said '<in/locker')
								(if jailLockerOpen
									(if (ego inRect: 135 99 163 108)
										(if (!= ((inventory at: 0) owner?) 22)
											(LocPrint 22 22)
										else
											(LocPrint 22 23)
										)
									else
										(LocPrint 22 24)
									)
								else
									(LocPrint 22 25)
								)
							)
							((Said '/locker')
								(LocPrint 22 26)
							)
							((Said '/button,button')
								(LocPrint 22 27)
							)
							((Said '/camera')
								(LocPrint 22 28)
							)
							((Said '/pa,pa')
								(LocPrint 22 29)
							)
							((Said '/door')
								(LocPrint 22 30)
							)
							(else
								(event claimed: 0)
							)
						)
					)
					((Said 'chat/friend')
						(if
							(and
								(== currentCar 13)
								(< (keith y?) 10)
							)
							(if (ego inRect: 235 102 300 158)
								(LocPrint 22 31)
							else
								(LocPrint 22 32)
							)
						else
							(event claimed: 0)
						)
					)
					(
						(or
							(Said 'use/extender')
							(Said 'extender')
						)
						(if (== currentCar 13)
							(LocPrint 22 33)
						else
							(LocPrint 22 34)
						)
					)
					((Said 'drive')
						(if local6
							(LocPrint 22 35)
						else
							(LocPrint 22 36)
						)
					)
					((Said 'enter/auto')
						(cond 
							((ego inRect: 230 118 250 127)
								(EnterCar)
							)
							((ego inRect: 230 129 260 138)
								(LocPrint 22 37)
							)
							(else
								(LocPrint 22 38)
							)
						)
					)
					((Said 'knock')
						(if (ego inRect: 145 99 205 108)
							(LocPrint 22 39)
						else
							(LocPrint 22 40)
						)
					)
					((Said 'open/auto')
						(EnterCar)
					)
					((Said 'unlock/gate')
						(LocPrint 22 41)
					)
					(
						(or
							(Said 'unlock,open/locker')
							(Said 'unlock,open/(door<locker)')
						)
						(cond 
							(jailLockerOpen (LocPrint 22 42))
							((ego inRect: 135 99 163 108)
								(if (== ((inventory at: iHandGun) owner?) 22)
									(LocPrint 22 43)
								else
									(LocPrint 22 44)
								)
								(= jailLockerOpen 1)
								(= lockerUnlocked 1)
							)
							(else
								(LocPrint 22 45)
							)
						)
					)
					((Said 'enter,go[<in]/jail')
						(if (ego inRect: 145 99 205 108)
							(if (not pressedBuzzer)
								(LocPrint 22 46)
							else
								(LocPrint 22 47)
							)
						else
							(LocPrint 22 48)
						)
					)
					((Said 'exit,(get<out)[/auto]')
						(= global132 1)
					)
					(
						(or
							(Said 'press,use,ring/button,button,bell')
							(Said 'chat,signal,buzz,call/jailer')
						)
						(if (ego inRect: 185 99 210 108)
							(LocPrint 22 49)
							(if pressedBuzzer
								(LocPrint 22 50)
							else
								(= pressedBuzzer 1)
								(LocPrint 22 51)
							)
						else
							(LocPrint 22 52)
						)
					)
					((Said 'affirmative')
						(if
							(and
								pressedBuzzer
								(ego inRect: 145 99 210 108)
							)
							(LocPrint 22 53)
						else
							(LocPrint 22 54)
						)
					)
					((Said 'no')
						(if
							(and
								pressedBuzzer
								(ego inRect: 145 99 210 108)
							)
							(LocPrint 22 55)
						else
							(LocPrint 22 56)
						)
					)
					((Said 'display/badge,card,badge,billfold,painting')
						(if pressedBuzzer
							(if (ego inRect: 145 99 205 108)
								(if (ego has: iWallet)
									(doorScript changeState: 0)
								else
									(LocPrint 22 57)
								)
							else
								(LocPrint 22 58)
							)
						else
							(LocPrint 22 59)
						)
					)
					((Said 'call,holler/friend')
						(if (keith inRect: 280 150 320 170)
							(LocPrint 22 60)
						else
							(LocPrint 22 61)
						)
					)
					((Said 'get,remove/9mm')
						(cond 
							((ego has: iHandGun)
								(AlreadyTook)
							)
							((not (ego inRect: 135 99 163 108))
								(LocPrint 22 62)
							)
							((not jailLockerOpen)
								(LocPrint 22 63)
							)
							(gunStolenFromLocker
								(LocPrint 22 64)
								(EgoDead
									{Yes, even in peaceful Lytton, crimes of opportunity still occur. Next time, keep track of your piece.}
								)
							)
							((== ((inventory at: iHandGun) owner?) 22)
								(LocPrint 22 65)
								(ego get: iHandGun)
								(= jailLockerOpen 0)
							)
							(else
								(LocPrint 22 66)
							)
						)
					)
					((Said 'deposit,place,replace,throw,exit/9mm')
						(cond 
							((not (ego inRect: 135 99 163 108))
								(LocPrint 22 62)
							)
							((not jailLockerOpen)
								(LocPrint 22 63)
							)
							((not (ego has: iHandGun))
								(LocPrint 22 67)
							)
							(gunDrawn
								(LocPrint 22 68)
							)
							(else
								(LocPrint 22 69)
								(ego put: iHandGun 22)
							)
						)
					)
					(
					(Said 'deposit,place,replace,throw/key[<locker]')
						(if lockerUnlocked
							(LocPrint 22 70)
						else
							(LocPrint 22 34)
						)
					)
					((Said 'get/key<locker')
						(if lockerUnlocked
							(AlreadyTook)
						else
							(LocPrint 22 71)
						)
					)
					((Said 'get/key')
						(if
							(and
								(ego inRect: 135 99 163 108)
								lockerUnlocked
							)
							(LocPrint 22 72)
						else
							(LocPrint 22 73)
						)
					)
					(
						(or
							(Said 'close,lock/locker')
							(Said 'close,lock/(door<locker)')
						)
						(if (ego inRect: 135 99 163 108)
							(if jailLockerOpen
								(= jailLockerOpen 0)
								(LocPrint 22 74)
								(if (== ((inventory at: iHandGun) owner?) 22)
									(SolvePuzzle 3 fLockedUpGunAtJail)
								)
							else
								(LocPrint 22 75)
							)
						else
							(LocPrint 22 62)
						)
					)
					((Said 'open,move/gate,fence')
						(LocPrint 22 76)
					)
					((Said 'climb/gate,fence')
						(LocPrint 22 77)
					)
					((Said 'unlock/door')
						(cond 
							((ego inRect: 230 118 250 127)
								(cond 
									((== currentCar carWork)
										(if (ego has: iUnmarkedCarKeys)
											(if workCarLocked
												(= workCarLocked 0)
												(LocPrint 22 78)
											else
												(LocPrint 22 79)
											)
										else
											(LocPrint 22 80)
										)
									)
									((== currentCar carPersonal)
										(if (ego has: iKeyRing)
											(if personalCarLocked
												(= personalCarLocked 0)
												(LocPrint 22 78)
											else
												(LocPrint 22 79)
											)
										else
											(LocPrint 22 80)
										)
									)
								)
							)
							((ego inRect: 145 99 205 108)
								(if (not pressedBuzzer)
									(LocPrint 22 46)
								else
									(LocPrint 22 47)
								)
							)
							((ego inRect: 230 129 260 138)
								(LocPrint 22 81)
							)
							(else
								(LocPrint 22 45)
							)
						)
					)
					((Said 'lock/door')
						(cond 
							((ego inRect: 230 118 250 127)
								(if (== currentCar 13)
									(if (not workCarLocked)
										(= workCarLocked 1)
										(LocPrint 22 82)
									else
										(LocPrint 22 83)
									)
								)
								(if (== currentCar 33)
									(if (not personalCarLocked)
										(= personalCarLocked 1)
										(LocPrint 22 82)
									else
										(LocPrint 22 83)
									)
								)
							)
							((ego inRect: 230 129 260 138)
								(LocPrint 22 84)
							)
							(else
								(LocPrint 22 45)
							)
						)
					)
					((Said 'open,unlock/trunk')
						(cond 
							((== currentCar carWork)
								(if (ego inRect: 176 123 206 135)
									(cond 
										(workCarTrunkOpened
											(Print 22 85)
										)
										((ego has: iUnmarkedCarKeys)
											(carScript changeState: 14)
										)
										(else
											(LocPrint 22 86)
										)
									)
								else
									(LocPrint 22 87)
								)
							)
							((ego inRect: 176 123 206 135)
								(LocPrint 22 88)
							)
							(else
								(LocPrint 22 87)
							)
						)
					)
					((Said 'close,lock/trunk')
						(if (== currentCar carWork)
							(if (ego inRect: 176 123 206 135)
								(if workCarTrunkOpened
									(carScript changeState: 16)
								else
									(Print 22 89)
								)
							else
								(NotClose)
							)
						else
							(LocPrint 22 88)
						)
					)
					(
						(or
							(Said 'press,open/door')
							(Said 'let/i')
							(Said 'get<in')
						)
						(cond 
							((ego inRect: 145 99 205 108)
								(if (not pressedBuzzer)
									(LocPrint 22 46)
								else
									(LocPrint 22 47)
								)
							)
							(local6
								(= global132 1)
							)
							((ego inRect: 230 129 260 138)
								(LocPrint 22 84)
							)
							(else
								(EnterCar)
							)
						)
					)
					((Said 'close/door')
						(LocPrint 22 90)
					)
					((Said 'deposit,place/briefcase')
						(if (ego inRect: 176 123 206 135)
							(if workCarTrunkOpened
								(if (ego has: iFieldKit)
									(LocPrint 22 91)
									(PutInRoom iFieldKit 13)
									(if (IsObject theFieldKit)
										(theFieldKit dispose:)
									)
									(= fieldKitOpen 0)
								else
									(LocPrint 22 92)
								)
							else
								(LocPrint 22 93)
							)
						else
							(LocPrint 22 94)
						)
					)
					((Said 'remove,get/briefcase')
						(if (ego inRect: 176 123 206 135)
							(if workCarTrunkOpened
								(if (== ((inventory at: iFieldKit) owner?) 13)
									(LocPrint 22 95)
									(ego get: iFieldKit)
								else
									(LocPrint 22 96)
								)
							else
								(LocPrint 22 93)
							)
						else
							(LocPrint 22 94)
						)
					)
				)
			)
		)
	)
)

(instance cameraScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cameraIsOscillating 1)
				(self cue:)
			)
			(1
				;(= local17 1)
				(camera setCycle: EndLoop self)
			)
			(2
				;(= local17 0)
				(camera setCycle: BegLoop self)
			)
			(3
				(self changeState: 1)
			)
			(4
				(= cameraIsOscillating 0)
				(camera setCycle: 0)
				(self cue:)
			)
			(5 (camera setCycle: CycleTo 0 -1))
		)
	)
)

(instance doorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(LocPrint 22 97)
				(ego setMotion: MoveTo 180 101 self)
			)
			(1
				(ego setLoop: 3)
				(door setCycle: EndLoop self)
			)
			(2
				(ego setLoop: -1)
				(HandsOn)
				(curRoom newRoom: 23)
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
					posn: 240 136
					setPri: 8
					setLoop: 0
					setCycle: 0
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo 238 124 self
				)
				(if
					(and
						(== currentCar carWork)
						(not local7)
					)
					(keith
						posn: 250 136
						loop: 0
						cel: 1
						startUpd:
					)
					((= carDoor (Prop new:))
						view: 51
						loop: 3
						cel: 0
						posn: [carDoorPos 0] [carDoorPos 1]
						setPri: 9
						init:
						setCycle: EndLoop
					)
				)
			)
			(1
				(= local6 0)
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
					illegalBits: cWHITE ;-32768
				)
				(HandsOn)
				(if (== currentCar carWork)
					(self cue:)
				)
			)
			(2
				(if (== currentCar carWork)
					(if local7
						(LocPrint 22 98)
						(LocPrint 22 99)
						(Bset fKeithWaitsAtJail)
					else
						(carDoor dispose:)
						(keith
							illegalBits: 0
							setMotion: MoveTo 250 140 self
						)
					)
				)
			)
			(3
				(keith
					illegalBits: cWHITE ;-16384
					setMotion: MoveTo 260 145 self
				)
				(LocPrint 22 100)
			)
			(4
				(keith setMotion: MoveTo 300 157 self)
			)
			(5
				(keith stopUpd:)
			)
			(8
				(HandsOff)
				(ego stopUpd:)
				(if (== currentCar 13)
					(if (Btst fKeithWaitsAtJail)
						(LocPrint 22 101)
						(self changeState: 12)
					else
						(LocPrint 22 102)
						(self cue:)
					)
				else
					(self changeState: 12)
				)
			)
			(9
				(keith
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 250 136 self
				)
			)
			(10
				(keith setLoop: 0)
				(self cue:)
			)
			(11
				((= carDoor (Prop new:))
					view: 51
					loop: 3
					cel: 0
					posn: [carDoorPos 0] [carDoorPos 1]
					setPri: 9
					init:
					setCycle: EndLoop self
				)
			)
			(12
				(ego
					ignoreActors: 1
					illegalBits: 0
					setPri: 8
					setLoop: 0
					setCycle: 0
					setStep: 1 2
					setMotion: MoveTo 240 134 self
				)
			)
			(13
				(curRoom newRoom: currentCar)
			)
			(14
				(= workCarTrunkOpened 1)
				(unTrunk
					view: 51
					loop: 5
					cel: 0
					posn: 209 138
					setPri: 9
					init:
					setCycle: EndLoop self
				)
			)
			(15
				(unTrunk stopUpd:)
			)
			(16
				(= workCarTrunkOpened 0)
				(unTrunk
					view: 51
					loop: 5
					cel: 2
					posn: 209 138
					setPri: 9
					startUpd:
					setCycle: CycleTo 0 -1 self
				)
			)
			(17 (unTrunk dispose:))
		)
	)
)

(instance stopScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego stopUpd:)
				(if (== currentCar carWork)
					(keith stopUpd:)
				)
			)
			(1
				(= global132 1)
				(car
					stopUpd:
					addToPic:
				)
			)
		)
	)
)
