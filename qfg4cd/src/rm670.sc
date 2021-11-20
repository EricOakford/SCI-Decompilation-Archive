;;; Sierra Script 1.0 - (do not remove this comment)
(script# 670)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use Intrface)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Reverse)
(use Sound)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm670 0
	proc670_1 1
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
	gTheObj_2CycleSpeed
	gTheObj_2Loop
	local11
	local12
	local13
	local14
	local15
)
(procedure (proc670_1)
	(theGame handsOff:)
	(if debugging
		(if (== (= local0 (GetNumber {Event #?})) 2)
			(= prevRoomNum 680)
		)
	else
		(= local0
			(cond 
				((== prevRoomNum 680) 2)
				((!= prevRoomNum 628) 1)
				(else 0)
			)
		)
	)
	(theMusic number: 670 setLoop: -1 play:)
	(switch prevRoomNum
		(680
			(Bclr 35)
			(if (Btst 235)
				(ego
					view: 671
					setLoop: 0 1
					setCel: 0
					posn: 188 115
					init:
					ignoreActors:
					setPri: 152
					setScaler: Scaler 82 60 114 101
					setScript: sKatrinaHere
				)
				(heroTeller init: ego 670 18 128 2)
				(pKatrina init: setScaler: Scaler 82 60 114 101)
			else
				(ego
					posn: 261 99
					init:
					setScaler: Scaler 82 60 114 101
					normalize:
					setScript: sComeOnIn
				)
			)
		)
		(else 
			(ego
				view: 35
				setLoop: 3 1
				setCel: 5
				posn: 188 123
				setPri: 174
				init:
				setScaler: Scaler 82 60 114 101
			)
			(mouseDownHandler addToFront: fPlatform self)
			(keyDownHandler addToFront: fPlatform self)
			(walkHandler addToFront: self)
			(= local4 1)
			(pAdavis init: setPri: 174)
			(curRoom setScript: sAdavisHere)
		)
	)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: 2
				init:
					15
					90
					18
					99
					44
					150
					64
					166
					122
					180
					319
					178
					619
					489
					-300
					489
					-300
					-300
					419
					-1
					619
					124
					271
					108
					239
					105
					155
					82
					62
					78
					66
					82
					106
					87
					174
					102
					173
					113
					249
					113
					250
					145
					235
					152
					139
					152
					122
					133
					93
					126
					95
					132
					105
					142
					113
					151
					124
					158
					147
					160
					175
					168
					176
					172
					153
					170
					132
					169
					112
					164
					97
					155
					78
					137
					71
					113
					64
					90
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 185 102 205 102 205 109 185 109
				yourself:
			)
	)
	(pMainDoor init: approachVerbs: 4)
	(pIMLDoor init: approachVerbs: 4)
	(pIMRDoor init: approachVerbs: 4)
	(vBackGround ignoreActors: approachVerbs: 4 init:)
	(if (not (ego has: 50))
		(vStake init: approachVerbs: 4)
	)
	(if (not (ego has: 49))
		(vHammer init: approachVerbs: 4)
	)
	(vChain1 init: ignoreActors:)
	(vChain2 init: ignoreActors:)
	(super init: &rest)
	(fCreatures init:)
	(fMaiden init: approachVerbs: 4)
	(fCrane init: approachVerbs: 4)
	(fGrate init: approachVerbs: 4)
	(fCollar init: approachVerbs: 4)
	(fSaw init: approachVerbs: 4)
	(fCages init: approachVerbs: 4)
	(fChopBlock init: approachVerbs: 4)
	(fAxe init: approachVerbs: 4)
	(fSkeleton1 init:)
	(fSkeleton2 init:)
	(fMace init: approachVerbs: 4)
	(fPlatform init: approachVerbs: 4)
)

(instance rm670 of GloryRm
	(properties
		noun 15
		picture 670
		west 661
	)
	
	(method (init)
		(theGame handsOff:)
		(if debugging
			(if (== (= local0 (GetNumber {Event #?})) 2)
				(= prevRoomNum 680)
			)
		else
			(= local0
				(cond 
					((== prevRoomNum 680) 2)
					((!= prevRoomNum 628) 1)
					(else 0)
				)
			)
		)
		(theMusic number: 670 setLoop: -1 play:)
		(switch prevRoomNum
			(680
				(Bclr 35)
				(if (Btst 235)
					(ego
						view: 671
						setLoop: 0 1
						setCel: 0
						posn: 188 115
						init:
						ignoreActors:
						setPri: 152
						setScaler: Scaler 82 60 114 101
						setScript: sKatrinaHere
					)
					(heroTeller init: ego 670 18 128 2)
					(pKatrina init: setScaler: Scaler 82 60 114 101)
				else
					(ego
						posn: 261 99
						init:
						setScaler: Scaler 82 60 114 101
						normalize:
						setScript: sComeOnIn
					)
				)
			)
			(else 
				(ego
					view: 35
					setLoop: 3 1
					setCel: 5
					posn: 188 123
					setPri: 174
					init:
					setScaler: Scaler 82 60 114 101
				)
				(mouseDownHandler addToFront: fPlatform self)
				(keyDownHandler addToFront: fPlatform self)
				(walkHandler addToFront: self)
				(= local4 1)
				(pAdavis init: setPri: 174)
				(curRoom setScript: sAdavisHere)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						15
						90
						18
						99
						44
						150
						64
						166
						122
						180
						319
						178
						619
						489
						-300
						489
						-300
						-300
						419
						-1
						619
						124
						271
						108
						239
						105
						155
						82
						62
						78
						66
						82
						106
						87
						174
						102
						173
						113
						249
						113
						250
						145
						235
						152
						139
						152
						122
						133
						93
						126
						95
						132
						105
						142
						113
						151
						124
						158
						147
						160
						175
						168
						176
						172
						153
						170
						132
						169
						112
						164
						97
						155
						78
						137
						71
						113
						64
						90
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 185 102 205 102 205 109 185 109
					yourself:
				)
		)
		(pMainDoor init: approachVerbs: 4)
		(pIMLDoor init: approachVerbs: 4)
		(pIMRDoor init: approachVerbs: 4)
		(vBackGround ignoreActors: approachVerbs: 4 init:)
		(if (not (ego has: 50))
			(vStake init: approachVerbs: 4)
		)
		(if (not (ego has: 49))
			(vHammer init: approachVerbs: 4)
		)
		(vChain1 init: ignoreActors:)
		(vChain2 init: ignoreActors:)
		(super init: &rest)
		(fCreatures init:)
		(fMaiden init: approachVerbs: 4)
		(fCrane init: approachVerbs: 4)
		(fGrate init: approachVerbs: 4)
		(fCollar init: approachVerbs: 4)
		(fSaw init: approachVerbs: 4)
		(fCages init: approachVerbs: 4)
		(fChopBlock init: approachVerbs: 4)
		(fAxe init: approachVerbs: 4)
		(fSkeleton1 init:)
		(fSkeleton2 init:)
		(fMace init: approachVerbs: 4)
		(fPlatform init: approachVerbs: 4)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self fPlatform)
		(keyDownHandler delete: self fPlatform)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				local4
				(not (event modifiers?))
				(OneOf (event type?) 4 1)
				(!= ((theIconBar getCursor:) view?) 948)
			)
			(cond 
				((== ((theIconBar getCursor:) view?) 940) (event claimed: 1) (messager say: 17 6 13 1 self))
				((== ((theIconBar getCursor:) view?) 942)
					(event claimed: 1)
					(if (OneOf heroType 0 3)
						(= local5 1)
						(curRoom setScript: sUnlockChains)
					else
						(messager say: 14 4 20)
					)
				)
				(
					(and
						(== ((theIconBar getCursor:) view?) 905)
						(== ((theIconBar getCursor:) loop?) 4)
						(== ((theIconBar getCursor:) cel?) 13)
						(< 169 (event x?))
						(< (event x?) 210)
						(< 88 (event y?))
						(< (event y?) 119)
						(not (curRoom script?))
					)
					(event claimed: 1)
					(curRoom setScript: sUnlockChains)
				)
				(else (event claimed: 0) (super handleEvent: event &rest))
			)
		else
			(super handleEvent: event &rest)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1) (messager say: 0 1 0 0 0 670))
		(if local4
			(switch theVerb
				(92 (messager say: 17 6 33))
				(91 (messager say: 17 6 33))
				(80
					(if local6
						(messager say: 17 6 31)
					else
						(= local6 1)
						(curRoom setScript: sUnlockChains)
					)
				)
				(else  (super doVerb: theVerb))
			)
		else
			(switch theVerb
				(92 (messager say: 17 6 33))
				(91 (messager say: 17 6 33))
				(80
					(if local6
						(messager say: 17 6 31)
					else
						(= local6 1)
						(curRoom setScript: sUnlockChains)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sGoToSkeleton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if local12
					(ego setMotion: PolyPath 214 173 self)
				else
					(ego setMotion: PolyPath 58 160 self)
				)
			)
			(1
				(if local12 (ego setLoop: 4 1) else (ego setLoop: 5 1))
				(ego normalize:)
				(= ticks 30)
			)
			(2
				(if local12
					(messager say: 12 1 0 1 self)
				else
					(messager say: 11 1 0 1 self)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUnlockChains of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					(local6 (self cue:))
					(local5 (messager say: 14 4 19 1 self))
					(else (messager say: 14 42 0 1 self))
				)
			)
			(1
				(mouseDownHandler delete: self)
				(keyDownHandler delete: self)
				(walkHandler delete: self)
				(ego setCycle: Beg self)
			)
			(2
				(ego setLoop: 1 1 setCel: 8 posn: 197 125)
				(= cycles 4)
			)
			(3
				(ego setCel: 7 posn: 192 121)
				(= cycles 4)
			)
			(4
				(ego setCel: 6 posn: 193 122)
				(= cycles 4)
			)
			(5
				(if local5
					(ego setCel: 7 posn: 192 121)
					(= cycles 4)
				else
					(self cue:)
				)
			)
			(6
				(if local5
					(ego setCel: 6 posn: 193 122)
					(= cycles 4)
				else
					(self cue:)
				)
			)
			(7
				(if local5
					(ego setCel: 7 posn: 192 121)
					(= cycles 4)
				else
					(self cue:)
				)
			)
			(8
				(if local5
					(ego setCel: 6 posn: 193 122)
					(= cycles 4)
				else
					(self cue:)
				)
			)
			(9
				(ego setCel: 5 posn: 185 121)
				(= cycles 4)
			)
			(10
				(ego setCel: 4 posn: 177 120)
				(= cycles 4)
			)
			(11
				(ego setCel: 3 posn: 166 116 setCycle: CT 0 -1 self)
			)
			(12
				(= local4 0)
				(if local6
					(ego trySkill: 20)
					(= projX (ego x?))
					(= projY (ego y?))
					(curRoom setScript: sCastOpen 0 fPlatform)
				else
					(= local6 1)
					(curRoom setScript: sGetDown)
				)
			)
		)
	)
)

(instance sGetDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: 188 116
					normalize:
					setPri: 174
					setMotion: MoveTo 168 117 self
				)
			)
			(1 (= ticks 30))
			(2
				(ego setMotion: JumpTo 153 126 self)
			)
			(3
				(ego setMotion: MoveTo 140 127 self)
			)
			(4 (= ticks 30))
			(5
				(ego setMotion: JumpTo 126 143 self)
			)
			(6
				(ego normalize: setMotion: MoveTo 111 146 self)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCastOpen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(theGame handsOff:)
				(ego
					setSpeed: defaultCycles
					setHeading: (GetAngle (ego x?) (ego y?) projX projY) self
				)
			)
			(1
				(ego
					view: 14
					loop: 3
					setCel: 0
					posn: 192 115
					setCycle: End self
				)
			)
			(2
				(myProject
					view: 21
					setLoop: 0 1
					x: projX
					y: projY
					setScaler: ego
					cycleSpeed: 0
					priority: 207
					init:
					setCycle: Osc 4 self
				)
				(if register (= local11 (register onMe: projX projY)))
				(ego setCycle: Beg)
				(theMusic2 number: 934 setLoop: 1 1 play:)
			)
			(3
				(myProject hide:)
				(= ticks 12)
			)
			(4
				(ego
					posn: 188 116
					normalize:
					setPri: 174
					setSpeed: gTheObj_2CycleSpeed
				)
				(messager say: 14 80 0 1 self)
			)
			(5
				(curRoom setScript: sGetDown)
			)
		)
	)
)

