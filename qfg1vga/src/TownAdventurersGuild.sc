;;; Sierra Script 1.0 - (do not remove this comment)
(script# 311)
(include game.sh) (include "311.shm")
(use Main)
(use Teller)
(use Procs)
(use Print)
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
	rm311 0
	wolfgangTalker 1
)

(local
	masterTellMainBranch = [
		STARTTELL
		-33		;C_GUILDHALL
		-45		;C_NAME
		-26		;C_CURSE
		-53		;C_SPIELBURG
		-51		;C_SCHULTZ
		ENDTELL
		]
	masterTell1 = [
		STARTTELL
		C_ADVENTURERS
		-42		;C_MONSTERS
		-20		;C_BULLETINBOARD
		-58		;C_TROPHIES
		ENDTELL
		]
	masterTell2 = [
		STARTTELL
		C_GUILDMASTER
		ENDTELL
		]
	masterTell3 = [
		STARTTELL
		C_BARON
		C_BARON_SON
		-27		;C_BARON_DAUGHTER
		-17		;C_BABAYAGA
		C_BRIGANDS
		ENDTELL
		]
	masterTell4 = [
		STARTTELL
		C_MOUNTAINS
		C_FORESTS
		C_WATERFALLS
		-56		;C_TOWN
		ENDTELL
		]
	masterTell5 = [
		STARTTELL
		-47		;C_OTTO
		ENDTELL
		]
	masterTell6 = [
		STARTTELL
		C_TROLL
		C_SAURUS
		C_OGRES
		C_GOBLINS
		C_CHEETAUR
		C_MANTRAYS
		C_SAURUSREX
		ENDTELL
		]
	masterTell7 = [
		STARTTELL
		C_MAGICSHOP
		C_DRYGOODS
		-54		;C_TAVERN
		C_BAKERY
		C_BUTCHER
		-39		;C_FARMERS_MART
		ENDTELL
		]
	masterTell8 = [
		STARTTELL
		C_REWARD
		C_HEALER
		C_CASTLE
		ENDTELL
		]
	masterTell9 = [
		STARTTELL
		C_YORICK
		ENDTELL
		]
	masterTell10 = [
		STARTTELL
		C_HERO
		ENDTELL
		]
	masterTell11 = [
		STARTTELL
		C_DRAGON
		-16		;C_ANTWERP
		C_MOOSE
		C_TROLL_TROPHY
		C_CHEETAUR_TROPHY
		-50		;C_SAURUS_TROPHY
		ENDTELL
		]
	masterTell12 = [
		STARTTELL
		C_TOURISTS
		ENDTELL
		]
	masterTell13 = [
		STARTTELL
		C_HALFWITTEN
		ENDTELL
		]
	masterTell14 = [
		STARTTELL
		C_CENTAUR
		ENDTELL
		]
	masterTell15 = [
		STARTTELL
		C_GOON
		ENDTELL
		]
	masterTell16 = [
		STARTTELL
		C_GOON
		ENDTELL
		]
	[masterTellTree 19]
	masterTellKeys = [
		STARTTELL
		-33		;C_GUILDHALL
		-45		;C_NAME
		-26		;C_CURSE
		-53		;C_SPIELBURG
		-51		;C_SCHULTZ
		-42		;C_MONSTERS
		-56		;C_TOWN
		-20		;C_BULLETINBOARD
		-27		;C_BARON_DAUGHTER
		-17		;C_BABAYAGA
		-58		;C_TROPHIES
		-16		;C_ANTWERP
		-50		;C_SAURUS_TROPHY
		-39		;C_FARMERS_MART
		-54		;C_TAVERN
		-47		;C_OTTO
		ENDTELL
		]
	snoreCount
	armTimer
	saidHello
	wokenUp
	local626
	dozingOff
	logbookPage
	readUnsigned
	local630
)
(procedure (LookRoom)
	(messager say: N_ROOM)
)

