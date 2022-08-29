;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include sci.sh)
(use Main)
(use Procs)
(use Print)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm380 0
)

(local
	gabSittingDown
	grannyGreet
	itemShown
)
(instance rm380 of Rm
	(properties
		noun 1
		picture 380
		style $000a
	)
	
	(method (init)
		(super init:)
		(Load rsMESSAGE 380)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						144
						144
						310
						132
						287
						103
						214
						103
						226
						129
						191
						131
						181
						128
						174
						114
						92
						113
						91
						111
						75
						110
						51
						120
						66
						122
						54
						130
						18
						146
					yourself:
				)
		)
		(ego
			view: 900
			setCycle: StopWalk -1
			init:
			state: 2
			ignoreControl: -32768
			ignoreActors: 1
			setScale: Scaler 100 91 144 102
			signal: 4096
			posn: 70 114
		)
		(cond 
			((== prevRoomNum 390) (ego posn: 234 102 setMotion: PolyPath 234 120))
			((== prevRoomNum 50)
				(ego view: 381 setLoop: 0 cel: 5 posn: 175 117)
				(= gabSittingDown 1)
			)
		)
		(clock init:)
		(chair init:)
		(firePlace init:)
		(oilLamp init:)
		(flowers init:)
		(grandmaPic init:)
		(grandpaPic init:)
		(knitBasket init:)
		(magazineBin init:)
		(miscPhotos init:)
		(light init:)
		(tree init:)
		(sofa init:)
		(stairs init:)
		(tableLamp init:)
		(woodBin init:)
		(frontDoor init:)
		(oilLampFlame init:)
		(granny init:)
		(walkHandler add: stairs)
		(walkHandler add: self)
		(if
			(and
				(not (== prevRoomNum 50))
				(not (== prevRoomNum 390))
			)
			(curRoom setScript: egoEnters)
		)
	)
	
	(method (dispose)
		(walkHandler delete: stairs)
		(walkHandler delete: self)
		(super dispose:)
		(DisposeScript 935)
		(DisposeScript 939)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(13
					(if gabSittingDown
						(ego setMotion: 0 setScript: standUp)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego hide:)
				(if (Btst 201) (= grannyGreet (Random 3 7)))
				(= cycles 12)
			)
			(1
				(Load rsVIEW 382)
				(granny view: 385 loop: 0 cel: 7 setCycle: Beg self)
			)
			(2
				(granny view: 384 loop: 0 cel: 4 setCycle: Beg self)
			)
			(3
				(granny
					view: 382
					setPri: 4
					setCycle: StopWalk -1
					setMotion: PolyPath 75 118 self
				)
			)
			(4
				(UnLoad 128 382)
				(granny view: 386 loop: 0 cel: 0 setCycle: End self)
			)
			(5
				(if (Btst 201)
					(messager say: 20 0 grannyGreet 1)
				else
					(messager say: 20 0 2 1)
				)
				(= cycles 12)
			)
			(6
				(granny view: 386 loop: 1 cel: 0 setCycle: End self)
			)
			(7
				(ego
					show:
					posn: 30 114
					setPri: 3
					setMotion: PolyPath 101 124 self
				)
				(if (Btst 201)
					(messager say: 20 0 grannyGreet 2)
				else
					(messager say: 20 0 2 2)
				)
			)
			(8
				(Face ego granny)
				(granny view: 386 loop: 2 cel: 0 setCycle: End self)
			)
			(9
				(if (Btst 201)
					(messager say: 20 0 grannyGreet 3)
				else
					(messager say: 20 0 2 3)
				)
				(granny view: 382 setCycle: StopWalk -1)
				(Face granny ego self)
			)
			(10
				(ego hide:)
				(granny view: 383 loop: 3 cel: 0 setCycle: Osc 1 self)
			)
			(11
				(if (not (Btst 201)) (messager say: 20 0 2 4))
				(ego
					show:
					posn: 103 115
					setCycle: StopWalk -1
					setMotion: PolyPath 175 117 self
				)
				(granny
					view: 382
					loop: 0
					moveSpeed: 9
					cycleSpeed: 9
					setCycle: StopWalk -1
					setMotion: PolyPath 157 113 self
				)
			)
			(12 (Face ego 0 117 self))
			(13
				(granny view: 384 loop: 0 cel: 0 setCycle: End self)
				(ego view: 381 setLoop: 0 cel: 0 setCycle: End self)
			)
			(14)
			(15
				(theGame handsOn:)
				(Bset 201)
				(= gabSittingDown 1)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 78 126 75 121 81 114 101 115 122 115 126 119 113 126 97 127
							yourself:
						)
				)
				(self dispose:)
			)
		)
	)
)

