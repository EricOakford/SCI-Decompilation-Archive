;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
(use Kq6IconBar)
(use Kq6Sound)
(use EgoGroop)
(use KQ6Print)
(use Intrface)
(use KQ6Room)
(use Kq6Talker)
(use Kq6Window)
(use Kq6Procs)
(use Kq6Ego)
(use Print)
(use Dialog)
(use Messager)
(use Conv)
(use Talker)
(use PMouse)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Game)
(use User)
(use System)

(public
	Kq6 0
	EgoDead 1
	VGAOrEGA 2
	emberTimer 4
	beastTimer 5
	CharonTimer 6
	lettuceTimer 7
)

(local
	ego
	theGame
	curRoom
	unused_1
	quit
	cast
	regions
	timers
	sounds
	inventory
	addToPics
	curRoomNum
	prevRoomNum
	newRoomNum
	debugOn
	score
	possibleScore
	textCode
	cuees
	theCursor
	normalCursor =  ARROW_CURSOR
	waitCursor =  HAND_CURSOR
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  USERFONT
	version
	unused_3
	curSaveDir
	unused_4
	perspective
	features
	unused_5
	useSortedFeatures
	unused_6
	overlays =  -1
	doMotionCue
	systemWindow
	unused_7
	unused_8
	modelessPort
	sysLogPath
		global43
		global44
		global45
		global46
		global47
		global48
		global49
		global50
		global51
		global52
		global53
		global54
		global55
		global56
		global57
		global58
		global59
		global60
		global61
	endSysLogPath
	gameControls
	ftrInitializer
	doVerbCode
	approachCode
	useObstacles =  TRUE
	unused_9
	theIconBar
	mouseX
	mouseY
	keyDownHandler
	mouseDownHandler
	directionHandler
	speechHandler
	lastVolume
	pMouse
	theDoits
	eatMice =  60
	user
	syncBias
	theSync
	unused_10
	fastCast
	inputFont
	tickOffset
	howFast
	gameTime
	narrator
	msgType =  TEXT_MSG
	messager
	prints
	walkHandler
	textSpeed =  2
	altPolyList
		global96
		global97
		global98
	lastSysGlobal
	debugging
	dongle =  1234
	theMusic
	theGlobalSound
	soundFx
	soundFx2
	gEgo
	numColors
	numVoices
	global109
	global110
	myTextColor
	global112
	global113
	global114
	global115
	global116
	global117
	global118
	global119
	global120
	global121
	global122
	global123
	global124
	global125
	global126
	global127
	global128
	global129
	global130
	global131
	global132
	global133
	global134
	global135
	startingRoom
	gameFlags
		global138
		global139
		global140
		global141
		global142
		global143
		global144
		global145
		global146
		global147
		global148
		global149
	endGameFlags
	theEgoGroop
	global152
	currentAct
	gCurrentAct
	global155
	gKillDog
	global157
	global158
	global159
	deathReason
	global161
	global162
	audioMusic
	global164
	global165
	global166
	global167
	global168
	global169
	refreshingInvWindow
)
(procedure (EgoDead reason)
	(= deathReason reason)
	(Bset fEgoDead)
	(curRoom newRoom: 640)
)

(procedure (VGAOrEGA param1 param2)
	(if (< param1 0) (= param1 0))
	(if (> param1 255) (= param1 255))
	(if (< param2 0) (= param2 0))
	(if (> param2 15) (= param2 15))
	(return (if (>= numColors 32) param1 else param2))
)

(class Kq6Points of Kq6Sound
	
	(method (check)
		(super check: &rest)
		(if (== prevSignal -1) (self dispose:))
	)
)

