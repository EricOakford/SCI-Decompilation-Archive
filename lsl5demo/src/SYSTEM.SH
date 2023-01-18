;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;SYSTEM - Header file




;System file name / script numbers
;
(define	SYSTEM		999)
(define	ACTOR			998)
(define	MENU			997)
(define	USER			996)
(define	INVENT		995)
(define	GAME			994)
(define	FILE			993)
(define	MOTION		992)
(define	JUMP			991)
(define	SAVE			990)
(define	MUSIC			989)
(define	EXTRA			988)
(define	GAUGE			987)
(define	ORBIT			986)
(define	AVOIDER		985)
(define	SORTCOPY		984)
(define	PATH			983)
(define	SIGHT			982)
(define	WINDOW		981)
(define	TEXTRA		980)
(define	MOUSER		979)
(define	RFEATURE		978)
(define	GROOPER		977)
(define	CAT			976)
(define	DEMO			975)
(define	NAMEFIND		974)
(define	TIMER			973)
(define	CHASE			972)
(define	FOLLOW		971)
(define	WANDER		970)
(define	REVERSE		969)
(define	SMOOPER		968)
(define	DCICON		967)
(define	SORT			966)
(define	COUNT			965)
(define	DPATH			964)
(define	RELDPATH		963)
(define	QSCRIPT		962)
(define	STOPWALK		961)
(define	TIMEDCUE		960)
(define	QSOUND		959)
(define	LOADMANY		958)	;load/unload many modules
(define	LASTLINK		957)	;follow a specified link to the bitter end
(define	FORCOUNT		956)	;forward-loop a specified number of times
(define	TRACK			955)	;motion to track an actor at an x/y offset.
(define	GOTOSAID		954)	;turn to or approach "said" object.
(define	APPROACH		953)	;like Chase but for immobile targets.
(define	LOGGER		952)	; system logging script
(define	MOVEFWD     951)	;move an actor forward based on heading.
(define FEATURE		950)
(define BLOCK			949)
(define WRITEFTR		948)
(define DELAYEVT		947)
(define POLYGON		946)
(define POLYPATH		945)
(define FILESEL		944)
(define POLYEDIT		943)
(define MOVECYC		942)
(define RANDCYC		941)
(define PRINTD			940)
(define OSC				939)	;cycle a props cels back and forth
(define PCYCLE			938)	;cycle a props palette
(define ICONBAR		937)
(define BORDWIND		936)
(define SLIDERS		935)
(define SLIDEICON	934)
(define PMOUSE		933)
(define LANGUAGE		932)
(define CDACTOR		931)
(define PCHASE		930)
(define SYNC			929)
(define TALKER		928)
(define PAVOID		927)
(define FLIPPOLY		926)

(define	INTRFACE		255)



(define	INFINITY $7fff)	

(define	NODIRECTION $7fff);Arbitrary value for an undefined heading
(define goToSaid		$8000);or with longRangeDist to indicate goToSaid desired 
(define turnIfSaid	$8000);or with sightAngle to indicate turnIfSaid desired

;Some selector aliases
;;;(define	nowSeen	nsTop)
;;;(define	lastSeen	lsTop)
;;;(define	baseRect	brTop)

;Defines for the system view
(define	SYSVIEW			999)
;;;(define	STOPSIGN			SYSVIEW 0 0)
;;;(define	QUESTIONMARK	SYSVIEW 0 1)
;;;(define	EXCLAMATION		SYSVIEW 0 2)

; Standard cursor IDs
(define	ARROW_CURSOR	999)
(define	SNAIL_CURSOR	998)
(define	HAND_CURSOR 	20)

;Text rendering
(define	TPLAIN	0)
(define	TDIMMED	1)


;Dialog item types
(define	dButton	1)
(define	dText	2)
(define	dEdit	3)
(define	dIcon	4)
(define	dMenu	5)				; the title portion
(define	dSelector	6)

;Dialog item states
(define	dActive		$0001)
(define	dExit			$0002)
(define	dBold			$0004)
(define	dSelected	$0008)
(define dIconTop		$0010)

; Window manager defines
(define 	stdWindow	0)
(define	wTitled		4)


;Text justification for DItem mode
(define teJustLeft	0)
(define teJustCenter 1)
(define teJustRight -1)