(instance rm311 of Room
	(properties
		noun N_ROOM
		picture 311
		style WIPELEFT
		east 310
	)
	
	(method (init)
		(= [masterTellTree 0] @masterTellMainBranch)
		(= [masterTellTree 1] @masterTell1)
		(= [masterTellTree 2] @masterTell2)
		(= [masterTellTree 3] @masterTell3)
		(= [masterTellTree 4] @masterTell4)
		(= [masterTellTree 5] @masterTell5)
		(= [masterTellTree 6] @masterTell6)
		(= [masterTellTree 7] @masterTell7)
		(= [masterTellTree 8] @masterTell8)
		(= [masterTellTree 9] @masterTell9)
		(= [masterTellTree 10] @masterTell10)
		(= [masterTellTree 11] @masterTell11)
		(= [masterTellTree 12] @masterTell12)
		(= [masterTellTree 13] @masterTell13)
		(= [masterTellTree 14] @masterTell14)
		(= [masterTellTree 15] @masterTell15)
		(= [masterTellTree 16] @masterTell16)
		(= [masterTellTree 17] ENDTELL)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 143
						145 113
						114 121
						106 139
						80 148
						43 129
						3 139
						3 185
						319 185
						319 189
						0 189
						0 0
						319 0
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						200 158
						173 173
						86 158
						123 144
					yourself:
				)
		)
		(LoadMany VIEW 311 312)
		(= logbookPage 0)
		(if Night (aWindow init:))
		(book init:)
		(desk init: approachVerbs: V_DO)
		(noteSign init:)
		(theWindow init:)
		(smallWindow init:)
		(features
			add:
				gryphon
				wood
				armor
				saurusHead
				mooseHead
				antwerpHead
				cheetaurHead
				trollHead
				dragonHead
				painting
				wallOfStuff
			eachElementDo: #init
		)
		;UPGRADE
