;;; Sierra Script 1.0 - (do not remove this comment)
(script# 315)
(include sci.sh)
(use Main)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm315 0
)

(local
	local0
	local1
	[local2 16] = [224 221 221 221 221 221 225 225 234 240 242 243 239 238 239 238]
	[local18 16] = [66 67 67 67 67 67 67 67 73 77 86 90 88 89 91 91]
	local34
	[local35 18] = [253 255 255 254 254 255 251 250 248 244 238 233 233 234 240 241 241 240]
	[local53 18] = [70 71 71 71 71 71 71 71 70 69 73 85 87 87 84 86 87 87]
	[local71 9] = [256 253 252 241 228 218 213 201 192]
	[local80 9] = [38 39 39 40 46 59 56 54 54]
	[local89 9] = [0 1 2 3 4 5 6 4]
	local98
	[local99 6] = [159 148 138 126 116 102]
	[local105 6] = [145 147 144 146 144 145]
)
(instance rm315 of Rm
	(properties
		noun 9
		picture 64
	)
	
	(method (init)
		(self setRegions: 350)
		(LoadMany 128 422 423 411 425 402 401 424 400 426)
		(NormalEgo 0)
		(ego actions: (ScriptID 350 1))
		(switch prevRoomNum
			(305
				(if (Btst 21)
					(curRoom setScript: sWD40Explodes)
					(curRoom style: 14)
				else
					(curRoom setScript: sEnterViaTunnelE)
				)
			)
			(else 
				(curRoom setScript: sEnterViaTunnelB)
			)
		)
		(if (not (Btst 21))
			(ego setScale: Scaler 38 36 143 69 init:)
		)
		(super init:)
		(theMusic3
			number: 403
			owner: self
			flags: 1
			init:
			setLoop: -1
			play: 50
		)
		(if (not (Btst 13))
			(rock init:)
			(walkHandler addToFront: rock)
		)
		(bubbles init: setCycle: Fwd)
		(fall1 init: setCycle: Fwd)
		(fall2 init: setCycle: Fwd)
		(if (not (Btst 21))
			(wd40 view: 424 loop: 2 cel: 0 x: 50 y: 50 init:)
		)
		(bottomCave init: setOnMeCheck: 1 8192)
		(bottomFalls init: setOnMeCheck: 1 2)
		(cave305 init: setOnMeCheck: 1 256)
		(cave310 init: setOnMeCheck: 1 4096)
		(pond init: setOnMeCheck: 1 4)
		(topCave init: setOnMeCheck: 1 16384)
		(topFalls init: setOnMeCheck: 1 8)
		(tree init: setOnMeCheck: 1 16)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						47
						144
						63
						143
						100
						143
						128
						139
						128
						138
						100
						138
						100
						140
						63
						140
						62
						139
						47
						138
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 225 65 225 63 190 63 189 66 197 66 224 66
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 254 70 285 71 285 70 285 68 254 68
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 273 43 262 43 262 45 273 45
					yourself:
				)
		)
	)
	
	(method (doit)
		(asm
			pushi    #doit
			pushi    0
			super    Rm,  4
			pushi    7
			pushi    6
			pushi    240
			pushi    248
			pushi    5
			pushi    249
			pushi    255
			pushi    65528
			callk    Palette,  14
			pushi    5
			pushi    34
			pushi    140
			pushi    73
			pushi    150
			lsg      ego
			calle    InRect,  10
			bnt      code_03c9
			pushi    #script
			pushi    0
			lofsa    wd40
			send     4
			not     
			bnt      code_03c9
			pushi    1
			pushi    14
			callb    Btst,  2
			not     
			bt       code_03b9
			pushi    1
			pushi    15
			callb    Btst,  2
			bnt      code_03c9
code_03b9:
			pushi    #setScript
			pushi    1
			lofsa    sWD40Appears
			push    
			lofsa    wd40
			send     6
			jmp      code_0589
code_03c9:
			pushi    5
			pushi    100
			pushi    137
			pushi    129
			pushi    139
			lsg      ego
			calle    InRect,  10
			bnt      code_040e
			pushi    270
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			gt?     
			bnt      code_03f1
			pprev   
			ldi      90
			gt?     
code_03f1:
			not     
			bnt      code_040e
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_040e
			pushi    #setScript
			pushi    1
			lofsa    sExitViaTunnelB
			push    
			lag      curRoom
			send     6
			jmp      code_0589
code_040e:
			pushi    5
			pushi    275
			pushi    67
			pushi    287
			pushi    71
			lsg      ego
			calle    InRect,  10
			bnt      code_044c
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			push    
			ldi      180
			lt?     
			bnt      code_044c
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_044c
			pushi    #setScript
			pushi    1
			lofsa    sExitViaTunnelE
			push    
			lag      curRoom
			send     6
			jmp      code_0589
code_044c:
			pushi    5
			pushi    34
			pushi    130
			pushi    73
			pushi    139
			lsg      ego
			calle    InRect,  10
			bnt      code_0493
			pushi    270
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			gt?     
			bnt      code_0473
			pprev   
			ldi      90
			gt?     
code_0473:
			not     
			bnt      code_0493
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_0493
			pushi    #setScript
			pushi    3
			lofsa    sThruTunnelC
			push    
			pushi    0
			pushi    1
			lag      curRoom
			send     10
			jmp      code_0589
code_0493:
			pushi    5
			pushi    191
			pushi    63
			pushi    200
			pushi    66
			lsg      ego
			calle    InRect,  10
			bnt      code_04d4
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			push    
			ldi      180
			gt?     
			bnt      code_04d4
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_04d4
			pushi    #setScript
			pushi    3
			lofsa    sThruTunnelC
			push    
			pushi    0
			pushi    0
			lag      curRoom
			send     10
			jmp      code_0589
code_04d4:
			pushi    5
			pushi    221
			pushi    62
			pushi    227
			pushi    67
			lsg      ego
			calle    InRect,  10
			bnt      code_0516
			pushi    0
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0516
			pprev   
			ldi      180
			lt?     
			bnt      code_0516
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_0516
			pushi    #setScript
			pushi    1
			lofsa    sJumpLeftToRight
			push    
			lag      curRoom
			send     6
			jmp      code_0589
code_0516:
			pushi    5
			pushi    254
			pushi    67
			pushi    258
			pushi    71
			lsg      ego
			calle    InRect,  10
			bnt      code_0553
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			push    
			ldi      180
			gt?     
			bnt      code_0553
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_0553
			pushi    #setScript
			pushi    1
			lofsa    sJumpRightToLeft
			push    
			lag      curRoom
			send     6
			jmp      code_0589
code_0553:
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      45
			eq?     
			bnt      code_0589
			pushi    270
			pushi    #heading
			pushi    0
			lag      ego
			send     4
			ge?     
			bnt      code_0589
			pprev   
			ldi      90
			ge?     
			bnt      code_0589
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_0589
			pushi    #setScript
			pushi    1
			lofsa    sClimbDownFromRock
			push    
			lag      curRoom
			send     6
code_0589:
			ret     
		)
	)
	
	(method (dispose)
		(theMusic2 stop:)
		(theMusic3 dispose:)
		(if (walkHandler contains: rock)
			(walkHandler delete: rock)
		)
		(DisposeScript 991)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 16)
					(messager say: noun theVerb 2 0)
				else
					(messager say: noun theVerb 1 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sBounceRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sWD40EntersCave dispose:)
				(= local0 0)
				(Bset 13)
				(= local34 0)
				(= cycles 1)
			)
			(1
				(ego setMotion: MoveTo 281 41 self)
			)
			(2 (ego setHeading: 270 self))
			(3
				(theMusic2 number: 405 setLoop: 1 play:)
				(ego
					view: 422
					setLoop: 6
					cel: 0
					x: 279
					y: 44
					setScale: 0
					setCycle: End self
				)
			)
			(4 (ego setCycle: Beg self))
			(5
				(ego setCycle: End self)
				(theMusic2 number: 405 setLoop: 1 play:)
			)
			(6
				(theMusic2 stop:)
				(= cycles 1)
			)
			(7
				(NormalEgo 0)
				(ego
					x: 281
					y: 41
					setScale: Scaler 38 36 143 69
					setMotion: MoveTo 263 43
				)
				(= cycles 1)
			)
			(8
				(rock
					cel: [local89 local34]
					x: [local71 local34]
					y: [local80 local34]
				)
				(if (== local34 3)
					(ext1
						view: 422
						cel: 0
						x: 269
						y: 57
						setStep: 2 10
						signal: (| (ext1 signal?) $2000)
						ignoreActors:
						init:
						setCycle: 0
						setLoop: -1
						setLoop: 8
						setMotion: MoveTo 287 189
					)
					(ext2
						view: 422
						cel: 1
						x: 270
						y: 48
						setStep: 2 10
						signal: (| (ext2 signal?) $2000)
						ignoreActors:
						init:
						setCycle: 0
						setLoop: -1
						setLoop: 8
						setMotion: MoveTo 272 189
					)
					(ext3
						view: 422
						cel: 2
						x: 268
						y: 47
						setStep: 2 10
						signal: (| (ext3 signal?) $2000)
						ignoreActors:
						init:
						setCycle: 0
						setLoop: -1
						setLoop: 8
						setMotion: MoveTo 270 189
					)
					(ext4
						view: 422
						cel: 3
						x: 271
						y: 50
						setStep: 2 10
						signal: (| (ext4 signal?) $2000)
						ignoreActors:
						init:
						setCycle: 0
						setLoop: -1
						setLoop: 8
						setMotion: MoveTo 277 189
					)
				)
				(if (== local34 5)
					(theMusic2 number: 407 setLoop: 1 play:)
				)
				(if (< (++ local34) 9) (-- state))
				(= ticks 5)
			)
			(9
				(theMusic2 number: 408 setLoop: -1 play:)
				(rock hide:)
				(= ticks 180)
			)
			(10
				(theMusic2 number: 409 setLoop: 1 play:)
				(rock loop: 9 cel: 0 x: 45 y: 128 setPri: 13 show:)
				(= ticks 4)
			)
			(11
				(rock loop: 9 cel: 1 x: 45 y: 128)
				(wd40
					view: 423
					loop: 0
					cel: 0
					x: 92
					y: 142
					show:
					setCycle: End
				)
				(= ticks 5)
			)
			(12
				(wd40
					loop: 1
					cel: 0
					setCycle: 0
					scaleSignal: 1
					scaleX: 64
					scaleY: 64
					setMotion: JumpTo 240 240
				)
				(rock dispose:)
				(ext1
					view: 422
					cel: 2
					x: 43
					y: 159
					setStep: 5 10
					setLoop: -1
					setLoop: 9
					setMotion: MoveTo 26 189
				)
				(ext2
					view: 422
					cel: 3
					x: 93
					y: 145
					setStep: 5 10
					setLoop: -1
					setLoop: 9
					setMotion: MoveTo 129 159
				)
				(ext3
					view: 422
					cel: 4
					x: 69
					y: 162
					setStep: 5 10
					setLoop: -1
					setLoop: 9
					setMotion: MoveTo 76 189
				)
				(ext4
					view: 422
					cel: 5
					x: 31
					y: 133
					setStep: 5 10
					setLoop: -1
					setLoop: 9
					setMotion: MoveTo 0 127
				)
				(ext5
					view: 422
					cel: 6
					x: 39
					y: 118
					setStep: 5 10
					ignoreActors:
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 9
					setMotion: MoveTo 1 111
				)
				(ext6
					view: 422
					cel: 8
					x: 43
					y: 130
					ignoreActors:
					setStep: 5 10
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 9
					setMotion: MoveTo 20 144
				)
				(ext7
					view: 422
					cel: 14
					x: 91
					y: 128
					ignoreActors:
					setStep: 5 10
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 9
					setMotion: MoveTo 117 128
				)
				(ext8
					view: 422
					cel: 15
					x: 62
					y: 155
					setStep: 5 10
					ignoreActors:
					xStep: 10
					yStep: 10
					init:
					setCycle: 0
					setLoop: -1
					setLoop: 9
					setMotion: MoveTo 62 189
				)
				(= ticks 60)
			)
			(13
				(theMusic2 number: 410 setLoop: 1 play:)
				(ext1 dispose:)
				(ext2 setMotion: MoveTo 157 189)
				(ext3 dispose:)
				(ext4 dispose:)
				(ext5 dispose:)
				(ext6 setMotion: MoveTo 7 180)
				(ext7 setMotion: MoveTo 150 134 self)
				(ext8 dispose:)
			)
			(14
				(theMusic2 fade:)
				(ext2 dispose:)
				(ext6 dispose:)
				(ext7 setMotion: MoveTo 190 145 self)
			)
			(15
				(ext7 setMotion: MoveTo 222 185 self)
			)
			(16
				(ext7 dispose:)
				(Bset 14)
				(SolvePuzzle 208 100)
				(theMusic2 number: 411 setLoop: 1 play: self)
			)
			(17
				(theMusic1 fade: 0 5 10 1)
				(messager say: 7 0 0 0 self)
			)
			(18
				(theMusic1 number: 15 loop: -1 play: 0 fade: 127 10 5 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbUptoRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 265 69 self)
			)
			(1
				(ego
					view: 422
					setLoop: 5
					cel: 0
					x: 268
					y: 48
					setPri: 2
					setScale: 0
					setCycle: End self
				)
			)
			(2
				(ego cel: 0 x: 268 y: 38 setCycle: CT 9 1 self)
			)
			(3
				(NormalEgo 0)
				(ego x: 269 y: 44 setScale: Scaler 38 36 143 69)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbDownFromRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 271 43 self)
			)
			(1
				(ego
					view: 422
					setLoop: 5
					cel: 9
					x: 268
					y: 38
					setPri: 2
					setScale: 0
					setCycle: Beg self
				)
			)
			(2
				(ego cel: 10 x: 268 y: 48 setCycle: Beg self)
			)
			(3
				(NormalEgo 0)
				(ego
					x: 265
					y: 69
					setPri: -1
					setScale: Scaler 38 36 143 69
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterViaTunnelE of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					x: 287
					y: 69
					init:
					setPri: -1
					setMotion: MoveTo 270 69 self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterViaTunnelB of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego x: 107 y: 135 init: setMotion: MoveTo 107 140 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitViaTunnelE of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 411
					loop: 5
					cel: 0
					x: 273
					y: 66
					setCycle: End self
				)
			)
			(1
				(curRoom newRoom: 305)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitViaTunnelB of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 107 135 self)
			)
			(1
				(curRoom newRoom: 310)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThruTunnelC of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(ego setMotion: MoveTo 80 135 self)
				else
					(ego setMotion: MoveTo 185 66 self)
					(if (== local0 5) (curRoom setScript: sRogDiesInTunnel))
				)
			)
			(1
				(cond 
					((not register) (= seconds 5))
					((not (wd40 script?)) (= seconds 5))
				)
			)
			(2
				(if register
					(ego x: 185 y: 66 setMotion: MoveTo 208 65 self)
				else
					(ego x: 80 y: 135 setMotion: MoveTo 61 141 self)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sJumpLeftToRight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local34 0)
				(= cycles 1)
			)
			(1
				(ego
					view: 422
					loop: 0
					cel: local34
					setScale: 0
					setCycle: 0
					x: [local2 local34]
					y: [local18 local34]
				)
				(if (== local34 12)
					(theMusic2 number: 5041 setLoop: 1 play:)
				)
				(if (< (++ local34) 16) (-- state))
				(= ticks 6)
			)
			(2
				(ego
					view: 422
					loop: 2
					cel: 0
					x: 258
					y: 72
					setCycle: End self
				)
			)
			(3
				(NormalEgo 0)
				(ego x: 259 y: 70 setScale: Scaler 38 36 143 69)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sJumpRightToLeft of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local34 0)
				(= cycles 1)
			)
			(1
				(ego
					view: 422
					loop: 1
					setScale: 0
					x: [local35 local34]
					y: [local53 local34]
					setCycle: 0
				)
				(cond 
					((< local34 10) (ego cel: local34))
					((> local34 11) (ego cel: (- local34 2)))
					(else (ego cel: 9))
				)
				(if (== local34 12)
					(theMusic2 number: 5041 setLoop: 1 play:)
				)
				(if (< (++ local34) 17) (-- state))
				(= ticks 6)
			)
			(2
				(ego view: 422 loop: 3 cel: 0 x: 222 y: 70 setCycle: 0)
				(= cycles 1)
			)
			(3 (ego setCycle: End self))
			(4
				(NormalEgo 0)
				(ego loop: 1 y: 65 setScale: Scaler 38 36 143 69)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWD40Appears of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 0)
				(if (Btst 15) (= state (+ state 3)))
				(= cycles 1)
			)
			(1
				(theMusic2
					number: 401
					setLoop: -1
					play: 0
					fade: 127 10 10 0
				)
				(ext1
					view: 420
					loop: 0
					cel: 0
					x: 132
					y: 12
					init:
					setCycle: Osc 1 self
				)
				(ext2
					view: 420
					loop: 1
					cel: 0
					x: 139
					y: 28
					init:
					setCycle: Osc 1
				)
			)
			(2
				(ext1
					view: 420
					loop: 2
					cel: 0
					x: 226
					y: 136
					setCycle: Osc 1 self
				)
				(ext2
					view: 420
					loop: 3
					cel: 0
					x: 243
					y: 145
					setCycle: Osc 1
				)
			)
			(3
				(ext1
					view: 420
					loop: 4
					cel: 0
					x: 199
					y: 186
					setCycle: Osc 1 self
				)
			)
			(4
				(ext1 dispose:)
				(ext2 dispose:)
				(= local0 4)
				(theMusic1 number: 35 loop: -1 play:)
				(if (Btst 15)
					(wd40
						view: 425
						setLoop: -1
						setLoop: 0
						cel: 0
						x: 263
						y: 190
						ignoreActors:
						show:
						setMotion: MoveTo 263 170 self
					)
				else
					(= local0 2)
					(wd40
						view: 425
						loop: 2
						cel: 0
						x: 263
						y: 170
						ignoreActors:
						show:
						setCycle: End self
					)
				)
			)
			(5
				(jetpack init:)
				(wd40
					cel: 0
					xStep: 10
					yStep: 12
					setCycle: 0
					setLoop: -1
					setLoop: 0
					setMotion: MoveTo 242 89 self
				)
			)
			(6
				(if (InRect 76 131 84 139 ego)
					(if (Btst 15)
						(= next sWD40BackDown)
					else
						(= next sWD40EntersCave)
					)
				else
					(wd40 stopUpd:)
					(= next sWD40Fires)
				)
				(self dispose:)
			)
		)
	)
)

