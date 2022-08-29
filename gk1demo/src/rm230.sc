;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include game.sh) (include "230.shm")
(use Main)
(use Procs)
(use Print)
(use Inset)
(use Conv)
(use Talker)
(use BordWind)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Window)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm230 0
)

(local
	franksInRoom
	sargeInRoom
	sargeSleeps
	local3
	sargeTalkCount
	frankyTalkCount
	inOfficialArea
	flirtedWithFranky
	printRet
	local9
	oldNarrator
	newProp
	local12
	local13
	local14
)
(instance rm230 of Room
	(properties
		noun N_ROOM
		picture 230
		style FADEOUT
		horizon 80
	)
	
	(method (init)
		(= oldNarrator narrator)
		(= narrator policeNarrator)
		(if (FileIO fileExists {g})
			(= inOfficialArea
				(Print
					addText: {set inOfficialArea local!}
					addButton: 0 {off} 0 12
					addButton: 1 {on} 0 26
					init:
				)
			)
			(if
				(and
					(== currentDay 1)
					(= printRet
						(Print
							addText: {set flag fBeenToLake?}
							addButton: 0 {no} 0 12
							addButton: 1 {yes} 0 26
							init:
						)
					)
				)
				(Bset fBeenToLake)
			)
			(if
				(= printRet
					(Print
						addText: {set flag fBeignetHinted?}
						addButton: 0 {no} 0 12
						addButton: 1 {yes} 0 26
						init:
					)
				)
				(Bset fBeignetHinted)
			)
			(if
				(= printRet
					(Print
						addText: {set flag fCaseIsReOpened?}
						addButton: 0 {no} 0 12
						addButton: 1 {yes} 0 26
						init:
					)
				)
				(Bset fCaseIsReopened)
			)
		)
		(if (not (== prevRoomNum 50))
			(theMusic1
				number:
				(switch (DoSound NumVoices)
					(32 230)
					(else  5230)
				)
				loop: -1
				play:
				setVol: 0
				fade: 80 5 10 0
			)
		)
		(Load RES_VIEW 233)
		(theGame handsOn:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						131 103
						160 103
						201 96
						246 108
						283 119
						304 84
						288 78
						252 79
						247 88
						118 94
					yourself:
				)
				((Polygon new:)
					type: PContainedAccess
					init:
						1 86
						1 125
						27 146
						200 148
						92 93
						70 93
						58 87
						42 87
						38 84
					yourself:
				)
		)
		(super init:)
		(narrator modeless: TRUE)
		(frankCam addToPic:)
		(if (not (Btst fFlag34))
			(franky init:)
		)
		(cond 
			((== prevRoomNum 50)
				(cond 
					((== interrogateSubject SARGE)
						(if (and (Btst fFlag12) (not (Btst fGotPhotos)))
							(Bset fGotPhotos)
							(curRoom setScript: getPhotos)
						else
							(deskSarg init:)
							(ego normalize: 0)
							(ego posn: 191 145 init:)
							(if (and (== currentDay 6) (not (ego has: iTracker)) (Btst fBeignetHinted))
								(ego setScript: sargSleeping)
							)
						)
					)
					((Btst fFlag13)
						(deskSarg init:)
						(= inOfficialArea TRUE)
						(curRoom setScript: franksGetsFile)
					)
					(else
						(deskSarg init:)
						(= inOfficialArea TRUE)
						(ego normalize: 0)
						(ego posn: 282 129 init:)
					)
				)
				(mosDoor init: stopUpd:)
				(extDoor init: stopUpd:)
			)
			((== prevRoomNum 240)
				(= inOfficialArea TRUE)
				(deskSarg init:)
				(ego normalize:)
				(ego
					view: 900
					loop: 2
					cel: 0
					setCycle: StopWalk -1
					posn: 265 80
					init:
					setMotion: MoveTo 265 87
				)
				(mosDoor cycleSpeed: 12 setCel: 4 setCycle: BegLoop init:)
				(if (Btst fCaseIsReopened)
					(mosely posn: 260 82 loop: 2 setCycle: StopWalk -1 init:)
					(self setScript: moselyLeaves)
				)
				(if (== currentDay 6) (curRoom setScript: getOuttaHere))
				(extDoor init: stopUpd:)
			)
			(else
				(deskSarg init:)
				(curRoom setScript: sGabeEnters)
				(mosDoor init: stopUpd:)
				(cond 
					((or (== currentDay 4) (== currentDay 5))
						(ego setScript: whinerTimerScr)
					)
					(
						(and
							(== currentDay 6)
							(not ((inventory at: iTracker) owner?))
							(Btst fBeignetHinted)
						)
						(ego setScript: sargSleeping)
					)
					((and (== currentDay 6) (not (Btst fCopiedVeve)))
						(ego setScript: beignetManShows)
					)
				)
			)
		)
		(gate init: stopUpd:)
		(features
			add:
				seal
				inBox
				franksDesk
				copier
				umbrellaStand
				coatRack
				bullBoard
				desk
				fileCab
				mirror
				bench
				thermo
				windows
			eachElementDo: #init
		)
	)
	
	(method (dispose)
		(LoadMany FALSE RANDCYC CONV)
		(= narrator oldNarrator)
		(super dispose:)
	)
)

(instance myConv of Conversation)

(instance sGabeEnters of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 900
					loop: 2
					cel: 0
					setCycle: StopWalk -1
					posn: 17 86
					init:
					setMotion: PolyPath 22 97
				)
				(extDoor init: setCel: 6 setCycle: BegLoop self)
			)
			(1
				(theSound2 number: 374 play:)
				(self dispose:)
			)
		)
	)
)

(instance moselyLeaves of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 3)
			)
			(1
				(myConv
					add: -1 N_ROOM_EVENTS NULL C_MOSELY_LEAVES 1
					add: -1 N_ROOM_EVENTS NULL C_MOSELY_LEAVES 2
					add: -1 N_ROOM_EVENTS NULL C_MOSELY_LEAVES 3
					init: self
				)
			)
			(2
				(ego setMotion: PolyPath 292 93 self)
			)
			(3
				(ego setMotion: PolyPath 290 93)
				(mosely setMotion: PolyPath 18 85 self)
				(= seconds 2)
			)
			(4
				(ego setMotion: PolyPath 140 130 self)
			)
			(5
				(mosely dispose:)
				(messager say: N_ROOM_EVENTS NULL C_MOSELY_LEAVES 4 self)
			)
			(6
				(ego setMotion: PolyPath 140 132 self)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance askFranky of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 282 129 self)
			)
			(1
				(Face ego franky self)
			)
			(2
				(franky setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(3
				(messager say: N_FRANKY V_ASK register 0 self)
			)
			(4
				(theGame handsOn:)
				(= interrogateSubject FRANKY)
				(curRoom newRoom: 50)
			)
		)
	)
)

