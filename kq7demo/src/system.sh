;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SYSTEM.SH
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Unknown
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	This is the header file for the SCI system


(include kernel.sh)		;kernel external declarations


;ษออออออออออออออออป
;บ                บ
;บ Module Defines บ
;บ                บ
;ศออออออออออออออออผ

(define	SYSTEM		64999)
(define	ACTOR			64998)
(define	MENU			64997)
(define	USER			64996)
(define	INVENT		64995)
(define	GAME			64994)
(define	FILE			64993)
(define	MOTION		64992)
(define	JUMP			64991)
(define	SAVE			64990)
(define	MUSIC			64989)
(define	EGO 			64988)		; was EXTRA
(define	GAUGE			64987)
(define	ORBIT			64986)
(define	FLAGS			64985)		; was AVOIDER
(define	REGPATH		64984)		; was SORTCOPY
(define	PATH			64983)
(define	SIGHT			64982)
(define	HOTRECT		64981)
(define	TUTORIAL		64980)		; was TEXTRA
(define	MAGNIFIER	64979)		; was MOUSER
(define	GCONTROL		64978)
(define	GROOPER		64977)
(define	CAT			64976)
(define	SCALETO		64975)		; was DEMO
(define	NAMEFIND		64974)
(define	TIMER			64973)
(define	CHASE			64972)
(define	FOLLOW		64971)
(define	WANDER		64970)
(define	REVERSE		64969)
(define	SMOOPER		64968)
(define	DCICON		64967)
(define	SORT			64966)
(define	COUNT			64965)
(define	DPATH			64964)		; will be merged into PATH
(define	RELDPATH		64963)		; will be merged into PATH
(define	CURSOR		64962)		; was QSCRIPT
(define	STOPWALK		64961)
(define	WALKTALK		64960)		; was TIMEDCUE
(define	QSOUND		64959)
(define	LOADMANY		64958)
(define	UNUSED_10	64957)		; was LASTLINK
(define	FORCOUNT		64956)
(define	TRACK			64955)
(define	DOOR			64954)		; was GOTOSAID
(define	APPROACH		64953)
(define	LOGGER		64952)
(define	MOVEFWD     64951)
(define 	FEATURE		64950)
(define 	BLOCK			64949)
(define 	WRITEFTR		64948)
(define 	DLGEDIT		64947)		; was DELAYEVT
(define 	POLYGON		64946)
(define 	POLYPATH		64945)
(define 	FILESEL		64944)
(define 	POLYEDIT		64943)
(define 	MOVECYC		64942)
(define 	RANDCYC		64941)
(define 	PANELPLANE	64940)		; was PRINTD
(define 	OSC	 		64939)
(define 	RANGEOSC		64938)		; was PCYCLE
(define 	ICONBAR		64937)
(define 	BORDWIND		64936)
(define 	SCALER		64935)
(define 	SLIDEICON	64934)
(define 	PMOUSE		64933)
(define 	PFOLLOW		64932)		; was LANGUAGE
(define 	CDACTOR		64931)
(define 	PCHASE		64930)
(define 	SYNC			64929)
(define 	TALKER		64928)
(define 	PAVOID		64927)
(define 	FLIPPOLY		64926)
(define	CONV			64925)
(define	MESSAGER		64924)
(define	INSET			64923)
(define	DIALOG		64922)
(define	PRINT			64921)	;Front-end for dialogs
(define	ARRAY			64920)
(define	RECT			64919)
(define	STRING		64918)
(define	PLANE			64917)
(define	DITEM			64916)
(define	DTEXT			64915)
(define	DEDIT			64914)
(define	DSELECTOR	64913)
(define	DICON			64912)
(define	DBUTTON		64911)
(define	DSLIDER		64910)
(define	STYLER		64909)
(define	SPEEDTEST_ROOM	64908)
(define	ANM			64907)
(define	ANMSCRWN		64906)
(define	ANMCAST		64905)
(define	ANMCMD		64904)
(define	ANMSCORE		64903)


(define	INTRFACE		64255)



;ษออออออออออออออออป
;บ                บ
;บ Extern Defines บ
;บ                บ
;ศออออออออออออออออผ