(instance sWD40BackDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wd40 setMotion: MoveTo 263 190 self)
			)
			(1
				(jetpack dispose:)
				(wd40 hide:)
				(sThruTunnelC cue:)
				(= local0 0)
				(self dispose:)
			)
		)
	)
)

(instance sWD40Fires of Script
	(properties)
	
	(method (doit)
		(if (InRect 76 131 84 139 ego)
			(if (Btst 15)
				(= next sWD40BackDown)
			else
				(= next sWD40EntersCave)
			)
			(self dispose:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(and (< (ego y?) 139) (not (curRoom script?)))
					(wd40 hide:)
					(self dispose:)
				else
					(Bset 66)
					(theMusic2 number: 402 setLoop: 1 play:)
					(fireBall
						view: 402
						loop: 0
						cel: 0
						x: 236
						y: 89
						setStep: 15 5
						moveSpeed: 0
						init:
						setScript: sFireBall self 0
					)
				)
			)
			(1 (= seconds 4))
			(2
				(if
				(and (< (ego y?) 139) (not (curRoom script?)))
					(wd40 hide:)
					(self dispose:)
				else
					(theMusic2 number: 402 setLoop: 1 play:)
					(fireBall
						view: 402
						loop: 0
						cel: 0
						x: 236
						y: 89
						setScript: sFireBall self 0
					)
				)
			)
			(3 (= seconds 4))
			(4
				(if
				(and (< (ego y?) 139) (not (curRoom script?)))
					(wd40 hide:)
					(self dispose:)
				else
					(ego setMotion: 0)
					(theMusic2 number: 402 setLoop: 1 play:)
					(fireBall
						view: 402
						loop: 0
						cel: 0
						x: 236
						y: 89
						setScript: sFireBall self 1
					)
				)
			)
			(5
				(fireBall dispose:)
				(cond 
					(
					(and (<= 45 (ego heading?)) (<= (ego heading?) 135)) (ego loop: 3))
					(
					(and (<= 136 (ego heading?)) (<= (ego heading?) 225)) (ego loop: 0))
					(
					(and (<= 226 (ego heading?)) (<= (ego heading?) 315)) (ego loop: 2))
					(else (ego loop: 1))
				)
				(ego
					view: 401
					cel: 0
					setScale: Scaler 38 36 143 69
					setCycle: End self
				)
			)
			(6
				(EgoDead 17)
				(self dispose:)
			)
		)
	)
)

