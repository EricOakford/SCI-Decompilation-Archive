;;; Sierra Script 1.0 - (do not remove this comment)
(script# 710)
(include game.sh)
(use Main)
(use LLRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm710 0
)

(instance rm710 of LLRoom
	(properties
		lookStr {Tramp's Casino has spared no expense to create the ultimate in tasteless luxuriant opulent detritus. 
		Video poker machines surround you to such an extent that you are unsure how to walk around them.}
		picture 710
		north 730
		south 700
	)
	
	(method (init)
		(ego init: normalize: 711)
		(switch prevRoomNum
			(720
				(ego edgeHit: 0)
				(theIconBar disable: 6)
			)
			(north
				(HandsOff)
				(ego setHeading: 180 edgeHit: 0)
				(if (Btst fEnteringRightArenaDoor)
					(self setScript: sFromNorth 0 1)
				else
					(self setScript: sFromNorth 0 0)
				)
			)
			(south (ego x: 144))
			(else 
				(HandsOn)
				(ego posn: 160 160 edgeHit: 0)
			)
		)
		(super init:)
		(girlPic1 init:)
		(girlPic2 init:)
		(rouletteArea init: approachVerbs: verbTalk)
		(blackjackArea init: approachVerbs: verbTalk)
		(chandelier init:)
		(chandelier2 init:)
		(ballroom init:)
		(statue1 init: approachVerbs: verbDo verbUse)
		(statue2 init: approachVerbs: verbDo verbUse)
		(statue3 init: approachVerbs: verbDo verbUse)
		(statue4 init: approachVerbs: verbDo verbUse)
		(statue5 init: approachVerbs: verbDo verbUse)
		(poker1 init: approachVerbs: verbDo verbUse)
		(poker3 init: approachVerbs: verbDo verbUse)
		(poker5 init: approachVerbs: verbDo verbUse)
		(poker6 init: approachVerbs: verbDo verbUse)
		(poker7 init: approachVerbs: verbDo verbUse)
		(poker8 init: approachVerbs: verbDo verbUse)
		(poker9 init: approachVerbs: verbDo verbUse)
		(poker10 init: approachVerbs: verbDo verbUse)
		(poker11 init: approachVerbs: verbDo verbUse)
		(poker12 init: approachVerbs: verbDo verbUse)
		(poker15 init: approachVerbs: verbDo verbUse)
		(poker16 init: approachVerbs: verbDo verbUse)
		(poker17 init: approachVerbs: verbDo verbUse)
		(poker18 init: approachVerbs: verbDo verbUse)
		(poker19 init: approachVerbs: verbDo verbUse)
		(poker20 init: approachVerbs: verbDo verbUse)
		(poker21 init: approachVerbs: verbDo verbUse)
		(poker22 init: approachVerbs: verbDo verbUse)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						99 0
						126 46
						126 62
						136 62
						136 72
						118 88
						108 88
						100 76
						72 76
						55 94
						113 94
						108 126
						121 126
						123 88
						151 88
						151 138
						105 138
						105 146
						41 146
						50 134
						50 129
						48 129
						46 135
						29 135
						15 160
						121 160
						128 176
						128 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						319 0
						319 189
						154 189
						165 184
						223 184
						246 174
						273 174
						273 154
						239 154
						239 150
						197 150
						197 136
						307 136
						294 100
						285 100
						275 112
						257 112
						243 100
						220 100
						227 115
						263 115
						263 124
						161 124
						161 114
						197 114
						194 101
						161 101
						159 90
						229 90
						224 73
						188 73
						188 63
						222 63
						219 52
						201 52
						190 46
						215 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						128 154
						194 154
						191 165
						133 165
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						161 0
						190 40
						182 46
						136 46
						129 41
					yourself:
				)
		)
		(if (== prevRoomNum 700)
			(theMusic fade: 127 10 10 0)
		else
			(theMusic number: 710 setLoop: -1 flags: mNOPAUSE play: 127)
		)
		(theMusic2 number: 711 setLoop: -1 flags: mNOPAUSE play: 127)
		(person0 init:)
		(person1 init:)
		(person2 init:)
		(person3 init:)
		(person4 init:)
		(person5 init:)
		(if (Btst 49)
			(rope1 init: stopUpd:)
			(rope2 init: stopUpd:)
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((and (Btst fTrampCasinoDone) (IsObjectOnControl ego (| cBLUE cGREEN)))
				(HandsOff)
				(curRoom setScript: sClosed)
			)
			((IsObjectOnControl ego cBLUE)
				(ego edgeHit: NORTH)
				(HandsOff)
				(self setScript: sToNorth 0 1)
			)
			((IsObjectOnControl ego 4)
				(ego edgeHit: NORTH)
				(HandsOff)
				(self setScript: sToNorth 0 0)
			)
		)
	)
	
	(method (newRoom n)
		(if (!= n 700) (theMusic fade:))
		(theMusic2 fade:)
		(super newRoom: n)
	)
)