;;;		(gryphon init:)
;;;		(wood init:)
;;;		(armor init:)
;;;		(saurusHead init:)
;;;		(mooseHead init:)
;;;		(antwerpHead init:)
;;;		(cheetaurHead init:)
;;;		(trollHead init:)
;;;		(dragonHead init:)
;;;		(painting init:)
;;;		(wallOfStuff init:)
		
		(super init: &rest)
		(self setRegions: TOWN)
		(theCandles init: setCycle: Forward)
		(chair init: setPri: 5)
		(masterTeller init: master @masterTellMainBranch @masterTellTree @masterTellKeys)
		(master
			init:
			actions: masterTeller
			setLoop: 2
			setPri: 6
			posn: 76 137
			setScript: sMaster
		)
		(theFire init: setCycle: Forward)
		(snore init:)
		(NormalEgo)
		(if (== prevRoomNum 318)
			(ego init: posn: 164 119 loop: 3)
			(HandsOn)
		else
			(cSound number: 110 loop: -1 init: play:)
			(ego init: setLoop: 1 posn: 276 148)
			(NormalEgo)
		)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(ego inRect: 64 120 126 162)
					(== wokenUp FALSE)
					(== (master script?) sMaster)
				)
				(master setScript: upAndAtIm)
			)
			(
				(and
					(not (ego inRect: 64 120 126 162))
					(== wokenUp TRUE)
				)
				(masterHead ignoreActors: posn: 101 104 show:)
				(master setScript: sMaster)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= nightPalette 0)
		(Bset fBeenIn311)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROOM)
			)
			(V_DO
				(messager say: N_ROOM V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance smallWindow of Feature
	(properties
		x 81
		y 92
		noun N_SMALLWINDOW
		nsTop 82
		nsLeft 77
		nsBottom 89
		nsRight 86
		sightAngle 40
		approachX 119
		approachY 119
	)
)

(instance gryphon of Feature
	(properties
		x 116
		y 103
		z 65
		noun N_GRYPHON
		nsTop 23
		nsLeft 101
		nsBottom 54
		nsRight 131
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_GRYPHON V_LOOK)
			)
			(V_DO
				(messager say: N_ROOM V_DO C_DONT_TAKE_TROPHIES)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance wood of Feature
	(properties
		x 289
		y 123
		noun N_WOOD
		nsTop 114
		nsLeft 269
		nsBottom 133
		nsRight 309
		sightAngle 40
	)
)

(instance desk of Feature
	(properties
		x 148
		y 148
		noun N_DESK
		nsTop 130
		nsLeft 98
		nsBottom 167
		nsRight 198
		sightAngle 40
		approachX 149
		approachY 167
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(switch (Random 1 2)
					(1
						(messager say: N_DESK V_LOOK C_LOOKDESK1)
					)
					(2
						(messager say: N_DESK V_LOOK C_LOOKDESK2)
					)
				)
			)
			(V_DO
				(messager say: N_DESK V_DO)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance book of Feature
	(properties
		x 163
		y 243
		z 100
		heading 360
		noun N_BOOK
		onMeCheck cBLUE
		approachX 149
		approachY 167
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(ego setScript: signTheBook)
			)
			(V_LOOK
				(ego setScript: youGotPoints)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance noteSign of Feature
	(properties
		x 164
		y 110
		z 27
		nsTop 72
		nsLeft 151
		nsBottom 94
		nsRight 178
		sightAngle 40
		approachX 164
		approachY 119
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_DO V_LOOK)
			(ego setScript: goToBoard)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance armor of Feature
	(properties
		x 126
		y 90
		noun N_ARMOR
		nsTop 71
		nsLeft 118
		nsBottom 109
		nsRight 135
		sightAngle 40
	)
)

(instance antwerpHead of Feature
	(properties
		x 295
		y 127
		z 67
		noun N_ANTWERPHEAD
		nsTop 48
		nsLeft 286
		nsBottom 72
		nsRight 305
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: N_ROOM V_DO C_DONT_TAKE_TROPHIES)
			)
			(V_LOOK
				(messager say: N_ANTWERPHEAD V_LOOK)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theWindow of Feature
	(properties
		x 58
		y 90
		z 60
		noun N_WINDOW
		nsTop 23
		nsLeft 58
		nsBottom 94
		nsRight 93
		sightAngle 40
		approachX 119
		approachY 119
	)
)

(instance saurusHead of Feature
	(properties
		x 52
		y 124
		z 54
		noun N_SAURUSHEAD
		nsTop 62
		nsLeft 44
		nsBottom 79
		nsRight 61
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_SAURUSHEAD V_LOOK)
			)
			(V_DO
				(messager say: N_ROOM V_DO C_DONT_TAKE_TROPHIES)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mooseHead of Feature
	(properties
		x 54
		y 122
		z 86
		noun N_MOOSEHEAD
		nsTop 24
		nsLeft 42
		nsBottom 48
		nsRight 66
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(messager say: N_ROOM V_DO C_DONT_TAKE_TROPHIES)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance trollHead of Feature
	(properties
		x 199
		y 115
		z 78
		noun N_TROLLHEAD
		nsTop 29
		nsLeft 191
		nsBottom 46
		nsRight 207
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_TROLLHEAD V_LOOK)
			)
			(V_DO
				(messager say: N_ROOM V_DO C_DONT_TAKE_TROPHIES)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dragonHead of Feature
	(properties
		x 223
		y 115
		z 92
		noun N_DRAGONHEAD
		nsTop 7
		nsLeft 205
		nsBottom 39
		nsRight 241
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_DRAGONHEAD V_LOOK)
			)
			(V_DO
				(messager say: N_ROOM V_DO C_DONT_TAKE_TROPHIES)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance cheetaurHead of Feature
	(properties
		x 267
		y 120
		z 83
		noun N_CHEETAURHEAD
		nsTop 26
		nsLeft 259
		nsBottom 48
		nsRight 275
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_CHEETAURHEAD V_LOOK)
			)
			(V_DO
				(messager say: N_ROOM V_DO C_DONT_TAKE_TROPHIES)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance painting of Feature
	(properties
		x 13
		y 130
		z 67
		noun N_PAINTING
		nsTop 26
		nsBottom 100
		nsRight 27
		sightAngle 40
	)
)

(instance wallOfStuff of Feature
	(properties
		x 159
		y 33
		noun N_WALLOFSTUFF
		nsTop 33
		nsBottom 94
		nsRight 319
		sightAngle 40
	)
)

(instance aWindow of View
	(properties
		x 53
		y 97
		noun N_WINDOW
		approachX 119
		approachY 119
		view 311
		loop 5
	)
)

(instance chair of View
	(properties
		x 83
		y 130
		noun N_MASTER
		view 312
		loop 4
		cel 1
	)
	
	(method (doVerb)
		(master doVerb: &rest)
	)
)

(instance theFire of Prop
	(properties
		x 239
		y 121
		noun N_FIRE
		view 311
		loop 1
		cycleSpeed 8
	)
)

(instance theCandles of Prop
	(properties
		x 157
		y 47
		noun N_CANDLES
		view 311
		loop 4
		cycleSpeed 12
	)
)

(instance masterHead of Prop
	(properties
		x 98
		y 104
		noun N_MASTER
		view 312
		loop 5
		priority 7
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb)
		(master doVerb: &rest)
	)
)

(instance master of Prop
	(properties
		noun N_MASTER
		view 312
	)
	
	(method (init &tmp temp0)
		(= nightPalette 1311)
		(PalVary PALVARYTARGET 1311)
		(AssertPalette 311)
		(super init:)
		(self posn: 76 137 ignoreActors: startUpd:)
		(masterHead init:)
	)
)

(instance masterTeller of Teller
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(messager say: N_MASTER V_LOOK)
			)
			(V_DO
				(HandsOff)
				(ego setScript: goToMaster)
			)
			(V_TALK
				(if (not (ego inRect: 64 120 126 162))
					(messager say: N_MASTER V_TALK C_GETCLOSER)
				else
					(if (not dozingOff)
						(switch (Random 0 4)
							(0
								(messager say: N_MASTER V_TALK C_FIRSTMEET)
							)
							(1
								(messager say: N_MASTER V_TALK C_FIRSTMEET2)
							)
							(2
								(messager say: N_MASTER V_TALK C_FIRSTMEET3)
							)
							(3
								(messager say: N_MASTER V_TALK C_FIRSTMEET4)
							)
							(4
								(messager say: N_MASTER V_TALK C_FIRSTMEET5)
							)
						)
					else
						(SolvePuzzle f311TalkToWolfgang 1)
						(super doVerb: theVerb)
					)
					(= dozingOff TRUE)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
		(return TRUE)
	)
)

(instance sMaster of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= snoreCount 5)
				(= wokenUp FALSE)
				(masterHead setLoop: 5 setCel: 0 ignoreActors:)
				(master setLoop: 1 setCel: 0)
				(= ticks (Random 30 90))
			)
			(1
				(if (not (Btst fBeenIn311))
					(Bset fBeenIn311)
					(LookRoom)
				)
				(masterHead cel: 1)
				(= ticks 6)
			)
			(2
				(masterHead cel: 2)
				(= ticks 6)
			)
			(3
				(snore play:)
				(masterHead cel: 3)
				(= ticks 6)
			)
			(4
				(masterHead cel: 1)
				(= ticks 6)
			)
			(5
				(masterHead cel: 0)
				(-- snoreCount)
				(= ticks 6)
			)
			(6
				(if (> snoreCount 0)
					(self changeState: 4)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance upAndAtIm of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: N_MASTER NULL C_SLEEPING 1 self)
			)
			(1
				(if (and (== (ego x?) 70) (== (ego y?) 145))
					(self changeState: 3)
				else
					(ego
						ignoreControl: cWHITE
						setMotion: PolyPath 50 145 self
					)
				)
			)
			(2
				(ego setMotion: PolyPath 70 145 self)
			)
			(3
				(masterHead hide:)
				(master
					setLoop: 2
					cel: 0
					posn: 80 135
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(4
				(master setCycle: CycleTo 4 -1 self)
			)
			(5
				(master setLoop: 3 cel: 0 posn: 80 137)
				(self cue:)
			)
			(6
				(HandsOn)
				(if saidHello
					(switch (Random 21 25)
						(21
							(messager say: N_MASTER NULL C_WELCOMEBACK 1 self)
						)
						(22
							(messager say: N_MASTER NULL C_MUMBLE1 1 self)
						)
						(23
							(messager say: N_MASTER NULL C_MUMBLE2 1 self)
						)
						(24
							(messager say: N_MASTER NULL C_MUMBLE3 1 self)
						)
						(25
							(messager say: N_MASTER NULL C_MUMBLE4 1 self)
						)
					)
				else
					(= saidHello TRUE)
					(messager say: N_MASTER NULL C_WAKEUP 1 self)
				)
				(= wokenUp 1)
				(= armTimer (Random 2 6))
			)
			(7 (master setCycle: EndLoop self))
			(8 (master setCycle: BegLoop self))
			(9
				(if (> armTimer 0)
					(-- armTimer)
					(self changeState: 7)
				else
					(= ticks (Random 30 60))
				)
			)
			(10
				(= armTimer (Random 2 6))
				(self changeState: 7)
			)
			(11
				(= wokenUp 0)
				(master
					setLoop: 2
					cel: 6
					posn: 80 135
					ignoreActors:
					setCycle: BegLoop self
				)
			)
			(12
				(master setLoop: 1 cel: 0 posn: 76 137 setScript: sMaster)
				(masterHead ignoreActors: posn: 101 104 show:)
				(self start: 0 dispose:)
			)
		)
	)
)

(instance signTheBook of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(NormalEgo)
				(HandsOff)
				(self cue:)
			)
			(1
				(ego setMotion: PolyPath 149 167 self)
			)
			(2
				(ego setHeading: 360)
				(= ticks 120)
			)
			(3
				(if (Btst fSignedLogbook)
					(messager say: N_BOOK V_DO C_SIGNEDIN 1 self)
				else
					(messager say: N_BOOK V_DO NULL 1 self)
				)
			)
			(4
				(SolvePuzzle f311SignBook 1)
				(Bset fSignedLogbook)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance youGotPoints of Script
	(method (changeState newState &tmp [str 300] [buf 350])
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (not (Btst fSignedLogbook)) (not readUnsigned))
					(= readUnsigned TRUE)
					(= logbookPage 1)
				)
				(SolvePuzzle f311ReadBook 4)
				(= ticks 60)
			)
			(1
				(ego setMotion: PolyPath 149 167 self)
			)
			(2
				(ego setHeading: 360)
				(= ticks 120)
			)
			(3
				(switch (++ logbookPage)
					(1
						(Message MsgGet 311 N_BOOK V_LOOK C_SIGNEDIN 1 @str)
						(Print
							font: userFont
							mode: teJustCenter
							width: 260
							addTextF: @buf @str @userName
							init:
						)
						(HandsOn)
						(self dispose:)
					)
					(2
						(messager say: N_BOOK V_LOOK C_PAGE2)
						(HandsOn)
						(self dispose:)
					)
					(3
						(messager say: N_BOOK V_LOOK C_PAGE3)
						(HandsOn)
						(self dispose:)
					)
					(else 
						(messager say: N_BOOK V_LOOK C_PAGE4)
						(HandsOn)
						(self dispose:)
					)
				)
			)
		)
	)
)