(instance sFireBall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not register)
					(fireBall
						setMotion: MoveTo (+ (ego x?) 7) (- (ego y?) 10) self
					)
				else
					(fireBall
						setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 18) self
					)
				)
			)
			(1
				(if (not register)
					(theMusic2 number: 4021 setLoop: 1 play:)
					(fireBall view: 401 loop: 4 cel: 0 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sWD40EntersCave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ext10
					view: (wd40 view?)
					loop: (wd40 loop?)
					cel: (wd40 cel?)
					x: (wd40 x?)
					y: (wd40 y?)
					init:
					addToPic:
				)
				(wd40 hide:)
				(ext9
					view: (jetpack view?)
					loop: (jetpack loop?)
					cel: (jetpack cel?)
					x: (jetpack x?)
					y: (jetpack y?)
					init:
					addToPic:
				)
				(jetpack dispose:)
				(= cycles 2)
			)
			(1
				(ext9 dispose:)
				(ext10 dispose:)
				(addToPics delete: ext9 ext10)
				(DrawPic 64 dpOPEN_PIXELATION)
				(= cycles 2)
			)
			(2
				(theMusic3 play: 50)
				(= ticks 240)
			)
			(3
				(= local98 0)
				(wd40
					view: 424
					setLoop: 0
					cel: 0
					setPri: 15
					x: [local99 local98]
					y: [local105 local98]
					ignoreActors:
					setCycle: End
					setMotion: 0
					show:
				)
				(= ticks 30)
			)
			(4
				(wd40
					x: [local99 local98]
					y: [local105 local98]
					cel: 0
					setCycle: End self
				)
				(if (< (++ local98) 6) (-- state))
			)
			(5
				(theMusic2 number: 222 setLoop: 1 play:)
				(wd40
					view: 424
					loop: 1
					cel: 0
					x: 102
					y: 147
					ignoreActors:
					setCycle: End self
				)
			)
			(6
				(theMusic2 fade:)
				(wd40 hide:)
				(= local0 5)
				(sThruTunnelC cue:)
				(= cycles 1)
			)
			(7 (= seconds 26))
			(8
				(if (and (InRect 200 50 225 80 ego) (== local0 5))
					(curRoom setScript: sRogDies)
					(self dispose:)
				)
				(= local0 0)
				(= cycles 1)
			)
			(9
				(wd40
					view: 424
					loop: 3
					cel: 0
					x: 89
					y: 144
					show:
					ignoreActors:
					setCycle: End self
				)
			)
			(10
				(= local98 5)
				(wd40
					view: 424
					setLoop: 0
					cel: 0
					x: [local99 local98]
					y: [local105 local98]
					setCycle: End self
				)
			)
			(11
				(wd40
					x: [local99 local98]
					y: [local105 local98]
					setCycle: End self
				)
				(if (>= (-- local98) 0) (-- state))
			)
			(12
				(wd40 hide:)
				(self dispose:)
			)
		)
	)
)