(instance egoObj of Body
	(properties
		name "ego"
		noun N_ALEX
		modNum 0
		sightAngle 45
		view 900
	)
	
	(method (init)
		(super init: &rest)
		(= scaleSignal (| scaleSignal $0004))
		(= state (| state $0002))
	)
	
	(method (handleEvent event)
		(return
			(if (& (event type?) direction)
				(return 0)
			else
				(super handleEvent: event &rest)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(19 (curRoom setScript: 908))
			(14
				(if (and (!= curRoomNum 280) (Btst 153))
					(messager say: 1 14 18 0 0 0)
				else
					(curRoom setScript: 87)
				)
			)
			(31 (curRoom setScript: 85))
			(42 (curRoom setScript: 88))
			(27 (curRoom setScript: 90))
			(83
				(if (Btst 151)
					(messager say: 1 83 17 0 0 0)
				else
					(curRoom setScript: 92)
				)
			)
			(37 (curRoom setScript: 93))
			(28 (curRoom setScript: 190))
			(32
				(if (curRoom script?)
					(messager say: 7 0 16 0 0 0)
				else
					(curRoom setScript: 97)
				)
			)
			(65
				(if (curRoom script?)
					(messager say: 7 0 16 0 0 0)
				else
					(curRoom setScript: 96)
				)
			)
			(61
				(if (curRoom script?)
					(messager say: 7 0 16 0 0 0)
				else
					(curRoom setScript: 101)
				)
			)
			(67
				(ego put: 31 0)
				(messager say: noun theVerb 0 1 0 0)
			)
			(24
				(ego put: 40 0)
				(messager say: noun theVerb 0 1 0 0)
			)
			(62
				(ego put: 22 470)
				(messager say: noun theVerb 0 1 0 0)
			)
			(63
				(ego put: 23 280)
				(messager say: noun theVerb 0 1 0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(class NewSound of Sound
	(properties
		flags mNOPAUSE
		newPiece 0
		newLoop 0
		fadeTicks 15
		fadeStep 10
		volSwitch 50
	)
	
	(method (init)
		(super init: &rest)
		(= prevSignal -1)
	)
	
	(method (fadeTo theNewPiece)
		(= newLoop 0)
		(if (and argc (>= argc 1))
			(= newPiece [theNewPiece 0])
			(if (>= argc 2) (= newLoop [theNewPiece 1]))
		)
		(if (== prevSignal -1)
			(self cue:)
		else
			(self client: self fade: volSwitch fadeTicks fadeStep 1)
		)
	)
	
	(method (cue)
		(if (== prevSignal -1)
			(self
				number: newPiece
				loop: newLoop
				play: volSwitch
				fade: 127 fadeTicks fadeStep 0
			)
		)
	)
)

(instance globalSound of NewSound
	(properties)
)

(instance globalSound2 of Kq6Sound
	(properties)
)

(instance globalSound3 of Kq6Sound
	(properties)
)

(instance globalSound4 of Kq6Sound
	(properties)
)

(instance kq6KeyDownHandler of EventHandler
	(properties)
)

(instance kq6MouseDownHandler of EventHandler
	(properties)
)

(instance kq6DirectionHandler of EventHandler
	(properties)
)

(instance kq6WalkHandler of EventHandler
	(properties)
)

(class Kq6 of Game
	(properties
		isHandsOn TRUE
		oldCurIcon 0
	)
	
	(method (init &tmp [temp0 33])
		(ScriptID SIGHT)
		DText
		DButton
		Polygon
		PolyPath
		KQ6Room
		Kq6Talker
		Talker
		RandCycle
		Conversation
		Scaler
		(super init: &rest)
		(timers
			add:
				(emberTimer client: emberTimer yourself:)
				(beastTimer client: beastTimer yourself:)
				(CharonTimer client: CharonTimer yourself:)
				(lettuceTimer client: lettuceTimer yourself:)
		)
		(if (and (FileIO fileExists {KQ6CD}) (DoAudio DACFound))
			(= msgType CD_MSG)
			(DoAudio Rate 22050)
		else
			(= msgType TEXT_MSG)
		)
		(= numColors (Graph GDetect))
		(= systemWindow Kq6Window)
		(= theCursor theGameCursor)
		(= waitCursor theWaitCursor)
		(= normalCursor arrowCursor)
		(= userFont 4)
		(self setCursor: (waitCursor posn: 300 180 yourself:))
		(= narrator Kq6Narrator)
		(= messager Kq6Messager)
		((ScriptID 902) init:)
		(DisposeScript 902)
		(= theEgoGroop EgoGroop)
		(= useSortedFeatures TRUE)
		(StrCpy @sysLogPath {})
		(= doVerbCode kq6DoVerbCode)
		(= ftrInitializer kq6FtrInit)
		(= approachCode kq6ApproachCode)
		((= keyDownHandler kq6KeyDownHandler) add:)
		((= mouseDownHandler kq6MouseDownHandler) add:)
		((= directionHandler kq6DirectionHandler) add:)
		((= walkHandler kq6WalkHandler) add:)
		(= pMouse kq6PseudoMouse)
		(= ego egoObj)
		(User alterEgo: ego canControl: 0 canInput: 0)
		((= theMusic globalSound) owner: self init:)
		((= theGlobalSound globalSound2) owner: self init:)
		((= soundFx globalSound3) owner: self init:)
		((= soundFx2 globalSound4) owner: self init:)
		(= possibleScore 231)
		(= version {x.yyy.zzz})
		(Format @temp0 0 0 911)
		(if (FileIO fiEXISTS @temp0)
			(= debugging 1)
		else
			(= debugging 0)
		)
		(if (and (== (Platform 4) 2) (== numColors 256))
			(= global169 1)
		)
		(ego setSpeed: 6 currentSpeed: 6)
		(= numVoices (DoSound sndGET_POLYPHONY))
		((= theIconBar Kq6IconBar)
			add:
				(icon0 cursor: cIcon0 yourself:)
				(icon1 cursor: cIcon1 yourself:)
				(icon2 cursor: cIcon2 yourself:)
				(icon3 cursor: cIcon3 yourself:)
				icon4
				icon5
				icon6
			eachElementDo: #init
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor 53
			curIcon: icon0
			useIconItem: icon4
			walkIconItem: icon0
			disable: icon4
			disable:
		)
		(icon5 message: (if (HaveMouse) 3840 else 9))
		((ScriptID 907) init:)
		(= startingRoom (if (GameIsRestarting) 200 else 100))
		(= eatMice 2)
		(Load rsVIEW 998)
		(Lock 128 998 1)
		(self newRoom: 99)
	)
	
	(method (play)
		(= theGame self)
		(= curSaveDir (GetSaveDir))
		(self init:)
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(= temp0 (if global169 (Platform 6) else 0))
		(if pMouse (pMouse stop:))
		((ScriptID 919) doit: roomNum)
		(if temp0 (SetSynonyms 2 0))
		(if
			(and
				debugging
				(not (Btst fFragmented))
				(u> (MemoryInfo 1) (+ 10 (MemoryInfo 0)))
				(!= (- (MemoryInfo 1) 2) (MemoryInfo 0))
			)
			(if
				(switch
					(Print
						font: userFont
						addText: {*** Memory fragmented.}
						addButton: 0 {Who cares} 0 20
						addButton: 1 {Debug} 0 34
						init:
					)
					(0 (Bset 38))
					(1 (SetDebug))
				)
			)
		)
		(if debugOn (SetDebug))
		(cond 
			(
			(OneOf roomNum 200 210 220 230 240 250 260 270 280 290) (ScriptID 10) ((ScriptID roomNum) setRegions: 10))
			(
			(OneOf roomNum 300 310 320 330 340 350 370 380 390)
				(ScriptID 20)
				(if (OneOf roomNum 300 320)
					(ScriptID 21)
					((ScriptID roomNum) setRegions: 21)
				)
				((ScriptID roomNum) setRegions: 20)
			)
			(
				(OneOf
					roomNum
					400
					405
					410
					415
					420
					425
					430
					435
					440
					406
					407
					408
					409
					411
				)
				(ScriptID 30)
				((ScriptID roomNum) setRegions: 30)
			)
			((OneOf roomNum 450 460 461 470 475 480 490) (ScriptID 40) ((ScriptID roomNum) setRegions: 40))
			((OneOf roomNum 500 510 520 530 540) (ScriptID 50) ((ScriptID roomNum) setRegions: 50))
			((OneOf roomNum 550 560 570 580) (ScriptID 60) ((ScriptID roomNum) setRegions: 60))
			(
				(OneOf
					roomNum
					600
					605
					615
					620
					630
					640
					650
					660
					670
					680
					690
				)
				(ScriptID 70)
				((ScriptID roomNum) setRegions: 70)
			)
			(
				(OneOf
					roomNum
					700
					710
					720
					730
					740
					750
					760
					770
					780
					781
					790
					800
					810
					820
					840
					850
					860
					870
					880
					180
					743
				)
				(ScriptID 80)
				(if (OneOf roomNum 840 710 720 770 820 780)
					(ScriptID 81)
					((ScriptID roomNum) setRegions: 81)
				)
				((ScriptID roomNum) setRegions: 80)
			)
			(else 0)
		)
		(super startRoom: roomNum)
		(CueObj client: 0 state: 0)
		(if
		(and (cast contains: ego) (not (ego looper?)))
			(ego setLoop: EgoGroop)
		)
		(if temp0 ((ScriptID 109 0) doit: roomNum))
	)
	
	(method (restart param1 &tmp temp0 temp1)
		(if modelessDialog (modelessDialog dispose:))
		(if (not argc)
			(= temp1 (theGame setCursor: normalCursor))
			(if
				(= temp0
					(KQ6Print
						posn: 59 70
						font: 4
						addButton: 1 5 0 13 0 0 36 0
						addButton: 0 5 0 14 0 115 36 0
						font: 1
						say: 1 5 0 0 0 0 0 0
						init:
					)
				)
				(super restart: &rest)
			else
				(theGame setCursor: temp1)
			)
		else
			(super restart: &rest)
		)
	)
	
	(method (restore)
		(if
		(or (not (Btst 49)) (>= (MemoryInfo 0) 1500))
			(super restore: &rest)
			(self
				setCursor:
					(cond 
						((or (user canControl:) (user canInput:)) ((theIconBar curIcon?) cursor?))
						((Btst 44) normalCursor)
						(else waitCursor)
					)
			)
		else
			(messager say: 7 0 15 0 0 0)
		)
	)
	
	(method (save)
		(if
		(and (not (Btst 49)) (>= (MemoryInfo 0) 1500))
			(super save: &rest)
			(self
				setCursor:
					(if (or (user canControl:) (user canInput:))
						((theIconBar curIcon?) cursor?)
					else
						waitCursor
					)
			)
		else
			(messager say: 7 0 15 0 0 0)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(if debugging
			(switch (event type?)
				(evKEYBOARD
					(switch (event message?)
						(KEY_TAB
							(if (not (& (icon5 signal?) $0004))
								((ScriptID 907 1) init: ego)
								(event claimed: 1)
							)
						)
						(KEY_SHIFTTAB
							(if (not (& (icon5 signal?) $0004))
								((ScriptID 907 1) init: ego)
								(event claimed: 1)
							)
						)
						(KEY_CONTROL
							(theGame quitGame:)
							(event claimed: 1)
						)
						(KEY_F2
							(cond 
								((theGame masterVolume:) (self masterVolume: 0))
								((> numVoices 1) (self masterVolume: 15))
								(else (self masterVolume: 1))
							)
							(event claimed: 1)
						)
						(KEY_F5
							(self save:)
							(event claimed: 1)
						)
						(KEY_F7
							(self restore:)
							(event claimed: 1)
						)
						(KEY_F9
							(self restart:)
							(event claimed: 1)
						)
						(else 
							(event claimed: 1)
							(if
								(and
									debugging
									(not
										(OneOf curRoomNum 440 450 480 270 280 470 490 670 750 740)
									)
								)
								(event claimed: 0)
								((ScriptID 911) handleEvent: event)
								((ScriptID 911) dispose:)
								(DisposeScript 911)
							)
						)
					)
				)
			)
		)
		(cond 
			((event claimed?) 1)
			((and script (script handleEvent: event)) 1)
			((& (event type?) evVERB) (self pragmaFail: (event message?)))
		)
		(return (event claimed?))
	)
	
	(method (setCursor form showIt theX theY &tmp theTheCursor temp1 temp2)
		(= theTheCursor theCursor)
		(= theCursor form)
		(if (> argc 2)
			(= temp1 (if (< theX 0) 0 else theX))
			(= temp2 (if (< theY 0) 0 else theY))
			(SetCursor temp1 temp2)
		)
		(if (IsObject form)
			(if argc ((= theCursor form) init:))
			(form init:)
		else
			(SetCursor form 0 0)
		)
		(return theTheCursor)
	)
	
	(method (quitGame &tmp temp0)
		(if modelessDialog (modelessDialog dispose:))
		(if
			(or
				(and (== curRoomNum 640) (Btst 44))
				(and
					(== curRoomNum 740)
					(== msgType 2)
					(== prevRoomNum 180)
				)
			)
			(DoAudio 10 3)
			(= quit 1)
		else
			(= temp0 (theGame setCursor: normalCursor))
			(if
				(not
					(= quit
						(KQ6Print
							posn: 59 70
							font: 4
							addButton: 1 4 0 12 0 0 36 0
							addButton: 0 4 0 11 0 85 36 0
							font: 1
							say: 1 4 0 0 0 0 0 0
							init:
						)
					)
				)
				(theGame setCursor: temp0)
			)
		)
	)
	
	(method (pragmaFail param1 &tmp temp0)
		(if (User canInput:)
			(= temp0 (Random 1 3))
			(if (== (approachCode doit: param1) -32768)
				(= param1 0)
			)
			(messager say: 0 param1 0 temp0 0 0)
		)
	)
	
	(method (handsOff)
		(= isHandsOn 0)
		(if (not argc) (ego setMotion: 0))
		(if (not oldCurIcon)
			(= oldCurIcon (theIconBar curIcon?))
		)
		(ego oldXStep: (ego xStep?))
		(ego oldYStep: (ego yStep?))
		(if
			(and
				(& (ego scaleSignal?) $0003)
				(not (ego oldScaleSignal?))
			)
			(ego oldScaleSignal: (& (ego scaleSignal?) $fffb))
			(cond 
				((& (ego oldScaleSignal?) $0002) (ego oldMaxScale: (ego maxScale?)))
				((IsObject (ego scaler?))
					(ego
						oldMaxScale: ((ego scaler?) frontSize?)
						oldBackSize: ((ego scaler?) backSize?)
						oldFrontY: ((ego scaler?) frontY?)
						oldBackY: ((ego scaler?) backY?)
					)
				)
			)
		)
		(theIconBar
			disable: (theIconBar at: 0) icon1 icon2 icon3 icon4 icon5
		)
		(User canControl: 0 canInput: 0)
		(theGame setCursor: waitCursor)
		(if pMouse (pMouse stop:))
	)
	
	(method (handsOn &tmp temp0)
		(= isHandsOn 1)
		(User canControl: 1 canInput: 1)
		(if (IsObject oldCurIcon)
			(theIconBar curIcon: oldCurIcon)
		)
		(= oldCurIcon 0)
		(theIconBar
			enable: (theIconBar at: 0) icon1 icon2 icon3 icon4 icon5
		)
		(if (not (theIconBar curInvIcon?))
			(theIconBar disable: icon4)
		)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	
	(method (givePoints param1)
		(= score (+ score param1))
		((Kq6Points new:) flags: 1 number: 900 play:)
	)
	
	(method (killSound param1)
		(if (and argc param1)
			(sounds eachElementDo: #pause 1)
			(= audioMusic sounds)
			((= sounds globalSounds) add:)
		else
			(globalSounds dispose:)
			(= sounds audioMusic)
			(= audioMusic 0)
			(sounds eachElementDo: #pause 0)
		)
	)
	
	(method (refresh param1)
		(if (and param1 argc)
			(if
			(and fastCast (not (& (inventory state?) $0020)))
				(Animate (cast elements?) 0)
			)
		else
			(= refreshingInvWindow 1)
		)
	)
)

(instance globalSounds of Sounds
	(properties)
)

(class Kq6Messager of Messager
	(properties
		caller 0
		disposeWhenDone 1
		oneOnly 0
		killed 0
		oldIconBarState 0
		curSequence 0
		lastSequence 0
		talker 0
	)
	
	(method (findTalker param1 &tmp temp0)
		(if
			(= temp0
				(switch param1
					(21 (ScriptID 1000 21))
					(2 (ScriptID 1001 2))
					(87 narrator)
					(62 (ScriptID 1063 62))
					(20 (ScriptID 1002 20))
					(56 (ScriptID 1057 56))
					(17 (ScriptID 1003 17))
					(33 (ScriptID 1034 33))
					(43 (ScriptID 1044 43))
					(77 (ScriptID 1050 77))
					(82 (ScriptID 1015 6))
					(88 (ScriptID 1039 71))
					(71 (ScriptID 1039 71))
					(48 (ScriptID 1049 48))
					(46 (ScriptID 1047 46))
					(11 (ScriptID 1004 11))
					(28 (ScriptID 1005 28))
					(93 narrator)
					(4 (ScriptID 1006 4))
					(57 (ScriptID 1010 57))
					(73 (ScriptID 1040 73))
					(9 (ScriptID 1007 9))
					(79 (ScriptID 1025 79))
					(45 (ScriptID 1046 45))
					(69 (ScriptID 1016 69))
					(83 (ScriptID 1060 83))
					(59 (ScriptID 1033 59))
					(60 (ScriptID 1033 60))
					(23 (ScriptID 1008 23))
					(58 (ScriptID 1061 58))
					(14 (ScriptID 1009 14))
					(15 (ScriptID 1009 15))
					(27 (ScriptID 1011 27))
					(32 (ScriptID 1012 32))
					(55 (ScriptID 1056 55))
					(29 (ScriptID 1013 29))
					(34 (ScriptID 1035 34))
					(30 (ScriptID 1014 30))
					(36 (ScriptID 1037 36))
					(94 (ScriptID 1065 94))
					(8 (ScriptID 1015 8))
					(6 (ScriptID 1015 6))
					(7 (ScriptID 1015 7))
					(1 (ScriptID 1018 1))
					(22 (ScriptID 1019 22))
					(26 (ScriptID 1033 26))
					(38 (ScriptID 1041 38))
					(5 (ScriptID 1020 5))
					(16 (ScriptID 1021 16))
					(78 (ScriptID 1024 78))
					(3 (ScriptID 1022 3))
					(40 (ScriptID 1064 40))
					(99 narrator)
					(80 (ScriptID 1062 80))
					(19 (ScriptID 1023 19))
					(44 (ScriptID 1045 44))
					(35 (ScriptID 1036 35))
					(97 -1)
					(42 (ScriptID 1017 42))
					(13 (ScriptID 1026 13))
					(10 (ScriptID 1027 10))
					(75 (ScriptID 1055 75))
					(95 (ScriptID 1067 95))
					(53 (ScriptID 490 53))
					(50 (ScriptID 1051 50))
					(92 narrator)
					(81 (ScriptID 1015 7))
					(74 (ScriptID 1031 74))
					(86 (ScriptID 1028 86))
					(68 (ScriptID 1037 68))
					(65 (ScriptID 1037 65))
					(72 (ScriptID 1042 72))
					(61 (ScriptID 1037 61))
					(47 (ScriptID 1048 47))
					(25 (ScriptID 1059 25))
					(37 (ScriptID 1038 37))
					(66 (ScriptID 1037 66))
					(70 (ScriptID 1051 50))
					(67 (ScriptID 1037 67))
					(51 (ScriptID 1052 51))
					(41 narrator)
					(12 (ScriptID 1066 12))
					(24 (ScriptID 1029 24))
					(91 narrator)
					(49 (ScriptID 1058 49))
					(90 narrator)
					(39 (ScriptID 1043 39))
					(76 (ScriptID 1055 76))
					(18 (ScriptID 1030 18))
					(31 (ScriptID 1030 31))
					(52 (ScriptID 490 52))
				)
			)
			(return)
		else
			(super findTalker: param1)
		)
	)
)

(instance kq6DoVerbCode of Code
	(properties)
	
	(method (doit param1 param2)
		(cond 
			(
				(and
					(== (kq6ApproachCode doit: param1) -32768)
					(Message msgGET (param2 modNum?) (param2 noun?) 0 0 1)
				)
				(messager say: (param2 noun?) 0 0 0 0 (param2 modNum?))
			)
			((not (curRoom doVerb: param1)) (theGame pragmaFail: param1))
		)
	)
)

(instance kq6FtrInit of Code
	(properties)
	
	(method (doit param1)
		(if (== (param1 sightAngle?) 26505)
			(param1 sightAngle: 90)
		)
		(if (== (param1 actions?) 26505) (param1 actions: 0))
		(if
			(and
				(!= (param1 onMeCheck?) 26505)
				(not (IsObject (param1 onMeCheck?)))
			)
			(param1 state: (| (param1 state?) $0004))
		)
	)
)

(instance kq6ApproachCode of Code
	(properties)
	
	(method (doit param1)
		(switch param1
			(1 1)
			(2 2)
			(3 4)
			(5 8)
			(else  -32768)
		)
	)
)

(instance kq6PseudoMouse of PseudoMouse
	(properties)
	
	(method (handleEvent event &tmp theIconBarCurIcon)
		(if (& (event type?) evJOYSTICK)
			(= theIconBarCurIcon (theIconBar curIcon?))
			(theIconBar curIcon: 0)
			(super handleEvent: event)
			(theIconBar curIcon: theIconBarCurIcon)
		)
	)
)

(instance icon0 of Kq6IconItem
	(properties
		loop 0
		cel 0
		type $5000
		message 3
		signal $0041
		maskCel 2
	)
	
	(method (init)
		(= maskView (= view 980))
		(super init: &rest)
	)
)

(instance icon1 of Kq6IconItem
	(properties
		loop 1
		cel 0
		message 5
		signal $0041
		maskLoop 1
		maskCel 2
	)
	
	(method (init)
		(= maskView (= view 980))
		(super init: &rest)
	)
)

(instance icon2 of Kq6IconItem
	(properties
		loop 2
		cel 0
		message 1
		signal $0041
		maskLoop 2
		maskCel 2
	)
	
	(method (init)
		(= maskView (= view 980))
		(super init: &rest)
	)
)

(instance icon3 of Kq6IconItem
	(properties
		loop 3
		cel 0
		message 2
		signal $0041
		maskLoop 3
		maskCel 2
	)
	
	(method (init)
		(= maskView (= view 980))
		(super init: &rest)
	)
)

(instance icon4 of Kq6IconItem
	(properties
		loop 4
		cel 0
		message 0
		signal $0040
		maskLoop 4
		maskCel 2
	)
	
	(method (init)
		(= maskView (= view 980))
		(super init: &rest)
	)
)

(instance icon5 of Kq6IconItem
	(properties
		loop 5
		cel 0
		type $0000
		message 0
		signal $0043
		maskLoop 5
		maskCel 2
	)
	
	(method (init)
		(= maskView (= view 980))
		(super init: &rest)
	)
	
	(method (doit)
		((ScriptID 907 1) init: ego)
	)
)

(instance icon6 of Kq6IconItem
	(properties
		loop 6
		cel 0
		message 0
		signal $0043
		maskLoop 6
		maskCel 2
	)
	
	(method (init)
		(= maskView (= view 980))
		(super init: &rest)
	)
	
	(method (doit)
		(if
		(and (Cursor hidden?) (>= (MemoryInfo 0) 1500))
			((ScriptID 903) init: show: dispose:)
		else
			(messager say: 7 0 15 0 0 0)
		)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(theGame setCursor: waitCursor)
				(theIconBar hide:)
				1
			else
				0
			)
		)
	)
)

(instance theGameCursor of Cursor
	(properties
		view 998
		loop 1
		cel 7
		hidden 1
	)
)

(instance theWaitCursor of Cursor
	(properties
		view 998
		loop 1
		cel 8
		hidden 1
	)
)

(instance cIcon0 of Cursor
	(properties
		view 998
		loop 1
		hidden 1
	)
)

(instance cIcon1 of Cursor
	(properties
		view 998
		loop 1
		cel 2
		hidden 1
	)
)

(instance cIcon2 of Cursor
	(properties
		view 998
		loop 1
		cel 1
		hidden 1
	)
)

(instance cIcon3 of Cursor
	(properties
		view 998
		loop 1
		cel 3
		hidden 1
	)
)

(instance arrowCursor of Cursor
	(properties
		view 998
		loop 1
		cel 7
		hidden 1
	)
)

(instance emberTimer of Timer
	(properties)
	
	(method (doit)
		(if (!= client self) (super doit: &rest))
	)
	
	(method (dispose)
		(super dispose:)
		(= client self)
	)
	
	(method (delete)
		(if (not client) (= client self))
		(super delete:)
	)
)

(instance beastTimer of Timer
	(properties)
	
	(method (doit)
		(if (!= client self) (super doit: &rest))
	)
	
	(method (dispose)
		(super dispose:)
		(= client self)
	)
)

(instance CharonTimer of Timer
	(properties)
	
	(method (doit)
		(if (!= client self) (super doit: &rest))
	)
	
	(method (dispose)
		(super dispose:)
		(= client self)
	)
)

(instance lettuceTimer of Timer
	(properties)
	
	(method (doit)
		(if (!= client self) (super doit: &rest))
	)
	
	(method (dispose)
		(super dispose:)
		(= client self)
	)
	
	(method (delete)
		(if (not client) (= client self))
		(super delete:)
	)
)
