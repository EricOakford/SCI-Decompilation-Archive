;;; Sierra Script 1.0 - (do not remove this comment)
(script# 322)
(include game.sh) (include "322.shm")
(use Main)
(use Teller)
(use Ware)
(use Procs)
(use Talker)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm322 0
	shopKeeperTalker 1
)

(local
	nearProprietor
	local1
	local2
	local3
	[local4 2]
	local6
	storeCue
	[local8 8] = [0 14 -6 -10 8 15 13 999]
	[local16 3] = [0 7 999]
	[local19 6] = [0 9 5 12 11 999]
	[local25 10]
	[local35 4] = [0 -6 -10 999]
)

(enum -1
	noFunds
	buyNothing
	buyAFlask
	buyRations
	buyADagger
	buyChainmail
)

(instance rm322 of Room
	(properties
		noun N_ROOM
		picture 322
		style DISSOLVE
	)
	
	(method (init)
		(= [local25 0] @local8)
		(= [local25 1] @local16)
		(= [local25 2] @local19)
		(= [local25 3] 999)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						302 189
						265 168
						248 167
						223 158
						219 150
						61 150
						65 167
						47 169
						41 174
						44 181
						45 189
						0 189
					yourself:
				)
		)
		(Load RES_VIEW 322)
		(super init:)
		(self
			setFeatures:
				woodenPole
				onBarrels
				onTeaPot
				onPole
				onWindow
				onSword
				onShield
				onEquipment
				onOddsNEnds
				onDrygoods
				onClothes1
				onClothes2
				onSack
				onTorch1
				onTorch2
				onRolledUpItems
				onCape
		)
		;UPGRADE
;;;		(woodenPole init:)
;;;		(onBarrels init:)
;;;		(onTeaPot init:)
;;;		(onPole init:)
;;;		(onWindow init:)
;;;		(onSword init:)
;;;		(onShield init:)
;;;		(onEquipment init:)
;;;		(onOddsNEnds init:)
;;;		(onDrygoods init:)
;;;		(onClothes1 init:)
;;;		(onClothes2 init:)
;;;		(onSack init:)
;;;		(onTorch1 init:)
;;;		(onTorch2 init:
;;;		(onRolledUpItems init:)
;;;		(onCape init:)
		
		(if Night
			(theWindow init:)
		)
		(self setRegions: TOWN)
		(cSound priority: 0 number: 93 loop: -1 play:)
		(shopTeller init: proprietor @local8 @local25 @local35)
		(proprietor actions: shopTeller init:)
		(stove init: setCycle: Forward)
		(ego loop: 3 posn: 163 188 init: setScript: enterTheShop)
	)
	
	(method (doit)
		(cond 
			((and (<= (ego y?) 155) (not nearProprietor)) (= nearProprietor 1))
			((and (> (ego y?) 155) nearProprietor)
				(= nearProprietor FALSE)
				(if local2 (= local2 0))
				(if (and local1 local3)
					(proprietor setCycle: BegLoop)
					(= local3 0)
				)
			)
			((and (> (ego y?) 187) (not (ego script?))) (ego setScript: outTo320))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset VISITED_STORE)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_ROOM V_LOOK C_LOOKCOUNTER))
			(V_MONEY (messager say: N_ROOM V_MONEY))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(switch storeCue
			(1 (messager say: N_SHOPKEEPER V_MONEY C_BUYSOMETHING))
		)
	)
)