(instance getPhotos of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 2321 setLoop: 1 cel: 0 posn: 191 145 init:)
				(deskSarg
					view: 2321
					init:
					setLoop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: 0
				)
				(= seconds 2)
			)
			(1
				(messager say: N_ROOM_EVENTS NULL C_GET_PHOTOS 1 self)
				(deskSarg view: 232 setLoop: 2 setCycle: 0)
			)
			(2
				(deskSarg
					view: 2321
					init:
					setLoop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: CycleTo 7 1 self
				)
			)
			(3
				(ego setCycle: CycleTo 3 1 self)
			)
			(4
				(deskSarg setCycle: EndLoop self)
			)
			(5
				(ego setCycle: EndLoop self)
			)
			(6
				(ego normalize: 0)
				(messager say: N_ROOM_EVENTS NULL C_GET_PHOTOS 2 self)
			)
			(7
				(theGame handsOn:)
				(ego get: iPhoEnvelope)
				(deskSarg
					view: 232
					setLoop: 0
					cycleSpeed: 6
					setCycle: RandCycle
				)
				(ego getPoints: f230GetPhotos 2)
				(self dispose:)
			)
		)
	)
)

(instance sInGateWithoutPermission of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 103 106 self)
			)
			(1
				(deskSarg
					view: 232
					setLoop: 1
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(2
				(messager say: N_GATE V_MOVE C_UNAUTHORIZED 1 self)
			)
			(3
				(ego
					view: 233
					loop: 0
					cel: 0
					posn: 88 108
					setCycle: EndLoop self
				)
			)
			(4
				(deskSarg
					view: 232
					setLoop: 1
					setCel: 2
					cycleSpeed: 12
					setCycle: BegLoop
				)
				(ego posn: 81 110 view: 900 cel: 4 setLoop: 8)
				(messager say: N_GATE V_MOVE C_UNAUTHORIZED 2 self)
			)
			(5
				(deskSarg setLoop: 0 cycleSpeed: 6 setCycle: RandCycle)
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sInGateWithPermission of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not isDemo)
					(ego setMotion: PolyPath 108 105 self)
				else
					(messager say: NULL NULL C_DEMO 0)
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(1
				(gate
					setLoop: 3
					cycleSpeed: 12
					setCel: 0
					setCycle: CycleTo 2 1
				)
				(ego setMotion: MoveTo 134 103 self)
				(= inOfficialArea TRUE)
			)
			(2
				(gate setPri: 8 setScript: sOscillateGate 0 1)
				(= cycles 1)
			)
			(3
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance outGate of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 133 103 self)
			)
			(1
				(if (and (ego has: iPoliceVeve) franksInRoom)
					(messager say: N_GATE V_MOVE C_NO_EXIT_WITH_FILE 0)
					(theGame handsOn:)
					(self dispose:)
				else
					(gate
						setLoop: 4
						setCel: 0
						cycleSpeed: 12
						setPri: 5
						setCycle: CycleTo 2 1
					)
					(ego setMotion: MoveTo 100 105 self)
				)
			)
			(2
				(gate setScript: sOscillateGate 0 0)
				(= cycles 1)
			)
			(3
				(theGame handsOn:)
				(= inOfficialArea FALSE)
				(self dispose:)
			)
		)
	)
)

(instance sOscillateGate of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gate cycleSpeed: 6 setCycle: BegLoop self)
			)
			(1
				(if register
					(gate setLoop: 4)
				else
					(gate setLoop: 3)
				)
				(gate setCel: 1 setCycle: CycleTo 2 1 self)
			)
			(2
				(gate setCycle: BegLoop self)
			)
			(3
				(if register
					(gate setLoop: 3)
				else
					(gate setLoop: 4)
				)
				(gate setCel: 1)
				(= cycles 1)
			)
			(4
				(gate setCycle: BegLoop self)
			)
			(5
				(gate stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance getOuttaHere of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(theGame handsOff:)
				(messager say: N_ROOM_EVENTS NULL C_GET_OUTTA_HERE 1 self)
			)
			(2
				(deskSarg setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(3
				(messager say: N_ROOM_EVENTS NULL C_GET_OUTTA_HERE 2 self)
			)
			(4
				(Face ego 95 110 self)
			)
			(5
				(messager say: N_ROOM_EVENTS NULL C_GET_OUTTA_HERE 3 self)
			)
			(6
				(ego setScript: outGate self)
			)
			(7
				(deskSarg setCycle: BegLoop self)
			)
			(8
				(deskSarg setLoop: 0 setCycle: RandCycle)
				(self dispose:)
			)
		)
	)
)

(instance sargSleeping of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 5 20))
			)
			(1
				(deskSarg
					setLoop: 3
					cel: 0
					cycleSpeed: 18
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(deskSarg cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(= seconds 3)
			)
			(4
				(deskSarg setLoop: 3 cycleSpeed: 18 setCycle: CycleTo 4 1 self)
			)
			(5
				(deskSarg cycleSpeed: 1 setCycle: EndLoop self)
			)
			(6
				(= seconds 3)
			)
			(7
				(deskSarg
					setLoop: 3
					cel: 0
					cycleSpeed: 18
					setCycle: CycleTo 4 1 self
				)
				(= sargeSleeps TRUE)
			)
			(8
				(= seconds 7)
			)
			(9
				(= sargeSleeps FALSE)
				(deskSarg cycleSpeed: 1 setCycle: EndLoop self)
			)
			(10
				(if inOfficialArea
					(self setScript: getOutOfThere self 38)
				else
					(= seconds 3)
				)
			)
			(11
				(deskSarg setLoop: 0 cycleSpeed: 6 setCycle: RandCycle)
				(-= state 12)
				(self cue:)
			)
		)
	)
)

