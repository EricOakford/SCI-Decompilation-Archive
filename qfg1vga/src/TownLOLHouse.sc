;;; Sierra Script 1.0 - (do not remove this comment)
(script# 313)
(include game.sh) (include "313.shm")
(use Main)
(use Procs)
(use PAvoid)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Smooper)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm313 0
)

(local
	local0
	local1
	local2
	timesPettedCat
	local4
	local5
	wentUpstairs
	pettedCat
	fedCat
	local9
	birdcageUncovered
	attackedCat
	local12
	[local13 2]
	lootCue
)
(enum 1
	cuePurse
	cueBasket
	cueCouch
)

(procedure (PetCat)
	(SolvePuzzle POINTS_PETLOLCAT 3 THIEF)
	(ego setScript: catPetOrFeed1)
)

(procedure (SearchPurse)
	(if (Btst SEARCHED_LOL_PURSE)
		(AlreadyDone)
	else
		(messager say: N_ROOM 0 C_SEARCHPURSE)
		(= lootCue cuePurse)
		(Bset SEARCHED_LOL_PURSE)
		(SolvePuzzle POINTS_SEARCHLOLPURSE 1 THIEF)
		(ego setScript: cuedIt)
	)
)

(procedure (SearchBasket)
	(if (Btst SEARCHED_LOL_BASKET)
		(AlreadyDone)
	else
		(messager say: N_ROOM 0 C_SEARCHBASKET)
		(= lootCue cueBasket)
		(Bset SEARCHED_LOL_BASKET)
		(SolvePuzzle POINTS_SEARCHLOLBASKET 1 THIEF)
		(ego setScript: cuedIt)
	)
)

(procedure (UncoverBirdcage)
	(if (Btst UNCOVERED_BIRDCAGE)
		(AlreadyDone)
	else
		(ego setScript: birdieSings)
	)
)

(instance catTurn of SmoothLooper
	(properties
		vChangeDir 637
	)
)

