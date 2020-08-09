;;; Sierra Script 1.0 - (do not remove this comment)
(script# 670)
(include game.sh) (include "670.shm")
(use Main)
(use FPRoom)
(use Scaler)
(use RandCyc)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm670 0
)

(instance rm670 of FPRoom
	(properties
		noun N_ROOM
		picture 670
		style PLAIN
		south 230
	)
	
	(method (init &tmp temp0)
		(ego
			init:
			x: 47
			setScale: Scaler 120 63 189 97
			normalize:
		)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						8 189
						0 189
						0 0
						319 0
						319 189
						82 189
						82 185
						95 180
						108 177
						216 177
						228 185
						244 185
						251 179
						291 179
						291 151
						305 151
						292 128
						269 128
						257 123
						257 112
						250 104
						221 99
						183 99
						150 99
						150 115
						130 115
						120 121
						86 121
						72 113
						56 113
						56 121
						72 121
						75 128
						75 133
						8 165
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						154 104
						238 104
						238 129
						154 129
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						177 173
						177 133
						277 133
						281 151
						277 170
						263 173
						239 173
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						174 133
						174 173
						80 173
						68 146
						81 133
					yourself:
				)
		)
		(sam
			init:
			stopUpd:
			approachVerbs: V_MONEY V_DO V_TALK
			setScript: sSamMotions
		)
		(= saloonMusic 673)
		(shieldTable ignoreActors: stopUpd: init:)
		(pokerTable ignoreActors: stopUpd: init:)
		(bottles init:)
		(spinner init:)
		(stage init:)
		(backDoor init: stopUpd: approachVerbs: V_DO)
		(chandelier2 init:)
		(poolTable init: setOnMeCheck: ftrControl cBLUE)
		(roulette init: setOnMeCheck: ftrControl cGREEN)
		(diceGame init: setOnMeCheck: ftrControl cCYAN)
		(moose init: setOnMeCheck: ftrControl cRED)
		(hatRack init: setOnMeCheck: ftrControl cMAGENTA)
		(table1 init: setOnMeCheck: ftrControl cGREY)
		(table2 init: setOnMeCheck: ftrControl cLGREY)
		(blackJack init: setOnMeCheck: ftrControl cLBLUE)
		(piano init: setOnMeCheck: ftrControl cLGREEN)
		(chandelier init: setOnMeCheck: ftrControl cLCYAN)
		(painting2 init: setOnMeCheck: ftrControl cLRED)
		(littlePic init: setOnMeCheck: ftrControl cWHITE)
		(bar init: setOnMeCheck: ftrControl cLMAGENTA)
		(rail init: setOnMeCheck: ftrControl cYELLOW)
		(sittingCowboy1
			ignoreActors:
			init:
			stopUpd:
			approachVerbs: V_DO V_TALK
		)
		(sittingCowboy2
			ignoreActors:
			init:
			stopUpd:
			approachVerbs: V_DO V_TALK
		)
		(sittingCowboy3
			ignoreActors:
			init:
			stopUpd:
			approachVerbs: V_DO V_TALK
		)
		(pianoPlayer setCycle: RandCycle init: approachVerbs: V_DO V_TALK)
		(doc init: approachVerbs: V_DO V_TALK setScript: sDrinkWhiskey)
		(whiskey init:)
		(painting1 init: approachVerbs: V_TALK V_DO setOnMeCheck: ftrControl cBROWN)
		(theGame handsOn:)
	)
	
	(method (doit)
		(cond 
			((== (curRoom script?) (ScriptID FPROOM 0)))
			((== (curRoom script?) sExitSouth))
			((== (ego edgeHit?) 3)
				(curRoom setScript: sExitSouth)
			)
		)
		(super doit:)
	)
)

(instance sDrinkWhiskey of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 7 15)))
			(1
				(whiskey hide:)
				(doc setCycle: EndLoop self)
			)
			(2
				(whiskey show:)
				(doc setCel: 0)
				(self changeState: 0)
			)
		)
	)
)