(instance whinerTimerScr of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds (Random 1 3))
			)
			(1
				(myConv
					add: 230 N_ROOM_EVENTS NULL C_WHINER 1
					add: 230 N_ROOM_EVENTS NULL C_WHINER 2
					add: 230 N_ROOM_EVENTS NULL C_WHINER 3
					init: self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance beignetManShows of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== currentDay 6)
					(ego setMotion: PolyPath 62 112 self)
				else
					(= seconds 2)
				)
			)
			(1
				(theVendor init: setMotion: MoveTo 138 87 self)
			)
			(2
				(theVendor stopUpd:)
				(deskSarg setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(3
				(if (== currentDay 6)
					(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_6 1 self)
				else
					(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_1 1 self)
				)
			)
			(4
				(deskSarg setCycle: BegLoop self)
			)
			(5
				(if (== currentDay 6)
					(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_6 2 self)
				else
					(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_1 2 self)
				)
			)
			(6
				(deskSarg
					view: 232
					setLoop: 0
					cycleSpeed: 6
					setCycle: RandCycle
				)
				(= local13 0)
				(= local12 232)
				(franky view: 238 setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(7
				(franky setCycle: BegLoop self)
			)
			(8
				(chair2 init: addToPic:)
				(franky
					setLoop: 1
					cel: 0
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(9
				(gate setPri: 8)
				(franky
					view: 2361
					loop: 7
					posn: 290 120
					setLoop: -1
					setCycle: Walk
					ignoreHorizon: 1
					setMotion: MoveTo 255 110 self
				)
			)
			(10
				(franky setMotion: PolyPath 133 103 self)
			)
			(11
				(gate
					setLoop: 4
					setCel: 0
					cycleSpeed: 12
					setPri: 5
					setCycle: EndLoop
				)
				(franky setMotion: MoveTo 100 105 self)
			)
			(12
				(franky setMotion: MoveTo 15 91 self)
				(gate setScript: sOscillateGate 0 0)
			)
			(13
				(theSound1 number: 373 play:)
				(extDoor setCycle: EndLoop self)
			)
			(14
				(franky setMotion: MoveTo 15 80 self)
			)
			(15
				(franky setPri: 0 setMotion: MoveTo 60 60 self)
				(extDoor setCycle: BegLoop)
				(theSound2 number: 374 play:)
			)
			(16
				(= franksInRoom 0)
				(franky setMotion: MoveTo 150 80 self)
			)
			(17
				(deskSarg setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(18
				(= seconds 2)
			)
			(19
				(deskSarg setCycle: BegLoop self)
			)
			(20
				(deskSarg setLoop: 0 setCycle: RandCycle)
				(= seconds 4)
			)
			(21
				(deskSarg setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(22 (= seconds 2))
			(23
				(deskSarg setCycle: BegLoop self)
			)
			(24
				(chair1 init: addToPic:)
				(deskSarg view: 2302 setLoop: 6 cel: 0 setCycle: EndLoop self)
				(= local13 0)
				(= local12 0)
			)
			(25
				(deskSarg
					setPri: 5
					view: 2302
					setLoop: 4
					posn: 240 128
					setCycle: Walk
					setMotion: MoveTo 174 99 self
				)
			)
			(26
				(deskSarg setLoop: 1 setMotion: MoveTo 138 99 self)
			)
			(27
				(gate
					setLoop: 4
					setCel: 0
					cycleSpeed: 12
					setPri: 5
					setCycle: CycleTo EndLoop
				)
				(deskSarg
					view: 2301
					setPri: -1
					setLoop: 0
					posn: 118 99
					setCycle: EndLoop self
				)
			)
			(28
				(gate setScript: sOscillateGate 0 0)
				(deskSarg
					view: 2302
					setLoop: 4
					posn: 93 101
					setCycle: Walk
					setMotion: MoveTo 18 85 self
				)
			)
			(29
				(theSound1 number: 373 play:)
				(extDoor setCycle: CycleTo 4 1 self)
			)
			(30
				(deskSarg setLoop: 5)
				(= cycles 1)
			)
			(31
				(if (== currentDay 6)
					(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_6 3 self)
				else
					(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_1 3 self)
				)
			)
			(32
				(if (== currentDay 6)
					(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_6 4 self)
				else
					(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_1 4 self)
				)
			)
			(33
				(if (== currentDay 6)
					(client setScript: daySixBeignet)
				else
					(client setScript: dayOneBeignet)
				)
			)
		)
	)
)

(instance dayOneBeignet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theSound2 number: 374 play:)
				(extDoor setCycle: BegLoop)
				(deskSarg
					view: 2302
					setLoop: 2
					setCycle: Walk
					setMotion: PolyPath 108 105 self
				)
			)
			(1
				(gate
					setLoop: 3
					cycleSpeed: 12
					setCel: 0
					setCycle: CycleTo 2 1
				)
				(deskSarg setLoop: 0 setMotion: MoveTo 134 103 self)
			)
			(2
				(gate setPri: 8 setScript: sOscillateGate 0 1)
				(deskSarg setPri: 5 setMotion: MoveTo 174 99 self)
			)
			(3
				(deskSarg setLoop: 2 setMotion: MoveTo 230 132 self)
			)
			(4
				(deskSarg
					setLoop: 6
					cel: 4
					setPri: 10
					posn: 252 118
					setCycle: BegLoop self
				)
			)
			(5
				(chair1 dispose:)
				(deskSarg view: 232 setLoop: 0 setCycle: RandCycle)
				(= seconds 3)
			)
			(6
				(franky
					view: 2361
					setCycle: Walk
					setMotion: MoveTo 18 75 self
				)
				(= franksInRoom 1)
			)
			(7
				(theSound1 number: 373 play:)
				(extDoor setCycle: EndLoop self)
			)
			(8
				(franky
					setLoop: -1
					setPri: -1
					setMotion: MoveTo 18 85 self
				)
			)
			(9
				(gate setPri: 5)
				(theSound2 number: 374 play:)
				(extDoor setCycle: BegLoop)
				(franky
					setLoop: -1
					loop: 4
					setPri: -1
					setMotion: MoveTo 108 105 self
				)
			)
			(10
				(gate
					setLoop: 3
					cycleSpeed: 12
					setCel: 0
					setCycle: CycleTo 2 1
				)
				(franky setMotion: MoveTo 134 103 self)
			)
			(11
				(gate setScript: sOscillateGate 0 1)
				(franky setMotion: PolyPath 298 118 self)
			)
			(12
				(franky
					view: 238
					setLoop: 0
					cel: 0
					posn: 298 118
					setCycle: EndLoop self
				)
			)
			(13
				(chair2 dispose:)
				(franky view: 2380 setLoop: 0 setCycle: RandCycle)
				(client setScript: vendorSplit)
			)
		)
	)
)

(instance daySixBeignet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_6 5 self)
			)
			(1
				(deskSarg loop: 2)
				(Face ego deskSarg self)
			)
			(2
				(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_6 6 self)
			)
			(3
				(messager say: N_ROOM_EVENTS NULL C_BEIGNET_DAY_6 7 self)
			)
			(4
				(theSound2 number: 374 play:)
				(extDoor setCycle: BegLoop)
				(theGame handsOn:)
				(= sargeInRoom 0)
				(deskSarg
					loop: 5
					setPri: 0
					posn: 40 60
					setMotion: MoveTo 105 85 self
				)
			)
			(5
				(deskSarg z: 1000)
				(= seconds 15)
			)
			(6
				(deskSarg
					view: 237
					setLoop: 7
					z: 0
					setCycle: Walk
					setMotion: PolyPath 40 60 self
				)
			)
			(7
				(theGame handsOff:)
				(deskSarg setLoop: -1 loop: 2 posn: 18 85 setPri: -1)
				(if inOfficialArea
					(self setScript: getOutOfThere self 36)
				else
					(self cue:)
				)
			)
			(8
				(deskSarg setMotion: PolyPath 206 113 self)
			)
			(9
				(deskSarg setMotion: MoveTo 230 132 self)
			)
			(10
				(franky
					view: 2361
					loop: 7
					ignoreHorizon: 1
					z: 0
					setCycle: Walk
					setMotion: MoveTo 60 60 self
				)
				(deskSarg
					view: 232
					setLoop: 4
					cel: 3
					setPri: 10
					posn: 248 120
					setCycle: BegLoop self
				)
			)
			(11
				(deskSarg setLoop: 0)
			)
			(12
				(franky
					view: 2361
					setLoop: -1
					setPri: -1
					loop: 2
					setCycle: Walk
					posn: 18 85
					setMotion: PolyPath 280 120 self
				)
			)
			(13
				(deskSarg setCycle: RandCycle)
				(franky
					view: 238
					setLoop: 0
					cel: 0
					posn: 298 118
					setCycle: EndLoop self
				)
			)
			(14
				(franky view: 2380 setLoop: 1 setCycle: RandCycle)
				(client setScript: vendorSplit)
			)
		)
	)
)