(instance sClosed of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(TimePrint 710 0)
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 4) self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance sToNorth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setPri: 0
					setMotion: MoveTo (if register 115 else 205) 41 self
				)
			)
			(1
				(ego setPri: -1)
				(if register
					(Bset fEnteringRightArenaDoor)
				else
					(Bclr fEnteringRightArenaDoor)
				)
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance sFromNorth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setPri: 0
					x: (if register 115 else 205)
					y: 41
					setMotion: MoveTo (if register 136 else 184) 48 self
				)
			)
			(1
				(ego setPri: -1 setHeading: 180 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance person0 of Prop
	(properties
		x 29
		y 132
		description {the gambler}
		lookStr {All of these gamblers look compulsive to you.}
		view 710
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self setCycle: BegLoop person1)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 710 1)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: BegLoop person1)
	)
)

(instance person1 of Prop
	(properties
		x 93
		y 143
		view 710
		loop 1
		signal ignrAct
	)
	
	(method (doVerb)
		(person0 doVerb: &rest)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: BegLoop person2)
	)
)

(instance person2 of Prop
	(properties
		x 194
		y 184
		view 710
		loop 2
		signal ignrAct
	)
	
	(method (doVerb)
		(person0 doVerb: &rest)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: BegLoop person3)
	)
)

(instance person3 of Prop
	(properties
		x 294
		y 171
		view 710
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb)
		(person0 doVerb: &rest)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: BegLoop person4)
	)
)

(instance person4 of Prop
	(properties
		x 261
		y 152
		view 710
		loop 4
		signal ignrAct
	)
	
	(method (doVerb)
		(person0 doVerb: &rest)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: BegLoop person5)
	)
)

(instance person5 of Prop
	(properties
		x 73
		y 75
		view 710
		loop 5
		signal ignrAct
	)
	
	(method (doVerb)
		(person0 doVerb: &rest)
	)
	
	(method (cue)
		(super cue:)
		(self setCycle: BegLoop person0)
	)
)

(instance rope1 of View
	(properties
		x 131
		y 36
		view 710
		loop 6
		priority 1
		signal (| ignrAct fixPriOn)
	)
)

(instance rope2 of View
	(properties
		x 195
		y 37
		view 710
		loop 6
		priority 1
		signal (| ignrAct fixPriOn)
	)
)

