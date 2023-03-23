;;; Sierra Script 1.0 - (do not remove this comment)
(script# 83)
(include game.sh) (include "83.shm")
(use Main)
(use Sleep)
(use Teller)
(use Procs)
(use Print)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm83 0
	hermitTalker 1
)

(local
	sittingDown
	local1
	lastTicks
	local3
	local4
	hermitTellMainBranch = [
		STARTTELL
		C_NAME
		-9		;C_HERMIT
		C_FALLS
		-10		;C_LADDER
		-1		;C_BRIGANDS
		-3		;C_CRIBBAGE
		-16		;C_SLEEP
		ENDTELL
		]
	local14 = [
		STARTTELL
		C_CAVE
		C_FAMILY
		ENDTELL
		]
	local18 = [
		STARTTELL
		-4		;C_ERASMUS
		-17		;C_SPELLS
		ENDTELL
		]
	local22 = [
		STARTTELL
		-19		;C_WARLOCK
		ENDTELL
		]
	local25 = [
		STARTTELL
		C_FENRUS
		C_GAMES
		ENDTELL
		]
	local29 = [
		STARTTELL
		-18		;C_TRIGGER
		ENDTELL
		]
	local32 = [
		STARTTELL
		C_MIRROR
		ENDTELL
		]
	local35 = [
		STARTTELL
		-15		;C_SCROLL3
		-13		;C_SCROLL1
		-14		;C_SCROLL2
		ENDTELL
		]
	[hermitTellTree 10]
	hermitTellKeys = [
		STARTTELL
		-9		;C_HERMIT
		-10		;C_LADDER
		-1		;C_BRIGANDS
		-4		;C_ERASMUS
		-17		;C_SPELLS
		-19		;C_WARLOCK
		-18		;C_TRIGGER
		ENDTELL
		]
)
(instance rm83 of Room
	(properties
		noun N_ROOM
		picture 83
		style DISSOLVE
		horizon 80
	)
	
	(method (init)
		(walkHandler add: self)
		(= [hermitTellTree 0] @hermitTellMainBranch)
		(= [hermitTellTree 1] @local14)
		(= [hermitTellTree 2] @local18)
		(= [hermitTellTree 3] @local22)
		(= [hermitTellTree 4] @local25)
		(= [hermitTellTree 5] @local29)
		(= [hermitTellTree 6] @local32)
		(= [hermitTellTree 7] @local35)
		(= [hermitTellTree 8] ENDTELL)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 189
						0 189
						0 0
						96 0
						96 75
						84 103
						129 106
						158 118
						162 123
						158 138
						135 149
						162 154
						183 145
						249 142
						195 110
						150 108
						135 94
						137 77
						133 77
						134 0
						319 0
					yourself:
				)
		)
		(super init:)
		(NormalEgo)
		(Load RES_SOUND 41)
		(SolvePuzzle f83MeetHermit 5)
		(ego loop: loopS posn: 116 94 init:)
		(onSeat init: approachVerbs: V_DO)
		(features
			add:
				straw
				theFire
				pot1
				pot2
				pot3
				pot4
				pot5
				pot6
				pot7
				table
				chair
				cot
				bows
				water
				skull
		)
		;UPGRADE