(instance getOutOfThere of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= (ego view?) 900)
					(ego normalize: 3)
					(Face ego deskSarg self)
				else
					(self cue:)
				)
			)
			(1
				(if (== register C_NO_ENTRY)
					(messager say: N_ROOM_EVENTS NULL C_NO_ENTRY 0 self)
				else
					(messager say: N_ROOM_EVENTS NULL C_NO_ENTRY_2 0 self)
				)
			)
			(2
				(ego setMotion: PolyPath 140 130 self)
			)
			(3
				(Face ego 140 135)
				(self dispose:)
			)
		)
	)
)

(instance vendorSplit of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theVendor startUpd: setMotion: MoveTo -20 78 self)
			)
			(1
				(theGame handsOn:)
				(Bset fBeignetHinted)
				(theVendor dispose:)
				(self dispose:)
			)
		)
	)
)

(instance turnUpHeat of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 259 85 self)
			)
			(1
				(Face ego 259 80 self)
			)
			(2
				(messager say: N_THERMOSTAT V_OPERATE C_TURN_UP_HEAT 1 self)
			)
			(3
				(ego
					view: 234
					setLoop: 0
					cel: 0
					cycleSpeed: 12
					posn: 261 85
					setCycle: CycleTo 4 1 self
				)
			)
			(4
				(thermoInset init: self rm230)
			)
			(5
				(ego setCycle: EndLoop self)
			)
			(6
				(ego normalize:)
				(= cycles 4)
			)
			(7
				(ego setMotion: PolyPath 261 88 self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance goToMirror of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 292 79 self)
			)
			(1
				(Face ego 299 80 self)
			)
			(2
				(ego
					view: 234
					setLoop: 1
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3
				(messager say: N_MIRROR V_LOOK NULL 1 self)
				(ego setCycle: BegLoop self)
			)
			(4 0)
			(5
				(if (== register 6)
					(client setScript: goIntoMosOffice)
				else
					(messager say: N_MIRROR V_LOOK C_LOOK_IN_MIRROR (Random 1 3) self)
				)
			)
			(6
				(ego setCycle: BegLoop self)
			)
			(7
				(theGame handsOn:)
				(ego normalize: 0)
				(self dispose:)
			)
		)
	)
)

(instance leaveStation of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 15 91 self)
			)
			(1
				(ego view: 233 setLoop: 5 cel: 0 setCycle: CycleTo 4 1 self)
				(theSound1 number: 373 play:)
				(extDoor setCycle: CycleTo 4 1 self)
			)
			(2 1)
			(3
				(ego setCycle: EndLoop self)
			)
			(4
				(if (ego has: iPoliceVeve)
					(Bset fStoleVeve)
				)
				(ego setLoop: -1)
				(theGame handsOn:)
				(Bclr fMetFranks)
				(Bclr fMetSarge)
				(Bclr fCanEnterOfficialArea)
				(theMusic1 fade:)
				(curRoom newRoom: 200)
			)
		)
	)
)

(instance goIntoMosOffice of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== currentDay 6)
					(ego setMotion: PolyPath 290 88 self)
				else
					(= cycles 1)
				)
			)
			(1
				(Face ego 273 83 self)
			)
			(2
				(mosDoor setCycle: EndLoop)
				(ego
					view: 234
					setLoop: 3
					cel: 0
					posn: 290 88
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(3
				(theGame handsOn:)
				(ego normalize:)
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance franksGetsFile of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 900
					loop: 0
					setCycle: StopWalk -1
					posn: 247 120
					init:
				)
				(franky
					setLoop: 4
					cel: 0
					illegalBits: 0
					setCycle: EndLoop self
				)
			)
			(1
				(franky
					view: 2361
					loop: 7
					posn: 290 120
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 293 85 self
				)
			)
			(2
				(franky
					view: 2381
					setLoop: 0
					posn: 293 85
					setCycle: EndLoop self
				)
			)
			(3 (franky setCycle: BegLoop self))
			(4
				(franky
					view: 2361
					loop: 6
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 298 118 self
				)
			)
			(5
				(messager say: N_ROOM_EVENTS NULL C_GET_VEVE 0 self)
				(ego get: iPoliceVeve)
			)
			(6
				(franky view: 238 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(7
				(theGame handsOn:)
				(franky view: 2380 setLoop: 0 setCycle: Forward)
				(self dispose:)
			)
		)
	)
)

