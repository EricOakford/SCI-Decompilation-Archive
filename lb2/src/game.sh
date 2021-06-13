;;; Sierra Script 1.0 - (do not remove this comment)
;**************************************************************
;***
;***	GAME.SH--
;***
;**************************************************************

(include system.sh) (include sci2.sh) ;system and kernel functions
(include verbs.sh) (include talkers.sh)

(define DISPOSE	11)
(define LBINIT	14)
(define LBINV	15)
(define LBDOOR	16)
(define LBEGO	19)

;Icon bar
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ASK
	ICON_ITEM
	ICON_INVENTORY
	ICON_CONTROL
)
;Event flags
(define fFlag25			25)
(define fCountessDead	92)