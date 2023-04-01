;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include game.sh) (include "70.shm")
(use Main)
(use CastDart)
(use CastCalm)
(use CastDazzle)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Timer)
(use Motion)
(use Game)
(use System)

(public
	rm70 0
	proc70_1 1
)

(local
	;Night version is now its own room
	eatingShrooms
	faeriesAreHere
	theWindow
	noFaeries
	;New for VGA
	pickEmX
	pickEmY
	pickEmLoop
	;unused EGA remnants
	askedToDance
	[mush 8]
	mushX = [62 119 91 145 134 104 75 40]
	mushY = [109 108 108 107 101 98 97 101]
)
(procedure (proc70_1 param1 param2)
	;NOTE: This procedure is unused
;;;	(asm
;;;		pushi    0
;;;		sal      pickEmX
;;;		ldi      95
;;;		sal      pickEmY
;;;		ldi      0
;;;		sal      pickEmLoop
;;;		pushi    #setScript
;;;		pushi    1
;;;		lofsa    pickEm
;;;		push    
;;;		lag      curRoom
;;;		send     6
;;;		jmp      code_042f
;;;		pushi    #doVerb
;;;		pushi    1
;;;		lsp      param1
;;;		&rest    param2
;;;		super    Feature,  6
;;;code_042f:
;;;		toss    
;;;		ret     
;;;	)
)

(instance rm70 of Room
	(properties
		noun N_ROOM
		picture 70
		style HSHUTTER
		horizon 37
		north 62
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						63 71
						230 71
						230 88
						254 88
						254 144
						241 144
						241 156
						139 156
						139 123
						160 114
						132 102
						88 102
						81 111
						59 111
						57 103
						39 92
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						59 140
						125 140
						125 161
						59 161
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						66 115
						102 115
						132 121
						132 138
						97 124
						66 124
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						94 0
						94 54
						0 54
						0 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 0
						319 54
						149 54
						150 -3
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 137
						33 157
						39 184
						110 184
						110 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						163 189
						163 187
						319 187
						319 189
					yourself:
				)
		)
		(LoadMany RES_VIEW vEgoThrowing vEgoGetDust)
		(= currentPalette 0)
		(super init:)
		(StatusLine enable:)
		(NormalEgo)
		(switch prevRoomNum
			(62
				(curRoom setScript: sEnterFromNorth)
			)
			(71
				(curRoom setScript: sEnterFromEast)
			)
			(69
				(curRoom setScript: sEnterFromWest)
			)
			(999
				(ego posn: (ego x?) (ego y?) init:)
			)
			(else 
				(curRoom setScript: sEnterFromSouth)
			)
		)
		(mush1 init:)
		(mush2 init:)
		(mush3 init:)
		(mush4 init:)
		(mush5 init:)
		(mush6 init:)
		(mush7 init:)
		(ring
			init:
;;;			setOnMeCheck: ftrControl cYELLOW
		)
		(rocks
			init:
;;;			setOnMeCheck: ftrControl cCYAN
		)
		(treesRight
			init:
;;;			setOnMeCheck: ftrControl cGREEN
		)
		(treesLeft
			init:
;;;			setOnMeCheck: ftrControl cBLUE
		)
		(roomTimer setReal: self 6)
	)
	
	(method (doit)
		(cond 
			(script)
			((> (ego x?) 310)
				(HandsOff)
				(curRoom setScript: sExitEast)
			)
			((< (ego x?) 10)
				(HandsOff)
				(curRoom setScript: sExitWest)
			)
			((> (ego y?) 185)
				(HandsOff)
				(curRoom setScript: sExitSouth)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK ;ALTTALK
				(messager say: N_ROOM V_ALTTALK NULL 1)
			)
			(V_DETECT
				;EO: This message uses the wrong verb in the MSG file, so we need to use that here.
				(messager say: N_ROOM V_ALTTALK NULL 2)
			)
			(V_DAZZLE
				(CastDazz)
			)
			(V_CALM
				(CastCalm)
			)
			(V_FLAME
				(CastDart 0)
			)
			(else
				(messager say: N_ROOM NULL C_NONEEDTO)
			)
		)
	)
	
	(method (cue)
		(if (not (Btst fBeenIn70)) (Bset fBeenIn70)
			(messager say: N_ROOM NULL C_FIRSTENTRY)
		)
	)
	
	(method (newRoom n)
		(roomTimer dispose: delete:)
		(super newRoom: n)
	)
)

