;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include game.sh) (include "320.shm")
(use Main)
(use Teller)
(use Ware)
(use Door)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm320 0
	hildeTalker 1
)

(enum -1
	noFunds
	buyNothing
	buyApples
	buyVegetables
)

(local
	newProp
	local1
	local2
	nearStand
	[local4 2]
	[local6 6] = [0 -16 -11 -22 -8 999]
	[local12 7] = [0 12 14 13 15 10 999]
	[local19 6] = [0 12 14 13 15 999]
	[local25 7] = [0 7 9 19 17 21 999]
	[local32 4] = [0 18 20 999]
	[local36 8]
	[local44 6] = [0 -16 -11 -22 -8 999]
)
(instance rm320 of Room
	(properties
		noun N_ROOM
		picture 320
	)
	
	(method (init)
		(self setRegions: STREET TOWN)
		(= [local36 0] @local6)
		(= [local36 1] @local12)
		(= [local36 2] @local19)
		(= [local36 3] @local25)
		(= [local36 4] @local32)
		(= [local36 5] 999)
		(if (== prevRoomNum 330) (= style 12) else (= style 0))
		(super init:)
		(ego init: setScript: 0)
		(hildeTeller init: centaur @local6 @local36 @local44)
		(if (not Night)
			(centaur
				setPri: 6
				cycleSpeed: 1
				init:
				actions: hildeTeller
				approachVerbs: V_DO V_MONEY
				stopUpd:
			)
			(centaurBody
				setPri: 3
				init:
				actions: hildeTeller
				stopUpd:
				approachVerbs: V_DO V_MONEY
				addToPic:
			)
			(theApples setPri: 4 init: stopUpd: addToPic:)
			(scales setPri: 11 init: stopUpd: addToPic:)
			((= newProp (Prop new:))
				view: 325
				loop: 1
				cel: 0
				posn: 281 133
				setPri: (centaurBody priority?)
				init:
				actions: hildeTeller
				cycleSpeed: 1
				stopUpd:
				setScript: tailScript
			)
		)
		(= yesNoTimer 0)
		(NormalEgo)
		(switch prevRoomNum
			(0
				(ego posn: 226 182 setMotion: MoveTo 250 184)
			)
			(330
				(ego x: 21 setCycle: Walk setScript: comeIn)
			)
			(300
				(NormalEgo)
				(ego posn: 275 250 setScript: comeFromTown)
			)
			(321
				(ego posn: 78 122 setMotion: PolyPath 40 170)
				(sheriffDoor close:)
			)
			(322
				(ego posn: 158 145 setScript: outOfStore)
			)
			(999
				(ego view: 515 setLoop: 3 cel: 5 setScript: egoWakes)
			)
			(else 
				(NormalEgo)
				(ego posn: 275 188 setMotion: MoveTo 250 184)
			)
		)
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
						298
						189
						159
						174
						160
						154
						124
						151
						102
						164
						48
						158
						59
						141
						80
						140
						80
						136
						43
						136
						42
						140
						55
						141
						43
						157
						0
						157
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 109 187 109 189 0 189 0 187
					yourself:
				)
		)
		(LoadMany RES_VIEW 515 320 322 325)
		(if (and Night [egoStats PICK])
			(Load RES_SOUND (SoundFX 35))
		)
		(features
			add:
				onBarrels
				sky
				leftFence
				rightFence
				roundStones
				squareStones
				aWindow
				rtWindow
				onSheriffHouse
				onStand
				onStable
				onGoodsStore
			eachElementDo: #init
		)
		(storeDoor
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
			locked: (if Night TRUE else FALSE)
		)
		(sheriffDoor
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
			locked: TRUE
		)
		(if (not (ego script?)) (HandsOn))
	)
	
	(method (doit)
		(cond 
			(
				(and
					(< (ego x?) 3)
					(not (== (ego script?) comeIn))
					(not (== (ego script?) to330))
				)
				(= exploringTown 0)
				(= style SCROLLRIGHT)
				(ego setScript: to330)
			)
			(
				(and
					(> (ego y?) 188)
					(not (== (ego script?) comeFromTown))
					(not (== (ego script?) backToTown))
					(not (== (centaur script?) centaurScript))
				)
				(= exploringTown 0)
				(ego setScript: backToTown)
			)
			((ego script?) 0)
			(
			(and (not (ego inRect: 210 130 319 185)) local1) (= local1 0))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn320)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_ROOM 0 0 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (notify param1)
		(cond 
			((!= param1 1))
			((not (sheriffDoor locked?))
				(self setScript: inToSheriff))
			((not (TrySkill PICK 50 lockPickBonus)
				)
				(messager say: N_ROOM NULL NULL 3)
			)
			((and (Btst fBeenIn321) (< daySheriffBreakIn Day))
				(messager say: N_ROOM NULL NULL 4)
			)
			(else
				(lockSound number: (SoundFX 35) init: play:)
				(messager say: N_ROOM 0 0 5)
				(sheriffDoor locked: FALSE)
				(self setScript: inToSheriff)
			)
		)
	)
)

