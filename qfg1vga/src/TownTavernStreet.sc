;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include game.sh) (include "330.shm")
(use Main)
(use Door)
(use Procs)
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
	rm330 0
)

(local
	[local0 2]
	saveMoveSpeed
	saveCycleSpeed
	glintInAlley
	roomHandsOff
)
(instance rm330 of Room
	(properties
		picture 330
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						30 147
						0 159
						318 188
						319 189
						0 189
						0 0
						319 0
						319 147
						308 154
						249 149
						249 144
						242 144
						233 149
						145 143
						169 123
						156 125
						131 143
					yourself:
				)
		)
		(curRoom setRegions: STREET TOWN)
		(super init:)
		(LoadMany RES_VIEW 300 330 515)
		(if (Btst fBarThrownOut)
			(LoadMany RES_VIEW 503 338)
			(LoadMany RES_SOUND 9 66)
		)
		(rm330
			style:
				(switch prevRoomNum
					(0 WIPELEFT)
					(320 SCROLLRIGHT)
					(331 (| BLACKOUT IRISOUT))
					(332 (| BLACKOUT IRISOUT))
					(else  HSHUTTER)
				)
		)
		(if (Btst fBarDrunk)
			(Load RES_VIEW 503)
		)
		(features
			add:
				onPots
				onWorkDoor
				onAlley
				butcherWindow
				breadWindow
				onBakeryDoor
				onButcherDoor
				onWorkShop
				onTavern
				onButcherShop
				onBakery
			eachElementDo: #init
		)
		;UPGRADE