(instance sAdavisHere of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== state 13)
			(if (> (= register (- register 2)) 0)
				(Palette palSET_FLAG 0 255 (= register (- register 2)))
			else
				(self cue:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register 100)
				(= seconds 2)
			)
			(1 (= seconds 1))
			(2
				(messager say: 16 6 10 0 self)
			)
			(3
				(pAdavis
					view: 675
					setCel: 0
					setLoop: 1 1
					setCycle: CT 7 1 self
				)
			)
			(4
				(getSound play:)
				(myProject
					view: 673
					setLoop: 0 1
					setCel: 0
					posn: 119 75
					init:
					setCycle: End self
				)
			)
			(5
				(myProject hide: dispose:)
				(pAdavis setCel: 7 setCycle: End self)
			)
			(6
				(pAdavis setCel: 0)
				(= cycles 4)
			)
			(7
				(messager say: 17 6 11 1 self)
			)
			(8 (ego setCycle: Beg self))
			(9
				(pAdavis setCycle: CT 7 1 self)
			)
			(10
				(getSound play:)
				(pAdavis setCel: 7 setCycle: End self)
			)
			(11
				(pAdavis setLoop: 2 1 setCel: 0 setCycle: End self)
			)
			(12 (= seconds 2))
			(13 (pAdavis hide: dispose:))
			(14 (= seconds 2))
			(15
				((ScriptID 7 4) init: 17 30)
				(Palette palSET_FLAG 0 255 100)
				(= cycles 24)
			)
			(16
				(messager say: 17 6 25 1 self)
			)
			(17
				(if (== heroType 3)
					(messager say: 17 6 12 1 self)
				else
					(= seconds 2)
				)
			)
			(18 (ego setCycle: End self))
			(19
				(theGame handsOn:)
				(User canControl: 0)
				(self dispose:)
			)
		)
	)
)

