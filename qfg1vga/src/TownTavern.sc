;;; Sierra Script 1.0 - (do not remove this comment)
(script# 331)
(include game.sh) (include "331.shm")
(use Main)
(use Teller)
(use Ware)
(use Procs)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm331 0
)

(enum 1
	noteSuspicious
	noteSuspiciousRead
	noteArchery
	noteArcheryRead
	noteNosy
	noteNosyRead
)

(enum	1
	drinkAle
	drinkSweat
	drinkBreath
)

(enum	-1
	noFunds
	buyNothing
	buyAle
	buySweat
	buyBreath
)

(local
	noteState
	local1
	local2
	local3
	drinkOrdered
	drinkConsumed
	numberOfAlesDrunk
	local7
	bartenderAttention
	tookNote
	local10
	crusherAnnoyed
	local12
	local13
	gEgoCycleSpeed
	gEgoMoveSpeed
	[local16 8] = [0 6 -23 27 25 22 -26 999]
	[local24 5] = [0 20 24 21 999]
	[local29 5]
	[local34 4] = [0 -23 999 3]
	[local38 6] = [0 3 4 6 5 999]
	[local44 8]
	[drinkConsumed2 2] = [0 999]
	[drinkConsumed4 6] = [0 34 4 6 5 999]
	[numberOfAlesDrunk0 8]
	[numberOfAlesDrunk8 2] = [0 999]
	[local70 12] = [0 22 40 41 25 46 47 44 42 43 45 999]
	[bartenderAttention2 4]
	[bartenderAttention6 2] = [0 999]
)
(instance rm331 of Room
	(properties
		noun 20
		picture 331
		style WIPELEFT
	)
	
	(method (init)
		(self setRegions: TOWN)
		(walkHandler add: self)
		(= [local29 0] @local16)
		(= [local29 1] @local24)
		(= [local29 2] 999)
		(= [local44 0] @local38)
		(= [local44 1] 999)
		(= [numberOfAlesDrunk0 0] @drinkConsumed4)
		(= [numberOfAlesDrunk0 1] 999)
		(= [bartenderAttention2 0] @local70)
		(= [bartenderAttention2 1] 999)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						0
						319
						0
						319
						189
						306
						189
						306
						162
						197
						162
						181
						146
						143
						146
						137
						152
						88
						152
						51
						189
						0
						189
					yourself:
				)
		)
		(LoadMany RES_SOUND 44 43)
		(barTune play:)
		(super init:)
		(= crusherAnnoyed 0)
		(= drinkConsumed 0)
		(if (>= timeODay TIME_SUNSET) (windowShudder init:))
		(barKeepTeller init: bartender @local16 @local29 @local34)
		(bartender
			init:
			actions: barKeepTeller
			setScript: bartenderScript
		)
		(crusherTeller init: crusher @local70 @bartenderAttention2 @bartenderAttention6)
		(crusher init: posn: 31 164 stopUpd:)
		(Bclr fTookBarNote)
		(cond 
			(
				(or
					(>= barNote noteNosyRead)
					(and (> barNote 0) (not (Btst fBearGone)))
				)
				(Bset fTookBarNote)
			)
			((Btst fBeatBrutus) (= barNote noteNosyRead) (Bset fTookBarNote))
			((and (Btst fSpiedOnThieves) (== barNote noteArchery)) (= barNote noteArcheryRead))
			((and (Btst fBearGone) (<= barNote noteSuspicious)) (= barNote noteSuspiciousRead))
			((== barNote noteArchery) (Bset fTookBarNote))
		)
		(curRoom
			setFeatures:
				onWindow
				onFloor
				onTableBottom
				onRtTableTop
				onLtTable
				onKeg1
				onKeg2
				onKeg3
				onKeg4
				onFloor
				onWholeKeg
				onBar
		)
		;UPGRADE