(instance rm313 of Room
	(properties
		noun N_ROOM
		picture 313
		style WIPELEFT
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						25
						189
						242
						189
						319
						163
						314
						143
						281
						146
						220
						128
						200
						118
						184
						120
						156
						74
						136
						68
						176
						133
						149
						134
						95
						117
						20
						135
						8
						189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 189 246 189 319 167
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 162 156 162 177 134 187 109 178 103 165
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 105 127 120 135 124 152 77 170 64 170 38 150
					yourself:
				)
		)
		(LoadMany RES_VIEW 313 635 636 525)
		(Load RES_SCRIPT 927)
		(Load RES_SOUND 8)
		(Load RES_SOUND (SoundFX 52))
		(super init:)
		(SolvePuzzle POINTS_ENTERLOLHOUSE 5 2)
		(= timesPettedCat 0)
		(self setFeatures: onPlant onTable onDoor onStorageBox)
		(onDrawer init:)
		(onStair init:)
		(onChair init:)
		(onFirePlace init:)
		(onRug init:)
		(onTableFood init:)
		(onCouch
			approachVerbs:
				4
				34
				42
				44
				46
				16
				38
				21
				36
				39
				32
				29
				37
				22
				26
				14
				17
				27
				23
				31
				30
				40
				43
				45
				53
				11
				28
				20
				35
				15
				10
				24
				12
				18
				19
				47
				41
				33
			init:
		)
		(onPlant
			approachVerbs:
				4
				34
				42
				44
				46
				16
				38
				21
				36
				39
				32
				29
				37
				22
				26
				14
				17
				27
				23
				31
				30
				40
				43
				45
				53
				11
				28
				20
				35
				15
				10
				24
				12
				18
				19
				47
				41
				33
		)
		(NormalEgo)
		(ego
			posn: 159 189
			init:
			ignoreControl: -32768
			setAvoider: PAvoider
			setScript: enterRoom
		)
		(= deathMusic (SoundFX 52))
		(cat
			init:
			setStep: 2 1
			setAvoider: PAvoider
			setCycle: Walk
			setScript: catWalk
		)
		(lamp1 cel: 0 init: setCycle: Forward)
		(lampLight cel: 0 init: setCycle: Forward)
		(cageStand init:)
		(theCandles init: setCycle: Forward)
		(purse
			cel: 2
			approachVerbs:
				4
				34
				42
				44
				46
				16
				38
				21
				36
				39
				32
				29
				37
				22
				26
				14
				17
				27
				23
				31
				30
				40
				43
				45
				53
				11
				28
				20
				35
				15
				10
				24
				12
				18
				19
				47
				41
				33
			init:
		)
		(birdcage cel: 5 init:)
		(drawer init:)
		(bag
			cel: 1
			approachVerbs:
				4
				34
				42
				44
				46
				16
				38
				21
				36
				39
				32
				29
				37
				22
				26
				14
				17
				27
				23
				31
				30
				40
				43
				45
				53
				11
				28
				20
				35
				15
				10
				24
				12
				18
				19
				47
				41
				33
			init:
		)
		(if (not (Btst STOLE_CANDLESTICKS))
			(candle1 cel: 0 ignoreActors: init:)
			(candle2 cel: 0 ignoreActors: init:)
		)
		(self setRegions: TOWN)
		(self setScript: first313Script)
		(sillyThief init: play:)
		(Load RES_SCRIPT SMOOPER)
		(Load RES_SCRIPT PAVOID)
	)
	
	(method (doit)
		(cond 
			((ego script?) 0)
			(
				(and
					(& (ego onControl: origin) $4000)
					(< (ego distanceTo: cat) 25)
					(not local2)
					(not local9)
				)
				(= local2 1)
				(= local4 3)
				(ego setScript: gonnaGetYou)
			)
			((> (ego y?) 187)
				(if (not Night)
					(EgoDead 52 53)
				else
					(cat dispose:)
					(= dayLOLBreakIn Day)
					(curRoom newRoom: 310)
				)
			)
			(
				(and
					(& (ego onControl: origin) $2000)
					(or
						(== (ego loop?) 3)
						(== (ego loop?) 6)
						(== (ego loop?) 7)
					)
					(!= local1 1)
				)
				(= local1 1)
				(ego setMotion: 0 setScript: climbUp1)
			)
			(
				(and
					(& (ego onControl: origin) $1000)
					(or
						(== (ego loop?) 3)
						(== (ego loop?) 6)
						(== (ego loop?) 7)
					)
					(!= local1 2)
				)
				(= local1 2)
				(ego setMotion: 0 setScript: climbUp2)
			)
			(
				(and
					(& (ego onControl: origin) $0800)
					(or
						(== (ego loop?) 3)
						(== (ego loop?) 6)
						(== (ego loop?) 7)
					)
					(!= local1 3)
				)
				(= local1 3)
				(ego setMotion: 0 setScript: climbUp3)
			)
			((== (ego onControl: origin) 1024) (= local1 0))
		)
		(super doit:)
	)
	
	(method (dispose)
		(ego observeControl: -32768)
		(DisposeScript PAVOID)
		(DisposeScript SMOOPER)
		(super dispose:)
	)
	
	(method (newRoom newRoomNumber)
		(Bset fBeenIn313)
		(= deathMusic (SoundFX 26))
		(super newRoom: newRoomNumber)
	)
)

