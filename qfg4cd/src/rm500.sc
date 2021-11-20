;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use OccCyc)
(use Intrface)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use Reverse)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm500 0
)

(local
	local0
	local1
	local2
	gTheObj_2CycleSpeed
	gTheObj_2MoveSpeed
	theGGGTheObj_2CycleSpeed
	local6
	local7
	local8
)
(instance rm500 of GloryRm
	(properties
		picture 500
		east 571
		south 571
	)
	
	(method (init)
		(Palette palSET_FLAG 0 255 100)
		(if debugging
			(if (GetNumber {Want Igor dead? (1 yes, 0 no)})
				(Bset 38)
			else
				(Bclr 38)
			)
			(Bset 37)
		)
		(cond 
			((and (Btst 37) (not (Btst 48))) (= local0 4) (if (not (Btst 236)) (igor init:)))
			(Night (if (not (Btst 230)) (= local0 3)))
			((not (Btst 229)) (= local0 1))
			((and (not (Btst 37)) (not (Btst 231))) (= local0 2) (Bset 231) (igor init:))
			(else
				(headStone init: setPri: 138)
				(if (Btst 236) (headStone cel: 0))
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						176
						250
						162
						250
						142
						220
						132
						242
						114
						242
						106
						189
						106
						189
						97
						241
						97
						222
						75
						192
						75
						132
						100
						158
						100
						158
						110
						137
						112
						118
						106
						118
						100
						103
						101
						118
						115
						94
						119
						115
						129
						69
						170
						119
						189
						0
						189
						0
						0
						319
						0
					yourself:
				)
				(if
					(or
						(== local0 2)
						(== local0 4)
						(cast contains: headStone)
					)
					((Polygon new:)
						type: 2
						init: 86 169 121 135 187 135 130 179
						yourself:
					)
				else
					((Polygon new:)
						type: 2
						init: 86 169 142 137 161 136 170 143 130 179
						yourself:
					)
				)
		)
		(super init: &rest)
		(ego init: normalize: setScaler: Scaler 110 65 129 87)
		(= local1 224)
		(= local2 181)
		(if (!= prevRoomNum 510)
			(if (== prevRoomNum 810)
				(self setScript: sFromCombat)
			else
				(ego posn: 265 240)
				(self setScript: sEnterScr)
			)
		else
			(ego posn: -500 0)
			(self setScript: sFromCrypt)
		)
		(door init: setPri: 85 approachVerbs: 4 29 42)
		(graveStone init: setPri: 106)
		(ligeiaDoor init: setPri: 131 approachVerbs: 4)
		(moss init: setPri: 148)
		(moss2 init: setPri: 148)
		(moss3 init: setPri: 148)
		(moss4 init: setPri: 148)
		(moss5 init: setPri: 148)
		(shadow init:)
		(shadow2 setPri: 148 init:)
		(shadow3 setPri: 130 init:)
		(coffin init:)
		(coffin2 init:)
		(rusalkaGrave approachVerbs: 4 58 1 init:)
		(loverGrave init: approachVerbs: 4 58 1)
		(cryptDoor init: approachVerbs: 4 29 42 1)
		(doorTeller init: cryptDoor 500 39 155 11)
		(crypt init:)
		(grave1 init:)
		(grave2 init:)
		(grave3 init:)
		(grave4 init:)
		(grave5 init:)
		(urn1 init:)
		(urn2 init:)
		(ligeiaDoorF init: approachVerbs: 4)
		(ligeiaTomb init:)
		(ligeiaTop init:)
		(thinTree init:)
		(thinBranch init:)
		(bigBranch init:)
		(bigTrunk init: approachVerbs: 4)
		(coffins init:)
		(dirtPile init:)
		(openGrave init:)
		(theMusic number: 500 setLoop: -1 play:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			((and (== (ego view?) 7) (ego mover?)) (curRoom setScript: sClimbDown))
		)
	)
	
	(method (dispose)
		(if (theMusic2 number?) (theMusic2 stop:))
		(super dispose:)
	)
)

(instance sEgosGone of Script
	(properties)
	
	(method (dispose)
		(ego
			changeGait: theGGGTheObj_2CycleSpeed
			moveSpeed: gTheObj_2MoveSpeed
			cycleSpeed: gTheObj_2CycleSpeed
		)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1
				(= theGGGTheObj_2CycleSpeed egoGait)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(ego
					changeGait: 1
					moveSpeed: 3
					cycleSpeed: 3
					setMotion: MoveTo 242 260 self
				)
			)
			(2
				(curRoom newRoom: (curRoom south?))
			)
		)
	)
)

