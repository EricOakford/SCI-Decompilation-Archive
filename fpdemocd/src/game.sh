;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)
(include verbs.sh) (include talkers.sh)

;Global stuff
(define DEBUG 10)
(define DISPOSE 11)
(define SWING 12)
(define FPABOUT 13)
(define FPINIT 14)
(define FPINV 15)
(define OCC_CYC 16)
(define FPROOM 17)
(define FPEGO 19)
(define FPCONTROLS 24)
(define DEBUGDLG 25)
(define SPEED 28)
(define WHERETO 29)
(define FPQUIT 31)
(define rgFreddy 90)
(define rgRowdy 91)
(define DEATH 99)

;Inventory items
(enum
	iBeer			;0
	iLadder			;1
	iMoney			;2
	iOpenBeer		;3
	iLastInvItem	;this MUST be last
)

; Indices for the icons in the icon bar
(enum
	ICON_WALK		;0
	ICON_LOOK		;1
	ICON_DO			;2
	ICON_TALK		;3
	ICON_SKIP		;4
	ICON_USEIT		;5
	ICON_INVENTORY	;6
	ICON_CONTROL	;7
	ICON_HELP		;8
	ICON_SCORE		;9
)

;Event flags
(enum
	fIsVGA					;0
	fFlag1					;1
	fSavedSrini				;2
)
(define fSaloonWindowBroken		26)

;Death reasons
(define deathFALLOFFCLIFF 9)

;Talker scripts
(define tlkFreddy		1800)
(define tlkSrini		1802)
(define tlkDoc			1804)
(define tlkSamAndreas	1808)
(define tlkSmithie		1809)
(define tlkAl			1810)
(define tlkSheriff		1813)
(define tlkWilly		1814)
(define tlkMadame		1817)
(define tlkBilly		1846)