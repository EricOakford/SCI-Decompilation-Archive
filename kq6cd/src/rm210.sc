;;; Sierra Script 1.0 - (do not remove this comment)
(script# 210)
(include sci.sh)
(use Main)
(use rgCrown)
(use walkEgoInScr)
(use KQ6Print)
(use NewRoomCue)
(use CartoonScript)
(use Kq6Procs)
(use Print)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use DPath)
(use Path)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm210 0
)

(local
	local0
	[local1 8] = [295 71 283 65 277 64 -32768 -32768]
	[local9 20] = [292 71 280 65 268 61 262 61 258 56 270 55 285 55 290 53 298 47 -32768 -32768]
	local29
	local30
	local31
	local32
	local33 =  4660
	local34
	local35 =  -1
)
(procedure (localproc_0727 param1)
	(if (singSing script?)
		(= local29 param1)
		(if (OneOf (singSingScr state?) 3 9)
			(singSing setScript: 0)
		)
	else
		(param1 cue:)
	)
)

(instance rm210 of KQ6Room
	(properties
		noun 3
		picture 210
		horizon 0
		south 200
	)
	
	(method (init param1 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(asm
			pushi    #addObstacle
			pushi    5
			pushi    #type
			pushi    1
			pushi    2
			pushi    112
			pushi    14
			pushi    39
			pushi    65526
			pushi    263
			pushi    65526
			pushi    250
			pushi    78
			pushi    232
			pushi    104
			pushi    139
			dup     
			pushi    76
			pushi    113
			pushi    39
			pushi    82
			pushi    119
			pushi    0
			pushi    #new
			pushi    0
			class    Polygon
			send     4
			send     42
			push    
			pushi    #type
			pushi    1
			pushi    0
			pushi    112
			pushi    10
			pushi    0
			pushi    189
			pushi    0
			pushi    120
			pushi    65
			pushi    120
			pushi    117
			pushi    148
			pushi    68
			pushi    189
			pushi    119
			pushi    0
			pushi    #new
			pushi    0
			class    Polygon
			send     4
			send     34
			push    
			pushi    #type
			pushi    1
			pushi    2
			pushi    112
			pushi    8
			pushi    29
			pushi    138
			pushi    94
			pushi    138
			pushi    94
			pushi    156
			pushi    31
			pushi    162
			pushi    119
			pushi    0
			pushi    #new
			pushi    0
			class    Polygon
			send     4
			send     30
			push    
			pushi    #type
			pushi    1
			pushi    2
			pushi    112
			pushi    10
			pushi    0
			pushi    65526
			pushi    27
			pushi    65526
			pushi    27
			pushi    84
			pushi    64
			pushi    119
			pushi    0
			pushi    119
			dup     
			pushi    0
			pushi    #new
			pushi    0
			class    Polygon
			send     4
			send     34
			push    
			pushi    #type
			pushi    1
			pushi    2
			pushi    112
			pushi    12
			pushi    115
			pushi    189
			pushi    151
			pushi    145
			pushi    244
			pushi    108
			pushi    266
			pushi    79
			pushi    319
			pushi    79
			pushi    319
			pushi    189
			pushi    119
			pushi    0
			pushi    #new
			pushi    0
			class    Polygon
			send     4
			send     38
			push    
			lag      curRoom
			send     14
			pushi    #init
			pushi    0
			&rest    param1
			super    KQ6Room,  4
			pushi    #init
			pushi    0
			pushi    307
			pushi    5
			class    Scaler
			push    
			pushi    100
			pushi    84
			pushi    134
			pushi    81
			lag      ego
			send     18
			pushi    #add
			pushi    3
			lofsa    genericFeatures
			push    
			lofsa    tree
			push    
			lofsa    holeInTree
			push    
			pushi    121
			pushi    1
			pushi    112
			lag      features
			send     16
			pushi    #onMeCheck
			pushi    1
			pushi    2
			pushi    299
			pushi    2
			pushi    1
			pushi    2
			pushi    112
			pushi    0
			pushi    2
			pushi    10
			pushi    4
			callk    ScriptID,  4
			send     18
			pushi    4
			pushi    132
			pushi    214
			pushi    215
			pushi    216
			calle    LoadMany,  8
			lsg      prevRoomNum
			dup     
			ldi      220
			eq?     
			bnt      code_0273
			pushi    #setScript
			pushi    3
			lofsa    enterFromCastleScr
			push    
			pushi    0
			lsg      ego
			lag      curRoom
			send     10
			jmp      code_02e8
code_0273:
			dup     
			ldi      240
			eq?     
			bnt      code_0288
			pushi    #setScript
			pushi    1
			lofsa    enterFromVillageScr
			push    
			lag      curRoom
			send     6
			jmp      code_02e8
code_0288:
			dup     
			ldi      140
			eq?     
			bnt      code_02c1
			pushi    #fade
			pushi    4
			pushi    0
			pushi    30
			pushi    8
			pushi    1
			lag      soundFx
			send     12
			pushi    #posn
			pushi    2
			pushi    #approachX
			pushi    0
			lofsa    singSing
			send     4
			push    
			pushi    #approachY
			pushi    0
			lofsa    singSing
			send     4
			push    
			pushi    3
			pushi    1
			pushi    6
			lag      ego
			send     14
			jmp      code_02e8
code_02c1:
			lsg      prevRoomNum
			ldi      200
			ne?     
			bnt      code_02d7
			pushi    #setIt
			pushi    1
			pushi    2
			pushi    2
			pushi    10
			pushi    0
			callk    ScriptID,  4
			send     6
code_02d7:
			ldi      1
			sal      local34
			pushi    3
			pushi    91
			pushi    185
			pushi    30
			calle    proc12_1,  6
code_02e8:
			toss    
			pushi    #owner
			pushi    0
			pushi    #at
			pushi    1
			pushi    39
			lag      inventory
			send     6
			send     4
			sat      temp2
			pushi    #owner
			pushi    0
			pushi    #at
			pushi    1
			pushi    38
			lag      inventory
			send     6
			send     4
			sat      temp3
			pushi    1
			pushi    94
			calle    Btst,  2
			bnt      code_0322
			lst      temp3
			ldi      140
			ne?     
			bnt      code_0322
			lag      curRoomNum
			sat      temp3
code_0322:
			pushi    #owner
			pushi    0
			pushi    #at
			pushi    1
			pushi    47
			lag      inventory
			send     6
			send     4
			sat      temp1
			pushi    #owner
			pushi    0
			pushi    #at
			pushi    1
			pushi    35
			lag      inventory
			send     6
			send     4
			push    
			ldi      65535
			eq?     
			bnt      code_0350
			pushi    #init
			pushi    0
			lofsa    theRibbon
			send     4
code_0350:
			pushi    #owner
			pushi    0
			pushi    #at
			pushi    1
			pushi    32
			lag      inventory
			send     6
			send     4
			push    
			ldi      65535
			eq?     
			bnt      code_036d
			pushi    #init
			pushi    0
			lofsa    letter
			send     4
code_036d:
			lst      temp2
			ldi      140
			eq?     
			bnt      code_0386
			pushi    #owner
			pushi    1
			lsg      curRoomNum
			pushi    #at
			pushi    1
			pushi    39
			lag      inventory
			send     6
			send     6
code_0386:
			lst      temp3
			ldi      140
			eq?     
			bnt      code_039f
			pushi    #owner
			pushi    1
			lsg      curRoomNum
			pushi    #at
			pushi    1
			pushi    38
			lag      inventory
			send     6
			send     6
code_039f:
			lst      temp1
			ldi      140
			eq?     
			bnt      code_03b8
			pushi    #owner
			pushi    1
			lsg      curRoomNum
			pushi    #at
			pushi    1
			pushi    47
			lag      inventory
			send     6
			send     6
code_03b8:
			pushi    3
			lst      temp1
			lsg      curRoomNum
			pushi    140
			calle    OneOf,  6
			bnt      code_03e9
			pushi    3
			lst      temp2
			lsg      curRoomNum
			pushi    140
			calle    OneOf,  6
			bnt      code_03e9
			pushi    3
			lst      temp3
			lsg      curRoomNum
			pushi    140
			calle    OneOf,  6
code_03e9:
			not     
			sat      temp0
			pushi    #isSet
			pushi    1
			pushi    2
			pushi    2
			pushi    10
			pushi    0
			callk    ScriptID,  4
			send     6
			bnt      code_0412
			pushi    #clrIt
			pushi    1
			pushi    2
			pushi    2
			pushi    10
			pushi    0
			callk    ScriptID,  4
			send     6
			pushi    #init
			pushi    0
			lofsa    clown
			send     4
code_0412:
			lsg      prevRoomNum
			ldi      140
			eq?     
			bnt      code_043f
			lst      temp3
			ldi      140
			eq?     
			bnt      code_043f
			lst      temp1
			lag      curRoomNum
			eq?     
			bnt      code_042e
			lst      temp2
			lag      curRoomNum
			eq?     
code_042e:
			not     
			bnt      code_043f
			pushi    2
			lofsa    returnToBranch
			push    
			pushi    18
			calle    proc10_2,  4
			jmp      code_0554
code_043f:
			lsg      prevRoomNum
			ldi      140
			eq?     
			bnt      code_046b
			lst      temp3
			ldi      140
			eq?     
			bnt      code_046b
			lst      temp1
			lag      curRoomNum
			eq?     
			bnt      code_046b
			lst      temp2
			lag      curRoomNum
			eq?     
			bnt      code_046b
			pushi    2
			lofsa    notReturnScr
			push    
			pushi    20
			calle    proc10_2,  4
			jmp      code_0554
code_046b:
			lsg      prevRoomNum
			ldi      140
			eq?     
			bnt      code_0490
			lst      temp1
			ldi      140
			eq?     
			bnt      code_0490
			lst      temp2
			lag      curRoomNum
			ne?     
			bnt      code_0490
			pushi    2
			lofsa    returnToBranch
			push    
			pushi    12
			calle    proc10_2,  4
			jmp      code_0554
code_0490:
			lsg      prevRoomNum
			ldi      140
			eq?     
			bnt      code_04c1
			lst      temp2
			ldi      140
			eq?     
			bt       code_04af
			lst      temp1
			ldi      140
			eq?     
			bnt      code_04c1
			lst      temp2
			lag      curRoomNum
			eq?     
			bnt      code_04c1
code_04af:
			ldi      1
			sal      local32
			pushi    2
			lofsa    deliveryScr
			push    
			pushi    13
			calle    proc10_2,  4
			jmp      code_0554
code_04c1:
			pushi    1
			pushi    62
			calle    Btst,  2
			bnt      code_04da
			pushi    2
			lofsa    deliveryScr
			push    
			pushi    5
			calle    proc10_2,  4
			jmp      code_0554
code_04da:
			pushi    1
			pushi    63
			calle    Btst,  2
			bnt      code_04f3
			pushi    2
			lofsa    deliveryScr
			push    
			pushi    6
			calle    proc10_2,  4
			jmp      code_0554
code_04f3:
			pushi    #has
			pushi    1
			pushi    0
			lag      ego
			send     6
			bnt      code_0542
			lat      temp0
			bnt      code_0542
			pushi    1
			pushi    21
			calle    Btst,  2
			bnt      code_0518
			pushi    #init
			pushi    1
			pushi    0
			lofsa    singSing
			send     6
			jmp      code_0521
code_0518:
			pushi    #init
			pushi    1
			pushi    1
			lofsa    singSing
			send     6
code_0521:
			pushi    #number
			pushi    1
			pushi    210
			pushi    3
			pushi    1
			pushi    65535
			pushi    39
			pushi    0
			lag      theGlobalSound
			send     16
			pushi    #client
			pushi    1
			lofsa    musicScr
			push    
			pushi    109
			pushi    0
			lofsa    musicScr
			send     10
code_0542:
			pushi    1
			pushi    62
			calle    Bclr,  2
			pushi    1
			pushi    63
			calle    Bclr,  2
code_0554:
			pushi    3
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			lofsa    enterFromCastleScr
			push    
			lofsa    enterFromVillageScr
			push    
			calle    OneOf,  6
			bt       code_0571
			lal      local34
code_0571:
			sat      temp5
			pushi    #contains
			pushi    1
			lofsa    singSing
			push    
			lag      cast
			send     6
			bnt      code_05b2
			lat      temp5
			bnt      code_0595
			pushi    #script
			pushi    0
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			send     4
			not     
			bt       code_05a5
code_0595:
			lat      temp5
			not     
			bnt      code_05b2
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_05b2
code_05a5:
			pushi    #setScript
			pushi    1
			lofsa    singSingScr
			push    
			lofsa    singSing
			send     6
code_05b2:
			lsg      prevRoomNum
			ldi      220
			ne?     
			bnt      code_05cc
			pushi    #number
			pushi    1
			pushi    917
			pushi    3
			pushi    1
			pushi    65535
			pushi    39
			pushi    0
			lag      theMusic
			send     16
code_05cc:
			ret     
		)
	)
	
	(method (doit &tmp temp0)
		(= temp0 (ego onControl: 1))
		(cond 
			(script 0)
			((& temp0 $4000)
				(ego setSpeed: 6)
				(curRoom setScript: (Clone exitToCastleScr) 0 ego)
			)
			((& temp0 $2000) (curRoom setScript: exitToVillageScr))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theMusic fade:)
		(super dispose:)
		(DisposeScript 983)
		(DisposeScript 964)
	)
	
	(method (newRoom n)
		(if (and (== n 220) (cast contains: clown))
			((ScriptID 10 0) setIt: 2)
		)
		(if (cast contains: singSing)
			(theGlobalSound fade:)
			(if (theDoits contains: musicScr) (musicScr dispose:))
		)
		(super newRoom: n &rest)
	)
	
	(method (edgeToRoom param1)
		(return
			(cond 
				((== param1 3) (proc12_0 param1 30) (return 0))
				(argc (super edgeToRoom: param1 &rest))
				(else (super edgeToRoom:))
			)
		)
	)
	
	(method (scriptCheck param1 &tmp temp0)
		(switch param1
			(85
				(if (not (cast contains: singSing))
					(= temp0 1)
				else
					(self setScript: fluteScr)
					(= temp0 0)
				)
			)
			(93
				(if (not (cast contains: singSing))
					(= temp0 1)
				else
					(singSing doVerb: 37)
					(= temp0 0)
				)
			)
		)
		(return temp0)
	)
)

(instance exitPath of Path
	(properties)
	
	(method (at param1)
		(return
			(if (== client ego)
				(return [local1 param1])
			else
				(return [local9 param1])
			)
		)
	)
)

(instance musicScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(if register (theDoits delete: self))
		(= register 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not register) (soundFx stop:))
				(= register 1)
				(theDoits add: self)
				(while (not (!= (= register (Random 0 2)) local33))
				)
				(soundFx
					number:
					(switch register
						(0 214)
						(1 215)
						(2 216)
					)
					loop: 1
					play: self
				)
			)
			(1
				(= state -1)
				(= ticks (Random 60 300))
			)
		)
	)
)