;;;		(straw init:)
;;;		(theFire init:)
;;;		(pot1
;;;			init:
;;;			setOnMeCheck: ftrControl cCYAN
;;;		)
;;;		(pot2
;;;			init:
;;;			setOnMeCheck: ftrControl cLGREY
;;;		)
;;;		(pot3
;;;			init:
;;;			setOnMeCheck: ftrControl cGREY
;;;		)
;;;		(pot4
;;;			init:
;;;			setOnMeCheck: ftrControl cLBLUE
;;;		)
;;;		(pot5
;;;			init:
;;;			setOnMeCheck: ftrControl cLGREEN
;;;		)
;;;		(pot6
;;;			init:
;;;			setOnMeCheck: ftrControl cLCYAN
;;;		)
;;;		(pot7
;;;			init:
;;;			setOnMeCheck: ftrControl cLRED
;;;		)
;;;		(table init:)
;;;		(chair
;;;			init:
;;;			setOnMeCheck: ftrControl cBROWN
;;;		)
;;;		(cot
;;;			init:
;;;			setOnMeCheck: ftrControl cRED
;;;		)
;;;		(bows
;;;			init:
;;;			setOnMeCheck: ftrControl cMAGENTA
;;;		)
;;;		(water
;;;			init:
;;;			setOnMeCheck: ftrControl cGREEN
;;;		)
;;;		(skull init:)

		(glowCoals init: setPri: 15 setCycle: Forward)
		(waterFallSplash init: cycleSpeed: 6 setCycle: Forward)
		(caveDoor init:)
		(candle init: setPri: 14 cycleSpeed: 8 setCycle: Forward)
		(hermitTeller init: hermit @hermitTellMainBranch @hermitTellTree @hermitTellKeys)
		(hermit init: actions: hermitTeller setPri: 10)
		(theMusic number: 71 loop: -1 play: 90)
		(hermitSound init: play:)
		(pfSnd number: 28)
		(self setScript: introToHenry)
	)
	
	(method (doit)
		(if
			(and
				(not (Btst fCharSheetActive))
				(> (Abs (- gameTime lastTicks)) 2)
			)
			(= lastTicks gameTime)
			(Palette PALCycle 232 238 -1 239 244 -1 245 251 -1)
		)
		(super doit:)
		(cond 
			((or sittingDown isHandsOff)
				(if (User canControl:)
					(User canControl: FALSE)
				)
			)
			((not (User canControl:))
				(User canControl: TRUE)
			)
		)
		(return
			(if
				(and
					(& (ego onControl: origin) cBLUE)
					(!= (curRoom script?) doorScript)
					(!= (curRoom script?) sLeaving)
				)
				(if (not (Btst fBeenIn83))
					(self setScript: sLeaving)
				else
					(self setScript: doorScript)
				)
			else
				0
			)
		)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(hermitSound fade:)
		(walkHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(if sittingDown
					(ego setScript: standUp)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_LOOK
				(messager say: N_ROOM V_LOOK)
			)
			(V_DO
				(if sittingDown
					(ego setScript: standUp)
				else
					(messager say: N_ROOM V_DO)
				)
			)
			(V_OPEN
				(messager say: N_ROOM V_OPEN)
			)
			(V_DETECT
				(messager say: N_ROOM V_DETECT)
			)
			(V_SLEEP
				(cond 
					(sittingDown
						(messager say: N_ROOM V_SLEEP)
					)
					(
						(not
							(if (Btst fFedHenry)
							else
								(>= ((inventory at: iRations) amount?) 1)
							)
						)
						(curRoom setScript: sNoSleep)
					)
					((not (NeedSleep))
						(EgoSleeps 5)
					)
					(else
						(if (not (Btst fFedHenry))
							(ego use: iRations 1)
						)
						(Bclr fFedHenry)
						(curRoom setScript: sGoSleep)
					)
				)
			)
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(self setScript: TPego)
			)
			(V_TRIGGER
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(self setScript: TPego)
			)
;			(V_DETECT				;This instance already exists, and will probably never be executed.
;									;In fact, this SHOULDN'T be executed, since Detect is harmless.
;				(messager say: N_HERMIT V_DAGGER)
;				(Bset fDeadlyTP)
;				(self setScript: TPego)
;			)
			(V_DAZZLE
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(self setScript: TPego)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoOnChair of Feature
	(properties
		x 65
		y 142
		z 100
		nsTop 96
		nsLeft 130
		nsBottom 140
		nsRight 155
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
			(curRoom setScript: standUp)
			(event claimed: TRUE)
			(return)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_WALK)
			(ego setScript: standUp)
		else
			(chair doVerb: theVerb &rest)
		)
	)
)