;;;		(onWindow init:)
;;;		(onFloor init:)
;;;		(onTableBottom init:)
;;;		(onRtTableTop init:)
;;;		(onLtTable init:)
;;;		(onKeg1 init:)
;;;		(onKeg2 init:)
;;;		(onKeg3 init:)
;;;		(onKeg4 init:)
;;;		(onFloor init:)
;;;		(onWholeKeg init:)
;;;		(onBar init:)
		
		(Bset fOrderedDrink)
		(Bclr fEgoSitting)
		(= drinkConsumed 0)
		(= drinkOrdered 0)
		(= numberOfAlesDrunk 0)
		(if (not (Btst fTookBarNote))
			(paper init: approachVerbs: V_DO stopUpd:)
		)
		(rtStool init:)
		(guck init:)
		(puddle init:)
		(barb setPri: 6 ignoreActors: addToPic: init:)
		(ctrStool
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
			ignoreActors:
		)
		(player1Teller init: player1 @drinkConsumed4 @numberOfAlesDrunk0 @numberOfAlesDrunk8)
		(player1 init: actions: player1Teller setCycle: Forward)
		(player2Teller init: player2 @local38 @local44 @drinkConsumed2)
		(player2 init: actions: player2Teller setCycle: Forward)
		(smoke init: setPri: 4 setCycle: Forward startUpd:)
		(trap init: setPri: 5)
		(caddy init:)
		(barSound number: 44 init:)
		(NormalEgo)
		(switch prevRoomNum
			(332
				(ooze init: setScript: oozeDrip)
				(ego init: posn: 120 180 loop: 0)
			)
			(else  (self setScript: sEnter))
		)
	)
	
	(method (doit)
		(cond 
			(script)
			((ego script?) 0)
			((== (ego edgeHit?) 3) (curRoom setScript: sExit))
		)
		(cond 
			(
			(and (ego inRect: 190 140 310 166) (not local10)) (= local10 1) (ego setScript: cardScript))
			(
			(and (not (ego inRect: 190 140 310 166)) local10) (= local10 0) (ego setScript: 0))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn331)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(if (Btst fEgoSitting)
					(ego setScript: standUpScript)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_LOOK
				(ego setScript: describeTavern)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(switch (++ noteState)
			(1
				(paper hide:)
				(Bset fTookBarNote)
				(= tookNote TRUE)
				(++ barNote)
				(SolvePuzzle POINTS_PICKUPNOTE 2)
				(messager say: N_PAPER V_DO 0 1 self)
			)
			(2
				(messager say: N_ROOM 0 C_READPAPER 1 self)
			)
			(3
				(cond 
					((== barNote noteSuspicious) (messager say: N_ROOM 0 76))
					((== barNote noteArchery) (messager say: N_ROOM 0 66))
					(else (messager say: N_ROOM 0 68) (= barNote noteNosyRead))
				)
				(= noteState NULL)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(DisposeScript 342)
		(super newRoom: newRoomNumber)
	)
)

(instance egoOnChair of Feature
	(properties
		x 65
		y 142
		z 100
		nsTop 91
		nsLeft 137
		nsBottom 142
		nsRight 167
	)
	
	(method (init)
		(super init: &rest)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (== (event message?) nullEvt))
				(== (theIconBar curIcon?) (theIconBar walkIconItem?))
				(& (event type?) direction)
			)
			(curRoom setScript: standUpScript)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		;Added more interactivity with the sitting Hero
		(switch theVerb
			(V_WALK
				(ego setScript: standUpScript)
			)
			(V_LOOK
				(messager say: N_EGOONCHAIR V_LOOK C_SITTINGDOWN)
			)
			(V_DO
				(messager say: N_EGOONCHAIR V_DO C_SITTINGDOWN)
			)
			(else
				(chair doVerb: theVerb &rest)
			)
		)
	)
)
(instance onWindow of Feature
	(properties
		x 23
		y 94
		noun N_WINDOW
		nsTop 50
		nsBottom 94
		nsRight 63
	)
)

(instance onBar of Feature
	(properties
		x 164
		y 107
		noun N_BAR
		nsTop 103
		nsLeft 70
		nsBottom 112
		nsRight 258
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoSitting) (head setCel: 1 stopUpd:))
				(messager say: N_BAR V_LOOK C_SITTINGDOWN)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onRtTableTop of Feature
	(properties
		x 243
		y 158
		z 27
		noun N_RTABLE
		nsTop 124
		nsLeft 229
		nsBottom 138
		nsRight 258
	)
)

(instance onLtTable of Feature
	(properties
		x 96
		y 134
		noun N_LTABLE
		nsTop 121
		nsLeft 68
		nsBottom 148
		nsRight 124
	)
)

(instance onTableBottom of Feature
	(properties
		x 250
		y 149
		noun N_TABLEBOTTOM
		nsTop 143
		nsLeft 235
		nsBottom 155
		nsRight 266
	)
)

