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
	tail
	centaurCued
	local2
	sleepNearStall
	[local4 2]
	hildeTellMainBranch = [
		STARTTELL
		-16		;C_NAME
		-11		;C_MARKET
		-22		;C_VEGETABLES
		-8		;C_BRIGANDS
		ENDTELL
		]
	hildeTell1 = [
		STARTTELL
		C_FARMER
		C_FATHER
		C_FARM
		C_MOTHER
		C_DATE
		ENDTELL
		]
	hildeTell2 = [
		STARTTELL
		C_FARMER
		C_FATHER
		C_FARM
		C_MOTHER
		ENDTELL
		]
	hildeTell3 = [
		STARTTELL
		C_APPLES
		C_CARROTS
		C_RUTABAGAS
		C_PARSNIPS
		C_TURNIPS
		ENDTELL
		]
	hildeTell4 = [
		STARTTELL
		C_ROBBERY
		C_SHERIFF
		ENDTELL
		]
	[hildeTellTree 8]
	hildeTellKeys = [
		STARTTELL
		-16		;C_NAME
		-11		;C_MARKET
		-22		;C_VEGETABLES
		-8		;C_BRIGANDS
		ENDTELL
		]
)

(instance rm320 of Room
	(properties
		noun N_ROOM
		picture 320
	)
	
	(method (init)
		(self setRegions: STREET TOWN)
		(= [hildeTellTree 0] @hildeTellMainBranch)
		(= [hildeTellTree 1] @hildeTell1)
		(= [hildeTellTree 2] @hildeTell2)
		(= [hildeTellTree 3] @hildeTell3)
		(= [hildeTellTree 4] @hildeTell4)
		(= [hildeTellTree 5] ENDTELL)
		(if (== prevRoomNum 330)
			(= style SCROLLLEFT)
		else
			(= style HSHUTTER)
		)
		(super init:)
		(ego init: setScript: 0)
		(hildeTeller init: centaur @hildeTellMainBranch @hildeTellTree @hildeTellKeys)
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
			((= tail (Prop new:))
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
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						298 189
						159 174
						160 154
						124 151
						102 164
						48 158
						59 141
						80 140
						80 136
						43 136
						42 140
						55 141
						43 157
						0 157
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						109 187
						109 189
						0 189
						0 187
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
		;UPGRADE
;;;		(onBarrels init:)
;;;		(sky init:)
;;;		(leftFence init:)
;;;		(rightFence init:)
;;;		(roundStones init:)
;;;		(squareStones init:)
;;;		(aWindow init:)
;;;		(rtWindow init:)
;;;		(onSheriffHouse init:)
;;;		(onStand init:)
;;;		(onStable init:)
;;;		(onGoodsStore init:)
		
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
		(if (not (ego script?))
			(HandsOn)
		)
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
			((and (not (ego inRect: 210 130 319 185)) centaurCued)
				(= centaurCued FALSE)
			)
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
			(V_LOOK
				(messager say: N_ROOM NULL NULL 1)
			)
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
				(messager say: N_ROOM NULL NULL 5)
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
			(V_LOOK
				(messager say: N_STONES NULL NULL 1)
			)
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
			(V_LOOK
				(messager say: N_STONES NULL NULL 2)
			)
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
					(messager say: N_STAND NULL C_NIGHT)
				else
					(messager say: N_STAND NULL NULL 1)
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
		facingLoop loopN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(messager say: N_DOOR NULL NULL 3)
				else
					(messager say: N_DOOR NULL NULL 2)
				)
			)
			(V_DO
				(if Night
					(messager say: N_DOOR NULL C_NIGHT 1)	;Changed to the right message.
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
			(V_DAGGER
				(messager say: N_DOOR V_DAGGER NULL 1)
			)
			(V_BRASSKEY
				(messager say: N_DOOR V_BRASSKEY NULL 1)
			)
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
		facingLoop loopN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (< timeODay TIME_SUNSET)
					(messager say: N_DOOR NULL C_NIGHT 4)
				else
					(messager say: N_DOOR NULL C_NIGHT 3)
				)
			)
			(V_DO
				(if (< timeODay TIME_SUNSET)
					(messager say: N_DOOR NULL C_NIGHT 2)
				else
					(messager say: N_DOOR NULL NULL 1)
				)
			)
			(V_LOCKPICK
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_THIEFKIT
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_DAGGER
				(messager say: N_DOOR V_DAGGER NULL 1)
			)
			(V_BRASSKEY
				(messager say: N_DOOR V_BRASSKEY NULL 1)
			)
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
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_CENTAUR NULL NULL 2)
			)
			(V_DO (messager say: N_CENTAUR V_DO))
			(V_TALK
				(SolvePuzzle f320TalkToHilde 1)
				(super doVerb: theVerb &rest)
			)
			(V_MONEY
				((= wareList (List new:))
					add:
						((Clone Ware) name: {Apples} price: {1})
						((Clone Ware) name: {Vegetables} price: {1})
				)
				(switch ((ScriptID WARE 0) doit:)
					(noFunds
						(messager say: N_ROOM NULL C_NOFUNDS)
					)
					(buyNothing
						;Added in an unused message
						(messager say: N_CENTAUR NULL C_BUYNOTHING)
					)
					(buyApples
						(SolvePuzzle f320BuyApples 3)
						(ego setScript: buyFruit)
					)
					(buyVegetables
						(SolvePuzzle f320BuyApples 3)
						(ego setScript: buyVeg)
					)
				)
			)
			(V_FLOWERS
				(messager say: N_CENTAUR V_FLOWERS)
			)
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
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (Random 40 100))
			)
			(1
				(tail setCycle: EndLoop)
				(= state -1)
			)
		)
	)
)

(instance centaurScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 3)
			)
			(1
				(if (not (Btst fBeenIn320))
					(User canControl: FALSE)
					(messager say: N_ROOM NULL C_FIRSTENTRY 0 self)
				else
					(= cycles 2)
				)
			)
			(2
				(if (not (Btst fBeenIn320))
					(messager say: N_CENTAUR NULL NULL 3 self)
					(User canControl: TRUE)
					(= centaurCued 1)
				else
					(= ticks 30)
				)
			)
			(3
				(if (not (ego script?))
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoWakes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 7)
			)
			(1
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo)
				(messager say: N_ROOM NULL NULL 11 self)
			)
			(3
				(if sleepNearStall
					(messager say: N_ROOM NULL C_OUTOFSTALL 17 self)
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
			(5
				(self dispose:)
			)
		)
	)
)

(instance to330 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(1
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance backToTown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 275 self)
			)
			(1
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance comeIn of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 45 174 self)
			)
			(1
				(= seconds 1)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enterToStore of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(storeDoor setCycle: EndLoop self)
			)
			(1
				(ego setMotion: PolyPath 167 140 self)
			)
			(2
				(curRoom newRoom: 322)
			)
		)
	)
)

(instance inToSheriff of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sheriffDoor setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 89 120 self)
			)
			(2
				(curRoom newRoom: 321)
			)
		)
	)
)

(instance outOfStore of Script
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
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance comeFromTown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 250 184 self)
			)
			(1
				(= seconds 1)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance buyFruit of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM NULL C_BUYFRUIT 1 self)
			)
			(1
				(HandsOn)
				(ego get: iFruit 10)
				(= ticks 30)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance buyVeg of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM NULL C_BUYVEGETABLES 1 self)
			)
			(1
				(HandsOn)
				(ego get: iVegetables 5)
				(= ticks 30)
			)
			(2
				(self dispose:)
			)
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
		(AssertPalette 1320)
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