(instance climbTheStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if gabSittingDown
					(ego view: 381 loop: 0 cel: 5 setCycle: Beg self)
				else
					(self cue:)
				)
			)
			(1
				(if gabSittingDown
					(granny view: 385 loop: 0 cel: 0 setCycle: End self)
				)
				(if (Btst 202)
					(messager say: 5 63 17 1 self)
				else
					(messager say: 5 63 16 1 self)
				)
			)
			(2
				(if (not gabSittingDown) (self cue:))
			)
			(3
				(if gabSittingDown (granny loop: 1 setCycle: Fwd))
				(ego normalize: 1 900 setMotion: PolyPath 226 102 self)
			)
			(4 (Face ego granny self))
			(5
				(if (Btst 202)
					(messager say: 5 63 17 2 self)
				else
					(messager say: 5 63 16 2 self)
				)
			)
			(6
				(curRoom newRoom: 390)
				(self dispose:)
			)
		)
	)
)

(instance sitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 175 117 self)
				(= gabSittingDown 1)
			)
			(1 (Face ego 0 117 self))
			(2
				(granny view: 385 loop: 0 cel: 7 setCycle: Beg self)
				(ego view: 381 setLoop: 0 cel: 0 setCycle: End self)
			)
			(3 (messager say: 8 8 8 0))
			(4
				(granny view: 384 loop: 1 setCycle: End self)
			)
			(5
				(granny loop: 0 cel: 4)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance standUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 381 loop: 0 cel: 5 setCycle: Beg self)
			)
			(1
				(= gabSittingDown 0)
				(ego normalize: 1 900)
				(self dispose:)
			)
		)
	)
)

(instance interrogateGrannyStanding of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 175 117 self)
			)
			(1 (Face ego 0 117 self))
			(2
				(messager say: 19 10 0 1 self)
				(ego view: 381 setLoop: 0 cel: 0 setCycle: End)
				(granny view: 385 loop: 0 cel: 7 setCycle: Beg self)
			)
			(3 (= interrogateSubject 26))
			(4
				(messager say: 19 10 0 2 self)
				(granny view: 384 loop: 1 cycleSpeed: 9 setCycle: Fwd)
			)
			(5
				(granny loop: 0 cel: 4)
				(curRoom newRoom: 50)
				(self dispose:)
			)
		)
	)
)

(instance interrogateGrannySitting of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 19 10 0 1 self)
				(= interrogateSubject 26)
			)
			(1
				(messager say: 19 10 0 2 self)
				(granny view: 384 loop: 1 cycleSpeed: 9 setCycle: Fwd)
			)
			(2
				(granny loop: 0 cel: 4)
				(curRoom newRoom: 50)
				(self dispose:)
			)
		)
	)
)

(instance talkToGranny of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 384)
				(if (and (not (Btst 200)) (not (ego has: 27)))
					(messager say: 19 11 15 1 self)
				else
					(messager say: 19 11 (Random 9 14) 1 self)
				)
			)
			(1
				(granny view: 384 loop: 1 cycleSpeed: 9 setCycle: Fwd)
				(if (and (not (Btst 200)) (not (ego has: 27)))
					(messager say: 19 11 15 2 self)
				else
					(messager say: 19 11 (Random 9 14) 2 self)
				)
			)
			(2
				(granny loop: 0 cel: 4 setCycle: 0)
				(if (and (not (Btst 200)) (not (ego has: 27)))
					(messager say: 19 11 15 3 self)
				else
					(self dispose:)
				)
				(Bset 200)
			)
			(3 (self dispose:))
		)
	)
)

