;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm25 0
)

(local
	local0
	local1
	local2
	newAct
	newProp_3
	newProp
	newAct_2
	local7
	local8
	local9
	local10
	local11
	local12
	local13
	local14
	newProp_2
	local16
	local17
	newProp_6
	newProp_4
	newAct_3
	local21
	newProp_5
	newAct_4
	[local24 2]
	[local26 2]
	[local28 4]
	[local32 5]
	local37
	local38
	local39
	local40
	local41
)
(procedure (EnterCar)
	(return
		(switch currentCar
			(carWork
				(cond 
					((not (ego inRect: [local28 0] [local28 1] [local28 2] [local28 3]))
						(Print 25 72)
					)
					((== workCarLocked 1)
						(Print 25 73)
						(return 0)
					)
					(workCarTrunkOpened
						(Print 25 74)
						(return 0)
					)
					((ego has: iFieldKit)
						(Print 25 75)
						(return 0)
					)
					(else
						(carScript changeState: 4)
						(return 1)
					)
				)
			)
			(carPersonal
				(cond 
					((not (ego inRect: [local28 0] [local28 1] [local28 2] [local28 3]))
						(Print 25 72)
					)
					((== personalCarLocked 1)
						(Print 25 73)
					)
					(else
						(carScript changeState: 4)
					)
				)
			)
		)
	)
)

(instance swatSound of Sound
	(properties
		number 13
	)
)

(instance kAct of Actor
	(properties)
)

(instance gasBomb of Actor
	(properties)
)