;;;(extern
;;;	Prints			PRINT			0
;;;	Printf			PRINT			1
;;;	GetInput			PRINT			2
;;;	FindFormatLen	PRINT			3
;;;	StillDown		INTRFACE		0
;;;	GetNumber		INTRFACE		1
;;;	MousedOn			INTRFACE		2
;;;	sign				SYSTEM		0
;;;	umod				SYSTEM		1
;;;	Min				SYSTEM		2
;;;	Max				SYSTEM		3
;;;	InRect			SYSTEM		4
;;;	OneOf				SYSTEM		5
;;;	Eval				SYSTEM		6
;;;	LoadMany 		LOADMANY 	0
;;;	IsOffScreen		SIGHT			0
;;;	CantBeSeen		SIGHT 		1
;;;	AngleDiff		SIGHT 		2
;;;	FlipPoly			FLIPPOLY		0
;;;	FlipFeature		FLIPPOLY		1
;;;	SpeedTest		SPEEDTEST_ROOM	0
;;;	ShakePlane		STYLER		0
;;;)



;ษออออออออออออออออออป
;บ                  บ
;บ Resource Defines บ
;บ                  บ
;ศออออออออออออออออออผ

;Resource types
(define	RES_VIEW			$80)
(define	RES_PIC			$81)
(define	RES_SCRIPT		$82)
(define	RES_ANIM			$83)
(define	RES_SOUND		$84)
(define	RES_MEMORY		$85)
(define	RES_VOCAB		$86)
(define	RES_FONT			$87)
(define	RES_CURSOR		$88)
(define	RES_PATCH		$89)
(define	RES_BITMAP		$8A)
(define	RES_PALETTE		$8B)
(define	RES_WAVE			$8C)
(define	RES_AUDIO		$8D)
(define	RES_SYNC			$8E)
(define	RES_MESSAGE		$8F)
(define	RES_MAP			$90)
(define	RES_HEAP			$91)
(define  RES_CHUNK		$92)
(define	RES_AUDIO36		$93)
(define	RES_SYNC36		$94)
(define  RES_TRANS		$95)
(define  RES_ROBOT		$96)
(define  RES_VMD			$97)

;Virtual bitmap ID's
(define	VMAP		1)
(define	PMAP		2)
(define	CMAP		4)

;New defines for show styles
(enum
	SHOW_PLAIN
	SHOW_HSHUTTER_OUT
	SHOW_HSHUTTER_IN
	SHOW_VSHUTTER_OUT
	SHOW_VSHUTTER_IN
	SHOW_WIPE_LEFT
	SHOW_WIPE_RIGHT
	SHOW_WIPE_UP
	SHOW_WIPE_DOWN
	SHOW_IRIS_OUT
	SHOW_IRIS_IN
	SHOW_DISSOLVE
	SHOW_PIXEL_DISSOLVE
	SHOW_FADE_OUT
	SHOW_FADE_IN
	SHOW_SCROLL_LEFT
	SHOW_SCROLL_RIGHT
	SHOW_SCROLL_UP
	SHOW_SCROLL_DOWN
)

(define	SHOW_BLACKOUT	$0100)
(define	SHOW_WHITEOUT	$0200)
(define	SHOW_MIRRORED	$0400)
;;;(define	SHOW_SCROLLS	SHOW_SCROLL_LEFT SHOW_SCROLL_RIGHT SHOW_SCROLL_UP SHOW_SCROLL_DOWN)
;;;(define	SHOW_WIPES		SHOW_WIPE_LEFT SHOW_WIPE_RIGHT SHOW_WIPE_DOWN SHOW_WIPE_UP)
;;;(define	SHOW_DISSOLVES	SHOW_PIXEL_DISSOLVE SHOW_DISSOLVE)
;;;(define	SHOW_SHUTTERS	SHOW_HSHUTTER_OUT SHOW_HSHUTTER_IN SHOW_VSHUTTER_OUT SHOW_VSHUTTER_IN)

;ShakePlane procedure uses:
(enum 1
	SHAKE_HORIZONTAL
	SHAKE_VERTICAL
)



;ษออออออออออออออออป
;บ                บ
;บ Cursor Defines บ
;บ                บ
;ศออออออออออออออออผ

(define	ARROW_CURSOR	999)
(define	SNAIL_CURSOR	998)
(define	HAND_CURSOR 	997)
(define	INVIS_CURSOR	996)



;ษออออออออออออออออออออออออออป
;บ                          บ
;บ Numeric Constant Defines บ
;บ                          บ
;ศออออออออออออออออออออออออออผ

(define	INFINITY 	$7fff)	
(define	NULL			0)
(define  UNSET			-1)

