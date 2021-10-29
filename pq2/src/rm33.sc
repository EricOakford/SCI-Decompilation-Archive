;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include game.sh)
(include system.sh)
(include keys.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm33 0
	ExitPersonalCar 1
)

(local
	local0
	thePrevRoomNum
	local2
	local3
	local4
	local5
	local6
	local7
	newProp
)
(procedure (ExitPersonalCar)
	(if (and (== local6 local4) (== local7 local5))
		(Print 33 0)
		(ego setPri: -1)
		(if (and (Btst 165) (== thePrevRoomNum 25))
			(= thePrevRoomNum 225)
		)
		(ego setStep: 3 2)
		(curRoom newRoom: thePrevRoomNum)
	else
		(Print 33 1)
	)
)

(procedure (localproc_000e)
	(if (== gamePhase 8) (= captainWarningTimer 10))
	(lines startUpd:)
	(extraScene hide:)
	(rightScene show: startUpd:)
	(leftScene show: startUpd:)
	(= local0 1)
	(= roomCarParked 33)
	(Bclr 40)
)

(instance sonny of View
	(properties)
)

(instance wheel of View
	(properties)
)

(instance hand of View
	(properties)
)

(instance street of Prop
	(properties)
)

(instance lines of Actor
	(properties)
)

(instance rightScene of Actor
	(properties)
)

(instance leftScene of Actor
	(properties)
)

(instance extraScene of View
	(properties)
)

(instance shoulder1 of View
	(properties)
)

(instance shoulder2 of View
	(properties)
)

(instance gloveBox of View
	(properties)
)

(instance gloveBoxDoor of Prop
	(properties)
)

(instance smallFlashLight of View
	(properties)
)

(instance businessCard of View
	(properties)
)

(instance registration of View
	(properties)
)

;;;(instance leftFwd of Forward
;;;	(properties)
;;;)
;;;
;;;(instance rightFwd of Forward
;;;	(properties)
;;;)
;;;
;;;(instance lineFwd of Forward
;;;	(properties)
;;;)

(instance aTimer of Timer
	(properties)
)

(instance rm33 of Room
	(properties
		picture 33
		style $0000
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(User canInput: 1)
		(curRoom setLocales: 153)
		(Load VIEW 71)
		(Load VIEW 200)
		(Load VIEW 269)
		(ego
			setPri: -1
			setLoop: -1
			setCel: -1
			posn: 0 0
			ignoreActors: 0
			illegalBits: -32768
			init:
		)
		(= currentCar 33)
		(= local0 0)
		(= gunDrawn 0)
		(= thePrevRoomNum prevRoomNum)
		(= local6
			(switch prevRoomNum
				(14 0)
				(25 60)
				(225 60)
				(29 120)
				(67 180)
				(61 220)
				(22 140)
				(31 40)
				(27 62)
				(1 122)
			)
		)
		(= local7
			(switch prevRoomNum
				(14 100)
				(25 120)
				(29 120)
				(67 120)
				(61 0)
				(22 20)
				(31 80)
				(27 60)
				(1 60)
			)
		)
		(switch local6
			(0 (Load VIEW 329))
			(60 (Load VIEW 332))
			(120 (Load VIEW 331))
			(180 (Load VIEW 328))
			(220 (Load VIEW 326))
			(140 (Load VIEW 334))
			(40 (Load VIEW 333))
			(62 (Load VIEW 330))
			(122 (Load VIEW 327))
		)
		(= local5 local7)
		(= local4 local6)
		(if (== global137 1)
			(= local2 1)
			(gloveBoxScript changeState: 0)
		)
		(if (or (== gamePhase 5) (== gamePhase 10))
			(if global159 (= global159 3))
			(if captainWarningTimer
				(= captainWarningTimer 3)
				(= global159 600)
			)
		)
		(self setScript: rm33Script)
	)
	
	(method (dispose)
		(aTimer dispose: delete:)
		(super dispose:)
	)
)