(instance sky of Feature
	(properties
		x 279
		y 122
		z 112
		noun N_SKY
		nsLeft 240
		nsBottom 20
		nsRight 318
		sightAngle 40
	)
)

(instance leftFence of Feature
	(properties
		x 22
		y 144
		z 14
		noun N_FENCE
		nsTop 115
		nsLeft 1
		nsBottom 146
		nsRight 43
		sightAngle 40
	)
)

(instance rightFence of Feature
	(properties
		x 90
		y 149
		z 14
		noun N_FENCE
		nsTop 126
		nsLeft 70
		nsBottom 145
		nsRight 110
		sightAngle 40
	)
)

(instance roundStones of Feature
	(properties
		x 152
		y 151
		noun N_STONES
		nsTop 144
		nsLeft 137
		nsBottom 158
		nsRight 168
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_STONES 0 0 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance squareStones of Feature
	(properties
		x 56
		y 139
		noun N_STONES
		nsTop 132
		nsLeft 45
		nsBottom 147
		nsRight 68
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_STONES 0 0 2))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance aWindow of Feature
	(properties
		x 41
		y 121
		z 41
		noun N_WINDOW
		nsTop 67
		nsLeft 29
		nsBottom 94
		nsRight 53
		sightAngle 40
	)
)

(instance rtWindow of Feature
	(properties
		x 106
		y 127
		z 40
		noun N_WINDOW
		nsTop 74
		nsLeft 99
		nsBottom 100
		nsRight 114
		sightAngle 40
	)
)

(instance onGoodsStore of Feature
	(properties
		y 36
		noun N_DRYGOODS
		nsTop 9
		nsLeft 99
		nsBottom 135
		nsRight 212
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance onSheriffHouse of Feature
	(properties
		y 36
		noun N_SHERIFFHOUSE
		nsTop 3
		nsLeft 2
		nsBottom 114
		nsRight 95
		sightAngle 40
		onMeCheck cGREEN
	)
)

(instance onStable of Feature
	(properties
		x 278
		y 94
		noun N_STALL
		nsTop 36
		nsLeft 237
		nsBottom 153
		nsRight 319
		sightAngle 40
		onMeCheck cRED
	)
)

(instance onBarrels of Feature
	(properties
		x 247
		y 160
		noun N_BARRELS
		nsTop 135
		nsLeft 177
		nsBottom 185
		nsRight 317
		sightAngle 40
		onMeCheck cCYAN
	)
)

(instance onStand of Feature
	(properties
		noun N_STAND
		sightAngle 40
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (>= timeODay TIME_SUNSET)
					(messager say: N_STAND 0 C_NIGHT)
				else
					(messager say: N_STAND 0 0 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance storeDoor of Door
	(properties
		x 170
		y 146
		noun N_DOOR
		approachX 160
		approachY 156
		view 320
		priority 9
		entranceTo 322
		facingLoop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(messager say: N_DOOR 0 0 3)
				else
					(messager say: N_DOOR 0 0 2)
				)
			)
			(V_DO
				(if Night
					(messager say: N_DOOR 0 1 1)	;Changed to the right message.
				else
					(curRoom setScript: enterToStore)
				)
			)
			(V_LOCKPICK
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_THIEFKIT
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_DAGGER (messager say: N_DOOR V_DAGGER 0 1))
			(V_BRASSKEY (messager say: N_DOOR V_BRASSKEY 0 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sheriffDoor of Door
	(properties
		x 89
		y 127
		noun N_DOOR
		approachX 75
		approachY 136
		view 320
		loop 1
		priority 9
		entranceTo 321
		facingLoop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (< timeODay TIME_SUNSET)
					(messager say: N_DOOR 0 C_NIGHT 4)
				else
					(messager say: N_DOOR 0 C_NIGHT 3)
				)
			)
			(V_DO
				(if (< timeODay TIME_SUNSET)
					(messager say: N_DOOR 0 C_NIGHT 2)
				else
					(messager say: N_DOOR 0 0 1)
				)
			)
			(V_LOCKPICK
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_THIEFKIT
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_DAGGER (messager say: N_DOOR V_DAGGER 0 1))
			(V_BRASSKEY (messager say: N_DOOR V_BRASSKEY 0 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theApples of View
	(properties
		x 230
		y 159
		noun N_PRODUCE
		view 320
		loop 4
	)
)

(instance scales of View
	(properties
		x 233
		y 89
		noun N_SCALES
		view 320
		loop 5
	)
)

(instance centaurBody of Prop
	(properties
		x 272
		y 141
		noun N_CENTAUR
		approachX 228
		approachY 186
		view 325
	)
	
	(method (init)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(centaur doVerb: theVerb &rest)
	)
)

(instance hildeTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_CENTAUR 0 0 2))
			(V_DO (messager say: N_CENTAUR V_DO))
			(V_TALK
				(SolvePuzzle POINTS_TALKTOCENTAURGIRL 1)
				(super doVerb: theVerb &rest)
			)
			(V_MONEY
				((= wareList (List new:))
					add:
						((Clone Ware) name: {Apples} price: {1})
						((Clone Ware) name: {Vegetables} price: {1})
				)
				(switch ((ScriptID 551 0) doit:)
					(noFunds (messager say: N_ROOM 0 C_NOFUNDS))
					(buyNothing (messager say: N_CENTAUR 0 C_BUYNOTHING)) ;Added in an unused message
					(buyApples
						(SolvePuzzle POINTS_BUYAPPLES 3)
						(ego setScript: buyFruit)
					)
					(buyVegetables
						(SolvePuzzle POINTS_BUYAPPLES 3)
						(ego setScript: buyVeg)
					)
				)
			)
			(V_FLOWERS (messager say: N_CENTAUR V_FLOWERS))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance centaur of Prop
	(properties
		x 268
		y 136
		noun N_CENTAUR
		approachX 228
		approachY 186
		view 325
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setScript: centaurScript)
	)
)

(instance lockSound of Sound
	(properties
		number 35
	)
)

(instance tailScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (Random 40 100)))
			(1
				(newProp setCycle: EndLoop)
				(= state -1)
			)
		)
	)
)

