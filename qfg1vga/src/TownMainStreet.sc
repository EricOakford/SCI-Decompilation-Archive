;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include game.sh) (include "310.shm")
(use Main)
(use Door)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm310 0
)

(local
	local0
)
(instance rm310 of Room
	(properties
		noun N_ROOM
		picture 310
		style DISSOLVE
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 190
						0 190
						0 0
						319 0
						319 171
						278 157
						274 149
						260 144
						252 148
						235 140
						210 144
						194 121
						193 116
						210 111
						190 106
						180 106
						150 99
						136 104
						189 120
						200 149
						197 153
						125 129
						82 137
						82 150
						4 160
						4 187
						319 187
					yourself:
				)
		)
		(if (and (>= timeODay TIME_SUNSET) [egoStats PICK])
			(Load SOUND 35)
		)
		(Load VIEW 310)
		(Load VIEW 515)
		(super init:)
		(self setFeatures: onGuildSign magicShopSign theLock)
		;UPGRADE
;;;		(onGuildSign init:)
;;;		(magicShopSign init:)
;;;		(theLock init:)
		
		(self setRegions: STREET TOWN)
		(theIconBar enable:)
		(NormalEgo)
		(if (!= prevRoomNum 314)
			(ego
				illegalBits: (if (> timeODay TIME_SUNSET) cWHITE else -30720)
				init:
			)
		)
		(theEye
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL V_GREENFUR
				V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM V_MANA V_MANDRAKE
				V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS V_PAPER V_RATIONS V_RING
				V_ROCK V_SEED V_SHIELD V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE
				V_TROLLBEARD V_VASE V_VEGETABLES
			stopUpd:
		)
		(if (< timeODay TIME_SUNSET)
			(LOL init: cycleSpeed: 10 setCycle: Forward startUpd:)
		)
		(LOLDoor
			init:
			locked: TRUE
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL V_GREENFUR
				V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM V_MANA V_MANDRAKE
				V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS V_PAPER V_RATIONS V_RING
				V_ROCK V_SEED V_SHIELD V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE
				V_TROLLBEARD V_VASE V_VEGETABLES
		)
		(guildDoor
			init:
			approachVerbs:
				V_DO V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
				V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT V_GHOSTOIL V_GREENFUR
				V_HEALING V_BRASSKEY V_LEATHER V_LOCKPICK V_MAGICGEM V_MANA V_MANDRAKE
				V_MAGICMIRROR V_MUSHROOM V_MUSICBOX V_PEARLS V_PAPER V_RATIONS V_RING
				V_ROCK V_SEED V_SHIELD V_MONEY V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE
				V_TROLLBEARD V_VASE V_VEGETABLES
			locked: (if Night TRUE else FALSE)
		)
		(switch prevRoomNum
			(0
				(ego posn: 315 177 setMotion: MoveTo 305 170)
			)
			(300
				(curRoom setScript: enterFrom300)
			)
			(311
				(ego posn: 61 155 loop: loopE)
				(guildDoor close:)
			)
			(313
				(ego posn: 190 110)
				(LOLDoor close:)
			)
			(314
				(curRoom setScript: exitTheMagicShop)
			)
			(999
				(ego
					view: 515
					setLoop: 3
					cel: 5
					init:
					setScript: egoWakes
				)
			)
			(else
				(ego x: 160 y: 160)
			)
		)
	)
	
	(method (doit &tmp theControl)
		(super doit:)
		(= theControl (ego onControl: origin))
		(cond 
			(
				(and
					(> (ego x?) 310)
					(!= (curRoom script?) enterFrom300)
					(!= (curRoom script?) enter300)
				)
				(= exploringTown FALSE)
				(curRoom setScript: enter300)
			)
			((and (== theControl cLGREEN) (not (curRoom script?)))
				(curRoom setScript: enterMagicShop)
			)
			((and (not (curRoom script?)) (== theControl cLCYAN))
				(curRoom setScript: climbUpStairs)
			)
			((and (not (curRoom script?)) (== theControl cLRED))
				(curRoom setScript: climbDownStairs)
			)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn302)
		(super dispose:)
	)
	
	(method (notify param1)
		(cond 
			((!= param1 1))
			((not (LOLDoor locked?))
				(messager say: N_ROOM NULL C_DOORUNLOCKED)
			)
			((not (TrySkill PICK 30 lockPickBonus))
				(messager say: N_ROOM NULL C_PICKFAIL)
			)
			((and (Btst fBeenIn313) (< dayLOLBreakIn Day))
				(messager say: N_ROOM NULL C_DOORBARRED)
			)
			(else
				(lockSound number: (SoundFX 35) init: play:)
				(messager say: N_ROOM NULL C_PICKSUCCESS)
				(LOLDoor facingLoop: (ego loop?) locked: FALSE)
				(curRoom setScript: enterLOLLand)
			)
		)
	)
)

