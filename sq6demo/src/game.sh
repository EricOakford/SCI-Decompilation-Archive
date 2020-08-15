;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here
(include system.sh) (include sci2.sh)
(include Talkers.sh) (include Verbs.sh)

;Script defines
(define SQ6INV 15)
(define SQ6EGO 20)
(define DEBUG 21)
(define SQ6CONTROLS 32)
(define SQ6TALKERS 40)
(define EGODEAD 666)

;Inventory items
(enum
	iBjornBelt
	iBjornChow
	iBrokenClapmaster
	iFixedClapmaster	
	iDuctTape
	iPin
	iPliers
	iWrappedPliers
)
(define NUM_INVITEMS 7)

;Event flags
(enum
	fInIntro			;0
	fBjornEatChow		;1
	fClapmasterPluggedIn		;2
	fGotClapmaster				;3
	fGotPin						;4
	fBjornPluggedIn			;5
	fGotChow				;6
	fChowReady			;7
	fWalkActive				;8
	fLookActive				;9
	fDoActive					;10
	fTalkActive				;11
	fHelpActive				;12
	fControlActive				;13
	fFlag14					;14
	fExitActive				;15
	fInvActive					;16
	fFixingClapmaster			;17
	fVisitedReplicator		;18
)
;Icon bar defines
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_INVENTORY
	ICON_HELP
	ICON_CONTROL
	ICON_USE
	ICON_EXIT
)