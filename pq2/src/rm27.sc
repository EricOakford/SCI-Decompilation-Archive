;;; Sierra Script 1.0 - (do not remove this comment)
(script# 27)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm27 0
)
(synonyms
	(cop police)
	(body roberts)
)

(local
	mrG
	newAct_2
	newAct_3
	newProp_2
	newAct_4
	newProp
	local6
	local7
	local8
	local9
	local10
	local11
	local12
	local13
)
(procedure (localproc_000c)
	(return
		(if (ego has: 10)
			(return 1)
		else
			(Print 27 0)
			(return 0)
		)
	)
)

(procedure (localproc_2146)
	(return
		(if
		(and (ego inRect: 226 148 236 154) (== local9 1))
			(Print 27 22)
		else
			(switch currentCar
				(13
					(cond 
						((not (ego inRect: 268 152 290 161)) (Print 27 103))
						((== workCarLocked 1) (Print 27 104) (return 0))
						(workCarTrunkOpened (Print 27 105) (return 0))
						((ego has: 10) (Print 27 106) (return 0))
						(else (carScript changeState: 8) (return 1))
					)
				)
				(33
					(cond 
						((not (ego inRect: 268 152 290 161)) (Print 27 103))
						((== personalCarLocked 1) (Print 27 104))
						(else (carScript changeState: 8))
					)
				)
			)
		)
	)
)

(instance qDoor of Prop
	(properties)
)

(instance unTrunk of Prop
	(properties)
)

(instance quincyCarBlock of Block
	(properties
		top 154
		left 172
		bottom 188
		right 201
	)
)

(instance ourCarBlock of Block
	(properties
		top 158
		left 233
		bottom 171
		right 321
	)
)

(instance bwCarBlock of Block
	(properties
		top 137
		left 36
		bottom 147
		right 150
	)
)