;Array types
(enum
	WORDARRAY
	IDARRAY
	BYTEARRAY
	STRARRAY
)

;Bits in the -info- property.
(define	CLONED		$0001)
(define	NODISPOSE	$0002)
(define	NODISPLAY	$0004)
(define	GRAPH_UPD 	$0008)
(define	IN_SILIST	$0010)
(define	CLASS			$8000)

;For compatability -- bringing stuff in line with SmallTalk syntax.
;(define	myself		yourself)

;Screen edges for edgeHit
(enum	1
	NORTH
	EAST
	SOUTH
	WEST
)

;Values for above
;;;(define	northEdge	(curRoom edgeN?))	;\
;;;(define	southEdge	(curRoom edgeS?))	; Edges have been redefined
;;;(define	eastEdge		(curRoom edgeE?))	; as properties of Room
;;;(define	westEdge		(curRoom edgeW?))	;/

;Values returned by (SaveGame SGGameIsRestarting)
(enum 1
	RESTARTING
	RESTORING
)

(define	PATHEND		$8000)	;Indicates end of path array
(define	ENDPOLY		$8000)	;Indicates end of polygon array
(define	SAMEVIEW 	-1)		;Indicates stop cels are in same view as walks
(define	END_CONV		0)			;Indicates end of Conversation array
(define	MARGIN		4)			;Standard intrface item/edge spacing

;System font should not be screwed with
(define	SYSFONT		0)
(define	USERFONT		1)

;Used to specify the origin of an Actor in certain methods.
(define	origin		1)


;File opening parameters
(define	fAppend		0)			;appends to end of file
(define	fRead			1)			;positions at start of file
(define	fTrunc		2)			;truncates file on open

;File seek parameters (limited to offset of +/- 32767)
(enum
	fileSeekBeg		; seek from beginning of file (positive offset)
	fileSeekCur		; seek from current position of file (positively/negatively)
	fileSeekEnd		; seek from end of file (negative offset)
)

; Feature defines
(define	ftrDefault	$6789)

(define	SKIPCHECK	$1000)


(define maxFileName 	13) 		;MS-DOS maximum


;Enums for polygon types for the polygon based avoider
(enum
	PTotalAccess
	PNearestAccess
	PBarredAccess
	PContainedAccess
)


;(define	NODIRECTION $7fff);Arbitrary value for an undefined heading

;Door defines
(enum
	doorClosed
	doorOpening
	doorOpen
	doorClosing
)

(enum
	doorWalkEgo		; ->: 	ego will walk in through the door
						; <-:		ego will walk out the door to new room
	doorPlaceEgo	; ->: 	ego will appear in front of the door
						; <-:		ego will walk out the door & door will close
	doorLeaveEgo	; both: 	the door does not affect ego
)


; Language defines
(define	ENGLISH		1)
(define	FRENCH		33)
(define	SPANISH		34)
(define	ITALIAN		39)
(define	GERMAN		49)
(define	JAPANESE		81)
(define	PORTUGUESE	351)



;ษออออออออออออออออป
;บ                บ
;บ Dialog Defines บ
;บ                บ
;ศออออออออออออออออผ

;Dialog item types
(define	dButton			1)
(define	dText				2)
(define	dEdit				3)
(define	dIcon				4)
(define	dMenu				5)				; the title portion
(define	dSelector		6)
(define	dSlider			7)

;Dialog item states
(define	dActive			$0001)
(define	dExit				$0002)
(define	dBold				$0004)
(define	dSelected		$0008)
(define 	dIconTop			$0010)

;Window manager defines
(define 	stdWindow		$0000)
(define	wNoSave			$0001)
(define	wNoBorder		$0002)
(define	wTitled			$0004)
(define	wCustom			$0080)

;Text justification for DItem mode
(enum
	teJustLeft	
	teJustCenter
	teJustRight 
)

;Message type definitions
(define	TEXT_MSG			$0001)
(define	CD_MSG			$0002)

;Defines for messages
(define	NEXT				-1)
(define	ALL				0)

;Return values
(define	RET_NO_EVENT	0)
(define	RET_ESC			-1)
(define	RET_NO_ACTIVE	-2)

;Modal state values
(define	DLG_MODAL		0)
(define	DLG_SEMI_MODAL	1)
(define	DLG_MODELESS	2)



;ษอออออออออออออออป
;บ               บ
;บ Event Defines บ
;บ               บ
;ศอออออออออออออออผ