(instance sCreatures of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(= ticks 3)
			)
			(1
				(if (== heroType 3)
					(messager say: 17 6 15 1 self)
				else
					(self cue:)
				)
			)
			(2
				(= local7 1)
				(pTenacle1 init: setCycle: End)
				(pTenacle2 init: setCycle: End self)
			)
			(3
				(pTenacle1 setCycle: Beg)
				(pTenacle2 setCycle: Beg)
				(ego
					setCycle: Rev
					setMotion: PolyPath (- (ego x?) 5) (+ (ego y?) 15) self
				)
			)
			(4
				(ego setCycle: 0)
				(cond 
					(local8 (messager say: 17 6 18 1 self))
					(local7 (messager say: 17 6 17 1 self))
					(else (self cue:))
				)
			)
			(5
				(= local8 0)
				(ego normalize:)
				(DisposeScript -567)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance castOpenScript of Script
	(properties)
	
	(method (dispose)
		(= local11 (= register 0))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local14 1)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego trySkill: 20)
				(theGame handsOff:)
				(ego
					setSpeed: defaultCycles
					setHeading: (GetAngle (ego x?) (ego y?) projX projY) self
				)
			)
			(1
				(= gTheObj_2Loop (ego loop?))
				(ego view: 14 loop: 2 setCel: 0 setCycle: End self)
			)
			(2
				(openEffect
					x: projX
					y: projY
					setScaler: ego
					cycleSpeed: 0
					priority: 207
					init:
					setCycle: Osc 4 self
				)
				(ego setCycle: Beg)
				(theMusic2 number: 934 setLoop: 1 1 play:)
			)
			(3
				(openEffect dispose:)
				(= cycles 2)
			)
			(4
				(ego
					setSpeed: gTheObj_2CycleSpeed
					normalize: gTheObj_2Loop
				)
				(self setScript: sEnterMaiden)
			)
		)
	)
)