(instance sRogDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(theMusic2 number: 402 setLoop: 1 play:)
				(fireBall
					view: 402
					loop: 0
					cel: 0
					x: 200
					y: 50
					init:
					setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 8) self
				)
			)
			(1
				(fireBall dispose:)
				(cond 
					(
					(and (<= 45 (ego heading?)) (<= (ego heading?) 135)) (ego loop: 3))
					(
					(and (<= 136 (ego heading?)) (<= (ego heading?) 225)) (ego loop: 0))
					(
					(and (<= 226 (ego heading?)) (<= (ego heading?) 315)) (ego loop: 2))
					(else (ego loop: 1))
				)
				(ego
					view: 401
					cel: 0
					setScale: Scaler 38 36 143 69
					setCycle: End self
				)
			)
			(2
				(EgoDead 17)
				(self dispose:)
			)
		)
	)
)

(instance sRogDiesInTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0)
				(theMusic2 number: 402 setLoop: 1 play:)
				(ext1
					view: 422
					loop: 11
					cel: 0
					x: 195
					y: 47
					init:
					setCycle: End
				)
				(ext2
					view: 422
					loop: 12
					cel: 0
					x: 207
					y: 63
					init:
					setPri: 15
					setCycle: End self
				)
			)
			(1
				(ext1 hide:)
				(ext2 hide:)
				(ego
					view: 401
					cel: 0
					setScale: Scaler 38 36 143 69
					setLoop: -1
					setLoop: 1
					setCycle: End self
				)
			)
			(2
				(EgoDead 18)
				(self dispose:)
			)
		)
	)
)