(instance onKeg1 of Feature
	(properties
		x 97
		y 75
		noun N_KEG1
		nsTop 49
		nsLeft 83
		nsBottom 87
		nsRight 111
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_KEG1 V_LOOK))
			(V_DO
				(if bartenderAttention
					(cond 
						((not (Btst fEgoSitting)) (messager say: N_BARTENDER 0 16))
						((> drinkConsumed 0) (messager say: N_KEG3 0 C_BARINTERACTIONS))
						(else (messager say: N_BARTENDER 0 8 2) (= drinkOrdered 0))
					)
				else
					(messager say: N_KEG1 0 C_BARINTERACTIONS)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onKeg2 of Feature
	(properties
		x 165
		y 68
		noun N_KEG2
		nsTop 49
		nsLeft 149
		nsBottom 87
		nsRight 182
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_KEG2 V_LOOK))
			(V_DO
				(if bartenderAttention
					(cond 
						((not (Btst fEgoSitting)) (messager say: N_BARTENDER 0 16))
						((> drinkConsumed 0) (messager say: N_KEG3 0 C_BARINTERACTIONS))
						(else (messager say: N_BARTENDER 0 8 2) (= drinkOrdered 0))
					)
				else
					(messager say: N_KEG2 0 9)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onKeg3 of Feature
	(properties
		x 239
		y 73
		noun N_KEG3
		nsTop 49
		nsLeft 222
		nsBottom 86
		nsRight 256
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_KEG3 V_LOOK))
			(V_DO
				(if bartenderAttention
					(cond 
						((not (Btst fEgoSitting)) (messager say: N_BARTENDER 0 16))
						((> drinkConsumed 0) (messager say: N_KEG3 0 C_BARINTERACTIONS))
						(else (messager say: N_BARTENDER 0 8 2) (= drinkOrdered 0))
					)
				else
					(messager say: N_KEG3 0 C_BARINTERACTIONS)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onKeg4 of Feature
	(properties
		x 199
		y 82
		noun N_KEG4
		nsTop 77
		nsLeft 187
		nsBottom 96
		nsRight 211
	)
)

(instance onWholeKeg of Feature
	(properties
		x 166
		y 48
		noun N_KEGS
		nsTop 21
		nsLeft 64
		nsBottom 75
		nsRight 268
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoSitting) (head setCel: 2 stopUpd:))
				(messager say: N_KEGS V_LOOK)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onFloor of Feature
	(properties
		x 171
		y 169
		noun N_FLOOR
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if
				(and (ego inRect: 137 138 180 155) (not (Btst fTookBarNote)))
					(messager say: N_FLOOR V_LOOK C_SEEPAPER)
				else
					(messager say: N_FLOOR V_LOOK C_BARINTERACTIONS)
				)
			)
			(V_DO
				(if (Btst fEgoSitting)
					(ego setScript: standUpScript)
				else
					(messager say: N_FLOOR V_DO C_BARINTERACTIONS)
				)
			)
			(V_PAPER
				(if (not (Btst fEgoSitting))
					(ego setScript: putItBack)
				else
					(messager say: N_CRUSHER V_DO C_SITTINGDOWN)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance deadMug of View
	(properties
		x 168
		y 106
		noun N_MUG
		view 331
		cel 1
		priority 14
		signal $4010
	)
	;Added interactivity with the drink mug
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_MUG V_LOOK)
			)
			(V_DO
				(messager say: N_MUG V_DO)
			)
		)
	)
)