(instance fluteScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_0727 self)
			)
			(1
				(if (cast contains: singSing)
					(theGlobalSound fade:)
					(musicScr dispose:)
				)
				(theMusic fade:)
				(self setScript: (ScriptID 85) self)
			)
			(2
				(messager say: 14 31 9 0 self)
			)
			(3 (= cycles 2))
			(4
				(theMusic play:)
				(if (cast contains: singSing)
					(theGlobalSound play:)
					(musicScr state: -1 client: musicScr cue:)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromVillageScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setSpeed: 6
					posn: 23 70
					setScale: Scaler 86 20 88 70
					setMotion: MoveTo 38 88 self
				)
			)
			(1
				(ego reset: 2 setScale: Scaler 100 84 134 81)
				(= cycles 1)
			)
			(2
				(if (not script) (= cycles 1))
			)
			(3
				(if (not (cast contains: clown)) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance exitToVillageScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setSpeed: 6
					setScale: Scaler 90 20 88 70
					setMotion: MoveTo 23 70 self
				)
			)
			(1 (= cycles 2))
			(2 (curRoom newRoom: 240))
		)
	)
)

(instance enterFromCastleScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setSpeed: 6
					posn: 277 64
					setScale: Scaler 30 5 75 15
					setMotion: DPath 283 65 295 71 300 79 self
				)
			)
			(1 (ego hide:) (= ticks 150))
			(2
				(ego
					show:
					setPri: 3
					posn: 271 130
					setLoop: 2
					scaleX: 108
					scaleY: 108
					setScale:
					setMotion: MoveTo 263 81 self
				)
			)
			(3
				(ego setPri: -1 setScale: Scaler 100 84 134 81)
				(= cycles 1)
			)
			(4
				(ego setMotion: MoveTo 260 85 self)
			)
			(5
				(ego reset: 2)
				(if register (register cue:))
				(if (not script) (= cycles 1))
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitToCastleScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== register ego) (theGame handsOff:))
				(register
					setLoop: 3
					setPri: 3
					setScale:
					setMotion: MoveTo 267 131 self
				)
			)
			(1
				(register hide:)
				(= ticks 150)
				(if (!= register ego) (theGame handsOn:))
			)
			(2
				(register
					show:
					setLoop: -1
					setPri: -1
					setScale: Scaler 30 5 75 15
					posn: 300 79
				)
				(register setMotion: (Clone exitPath) self)
			)
			(3 (= cycles 2))
			(4
				(if (== register ego)
					(curRoom newRoom: 220)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance clownScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(clown setMotion: PolyPath 138 144 self)
			)
			(1
				(clown
					view: 718
					setLoop: 5
					setMotion: PolyPath 247 104 self
				)
			)
			(2
				(clown
					view: 717
					setLoop: -1
					setMotion: PolyPath 261 85 self
				)
			)
			(3
				(self setScript: (Clone exitToCastleScr) self clown)
			)
			(4
				(clown hide:)
				(= seconds 10)
			)
			(5 (clown dispose:))
		)
	)
)