(instance sEnterMaiden of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(creakDoorSound play:)
				(pIMLDoor setCycle: End)
				(pIMRDoor setCycle: End self)
			)
			(1
				(if (OneOf prevRoomNum 680 628)
					(self cue:)
				else
					(messager say: 5 4 0 1 self)
				)
			)
			(2
				(if local14
					(ego setMotion: PolyPath 253 107 self)
				else
					(self cue:)
				)
			)
			(3
				(ego setMotion: MoveTo 256 101 self)
			)
			(4
				(ego setPri: 53)
				(creakDoorSound play:)
				(pIMLDoor setCycle: Beg)
				(pIMRDoor setCycle: Beg self)
			)
			(5 (curRoom newRoom: 680))
		)
	)
)

(instance sDoorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(switch local2
					(0
						(messager say: 4 4 0 0 self)
						(++ local2)
					)
					(1
						(messager say: 19 6 26 0 self)
						(++ local2)
					)
					(else 
						(messager say: 17 6 16 3 self)
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

(instance sDoorScript1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 17 6 14 0 self)
				((pMainDoor heading?) dispose:)
				(pMainDoor heading: 0)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPickUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 4
					setLoop: 0 1
					setCel: 0
					posn: 176 152
					setCycle: CT 2 1 self
				)
			)
			(1
				(if local3
					(vHammer hide: dispose:)
				else
					(vStake hide: dispose:)
				)
				(= ticks 6)
			)
			(2
				(if local3
					(messager say: 20 4 0 1 self)
				else
					(messager say: 21 4 0 1 self)
				)
			)
			(3
				(getSound play:)
				(ego setCycle: CT 0 -1 self)
			)
			(4
				(ego
					get: (if local3 49 else 50)
					posn: 183 152
					normalize:
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFetch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local3
					(vHammer hide: dispose:)
					(messager say: 20 87 0 1 self)
				else
					(vStake hide: dispose:)
					(messager say: 21 87 0 1 self)
				)
			)
			(1
				(getSound play:)
				(ego get: (if local3 49 else 50))
				(= ticks 6)
			)
			(2 (self dispose:))
		)
	)
)