(instance sChip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 7 15)))
			(1
				(client
					signal: (| (client signal?) $0001)
					setLoop: 0
					setCycle: Fwd
				)
				(= seconds (Random 3 6))
			)
			(2 (client setCycle: End self))
			(3
				(client setLoop: 1 setCel: 0)
				(if (Random 0 1)
					(client setCycle: End self)
				else
					(self cue:)
				)
			)
			(4 (self changeState: 0))
		)
	)
)

(instance sEnterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (!= local0 2)
					(theMusic2 number: 983 setLoop: -1 play:)
				)
				(ego setMotion: MoveTo local1 local2 self)
			)
			(1
				(= cycles (+ (ego cycleSpeed?) 2))
			)
			(2
				(cond 
					(Night
						(if (not (Btst 230))
							(Bset 230)
							(= register 1)
							(messager say: 37 6 20 0 self)
						else
							(self cue:)
						)
					)
					((not (Btst 229)) (Bset 229) (messager say: 37 6 18 0 self))
					(else (self cue:))
				)
			)
			(3
				(if (and register (== heroType 3))
					(messager say: 37 6 21 0 self)
				else
					(self cue:)
				)
			)
			(4
				(if
				(and (!= local0 2) (> (theGame detailLevel:) 1))
					(moss setScript: sWind)
				)
				(if (and (== local0 4) (not (Btst 38)))
					(soundFX number: 854 play:)
					(self cue:)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(5
				(messager say: 37 6 22)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFromCrypt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (!= local0 2)
					(theMusic2 number: 983 setLoop: -1 play:)
				)
				(door
					signal: (| (door signal?) $0001)
					setCycle: End self
				)
			)
			(1
				(door setPri: 0 signal: (& (door signal?) $fffe))
				(ego
					posn: 87 124
					setLoop: 4 1
					setPri: 2
					setMotion: MoveTo 111 100 self
				)
			)
			(2
				(door
					signal: (| (door signal?) $0001)
					setCycle: Beg self
				)
				(ego normalize: 4)
			)
			(3
				(door signal: (& (door signal?) $fffe))
				(= cycles (+ (ego cycleSpeed?) 2))
			)
			(4
				(cond 
					(Night
						(if (not (Btst 230))
							(Bset 230)
							(= register 1)
							(messager say: 37 6 20 0 self)
						else
							(self cue:)
						)
					)
					((not (Btst 229)) (Bset 229) (messager say: 37 6 18 0 self))
					(else (self cue:))
				)
			)
			(5
				(if (and register (== heroType 3))
					(messager say: 37 6 21 0 self)
				else
					(self cue:)
				)
			)
			(6
				(if (!= local0 2) (moss setScript: sWind))
				(if (and (== local0 4) (not (Btst 38)))
					(theMusic2 number: 983 setLoop: -1 play:)
					(self cue:)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(7
				(messager say: 37 6 22)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWind of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 974 setLoop: 1 play:)
				(moss signal: (| (moss signal?) $0001) setCycle: Osc)
				(moss2 signal: (| (moss2 signal?) $0001) setCycle: Osc)
				(moss3 signal: (| (moss3 signal?) $0001) setCycle: Osc)
				(moss4 signal: (| (moss4 signal?) $0001) setCycle: Osc)
				(moss5 signal: (| (moss5 signal?) $0001) setCycle: Osc)
				(shadow
					signal: (| (shadow signal?) $0001)
					setCycle: Fwd
				)
				(shadow2
					signal: (| (shadow2 signal?) $0001)
					setCycle: Fwd
				)
				(shadow3
					signal: (| (shadow3 signal?) $0001)
					setCycle: Fwd
				)
				(coffin
					signal: (| (coffin signal?) $0001)
					setCycle: Fwd
				)
				(coffin2
					signal: (| (coffin2 signal?) $0001)
					setCycle: Fwd
				)
				(if (= register (Random 0 1))
					(lightning
						init:
						setPri: 126
						signal: (| (lightning signal?) $0001)
						setCel: 0
						show:
						setCycle: End self
					)
				else
					(lightning2
						init:
						setPri: 126
						signal: (| (lightning2 signal?) $0001)
						setCel: 0
						show:
						setCycle: End self
					)
				)
			)
			(1
				(Palette palSET_FLAG 0 255 500)
				(Palette palSET_FLAG 0 255 100)
				(= ticks 6)
			)
			(2
				(Palette palSET_FLAG 0 255 500)
				(Palette palSET_FLAG 0 255 100)
				(if register
					(lightning dispose:)
					(self cue:)
				else
					(lightning2 dispose:)
					(if (not (graveStone cel?))
						(graveStone
							signal: (| (graveStone signal?) $0001)
							setCycle: End self
						)
					else
						(self cue:)
					)
				)
			)
			(3
				(graveStone signal: (& (graveStone signal?) $fffe))
				(= ticks (Random 20 60))
			)
			(4
				(if (and register (< (ego y?) 100))
					(coffin2 setScript: sLiftLid)
				)
				(= ticks (Random 120 240))
			)
			(5
				(moss2 setCel: 0 signal: (& (moss2 signal?) $fffe))
				(moss3 setCel: 0 signal: (& (moss3 signal?) $fffe))
				(moss4 setCel: 0 signal: (& (moss4 signal?) $fffe))
				(moss5 setCel: 0 signal: (& (moss5 signal?) $fffe))
				(shadow signal: (& (shadow signal?) $fffe) setCycle: 0)
				(shadow2
					signal: (& (shadow2 signal?) $fffe)
					setCycle: 0
				)
				(shadow3
					signal: (& (shadow3 signal?) $fffe)
					setCycle: 0
				)
				(coffin signal: (& (coffin signal?) $fffe) setCycle: 0)
				(coffin2
					signal: (& (coffin2 signal?) $fffe)
					setCycle: 0
				)
				(if (> (theGame _detailLevel?) 2)
					(self setScript: sMossBlow self)
				else
					(= seconds (Random 15 20))
				)
			)
			(6
				(if (> (theGame _detailLevel?) 2)
					(self changeState: 0)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sMossBlow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (Random 0 4)
					(0 (= register moss))
					(1 (= register moss2))
					(2 (= register moss3))
					(3 (= register moss4))
					(4 (= register moss5))
				)
				(register
					signal: (| (register signal?) $0001)
					setCycle: Osc
				)
				(= cycles (Random 20 60))
			)
			(1
				(register setCycle: End self)
			)
			(2
				(register signal: (& (register signal?) $fffe))
				(= cycles 12)
			)
			(3
				(register setCel: 0)
				(= cycles (Random 60 180))
			)
			(4
				(if (> (++ local6) 6)
					(= local6 0)
					(self dispose:)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance castOpenDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(door
					signal: (| (door signal?) $0001)
					setCycle: End self
				)
			)
			(1
				(messager say: 11 80 0 0 self)
			)
			(2
				(door signal: (& (door signal?) $fffe))
				(ego
					setMotion: PolyPath (cryptDoor approachX?) (cryptDoor approachY?) self
				)
			)
			(3
				(sToCrypt start: 3)
				(curRoom setScript: sToCrypt)
			)
		)
	)
)