;;;		(onPots
;;;			init:
;;;			setOnMeCheck: ftrControl cLBLUE
;;;		)
;;;		(onAlley init:)
;;;		(butcherWindow init:)
;;;		(breadWindow init:)
;;;		(onWorkShop
;;;			init:
;;;			setOnMeCheck: ftrControl cGREEN
;;;		)
;;;		(onTavern
;;;			init:
;;;			setOnMeCheck: ftrControl cBLUE
;;;		)
;;;		(onButcherShop
;;;			init:
;;;			setOnMeCheck: ftrControl cCYAN
;;;		)
;;;		(onBakery
;;;			init:
;;;			setOnMeCheck: ftrControl cGREY
;;;		)

		(onBakeryDoor
;;;			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
;;;			setOnMeCheck: ftrControl cRED
		)
		(onButcherDoor
;;;			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
;;;			setOnMeCheck: ftrControl cMAGENTA
		)
		(onWorkDoor
;;;			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL
				V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM
				V_MANA V_MANDRAKE V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS
				V_PAPER V_RATIONS V_RING V_ROCK V_SEED V_SHIELD
				V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
				V_VASE V_VEGETABLES
		)
		(butcherSign
			init:
;;;			setOnMeCheck: ftrControl cBROWN
		)
		(bakerySign
			init:
;;;			setOnMeCheck: ftrControl cLGREY
		)
		(if (and (not (Btst fBarThrownOut)) (not (Btst fBarDrunk)))
			(NormalEgo)
			(ego init:)
		else
			(ego view: 503 init: hide:)
		)
		(if (not (Btst fBarThrownOut))
			(tDoor
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
		)
		(cond 
			((== prevRoomNum 320)
				(NormalEgo)
				(ego posn: 289 174)
			)
			((Btst fBarThrownOut)
				(ego view: 503 posn: 94 114)
			)
			((Btst fBarDrunk)
				(ego view: 503 posn: 62 146 setLoop: 2)
			)
			(else
				(NormalEgo)
				(ego posn: 269 170)
			)
		)
		(cond 
			((== prevRoomNum 0)
				(NormalEgo)
				(ego posn: 269 170 setMotion: MoveTo 300 174)
			)
			((== prevRoomNum 64)
				(ego posn: 63 168 setMotion: MoveTo 123 168)
			)
			(
				(and
					(or (== prevRoomNum 331) (== prevRoomNum 332))
					(= roomHandsOff TRUE)
				)
				(cond 
					((Btst fBarThrownOut)
						(ego setScript: kickOutScript)
					)
					((Btst fBarDrunk)
						(tDoor cel: 0 doorState: doorClosed)
						((inventory at: iGold) amount: 0)
						((inventory at: iSilver) amount: 1)
						(ego use: iSilver 1)
						(ego setScript: gotDrunkScript)
					)
					(else
						(ego loop: 2 posn: 88 133 setScript: outOfTavernScript)
					)
				)
			)
			((== prevRoomNum 320)
				(NormalEgo)
				(ego init: posn: 289 174 setMotion: MoveTo 280 174)
			)
			((== prevRoomNum SYSTEM)
				((inventory at: iGold) amount: 0)
				((inventory at: iSilver) amount: 1)
				(ego use: iSilver 1)
				(ego
					view: 951
					posn: 159 168
					setLoop: 1
					cel: 5
					init:
					setScript: 0
				)
				(curRoom setScript: egoWakes)
			)
			(else
				(ego loop: 2 posn: 145 137 setMotion: MoveTo 148 144)
			)
		)
	)
	
	(method (doit)
		(cond 
			((and Night (not glintInAlley))
				(= glintInAlley TRUE)
				(glint ignoreActors: TRUE cycleSpeed: 12 setCycle: Forward init:)
			)
			((not (>= timeODay TIME_SUNSET))
				(glint dispose:)
			)
		)
		(cond 
			(
				(or
					(== roomHandsOff TRUE)
					(== (ego script?) kickOutScript)
					(== (ego script?) gotDrunkScript)
					(== (ego script?) outOfTavernScript)
				)
				0
			)
			((< (ego y?) 132)
				(= roomHandsOff TRUE)
				(ego setScript: toTheAlley)
			)
			((> (ego x?) 310)
				(= roomHandsOff TRUE)
				(= style SCROLLLEFT)
				(ego setScript: to320)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn330)
		(cond 
			((Btst fBarThrownOut)
				(Bclr fBarThrownOut)
			)
			((Btst fBarDrunk)
				(Bclr fBarDrunk)
			)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM V_LOOK NULL 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (notify param1)
		(cond 
			((!= param1 1))
			((not (TrySkill PICK 10 lockPickBonus))
				(messager say: N_ROOM NULL C_PICK_FAIL)
			)
			(else (messager say: N_ROOM NULL C_PICK_SUCCESS)
				(ego setScript: enterToTavern)
			)
		)
	)
)

(instance onWorkDoor of Feature
	(properties
		x 12
		y 122
		noun N_WORKDOOR
		nsTop 100
		nsLeft 3
		nsBottom 145
		nsRight 22
		sightAngle 40
		approachX 32
		approachY 153
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_WORKDOOR V_LOOK)
			)
			(V_DO
				(if Night
					(messager say: N_WORKDOOR V_DO C_NIGHT)
				else
					(messager say: N_WORKDOOR V_DO)
				)
			)
			(V_LOCKPICK
				((ScriptID STREET 0) doVerb: theVerb) ;&rest removed to allow script to compile.
			)
			(V_THIEFKIT
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_DAGGER
				(messager say: N_WORKDOOR V_DAGGER)
			)
			(V_BRASSKEY
				(messager say: N_WORKDOOR V_BRASSKEY NULL 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onPots of Feature
	(properties
		y 36
		noun N_POTS
		onMeCheck cLBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_POTS V_LOOK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onWorkShop of Feature
	(properties
		y 36
		noun N_WORKSHOP
		nsTop 36
		nsBottom 148
		nsRight 48
		sightAngle 40
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(messager say: N_WORKSHOP V_LOOK C_NIGHT)
				else
					(messager say: N_WORKSHOP V_LOOK NULL 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onTavern of Feature
	(properties
		x 188
		y 1
		noun N_TAVERN
		nsLeft 47
		nsBottom 133
		nsRight 127
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance onAlley of Feature
	(properties
		x 140
		y 83
		noun N_ALLEY
		nsTop 29
		nsLeft 123
		nsBottom 137
		nsRight 158
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(messager say: N_ALLEY V_LOOK C_NIGHT)
				else
					(messager say: N_ALLEY V_LOOK NULL)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onButcherShop of Feature
	(properties
		x 186
		y 1
		noun N_BUTCHERSHOP
		nsTop -1
		nsLeft 155
		nsBottom 140
		nsRight 254
		sightAngle 40
		onMeCheck cCYAN
	)
)

(instance onButcherDoor of Feature
	(properties
		x 179
		y 109
		noun N_BUTCHERDOOR
		nsTop 75
		nsLeft 163
		nsBottom 143
		nsRight 196
		sightAngle 40
		onMeCheck cMAGENTA
		approachX 172
		approachY 151
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BUTCHERDOOR V_LOOK)
			)
			(V_DO
				(messager say: N_BUTCHERDOOR V_DO)
			)
			(V_LOCKPICK
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_THIEFKIT
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_DAGGER
				(messager say: N_BUTCHERDOOR V_DAGGER)
			)
			(V_BRASSKEY
				(messager say: N_BUTCHERDOOR V_BRASSKEY)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onBakery of Feature
	(properties
		x 302
		y 1
		noun N_BAKERY
		onMeCheck cGREY
	)
)

(instance onBakeryDoor of Feature
	(properties
		x 266
		y 141
		z 49
		noun N_BAKERYDOOR
		nsTop 77
		nsLeft 254
		nsBottom 107
		nsRight 278
		sightAngle 40
		onMeCheck cRED
		approachX 255
		approachY 151
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BAKERYDOOR V_LOOK)
			)
			(V_DO
				(messager say: N_BAKERYDOOR V_DO)
			)
			(V_LOCKPICK
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_THIEFKIT
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_DAGGER
				(messager say: N_BAKERYDOOR V_DAGGER)
			)
			(V_BRASSKEY
				(messager say: N_BAKERYDOOR V_BRASSKEY)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance butcherWindow of Feature
	(properties
		x 210
		y 142
		z 37
		noun N_BUTCHERWINDOW
		nsTop 90
		nsLeft 199
		nsBottom 121
		nsRight 222
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(messager say: N_BUTCHERWINDOW V_LOOK C_NIGHT)
				else
					(messager say: N_BUTCHERWINDOW V_LOOK)
				)
			)
			(V_DO (messager say: N_BUTCHERWINDOW V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance breadWindow of Feature
	(properties
		x 299
		y 146
		z 52
		noun N_BREADWINDOW
		nsTop 87
		nsLeft 293
		nsBottom 102
		nsRight 305
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (not Night)
					(messager say: N_BREADWINDOW V_LOOK)
				else
					(messager say: N_BREADWINDOW V_LOOK C_NIGHT)
				)
			)
			(V_DO
				(messager say: N_BREADWINDOW V_DO C_NIGHT)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance butcherSign of Feature
	(properties
		x 61
		y 93
		noun N_BUTCHERSIGN
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BUTCHERSIGN V_LOOK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bakerySign of Feature
	(properties
		x 264
		y 44
		noun N_BAKERYSIGN
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BAKERYSIGN V_LOOK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance glint of Prop
	(properties
		x 158
		y 136
		noun N_GLINT
		approachX 141
		approachY 140
		view 330
		loop 4
		priority 5
	)
)

(instance tDoor of Door
	(properties
		x 103
		y 133
		noun N_TAVERNDOOR
		approachX 88
		approachY 136
		view 330
		priority 9
		entranceTo 331
		doorControl 16384
		facingLoop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (> timeODay TIME_MIDNIGHT)
					(messager say: N_TAVERNDOOR V_LOOK C_NIGHT)
				else
					(messager say: N_TAVERNDOOR V_LOOK)
				)
			)
			(V_DO
				(if (> timeODay TIME_MIDNIGHT)
					(messager say: N_TAVERNDOOR V_DO C_NIGHT)
				else
					(ego setScript: enterToTavern)
				)
			)
			(V_LOCKPICK
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_THIEFKIT
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_DAGGER
				(messager say: N_TAVERNDOOR V_DAGGER C_NIGHT)
			)
			(V_BRASSKEY
				(messager say: N_TAVERNDOOR V_BRASSKEY C_NIGHT)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance kickOutScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tDoor init:)
				(ego
					setLoop: 2
					setCel: 0
					illegalBits: 0
					ignoreActors:
					setPri: 8
					show:
				)
				(self cue:)
			)
			(1
				(fallSound play:)
				(= saveMoveSpeed (ego moveSpeed?))
				(= saveCycleSpeed (ego cycleSpeed?))
				(= ticks 60)
			)
			(2
				(ego
					setPri: 11
					moveSpeed: 0
					cycleSpeed: 6
					setCycle: 0
					setMotion: JumpTo 74 146 self
				)
			)
			(3
				(fallSound number: 66 play: 90)
				(ego setCel: -1 setCycle: EndLoop self)
			)
			(4
				(ShakeScreen 5)
				(tDoor close: stopUpd:)
				(= seconds 2)
			)
			(5 (= seconds 5))
			(6
				(ego setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(7
				(if (not (TakeDamage 5))
					;This stops the Hero from dying
					(TakeDamage -5)
				)	
				(= roomHandsOff 0)
				(HandsOn)
				(NormalEgo)
				(ChangeGait MOVE_WALK)
				(ego
					moveSpeed: saveMoveSpeed
					cycleSpeed: saveCycleSpeed
					loop: 2
					cel: 2
				)
				(self dispose:)
			)
		)
	)
)

(instance gotDrunkScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveCycleSpeed (ego cycleSpeed?))
				(ego setCel: 3 illegalBits: 0 setPri: 8 show:)
				(= seconds 7)
			)
			(1
				(messager say: N_TAVERNDOOR V_BRASSKEY C_NIGHT 2 self)
			)
			(2
				(messager say: N_ROOM NULL NULL 0 self)
			)
			(3
				(ego
					setLoop: 4
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(4
				(= roomHandsOff FALSE)
				(ChangeGait MOVE_WALK)
				(ego cycleSpeed: saveCycleSpeed)
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoWakes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
			)
			(1
				(NormalEgo)
				(messager say: N_ROOM NULL NULL 4 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance to320 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 340 175 self)
			)
			(1
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance enterToTavern of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tDoor stopUpd:)
				(ego setMotion: MoveTo 93 134 self)
			)
			(1
				(ego setCycle: 0 setMotion: 0)
				(self cue:)
			)
			(2 (curRoom newRoom: 331))
		)
	)
)

(instance outOfTavernScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 30)
			)
			(1
				(ego setMotion: MoveTo 85 151 self)
			)
			(2
				(tDoor close:)
				(= roomHandsOff FALSE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance toTheAlley of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 1)
			)
			(1
				(if Night
					(curRoom newRoom: 334)
				else
					(curRoom newRoom: 333)
				)
			)
		)
	)
)

(instance fallSound of Sound
	(properties
		number 9
	)
)
