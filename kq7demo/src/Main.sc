;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Procs)
(use KQEgo)
(use DButton)
(use DText)
(use Plane)
(use Print)
(use Messager)
(use Talker)
(use PolyPath)
(use Polygon)
(use Cursor)
(use Timer)
(use Grooper)
(use Sound)
(use Game)
(use System)

(public
	KQ7 0
	interfacePlane 1
	proc0_2 2
	proc0_3 3
	respondSet 4
	lavaDeathTimer 5
	scorpDeathTimer 6
	desertDeathTimer 7
	stormDeathTimer 8
)

(local
	ego
	theGame
	curRoom
	thePlane
	quit
	cast
	regions
	timers
	sounds
	inventory
	planes
	curRoomNum
	prevRoomNum
	newRoomNum
	debugOn
	score
	possibleScore
	textCode
	cuees
	theCursor
	normalCursor
	waitCursor
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	eventMask =  allEvents
	bigFont =  USERFONT
	version
	autoRobot
	curSaveDir
	numCD
	perspective
	features
	panels
	useSortedFeatures
	unused_6
	overlays =  -1
	doMotionCue
	systemPlane
	saveFileSelText
	unused_8
	unused_2
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
	extMouseHandler
	talkers
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
	theGrooper
	gameCode =  1234
	theMusic
	theSoundFX
	global104
	global105
	numVoices
	global107 =  29
	theInterfaceCast
	scrollingIsOn
	theInterfacePlane
	debugging
	global112
	global113
	global114
	egoMoveSpeed =  6
	demoScripts
	global117
	global118
	global119
	global120
	invCursor
	curChapter
	hotCast
	interfaceHotCast
	global125
	global126
	gameFlags
	global128
	global129
	global130
	global131
	global132
	global133
	global134
	global135
	global136
	global137
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
	global151
	global152
	global153
	global154
	global155
	global156
	global157
	global158
	global159
	global160
	global161
	global162
	global163
	global164
	global165
	global166 =  1
	global167 =  2
	global168 =  3
	gNewSkeletonIconFileNumber
	global170
	global171
	curInvItem
	global173
	respondCode
	theExitFeature
	global176
	global177
	global178 =  1
	global179 =  1
)
(procedure (proc0_2 param1 param2 param3)
	(param1 at: param2 param3)
	(param1 at: (+ param2 1) (>> param3 $0008))
)

(procedure (proc0_3 param1 param2)
	(return
		(+
			(& (param1 at: param2) $00ff)
			(<< (param1 at: (+ param2 1)) $0008)
		)
	)
)

(instance interfacePlane of Plane)

(instance interfaceCast of Cast)

(instance gameHotCast of Cast)

(instance intFaceHotCast of Cast)

(instance theWaitCursor of Cursor
	(properties
		view HAND_CURSOR
	)
)

(instance theNormalCursor of Cursor
	(properties
		view ARROW_CURSOR
	)
)

(instance theInvCursor of Cursor
	(properties
		view 990
	)
)