(instance sToCrypt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(messager say: 39 155 34 0 self)
				else
					(messager say: 11 42 6 0 self)
				)
			)
			(1
				(door
					signal: (| (door signal?) $0001)
					setCycle: End self
				)
			)
			(2
				(door signal: (& (door signal?) $fffe))
				(= ticks 30)
			)
			(3
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(door setPri: 0)
				(ego
					setSpeed: defaultCycles
					setScale:
					setLoop: 7 1
					setPri: 2
					setMotion: MoveTo 87 124 self
				)
			)
			(4
				(ego setSpeed: gTheObj_2CycleSpeed)
				(door
					signal: (| (door signal?) $0001)
					setCycle: Beg self
				)
			)
			(5 (curRoom newRoom: 510))
		)
	)
)

(instance castOpenGrave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(igor show:)
				(headStone
					signal: (| (headStone signal?) $0001)
					setCycle: Beg self
				)
			)
			(1
				(headStone signal: (& (headStone signal?) $fffe))
				(curRoom setScript: sIgorLeaves)
			)
		)
	)
)

(instance sLiftStone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 4
					setLoop: 1 1
					setCel: 0
					setSpeed: defaultCycles
					setCycle: End self
				)
			)
			(1 (= ticks 60))
			(2
				(if (> (ego trySkill: 0 300) 0)
					(= register 1)
					(headStone
						signal: (| (headStone signal?) $0001)
						setCycle: Beg self
					)
					(igor show:)
					(ego setCycle: Beg self)
				else
					(messager say: 37 6 35 0 self)
				)
			)
			(3
				(if (not register) (ego setCycle: Beg self))
			)
			(4
				(headStone signal: (& (headStone signal?) $fffe))
				(ego setSpeed: gTheObj_2CycleSpeed normalize: 1)
				(if register
					(curRoom setScript: sIgorLeaves)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance sTryTree2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					setPri: (ego priority?)
					setScale:
					setMotion: MoveTo 34 166 self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(ego view: 7 setLoop: 3 1 setStep: 1 4 setCycle: Fwd)
				(if (> (ego trySkill: 11 200) 0)
					(curRoom setScript: sClimbTree2)
				else
					(ego setSpeed: 1 setCycle: ForwardCounter 5 self)
				)
			)
			(3
				(messager say: 37 6 23 0 self)
			)
			(4
				(= start 0)
				(ego
					normalize: 3
					setPri: 180
					setScaler: Scaler 110 65 129 87
					setMotion: MoveTo (bigTrunk approachX?) (bigTrunk approachY?) self
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbTree2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 30 4 0 0 self)
			)
			(1
				(ego
					setSpeed: defaultCycles
					setMotion: MoveTo (ego x?) 69 self
				)
			)
			(2
				(ego setCycle: 0 setSpeed: gTheObj_2CycleSpeed)
				(ego
					setLoop: (ego loop?) 1
					setCycle: Rev
					setMotion: MoveTo 34 166 self
				)
			)
			(3
				(sTryTree2 start: 4)
				(curRoom setScript: sTryTree2)
			)
		)
	)
)

