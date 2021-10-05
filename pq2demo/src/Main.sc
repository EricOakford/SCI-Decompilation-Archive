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
	InRoom 14
	PutInRoom 15
	Bset 16
	Bclr 17
	Btst 18
	NormalEgo 20
)

(local
	ego									;pointer to ego
	theGame								;ID of the Game instance
	curRoom								;ID of current room
	speed =  6							;number of ticks between animations
	quit								;when TRUE, quit game
	cast								;collection of actors
	regions								;set of current regions
	timers								;list of timers in the game
	sounds								;set of sounds being played
	inventory							;set of inventory items in game
	addToPics							;list of views added to the picture
	curRoomNum							;current room number
	prevRoomNum							;previous room number
	newRoomNum							;number of room to change to
	debugOn								;generic debug flag -- set from debug menu
	score								;the player's current score
	possibleScore						;highest possible score
	showStyle	=		IRISOUT			;style of picture showing
	aniInterval							;# of ticks it took to do the last animation cycle
	theCursor							;the number of the current cursor

	normalCursor =		ARROW_CURSOR	;number of normal cursor form
	waitCursor	 =		HAND_CURSOR		;cursor number of "wait" cursor
	userFont	 =		USERFONT		;font to use for Print
	smallFont	 =		4				;small font for save/restore, etc.
	lastEvent							;the last event (used by save/restore game)
	modelessDialog						;the modeless Dialog known to User and Intrface
	bigFont		=		USERFONT		;large font
	volume		=		12				;sound volume
	version		=		{x.yyy.zzz}		;pointer to 'incver' version string			
	locales								;set of current locales
	[curSaveDir 20]						;address of current save drive/directory string
	aniThreshold	=	10
	perspective							;player's viewing angle:
										;	 degrees away from vertical along y axis
	features							;locations that may respond to events
	sortedFeatures          			;above+cast sorted by "visibility" to ego
	sortedCast							;cast sorted by "visibility" to ego
	deleteCastMember					;a member of the cast needs deleting
	skipFactor
	overlays =  -1
	;globals 58 to 99 are unused
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
	;globals 100 and above are for game use
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
	bainsInCoveTimer
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
	thisTime
	oldSysTime
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
	;check how much heap is available
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (RedrawCast)
	;re-animate the cast without cycling
	(Animate (cast elements?) FALSE)
)

(procedure (cls)
	;clear modeless dialog from the screen
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
	;disable ego control
	(cond 
		((== argc 1)
			(= global243 1)
		)
		(global243
			(= global244 1)
		)
	)
	(= isHandsOff TRUE)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
)

(procedure (HandsOn)
	;enable ego control
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

(procedure (InRoom what where)
	;check whether an inventory object is in a room.
	; If no room is specified, it assumes the current room.
	(return
		(==
			((inventory at: what) owner?)
			(if (== argc 1) curRoomNum else where)
		)
	)
)

(procedure (PutInRoom what where)
	;put an inventory object in a room.
	; If no room is specified, it assumes the current room.
	((inventory at: what)
		owner: (if (== argc 1) curRoomNum else where)
	)
)

(procedure (Bset flagEnum)
	;Set a boolean game flag
	(|= [gameFlags (/ flagEnum 16)]
		(>> $8000 (mod flagEnum 16))
	)
)

(procedure (Bclr flagEnum)
	;Clear a boolean game flag
	(&= [gameFlags (/ flagEnum 16)]
		(~ (>> $8000 (mod flagEnum 16)))
	)
)

(procedure (Btst flagEnum)
	;Test a boolean game flag
	(return
		(& [gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (NormalEgo)
	;normalizes ego's animation
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		setCycle: Walk
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		setStep: 3 2
		ignoreActors: FALSE
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
	(method (doit strg)
		(Format strg 0 0 score possibleScore)
	)
)

(instance PQ of Game
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
		(music
			owner: self
			number: 6
			init:
		)
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
		(if (!= oldSysTime (= thisTime (GetTime TRUE)))
			(= oldSysTime thisTime)
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
		(if (> bainsInCoveTimer 0)
			(-- bainsInCoveTimer)
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
		(if (and (== diverState 11) (< (diverClock timeLeft?) 1))
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
		(if (and (< bainsInCoveTimer 250) bainsInCove)
			(= bainsInCove 2)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp [temp0 54])
		(if (event claimed?) (return))
	)
)