(instance guildDoor of Door
	(properties
		x 16
		y 150
		noun N_GUILDDOOR
		approachX 61
		approachY 155
		view 310
		loop 2
		priority 9
		entranceTo 311
		facingLoop 1
	)
	
	(method (init)
		(= nightPalette 1310)
		(PalVary PALVARYTARGET 1310)
		(AssertPalette 310)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if Night
					(messager say: N_GUILDDOOR V_LOOK C_NIGHT)
				else
					(messager say: N_GUILDDOOR V_LOOK C_DAY)
				)
			)
			(V_DO
				(if Night
					(messager say: N_GUILDDOOR V_DO C_NIGHT)
				else
					(messager say: N_GUILDDOOR V_DO C_DAY)
					(curRoom setScript: enterToGuildHall)
				)
			)
			(V_LOCKPICK
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_THIEFKIT
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_BRASSKEY
				(messager say: N_GUILDDOOR V_BRASSKEY NULL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theLock of Feature
	(properties
		noun N_LOCK
		nsTop 76
		nsLeft 193
		nsBottom 89
		nsRight 208
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_LOCK V_LOOK NULL)
			)
			(else 
				(LOLDoor doVerb: theVerb &rest)
			)
		)
	)
)

(instance LOLDoor of Door
	(properties
		x 217
		y 109
		noun N_LOLDOOR
		approachX 190
		approachY 110
		view 310
		loop 1
		priority 4
		signal fixPriOn
		entranceTo 313
		facingLoop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (< timeODay TIME_SUNSET)
					(messager say: N_LOLDOOR V_LOOK C_DAY)
				else
					(messager say: N_LOLDOOR V_LOOK C_NIGHT)
				)
			)
			(V_DO
				(cond 
					((< timeODay TIME_SUNSET)
						(messager say: N_LOLDOOR V_DO C_DAY)
					)
					((== locked FALSE)
						(curRoom setScript: enterLOLLand)
					)
					(else
						(messager say: N_LOLDOOR V_DO C_NIGHT)
					)
				)
			)
			(V_LOCKPICK
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_THIEFKIT
				((ScriptID STREET 0) doVerb: theVerb)
			)
			(V_BRASSKEY
				(messager say: N_LOLDOOR V_BRASSKEY NULL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theEye of Prop
	(properties
		x 259
		y 47
		noun N_EYE
		view 310
		loop 3
		priority 10
		signal (| ignrAct fixPriOn)
		cycleSpeed 10
	)
	
	(method (doit &tmp theCel temp1 egoX_2)
		(super doit: &rest)
		(= egoX_2 (ego x?))
		(= theCel cel)
		(cond 
			((<= egoX_2 200)
				(= temp1 0)
			)
			((<= egoX_2 260)
				(= temp1 1)
			)
			((<= egoX_2 280)
				(= temp1 2)
			)
			((<= egoX_2 300)
				(= temp1 3)
			)
			((<= egoX_2 310)
				(= temp1 4)
			)
			(else
				(= temp1 5)
			)
		)
		(if (!= theCel temp1)
			(self cel: temp1 forceUpd:)
		)
	)
)

(instance LOL of Prop
	(properties
		x 163
		y 114
		noun N_LOL
		view 310
		loop 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(messager say: N_LOL V_ALTTALK NULL)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance magicShopSign of Feature
	(properties
		x 260
		y 145
		z 96
		noun N_MAGICSHOPSIGN
		nsTop 37
		nsLeft 252
		nsBottom 61
		nsRight 268
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onGuildSign of Feature
	(properties
		x 90
		y 133
		z 36
		noun N_GUILDSIGN
		nsTop 85
		nsLeft 75
		nsBottom 109
		nsRight 106
	)
)

(instance lockSound of Sound
	(properties
		number 35
	)
)

(instance enterToGuildHall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guildDoor setCycle: EndLoop self)
			)
			(1
				(ego setMotion: MoveTo 41 130 self)
			)
			(2
				(HandsOn)
				(curRoom newRoom: 311)
			)
		)
	)
)

(instance enterMagicShop of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 266 146 self)
			)
			(1
				(ego setHeading: 45 setMotion: MoveTo 272 142 self)
			)
			(2
				(HandsOn)
				(curRoom newRoom: 314)
			)
		)
	)
)

(instance exitTheMagicShop of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(HandsOff)
				(ego
					init:
					show:
					illegalBits: (if (> timeODay TIME_SUNSET) cWHITE else -30720)
					posn: 256 155
					heading: 225
					setMotion: MoveTo 245 161 self
				)
			)
			(1
				1
				(HandsOn)
				(NormalEgo)
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
				(messager say: N_ROOM NULL C_SLEPTINSTREET)
				((inventory at: iGold) amount: 0)
				((inventory at: iSilver) amount: 0)
				(HandsOn)
			)
		)
	)
)

(instance enterLOLLand of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(LOLDoor setCycle: EndLoop self)
			)
			(1
				(HandsOn)
				(curRoom newRoom: 313)
			)
		)
	)
)

(instance climbUpStairs of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 201 137 self)
			)
			(1
				(ego setMotion: PolyPath 191 117 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance climbDownStairs of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 201 137 self)
			)
			(1
				(ego setMotion: MoveTo 206 146 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance enter300 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 350 180 self)
			)
			(1
				(HandsOn)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance enterFrom300 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 350 177 setMotion: MoveTo 280 170 self)
			)
			(1
				(HandsOn)
				(client setScript: 0)
				(self dispose:)
			)
		)
	)
)
