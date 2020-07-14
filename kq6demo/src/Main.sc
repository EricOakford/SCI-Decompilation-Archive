;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
(use Kq6IconBar)
(use Kq6Sound)
(use EgoGroop)
(use Intrface)
(use KQ6Room)
(use Kq6Talker)
(use Kq6Window)
(use Kq6Procs)
(use KQ6Ego)
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
	EGAOrVGA 2
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
	cDAudio
	fastCast
	inputFont
	tickOffset
	howFast
	gameTime
	narrator
	msgType =  1
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
	theGlobalSound1
	theGlobalSound2
	theGlobalSound3
	theGlobalSound4
	global106
	colorCount
	musicChannels
	global109
	global110
	
	;color globals
	global111
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
	;end color globals
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
	global150
	theEgoGroop
	global152
	currentAct
	global154
	global155
	global156
	global157
	global158
	global159
	deathReason
	huntersLampState
	global162
	killedSounds
	global164
	global165
	global166
)
(procedure (EgoDead death)
	(= deathReason death)
	(Bset fEgoDead)
	(curRoom newRoom: 640)
)

(procedure (EGAOrVGA vga ega)
	(if (< vga 0) (= vga 0))
	(if (> vga 255) (= vga 255))
	(if (< ega 0) (= ega 0))
	(if (> ega 15) (= ega 15))
	(return (if (>= colorCount 32) vga else ega))
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
	)
	
	(method (handleEvent event)
		(return
			(if (& (event type?) direction)
				(return FALSE)
			else
				(super handleEvent: event &rest)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EGG (curRoom setScript: 908))
			(V_POTION
				(if (and (!= curRoomNum 280) (Btst fDrankPotion))
					(messager say: N_ALEX V_POTION C_ALREADY_DRANK_POTION 0 0 0)
				else
					(curRoom setScript: 87)
				)
			)
			(V_FLUTE (curRoom setScript: 85))
			(V_BORINGBOOK (curRoom setScript: 88))
			(V_RIDDLE_BOOK (curRoom setScript: 90))
			(V_INK
				(if (Btst fUsedInk)
					(messager say: N_ALEX V_INK C_ALREADY_USED_INK 0 0 0)
				else
					(curRoom setScript: 92)
				)
			)
			(V_NIGHTINGALE (curRoom setScript: 93))
			(V_SPELLBOOK (curRoom setScript: 190))
			(V_POEM
				(if (curRoom script?)
					(messager say: N_NOTNOW NULL C_BUSY 0 0 0)
				else
					(curRoom setScript: 97)
				)
			)
			(V_NOTE
				(if (curRoom script?)
					(messager say: N_NOTNOW NULL C_BUSY 0 0 0)
				else
					(curRoom setScript: 96)
				)
			)
			(V_LETTER
				(if (curRoom script?)
					(messager say: N_NOTNOW NULL C_BUSY 0 0 0)
				else
					(curRoom setScript: 101)
				)
			)
			(V_PEPPERMINT
				(ego put: iPeppermint 0)
				(messager say: noun theVerb 0 1 0 0)
			)
			(V_SACRED_WATER
				(ego put: iSacredWater 0)
				(messager say: noun theVerb 0 1 0 0)
			)
			(V_MILK
				(ego put: iMilk 470)
				(messager say: noun theVerb 0 1 0 0)
			)
			(V_MINT
				(ego put: iMint 280)
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
	
	(method (init &tmp [fileBuf 20])
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
		(DoAudio Rate 11000)
		(= msgType TEXT_MSG)
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
		(User alterEgo: ego canControl: FALSE canInput: FALSE)
		((= theGlobalSound1 globalSound) owner: self init:)
		((= theGlobalSound2 globalSound2) owner: self init:)
		((= theGlobalSound3 globalSound3) owner: self init:)
		((= theGlobalSound4 globalSound4) owner: self init:)
		(= possibleScore 231)
		(= version {x.yyy.zzz})
		(Format @fileBuf 0 0 911)
		(if (FileIO fileExists @fileBuf)
			(= debugging TRUE)
		else
			(= debugging FALSE)
		)
		(ego setSpeed: 6 currentSpeed: 6)
		(= musicChannels (DoSound NumVoices))
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
		(Load RES_VIEW 998)
		(Lock RES_VIEW 998 1)
		(self newRoom: 99)
	)
	
	(method (play)
		(= theGame self)
		(= curSaveDir (GetSaveDir))
		(if (not (GameIsRestarting)) (GetCWD curSaveDir))
		(self init:)
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (startRoom roomNum)
		(if pMouse (pMouse stop:))
		((ScriptID 919) doit: roomNum)
		(if
			(and
				debugging
				(not (Btst 38))
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
					(0 (Bset fFragmented))
					(1 (SetDebug))
				)
			)
		)
		(if debugOn (SetDebug))
		(cond 
			(
			(OneOf roomNum 200 210 220 230 240 250 260 270 280 290)
				(ScriptID regionCROWN) ((ScriptID roomNum) setRegions: regionCROWN)
			)
			(
			(OneOf roomNum 300 310 320 330 340 350 370 380 390)
				(ScriptID regionSACRED)
				(if (OneOf roomNum 300 320)
					(ScriptID regionCLIFFS)
					((ScriptID roomNum) setRegions: regionCLIFFS)
				)
				((ScriptID roomNum) setRegions: regionSACRED)
			)
			(
				(OneOf roomNum
					400 405 410 415 420 425 430 435 440
					406 407 408 409 411
				)
				(ScriptID regionLABYRINTH)
				((ScriptID roomNum) setRegions: regionLABYRINTH)
			)
			((OneOf roomNum 450 460 461 470 475 480 490) (ScriptID regionWONDER) ((ScriptID roomNum) setRegions: regionWONDER))
			((OneOf roomNum 500 510 520 530 540) (ScriptID regionBEAST) ((ScriptID roomNum) setRegions: regionBEAST))
			((OneOf roomNum 550 560 570 580) (ScriptID regionMIST) ((ScriptID roomNum) setRegions: regionMIST))
			(
			(OneOf roomNum 600 605 615 620 630 640 650 660 670 680 690) (ScriptID regionDEAD) ((ScriptID roomNum) setRegions: regionDEAD))
			(
				(OneOf roomNum
					700 710 720 730 740 750 760 770 780 781
					790 800 810 820 840 850 860 870 880 180
					743
				)
				(ScriptID regionCASTLE)
				(if (OneOf roomNum 840 710 720 770 820 780)
					(ScriptID regionBASEMENT)
					((ScriptID roomNum) setRegions: regionBASEMENT)
				)
				((ScriptID roomNum) setRegions: regionCASTLE)
			)
			(else FALSE)
		)
		(super startRoom: roomNum)
		(CueObj client: 0 state: 0)
		(if
		(and (cast contains: ego) (not (ego looper?)))
			(ego setLoop: EgoGroop)
		)
	)
	
	(method (restart tOrF &tmp ret oldCur)
		(if modelessDialog (modelessDialog dispose:))
		(if (not argc)
			(= oldCur (theGame setCursor: normalCursor))
			(if
				(= ret
					(Print
						posn: 59 85
						font: 4
						addButton: TRUE N_RESTART 0 C_YES_RESTART 0 115 18 0
						addButton: FALSE N_RESTART 0 C_NO_RESTART 0 115 36 0
						font: USERFONT
						addText: N_RESTART 0 0 0 0 0 0
						init:
					)
				)
				(super restart: &rest)
			else
				(theGame setCursor: oldCur)
			)
		else
			(super restart: &rest)
		)
	)
	
	(method (restore)
		(if
		(or (not (Btst fSaveDisabled)) (>= (MemoryInfo LargestPtr) 1500))
			(super restore: &rest)
		else
			(messager say: N_NOTNOW 0 C_NOMEMORY 0 0 0)
		)
	)
	
	(method (save)
		(if
		(and (not (Btst fSaveDisabled)) (>= (MemoryInfo LargestPtr) 1500))
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
			(messager say: N_NOTNOW 0 C_NOMEMORY 0 0 0)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(cond 
			(debugging
				(switch (event type?)
					(keyDown
						(switch (event message?)
							(TAB
								(if (not (& (icon5 signal?) DISABLED))
									((ScriptID 907 1) init: ego)
									(event claimed: TRUE)
								)
							)
							(SHIFTTAB
								(if (not (& (icon5 signal?) DISABLED))
									((ScriptID 907 1) init: ego)
									(event claimed: TRUE)
								)
							)
							(KEY_CONTROL
								(theGame quitGame:)
								(event claimed: TRUE)
							)
							(`#2
								(cond 
									((theGame masterVolume:) (self masterVolume: 0))
									((> musicChannels 1) (self masterVolume: 15))
									(else (self masterVolume: 1))
								)
								(event claimed: TRUE)
							)
							(`#5
								(self save:)
								(event claimed: TRUE)
							)
							(`#7
								(self restore:)
								(event claimed: TRUE)
							)
							(`#9
								(self restart:)
								(event claimed: TRUE)
							)
							(else 
								(event claimed: TRUE)
								(if
									(and
										debugging
										(not
											(OneOf curRoomNum 440 450 480 270 280 470 490 670 750 740)
										)
									)
									(event claimed: FALSE)
									((ScriptID 911) handleEvent: event)
									((ScriptID 911) dispose:)
									(DisposeScript 911)
								)
							)
						)
					)
				)
			)
			((== (event type?) keyDown)
				(switch (event message?)
					(KEY_CONTROL
						(theGame quitGame:)
						(event claimed: TRUE)
					)
					(else  (event claimed: TRUE))
				)
			)
		)
		(cond 
			((event claimed?) TRUE)
			((and script (script handleEvent: event)) TRUE)
			((& (event type?) userEvent) (self pragmaFail: (event message?)))
		)
		(return (event claimed?))
	)
	
	(method (setCursor cursorObj tOrF theX theY &tmp oldCurObj moveToX moveToY)
		(= oldCurObj theCursor)
		(= theCursor cursorObj)
		(if (> argc 2)
			(= moveToX (if (< theX 0) 0 else theX))
			(= moveToY (if (< theY 0) 0 else theY))
			(SetCursor moveToX moveToY)
		)
		(if (IsObject cursorObj)
			(if argc ((= theCursor cursorObj) init:))
			(cursorObj init:)
		else
			(SetCursor cursorObj 0 0)
		)
		(return oldCurObj)
	)
	
	(method (quitGame &tmp temp0)
		(if modelessDialog (modelessDialog dispose:))
		(= quit TRUE)
	)
	
	(method (pragmaFail theVerb &tmp seq)
		(if (User canInput:)
			(= seq (Random 1 3))
			(if (== (approachCode doit: theVerb) -32768)
				(= theVerb NULL)
			)
			(messager say: 0 theVerb 0 seq 0 0)
		)
	)
	
	(method (handsOff)
		(= isHandsOn FALSE)
		(if (not argc) (ego setMotion: 0))
		(if (not oldCurIcon)
			(= oldCurIcon (theIconBar curIcon?))
		)
		(ego oldXStep: (ego xStep?))
		(ego oldYStep: (ego yStep?))
		(if
			(and
				(& (ego scaleSignal?) (| scalable autoScale))
				(not (ego oldScaleSignal?))
			)
			(ego oldScaleSignal: (& (ego scaleSignal?) $fffb))
			(cond 
				((& (ego oldScaleSignal?) autoScale) (ego oldMaxScale: (ego maxScale?)))
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
			disable: (theIconBar at: ICON_WALK) icon1 icon2 icon3 icon4 icon5
		)
		(User canControl: FALSE canInput: FALSE)
		(theGame setCursor: waitCursor)
		(if pMouse (pMouse stop:))
	)
	
	(method (handsOn &tmp temp0)
		(= isHandsOn TRUE)
		(User canControl: TRUE canInput: TRUE)
		(if (IsObject oldCurIcon)
			(theIconBar curIcon: oldCurIcon)
		)
		(= oldCurIcon 0)
		(theIconBar
			enable: (theIconBar at: ICON_WALK) icon1 icon2 icon3 icon4 icon5
		)
		(if (not (theIconBar curInvIcon?))
			(theIconBar disable: icon4)
		)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	
	(method (givePoints delta)
		(= score (+ score delta))
		((Kq6Points new:) flags: mNOPAUSE number: 900 play:)
	)
	
	(method (killSound theSound)
		(if (and argc theSound)
			(sounds eachElementDo: #pause TRUE)
			(= killedSounds sounds)
			((= sounds globalSounds) add:)
		else
			(globalSounds dispose:)
			(= sounds killedSounds)
			(= killedSounds FALSE)
			(sounds eachElementDo: #pause FALSE)
		)
	)
)

(instance globalSounds of Sounds)

(class Kq6Messager of Messager
	
	(method (findTalker who &tmp theTalker)
		(if
			(= theTalker
				(switch who
					(AERIEL (ScriptID 1000 21))
					(ALEX (ScriptID 1001 2))
					(87 narrator)
					(ALLARIA (ScriptID 1063 62))
					(AZURE (ScriptID 1002 20))
					(BEAST (ScriptID 1057 56))
					(BEAUTY (ScriptID 1003 17))
					(BOOKSH (ScriptID 1034 33))
					(BOOKWORM (ScriptID 1044 43))
					(BOYGHOST (ScriptID 1050 77))
					(82 (ScriptID 1015 6))	;guard dog
					(BTEARS1 (ScriptID 1039 71))
					(BTEARS2 (ScriptID 1039 71))
					(BUMP (ScriptID 1049 48))
					(BWIDOW (ScriptID 1047 46))
					(CALIPHIM (ScriptID 1004 11))
					(CASSIMA (ScriptID 1005 28))
					(93 narrator)
					(CELESTE (ScriptID 1006 4))
					(COOK (ScriptID 1010 57))
					(CLINGVINES (ScriptID 1040 73))
					(DPARTICIPLE (ScriptID 1007 9))
					(DEADSOULS (ScriptID 1025 79))
					(DIPTHONG (ScriptID 1046 45))
					(DOGWOOD (ScriptID 1016 69))
					(DOORMASTER (ScriptID 1060 83))
					(DPRIEST1 (ScriptID 1033 59))
					(DPRIEST2 (ScriptID 1033 60))
					(FERRYM (ScriptID 1008 23))
					(GATE (ScriptID 1061 58))
					(GATEGUARD1 (ScriptID 1009 14))
					(GATEGUARD2 (ScriptID 1009 15))
					(GENIE_1011 (ScriptID 1011 27))
					(GENIE_PAGE (ScriptID 1012 32))
					(GENIE_GARDENER (ScriptID 1056 55))
					(GENIE_1013 (ScriptID 1013 29))
					(GOLDMAN (ScriptID 1035 34))
					(GOLDLADY (ScriptID 1014 30))
					(GNOMES (ScriptID 1037 36))
					(GRAHAM (ScriptID 1065 94))
					(GUARDDOG (ScriptID 1015 8))
					(GUARDDOG1 (ScriptID 1015 6))
					(GUARDDOG2 (ScriptID 1015 7))
					(GENIE_1018 (ScriptID 1018 1))
					(GENIE_BOY (ScriptID 1019 22))
					(HEADDRU (ScriptID 1033 26))
					(HOLE_IN_WALL (ScriptID 1041 38))
					(JOLLO (ScriptID 1020 5))
					(LAMPSELL (ScriptID 1021 16))
					(LORD_OF_DEAD (ScriptID 1024 78))
					(MINOTAUR (ScriptID 1022 3))
					(MOTHER_GHOST (ScriptID 1064 40))
					(NARRATOR narrator)
					(NIGHTMARE (ScriptID 1062 80))
					(ORACLE (ScriptID 1023 19))
					(OXYMORON (ScriptID 1045 44))
					(PAWNSHOP (ScriptID 1036 35))
					(SYSMSG -1)
					(PUSSY_WILLOWS (ScriptID 1017 42))
					(SALADIN (ScriptID 1026 13))
					(STEPMOTHER (ScriptID 1027 10))
					(RKNIGHT (ScriptID 1055 75))
					(ROSELLA (ScriptID 1067 95))
					(RQUEEN (ScriptID 490 53))
					(TOMATO (ScriptID 1051 50))
					(92 narrator)
					(81 (ScriptID 1015 7))	;guard dog
					(SNAP_DRAGONS (ScriptID 1031 74))
					(SERVINGWOMAN (ScriptID 1028 86))
					(GSIGHT (ScriptID 1037 68))
					(GSOUND (ScriptID 1037 65))
					(SGRAPES (ScriptID 1042 72))
					(GSMELL (ScriptID 1037 61))
					(STICK (ScriptID 1048 47))
					(BEAR (ScriptID 1059 25))
					(OYSTER (ScriptID 1038 37))
					(GTASTE (ScriptID 1037 66))
					(70 (ScriptID 1051 50))	;tomato
					(GTOUCH (ScriptID 1037 67))
					(TOMATO_VINES (ScriptID 1052 51))
					(41 narrator)
					(VALANICE (ScriptID 1066 12))
					(VIZIER (ScriptID 1029 24))
					(91 narrator)
					(WAITER (ScriptID 1058 49))
					(90 narrator)
					(WALLFLOWERS (ScriptID 1043 39))
					(WKNIGHT (ScriptID 1055 76))
					(WINGG1 (ScriptID 1030 18))
					(WINGG2 (ScriptID 1030 31))
					(WQUEEN (ScriptID 490 52))
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)
(instance kq6DoVerbCode of Code
	(properties)
	
	(method (doit theVerb theNoun)
		(cond 
			(
				(and
					(== (kq6ApproachCode doit: theVerb) -32768)
					(Message MsgGet (theNoun modNum?) (theNoun noun?) 0 0 1)
				)
				(messager say: (theNoun noun?) 0 0 0 0 (theNoun modNum?))
			)
			((not (curRoom doVerb: theVerb)) (theGame pragmaFail: theVerb))
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
	)
)

(instance kq6ApproachCode of Code
	(properties)
	
	(method (doit theDirection)
		(switch theDirection
			(dirN 1)
			(dirW 2)
			(dirE 4)
			(dirS 8)
			(else  -32768)
		)
	)
)

(instance kq6PseudoMouse of PseudoMouse
	(properties)
	
	(method (handleEvent event &tmp oldIcon)
		(if (& (event type?) direction)
			(= oldIcon (theIconBar curIcon?))
			(theIconBar curIcon: 0)
			(super handleEvent: event)
			(theIconBar curIcon: oldIcon)
		)
	)
)

(instance icon0 of Kq6IconItem
	(properties
		view 980
		loop 0
		cel 0
		type (| userEvent walkEvent)
		message V_WALK
		signal (| RELVERIFY HIDEBAR)
		maskView 980
		maskCel 2
	)
)

(instance icon1 of Kq6IconItem
	(properties
		view 980
		loop 1
		cel 0
		message V_DO
		signal (| RELVERIFY HIDEBAR)
		maskView 980
		maskLoop 1
		maskCel 2
	)
)

(instance icon2 of Kq6IconItem
	(properties
		view 980
		loop 2
		cel 0
		message V_LOOK
		signal (| RELVERIFY HIDEBAR)
		maskView 980
		maskLoop 2
		maskCel 2
	)
)

(instance icon3 of Kq6IconItem
	(properties
		view 980
		loop 3
		cel 0
		message V_TALK
		signal (| RELVERIFY HIDEBAR)
		maskView 980
		maskLoop 3
		maskCel 2
	)
)

(instance icon4 of Kq6IconItem
	(properties
		view 980
		loop 4
		cel 0
		message 0
		signal RELVERIFY
		maskView 980
		maskLoop 4
		maskCel 2
	)
)

(instance icon5 of Kq6IconItem
	(properties
		view 980
		loop 5
		cel 0
		type NULL
		message 0
		signal (| RELVERIFY HIDEBAR IMMEDIATE)
		maskView 980
		maskLoop 5
		maskCel 2
	)
	
	(method (doit)
		((ScriptID 907 1) init: ego)
	)
)

(instance icon6 of Kq6IconItem
	(properties
		view 980
		loop 6
		cel 0
		message 0
		signal (| RELVERIFY HIDEBAR IMMEDIATE)
		maskView 980
		maskLoop 6
		maskCel 2
	)
	
	(method (doit)
		(if (>= (MemoryInfo LargestPtr) 1500)
			((ScriptID 903) init: show: dispose:)
		else
			(messager say: N_NOTNOW 0 C_NOMEMORY 0 0 0)
		)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(theGame setCursor: waitCursor)
				(theIconBar hide:)
				TRUE
			else
				FALSE
			)
		)
	)
)

(instance theGameCursor of Cursor
	(properties
		view 998
		loop 1
		cel 7
	)
)

(instance theWaitCursor of Cursor
	(properties
		view 998
		loop 1
		cel 8
	)
)

(instance cIcon0 of Cursor
	(properties
		view 998
		loop 1
	)
)

(instance cIcon1 of Cursor
	(properties
		view 998
		loop 1
		cel 2
	)
)

(instance cIcon2 of Cursor
	(properties
		view 998
		loop 1
		cel 1
	)
)

(instance cIcon3 of Cursor
	(properties
		view 998
		loop 1
		cel 3
	)
)

(instance arrowCursor of Cursor
	(properties
		view 998
		loop 1
		cel 7
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
