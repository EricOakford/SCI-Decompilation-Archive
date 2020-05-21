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
(define FPROOM 17)
(define FPEGO 19)
(define FPCONTROLS 24)
(define DEBUGDLG 25)
(define SPEED 28)
(define WHERETO 29)
(define FPQUIT 31)
(define rgFreddy 90)
(define rgRowdy 91)

;Inventory items
(enum
	iEmptyVial		;0
	iFullVial		;1
	iBeerBottle		;2
	iEmptyBottle	;3
	iFullBottle		;4
	iCharcoal		;5
	iSaltpeter		;6
	iFullSaltpeter	;7
	iTinCan			;8
	iEmptyCup		;9
	iFullCup		;10
	iMole			;11
	iPrescription	;12
	iFuse			;13
	iMatches		;14
	iUnlitBomb		;15
	iBomb			;16
)

; Indices for the icons in the icon bar
(enum
	ICON_WALK		;0
	ICON_LOOK		;1
	ICON_DO			;2
	ICON_TALK		;3
	ICON_SCORE		;4
	ICON_USEIT		;5
	ICON_INVENTORY	;6
	ICON_CONTROL	;7
	ICON_HELP		;8
)

;Event flags
(enum
	fIsVGA				;0
	fDrankBeer			;1
	fGotMole			;2
	fGaveMoleToMadame	;3
	fPutSulfurInCan		;4
	fPutSaltpeterInCan	;5
	fPutCharcoalInCan	;6
	fGaveDocCoffee		;7
	fGotSaltpeter		;8
)

;Talker scripts
(define tlkFreddy		1800)
(define tlkSrini		1802)
(define tlkDoc			1804)
(define tlkWilly		1806)
(define tlkSamAndreas	1808)
(define tlkSmithie		1809)
(define tlkMadame		1817)