(instance sTryTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					setPri: (ego priority?)
					setScale:
					setMotion: MoveTo 34 166 self
				)
			)
			(1 (ego setHeading: 0 self))
			(2
				(ego view: 7 setLoop: 3 1 setStep: 1 4 setCycle: Fwd)
				(if (> (ego trySkill: 11 200) 0)
					(curRoom setScript: sClimbTree)
				else
					(ego setSpeed: 1 setCycle: ForwardCounter 5 self)
				)
			)
			(3
				(messager say: 37 6 23 0 self)
			)
			(4
				(= start 0)
				(ego
					normalize: 3
					setPri: 180
					setScaler: Scaler 110 65 129 87
					setMotion: MoveTo (bigTrunk approachX?) (bigTrunk approachY?) self
				)
			)
			(5
				(if (cast contains: rope1)
					(ego setPri: -1 setMotion: PolyPath 179 141 self)
				else
					(ego setSpeed: gTheObj_2CycleSpeed setPri: -1)
					(= oldCurs 0)
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(6
				(ego
					view: 4
					setLoop: 1 1
					setCel: 0
					setPri: (- (ego priority?) 3)
					setCycle: End self
				)
			)
			(7
				(rope2
					signal: (| (rope2 signal?) $0001)
					setCycle: End self
				)
			)
			(8
				(rope2 signal: (& (rope2 signal?) $fffe))
				(ego setCycle: Beg self)
			)
			(9
				(ego normalize: 7 setMotion: PolyPath 137 174 self)
			)
			(10
				(ego setMotion: PolyPath 94 163 self)
			)
			(11 (ego setHeading: 45 self))
			(12
				(ego view: 7 setLoop: 0 1 setCel: 0 setCycle: Fwd)
				(rope1
					signal: (| (rope1 signal?) $0001)
					setCycle: End
					setStep: 1 1
					setMotion: MoveTo (rope1 x?) (+ (rope1 y?) 16)
				)
				(rope2
					signal: (| (rope2 signal?) $0001)
					setStep: 1 1
					setMotion: MoveTo (+ (rope2 x?) 50) (- (rope1 y?) 50)
				)
				(igor show:)
				(headStone
					signal: (| (headStone signal?) $0001)
					setCycle: Beg self
				)
			)
			(13
				(headStone signal: (& (headStone signal?) $fffe))
				(ego setCycle: End self)
			)
			(14
				(rope2 setMotion: 0)
				(= ticks 60)
			)
			(15
				(rope1 dispose:)
				(rope2 dispose:)
				(ego setCycle: Beg self)
			)
			(16
				(ego
					normalize: 6
					setSpeed: gTheObj_2CycleSpeed
					setPri: -1
					setScaler: Scaler 110 65 129 87
				)
				(curRoom setScript: sIgorLeaves)
			)
		)
	)
)

(instance sClimbDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setLoop: (ego loop?) 1
					setCycle: Rev
					setMotion: MoveTo 34 166 self
				)
			)
			(1
				(sTryTree start: 4)
				(curRoom setScript: sTryTree)
			)
		)
	)
)

(instance sClimbTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setSpeed: defaultCycles
					setMotion: MoveTo (ego x?) 69 self
				)
			)
			(1
				(ego setCycle: 0 setSpeed: gTheObj_2CycleSpeed)
				(if local7
					(= local7 0)
					(bigBranch doVerb: 33)
				else
					(user canInput: 1 canControl: 1)
					(theIconBar enable: 1 0 7)
					(= oldCurs 1)
					(if (not (theIconBar curInvIcon?))
						(theIconBar disable: 6)
					)
					(theGame setCursor: (theIconBar getCursor:) 1)
					(self dispose:)
				)
			)
		)
	)
)

