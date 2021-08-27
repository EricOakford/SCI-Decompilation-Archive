;;; Sierra Script 1.0 - (do not remove this comment)
(script# 333)
(include game.sh) (include "333.shm") (include "811.shm")
(use Main)
(use Teller)
(use Procs)
(use Talker)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm333 0
	beggarTalker 1
)

(local
	nearBeggar
	gaveMoney
	willTalk
	talkCount
	talkRet
	climbCount
	beggarCue
	beggarTellMainBranch = [
		STARTTELL
		-3		;C_BEGGING
		-10		;C_NAME
		-16		;C_SPIELBURG
		C_BRIGANDS
		-11		;C_NIGHT
		ENDTELL
		]
	beggarTell1 = [
		STARTTELL
		C_CASH
		C_WORK
		ENDTELL
		]
	beggarTell2 = [
		STARTTELL
		C_SOUTH
		ENDTELL
		]
	beggarTell3 = [
		STARTTELL
		C_TRADERS
		-15		;C_TOURISTS
		C_LUCK
		C_BRIGANDS
		ENDTELL
		]
	beggarTell4 = [
		STARTTELL
		C_GAUNTS
		-13		;C_SPELL
		C_THIEVES
		ENDTELL
		]
	beggarTell5 = [
		STARTTELL
		C_CIDER
		ENDTELL
		]
	beggarTell6 = [
		STARTTELL
		C_CRUSHER
		ENDTELL
		]
	[beggarTellTree 12]
	beggarTellKeys = [
		STARTTELL
		-3		;C_BEGGING
		-10		;C_NAME
		-16		;C_SPIELBURG
		-11		;C_NIGHT
		-15		;C_TOURISTS
		-13		;C_SPELL
		ENDTELL
		]
)

(enum 1	;what is given to beggar
	giveMoney
	giveApples
	giveVegetables
)