(instance rm25 of Room
	(properties
		picture 25
		style HWIPE
	)
	
	(method (init)
		(if (== gamePhase 11)
			(= captainWarningTimer 0)
			(= global159 0)
		)
		(super init:)
		(= keith 0)
		(= gunNotNeeded gunPERMITTED)
		(= gunFireState 3)
		(= local1 (< gamePhase 11))
		(= [local26 0] 142)
		(= [local26 1] 193)
		(cond 
			((= local9 (if (== prevRoomNum 33) else (== prevRoomNum 13)))
				(= workCarTrunkOpened 0)
			)
			(workCarTrunkOpened
				((= newProp (Prop new:))
					view: 51
					loop: 5
					cel: 2
					posn: [local26 0] [local26 1]
					setPri: 14
					init:
				)
			)
		)
		(Load VIEW 54)
		(Load VIEW 51)
		(Load VIEW 251)
		(Load VIEW 30)
		(Load VIEW 999)
		(Load VIEW 123)
		(Load VIEW 112)
		(Load VIEW 268)
		(Load VIEW 97)
		(Load VIEW 286)
		(Load VIEW 50)
		(Load VIEW 53)
		(Load SOUND 41)
		(swatSound init:)
		(if (== currentCar carWork)
			(= local7 180)
			(= local8 189)
			(= [local24 0] 197)
			(= [local24 1] 184)
			(= [local28 0] 156)
			(= [local28 1] 140)
			(= [local28 2] 180)
			(= [local28 3] 193)
			(= [local32 0] 106)
			(= [local32 1] 174)
			(= [local32 2] 140)
			(= [local32 3] 190)
			(= local13 165)
		else
			(= local7 192)
			(= local8 189)
			(= [local28 0] 170)
			(= [local28 1] 165)
			(= [local28 2] 195)
			(= [local28 3] 179)
			(= [local24 0] (- local7 1))
			(= [local24 1] (- local8 2))
			(= local13 175)
		)
		(= local21 0)
		(= local11 (- local7 10))
		(= local12 local7)
		(= local14 26)
		(= local10
			(if (== roomCarParked curRoomNum)
				(!= prevRoomNum local14)
			else
				0
			)
		)
		(if (!= prevRoomNum local14)
			(= local9 1)
			(HandsOff)
		else
			(= local9 0)
			(HandsOn)
		)
		(if (or global170 (Btst 13)) (= global178 1))
		((= newAct (Actor new:))
			view: 54
			setStep: 3 3
			setLoop: 1
			setCel: (if (== currentCar 13) 1 else 5)
			setCycle: 0
			setMotion: 0
			posn:
				(if
					(and
						(or (== prevRoomNum 13) (== prevRoomNum 33))
						(not local10)
					)
					local11
				else
					local12
				)
				local8
			init:
			illegalBits: 0
		)
		((= newProp_2 (Prop new:))
			view: 251
			setLoop: (if (== gamePhase 12) 7 else 1)
			setCel: (if (== gamePhase 12) 255 else 0)
			setCycle: 0
			setPri: 9
			posn:
				(if (== gamePhase 12) 252 else 261)
				(if (== gamePhase 12) 156 else 155)
			init:
			ignoreActors:
			stopUpd:
		)
		((View new:)
			view: 251
			setLoop: 2
			setCel: 0
			setPri: 7
			posn: 104 72
			init:
			addToPic:
		)
		((View new:)
			view: 251
			setLoop: 0
			setCel: 2
			setPri: 10
			posn: 167 138
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 251
			setLoop: 0
			setCel: 0
			setPri: 0
			posn: 67 7
			init:
			ignoreActors: 0
			addToPic:
		)
		((View new:)
			view: 251
			loop: 6
			cel: 0
			setPri: 3
			posn: 104 (if (not (< gamePhase 11)) 110 else 123)
			ignoreActors:
			init:
			addToPic:
		)
		(if (and (Btst 14) (== gamePhase 12))
			((View new:)
				view: 251
				setLoop: 0
				setCel: 1
				setPri: 9
				posn: 229 121
				init:
				addToPic:
			)
		)
		(ego
			view: 0
			posn:
				(if (== prevRoomNum 26) 256 else 340)
				(if (== prevRoomNum 26) 154 else 300)
			init:
			loop: 1
			setMotion: 0
		)
		(cond 
			((!= roomCarParked curRoomNum) (= roomCarParked curRoomNum))
			((== local9 1) (= global132 1))
		)
		(= keith 0)
		(if (== currentCar 13)
			((= keith kAct)
				view: 20
				illegalBits: -32768
				setCycle: Walk
				setAvoider: (Avoider new:)
				setMotion: Follow ego 500
			)
			(if (== prevRoomNum 26)
				(ego setMotion: MoveTo 200 160)
				(keith
					loop: 1
					posn: 270 150
					init:
					setAvoider: Avoider
					setMotion: MoveTo 227 160 keithScript
				)
			else
				(if (< howFast 30) (keith setMotion: 0 stopUpd:))
				(keith posn: 340 1000 init: stopUpd:)
			)
		)
		(if (!= prevRoomNum 26)
			(= global132 1)
			(ego stopUpd:)
			(if (== currentCar 13) (keith stopUpd:))
		)
		(newAct posn: local12 local8 ignoreActors: 0 addToPic:)
		(if (Btst 29) (self setScript: warrantArrives))
		(if (Btst 14) (self setScript: swatArrives))
		(HandsOn)
	)
	
	(method (doit)
		(super doit:)
		(if (not script)
			(cond 
				((& $0040 (ego onControl:)) (curRoom setScript: enterMotel))
				(
					(or
						(ego inRect: 152 77 176 109)
						(ego inRect: 159 109 184 142)
					)
					(ego setPri: 10 illegalBits: 8192)
					(if (== currentCar 13) (keith stopUpd:))
				)
				((< (ego y?) 83)
					(ego setPri: 7 illegalBits: -32768)
					(if (== currentCar 13) (keith stopUpd:))
				)
				(else
					(if (== currentCar 13) (keith startUpd:))
					(if (> (ego y?) 183)
						(ego setPri: 15)
					else
						(ego setPri: -1)
					)
					(ego illegalBits: -32768)
				)
			)
			(if (== gamePhase 12)
				(ego ignoreControl: 2)
			else
				(ego observeControl: 2)
			)
			(cond 
				(local39 (self setScript: warrantLeaves) (= local39 0))
				((== (ego onControl: 1) 4096) (self setScript: talkWindow))
				((and (== global132 local9) (== local9 1))
					(if (not (cast contains: newAct))
						(= global132 0)
						(self setScript: exitCar)
					)
				)
				(
				(and (== global132 (not local9)) (== (not local9) 1)) (= global132 0) (EnterCar))
				((== global171 1) (= global171 0) (self setScript: swatArrives))
				((== global170 1) (= global170 0) (self setScript: warrantArrives))
			)
		)
		(= local37 (& (ego onControl: 1) $0f00))
		(if
			(and
				(<= (ego y?) 130)
				(or (< (ego x?) 0) (> (ego x?) 320))
			)
			(ego y: 132)
		)
		(if (== currentCar 13)
			(cond 
				((== (keith priority?) 15) (if (<= (keith y?) 183) (keith setPri: -1)))
				((> (keith priority?) 183) (keith setPri: 15))
			)
		)
		(if (> local2 1) (-- local2))
		(if (== local2 1)
			(-- local2)
			(newProp_6 dispose:)
			(Print 25 0 #draw)
		)
		(if (and (== local9 0) (not (ego onControl: 1)))
			(Print 25 1)
			(cond 
				((<= (ego x?) 0) (ego setMotion: MoveTo 10 (ego y?)))
				((>= (ego y?) 180) (ego setMotion: MoveTo (ego x?) 177))
			)
		)
	)
	
	(method (dispose)
		(carScript dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((Said 'read/number,door')
				(cond 
					((not local37) (Print 25 2))
					((ego inRect: 80 70 140 77) (Print 25 3))
					((ego inRect: 168 70 210 76) (Print 25 4))
					((ego inRect: 233 70 286 81) (Print 25 5))
					(else (Print 25 6))
				)
			)
			((Said 'go/pane') (Print 25 7))
			((Said 'look,smell/cigarette,gas') (if local2 (Print 25 8) else (event claimed: 0)))
			((Said 'look>')
				(cond 
					((Said '[<around,at][/building,chamber]') (Print 25 9))
					((Said '/pane')
						(cond 
							((!= (ego onControl: 1) 16384) (Print 25 10))
							((and local16 (ego inRect: 197 130 243 143)) (Print 25 11))
							(else (Print 25 12))
						)
					)
					((Said '/door')
						(cond 
							((not local37) (Print 25 13))
							((!= (ego onControl: 1) 256) (Print 25 14))
							((== gamePhase 12) (Print 25 15))
							(else (Print 25 6))
						)
					)
					((Said '/briefcase') (if (ego has: 10) (Print 25 16) else (Print 25 17)))
					((Said '/trunk')
						(if (== currentCar 13)
							(if
								(and
									(ego
										inRect: [local32 0] [local32 1] [local32 2] [local32 3]
									)
									(cast contains: newProp)
								)
								(inventory
									carrying: {The car's trunk contains:}
									empty: {The car's trunk is empty.}
									showSelf: 13
								)
							else
								(Print 25 18)
							)
						else
							(Print 25 19)
						)
					)
				)
			)
			((Said 'open/briefcase') (if (ego has: 10) (Print 25 20) else (Print 25 17)))
			((or (Said 'go/in') (Said 'go<in'))
				(cond 
					((not local37) (Print 25 2))
					((!= (ego onControl: 1) 256) (Print 25 21))
					((< gamePhase 12) (Print 25 22))
					((curRoom script?) (Print 25 23))
					(else (curRoom setScript: enterMotel))
				)
			)
			(
				(and
					(ego
						inRect: [local28 0] [local28 1] [local28 2] [local28 3]
					)
					(or (Said 'unlock/door') (Said 'use/key'))
				)
				(cond 
					((== currentCar 13)
						(cond 
							((not (ego has: 3)) (Print 25 24))
							(workCarLocked (= workCarLocked 0) (Print 25 25))
							(else (Print 25 26))
						)
					)
					((not (ego has: 2)) (Print 25 24))
					(personalCarLocked (= personalCarLocked 0) (Print 25 25))
					(else (Print 25 26))
				)
			)
			(
			(or (Said 'press,unlock,open/door') (Said 'use/key'))
				(cond 
					(
						(ego
							inRect: [local28 0] [local28 1] [local28 2] [local28 3]
						)
						(EnterCar)
					)
					((not local37) (NotClose))
					((!= (ego onControl:) 256) (Print 25 27))
					((== gamePhase 12) (Print 25 28))
					((not (ego has: 27)) (Print 25 29))
					((== local37 256) (self setScript: enterRoom) (SolvePuzzle 3))
				)
			)
			((Said 'extender,ask,get,call/warrant[<frisk]')
				(cond 
					((Btst 13) (Print 25 30))
					((> global170 1) (Print 25 31))
					((Btst 29)
						(if (> (ego distanceTo: newAct_4) 30)
							(NotClose)
						else
							(= local39 1)
						)
					)
					(else (Print 25 32))
				)
			)
			((Said 'extender,call/dispatch') (Print 25 33))
			((Said 'look,look/warrant[<frisk]')
				(cond 
					((Btst 13) (Print 25 34))
					((> global170 1) (Print 25 31))
					(else (Print 25 35))
				)
			)
			((Said 'extender,ask,call,get/backup,swat,team')
				(cond 
					((Btst 14) (Print 25 30))
					((> global171 1) (Print 25 36))
					(else (Print 25 32))
				)
			)
			((or (Said 'enter/chamber,inn') (Said 'go<in'))
				(cond 
					((not local37) (NotClose))
					((!= (ego onControl:) 256) 25 22)
					((!= gamePhase 12) (Print 25 22))
					(else (Print 25 37))
				)
			)
			((Said 'enter/auto') (EnterCar))
			((Said 'exit/auto') (= global132 1))
			((Said 'lock/door')
				(if
					(or
						(ego
							inRect: [local28 0] [local28 1] [local28 2] [local28 3]
						)
						(ego inRect: 180 187 200 197)
					)
					(if (== currentCar 13)
						(if (== workCarLocked 0)
							(= workCarLocked 1)
							(Print 25 38)
						else
							(Print 25 39)
						)
					)
					(if (== currentCar 33)
						(if (== personalCarLocked 0)
							(= personalCarLocked 1)
							(Print 25 38)
						else
							(Print 25 39)
						)
					)
				else
					(Print 25 40)
				)
			)
			((Said 'knock[/door]') (if local37 (Print 25 41) else (Print 25 40)))
			((Said '[open]/police')
				(if local37
					(Print 25 42)
					(Print 25 43)
				else
					(Print 25 44)
				)
			)
			((Said 'open,unlock/trunk')
				(if (== currentCar 13)
					(if
						(ego
							inRect: [local32 0] [local32 1] [local32 2] [local32 3]
						)
						(cond 
							(workCarTrunkOpened (Print 25 45))
							((ego has: 3) (= workCarTrunkOpened 1) (carScript changeState: 9))
							(else (Print 25 46))
						)
					else
						(NotClose)
					)
				else
					(Print 25 19)
				)
			)
			((Said 'close,lock/trunk')
				(if (== currentCar 13)
					(if
						(ego
							inRect: [local32 0] [local32 1] [local32 2] [local32 3]
						)
						(if workCarTrunkOpened
							(= workCarTrunkOpened 0)
							(carScript changeState: 11)
						else
							(Print 25 47)
						)
					else
						(NotClose)
					)
				else
					(Print 25 19)
				)
			)
			((Said 'chat,ask/friend')
				(if (== currentCar 13)
					(keith setMotion: Follow ego 500)
					(Print 25 48)
				else
					(Print 25 49)
				)
			)
			((Said 'chat>')
				(event claimed: 1)
				(cond 
					(
					(and (Btst 14) (<= (ego distanceTo: newAct_3) 30))
						(cond 
							((Btst 0) (Print 25 50) (Print 25 51))
							((== gamePhase 12) (Print 25 52))
							(local41 (Print 25 53))
							(else
								(= local41 1)
								(Print 25 54)
								(ego setMotion: 0)
								(newAct_3 view: 268 setLoop: 1 setCel: 0 setCycle: EndLoop)
								(Print 25 55)
								(Print 25 56)
								(Print 25 57)
							)
						)
					)
					(
					(and (Btst 29) (<= (ego distanceTo: newAct_4) 30)) (Print 25 58))
					((and keith (Said '/friend')) (event claimed: 0))
					(else (event claimed: 0))
				)
			)
			((or (Said 'break/door') (Said 'beat/door'))
				(cond 
					((not local37) (Print 25 44))
					(
						(or
							(not (& (ego onControl: 1) $0100))
							(!= gamePhase 11)
						)
						(Print 25 59)
						(Print 25 60)
					)
					((== gamePhase 12) (Print 25 61))
					(else (= local21 1) (self setScript: kickInDoor))
				)
			)
			((Said 'deposit,place/briefcase')
				(if
					(ego
						inRect: [local32 0] [local32 1] [local32 2] [local32 3]
					)
					(if workCarTrunkOpened
						(if (ego has: 10)
							(Print 25 62)
							(PutInRoom 10 13)
							(if (IsObject theFieldKit) (theFieldKit dispose:))
							(= fieldKitOpen 0)
						else
							(Print 25 63)
						)
					else
						(Print 25 64)
					)
				else
					(Print 25 65)
				)
			)
			((Said 'remove,get/briefcase')
				(if
					(ego
						inRect: [local32 0] [local32 1] [local32 2] [local32 3]
					)
					(if workCarTrunkOpened
						(if (InRoom 10 13)
							(Print 25 66)
							(ego get: 10)
						else
							(Print 25 67)
						)
					else
						(Print 25 64)
					)
				else
					(Print 25 65)
				)
			)
			((Said 'get/browning')
				(if
					(ego
						inRect: [local32 0] [local32 1] [local32 2] [local32 3]
					)
					(if 1 (Print 25 68) else (Print 25 69))
				else
					(Print 25 70)
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
				(User canInput: 1 canControl: 1)
				(if (== currentCar 13)
					((= newProp_3 (Prop new:))
						view: 51
						loop: 3
						cel: 0
						ignoreActors:
						posn: [local24 0] [local24 1]
						setPri: 14
						setCycle: EndLoop self
						init:
					)
				else
					(self cue:)
				)
			)
			(1
				(if (== currentCar 13)
					(keith
						posn: 196 197
						setCycle: Walk
						setStep: 3 2
						setPri: 15
						setAvoider: (Avoider new:)
					)
				)
				(ego
					posn: local13 178
					loop: 0
					cel: 0
					setCycle: Walk
					setPri: 13
				)
				(= local9 0)
				(if (== currentCar 13)
					(= workCarLocked 0)
				else
					(= personalCarLocked 0)
				)
				(if (== currentCar 13) (newProp_3 dispose:))
				(User canInput: 1 canControl: 1)
				(self cue:)
			)
			(2
				(if (== currentCar 13)
					(keith
						posn: 193 197
						ignoreActors: 0
						illegalBits: -32768
						setMotion: MoveTo 190 200 self
					)
				)
			)
			(3
				(if (>= howFast 60)
					(keith setPri: -1 setMotion: Follow ego 500)
				else
					(keith setPri: -1 stopUpd:)
				)
			)
			(4
				(HandsOff)
				(if (== currentCar 13)
					(ego stopUpd:)
					(Print 25 71)
					(cond 
						((> (keith y?) 189)
							(keith
								ignoreActors:
								illegalBits: -32768
								setMotion: MoveTo 185 (keith y?) self
							)
						)
						((< (keith x?) 110)
							(keith
								ignoreActors:
								illegalBits: -32768
								setMotion: MoveTo 110 (keith y?) self
							)
						)
						(else
							(keith
								ignoreActors:
								illegalBits: -32768
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
							illegalBits: -32768
							setMotion: MoveTo 110 195 self
						)
					)
					((== (keith x?) 105)
						(keith
							ignoreActors:
							illegalBits: -32768
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
				(if (== currentCar 13)
					((= newProp_3 (Prop new:))
						view: 51
						loop: (if (== currentCar 13) 3 else 0)
						cel: 0
						posn: [local24 0] [local24 1]
						setPri: 15
						init:
						setCycle: EndLoop self
					)
					(keith
						ignoreActors: 1
						illegalBits: 0
						setLoop: 0
						setStep: 1 2
						setPri: 15
						setMotion: MoveTo 190 195
					)
				else
					(self cue:)
				)
			)
			(8 (= newRoomNum currentCar))
			(9
				((= newProp (Prop new:))
					view: 51
					loop: 5
					cel: 0
					posn: [local26 0] [local26 1]
					setPri: 14
					init:
					setCycle: EndLoop self
				)
			)
			(10 (newProp stopUpd:))
			(11
				(newProp startUpd: setCycle: BegLoop self)
			)
			(12 (newProp dispose:))
		)
	)
)

(instance pLight of Prop
	(properties)
	
	(method (doit)
		(self posn: (+ (newAct_2 x?) 4) (- (newAct_2 y?) 29))
		(super doit:)
	)
)

(instance swatArrives of Script
	(properties)
	
	(method (changeState newState &tmp egoX)
		(switch (= state newState)
			(0
				(= gunNotNeeded 0)
				((= newAct_2 (Actor new:))
					view: 54
					setLoop: 1
					setCel: 3
					posn: 64 156
					setPri: 11
					ignoreControl: -1
					init:
				)
				(pLight
					view: 54
					setLoop: 3
					setCel: 0
					setPri: 11
					setCycle: Forward
					ignoreActors:
					doit:
					init:
				)
				((= newProp_4 (Prop new:))
					view: 268
					loop: 0
					cel: 2
					posn: 48 144
					setPri: 10
					ignoreActors: 0
					init:
					hide:
				)
				((= newAct_3 (Actor new:))
					view: 30
					posn: 73 164
					setPri: 12
					setCycle: Walk
					init:
					hide:
				)
				((= newProp_5 (Prop new:))
					view: 51
					setLoop: 8
					setCel: 255
					posn: 82 151
					setPri: 12
					ignoreActors: 0
					init:
					hide:
				)
				(if (Btst 14)
					(newAct_2 stopUpd:)
					(newProp_5 stopUpd: show:)
					(newProp_4 stopUpd: show:)
					(newAct_3 stopUpd: show:)
					(client setScript: 0)
					(return)
				)
				(swatSound play:)
				(newAct_2 posn: -60 157)
				(HandsOff)
				(if (ego inRect: 0 130 135 170)
					(if (< (= egoX (ego x?)) 10) (= egoX 10))
					(ego setMotion: MoveTo egoX 170 self)
				else
					(self cue:)
				)
			)
			(1
				(if (>= (ego y?) 168)
					(ego loop: 3)
				else
					(ego loop: 2)
				)
				(ego stopUpd:)
				(newAct_2 posn: -60 157 setMotion: MoveTo 64 157 self)
				(Bset 14)
			)
			(2
				(HandsOn)
				(ego setMotion: 0 startUpd:)
				(newProp_5 show: setCel: 0 setCycle: EndLoop self)
			)
			(3
				(newProp_5 stopUpd:)
				(newAct_3 show:)
				(newProp_4 show:)
				(= cycles 1)
			)
			(4
				(newProp_4 stopUpd:)
				(newAct_2 stopUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance warrantArrives of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 stopUpd:)
				((= newAct_4 (Actor new:))
					setAvoider: Avoider
					view: 50
					setCycle: Walk
					posn: 85 180
					init:
				)
				(if (Btst 29)
					(HandsOn)
					(client setScript: 0)
				else
					(Bset 29)
					(newAct_4 posn: -10 180)
					(cond 
						((not (ego inRect: 0 170 95 200)) (newAct_4 setMotion: MoveTo 85 180 self))
						((>= (ego x?) 30)
							(newAct_4
								setMotion: MoveTo (- (ego x?) 20) (ego y?) self
							)
						)
						((and (<= (ego y?) 182) (>= (ego y?) 178)) (newAct_4 posn: -10 172 setMotion: MoveTo 15 172 self))
						(else (newAct_4 posn: -10 180 setMotion: MoveTo 15 180 self))
					)
				)
			)
			(1
				(newAct_4 setMotion: 0)
				(Print 25 76)
				(ego setMotion: 0 startUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance warrantLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 25 77)
				(Bset 13)
				(Bclr 29)
				(newAct_4
					setAvoider: Avoider
					setMotion: MoveTo -10 (newAct_4 y?) self
				)
			)
			(1
				(HandsOn)
				(newAct_4 dispose:)
				(client setScript: 0)
			)
		)
	)
)

(instance exitCar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== currentCar 13)
					((= newProp_3 (Prop new:))
						view: 51
						loop: 3
						cel: 0
						posn: [local24 0] [local24 1]
						setPri: 14
						init:
						setCycle: EndLoop self
					)
				else
					(self cue:)
				)
			)
			(1
				(= local9 0)
				(ego
					illegalBits: -32768
					posn: local13 175
					loop: 0
					cel: 0
					setCycle: Walk
					setPri: 13
				)
				(if keith
					(= workCarLocked 0)
					(newProp_3 dispose:)
					(keith
						ignoreActors: 0
						illegalBits: -32768
						posn: 193 197
						setCycle: Walk
						setStep: 3 2
						setPri: 15
						setAvoider: Avoider
						setMotion: MoveTo 190 200 self
					)
				else
					(= personalCarLocked 0)
					(self cue:)
				)
			)
			(2
				(if keith
					(keith setPri: -1)
					(if (< howFast 30)
						(keith stopUpd:)
					else
						(keith setMotion: Follow ego 500)
					)
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance talkWindow of Script
	(properties)
	
	(method (doit)
		(if (not (& $1000 (ego onControl:)))
			(client setScript: 0)
		)
	)
	
	(method (changeState newState &tmp [temp0 100])
		(switch (= state newState)
			(0
				(ego setMotion: 0 loop: 3)
				(cond 
					((< gamePhase 11) (Print 25 78))
					((== gamePhase 11) (Print 25 79))
					(else (Print 25 80))
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (event claimed?) (!= (event type?) saidEvent))
			(return)
		)
		(cond 
			((Said 'look/pane,dude,boy') (if (< gamePhase 11) (Print 25 81) else (Print 25 82)))
			((Said 'gave/cash') (if (< gamePhase 11) (Print 25 83) else (Print 25 84)))
			((Said 'chat/dude,boy')
				(cond 
					((< gamePhase 11) (Print 25 85))
					((== gamePhase 12) (Print 25 86))
					(else (Print 25 87))
				)
			)
			((Said 'display/badge,badge')
				(if (ego has: 7)
					(Print 25 88)
					(= showedBadgeToMotelManager 1)
				else
					(Print 25 89)
				)
			)
			((Said 'rent/chamber')
				(if (== gamePhase 12)
					(switch (Random 0 2)
						(0 (Print 25 90))
						(1 (Print 25 91))
						(2 (Print 25 92))
					)
				else
					(Print 25 93)
				)
			)
			((Said 'display/painting,(shot<mug),mugshot')
				(cond 
					((< gamePhase 11) (Print 25 94))
					((ego has: 12)
						(Print 25 95 #icon 112)
						(if keith (Print 25 96))
						(if (not (Btst 88))
							(Bset 1)
							(Bset 2)
							(SolvePuzzle 3 88)
						)
					)
					((ego has: 23) (Print 25 97 #icon 123))
					(else (Print 25 17))
				)
			)
			(
			(Said 'look,get,gave,display,ask/list,guest,register')
				(cond 
					((< gamePhase 11) (Print 25 94))
					((not showedBadgeToMotelManager) (Print 25 98))
					(else (Print 25 99))
				)
			)
			(
			(or (Said '[ask]/bains') (Said '[ask]/dude,boy/bains'))
				(if (< gamePhase 11)
					(Print 25 100)
				else
					(Print 25 101)
				)
			)
			(
				(or
					(Said '[ask,get]/cole,chamber')
					(Said '[ask,get]/dude,boy/bains,cole')
				)
				(if (< gamePhase 11)
					(Print 25 100)
				else
					(= global178 1)
					(Print 25 102)
					(if (not (Btst 88))
						(Bset 1)
						(Bset 2)
						(SolvePuzzle 3 88)
					)
				)
			)
			((Said 'display/warrant[<frisk]')
				(cond 
					((< gamePhase 11) (Print 25 94))
					((Btst 13) (Print 25 103) (= global174 1))
					(else (Print 25 17))
				)
			)
			(
			(or (Said 'replace/key') (Said 'gave/key/manager,boy'))
				(if (ego has: 27)
					(Print 25 104)
					(PutInRoom 27)
				else
					(Print 25 105)
				)
			)
			((Said '[ask,get]/key')
				(cond 
					((ego has: 27) (Print 25 30))
					((< gamePhase 11) (Print 25 94))
					((not showedBadgeToMotelManager) (Print 25 106))
					((not global174) (Print 25 107))
					((not global178) (Print 25 108))
					(else (Print 25 109) (ego get: 27) (SolvePuzzle 3 162))
				)
			)
			((Said 'arrest/dude,boy') (Print 25 110))
		)
	)
)

(instance kickInDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= gamePhase 12)
				(ego
					view: 286
					setLoop: 0
					setCel: 0
					posn: 251 155
					setPri: 11
					setCycle: EndLoop self
				)
			)
			(1
				(newProp_2
					setLoop: 7
					posn: 252 158
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(if keith
					(keith
						setLoop: -1
						startUpd:
						setCycle: Walk
						setMotion: Follow ego 15
					)
				)
				(ego
					view: 97
					setLoop: 2
					setCel: 0
					posn: 234 159
					setCycle: EndLoop self
				)
			)
			(3
				(Print 25 111)
				(Print 25 112)
				(EgoDead 25 113)
			)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= gamePhase 12)
				(if keith
					(Print 25 114 #draw)
					(keith
						view: 53
						setMotion: MoveTo 252 (keith y?) keithScript
					)
				)
				(if (or gunDrawn (not (ego has: 0)))
					(= cycles 4)
				else
					(= gunDrawn 1)
					(ego view: 4 setLoop: 0 setCel: 0 setCycle: EndLoop self)
				)
			)
			(1
				(Print 25 115)
				(swatSound number: 41 play:)
				(newProp_2
					setLoop: 7
					setCel: 0
					posn: 252 156
					setCycle: EndLoop
				)
				(if (ego inRect: 220 151 280 156)
					(= local38 1)
					(ego view: 97 setLoop: 2 setCel: 0 setCycle: EndLoop self)
				else
					(ego
						view: 557
						posn: 262 161
						setLoop: (if gunDrawn 0 else 1)
						setCel: 0
						setCycle: EndLoop self
					)
				)
			)
			(2
				(if (not local38)
					(Print 25 116)
					(if (ego has: 0)
						(= gunWindageScrew
							(switch (Random 1 2)
								(1 (Random 8 18))
								(2 (- 0 (Random 8 18)))
							)
						)
						(= gunElevationScrew
							(switch (Random 1 2)
								(1 (Random 6 14))
								(2 (- 0 (Random 6 14)))
							)
						)
						(= gunSightsAligned 0)
					)
					(if (Btst 14)
						(= cycles 2)
					else
						(ego
							view: (if gunDrawn 6 else 0)
							setLoop: -1
							setCycle: Walk
						)
						(if keith (keith view: 20))
						(= local40 1)
						(client setScript: 0)
						(HandsOn)
					)
				else
					(Print 25 112 #draw)
					(EgoDead 25 113)
				)
			)
			(3
				(Print 25 117 #at -1 20 #width 280)
				(newAct_3
					view: 268
					setLoop: 2
					posn: 73 164
					cycleSpeed: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(4
				(swatSound number: 41 play:)
				(newAct_3
					view: 268
					setLoop: 3
					cycleSpeed: 1
					setCel: 0
					setCycle: EndLoop
				)
				(gasBomb
					view: 268
					setLoop: 4
					setPri: 14
					setStep: 10 10
					setCycle: Forward
					ignoreActors:
					ignoreControl: -1
					posn: 75 140
					setMotion: MoveTo 225 111 self
					init:
				)
				(ego stopUpd:)
				(keith stopUpd:)
			)
			(5
				(gasBomb dispose:)
				(= local16 1)
				(newAct_3
					view: 30
					posn: 73 164
					setLoop: 0
					setCel: 0
					cycleSpeed: 0
					stopUpd:
				)
				((View new:)
					view: 251
					setLoop: 0
					setCel: 1
					setPri: 9
					posn: 229 121
					init:
					stopUpd:
					addToPic:
				)
				((= newProp_6 (Prop new:))
					view: 251
					setLoop: 8
					setCel: 0
					posn: 229 115
					setPri: 10
					init:
					setCycle: Forward
				)
				(= seconds 3)
				(if (< howFast 30) (= local2 100) else (= local2 200))
				(ego
					view: (if gunDrawn 6 else 0)
					setLoop: -1
					setCycle: Walk
				)
				(if keith (keith view: 20 setMotion: Follow ego 40))
				(= local40 1)
				(ego setMotion: 0 startUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance enterMotel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and keith local2) (Print 25 118))
				(ego
					ignoreControl: -1
					setLoop: -1
					setMotion: MoveTo 278 151 self
				)
			)
			(1
				(if local2
					(Print 25 119)
					(= seconds 2)
				else
					(HandsOn)
					(if local40 (cSound stop: loop: 1 number: 35 play:))
					(curRoom newRoom: 26)
				)
			)
			(2
				(HandsOff)
				(EgoDead
					{Congratulations, you have been successful in taking yourself out of the game.}
				)
			)
		)
	)
)

(instance keithScript of Script
	(properties)
	
	(method (cue)
		(keith setMotion: Follow ego 500)
		(if (< howFast 30) (keith stopUpd:))
	)
)