(instance sWD40Explodes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(wd40
					view: 400
					cel: 0
					x: 250
					y: 180
					setStep: 5 15
					scaleX: 106
					scaleY: 106
					scaleSignal: 1
					setCycle: 0
					setLoop: -1
					setLoop: 7
					moveSpeed: 0
					init:
					setMotion: MoveTo 148 107 self
				)
			)
			(1
				(theMusic2 number: 222 setLoop: -1 play:)
				(wd40
					view: 426
					loop: 0
					cel: 0
					x: 142
					y: 80
					setCycle: End self
				)
			)
			(2
				(theMusic2 number: 203 setLoop: 1 play:)
				(wd40 loop: 1 cel: 0 setCycle: End self)
			)
			(3
				(theMusic2 fade:)
				(wd40 dispose:)
				(ext1
					view: 426
					loop: 2
					cel: 0
					x: 142
					y: 80
					setStep: 3 8
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setMotion: MoveTo 100 200
				)
				(ext2
					view: 426
					loop: 2
					cel: 1
					x: 138
					y: 83
					setStep: 3 7
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setMotion: MoveTo 111 200
				)
				(ext3
					view: 426
					loop: 2
					cel: 2
					x: 129
					y: 78
					setStep: 3 9
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setMotion: MoveTo 120 200
				)
				(ext4
					view: 426
					loop: 2
					cel: 3
					x: 142
					y: 80
					setStep: 3 7
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setMotion: MoveTo 130 200
				)
				(ext5
					view: 426
					loop: 2
					cel: 4
					x: 142
					y: 80
					setStep: 3 8
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setMotion: MoveTo 150 200
				)
				(ext6
					view: 426
					loop: 2
					cel: 5
					x: 142
					y: 87
					setStep: 3 9
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setMotion: MoveTo 160 200
				)
				(ext7
					view: 426
					loop: 2
					cel: 6
					x: 148
					y: 80
					setStep: 3 6
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setMotion: MoveTo 170 200
				)
				(ext8
					view: 426
					loop: 2
					cel: 7
					x: 122
					y: 76
					setStep: 3 7
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setMotion: MoveTo 180 200
				)
				(ext9
					view: 426
					loop: 2
					cel: 8
					x: 142
					y: 80
					setStep: 3 8
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setMotion: MoveTo 190 200
				)
				(ext10
					view: 426
					loop: 3
					cel: 0
					x: 112
					y: 70
					setStep: 3 7
					moveSpeed: 0
					init:
					setPri: 15
					ignoreActors:
					setCycle: Fwd
					setMotion: MoveTo 200 200 self
				)
			)
			(4
				(theMusic2 fade:)
				(theMusic1 number: 17 setLoop: -1 play:)
				(Bset 16)
				(Bclr 15)
				(theGame handsOn:)
				(curRoom newRoom: 305)
				(self dispose:)
			)
		)
	)
)

