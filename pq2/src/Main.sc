;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include menu.sh)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Menu)
(use System)

(public
	PQ 0
	EgoDead 2
	NotifyScript 3
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
	AssignObjectToScript 19
	NormalEgo 20
	SolvePuzzle 22
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
	useSortedFeatures					;enable cast & feature sorting?
	demoScripts							;add to curRoomNum to find room demo script
	egoBlindSpot						;used by sortCopy to exclude
										;actors behind ego within angle 
										;from straight behind. 
										;Default zero is no blind spot
	overlays	=		-1
	doMotionCue							;a motion cue has occurred - process it
	systemWindow						;ID of standard system window
	demoDialogTime	=	3				;how long Prints stay up in demo mode
	currentPalette
	;globals 62-99 are unused
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
		doCoveTimer ;global86
		global87
		bainsInCove ;global88
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
	bainsInCoveState
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
	gunFireState
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
		global270
)
(procedure (EgoDead)
	;disposes all sounds, plays the death music, and gives the player a choice:
	;	Restore, Restart, Quit
	(HandsOff)
	(Wait 0)
	(Wait 150)
	(sounds eachElementDo: #dispose)
	(music number: 2 play:)
	(repeat
		(switch
			(Print &rest
				#title {Jim shakes his head and says...}
				#width 184
				#icon 555 0 0
				#button {Restore} 1
				#button { Restart_} 2
				#button { Quit_} 3
			)
			(1
				(theGame restore:)
			)
			(2
				(theGame restart:)
			)
			(3
				(= quit TRUE)
				(break)
			)
		)
	)
)


(procedure (NotifyScript script &tmp i)
	(= i (ScriptID script))
	(i notify: &rest)
	;((ScriptID script) notify: &rest)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo LargestPtr) howMuch))
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
	(Print 0 32)
)

(procedure (AlreadyTook)
	(Print 0 33)
)

(procedure (DontHave)
	(Print 0 35)
)

(procedure (CantDo)
	(Print 0 34)
)

(procedure (HandsOff)
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
	(Print 0 36)
)

(procedure (InRoom what where)
	(return
		(==
			((inventory at: what) owner?)
			(if (== argc 1) curRoomNum else where)
		)
	)
)

(procedure (PutInRoom what where)
	((inventory at: what)
		owner: (if (== argc 1) curRoomNum else where)
	)
)

(procedure (Bset flagEnum)
	(|= [gameFlags (/ flagEnum 16)] (>> $8000 (mod flagEnum 16)))
)

(procedure (Bclr flagEnum)
	(&= [gameFlags (/ flagEnum 16)] (~ (>> $8000 (mod flagEnum 16))))
)

