;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include sci.sh)
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
	newProp
	camera
	newAct
	newProp_3
	[local4 2]
	local6
	local7
	local8
	local9
	local10
	local11
	local12
	local13
	local14
	local15
	local16
	local17
	local18
)
(procedure (localproc_000c)
	(Print &rest #at -1 130)
)

(procedure (localproc_192c)
	(return
		(switch currentCar
			(13
				(cond 
					((not (ego inRect: 230 118 250 127)) (localproc_000c 22 103))
					((== workCarLocked 1) (localproc_000c 22 104) (return 0))
					(workCarTrunkOpened (localproc_000c 22 105) (return 0))
					((ego has: 10) (localproc_000c 22 106) (return 0))
					(else (carScript changeState: 8) (return 1))
				)
			)
			(33
				(cond 
					((not (ego inRect: 230 118 250 127)) (localproc_000c 22 107))
					(personalCarLocked (localproc_000c 22 104))
					(else (carScript changeState: 8))
				)
			)
		)
	)
)

(instance unTrunk of Prop
	(properties)
)

(instance rm22 of Rm
	(properties
		picture 22
		style $0008
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
		(if (== currentCar 13) (= gunFireState 3))
		(if global165 (= local18 1))
		(if
		(and global165 (== ((inventory at: 0) owner?) 22))
			(= local18 1)
			((inventory at: 0) moveTo: 0)
			(= local14 1)
		)
		(Load rsVIEW 0)
		(Load rsVIEW 20)
		(Load rsVIEW 54)
		(Load rsVIEW 51)
		(= [local4 0] 265)
		(= [local4 1] 129)
		(= local10 240)
		(= local8 238)
		(= local9 247)
		(= local11 23)
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
		(if (== currentCar 33)
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
		((View new:)
			view: 211
			loop: 1
			cel: 0
			posn: 262 100
			init:
			stopUpd:
			addToPic:
		)
		((= newProp (Prop new:))
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
				(!= prevRoomNum local11)
			else
				0
			)
		)
		(if (!= prevRoomNum local11)
			(= local6 1)
			(User canControl: 0)
		else
			(= local6 0)
		)
		((= newAct (Act new:))
			view: 54
			setStep: 1
			setLoop: 1
			setCel: (if (== currentCar 13) 1 else 5)
			setCycle: 0
			setMotion: 0
			illegalBits: 0
			posn:
				(if (and (!= prevRoomNum local11) (not local7))
					local8
				else
					local9
				)
				134
			init:
		)
		(self setLocales: 153)
		(self setScript: rm22Script)
	)
)

(instance rm22Script of Script
	(properties)
	
	(method (doit)
		(cond 
			(
			(and (!= (mod (ego view?) 2) 0) (<= (ego y?) 122)) (ego view: (- (ego view?) 1)))
			(
			(and (!= (mod (ego view?) 2) 1) (> (ego y?) 122)) (ego view: (+ (ego view?) 1)))
		)
		(cond 
			(
				(and
					(== global132 local6)
					(== local6 1)
					(not (cast contains: newAct))
				)
				(= global132 0)
				(carScript changeState: 0)
			)
			(
			(and (== global132 (not local6)) (== (not local6) 1)) (= global132 0) (localproc_192c))
		)
		(cond 
			(
				(and
					(or (< (ego x?) 15) (> (ego x?) 296))
					(> (ego y?) 155)
					(not local13)
				)
				(localproc_000c 22 0)
				(= local13 1)
			)
			(
			(and local13 (< (ego x?) 295) (> (ego x?) 16)) (= local13 0))
			((and (ego inRect: 145 99 210 108) local16) (cameraScript changeState: 4))
			(
			(and (not (ego inRect: 145 99 210 108)) (not local16)) (cameraScript changeState: 0))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 0
					posn:
						(if (== prevRoomNum local11) 179 else 0)
						(if (== prevRoomNum local11) 106 else 0)
					init:
					setMotion: 0
				)
				(if (== currentCar 13)
					((= keith (Act new:))
						view: 20
						illegalBits: -16384
						posn:
							(if (!= prevRoomNum local11) (- (ego x?) 4) else 300)
							(if (!= prevRoomNum local11) (- (ego y?) 20) else 157)
						setCycle: Walk
						init:
					)
				)
				(if (!= prevRoomNum local11)
					(= workCarTrunkOpened 0)
					(stopScript init:)
					(newAct setMotion: MoveTo local9 134 stopScript)
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
					(newAct addToPic:)
				)
				(cond 
					((!= roomCarParked curRoomNum) (Bclr 142) (= roomCarParked curRoomNum))
					(local6 (= global132 1))
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'look>')
						(cond 
							((Said '/jailer') (localproc_000c 22 1))
							((Said '/friend')
								(if (and (== currentCar 13) (< (keith y?) 10))
									(if (ego inRect: 235 102 300 158)
										(localproc_000c 22 2)
									else
										(localproc_000c 22 3)
									)
								else
									(event claimed: 0)
								)
							)
							((Said '[<at,around][/building,garage,chamber]')
								(Print 22 4 #width 260 #at -1 120)
								(if (>= gamePhase 1) (Print 22 5 #width 280 #at -1 120))
							)
							((Said '[<at,up][/ceiling,roof]') (localproc_000c 22 6))
							((Said '[<at,down][/floor,dirt]') (localproc_000c 22 7))
							((Said '/auto<blue')
								(if (== currentCar 33)
									(localproc_000c 22 8)
								else
									(localproc_000c 22 9)
								)
							)
							((Said '/key[<locker]')
								(if local18
									(localproc_000c 22 10)
								else
									(localproc_000c 22 11)
								)
							)
							((Said '<below/auto') (localproc_000c 22 12))
							((Said '/auto')
								(if (ego inRect: 150 0 250 146)
									(if (== currentCar 13)
										(localproc_000c 22 13)
									else
										(localproc_000c 22 14)
									)
								else
									(localproc_000c 22 15)
								)
							)
							((Said '/cruiser') (localproc_000c 22 16))
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
									(localproc_000c 22 17)
								)
							)
							((Said '/sign,gate,fence')
								(if (> (ego x?) 235)
									(localproc_000c 22 18)
								else
									(localproc_000c 22 19)
								)
							)
							((Said '/wall') (localproc_000c 22 20) (localproc_000c 22 21 70 260))
							((Said '<in/locker')
								(if global165
									(if (ego inRect: 135 99 163 108)
										(if (!= ((inventory at: 0) owner?) 22)
											(localproc_000c 22 22)
										else
											(localproc_000c 22 23)
										)
									else
										(localproc_000c 22 24)
									)
								else
									(localproc_000c 22 25)
								)
							)
							((Said '/locker') (localproc_000c 22 26))
							((Said '/button,button') (localproc_000c 22 27))
							((Said '/camera') (localproc_000c 22 28))
							((Said '/pa,pa') (localproc_000c 22 29))
							((Said '/door') (localproc_000c 22 30))
							(else (event claimed: 0))
						)
					)
					((Said 'chat/friend')
						(if (and (== currentCar 13) (< (keith y?) 10))
							(if (ego inRect: 235 102 300 158)
								(localproc_000c 22 31)
							else
								(localproc_000c 22 32)
							)
						else
							(event claimed: 0)
						)
					)
					((or (Said 'use/extender') (Said 'extender'))
						(if (== currentCar 13)
							(localproc_000c 22 33)
						else
							(localproc_000c 22 34)
						)
					)
					((Said 'drive')
						(if local6
							(localproc_000c 22 35)
						else
							(localproc_000c 22 36)
						)
					)
					((Said 'enter/auto')
						(cond 
							((ego inRect: 230 118 250 127) (localproc_192c))
							((ego inRect: 230 129 260 138) (localproc_000c 22 37))
							(else (localproc_000c 22 38))
						)
					)
					((Said 'knock')
						(if (ego inRect: 145 99 205 108)
							(localproc_000c 22 39)
						else
							(localproc_000c 22 40)
						)
					)
					((Said 'open/auto') (localproc_192c))
					((Said 'unlock/gate') (localproc_000c 22 41))
					(
						(or
							(Said 'unlock,open/locker')
							(Said 'unlock,open/(door<locker)')
						)
						(cond 
							(global165 (localproc_000c 22 42))
							((ego inRect: 135 99 163 108)
								(if (== ((inventory at: 0) owner?) 22)
									(localproc_000c 22 43)
								else
									(localproc_000c 22 44)
								)
								(= global165 1)
								(= local18 1)
							)
							(else (localproc_000c 22 45))
						)
					)
					((Said 'enter,go[<in]/jail')
						(if (ego inRect: 145 99 205 108)
							(if (not local12)
								(localproc_000c 22 46)
							else
								(localproc_000c 22 47)
							)
						else
							(localproc_000c 22 48)
						)
					)
					((Said 'exit,(get<out)[/auto]') (= global132 1))
					(
						(or
							(Said 'press,use,ring/button,button,bell')
							(Said 'chat,signal,buzz,call/jailer')
						)
						(if (ego inRect: 185 99 210 108)
							(localproc_000c 22 49)
							(if local12
								(localproc_000c 22 50)
							else
								(= local12 1)
								(localproc_000c 22 51)
							)
						else
							(localproc_000c 22 52)
						)
					)
					((Said 'affirmative')
						(if (and local12 (ego inRect: 145 99 210 108))
							(localproc_000c 22 53)
						else
							(localproc_000c 22 54)
						)
					)
					((Said 'n')
						(if (and local12 (ego inRect: 145 99 210 108))
							(localproc_000c 22 55)
						else
							(localproc_000c 22 56)
						)
					)
					(
					(Said 'display/badge,card,badge,billfold,painting')
						(if local12
							(if (ego inRect: 145 99 205 108)
								(if (ego has: 7)
									(doorScript changeState: 0)
								else
									(localproc_000c 22 57)
								)
							else
								(localproc_000c 22 58)
							)
						else
							(localproc_000c 22 59)
						)
					)
					((Said 'call,holler/friend')
						(if (keith inRect: 280 150 320 170)
							(localproc_000c 22 60)
						else
							(localproc_000c 22 61)
						)
					)
					((Said 'get,remove/9mm')
						(cond 
							((ego has: 0) (AlreadyTook))
							((not (ego inRect: 135 99 163 108)) (localproc_000c 22 62))
							((not global165) (localproc_000c 22 63))
							(local14
								(localproc_000c 22 64)
								(EgoDead
									{Yes, even in peaceful Lytton, crimes of opportunity still occur. Next time, keep track of your piece.}
								)
							)
							((== ((inventory at: 0) owner?) 22) (localproc_000c 22 65) (ego get: 0) (= global165 0))
							(else (localproc_000c 22 66))
						)
					)
					((Said 'deposit,place,replace,throw,exit/9mm')
						(cond 
							((not (ego inRect: 135 99 163 108)) (localproc_000c 22 62))
							((not global165) (localproc_000c 22 63))
							((not (ego has: 0)) (localproc_000c 22 67))
							(gunDrawn (localproc_000c 22 68))
							(else (localproc_000c 22 69) (ego put: 0 22))
						)
					)
					(
					(Said 'deposit,place,replace,throw/key[<locker]')
						(if local18
							(localproc_000c 22 70)
						else
							(localproc_000c 22 34)
						)
					)
					((Said 'get/key<locker') (if local18 (AlreadyTook) else (localproc_000c 22 71)))
					((Said 'get/key')
						(if (and (ego inRect: 135 99 163 108) local18)
							(localproc_000c 22 72)
						else
							(localproc_000c 22 73)
						)
					)
					(
						(or
							(Said 'close,lock/locker')
							(Said 'close,lock/(door<locker)')
						)
						(if (ego inRect: 135 99 163 108)
							(if global165
								(= global165 0)
								(localproc_000c 22 74)
								(if (== ((inventory at: 0) owner?) 22)
									(SolvePuzzle 3 115)
								)
							else
								(localproc_000c 22 75)
							)
						else
							(localproc_000c 22 62)
						)
					)
					((Said 'open,move/gate,fence') (localproc_000c 22 76))
					((Said 'climb/gate,fence') (localproc_000c 22 77))
					((Said 'unlock/door')
						(cond 
							((ego inRect: 230 118 250 127)
								(cond 
									((== currentCar 13)
										(if (ego has: 3)
											(if workCarLocked
												(= workCarLocked 0)
												(localproc_000c 22 78)
											else
												(localproc_000c 22 79)
											)
										else
											(localproc_000c 22 80)
										)
									)
									((== currentCar 33)
										(if (ego has: 2)
											(if personalCarLocked
												(= personalCarLocked 0)
												(localproc_000c 22 78)
											else
												(localproc_000c 22 79)
											)
										else
											(localproc_000c 22 80)
										)
									)
								)
							)
							((ego inRect: 145 99 205 108)
								(if (not local12)
									(localproc_000c 22 46)
								else
									(localproc_000c 22 47)
								)
							)
							((ego inRect: 230 129 260 138) (localproc_000c 22 81))
							(else (localproc_000c 22 45))
						)
					)
					((Said 'lock/door')
						(cond 
							((ego inRect: 230 118 250 127)
								(if (== currentCar 13)
									(if (not workCarLocked)
										(= workCarLocked 1)
										(localproc_000c 22 82)
									else
										(localproc_000c 22 83)
									)
								)
								(if (== currentCar 33)
									(if (not personalCarLocked)
										(= personalCarLocked 1)
										(localproc_000c 22 82)
									else
										(localproc_000c 22 83)
									)
								)
							)
							((ego inRect: 230 129 260 138) (localproc_000c 22 84))
							(else (localproc_000c 22 45))
						)
					)
					((Said 'open,unlock/trunk')
						(cond 
							((== currentCar 13)
								(if (ego inRect: 176 123 206 135)
									(cond 
										(workCarTrunkOpened (Print 22 85))
										((ego has: 3) (carScript changeState: 14))
										(else (localproc_000c 22 86))
									)
								else
									(localproc_000c 22 87)
								)
							)
							((ego inRect: 176 123 206 135) (localproc_000c 22 88))
							(else (localproc_000c 22 87))
						)
					)
					((Said 'close,lock/trunk')
						(if (== currentCar 13)
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
							(localproc_000c 22 88)
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
								(if (not local12)
									(localproc_000c 22 46)
								else
									(localproc_000c 22 47)
								)
							)
							(local6 (= global132 1))
							((ego inRect: 230 129 260 138) (localproc_000c 22 84))
							(else (localproc_192c))
						)
					)
					((Said 'close/door') (localproc_000c 22 90))
					((Said 'deposit,place/briefcase')
						(if (ego inRect: 176 123 206 135)
							(if workCarTrunkOpened
								(if (ego has: 10)
									(localproc_000c 22 91)
									(PutInRoom 10 13)
									(if (IsObject theFieldKit) (theFieldKit dispose:))
									(= fieldKitOpen 0)
								else
									(localproc_000c 22 92)
								)
							else
								(localproc_000c 22 93)
							)
						else
							(localproc_000c 22 94)
						)
					)
					((Said 'remove,get/briefcase')
						(if (ego inRect: 176 123 206 135)
							(if workCarTrunkOpened
								(if (== ((inventory at: 10) owner?) 13)
									(localproc_000c 22 95)
									(ego get: 10)
								else
									(localproc_000c 22 96)
								)
							else
								(localproc_000c 22 93)
							)
						else
							(localproc_000c 22 94)
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
			(0 (= local16 1) (self cue:))
			(1
				(= local17 1)
				(camera setCycle: End self)
			)
			(2
				(= local17 0)
				(camera setCycle: Beg self)
			)
			(3 (self changeState: 1))
			(4
				(= local16 0)
				(camera setCycle: 0)
				(self cue:)
			)
			(5 (camera setCycle: CT 0 -1))
		)
	)
)

(instance doorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(localproc_000c 22 97)
				(ego setMotion: MoveTo 180 101 self)
			)
			(1
				(ego setLoop: 3)
				(newProp setCycle: End self)
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
				(if (and (== currentCar 13) (not local7))
					(keith posn: 250 136 loop: 0 cel: 1 startUpd:)
					((= newProp_3 (Prop new:))
						view: 51
						loop: 3
						cel: 0
						posn: [local4 0] [local4 1]
						setPri: 9
						init:
						setCycle: End
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
					illegalBits: -32768
				)
				(HandsOn)
				(if (== currentCar 13) (self cue:))
			)
			(2
				(if (== currentCar 13)
					(if local7
						(localproc_000c 22 98)
						(localproc_000c 22 99)
						(Bset 142)
					else
						(newProp_3 dispose:)
						(keith illegalBits: 0 setMotion: MoveTo 250 140 self)
					)
				)
			)
			(3
				(keith illegalBits: -16384 setMotion: MoveTo 260 145 self)
				(localproc_000c 22 100)
			)
			(4
				(keith setMotion: MoveTo 300 157 self)
			)
			(5 (keith stopUpd:))
			(8
				(HandsOff)
				(ego stopUpd:)
				(if (== currentCar 13)
					(if (Btst 142)
						(localproc_000c 22 101)
						(self changeState: 12)
					else
						(localproc_000c 22 102)
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
				((= newProp_3 (Prop new:))
					view: 51
					loop: 3
					cel: 0
					posn: [local4 0] [local4 1]
					setPri: 9
					init:
					setCycle: End self
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
					setCycle: End self
				)
			)
			(15 (unTrunk stopUpd:))
			(16
				(= workCarTrunkOpened 0)
				(unTrunk
					view: 51
					loop: 5
					cel: 2
					posn: 209 138
					setPri: 9
					startUpd:
					setCycle: CT 0 -1 self
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
				(if (== currentCar 13) (keith stopUpd:))
			)
			(1
				(= global132 1)
				(newAct stopUpd: addToPic:)
			)
		)
	)
)