(instance returnToBranch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(singSing
					init:
					z: 0
					setPri: 1
					posn: 305 30
					setScale: Scaler 100 5 51 30
					setLoop: 0
					setCycle: Fwd
					setMotion: MoveTo 272 52 self
				)
			)
			(1
				(singSing
					setStep: 4 3
					setScale: 0
					setMotion: MoveTo 226 55 self
				)
			)
			(2
				(singSing setLoop: 3 cel: 0 setCycle: End self)
			)
			(3
				(singSing loop: 2 cel: 8 posn: 252 125 97 setPri: 14)
				(= cycles 2)
			)
			(4
				(messager say: 1 0 register 0 self)
			)
			(5
				(theGlobalSound number: 210 loop: -1 play:)
				(musicScr client: musicScr cue:)
				(Bclr 62)
				(Bclr 63)
				(singSing setScript: singSingScr)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance notReturnScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(messager say: 1 0 register 0 self)
			)
			(2
				(Bclr 62)
				(Bclr 63)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance deliveryScr of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 40])
		(switch (= state newState)
			(0
				(if (OneOf register 5 6)
					(singSing init: 0)
					(cond 
						((clown script?) (= seconds 6))
						((== client enterFromCastleScr) (client register: self))
						(else (= seconds 3))
					)
				else
					(= state 2)
					(singSing
						init:
						z: 0
						posn: 305 30
						setScale: Scaler 100 5 51 30
					)
					(= cycles 2)
				)
			)
			(1
				(singSing
					z: 0
					loop: 4
					cel: 0
					posn: 225 56
					setCycle: End self
				)
			)
			(2
				(singSing posn: 201 56)
				(self cue:)
			)
			(3
				(singSing
					view: 214
					setLoop: (if (Btst 62) 0 else 1)
					setCycle: Fwd
				)
				(if (OneOf register 5 6) (= state 4))
				(self cue:)
			)
			(4
				(singSing setMotion: MoveTo 272 52 self)
			)
			(5
				(singSing setScale: 0 setStep: 4 3)
				(if (Btst 62)
					(singSing setMotion: MoveTo 156 79 self)
				else
					(singSing setMotion: MoveTo 111 81 self)
				)
			)
			(6
				(if (Btst 62) (theRibbon init:) else (letter init:))
				(if
					(and
						(== ((inventory at: 39) owner?) curRoomNum)
						(== ((inventory at: 38) owner?) curRoomNum)
						(== ((inventory at: 47) owner?) curRoomNum)
					)
					(singSing
						view: 213
						setLoop: 0
						setMotion: MoveTo -10 79 self
					)
				else
					(++ state)
					(self cue:)
				)
			)
			(7
				(singSing dispose:)
				(= cycles 2)
				(= state 10)
			)
			(8
				(if (Btst 62)
					(singSing posn: 156 79)
				else
					(singSing posn: 111 81)
				)
				(singSing view: 213 setLoop: 5 cel: 0 setCycle: End self)
			)
			(9
				(if (Btst 62)
					(singSing posn: 185 69)
				else
					(singSing posn: 141 72)
				)
				(singSing
					setLoop: 1
					setCycle: Fwd
					setMotion: MoveTo 220 49 self
				)
			)
			(10
				(singSing
					setLoop: -1
					loop: 6
					cel: 0
					posn: 225 56
					setCycle: End self
				)
			)
			(11
				(if (cast contains: singSing)
					(theGlobalSound number: 210 loop: -1 play:)
					(musicScr state: -1 client: musicScr cue:)
				)
				(messager say: 1 0 register 0 self)
			)
			(12
				(if (cast contains: singSing)
					(singSing
						loop: 2
						cel: 8
						posn: 252 125 97
						setPri: 14
						setScript: singSingScr
					)
				)
				(if local32 (theGame handsOn:))
				(Bclr 62)
				(Bclr 63)
				(self dispose:)
			)
		)
	)
)