(instance woodenPole of Feature
	(properties
		x 305
		y 161
		z 26
		noun N_TOOTHPICKS
		nsTop 114
		nsLeft 294
		nsBottom 156
		nsRight 316
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onPole of Feature
	(properties
		x 42
		y 133
		noun N_POLE
		nsTop 103
		nsLeft 32
		nsBottom 163
		nsRight 53
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onTeaPot of Feature
	(properties
		x 123
		y 151
		z 38
		noun N_TEAPOT
		nsTop 108
		nsLeft 118
		nsBottom 119
		nsRight 129
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onBarrels of Feature
	(properties
		x 260
		y 139
		noun N_BARRELS
		nsTop 127
		nsLeft 233
		nsBottom 151
		nsRight 288
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onWindow of Feature
	(properties
		x 266
		y 153
		z 79
		noun N_WINDOW
		nsTop 61
		nsLeft 245
		nsBottom 87
		nsRight 287
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(messager say: N_WINDOW V_LOOK C_NIGHT)	;A special night message, but the store is closed at night.
				else
					(messager say: N_WINDOW V_LOOK)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onSword of Feature
	(properties
		x 42
		y 143
		z 70
		noun N_SWORD
		nsTop 55
		nsLeft 38
		nsBottom 92
		nsRight 47
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onShield of Feature
	(properties
		x 116
		y 149
		z 104
		noun N_SHIELD
		nsTop 33
		nsLeft 88
		nsBottom 57
		nsRight 145
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onEquipment of Feature
	(properties
		x 176
		y 144
		z 64
		noun N_EQUIPMENT
		nsTop 59
		nsLeft 161
		nsBottom 101
		nsRight 192
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onOddsNEnds of Feature
	(properties
		x 84
		y 131
		z 45
		noun N_ODDSNENDS
		nsTop 58
		nsLeft 64
		nsBottom 115
		nsRight 105
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onDrygoods of Feature
	(properties
		x 132
		y 102
		noun N_DRYGOODS
		nsTop 58
		nsLeft 107
		nsBottom 102
		nsRight 158
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onTorch1 of Feature
	(properties
		x 82
		y 121
		z 70
		nsTop 46
		nsLeft 73
		nsBottom 56
		nsRight 91
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(onDrygoods doVerb: theVerb &rest)
	)
)

(instance onTorch2 of Feature
	(properties
		x 174
		y 124
		z 72
		nsTop 47
		nsLeft 159
		nsBottom 57
		nsRight 189
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(onDrygoods doVerb: theVerb &rest)
	)
)

(instance onSack of Feature
	(properties
		x 21
		y 160
		noun N_SACK
		nsTop 138
		nsLeft 11
		nsBottom 183
		nsRight 32
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onClothes1 of Feature
	(properties
		x 13
		y 162
		z 78
		noun N_CLOTHES1
		nsTop 56
		nsBottom 113
		nsRight 26
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onClothes2 of Feature
	(properties
		x 307
		y 166
		z 84
		noun N_CLOTHES2
		nsTop 58
		nsLeft 295
		nsBottom 107
		nsRight 319
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onRolledUpItems of Feature
	(properties
		x 221
		y 134
		z 41
		noun N_DOOR
		nsTop 71
		nsLeft 200
		nsBottom 115
		nsRight 242
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onCape of Feature
	(properties
		x 193
		y 130
		noun N_CAPE
		nsTop 116
		nsLeft 176
		nsBottom 145
		nsRight 211
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (messager say: N_ROOM V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theWindow of View
	(properties
		x 288
		y 89
		noun N_WINDOW
		view 322
		loop 6
		signal $0001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(messager say: N_WINDOW V_LOOK C_NIGHT)
				else
					(messager say: N_WINDOW V_LOOK)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stove of Prop
	(properties
		x 306
		y 166
		noun N_STOVE
		view 322
	)
	
	(method (init)
		(= nightPalette 1322)
		(PalVary PALVARYTARGET 1322)
		(kernel_128 322)
		(super init:)
	)
)

(instance proprietor of Prop
	(properties
		x 151
		y 119
		noun N_SHOPKEEPER
		view 322
		loop 4
	)
	
	(method (init)
		(PalVary PALVARYTARGET 1322)
		(kernel_128 322)
		(super init:)
	)
	
	(method (doit)
		(if (and nearProprietor local1 (not local3))
			(self setCycle: EndLoop)
			(= local3 1)
		)
		(super doit:)
	)
)

(instance shopTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(messager say: N_SHOPKEEPER V_LOOK)
					(return TRUE)
				)
				(V_TALK
					(if (not nearProprietor)
						(messager say: N_SHOPKEEPER V_TALK C_CANTHEARYOU) ;Originally mapped to LOOK verb by mistake. Changed to TALK Verb.
					else
						(= local6 1)
						(SolvePuzzle POINTS_TALKTOSHOPKEEPER 1)
						(super doVerb: theVerb &rest)
					)
					(return TRUE)
				)
				(V_MONEY
					((= wareList (List new:))
						add:
							((Clone Ware) name: {Flask} price: {2})
							((Clone Ware) name: {Food} price: {5})
							((Clone Ware) name: {Dagger} price: {20})
							((Clone Ware) name: {Armor} price: {500})
					)
					(switch ((ScriptID 551 0) doit:)
						(noFunds (messager say: N_SHOPKEEPER V_MONEY C_NOFUNDS))
						(buyAFlask
							(Bset PURCHASED_FROM_DRYGOODS)
							(ego setScript: buyFlask)
						)
						(buyRations
							(Bset PURCHASED_FROM_DRYGOODS)
							(ego setScript: buyFood)
						)
						(buyADagger
							(Bset PURCHASED_FROM_DRYGOODS)
							(ego setScript: buyDagger)
						)
						(buyChainmail
							(SolvePuzzle POINTS_BUYCHAINMAIL 3 FIGHTER)
							(Bset PURCHASED_FROM_DRYGOODS)
							(ego setScript: buyArmor)
						)
					)
					(return TRUE)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance buyFlask of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_SHOPKEEPER V_MONEY C_BUYSOMETHING 1 self)
			)
			(1
				(HandsOn)
				(ego get: iFlask 1)
				(self cue:)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance buyFood of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_SHOPKEEPER V_MONEY C_BUYSOMETHING 1 self)
			)
			(1
				(HandsOn)
				(ego get: iRations 10)
				(self cue:)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance buyDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_SHOPKEEPER V_MONEY C_BUYSOMETHING 1 self)
			)
			(1
				(HandsOn)
				(ego get: iDagger 1)
				(self cue:)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance buyArmor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego has: iLeather)
					(messager say: N_SHOPKEEPER V_MONEY C_TRADEINLEATHER 1 self)
				else
					(self cue:)
				)
			)
			(1
				(if (ego has: iLeather)
					(ego use: iLeather)
					(ego get: iSilver 50)
					(= ticks 30)
				else
					(self cue:)
				)
			)
			(2
				(messager say: N_SHOPKEEPER V_MONEY C_BUYSOMETHING 2 self)	;decided to put an unused message to good use.
			)
			(3
				(HandsOn)
				(ego get: iChainmail 1)
				(self cue:)
			)
			(4 (self dispose:))
		)
	)
)

(instance proprietorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proprietor cycleSpeed: 1 stopUpd:)
				(= ticks 60)
			)
			(1
				(proprietor setCycle: EndLoop self)
				(= local3 1)
			)
			(2
				(= local1 1)
				(= local2 1)
				(messager say: N_SHOPKEEPER 0 C_HITHERE 1 self)
			)
			(3 (Bset MET_SHOPKEEPER) (= ticks 10))
			(4
				(messager say: N_SHOPKEEPER 0 C_FIRSTMEET)
				(self cue:)
			)
			(5 (self cue:))
			(6 (self dispose:) (HandsOn))
		)
	)
)

(instance secondEntrance of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proprietor cycleSpeed: 1 stopUpd:)
				(= ticks 60)
			)
			(1
				(proprietor setCycle: EndLoop self)
				(= local3 1)
			)
			(2
				(= local1 1)
				(= local2 1)
				(messager say: N_SHOPKEEPER 0 C_HITHERE 1 self)
			)
			(3 (self dispose:) (HandsOn))
		)
	)
)

(instance enterTheShop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 163 155 self)
			)
			(1 (NormalEgo) (= cycles 6))
			(2
				(if (Btst VISITED_STORE)
					(self cue:)
				else
					(messager say: N_ROOM 0 C_FIRSTENTRY 1 self)
				)
			)
			(3
				(if (Btst VISITED_STORE)
					(ego setScript: secondEntrance)
				else
					(ego setScript: proprietorScript)
				)
			)
		)
	)
)

(instance outTo320 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 240 self)
			)
			(1 (curRoom newRoom: 320))
		)
	)
)

(instance shopKeeperTalker of Talker
	(properties
		x 10
		y 10
		view 1322
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2322)
		(PalVary PALVARYTARGET 2322)
		(kernel_128 1322)
		(= font userFont)
		(super init: shopBust shopEye shopMouth &rest)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance shopBust of Prop
	(properties
		view 1322
	)
)

(instance shopMouth of Prop
	(properties
		nsTop 50
		nsLeft 31
		view 1322
		loop 1
	)
)

(instance shopEye of Prop
	(properties
		nsTop 35
		nsLeft 30
		view 1322
		loop 2
	)
)
