;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include game.sh) (include "65.shm")
(use Main)
(use Teller)
(use Ware)
(use Target)
(use Procs)
(use Talker)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm65 0
	bruno65Talker 1
)

(enum -1
	noFunds
	giveNothing
	give1Silver
	give2Silvers
	give2Gold
	give10Gold
)

(local
	paid1Silver
	paid2Silvers
	paid1Gold
	paid10Gold
	brunoReadyToKnife
	whinerCount
	gotBrunoAdvice
	brunoWantsMoney
	wantMoneyMsg
	brunoGotMoney
	attackedBruno
	showedThiefSign
	lookedAtBruno
	roadLookCount
	lookAround
	saveMoveSpeed
	saveCycleSpeed
	brunoTellMainBranch = [
		STARTTELL
		C_NAME
		C_BABAYAGA
		C_BARON
		-20		;C_MONSTERS
		-19		;C_BABAHUT
		C_HEALER
		-17		;C_THIEVESGUILD
		ENDTELL
		]
	brunoTell1 = [
		STARTTELL
		C_CEMETERY
		C_PASSWORD
		C_CHIEF
		C_SHERIFF
		C_OTTO
		C_CRUSHER
		-11		;C_DRAGONBREATH
		ENDTELL
		]
	brunoTell2 = [
		STARTTELL
		C_ANTWERP
		C_GOBLINS
		C_WARLOCK
		-12		;C_BRIGANDS
		ENDTELL
		]
	[brunoTellTree 11]
	brunoTellKeys = [
		STARTTELL
		-20		;C_MONSTERS
		-17		;C_THIEVESGUILD
		ENDTELL
		]
)
(procedure (SetNightPoly)
	(if (curRoom obstacles?)
		((curRoom obstacles?) dispose:)
		(curRoom obstacles: 0)
	)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: PBarredAccess
				init:
					102 157
					102 189
					0 189
					0 0
					319 0
					319 108
					234 104
					262 41
					239 41
					189 86
					123 83
					97 96
					83 95
					27 125
				yourself:
			)
			((Polygon new:)
				type: PBarredAccess
				init:
					173 189
					203 144
					319 134
					319 189
				yourself:
			)
	)
)

