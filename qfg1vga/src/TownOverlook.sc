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
	local0
	local1
	local2
	local3
	local4
	brunoWhines
	gotBrunoAdvice
	local7
	local8
	local9
	attackedBruno
	showedThiefSign
	lookedAtBruno
	lookedAtRoad
	lookAround
	climbMoveSpeed
	climbCycleSpeed
	[local17 9] = [0 21 9 10 -20 -19 18 -17 999]
	[local26 9] = [0 13 23 14 24 22 15 -11 999]
	[local35 6] = [0 8 16 25 -12 999]
	[local41 11]
	[local52 4] = [0 -20 -17 999]
)
(procedure (localproc_00ae)
	(if (curRoom obstacles?)
		((curRoom obstacles?) dispose:)
		(curRoom obstacles: 0)
	)
	(curRoom
		addObstacle:
			((Polygon new:)
				type: 2
				init:
					102
					157
					102
					189
					0
					189
					0
					0
					319
					0
					319
					108
					234
					104
					262
					41
					239
					41
					189
					86
					123
					83
					97
					96
					83
					95
					27
					125
				yourself:
			)
			((Polygon new:)
				type: 2
				init: 173 189 203 144 319 134 319 189
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
		(= [local41 0] @local17)
		(= [local41 1] @local35)
		(= [local41 2] @local26)
		(= [local41 3] 999)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						30
						90
						8
						98
						102
						157
						102
						189
						0
						189
						0
						0
						319
						0
						319
						108
						234
						104
						262
						41
						239
						41
						189
						86
						123
						83
						97
						96
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 173 189 203 144 319 134 319 189
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
			setFeatures: rock onTheWall onTownSign deadSquare onTheRoad onTheWhat
		)
		(= brunoWhines 0)
		(if
			(and
				(Btst EXITED_TOWN_2)
				(or (== timeODay TIME_MIDDAY) (== timeODay TIME_MIDAFTERNOON))
				(or (not (Btst fBearGone)) (Btst SPIED_THIEVES))
			)
			(Load RES_VIEW 516)
			(brunoTeller init: bruno @local17 @local41 @local52)
			(bruno
				init:
				actions: brunoTeller
				setScript: brunoFlippingDagger
			)
		)
		(NormalEgo)
		(ego init:)
		(if (not (Btst fTownGateOpen)) (ego illegalBits: -32640))
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
		(if (> timeODay TIME_MIDAFTERNOON) (localproc_00ae))
		(cond 
			(script)
			((and (< (ego y?) 47) (not (ego script?))) (curRoom setScript: goToHealer))
			((and (> (ego y?) 187) (not (ego script?))) (curRoom setScript: goSouth))
			((and (> (ego x?) 317) (not (ego script?))) (curRoom setScript: goEast))
			((and (& (ego onControl: 1) $0004) (Btst fTownGateOpen)) (HandsOff) (curRoom setScript: goToTown))
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
			((== theVerb V_LOOK) (= lookAround TRUE) (messager say: N_ROOM V_LOOK 0 0 curRoom))
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
			(V_DO (messager say: N_TOWNGATE V_DO))
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
		onMeCheck $0008
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
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_WALL V_LOOK))
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
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(switch (Random 1 6)
				(1 (messager say: N_CART V_LOOK 47))
				(2 (messager say: N_CART V_LOOK 50))
				(3 (messager say: N_CART V_LOOK 49))
				(4 (messager say: N_CART V_LOOK 46))
				(5 (messager say: N_CART V_LOOK 45))
				(6 (messager say: N_CART V_LOOK 48))
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
		onMeCheck $0040
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_LOOK)
			(switch (++ lookedAtRoad)
				(1 (messager say: N_ROAD V_LOOK 47))
				(2 (messager say: N_ROAD V_LOOK 50))
				(3 (messager say: N_ROAD V_LOOK 49))
				(4 (messager say: N_ROAD V_LOOK 46))
				(5 (messager say: N_ROAD V_LOOK 45))
				(6 (messager say: N_ROAD V_LOOK 48))
			)
			(if (== lookedAtRoad 7) (= lookedAtRoad 0))
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
		onMeCheck $0010
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
		signal $4011
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
					local4
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
		(super
			showDialog: -17 local2 -19 local2 -12 local3 -11 local1
		)
	)
	
	(method (doChild)
		(if (== query -19) (Bset fLearnedRhyme))
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
					((not local9) (= local7 1) (messager say: N_BRUNO V_TALK C_WANTSMONEY))
					(local7
						(switch (= local8 (Random 1 2))
							(1 (messager say: N_BRUNO V_TALK C_LOOSENLIP))
							(2 (messager say: N_BRUNO V_TALK C_GIVEMEDOUGH)) ;was identical; now gives different message
						)
					)
					(else (super doVerb: theVerb &rest))
				)
			)
			(V_DO
				(cond 
					((== showedThiefSign FALSE) (messager say: N_BRUNO V_DO C_THIEFSIGN) (= showedThiefSign TRUE))
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
						(= local4 1)
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
						(= local0 1)
						(= local9 1)
						(= local7 0)
						(messager say: N_BRUNO V_MONEY C_GIVESILVER)
					)
					(give2Silvers
						(HandsOff)
						(= local9 1)
						(= local7 0)
						(= gotBrunoAdvice TRUE)
						(= local0 1)
						(= local1 1)
						(messager say: N_BRUNO V_MONEY C_BABAYAGA)
					)
					(give2Gold
						(HandsOff)
						(= local9 1)
						(= local7 0)
						(= gotBrunoAdvice TRUE)
						(= local0 1)
						(= local1 1)
						(= local2 1)
						(messager say: N_BRUNO V_MONEY C_BABAYAGA)
					)
					(give10Gold
						(HandsOff)
						(= local9 1)
						(= local7 0)
						(= gotBrunoAdvice TRUE)
						(= local0 1)
						(= local1 1)
						(= local2 1)
						(= local3 1)
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
	(properties)
	
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
	(properties)
	
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bruno setCycle: EndLoop)
				(= brunoWhines (Random 1 7))
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
				(switch brunoWhines
					(1 (messager say: N_BRUNO 0 C_WHINER3 1 self))
					(2 (messager say: N_BRUNO 0 C_WHINER6 1 self))
					(3 (messager say: N_BRUNO 0 C_WHINER5 1 self))
					(4 (messager say: N_BRUNO 0 C_WHINER2 1 self))
					(5 (messager say: N_BRUNO 0 C_WHINER1 1 self))
					(6 (messager say: N_BRUNO 0 C_WHINER4 1 self))
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst EXITED_TOWN))
					(Bset EXITED_TOWN)
					(++ state)
					(self cue:)
				else
					(ego setMotion: MoveTo 120 114 self)
					(Bset EXITED_TOWN_2)
				)
			)
			(1 (HandsOn) (self dispose:))
			(2
				(cSound number: 22 loop: 1 priority: 0 play:)
				(SolvePuzzle POINTS_LEAVETOWNFIRSTTIME 1)
				(ego setMotion: MoveTo 75 114 self)
			)
			(3
				(messager say: N_ROOM 0 C_LEAVETOWN 1 self)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance climbTheWall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= climbMoveSpeed (ego moveSpeed?))
				(= climbCycleSpeed (ego cycleSpeed?))
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
					(messager say: N_ROOM 0 C_CLIMBFAIL)
				)
				(self cue:)
			)
			(2
				(ego moveSpeed: climbMoveSpeed cycleSpeed: climbCycleSpeed)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance goForIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 517
					loop: 0
					cel: 0
					posn: 83 62
					signal: (| (ego signal?) $2000)
				)
				(messager say: N_ROOM 0 C_CLIMBSUCCESS 1 self)
			)
			(1
				(ego
					setPri: 15
					setLoop: 0
					moveSpeed: climbMoveSpeed
					cycleSpeed: climbCycleSpeed
					setCycle: Forward
					setMotion: DPath 82 50 81 47 self
				)
			)
			(2 (curRoom newRoom: 300))
		)
	)
)