(instance stroll2 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setLoop: 7 setMotion: MoveTo 221 123 self)
			)
			(1
				(= seconds 4)
			)
			(2
				(client setLoop: 4 setMotion: MoveTo 350 140 self)
			)
			(3
				(= seconds 1)
			)
			(4
				(-= state 5)
				(self cue:)
			)
		)
	)
)

(instance stroll3 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 4
					setMotion: MoveTo (Random 90 260) 240 self
				)
			)
			(1
				(= seconds (Random 4 12))
			)
			(2
				(client
					setLoop: 7
					setMotion: MoveTo -30 (Random 120 125) self
				)
			)
			(3
				(= seconds (Random 4 12))
			)
			(4
				(-= state 5)
				(self cue:)
			)
		)
	)
)

(instance goToCopier of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 223 97 self)
			)
			(1
				(Face ego 218 92 self)
			)
			(2
				(ego view: 235 setLoop: 0 cel: 0)
				(= cycles 1)
			)
			(3
				(if franksInRoom
					(messager say: N_COPIER V_POLICE_VEVE C_COPY_STOPPED 1 self)
				else
					(self cue:)
				)
			)
			(4
				(if franksInRoom
					(ego normalize: 7)
					(ego setMotion: PolyPath 227 107 self)
				else
					(client setScript: useCopyMachine)
				)
			)
			(5
				(messager say: N_COPIER V_POLICE_VEVE C_COPY_STOPPED 2 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance useCopyMachine of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: N_COPIER V_POLICE_VEVE C_COPY_SUCCESS 1 self)
			)
			(1
				(ego setLoop: 2 cycleSpeed: 12 cel: 0 setCycle: EndLoop self)
			)
			(2
				(= seconds 2)
			)
			(3
				(ego setLoop: 1 cel: 0 setCycle: EndLoop self)
			)
			(4
				(= seconds 2)
			)
			(5
				(ego setLoop: 4 cel: 0 posn: 216 93 setCycle: EndLoop self)
			)
			(6
				(theGame handsOn:)
				(ego normalize: 2)
				(Bset fCopiedVeve)
				(ego get: iVeveCopy posn: 220 93)
				(self dispose:)
			)
		)
	)
)

(instance putVeveInBox of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 282 129 self)
			)
			(1
				(cond 
					((Btst fStoleVeve)
						(messager say: N_INBOX register C_STOLE_VEVE 1 self)
					)
					(franksInRoom
						(ego put: iPoliceVeve)
						(messager say: N_INBOX register C_PUT_AWAY_FILE 1 self)
					)
					(else
						(ego put: iPoliceVeve)
						(messager say: N_INBOX register C_PUT_VEVE_IN_BOX 2 self)
					)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance grabOriginalVeve of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 276 120 self)
			)
			(1
				(ego
					view: 235
					loop: 0
					cel: 0
					setPri: 10
					signal: 16
					setCycle: EndLoop self
				)
			)
			(2
				(if franksInRoom
					(messager say: N_INBOX V_TAKE register 0 self)
				else
					(= cycles 1)
				)
			)
			(3
				(theGame handsOn:)
				(ego normalize: 4)
				(ego get: iPoliceVeve)
				(self dispose:)
			)
		)
	)
)

(instance sGiveFileToFranks of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 276 120 self)
			)
			(1
				(ego
					view: 235
					loop: 0
					cel: 0
					setPri: 10
					signal: 16
					setCycle: EndLoop self
				)
			)
			(2
				(franky view: 238 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(franky view: 2380 setLoop: 1 setCycle: EndLoop self)
			)
			(4
				(franky view: 2380 setLoop: 0 setCycle: Forward)
				(ego normalize: 4)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sShowStuffToSarg of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local14 1)
				(theGame handsOff:)
				(if (or (!= (ego x?) 191) (!= (ego y?) 145))
					(ego setMotion: PolyPath 191 145 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					view: 904
					cel: 0
					setLoop: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(2
				(messager say: N_SHOW_ITEM NULL C_SHOW_STUFF_TO_SARGE 1 self)
			)
			(3
				(deskSarg view: 232 setLoop: 1 cel: 0 setCycle: EndLoop self)
				(= local13 1)
				(= local12 232)
			)
			(4
				(= cycles 1)
			)
			(5
				(if (and msgType (Message MsgGet 230 N_SARGE register NULL 1))
					(messager say: N_SARGE register NULL 0 self)
				else
					(messager say: N_SARGE NULL NULL 0 self)
				)
			)
			(6
				(deskSarg view: 232 setLoop: 1 cel: 2 setCycle: BegLoop self)
			)
			(7
				(deskSarg
					view: 232
					setLoop: 0
					cycleSpeed: 6
					setCycle: RandCycle
				)
				(= local13 0)
				(= local12 232)
				(ego
					view: 904
					cel: 9
					setLoop: 0
					cycleSpeed: 12
					setCycle: BegLoop self
				)
			)
			(8
				(ego normalize:)
				(= local14 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance stepToSergeant of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (!= (ego x?) 191) (!= (ego y?) 145))
					(ego setMotion: PolyPath 191 145 self)
				else
					(= cycles 1)
				)
			)
			(1
				(switch register
					(11
						(client setScript: talkToSergeant)
					)
					(10
						(client setScript: askSergeant)
					)
				)
			)
		)
	)
)

(instance askSergeant of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(deskSarg view: 232 setLoop: 1 cel: 0 setCycle: EndLoop self)
				(= local13 1)
				(= local12 232)
			)
			(1
				(if (Btst fMetSarge)
					(messager say: N_SARGE V_ASK C_ASK_AGAIN 0 self)
				else
					(Bset fMetSarge)
					(messager say: N_SARGE V_ASK C_ASK_FIRST 0 self)
				)
			)
			(2
				(= interrogateSubject SARGE)
				(curRoom newRoom: 50)
			)
		)
	)
)