(instance treesLeft of Feature
	(properties
		x 58
		y 94
		noun N_TREESLEFT
		nsTop -1
		nsBottom 189
		nsRight 116
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance treesRight of Feature
	(properties
		x 224
		y 94
		noun N_TREESRIGHT
		nsTop -1
		nsLeft 130
		nsBottom 189
		nsRight 319
		sightAngle 40
		onMeCheck cGREEN
	)
)

(instance rocks of Feature
	(properties
		x 145
		y 101
		noun N_ROCKS
		nsTop 49
		nsLeft 43
		nsBottom 153
		nsRight 247
		sightAngle 40
		onMeCheck cCYAN
	)
)

(instance mush1 of Feature
	(properties
		x 115
		y 102
		noun N_MUSHROOMS
		nsTop 84
		nsLeft 79
		nsBottom 102
		nsRight 152
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_MUSHROOMS V_LOOK NULL)
			)
			(V_DO
				(if (not pickEmX)
					(= pickEmX 118)
					(= pickEmY 95)
					(= pickEmLoop 0)
				)
				(curRoom setScript: pickEm)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mush2 of Feature
	(properties
		x 71
		y 102
		noun N_MUSHROOMS
		nsTop 95
		nsLeft 64
		nsBottom 109
		nsRight 79
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(= pickEmLoop 0)
			(= pickEmX 55)
			(= pickEmY 104)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush3 of Feature
	(properties
		x 80
		y 116
		noun N_MUSHROOMS
		nsTop 111
		nsLeft 72
		nsBottom 121
		nsRight 89
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(HandsOff)
			(= pickEmLoop 0)
			(= pickEmX 61)
			(= pickEmY 118)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush4 of Feature
	(properties
		x 113
		y 123
		noun N_MUSHROOMS
		nsTop 115
		nsLeft 97
		nsBottom 131
		nsRight 129
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(HandsOff)
			(= pickEmX 140)
			(= pickEmY 123)
			(= pickEmLoop 1)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush5 of Feature
	(properties
		x 156
		y 125
		noun N_MUSHROOMS
		nsTop 119
		nsLeft 144
		nsBottom 131
		nsRight 168
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(HandsOff)
			(= pickEmX 157)
			(= pickEmY 114)
			(= pickEmLoop 0)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush6 of Feature
	(properties
		x 163
		y 102
		noun N_MUSHROOMS
		nsTop 96
		nsLeft 153
		nsBottom 108
		nsRight 174
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(HandsOff)
			(= pickEmX 157)
			(= pickEmY 114)
			(= pickEmLoop 0)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance mush7 of Feature
	(properties
		x 177
		y 116
		noun N_MUSHROOMS
		nsTop 109
		nsLeft 170
		nsBottom 121
		nsRight 185
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(HandsOff)
			(= pickEmX 157)
			(= pickEmY 114)
			(= pickEmLoop 0)
		)
		(mush1 doVerb: theVerb &rest)
	)
)

(instance ring of Feature
	(properties
		x 137
		y 100
		z 100
		noun N_MUSHROOMS
		sightAngle 40
		onMeCheck cYELLOW
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_MUSHROOMS V_LOOK NULL)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance sExitEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 350 (ego y?) self)
			)
			(1
				(curRoom newRoom: 71)
			)
		)
	)
)

(instance sExitWest of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo -25 (ego y?) self)
			)
			(1
				(curRoom newRoom: 69)
			)
		)
	)
)

(instance sExitSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) 245 self)
			)
			(1
				(curRoom newRoom: 77)
			)
		)
	)
)

(instance sEnterFromEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego init: posn: 350 111 setMotion: MoveTo 300 111 self)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromWest of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego init: posn: -25 126 setMotion: MoveTo 57 126 self)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance pickEm of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: PolyPath pickEmX pickEmY self
				)
			)
			(1
				(ego
					view: vEgoThrowing
					setLoop: pickEmLoop
					setCel: 0
					setCycle: EndLoop self
				)
				(= pickEmX 0)
				(= pickEmY 0)
				(= pickEmLoop 0)
			)
			(2
				(messager say: N_ROOM NULL NULL 0 self)
			)
			(3
				(Bset fHaveFaeryShrooms)
				(SolvePuzzle f70GetMushrooms 3)
				(ego
					get: iMushroom 3
					setCycle: BegLoop self
				)
			)
			(4
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromNorth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego init: posn: 122 39 setMotion: MoveTo 122 51 self)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFromSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					init:
					posn: 139 245
					setMotion: MoveTo 139 180
					self
				)
			)
			(1
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance roomTimer of Timer)