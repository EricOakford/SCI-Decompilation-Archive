;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here
(include system.sh) (include sci2.sh)


;Icon buttons
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_SMELL
	ICON_TASTE
	ICON_ITEM
	ICON_INVENTORY
	ICON_CONTROL
	ICON_HELP
)

;Inventory items
(enum
	iBuckazoid
)

;Verbs
(enum 10
	verbSmell	;10
	verbTaste	;11
)

;Space Quest framework scripts
(define COLORINIT 802)