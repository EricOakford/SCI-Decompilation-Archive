;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

;Script defines
(define PROCS			10)
(define TALES_ACTOR		90)
(define TALES_INV		91)
(define TALES_SCRIPTS	92)
(define MAP				401)

;Inventory items and friends
(enum
	iPumpkin
	iSlipper
	iBeans
	iAxe
	iRooster
	iCat
	iDog
	iDonkey
	iBeauty
	iRose
	iSnowWhite
	iPrince
)

;Icons
(enum
	ICON_DO
	ICON_LOOK
	ICON_USE
	ICON_BOOK
	ICON_MAP
	ICON_CONTROL
	ICON_WHAT
)