(instance rm65 of Room
	(properties
		noun N_ROOM
		picture 65
		style DISSOLVE
	)
	
	(method (init)
		(= [brunoTellTree 0] @brunoTellMainBranch)
		(= [brunoTellTree 1] @brunoTell2)
		(= [brunoTellTree 2] @brunoTell1)
		(= [brunoTellTree 3] ENDTELL)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						30 90
						8 98
						102 157
						102 189
						0 189
						0 0
						319 0
						319 108
						234 104
						262 41
						239 41
						189 86
						123 83
						97 96
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						173 189
						203 144
						319 134
						319 189
					yourself:
				)
		)
		(LoadMany RES_VIEW 65 165 1074)
		(Load RES_SCRIPT DPATH)
		(super init: &rest)
		(if Night
			(Bclr fTownGateOpen)
			(townGate cel: 0)
			(addToPics add: townGate eachElementDo: #init doit:)
		else
			(Bset fTownGateOpen)
			(onTownGate init:)
		)
		(self
			setFeatures:
				rock
				onTheWall
				onTownSign
				deadSquare
				onTheRoad
				onTheWhat
		)
		;UPGRADE
;;;		(rock init:)
;;;		(onTheWall init:)
;;;		(onTownSign init:)
;;;		(deadSquare init:)
;;;		(onTheRoad init:)
;;;		(onTheWhat init:)
		
		(= whinerCount 0)
		(if
			(and
				(Btst fLeftTown2)
				(or (== timeODay TIME_MIDDAY) (== timeODay TIME_MIDAFTERNOON))
				(or (not (Btst fBearGone)) (Btst fSpiedOnThieves))
			)
			(Load RES_VIEW 516)
			(brunoTeller init: bruno @brunoTellMainBranch @brunoTellTree @brunoTellKeys)
			(bruno
				init:
				actions: brunoTeller
				setScript: brunoFlippingDagger
			)
		)
		(NormalEgo)
		(ego init:)
		(if (not (Btst fTownGateOpen))
			(ego illegalBits: (| cWHITE cLGREY)) ;-32640
		)
		(switch prevRoomNum
			(74
				(ego posn: 160 188 setScript: comeFromSouth)
			)
			(54
				(ego posn: 232 64 setMotion: MoveTo 220 83)
			)
			(66
				(ego posn: 318 140 setScript: comeFromEast)
			)
			(320
				(ego posn: 140 91 setHeading: 100)
			)
			(300
				(if (not (Btst fTownGateOpen))
					(HandsOn)
					(ego posn: 90 101 setHeading: 100)
				else
					(HandsOff)
					(ego posn: 31 91 setHeading: 100)
					(curRoom setScript: sExitFromTown)
				)
			)
			(else 
				(HandsOn)
				(ego posn: 265 140)
			)
		)
	)
	
	(method (doit)
		(if (> timeODay TIME_MIDAFTERNOON)
			(SetNightPoly)
		)
		(cond 
			(script)
			((and (< (ego y?) 47) (not (ego script?)))
				(curRoom setScript: goToHealer)
			)
			((and (> (ego y?) 187) (not (ego script?)))
				(curRoom setScript: goSouth)
			)
			((and (> (ego x?) 317) (not (ego script?)))
				(curRoom setScript: goEast)
			)
			((and (& (ego onControl: origin) cGREEN) (Btst fTownGateOpen))
				(HandsOff)
				(curRoom setScript: goToTown)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn65)
		(DisposeScript DPATH)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb V_LOOK)
				(= lookAround TRUE)
				(messager say: N_ROOM V_LOOK NULL 0 curRoom)
			)
			((OneOf theVerb V_OPEN V_DETECT V_TRIGGER V_DAZZLE V_CALM V_FLAME V_FETCH V_ZAP)
				(if (cast contains: bruno)
					(= attackedBruno TRUE)
					(bruno setScript: knifeEgo)
				else
					(VerbSpell theVerb)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
	
	(method (cue)
		(switch lookAround
			(V_LOOK
				(if Night
					(messager say: N_ROOM V_LOOK C_NIGHT)
				else
					(messager say: N_ROOM V_LOOK C_DAY)
				)
			)
		)
	)
)

(instance rock of Feature
	(properties
		x 181
		y 75
		z 6
		noun N_ROCK
		nsTop 64
		nsLeft 167
		nsBottom 75
		nsRight 196
		sightAngle 40
	)
)

(instance onTownGate of Feature
	(properties
		x 51
		y 58
		noun N_TOWNGATE
		nsTop 8
		nsLeft 18
		nsBottom 109
		nsRight 84
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (not (Btst fTownGateOpen))
					(messager say: N_TOWNGATE V_LOOK C_GATECLOSED)
				else
					(messager say: N_TOWNGATE V_LOOK C_DAY)
				)
			)
			(V_DO
				(messager say: N_TOWNGATE V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onTownSign of Feature
	(properties
		x 278
		y 150
		noun N_TOWNSIGN
		nsTop 114
		nsLeft 241
		nsBottom 187
		nsRight 316
		sightAngle 40
		onMeCheck cCYAN
	)
)

(instance onTheWall of Feature
	(properties
		x 118
		y 12
		noun N_WALL
		nsBottom 75
		nsRight 236
		sightAngle 40
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_WALL V_LOOK)
			)
			(V_DO
				(if (>= timeODay TIME_SUNSET)
					(ego setScript: climbTheWall)
				else
					(messager say: N_WALL V_DO)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onTheWhat of Feature
	(properties
		x 118
		y 180
		noun N_CART
		sightAngle 40
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(switch (Random 1 6)
				(1 (messager say: N_CART V_LOOK C_RAND3))
				(2 (messager say: N_CART V_LOOK C_RAND6))
				(3 (messager say: N_CART V_LOOK C_RAND5))
				(4 (messager say: N_CART V_LOOK C_RAND2))
				(5 (messager say: N_CART V_LOOK C_RAND1))
				(6 (messager say: N_CART V_LOOK C_RAND4))
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance onTheRoad of Feature
	(properties
		x 118
		y 20
		noun N_ROAD
		sightAngle 40
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(switch (++ roadLookCount)
				(1 (messager say: N_ROAD V_LOOK C_RAND3))
				(2 (messager say: N_ROAD V_LOOK C_RAND6))
				(3 (messager say: N_ROAD V_LOOK C_RAND5))
				(4 (messager say: N_ROAD V_LOOK C_RAND2))
				(5 (messager say: N_ROAD V_LOOK C_RAND1))
				(6 (messager say: N_ROAD V_LOOK C_RAND4))
			)
			(if (== roadLookCount 7)
				(= roadLookCount 0)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance deadSquare of Feature
	(properties
		x 117
		y 74
		noun N_DEADSQUARE
		onMeCheck cRED
	)
)

(instance townGate of View
	(properties
		x 20
		y 114
		noun N_CLOSEDGATE
		view 165
		loop 1
		priority 4
		signal (| ignrAct fixPriOn stopUpdOn)
	)
)

(instance knife1 of Actor
	(properties
		view 65
		loop 6
	)
)

(instance knife2 of Actor
	(properties
		view 65
		loop 7
	)
)

(instance bruno of TargActor
	(properties
		x 99
		y 89
		noun N_BRUNO
		approachX 94
		approachY 104
		view 65
	)
	
	(method (doit)
		(cond 
			((== script knifeEgo) 0)
			(
				(and
					brunoReadyToKnife
					(or
						(ego inRect: 212 53 256 68)
						(ego inRect: 283 107 319 130)
						(ego inRect: 123 171 172 188)
					)
				)
				(self setScript: knifeEgo)
			)
		)
		(super doit:)
	)
)

(instance brunoTeller of Teller
	(properties)
	
	(method (showDialog)
		(super showDialog:
			-17 paid1Gold
			-19 paid1Gold
			-12 paid10Gold
			-11 paid2Silvers
		)
	)
	
	(method (doChild)
		(if (== query -19)
			(Bset fLearnedRhyme)
		)
		(return
			(if
				(not
					(if (or (== query -19) (== query -11))
					else
						(== query -12)
					)
				)
				(super doChild: query)
			else
				(return TRUE)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (== lookedAtBruno FALSE)
					(messager say: N_BRUNO V_LOOK C_SEEBRUNO1)
					(= lookedAtBruno TRUE)
				else
					(messager say: N_BRUNO V_LOOK C_SEEBRUNO2)
				)
			)
			(V_TALK
				(cond 
					((not brunoGotMoney)
						(= brunoWantsMoney TRUE)
						(messager say: N_BRUNO V_TALK C_WANTSMONEY)
					)
					(brunoWantsMoney
						(switch (= wantMoneyMsg (Random 1 2))
							(1
								(messager say: N_BRUNO V_TALK C_LOOSENLIP)
							)
							(2
								;was identical; now gives different message
								(messager say: N_BRUNO V_TALK C_GIVEMEDOUGH)
							)
						)
					)
					(else (super doVerb: theVerb &rest))
				)
			)
			(V_DO
				(cond 
					((== showedThiefSign FALSE)
						(messager say: N_BRUNO V_DO C_THIEFSIGN)
						(= showedThiefSign TRUE)
					)
					(
						(or
							(ego inRect: 212 53 256 68)
							(ego inRect: 283 107 319 130)
							(ego inRect: 123 171 172 188)
						)
						(bruno setScript: knifeEgo)
					)
					(else
						(if (ego inRect: 93 76 125 100)
							(bruno setScript: whiner)
						else
							(messager say: N_BRUNO V_DO C_ALREADYGAVESIGN)
						)
						(= brunoReadyToKnife TRUE)
					)
				)
			)
			(V_DAGGER (bruno setScript: knifeEgo))
			(V_MONEY
				(ego setMotion: MoveTo 92 104)
				((= wareList (List new:))
					add:
						((Clone Ware) name: {One Silver} price: {1})
						((Clone Ware) name: {Two Silver} price: {2})
						((Clone Ware) name: {Two Gold} price: {20})
						((Clone Ware) name: {Ten Gold} price: {100})
				)
				(switch ((ScriptID 551 0) doit:)
					(noFunds
						(HandsOff)
						(messager say: N_BRUNO V_MONEY C_NOMONEY)
					)
					(give1Silver
						(HandsOff)
						(= gotBrunoAdvice TRUE)
						(= paid1Silver TRUE)
						(= brunoGotMoney TRUE)
						(= brunoWantsMoney FALSE)
						(messager say: N_BRUNO V_MONEY C_GIVESILVER)
					)
					(give2Silvers
						(HandsOff)
						(= brunoGotMoney TRUE)
						(= brunoWantsMoney FALSE)
						(= gotBrunoAdvice TRUE)
						(= paid1Silver TRUE)
						(= paid2Silvers TRUE)
						(messager say: N_BRUNO V_MONEY C_BABAYAGA)
					)
					(give2Gold
						(HandsOff)
						(= brunoGotMoney TRUE)
						(= brunoWantsMoney FALSE)
						(= gotBrunoAdvice TRUE)
						(= paid1Silver TRUE)
						(= paid2Silvers TRUE)
						(= paid1Gold TRUE)
						(messager say: N_BRUNO V_MONEY C_BABAYAGA)
					)
					(give10Gold
						(HandsOff)
						(= brunoGotMoney TRUE)
						(= brunoWantsMoney FALSE)
						(= gotBrunoAdvice TRUE)
						(= paid1Silver TRUE)
						(= paid2Silvers TRUE)
						(= paid1Gold TRUE)
						(= paid10Gold TRUE)
						(messager say: N_BRUNO V_MONEY C_BABAYAGA)
					)
				)
				(HandsOn)
			)
			(else 
				(if (== theVerb V_FLAME)
					(curRoom doVerb: theVerb &rest)
				else
					(super doVerb: theVerb &rest)
				)
			)
		)
		(return TRUE)
	)
)

(instance brunoFlippingDagger of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bruno setLoop: 4 cel: 0 setCycle: Forward)
				(= seconds (Random 1 3))
			)
			(1
				(bruno setCycle: EndLoop)
				(= seconds (Random 2 6))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance knifeEgo of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(bruno setLoop: 5 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(1
				(knife1
					setLoop: 7
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setCycle: Forward
					posn: (+ (bruno x?) 41) (- (bruno y?) 24)
					setMotion: MoveTo (ego x?) (- (ego y?) 22)
				)
				(bruno setCycle: CycleTo 5 1 self)
			)
			(2 (= ticks (Random 30 90)))
			(3
				(bruno setCel: 0 setCycle: EndLoop self)
			)
			(4
				(knife2
					setLoop: 7
					illegalBits: 0
					ignoreActors:
					ignoreHorizon:
					xStep: 6
					yStep: 7
					init:
					setCycle: Forward
					posn: (+ (bruno x?) 41) (- (bruno y?) 27)
					setMotion: MoveTo (ego x?) (- (ego y?) 25) self
				)
			)
			(5
				(knife1 dispose:)
				(knife2 dispose:)
				(ego
					view: 516
					posn: (- (ego x?) 7) (- (ego y?) 7)
					setLoop: 1
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(6 (= seconds 3))
			(7
				(if attackedBruno
					(EgoDead 153 154 2 5 517)
				else
					(EgoDead 104 105 2 5 517)
				)
			)
		)
	)
)

(instance whiner of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bruno setCycle: EndLoop)
				(= whinerCount (Random 1 7))
				(self cue:)
			)
			(1
				(if (not (ego inRect: 93 76 125 100))
					(self dispose:)
				else
					(self cue:)
				)
			)
			(2
				(switch whinerCount
					(1 (messager say: N_BRUNO NULL C_WHINER3 1 self))
					(2 (messager say: N_BRUNO NULL C_WHINER6 1 self))
					(3 (messager say: N_BRUNO NULL C_WHINER5 1 self))
					(4 (messager say: N_BRUNO NULL C_WHINER2 1 self))
					(5 (messager say: N_BRUNO NULL C_WHINER1 1 self))
					(6 (messager say: N_BRUNO NULL C_WHINER4 1 self))
					(7
						(messager say: N_BRUNO V_DO C_OUTTAMYFACE 1 self)
					)
				)
			)
			(3 (self dispose:))
		)
	)
)

(instance sExitFromTown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst fLeftTown))
					(Bset fLeftTown)
					(++ state)
					(self cue:)
				else
					(ego setMotion: MoveTo 120 114 self)
					(Bset fLeftTown2)
				)
			)
			(1 (HandsOn) (self dispose:))
			(2
				(cSound number: 22 loop: 1 priority: 0 play:)
				(SolvePuzzle f65LeaveTown 1)
				(ego setMotion: MoveTo 75 114 self)
			)
			(3
				(messager say: N_ROOM NULL C_LEAVETOWN 1 self)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance climbTheWall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveMoveSpeed (ego moveSpeed?))
				(= saveCycleSpeed (ego cycleSpeed?))
				(ego
					moveSpeed: 6
					cycleSpeed: 6
					setMotion: MoveTo 86 92 self
				)
			)
			(1
				(if (TrySkill CLIMB 35 0)
					(ego setScript: goForIt)
				else
					(messager say: N_ROOM NULL C_CLIMBFAIL)
				)
				(self cue:)
			)
			(2
				(ego moveSpeed: saveMoveSpeed cycleSpeed: saveCycleSpeed)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goForIt of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 517
					loop: 0
					cel: 0
					posn: 83 62
					signal: (| (ego signal?) ignrHrz)
				)
				(messager say: N_ROOM NULL C_CLIMBSUCCESS 1 self)
			)
			(1
				(ego
					setPri: 15
					setLoop: 0
					moveSpeed: saveMoveSpeed
					cycleSpeed: saveCycleSpeed
					setCycle: Forward
					setMotion: DPath 82 50 81 47 self
				)
			)
			(2 (curRoom newRoom: 300))
		)
	)
)