(instance sIgorLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 236)
				(if (cast contains: igor) (igor show:))
				(if (Btst 38)
					(theGame handsOn:)
					(self dispose:)
				else
					(Bset 48)
					(heroTeller dispose:)
					(ego actions: 0)
					(= ticks 120)
				)
			)
			(1
				(ego solvePuzzle: 441 15)
				(igor
					signal: (| (igor signal?) $0001)
					setCycle: End self
				)
			)
			(2
				(ego
					setHeading: (GetAngle (ego x?) (ego y?) (igor x?) (igor y?))
				)
				(igor setLoop: 4 1 setCel: 0 setCycle: End self)
			)
			(3
				(igor signal: (& (igor signal?) $fffe))
				(= ticks 60)
			)
			(4
				(messager say: 34 6 19 0 self)
			)
			(5 (= ticks 60))
			(6
				(igor
					signal: (| (igor signal?) $0001)
					setLoop: 5 1
					setCycle: Fwd
					setMotion: MoveTo (igor x?) 240 self
				)
			)
			(7
				(igor dispose:)
				(= oldCurs 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLiftLid of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(coffin2
					setLoop: 3 1
					setCel: 0
					x: 95
					y: 137
					signal: (| (coffin2 signal?) $0001)
					setCycle: End self
				)
			)
			(1
				(coffin2 signal: (& (coffin2 signal?) $fffe))
				(= seconds (Random 3 5))
			)
			(2
				(coffin2
					signal: (| (coffin2 signal?) $0001)
					setCycle: Beg self
				)
			)
			(3
				(coffin2
					setLoop: 2 1
					x: 55
					y: 125
					signal: (& (coffin2 signal?) $fffe)
				)
				(self dispose:)
			)
		)
	)
)

(instance sLigeiaOPEN of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ligeiaDoor
					signal: (| (ligeiaDoor signal?) $0001)
					setCycle: End self
				)
				(if (cast contains: shadow3) (shadow3 z: 1000))
			)
			(1
				(ligeiaDoor signal: (& (ligeiaDoor signal?) $fffe))
				(ego setMotion: PolyPath 249 143 self)
			)
			(2
				(if (and (not Night) (not (Btst 246)))
					(ligeia
						init:
						signal: (| (ligeia signal?) $0001)
						setPri: 138
						setCycle: End self
					)
				else
					(= ticks 1)
				)
			)
			(3
				(if (and Night (not (Btst 246)))
					(Bset 398)
					(client setScript: sWraithRise)
				else
					(client setScript: sLigeia 0 1)
				)
			)
		)
	)
)

(instance sLigeia of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(if register
					(ego cycleSpeed: defaultCycles)
					(= ticks 1)
				else
					(ego
						view: 31
						setLoop: 0 1
						setCel: 0
						cycleSpeed: defaultCycles
						setCycle: End self
					)
				)
			)
			(1
				(if register
					(self changeState: 4)
				else
					(ego setCycle: Beg)
					(if (cast contains: shadow3) (shadow3 z: 1000))
					(ligeiaDoor
						signal: (| (ligeiaDoor signal?) $0001)
						setCycle: End self
					)
				)
			)
			(2
				(if (and Night (not (Btst 246)))
					(Bset 398)
					(ego cycleSpeed: gTheObj_2CycleSpeed)
					(client setScript: sWraithRise)
				else
					(ligeiaDoor signal: (& (ligeiaDoor signal?) $fffe))
					(ligeia
						init:
						setPri: 138
						signal: (| (ligeia signal?) $0001)
						setCycle: End self
					)
				)
			)
			(3 (= ticks 60))
			(4
				(if (and Night (Btst 246))
					(if (not local8)
						(= local8 1)
						(messager say: 21 4 9 0 self)
					)
				else
					(messager say: 21 4 8 0 self)
				)
			)
			(5
				(if register (ego view: 31 setLoop: 0 1 setCel: 0))
				(ego setCycle: End self)
				(ligeia
					signal: (| (ligeia signal?) $0001)
					setCycle: Beg
				)
			)
			(6
				(ligeia dispose:)
				(ego setCycle: Beg)
				(ligeiaDoor
					signal: (| (ligeiaDoor signal?) $0001)
					setCycle: Beg self
				)
			)
			(7
				(if (cast contains: shadow3) (shadow3 z: 0))
				(ligeiaDoor signal: (& (ligeiaDoor signal?) $fffe))
				(ego normalize: 6 setSpeed: gTheObj_2CycleSpeed)
				(= register 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWraithRise of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (Btst 398)
					(wraith posn: 260 120 setPri: 140)
					(= monsterHealth 200)
					(messager say: 21 4 10 0 self)
				else
					(= ticks 1)
					(= monsterHealth 300)
				)
			)
			(1
				(= global365 850)
				(soundFX number: 854 play:)
				(wraith init: setCycle: End self)
			)
			(2 (curRoom newRoom: 810))
		)
	)
)