(instance goToTown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gotBrunoAdvice
					(SolvePuzzle POINTS_RECEIVEBADADVICEFROMBRUNO 2)
					(messager say: N_BRUNO 0 C_BADADVICE 1 self)
				else
					(self cue:)
				)
			)
			(1 (curRoom newRoom: 300))
		)
	)
)

(instance goToHealer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gotBrunoAdvice
					(SolvePuzzle POINTS_RECEIVEBADADVICEFROMBRUNO 2)
					(messager say: N_BRUNO 0 C_BADADVICE 1 self)
				else
					(self cue:)
				)
			)
			(1 (curRoom newRoom: 54))
		)
	)
)

(instance goSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gotBrunoAdvice
					(SolvePuzzle POINTS_RECEIVEBADADVICEFROMBRUNO 2)
					(messager say: N_BRUNO 0 C_BADADVICE 1 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: MoveTo (ego x?) 240 self)
			)
			(2 (curRoom newRoom: 74))
		)
	)
)

(instance goEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if gotBrunoAdvice
					(SolvePuzzle POINTS_RECEIVEBADADVICEFROMBRUNO 2)
					(messager say: N_BRUNO 0 C_BADADVICE 1 self)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: MoveTo 340 (ego y?) self)
			)
			(2 (curRoom newRoom: 66))
		)
	)
)

(instance comeFromEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 265 140 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance comeFromSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 160 170 self)
			)
			(1 (HandsOn) (self dispose:))
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
		(kernel_128 1074)
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
