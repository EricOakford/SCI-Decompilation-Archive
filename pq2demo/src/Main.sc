;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include menu.sh)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Menu)
(use System)

(public
	PQ 0
	HaveMem 4
	RedrawCast 5
	cls 6
	NotClose 7
	AlreadyTook 8
	DontHave 9
	CantDo 10
	HandsOff 11
	HandsOn 12
	DontHaveGun 13
	CheckItemOwner 14
	SetItemOwner 15
	Bset 16
	Bclr 17
	Btst 18
	NormalEgo 20
)

(local
	ego
	theGame
	curRoom
	speed =  6
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
	showStyle =  IRISOUT
	aniInterval
	theCursor
	normalCursor =  ARROW_CURSOR
	waitCursor =  HAND_CURSOR
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  USERFONT
	volume =  12
	version =  {diverClock}
	locales
	curSaveDir
		global31
		global32
		global33
		global34
		global35
		global36
		global37
		global38
		global39
		global40
		global41
		global42
		global43
		global44
		global45
		global46
		global47
		global48
		global49
	aniThreshold =  10
	perspective
	features
	sortedFeatures
	sortedCast
	deleteCastMember
	skipFactor
	overlays =  -1
		global58
		global59
		global60
		global61
		global62
		global63
		global64
		global65
		global66
		global67
		global68
		global69
		global70
		global71
		global72
		global73
		global74
		global75
		global76
		global77
		global78
		global79
		global80
		global81
		global82
		global83
		global84
		global85
		global86
		global87
		global88
		global89
		global90
		global91
		global92
		global93
		global94
		global95
		global96
		global97
		global98
		lastSysGlobal
	gamePhase
	debugging
	global102
	gunWindageScrew
	gunElevationScrew
	global105
	isHandsOff
	dollars
	gGEgoX
	global109
	howFast
	global111
	keith
	sewerRat
	sewerLight
	sewerLight2
	theFieldKit
	global117
	global118
	global119
	global120
	global121
	global122
	global123
	global124
	gDView
	dateState
	global127
	global128
	global129
	roomCarParked
	currentCar
	global132
	workCarLocked
	global134
	workCarTrunkOpened
	personalCarLocked
	global137
	isOnDuty
	sewerCutscene
	wearingGasMask
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
	methaneGasTimer
	global157
	captainWarningTimer
	global159
	global160
	marieWantsCall
	global162
	talkedToKeith
	talkedToCaptain
	global165
	gunSightsAligned
	lloydInRehab
	global168
	global169
	global170
	global171
	global172
	global173
	global174
	showedBadgeToMotelManager
	global176
	global177
	global178
	global179
	global180
	global181
	global182
	global183
	removedBodyFromRiver
	global185
	docCoveTimer
	global187
	bainsInCove
	diverState
	correctScubaTank
	scubaTankOxygen

	global192
	global193
	global194
	airplaneToSteelton
	manholeIsOpen
	egoDrunk
	stewardess
	hijacker1
	hijacker2
	sittingInPlane
	wearingSeatbelt
	untiedMarie
	gunDrawn
	global205
	cSound
	bulletsInGun
	global208
	unprotectedShots
	global210
	gunNotNeeded
	gunFireState	;response to firing your gun varies by this global.
					; if this is 3 (gunfire prohibited), firing the gun
					; gets Sonny institutionalized, ending the game.
	global213
	global214
	numAmmoClips
	global216
	global217
	global218
	global219
	global220
	global221
	global222
	global223
	global224
	global225
	global226
	global227
	global228
	global229
	global230
	global231
	global232
	global233
	global234
	global235
	global236
	muggerFleeing
	muggerArrested
	gMethaneGasTimer
	fieldKitOpen
	global241
	global242
	global243
	global244
	global245
	global246
	global247
	global248
	global249
	gameFlags
		global251
		global252
		global253
		global254
		global255
		global256
		global257
		global258
		global259
		global260
		global261
		global262
		global263
		global264
		global265
		global266
		global267
		global268
		global269
)
(procedure (HaveMem howMuch)
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (NotClose)
	(Print 0 1)
)

(procedure (AlreadyTook)
	(Print 0 2)
)

