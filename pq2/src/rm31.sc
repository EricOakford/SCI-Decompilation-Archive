;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include sci.sh)
(use Main)
(use Intrface)
(use AutoDoor)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm31 0
)

(local
	car
	newProp
	[local2 2]
	[local4 2]
	local6
	local7
	local8
	local9
	[carRect 8]
	local18
	local19
	frontDoor
	local21
	local22
	local23
	local24
	local25
)
(procedure (localproc_000c)
	(Print &rest #at -1 15)
)

(procedure (localproc_1566)
	(return
		(switch currentCar
			(13
				(cond 
					(
						(not
							(ego
								inRect: [carRect 0] [carRect 1] [carRect 2] [carRect 3]
							)
						)
						(localproc_000c 31 76)
					)
					(workCarLocked (localproc_000c 31 77) (return 0))
					(workCarTrunkOpened (localproc_000c 31 78) (return 0))
					((ego has: 10) (localproc_000c 31 79) (return 0))
					(else (carScript changeState: 5) (return 1))
				)
			)
			(33
				(cond 
					(
						(not
							(ego
								inRect: [carRect 0] [carRect 1] [carRect 2] [carRect 3]
							)
						)
						(localproc_000c 31 76)
					)
					(personalCarLocked (localproc_000c 31 77))
					(else (carScript changeState: 5))
				)
			)
		)
	)
)

(instance unTrunk of Prop
	(properties)
)

(instance rm31 of Room
	(properties
		picture 31
		style $0000
	)
	
	(method (init)
		(super init:)
		(= perspective 70)
		(if (ego has: 36) (= local22 1))
		(if
			(= local6
				(if (== prevRoomNum 33) else (== prevRoomNum 13))
			)
			(HandsOff)
		else
			(HandsOn)
		)
		(Load rsVIEW 54)
		(Load rsVIEW 51)
		(Load rsVIEW 270)
		(Load rsVIEW 0)
		(if (== prevRoomNum 33) (Load rsVIEW 20))
		(if (== currentCar 13)
			(= [carRect 0] 19)
			(= [carRect 1] 156)
			(= [carRect 2] 31)
			(= [carRect 3] 164)
			(= [local2 0] 13)
			(= [local2 1] 152)
			(= local18 22)
		else
			(= [carRect 0] 32)
			(= [carRect 1] 158)
			(= [carRect 2] 47)
			(= [carRect 3] 165)
			(= [local2 0] 29)
			(= [local2 1] 155)
			(= local18 41)
		)
		(= local8 120)
		(= local9 30)
		(= local19 32)
		(= local21 0)
		((= frontDoor (AutoDoor new:))
			entranceTo: 32
			facingLoop: -1
			view: 270
			posn: 145 105
			locked: 1
			init:
			stopUpd:
		)
		(= local7
			(if (== roomCarParked curRoomNum)
				(!= prevRoomNum local19)
			else
				0
			)
		)
		(if (!= prevRoomNum local19)
			(= local6 1)
			(HandsOff)
		else
			(= local6 0)
			(HandsOn)
		)
		((= car (Actor new:))
			view: 54
			setStep: 3 3
			setLoop: 0
			setCel: (if (== currentCar 13) 1 else 5)
			setCycle: 0
			setMotion: 0
			posn:
				(if (and (!= local19 prevRoomNum) (not local7))
					local8
				else
					local9
				)
				(if (and (!= local19 prevRoomNum) (not local7))
					162
				else
					157
				)
			init:
			illegalBits: 0
		)
		(self setLocales: 153)
		(self setScript: rm31Script)
	)
	
	(method (dispose)
		(carScript dispose:)
		(stopScript dispose:)
		(DisposeScript 301)
		(super dispose:)
	)
)

(instance rm31Script of Script
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(>= gamePhase 8)
					(== (ego onControl: 1) 16384)
					(not local22)
				)
				(= local22 1)
				(localproc_000c 31 0)
			)
			((== (frontDoor doorState?) 2) (= perspective 0) (curRoom newRoom: local19))
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
			(and (== global132 (not local6)) (== (not local6) 1)) (= global132 0) (localproc_1566))
		)
		(if
		(and (>= gamePhase 8) (== currentCar 13) (not local25))
			(cond 
				((< (ego x?) 95)
					(if local24
						(= local24 0)
						(keith setMotion: Follow ego 500)
					)
				)
				((not local24) (= local24 1) (keith setMotion: Follow ego 34))
			)
		)
		(if
			(and
				(not local6)
				(or (<= (ego x?) -30) (>= (ego x?) 380))
			)
			(rm31Script changeState: 1)
		)
		(if
			(and
				(>= (ego y?) 233)
				(not local6)
				(< (rm31Script state?) 2)
			)
			(rm31Script changeState: 2)
		)
		(if
		(and (== (rm31Script state?) 2) (>= (ego y?) 240))
			(rm31Script changeState: 3)
		)
		(if (<= (ego y?) 108) (ego y: 110))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 0
					posn:
						(if (== prevRoomNum local19) 148 else 340)
						(if (== prevRoomNum local19) 118 else 340)
					init:
					setMotion: 0
				)
				(if (== currentCar 13)
					(if (== prevRoomNum local19)
						(if (< gamePhase 8)
							(= local25 1)
							((= keith (Actor new:))
								view: 20
								posn: 340 340
								init:
								setMotion: 0
							)
							(localproc_000c 31 1)
						else
							((= keith (Actor new:))
								view: 20
								posn: 148 112
								setCycle: Walk
								setAvoider: (Avoider new:)
								init:
								setMotion: 0
							)
						)
					else
						((= keith (Actor new:))
							view: 20
							posn: (ego x?) (- (ego y?) 25)
							setCycle: Walk
							setAvoider: (Avoider new:)
							init:
							setMotion: 0
						)
					)
				)
				(= [local4 0] 68)
				(= [local4 1] 162)
				(if
					(and
						(== currentCar 13)
						(== prevRoomNum local19)
						workCarTrunkOpened
					)
					(unTrunk
						view: 51
						loop: 4
						cel: 2
						posn: [local4 0] [local4 1]
						setPri: 11
						init:
					)
				)
				(if (!= prevRoomNum local19)
					(stopScript init:)
					(= workCarTrunkOpened 0)
					(car setMotion: MoveTo local9 157 stopScript)
				else
					(car stopUpd: addToPic:)
				)
				(cond 
					((!= roomCarParked curRoomNum) (= roomCarParked curRoomNum))
					(local6 (= global132 1))
				)
			)
			(1
				(Print 31 2)
				(localproc_000c 31 3)
				(ego setMotion: MoveTo 150 (ego y?))
			)
			(2
				(if (and (== currentCar 13) (>= gamePhase 8))
					(Print 31 4 #draw)
				else
					(localproc_000c 31 5)
				)
			)
			(3
				(Print 31 6 #time 5)
				(Print 31 7 #dispose)
				(ShakeScreen 5)
				(cls)
				(EgoDead
					{In the days that followed, it was most folks' opinion that Sonny Bonds was overly distracted by continuous and lengthy sessions playing a recently released computer game called POLICE QUEST II. This must be why he didn't see the Mack truck driven by the little old lady who lived in the house next to Marie.}
				)
			)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(evSAID
				(cond 
					((Said 'open,read,look[<in]/note,newspaper')
						(= local23 1)
						(if local22
							(if (not (ego has: 36))
								(localproc_000c 31 8)
							else
								(localproc_000c 31 9 25 3)
								(Print 31 10 #at -1 15 #icon 136 0 0)
							)
						else
							(localproc_000c 31 11)
						)
					)
					((Said 'look>')
						(cond 
							((Said '/trunk')
								(if (== currentCar 13)
									(if
										(and
											(ego inRect: 73 143 101 159)
											(cast contains: unTrunk)
										)
										(inventory
											carrying: {The car's trunk contains:}
											empty: {The car's trunk is empty.}
											showSelf: 13
										)
									else
										(localproc_000c 31 12)
									)
								else
									(localproc_000c 31 13)
								)
							)
							((Said '[<at,around][/!*,home,building]') (localproc_000c 31 14))
							((Said '/chamber') (localproc_000c 31 15))
							((Said '/tree,bush,lawn,grass,flower') (localproc_000c 31 16))
							((Said '/ave,sidewalk,driveway') (localproc_000c 31 17))
							((or (Said '<up,down') (Said '/air,dirt,roof')) (localproc_000c 31 18))
							((Said '/fence') (localproc_000c 31 19))
							((Said '/note,newspaper')
								(if local22
									(cond 
										((not (ego has: 36)) (localproc_000c 31 8))
										(local23
											(localproc_000c 31 9 25 3)
											(Print 31 10 #at -1 15 #icon 136 0 0)
										)
										(else (Print 31 20 #at -1 15 #icon 136 0 1))
									)
								else
									(localproc_000c 31 11)
								)
							)
							((Said '/wall') (localproc_000c 31 21))
							((Said '/auto')
								(cond 
									((== currentCar 13) (localproc_000c 31 22))
									((== currentCar 33) (localproc_000c 31 23))
								)
							)
							((Said '/friend')
								(if (== currentCar 13)
									(if (== (keith x?) 340)
										(if (< (ego x?) 101)
											(localproc_000c 31 24)
										else
											(localproc_000c 31 25)
										)
									else
										(localproc_000c 31 26)
									)
								else
									(localproc_000c 31 27)
								)
							)
							((Said '/pane')
								(if (== (ego onControl: 1) 8192)
									(localproc_000c 31 28)
								else
									(localproc_000c 31 29)
								)
							)
							((Said '/door<garage')
								(if (> (ego x?) 160)
									(localproc_000c 31 30)
								else
									(localproc_000c 31 31)
								)
							)
							((Said '/door<front,home')
								(if (and (>= gamePhase 8) (not local22))
									(localproc_000c 31 0)
									(= local22 1)
								else
									(localproc_000c 31 32)
								)
							)
							((Said '/door')
								(cond 
									((ego inRect: 173 112 305 127) (localproc_000c 31 30))
									((ego inRect: 122 106 165 124)
										(if (and (>= gamePhase 8) (not local22))
											(localproc_000c 31 0)
											(= local22 1)
										else
											(localproc_000c 31 32)
										)
									)
									((ego inRect: 0 135 101 185) (localproc_000c 31 33))
									(else (localproc_000c 31 31))
								)
							)
							((Said '/garage') (localproc_000c 31 30))
						)
					)
					((Said 'drive')
						(if local6
							(localproc_000c 31 34)
						else
							(localproc_000c 31 35)
						)
					)
					((Said 'get/key')
						(if (ego inRect: 122 106 165 124)
							(if (== (ego onControl: 1) 16384)
								(if (not local21)
									(localproc_000c 31 36)
									(localproc_000c 31 37)
									(= local21 1)
								)
								(frontDoor locked: 0)
							else
								(localproc_000c 31 38)
							)
						else
							(localproc_000c 31 39)
						)
					)
					((Said 'chat/friend')
						(if (== currentCar 13)
							(if (== (keith x?) 340)
								(if (< (ego x?) 101)
									(localproc_000c 31 40)
								else
									(localproc_000c 31 41)
								)
							else
								(localproc_000c 31 42)
							)
						else
							(localproc_000c 31 27)
						)
					)
					((Said 'get/note')
						(if local22
							(if (not (ego has: 36))
								(Print 31 43 #at -1 15 #icon 136 0 1)
								(ego get: 36)
							else
								(localproc_000c 31 44)
							)
						else
							(localproc_000c 31 11)
						)
					)
					((Said 'deposit,place/briefcase')
						(if (ego inRect: 73 143 101 159)
							(if workCarTrunkOpened
								(if (ego has: 10)
									(localproc_000c 31 45)
									(PutInRoom 10 13)
									(if (IsObject theFieldKit) (theFieldKit dispose:))
									(= fieldKitOpen 0)
								else
									(localproc_000c 31 46)
								)
							else
								(localproc_000c 31 47)
							)
						else
							(localproc_000c 31 48)
						)
					)
					((Said 'remove,get/briefcase')
						(if (ego inRect: 73 143 101 159)
							(if workCarTrunkOpened
								(if (InRoom 10 13)
									(localproc_000c 31 49)
									(ego get: 10)
								else
									(localproc_000c 31 50)
								)
							else
								(localproc_000c 31 47)
							)
						else
							(localproc_000c 31 48)
						)
					)
					((Said 'climb/fence')
						(if (== (ego onControl: 1) 4096)
							(localproc_000c 31 51)
						else
							(localproc_000c 31 52)
						)
					)
					((Said 'break/pane') (localproc_000c 31 53))
					((or (Said '/police') (Said 'open/police'))
						(if (== (ego onControl: 1) 16384)
							(localproc_000c 31 54)
						else
							(localproc_000c 31 55)
						)
					)
					((Said '/bains')
						(if (== (ego onControl: 1) 16384)
							(localproc_000c 31 56)
						else
							(localproc_000c 31 55)
						)
					)
					((Said '/cheeks')
						(if (== (ego onControl: 1) 16384)
							(localproc_000c 31 57)
						else
							(localproc_000c 31 55)
						)
					)
					((Said 'knock/door')
						(if (== (ego onControl: 1) 16384)
							(localproc_000c 31 58)
						else
							(localproc_000c 31 55)
						)
					)
					((or (Said 'open/door') (Said 'get<in'))
						(cond 
							((== (ego onControl: 1) 16384)
								(if (not local21)
									(localproc_000c 31 59)
									(localproc_000c 31 60)
									(= local21 1)
								)
								(frontDoor locked: 0)
							)
							((ego inRect: 173 112 305 127) (localproc_000c 31 61))
							(local6 (= global132 1))
							(else (localproc_1566))
						)
					)
					((Said 'enter,get<in/auto') (localproc_1566))
					((Said 'exit,get<out/auto') (= global132 1))
					((Said 'unlock/door')
						(cond 
							((== (ego onControl: 1) 16384)
								(localproc_000c 31 59)
								(localproc_000c 31 62)
								(frontDoor locked: 0)
							)
							((ego inRect: 173 112 305 127) (localproc_000c 31 63))
							(
								(ego
									inRect: [carRect 0] [carRect 1] [carRect 2] [carRect 3]
								)
								(cond 
									((and (== currentCar 13) (ego has: 3))
										(if workCarLocked
											(= workCarLocked 0)
											(localproc_000c 31 64)
										else
											(localproc_000c 31 65)
										)
									)
									((== currentCar 13) (localproc_000c 31 66))
								)
								(cond 
									((and (== currentCar 33) (ego has: 2))
										(if personalCarLocked
											(= personalCarLocked 0)
											(localproc_000c 31 64)
										else
											(localproc_000c 31 65)
										)
									)
									((== currentCar 33) (localproc_000c 31 66))
								)
							)
							(else (localproc_000c 31 67))
						)
					)
					((Said 'lock/door')
						(if
							(ego
								inRect: [carRect 0] [carRect 1] [carRect 2] [carRect 3]
							)
							(if (== currentCar 13)
								(if (not workCarLocked)
									(= workCarLocked 1)
									(localproc_000c 31 68)
								else
									(localproc_000c 31 69)
								)
							)
							(if (== currentCar 33)
								(if (not personalCarLocked)
									(= personalCarLocked 1)
									(localproc_000c 31 68)
								else
									(localproc_000c 31 69)
								)
							)
						else
							(localproc_000c 31 67)
						)
					)
					((Said 'open,unlock/trunk')
						(if (== currentCar 13)
							(if (ego inRect: 73 143 101 159)
								(cond 
									(workCarTrunkOpened (Print 31 70))
									((ego has: 3) (carScript changeState: 10))
									(else (localproc_000c 31 71))
								)
							else
								(NotClose)
							)
						else
							(localproc_000c 31 13)
						)
					)
					((Said 'close,lock/trunk')
						(if (== currentCar 13)
							(if (ego inRect: 73 143 101 159)
								(if workCarTrunkOpened
									(carScript changeState: 12)
									(= workCarTrunkOpened 0)
								else
									(Print 31 72)
								)
							else
								(NotClose)
							)
						else
							(localproc_000c 31 13)
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
				(HandsOff)
				(ego
					posn: local18 159
					setPri: -1
					loop: 1
					cel: 0
					setCycle: Walk
					startUpd:
				)
				((= newProp (Prop new:))
					view: 51
					loop: (if (== currentCar 13) 2 else 0)
					cel: 0
					posn: [local2 0] [local2 1]
					setPri: 12
					init:
					setCycle: EndLoop self
				)
				(if (== currentCar 13)
					(= local25 1)
					(keith
						posn: 35 156
						setStep: 1 2
						setLoop: 1
						setCel: 0
						setPri: 9
						ignoreActors: 1
						illegalBits: 0
						startUpd:
						setMotion: MoveTo 38 146
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
				(newProp dispose:)
				(self cue:)
			)
			(2
				(HandsOn)
				(if (and (== currentCar 13) (>= gamePhase 8))
					(keith
						setStep: 3 2
						setCycle: Walk
						setLoop: -1
						setPri: 8
						ignoreActors: 0
						illegalBits: -32768
						setMotion: MoveTo 38 146 self
					)
				else
					(self cue:)
				)
			)
			(3
				(cond 
					((and (== currentCar 13) (>= gamePhase 8))
						(= local25 0)
						(keith setPri: -1)
						(localproc_000c 31 73)
						(keith setMotion: Follow ego 500)
					)
					((== currentCar 13)
						(localproc_000c 31 74 83)
						(keith
							ignoreActors: 1
							illegalBits: 0
							setMotion: MoveTo 35 155 self
						)
					)
				)
			)
			(4
				(keith posn: 340 340 ignoreActors: 0)
			)
			(5
				(HandsOff)
				(ego stopUpd:)
				(if (and (== currentCar 13) (< (keith x?) 300))
					(= local25 1)
					(localproc_000c 31 75)
					(if (> (keith y?) 150)
						(keith
							ignoreActors:
							illegalBits: 0
							setMotion: MoveTo 96 157 self
						)
					else
						(self cue:)
					)
				else
					(self changeState: 8)
				)
			)
			(6
				(keith
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 96 136 self
				)
			)
			(7
				(keith setPri: 10 setMotion: MoveTo 41 148 self)
			)
			(8
				(ego setPri: 12)
				((= newProp (Prop new:))
					view: 51
					loop: (if (== currentCar 13) 2 else 0)
					cel: 0
					posn: [local2 0] [local2 1]
					setPri: 11
					init:
					setCycle: EndLoop self
				)
				(if (== currentCar 13)
					(keith
						ignoreActors: 1
						illegalBits: 0
						setLoop: 1
						setStep: 1 2
						setMotion: MoveTo 42 153
					)
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
					loop: 4
					cel: 0
					posn: [local4 0] [local4 1]
					setPri: 11
					init:
					setCycle: EndLoop self
				)
			)
			(11 (unTrunk stopUpd:))
			(12
				(= workCarTrunkOpened 0)
				(unTrunk
					view: 51
					loop: 4
					cel: 2
					posn: [local4 0] [local4 1]
					setPri: 11
					startUpd:
					setCycle: CycleTo 0 -1 self
				)
			)
			(13 (unTrunk dispose:))
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
				(car stopUpd: addToPic:)
			)
		)
	)
)
