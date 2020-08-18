;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

;SWAT framework scripts
(define PQINIT 901)
(define PROCS 902)
(define PQEGO 904)
(define PQROOM 905)
(define MOVIE 906)
(define SWATSAVE 907)
(define SWAT_INTERFACE 908)
(define SWATRESTORE 909)
(define SWATINV 910)
(define BUTTON 912)
(define SWATCONTROLS 913)

;Inventory items
(enum
	iGovt45
	iGovt45Light
	iGasMask
	iMp5
	iGauge12
	iGauge12Buck
	iGauge12Slug
	iJHP45Mag
	iFMJ45Mag
	iSTHP9Mag
	iFMJ9Mag
	iFlashBang
	iMirrorOnStick
	iBatteringRam
	iRobar308
	iFederal308
	iGasGun
	iGasRound
	iGasGrenade
	iGhilleSuit
	iDopeBook
)

;Event flags
(enum 1
	fIsHiRes
)
(define fScrollingEnabled 4)