(instance rm33Script of Script
	(properties)
	
	(method (doit)
		(if local0
			(if (not (< howFast 30))
				(cond 
					((<= (hand y?) 156) (hand posn: 145 (+ (hand y?) 1)))
					((>= (hand y?) 160) (hand posn: 145 (- (hand y?) 1)))
					(else (hand posn: 145 (+ (hand y?) (Random -2 2))))
				)
			)
			(if
				(and
					(or (== global159 1) (== captainWarningTimer 1))
					(not local3)
				)
				(= local3 1)
				(self changeState: 1)
			else
				(if (> global159 1) (-- global159))
				(if (> captainWarningTimer 1) (-- captainWarningTimer))
			)
			(cond 
				((> local6 local4) (-- local6))
				((< local6 local4) (++ local6))
				((> local7 local5) (-- local7))
				((< local7 local5) (++ local7))
				((and local0 (not local3))
					(if (and (Btst 165) (== thePrevRoomNum 25))
						(= thePrevRoomNum 225)
					)
					(ego setStep: 3 2)
					(curRoom newRoom: thePrevRoomNum)
				)
			)
		else
			(lines stopUpd:)
			(rightScene stopUpd:)
			(leftScene stopUpd:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(PutInRoom 34)
				(sonny
					view: 71
					posn: 78 104
					loop: 0
					cel: 0
					setPri: 12
					init:
					addToPic:
				)
				(wheel
					view: 71
					posn: 130 135
					loop: 0
					cel: 2
					setPri: 12
					init:
					addToPic:
				)
				(shoulder1
					view: 71
					posn: 126 139
					loop: 0
					cel: 4
					setPri: 12
					init:
					addToPic:
				)
				(shoulder2
					view: 71
					posn: 133 167
					loop: 0
					cel: 4
					setPri: 12
					init:
					addToPic:
				)
				(street
					view: 71
					posn: 155 111
					setPri: 1
					setLoop: 5
					cel: 0
					init:
					addToPic:
				)
				(lines
					view: 71
					posn: 149 114
					setPri: 2
					setLoop: 4
					setCycle: Forward
					illegalBits: 0
					init:
					stopUpd:
				)
				(leftScene
					view: 200
					posn: 102 103
					setPri: 0
					setLoop: 0
					cel: 1
					setCycle: Forward
					illegalBits: 0
					init:
					stopUpd:
					hide:
				)
				(rightScene
					view: 200
					posn: 218 103
					setPri: 0
					setLoop: 1
					setCycle: Forward
					illegalBits: 0
					init:
					stopUpd:
					hide:
				)
				(hand
					view: 71
					posn: 145 159
					setPri: 12
					loop: 0
					init:
					cel: 3
				)
				(if (< howFast 30) (hand stopUpd:))
				(extraScene
					view:
						(switch local6
							(0 329)
							(60 332)
							(120 331)
							(180 328)
							(220 326)
							(140 334)
							(40 333)
							(62 330)
							(122 327)
						)
					setLoop: 0
					setCel: 0
					posn: 175 112
					setPri: 3
					setLoop: 0
					init:
					stopUpd:
				)
			)
			(1 (= seconds 5))
			(2
				((= newProp (Prop new:))
					view: 163
					posn: 148 69
					setPri: 13
					init:
					setCycle: Forward
				)
				(= seconds 3)
			)
			(3
				(Print 33 2 #at -1 120)
				(Print 33 3 #at -1 120)
				(leftScene cycleSpeed: 1 setMotion: MoveTo 98 104)
				(rightScene cycleSpeed: 1 setMotion: MoveTo 212 102)
				(lines cycleSpeed: 1 setMotion: MoveTo 140 114)
				(= seconds 2)
			)
			(4
				(lines cycleSpeed: 2)
				(leftScene cycleSpeed: 2)
				(rightScene cycleSpeed: 2)
				(= seconds 3)
			)
			(5
				(lines addToPic:)
				(leftScene addToPic:)
				(rightScene addToPic:)
				(= local0 0)
				(= seconds 4)
			)
			(6
				(Print 33 4 #at -1 120)
				(= seconds 3)
			)
			(7
				(Print 33 5 #at -1 120)
				(Print 33 6)
				(NormalEgo)
				(curRoom newRoom: 300)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(if
					(or
						(== (= temp0 (event message?)) KEY_F6)
						(== temp0 KEY_F8)
						(== temp0 KEY_F10)
					)
					(Print 33 7)
					(event claimed: 1)
				else
					(event claimed: 0)
				)
			)
			(saidEvent
				(cond 
					((Said 'extender/dispatch') (Print 33 8))
					((Said 'look/ignition') (if (InRoom 2 0) (Print 33 9) else (Print 33 10)))
					((Said 'look/speedometer,speed') (if local0 (Print 33 11) else (Print 33 12)))
					((Said 'look/pane')
						(Print 33 13)
						(switch local6
							(0 (Print 33 14))
							(60 (Print 33 15))
							(120 (Print 33 16))
							(180 (Print 33 17))
							(220 (Print 33 18))
							(140 (Print 33 19))
							(40 (Print 33 20))
							(62 (Print 33 21))
							(122 (Print 33 22))
							(else  (Print 33 23))
						)
					)
					((Said 'look/mirror')
						(if local3
							(Print 33 24)
						else
							(switch (Random 0 2)
								(0 (Print 33 25))
								(1 (Print 33 26))
								(2 (Print 33 27))
							)
						)
					)
					((Said 'look/dash') (Print 33 28) (if (InRoom 2 0) (Print 33 29)))
					((Said 'look/key,ring') (if (InRoom 2 0) (Print 33 29) else (event claimed: 0)))
					((Said 'look/wheel[<steering]') (Print 33 30))
					((Said 'look/back,(bench<back)') (Print 33 31))
					((Said 'look/air,tree,home,cloud') (Print 33 32))
					((Said 'adjust') (Print 33 0))
					((Said '/extender') (Print 33 33) (Print 33 34))
					(
						(or
							(Said 'wear,fasten,use,buckle,(deposit<on)/belt,belt')
							(Said 'buckle')
						)
						(Print 33 35)
					)
					((Said '(turn<on),look/heat,air,conditioner,fan') (Print 33 36))
					((Said '/tray,ashtray') (Print 33 37))
					((Said '/visor') (Print 33 38))
					(
						(or
							(Said 'begin[/auto,ignition,engine]')
							(Said 'turn/key')
							(Said 'turn<on/engine,auto')
							(Said 'deposit/key/ignition')
						)
						(PutInRoom 2 0)
						(Print 33 39)
					)
					(
						(or
							(Said 'cease[/auto,engine]')
							(Said 'turn<off/engine,auto')
							(Said 'switch<off/engine,auto')
						)
						(if (== local4 local6) (Print 33 40) else (Print 33 1))
					)
					((Said 'open/box,compartment')
						(if (== global137 1)
							(Print 33 41)
						else
							(= global137 1)
							(= local2 0)
							(gloveBoxScript changeState: 0)
						)
					)
					((Said 'close/box,compartment')
						(if (== global137 0)
							(Print 33 42)
						else
							(= global137 0)
							(gloveBoxScript changeState: 2)
						)
					)
					((Said 'look/box,compartment')
						(if (cast contains: gloveBox)
							(inventory
								carrying: {In the glove compartment you see:}
								showSelf: 33
							)
						else
							(Print 33 43)
						)
					)
					((Said 'get,remove/key,ring')
						(cond 
							((not (InRoom 2 0)) (Print 33 44))
							((not local0) (ego get: 2) (Print 33 45) (SolvePuzzle 1 102))
							(else (Print 33 1))
						)
					)
					((Said 'get,remove/card')
						(cond 
							((ego has: 37) (AlreadyTook))
							((and global137 (InRoom 37))
								(ego get: 37)
								(businessCard dispose:)
								(Print 33 46 #draw)
								(SolvePuzzle 1 103)
							)
							(else (Print 33 47))
						)
					)
					((Said 'deposit,replace/card')
						(cond 
							((not (ego has: 37)) (DontHave))
							((not global137) (Print 33 43))
							(else
								(Print 33 46 #draw)
								(ego put: 37 33)
								(businessCard
									view: 269
									posn: 192 132
									setPri: 15
									loop: 0
									cel: 2
									init:
									stopUpd:
								)
							)
						)
					)
					((Said 'look,get/newspaper,registration')
						(if (== global137 0)
							(Print 33 48)
						else
							(registration startUpd: hide:)
							(Print 33 49 #mode 1 #draw)
							(Print 33 50)
							(registration show: stopUpd:)
						)
					)
					(
						(Said
							'look/building,ave,air,cloud,sun,barn,home,airplane,airport,lot,fence,cove,inn,bush,tree,garage,sign,mall,store,cafe,warehouse'
						)
						(Print 33 51)
					)
					((Said 'look[<at,around,in][/!*,auto]') (Print 33 52))
					((Said 'beep,press/horn') (Print 33 53))
					((Said 'drive/auto') (Print 33 54))
					((Said 'drive,go>')
						(if (and (== gamePhase phaseMALL) (Btst fBeenAtMallCrimeScene))
							(= gamePhase 4)
						)
						(if (and (== gamePhase 12) (not (Btst fDroveToMotel)))
							(if (Said '/inn[<snuggler,to]')
								(event claimed: 0)
							else
								(Bset fDroveToMotel) ;165
							)
						)
						(PutInRoom iKeyRing 0) 
						(cond 
							(
								(or
									(Said '/home[<barn,to]')
									(Said '/barn[<police,to]')
									(Said '/office,homicide')
								)
								(cond 
									((!= thePrevRoomNum 1)
										(= thePrevRoomNum 1)
										(= local4 122)
										(= local5 60)
										(localproc_000e)
									)
									(local0 
										(Print 33 55)
									)
									(else 
										(Print 33 56)
									)
								)
							)
							((Said '/jail[<lytton,to]')
								(cond 
									((!= thePrevRoomNum 22)
										(= thePrevRoomNum 22)
										(= local4 140)
										(= local5 20)
										(localproc_000e)
									)
									(local0
										(Print 33 55)
									)
									(else
										(Print 33 56)
									)
								)
							)
							((Said '/cove[<cotton,to]')
								(cond 
									((!= thePrevRoomNum 61)
										(= thePrevRoomNum 61)
										(= local4 220)
										(= local5 0)
										(localproc_000e)
									)
									(local0
										(Print 33 55)
									)
									(else
										(Print 33 56)
									)
								)
							)
							((Said '/airport[<lytton,to]')
								(cond 
									((!= thePrevRoomNum 14)
										(= thePrevRoomNum 14)
										(= local4 0)
										(= local5 100)
										(localproc_000e)
									)
									(local0
										(Print 33 55)
									)
									(else
										(Print 33 56)
									)
								)
							)
							((Said '/inn[<snuggler,to]')
								(cond 
									((!= thePrevRoomNum 25)
										(= thePrevRoomNum 25)
										(= local4 60)
										(= local5 120)
										(localproc_000e)
									)
									(local0
										(Print 33 55)
									)
									(else
										(Print 33 56)
									)
								)
							)
							(
								(or
									(Said '/home[<cheeks,to]')
									(Said '/cheeks')
									(Said '/ave<peach<lonny<222')
									(Said '/peach<lonny<222')
								)
								(cond 
									((!= thePrevRoomNum 31)
										(= thePrevRoomNum 31)
										(= local4 40)
										(= local5 80)
										(localproc_000e)
									)
									(local0
										(Print 33 55)
									)
									(else
										(Print 33 56)
									)
								)
							)
							(
							(or (Said '/cafe[<arnie,to]') (Said '/arnie,date,chow'))
								(cond 
									((!= thePrevRoomNum 29)
										(= thePrevRoomNum 29)
										(= local4 120)
										(= local5 120)
										(localproc_000e)
									)
									(local0
										(Print 33 55)
									)
									(else
										(Print 33 56)
									)
								)
							)
							(
								(or
									(Said '/area<death>')
									(Said '/death[<area,to]>')
									(Said '/warehouse')
									(Said '/district<warehouse')
									(Said '/ave<rose<lonny<160')
									(Said '/rose<lonny<160')
								)
								(cond 
									(
										(and
											(or (Said '/area<death') (Said '/death'))
											(!= gamePhase 10)
										)
										(Print 33 57)
									)
									((!= thePrevRoomNum 27)
										(= thePrevRoomNum 27)
										(= local4 62)
										(= local5 60)
										(localproc_000e)
									)
									(local0
										(Print 33 55)
									)
									(else
										(Print 33 56)
									)
								)
							)
							(
								(or
									(Said '/mall[<(tree<fig),to]')
									(Said '/tree<fig')
									(Said '/mall,center<shopping')
								)
								(cond 
									((!= thePrevRoomNum 67)
										(= thePrevRoomNum 67)
										(= local4 180)
										(= local5 120)
										(localproc_000e)
									)
									(local0
										(Print 33 55)
									)
									(else
										(Print 33 56)
									)
								)
							)
							((Said '/ave<fig<5556')
								(Print 33 58) (User canInput: 1)
							)
							(
								(or
									(Said '/castle<caffeine[<carol,to]')
									(Said '/willie<drunk')
									(Said '/chamber<blue')
									(Said '/delphoria[<hotel,to]')
								)
								(Print 33 59)
							)
							((Said '/steelton,houston,coarsegold')
								(User canInput: 1) (Print 33 60)
							)
							((Said '/[*]')
								(User canInput: 1)
								(Print 33 61)
							)
							(else
								(event claimed: 1)
								(Print 33 62)
							)
						)
					)
					(
						(or
							(Said 'exit[/auto]')
							(Said 'get<out')
							(Said 'open/door')
						)
						(ExitPersonalCar)
					)
				)
			)
		)
	)
)

(instance gloveBoxScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gloveBox
					view: 269
					loop: 0
					cel: 0
					posn: 201 151
					setPri: 14
					init:
					ignoreActors:
					stopUpd:
				)
				(gloveBoxDoor
					view: 269
					loop: 1
					cel: (if (not local2) 0 else 1)
					posn: 197 144
					setPri: 15
					init:
					ignoreActors:
				)
				(registration
					view: 269
					loop: 0
					cel: 1
					posn: 198 135
					setPri: 15
					init:
					ignoreActors:
					stopUpd:
				)
				(if (InRoom 37 33)
					(businessCard
						view: 269
						posn: 192 132
						setPri: 15
						loop: 0
						cel: 2
						init:
						stopUpd:
					)
				)
				(if (not local2) (aTimer setReal: self 2))
			)
			(1 (gloveBoxDoor setCel: 1))
			(2
				(gloveBoxDoor setCel: 0)
				(aTimer setReal: self 2)
			)
			(3
				(if (!= (cast indexOf: businessCard) -1)
					(businessCard dispose:)
				)
				(registration dispose:)
				(if (!= (cast indexOf: smallFlashLight) -1)
					(smallFlashLight dispose:)
				)
				(gloveBox dispose:)
				(gloveBoxDoor dispose:)
			)
		)
	)
)