(instance sExitSouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setLoop: 2 setMotion: MoveTo (ego x?) 290 self)
			)
			(1
				(curRoom newRoom: 230)
			)
		)
	)
)

(instance sSamMotions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (sam setCycle: EndLoop self))
			(1 (= seconds (Random 1 4)))
			(2 (sam setCycle: BegLoop self))
			(3 (= seconds (Random 1 4)))
			(4
				(if (Random 0 1)
					(sam setLoop: 1 setCel: 0 setCycle: CycleTo 5 1 self)
				else
					(= cycles 1)
				)
			)
			(5
				(if (== (sam cel?) 5)
					(sam setLoop: 0 setCel: 0 setCycle: Forward)
					(= seconds (Random 4 7))
				else
					(= cycles 1)
				)
			)
			(6
				(if (== (sam loop?) 0)
					(sam setLoop: 1 setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(7 (self changeState: 0))
			(8 (self dispose:))
		)
	)
)

(instance whiskey of View
	(properties
		x 224
		y 143
		z 22
		noun N_WHISKEY
		view 670
		loop 1
		priority 12
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: N_DOC V_DO C_TAKE_GLASS)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chandelier2 of Actor
	(properties
		x 221
		y 135
		z 137
		noun N_CHANDELIER2
		view 670
		loop 2
		priority 13
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance pianoPlayer of Prop
	(properties
		x 57
		y 85
		noun N_PIANO_PLAYER
		approachX 69
		approachY 113
		view 673
		loop 2
		priority 6
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (init)
		(super init:)
		(if (== prevRoomNum 230)
			(theMusic1 fade: 127 10 16 0)
		else
			(theMusic1 number: saloonMusic flags: mNOPAUSE loop: -1 play:)
		)
	)
)

(instance sam of Prop
	(properties
		x 14
		y 98
		noun N_SAM
		approachX 45
		approachY 150
		view 673
		loop 1
		priority 9
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_MONEY
				(theIconBar disable:)
				(theGame points: 5 0 self)
				(ego put: iMoney)
				(ego get: -1 iBeer)
			)
			(V_TALK
				(switch samTalkCount
					(0
						(messager say: noun theVerb C_TALK1)
						(++ samTalkCount)
					)
					(1
						(messager say: noun theVerb C_TALK2)
						(++ samTalkCount)
					)
					(2
						(messager say: noun theVerb C_TALK3)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(theIconBar enable:)
		(messager say: noun V_MONEY)
	)
)

(instance doc of Prop
	(properties
		x 193
		y 140
		z 13
		noun N_DOC
		approachX 224
		approachY 143
		view 671
		priority 11
		signal (| ignrAct skipCheck fixPriOn)
		cycleSpeed 10
	)
	
	(method (init)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(messager say: noun theVerb C_TALK1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance backDoor of View
	(properties
		x 213
		y 89
		noun N_BACK_DOOR
		approachX 221
		approachY 101
		view 670
	)
)

(instance sittingCowboy1 of View
	(properties
		x 180
		y 103
		noun N_SITTING_COWBOYS
		approachX 246
		approachY 119
		view 684
		priority 8
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance sittingCowboy2 of View
	(properties
		x 207
		y 95
		noun N_SITTING_COWBOYS
		approachX 246
		approachY 119
		view 684
		loop 1
		priority 8
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance sittingCowboy3 of View
	(properties
		x 169
		y 93
		noun N_SITTING_COWBOYS
		approachX 246
		approachY 119
		view 684
		loop 2
		priority 8
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance shieldTable of Prop
	(properties
		x 120
		y 129
		noun N_SHIELD_TABLE
		view 678
		loop 5
		priority 11
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (init)
		(super init:)
		(chair1 ignoreActors: init: stopUpd:)
		(chair2 ignoreActors: init: stopUpd:)
		(chair3 ignoreActors: init: stopUpd:)
		(chair4 ignoreActors: init: stopUpd:)
	)
)

(instance pokerTable of View
	(properties
		x 228
		y 129
		noun N_POKER_TABLE
		approachX 232
		approachY 174
		view 678
		loop 5
		priority 11
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (init)
		(super init:)
		(pChair1 ignoreActors: init: stopUpd:)
		(pChair2 ignoreActors: init: stopUpd:)
		(pChair3 ignoreActors: init: stopUpd:)
		(pChair4 ignoreActors: init: stopUpd:)
	)
)

(instance pChair1 of View
	(properties
		x 200
		y 127
		noun N_CHAIR
		view 678
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance pChair2 of View
	(properties
		x 249
		y 127
		noun N_CHAIR
		view 678
		loop 1
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance pChair3 of View
	(properties
		x 256
		y 148
		noun N_CHAIR
		view 678
		loop 2
		priority 12
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance pChair4 of View
	(properties
		x 205
		y 148
		noun N_CHAIR
		view 678
		loop 3
		priority 12
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance chair1 of View
	(properties
		x 101
		y 127
		noun N_CHAIR
		view 678
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance chair2 of View
	(properties
		x 147
		y 127
		noun N_CHAIR
		view 678
		loop 1
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance chair3 of View
	(properties
		x 147
		y 150
		noun N_CHAIR
		view 678
		loop 2
		priority 12
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance chair4 of View
	(properties
		x 98
		y 151
		noun N_CHAIR
		view 678
		loop 3
		priority 12
		signal (| ignrAct skipCheck fixPriOn)
	)
)

(instance bottles of Feature
	(properties
		x 12
		y 86
		noun N_BOTTLES
		nsTop 72
		nsBottom 100
		nsRight 24
	)
)

(instance spinner of Feature
	(properties
		x 309
		y 71
		noun N_SPINNER
		nsTop 49
		nsLeft 299
		nsBottom 94
		nsRight 319
	)
)

(instance stage of Feature
	(properties
		x 127
		y 45
		noun N_STAGE
		nsTop 6
		nsLeft 50
		nsBottom 85
		nsRight 205
	)
)

(instance poolTable of Feature
	(properties
		x 155
		y 175
		noun N_POOL_TABLE
	)
)

(instance roulette of Feature
	(properties
		x 285
		y 179
		noun N_ROULETTE
	)
)

(instance diceGame of Feature
	(properties
		x 310
		y 150
		noun N_DICE_GAME
	)
)

(instance moose of Feature
	(properties
		x 289
		y 200
		noun N_MOOSE
	)
)

(instance hatRack of Feature
	(properties
		x 245
		y 72
		noun N_HAT_RACK
	)
)

(instance painting1 of Feature
	(properties
		x 223
		y 31
		noun N_PAINTING
		approachX 221
		approachY 101
	)
)

(instance table1 of Feature
	(properties
		x 107
		y 89
		noun N_TABLE2
	)
)

(instance table2 of Feature
	(properties
		x 198
		y 98
		noun N_TABLE1
	)
)

(instance blackJack of Feature
	(properties
		x 281
		y 103
		noun N_BLACKJACK
	)
)

(instance piano of Feature
	(properties
		x 47
		y 81
		noun N_PIANO
	)
)

(instance chandelier of Feature
	(properties
		x 105
		y 100
		noun N_CHANDELIER
	)
)

(instance painting2 of Feature
	(properties
		x 7
		y 39
		noun N_PAINTING2
	)
)

(instance littlePic of Feature
	(properties
		x 32
		y 38
		noun N_LITTLE_PIC
	)
)

(instance bar of Feature
	(properties
		x 26
		y 120
		noun N_BAR
	)
)

(instance rail of Feature
	(properties
		x 26
		y 120
		noun N_RAIL
	)
)