(instance giveItemToBirdScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 138 142 self)
			)
			(1 (ego setHeading: 90 self))
			(2 (localproc_0727 self))
			(3
				(ego
					view: 211
					posn: 147 143
					loop: 0
					cel: 0
					normal: 0
					setCycle: End self
				)
				(if local31 (genieSnake init:))
			)
			(4
				(if
					(and
						(cast contains: genieSnake)
						(<= ((genieSnake script?) state?) 0)
					)
					(-- state)
					(= ticks 15)
				else
					(self cue:)
				)
			)
			(5 (client cue:))
			(6
				(theGlobalSound fade:)
				(musicScr dispose:)
				(singSing z: 0 loop: 4 posn: 225 56 setCycle: End self)
			)
			(7
				(singSing
					setLoop: 0
					posn: 201 56
					setCycle: Fwd self
					setMotion: MoveTo 158 95 self
				)
			)
			(8
				(if (!= (theGlobalSound prevSignal?) -1)
					(theGlobalSound stop:)
				)
				(soundFx2 number: 212 play:)
				(if (Btst 10) (soundFx2 hold: 10))
				(singSing setLoop: 5 cel: 0 setCycle: End self)
			)
			(9 (client cue:))
			(10
				(ego setCycle: Beg self)
				(self cue:)
				(= register 0)
			)
			(11
				(singSing
					view: 214
					setLoop:
					(switch register
						(2 2)
						(1 3)
						(0 4)
					)
					posn: 187 85
					setCycle: Fwd
					setMotion: MoveTo 272 52 self
				)
			)
			(12
				(ego posn: (- (ego x?) 10) (- (ego y?) 3) reset: 4)
			)
			(13 (client cue:))
			(14
				(singSing
					setScale: Scaler 100 5 51 30
					setMotion: MoveTo 305 30 self
				)
			)
			(15
				(singSing dispose:)
				(if (cast contains: genieSnake)
					((genieSnake script?)
						caller: self
						ticks: 0
						state: 2
						cue:
					)
				else
					(self cue:)
				)
			)
			(16 (self dispose:))
		)
	)
)