(instance onStorageBox of Feature
	(properties
		x 31
		y 115
		noun N_STORAGEBOX
		nsTop 106
		nsLeft 17
		nsBottom 125
		nsRight 45
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_STORAGEBOX V_LOOK))
			(V_DO (messager say: N_CHEST V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onChair of Feature
	(properties
		x 223
		y 104
		noun N_CHAIR
		onMeCheck $0020
	)
)

(instance onStair of Feature
	(properties
		x 159
		y 94
		noun N_STAIRS
		onMeCheck $0080
	)
)

(instance onDoor of Feature
	(properties
		x 110
		y 38
		noun N_STAIRS
		nsTop 14
		nsLeft 99
		nsBottom 63
		nsRight 121
	)
)

(instance onDrawer of Feature
	(properties
		x 61
		y 122
		z 40
		noun N_DRAWER
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (ego setScript: SearchDesk))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onFirePlace of Feature
	(properties
		x 268
		y 115
		noun N_FIREPLACE
		onMeCheck $0008
	)
)

(instance onTable of Feature
	(properties
		x 134
		y 180
		z 20
		noun N_TABLE
		nsTop 153
		nsLeft 117
		nsBottom 168
		nsRight 152
	)
)

(instance onPlant of Feature
	(properties
		x 153
		y 45
		noun N_PLANT
		nsTop 21
		nsLeft 144
		nsBottom 69
		nsRight 162
		approachX 142
		approachY 72
	)
)

(instance onRug of Feature
	(properties
		x 180
		y 174
		noun N_RUG
		onMeCheck $0040
	)
)

(instance onCouch of Feature
	(properties
		x 85
		y 130
		noun N_COUCH
		onMeCheck $0004
		approachX 100
		approachY 167
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (Btst SEARCHED_LOL_COUCH)
					(messager say: N_COUCH V_DO C_ALREADYSEARCHEDCOUCH)
				else
					(messager say: N_COUCH V_DO C_SEARCHCOUCH)
					(= lootCue cueCouch)
					(Bset SEARCHED_LOL_COUCH)
					(SolvePuzzle POINTS_SEARCHLOLCOUCH 1 THIEF)
					(ego setScript: cuedIt)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance onTableFood of Feature
	(properties
		x 307
		y 181
		noun N_FRUIT
		onMeCheck $0010
	)
)

(instance cageStand of View
	(properties
		x 209
		y 96
		noun N_CAGESTAND
		approachX 200
		approachY 120
		view 313
		loop 3
		priority 6
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (birdcage doVerb: V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance candle1 of View
	(properties
		x 71
		y 73
		noun N_CANDLE1
		approachX 66
		approachY 124
		view 313
		loop 2
		priority 10
		signal $0011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (Btst STOLE_CANDLESTICKS)
					(AlreadyDone)
				else
					(curRoom setScript: toTheCandles)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance candle2 of View
	(properties
		x 55
		y 74
		noun N_CANDLE2
		approachX 66
		approachY 124
		view 313
		loop 2
		priority 10
		signal $0011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (candle1 doVerb: V_DO))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance purse of View
	(properties
		x 72
		y 134
		noun N_PURSE
		approachX 100
		approachY 167
		view 313
		loop 2
		cel 2
		priority 12
		signal $0011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (SearchPurse))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bag of View
	(properties
		x 74
		y 154
		noun N_BAG
		approachX 100
		approachY 167
		view 313
		loop 2
		cel 1
		priority 10
		signal $0011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (SearchBasket))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance birdcage of Prop
	(properties
		x 209
		y 96
		noun N_BIRDCAGE
		approachX 200
		approachY 120
		view 313
		loop 5
		priority 6
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (ego setScript: removeCover))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lamp1 of Prop
	(properties
		x 262
		y 75
		noun N_LAMP1
		view 313
		loop 8
		priority 7
		signal $4011
	)
)

(instance lampLight of Prop
	(properties
		x 283
		y 97
		noun N_LAMPLIGHT
		view 313
		loop 6
		priority 6
		signal $4011
	)
)

(instance theCandles of Prop
	(properties
		x 245
		y 37
		noun N_CANDLES
		view 313
		loop 7
		priority 14
		signal $0010
	)
)

(instance drawer of Prop
	(properties
		x 64
		y 95
		noun N_DRAWER
		approachX 101
		approachY 124
		view 313
		loop 1
		priority 5
		signal $0011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (ego setScript: SearchDesk))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cat of Actor
	(properties
		x 266
		y 160
		noun N_CAT
		view 635
	)
	
	(method (dispose)
		(self
			setScript: 0
			setLoop: 0
			setAvoider: 0
			setCycle: 0
			looper: 0
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(= pettedCat TRUE)
				(PetCat)
			)
			(V_RATIONS
				(= fedCat TRUE)
				(PetCat)
			)
			(V_DAGGER	;Can't do this; gives "Despite your intentions..." message (as per Town region)
				(= attackedCat TRUE)
				(ego setMotion: 0)
				(HandsOff)
				(cat setScript: gonnaGetYou)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance birdieSings of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 450])
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset UNCOVERED_BIRDCAGE)
				(= birdcageUncovered TRUE)
				(birdcage loop: 5 cel: 0 setCycle: EndLoop self startUpd:)
			)
			(1
				(birdcage loop: 4 setCel: 0 setCycle: EndLoop)
				(= cycles 2)
			)
			(2 (messager say: N_BIRDIESINGS 0 C_CHIRP 1 self))
			(3 (messager say: N_BIRDIESINGS 0 C_DONTBOTHERBIRD 1 self))
			(4 (messager say: N_BIRDIESINGS 0 C_NOISYBIRD 1 self))
			(5 (messager say: N_BIRDIESINGS 0 C_COVERCAGE 1 self))
			(6
				(birdcage loop: 5 setCel: 3 setCycle: BegLoop self)
			)
			(7
				(birdcage stopUpd:)
				(= birdcageUncovered FALSE)
				(HandsOn)
				(ego ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance deskOpen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(drawer setCycle: EndLoop self)
			)
			(1 (= ticks 60))
			(2
				(messager say: N_OPENDRAWER 0 C_FIND1SILVER 1 self)
			)
			(3
				(ego get: iSilver 1)
				(Bset SEARCHED_LOL_DESK)
				(drawer setCycle: BegLoop self)
			)
			(4
				(HandsOn)
				(ego ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance first313Script of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 400])
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if (or (not (Btst fBeenIn313)) (!= prevRoomNum 0))
					(messager say: N_ROOM 0 C_FIRSTENTRY 1 self)
				else
					(client setScript: 0)
				)
			)
			(2
				(if (or (not (Btst fBeenIn313)) (!= prevRoomNum 0))
					(messager say: N_ROOM 0 C_FIRSTENTRY2 1 self)
				)
				(client setScript: 0)
			)
		)
	)
)