(instance ctrStool of View
	(properties
		x 151
		y 143
		noun N_EGOONCHAIR
		approachX 151
		approachY 155
		view 331
		loop 3
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoSitting)
					(messager say: N_EGOONCHAIR V_LOOK C_SITTINGDOWN)
				else
					(messager say: N_EGOONCHAIR V_LOOK C_BARINTERACTIONS)
				)
			)
			(V_DO
				(if (Btst fEgoSitting)
					(messager say: N_EGOONCHAIR V_DO C_SITTINGDOWN)
				else
					(ego setScript: sitDown)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance rtStool of View
	(properties
		x 197
		y 143
		noun N_RIGHTSTOOL
		view 331
		loop 3
		signal $4000
	)
)

(instance guck of View
	(properties
		x 191
		y 118
		noun N_GUCK
		view 331
		loop 3
		cel 3
		priority 11
		signal $4010
	)
)

(instance puddle of View
	(properties
		x 197
		y 144
		z 1
		noun N_GUCK
		view 331
		loop 3
		cel 2
		priority 10
		signal $4010
	)
)

(instance caddy of View
	(properties
		x 240
		y 126
		view 331
		loop 8
		priority 12
		signal $0011
	)
)

(instance windowShudder of View
	(properties
		x 23
		y 95
		noun N_WINDOW
		view 331
		loop 9
		signal $0001
	)
)

(instance barb of View
	(properties
		x 72
		y 240
		z 100
		noun N_BARBER
		view 331
		loop 3
		cel 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoSitting) (head setCel: 1 stopUpd:))
				(messager say: N_BARBER V_LOOK)
			)
			(V_TALK (messager say: N_BARBER V_TALK))
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance paper of View
	(properties
		x 137
		y 143
		noun N_PAPER
		approachX 137
		approachY 150
		view 331
		priority 11
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_PAPER V_LOOK C_SEEPAPER))
			(V_DO
				(if (not (Btst fEgoSitting))
					(ego get: iPaper)
					(= tookNote TRUE)
					(if local1
						(cond 
							((== barNote noteSuspicious) (messager say: N_ROOM 0 76))
							((== barNote noteArchery) (messager say: N_ROOM 0 66))
							(else (messager say: N_ROOM 0 68) (= barNote noteNosyRead))
						)
					else
						(curRoom cue:)
					)
				else
					(messager say: N_CRUSHER V_DO C_SITTINGDOWN)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance deck of View
	(properties
		x 240
		y 126
		view 331
		loop 8
		priority 12
		signal $4010
	)
)

(instance head of Prop
	(properties
		x 150
		y 100
		view 504
		loop 2
		cel 3
		priority 15
		signal $4010
		cycleSpeed 1
	)
)

(instance chair of Prop
	(properties
		x 34
		y 163
		view 338
		loop 2
		signal $4000
	)
)

(instance smoke of Prop
	(properties
		x 170
		y 139
		z 92
		noun N_SMOKE
		view 331
		loop 1
		signal $4000
		cycleSpeed 12
		detailLevel 2
	)
)

(instance ooze of Prop
	(properties
		x 191
		y 118
		view 331
		loop 4
		priority 11
		signal $4010
		detailLevel 2
	)
)

(instance trap of View
	(properties
		x 12
		y 163
		noun N_TRAPDOOR
		view 331
		loop 2
	)
)

(instance dB of Prop
	(properties
		view 337
		loop 2
		priority 3
		signal $4010
		cycleSpeed 12
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if bartenderAttention
					(cond 
						((not (Btst fEgoSitting)) (messager say: N_BARTENDER 0 16))
						((> drinkConsumed 0) (messager say: N_KEG3 0 C_BARINTERACTIONS))
						(else (messager say: N_BARTENDER 0 8 2) (= drinkOrdered 0))
					)
				else
					(messager say: N_KEG2 0 C_BARINTERACTIONS)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance player1 of Prop
	(properties
		x 226
		y 151
		noun N_BUTCHER
		view 331
		loop 5
		priority 12
		signal $0010
		cycleSpeed 40
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(self doVerb: 2)
	)
)

(instance player1Teller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoSitting) (head setCel: 2 stopUpd:))
				(messager say: N_BUTCHER V_LOOK)
				(return TRUE)
			)
			(V_TALK
				(if (and (!= (ego x?) 252) (!= (ego y?) 162))
					(if (not (Btst fEgoSitting))
						(ego setMotion: PolyPath 252 162 player1)
					)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance player2 of Prop
	(properties
		x 262
		y 148
		noun N_BAKER
		view 331
		loop 7
		cel 12
		priority 12
		signal $0010
		cycleSpeed 48
		detailLevel 2
	)
	
	(method (cue)
		(super cue:)
		(self doVerb: V_TALK)
	)
)

(instance player2Teller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoSitting) (head setCel: 2 stopUpd:))
				(messager say: N_BAKER V_LOOK)
				(return TRUE)
			)
			(V_TALK
				(if (and (!= (ego x?) 252) (!= (ego y?) 162))
					(if (not (Btst fEgoSitting))
						(ego setMotion: PolyPath 252 162 player2)
					)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance barKeepTeller of Teller
	(properties)
	
	(method (showDialog &tmp temp0)
		(if
			(==
				(= temp0 (super showDialog: -26 (== heroType THIEF)))
				-26
			)
			(= temp0 (Abs temp0))
		)
		(return temp0)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoSitting)
					(if (> (bartender x?) (ego x?))
						(head setCel: 2 stopUpd:)
					else
						(head setCel: 1 stopUpd:)
					)
				)
				(messager say: N_BARTENDER V_LOOK)
			)
			(V_TALK
				(if (Btst fEgoSitting)
					(bartender setScript: 0)
					(super doVerb: theVerb &rest)
				else
					(messager say: N_BARTENDER V_TALK C_ONBARTENDER)
				)
			)
			(V_DO
				(if bartenderAttention
					(if (== heroType THIEF)
						(messager say: N_BARTENDER V_DO C_ONBARTENDER)
					else
						(messager say: N_BARTENDER V_DO)
					)
				else
					(messager say: N_BARTENDER V_DO C_ONBARTENDER)
				)
			)
			(V_MONEY
				(if (Btst fEgoSitting)
					((= wareList (List new:))
						add:
							((Clone Ware) name: {Ale} price: {1})
							((Clone Ware) name: {Sweat} price: {5})
							((Clone Ware) name: {Breath} price: {25})
					)
					(switch ((ScriptID 551 0) doit:)
						(noFunds (messager say: N_BARTENDER V_MONEY 30))
						(buyAle
							(messager say: N_BARTENDER V_MONEY C_ORDERALE)
							(= drinkOrdered drinkAle)
							(bartender setScript: getAMug)
						)
						(buySweat
							(= drinkOrdered drinkSweat)
							(messager say: N_BARTENDER V_MONEY C_ORDERSWEAT)
							(bartender setScript: getAMug)
						)
						(buyBreath
							(messager say: N_BARTENDER V_MONEY C_ORDERBREATH)
							(= drinkOrdered drinkBreath)
							(bartender setScript: getAMug)
						)
					)
				else
					(messager say: N_BARTENDER V_MONEY C_BARINTERACTIONS)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance bartender of Actor
	(properties
		x 162
		y 102
		noun N_BARTENDER
		view 336
		loop 2
		priority 5
		signal $4010
	)
	
	(method (doit)
		(cond 
			(
			(and (< (ego y?) 113) (not (Btst fEgoSitting)) (not local7)) (= local7 1) (bartender setScript: askEgo))
			((and (> (ego y?) 113) local7) (= local7 0) (bartender setScript: bartenderScript))
			((or local7 (Btst fEgoSitting)) (= bartenderAttention 1))
			((and (not local7) (not (Btst fEgoSitting))) (= bartenderAttention 0))
		)
		(super doit: &rest)
	)
)

(instance crusher of Actor
	(properties
		noun N_CRUSHER
		approachX 63
		approachY 163
		view 331
		loop 6
	)
	
	(method (init)
		(= nightPalette 1331)
		(PalVary PALVARYTARGET 1331)
		(super init:)
	)
	
	(method (cue param1 &tmp egoPriority)
		(super cue:)
		(if param1
			(HandsOff)
			(cast eachElementDo: #stopUpd)
			(crusher view: 338 loop: 0 cycleSpeed: 18 setCycle: EndLoop)
			(if (<= (ego distanceTo: crusher) 25)
				(if
					(==
						(= egoPriority (ego priority?))
						(crusher priority?)
					)
					(++ egoPriority)
				)
				(ego
					setPri: egoPriority
					setLoop: 1
					setCycle: Reverse
					ignoreActors: 1
					setMotion: MoveTo 125 163 param1
				)
			else
				(param1 cue:)
			)
		)
	)
)

(instance crusherTeller of Teller
	(properties)
	
	(method (showDialog &tmp topic)
		(if
			(==
				(= topic
					(super
						showDialog:
							C_CRUSHER (== crusherAnnoyed 1)
							C_ANNOYED1 (== crusherAnnoyed 2)
							41
							(== crusherAnnoyed 3)
							25
							(== crusherAnnoyed 1)
							46
							(== crusherAnnoyed 2)
							47
							(== crusherAnnoyed 3)
							44
							(== crusherAnnoyed 1)
							42
							(== crusherAnnoyed 2)
							43
							(== crusherAnnoyed 3)
					)
				)
				41
			)
			(ego setScript: 0)
			(HandsOff)
			(crusher setScript: crusherThrows)
		)
		(if (== topic 47)
			(ego setScript: 0)
			(HandsOff)
			(crusher setScript: crusherThrows)
		)
		(if (== topic 43)
			(ego setScript: 0)
			(HandsOff)
			(crusher setScript: crusherThrows)
		)
		(if (== topic C_WHATSTHEPASSWORD) (curRoom setScript: talkToHeroScript))
		(return topic)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoSitting) (head setCel: 1 stopUpd:))
				(messager say: N_CRUSHER V_LOOK)
			)
			(V_DO
				(if (Btst fEgoSitting)
					(messager say: N_CRUSHER V_DO C_SITTINGDOWN)
				else
					(ego setScript: moveToCrusher)
				)
			)
			(V_TALK
				(if (Btst fEgoSitting)
					(messager say: N_CRUSHER V_DO C_SITTINGDOWN)
				else
					(if (< crusherAnnoyed 3) (++ crusherAnnoyed))
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance oozeDrip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ooze cycleSpeed: (* howFast 6) setCycle: CycleTo 7 1 self)
			)
			(1
				(dripSound play:)
				(ooze cycleSpeed: (* howFast 2) setCycle: EndLoop)
				(= cycles (Random 30 50))
			)
			(2 (self init:))
		)
	)
)