(instance sComeOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(pIMLDoor setCycle: End)
				(pIMRDoor setCycle: End self)
			)
			(1
				(creakDoorSound play:)
				(ego setMotion: MoveTo 255 118 self)
			)
			(2
				(creakDoorSound play:)
				(pIMLDoor setCycle: Beg)
				(pIMRDoor setCycle: Beg self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKatrinaHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1 (messager say: 1 6 1 0 self))
			(2
				(whipSound play:)
				(pKatrina setCycle: End self)
			)
			(3
				(pKatrina setScript: sWhipIt)
				(katrinaTeller init: pKatrina 670 18 127 2)
				(theGame handsOn:)
				(theIconBar disable: 0 2 4 5 6 7)
				(self dispose:)
			)
		)
	)
)

(instance sWhipIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1
				(whipSound play:)
				(client setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance sFirstFavor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(heroTeller dispose:)
				(katrinaTeller dispose:)
				(= seconds 4)
			)
			(1
				(switch local13
					(1
						(messager say: 18 128 21 0 self)
					)
					(2
						(messager say: 18 128 22 0 self)
					)
					(3
						(messager say: 18 128 7 0 self)
					)
					(4
						(messager say: 18 128 3 0 self)
					)
					(5
						(messager say: 18 128 5 0 self)
					)
					(else 
						(messager say: 18 128 4 0 self)
					)
				)
			)
			(2
				(katrinaTeller2 init: pKatrina 670 18 127 3)
				(heroTeller2 init: ego 670 18 128 3)
				(= seconds 1)
			)
			(3
				(theGame handsOn:)
				(theIconBar disable: 0 2 4 5 6 7)
				(self dispose:)
			)
		)
	)
)

(instance sDoFavor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(heroTeller2 dispose:)
				(katrinaTeller2 dispose:)
				(= seconds 4)
			)
			(1
				(switch local13
					(1
						(messager say: 18 128 23 0 self)
					)
					(2
						(messager say: 18 128 24 0 self)
					)
					(3
						(messager say: 18 128 7 0 self)
					)
					(4
						(messager say: 18 128 6 0 self)
					)
					(5
						(messager say: 18 128 8 0 self)
					)
					(else  (self cue:))
				)
			)
			(2
				(katrinaTeller3 init: pKatrina 670 18 127 22)
				(heroTeller3 init: ego 670 18 128 22)
				(messager say: 1 6 29 0 self)
			)
			(3
				(theGame handsOn:)
				(theIconBar disable: 0 2 4 5 6 7)
				(self dispose:)
			)
		)
	)
)