(instance girlPic1 of Feature
	(properties
		x 56
		y 21
		nsTop 1
		nsBottom 41
		nsRight 112
		description {the painting}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 710 2)
				(TimePrint 710 3)
			)
			(verbDo
				(TimePrint 710 4)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance girlPic2 of Feature
	(properties
		x 56
		y 21
		nsTop 1
		nsLeft 203
		nsBottom 43
		nsRight 319
		description {the painting}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 710 2)
				(TimePrint 710 3)
			)
			(verbDo
				(TimePrint 710 4)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance rouletteArea of Feature
	(properties
		x 23
		y 70
		nsTop 45
		nsBottom 96
		nsRight 46
		description {the roulette tables}
		sightAngle 40
		lookStr {You don't know how to play roulette.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 710 5)
			)
			(verbTalk
				(TimePrint 710 6)
			)
			(verbUse
				(switch theItem
					(iSilverDollar
						(TimePrint 710 7)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance blackjackArea of Feature
	(properties
		x 278
		y 62
		nsTop 42
		nsLeft 237
		nsBottom 82
		nsRight 319
		description {the blackjack tables}
		sightAngle 40
		lookStr {Blackjack is just not your game. You can remember that much from "Leisure Suit Larry 1!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 710 8)
				(TimePrint 710 9 #at -1 185)
			)
			(verbTalk
				(TimePrint 710 10)
			)
			(verbUse
				(switch theItem
					(iSilverDollar
						(TimePrint 710 7)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chandelier of Feature
	(properties
		x 164
		y 107
		z 73
		nsTop 46
		nsLeft 134
		nsBottom 86
		nsRight 184
		description {the chandelier}
		sightAngle 40
		lookStr {Isn't it wonderful what they're doing with polymers these days?}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 710 11)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chandelier2 of Feature
	(properties
		x 164
		y 107
		z 73
		nsTop 25
		nsLeft 151
		nsBottom 44
		nsRight 177
		description {the chandelier}
		lookStr {Isn't it wonderful what they're doing with polymers these days?}
	)
	
	(method (doVerb theVerb theItem)
		(chandelier doVerb: theVerb theItem)
	)
)

(instance ballroom of Feature
	(properties
		x 156
		y 22
		nsLeft 120
		nsBottom 45
		nsRight 199
		description {the ballroom entrance}
		sightAngle 40
		lookStr {It appears a crowd has gathered inside the exclusive Tramp Ballroom.}
	)
)

(instance statue1 of Feature
	(properties
		x 229
		y 170
		nsTop 151
		nsLeft 212
		nsBottom 189
		nsRight 246
		description {the statue}
		sightAngle 40
		lookStr {Naked girls holding balloon bouquets have been a favorite subject for the world's greatest artists for many centuries.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 710 12)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance statue2 of Feature
	(properties
		x 258
		y 74
		nsTop 45
		nsLeft 248
		nsBottom 104
		nsRight 268
		sightAngle 40
	)
	
	(method (doVerb)
		(statue1 doVerb: &rest)
	)
)

(instance statue3 of Feature
	(properties
		x 69
		y 109
		nsTop 86
		nsLeft 55
		nsBottom 133
		nsRight 84
		sightAngle 40
	)
	
	(method (doVerb)
		(statue1 doVerb: &rest)
	)
)

(instance statue4 of Feature
	(properties
		x 116
		y 51
		nsTop 28
		nsLeft 108
		nsBottom 75
		nsRight 124
		sightAngle 40
	)
	
	(method (doVerb)
		(statue1 doVerb: &rest)
	)
)

(instance statue5 of Feature
	(properties
		x 159
		y 125
		nsTop 96
		nsLeft 148
		nsBottom 154
		nsRight 170
		description {the statue}
		sightAngle 40
		lookStr {What a set of balloons she has!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 710 13)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance poker1 of Feature
	(properties
		x 48
		y 82
		nsTop 66
		nsLeft 43
		nsBottom 99
		nsRight 54
		description {the video poker machine}
		sightAngle 40
		approachX 70
		approachY 93
	)
	
	(method (doVerb theVerb theItem &tmp [str 100])
		(switch theVerb
			(verbLook
				(Format @str 710 14 pokerJackpot)
				(TimePrint @str)
			)
			(verbDo
				(if (ego has: iSilverDollar)
					(Format @str 710 15 silverDollars)
					(SolvePuzzle 4 fPlayedVideoPoker)
					(TimePrint @str)
					(curRoom newRoom: 720)
				else
					(TimePrint 710 16)
				)
			)
			(verbUse
				(switch theItem
					(iSilverDollar
						(if (ego has: iSilverDollar)
							(Format @str 710 15 silverDollars)
							(TimePrint @str)
							(curRoom newRoom: 720)
						else
							(TimePrint 710 16)
						)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance poker3 of Feature
	(properties
		x 95
		y 115
		nsTop 80
		nsLeft 84
		nsBottom 135
		nsRight 107
		sightAngle 40
		approachX 109
		approachY 115
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker5 of Feature
	(properties
		x 127
		y 127
		nsTop 118
		nsLeft 108
		nsBottom 136
		nsRight 146
		sightAngle 40
		approachX 148
		approachY 135
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker6 of Feature
	(properties
		x 136
		y 99
		nsTop 82
		nsLeft 127
		nsBottom 117
		nsRight 146
		sightAngle 40
		approachX 151
		approachY 119
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker7 of Feature
	(properties
		x 212
		y 90
		nsTop 83
		nsLeft 177
		nsBottom 98
		nsRight 247
		sightAngle 40
		approachX 226
		approachY 106
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker8 of Feature
	(properties
		x 207
		y 113
		nsTop 105
		nsLeft 170
		nsBottom 122
		nsRight 245
		sightAngle 40
		approachX 212
		approachY 130
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker9 of Feature
	(properties
		x 292
		y 90
		nsTop 82
		nsLeft 270
		nsBottom 99
		nsRight 315
		sightAngle 40
		approachX 286
		approachY 107
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker10 of Feature
	(properties
		x 309
		y 124
		nsTop 99
		nsLeft 301
		nsBottom 149
		nsRight 318
		sightAngle 40
		approachX 304
		approachY 126
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker11 of Feature
	(properties
		x 238
		y 138
		nsTop 129
		nsLeft 208
		nsBottom 148
		nsRight 300
		sightAngle 40
		approachX 238
		approachY 154
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker12 of Feature
	(properties
		x 273
		y 185
		nsTop 164
		nsLeft 246
		nsBottom 185
		nsRight 319
		sightAngle 40
		approachX 273
		approachY 174
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker15 of Feature
	(properties
		x 48
		y 116
		nsTop 107
		nsLeft 8
		nsBottom 125
		nsRight 55
		sightAngle 40
		approachX 48
		approachY 131
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker16 of Feature
	(properties
		x 10
		y 146
		nsTop 126
		nsBottom 167
		nsRight 20
		sightAngle 40
		approachX 25
		approachY 143
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker17 of Feature
	(properties
		x 59
		y 162
		nsTop 149
		nsLeft 21
		nsBottom 168
		nsRight 111
		sightAngle 40
		approachX 59
		approachY 160
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker18 of Feature
	(properties
		x 112
		y 179
		nsTop 169
		nsLeft 104
		nsBottom 189
		nsRight 121
		sightAngle 40
		approachX 128
		approachY 188
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker19 of Feature
	(properties
		x 80
		y 63
		nsTop 55
		nsLeft 56
		nsBottom 72
		nsRight 105
		sightAngle 40
		approachX 93
		approachY 77
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker20 of Feature
	(properties
		x 228
		y 63
		nsTop 44
		nsLeft 221
		nsBottom 82
		nsRight 236
		sightAngle 40
		approachX 224
		approachY 80
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker21 of Feature
	(properties
		x 207
		y 64
		nsTop 57
		nsLeft 194
		nsBottom 72
		nsRight 220
		sightAngle 40
		approachX 209
		approachY 81
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)

(instance poker22 of Feature
	(properties
		x 100
		y 128
		nsTop 118
		nsLeft 79
		nsBottom 138
		nsRight 121
		sightAngle 40
		approachX 111
		approachY 144
	)
	
	(method (doVerb)
		(poker1 doVerb: &rest)
	)
)