(instance catWalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cat setLoop: 8 setAvoider: PAvoider setMotion: 0)
				(self cue:)
			)
			(1
				(if (not (cat looper?)) (cat setLoop: catTurn))
				(cat
					setLoop: 0
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 278 153 self
				)
			)
			(2
				(cat setLoop: 4 cel: 0 setMotion: MoveTo 229 135 self)
			)
			(3
				(cat setLoop: 2 cel: 0 setMotion: MoveTo 201 143 self)
			)
			(4
				(= state 0)
				(cat setLoop: 0 cel: 0 setMotion: MoveTo 266 160 self)
			)
		)
	)
)

(instance catPetOrFeed1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cat setMotion: 0 setScript: 0)
				(ego
					setMotion: PolyPath (+ (cat x?) 15) (+ (cat y?) 15) self
				)
			)
			(1 (self cue:))
			(2
				(Face ego cat)
				(= cycles 2)
			)
			(3
				(= local9 1)
				(if pettedCat
					(switch timesPettedCat
						(0
							(messager say: N_CATNEEDY 0 C_PETCAT1 1 self)
							(= timesPettedCat 1)
						)
						(1
							(messager say: N_CATNEEDY 0 C_PETCAT2 1 self)
							(= timesPettedCat 2)
						)
						(2
							(messager say: N_CATNEEDY 0 C_PETCAT3 1 self)
						)
					)
				)
				(if fedCat
					(switch timesPettedCat
						(0
							(messager say: N_CATNEEDY 0 C_FEEDCAT2 1 self)
							(= timesPettedCat 1)
						)
						(1
							(messager say: N_CATNEEDY 0 C_FEEDCAT3 1 self)
							(= timesPettedCat 2)
						)
						(2
							(if (ego has: iRations)
								(messager say: N_CATNEEDY 0 C_FEEDCAT1 1 self)
							else
								(messager say: N_CATNEEDY 0 C_NORATIONS 1 self)
							)
						)
					)
				)
			)
			(4
				(if (and (== timesPettedCat 2) (ego has: iRations)) (ego use: iRations 1))
				(= fedCat 0)
				(= pettedCat 0)
				(HandsOn)
				(ego ignoreControl: -32768)
				(= seconds 5)
			)
			(5
				(cat setScript: catWalk)
				(= local9 0)
				(self dispose:)
			)
		)
	)
)

(instance catChasesEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local2 1)
				(= seconds 0)
				(if (not (cat looper?)) (cat setLoop: catTurn))
				(self cue:)
			)
			(1
				(= local2 0)
				(++ local4)
				(cat setCycle: EndLoop setMotion: 0)
				(= cycles 2)
			)
			(2 (messager say: N_CATATTACKS 0 C_CATGROWLS 1 self))
			(3 (ego setScript: catAttack))
		)
	)
)

(instance gonnaGetYou of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (cat looper?)) (cat setLoop: catTurn))
				(cat setMotion: 0)
				(if Night
					(if (== attackedCat TRUE)
						(messager say: N_CATTURNS 0 C_ATTACKEDCAT 1 self)
					else
						(messager say: N_CATTURNS 0 C_PROVOKEDCAT 1 self)
					)
				else
					(self cue:)
				)
			)
			(1
				(ego
					illegalBits: 0
					ignoreActors:
					setCycle: Walk
					setMotion: MoveTo 155 140 self
				)
			)
			(2
				(cat
					loop: 1
					ignoreActors:
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 189 154 self
				)
			)
			(3
				(ego setScript: catChasesEgo)
			)
		)
	)
)

(instance catAttack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 156 143 self)
			)
			(1
				(ego view: 525 setLoop: 4 cel: 0)
				(self cue:)
			)
			(2
				(cat
					view: 635
					illegalBits: 0
					ignoreActors:
					looper: 0
					setLoop: 7
					cel: 0
					posn: 189 154
					setCycle: EndLoop self
				)
				(ego setCycle: Forward setMotion: MoveTo 126 141)
			)
			(3
				(cat
					view: 636
					loop: 0
					cel: 0
					posn: 189 154
					setPri: 10
					cycleSpeed: 12
					setCycle: CycleTo 5 1 self
				)
			)
			(4
				(ego posn: 1000 1000)
				(cat setCel: 5 setCycle: CycleTo 8 1 self)
			)
			(5
				(cat setCel: 8 setCycle: CycleTo 10 1 self)
			)
			(6
				(cat view: 636 loop: 1 cel: 0 posn: 111 128 setCycle: Forward)
				(= seconds 5)
			)
			(7
				(if wentUpstairs
					(EgoDead 112 113 1 0 637)
				else
					(EgoDead 110 111 1 0 637)
				)
			)
		)
	)
)