;Event types
(define	nullEvt		$0000)
(define	mouseDown	$0001)
(define	mouseUp		$0002)
(define	keyDown		$0004)
(define	keyUp			$0008)
(define	menuStart	$0010)
(define	menuHit		$0020)
(define	direction	$0040)
(define	saidEvent	$0080)
(define	joyDown		$0100)
(define	joyUp			$0200)
(define	userEvent	$4000)

(define	allEvents	$7fff)

(define	leaveIt		$8000)

; event modifiers (in who property)
(define	shiftRight	1)		; right shift key pressed
(define	shiftLeft	2)		; left shift key pressed
(define	shiftDown	3)		; either shift key pressed
(define	ctrlDown		4)		; control key pressed
(define	altDown		8)		; alt key down pressed

; pseudo selectors for SetMenu, Display, and Print (eventually)
; must be duplicated by #define's in KERNEL.H
(define p_at		100)
(define p_mode		101)
(define p_color	102)
(define p_back		103)
(define p_style	104)
(define p_font		105)
(define p_width	106)
(define p_save		107)
(define p_restore	108)
(define p_said		109)
(define p_text		110)
(define p_key		111)
(define p_state	112)
(define p_value	113)
(define p_dispose	114)
(define p_time		115)
(define p_title	116)
(define p_draw		117)
(define p_edit		118)
(define p_button	119)
(define p_icon		120)
(define p_noshow		121)

;Direction event messages.
(enum
	dirStop
	dirN
	dirNE
	dirE
	dirSE
	dirS
	dirSW
	dirW
	dirNW
)

;Standard loop order for actors
(enum
	loopE
	loopW
	loopS
	loopN
	
	loopSE
	loopSW
	loopNE
	loopNW
)

(enum												;**	Additional Standard loops
	facingEast
	facingWest
	facingSouth
	facingNorth
 	facingSE
	facingSW
	facingNE
	facingNW
)


