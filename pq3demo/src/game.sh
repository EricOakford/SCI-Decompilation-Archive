;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

;Icon buttons
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ITEM
	ICON_INVENTORY
	ICON_DUMMY
	ICON_NOTEBOOK
	ICON_CONTROL
	ICON_HELP
)

;Framework scripts
(define SPEED 99)
(define PQEGO 895)
(define COLORINIT 897)
(define DEBUG 899)

;Game-specific verbs
;(for generic verbs, refer to SYSTEM.SH)
(define verbNotebook 10)

;Event flags
(define fIsVGA 2)