(instance leaveRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 381 loop: 0 cel: 5 setCycle: Beg self)
			)
			(1
				(messager say: 2 6 0 1 self)
				(= gabSittingDown 0)
				(ego normalize: 1 900)
			)
			(2
				(messager say: 2 6 0 2)
				(granny
					view: 384
					loop: 0
					cel: 4
					setPri: 3
					setCycle: Beg self
				)
			)
			(3
				(ego setPri: 4 setMotion: PolyPath 129 116 self)
				(granny
					view: 383
					loop: 2
					cel: 0
					cycleSpeed: 9
					moveSpeed: 9
					setCycle: End
				)
			)
			(4 (granny setCycle: Beg self))
			(5
				(ego setPri: 4 setMotion: PolyPath 79 116 self)
				(granny
					view: 382
					setCycle: StopWalk -1
					setMotion: PolyPath 99 116 self
				)
			)
			(6 (Face ego granny))
			(7
				(ego hide:)
				(granny view: 383 loop: 4 cel: 0 setCycle: Osc 1 self)
			)
			(8
				(messager say: 2 6 (Random 18 22) 0 self)
				(ego show:)
				(granny view: 382 loop: 1)
			)
			(9 (Face ego 0 116 self))
			(10
				(curRoom newRoom: 205)
				(self dispose:)
			)
		)
	)
)

(instance showSomethingToGranny of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints {Gab shows Granny the item here.})
				(= cycles 1)
			)
			(1
				(messager say: 19 itemShown 0 1 self)
			)
			(2
				(messager say: 19 itemShown 0 2 self)
				(granny view: 384 loop: 1 cel: 0 setCycle: Fwd)
			)
			(3
				(granny loop: 0 cel: 4 setCycle: 0)
				(self dispose:)
			)
		)
	)
)

(instance granny of Actor
	(properties
		x 157
		y 113
		noun 19
		sightAngle 20
		view 385
		loop 1
		signal $5000
	)
	
	(method (init)
		(super init:)
		(if (== prevRoomNum 50)
			(self view: 384 loop: 0 cel: 4)
		else
			(self cycleSpeed: 8 setCycle: Fwd)
		)
	)
	
	(method (doVerb theVerb)
		(= itemShown theVerb)
		(if (OneOf theVerb 36 39 41 15 32)
			(ego setScript: showSomethingToGranny)
		else
			(switch theVerb
				(11
					(if (not gabSittingDown)
						(ego setScript: sitDown)
					else
						(ego setScript: talkToGranny)
					)
				)
				(10
					(if gabSittingDown
						(ego setScript: interrogateGrannySitting)
					else
						(ego setScript: interrogateGrannyStanding)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance oilLampFlame of Prop
	(properties
		x 299
		y 106
		noun 9
		sightAngle 20
		view 380
	)
)

(instance clock of Feature
	(properties
		x 273
		y 68
		noun 11
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 260 51 280 51 280 78 271 89 261 89
					yourself:
				)
		)
		(super init:)
	)
)

(instance chair of Feature
	(properties
		x 199
		y 106
		noun 8
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						181
						96
						201
						95
						210
						82
						220
						87
						211
						103
						209
						115
						204
						115
						202
						114
						191
						114
						187
						117
						181
						112
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if gabSittingDown
					(messager say: 8 8 1 0)
				else
					(ego setScript: sitDown)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance firePlace of Feature
	(properties
		x 14
		y 111
		noun 4
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						19
						131
						5
						129
						0
						132
						0
						95
						31
						90
						36
						91
						34
						115
						26
						121
						28
						125
						42
						129
						21
						138
					yourself:
				)
		)
		(super init:)
	)
)

(instance oilLamp of Feature
	(properties
		x 302
		y 195
		noun 9
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						277
						145
						270
						132
						272
						126
						284
						121
						285
						115
						283
						110
						290
						103
						281
						102
						274
						92
						274
						86
						288
						68
						290
						26
						299
						23
						309
						25
						312
						70
						319
						81
						319
						106
						313
						113
						313
						119
						319
						120
						319
						145
					yourself:
				)
		)
		(super init:)
	)
)

