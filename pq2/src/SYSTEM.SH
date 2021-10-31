;;; Sierra Script 1.0 - (do not remove this comment)
;SYSTEM - Header file




;System file name / script numbers
;
(define	SYSTEM			999)
(define	ACTOR			998)
(define	MENU			997)
(define	USER			996)
(define	INVENT			995)
(define	GAME			994)
(define	FILE			993)
(define	MOTION			992)
(define	JUMP			991)
(define	SAVE			990)
(define	MUSIC			989)
(define	EXTRA			988)
(define	GAUGE			987)
(define	ORBIT			986)
(define	AVOIDER			985)
(define	SORTCOPY		984)
(define	PATH			983)
(define	SIGHT			982)
(define	WINDOW			981)
(define	TEXTRA			980)
(define	MOUSER			979)
(define	RFEATURE		978)
(define	GROOPER			977)
(define	CAT				976)
(define	DEMO			975)
(define	NAMEFIND		974)
(define	TIMER			973)
(define	CHASE			972)
(define	FOLLOW			971)
(define	WANDER			970)
(define	REVERSE			969)
(define	SMOOPER			968)
(define	DCICON			967)
(define	SORT			966)
(define	COUNT			965)
(define	DPATH			964)
(define	RELDPATH		963)
(define	QSCRIPT			962)
(define	STOPWALK		961)
(define	TIMEDCUE		960)
(define	QSOUND			959)
(define	LOADMANY		958)	;load/unload many modules
(define	LASTLINK		957)	;follow a specified link to the bitter end
(define	FORCOUNT		956)	;forward-loop a specified number of times
(define	TRACK			955)	;motion to track an actor at an x/y offset.
(define	GOTOSAID		954)	;turn to or approach "said" object.


(define	INTRFACE		255)



(define	INFINITY $7fff)	

(define	NODIRECTION $7fff)		;Arbitrary value for an undefined heading


;Some selector aliases
;NOTE: Macro defines not supported by SCICompanion
;(define	nowSeen	nsTop)
;(define	lastSeen	lsTop)
;(define	baseRect	brTop)

;Defines for the system view
(define	SYSVIEW			999)
;NOTE: Macro defines not supported by SCICompanion
;;;(define	STOPSIGN		SYSVIEW 0 0)
;;;(define	QUESTIONMARK	SYSVIEW 0 1)
;;;(define	EXCLAMATION		SYSVIEW 0 2)

; Standard cursor IDs
(define	ARROW_CURSOR	999)
(define	SNAIL_CURSOR	998)
(define	HAND_CURSOR		997)

;Text rendering
(define	TPLAIN		0)
(define	TDIMMED		1)


;Dialog item types
(define	dButton		1)
(define	dText		2)
(define	dEdit		3)
(define	dIcon		4)
(define	dMenu		5)				; the title portion
(define	dSelector	6)

;Dialog item states
(define	dActive		1)
(define	dExit		2)
(define	dBold		4)
(define	dSelected	8)

; Window manager defines
(define	stdWindow	0)
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
(define	keyUp		$0008)
(define	menuStart	$0010)
(define	menuHit		$0020)
(define	direction	$0040)
(define	saidEvent	$0080)
(define	joyDown		$0100)
(define	joyUp		$0200)

(define	allEvents	$7fff)

(define	leaveIt		$8000)

; event modifiers (in who property)
(define	shiftDown	3)		; either shift key pressed
(define	ctrlDown	4)		; control key pressed
(define	altDown		8)		; alt key down pressed

; pseudo selectors for SetMenu, Display, and Print (eventually)
; must be duplicated by #define's in KERNEL.H
;NOTE: these defines *should* all end with : but
; that is not supported by SCICompanion

(define p_at		100)
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
(define p_key		111)
(define p_state		112)
(define p_value		113)
(define p_dispose	114)
(define p_time		115)
(define p_title		116)
(define p_draw		117)
(define p_edit		118)
(define p_button	119)
(define p_icon		120)

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