(procedure (Btst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (AssignObjectToScript obj theScript theState)
	(switch argc
		(2
			(obj setScript: theScript)
		)
		(3
			(obj script: theScript)
			(theScript client: obj)
			(theScript changeState: theState)
		)
	)
	(while (obj script?)
		(Animate (cast elements?) TRUE)
		(if doMotionCue
			(= doMotionCue FALSE)
			(cast eachElementDo: #motionCue)
		)
		(Wait 5)
	)
)

(procedure (NormalEgo)
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		setCycle: Walk
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		setStep: 3 2
		ignoreActors: 0
	)
)

(procedure (SolvePuzzle pValue pFlag)
	(if (and (== argc 2) (Btst pFlag)) (return))
	(theGame changeScore: pValue)
	(if (Btst fGotPoints)
		(Bclr fGotPoints)
	else
		(music number: 6 play:)
	)
	(if (== argc 2)
		(Bset pFlag)
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

(instance egoObj of Ego
	(properties
		name {ego}
	)
)

(instance PQ of Game
	(method (init &tmp [temp0 21])
		(= systemWindow (SysWindow new:))
		(super init:)
		;(= debugging FALSE)
		(= debugging TRUE)	;added to enable debug features
		(= ego egoObj)
		(User alterEgo: ego blocks: 0 y: 155)
		(TheMenuBar init:)
		(StatusLine code: statusCode)
		(= possibleScore 300)
		(= captainWarningTimer 700)
		(= gunWindageScrew
			(switch (Random 1 2)
				(1 (Random 8 18))
				(2 (- 0 (Random 8 18)))
			)
		)
		(= gunElevationScrew
			(switch (Random 1 2)
				(1 (Random 6 14))
				(2 (- 0 (Random 6 14)))
			)
		)
		(= dollars 36)
		(= [numAmmoClips 1] 7)
		(= [numAmmoClips 2] 7)
		(= workCarLocked 1)
		(= global134 1)
		(= personalCarLocked 1)
		(= currentCar 33)
		(= methaneGasTimer -1)
		(= correctScubaTank (Random 1 3))
		(Bset fEgoDeskUnlocked)
		(= version {1.002.011})
		(Inventory
			add:
				hand_gun
				extra_ammo_clips
				key_ring
				unmarked_car_keys
				money_clip
				thank_you_letter
				death_threat
				wallet
				handcuffs
				wire_clippers
				field_kit
				potted_plant
				new_mug_shot
				hit_list
				makeshift_knife
				ear_protectors
				plane_ticket
				plaster_cast
				lost_badge
				thumbprint
				bullets
				empty_holster
				fingerprint
				old_mug_shot
				envelope_corner
				envelope
				jail_clothes
				motel_key
				vial_of_blood
				lipstick
				walkie_talkie
				jailer_s_revolver
				gas_mask
				bomb_instructions
				car_registration
				Colby_s_business_card
				note_from_Marie_s_door
				your_LPD_business_card
		)
		(ego get: iMoneyClip)
		(HandsOn)
		(= showStyle HSHUTTER)
		(DoSound ChangeVolume 15)
		((= cSound continuousMusic)
			owner: self
			number: 6
			init:
		)
		(music owner: self number: 6 init:)
		(if (GameIsRestarting)
			(Bset fGameIsRestarting)
			(TheMenuBar draw:)
			(StatusLine enable:)
			(self newRoom: 99)
		else
			(self newRoom: 99)
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
		(if (DoSound SoundOn)
			(SetMenu soundI p_value FALSE p_text {Turn Off})
		else
			(SetMenu p_value TRUE p_text {Turn On})
		)
		(super replay:)
	)
	
	(method (startRoom roomNum &tmp temp0 avd evt [temp3 50])
		(while ((= evt (Event new:)) type?)
			(evt dispose:)
		)
		(evt dispose:)
		(DisposeScript 301)
		(DisposeScript 976)
		(if (and (!= roomNum carWork) (!= roomNum carPersonal))
			(= avd Avoider)
		else
			(DisposeScript AVOIDER)
		)
		(if
			(and
				debugging
				(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
				(Print 0 4 #button {Debug} 1)
			)
			(SetDebug)
		)
		(= gunNotNeeded TRUE)
		(= gunFireState gunUSELESS)
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
			(SetMenu carI p_state FALSE)
		else
			(SetMenu carI p_state TRUE)
		)
		(= global132 0)
		(if (and (< bainsInCoveTimer 250) bainsInCoveState)
			(= bainsInCoveState 2)
		)
		(super startRoom: roomNum)
		(if
			(and
				gunDrawn
				(!= (ego view?) 7)
				(!= (ego view?) 306)
				(!= (ego view?) 6)
			)
			(= gunDrawn FALSE)
		)
		(if global214
			(= global214 0)
			(HandsOn 1)
			(switch (ego view?)
				(4 (ego view: 0))
				(5 (ego view: 1))
			)
		)
		(if (and (!= roomNum carWork) (!= roomNum carPersonal))
			(curRoom setLocales: regGun)
		)
		(if debugging
			(curRoom setLocales: DEBUG)
		)
		(Load SOUND 6)
	)
	
	(method (handleEvent event &tmp index [temp1 53])
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((and (Said 'kiss/angel>') (Said '/death'))
						(event claimed: TRUE)
						(if (^= debugging TRUE)
							(curRoom setLocales: DEBUG)
						)
					)
					((Said 'frisk,(look<in,through)/billfold')
						(if (not (ego has: iWallet))
							(DontHave)
						else
							(Print 800 50)
							(SolvePuzzle 2 fFirstFindDivingCard)
							(Bset fFoundDiverCard)
						)
					)
					((Said 'open/billfold')
						(if (ego has: iWallet)
							((inventory at: iWallet) showSelf:)
						else
							(DontHave)
						)
					)
					(
						(or
							(Said 'fuck,crap,cunt,cocksucker,leak')	;EO: removed kiss to allow debugging
							(Said '/fuck,crap,cunt,cocksucker,leak')
							(Said 'eat/crap,and,die')
						)
						(Print 800 (Random 0 7))
					)
					((Said 'read/instruction,newspaper')
						(if (ego has: iBombInstructions)
							(Print 800 53 #font smallFont)
						else
							(DontHave)
						)
					)
					((Said 'look,compare,check/handwriting,note,handwriting[<hand]')
						(if (ego has: iMarieDoorNote)
							(SolvePuzzle 3 fCheckMarieHandwriting)
							(Print 0 5
								#at -1 15
								#icon 136 0 0
							)
						else
							(DontHave)
						)
					)
					((Said 'holler')
						(Print 0 6)
					)
					((Said 'chat>')
						(cond 
							((Said '/friend')
								(cond 
									((== curRoomNum carWork)
										(Print 800 (Random 10 15))
									)
									(
										(or
											(not (cast contains: keith))
											(not (keith inRect: -10 -10 340 240))
										)
										(Print 0 7)
									)
									((> (ego distanceTo: keith) 30)
										(Print 0 8)
									)
									(else
										(Print 800 (Random 10 15))
									)
								)
							)
							((Said '/noword')
								(Print 0 9)
							)
							(else
								(event claimed: TRUE)
								(Print 0 10)
							)
						)
					)
					((or (Said '[say]/hello') (Said 'smile,wave'))
						(Print 0 11)
					)
					((Said '[say]/bye')
						(Print 0 12)
					)
					((Said 'open/door')
						(CantDo)
					)
					(
						(or
							(Said 'use,remove/powder,brush')
							(Said 'deposit,apply,use/powder,dust')
							(Said 'dust,powder[/anyword]')
							(Said 'get,remove,hoist/fingerprint,print[<finger]')
							(Said 'open,use,look/briefcase')
						)
						(if (== gamePhase 14)
							(Print 0 13)
						else
							(Print 0 14)
						)
					)
					((Said 'get/anyword')
						(event claimed: FALSE)
						(if
							(and
								(= index (inventory saidMe: event))
								(== (index owner?) ego)
							)
							(AlreadyTook)
						else
							(event claimed: TRUE)
							(Print 0 15)
						)
					)
					((Said 'use/anyword')
						(Print 0 16)
					)
					((Said 'deposit,deposit/anyword')
						(Print 0 14)
					)
					((Said 'ask>')
						(cond 
							((Said '/noword')
								(Print 0 17)
							)
							((Said '//noword')
								(Print 0 18)
							)
						)
					)
					((Said 'eat/9mm')
						(Print 0 19)
					)
					((Said 'eat')
						(Print 0 20)
					)
					((Said 'turn/card')
						(cond 
							((not (ego has: iLPDBusinessCard))
								(DontHave)
							)
							((your_LPD_business_card cel?)
								(your_LPD_business_card cel: 0 showSelf:)
							)
							(else
								(your_LPD_business_card cel: 1 showSelf:)
							)
						)
					)
					((or (Said 'thank') (Said '/thanks'))
						(Print 0 21)
					)
					((Said '/oop,oop')
						(Print 0 22)
					)
					((Said 'affirmative,n')
						(Print 0 23)
					)
					((Said 'sat')
						(Print 0 10)
					)
					((Said 'beat,kill,fire,beat')
						(Print 0 24)
					)
					((Said 'cigarette')
						(Print 800 51)
					)
					((Said 'gave[/anyword]')
						(Print 0 14)
					)
					((Said 'frisk>')
						(if (Said '/noword')
							(Print 0 25)
						else
							(event claimed: TRUE)
							(Print 0 26)
						)
					)
					((Said 'look,read>')
						(cond 
							((or (Said '/pocket<coat') (Said '/coat<pocket'))
								(event claimed: FALSE)
							)
							((Said '/pocket')
								(Print 0 27)
							)
							((Said '/certificate')
								(if (Btst fFoundDiverCard)
									(Print 800 49
										#icon 164 0 0
									)
								else
									(Print 800 52)
								)
							)
							((or (Said '<back/card') (Said '/back/card'))
								(if (ego has: iLPDBusinessCard)
									(your_LPD_business_card cel: 1 showSelf:)
								else
									(DontHave)
								)
							)
							((or (Said '<front/card') (Said '/front/card'))
								(if (ego has: iLPDBusinessCard)
									(your_LPD_business_card cel: 0 showSelf:)
								else
									(DontHave)
								)
							)
							((Said '/friend')
								(if
									(or
										(and
											(cast contains: keith)
											(< (ego distanceTo: keith) 150)
										)
										(== curRoomNum 13)
									)
									(Print 800 (Random 20 24))
								else
									(Print 0 7)
								)
							)
							(
								(or
									(Said '/bains,john,dooley,lloyd,gelepsi,captain,hall,james,pierson')
									(Said '/simpson,bob,adams,cole,jerome,ken,ken,saxton,luis')
									(Said '/roberts,calvin,calvin,willis,jerk,diver,chuck,colby,miller')
								)
								(Print 0 28)
							)
							((Said '/kim,holt,gomez,cheeks,cheeks,holt')
								(Print 0 29)
							)
							((Said '/boob')
								(if
									(or
										(== curRoomNum 3)
										(== curRoomNum 6)
										(== curRoomNum 61)
										(== curRoomNum 30)
										(== curRoomNum 40)
									)
									(Print 0 30)
								else
									(Print 0 31)
								)
							)
							((Said '/clock,wrist,time')
								(Print 800 9)
							)
							((= index (inventory saidMe: event))
								(if (ego has: (inventory indexOf: index))
									(index showSelf:)
								else
									(DontHave)
								)
							)
							(else
								(event claimed: TRUE)
								(Print 800 (Random 30 32))
							)
						)
					)
				)
			)
		)
	)
	
	(method (wordFail word &tmp [str 40])
		(Printf 0 1 word)
	)
	
	(method (syntaxFail)
		(Print 0 2
			#icon 555 1 0
		)
	)
	
	(method (pragmaFail &tmp [str 100])
		(Print 0 3)
	)
)

(class Iitem of InvItem
	(method (showSelf)
		(Print 899 (- view 100)
			#title name
			#icon view 0 cel)
	)
)

(instance hand_gun of Iitem
	(properties
		said '/9mm[<hand]'
		owner 5
		view 100
		name "hand gun"
	)
)

(instance extra_ammo_clips of Iitem
	(properties
		said '/ammo,(clip[<ammo])'
		owner 5
		view 101
		name "extra ammo clips"
	)
	
	(method (ownedBy param1)
		(switch bulletsInGun
			(0
				(= cel
					(+ 2 (> [numAmmoClips 1] 0) (> [numAmmoClips 2] 0))
				)
			)
			(1
				(= cel (> [numAmmoClips 2] 0))
			)
			(else 
				(= cel (> [numAmmoClips 1] 0))
			)
		)
		(super ownedBy: param1)
	)
)

(instance key_ring of Iitem
	(properties
		said '/ring<key'
		view 102
		name "key ring"
	)
	
	(method (saidMe event)
		(return
			(if (and (ego has: iUnmarkedCarKeys) (Said '/key'))
				(event claimed: FALSE)
				(return FALSE)
			else
				(return (if (Said '/key') else (Said said)))
			)
		)
	)
)

(instance unmarked_car_keys of Iitem
	(properties
		said '/key[<auto]'
		owner 4
		view 103
		name "unmarked car keys"
	)
)

(instance money_clip of InvItem
	(properties
		said '/coat,(pocket<coat),cash,(clip[<cash])'
		view 104
		name "money clip"
	)
	
	(method (showSelf &tmp [str 40])
		(Print
			(Format @str 0 37 dollars)
			#title name
			#icon view 0 (if (== dollars 0) 1 else 0)
		)
	)
)

(instance thank_you_letter of Iitem
	(properties
		said '/letter[<ya<thank]'
		owner 12
		view 105
		name "thank you letter"
	)
)

(instance death_threat of Iitem
	(properties
		said '/threat,note<death'
		owner 28
		view 106
		name "death threat"
	)
)

(instance wallet of Iitem
	(properties
		said '/billfold,badge,(card<badge)'
		owner 12
		view 107
	)
	
	(method (saidMe event)
		(return
			(if (and (ego has: iLostBadge) (Said '/badge'))
				(event claimed: FALSE)
				(return FALSE)
			else
				(return (Said said))
			)
		)
	)
)

(instance handcuffs of Iitem
	(properties
		said '/arrest'
		owner 5
		view 108
	)
)

(instance wire_clippers of Iitem
	(properties
		said '/clipper,clipper'
		view 109
		name "wire clippers"
	)
)

(instance field_kit of Iitem
	(properties
		said '/briefcase[<field]'
		owner 2
		view 110
		name "field kit"
	)
)

(instance potted_plant of Iitem
	(properties
		said '/flower,rose,plant,bouquet'
		owner 15
		view 111
		name "potted plant"
	)
	
	(method (showSelf &tmp [str 40])
		(Print
			(Format @str 0 38
				(switch cel
					(0 {potted plant})
					(1 {single long-stemmed rose})
					(2 {bouquet})
				)
			)
			#title name
			#icon view loop cel
		)
	)
	
	(method (ownedBy whom)
		(switch cel
			(0 (= name {potted plant}))
			(1 (= name {rose}))
			(2 (= name {bouquet}))
		)
		(super ownedBy: whom)
	)
)

(instance new_mug_shot of Iitem
	(properties
		said '/mugshot,(shot<mug)'
		owner 23
		view 112
		name "new mug shot"
	)
	
	(method (saidMe event)
		(return
			(cond 
				((!= owner ego)
					(return FALSE)
				)
				((or (Said '/mugshot<old') (Said '/shot<mug<old'))
					(event claimed: FALSE)
					(return FALSE)
				)
				(else
					(return (Said said))
				)
			)
		)
	)
)

(instance hit_list of Iitem
	(properties
		said '/list[<body,beat]'
		owner 32
		view 113
		name "hit list"
	)
)

(instance makeshift_knife of Iitem
	(properties
		said '/knife'
		owner 64
		view 114
		name "makeshift knife"
	)
)

(instance ear_protectors of Iitem
	(properties
		said '/ep[<ear]'
		owner 10
		view 115
		name "ear protectors"
	)
)

(instance plane_ticket of InvItem
	(properties
		said '/ticket[<airplane]'
		view 116
		name "plane ticket"
	)
	
	(method (showSelf &tmp [str 40])
		(Print
			(Format @str 0 39
				(if (== airplaneToSteelton TRUE) {Steelton} else {Houston})
			)
			#title name
			#icon view 0 0
		)
	)
)

(instance plaster_cast of Iitem
	(properties
		said '/cast,(print<feet)'
		view 117
		name "plaster cast"
	)
)

(instance lost_badge of Iitem
	(properties
		said '/badge'
		owner 63
		view 118
		name "lost badge"
	)
)

(instance thumbprint of Iitem
	(properties
		said '/thumb,(print<thumb)'
		view 119
	)
)

(instance bullets of Iitem
	(properties
		said '/bullet'
		owner 68
		view 120
	)
)

(instance empty_holster of Iitem
	(properties
		said '/gunbelt'
		owner 68
		view 121
		name "empty holster"
	)
)

(instance fingerprint of Iitem
	(properties
		said '/(print<finger),fingerprint'
		view 122
	)
)

(instance old_mug_shot of Iitem
	(properties
		said '/mugshot,(shot<mug)'
		owner 7
		view 123
		name "old mug shot"
	)
)

(instance envelope_corner of Iitem
	(properties
		said '/corner[<envelope]'
		owner 28
		view 124
		name "envelope corner"
	)
)

(instance envelope of Iitem
	(properties
		said '/envelope'
		owner 26
		view 125
	)
)

(instance jail_clothes of Iitem
	(properties
		said '/cloth'
		owner 62
		view 126
		name "jail clothes"
	)
)

(instance motel_key of Iitem
	(properties
		said '/key<inn'
		owner 25
		view 127
		name "motel key"
	)
)

(instance vial_of_blood of Iitem
	(properties
		said '/vial,blood,sample[<blood]'
		owner 26
		view 128
		name "vial of blood"
	)
)

(instance lipstick of Iitem
	(properties
		said '/television,(baton<lip),lipstick'
		owner 26
		view 129
	)
)

(instance walkie_talkie of Iitem
	(properties
		said '/(talkie[<walkie]),extender'
		owner 101
		view 130
		name "walkie talkie"
	)
)

(instance jailer_s_revolver of Iitem
	(properties
		said '/revolver,(9mm<jailer)'
		owner 19
		view 131
		name "jailer's revolver"
	)
)

(instance gas_mask of Iitem
	(properties
		said '/mask[<gas]'
		owner 126
		view 132
		name "gas mask"
	)
)

(instance bomb_instructions of Iitem
	(properties
		said '/instruction[<bomb]'
		view 133
		name "bomb instructions"
	)
)

(instance car_registration of Iitem
	(properties
		said '/registration'
		view 134
		name "car registration"
	)
)

(instance Colby_s_business_card of Iitem
	(properties
		said '/(card<business<colby),(card<colby)'
		owner 26
		view 135
		name "Colby's business card"
	)
	
	(method (saidMe event)
		(return
			(if (and (ego has: iLPDBusinessCard) (Said '/card[<noword]'))
				(event claimed: FALSE)
				(return FALSE)
			else
				(return (if (Said '/card[<noword]') else (Said said)))
			)
		)
	)
)

(instance note_from_Marie_s_door of Iitem
	(properties
		said '/note[<door]'
		owner 31
		view 136
		name "note from Marie's door"
	)
)

(instance your_LPD_business_card of Iitem
	(properties
		said '/card[<business]'
		owner 33
		view 137
		name "your LPD business card"
	)
)