(instance rm333 of Room
	(properties
		noun N_ROOM
		picture 333
		style HSHUTTER
	)
	
	(method (init)
		(= [beggarTellTree 0] @beggarTellMainBranch)
		(= [beggarTellTree 1] @beggarTell1)
		(= [beggarTellTree 2] @beggarTell2)
		(= [beggarTellTree 3] @beggarTell3)
		(= [beggarTellTree 4] @beggarTell4)
		(= [beggarTellTree 5] @beggarTell5)
		(= [beggarTellTree 6] @beggarTell6)
		(= [beggarTellTree 7] ENDTELL)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 0
						319 0
						319 189
						257 189
						213 151
						177 145
						170 137
						165 111
						143 112
						138 119
						143 133
						148 149
						121 179
						108 184
						85 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						219 177
						177 177
						172 159
						206 158
					yourself:
				)
		)
		(Load VIEW 333)
		(super init:)
		(StatusLine enable:)
		;don't make this part of the Town region,
		; since Erana's aura doesn't cover it.
		(self setRegions: STREET) ;TOWN)
		(NormalEgo)
		(ego posn: 120 187 init: setMotion: MoveTo 120 180)
		(beggarTeller init: beggar @beggarTellMainBranch @beggarTellTree @beggarTellKeys)
		(beggar actions: beggarTeller init:)
		(features
			add:
				onTheWalls
				theSky
				onBarrels
				onBarrels2
				onHotDogStand
				onBricks
				marksOnWall
			eachElementDo: #init
		)
		(= nearBeggar FALSE)
		;now only shows the message on first entry
		(if (not (Btst fBeenIn333))
			(messager say: N_ROOM NULL C_LOOKROOM)
		)
	)
	
	(method (doit)
		(if (and (> (ego y?) 187) (not (ego script?)))
			(ego setScript: goBackToStreet)
		)
		(cond 
			((and (not nearBeggar) (ego inRect: 100 135 215 185))
				(beggar setCycle: EndLoop)
				(messager say: N_BEGGAR NULL C_ALMSFORTHEPOOR)
				(= nearBeggar TRUE)
			)
			((and nearBeggar (not (ego inRect: 100 135 215 185)))
				(beggar setCycle: BegLoop)
				(= nearBeggar FALSE)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn333)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM NULL C_LOOKROOM)
			)
			(V_DO
				(messager say: N_ROOM V_DO C_LOOKROOM)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onTheWalls of Feature
	(properties
		x 75
		y 80
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_ROOM V_LOOK C_LOOKROOM)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theSky of Feature
	(properties
		x 165
		y 110
		z 98
		noun N_SKY
		nsTop 5
		nsLeft 124
		nsBottom 19
		nsRight 207
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(if Night
				(messager say: N_SKY V_LOOK C_NIGHT)
			else
				(messager say: N_SKY V_LOOK)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance onBarrels of Feature
	(properties
		x 106
		y 148
		noun N_BARRELS
		nsTop 133
		nsLeft 90
		nsBottom 164
		nsRight 122
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance onBarrels2 of Feature
	(properties
		x 198
		y 120
		noun N_BARRELS2
		nsTop 104
		nsLeft 184
		nsBottom 136
		nsRight 212
		sightAngle 40
		onMeCheck cBLUE
	)
)

(instance onHotDogStand of Feature
	(properties
		x 114
		y 128
		noun N_HOTDOGSTAND
		nsTop 104
		nsLeft 105
		nsBottom 153
		nsRight 124
		sightAngle 40
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(messager say: N_HOTDOGSTAND V_LOOK)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance onBricks of Feature
	(properties
		x 158
		y 74
		noun N_BRICKS
		nsTop 44
		nsLeft 129
		nsBottom 107
		nsRight 187
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_BRICKS V_LOOK)
			)
			(V_DO
				(cond 
					((TrySkill CLIMB 35 0)
						(ego setScript: toTheCentaur)
					)
					((not [egoStats CLIMB])
						(messager say: N_ROOM NULL C_NOCLIMB)
						(++ climbCount)
					)
					((and (< 3 climbCount) (< climbCount 10))
						(messager say: N_ROOM NULL C_NOMORECLIMBING)
						(= climbCount 10)
					)
					(else
						(messager say: N_ROOM NULL C_CLIMBFAIL)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance marksOnWall of Feature
	(properties
		x 120
		y 103
		noun N_MARKS
		nsTop 100
		nsLeft 112
		nsBottom 107
		nsRight 128
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (<= (ego y?) 117)
					(messager say: N_MARKS V_LOOK C_SEEMARKS)
				else
					(messager say: N_MARKS V_LOOK C_TOOFAR)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beggar of Prop
	(properties
		x 184
		y 171
		noun N_BEGGAR
		view 333
		signal ignrAct
	)
)

(instance beggarTeller of Teller
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(V_LOOK
					(if gaveMoney
						(messager say: N_BEGGAR V_LOOK C_BOWLHASMONEY)
					else
						(messager say: N_BEGGAR V_LOOK C_BOWLEMPTY)
					)
					(return TRUE)
				)
				(V_TALK
					(cond 
						((not gaveMoney)
							(messager say: N_BEGGAR V_TALK C_BOWLHASMONEY)
							(return TRUE)
						)
						((> talkCount 5)
							(= talkRet FALSE)
							(messager say: N_BEGGAR V_TALK C_BACKTOWORK)
							(return TRUE)
						)
						(else
							(++ talkCount)
							(SolvePuzzle f333TalkToBeggar 1)
							(= talkRet TRUE)
							(super doVerb: theVerb &rest)
						)
					)
				)
				(V_DO
					(messager say: N_BEGGAR V_DO)
					(return TRUE)
				)
				(V_MONEY
					(SolvePuzzle f333GiveAlms 1)
					(if
						(or
							(> ((inventory at: iSilver) amount?) 0)
							(> ((inventory at: iGold) amount?) 0)
						)
						(= beggarCue giveMoney)
						(ego setScript: cueItScript)
						(messager say: N_BEGGAR V_MONEY)
						(= gaveMoney TRUE)
						(= willTalk TRUE)
						(= talkCount 0)
					else
						(messager say: N_BEGGAR V_MONEY C_NOFUNDS)
					)
					(return TRUE)
				)
				(V_FRUIT
					(messager say: N_BEGGAR V_FRUIT)
					(= beggarCue giveApples)
					(ego setScript: cueItScript)
				)
				(V_VEGETABLES
					(messager say: N_BEGGAR V_VEGETABLES)
					(= beggarCue giveVegetables)
					(ego setScript: cueItScript)
				)
				(V_DAGGER
					(messager say: N_STREET V_ROCK NULL 1 0 STREET)
				)
				(V_SWORD
					(messager say: N_STREET V_ROCK NULL 1 0 STREET)
				)
				(V_ROCK
					(messager say: N_STREET V_ROCK NULL 1 0 STREET)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance toTheCentaur of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 161 112 self)
			)
			(1
				(ego
					view: 517
					setLoop: 0
					setCel: 0
					posn: 163 76
					setCycle: Walk
					setMotion: MoveTo 159 62 self
				)
			)
			(2
				(ego
					setLoop: 1
					setCel: 0
					posn: 161 44
					setCycle: CycleTo 7 1 self
				)
			)
			(3
				(curRoom newRoom: 53)
			)
		)
	)
)

(instance goBackToStreet of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= seconds 1)
			)
			(1
				(if (and gaveMoney (not (Btst fBeenIn333)))
					(messager say: N_BEGGAR NULL C_DONTDRINKBREATH 1 self)
				else
					(self cue:)
				)
			)
			(2
				(curRoom newRoom: 330)
			)
		)
	)
)

(instance cueItScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 60)
			)
			(1
				(switch beggarCue
					(giveMoney
						(ego use: iSilver 1)
					)
					(giveApples
						(ego use: iFruit 1)
					)
					(giveVegetables
						(ego use: iVegetables 1)
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

(instance beggarTalker of Talker
	(properties
		x 5
		y 2
		view 1334
		talkWidth 260
		textX 15
		textY 112
	)
	
	(method (init)
		(= nightPalette 2334)
		(PalVary PALVARYTARGET 2334)
		(AssertPalette 1334)
		(= font userFont)
		(super init: beggarBust beggarEye beggarMouth &rest)
	)
)

(instance beggarBust of Prop
	(properties
		view 1334
	)
)

(instance beggarMouth of Prop
	(properties
		nsTop 42
		nsLeft 52
		view 1334
		loop 1
	)
)

(instance beggarEye of Prop
	(properties
		nsTop 26
		nsLeft 79
		view 1334
		loop 2
	)
)