(instance flowers of Feature
	(properties
		x 261
		y 143
		noun 10
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						248
						130
						245
						128
						233
						104
						241
						97
						241
						93
						250
						94
						246
						89
						253
						91
						259
						84
						264
						87
						269
						84
						272
						90
						281
						103
						290
						104
						282
						110
						283
						114
						270
						129
						274
						138
						275
						145
						244
						145
					yourself:
				)
		)
		(super init:)
	)
)

(instance grandmaPic of Feature
	(properties
		x 110
		y 63
		noun 7
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 99 67 100 54 108 47 115 47 124 56 123 69 114 76 108 76
					yourself:
				)
		)
		(super init:)
	)
)

(instance grandpaPic of Feature
	(properties
		x 154
		y 58
		noun 6
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 143 68 143 56 150 47 159 47 167 56 168 66 159 77 154 77
					yourself:
				)
		)
		(super init:)
	)
)

(instance knitBasket of Feature
	(properties
		x 101
		y 118
		noun 13
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 90 111 98 108 114 118 105 123 87 120
					yourself:
				)
		)
		(super init:)
	)
)

(instance magazineBin of Feature
	(properties
		x 199
		y 120
		noun 16
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 187 117 189 114 202 113 204 116 209 117 207 122 208 125 189 125
					yourself:
				)
		)
		(super init:)
	)
)

(instance miscPhotos of Feature
	(properties
		x 90
		y 143
		noun 15
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						190
						135
						198
						130
						204
						132
						206
						138
						214
						119
						238
						116
						242
						123
						239
						133
						242
						146
						191
						144
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 0 67 9 73 11 81 17 80 15 76 26 75 32 90 13 94 0 94
					yourself:
				)
		)
		(super init:)
	)
)

(instance light of Feature
	(properties
		x 131
		y 49
		noun 12
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						135
						28
						133
						32
						133
						46
						138
						51
						138
						56
						132
						61
						125
						57
						125
						51
						131
						46
						131
						32
						127
						28
					yourself:
				)
		)
		(super init:)
	)
)

(instance tree of Feature
	(properties
		x 85
		y 84
		noun 17
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						79
						99
						82
						96
						73
						89
						73
						68
						77
						61
						92
						58
						97
						74
						96
						81
						85
						92
						85
						96
						90
						98
						92
						102
						89
						108
						80
						108
					yourself:
				)
		)
		(super init:)
	)
)

(instance sofa of Feature
	(properties
		x 133
		y 82
		noun 3
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						92
						96
						88
						98
						84
						95
						86
						92
						96
						82
						107
						82
						126
						76
						139
						75
						155
						82
						173
						81
						181
						96
						175
						97
						173
						95
						171
						111
						96
						111
					yourself:
				)
		)
		(super init:)
	)
)

(instance stairs of Feature
	(properties
		x 227
		y 70
		noun 5
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 220 88 215 82 215 51 235 51 247 74 247 94 241 100 215 101
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(ego setScript: climbTheStairs)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tableLamp of Feature
	(properties
		x 189
		y 75
		noun 18
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 182 77 186 67 191 68 195 80 193 89 184 89
					yourself:
				)
		)
		(super init:)
	)
)

(instance woodBin of Feature
	(properties
		x 37
		y 150
		noun 14
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 26 120 35 111 44 110 48 118 55 119 53 127 46 128 28 125
					yourself:
				)
		)
		(super init:)
	)
)

(instance frontDoor of Feature
	(properties
		x 54
		y 75
		noun 2
		sightAngle 20
	)
	
	(method (init)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 36 48 70 52 72 108 44 118 38 116
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (ego setScript: leaveRoom))
			(else  (super doVerb: theVerb))
		)
	)
)
