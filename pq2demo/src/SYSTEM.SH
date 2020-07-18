;;; Sierra Script 1.0 - (do not remove this comment)
;SYSTEM - Header file

;system globals from 0 to 99

;;;(global
;;;	ego					0		;pointer to ego
;;;	theGame				1		;ID of the Game instance
;;;	curRoom				2		;ID of current room
;;;	speed					3		;game speed (number of ticks between animations)
;;;	quit					4		;when TRUE, quit game
;;;	cast					5		;collection of actors
;;;	regions				6		;set of current regions
;;;	timers				7		;list of timers in the game
;;;	sounds				8		;set of sounds being played
;;;	inventory			9		;set of inventory items in game
;;;	addToPics			10		;list of views added to the picture
;;;	curRoomNum			11		;current room number
;;;	prevRoomNum			12		;previous room number
;;;	newRoomNum			13		;number of room to change to
;;;	debugOn				14		;generic debug flag -- set from debug menu
;;;	score					15		;the player's current score
;;;	possibleScore		16		;highest possible score
;;;	showStyle			17		;style of picture showing
;;;	aniInterval			18		;the # of ticks it took to do the last animation cycle
;;;	theCursor			19		;the number of the current cursor
;;;	normalCursor		20		;number of normal cursor form
;;;	waitCursor			21		;cursor number of "wait" cursor
;;;	userFont				22		;font to use for Print
;;;	smallFont			23		;small font for save/restore, etc.
;;;	lastEvent			24		;the last event (used by save/restore game)
;;;	modelessDialog		25		;the modeless Dialog known to User and Intrface
;;;	bigFont				26		;large font
;;;	volume				27		;sound volume for machines with control over it
;;;	version				28		;pointer to "x.yyy.zzz"
;;;	locales				29		;set of current locales
;;;	curSaveDir			30		;address of current save drive/directory string
;;;
;;;	aniThreshold		50
;;;	
;;;	perspective			51		;player's viewing angle:
;;;									;	 degrees away from vertical along y axis
;;;								
;;;	features				52		;locations that may respond to events
;;;	sortedFeatures		53		;above sorted by "visibility" to ego
;;;	sortedCast			54		;cast sorted by "visibility" to ego
;;;	deleteCastMember	55		;a member of the cast needs deleting
;;;
;;;	skipFactor			56		;default=1, set to 2 or 3 if we're really crawling
;;;
;;;	lastSysGlobal		99
;;;)



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

(define	INTRFACE		255)



;Some selector aliases
;NOTE: Macro defines are not currently supported by SCICompanion
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
(define	HAND_CURSOR		997)

;Intrface procedures
;;;(extern
;;;	Print			INTRFACE	0
;;;	ShowView		INTRFACE	1
;;;	GetInput		INTRFACE	2
;;;	GetNumber	INTRFACE	3
;;;	Printf		INTRFACE 4
;;;)


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
(define	dActive		1)
(define	dExit			2)
(define	dBold			4)
(define	dSelected	8)

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

(define	allEvents	$7fff)

(define	leaveIt		$8000)

; event modifiers (in who property)
(define	shiftDown	3)		; either shift key pressed
(define	ctrlDown		4)		; control key pressed
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

; some keycode definitions
(define	ESC			27)
(define	SPACEBAR	32)
(define	ENTER       $000d)		;(define ENTER	`^m) in SSCI
(define	TAB         $0009)		;(define TAB	`^i) in SSCI
(define	SHIFTTAB	$0f00)
(define	BACKSPACE	$0008)		;(define	BACKSPACE	`^h) in SSCI



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
; adding the following constant to any of the styles
; will blacken the screen in the converse manner before showing
(define	BLACKOUT		9)


(define	NULL			0)

;Bits in the -info- property.
(define	CLONED		$0001)
(define	NODISPOSE	$0002)
(define	NODISPLAY	$0004)
(define	CLASS			$8000)

;For compatability -- bringing stuff in line with SmallTalk syntax.
;NOTE: Macro defines are not currently supported by SCICompanions
;(define	myself		yourself)


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

(define	ADDTOPIC		$8021);	(| delObj stopUpdOn viewAdded))


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


;Definitions for sound.
(define	SND_DONE			-1)	;sound finished playing

(define	SND_NOTREADY	0)
(define	SND_READY		1)
(define	SND_BLOCKED		2)
(define	SND_ACTIVE		3)
(define	SND_DELETE		4)


; kernel external declarations
(include	kernel.sh)