(instance givePoemScr of CartoonScript
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(ego put: 47 140)
				(theGame handsOff:)
				(if
				(== (= temp0 ((inventory at: 39) owner?)) 210)
					(theGame givePoints: 1)
				)
				(= temp1 ((inventory at: 38) owner?))
				(= register
					(cond 
						(
							(and
								(!= temp0 curRoomNum)
								(!= temp1 curRoomNum)
								(!= ((inventory at: 47) owner?) curRoomNum)
							)
							(= local31 1)
							(if (Btst 10) 25 else 26)
						)
						(
						(and (!= temp0 curRoomNum) (== temp1 curRoomNum)) 32)
						((== temp0 curRoomNum) (Bset 63) 14)
					)
				)
				(self setScript: giveItemToBirdScr self 2)
			)
			(1
				(if (OneOf register 14 32 25)
					(KQ6Print posn: -1 80)
				else
					(KQ6Print posn: -1 100)
				)
				(KQ6Print
					font: userFont
					say: 0 4 32 register 1
					init: giveItemToBirdScr
				)
			)
			(2
				(if (OneOf register 25 26)
					(KQ6Print
						font: userFont
						posn: -1 100
						say: 0 4 32 register 2
						init: giveItemToBirdScr
					)
				else
					(giveItemToBirdScr cue:)
				)
			)
			(3
				(KQ6Print
					font: userFont
					posn: -1 100
					modeless: 1
					ticks: 0
					say: 0 4 32 register (if (OneOf register 25 26) 3 else 2)
					init:
				)
				(giveItemToBirdScr cue:)
			)
			(4
				(if modelessDialog
					(if (& msgType $0002)
						(self cue:)
					else
						(++ state)
						(KQ6Print caller: self)
					)
				else
					(++ state)
					(self cue:)
				)
			)
			(5
				(if (not (== (DoAudio 6) -1)) (-- state))
				(= ticks 10)
			)
			(6
				(if (Btst 10)
					(curRoom newRoom: 140)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance giveRingScr of CartoonScript
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(ego put: 39 140)
				(theGame handsOff:)
				(if (Btst 10)
					(theGame givePoints: 3)
				else
					(theGame givePoints: 1)
				)
				(Bset 62)
				(= temp0 ((inventory at: 47) owner?))
				(= temp1 ((inventory at: 38) owner?))
				(= register
					(cond 
						(
							(and
								(!= temp0 curRoomNum)
								(!= temp1 curRoomNum)
								(!= ((inventory at: 39) owner?) curRoomNum)
							)
							(= local31 1)
							(if (Btst 10) 25 else 26)
						)
						(
						(and (== temp0 curRoomNum) (!= temp1 curRoomNum)) 36)
						(
						(and (== temp0 curRoomNum) (== temp1 curRoomNum)) 38)
						(
						(and (== temp1 curRoomNum) (!= temp0 curRoomNum)) 34)
						(else
							(Prints
								{Problem! In else state of giveRoseScr conditional.}
							)
						)
					)
				)
				(self setScript: giveItemToBirdScr self 0)
			)
			(1
				(cond 
					((== register 25) (KQ6Print posn: -1 100))
					((OneOf register 36 34) (KQ6Print posn: -1 100))
					((== register 38) (KQ6Print posn: -1 100))
					(else (KQ6Print posn: -1 100))
				)
				(KQ6Print
					font: userFont
					say: 0 4 70 register 1
					init: giveItemToBirdScr
				)
			)
			(2
				(if (OneOf register 25 26)
					(KQ6Print
						font: userFont
						posn: -1 100
						say: 0 4 70 register 2
						init: giveItemToBirdScr
					)
				else
					(giveItemToBirdScr cue:)
				)
			)
			(3
				(if (OneOf register 36 38) (= register 34))
				(KQ6Print
					font: userFont
					posn: -1 100
					modeless: 1
					ticks: 0
					say: 0 4 70 register (if (OneOf register 25 26) 3 else 2)
					init:
				)
				(giveItemToBirdScr cue:)
			)
			(4
				(if modelessDialog
					(if (& msgType $0002)
						(self cue:)
					else
						(++ state)
						(KQ6Print caller: self)
					)
				else
					(++ state)
					(self cue:)
				)
			)
			(5
				(if (not (== (DoAudio 6) -1)) (-- state))
				(= ticks 10)
			)
			(6
				(if (Btst 10)
					(curRoom newRoom: 140)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance giveRoseScr of CartoonScript
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(ego put: 38 140)
				(theGame handsOff:)
				(= temp0 ((inventory at: 39) owner?))
				(= temp1 ((inventory at: 47) owner?))
				(if (== temp0 curRoomNum) (theGame givePoints: 1))
				(= register
					(cond 
						(
							(and
								(!= temp0 curRoomNum)
								(!= temp1 curRoomNum)
								(!= ((inventory at: 38) owner?) curRoomNum)
							)
							(= local31 1)
							(if (Btst 10) 25 else 26)
						)
						(
						(and (!= temp0 curRoomNum) (== temp1 curRoomNum)) 41)
						(
						(and (== temp0 curRoomNum) (!= temp1 curRoomNum)) 23)
						(
						(and (== temp0 curRoomNum) (== temp1 curRoomNum)) 24)
						(else
							(Prints
								{Problem! In else state of giveRoseScr conditional.}
							)
						)
					)
				)
				(self setScript: giveItemToBirdScr self 1)
			)
			(1
				(cond 
					((== register 25) (KQ6Print posn: -1 80))
					((OneOf register 23 41) (KQ6Print posn: -1 80))
					(else (KQ6Print posn: -1 100))
				)
				(KQ6Print
					font: userFont
					say: 0 4 71 register 1
					init: giveItemToBirdScr
				)
			)
			(2
				(if (OneOf register 25 26)
					(KQ6Print
						font: userFont
						posn: -1 100
						say: 0 4 71 register 2
						init: giveItemToBirdScr
					)
				else
					(giveItemToBirdScr cue:)
				)
			)
			(3
				(if (OneOf register 41) (= register 23))
				(KQ6Print
					font: userFont
					posn: -1 100
					modeless: 1
					ticks: 0
					say: 0 4 71 register (if (OneOf register 25 26) 3 else 2)
					init:
				)
				(giveItemToBirdScr cue:)
			)
			(4
				(if modelessDialog
					(if (& msgType $0002)
						(self cue:)
					else
						(++ state)
						(KQ6Print caller: self)
					)
				else
					(++ state)
					(self cue:)
				)
			)
			(5
				(if (not (== (DoAudio 6) -1)) (-- state))
				(= ticks 10)
			)
			(6
				(if (Btst 10)
					(curRoom newRoom: 140)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance windBirdHeader of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 120 141 self)
			)
			(1 (ego setHeading: 90 self))
			(2 (localproc_0727 self))
			(3
				(KQ6Print
					font: userFont
					posn: 10 10
					say: 0 4 37 register 1
					init: self
				)
			)
			(4 (= cycles 2))
			(5
				(theGlobalSound fade:)
				(musicScr dispose:)
				(ego
					setSpeed: 6
					view: 883
					loop: 1
					cel: 0
					posn: 120 142
					normal: 0
					setCycle: Fwd
					scaleX: 128
					scaleY: 128
					setScale:
				)
				(soundFx2 number: 930 loop: -1 play:)
				(= ticks 180)
			)
			(6
				(soundFx2 stop:)
				(self dispose:)
			)
		)
	)
)