(instance sFromCombat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local1 238)
				(= local2 169)
				(ego x: 238 y: 169 normalize: 6)
				(if (Btst 398)
					(ligeiaDoor
						signal: (| (ligeiaDoor signal?) $0001)
						setCel: (ligeiaDoor lastCel:)
					)
					(ligeiaDoor signal: (& (ligeiaDoor signal?) $fffe))
					(wraith posn: 260 120 setPri: 140)
				)
				(wraith setCel: 13 init:)
				(if (== battleResult 2)
					(wraith setCycle: Beg self)
					(soundFX number: 854 play:)
				else
					(wraith setLoop: 3 setCycle: Fwd)
					(= cycles 3)
				)
			)
			(1
				(switch battleResult
					(1
						(if (Btst 398) (EgoDead 17 50) else (EgoDead 45 500))
					)
					(2
						(if (Btst 398)
							(Bset 246)
							(Bclr 398)
							(ligeiaDoor
								signal: (| (ligeiaDoor signal?) $0001)
								setCycle: Beg
							)
						else
							(Bset 228)
							(ego solvePuzzle: 530 6 8 addHonor: 60)
							(messager say: 37 6 13)
						)
						(wraith dispose:)
						(theGame handsOn:)
						(self dispose:)
					)
					(3
						(curRoom setScript: sEgosGone)
					)
				)
			)
		)
	)
)

(instance igorTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 73))
	)
)

(instance heroTeller of Teller
	(properties)
)

(instance doorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(Bclr 51)
		(cond 
			((== iconValue 1) (Bset 205) (super sayMessage: &rest))
			((== iconValue 34) (self clean:) (curRoom setScript: sToCrypt 0 1))
			((== iconValue 2)
				(if (> (ego trySkill: 9 250) 0)
					(self clean:)
					(curRoom setScript: sToCrypt)
				else
					(messager say: 11 42 7 0 self)
				)
			)
			((== iconValue 3) (Bset 205) (super sayMessage: &rest))
			(else (super sayMessage: &rest))
		)
	)
	
	(method (showCases)
		(super showCases: 2 (ego has: 24) 34 (ego has: 60))
	)
)

