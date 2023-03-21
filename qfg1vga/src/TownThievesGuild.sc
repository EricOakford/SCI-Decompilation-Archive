;;; Sierra Script 1.0 - (do not remove this comment)
(script# 332)
(include game.sh) (include "332.shm")
(use Main)
(use Teller)
(use Ware)
(use Procs)
(use Talker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm332 0
	chiefThiefTalker 1
	Dummy332_2 2
)
(enum -1
	noFunds
	buyNothing
	buyLicense
	buyLockpick
	buyToolkit
)

(enum 1
	alreadyHaveLicense
	getLicense
	getLockpick
	getToolkit
	fencePearls
	fenceVase
	fenceMusicBox
	fenceCandlesticks
	fenceCandelabra
)

(enum 1
	cueUnlicensed
	cueLicensed
)

(local
	local0
	local1
	newKnife
	local3
	local4
	local5
	local6
	local7
	daggersOnBoard
	attackedGuild
	local10
	local11
	borisCue
	guildCue
	deathMessage = [
		{Mighty careless of you to walk right in front of that knife!__
		Or did the Chief Thief hit you on purpose?__
		Or did you just discover an obscure bug in the program???__
		Maybe you'll never really know._}
		{You can't believe the Chief Thief's reflexes as he instantly reacts to your intention to pull a fast one.__
		A dagger flies like lightning and...._}
		{Trouble with sharp, pointy objects}
		{These guys are MEAN!}
		250 40 75 30 25 100 35]
	chiefTellMainBranch = [
		STARTTELL
		-42		;C_THIEVES
		-34		;C_GUILD
		C_NAME
		-45		;C_TOWN
		-44		;C_TOOLS_CHIEF
		ENDTELL
		]
	chiefTell1 = [
		STARTTELL
		C_STEALING
		-29		;C_BRIGANDS
		ENDTELL
		]
	chiefTell2 = [
		STARTTELL
		C_TIPS
		C_SECRETS
		C_CRUSHER
		-32		;C_FENCE
		C_SCABS
		ENDTELL
		]
	chiefTell3 = [
		STARTTELL
		C_SHERIFF
		C_OTTO
		ENDTELL
		]
	chiefTell4 = [
		STARTTELL
		-31		;C_DAGGERS
		ENDTELL
		]
	chiefTell5 = [
		STARTTELL
		C_SPY
		ENDTELL
		]
	chiefTell6 = [
		STARTTELL
		-28		;C_BORIS
		ENDTELL
		]
	chiefTell7 = [
		STARTTELL
		-33		;C_GAME
		ENDTELL
		]
	chiefTell8 = [
		STARTTELL
		C_BET
		C_BOARD
		ENDTELL
		]
	chiefTell9 = [
		STARTTELL
		C_ACCOUNTANT
		ENDTELL
		]
	[chiefTellTree 15]
	chiefTellKeys = [
		STARTTELL
		-42		;C_THIEVES
		-34		;C_GUILD
		-45		;C_TOWN
		-44		;C_TOOLS_CHIEF
		-29		;C_BRIGANDS
		-32		;C_FENCE
		-31		;C_DAGGERS
		-28		;C_BORIS
		-33		;C_GAME
		ENDTELL
		]
	borisTellMainBranch = [
		STARTTELL
		-2		;C_LICENSE
		-6		;C_TOOLS
		C_FENCING
		ENDTELL
		]
	borisTell1 = [
		STARTTELL
		C_SERVICES
		ENDTELL
		]
	borisTell2 = [
		STARTTELL
		C_TOOLKIT
		C_LOCKPICK
		ENDTELL
		]
	[borisTellTree 5]
	borisTellKeys = [
		STARTTELL
		-2		;C_LICENSE
		-6		;C_TOOLS
		ENDTELL
		]
)
(procedure (Dummy332_2)
	;"WARNING: Invalid function offset: f000" when attempting to decompile this procedure. Fortunately, it doesn't seem to be used at all.
)

(procedure (ChiefWontTalk)
	(messager say: N_CHIEF)
)

(procedure (localproc_018d)
	(return
		(if local4
			(return FALSE)
		else
			(knifeScript changeState: 1)
			(return TRUE)
		)
	)
)

(class Knife of View
	(properties
		noun N_KNIFE
	)
)

(instance rm332 of Room
	(properties
		noun N_ROOM
		picture 332
		style WIPEDOWN
	)
	
	(method (init)
		(= [chiefTellTree 0] @chiefTellMainBranch)
		(= [chiefTellTree 1] @chiefTell1)
		(= [chiefTellTree 2] @chiefTell2)
		(= [chiefTellTree 3] @chiefTell3)
		(= [chiefTellTree 4] @chiefTell4)
		(= [chiefTellTree 5] @chiefTell5)
		(= [chiefTellTree 6] @chiefTell6)
		(= [chiefTellTree 7] @chiefTell7)
		(= [chiefTellTree 8] @chiefTell9)
		(= [chiefTellTree 9] @chiefTell8)
		(= [chiefTellTree 10] ENDTELL)
		(= [borisTellTree 0] @borisTellMainBranch)
		(= [borisTellTree 1] @borisTell1)
		(= [borisTellTree 2] @borisTell2)
		(= [borisTellTree 3] ENDTELL)
		(= local0 0)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						80 123
						84 130
						142 130
						147 174
						61 174
						63 152
						56 148
						51 116
						14 155
						0 179
						192 180
						179 158
						284 158
						189 111
						146 111
						133 123
					yourself:
				)
		)
		(LoadMany RES_VIEW 332 338 509 503)
		(LoadMany RES_SOUND
			(SoundFX 31)
			(SoundFX 29)
			(SoundFX 30)
		)
		(super init:)
		(SolvePuzzle f332EnterGuild 5 THIEF)
		(dagMusic number: (SoundFX 31) init:)
		(StatusLine enable:)
		(= local5 100)
		(= local6 7)
		(= perspective 70)
		(features
			add:
				onBoard
				onLadder
				onLightFixture1
				onLightFixture2
				onBoxes
				onTrap
				onDaggers
				onPottery
				onOneKnife
				onCrock
				onTable
			eachElementDo: #init
		)
		(onWindow init:)	;added
		(onDoor init: approachVerbs: V_DO V_LOOK V_MONEY)
		(onTrunk init:)
		(onBarrels init:)
		(onCeiling init:)
		(chair init: actions: chiefTeller stopUpd: ignoreActors:)
		(chairArm
			init:
			ignoreActors:
			actions: chiefTeller
			setPri: (+ (chiefThief priority?) 1)
			stopUpd:
		)
		(fire1 init: setCycle: Forward)
		(fire2 init: setCycle: Forward)
		(borisTeller
			init: borisThief @borisTellMainBranch @borisTellTree @borisTellKeys
		)
		(chiefTeller init: chiefThief @chiefTellMainBranch @chiefTellTree @chiefTellKeys)
		(chiefThief
			approachVerbs: V_DO V_MONEY
			actions: chiefTeller
			init:
		)
		(crusher init: ignoreActors: TRUE stopUpd:)
		(crusherHead init: ignoreActors: TRUE setCycle: Forward)
		(crusherFoot
			init:
			ignoreActors: TRUE
			setCycle: Forward
			cycleSpeed: 18
		)
		(knife
			ignoreActors:
			init:
			setPri: 13
			setLoop: 7
			setCycle: Forward
			stopUpd:
			hide:
			setScript: knifeScript
		)
		(if (== prevRoomNum 340)
			(theMusic fade: 127 20 5 0)
			(ego
				view: 4
				posn: 160 160
				loop: 3
				cel: 0
				ignoreActors:
				setCycle: Walk
				init:
			)
			(NormalEgo)
			(chiefThief setScript: chiefBored)
		else
			(theMusic number: 8 loop: -1 init: play:)
			(ego
				view: 4
				setLoop: 3
				cel: 0
				posn: 227 156
				ignoreActors:
				init:
			)
			(self setScript: rm332EntranceScript)
		)
	)
	
	(method (doit)
		(cond 
			(local4
				(if
					(<=
						(= local5
							(GetDistance
								(ego x?)
								(- (ego y?) 30)
								(knife x?)
								(knife y?)
								perspective
							)
						)
						local6
					)
					(ego setScript: deathScript)
				)
			)
			((and attackedGuild (not local3)
					(== (knifeScript state?) 0))
					(= local3 1)
					(localproc_018d)
				)
		)
		(cond 
			(
				(and
					(not (borisThief script?))
					(not local1)
					(ego inRect: 139 109 212 127)
				)
				(= local1 1)
				(borisThief setScript: heComes)
			)
			(
				(and
					local1
					(not (borisThief script?))
					(not (ego inRect: 139 109 212 127))
				)
				(= local1 0)
				(borisThief setScript: heGoes)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn332)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM V_LOOK NULL)
			)
			(V_DO
				(messager say: N_ROOM V_DO NULL)
			)
			(V_DAGGER
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(V_SWORD
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(V_ROCK
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(V_SLEEP
				(messager say: N_CHIEF V_SLEEP) ;Added new message hidden in the MSG file.
				(HandsOff)
				(ego setScript: throwEgoOut)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(switch borisCue
			(1
				(messager say: N_BORIS V_LOOK C_UNLICENSED)
			)
			(2
				(messager say: N_BORIS V_LOOK C_FENCEIT)
			)
		)
	)
)

(instance onBoard of Feature
	(properties
		x 86
		y 55
		noun N_BOARD
		nsTop 55
		nsLeft 64
		nsBottom 92
		nsRight 108
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BOARD V_LOOK NULL 1)
				(if (> daggersOnBoard 5)
					(messager say: N_BOARD V_LOOK NULL 2)
				)
			)
			(V_DO
				(messager say: N_BOARD V_DO)
				(if
					(and
						(ego has: iDagger)
						(or (>= (ego has: iSilver) 5) (>= (ego has: iGold) 1))
					)
					(curRoom setScript: goToDagnabit)
				)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onDoor of Feature
	(properties
		x 166
		y 80
		noun N_DOOR
		nsTop 61
		nsLeft 143
		nsBottom 108
		nsRight 190
		sightAngle 40
		approachX 157
		approachY 116
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_DOOR V_LOOK)
			)
			(V_DO
				(ego setScript: toTheDoorScript)
			)
			(V_LOCKPICK
				(messager say: N_DOOR V_LOCKPICK)
			)
			(V_THIEFKIT
				(messager say: N_DOOR V_LOCKPICK)
			)
			(V_DAGGER
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onLadder of Feature
	(properties
		x 261
		y 78
		noun N_LADDER
		nsTop 9
		nsLeft 252
		nsBottom 147
		nsRight 271
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_LADDER V_LOOK)
			)
			(V_DO
				(ego setScript: leaveRoomScript)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onLightFixture1 of Feature
	(properties
		x 22
		y 113
		noun N_LIGHTS
		nsTop 88
		nsLeft 12
		nsBottom 138
		nsRight 32
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_LIGHTS V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onLightFixture2 of Feature
	(properties
		x 295
		y 134
		noun N_LIGHTS
		nsTop 110
		nsLeft 284
		nsBottom 158
		nsRight 306
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_LIGHTS V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onCeiling of Feature
	(properties
		x 180
		y 38
		noun N_CEILING
		nsBottom 38
		nsRight 319
		sightAngle 40
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_CEILING V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onTrunk of Feature
	(properties
		x 218
		y 105
		noun N_TRUNK
		nsTop 105
		nsLeft 202
		nsBottom 137
		nsRight 252
		sightAngle 40
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_TRUNK V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onBarrels of Feature
	(properties
		x 218
		y 137
		noun N_BARRELS
		nsTop 137
		nsLeft 166
		nsBottom 179
		nsRight 319
		sightAngle 40
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_BARRELS V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onBoxes of Feature
	(properties
		x 83
		y 171
		noun N_BOXES
		nsTop 164
		nsBottom 178
		nsRight 167
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_BOXES V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onTrap of Feature
	(properties
		x 251
		y 4
		noun N_TRAPDOOR
		nsLeft 246
		nsBottom 8
		nsRight 257
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_TRAPDOOR V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onDaggers of Feature
	(properties
		x 232
		y 124
		z 65
		noun N_DAGGERS
		nsTop 45
		nsLeft 213
		nsBottom 73
		nsRight 251
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_DAGGERS V_LOOK)
			)
			(V_DO
				(messager say: N_DAGGERS V_DO)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onPottery of Feature
	(properties
		x 232
		y 154
		noun N_POTTERY
		nsTop 80
		nsLeft 217
		nsBottom 104
		nsRight 248
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_POTTERY V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onTable of Feature
	(properties
		x 108
		y 119
		noun N_TABLE
		nsTop 119
		nsLeft 76
		nsBottom 156
		nsRight 140
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_TABLE V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onOneKnife of Feature
	(properties
		x 82
		y 125
		noun N_KNIFEONTABLE
		nsTop 121
		nsLeft 79
		nsBottom 129
		nsRight 85
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_KNIFEONTABLE V_LOOK)
			)
			(V_DO
				(messager say: N_KNIFEONTABLE V_DO)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance onCrock of Feature
	(properties
		x 122
		y 122
		noun N_CROCK
		nsTop 116
		nsLeft 108
		nsBottom 129
		nsRight 137
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_CROCK V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance onWindow of Feature
	(properties
		x 173
		y 81
		z 30
		noun N_WINDOW
		nsTop 70
		nsLeft 164
		nsBottom 86
		nsRight 182
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_WINDOW V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance chairArm of View
	(properties
		x 76
		y 148
		noun N_CHIEF
		view 332
		loop 3
		cel 1
		priority 10
	)
	
	(method (doVerb theVerb)
		(chiefThief doVerb: theVerb &rest)
	)
)

(instance chair of View
	(properties
		x 72
		y 148
		noun N_CHIEF
		view 332
		loop 3
		priority 5
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(chiefThief doVerb: theVerb &rest)
	)
)

(instance crusher of Prop
	(properties
		x 124
		y 119
		noun N_CRUSHER
		view 332
		loop 3
		cel 2
		priority 7
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_CRUSHER V_LOOK)
			)
			(V_DO
				(messager say: N_CRUSHER V_DO)
			)
			(V_TALK
				(messager say: N_CRUSHER V_TALK)
			)
			 ;added in an unused message
			(V_DAGGER
				(messager say: N_CRUSHER V_DAGGER)
			)
			(else 
				(curRoom doVerb: theVerb &rest)
			)
		)
	)
)

(instance fire1 of Prop
	(properties
		x 31
		y 96
		noun N_LIGHTS
		view 332
		loop 8
		cel 6
	)
	
	(method (doVerb theVerb)
		(onLightFixture1 doVerb: theVerb &rest)
	)
)

(instance fire2 of Prop
	(properties
		x 306
		y 110
		noun N_LIGHTS
		view 332
		loop 9
		cel 2
	)
	
	(method (doVerb theVerb)
		(onLightFixture1 doVerb: theVerb &rest)
	)
)

(instance crusherHead of Prop
	(properties
		x 116
		y 75
		noun N_CRUSHER
		view 332
		loop 4
		priority 8
		signal fixPriOn
		cycleSpeed 36
	)
	
	(method (doVerb theVerb)
		(crusher doVerb: theVerb &rest)
	)
)

(instance crusherFoot of Prop
	(properties
		x 127
		y 117
		noun N_CRUSHER
		view 332
		loop 13
		cel 1
		signal ignrAct
		cycleSpeed 2
	)
	
	(method (doVerb theVerb)
		(crusher doVerb: theVerb &rest)
	)
)

(instance knife of Actor
	(properties
		x 91
		y 124
		noun N_KNIFE
		yStep 7
		view 332
		loop 7
		cycleSpeed 1
		illegalBits $0000
		xStep 6
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_KNIFE V_LOOK)
		else
			(curRoom doVerb: theVerb &rest)
		)
	)
)

(instance chiefThief of Actor
	(properties
		x 91
		y 145
		noun N_CHIEF
		view 332
		loop 5
		priority 10
		signal (| ignrAct fixPriOn)
		illegalBits $0000
	)
	
	(method (init)
		(= nightPalette 1332)
		(PalVary PALVARYTARGET 1332)
		(AssertPalette 332)
		(super init:)
	)
)

(instance borisThief of Actor
	(properties
		x 182
		y 87
		noun N_BORIS
		approachX 157
		approachY 116
		view 332
		loop 12
	)
)

(instance chiefTeller of Teller
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_CHIEF V_LOOK)
			)
			(V_TALK
				(if (not (ego has: iThiefLicense))
					(ego setScript: getTheLicense)
				else
					(Face ego chiefThief)
					(super doVerb: theVerb &rest)
				)
			)
			(V_MONEY
				(if
					(and
						(ego has: iDagger)
						(or (>= (ego has: iSilver) 5) (>= (ego has: iGold) 1))
					)
					(theMusic fade: 63 20 5 0)
					(curRoom newRoom: 340)
				else
					(messager say: N_CHIEF V_DO)
				)
			)
			(V_DAGGER
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(V_SWORD
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(V_ROCK
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance borisTeller of Teller
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BORIS V_LOOK NULL 1 curRoom)
				(if (not (ego has: iThiefLicense))
					(= borisCue cueUnlicensed)
				else
					(= borisCue cueLicensed)
				)
			)
			(V_DO
				(cond 
					((not (ego has: iThiefLicense))
						(messager say: N_BORIS V_DO C_UNLICENSED)
					)
					(
						(or
							(Btst fSearchedBasket)
							(Btst fStoleCandles)
							(Btst fStoleVase)
							(Btst fStoleVase)
							(Btst fStoleMusicBox)
						)
						(messager say: N_BORIS V_DO C_CANTFENCETHAT)
					)
					(else
						(messager say: N_BORIS V_DO C_FENCEIT)
					)
				)
			)
			(V_TALK
				(if (not (ego has: iThiefLicense))
					(messager say: N_BORIS V_TALK C_UNLICENSED)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_MONEY
				((= wareList (List new:))
					add:
						((Clone Ware) name: {License} price: {25})
						((Clone Ware) name: {Lock Pick} price: {15})
						((Clone Ware) name: {Tool Kit} price: {100})
				)
				(switch ((ScriptID WARE 0) doit:)
					(noFunds (messager say: N_BORIS V_MONEY C_NOFUNDS))
					(buyLicense
						(if (ego has: iThiefLicense)
							(messager say: N_BORIS V_MONEY C_ALREADYPAID)
							(= guildCue alreadyHaveLicense)
							(ego setScript: cueItScript)
						else
							(messager say: N_BORIS V_MONEY C_GETLICENSE)
							(SolvePuzzle f332BuyLicense 3 THIEF)
							(= guildCue getLicense)
							(ego setScript: cueItScript)
						)
					)
					(buyLockpick
						(if (not (ego has: iLockPick))
							(= lockPickBonus 10)
						)
						(messager say: N_BORIS V_MONEY C_BUYSOMETHING)
						(= guildCue getLockpick)
						(ego setScript: cueItScript)
					)
					(buyToolkit
						(if (not (ego has: iThiefKit))
							(= lockPickBonus 35)
							(SolvePuzzle f332BuyToolkit 3 THIEF)
						)
						(messager say: N_BORIS V_MONEY C_BUYSOMETHING)
						(= guildCue getToolkit)
						(ego setScript: cueItScript)
					)
				)
				(return TRUE)
			)
			(V_PEARLS
				(if (not (ego has: iThiefLicense))
					(messager say: N_BORIS V_PEARLS C_UNLICENSED)
				else
					(messager say: N_BORIS V_PEARLS C_FENCEIT)
					(SolvePuzzle f332FenceGoods 3 THIEF)
					(= guildCue fencePearls)
					(ego setScript: cueItScript)
				)
			)
			(V_DAGGER
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(V_VASE
				(if (not (ego has: iThiefLicense))
					(messager say: N_BORIS V_VASE C_UNLICENSED)
				else
					(messager say: N_BORIS V_VASE C_FENCEIT)
					(SolvePuzzle f332FenceGoods 3 THIEF)
					(= guildCue fenceVase)
					(ego setScript: cueItScript)
				)
			)
			(V_MUSICBOX
				(if (not (ego has: iThiefLicense))
					(messager say: N_BORIS V_MUSICBOX C_UNLICENSED)
				else
					(messager say: N_BORIS V_MUSICBOX C_FENCEIT)
					(SolvePuzzle f332FenceGoods 3 THIEF)
					(= guildCue fenceMusicBox)
					(ego setScript: cueItScript)
				)
			)
			(V_CANDLESTICKS
				(if (not (ego has: iThiefLicense))
					(messager say: N_BORIS V_CANDLESTICKS C_UNLICENSED)
				else
					(messager say: N_BORIS V_CANDLESTICKS C_FENCEIT)
					(SolvePuzzle f332FenceGoods 3 THIEF)
					(= guildCue fenceCandlesticks)
					(ego setScript: cueItScript)
				)
			)
			(V_CANDELABRA
				(if (not (ego has: iThiefLicense))
					(messager say: N_BORIS V_CANDELABRA C_UNLICENSED)
				else
					(messager say: N_BORIS V_CANDELABRA C_FENCEIT)
					(SolvePuzzle f332FenceGoods 3 THIEF)
					(= guildCue fenceCandelabra)
					(ego setScript: cueItScript)
				)
			)
			(V_RING
				(messager say: N_ROOM V_RING)
			)
			(V_SWORD
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(V_ROCK
				(HandsOff)
				(= attackedGuild TRUE)
				(ego setScript: uhOh)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance rm332EntranceScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= ticks 120)
			)
			(1
				(if (not (Btst fBeenIn332))
					(crusherFoot setCycle: 0)
					(crusherHead setCycle: 0)
					(messager say: N_ROOM NULL C_FIRSTENTRY 0 self)
				else
					(= ticks 120)
				)
			)
			(2
				(crusherFoot setCycle: Forward)
				(crusherHead setCycle: Forward)
				(= ticks 30)
			)
			(3
				(NormalEgo)
				(theGame setCursor: waitCursor)
				(ego
					view: 0
					loop: 0
					cel: 0
					setCycle: Walk
					setMotion: PolyPath 155 157 self
				)
			)
			(4
				(if (Btst fBeenIn332)
					(self changeState: 40)
					(= ticks 1)
				else
					(self cue:)
				)
			)
			(5
				(knife hide:)
				(messager say: N_CHIEF NULL C_MEETCHIEF1)
				(ego view: 4 setLoop: 3)
				(chiefThief setLoop: 2 setCycle: EndLoop self)
			)
			(6
				(chiefThief
					setLoop: 0
					setCel: 0
					setCycle: Walk
					setMotion: MoveTo 210 136 self
				)
			)
			(7
				(chiefThief setLoop: 1 setMotion: MoveTo 180 136 self)
				(messager say: N_CHIEF NULL C_MEETCHIEF2)
			)
			(8
				(chiefThief setLoop: 1 setMotion: MoveTo 150 136 self)
			)
			(9
				(chiefThief setLoop: 0 setMotion: MoveTo 190 136 self)
			)
			(10
				(chiefThief setLoop: 1 setMotion: MoveTo 100 136 self)
			)
			(11
				(chiefThief setLoop: 0 setMotion: MoveTo 94 136 self)
			)
			(12
				(chiefThief
					setLoop: 2
					posn: 91 145
					cel: 9
					setCycle: BegLoop self
				)
			)
			(13
				(chiefThief setLoop: 5 setCel: 0)
				(= seconds 3)
			)
			(14
				(messager say: N_CHIEF NULL C_MEETCHIEF3)
				(Bset fBeenIn332)
				(self cue:)
			)
			(15
				(localproc_018d)
				(self changeState: 40)
			)
			(40
				(ego setPri: -1)
				(NormalEgo)
				(HandsOn)
				(chiefThief setScript: chiefBored)
				(self dispose:)
			)
		)
	)
)

(instance leaveRoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: Walk setMotion: PolyPath 240 150 self)
			)
			(1
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 (| BLACKOUT IRISIN))
				(self cue:)
			)
			(2 (curRoom newRoom: 331))
		)
	)
)

(instance throwEgoOut of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(NormalEgo loopN 0 4)
				(HandsOff)
				(ego
					setMotion: PolyPath (crusher x?) (+ (crusher y?) 30) self
				)
			)
			(1
				(crusherHead hide: dispose:)
				(crusher view: 338 loop: 0 setCycle: EndLoop self)
			)
			(2
				(crusher cel: 2)
				(= cycles 3)
			)
			(3 (crusher setCycle: EndLoop self))
			(4
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 400 (| BLACKOUT IRISOUT))
				(self cue:)
			)
			(5
				(Bset fBarThrownOut)
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance chiefBored of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 5 12))
			)
			(1
				(localproc_018d)
				(self changeState: 0)
			)
		)
	)
)