(instance sitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fEgoSitting)
				(HandsOff)
				(ego setMotion: MoveTo 162 (ego y?) self)
			)
			(1
				(ego setMotion: MoveTo 162 148 self)
			)
			(2
				(egoOnChair init:)
				(bartender ignoreActors:)
				(ctrStool
					x: 151
					y: 143
					signal: (| (ctrStool signal?) $4000)
				)
				(ego
					view: 504
					loop: 0
					setCel: 0
					signal: (| (ego signal?) $4000)
					setPri: 14
				)
				(self cue:)
			)
			(3
				(ego signal: (| (ego signal?) $4000) setCycle: EndLoop self)
			)
			(4
				(ego loop: 2 posn: 150 140 cel: 0 stopUpd:)
				(head init: setScript: headMove)
				(bartender setScript: askEgo)
				(= bartenderAttention 1)
				(= ticks 30)
			)
			(5 (self dispose:))
		)
	)
)

(instance standUpScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(egoOnChair dispose:)
				(head dispose: setScript: 0)
				(bartender ignoreActors:)
				(ego posn: 162 148 setLoop: 0 setCycle: BegLoop self)
			)
			(1
				(NormalEgo)
				(ego posn: 162 148 setMotion: MoveTo 162 160 self)
			)
			(2
				(Bclr fEgoSitting)
				(= bartenderAttention 0)
				(HandsOn)
				(bartender setScript: bartenderScript)
				(= ticks 6)
			)
			(3 (self dispose:))
		)
	)
)