(instance igor of Actor
	(properties
		noun 34
		x 140
		y 146
		priority 9
		view 505
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(switch local0
			(2
				(headStone init:)
				(igorTeller init: igor 500 39 123 34)
				(heroTeller init: ego 500 39 128 34)
				(self setScript: sChip)
				(self cue:)
			)
			(4
				(headStone
					view: 502
					loop: 2
					cel: 6
					init:
					approachVerbs: 4
				)
				(if (Btst 38)
					(self loop: 6 posn: 147 145)
					(if (Btst 236) (headStone cel: 0))
				else
					(self loop: 3 posn: 113 179)
					(heroTeller init: ego 500 39 128 38)
				)
				(self hide:)
			)
			(else  0)
		)
	)
	
	(method (doVerb theVerb)
		(if (Btst 38)
			(switch theVerb
				(1 (messager say: 34 1 37))
				(4
					(if (ego has: 60)
						(messager say: 34 4 12)
					else
						(ego get: 60 1)
						(messager say: 34 4 11)
					)
				)
				(else  (super doVerb: theVerb))
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(if loop
			(self setLoop: 0 1 setCel: 0 setCycle: End self)
		else
			(self
				setLoop: 1 1
				setCycle: OccCyc self 1 5 15 (Random 6 36) self
			)
		)
	)
)

(instance headStone of Prop
	(properties
		noun 41
		approachX 171
		approachY 148
		x 166
		y 157
		z 13
		priority 138
		fixPriority 1
		view 502
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== local0 4)
			(switch theVerb
				(4
					(if cel
						(curRoom setScript: sLiftStone)
					else
						(super doVerb: theVerb)
					)
				)
				(80
					(= projX ((User curEvent?) x?))
					(= projY ((User curEvent?) y?))
					(curRoom setScript: (ScriptID 13) 0 self)
				)
				(-80
					(if cel
						(curRoom setScript: castOpenGrave)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance lightning of Prop
	(properties
		x 149
		y 22
		view 500
		loop 1
		signal $6001
	)
)

(instance lightning2 of Prop
	(properties
		x 149
		y 22
		view 500
		loop 2
		signal $6001
	)
)

(instance shadow of Prop
	(properties
		x 235
		y 41
		view 500
		loop 3
		signal $4001
		cycleSpeed 12
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(ligeiaTomb doVerb: theVerb)
	)
)

(instance shadow2 of Prop
	(properties
		x 238
		y 50
		view 500
		loop 4
		signal $4001
		cycleSpeed 12
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(ligeiaTomb doVerb: theVerb)
	)
)

(instance shadow3 of Prop
	(properties
		x 244
		y 63
		view 500
		loop 5
		signal $4001
		cycleSpeed 12
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(ligeiaTomb doVerb: theVerb)
	)
)

(instance moss of Prop
	(properties
		noun 40
		x 81
		y 18
		priority 11
		view 500
		loop 6
		signal $4001
		cycleSpeed 16
		detailLevel 3
	)
)

(instance moss2 of Prop
	(properties
		x 15
		y 18
		view 500
		loop 7
		cel 7
		signal $4001
		cycleSpeed 16
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(moss doVerb: theVerb)
	)
)

(instance moss3 of Prop
	(properties
		x 117
		y 12
		view 500
		loop 8
		signal $4001
		cycleSpeed 16
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(moss doVerb: theVerb)
	)
)

(instance moss4 of Prop
	(properties
		x 245
		y 9
		view 501
		loop 6
		signal $4001
		cycleSpeed 16
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(moss doVerb: theVerb)
	)
)

(instance moss5 of Prop
	(properties
		x 173
		view 501
		loop 7
		signal $4001
		cycleSpeed 16
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(moss doVerb: theVerb)
	)
)

(instance door of Prop
	(properties
		noun 11
		approachX 111
		approachY 100
		x 111
		y 100
		priority 11
		view 500
		loop 9
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(doorTeller doVerb: theVerb)
		else
			(cryptDoor doVerb: theVerb)
		)
	)
)

(instance graveStone of Prop
	(properties
		x 211
		y 85
		view 501
		loop 4
		signal $4000
		cycleSpeed 18
		detailLevel 1
	)
	
	(method (doVerb theVerb)
		(grave1 doVerb: theVerb)
	)
)

(instance ligeiaDoor of Prop
	(properties
		approachX 249
		approachY 143
		x 252
		y 87
		view 501
		signal $4000
	)
	
	(method (doVerb theVerb)
		(ligeiaDoorF doVerb: theVerb)
	)
)

(instance ligeia of Prop
	(properties
		x 263
		y 121
		view 501
		loop 8
		signal $4000
	)
)

(instance coffin of Prop
	(properties
		x 71
		y 116
		view 501
		loop 1
		signal $4001
		cycleSpeed 12
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 138)
	)
	
	(method (doVerb theVerb)
		(coffins doVerb: theVerb)
	)
)

(instance coffin2 of Prop
	(properties
		x 55
		y 125
		view 501
		loop 2
		signal $4000
		cycleSpeed 12
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 138)
	)
	
	(method (doVerb theVerb)
		(coffins doVerb: theVerb)
	)
)

(instance rope1 of Actor
	(properties
		x 100
		y -10
		view 502
		signal $6000
	)
)

(instance rope2 of Actor
	(properties
		x 127
		y -5
		view 502
		loop 1
		signal $6000
	)
)

(instance rusalkaGrave of Feature
	(properties
		noun 32
		nsLeft 260
		nsTop 120
		nsRight 291
		nsBottom 158
		sightAngle 180
		x 275
		y 139
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (== heroType 3) (Bset 227))
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance loverGrave of Feature
	(properties
		noun 33
		nsLeft 292
		nsTop 126
		nsRight 319
		nsBottom 167
		sightAngle 180
		approachX 284
		approachY 173
		x 305
		y 146
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(58
				(if
					(and
						Night
						(not (Btst 228))
						((inventory at: 39) state?)
					)
					(curRoom setScript: sWraithRise)
				else
					(super doVerb: theVerb)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cryptDoor of Feature
	(properties
		noun 11
		nsLeft 99
		nsTop 48
		nsRight 120
		nsBottom 97
		sightAngle 180
		approachX 111
		approachY 100
		x 109
		y 72
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4 (doorTeller doVerb: theVerb))
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: castOpenDoor)
			)
			(29
				(if (ego has: 60)
					(curRoom setScript: sToCrypt 0 1)
				else
					(messager say: 11 29 43)
				)
			)
			(42
				(if (> (ego trySkill: 9 250) 0)
					(curRoom setScript: sToCrypt)
				else
					(messager say: 11 42 7)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance crypt of Feature
	(properties
		noun 20
		nsLeft 54
		nsTop 24
		nsRight 147
		nsBottom 108
		sightAngle 180
		x 100
		y 66
	)
)

(instance grave1 of Feature
	(properties
		noun 1
		nsLeft 196
		nsTop 85
		nsRight 215
		nsBottom 105
		sightAngle 180
		x 205
		y 95
	)
)

(instance grave2 of Feature
	(properties
		noun 2
		nsLeft 215
		nsTop 83
		nsRight 232
		nsBottom 103
		sightAngle 180
		x 223
		y 93
	)
)

(instance grave3 of Feature
	(properties
		noun 3
		nsLeft 157
		nsTop 71
		nsRight 167
		nsBottom 84
		sightAngle 180
		x 162
		y 77
	)
)

(instance grave4 of Feature
	(properties
		noun 4
		nsLeft 174
		nsTop 66
		nsRight 183
		nsBottom 76
		sightAngle 180
		x 178
		y 71
	)
)

(instance grave5 of Feature
	(properties
		noun 5
		nsLeft 225
		nsTop 67
		nsRight 235
		nsBottom 78
		sightAngle 180
		x 230
		y 72
	)
)

(instance urn1 of Feature
	(properties
		noun 24
		nsLeft 236
		nsTop 88
		nsRight 253
		nsBottom 107
		sightAngle 180
		x 244
		y 97
	)
)

(instance urn2 of Feature
	(properties
		noun 25
		nsLeft 278
		nsTop 91
		nsRight 294
		nsBottom 112
		sightAngle 180
		x 286
		y 101
	)
)

(instance ligeiaDoorF of Feature
	(properties
		noun 21
		nsLeft 255
		nsTop 59
		nsRight 283
		nsBottom 122
		sightAngle 180
		approachX 249
		approachY 143
		x 269
		y 90
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not local8)
					(curRoom setScript: sLigeia)
				else
					(super doVerb: theVerb)
				)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sLigeiaOPEN 0 1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ligeiaTomb of Feature
	(properties
		noun 22
		nsLeft 235
		nsTop 32
		nsRight 319
		nsBottom 131
		sightAngle 180
		x 277
		y 81
	)
)

(instance ligeiaTop of Feature
	(properties
		noun 23
		nsLeft 257
		nsTop 9
		nsRight 280
		nsBottom 32
		sightAngle 180
		x 268
		y 20
	)
)

(instance thinTree of Feature
	(properties
		noun 27
		nsLeft 299
		nsRight 311
		nsBottom 122
		sightAngle 180
		x 305
		y 82
	)
)

(instance thinBranch of Feature
	(properties
		noun 28
		nsLeft 216
		nsRight 299
		nsBottom 12
		sightAngle 180
		x 257
		y 6
	)
)

(instance bigBranch of Feature
	(properties
		noun 29
		nsLeft 37
		nsRight 135
		nsBottom 19
		sightAngle 180
		x 86
		y 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(cond 
					((== (ego view?) 7)
						(rope1 init: setPri: 148)
						(rope2 init: setPri: 116)
						(curRoom setScript: sClimbDown)
					)
					((or (!= local0 4) (not (headStone cel?))) (super doVerb: theVerb))
					((not [egoStats 11]) (messager say: 30 4 42))
					(else (= local7 1) (curRoom setScript: sTryTree))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bigTrunk of Feature
	(properties
		noun 30
		nsTop 11
		nsRight 51
		nsBottom 169
		sightAngle 180
		approachX 98
		approachY 181
		x 25
		y 90
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((or (!= local0 4) (not (headStone cel?))) (curRoom setScript: sTryTree2))
					((not [egoStats 11]) (messager say: 30 4 42))
					(else (curRoom setScript: sTryTree))
				)
			)
			(33
				(cond 
					((or (!= local0 4) (not (headStone cel?))) (super doVerb: theVerb))
					((not [egoStats 11]) (messager say: 30 4 42))
					(else (= local7 1) (curRoom setScript: sTryTree))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance coffins of Feature
	(properties
		noun 31
		nsLeft 51
		nsTop 110
		nsRight 104
		nsBottom 158
		sightAngle 180
		x 77
		y 134
	)
)

(instance dirtPile of Feature
	(properties
		noun 17
		nsLeft 134
		nsTop 170
		nsRight 169
		nsBottom 182
		sightAngle 180
		x 151
		y 176
	)
)

(instance openGrave of Feature
	(properties
		noun 16
		nsLeft 103
		nsTop 138
		nsRight 166
		nsBottom 174
		sightAngle 180
		x 134
		y 156
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init: 97 167 145 138 158 138 165 142 128 176
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 1 4)
			(cond 
				(
					(and
						(cast contains: igor)
						(igor isNotHidden:)
						(Btst 38)
					)
					(igor doVerb: theVerb)
				)
				(
					(and
						(cast contains: igor)
						(not (igor isNotHidden:))
						(== theVerb 1)
					)
					(messager say: 16 1 44)
				)
				(else (super doVerb: theVerb))
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance wraith of Actor
	(properties
		x 275
		y 139
		view 850
		loop 1
		signal $4001
	)
)

(instance soundFX of Sound
	(properties)
)