; some keycode definitions
(define	ESC			27)
(define	SPACEBAR		32)
(define	ENTER			`^m)
(define	TAB			`^i)
(define	SHIFTTAB		$0f00)
(define	BACKSPACE	`^h)


; Numeric key code in scan code order with missing codes added
(define	HOMEKEY		$4700)
(define	UPARROW		$4800)
(define	PAGEUP		$4900)

(define	LEFTARROW	$4b00)
(define	CENTERKEY	$4c00)
(define	RIGHTARROW	$4d00)

(define	ENDKEY		$4f00)
(define	DOWNARROW	$5000)
(define	PAGEDOWN	$5100)

(define	INSERT		$5200)
(define	DELETE		$5300)



;Screen colors
(define	BLACK			0)

;Screen dimensions
(define	MINTOP		0)
(define	MINLEFT		0)
(define	MAXRIGHT		320)
(define	MAXBOTTOM	200)



;Resource types
(define	VIEW		$80)
(define	PICTURE	$81)
(define	SCRIPT	$82)
(define	TEXT		$83)
(define	SOUND		$84)
(define	MEMORY	$85)
(define	VOCAB		$86)
(define	FONT		$87)
(define	CURSOR	$88)


;Virtual bitmap ID's
(define	VMAP		1)
(define	PMAP		2)
(define	CMAP		4)


;Picture change style constants
(define	HWIPE			0)
(define	HSHUTTER		0)
(define	VSHUTTER		1)
(define	WIPELEFT		2)
(define	WIPERIGHT	3)
(define	WIPEUP		4)
(define	WIPEDOWN		5)
(define	IRISIN		6)
(define	IRISOUT		7)
(define	DISSOLVE		8)
(define PIXELDISSOLVE 9)
; this style defaults to PLAIN on 16 color sci
(define	FADEOUT			30)		;Defaults to PLAIN in 16 color

(define	SCROLLRIGHT		40)		;Relative order of these four is important
(define	SCROLLLEFT		41)		;  in order for ShowPic to work correctly
(define	SCROLLUP		42)
(define	SCROLLDOWN		43)

(define	PLAIN			100)		; Draw quickly with no special effects

; the following constant will allow the picture to be drawn, but not be shown
(define DONTSHOW 		$1000)

; the following constant mirrors the picture vertically
(define VMIRROR			$2000)

; the following constant mirrors the picture horizontally
(define HMIRROR			$4000)

; oring the following constant to any of the styles
; will blacken the screen in the converse manner before showing
(define	BLACKOUT		$8000)


(define	NULL			0)

;Bits in the -info- property.
(define	CLONED		$0001)
(define	NODISPOSE	$0002)
(define	NODISPLAY	$0004)
(define	CLASS			$8000)

;For compatability -- bringing stuff in line with SmallTalk syntax.
;;;(define	myself		yourself)


;Bitmap for the 'signal' property of Props
; MSB xxxxxxxx/xxxPKxUU LSB
; x = don't touch
; P = priority
; K = kill actor
; U = update control

(define	stopUpdOn		$0001)
(define	startUpdOn		$0002)
(define	updOn				$0003)
(define	notUpd			$0004)
(define	hideActor		$0008)
(define	fixPriOn			$0010)
(define	viewAdded		$0020)	;view will be added to picture
(define	forceUpdOn		$0040)
(define	actorHidden		$0080)
(define	staticView		$0100)
(define	anExtra			$0200)	;can be added to pic if animation speed bad
(define	blocked			$0400)	;tried to move, but couldn't
(define	fixedLoop		$0800)	;loop is fixed
(define	fixedCel			$1000)	;cel fixed
(define	ignrHrz			$2000)	;can ignore horizon
(define	ignrAct			$4000)	;can ignore other actors
(define	delObj			$8000)

(define ADDTOPIC		$8021)
;(define	ADDTOPIC			(| delObj stopUpdOn viewAdded))


; Screen edges for edgeHit
(enum	1
	NORTH
	EAST
	SOUTH
	WEST
)

; values for above
(define	northEdge	40)
(define	southEdge	189)
(define	eastEdge		319)
(define	westEdge		0)

; screen dimensions
(define	SCRNWIDE	320)
(define	SCRNHIGH	200)


;Values returned by (GameIsRestarting)
(enum 1
	RESTARTING
	RESTORING
)

;Marker for end of path array.
(define	PATHEND	$8000)
;Marker for end of polygon array.
(define	ENDPOLY	$8000)

; system font should not be screwed with
(define	SYSFONT	0)
(define	USERFONT	1)

; this is standard inter item / edge spacing
(define	MARGIN	4)

;Used to specify the origin of an Actor in certain methods.
(define	origin	1)

;File opening parameters
(define	fAppend	0)				;appends to end of file
(define	fRead		1)				;positions at start of file
(define	fTrunc	2)				;truncates file on open

;file seek parameters (limited to offset of +/- 32767)
(enum
	fileSeekBeg		; seek from beginning of file (positive offset)
	fileSeekCur		; seek from current position of file (positively/negatively)
	fileSeekEnd		; seek from end of file (negative offset)
)

; define for features shiftClick and contClick properties
; that say don't do proximity checks if clicked on.
(define NOCHECKMOUSE $8000)


;**************************************************
;**
;** Definitions for Sound
;**
;**************************************************


(define	SND_DONE			-1)	;sound finished playing

;**(define	SND_NOTREADY	0)		;
;**(define	SND_READY		1)		;
;**(define	SND_BLOCKED		2)		; Dont use these...Their bogus
;**(define	SND_ACTIVE		3)		;
;**(define	SND_DELETE		4)		;


;; defines for MIDI commands

(define	mNOTEOFF			$80)
(define	mNOTEON			$90)
(define	mPOLYAFTER		$a0)
(define	mCONTROLLER		$b0)
(define	mPROGRAMCHANGE	$c0)
(define	mCHNLAFTER		$d0)
(define	mPITCHBEND		$e0)


;; defines for MIDI controller #'s

(define	mMODULATION		1)
(define	mVOLUME			7)
(define	mPAN				10)
(define	mMUTE				78)


;; defines for Sound flags property

(define mNOPAUSE	$0001)
(define mFIXEDPRI	$0002)


;**************************************************
;***
;***				COLORS
;***
;**************************************************

; ******* CONTROL colors
;         --------------						;	color#
;         											;	------
(define	cBLACK				$0001	)			;	0
(define	cBLUE					$0002	)			;	1
(define	cGREEN				$0004	)			;	2
(define	cCYAN					$0008	)			;	3
(define	cRED					$0010	)			;	4
(define	cMAGENTA				$0020	)			;	5
(define	cBROWN				$0040	)			;	6
(define	cLGREY				$0080	)			;	7
(define	cGREY					$0100	)			;	8
(define	cLBLUE				$0200	)			;	9
(define	cLGREEN				$0400	)			;	10
(define	cLCYAN				$0800	)			;	11
(define	cLRED					$1000	)			;	12
(define	cLMAGENTA			$2000	)			;	13
(define	cYELLOW				$4000	)			;	14
(define	cWHITE				$8000	)			;	15

; ******** VISUAL colors
(enum						;	color#
							;	------
	vBLACK				;	0
	vBLUE					;	1
	vGREEN				;	2
	vCYAN					;	3
	vRED					;	4
	vMAGENTA				;	5
	vBROWN				;	6
	vLGREY				;	7
	vGREY					;	8
	vLBLUE				;	9
	vLGREEN				;	10
	vLCYAN				;	11
	vLRED					;	12
	vLMAGENTA			;	13
	vYELLOW				;	14
	vWHITE				;	15
)

; ******** PRIORITY colors
(enum						;	color#
							;	------
	pBLACK				;	0
	pBLUE					;	1
	pGREEN				;	2
	pCYAN					;	3
	pRED					;	4
	pMAGENTA				;	5
	pBROWN				;	6
	pLGREY				;	7
	pGREY					;	8
	pLBLUE				;	9
	pLGREEN				;	10
	pLCYAN				;	11
	pLRED					;	12
	pLMAGENTA			;	13
	pYELLOW				;	14
	pWHITE				;	15
)

;**************************************************
;***
;***		SYSTEM GLOBALS (0 TO 99)
;***
;**************************************************

;;;(global
;;;	ego					0							;pointer to ego
;;;	theGame				1							;ID of the Game instance
;;;	curRoom				2							;ID of current room
;;;	speed					3 = 6						;number of ticks between animations
;;;	quit					4							;when TRUE, quit game
;;;	cast					5							;collection of actors
;;;	regions				6							;set of current regions
;;;	timers				7							;list of timers in the game
;;;	sounds				8							;set of sounds being played
;;;	inventory			9							;set of inventory items in game
;;;	addToPics			10							;list of views added to the picture
;;;	curRoomNum			11							;current room number
;;;	prevRoomNum			12							;previous room number
;;;	newRoomNum			13							;number of room to change to
;;;	debugOn				14							;generic debug flag -- set from debug menu
;;;	score					15							;the player's current score
;;;	possibleScore		16							;highest possible score
;;;	showStyle			17 = IRISOUT			;style of picture showing
;;;	aniInterval			18							;# of ticks it took to do the last animation cycle
;;;	theCursor			19							;the number of the current cursor
;;;	normalCursor		20	= ARROW_CURSOR		;number of normal cursor form
;;;	waitCursor			21	= HAND_CURSOR		;cursor number of "wait" cursor
;;;	userFont				22	= USERFONT			;font to use for Print
;;;	smallFont			23	= USERFONT			;small font for save/restore, etc.
;;;	lastEvent			24							;the last event (used by save/restore game)
;;;	modelessDialog		25							;the modeless Dialog known to User and Intrface
;;;	bigFont				26 = USERFONT		 	;large font
;;;	version				27	= 0					;pointer to 'incver' version string
;;;										;***WARNING***  Must be set in room 0
;;;										; (usually to {x.yyy    } or {x.yyy.zzz})
;;;	locales				28							;set of current locales
;;;	curSaveDir			29							;address of current save drive/directory string
;;;	aniThreshold		30 = 10
;;;	perspective			31							;player's viewing angle:
;;;														;	 degrees away from vertical along y axis
;;;	features				32							;locations that may respond to events
;;;	sortedFeatures		33							;above+cast sorted by "visibility" to ego
;;;	useSortedFeatures	34	= FALSE				;enable cast & feature sorting?
;;;	egoBlindSpot		35 = 0	;used by sortCopy to exclude 
;;;										;actors behind ego within angle 
;;;										;from straight behind. 
;;;										;Default zero is no blind spot
;;;	overlays				36 = -1
;;;	doMotionCue			37			;a motion cue has occurred - process it
;;;	systemWindow		38			;ID of standard system window
;;;	demoDialogTime		39	= 3	;how long Prints stay up in demo mode
;;;	currentPalette		40			;
;;;	modelessPort		41
;;;	sysLogPath			42			;used for system standard logfile path	
;;;	endSysLogPath		62			;uses 40 globals
;;;	gameControls		63
;;;	ftrInitializer		64
;;;	doVerbCode			65
;;;	firstSaidHandler	66			;will be the first to handle said events
;;;	useObstacles		67	= TRUE;will Ego use PolyPath or not?
;;;	theMenuBar			68			;points to TheMenuBar or Null	
;;;	theIconBar			69			;points to TheIconBar or Null	
;;;	mouseX				70
;;;	mouseY				71
;;;	keyDownHandler		72			;our EventHandlers, get called by the game
;;;	mouseDownHandler	73
;;;	directionHandler	74
;;;	gameCursor			75
;;;	lastVolume			76
;;;	pMouse				77	= NULL;pointer to a Pseudo-Mouse, or NULL
;;;	theDoits				78 = NULL;list of objects to get doits done every cycle
;;;	eatMice				79 = 60	;how many ticks minimum that a window stays up	
;;;	user					80 = NULL;pointer to specific applications User
;;;	syncBias				81	;; globals used by sync.sc (will be removed shortly)
;;;	theSync				82
;;;	cDAudio				83
;;;	fastCast				84
;;;	inputFont			85 = SYSFONT	;font used for user type-in
;;;
;;;	tickOffset			86
;;;
;;;	howFast				87 ;; measurment of how fast a machine is
;;;	gameTime				88
;;;	lastSysGlobal		99
;;;)

(include kernel.sh)		;kernel external declarations

;;(include extra.sh)		;externs and defines needed to use extras
(enum
	ExtraForward				;0=default
	ExtraEndLoop				;1
	ExtraEndAndBeginLoop		;2
)

(enum -2
	ExtraLastCel				;-1
	ExtraRandomCel				;-2
)

;;;(extern
;;;	;;Intrface procedures
;;;	;;-------------------
;;;	Print			INTRFACE	0
;;;	ShowView		INTRFACE	1
;;;	GetInput		INTRFACE	2
;;;	GetNumber	INTRFACE	3
;;;	Printf		INTRFACE 4
;;;	MousedOn		INTRFACE 5
;;;	sign			SYSTEM	0
;;;	umod			SYSTEM	1
;;;	Min			SYSTEM	2
;;;	Max			SYSTEM	3
;;;	InRect		SYSTEM	4
;;;	OneOf			SYSTEM	5
;;;	WordAt  		SYSTEM	6
;;;	Eval			SYSTEM	7
;;;	LoadMany 	LOADMANY 0
;;;	SortedAdd	SORTCOPY	0
;;;	IsOffScreen	SIGHT		0
;;;	CantBeSeen	SIGHT 	1
;;;	AngleDiff	SIGHT 	2
;;; 	PrintD		PRINTD	0
;;;	FlipPoly		FLIPPOLY	0
;;;	FlipFeature	FLIPPOLY	1
;;;)

(define	ftrDefault	$6789)

(define FACINGME		$0001)	
(define	NEARCHECK	$0002)
(define	FARCHECK		$0004)
(define	ISNOTHIDDEN	$0008)

(define maxFileName 13) ; MS-DOS maximum

;enums for polygon types for the polygon based avoider

(enum
	PTotalAccess
	PNearestAccess
	PBarredAccess
	PContainedAccess
)

;Icon bar definitions

; defines for Icon signal property
(define	RELVERIFY				$0001);release	verify, track mouse
(define IMMEDIATE				$0002);do the job now, don't condition events
(define	DISABLED					$0004);icon or bar not enabled
(define TRANSLATOR				$0010);condition event message (used internally)
(define	IB_ACTIVE				$0020);iconbar is up
(define	HIDEBAR					$0040);close bar after action
(define	FIXED_POSN				$0080);Item has fixed position in inv window
(define	VICON						$0100);up and down arrows will advance and retreat
(define	RELSEND					$0200);for sliders-don't send message until mouseUp
(define OPENIFONME				$0400);close if mouse not on bar
(define NOCLICKHELP				$0800)
(define	FORCE						$8000)

(enum
	verbNone
	verbWalk	
	verbLook	
	verbDo	
	verbUse	
	verbTalk	
	verbHelp
	gameVerbs
)