(instance goToBoard of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					ignoreControl: cWHITE
					ignoreActors:
					setMotion: PolyPath 164 119 self
				)
			)
			(1
				(SolvePuzzle f311ReadBoard 6)
				(= seconds 1)
			)
			(2
				(curRoom newRoom: 318)
			)
		)
	)
)

(instance goToMaster of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (and (== (ego x?) 70) (== (ego y?) 145))
					(self changeState: 2)
				else
					(ego
						ignoreControl: cWHITE
						setMotion: PolyPath 50 145 self
					)
				)
			)
			(1
				(ego setMotion: PolyPath 70 145 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance wolfgangTalker of Talker
	(properties
		x 10
		y 10
		view 1311
		talkWidth 260
		textX 15
		textY 110
	)
	
	(method (init)
		(= nightPalette 2311)
		(PalVary PALVARYTARGET 2311)
		(AssertPalette 1311)
		(= font userFont)
		(super init: wolfgangBust wolfgangEye wolfgangMouth &rest)
	)
)

(instance wolfgangBust of Prop
	(properties
		view 1311
	)
)

(instance wolfgangMouth of Prop
	(properties
		nsTop 40
		nsLeft 45
		view 1311
		loop 1
	)
)

(instance wolfgangEye of Prop
	(properties
		nsTop 17
		nsLeft 42
		view 1311
		loop 2
	)
)

(instance snore of Sound
	(properties
		number 112
	)
)