(instance goToTown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gotBrunoAdvice
					(SolvePuzzle f65GotAdviceFromBruno 2)
					(messager say: N_BRUNO NULL C_BADADVICE 1 self)
				else
					(self cue:)
				)
			)
			(1
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance goToHealer of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gotBrunoAdvice
					(SolvePuzzle f65GotAdviceFromBruno 2)
					(messager say: N_BRUNO NULL C_BADADVICE 1 self)
				else
					(self cue:)
				)
			)
			(1
				(curRoom newRoom: 54)
			)
		)
	)
)

(instance goSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gotBrunoAdvice
					(SolvePuzzle f65GotAdviceFromBruno 2)
					(messager say: N_BRUNO NULL C_BADADVICE 1 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: MoveTo (ego x?) 240 self)
			)
			(2
				(curRoom newRoom: 74)
			)
		)
	)
)

(instance goEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gotBrunoAdvice
					(SolvePuzzle f65GotAdviceFromBruno 2)
					(messager say: N_BRUNO NULL C_BADADVICE 1 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: MoveTo 340 (ego y?) self)
			)
			(2
				(curRoom newRoom: 66)
			)
		)
	)
)

(instance comeFromEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 265 140 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance comeFromSouth of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 160 170 self)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance bruno65Talker of Talker
	(properties
		x 10
		y 10
		view 1074
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2074)
		(PalVary PALVARYTARGET 2074)
		(AssertPalette 1074)
		(= font userFont)
		(super init: brunoBust brunoEye brunoMouth &rest)
	)
)

(instance brunoBust of Prop
	(properties
		view 1074
	)
)

(instance brunoMouth of Prop
	(properties
		nsTop 47
		nsLeft 43
		view 1074
		loop 1
	)
)

(instance brunoEye of Prop
	(properties
		nsTop 26
		nsLeft 43
		view 1074
		loop 3
	)
)