(instance rm27 of Room
	(properties
		picture 27
		style $0008
	)
	
	(method (init)
		(super init:)
		(Load rsVIEW 0)
		(Load rsVIEW 20)
		(Load rsVIEW 253)
		(Load rsVIEW 54)
		(Load rsVIEW 51)
		(self setLocales: 153)
		(NormalEgo)
		(if (== currentCar 33) (ourCarBlock left: 250))
		(= local6 (not (= local8 (== prevRoomNum 28))))
		(if local8
			(ego observeBlocks: ourCarBlock)
			(HandsOn)
		else
			(HandsOff)
		)
		(if
		(and local8 (= currentCar 13) workCarTrunkOpened)
			(unTrunk
				view: 51
				loop: 5
				cel: 2
				posn: 250 172
				setPri: 13
				ignoreActors:
				init:
			)
		)
		(= local11 3)
		(cond 
			(local8 (= local9 1))
			(
				(and
					(== gamePhase 10)
					(> 90 captainWarningTimer)
					(> captainWarningTimer 1)
				)
				(= local9 3)
				(= captainWarningTimer 1)
				(= global182 3)
			)
			(
				(and
					(== gamePhase 10)
					(or
						(>= captainWarningTimer 90)
						(== captainWarningTimer 0)
					)
				)
				(= local9 1)
			)
			((< gamePhase 10) (= local9 0))
			((>= gamePhase 11) (= local9 2) (= global182 3))
		)
		(= gunFireState
			(cond 
				((== local9 1) 3)
				((== currentCar 13) 2)
				(else 1)
			)
		)
		(if (and (== gamePhase 10) (!= local9 3))
			(= captainWarningTimer 0)
			(= global159 0)
			(= isOnDuty 0)
		)
		((View new:)
			view: 253
			loop: 0
			cel: 0
			posn: 242 140
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 0
			posn: 10 140
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 3
			posn: 28 138
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 3
			posn: 277 143
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 3
			posn: 10 149
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 1
			posn: 221 140
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 1
			posn: 109 130
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 3
			posn: 194 130
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 2
			posn: 241 133
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 3
			posn: 17 130
			init:
			addToPic:
		)
		((View new:)
			view: 253
			loop: 0
			cel: 3
			posn: 292 133
			init:
			addToPic:
		)
		(if (== local9 1)
			((View new:)
				view: 54
				loop: 0
				cel: 3
				posn: 95 148
				init:
				ignoreActors:
				addToPic:
			)
			(ego observeBlocks: bwCarBlock)
			(if (and (< 0 global182) (< global182 3))
				(ego observeBlocks: quincyCarBlock)
			)
			((View new:)
				view: 86
				loop: 0
				cel: 0
				posn: 233 147
				init:
				addToPic:
			)
			((= mrG (Actor new:))
				view: 48
				posn: 178 136
				setStep: 3 4
				init:
				setCycle: Walk
				illegalBits: 0
				setScript: mrGScript
			)
		)
		((= newAct_2 (Actor new:))
			view: 10
			posn: (if (and (> 3 global182) (> global182 1))
				223
			else
				-100
			) 150
			loop: 1
			init:
			setCycle: Walk
			illegalBits: 0
			stopUpd:
		)
		((= newAct_3 (Actor new:))
			view: 162
			posn: 188
			(switch global182
				(1 264)
				(2 187)
				(else  264)
			)
			setLoop: 0
			init:
		)
		(= local7
			(if (== roomCarParked curRoomNum) (not local8) else 0)
		)
		(if (not local8) (= local6 1) else (= local6 0))
		((= newAct_4 (Actor new:))
			view: 54
			setStep: 1
			setLoop: 1
			setCel: (if (== currentCar 13) 1 else 5)
			setCycle: 0
			setMotion: 0
			posn: (if (and (not local8) (not local7)) 272 else 288) 169
			init:
			ignoreActors:
		)
		(self setScript: rm27Script)
	)
	
	(method (doit)
		(super doit:)
		(quincyScript doit:)
		(return (if (> local13 0) (-- local13) else 0))
	)
	
	(method (dispose)
		(carScript dispose:)
		(stopScript dispose:)
		(mrGScript dispose:)
		(quincyScript dispose:)
		(egoScript dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(evSAID
				(if (== local9 1)
					(if (ego inRect: 169 133 200 151)
						(cond 
							((Said 'drink/blood') (Print 27 1))
							(
								(or
									(Said 'get,remove,pick/sample,blood')
									(Said 'use/dropper,vial')
									(Said 'deposit/blood/vial')
								)
								(if (localproc_000c)
									(if (Btst 143)
										(Print 27 2)
									else
										(global119 startUpd: setPri: 0)
										(global118 startUpd: setPri: 0)
										(SolvePuzzle 1)
										(Print 27 3 #draw)
										(ego get: 28)
										(Bset 143)
										(global119 setPri: 13 stopUpd:)
										(Print 27 4 #draw)
									)
								)
							)
							(
								(or
									(Said 'make,get/print<feet')
									(Said 'use<(cast,plaster)')
									(Said 'make/plaster,cast,footprint,footprint')
									(Said 'get,cast/print,footprint')
								)
								(if (localproc_000c) (Print 27 5))
							)
							((or (Said 'use/camera') (Said 'get/painting'))
								(if (localproc_000c)
									(global124 forceUpd: setPri: 0)
									(SolvePuzzle 1 116)
									(Print 27 6 #draw)
									(global124 setPri: 14)
								)
							)
							(
								(and
									(or
										(Said 'use,get,remove/baggie')
										(Said 'get/hair,dirt')
									)
									(localproc_000c)
								)
								(Print 27 7)
							)
						)
					)
					(cond 
						((Said 'chat>')
							(cond 
								((Said '/friend') (if (== local9 1) (Print 27 8) else (event claimed: 0)))
								((Said '/gelepsi,cop,cop,dude')
									(if (== local9 1)
										(if (Random 0 1) (Print 27 9) else (Print 27 10))
									else
										(event claimed: 0)
									)
								)
								((Said '/coroner,dude')
									(cond 
										(local10 (Print 27 11))
										((and (> 3 global182) (> global182 1)) (if (Btst 9) (Print 27 12) else (Print 27 13)))
										(else (event claimed: 0))
									)
								)
								((Said '/dude') (Print 27 14))
							)
						)
						((Said 'read,look/note,threat,letter')
							(if (ego has: 6)
								((inventory at: 6) showSelf:)
							else
								(DontHave)
							)
						)
						((Said 'look,frisk>')
							(cond 
								((Said '/dude') (Print 27 15))
								((Said '/broad') (Print 27 16))
								((Said '/coroner')
									(if (and (> 3 global182) (> global182 1))
										(Print 27 17)
									else
										(Print 27 18)
									)
								)
								((Said '/gelepsi,cop') (if (== local9 1) (Print 27 19) else (Print 27 20)))
								((or (Said '<below/auto') (Said 'blood')) (Print 27 21))
								((Said 'frisk,(look<in)/auto')
									(if (and (== currentCar 13) (< (keith y?) 160))
										(Print 27 22)
									else
										(Print 27 23)
									)
								)
								((Said '/auto<coroner,white')
									(if (and (> 3 global182) (> global182 0))
										(Print 27 24)
									else
										(Print 27 25)
									)
								)
								((Said '/auto<cop,marked') (Print 27 26))
								((Said '/auto<blue,unmarked') (Print 27 27))
								((Said '/auto')
									(if (== local9 1) (Print 27 28) else (Print 27 29))
									(event claimed: 1)
								)
								((or (Said '/trunk,license') (Said '<in'))
									(if (ego inRect: 170 133 196 149)
										(curRoom newRoom: 28)
									else
										(event claimed: 0)
									)
								)
								((Said '/body')
									(cond 
										((Btst 9) (Print 27 30))
										((ego inRect: 170 133 196 149) (curRoom newRoom: 28))
										(else (event claimed: 0))
									)
								)
								(else (event claimed: 0))
							)
						)
					)
				)
				(cond 
					((or (Said 'affirmative') (Said 'display'))
						(if (> local13 0)
							(= local13 0)
							(Print 27 31)
						else
							(event claimed: 0)
						)
					)
					((Said 'n')
						(if (> local13 0)
							(= local13 0)
							(Print 27 32)
						else
							(Print 27 33)
						)
					)
					((Said 'look/trunk')
						(if
							(and
								(== currentCar 13)
								(ego inRect: 207 156 258 178)
								(cast contains: unTrunk)
							)
							(inventory
								carrying: {The car's trunk contains:}
								empty: {The car's trunk is empty.}
								showSelf: 13
							)
						else
							(Print 27 34)
						)
					)
					((Said 'look/building,warehouse') (Print 27 35))
					((Said 'look/fence') (Print 27 36))
					((Said 'look/hole,gap,rip') (Print 27 37))
					((Said 'look/blood') (if (== local9 1) (Print 27 38) else (Print 27 39)))
					((Said 'look/body')
						(if (or (Btst 9) (!= local9 1))
							(Print 27 40)
						else
							(Print 27 41)
						)
					)
					((Said 'look,read/sign') (Print 27 42))
					(
						(Said
							'look[<at,around][/!*,chamber,area,area,ave,district]'
						)
						(if (== local9 1)
							(Print 27 43)
						else
							(event claimed: 1)
							(Print 27 44)
						)
					)
					((Said '/hello[/!*]')
						(if
							(or
								(cast contains: keith)
								(cast contains: newAct_2)
								(cast contains: mrG)
							)
							(Print 27 45)
						else
							(Print 27 46)
						)
					)
					(
					(or (Said 'climb<in/trunk') (Said 'get<in/trunk')) (if (== local9 1) (Print 27 47) else (Print 27 48)))
					((Said 'look/registration') (if (== local9 1) (Print 27 49) else (Print 27 50)))
					((Said 'climb,go<through/hole,fence') (Print 27 51))
					((Said 'climb') (Print 27 52))
					((or (Said '/extender') (Said 'extender/')) (Print 27 53))
					((Said 'deposit,place/briefcase')
						(if (ego inRect: 207 156 258 178)
							(if workCarTrunkOpened
								(if (localproc_000c)
									(Print 27 54)
									(PutInRoom 10 13)
									(if (IsObject theFieldKit) (theFieldKit dispose:))
									(= fieldKitOpen 0)
								else
									(Print 27 55)
								)
							else
								(Print 27 56)
							)
						else
							(Print 27 57)
						)
					)
					((Said 'remove,get/briefcase')
						(if (ego inRect: 207 156 258 178)
							(if workCarTrunkOpened
								(if (InRoom 10 13)
									(Print 27 58)
									(ego get: 10)
								else
									(Print 27 59)
								)
							else
								(Print 27 56)
							)
						else
							(Print 27 57)
						)
					)
					((Said 'enter,walk,go,crawl/hole') (Print 27 60))
					((Said 'climb,jump/fence') (Print 27 61))
					((Said 'remove,get,move,hoist/body,dude')
						(cond 
							((!= local9 1) (Print 27 62))
							((== global182 0) (Print 27 63))
							((!= global182 2) (Print 27 64))
							((not (ego inRect: 170 133 196 149)) (Print 27 65))
							(else (quincyScript changeState: 9))
						)
					)
					((or (Said 'open/door') (Said 'get<in')) (if local6 (= global132 1) else (localproc_2146)))
					((Said 'enter/auto') (localproc_2146))
					((Said 'unlock/door')
						(if (ego inRect: 268 152 290 161)
							(cond 
								((and (== currentCar 13) (ego has: 3))
									(if (== workCarLocked 1)
										(= workCarLocked 0)
										(Print 27 66)
									else
										(Print 27 67)
									)
								)
								((== currentCar 13) (Print 27 68))
							)
							(cond 
								((and (== currentCar 33) (ego has: 2))
									(if (== personalCarLocked 1)
										(= personalCarLocked 0)
										(Print 27 66)
									else
										(Print 27 67)
									)
								)
								((== currentCar 33) (Print 27 68))
							)
						else
							(NotClose)
						)
					)
					((Said 'lock/door')
						(if (ego inRect: 268 152 290 161)
							(if (== currentCar 13)
								(if (== workCarLocked 0)
									(= workCarLocked 1)
									(Print 27 69)
								else
									(Print 27 70)
								)
							)
							(if (== currentCar 33)
								(if (== personalCarLocked 0)
									(= personalCarLocked 1)
									(Print 27 69)
								else
									(Print 27 70)
								)
							)
						else
							(NotClose)
						)
					)
					((Said 'open,unlock/trunk')
						(cond 
							((ego inRect: 170 133 196 149) (Print 27 71))
							((== currentCar 13)
								(if (ego inRect: 207 156 258 178)
									(cond 
										(workCarTrunkOpened (Print 27 72))
										((ego has: 3) (carScript changeState: 15) (= workCarTrunkOpened 1))
										(else (Print 27 73))
									)
								else
									(NotClose)
								)
							)
							(else (Print 27 74))
						)
					)
					((Said 'close,lock/trunk')
						(cond 
							((ego inRect: 170 133 184 149) (Print 27 75))
							((== currentCar 13)
								(if (ego inRect: 207 156 258 178)
									(if workCarTrunkOpened
										(carScript changeState: 17)
										(= workCarTrunkOpened 0)
									else
										(Print 27 76)
									)
								else
									(NotClose)
								)
							)
							(else (Print 27 74))
						)
					)
				)
			)
		)
	)
)

(instance rm27Script of Script
	(properties)
	
	(method (doit)
		(cond 
			(
			(and global132 local6 (not (cast contains: newAct_4))) (= global132 0) (carScript changeState: 0))
			(
			(and (== global132 (not local6)) (== (not local6) 1)) (= global132 0) (localproc_2146))
		)
		(if (and (not (ego onControl:)) (not local6))
			(Print 27 77)
			(cond 
				((<= (ego x?) 0) (ego setMotion: MoveTo 5 (ego y?)))
				((>= (ego x?) 320) (ego setMotion: MoveTo 315 (ego y?)))
				((>= (ego y?) 180) (ego setMotion: MoveTo (ego x?) 177))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 0
					posn: (if local8 174 else 30) (if local8 145 else -20)
					init:
					setMotion: 0
				)
				(if (== currentCar 13)
					((= keith (Actor new:))
						view: 20
						posn: -100 0
						setCycle: Walk
						setMotion: 0
						illegalBits: 0
						setAvoider: (Avoider new:)
						init:
					)
				)
				(if (and (== currentCar 13) local8)
					(keith
						setPri: 9
						setLoop: 2
						ignoreActors:
						posn: 239 156
						stopUpd:
					)
				)
				(if (not local8)
					(= workCarTrunkOpened 0)
					(stopScript init:)
					(newAct_4 setMotion: MoveTo 288 169 stopScript)
					(= global132 1)
				else
					(newAct_4 addToPic:)
				)
				(if (!= roomCarParked curRoomNum)
					(= roomCarParked curRoomNum)
				)
				(if (and local8 (== global182 0))
					(= global182 1)
					(if (Btst 153) (Print 27 78 #draw #at -1 50))
					(newAct_2 setScript: quincyScript)
				)
				(if (and local8 (Btst 43))
					(Bclr 43)
					(quincyScript changeState: 9)
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
					setStep: 1 2
					posn: 281 168
					setPri: 12
					setLoop: 0
					setCycle: 0
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo 277 154 self
				)
				(if (== currentCar 13)
					(keith loop: 0 cel: 1 posn: 291 170 startUpd:)
					((= newProp (Prop new:))
						view: 51
						loop: 3
						cel: 0
						posn: 306 163
						setPri: 13
						init:
						setCycle: EndLoop
					)
				)
			)
			(1
				(ego observeBlocks: ourCarBlock)
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
				(cond 
					((and (== currentCar 13) (!= local9 1)) (self cue:))
					((== currentCar 13)
						(if (< howFast 30)
							(keith stopUpd:)
						else
							(keith setMotion: Follow ego 500)
						)
					)
				)
			)
			(2
				(newProp dispose:)
				(keith setMotion: MoveTo 221 170 self)
			)
			(3
				(keith setMotion: MoveTo 170 141 self)
			)
			(4
				(keith setMotion: MoveTo 170 133 self)
			)
			(5
				(switch local9
					(1
						(Print 27 79 #at -1 22)
						(Print 27 80 #at -1 22)
						(keith
							ignoreActors:
							illegalBits: 0
							setMotion: MoveTo 235 131 self
						)
					)
					(2 (Print 27 81 #at -1 22))
					(3 (Print 27 82 #at -1 22))
					(0
						(Print 27 83 #at -1 160 #draw)
					)
				)
			)
			(6
				(keith setPri: 9 setMotion: MoveTo 239 156 self)
			)
			(7
				(keith ignoreActors: stopUpd:)
			)
			(8
				(HandsOff)
				(ego stopUpd: ignoreBlocks: ourCarBlock)
				(if (and (== currentCar 13) (< (keith y?) 158))
					(Print 27 84)
					(keith
						ignoreActors:
						illegalBits: 0
						setLoop: -1
						posn: (keith x?) 130
						setMotion: MoveTo 239 132 self
					)
				else
					(self changeState: 13)
				)
			)
			(9
				(keith setPri: -1 setMotion: MoveTo 294 132 self)
			)
			(10
				(keith setMotion: MoveTo 332 170 self)
			)
			(11
				(keith setMotion: MoveTo 294 171 self)
			)
			(12
				((= newProp (Prop new:))
					view: 51
					loop: 3
					cel: 0
					posn: 306 163
					setPri: 13
					init:
					setCycle: EndLoop self
				)
			)
			(13
				(if (== currentCar 13) (keith loop: 0))
				(ego
					ignoreActors: 1
					illegalBits: 0
					setPri: 12
					setLoop: 0
					setCycle: 0
					setStep: 1 2
					setMotion: MoveTo 281 166 self
				)
			)
			(14
				(curRoom newRoom: currentCar)
			)
			(15
				(= workCarTrunkOpened 1)
				(unTrunk
					view: 51
					loop: 5
					cel: 0
					posn: 250 172
					setPri: 13
					init:
					ignoreActors:
					setCycle: EndLoop self
				)
			)
			(16 (unTrunk stopUpd:))
			(17
				(= workCarTrunkOpened 0)
				(unTrunk
					view: 51
					loop: 5
					cel: 2
					posn: 250 172
					setPri: 13
					startUpd:
					setCycle: CycleTo 0 -1 self
				)
			)
			(18 (unTrunk dispose:))
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
				(newAct_4 ignoreActors: 0 addToPic:)
				(HandsOn)
			)
		)
	)
)

(instance mrGScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (self state?) 2)
				(not local6)
				(< gamePhase 11)
			)
			(self changeState: 3)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or local8 (Btst 42))
					(mrG posn: 88 150 setMotion: MoveTo 110 149 self)
				else
					(mrG setMotion: MoveTo 178 147 self)
				)
			)
			(1
				(mrG stopUpd:)
				(cond 
					(local8
						(cond 
							((Btst 9) (Print 27 85 #at 40 24) (= local13 1000))
							((not (Btst 44)) (Print 27 86 #at 40 24) (Bset 44))
						)
					)
					((Btst 42) (Print 27 87))
					(else (Bset 42) (mrG setMotion: MoveTo 186 149 self))
				)
			)
			(2
				(mrG setMotion: MoveTo 210 149)
			)
			(3
				(HandsOff)
				(ego loop: 1 stopUpd:)
				(Print 27 88 #at -1 24 #draw)
				(= seconds 2)
			)
			(4
				(Print 27 89 #at -1 24)
				(= seconds 2)
			)
			(5
				(mrG setMotion: MoveTo 192 149 self)
			)
			(6
				(Print 27 90 #at -1 24)
				(carScript changeState: 2)
				(= seconds 1)
			)
			(7
				(Print 27 91 #at -1 14)
				(= seconds 1)
			)
			(8
				(mrG setMotion: MoveTo 90 152 self)
			)
			(9
				(mrG setLoop: 0 stopUpd:)
				(Print 27 92 #at -1 60)
				(Print 27 93 #at 88 144 #draw)
				(ego setMotion: 0 startUpd:)
				(HandsOn)
			)
		)
	)
)

(instance quincyScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego inRect: 168 151 206 250)
					(Print 27 94)
					(ego setMotion: MoveTo 158 155 self)
				else
					(self cue:)
				)
			)
			(1
				(if (== global182 1)
					(= global182 2)
					(ego observeBlocks: quincyCarBlock stopUpd:)
					(newAct_3 setMotion: MoveTo 188 187 self)
				)
			)
			(2
				(newAct_3 stopUpd:)
				(qDoor
					view: 162
					posn: 214 165
					loop: 2
					cel: 0
					setPri: 14
					setCycle: EndLoop self
					init:
				)
			)
			(3
				(newAct_2
					posn: 217 170
					ignoreActors:
					startUpd:
					setLoop: 2
					setCycle: 0
				)
				(= seconds 1)
			)
			(4
				(qDoor setCycle: BegLoop)
				(newAct_2
					setLoop: -1
					setCycle: Walk
					setStep: 3 2
					setMotion: MoveTo 222 150 self
				)
			)
			(5
				(= global181 0)
				(newAct_2 loop: 1 ignoreActors: 0)
				(Print 27 95 #at -1 60 #draw)
				(= seconds 2)
			)
			(6
				(Print 27 96 #at -1 50)
				(if (ego inRect: 190 147 223 153)
					(ego setMotion: MoveTo 190 152)
				)
				(newAct_2 setMotion: MoveTo 196 152 self)
			)
			(7
				(ego loop: 0)
				(= global181 1)
				(newAct_2 setMotion: MoveTo 196 149 self)
			)
			(8
				(Print 27 97 #at -1 50)
				(ego setMotion: 0 startUpd:)
				(HandsOn)
			)
			(9
				(HandsOff)
				(SolvePuzzle 2)
				(Print 27 98)
				(Print 27 99)
				(mrG ignoreActors: setMotion: MoveTo 186 149 self)
				(egoScript changeState: 0)
			)
			(10
				((= newProp_2 (Prop new:))
					view: 162
					loop: 1
					cel: 0
					posn: 190 136
					setPri: 15
					setCycle: EndLoop
					init:
					ignoreActors:
				)
				(mrG setMotion: MoveTo 178 145)
				(newAct_2 ignoreActors: setMotion: MoveTo 180 152)
				(= seconds 2)
			)
			(11
				(mrG setMotion: MoveTo 181 150)
				(newAct_2 setMotion: MoveTo 182 155)
				(= seconds 2)
			)
			(12
				(Print 27 100 #at -1 67 #width 118)
				(= local10 1)
				(mrG setMotion: MoveTo 88 150)
				(newAct_2 setMotion: MoveTo 210 150 self)
				(Bset 9)
			)
			(13
				(newProp_2 setCel: 0)
				(newAct_2 setMotion: MoveTo 218 170 self)
			)
			(14
				(Print 27 101)
				(= global182 3)
				(= local10 0)
				(qDoor
					view: 162
					posn: 214 165
					loop: 2
					cel: 0
					setPri: 14
					setCycle: EndLoop self
					init:
				)
			)
			(15
				(qDoor setCycle: BegLoop)
				(newAct_2 hide:)
				(if (ego inRect: 162 187 212 240)
					(Print 27 102)
					(HandsOff)
					(ego setMotion: MoveTo 10 180 self)
				else
					(= cycles 10)
				)
			)
			(16
				(ego setMotion: 0 startUpd:)
				(HandsOn)
				(newAct_3 setMotion: MoveTo 188 390)
				(mrG setLoop: 0 ignoreActors: 0 stopUpd:)
				(qDoor dispose:)
				(newProp_2 dispose:)
				(ego ignoreBlocks: quincyCarBlock)
			)
		)
	)
)

(instance egoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 176 133 self)
			)
			(1
				(ego setMotion: MoveTo 188 133 self)
			)
			(2 (ego loop: 2 stopUpd:))
		)
	)
)