(instance windUpBirdScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: windBirdHeader self 10)
			)
			(1
				(soundFx2 number: 931 loop: -1 play:)
				(ego loop: 7 cycleSpeed: 9 setCycle: Fwd)
				(= ticks 180)
			)
			(2
				(KQ6Print
					font: userFont
					posn: 10 10
					say: 0 4 37 10 2
					init: self
				)
			)
			(3
				(KQ6Print
					font: userFont
					posn: 10 10
					say: 0 4 37 10 3
					init: self
				)
			)
			(4 (= ticks 30))
			(5
				(soundFx2 fade:)
				(theGlobalSound play: 0 fade: 127 25 10 0)
				(musicScr state: -1 client: musicScr cue:)
				(ego reset: 0 posn: 126 139)
				(= cycles 2)
			)
			(6
				(singSing setScript: singSingScr)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance befriendSSScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame givePoints: 4)
				(self setScript: windBirdHeader self 11)
			)
			(1
				(ego loop: 3 cel: 0 posn: 119 142 setCycle: End self)
			)
			(2
				(windUpBird init:)
				(soundFx2 number: 931 loop: -1 play:)
				(ego posn: 120 141 reset: 0)
				(= cycles 2)
			)
			(3 (= ticks 180))
			(4
				(KQ6Print
					font: userFont
					posn: 10 10
					say: 0 4 37 11 2
					init: self
				)
			)
			(5
				(singSing
					posn: 252 125 97
					loop: 2
					cel: 0
					setCycle: End self
				)
			)
			(6 (= cycles 2))
			(7
				(KQ6Print
					font: userFont
					posn: 10 10
					say: 0 4 37 11 3
					init: self
				)
			)
			(8 (= ticks 48))
			(9
				(soundFx2 fade:)
				(windUpBird dispose:)
				(ego
					setSpeed: 6
					posn: 119 143
					view: 883
					loop: 3
					cel: 7
					normal: 0
					setCycle: CT 1 -1 self
				)
			)
			(10
				(theGlobalSound play: 0 fade: 127 25 10 0)
				(musicScr state: -1 client: musicScr cue:)
				(ego
					reset: 0
					posn: 120 141
					setScale: Scaler 100 84 134 81
				)
				(Bset 21)
				(singSing setScript: singSingScr)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance snakeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: End self))
			(1
				(client loop: 1 cel: 0 setCycle: End self)
			)
			(2
				(if (< (++ local35) 2)
					(soundFx number: 211 loop: 1 play:)
				)
				(if (and (not (Random 0 1)) (not local30))
					(= local30 1)
					(= state 5)
					(self cue:)
				else
					(= state 0)
					(= ticks (Random 60 150))
				)
			)
			(3
				(if (not local30)
					(= local30 (= register 1))
					(= state 5)
					(self cue:)
				else
					(= register 0)
					(if (cast contains: eye) (eye dispose:))
					(genieSnake loop: 2 cel: 0 setCycle: End self)
				)
			)
			(4 (genieSnake dispose:))
			(5
				(if (!= (genieSnake cel?) 6)
					(genieSnake setCycle: End self)
				else
					(self cue:)
				)
			)
			(6
				(eye init: setCycle: End self)
			)
			(7 (= ticks 45))
			(8 (eye setCycle: Beg self))
			(9
				(eye dispose:)
				(= state (- (if register 3 else 2) 1))
				(self cue:)
			)
		)
	)
)