(instance sGoTo600 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pKatrina ignoreActors: setScript: 0)
				(messager say: 18 128 28 0 self)
			)
			(1
				(Bset 110)
				(= geasDay Day)
				(pKatrina
					view: 638
					setLoop: 1 1
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(getSound play:)
				(pKatrina setCycle: Beg)
				(myProject
					view: 21
					setLoop: 4 1
					setCel: 0
					posn: 155 83
					init:
					moveSpeed: 0
					setCycle: Fwd
					setMotion: MoveTo 187 79 self
				)
			)
			(3
				(messager say: 1 6 30 0 self)
			)
			(4
				(myProject hide:)
				(pKatrina setCel: 0 setCycle: End self)
			)
			(5
				(getSound play:)
				(pKatrina setCycle: Beg)
				(myProject
					posn: 155 83
					setCel: 0
					show:
					setCycle: Fwd
					setMotion: MoveTo 187 79 self
				)
			)
			(6
				(myProject hide:)
				(= ticks 6)
			)
			(7 (curRoom newRoom: 600))
		)
	)
)

(instance sChangeScalerUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScaler: Scaler 82 82 175 90)
				(self cue:)
			)
			(1 (self dispose:))
		)
	)
)

(instance sChangeScalerDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setScaler: Scaler 82 60 114 101)
				(self cue:)
			)
			(1 (self dispose:))
		)
	)
)

(instance myProject of Actor
	(properties
		x 119
		y 75
		priority 185
		fixPriority 1
		view 673
		signal $4001
	)
)

(instance pKatrina of Prop
	(properties
		noun 1
		x 149
		y 130
		priority 163
		fixPriority 1
		view 638
		signal $4001
	)
)

(instance pIMLDoor of Prop
	(properties
		noun 5
		approachX 253
		approachY 107
		x 257
		y 103
		view 670
		loop 4
		signal $4001
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 4) (not local4)) (curRoom setScript: sEnterMaiden))
			((== theVerb -80) (messager say: 5 80 0 0))
			((== theVerb 80) (messager say: 5 80 0 0))
			(else (super doVerb: theVerb))
		)
	)
)

(instance openEffect of Prop
	(properties
		fixPriority 1
		view 21
		signal $6001
	)
)

(instance pIMRDoor of Prop
	(properties
		approachX 253
		approachY 107
		x 257
		y 103
		priority 97
		fixPriority 1
		view 670
		loop 5
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80 (messager say: 5 80 0 0))
			(80 (messager say: 5 80 0 0))
			(else 
				(pIMLDoor doVerb: theVerb)
			)
		)
	)
)

(instance pMainDoor of Prop
	(properties
		noun 4
		approachX 29
		approachY 90
		x 6
		y 35
		priority 64
		fixPriority 1
		view 670
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 8 92 45 87 56 91 15 97
						yourself:
					)
					1
					5
					7
					sDoorScript1
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				(
					(and
						(== theVerb 4)
						(not local4)
						(not (curRoom script?))
					)
					(curRoom setScript: sDoorScript)
					(return 1)
				)
				((== theVerb 80) (messager say: 17 6 32))
				(else (super doVerb: theVerb))
			)
		)
	)
)

(instance pTenacle1 of Prop
	(properties
		noun 25
		x 175
		y 93
		view 670
		loop 6
		signal $1001
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 37)
				(if (== (ego has: 5) 1)
					(messager say: 17 6 42)
				else
					(= local8 1)
					(ego use: 5)
					(curRoom setScript: sCreatures)
				)
			)
			((OneOf theVerb 83 86 79 88 171 91 93 94 84 36) (= local8 1) (curRoom setScript: sCreatures))
			(else (super doVerb: theVerb))
		)
	)
)