(instance knifeScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(knife posn: 91 124 cel: 0)
				(chiefThief setLoop: 5 cel: 0)
			)
			(1
				(knife show:)
				(chiefThief setLoop: 5 cel: 0 setCycle: EndLoop)
				(= cycles 1)
			)
			(2
				(= local4 1)
				(dagMusic number: (SoundFX 31) play:)
				(if attackedGuild
					(knife setMotion: MoveTo (ego x?) (- (ego y?) 30))
				else
					(knife setMotion: MoveTo 91 95)
				)
				(= cycles 12)
			)
			(3
				(knife setMotion: MoveTo 91 124 self)
			)
			(4
				(chiefThief loop: 6 cel: 0 posn: 91 145)
				(knife hide:)
				(= seconds 2)
			)
			(5
				(chiefThief setCycle: EndLoop)
				(if attackedGuild
					(knife
						show:
						setMotion: MoveTo (ego x?) (- (ego y?) 30) self
					)
				else
					(knife
						show:
						setMotion: MoveTo (Random 80 95) (Random 67 85) self
					)
				)
			)
			(6
				(dagMusic stop:)
				(dagMusic
					number: (if (Random 0 1)
						(SoundFX 30)
					else
						(SoundFX 29)
					)
					play:
				)
				(= local4 0)
				(if (< local10 12)
					(++ local0)
					((= newKnife (Knife new:))
						view: 332
						loop: 7
						cel: 1
						posn: (knife x?) (knife y?)
						init:
						stopUpd:
					)
					(++ local10)
				)
				(knife hide:)
				(= cycles 2)
			)
			(7 (self changeState: 0))
		)
	)
)

