;;; Sierra Script 1.0 - (do not remove this comment)
(script# 67)
(include system.sh)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm67 0
)

(local
	local0
	local1
	local2
	[local3 4]
	[carDoorPosn 2]
	[local9 2]
	[carRect 4]
	[trunkRect 4]
	[carDoorRect 4]
)
(procedure (localproc_1bf2)
	(return
		(switch currentCar
			(13
				(cond 
					((not (ego inRect: [carRect 0] [carRect 1] [carRect 2] [carRect 3]))
						(Print 67 67)
					)
					((== workCarLocked 1)
						(Print 67 68)
						(return 0)
					)
					(workCarTrunkOpened
						(Print 67 69)
						(return 0)
					)
					((ego has: iFieldKit)
						(Print 67 70)
						(return 0)
					)
					(else
						(curRoom setScript: startEnter)
						(return 1)
					)
				)
			)
			(33
				(cond 
					((not (ego inRect: [carRect 0] [carRect 1] [carRect 2] [carRect 3]))
						(Print 67 67)
					)
					((== personalCarLocked 1)
						(Print 67 68)
					)
					(else
						(curRoom setScript: startEnter)
					)
				)
			)
		)
	)
)

(instance carBlock of Block
	(properties)
)

(instance jBlock of Block
	(properties)
)

(instance unTrunk of Prop
	(properties)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance jailerdoor of Prop
	(properties)
)

(instance plight of Prop
	(properties)
)

(instance carDoor of Prop
	(properties)
)

(instance haines of Actor
	(properties)
)

(instance shopwoman of Actor
	(properties)
)

(instance ourCar of Actor
	(properties)
)