(instance pTenacle2 of Prop
	(properties
		noun 25
		x 215
		y 90
		view 670
		loop 7
		signal $1001
	)
	
	(method (doVerb theVerb)
		(pTenacle1 doVerb: theVerb)
	)
)

(instance pAdavis of Prop
	(properties
		noun 16
		x 58
		y 92
		view 675
		loop 1
	)
)

(instance vChain1 of View
	(properties
		noun 26
		x 179
		y 37
		view 671
		loop 3
		cel 1
	)
)

(instance vChain2 of View
	(properties
		noun 26
		x 189
		y 35
		view 671
		loop 3
		cel 1
	)
)

(instance vBackGround of View
	(properties
		noun 4
		approachX 29
		approachY 90
		x 34
		y 86
		fixPriority 1
		view 670
		loop 1
	)
	
	(method (doVerb theVerb)
		(pMainDoor doVerb: theVerb)
	)
)

(instance vStake of View
	(properties
		noun 21
		approachX 183
		approachY 152
		x 191
		y 132
		priority 150
		fixPriority 1
		view 670
		loop 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not local4)
					(= local3 0)
					(curRoom setScript: sPickUp)
				)
			)
			(87
				(= local3 0)
				(curRoom setScript: sFetch)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vHammer of View
	(properties
		noun 20
		approachX 183
		approachY 152
		x 181
		y 130
		priority 150
		fixPriority 1
		view 670
		loop 8
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not local4)
					(= local3 1)
					(curRoom setScript: sPickUp)
				)
			)
			(87
				(= local3 1)
				(curRoom setScript: sFetch)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fCreatures of Feature
	(properties
		nsLeft 164
		nsTop 91
		nsRight 224
		nsBottom 105
		sightAngle 180
		approachX 252
		approachY 157
		x 167
		y 103
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 164 91 224 91 224 105 164 105
						yourself:
					)
					1
					7
					5
					sCreatures
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 17 6 17)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fGrate of Feature
	(properties
		noun 24
		nsLeft 51
		nsTop 31
		nsRight 70
		nsBottom 81
		sightAngle 180
		approachX 175
		approachY 104
		x 167
		y 103
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4) 0 else (super doVerb: theVerb))
	)
)

(instance fMaiden of Feature
	(properties
		noun 5
		nsLeft 248
		nsTop 52
		nsRight 282
		nsBottom 102
		sightAngle 180
		approachX 253
		approachY 107
		x 265
		y 77
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(-80
				(if (not local4)
					(= projX ((User curEvent?) x?))
					(= projY ((User curEvent?) y?))
					(curRoom setScript: castOpenScript)
				else
					(messager say: 5 80 0 0)
				)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(else 
				(pIMLDoor doVerb: theVerb)
			)
		)
	)
)

(instance fCrane of Feature
	(properties
		noun 6
		nsLeft 248
		nsRight 272
		nsBottom 48
		sightAngle 180
		approachX 183
		approachY 152
		x 260
		y 24
	)
)

(instance fCollar of Feature
	(properties
		noun 23
		nsLeft 151
		nsTop 21
		nsRight 218
		nsBottom 37
		sightAngle 180
		approachX 183
		approachY 152
		x 184
		y 29
	)
)

(instance fSaw of Feature
	(properties
		noun 7
		nsLeft 125
		nsTop 6
		nsRight 156
		nsBottom 67
		sightAngle 180
		approachX 120
		approachY 133
		x 140
		y 36
	)
)

(instance fCages of Feature
	(properties
		noun 8
		nsLeft 205
		nsTop 10
		nsRight 240
		nsBottom 72
		sightAngle 180
		approachX 190
		approachY 102
		x 222
		y 41
	)
)

(instance fChopBlock of Feature
	(properties
		noun 9
		nsLeft 189
		nsTop 92
		nsRight 202
		nsBottom 107
		sightAngle 180
		approachX 193
		approachY 113
		x 195
		y 107
	)
)

(instance fAxe of Feature
	(properties
		noun 10
		nsLeft 184
		nsTop 73
		nsRight 207
		nsBottom 96
		sightAngle 180
		approachX 193
		approachY 113
		x 195
		y 84
	)
)