(instance bartenderScript of Script
	(properties)
	
	(method (doit)
		(cond 
			((> local12 1)
				(-- local12)
				(if local13
					(bartender posn: (bartender x?) 102)
				else
					(bartender posn: (bartender x?) 102)
				)
			)
			((== local12 1)
				(= local12 0)
				(self cue:)
				(if local13 (= local13 0) else (= local13 1))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(= ticks (Random 20 80))
			)
			(1
				(= cycles 0)
				(bartender
					setCycle: Walk
					loop: 0
					setMotion: MoveTo 240 102 self
				)
			)
			(2
				(bartender loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(3 (= ticks (Random 150 300)))
			(4
				(bartender
					setCycle: Walk
					loop: 1
					cel: -1
					setMotion: MoveTo 105 102 self
				)
			)
			(5
				(bartender loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(6 (= ticks (Random 150 300)))
			(7 (self changeState: 1))
		)
	)
)

(instance askEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 0)
				(HandsOff)
				(if (> (bartender distanceTo: ego) 15)
					(bartender
						setCycle: Walk
						loop: (if (< (ego x?) (bartender x?)) 1 else 0)
						cel: -1
						setMotion:
							MoveTo
							(if (Btst fEgoSitting) (+ (ego x?) 15) else (ego x?))
							102
							self
					)
				else
					(self cue:)
				)
			)
			(1
				(bartender loop: 2 cel: 2 stopUpd:)
				(if (Btst fEgoSitting) (head setCel: 2))
				(= cycles 2)
			)
			(2
				(messager say: N_BARTENDER 0 C_WHATYAWANT)
				(HandsOn)
				(User canControl: FALSE)
				(self dispose:)
			)
		)
	)
)

(instance getAMug of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(deadMug hide:)
				(if (== (ego loop?) 3)
					(ego loop: 2 cel: 0 stopUpd:)
					(head show:)
				)
				(switch drinkOrdered
					(drinkSweat
						(bartender
							setLoop: (if (Btst fOrderedDrink) 1 else 6)
							setCycle: Walk
							ignoreActors:
							setMotion: MoveTo 96 102 self
						)
					)
					(else 
						(bartender
							setLoop: (if (Btst fOrderedDrink) 0 else 5)
							setCycle: Walk
							ignoreActors:
							setMotion: MoveTo 236 102 self
						)
					)
				)
			)
			(1
				(head hide:)
				(ego setLoop: 3 setCel: 0)
				(if (Btst fOrderedDrink)
					(bartender setLoop: 4 cel: 2 setPri: 5 setCycle: EndLoop self)
				else
					(self cue:)
				)
			)
			(2
				(Bclr fOrderedDrink)
				(switch drinkOrdered
					(drinkAle (self cue:))
					(drinkSweat (self cue:))
					(drinkBreath
						(User canInput: TRUE)
						(dB setScript: breathScript)
					)
				)
			)
			(3
				(bartender setLoop: 7 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(bartender
					posn: (if (== drinkOrdered drinkSweat) 96 else 236) 102
					setLoop: 3
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(5
				(bartender setCycle: BegLoop self)
			)
			(6
				(bartender
					setCycle: Walk
					setLoop: (if (== drinkOrdered drinkSweat) 5 else 6)
					setCel: -1
					setMotion: MoveTo 167 102 self
				)
			)
			(7
				(bartender setLoop: (if (== drinkOrdered drinkSweat) 0 else 1) cel: 0)
				(messager say: N_BARTENDER 0 13)
				(User canInput: TRUE)
				(= drinkConsumed drinkOrdered)
				(= drinkOrdered 0)
				(= ticks 36)
			)
			(8
				(if (Btst fEgoSitting)
					(ego loop: 3 cel: 0)
				)
				(bartender loop: 2 cel: 2 stopUpd:)
				(if 1 (++ numberOfAlesDrunk))
				(ego setScript: drinkDown)
			)
		)
	)
)

