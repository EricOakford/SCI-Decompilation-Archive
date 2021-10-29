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
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_07ba
			pushi    5
			pushi    1
			pushi    54
			pushi    205
			pushi    1
			pushi    1
			pushi    162
			pushi    1
			lsg      currentCar
			ldi      13
			eq?     
			bnt      code_0453
			ldi      1
			jmp      code_0455
code_0453:
			ldi      5
code_0455:
			push    
			pushi    225
			pushi    2
			pushi    3
			dup     
			pushi    126
			pushi    1
			pushi    0
			pushi    216
			pushi    1
			pushi    0
			pushi    202
			pushi    2
			lsg      prevRoomNum
			ldi      13
			eq?     
			bt       code_047a
			lsg      prevRoomNum
			ldi      33
			eq?     
			bnt      code_0485
code_047a:
			lal      local3
			not     
			bnt      code_0485
			lal      local4
			jmp      code_0487
code_0485:
			lal      local5
code_0487:
			push    
			lsl      local12
			pushi    87
			pushi    0
			pushi    18
			pushi    1
			pushi    0
			pushi    #new
			pushi    0
			class    Actor
			send     4
			sal      local0
			send     56
			pushi    #view
			pushi    1
			pushi    251
			pushi    6
			pushi    1
			pushi    6
			pushi    7
			pushi    1
			pushi    0
			pushi    66
			pushi    1
			pushi    3
			pushi    202
			pushi    2
			pushi    104
			pushi    111
			pushi    87
			pushi    0
			pushi    208
			pushi    0
			pushi    203
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     44
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    9
			pushi    162
			pushi    1
			pushi    5
			pushi    66
			pushi    1
			pushi    9
			pushi    202
			pushi    2
			pushi    261
			pushi    156
			pushi    87
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     40
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    2
			pushi    162
			pushi    1
			pushi    0
			pushi    66
			pushi    1
			pushi    7
			pushi    202
			pushi    2
			pushi    104
			pushi    73
			pushi    87
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     40
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    0
			pushi    162
			pushi    1
			pushi    2
			pushi    66
			pushi    1
			pushi    10
			pushi    202
			pushi    2
			pushi    167
			pushi    139
			pushi    206
			pushi    0
			pushi    87
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     44
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    0
			pushi    162
			pushi    1
			pushi    0
			pushi    66
			pushi    1
			pushi    0
			pushi    202
			pushi    2
			pushi    67
			pushi    8
			pushi    87
			pushi    0
			pushi    206
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     44
			pushi    1
			pushi    14
			callb    Btst,  2
			bnt      code_05ca
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    9
			pushi    162
			pushi    1
			pushi    0
			pushi    202
			pushi    2
			pushi    228
			pushi    128
			pushi    66
			pushi    1
			pushi    9
			pushi    87
			pushi    0
			pushi    206
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     44
code_05ca:
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    9
			pushi    162
			pushi    1
			pushi    2
			pushi    202
			pushi    2
			pushi    270
			pushi    164
			pushi    66
			pushi    1
			pushi    12
			pushi    87
			pushi    0
			pushi    206
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     44
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    9
			pushi    162
			pushi    1
			pushi    2
			pushi    202
			pushi    2
			pushi    205
			pushi    166
			pushi    66
			pushi    1
			pushi    11
			pushi    87
			pushi    0
			pushi    206
			pushi    1
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     46
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    9
			pushi    162
			pushi    1
			pushi    2
			pushi    202
			pushi    2
			pushi    189
			pushi    137
			pushi    66
			pushi    1
			pushi    9
			pushi    87
			pushi    0
			pushi    206
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     44
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    9
			pushi    162
			pushi    1
			pushi    3
			pushi    202
			pushi    2
			pushi    238
			pushi    159
			pushi    66
			pushi    1
			pushi    12
			pushi    87
			pushi    0
			pushi    206
			pushi    1
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     46
			pushi    #view
			pushi    1
			pushi    251
			pushi    205
			pushi    1
			pushi    9
			pushi    162
			pushi    1
			pushi    4
			pushi    202
			pushi    2
			pushi    196
			pushi    149
			pushi    66
			pushi    1
			pushi    10
			pushi    87
			pushi    0
			pushi    206
			pushi    1
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     46
			pushi    #view
			pushi    1
			pushi    50
			pushi    205
			pushi    1
			pushi    2
			pushi    162
			pushi    1
			pushi    0
			pushi    202
			pushi    2
			pushi    227
			pushi    155
			pushi    66
			pushi    1
			pushi    10
			pushi    87
			pushi    0
			pushi    206
			pushi    0
			pushi    208
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     44
			pushi    5
			pushi    1
			pushi    0
			pushi    202
			pushi    2
			lsg      prevRoomNum
			lal      local7
			eq?     
			bnt      code_071b
			ldi      240
			jmp      code_071e