(instance showItemScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: MoveTo (singSing approachX?) (singSing approachY?) self
				)
			)
			(1
				(ego
					view: 211
					posn: 147 145
					loop: 0
					cel: 0
					normal: 0
					setCycle: End self
				)
			)
			(2 (= ticks 30))
			(3
				(messager say: 4 0 11 1 self)
			)
			(4
				(if (Btst 21)
					(messager say: 4 register 10 1 self)
				else
					(messager say: 4 register 11 2 self)
				)
			)
			(5 (ego setCycle: Beg self))
			(6 (= cycles 2))
			(7
				(ego
					reset: 4
					posn: (singSing approachX?) (singSing approachY?)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance singSingScr of Script
	(properties)
	
	(method (dispose)
		(if (and local29 (!= local29 -1))
			(if (not cuees) (= cuees (Set new:)))
			(cuees
				add: ((Cue new:) cuee: local29 cuer: self yourself:)
			)
		)
		(singSing cycleSpeed: 6)
		(= local29 (= seconds 0))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (Btst 21))
				(= cycles 1)
			)
			(1
				(= state
					(switch (Random 0 1)
						(0 4)
						(1 10)
					))
				(-- state)
				(if local29
					(self dispose:)
				else
					(= seconds (Random 3 12))
				)
			)
			(2
				(if register
					(singSing posn: 225 105 49)
				else
					(singSing posn: 268 96 85)
				)
				(= cycles 2)
			)
			(3 0)
			(4
				(singSing loop: 7 cel: 0)
				(self changeState: 2)
				(= state 4)
			)
			(5
				(singSing setCycle: End self)
			)
			(6 (= cycles 2))
			(7
				(singSing setCycle: Beg self)
			)
			(8 (= state 0) (= ticks 1))
			(9 0)
			(10
				(singSing loop: 8 cel: 0)
				(self changeState: 2)
				(= state 10)
			)
			(11
				(singSing cycleSpeed: 8 setCycle: End self)
			)
			(12
				(singSing cycleSpeed: 6 cel: 0)
				(= cycles 2)
			)
			(13 (self changeState: 8))
		)
	)
)