(instance getReady of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (cat looper?)) (cat setLoop: catTurn))
				(cat
					loop: 4
					setCycle: Walk
					setMotion: MoveTo 189 154 self
				)
			)
			(1
				(ego setScript: catAttack)
				(= seconds 2)
			)
			(2 (self dispose:))
		)
	)
)

(instance climbUp1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local5 1)
				(ego setLoop: 1 illegalBits: 0)
				(self cue:)
			)
			(1
				(ego setLoop: -1 illegalBits: -32768)
				(messager say: 14 0 18 1 self)
			)
			(2
				(= local12 1)
				(HandsOn)
				(ego ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance climbUp2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setLoop: 1)
				(self cue:)
			)
			(1
				(ego setLoop: -1 illegalBits: -32768)
				(messager say: 15 0 19 1 self)
			)
			(2
				(HandsOn)
				(ego ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance climbUp3 of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 450])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setLoop: 1)
				(self cue:)
			)
			(1
				(HandsOn)
				(ego
					setLoop: -1
					ignoreControl: -32768
					illegalBits: -32768
				)
				(messager say: 16 0 23 1 self)
			)
			(2
				(messager say: 16 0 20 1 self)
			)
			(3
				(messager say: 16 0 22 1 self)
				(= wentUpstairs TRUE)
			)
			(4 (ego setScript: climbDown))
		)
	)
)

(instance climbDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cat setScript: getReady)
				(= local1 0)
				(ego
					illegalBits: 0
					setLoop: 2
					setMotion: MoveTo 182 127 self
				)
			)
			(1
				(if wentUpstairs
					(messager say: 13 0 16 1 self)
				else
					(HandsOn)
					(ego
						setLoop: -1
						ignoreControl: -32768
						illegalBits: -32768
					)
					(ego setScript: 0)
					(cat setScript: catWalk)
				)
			)
			(2
				(if wentUpstairs (ego setLoop: -1))
			)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 172 181 self)
			)
			(1
				(HandsOn)
				(ego ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance SearchDesk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (== (ego x?) 76) (== (ego y?) 130))
					(self cue:)
				else
					(ego
						ignoreControl: -32768
						setMotion: PolyPath 46 179 self
					)
				)
			)
			(1
				(ego setMotion: PolyPath 76 130 self)
			)
			(2
				(if (Btst 134)
					(messager say: N_ROOM 0 32 1 self)
				else
					(SolvePuzzle 624 1 2)
					(ego setScript: deskOpen)
				)
			)
			(3
				(ego observeControl: -32768)
				(HandsOn)
				(ego ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance removeCover of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local2 0)
				(ego setMotion: PolyPath 200 120 self)
			)
			(1
				(UncoverBirdcage)
				(= seconds 3)
			)
			(2
				(HandsOn)
				(ego ignoreControl: -32768)
				(= local2 1)
				(self dispose:)
			)
		)
	)
)

(instance toTheCandles of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego get: iCandlesticks 1)
				(HandsOff)
				(if (and (== (ego x?) 76) (== (ego y?) 130))
					(self cue:)
				else
					(ego
						ignoreControl: -32768
						setMotion: PolyPath 46 179 self
					)
				)
			)
			(1
				(ego setMotion: PolyPath 76 130 self)
			)
			(2 (messager say: 5 4 7 1 self))
			(3
				(candle1 dispose:)
				(candle2 dispose:)
				(Bset STOLE_CANDLESTICKS)
				(SolvePuzzle POINTS_TAKECANDLESTICKS 1 THIEF)
				(= ticks 60)
			)
			(4
				(HandsOn)
				(ego ignoreControl: -32768)
				(self dispose:)
			)
		)
	)
)

(instance cuedIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 60))
			(1
				(switch lootCue
					(cuePurse (ego get: iSilver 20))
					(cueBasket (ego get: iPearls))
					(cueCouch (ego get: iSilver 3))
				)
				(self cue:)
			)
			(2 (self dispose:))
		)
	)
)

(instance sillyThief of Sound
	(properties
		number 8
		loop -1
	)
)