(instance fSkeleton1 of Feature
	(properties
		noun 11
		nsTop 117
		nsRight 32
		nsBottom 188
		sightAngle 40
		approachX 58
		approachY 160
		x 16
		y 152
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 144 165 157 165 205 180 182 186
						yourself:
					)
					1
					7
					5
					sChangeScalerUp
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local12 0)
			(curRoom setScript: sGoToSkeleton)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fSkeleton2 of Feature
	(properties
		noun 12
		nsLeft 273
		nsTop 108
		nsRight 319
		nsBottom 189
		sightAngle 40
		approachX 214
		approachY 173
		x 300
		y 126
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 144 165 157 165 205 180 182 186
						yourself:
					)
					0
					6
					4
					sChangeScalerDown
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local12 1)
			(curRoom setScript: sGoToSkeleton)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fMace of Feature
	(properties
		noun 13
		nsLeft 276
		nsRight 319
		nsBottom 55
		sightAngle 180
		approachX 252
		approachY 157
		x 297
		y 27
	)
)

(instance fPlatform of Feature
	(properties
		noun 14
		nsLeft 136
		nsTop 113
		nsRight 240
		nsBottom 144
		sightAngle 180
		approachX 183
		approachY 152
		x 188
		y 128
	)
	
	(method (doVerb theVerb)
		(if local4
			(switch theVerb
				(80
					(if local6
						(messager say: 17 6 31)
					else
						(= local6 1)
						(curRoom setScript: sUnlockChains)
					)
				)
				(42
					(curRoom setScript: sUnlockChains)
				)
				(4
					(if (OneOf heroType 0 3)
						(= local5 1)
						(curRoom setScript: sUnlockChains)
					else
						(messager say: 14 4 20)
					)
				)
				(else 
					(if (== theVerb 80) 0 else (super doVerb: theVerb))
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance katrinaTeller of Teller
	(properties
		title 1
		actionVerb 2
	)
)

(instance katrinaTeller2 of Teller
	(properties
		title 1
		actionVerb 2
	)
)

(instance katrinaTeller3 of Teller
	(properties
		title 1
		actionVerb 2
	)
)

(instance heroTeller of Teller
	(properties
		loopMenu 0
		actionVerb 2
		thiefSign 0
	)
	
	(method (sayMessage)
		(switch iconValue
			(3
				(= local13 4)
				(self clean:)
				(curRoom setScript: sFirstFavor)
			)
			(5
				(= local13 5)
				(self clean:)
				(curRoom setScript: sFirstFavor)
			)
			(4
				(self clean:)
				(curRoom setScript: sFirstFavor)
			)
			(7
				(= local13 3)
				(self clean:)
				(curRoom setScript: sFirstFavor)
			)
			(21
				(= local13 1)
				(self clean:)
				(curRoom setScript: sFirstFavor)
			)
			(22
				(= local13 2)
				(self clean:)
				(curRoom setScript: sFirstFavor)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super showCases: 21 (Btst 234) 22 (not (Btst 234)))
	)
)

(instance heroTeller2 of Teller
	(properties
		loopMenu 0
		actionVerb 2
		thiefSign 0
	)
	
	(method (sayMessage)
		(switch iconValue
			(23
				(= local13 1)
				(self clean:)
				(curRoom setScript: sDoFavor)
			)
			(24
				(= local13 2)
				(self clean:)
				(curRoom setScript: sDoFavor)
			)
			(6
				(= local13 4)
				(self clean:)
				(curRoom setScript: sDoFavor)
			)
			(8
				(= local13 5)
				(self clean:)
				(curRoom setScript: sDoFavor)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super showCases: 23 (Btst 234) 24 (not (Btst 234)))
	)
)

(instance heroTeller3 of Teller
	(properties
		loopMenu 0
		actionVerb 2
		thiefSign 0
	)
	
	(method (sayMessage)
		(self clean:)
		(curRoom setScript: sGoTo600)
	)
)

(instance getSound of Sound
	(properties
		number 934
	)
)

(instance creakDoorSound of Sound
	(properties
		number 960
	)
)

(instance whipSound of Sound
	(properties
		number 671
	)
)