(instance talkToSergeant of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (++ sargeTalkCount)
					(1
						(= register C_TALK_SARGE_1)
					)
					(2
						(= register C_TALK_SARGE_2)
					)
					(3
						(= register C_TALK_SARGE_3)
					)
					(else
						(= register C_TALK_SARGE_4)
					)
				)
				(messager say: N_SARGE V_TALK register 0 self)
			)
			(1
				(deskSarg
					view: 232
					setLoop: 0
					cycleSpeed: 6
					setCycle: RandCycle
				)
				(= local13 0)
				(= local12 232)
				(ego normalize: 0)
				(if (and (not (Btst fBeignetHinted)) (< currentDay 4) (not isDemo))
					(curRoom setScript: beignetManShows)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance deskSarg of Actor
	(properties
		x 252
		y 118
		noun N_SARGE
		view 232
		priority 11
		signal (| ignrAct ignrHrz fixPriOn)
		illegalBits $0000
	)
	
	(method (init)
		(self setCycle: RandCycle)
		(= sargeInRoom TRUE)
		(super init:)
	)
	
	(method (dispose)
		(= sargeInRoom FALSE)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(if (not (OneOf theVerb V_OPERATE V_OPEN V_TAKE V_ASK V_TALK V_MOVE V_LOOK V_WALK))
				(if (not inOfficialArea)
					(if (OneOf theVerb V_KEY_ENVELOPE V_MOSELY_KEY V_MOSELY_LETTER V_POLICE_VEVE V_VEVE_COPY V_TRACKER)
						(messager say: noun theVerb)
					else
						(curRoom setScript: sShowStuffToSarg 0 theVerb)
					)
				else
					(messager say: noun V_ASK C_IN_OFFICIAL_AREA 0)
				)
				(return TRUE)
			else
				(switch theVerb
					(V_LOOK
						(if sargeSleeps
							(messager say: noun theVerb C_SARGE_SLEEPS 0)
						else
							(messager say: noun theVerb NULL 0)
						)
					)
					(V_TALK
						(cond 
							(sargeSleeps
								(messager say: noun theVerb 39 0)
							)
							(inOfficialArea
								(messager say: noun V_ASK C_IN_OFFICIAL_AREA 0)
							)
							(else
								(curRoom setScript: stepToSergeant 0 theVerb)
							)
						)
					)
					(V_ASK
						(if inOfficialArea
							(messager say: noun theVerb C_IN_OFFICIAL_AREA 0)
						else
							(curRoom setScript: stepToSergeant 0 theVerb)
						)
					)
					(else
						(super doVerb: theVerb)
					)
				)
			)
		)
	)
)

(instance franky of Actor
	(properties
		x 298
		y 118
		noun N_FRANKY
		view 2380
		signal (| ignrAct ignrHrz skipCheck)
		illegalBits $0000
	)
	
	(method (init)
		(= franksInRoom TRUE)
		(self setCycle: Forward)
		(super init:)
	)
	
	(method (dispose)
		(= franksInRoom FALSE)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp theCase)
		(switch theVerb
			(V_ASK
				(if inOfficialArea
					(cond 
						((Btst fStoleVeve)
							(= theCase C_ASK_FRANKY_UPSET)
						)
						((Btst fMetFranks)
							(= theCase C_ASK_FRANKY_AGAIN)
						)
						((not (Btst fMetFranks))
							(Bset fMetFranks)
							(Bset fFlag13)
							(= theCase C_ASK_FRANKY_FIRST)
						)
					)
					(curRoom setScript: askFranky 0 theCase)
				else
					(messager say: noun theVerb C_TOO_FAR_TO_ASK 0)
				)
			)
			(V_LOOK
				(if (Btst fStoleVeve)
					(= theCase C_STOLE_VEVE)
				else
					(= theCase NULL)
				)
				(messager say: noun theVerb theCase 1)
			)
			(V_TAKE
				(if inOfficialArea
					(if flirtedWithFranky
						(= theCase C_ALREADY_FLIRTED)
					else
						(= theCase C_FLIRT)
						(= flirtedWithFranky TRUE)
					)
					(messager say: noun theVerb theCase 0)
				else
					(messager say: noun NULL C_IN_LOBBY 0)
				)
			)
			(V_TALK
				(if inOfficialArea
					(switch (++ frankyTalkCount)
						(1
							(= theCase C_TALK_FRANKY_1)
						)
						(2
							(= theCase C_TALK_FRANKY_2)
						)
						(3
							(= theCase C_TALK_FRANKY_3)
						)
						(else
							(= theCase C_TALK_FRANKY_4)
						)
					)
					(messager say: noun theVerb theCase 0)
				else
					(messager say: noun NULL C_IN_LOBBY 0)
				)
			)
			(V_POLICE_VEVE
				(if inOfficialArea
					(ego put: iPoliceVeve)
					(if (Btst fStoleVeve)
						(= theCase C_STOLE_VEVE)
					else
						(= theCase C_RETURN_VEVE)
					)
					(messager say: noun theVerb theCase 0)
					(self setScript: sGiveFileToFranks)
				else
					(messager say: noun NULL C_IN_LOBBY 0)
				)
			)
			(else 
				(if inOfficialArea
					(super doVerb: theVerb)
				else
					(messager say: noun NULL C_IN_LOBBY 0)
				)
			)
		)
	)
)

(instance mosely of Actor
	(properties
		view 245
		signal (| ignrAct ignrHrz)
		illegalBits $0000
	)
)

(instance theVendor of Actor
	(properties
		x 229
		y 87
		view 231
		priority 1
		signal (| ignrAct ignrHrz fixPriOn)
		illegalBits $0000
	)
)

(instance cop2 of Actor
	(properties
		view 237
		signal $4000
	)
	
	(method (init)
		(self
			setCycle: StopWalk -1
			posn: 330 140
			setLoop: 7
			setScript: stroll2
		)
		(super init:)
	)
)

(instance cop3 of Actor
	(properties
		view 237
		signal ignrAct
		illegalBits $0000
	)
	
	(method (init)
		(self
			setCycle: StopWalk -1
			posn: -30 120
			setLoop: 4
			setScript: stroll3
		)
		(super init:)
	)
)

(instance frankCam of View
	(properties
		x 288
		y 124
		z 26
		noun N_FRANK_CAMERA
		view 238
		loop 3
		priority 9
		signal (| ignrAct fixPriOn)
	)
)