; some keycode definitions
(define	ESC			27)
(define	SPACEBAR	32)
(define	ENTER       $000d)		;(define ENTER	`^m) in SSCI
(define	TAB         $0009)		;(define TAB	`^i) in SSCI
(define	SHIFTTAB	$0f00)
(define	BACKSPACE	$0008)		;(define	BACKSPACE	`^h)


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

(define	DELETE		$5300)



;Screen colors
(define	BLACK		0)

;Screen dimensions
(define	MINTOP		0)
(define	MINLEFT		0)
(define	MAXRIGHT	320)
(define	MAXBOTTOM	200)



;Resource types
(define	VIEW		$80)
(define	PICTURE		$81)
(define	SCRIPT		$82)
(define	TEXT		$83)
(define	SOUND		$84)
(define	MEMORY		$85)
(define	VOCAB		$86)
(define	FONT		$87)
(define	CURSOR		$88)


;Virtual bitmap ID's
(define	VMAP		1)
(define	PMAP		2)
(define	CMAP		4)


;Picture change style constants
(define	HWIPE		0)
(define	HSHUTTER	0) ;should be 9?
(define	VSHUTTER	1)
(define	WIPELEFT	2)
(define	WIPERIGHT	3)
(define	WIPEUP		4)
(define	WIPEDOWN	5)
(define	IRISIN		6)
(define	IRISOUT		7)
(define	DISSOLVE	8)
(define	PLAIN		100)		; Draw quickly with no special effects
; adding the following constant to any of the styles
; will blacken the screen in the converse manner before showing
(define	BLACKOUT	9)

(define	NULL		0)

;Bits in the -info- property.
(define	CLONED		$0001)
(define	NODISPOSE	$0002)
(define	NODISPLAY	$0004)
(define	CLASS		$8000)

;For compatability -- bringing stuff in line with SmallTalk syntax.
;NOTE: Macro define
;(define	myself		yourself)


;Bitmap for the 'signal' property of Props
; MSB xxxxxxxx/xxxPKxUU LSB
; x = don't touch
; P = priority
; K = kill actor
; U = update control

(define	stopUpdOn		$0001)
(define	startUpdOn		$0002)
(define	updOn			$0003)
(define	notUpd			$0004)
(define	hideActor		$0008)
(define	fixPriOn		$0010)
(define	viewAdded		$0020)	;view will be added to picture
(define	forceUpdOn		$0040)
(define	actorHidden		$0080)
(define	staticView		$0100)
(define	anExtra			$0200)	;can be added to pic if animation speed bad
(define	blocked			$0400)	;tried to move, but couldn't
(define	fixedLoop		$0800)	;loop is fixed
(define	fixedCel		$1000)	;cel fixed
(define	ignrHrz			$2000)	;can ignore horizon
(define	ignrAct			$4000)	;can ignore other actors
(define	delObj			$8000)

;(define	ADDTOPIC			(| delObj stopUpdOn viewAdded))
(define	ADDTOPIC		$8021)	;NOTE: Macro defines are not yet supported by SCICompanion.
								;Remove this define and uncomment the original ADDTOPIC define
								;when they are.


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
(define	eastEdge	319)
(define	westEdge	0)

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

; system font should not be screwed with
(define	SYSFONT	0)
(define	USERFONT	1)

; this is standard inter item / edge spacing
(define	MARGIN	4)

;Used to specify the origin of an Actor in certain methods.
(define	origin	1)

;File opening parameters
(define	fAppend	0)				;appends to end of file
(define	fRead	1)				;positions at start of file
(define	fTrunc	2)				;truncates file on open


;Definitions for sound.
(define	SND_DONE		-1)	;sound finished playing

(define	SND_NOTREADY	0)
(define	SND_READY		1)
(define	SND_BLOCKED		2)
(define	SND_ACTIVE		3)
(define	SND_DELETE		4)


;**************************************************
;***
;***				COLORS
;***
;**************************************************

; ******* CONTROL colors
;         --------------						;	color#
;         										;	------
(define	cBLACK				1		)			;	0			;$0001
(define	cBLUE				2		)			;	1			;$0002
(define	cGREEN				4		)			;	2			;$0004
(define	cCYAN				8		)			;	3			;$0008
(define	cRED				16		)			;	4			;$0010
(define	cMAGENTA			32		)			;	5			;$0020
(define	cBROWN				64		)			;	6			;$0040		
(define	cLGREY				128		)			;	7			;$0080
(define	cGREY				256		)			;	8			;$0100  
(define	cLBLUE				512		)			;	9			;$0200
(define	cLGREEN				1024	)			;	10			;$0400
(define	cLCYAN				2048	)			;	11			;$0800
(define	cLRED				4096	)			;	12			;$1000
(define	cLMAGENTA			8192	)			;	13			;$2000
(define	cYELLOW				16384	)			;	14			;$4000
(define	cWHITE				32768	)			;	15			;$8000

; ******** VISUAL colors
(enum						;	color#
							;	------
	vBLACK					;	0
	vBLUE					;	1
	vGREEN					;	2
	vCYAN					;	3
	vRED					;	4
	vMAGENTA				;	5
	vBROWN					;	6
	vLGREY					;	7
	vGREY					;	8
	vLBLUE					;	9
	vLGREEN					;	10
	vLCYAN					;	11
	vLRED					;	12
	vLMAGENTA				;	13
	vYELLOW					;	14
	vWHITE					;	15
)

; ******** PRIORITY colors
(enum						;	color#
							;	------
	pBLACK					;	0
	pBLUE					;	1
	pGREEN					;	2
	pCYAN					;	3
	pRED					;	4
	pMAGENTA				;	5
	pBROWN					;	6
	pLGREY					;	7
	pGREY					;	8
	pLBLUE					;	9
	pLGREEN					;	10
	pLCYAN					;	11
	pLRED					;	12
	pLMAGENTA				;	13
	pYELLOW					;	14
	pWHITE					;	15
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
;;;	speed				3 = 6						;number of ticks between animations
;;;	quit				4							;when TRUE, quit game
;;;	cast				5							;collection of actors
;;;	regions				6							;set of current regions
;;;	timers				7							;list of timers in the game
;;;	sounds				8							;set of sounds being played
;;;	inventory			9							;set of inventory items in game
;;;	addToPics			10							;list of views added to the picture
;;;	curRoomNum			11							;current room number
;;;	prevRoomNum			12							;previous room number
;;;	newRoomNum			13							;number of room to change to
;;;	debugOn				14							;generic debug flag -- set from debug menu
;;;	score				15							;the player's current score
;;;	possibleScore		16							;highest possible score
;;;	showStyle			17 = IRISOUT				;style of picture showing
;;;	aniInterval			18							;# of ticks it took to do the last animation cycle
;;;	theCursor			19							;the number of the current cursor
;;;	normalCursor		20	= ARROW_CURSOR			;number of normal cursor form
;;;	waitCursor			21	= HAND_CURSOR			;cursor number of "wait" cursor
;;;	userFont			22	= USERFONT				;font to use for Print
;;;	smallFont			23	= 4 					;small font for save/restore, etc.
;;;	lastEvent			24							;the last event (used by save/restore game)
;;;	modelessDialog		25							;the modeless Dialog known to User and Intrface
;;;	bigFont				26	= USERFONT		 		;large font
;;;	volume				27	= 12					;sound volume
;;;	version				28	= {x.yyy.zzz}			;pointer to 'incver' version string
;;;	locales				29							;set of current locales
;;;	curSaveDir			30							;address of current save drive/directory string
;;;	aniThreshold		50	= 10
;;;	perspective			51							;player's viewing angle:
;;;														;degrees away from vertical along y axis
;;;	features			52							;locations that may respond to events
;;;	sortedFeatures		53							;above+cast sorted by "visibility" to ego
;;;	useSortedFeatures	54	= FALSE					;enable cast & feature sorting?
;;;	demoScripts			55							;add to curRoomNum to find room demo script
;;;	egoBlindSpot		56	= 0						;used by sortCopy to exclude 
;;;														;actors behind ego within angle 
;;;														;from straight behind. 
;;;														;Default zero is no blind spot
;;;	overlays			57	= -1
;;;	doMotionCue			58							;a motion cue has occurred - process it
;;;	systemWindow		59							; ID of standard system window
;;;	demoDialogTime		60	= 3						;how long Prints stay up in demo mode
;;;	currentPalette		61
;;;	modelessPort		62
;;;	
;;;	lastSysGlobal		99
;;;)






(include	kernel.sh)		; kernel external declarations

;NOTE: Externs are not defined in SCICompanion, so much of the header files
;are unnecessary and have been omitted.

(include 	extra.sh)		;externs and defines needed to use extras
;(include sight.sh)			;externs needed to use CantBeSeen & Co.
;(include sortcopy.sh)		;extern needed to use SortedAdd
;(include intrface.sh)
(include 	cat.sh)			;for mouse-tracking cat objects
;(include demo.sh)			;demo demon for self-running demos
;(include	namefind.sh)
;(include	sort.sh)
;(include	count.sh)
;(include	qscript.sh)
;(include	loadmany.sh)
;(include	lastlink.sh)
;(include	gotosaid.sh)	


;;;(extern
;;;	sign			SYSTEM	0
;;;	umod			SYSTEM	1
;;;	Min				SYSTEM	2
;;;	Max				SYSTEM	3
;;;	InRect			SYSTEM	4
;;;	OneOf			SYSTEM	5
;;;)