(instance centaurScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 3))
			(1
				(if (not (Btst fBeenIn320))
					(User canControl: FALSE)
					(messager say: N_ROOM 0 C_FIRSTENTRY 0 self)
				else
					(= cycles 2)
				)
			)
			(2
				(if (not (Btst fBeenIn320))
					(messager say: N_CENTAUR 0 0 3 self)
					(User canControl: TRUE)
					(= local1 1)
				else
					(= ticks 30)
				)
			)
			(3
				(if (not (ego script?)) (HandsOn))
				(self dispose:)
			)
		)
	)
)

(instance egoWakes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 7))
			(1
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo)
				(messager say: N_ROOM 0 0 11 self)
			)
			(3
				(if nearStand
					(messager say: N_ROOM 0 C_OUTOFSTALL 17 self)
				else
					(self cue:)
				)
			)
			(4
				(HandsOn)
				((inventory at: iSilver) amount: 1)
				((inventory at: iGold) amount: 0)
				(ego use: iSilver 1)
				(= ticks 30)
			)
			(5 (self dispose:))
		)
	)
)

(instance to330 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(1 (curRoom newRoom: 330))
		)
	)
)

(instance backToTown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 275 self)
			)
			(1 (curRoom newRoom: 300))
		)
	)
)

(instance comeIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 45 174 self)
			)
			(1 (= seconds 1))
			(2
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterToStore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(storeDoor setCycle: EndLoop self)
			)
			(1
				(ego setMotion: PolyPath 167 140 self)
			)
			(2 (curRoom newRoom: 322))
		)
	)
)

(instance inToSheriff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sheriffDoor setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 89 120 self)
			)
			(2 (curRoom newRoom: 321))
		)
	)
)

(instance outOfStore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 158 170 self)
			)
			(1
				(storeDoor close:)
				(= ticks 30)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance comeFromTown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 250 184 self)
			)
			(1 (= seconds 1))
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance buyFruit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM 0 C_BUYFRUIT 1 self)
			)
			(1
				(HandsOn)
				(ego get: iFruit 10)
				(= ticks 30)
			)
			(2 (self dispose:))
		)
	)
)

(instance buyVeg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM 0 C_BUYVEGETABLES 1 self)
			)
			(1
				(HandsOn)
				(ego get: iVegetables 5)
				(= ticks 30)
			)
			(2 (self dispose:))
		)
	)
)

(instance hildeTalker of Talker
	(properties
		x 10
		y 10
		view 1320
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2320)
		(PalVary PALVARYTARGET 2320)
		(kernel_128 1320)
		(= font userFont)
		(super init: hildeBust hildeEye hildeMouth &rest)
	)
)

(instance hildeBust of Prop
	(properties
		view 1320
	)
)

(instance hildeMouth of Prop
	(properties
		nsTop 43
		nsLeft 41
		view 1320
		loop 1
	)
)

(instance hildeEye of Prop
	(properties
		nsTop 30
		nsLeft 36
		view 1320
		loop 2
	)
)