(instance breathScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bartender
					setLoop: 6
					setCycle: Walk
					setMotion: MoveTo 177 102 self
				)
			)
			(1
				(bartender
					view: 337
					setLoop: 0
					cel: 0
					posn: 164 102
					ignoreActors:
					setCycle: CycleTo 5 1 self
				)
			)
			(2
				(smoke hide:)
				(dB
					init:
					setLoop: 2
					posn: 166 86
					ignoreActors:
					setCycle: CycleTo 6 1 self
				)
			)
			(3
				(barSound number: 44 play:)
				(bartender setCycle: CycleTo 8 1 self)
				(dB setCycle: EndLoop self)
			)
			(4)
			(5
				(dB loop: 3 cel: 0 posn: 169 50)
				(bartender setCycle: EndLoop self)
			)
			(6
				(barSound number: 43 play:)
				(dB setCycle: EndLoop self)
			)
			(7
				(smoke show:)
				(bartender cel: 2 setCycle: BegLoop self)
			)
			(8
				(bartender view: 336 posn: 169 102 loop: 2 cel: 0)
				(self cue:)
			)
			(9
				(bartender setCycle: EndLoop self)
				(messager say: N_BARTENDER 0 C_THEREYOUGO)
			)
			(10
				(if (Btst fEgoSitting)
					(head hide:)
					(ego loop: 3 cel: 0)
				)
				(= drinkOrdered 0)
				(= drinkConsumed drinkBreath)
				(ego setScript: drinkDown)
				(self dispose:)
			)
		)
	)
)

(instance drinkDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: EndLoop self)
			)
			(1
				(switch drinkConsumed
					(drinkAle
						(switch numberOfAlesDrunk
							(1
								(messager say: N_ROOM 0 C_ALE1 1 self)
							)
							(2
								(messager say: N_ROOM 0 C_ALE2 1 self)
							)
							(3
								(messager say: N_ROOM 0 C_ALE3 1)
								(client setScript: tooDrunk)
							)
						)
					)
					(drinkSweat
						(messager say: N_ROOM 0 C_DRINKSWEAT)
						(client setScript: tooDrunk)
					)
					(drinkBreath
						(messager say: N_ROOM 0 C_DRINKBREATH 1 self)
					)
				)
			)
			(2
				(if (== drinkConsumed drinkBreath)
					(client setScript: breathDeath)
				else
					(ego setCycle: BegLoop self)
				)
			)
			(3
				(= drinkConsumed 0)
				(User canInput: TRUE)
				(deadMug init: show:)
				(if 1 (head show:) (ego loop: 2 cel: 0))
				(HandsOn)
				(User canControl: FALSE)
				(client setScript: 0)
			)
		)
	)
)

(instance tooDrunk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop)
				(= cycles 15)
			)
			(1
				(deadMug show:)
				(= ticks 18)
			)
			(2
				(switch drinkConsumed
					(drinkAle (messager say: N_ROOM 0 C_TOOMUCHALE))
					(drinkSweat (messager say: N_ROOM 0 C_SWEATPASSOUT))
				)
				(= ticks 18)
			)
			(3
				(ego loop: 6 cel: 0 posn: 152 155 setCycle: EndLoop self)
			)
			(4
				(bartenderScript dispose:)
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 -32761)
				(self cue:)
			)
			(5
				(SolvePuzzle POINTS_GOTDRUNK -5)
				(Bset fBarDrunk)
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance breathDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego moveSpeed: 6 cycleSpeed: 6)
				(= seconds 2)
			)
			(1
				(deadMug show:)
				(messager say: N_ROOM 0 C_BREATHDEATH 1 self)
				(ego loop: 5 cel: 0 posn: 151 136)
			)
			(2
				(barSound number: 44 play:)
				(ego cycleSpeed: (* howFast 3) setCycle: EndLoop self)
			)
			(3 (= seconds 2))
			(4 (EgoDead 140 141 2 3 337))
		)
	)
)

(instance crusherThrows of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(crusher cue: self)
			)
			(1
				(ego setCycle: Walk)
				(chair init: ignoreActors: setCycle: EndLoop)
				(crusher
					view: 338
					ignoreActors:
					loop: 1
					cel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(2
				(crusher cel: 2)
				(= cycles 3)
			)
			(3
				(barSound number: 106 loop: -1 play:)
				(crusher setCycle: Forward)
				(= seconds 3)
			)
			(4
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 -32762)
				(Bset fBarThrownOut)
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance crusherEscorts of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: Walk setMotion: PolyPath 50 175)
				(= ticks 30)
			)
			(1 (crusher cue: self))
			(2
				(chair init: ignoreActors: setCycle: EndLoop)
				(crusher
					ignoreActors:
					setLoop: 1
					setCel: 0
					setCycle: 0
					stopUpd:
				)
				(= ticks 180)
			)
			(3
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 -32762)
				(curRoom newRoom: 332)
			)
		)
	)
)