;Event types
(define	nullEvt		$0000)

(define	mouseDown	$0001)
(define	mouseUp		$0002)
(define	mouseEvt	$0003)	;(| mouseDown mouseUp))

(define	keyDown		$0004)
(define	keyUp			$0008)
(define	keyEvt		$000c)	;(| keyDown keyUp))

(define	direction	$0010)

(define	joyDown		$0020)
(define	joyUp			$0040)
(define	joyEvt		$0060)	;(| joyDown joyUp))

(define	speechEvent	$0100)
(define	extMouse		$0200)	;extended mouse (yaw, pitch, roll, z)

(define	walkEvent	$1000)
(define	helpEvent	$2000)
(define	userEvent	$4000)

(define	allEvents	$7fff)

(define	leaveIt		$8000)

(define	speechNoun	1)
(define	speechVerb	0)

;Event modifiers (in who property)
(define	shiftRight	1)		; right shift key pressed
(define	shiftLeft	2)		; left shift key pressed
(define	shiftDown	3)		; either shift key pressed
(define	ctrlDown		4)		; control key pressed
(define	altDown		8)		; alt key down pressed

;Pseudo selectors for SetMenu, Display, and Print (eventually)
;	must be duplicated by #define's in KERNEL.H
(define p_at			100)
(define p_mode		101)
(define p_color		102)
(define p_back		103)
(define p_style		104)
(define p_font		105)
(define p_width		106)
(define p_save		107)
(define p_restore	108)
(define p_said		109)
(define p_text		110)
(define p_key			111)
(define p_state		112)
(define p_value		113)
(define p_dispose	114)
(define p_time		115)
(define p_title		116)
(define p_draw		117)
(define p_edit		118)
(define p_button		119)
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

(enum
	facingEast
	facingWest
	facingSouth
	facingNorth
 	facingSE
	facingSW
	facingNE
	facingNW
)



;ษอออออออออออออป
;บ             บ
;บ Key Defines บ
;บ             บ
;ศอออออออออออออผ