(instance getRibbonScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame givePoints: 1)
				(= cycles 2)
			)
			(1
				(ego get: 35 setHeading: 90 self)
			)
			(2
				(if (not (Btst 112)) (Bset 93))
				(= cycles 2)
			)
			(3
				(ego
					normal: 0
					setSpeed: 6
					posn: 142 149
					view: 215
					loop: 1
					cel: 0
				)
				(= cycles 2)
			)
			(4 (ego setCycle: CT 3 1 self))
			(5 (= cycles 2))
			(6
				(theRibbon dispose:)
				(ego setCycle: End self)
			)
			(7 (= cycles 2))
			(8
				(ego reset: 6 posn: 129 144)
				(= cycles 2)
			)
			(9
				(UnLoad 128 215)
				(messager say: 6 5 (if (Btst 10) 22 else 21) 0 self)
			)
			(10
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getLetterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theGame givePoints: 1)
				(= cycles 2)
			)
			(1
				(ego get: 32 setHeading: 270 self)
			)
			(2 (= cycles 2))
			(3
				(ego
					normal: 0
					setSpeed: 6
					posn: 138 147
					view: 215
					loop: 0
					cel: 0
				)
				(= cycles 2)
			)
			(4 (ego setCycle: CT 3 1 self))
			(5 (= cycles 2))
			(6
				(letter dispose:)
				(ego setCycle: End self)
			)
			(7 (= cycles 2))
			(8
				(ego reset: 7 posn: 149 142)
				(= cycles 2)
			)
			(9
				(if (cast contains: singSing)
					(theGlobalSound fade: 0 15 20 1)
					(musicScr dispose:)
				else
					(++ state)
				)
				(messager say: 5 5 0 1 self)
			)
			(10
				(if (!= (theGlobalSound prevSignal?) -1) (-- state))
				(= cycles 2)
			)
			(11
				(soundFx2 number: 213 loop: -1 play:)
				(if (& msgType $0002)
					(messager say: 5 5 0 2 self)
				else
					(KQ6Print
						addText:
							{Dearest Alexander:\n\nI cannot believe you are here, my friend! Please, please be careful! Abdul isn't about to let anyone interfere with his plans. Watch out for Abdul's genie, Alexander, and do not do anything rash.}
						init: self
					)
				)
			)
			(12
				(if (& msgType $0002)
					(= cycles 1)
				else
					(KQ6Print
						addText:
							{I am not without resources, and I will prevail if I can only find some small means of defense. Do nothing to try to get to me. You must not be endangered again for my sake.\n\nGreatly in your family's debt,\n\nCassima}
						init: self
					)
				)
			)
			(13
				(messager say: 5 5 0 3 self oneOnly: 0)
			)
			(14
				(soundFx2 client: self fade: 0 15 20 1)
			)
			(15
				(if (cast contains: singSing)
					(theGlobalSound play:)
					(musicScr state: -1 client: musicScr cue:)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance eye of Prop
	(properties
		x 128
		y 24
		view 902
		priority 14
		signal $0010
	)
)

(instance singSing of Actor
	(properties
		x 252
		y 125
		z 97
		noun 4
		approachX 138
		approachY 142
		view 213
		loop 2
		priority 14
		signal $2010
	)
	
	(method (init param1)
		(super init: &rest)
		(self
			setScale: 0
			setStep: 4 3
			ignoreActors:
			cel: (if (not param1) 8 else 0)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 1 5) (messager say: noun theVerb (if (Btst 21) 10 else 11)))
			((and (OneOf theVerb 29 46 44 66) (Btst 21)) (messager say: noun 29 10))
			((== theVerb 37)
				(if (Btst 21)
					(curRoom setScript: windUpBirdScr)
				else
					(curRoom setScript: befriendSSScr)
				)
			)
			((== theVerb 2)
				(messager
					say:
						noun
						theVerb
						(cond 
							((not (Btst 21)) 11)
							((ego has: 32) 19)
							(else 43)
						)
				)
			)
			((== theVerb 31) (curRoom setScript: fluteScr))
			((not (Btst 21)) (curRoom setScript: showItemScr 0 0))
			((OneOf theVerb 15 18) (messager say: noun 15 10))
			((== theVerb 32) (curRoom setScript: givePoemScr))
			((== theVerb 71)
				(if (Btst 94)
					(messager say: noun theVerb 27)
				else
					(Bset 94)
					(curRoom setScript: giveRoseScr)
				)
			)
			((== theVerb 70) (curRoom setScript: giveRingScr))
			((OneOf theVerb 42 27 28 45 8) (messager say: noun 42 10))
			((OneOf theVerb 33 65) (messager say: noun 33))
			((OneOf theVerb 63 67) (curRoom setScript: showItemScr 0 63))
			((OneOf theVerb 35 47 68 72) (curRoom setScript: showItemScr 0 theVerb))
			(else (curRoom setScript: showItemScr 0 0))
		)
	)
)

(instance letter of Actor
	(properties
		x 119
		y 89
		noun 5
		sightAngle 45
		approachX 146
		approachY 142
		yStep 4
		view 214
		loop 7
		cel 1
		priority 1
		signal $4810
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5)
		(if (!= ((inventory at: 32) owner?) -1)
			((inventory at: 32) owner: -1)
			(self setMotion: MoveTo 120 137 self)
		else
			(self cue:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: getLetterScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(self posn: 112 136 setLoop: 8 cel: 0 stopUpd:)
	)
)

(instance theRibbon of Actor
	(properties
		x 155
		y 87
		noun 6
		sightAngle 45
		approachX 133
		approachY 143
		yStep 4
		view 214
		loop 5
		priority 1
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 signal: 18448)
		(if (!= ((inventory at: 35) owner?) -1)
			((inventory at: 35) owner: -1)
			(self setMotion: MoveTo 155 138 self)
		else
			(self cue:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: getRibbonScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(self posn: 153 138 setLoop: 6 cel: 0 stopUpd:)
	)
)

(instance genieSnake of Prop
	(properties
		x 109
		y 5
		view 212
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: snakeScr setPri: 14)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance clown of Actor
	(properties
		x 62
		y 102
		view 717
	)
	
	(method (init)
		(super init: &rest)
		(self
			setScale: Scaler 100 84 134 81
			setScript: clownScr
			ignoreActors:
			setStep: 4 3
			moveSpeed: 7
			cycleSpeed: 7
			setCycle: StopWalk 2741
		)
	)
)

(instance windUpBird of Prop
	(properties
		x 141
		y 142
		view 883
		loop 5
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Fwd)
	)
)

(instance tree of Feature
	(properties
		x 160
		y 30
		noun 11
		sightAngle 40
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(messager
					say:
						noun
						theVerb
						(cond 
							((and (cast contains: singSing) (Btst 21)) 10)
							((cast contains: singSing) 11)
							(else 8)
						)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance holeInTree of Feature
	(properties
		x 147
		y 111
		noun 12
		sightAngle 40
		onMeCheck $0080
		approachX 136
		approachY 139
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 2)
	)
)

(instance genericFeatures of Feature
	(properties)
	
	(method (onMe event &tmp temp0)
		(= noun
			(switch (= temp0 (OnControl 4 (event x?) (event y?)))
				(4 8)
				(8 9)
				(16 10)
				(64 13)
				(else 
					(if (OneOf temp0 64 16384) 13 else 0)
				)
			)
		)
	)
)