(procedure (DontHave)
	(Print 0 4)
)

(procedure (CantDo)
	(Print 0 3)
)

(procedure (HandsOff)
	(cond 
		((== argc 1) (= global243 1))
		(global243 (= global244 1))
	)
	(= isHandsOff TRUE)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
)

(procedure (HandsOn)
	(if (not global244)
		(= isHandsOff FALSE)
		(User canControl: TRUE canInput: TRUE)
	)
	(if (== argc 1)
		(= global243 0)
		(= global244 0)
	)
)

(procedure (DontHaveGun)
	(Print 0 5)
)

(procedure (CheckItemOwner item owner)
	(return
		(==
			((inventory at: item) owner?)
			(if (== argc 1) curRoomNum else owner)
		)
	)
)

(procedure (SetItemOwner item owner)
	((inventory at: item)
		owner: (if (== argc 1) curRoomNum else owner)
	)
)

(procedure (Bset flagEnum)
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Bclr flagEnum)
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
)

(procedure (Btst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (NormalEgo)
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		setCycle: Walk
		illegalBits: -32768
		cycleSpeed: 0
		moveSpeed: 0
		setStep: 3 2
		ignoreActors: 0
	)
)

(instance diverClock of TimeOut)

(instance continuousMusic of Sound)

(instance music of Sound
	(properties
		priority 10
	)
)

(instance statusCode of Code
	(properties)
	
	(method (doit strg)
		(Format strg 0 0 score possibleScore)
	)
)

(instance PQ of Game
	(properties)
	
	(method (init &tmp [temp0 21])
		(super init:)
		(TheMenuBar init:)
		(StatusLine code: statusCode)
		(Bset fEgoDeskUnlocked)
		(= showStyle HSHUTTER)
		(DoSound ChangeVolume 20)
		((= cSound continuousMusic)
			owner: self
			number: 6
			init:
		)
		(music owner: self number: 6 init:)
		(if (GameIsRestarting)
			(TheMenuBar draw:)
			(StatusLine enable:)
			(self newRoom: 99)
			(= userFont bigFont)
		else
			(self newRoom: 200)
		)
	)
	
	(method (doit)
		(super doit:)
		(diverClock doit:)
		(if (!= global242 (= global241 (GetTime TRUE)))
			(= global242 global241)
			(if (> captainWarningTimer 1)
				(-- captainWarningTimer)
			)
			(if (> global159 1)
				(-- global159)
			)
		)
		(if (> global157 1)
			(-- global157)
		)
		(if (> global170 1)
			(-- global170)
		)
		(if (> global171 1)
			(-- global171)
		)
		(if (> docCoveTimer 0)
			(-- docCoveTimer)
		)
		(if (== diverState 1)
			(diverClock set: 600)
			(= diverState 2)
		)
		(if (and (== diverState 2) (< (diverClock timeLeft?) 1))
			(= diverState 3)
		)
		(if (== diverState 10)
			(diverClock set: 500)
			(= diverState 11)
		)
		(if
		(and (== diverState 11) (< (diverClock timeLeft?) 1))
			(= removedBodyFromRiver TRUE)
			(= diverState 12)
		)
	)
	
	(method (replay)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(SetMenu soundI
			p_text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(super replay:)
	)
	
	(method (startRoom roomNum &tmp [temp0 2] evt [temp3 50])
		(while ((= evt (Event new:)) type?)
			(evt dispose:)
		)
		(evt dispose:)
		(DisposeScript 301)
		(if
			(and
				(!= roomNum 1)
				(!= roomNum 13)
				(!= roomNum 14)
				(!= roomNum 22)
				(!= roomNum 25)
				(!= roomNum 225)
				(!= roomNum 27)
				(!= roomNum 29)
				(!= roomNum 31)
				(!= roomNum 33)
				(!= roomNum 61)
				(!= roomNum 67)
			)
			(SetMenu carI p_value FALSE)
		else
			(SetMenu carI p_value TRUE)
		)
		(if (and (< docCoveTimer 250) bainsInCove)
			(= bainsInCove 2)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp [temp0 54])
		(if (event claimed?) (return))
	)
)