code_071b:
			ldi      340
code_071e:
			push    
			lsg      prevRoomNum
			lal      local7
			eq?     
			bnt      code_072d
			ldi      190
			jmp      code_0730
code_072d:
			ldi      300
code_0730:
			push    
			pushi    87
			pushi    0
			pushi    216
			pushi    1
			pushi    0
			lag      ego
			send     24
			lsg      currentCar
			ldi      13
			eq?     
			bnt      code_0777
			pushi    #view
			pushi    1
			pushi    20
			pushi    202
			pushi    2
			pushi    190
			pushi    195
			pushi    126
			pushi    1
			class    Walk
			push    
			pushi    217
			pushi    1
			pushi    #new
			pushi    0
			class    Avoider
			send     4
			push    
			pushi    18
			pushi    1
			pushi    32768
			pushi    #new
			pushi    0
			class    Actor
			send     4
			sag      keith
			send     32
code_0777:
			lsg      roomCarParked
			lag      curRoomNum
			ne?     
			bnt      code_078a
			lag      curRoomNum
			sag      roomCarParked
			ldi      1
			sag      global132
			jmp      code_0796
code_078a:
			lsl      local2
			ldi      1
			eq?     
			bnt      code_0796
			ldi      1
			sag      global132
code_0796:
			lsg      prevRoomNum
			lal      local7
			ne?     
			bnt      code_07b2
			pushi    #setMotion
			pushi    4
			class    MoveTo
			push    
			lsl      local5
			lsl      local12
			pushSelf
			lal      local0
			send     12
			jmp      code_08ad
code_07b2:
			pushi    #cue
			pushi    0
			self     4
			jmp      code_08ad
code_07ba:
			dup     
			ldi      1
			eq?     
			bnt      code_07d5
			pushi    #addToPic
			pushi    0
			pushi    206
			pushi    1
			pushi    0
			pushi    203
			pushi    0
			lal      local0
			send     14
			jmp      code_08ad
code_07d5:
			dup     
			ldi      2
			bnt      code_07de
			lal      local2
			not     
code_07de:
			eq?     
			bnt      code_0865
			pushi    2
			pushi    225
			pushi    4
			calle    Print,  4
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      0
			le?     
			bnt      code_0813
			pushi    #setMotion
			pushi    3
			class    MoveTo
			push    
			pushi    10
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			lag      ego
			send     10
			jmp      code_08ad
code_0813:
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      320
			ge?     
			bnt      code_083c
			pushi    #setMotion
			pushi    3
			class    MoveTo
			push    
			pushi    300
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			lag      ego
			send     10
			jmp      code_08ad
code_083c:
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      230
			ge?     
			bnt      code_08ad
			pushi    #setMotion
			pushi    3
			class    MoveTo
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    200
			lag      ego
			send     10
			jmp      code_08ad
code_0865:
			dup     
			ldi      3
			eq?     
			bnt      code_08ad
			pushi    2
			pushi    0
			pushi    2
			callk    Random,  4
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0887
			pushi    2
			pushi    225
			pushi    5
			calle    Print,  4
			jmp      code_08ac
code_0887:
			dup     
			ldi      1
			eq?     
			bnt      code_089b
			pushi    2
			pushi    225
			pushi    6
			calle    Print,  4
			jmp      code_08ac
code_089b:
			dup     
			ldi      2
			eq?     
			bnt      code_08ac
			pushi    2
			pushi    225
			pushi    7
			calle    Print,  4
code_08ac:
			toss    
code_08ad:
			toss    
			ret     
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