(instance wd40 of Actor
	(properties
		x 50
		y 50
		noun 4
		modNum 301
		view 424
		priority 14
		signal $4010
	)
)

(instance ext1 of Actor
	(properties
		signal $4000
	)
)

(instance ext2 of Actor
	(properties
		signal $4000
	)
)

(instance ext3 of Actor
	(properties
		signal $4000
	)
)

(instance ext4 of Actor
	(properties
		signal $4000
	)
)

(instance ext5 of Actor
	(properties
		signal $4000
	)
)

(instance ext6 of Actor
	(properties
		signal $4000
	)
)

(instance ext7 of Actor
	(properties
		signal $4000
	)
)

(instance ext8 of Actor
	(properties
		signal $4000
	)
)

(instance ext9 of Actor
	(properties
		signal $4000
	)
)

(instance ext10 of Actor
	(properties
		signal $4000
	)
)

(instance rock of Actor
	(properties
		x 256
		y 38
		noun 3
		view 422
		loop 7
		priority 1
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
					(and
						(< (ego distanceTo: self) 40)
						(> (ego y?) 55)
						(not (curRoom script?))
					)
					(curRoom setScript: sClimbUptoRock)
				)
			)
			(3
				(if
					(and
						(< (ego distanceTo: self) 40)
						(> (ego y?) 55)
						(not (curRoom script?))
					)
					(curRoom setScript: sClimbUptoRock)
				)
			)
			(21
				(if (< (ego distanceTo: self) 26)
					(if (== local0 5)
						(curRoom setScript: sBounceRock)
					else
						(messager say: 10 0 0 0)
					)
				else
					(messager say: 6 0 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fireBall of Actor
	(properties
		x 236
		y 89
		view 402
		priority 15
		signal $4010
	)
)

(instance bubbles of Prop
	(properties
		x 100
		y 145
		view 421
	)
)

(instance fall1 of Prop
	(properties
		x 84
		y 68
		view 421
		loop 1
	)
)

(instance fall2 of Prop
	(properties
		x 149
		y 153
		view 421
		loop 2
		signal $1000
	)
)

(instance jetpack of Prop
	(properties
		view 425
		loop 4
		priority 15
		signal $4010
		cycleSpeed 3
	)
	
	(method (init)
		(self x: 272 y: 226)
		(super init:)
		(self setCycle: Fwd)
	)
	
	(method (doit)
		(self x: (+ (wd40 x?) 9) y: (+ (wd40 y?) 56))
		(super doit:)
	)
)

(instance bottomCave of Feature
	(properties
		x 62
		y 133
		noun 1
		onMeCheck $2000
	)
)

(instance bottomFalls of Feature
	(properties
		x 156
		y 176
		noun 2
		onMeCheck $0002
	)
)

(instance cave305 of Feature
	(properties
		x 275
		y 60
		noun 4
		onMeCheck $0100
	)
)

(instance cave310 of Feature
	(properties
		x 107
		y 131
		noun 5
		onMeCheck $1000
	)
)

(instance pond of Feature
	(properties
		x 122
		y 146
		noun 8
		onMeCheck $0004
	)
)

(instance topCave of Feature
	(properties
		x 199
		y 55
		noun 11
		onMeCheck $4000
	)
)

(instance topFalls of Feature
	(properties
		x 94
		y 111
		noun 12
		onMeCheck $0008
	)
)

(instance tree of Feature
	(properties
		x 4
		y 116
		noun 13
		onMeCheck $0010
	)
)

(instance theMusic3 of Sound
	(properties
		flags $0001
	)
)