(instance toTheDoorScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 157 116 self)
			)
			(1
				(messager say: N_ROOM NULL C_DOORLOCKED)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance deathScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(knife setMotion: 0 hide:)
				(ego
					view: 503
					posn: (+ (ego x?) 5) (ego y?)
					setLoop: 0
					cel: 0
					setCycle: CycleTo 5 1
				)
				(= cycles 15)
			)
			(1 (ego setCycle: EndLoop self))
			(2 (EgoDead 47 48 0 0 800))
		)
	)
)

(instance uhOh of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego chiefThief self)
			)
			(1 (= seconds 2))
			(2 (EgoDead 47 48 0 0 800))
		)
	)
)

(instance heComes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(borisThief
					approachVerbs: V_DO V_MONEY
					init:
					loop: 12
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(borisThief setLoop: 10 setCel: 0)
				(self cue:)
			)
			(2 (self dispose:))
		)
	)
)

(instance heGoes of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(borisThief loop: 11 cel: 0 setCycle: EndLoop self)
			)
			(1
				(borisThief dispose: delete:)
				(self dispose:)
			)
		)
	)
)

(instance getTheLicense of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego chiefThief)
				(= seconds 3)
			)
			(1
				(ChiefWontTalk)
				(self cue:)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance goToDagnabit of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= ticks 60))
			(1
				(theMusic fade: 63 20 5 0)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance cueItScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(switch guildCue
					(alreadyHaveLicense
						(ego get: iSilver 25)
					)
					(getLicense
						(ego get: iThiefLicense)
					)
					(getLockpick
						(ego get: iLockPick)
					)
					(getToolkit
						(ego get: iThiefKit)
					)
					(fencePearls
						(ego get: iGold 50)
						(ego use: iPearls)
					)
					(fenceVase
						(ego use: iVase)
						(ego get: iGold 4)
					)
					(fenceMusicBox
						(ego use: iMusicBox)
						(ego get: iGold 9)
					)
					(fenceCandlesticks
						(ego use: iCandlesticks)
						(ego get: iGold 10)
					)
					(fenceCandelabra
						(ego use: iCandelabra)
						(ego get: iGold 15)
					)
				)
				(self cue:)
			)
			(2
				(self dispose:)
			)
		)
	)
)

(instance dagMusic of Sound
	(properties
		number 31
	)
)

(instance chiefThiefTalker of Talker
	(properties
		x 10
		y 10
		view 1332
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2332)
		(PalVary PALVARYTARGET 2332)
		(AssertPalette 1332)
		(= font userFont)
		(super
			init: chiefThiefBust chiefThiefEye chiefThiefMouth &rest
		)
	)
)

(instance chiefThiefBust of Prop
	(properties
		view 1332
	)
)

(instance chiefThiefMouth of Prop
	(properties
		nsTop 45
		nsLeft 41
		view 1332
		loop 1
	)
)

(instance chiefThiefEye of Prop
	(properties
		nsTop 14
		nsLeft 41
		view 1332
		loop 2
	)
)