(instance onSeat of Feature
	(properties
		x 134
		y 121
		noun N_SEAT
		nsTop 121
		nsLeft 132
		nsBottom 137
		nsRight 153
		approachX 152
		approachY 152
		_approachVerbs 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(if sittingDown
					(= sittingDown FALSE)
					(ego setScript: standUp)
				else
					(ego setScript: sitDown)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance table of Feature
	(properties
		x 96
		y 100
		noun N_TABLE
		nsTop 100
		nsLeft 92
		nsBottom 143
		nsRight 127
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_HERMIT V_DAGGER)
			(Bset fDeadlyTP)
			(curRoom setScript: TPego)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance skull of Feature
	(properties
		x 200
		y 100
		z 68
		noun N_SKULL
		nsTop 27
		nsLeft 196
		nsBottom 38
		nsRight 205
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_HERMIT V_DAGGER)
			(Bset fDeadlyTP)
			(curRoom setScript: TPego)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theFire of Feature
	(properties
		x 239
		y 148
		noun N_FIRE
		nsTop 142
		nsLeft 211
		nsBottom 155
		nsRight 267
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance straw of Feature
	(properties
		x 171
		y 140
		noun N_STRAW
		nsTop 134
		nsLeft 152
		nsBottom 146
		nsRight 190
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_HERMIT V_DAGGER)
			(Bset fDeadlyTP)
			(curRoom setScript: TPego)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance chair of Feature
	(properties
		x 159
		y 133
		noun N_CHAIR
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cot of Feature
	(properties
		x 227
		y 168
		noun N_COT
		onMeCheck cRED
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(messager say: N_SKULL V_DO)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance pot1 of Feature
	(properties
		x 154
		y 91
		noun N_POT1
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pot2 of Feature
	(properties
		x 154
		y 111
		noun N_POT2
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pot3 of Feature
	(properties
		x 154
		y 91
		noun N_POT3
		onMeCheck cGREY
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pot4 of Feature
	(properties
		x 154
		y 91
		noun N_POT4
		onMeCheck cLBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pot5 of Feature
	(properties
		x 154
		y 91
		noun N_POT5
		onMeCheck cLGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pot6 of Feature
	(properties
		x 154
		y 91
		noun N_POT6
		onMeCheck cLCYAN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pot7 of Feature
	(properties
		x 154
		y 91
		noun N_POT7
		onMeCheck cLRED
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bows of Feature
	(properties
		x 167
		y 45
		noun N_BOWS
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(messager say: N_SKULL V_DO)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance water of Feature
	(properties
		x 22
		y 36
		noun N_WATER
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_HERMIT V_DAGGER)
			(Bset fDeadlyTP)
			(curRoom setScript: TPego)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance niteOutside of Actor
	(properties
		x 117
		y 75
		view 83
		loop 9
	)
	
	(method (doVerb theVerb)
		(super doVerb: theVerb &rest)
	)
)

(instance caveDoor of Prop
	(properties
		x 144
		y 83
		noun N_CAVEDOOR
		view 83
		loop 8
		signal stopUpdOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					(sittingDown
						(messager say: N_CAVEDOOR V_DO)
					)
					((not (Btst fBeenIn83))
						(curRoom setScript: sLeaving)
					)
					(else
						(curRoom setScript: doorScript)
					)
				)
			)
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poof of Prop
	(properties
		view 83
		loop 5
	)
	
	(method (doVerb theVerb)
		(super doVerb: theVerb &rest)
	)
)

(instance scroll of Prop
	(properties
		x 114
		y 116
		noun N_SCROLL
		view 83
		loop 7
		cel 9
		priority 10
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_SCROLL V_LOOK)
			)
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(if (Btst fAskedForTrigger)
					(scroll dispose:)
					(messager say: N_SCROLL V_DO C_LEARNTRIGGER)
					(Bset fLearnedTrigger)
					(ego learn: TRIGGER 10)
					(SolvePuzzle f83LearnTrigger 4 MAGIC_USER)
				else
					(curRoom setScript: sAskScroll)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance candle of Prop
	(properties
		x 101
		y 124
		noun N_CANDLE
		view 83
		loop 4
		priority 14
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: N_CANDLE V_DO)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance hermit of Prop
	(properties
		x 78
		y 134
		noun N_HERMIT
		view 83
		loop 3
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance hermitTeller of Teller
	(method (showDialog &tmp temp0)
		(if
			(==
				(= temp0
					(super
						showDialog:
							-3 (Btst fBeenIn83)
							-16 (Btst fBeenIn83)
							-15 (if (not (Btst fLearnedTrigger)) (not (Btst fAskedForTrigger)) else 0)
							-13 (if (Btst fAskedForTrigger) (not (Btst fLearnedTrigger)))
							-14 (Btst fLearnedTrigger)
					)
				)
				-16
			)
			(= temp0 (Abs temp0))
		)
		(if (== temp0 -3)
			(= temp0 (Abs temp0))
		)
		(if (== temp0 -14)
			(= temp0 (Abs temp0))
		)
		(return temp0)
	)
	
	(method (doChild)
		(return
			(switch query
				(-3
					(return TRUE)
				)
				(-16
					(return TRUE)
				)
				(-15
					(curRoom setScript: sAskScroll)
				)
				(-13
					(curRoom setScript: sAskScroll)
				)
				(else
					(super doChild: query)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(SolvePuzzle f83TalkToHermit 2)
				(super doVerb: theVerb &rest)
			)
			(V_FLAME
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(curRoom setScript: TPego)
			)
			(V_DO
				(hermit setScript: sMisc)
			)
			(V_RATIONS
				(cond 
					(sittingDown
						(messager say: N_ROOM V_SLEEP C_STAND_UP_FIRST)
					)
					((and (Btst fFedHenry) Night)
						(ego setScript: sGoSleep)
					)
					((not (ego has: iRations 1))
						(messager say: N_HERMIT V_RATIONS C_NO_RATIONS)
					)
					(Night
						(messager say: N_HERMIT V_RATIONS C_GAVE_RATION)
						(Bset fFedHenry)
					)
					(else
						(messager say: N_HERMIT V_RATIONS C_DAY)
					)
				)
			)
			(V_DAGGER
				(if sittingDown
					(ego hide:)
				)
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(hermit setScript: TPego)
			)
			(V_ROCK
				(if sittingDown
					(ego hide:)
				)
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(hermit setScript: TPego)
			)
			(V_SWORD
				(if sittingDown
					(ego hide:)
				)
				(messager say: N_HERMIT V_DAGGER)
				(Bset fDeadlyTP)
				(hermit setScript: TPego)
			)
			(else 
				(if
					(OneOf theVerb
						V_ACORN V_CANDELABRA V_CANDLESTICKS V_CHEETAURCLAW V_DAGGER
						V_FAIRYDUST V_FLASK V_FLOWERS V_WATER V_FRUIT
						V_GHOSTOIL V_GREENFUR V_HEALING V_BRASSKEY V_LEATHER
						V_LOCKPICK V_MAGICGEM V_MANA V_MANDRAKE V_MAGICMIRROR
						V_MUSHROOM V_MUSICBOX V_PEARLS V_PAPER V_RATIONS
						V_RING V_ROCK V_SEED V_SHIELD V_MONEY
						V_VIGOR V_SWORD V_THIEFKIT V_THIEFLICENSE V_TROLLBEARD
						V_VASE V_VEGETABLES
					)
					(messager say: N_HERMIT V_ACORN)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
		(return TRUE)
	)
)

(instance glowCoals of Prop
	(properties
		x 238
		y 155
		noun N_GLOWCOALS
		view 83
		priority 12
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_HERMIT V_DAGGER)
			(Bset fDeadlyTP)
			(self setScript: TPego)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance waterFallSplash of Prop
	(properties
		x 41
		y 95
		noun N_WATER
		view 83
		loop 1
		priority 7
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(messager say: N_HERMIT V_DAGGER)
			(Bset fDeadlyTP)
			(self setScript: TPego)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance dummy of Script)

(instance introToHenry of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(NormalEgo)
				(= cycles 6)
			)
			(1
				(if (Btst fBeenIn83)
					(= state 4)
					(messager say: N_ROOM NULL C_HELLOAGAIN 1 self)
				else
					(messager say: N_ROOM NULL C_FIRSTMEET 1 self)
				)
			)
			(2
				(messager say: N_ROOM NULL C_HERMITINTRO1 1 self)
			)
			(3
				(messager say: N_ROOM NULL C_HERMITINTRO2 1 self)
			)
			(4
				(messager say: N_ROOM NULL C_HERMITINTRO3 1 self)
			)
			(5
				(self dispose:)
				(HandsOn)
			)
		)
	)
)

(instance sitDown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(egoOnChair init:)
				(self cue:)
			)
			(1
				(ego
					view: 504
					setLoop: 0
					setCel: 0
					ignoreActors: TRUE
					illegalBits: 0
					setCycle: EndLoop self
				)
				(= sittingDown TRUE)
			)
			(2
				(HandsOn)
				(ego stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance standUp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(egoOnChair dispose:)
				(= ticks 6)
			)
			(1
				(ego
					view: 504
					setLoop: 0
					setCel: 5
					startUpd:
					ignoreActors: TRUE
					illegalBits: 0
					setCycle: BegLoop self
				)
				(= sittingDown FALSE)
			)
			(2
				(NormalEgo)
				(ego setCycle: Walk setMotion: MoveTo 155 146 self)
			)
			(3
				(NormalEgo)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance TPego of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fBeenIn83)
				(HandsOff)
				(ego hide:)
				(poof
					posn: (ego x?) (ego y?)
					setCycle: CycleTo 6 1 self
					cycleSpeed: 4
					init:
				)
			)
			(1
				(ego dispose:)
				(pfSnd init: play:)
				(poof setCycle: EndLoop self)
			)
			(2
				(= cycles 10)
			)
			(3
				(poof dispose:)
				(curRoom newRoom: 82)
			)
		)
	)
)

(instance doorScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(caveDoor startUpd:)
				(if (and (== (ego x?) 80) (== (ego y?) 90))
					(self cue:)
				else
					(ego setMotion: MoveTo 80 90 self)
				)
				(if Night
					(niteOutside setPri: (- (caveDoor priority?) 1) init:)
				)
			)
			(1
				(caveDoor
					ignoreActors: TRUE
					setCycle: EndLoop self
				)
			)
			(2
				(ego
					setMotion: MoveTo (+ (ego x?) 30) (- (ego y?) 10) self
				)
			)
			(3
				(curRoom newRoom: 82)
			)
		)
	)
)

(instance sLeaving of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fBeenIn83)
				(HandsOff)
				(ego setMotion: PolyPath 109 89 self)
			)
			(1
				(messager say: N_ROOM NULL C_LEAVING 1 self)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance sGoSleep of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM NULL C_GOSLEEP 1 self)
			)
			(1
				(ego use: iRations 1)
				(ego illegalBits: 0 setMotion: MoveTo 204 134 self)
			)
			(2
				(ego view: 510 setLoop: 1 setCycle: EndLoop self)
			)
			(3
				(ego
					view: 523
					setLoop: 0
					setCel: 0
					posn: 191 153
					setPri: 13
					stopUpd:
				)
				(hermit setLoop: 3 setCycle: Forward)
				(= seconds 6)
			)
			(4
				(messager say: N_ROOM NULL C_WAKEUP 1 self)
				(Bset fBeenIn83)
			)
			(5
				(EgoSleeps 5)
				(ego
					view: 510
					posn: 204 134
					setLoop: 1
					setCycle: BegLoop self
				)
			)
			(6
				(NormalEgo)
				(ego setMotion: PolyPath 180 90 self)
			)
			(7
				(Face ego hermit self)
				(hermit setCycle: 0)
				(Bclr fFedHenry)
			)
			(8
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sNoSleep of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM NULL C_NO_SLEEP 1 self)
			)
			(1
				(Bset fSafeTP)
				(client setScript: TPego)
			)
		)
	)
)

(instance sAskScroll of Script
	(method (changeState newState)
		(if client
			(switch (= state newState)
				(0
					(HandsOff)
					(= seconds 3)
				)
				(1
					(cond 
						((Btst fAskedForTrigger)
							(client setScript: sNixScroll)
						)
						([egoStats MAGIC]
							(Bset fAskedForTrigger)
							(= local3 1)
							(= ticks 60)
						)
						(else
							(messager say: N_ROOM NULL C_NOMAGIC)
							(HandsOn)
							(client setScript: 0)
						)
					)
				)
				(2
					(switch
						(Print
							addText: N_ROOM NULL C_DOYOUWANTIT 1 0 0 83
							addButton: 1 N_ROOM NULL C_ANSWERHIM 1 0 30 83
							addButton: 2 N_ROOM NULL C_ANSWERHIM 2 0 48 83
							init:
						)
						(1
							(curRoom setScript: sGetScroll)
						)
						(2
							(curRoom setScript: sNoScroll)
						)
					)
				)
				(3
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance sNoScroll of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM NULL C_DECLINESCROLL 1 self)
			)
			(1
				(HandsOn)
			)
		)
	)
)

(instance sNixScroll of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fLearnedTrigger)
					(messager say: N_ROOM NULL C_ALREADYGOTSCROLL 1 self)
				else
					(self cue:)
				)
			)
			(1
				(if (Btst fLearnedTrigger)
					(HandsOn)
					(self dispose:)
				else
					(switch
						(Print
							addText: N_ROOM NULL C_DOYOUWANTIT 1 0 0 83
							addButton: 1 N_ROOM NULL C_ANSWERHIM 1 0 30 83
							addButton: 2 N_ROOM NULL C_ANSWERHIM 2 0 48 83
							init:
						)
						(1
							(curRoom setScript: sGetScroll)
						)
						(2
							(curRoom setScript: sNoScroll)
						)
					)
				)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sGetScroll of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== local4 1)
					(self changeState: 4)
				else
					(= local4 1)
					(messager say: N_ROOM NULL C_ACCEPTSCROLL 1 self)
				)
			)
			(1
				(messager say: N_ROOM NULL C_SUMMONSCROLL 1 self)
			)
			(2
				(scroll
					loop: 7
					setPri: 14
					init:
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(pfSnd init: play:)
			)
			(3
				(scroll setLoop: 2 setCel: 1)
				(messager say: N_ROOM NULL C_THEREYOUGO 1 self)
			)
			(4
				(HandsOn)
				(if sittingDown)
				(self dispose:)
			)
		)
	)
)

(instance sMisc of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_TABLE V_DO NULL 1 self)
			)
			(1
				(HandsOn)
			)
		)
	)
)

(instance getUpSleep of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: standUp self)
			)
			(1
				(self setScript: sGoSleep)
			)
		)
	)
)

(instance hermitSound of Sound
	(properties
		flags $ffff
		number 41
		loop -1
	)
)

(instance pfSnd of Sound
	(properties
		number 28
	)
)

(instance hermitTalker of Talker
	(properties
		x 10
		y 10
		view 1086
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= font userFont)
		(super init: hermitBust hermitEye hermitMouth &rest)
	)
)

(instance hermitBust of Prop
	(properties
		view 1086
	)
)

(instance hermitMouth of Prop
	(properties
		nsTop 58
		nsLeft 50
		view 1086
		loop 1
	)
)

(instance hermitEye of Prop
	(properties
		nsTop 28
		nsLeft 50
		view 1086
		loop 2
	)
)