(instance rm67 of Room
	(properties
		picture 67
		style VSHUTTER
	)
	
	(method (init)
		(if (== prevRoomNum 0)
			(= prevRoomNum 13)
			(= currentCar 13)
			(= gamePhase 3)
			(ego get: 0 1 2 3)
		)
		(if (== gamePhase 3)
			(Bset fBeenAtMallCrimeScene)
			(= captainWarningTimer 0)
			(= global159 0)
		)
		(Load PICTURE 67)
		(Load VIEW 0)
		(Load VIEW 20)
		(Load VIEW 54)
		(Load VIEW 51)
		(Load VIEW 75)
		(Load VIEW 49)
		(Load VIEW 33)
		(Load VIEW 85)
		(Load VIEW 2)
		(Load VIEW 76)
		(Load SOUND 27)
		(super init:)
		(= gunNotNeeded 1)
		(= gunFireState gunPROHIBITED)
		(= [local9 0] 56)
		(= [local9 1] 159)
		(if (== currentCar carWork)
			(= [carRect 0] 7)
			(= [carRect 1] 153)
			(= [carRect 2] 19)
			(= [carRect 3] 165)
			(= [carDoorPosn 0] 1)
			(= [carDoorPosn 1] 151)
			(= [carDoorRect 0] 23)
			(= [carDoorRect 1] 145)
			(= [carDoorRect 2] 41)
			(= [carDoorRect 3] 154)
			(= [trunkRect 0] 54)
			(= [trunkRect 1] 146)
			(= [trunkRect 2] 91)
			(= [trunkRect 3] 158)
		else
			(= [carRect 0] 20)
			(= [carRect 1] 155)
			(= [carRect 2] 35)
			(= [carRect 3] 165)
			(= [carDoorPosn 0] 17)
			(= [carDoorPosn 1] 152)
			(= [carDoorRect 0] 20)
			(= [carDoorRect 1] 145)
			(= [carDoorRect 2] 38)
			(= [carDoorRect 3] 154)
		)
		(= local1 60)
		(= local2 18)
		(carBlock
			top: 147
			bottom: 158
			left: -10
			right: 75
			init:
		)
		(jBlock
			top: 178
			bottom: 193
			left: 194
			right: 285
		)
		((View new:)
			view: 76
			loop: 0
			setCel: 0
			posn: 303 64
			setPri: 3
			init:
			ignoreActors: 0
			addToPic:
		)
		((View new:)
			view: 54
			loop: 1
			setCel: 2
			posn: 206 156
			setPri: 11
			init:
			ignoreActors: 0
			addToPic:
		)
		((View new:)
			view: 75
			loop: 1
			setCel: 2
			posn: 26 177
			setPri: 13
			addToPic:
		)
		((View new:)
			view: 75
			loop: 1
			setCel: 1
			posn: 16 191
			setPri: 14
			init:
			ignoreActors: 0
			addToPic:
		)
		((View new:)
			view: 85
			loop: 0
			setCel: 0
			posn: 128 10
			setPri: 4
			init:
			ignoreActors: 0
			addToPic:
		)
		(ourCar
			view: 54
			setStep: 4 1
			setLoop: 0
			setCel: (if (== currentCar 13) 1 else 5)
			setCycle: 0
			setMotion: 0
			posn: local2 153
			init:
		)
		(ego
			view: 0
			setMotion: 0
			observeBlocks: carBlock
			observeBlocks: jBlock
			posn: 340 300
			init:
		)
		(if (== currentCar 13)
			((= keith (Actor new:))
				view: 20
				setAvoider: Avoider
				observeBlocks: carBlock
				observeBlocks: jBlock
				posn: 400 400
				setCycle: Walk
				init:
			)
		else
			(= keith 0)
		)
		(if (== gamePhase 3)
			((View new:)
				view: 54
				loop: 1
				setCel: 3
				posn: 226 169
				setPri: 12
				init:
				ignoreActors: 0
				addToPic:
			)
			(plight
				view: 54
				loop: 3
				cel: 0
				posn: 231 140
				setPri: 13
				init:
				setCycle: Forward
			)
			((View new:)
				view: 75
				loop: 6
				setCel: 0
				yStep: 8
				posn: 240 193
				setPri: 14
				ignoreActors: 0
				addToPic:
			)
			(jailerdoor
				view: 75
				setLoop: 5
				cel: 0
				posn: 240 193
				setPri: 14
				ignoreActors:
				stopUpd:
				init:
			)
			(haines
				view: 49
				loop: 1
				setStep: 3 2
				setCycle: Walk
				setPri: -1
				posn: 210 178
				setAvoider: (Avoider new:)
				init:
			)
			(shopwoman
				view: 2
				setCycle: Walk
				setPri: -1
				posn: -20 1020
				setStep: 3 2
				init:
				stopUpd:
			)
			(if (Btst fBeenAtMall)
				(haines
					stopUpd:
					x: -20
					y: 500
				)
			)
			(if (Btst fWomanTalkToHaines)
				(haines
					posn: 254 172
					loop: 1
					stopUpd:
				)
				(shopwoman
					posn: 225 172
					loop: 0
					stopUpd:
				)
			)
		)
		(if (== prevRoomNum 68)
			(ego
				posn: 230 200
				setPri: -1
				init:
				ignoreActors: 0
			)
		)
		(if
			(or
				(== prevRoomNum 33)
				(== prevRoomNum 13)
			)
			(HandsOff)
			(self setScript: stopScript)
		else
			(HandsOn)
			(ourCar stopUpd:)
			(if (== currentCar 13)
				(unTrunk
					view: 51
					loop: 4
					cel: 0
					ignoreActors:
					setPri: 12
					x: (+ (ourCar x?) 38)
					y: (+ (ourCar y?) 4)
					setCel: (if workCarTrunkOpened 255 else 0)
					stopUpd:
					init:
				)
			)
		)
		(self setLocales: regFieldKit)
	)
	
	(method (doit)
		(if (not script)
			(cond 
				(
					(and
						(== prevRoomNum 68)
						(not (Btst fWomanTalkToHaines))
					)
					(self setScript: afterSearch)
				)
				(
					(and
						(== global132 (not local0))
						(== (not local0) 1)
					)
					(= global132 0) 
					(localproc_1bf2)
				)
				(
					(and
						(== gamePhase 3)
						(not (Btst fBeenAtMall))
					)
					(Bset fBeenAtMall)
					(self setScript: firstArrived)
				)
			)
			(if
				(and
					(not local0)
					(or
						(<= (ego x?) -10)
						(>= (ego x?) 330)
						(>= (ego y?) 230)
					)
				)
				(Print 67 0)
				(cond 
					((<= (ego x?) 0)
						(ego setMotion: MoveTo 10 (ego y?))
					)
					((>= (ego x?) 320)
						(ego setMotion: MoveTo 310 (ego y?))
					)
					((>= (ego y?) 230)
						(ego setMotion: MoveTo (ego x?) 220)
					)
				)
			)
		)
		(if (<= (ego y?) 90)
			(Print 67 1)
			(ego setMotion: MoveTo (ego x?) 92)
		)
		(super doit:)
	)
	
	(method (dispose)
		(cSound loop: 1 fade:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?)
			(return)
		)
		(if (!= (event type?) saidEvent)
			(return)
		)
		(cond 
			((Said 'chat/broad')
				(cond 
					((not (== gamePhase phaseMALL))
						(event claimed: 0)
					)
					((Btst fWomanTalkToHaines)
						(Print 67 2)
					)
					((== script afterSearch)
						(Print 67 3 #at -1 20)
					)
					(else
						(event claimed: 0)
					)
				)
			)
			((Said 'look/broad')
				(if
					(or
						(== script afterSearch)
						(Btst fWomanTalkToHaines)
					)
					(Print 67 4)
				else
					(event claimed: 0)
				)
			)
			(
				(or
					(Said 'happen<what[<ask]')
					(Said 'interrogate/broad')
				)
				(cond 
					(
						(or
							(not (== gamePhase phaseMALL))
							(not (Btst fWomanTalkToHaines))
						)
						(event claimed: 0)
					)
					((== script afterSearch)
						(Print 67 5 #at -1 20)
					)
					(else
						(Print 67 2)
					)
				)
			)
			(
				(or
					(Said 'describe,ask/auto')
					(Said 'ask/broad/auto')
					(Said 'get[/auto]/description,info')
					(Said 'get/description,info[/auto]')
					(Said '[/auto]/description,info')
					(Said '/description,info[/auto]')
					(Said 'ask,interrogate,get/name,address,number,license')
					(Said 'ask,interrogate,get/broad,witness[/number,badge,name,address]')
				)
				(cond 
					((not (== gamePhase 3))
						(event claimed: 0)
					)
					((!= script afterSearch)
						(Print 67 2)
					)
					((not (Btst fAskedWomanAboutCar))
						(Print 67 6 #at -1 20 #width 280)
						(SolvePuzzle 3)
						(Bset fAskedWomanAboutCar)
						(script seconds: 3)
					)
					(else
						(Print 67 7 #at -1 20)
					)
				)
			)
			((Said 'chat/cop,calvin')
				(cond 
					(
						(or
							(< gamePhase phaseMALL)
							(> gamePhase phaseMALL)
						)
						(Print 67 8)
					)
					((== script afterSearch)
						(Print 67 9)
					)
					((Btst fWomanTalkToHaines)
						(Print 67 10)
					)
					((== gamePhase phaseMALL)
						(Print 67 11)
					)
					(else
						(Print 67 12 #at -1 40 #width 280)
					)
				)
			)
			((Said 'look/cop,calvin')
				(cond 
					(
						(or
							(< gamePhase 3)
							(> gamePhase 3)
						)
						(Print 67 8)
					)
					((== script afterSearch)
						(Print 67 13 #at -1 20)
					)
					((Btst fWomanTalkToHaines)
						(Print 67 10)
					)
					((== gamePhase phaseMALL)
						(Print 67 11)
					)
					(else
						(Print 67 14)
					)
				)
			)
			((Said 'look,frisk/trunk')
				(cond 
					((!= currentCar carWork)
						(Print 67 15)
					)
					((not workCarTrunkOpened)
						(Print 67 16))
					(
						(not
							(ego
								inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3]
							)
						)
						(Print 67 17)
					)
					(else
						(inventory
							carrying: {The car's trunk contains_}
							empty: {The car's trunk is empty.}
							showSelf: iHitList
						)
					)
				)
			)
			((Said 'open/trunk')
				(cond 
					((!= currentCar carWork)
						(Print 67 15)
					)
					(
						(not
							(ego
								inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3]
							)
						)
						(Print 67 18)
					)
					(workCarTrunkOpened
						(Print 67 19)
					)
					(else
						(= workCarTrunkOpened 1)
						(unTrunk setCycle: EndLoop unTrunk)
					)
				)
			)
			((Said 'close/trunk')
				(cond 
					((not workCarTrunkOpened)
						(Print 67 20)
					)
					(
						(not
							(ego
								inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3]
							)
						)
						(NotClose)
					)
					(else
						(= workCarTrunkOpened 0)
						(unTrunk setCycle: BegLoop unTrunk)
					)
				)
			)
			((Said 'deposit,place/briefcase[/trunk]')
				(cond 
					((InRoom iFieldKit 13)
						(Print 67 21)
					)
					((not (ego has: iFieldKit))
						(Print 67 22)
					)
					((!= currentCar 13)
						(Print 67 15)
					)
					(
						(not
							(ego inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3])
						)
						(NotClose)
					)
					(workCarTrunkOpened
						(Print 67 23)
						(PutInRoom iFieldKit 13)
						(if (IsObject theFieldKit)
							(theFieldKit dispose:)
						else
							0
						)
						(= fieldKitOpen 0)
						
					)
					(script
						(Print 67 24)
					)
					(else
						(self setScript: kitToTrunk)
						(PutInRoom iFieldKit 13)
						(if (IsObject theFieldKit)
							(theFieldKit dispose:)
						)
						(= fieldKitOpen 0)
					)
				)
			)
			((Said 'get,remove/briefcase[/trunk]')
				(cond 
					((ego has: iFieldKit)
						(Print 67 25)
					)
					((not (InRoom iFieldKit 13))
						(Print 67 26)
					)
					(
						(not
							(ego inRect: [trunkRect 0] [trunkRect 1] [trunkRect 2] [trunkRect 3])
						)
						(NotClose)
					)
					(workCarTrunkOpened
						(Print 67 23)
						(ego get: iFieldKit)
					)
					(script
						(Print 67 24)
					)
					(else
						(self setScript: kitToTrunk)
						(ego get: iFieldKit)
					)
				)
			)
			((Said 'look>')
				(cond 
					((Said '[<at,around][/noword,building,mall,chamber]')
						(Print 67 27)
					)
					((Said '/tree,dirt,bush')
						(Print 67 28)
					)
					((Said '/pane')
						(cond 
							(
								(and
									(<= 58 (ego x?))
									(<= (ego x?) 187)
									(<= (ego y?) 100)
								)
								(Print 67 29)
							)
							(
								(or
									(ego inRect: -10 120 86 189)
									(ego inRect: 142 126 305 189)
								)
								(Print 67 30)
							)
							(else
								(Print 67 31)
							)
						)
					)
					((Said '/door')
						(cond 
							(
								(and
									(<= 58 (ego x?))
									(<= (ego x?) 187)
									(<= (ego y?) 100)
								)
								(Print 67 29)
							)
							(
								(or
									(ego inRect: -10 120 86 189)
									(ego inRect: 142 126 305 189)
								)
								(Print 67 32)
							)
							(else
								(Print 67 33)
							)
						)
					)
					((Said '/lot')
						(Print 67 34)
					)
					((Said '/sign')
						(Print 67 35)
					)
					((Said '/crowd')
						(Print 67 36)
					)
					((Said '/auto,auto')
						(if (ego inRect: 151 173 304 210)
							(switch (Random 0 2)
								(0
									(Print 67 37)
								)
								(1
									(Print 67 38)
								)
								(2
									(Print 67 39)
								)
							)
						else
							(Print 67 40)
						)
					)
					(
						(or
							(Said '/plate[<license]')
							(Said '/license')
						)
						(cond 
							((ego inRect: 151 176 206 210)
								(switch (Random 0 2)
									(0
										(Print 67 41)
									)
									(1
										(Print 67 42)
									)
									(2
										(Print 67 43)
									)
								)
							)
							((ego inRect: 252 173 304 210)
								(switch (Random 0 2)
									(0
										(Print 67 44)
									)
									(1
										(Print 67 45)
									)
									(2
										(Print 67 46)
									)
								)
							)
							(else
								(Print 67 47)
							)
						)
					)
				)
			)
			(
				(or
					(Said 'read/plate[<license]')
					(Said 'read/license')
				)
				(cond 
					((ego inRect: 151 176 206 210)
						(switch (Random 0 2)
							(0
								(Print 67 41)
							)
							(1
								(Print 67 42)
							)
							(2
								(Print 67 43)
							)
						)
					)
					((ego inRect: 252 173 304 210)
						(switch (Random 0 2)
							(0
								(Print 67 48)
							)
							(1
								(Print 67 49)
							)
							(2
								(Print 67 50)
							)
						)
					)
					(else
						(Print 67 47)
					)
				)
			)
			(
				(or
					(Said 'enter/auto')
					(Said 'open/door')
				)
				(cond 
					((ego inRect: 224 191 246 210)
						(self newRoom: 68)
					)
					(
						(ego inRect: [carDoorRect 0] [carDoorRect 1] [carDoorRect 2] [carDoorRect 3])
						(Print 67 51)
					)
					(
						(and
							(== (ego loop?) 2)
							(ego inRect: 220 170 240 180)
						)
						(Print 67 52)
					)
					(
						(and
							(== (ego loop?) 3)
							(ego inRect: 220 170 240 180)
						)
						(Print 67 53)
					)
					((ego inRect: 220 170 240 180)
						(Print 67 54)
					)
					(else
						(localproc_1bf2)
					)
				)
			)
			((Said 'exit/auto')
				(if script
					(Print 67 24)
				else
				(self setScript: exitCar))
			)
			((Said 'unlock/door')
				(cond 
					(
						(ego inRect: [carRect 0] [carRect 1] [carRect 2] [carRect 3])
						(cond 
							(
								(and
									(== currentCar carWork)
									(ego has: iUnmarkedCarKeys)
								)
								(if (== workCarLocked 1)
									(= workCarLocked 0)
									(Print 67 55)
								else
									(Print 67 56)
								)
							)
							((== currentCar carWork)
								(Print 67 57)
							)
						)
						(cond 
							(
								(and
									(== currentCar carPersonal)
									(ego has: iKeyRing)
								)
								(if (== personalCarLocked 1)
									(= personalCarLocked 0)
									(Print 67 58)
								else
									(Print 67 56)
								)
							)
							((== currentCar carPersonal)
								(Print 67 57)
							)
						)
					)
					((== currentCar carWork)
						(if (ego inRect: 22 148 40 152)
							(Print 67 59)
						else
							(Print 67 60)
						)
					)
					((ego inRect: 16 148 40 152)
						(Print 67 59)
					)
					(else
						(Print 67 60)
					)
				)
			)
			((Said 'lock/door')
				(cond 
					(
						(ego inRect: [carRect 0] [carRect 1] [carRect 2] [carRect 3])
						(cond 
							(
								(and
									(== currentCar carWork)
									(ego has: iUnmarkedCarKeys)
								)
								(if (== workCarLocked 0)
									(= workCarLocked 1)
									(Print 67 61)
								else
									(Print 67 62)
								)
							)
							((== currentCar carWork)
								(Print 67 63)
							)
						)
						(cond 
							(
								(and
									(== currentCar carPersonal)
									(ego has: iKeyRing)
								)
								(if (== personalCarLocked 0)
									(= personalCarLocked 1)
									(Print 67 61)
								else
									(Print 67 62)
								)
							)
							((== currentCar carPersonal)
								(Print 67 63)
							)
						)
					)
					((== currentCar carWork)
						(if (ego inRect: 22 148 40 152)
							(Print 67 59)
						else
							(Print 67 60)
						)
					)
					((ego inRect: 16 148 40 152)
						(Print 67 59)
					)
					(else
						(Print 67 60)
					)
				)
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
				(ego
					posn: (if (== currentCar carWork)
								12
							else
								28
							) 160
					loop: 0
					setPri: 12
					setStep: 3 2
					setCycle: Walk
					init:
					stopUpd:
				)
				(if keith
					(keith
						posn: 23 153
						setStep: 3 2
						setLoop: 0
						setCel: 0
						setPri: 10
						ignoreActors:
						illegalBits: 0
						setMotion: MoveTo 26 143
					)
				)
				(carDoor
					view: 51
					loop: (if (== currentCar 13) 2 else 0)
					cel: 0
					posn: [carDoorPosn 0] [carDoorPosn 1]
					setPri: 11
					init:
					setCycle: EndLoop self
				)
			)
			(1
				(= local0 0)
				(if (== currentCar 13)
					(= workCarLocked 0)
				else
					(= personalCarLocked 0)
				)
				(carDoor dispose:)
				(ego setPri: -1)
				(if keith
					(keith
						setStep: 3 2
						setCycle: Walk
						setLoop: -1
						setPri: -1
						setMotion: MoveTo 56 143 self
					)
				else
					(self cue:)
				)
			)
			(2
				(if keith
					(keith
						illegalBits: cWHITE ;-32768
						ignoreActors: 0
						setMotion: Follow ego 50
					)
				)
				(client setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance startEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 12
					stopUpd:
				)
				(cond 
					((not keith)
						(self changeState: 4)
					)
					((not (keith inRect: -10 0 330 240))
						(keith
							posn: 330 125
							setMotion: MoveTo 128 112 self
						)
						(Print 67 64)
					)
					(else
						(Print 67 65)
						(if (> (keith y?) 158)
							(if (< (keith x?) 102)
								(keith setMotion: MoveTo 102 (keith y?) self)
							else
								(keith setMotion: MoveTo (keith x?) 158 self)
							)
						else
							(self cue:)
						)
					)
				)
			)
			(1
				(keith
					setPri: 10
					setMotion: MoveTo (keith x?) 140 self
				)
			)
			(2
				(keith
					setPri: 10
					setMotion: MoveTo 29 145 self
				)
			)
			(3
				(ego setPri: 12)
				(if (== currentCar 13)
					(keith setMotion: MoveTo 30 140)
				)
				(carDoor
					view: 51
					loop: (if (== currentCar 13) 2 else 0)
					cel: 0
					posn: [carDoorPosn 0] [carDoorPosn 1]
					setPri: 11
					init:
					setCycle: EndLoop
				)
				(self cue:)
			)
			(4
				(if (== currentCar 13)
					(keith setLoop: 2)
					(if
						(and
							(not (Btst fToldKeithAboutMallScene))
							(or
								(Btst fGotEmptyHolster)
								(Btst fAskedWomanAboutCar)
							)
						)
						(Bset fToldKeithAboutMallScene)
						(Print 67 66)
					)
				)
				(curRoom newRoom: currentCar)
			)
		)
	)
)

(instance kitToTrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(unTrunk
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(= seconds 2)
			)
			(2
				(unTrunk setCycle: BegLoop self)
			)
			(3
				(unTrunk stopUpd:)
				(client setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance stopScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if keith
					(keith posn: 0 400)
				)
				(= local0 1)
				(if (!= roomCarParked curRoomNum)
					(= roomCarParked curRoomNum)
					(= workCarTrunkOpened 0)
					(ourCar
						posn: local1 153
						setMotion: MoveTo local2 153 self
					)
				else
					(ourCar
						posn: local2 153
						addToPic:
					)
					(if (== currentCar carWork)
						(unTrunk
							view: 51
							loop: 4
							cel: 0
							ignoreActors:
							setPri: 12
							x: (+ (ourCar x?) 38)
							y: (+ (ourCar y?) 4)
							stopUpd:
							init:
						)
					)
					(client setScript: exitCar)
				)
			)
			(1
				(ourCar
					ignoreActors: 0
					addToPic:
				)
				(if (== currentCar carWork)
					(unTrunk
						view: 51
						loop: 4
						cel: 0
						ignoreActors:
						setPri: 12
						x: (+ (ourCar x?) 38)
						y: (+ (ourCar y?) 4)
						stopUpd:
						init:
					)
				)
				(client setScript: exitCar)
			)
		)
	)
)

(instance firstArrived of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if keith
					(keith
						setAvoider: Avoider
						setMotion: MoveTo 110 (keith y?)
					)
					(Print 67 71)
				else
					(Print 67 72 #at -1 40)
				)
				(ego setMotion: MoveTo 106 163)
				(haines
					loop: 1
					illegalBits: 0 ;added to prevent freezing
					setMotion: MoveTo 152 172 self
				)
			)
			(1
				(if keith
					(keith
						setAvoider: Avoider
						setMotion: MoveTo 110 158
					)
				)
				(haines setMotion: MoveTo 134 164 self)
			)
			(2
				(ego stopUpd:)
				(haines loop: 1)
				(Print 67 73 #draw #at -1 40)
				(Print 67 74 #at -1 40)
				(if keith
					(Print 67 75 #at -1 40)
					(keith
						illegalBits: cWHITE ;-32768
						setMotion: MoveTo 155 125 self
					)
				else
					(= seconds 3)
				)
			)
			(3
				(cSound
					number: 27
					loop: -1
					play:
				)
				(if keith
					(keith setMotion: MoveTo 400 100)
				)
				(Print 67 76 #at -1 40)
				(haines
					setAvoider: Avoider
					setMotion: MoveTo -20 100 self
				)
			)
			(4
				(haines
					posn: -20 1000
					stopUpd:
				)
				(HandsOn)
				(ego startUpd:)
				(client setScript: 0)
			)
		)
	)
)

(instance afterSearch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego stopUpd:)
				(haines
					view: 49
					posn: -15 119
					setMotion: MoveTo 104 124
					init:
				)
				(shopwoman
					view: 2
					setCycle: Walk
					posn: -20 114
					setMotion: MoveTo 115 122 self
					init:
				)
			)
			(1
				(jailerdoor stopUpd:)
				(haines loop: 2)
				(Print 67 77 #at -1 20 #draw)
				(self cue:)
			)
			(2
				(haines stopUpd:)
				(shopwoman stopUpd:)
				(ego setMotion: MoveTo 138 189 self)
			)
			(3
				(ego setMotion: MoveTo 138 125 self)
			)
			(4
				(ego loop: 1)
				(haines startUpd: loop: 0)
				(Print 67 78 #at -1 20 #draw)
				(User canInput: 1)
				(= seconds 60)
			)
			(5
				(HandsOff)
				(Print 67 79 #at -1 20 #draw)
				(haines setMotion: MoveTo (haines x?) 174 self)
				(shopwoman setMotion: Follow haines 5)
				(ego
					loop: 2
					stopUpd:
				)
			)
			(6
				(haines setMotion: MoveTo 254 174)
				(shopwoman setMotion: MoveTo (shopwoman x?) 174 self)
			)
			(7
				(HandsOn)
				(shopwoman setMotion: MoveTo 225 174 self)
			)
			(8
				(haines
					loop: 1
					stopUpd:
				)
				(shopwoman stopUpd:)
				(Bset fWomanTalkToHaines)
				(client setScript: 0)
			)
		)
	)
)