(instance chair2 of View
	(properties
		x 307
		y 114
		view 2380
		loop 2
		cel 1
	)
)

(instance chair1 of View
	(properties
		x 262
		y 117
		view 232
		loop 4
	)
)

(instance mosDoor of Prop
	(properties
		x 250
		y 76
		noun N_MOSELY_DOOR
		sightAngle 40
		approachDist 34
		view 2300
		loop 1
		signal ignrAct
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
			(V_OPEN
				(if inOfficialArea
					(if (== currentDay 6)
						(curRoom setScript: goIntoMosOffice)
					else
						(curRoom setScript: goToMirror 0 theVerb)
					)
				else
					(messager say: N_MOSELY_DOOR NULL C_IN_LOBBY 0)
				)
			)
			(else 
				(if inOfficialArea
					(super doVerb: theVerb)
				else
					(messager say: N_MOSELY_DOOR NULL C_IN_LOBBY 0)
				)
			)
		)
	)
)

(instance extDoor of Prop
	(properties
		x 41
		y 79
		noun N_EXIT_DOOR
		sightAngle 40
		approachX 264
		approachY 83
		view 2300
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_OPEN
				(if inOfficialArea
					(messager say: N_EXIT_DOOR V_OPEN C_IN_OFFICIAL_AREA 2)
				else
					(curRoom setScript: leaveStation)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance gate of Prop
	(properties
		x 103
		y 92
		noun N_GATE
		sightAngle 40
		view 2300
		loop 3
		signal ignrAct
	)
	
	(method (init)
		(if inOfficialArea
			(self setPri: 8)
		else
			(self setPri: 5)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (or (== theVerb V_OPEN) (== theVerb V_MOVE))
			(theGame handsOff:)
			(cond 
				(inOfficialArea
					(curRoom setScript: outGate)
				)
				((or (Btst fCanEnterOfficialArea) sargeSleeps (not sargeInRoom))
					(curRoom setScript: sInGateWithPermission)
				)
				(else
					(curRoom setScript: sInGateWithoutPermission)
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance seal of Feature
	(properties
		x 81
		y 122
		noun N_SEAL
		sightAngle 40
		approachX 139
		approachY 109
		approachDist 30
	)
	
	(method (init)
		(self
			setOnMeCheck: ftrPolygon
				((Polygon new:)
					type: PTotalAccess
					init:
						78 109
						103 110
						127 117
						146 130
						147 141
						143 144
						40 144
						31 135
						32 122
						45 114
						66 109
					yourself:
				)
		)
		(super init:)
	)
)

(instance copier of Feature
	(properties
		x 216
		y 74
		noun N_COPIER
		sightAngle 40
		approachX 223
		approachY 92
		approachDist 30
	)
	
	(method (init)
		(self
			setOnMeCheck: ftrPolygon
				((Polygon new:)
					type: PTotalAccess
					init:
						190 83
						190 77
						184 76
						184 63
						189 63
						189 56
						234 55
						244 63
						244 85
						202 88
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_POLICE_VEVE
				(if (Btst fCopiedVeve)
					(messager say: noun theVerb C_ALREADY_COPIED_FILE 1)
				else
					(curRoom setScript: goToCopier)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance umbrellaStand of Feature
	(properties
		x 79
		y 81
		noun N_UMBRELLA_STAND
		nsTop 70
		nsLeft 72
		nsBottom 93
		nsRight 86
		sightAngle 40
		approachX 80
		approachY 95
		approachDist 22
	)
)

(instance coatRack of Feature
	(properties
		x 66
		y 59
		noun N_COAT_RACK
		sightAngle 40
		approachX 66
		approachY 93
		approachDist 43
	)
	
	(method (init)
		(self
			setOnMeCheck: ftrPolygon
				((Polygon new:)
					type: PTotalAccess
					init:
						63 83
						69 82
						67 43
						64 37
						68 40
						69 24
						71 24
						71 28
						74 26
						72 32
						71 38
						76 36
						72 44
						72 82
						75 82
						76 86
						64 86
					yourself:
				)
		)
		(super init:)
	)
)

(instance bullBoard of Feature
	(properties
		x 214
		y 25
		noun N_BULLETIN_BOARD
		nsTop 22
		nsLeft 195
		nsBottom 50
		nsRight 234
		sightAngle 40
		approachX 224
		approachY 91
		approachDist 68
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if inOfficialArea
					(messager say: noun V_LOOK C_IN_OFFICIAL_AREA (Random 1 7))
				else
					(messager say: noun V_LOOK C_IN_LOBBY 1)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance desk of Feature
	(properties
		x 219
		y 131
		noun N_DESK
		sightAngle 40
		approachX 190
		approachY 145
		approachDist 62
	)
	
	(method (init)
		(self
			setOnMeCheck: ftrPolygon
				((Polygon new:)
					type: PTotalAccess
					init:
						190 109
						220 104
						253 122
						232 130
					yourself:
				)
		)
		(super init:)
	)
)

(instance fileCab of Feature
	(properties
		x 306
		y 65
		noun N_FILE_CABINET
		nsTop 47
		nsLeft 293
		nsBottom 84
		nsRight 319
		sightAngle 40
		approachX 299
		approachY 88
		approachDist 38
	)
)

(instance windows of Feature
	(properties
		x 150
		y 43
		noun N_WINDOWS
		nsTop 22
		nsLeft 114
		nsBottom 65
		nsRight 187
		sightAngle 40
		approachX 153
		approachY 95
		approachDist 55
	)
)

(instance inBox of Feature
	(properties
		x 302
		y 150
		noun N_INBOX
		sightAngle 40
		approachX 282
		approachY 129
	)
	
	(method (init)
		(self
			setOnMeCheck: ftrPolygon
				((Polygon new:)
					type: PTotalAccess
					init:
						292 99
						302 95
						313 101
						304 103
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (and (not (ego has: iPoliceVeve)) (Btst fFlag13))
					(messager say: noun theVerb C_FILE_IN_BOX 0)
				else
					(messager say: noun theVerb C_INBOX_EMPTY 0)
				)
			)
			(V_TAKE
				(if inOfficialArea
					(cond 
						((ego has: iPoliceVeve)
							(messager say: noun theVerb C_INBOX_EMPTY 1)
						)
						((Btst fCopiedVeve)
							(messager say: noun theVerb C_ALREADY_COPIED_FILE 1)
						)
						((and franksInRoom (Btst fFlag13))
							(curRoom setScript: grabOriginalVeve 0 26)
						)
						((Btst fFlag13)
							(curRoom setScript: grabOriginalVeve 0 18)
						)
						(else
							(messager say: noun theVerb C_INBOX_EMPTY 1)
						)
					)
				else
					(messager say: N_INBOX V_TAKE C_IN_LOBBY 1)
				)
			)
			(V_POLICE_VEVE
				(curRoom setScript: putVeveInBox 0 theVerb)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance franksDesk of Feature
	(properties
		x 299
		y 140
		noun N_FRANKS_DESK
		sightAngle 40
		approachX 275
		approachY 120
		approachDist 24
	)
	
	(method (init)
		(self
			setOnMeCheck: ftrPolygon
				((Polygon new:)
					type: PTotalAccess
					init:
						280 124
						282 102
						279 101
						278 98
						310 95
						312 89
						319 87
						319 130
						302 132
					yourself:
				)
		)
		(super init:)
	)
)

(instance mirror of Feature
	(properties
		x 307
		y 32
		noun N_MIRROR
		nsTop 22
		nsLeft 296
		nsBottom 42
		nsRight 319
		sightAngle 40
		approachX 311
		approachY 85
		approachDist 53
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if inOfficialArea
					(curRoom setScript: goToMirror 0 theVerb)
				else
					(messager say: N_MIRROR V_LOOK C_IN_LOBBY 0)
				)
			)
			(else 
				(if inOfficialArea
					(super doVerb: theVerb)
				else
					(messager say: N_MIRROR NULL C_IN_LOBBY 0)
				)
			)
		)
	)
)

(instance bench of Feature
	(properties
		x 16
		y 134
		noun N_BENCH
		sightAngle 40
		approachX 31
		approachY 137
		approachDist 16
	)
	
	(method (init)
		(self
			setOnMeCheck: ftrPolygon
				((Polygon new:)
					type: PTotalAccess
					init:
						35 144
						0 145
						1 120
					yourself:
				)
		)
		(super init:)
	)
)

(instance thermo of Feature
	(properties
		x 238
		y 38
		noun N_THERMOSTAT
		nsTop 30
		nsLeft 235
		nsBottom 40
		nsRight 242
		sightAngle 36
		approachX 248
		approachY 87
		approachDist 50
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(super doVerb: theVerb)
			)
			(V_OPERATE
				(cond 
					((Btst fTurnedUpThermostat)
						(messager say: noun theVerb C_ALREADY_TURNED_UP_HEAT 1)
					)
					(inOfficialArea
						(curRoom setScript: turnUpHeat)
					)
					(else
						(messager say: noun theVerb C_IN_LOBBY 1)
					)
				)
			)
			(else 
				(if inOfficialArea
					(super doVerb: theVerb)
				else
					(messager say: noun V_OPERATE C_IN_LOBBY 1)
				)
			)
		)
	)
)

(instance thermoInset of Inset
	(properties
		view 2341
		x 82
		y 22
	)
	
	(method (doVerb)
		(self dispose:)
	)
)

(instance policeNarrator of Narrator
	(properties
		modeless TRUE
	)
	
	(method (init)
		(= systemWindow SysWindow)
		(self
			color: myHighlightColor
			back: myLowlightColor
			font: userFont
			talkWidth: 314
			x: 0
			y: 147
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow BorderWindow)
		(cond 
			((and (== local12 232) (== local13 0))
				(deskSarg
					view: 232
					setLoop: 0
					cycleSpeed: 6
					setCycle: RandCycle
				)
				(= local13 0)
				(= local12 232)
			)
			((and (== local12 232) (== local13 1))
				(deskSarg view: 232 setLoop: 1 setCel: 2 setCycle: 0)
			)
		)
		(if (and local14 (ego talking?))
			(newProp setCycle: 0 dispose:)
			(ego talking: 0)
		)
		(if (ego talking?)
			(newProp setCycle: 0 dispose:)
			(ego
				view: (ego oldView?)
				loop: (ego oldLoop?)
				cel: (ego oldCel?)
				signal: (ego oldSig?)
				talking: 0
			)
		)
		(super dispose:)
	)
	
	(method (display theText &tmp theView)
		(= color
			(switch currentTalker
				(GABRIEL 54)
				(NARRATOR 7)
				(SARGE 60)
				(MOSELY 30)
				(FRANKY 47)
				(else  myHighlightColor)
			)
		)
		(switch currentTalker
			(GABRIEL
				(if local14
					(ego talking: TRUE)
					((= newProp (Prop new:))
						view: 1900
						loop: 0
						cel: 0
						posn: (ego x?) (ego y?)
						ignoreActors:
						init:
						setCycle: RandCycle (* (StrLen theText) 3) 0 1
					)
				)
				(if
					(and
						(== currentTalker GABRIEL)
						(cast contains: ego)
						(or (== (ego view?) 901) (== (ego view?) 900))
						(== (ego loop?) 8)
						(not (ego scaleSignal?))
					)
					(if (or (== (ego cel?) 4) (== (ego cel?) 5))
						(= theView (+ (ego view?) 1000))
					else
						(if (== (ego view?) 900)
							(= theView (+ (ego view?) 1010 (ego cel?)))
						else
							(= theView (+ (ego view?) 1039 (ego cel?)))
						)
						(switch (Random 0 2)
							(0 1)
							(1
								(+= theView 10)
							)
							(2
								(= theView (+ (ego view?) 1000))
							)
						)
					)
					(ego
						talking: TRUE
						oldView: (ego view?)
						oldLoop: (ego loop?)
						oldCel: (ego cel?)
						oldSig: (ego signal?)
						view: theView
					)
					((= newProp (Prop new:))
						view: theView
						loop: (ego cel?)
						cel: 0
						posn: (ego x?) (ego y?)
						ignoreActors:
						cycleSpeed: 14
						init:
						setCycle: Forward
					)
				)
			)
			(SARGE
				(switch (deskSarg loop?)
					(0
						(deskSarg view: 2322 loop: 0 setCycle: RandCycle)
					)
					(1
						(deskSarg
							view: 232
							loop: 2
							setCycle: RandCycle (* (StrLen theText) 3) 0 1
						)
					)
				)
			)
		)
		(super display: theText)
	)
)