(define	ESC			27)
(define	SPACEBAR		32)
(define	ENTER			`^m)
(define	TAB			`^i)
(define	SHIFTTAB		$0f00)
(define	BACKSPACE	`^h)


;Numeric key code in scan code order with missing codes added
(define	HOMEKEY		$4700)
(define	UPARROW		$4800)
(define	PAGEUP		$4900)

(define	LEFTARROW	$4b00)
(define	CENTERKEY	$4c00)
(define	RIGHTARROW	$4d00)

(define	ENDKEY		$4f00)
(define	DOWNARROW	$5000)
(define	PAGEDOWN		$5100)

(define	INSERT		$5200)
(define	DELETE		$5300)



;ษออออออออออออออออออออป
;บ                    บ
;บ Signal Bit Defines บ
;บ                    บ
;ศออออออออออออออออออออผ

(define	canUpdate		$0001)	;detailLevel is high enough
(define	viewHidden		$0008)
(define	setBaseRect		$0020)	;view not affected by basesetters
(define	blocked			$0400)	;tried to move, but couldn't
(define	fixedLoop		$0800)	;loop is fixed
(define	skipCheck		$1000)	;onMe will check skip
(define	ignrHrz			$2000)	;can ignore horizon
(define	ignrAct			$4000)	;can ignore other actors

;scaleSignal bits
(define	scalable			$0001)	;scale on user-defined criteria
(define	autoScale		$0002)	;scaling as a function of y
(define	noStepScale		$0004)	;don't try to change stepSize when scaling
(define	scaleBase		128)		;represents 100%
(define	scaleShift		7)
(define	halfScale		64)

;setScale defines
(define	MATCH				-1)
(define	TO					1)
(define	FROM				2)



;ษอออออออออออออออออออป
;บ                   บ
;บ State Bit Defines บ
;บ                   บ
;ศอออออออออออออออออออผ

(define	approachObj		$0002)	;ego will try to approach objects


;ษอออออออออออออออป
;บ               บ
;บ Sound Defines บ
;บ               บ
;ศอออออออออออออออผ

(define	SND_DONE			-1)	;sound finished playing
(define FOREVER -1)
(define ONCE 0)

;MIDI commands
(define	mNOTEOFF			$80)
(define	mNOTEON			$90)
(define	mPOLYAFTER		$a0)
(define	mCONTROLLER		$b0)
(define	mPROGRAMCHANGE	$c0)
(define	mCHNLAFTER		$d0)
(define	mPITCHBEND		$e0)

;MIDI controller #'s
(define	mMODULATION		1)
(define	mVOLUME			7)
(define	mPAN				10)
(define	mMUTE				78)

;Flags bits
(define mNOPAUSE			$0001)
(define mFIXEDPRI			$0002)
(define mLOAD_AUDIO		$0004)



;ษอออออออออออออออป
;บ               บ
;บ Color Defines บ
;บ               บ
;ศอออออออออออออออผ

;Screen colors
(define	BLACK			 	0)

;CONTROL colors
(define	cBLACK		 	$0001)	;	0
(define	cBLUE			 	$0002)	;	1
(define	cGREEN		 	$0004)	;	2
(define	cCYAN			 	$0008)	;	3
(define	cRED			 	$0010)	;	4
(define	cMAGENTA		 	$0020)	;	5
(define	cBROWN		 	$0040)	;	6
(define	cLGREY		 	$0080)	;	7
(define	cGREY			 	$0100)	;	8
(define	cLBLUE		 	$0200)	;	9
(define	cLGREEN		 	$0400)	;	10
(define	cLCYAN		 	$0800)	;	11
(define	cLRED			 	$1000)	;	12
(define	cLMAGENTA	 	$2000)	;	13
(define	cYELLOW		 	$4000)	;	14
(define	cWHITE		 	$8000)	;	15

;VISUAL colors
(enum
	vBLACK		;	0
	vBLUE			;	1
	vGREEN		;	2
	vCYAN			;	3
	vRED			;	4
	vMAGENTA		;	5
	vBROWN		;	6
	vLGREY		;	7
	vGREY			;	8
	vLBLUE		;	9
	vLGREEN		;	10
	vLCYAN		;	11
	vLRED			;	12
	vLMAGENTA	;	13
	vYELLOW		;	14
	vWHITE		;	15
)

;PRIORITY colors
(enum
	pBLACK		;	0
	pBLUE			;	1
	pGREEN		;	2
	pCYAN			;	3
	pRED			;	4
	pMAGENTA		;	5
	pBROWN		;	6
	pLGREY		;	7
	pGREY			;	8
	pLBLUE		;	9
	pLGREEN		;	10
	pLCYAN		;	11
	pLRED			;	12
	pLMAGENTA	;	13
	pYELLOW		;	14
	pWHITE		;	15
)



;ษอออออออออออออออออป
;บ                 บ
;บ Iconbar Defines บ
;บ                 บ
;ศอออออออออออออออออผ

(define	RELVERIFY	  	$0001)	;release	verify, track mouse
(define 	IMMEDIATE	  	$0002)	;do the job now, don't condition events
(define	DISABLED		  	$0004)	;icon or bar not enabled
(define 	TRANSLATOR	  	$0010)	;condition event message (used internally)
(define	IB_ACTIVE	  	$0020)	;iconbar is up
(define	HIDEBAR		  	$0040)	;close bar after action
(define	FIXED_POSN	  	$0080)	;Item has fixed position in inv window
(define	VICON			  	$0100)	;up and down arrows will advance and retreat
(define	RELSEND		  	$0200)	;for sliders-don't send message until mouseUp
(define 	OPENIFONME	  	$0400)	;close if mouse not on bar
(define 	NOCLICKHELP	  	$0800)
(define	UPDATE_CURSOR	$1000)	;transfer view, loop, & cel to cursor obj
(define	FORCE			  	$8000)



;ษออออออออออออออออออออออออออป
;บ                          บ
;บ System Globals (0 to 99) บ
;บ                          บ
;ศออออออออออออออออออออออออออผ

;;;(global
;;;	ego					0					  	;pointer to ego
;;;	theGame				1					  	;ID of the Game instance
;;;	curRoom				2					  	;ID of current room
;;;	thePlane				3						;default plane
;;;	quit					4					  	;when TRUE, quit game
;;;	cast					5					  	;collection of actors
;;;	regions				6					  	;set of current regions
;;;	timers				7					  	;list of timers in the game
;;;	sounds				8					  	;set of sounds being played
;;;	inventory			9					  	;set of inventory items in game
;;;	planes				10					  	;list of all active planes in the game
;;;	curRoomNum			11					  	;current room number
;;;	prevRoomNum			12					  	;previous room number
;;;	newRoomNum			13					  	;number of room to change to
;;;	debugOn				14					  	;generic debug flag -- set from debug menu
;;;	score					15					  	;the player's current score
;;;	possibleScore		16					  	;highest possible score
;;;	textCode				17						;code that handles interactive text
;;;	cuees					18					  	;list of who-to-cues for next cycle
;;;	theCursor			19					  	;the number of the current cursor
;;;	normalCursor		20						;number of normal cursor form
;;;	waitCursor			21						;cursor number of "wait" cursor
;;;	userFont				22	= USERFONT	  	;font to use for Print
;;;	smallFont			23	= 4 			  	;small font for save/restore, etc.
;;;	lastEvent			24					  	;the last event (used by save/restore game)
;;;	eventMask			25	= allEvents	  	;event mask passed to GetEvent in (uEvt new:)
;;;	bigFont				26 = USERFONT	  	;large font
;;;	version				27	= 0			  	;pointer to 'incver' version string
;;;													;	WARNING!  Must be set in room 0
;;;													;	(usually to {x.yyy    } or {x.yyy.zzz})
;;;	autoRobot			28
;;;	curSaveDir			29						;address of current save drive/directory string
;;;	numCD					30	= 0				;number of current CD, 0 for file based
;;;	perspective			31						;player's viewing angle: degrees away
;;;													;	from vertical along y axis
;;;	features				32						;locations that may respond to events
;;;	panels				33	= NULL			;list of game panels
;;;	useSortedFeatures	34	= FALSE			;enable cast & feature sorting?
;;;	unused_6				35
;;;													;actors behind ego within angle 
;;;													;from straight behind. 
;;;													;Default zero is no blind spot
;;;	overlays				36 = -1
;;;	doMotionCue			37						;a motion cue has occurred - process it
;;;	systemPlane			38						;ID of standard system plane
;;;	saveFileSelText	39						;text of fileSelector item that's selected.
;;;	unused_8				40
;;;	unused_2				41
;;;	sysLogPath			42						;-used for system standard logfile path	
;;;	endSysLogPath		62						;/		(uses 40 globals)
;;;	gameControls		63						;pointer to instance of game controls
;;;	ftrInitializer		64						;pointer to code that gets called from
;;;													;	a feature's init
;;;	doVerbCode			65						;pointer to code that gets invoked if
;;;													;	no feature claims a user event
;;;	approachCode		66						;pointer to code that translates verbs
;;;													;	into bits
;;;	useObstacles		67	= TRUE			;will Ego use PolyPath or not?
;;;	unused_9				68
;;;	theIconBar			69						;points to TheIconBar or Null	
;;;	mouseX				70						;-last known mouse position
;;;	mouseY				71						;/
;;;	keyDownHandler		72						;-our EventHandlers, get called by game
;;;	mouseDownHandler	73						;/
;;;	directionHandler	74						;/
;;;	speechHandler		75						;a special handler for speech events
;;;	lastVolume			76
;;;	pMouse				77	= NULL			;pointer to a Pseudo-Mouse, or NULL
;;;	theDoits				78 = NULL			;list of objects to get doits each cycle
;;;	eatMice				79 = 60				;how many ticks before we can mouse
;;;	user					80 = NULL			;pointer to specific applications User
;;;	syncBias				81						;-globals used by sync.sc
;;;	theSync				82						;/		(will be removed shortly)
;;;	extMouseHandler	83						;extended mouse handler
;;;	talkers				84						;list of talkers on screen
;;;	inputFont			85 = SYSFONT		;font used for user type-in
;;;	tickOffset			86						;used to adjust gameTime after restore
;;;	howFast				87 					;measurment of how fast a machine is
;;;	gameTime				88						;ticks since game start
;;;	narrator				89						;pointer to narrator (normally Narrator)
;;;	msgType				90 = TEXT_MSG		;type of messages used
;;;	messager				91						;pointer to messager (normally Messager)
;;;	prints				92						;list of Print's on screen
;;;	walkHandler			93						;list of objects to get walkEvents
;;;	textSpeed			94 = 2				;time text remains on screen
;;;	altPolyList			95						;list of alternate obstacles
;;;	screenWidth			96 = 320				;
;;;	screenHeight		97 = 200				; Coordinate System Parameters
;;;	lastScreenX			98 = 319				;
;;;	lastScreenY			99	= 199				;
;;;	;globals > 99 are for game use
;;;)