(instance moveToCrusher of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(= gEgoMoveSpeed (ego moveSpeed?))
				(ego cycleSpeed: 6 moveSpeed: 6)
				(= ticks 30)
			)
			(1
				(ego setMotion: MoveTo 79 165 self)
			)
			(2
				(messager say: N_ROOM 0 C_MOVETOCRUSHER 1 self)
			)
			(3
				(ego cycleSpeed: gEgoCycleSpeed moveSpeed: gEgoMoveSpeed)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance talkToHeroScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theIconBar disable: ICON_WALK ICON_TALK ICON_DO ICON_USEIT ICON_LOOK ICON_INVENTORY ICON_ACTIONS)
				(= seconds 1)
			)
			(1
				(if modelessDialog (modelessDialog dispose:))
				(theGame setCursor: ARROW_CURSOR TRUE 140 80)
				(if (Btst fLearnedThiefPassword)
					(switch
						(Print
							addText: 19 0 50 1 0 0 331
							addButton: 1 19 0 49 1 0 14 331
							addButton: 2 19 0 49 2 0 30 331
							addButton: 3 19 0 49 3 0 46 331
							addButton: 4 19 0 49 4 0 62 331
							addButton: 5 19 0 49 5 0 78 331
							addButton: 6 19 0 49 6 0 94 331
							init:
						)
						(1 (self cue:))
						(2 (self cue:))
						(3 (self cue:))
						(4 (self cue:))
						(5
							(if (and (not [egoStats STEALTH]) (not [egoStats PICK]))
								(messager say: N_ROOM 0 C_NOTATHIEF)
								(self setScript: crusherThrows)
							else
								(self setScript: crusherEscorts)
							)
						)
						(6 (self cue:))
					)
				else
					(Print
						addText: 19 0 50 1 0 0 331
						addButton: 1 19 0 49 1 0 14 331
						addButton: 2 19 0 49 2 0 30 331
						addButton: 3 19 0 49 3 0 46 331
						addButton: 4 19 0 49 4 0 62 331
						addButton: 5 19 0 49 7 0 78 331
						addButton: 6 19 0 49 6 0 94 331
						init:
					)
					(self cue:)
				)
			)
			(2
				(switch crusherAnnoyed
					(1
						(messager say: N_CRUSHER 0 C_CRUSHERIGNORES 1 self)
					)
					(2
						(messager say: N_CRUSHER 0 C_EYESDARKEN 1 self)
					)
					(3
						(messager say: N_CRUSHER 0 C_CRUSHERUPSET 1)
						(ego setScript: 0)
						(HandsOff)
						(self setScript: crusherThrows)
					)
				)
			)
			(3
				(User canControl: TRUE canInput: TRUE)
				(theGame setCursor: 943 1 140 80)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance headMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(head cel: (Random 1 3))
				(-- state)
				(= ticks 120)
			)
		)
	)
)

(instance describeTavern of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_ROOM V_LOOK C_DESCRIBETAVERN 0 self)
			)
			(1
				(if (not local2)
					(messager say: N_ROOM V_LOOK C_FIRSTENTRY 1 self)
					(= local2 1)
				else
					(self cue:)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance putItBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego use: iPaper)
				(= ticks 30)
				(HandsOff)
			)
			(1 (= ticks 60))
			(2
				(paper show:)
				(= tookNote FALSE)
				(Bclr fTookBarNote)
				(messager say: N_ROOM 0 C_PUTITBACK 1 self)
			)
			(3 (self dispose:) (HandsOn))
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(NormalEgo)
				(ego init: posn: 162 240 setMotion: MoveTo 156 175 self)
			)
			(1 (NormalEgo) (= cycles 2))
			(2
				(if (not (Btst fBeenIn331))
					(crusher setScript: describeTavern)
				)
				(HandsOn)
				(ooze init: setScript: oozeDrip)
				(self dispose:)
			)
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(barTune fade:)
				(ego setMotion: MoveTo (ego x?) 240 self)
			)
			(1 (curRoom newRoom: 330))
		)
	)
)

(instance cardScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 30] temp30)
		(switch (= state newState)
			(0 (= ticks (Random 250 400)))
			(1
				(Printf
					@temp0
					(Format @temp0 331 0 (= temp30 (Random 1 1000)))
				)
				(= ticks (Random 250 350))
			)
			(2
				(switch (Random 1 2)
					(1 (messager say: N_BAKER 0 C_GOFISH))
					(2 (messager say: N_BUTCHER 0 C_GOFISH))
				)
				(self init:)
			)
		)
	)
)

(instance dripSound of Sound
	(properties
		number 116
	)
)

(instance barSound of Sound
	(properties
		number 44
	)
)

(instance barTune of Sound
	(properties
		flags $ffff
		number 127
		loop -1
	)
)