(class KQ7 of Game
	(properties
		isHandsOn 0
		currentSpeed 6
		oldCurs 0
	)
	
	(method (init &tmp [temp0 9] versionFile)
		(ScriptID SIGHT)
		DText
		DButton
		Print
		Polygon
		PolyPath
		(super init:)
		((ScriptID KQINIT 0) doit:)
		(kqMusic1 owner: self flags: mNOPAUSE init:)
		(kqSound1 flags: mNOPAUSE owner: self init:)
		(= systemPlane (Plane new:))
		(= theInterfaceCast (interfaceCast add:))
		(interfacePlane
			init: 0 137 319 199
			priority: 2
			addCast: interfaceCast
		)
		(interfaceCast plane: interfacePlane)
		(= theInterfacePlane interfacePlane)
		(= hotCast (gameHotCast add:))
		(= interfaceHotCast (intFaceHotCast add:))
		(respondSet add:)
		((ScriptID 18 0) doit:)
		(= version {xx.yyy.zzz})
		(= versionFile (FileIO FileOpen {version} 1))
		(FileIO FileFGets version 11 versionFile)
		(FileIO FileClose versionFile)
		(= ego KQEgo)
		(= theCursor theNormalCursor)
		(= normalCursor theNormalCursor)
		(= waitCursor theWaitCursor)
		(= invCursor theInvCursor)
		(= oldCurs normalCursor)
		(= theGrooper GradualLooper)
		(= doVerbCode kqDoVerbCode)
		(= approachCode kqApproachCode)
		(= respondCode kqRespondCode)
		(= messager kqMessager)
		(= narrator Narrator)
		(= theSoundFX kqSound1)
		(= theMusic kqMusic1)
		(= isHandsOn 0)
		(ScriptID SPEEDTEST_ROOM)
		(self newRoom: 30)
	)
	
	(method (startRoom roomNum &tmp [temp0 4])
		(if (and oldCurs (== (oldCurs view?) 989))
			(oldCurs view: 999 loop: 0 cel: 0)
		)
		(if
			(OneOf roomNum
				4350 4400 4050 4000 4100 4200
				4250 4300 4450 4500 4550 4650
				4700 4600
			)
			(ScriptID 4001)
			((ScriptID roomNum) setRegions: 4001)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event)
		(if (and debugging (== (event type?) keyDown))
			(switch (event message?)
				(`#5
					(theGame save:)
					(event claimed: TRUE)
				)
				(`#7
					(theGame restore:)
					(event claimed: TRUE)
				)
				(`+
					(if (user controls?)
						(= egoMoveSpeed (Max 0 (-- egoMoveSpeed)))
						(theGame currentSpeed: egoMoveSpeed)
						(ego setSpeed: egoMoveSpeed)
					)
				)
				(`-
					(if (user controls?)
						(++ egoMoveSpeed)
						(theGame currentSpeed: egoMoveSpeed)
						(ego setSpeed: egoMoveSpeed)
					)
				)
				(`=
					(if (user controls?)
						(= egoMoveSpeed 6)
						(ego setSpeed: 6)
					)
				)
			)
		)
		(if (and debugging (== (event type?) keyDown))
			((ScriptID DEBUG) handleEvent: event)
			((ScriptID DEBUG) dispose:)
			(DisposeScript DEBUG)
		)
		(cond 
			((event claimed?) (return TRUE))
			((and (& (event type?) userEvent) (user canControl:))
				(self pragmaFail: (event message?))
				(event claimed: TRUE)
			)
		)
		(return (event claimed?))
	)
	
	(method (pragmaFail &tmp [temp0 2])
		(Prints {pragmaFail})
	)
	
	(method (handsOff)
		(if isHandsOn
			(= isHandsOn FALSE)
			(user canControl: FALSE canInput: FALSE)
			(= oldCurs (theGame setCursor: waitCursor TRUE))
		)
	)
	
	(method (handsOn)
		(if (not isHandsOn)
			(= isHandsOn TRUE)
			(user canControl: TRUE canInput: TRUE)
			(theGame setCursor: oldCurs TRUE)
		)
	)
)

(instance kqDoVerbCode of Code
	
	(method (doit)
		(Prints {doVerbCode})
	)
)

(instance kqApproachCode of Code

	(method (doit theVerb)
		(switch theVerb
			(9 $0001)
			(7 $0002)
			(10 $0004)
			(8 $0008)
			(else  $8000)
		)
	)
)

(instance kqMessager of Messager
	
	(method (say)
		(if (and narrator (narrator initialized?))
			(narrator cueVal: 1 dispose:)
		)
		(super say: &rest)
	)
	
	(method (sayRange)
		(if (and narrator (narrator initialized?))
			(narrator cueVal: 1 dispose:)
		)
		(super sayRange: &rest)
	)
	
	(method (findTalker who &tmp theTalker)
		(if (= theTalker narrator)
			(return)
		else
			(super findTalker: who)
		)
	)
)

(instance kqMusic1 of Sound)

(instance kqSound1 of Sound)

(instance kqRespondCode of Code
	
	(method (doit param1)
		(switch param1
			((respondSet at: 0) $0001)
			((respondSet at: 1) $0002)
			((respondSet at: 2) $0004)
			((respondSet at: 3) $0008)
			((respondSet at: 4) $0010)
			((respondSet at: 5) $0020)
			((respondSet at: 6) $0040)
			((respondSet at: 7) $0080)
			((respondSet at: 8) $0100)
			((respondSet at: 9) $0200)
			((respondSet at: 10) $0400)
			((respondSet at: 11) $0800)
			((respondSet at: 12) $1000)
			((respondSet at: 13) $2000)
			((respondSet at: 14) $4000)
			(else  $8000)
		)
	)
)

(instance respondSet of Set)

(instance lavaDeathTimer of Timer
	
	(method (cue)
		(Prints
			{You took too long and the lava fills the tunnels... you flesh melts off of your skeleton.}
		)
		(Bset 247)
		(EgoDead deathLAVA curRoom)
	)
)

(instance scorpDeathTimer of Timer
	
	(method (cue)
		(Bclr fScorpionTrapped)
		(Prints {The scorpion has freed himself.})
		(if (== curRoom 1450)
			(curRoom cue:)
			(self client: 0 delete: dispose:)
		)
	)
)

(instance desertDeathTimer of Timer

	(method (cue)
		(if (or (Btst 17) (curRoom script?))
			(self setReal: self 15)
		else
			(Bset 120)
			(curRoom cue:)
			(self client: 0 delete: dispose:)
		)
	)
)

(instance stormDeathTimer of Timer
	
	(method (cue)
		(if (or (Btst 17) (curRoom script?))
			(self setReal: self 15)
		else
			(self client: 0 delete: dispose:)
			(curRoom cue:)
		)
	)
)
