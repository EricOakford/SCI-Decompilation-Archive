;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
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
	rm61 0
)
(synonyms
	(broad her shelly witness broad jogger)
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	local11
)
(procedure (LocPrint)
	(Print &rest #at -1 18)
)

(procedure (LocPrintBottom)
	(Print &rest #at -1 156)
)

(procedure (EnterCar)
	(return
		(switch currentCar
			(13
				(if (not (ego inRect: 144 188 166 197))
					(Print 61 115)
				else
					(if (and (== gamePhase 5) (not shotAtBainsInCove))
						(ego setMotion: MoveTo 125 188)
						(return (Print 61 116))
					)
					(cond 
						(workCarLocked (Print 61 117) (return 0))
						(workCarTrunkOpened (Print 61 118) (return 0))
						((ego has: iFieldKit) (Print 61 119) (return 0))
						(else (carScript changeState: 7) (return 1))
					)
				)
			)
			(33
				(cond 
					((not (ego inRect: 144 188 166 197)) (Print 61 115))
					((== personalCarLocked 1) (Print 61 117))
					(else (carScript changeState: 7))
				)
			)
		)
	)
)

(instance unTrunk of Prop
	(properties)
)

(instance carDoor of Prop
	(properties)
)

(instance gelepsi of Actor
	(properties)
)

(instance barbie of Actor
	(properties)
)

(instance ourCar of Actor
	(properties)
)

(instance grass1 of View
	(properties)
)

(instance grass2 of View
	(properties)
)

(instance grass3 of View
	(properties)
)

(instance grass4 of View
	(properties)
)

(instance ourCarBlock of Block
	(properties
		top 194
		left 107
		bottom 207
		right 207
	)
)

(instance bwCarBlock of Block
	(properties
		top 164
		left 105
		bottom 172
		right 201
	)
)

(instance vanBlock of Block
	(properties
		top 164
		bottom 178
		right 30
	)
)

(instance rm61 of Room
	(properties
		picture 61
		style IRISIN
	)
	
	(method (init)
		(Load VIEW 20)
		(Load VIEW 253)
		(Load VIEW 54)
		(Load VIEW 51)
		(super init:)
		(= gunNotNeeded (!= gamePhase phaseCOVE))
		(if (== gamePhase phaseCOVE)
			(= gunFireState gunPROHIBITED)
		)
		(= local0 (not (= local3 (== prevRoomNum 60))))
		(= local1
			(if (== roomCarParked curRoomNum)
				local0
			else
				0
			)
		)
		(if
			(and
				(>= 6 gamePhase)
				(>= gamePhase phaseCOVE)
			)
			(= isOnDuty 0)
			(= captainWarningTimer 0)
			(= global159 0)
		)
		(if (== diverState 3)
			(= diverState 1)
		)
		(= local11 20)
		(if local3
			(HandsOn)
			(= local4 global111)
		else
			(HandsOff)
			(cond 
				(local1
					(if
						(and
							(== global111 3)
							(Btst 20)
						)
						(= local4 4)
						(= gunNotNeeded 1)
						(Bclr 20)
					else
						(= local4 global111)
					)
					(= bainsInCoveTimer 0)
				)
				((< gamePhase phaseCOVE)
					(= local4 0)
				)
				((== gamePhase phaseCOVE)
					(cond 
						(
							(and
								(> 90 captainWarningTimer)
								(> captainWarningTimer 1)
							)
							(= local4 2)
							(= captainWarningTimer 0)
						)
						((Btst 20)
							(= local4 4)
							(Bclr 20)
							(= bainsInCoveTimer 0)
						)
						(else
							(= local4 3)
							(= captainWarningTimer 0)
						)
					)
				)
				((>= gamePhase 6)
					(= local4 1)
				)
			)
			(= global111 local4)
		)
		(if shotAtBainsInCove
			(= bainsInCoveState 0)
		)
		(self setLocales: regCove)
		(grass1
			view: 253
			loop: 0
			cel: 3
			posn: 213 132
			stopUpd:
			addToPic:
		)
		(grass2
			view: 253
			loop: 0
			cel: 2
			posn: 231 75
			stopUpd:
			addToPic:
		)
		(grass3
			view: 253
			loop: 0
			cel: 1
			posn: 153 76
			stopUpd:
			addToPic:
		)
		(grass4
			view: 253
			loop: 0
			cel: 3
			posn: 67 114
			stopUpd:
			addToPic:
		)
		(if (== prevRoomNum 60)
			(ego observeBlocks: ourCarBlock)
		)
		(if (>= local4 3)
			(ego observeBlocks: bwCarBlock)
			((View new:)
				view: 54
				loop: 1
				cel: 3
				posn: 159 175
				init:
				addToPic:
			)
			(gelepsi
				view: 48
				loop: 2
				posn: 190 120
				setStep: 3 4
				init:
				setCycle: Walk
				illegalBits: 0
				setScript: gelepsiScript
			)
			(if (!= diverState 0)
				(= global185 1)
			)
			(if
				(and
					(not global185)
					(not global187)
				)
				(barbie
					view: 11
					loop: 2
					posn: 219 136
					init:
					setCycle: Walk
					setAvoider: (Avoider new:)
					setMotion: Follow ego 500
					illegalBits: 0
					setScript: barbieScript
				)
				(if (< howFast 30)
					(barbie
						setMotion: 0
						stopUpd:
					)
				)
			)
		)
		(ourCar
			view: 54
			setStep: 1
			setLoop: 1
			setCel: (if (== currentCar 13) 1 else 5)
			setCycle: 0
			setMotion: 0
			posn: (if (and (not local3) (not local1)) 150 else 164) 205
			init:
			setPri: 15
			ignoreActors:
		)
		(= local2
			(if (cast contains: gelepsi)
				(== prevRoomNum 13)
			else
				0
			)
		)
		(if
			(and
				(>= local4 3)
				(>= diverState 5)
			)
			((View new:) ;coroner's van
				view: 154
				loop: 0
				cel: 0
				posn: -20 181
				init:
				addToPic:
			)
			(ego observeBlocks: vanBlock)
		)
		(self setScript: rm61Script)
	)
	
	(method (dispose)
		(carScript dispose:)
		(stopScript dispose:)
		(gelepsiScript dispose:)
		(barbieScript dispose:)
		(keithScript dispose:)
		(super dispose:)
	)
)

(instance rm61Script of Script
	(properties)
	
	(method (doit)
		(if local2
			(if (< (ego distanceTo: gelepsi) 50)
				(gelepsi setLoop: 1)
			else
				(gelepsi setLoop: -1)
			)
		)
		(if (> local11 1)
			(-- local11)
		)
		(cond 
			(
				(and
					(== global132 local0)
					(== local0 1)
					(not (cast contains: ourCar))
				)
				(= global132 0)
				(carScript changeState: 0)
			)
			((== (ego edgeHit?) 4)
				(= global205 0)
				(curRoom newRoom: 60)
			)
			(
				(and
					(== global132 (not local0))
					(== (not local0) 1))
					(= global132 0)
					(EnterCar)
				)
		)
		(cond 
			(
				(and
					(> (ego y?) 205)
					(not local0)
				)
				(switch (Random 0 2)
					(0
						(Print 61 0)
					)
					(1
						(Print 61 1)
					)
					(2
						(Print 61 2)
					)
				)
				(ego setMotion: MoveTo (ego x?) 176)
			)
			(
				(and
					(== (ego edgeHit?) 2)
					(== local11 1)
				)
				(ego x: 340)
				(if local6
					(Print 61 3)
				else
					(switch (Random 0 2)
						(0
							(Print 61 4)
						)
						(1
							(Print 61 5)
						)
						(2
							(Print 61 6)
						)
					)
				)
				(= local11 50)
			)
		)
		(if (< (ego y?) 72)
			(ego y: 72)
		)
		(if
			(and
				(== diverState 3)
				(not (ego inRect: 0 148 34 182))
			)
			(Print 61 7)
			(= diverState 5)
			(ego observeBlocks: vanBlock)
			((Actor new:)
				view: 154
				loop: 0
				cel: 0
				posn: -55 174
				init:
				setMotion: MoveTo -20 181
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 0
					posn: 28 (ego y?)
					illegalBits: cWHITE;-32768
					ignoreActors: 0
					setPri: -1
					setLoop: -1
					init:
					setMotion: 0
				)
				(if local3
					(ego setMotion: MoveTo 50 (ego y?))
				else
					(ego posn: 200 240)
				)
				(if (== currentCar carWork)
					((= keith (Actor new:))
						view: 20
						cel: 0
						posn: -100 400
						setAvoider: (Avoider new:)
						setScript: keithScript
						init:
					)
				)
				(if
					(and
						(== currentCar carWork)
						local3
					)
					(keith
						cel: 0
						posn: 6 (+ (ego y?) 5)
						setMotion: Follow ego 30
					)
					(if workCarTrunkOpened
						(unTrunk
							view: 51
							loop: 5
							cel: 2
							posn: 126 209
							setPri: 15
							init:
						)
					)
				)
				(if
					(and
						(>= local4 3)
						(not local0)
						(or
							global187
							(!= diverState 0)
						)
					)
					(keith
						posn: 157 158
						loop: 1
						cel: 0
						stopUpd:
					)
					(gelepsi
						posn: 133 162
						loop: 0
						cel: 4
						stopUpd:
					)
				)
				(if (not local3)
					(stopScript init:)
					(ourCar setMotion: MoveTo 164 205 stopScript)
				else
					(ourCar addToPic:)
				)
				(cond 
					((!= roomCarParked curRoomNum)
						(= roomCarParked curRoomNum)
					)
					((== local0 1)
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
					((Said 'display/badge')
						(Print 61 8)
					)
					((Said 'look<through/fence,hole')
						(if (ego inRect: 169 125 320 200)
							(Print 61 9)
						else
							(Print 61 10)
						)
					)
					((Said 'look/fence')
						(if (ego inRect: 169 125 320 200)
							(Print 61 11)
						else
							(Print 61 10)
						)
					)
					((Said 'read,look/fence,initials')
						(Print 61 12)
					)
					((Said 'look>')
						(cond 
							((Said '/briefcase')
								(Print 61 13)
							)
							((Said '/auto')
								(if (ego inRect: 72 142 360 240)
									(switch (Random 0 2)
										(0
											(Print 61 14)
										)
										(1
											(Print 61 15)
										)
										(2
											(Print 61 16)
										)
									)
								else
									(Print 61 10)
								)
							)
							((Said '/van,bus')
								(if
									(and
										(> diverState 2)
										(>= global111 3)
									)
									(Print 61 17)
								else
									(Print 61 18)
								)
							)
							((Said '/trunk<tree')
								(Print 61 19)
							)
							((Said '/trunk')
								(if (== currentCar carWork)
									(if
										(and
											(ego inRect: 93 193 129 208)
											(cast contains: unTrunk)
										)
										(inventory
											carrying: {The car's trunk contains_}
											empty: {The car's trunk is empty.}
											showSelf: 13
										)
									else
										(Print 61 20)
									)
								else
									(Print 61 21)
								)
							)
							((Said '<below/board,plank,wood')
								(if (ego inRect: 110 130 190 160)
									(Print 61 22)
								else
									(Print 61 23)
								)
							)
							((Said '<below/rock')
								(if (ego inRect: 0 110 120 160)
									(Print 61 22)
								else
									(Print 61 23)
								)
							)
							((Said '<below/bush')
								(Print 61 24)
							)
							((Said '/rock')
								(if (ego inRect: 12 88 132 120)
									(Print 61 25)
								else
									(Print 61 10)
								)
							)
							((Said '/board,plank,wood')
								(if (ego inRect: 120 145 196 170)
									(Print 61 26)
								else
									(Print 61 10)
								)
							)
							(
								(or
									(Said '/phone')
									(Said '/booth[<phone]')
								)
								(if (ego inRect: 208 102 273 138)
									(Print 61 27)
								else
									(Print 61 10)
								)
							)
							((Said '/crap,waste,garbage')
								(if (== (ego edgeHit?) 2)
									(Print 61 28)
								else
									(Print 61 29)
								)
							)
							((Said '/cop,gelepsi,cop')
								(if (cast contains: gelepsi)
									(Print 61 30)
								else
									(Print 61 31)
								)
							)
							((Said '/broad')
								(if (cast contains: barbie)
									(Print 61 32)
								else
									(Print 61 33)
								)
							)
							((Said '[<at,around][/(noword,cove,area)]')
								(Print 61 34)
								(event claimed: 1)
							)
						)
					)
					((Said 'smell/garbage,crap,waste')
						(Print 61 35)
					)
					((Said 'deposit,place,replace/briefcase')
						(if (ego inRect: 93 193 129 208)
							(if workCarTrunkOpened
								(if (ego has: iFieldKit)
									(Print 61 36)
									(PutInRoom iFieldKit 13)
									(= fieldKitOpen 0)
									(if (IsObject theFieldKit)
										(theFieldKit dispose:)
									)
								else
									(Print 61 37)
								)
							else
								(Print 61 38)
							)
						else
							(Print 61 39)
						)
					)
					((Said 'remove,get/briefcase')
						(if (ego inRect: 93 193 129 208)
							(if workCarTrunkOpened
								(if (== ((inventory at: iFieldKit) owner?) 13)
									(Print 61 40)
									(ego get: iFieldKit)
								else
									(Print 61 41)
								)
							else
								(Print 61 38)
							)
						else
							(Print 61 39)
						)
					)
					((Said 'affirmative')
						(Print 61 42)
					)
					((Said 'no')
						(Print 61 43)
					)
					(
						(or
							(Said 'use,dial/phone')
							(Said 'deposit/dime,cash[/phone]')
						)
						(if (ego inRect: 240 122 246 126)
							(HandsOff)
							(Print 61 44 #at 120 50 #time 5)
							(Print 61 45 #at 120 50 #time 8)
							(Print 61 46 #at 120 50 #time 8)
							(HandsOn)
						else
							(Print 61 47)
						)
					)
					(
						(Said
							'enter,walk,go,crawl[<through]/hole,fence[/hole,fence]'
						)
						(Print 61 48)
					)
					((Said 'hoist,move,get/board,plank,wood')
						(if (ego inRect: 120 145 196 170)
							(Print 61 22)
						else
							(Print 61 23)
						)
					)
					(
						(or
							(Said 'enter/auto')
							(Said 'open/door')
							(Said 'get<in')
						)
						(EnterCar)
					)
					((Said 'exit/auto')
						(= global132 1)
					)
					((Said 'unlock/door')
						(if (ego inRect: 144 188 166 197)
							(cond 
								(
									(and
										(== currentCar 13)
										(ego has: iUnmarkedCarKeys)
									)
									(if (== workCarLocked 1)
										(= workCarLocked 0)
										(Print 61 49)
									else
										(Print 61 50)
									)
								)
								((== currentCar carWork)
									(Print 61 51)
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
										(Print 61 49)
									else
										(Print 61 50)
									)
								)
								((== currentCar carPersonal)
									(Print 61 51)
								)
							)
						else
							(NotClose)
						)
					)
					((Said 'lock/door')
						(if (ego inRect: 144 188 166 197)
							(if (== currentCar carWork)
								(if (== workCarLocked 0)
									(= workCarLocked 1)
									(Print 61 52)
								else
									(Print 61 53)
								)
							)
							(if (== currentCar carPersonal)
								(if (== personalCarLocked 0)
									(= personalCarLocked 1)
									(Print 61 52)
								else
									(Print 61 53)
								)
							)
						else
							(NotClose)
						)
					)
					((Said 'open,unlock/trunk')
						(if (== currentCar carWork)
							(if (ego inRect: 93 193 129 208)
								(cond 
									(workCarTrunkOpened
										(Print 61 54)
									)
									((ego has: iUnmarkedCarKeys)
										(carScript changeState: 13)
									)
									(else
										(Print 61 55)
									)
								)
							else
								(NotClose)
							)
						else
							(Print 61 21)
						)
					)
					((Said 'close,lock/trunk')
						(if (== currentCar carWork)
							(if (ego inRect: 93 193 129 208)
								(if workCarTrunkOpened
									(carScript changeState: 15)
									(= workCarTrunkOpened 0)
								else
									(Print 61 56)
								)
							else
								(NotClose)
							)
						else
							(Print 61 21)
						)
					)
					((Said 'calm,calm[/broad]')
						(if (cast contains: barbie)
							(Print 61 57)
						else
							(Print 61 58)
						)
					)
					((Said 'call/coroner')
						(cond 
							((not (cast contains: gelepsi))
								(Print 61 59)
							)
							(
								(and
									removedBodyFromRiver
									(not (Btst fCalledCoroner))
								)
								(Print 61 60)
								(SolvePuzzle 3 fCalledCoroner)
							)
							((< (ego distanceTo: gelepsi) 35)
								(Print 61 61)
							)
							(else
								(Print 61 62)
							)
						)
					)
					((Said 'chat>')
						(cond 
							((Said '/cop,gelepsi,cop')
								(if (cast contains: gelepsi)
									(if (< (ego distanceTo: gelepsi) 25)
										(if (== currentCar carWork)
											(cond 
												((< (keith distanceTo: gelepsi) 30)
													(if shotAtBainsInCove
														(Print 61 63)
													else
														(Print 61 64))
												)
												(
													(and
														removedBodyFromRiver
														(not (Btst fCalledCoroner))
													)
													(Print 61 60)
													(SolvePuzzle 3 fCalledCoroner)
												)
												(else
													(Print 61 61)
												)
											)
										else
											(Print 61 65)
										)
									else
										(Print 61 62)
									)
								else
									(Print 61 31)
								)
							)
							((Said '/friend')
								(if (cast contains: keith)
									(if (< (ego distanceTo: keith) 45)
										(Print 61 66)
									else
										(Print 61 67)
									)
								else
									(Print 61 68)
								)
							)
							((Said '/broad')
								(if
									(and
										(cast contains: barbie)
										(< (ego distanceTo: barbie) 38)
									)
									(if local5
										(LocPrintBottom 61 69)
									else
										(= local5 1)
										(Print 61 70)
									)
								else
									(Print 61 71)
								)
							)
							(else
								(Print 61 72)
								(event claimed: 1)
							)
						)
					)
					((Said 'close/door[<booth]')
						(Print 61 73)
					)
					((Said '/briefcase')
						(Print 61 13)
					)
					((cast contains: barbie)
						(cond 
							((Said '[say]/hello')
								(if (> (ego distanceTo: barbie) 38)
									(Print 61 74)
								else
									(barbieScript changeState: 1)
								)
							)
							((Said 'feel,fuck,kiss,eat/broad')
								(if (> (ego distanceTo: barbie) 38)
									(Print 61 74)
								else
									(barbieScript changeState: 4)
								)
							)
							((Said 'interrogate,ask>')
								(cond 
									((> (ego distanceTo: barbie) 38)
										(event claimed: 1)
										(Print 61 75)
									)
									((Said '/badge,license[<broad]')
										(barbieScript changeState: 3)
									)
									(
										(or
											(Said '/number[<phone]')
											(Said '/date')
											(Said '//date')
										)
										(Print 61 76)
									)
									((Said '/name[<broad]')
										(barbieScript changeState: 2)
									)
									((Said '/address[<broad]')
										(barbieScript changeState: 3)
									)
									(
										(or
											(Said '/blood,mark,clue,location')
											(Said '/broad')
											(Said '<where')
										)
										(if local6
											(Print 61 77)
										else
											(= local6 1)
											(SolvePuzzle 2)
											(barbieScript changeState: 5)
											(LocPrint 61 78)
										)
									)
									(else
										(Print 61 79)
										(event claimed: 1)
									)
								)
							)
							((Said 'display[/clue,(print<feet),blood,i]')
								(if (> (ego distanceTo: barbie) 28)
									(Print 61 74)
								else
									(barbieScript changeState: 6)
								)
							)
						)
					)
					((Said 'ask/')
						(Print 61 80)
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
					setStep: 1 2
					posn: 157 202
					setPri: 14
					setLoop: 0
					setCycle: 0
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo 155 185 self
				)
				(if (== currentCar 13)
					(keith posn: 167 206 loop: 0 cel: 0 setPri: 15 startUpd:)
					(carDoor
						view: 51
						loop: 3
						cel: 0
						posn: 182 197
						setPri: 15
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
					illegalBits: -16384
				)
				(if (>= local4 3)
					(ego observeBlocks: ourCarBlock bwCarBlock)
				else
					(ego observeBlocks: ourCarBlock)
				)
				(if (== currentCar carWork)
					(self cue:)
				else
					(HandsOn)
				)
			)
			(2
				(carDoor dispose:)
				(keith setMotion: MoveTo 98 198 self)
			)
			(3
				(keith setPri: -1 setMotion: MoveTo 92 185 self)
				(HandsOn)
			)
			(4
				(keith
					loop: 2
					cel: 0
					setMotion: MoveTo 88 154 self
				)
				(switch local4
					(1
						(Print 61 81 #draw)
					)
					(2
						(Print 61 82 #at -1 60 #draw)
					)
					(0
						(Print 61 83 #at -1 60 #draw)
					)
				)
			)
			(5
				(keith setMotion: MoveTo 124 148 self)
			)
			(6
				(keith stopUpd:)
			)
			(7
				(HandsOff)
				(ego stopUpd: ignoreBlocks: ourCarBlock)
				(if (== currentCar carWork)
					(if bainsInCoveTimer
						(Print 61 84)
					else
						(Print 61 85)
					)
					(keith
						ignoreActors:
						illegalBits: 0
					)
					(cond 
						((< (keith y?) 168)
							(keith setMotion: MoveTo 90 150 self)
						)
						((< (keith y?) 196)
							(keith setMotion: MoveTo 82 196 self)
						)
						(else
							(self cue:)
						)
					)
				else
					(self changeState: 11)
				)
			)
			(8
				(keith
					ignoreActors:
					illegalBits: 0
					setPri: 15
					setMotion: MoveTo 108 207 self
				)
			)
			(9
				(if (cast contains: gelepsi)
					(cond 
						(bainsInCoveTimer
							(Print 61 86)
						)
						(removedBodyFromRiver
							(Print 61 87)
						)
						(else
							(Print 61 87)
						)
					)
				)
				(keith setMotion: MoveTo 170 207 self)
			)
			(10
				(carDoor
					view: 51
					loop: 3
					cel: 0
					posn: 182 197
					setPri: 15
					init:
					setCycle: EndLoop self
				)
			)
			(11
				(ego
					ignoreActors: 1
					illegalBits: 0
					setPri: 14
					setLoop: 0
					setCycle: 0
					setStep: 1 2
					setMotion: MoveTo 157 202 self
				)
			)
			(12
				(curRoom newRoom: currentCar)
			)
			(13
				(= workCarTrunkOpened 1)
				(unTrunk
					view: 51
					loop: 5
					cel: 0
					posn: 126 209
					setPri: 15
					init:
					setCycle: EndLoop self
				)
			)
			(14
				(unTrunk stopUpd:)
			)
			(15
				(unTrunk
					view: 51
					loop: 5
					cel: 2
					posn: 126 209
					setPri: 1
					startUpd:
					setCycle: CycleTo 0 -1 self
				)
			)
			(16
				(unTrunk dispose:)
			)
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
				(HandsOn)
				(ourCar addToPic:)
			)
		)
	)
)

(instance gelepsiScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local3 (self cue:) else (= seconds 3))
			)
			(1
				(if local3
					(gelepsi posn: 188 128 setMotion: MoveTo 170 128 self)
				else
					(gelepsi setMotion: MoveTo 150 146 self)
				)
			)
			(2
				(if (< howFast 30) (gelepsi setMotion: 0 stopUpd:))
				(if local3
					(if (and shotAtBainsInCove (not (Btst 30)))
						(Bset 30)
						(if (cast contains: barbie)
							(barbieScript changeState: 7)
						)
						(Print 61 88)
					else
						(Print 61 89 #at 40 24)
					)
				else
					(gelepsi setMotion: MoveTo 150 150 self)
				)
			)
			(3
				(ego loop: 1)
				(gelepsi loop: 2 setStep: 3 4 setMotion: Follow ego 500)
				(if (< howFast 30) (gelepsi setMotion: 0 stopUpd:))
				(cond 
					((== local4 3)
						(if (Btst 53)
							(Print 61 90)
						else
							(if (Btst 114)
								(++ dollars)
								(LocPrint 61 91)
							else
								(LocPrint 61 92)
							)
							(Print 61 93 #at -1 24 #draw)
							(Bset 53)
							(= seconds 2)
						)
					)
					((and (== local4 4) (not local1)) (= bainsInCoveTimer 0) (LocPrint 61 94))
					((== local4 4) (LocPrint 61 95))
				)
			)
			(4 (LocPrint 61 96))
			(5
				(gelepsi setMotion: MoveTo 192 149)
			)
		)
	)
)

(instance barbieScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(if local7
					(LocPrint 61 97)
				else
					(LocPrint 61 98)
					(= local7 1)
				)
			)
			(2
				(if local8 (LocPrint 61 99) else (= local8 1))
				(LocPrint 61 100)
			)
			(3
				(if local9 (LocPrint 61 99) else (= local9 1))
				(LocPrint 61 101)
			)
			(4
				(if local10
					(Print 61 102)
					(self changeState: 7)
				else
					(= local10 1)
					(switch (Random 1 4)
						(1 (Print 61 103))
						(2 (Print 61 104))
						(3 (Print 61 105))
						(4 (Print 61 106))
					)
				)
			)
			(5
				(LocPrint 61 107)
				(LocPrint 61 108)
				(LocPrint 61 109)
				(LocPrint 61 110)
			)
			(6
				(LocPrint 61 111)
				(LocPrint 61 78)
				(= local6 1)
			)
			(7
				(HandsOff)
				(= global185 1)
				(barbie
					view: 16
					setStep: 7 3
					ignoreActors: ;added to prevent lockup
					setMotion: MoveTo 105 130 self
				)
			)
			(8
				(barbie setMotion: MoveTo 73 172 self)
			)
			(9
				(if shotAtBainsInCove (LocPrint 61 112))
				(User canControl: 1)
				(barbie setPri: 15 setMotion: MoveTo 86 198 self)
			)
			(10
				(barbie setMotion: MoveTo 96 220 self)
			)
			(11
				(HandsOn)
				(barbie dispose:)
			)
		)
	)
)

(instance keithScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and global187 (> (ego x?) 40))
			(= global187 0)
			(self changeState: 3)
		)
		(cond 
			(
				(and
					(== diverState 0)
					(< (ego x?) 30)
					(!= (self state?) 1)
				)
				(self changeState: 1)
			)
			((and (>= (ego x?) 30) (== (self state?) 1)) (self changeState: 2))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (keith setCycle: Walk))
			(1
				(keith setMotion: Follow ego 50)
				(if (and (not local1) (== prevRoomNum 13))
					(Print 61 113 #draw)
				)
			)
			(2
				(keith setMotion: 0 stopUpd:)
			)
			(3
				(if (< diverState 3) (Print 61 114))
			)
		)
	)
)
