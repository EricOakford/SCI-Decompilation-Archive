;;; Sierra Script 1.0 - (do not remove this comment)
(include system.sh) (include sci2.sh)

; howFast values
(enum
	slow
	medium
	fast
	fastest
)

; Icons
(enum
	ICON_WALK
	ICON_LOOK
	ICON_DO
	ICON_TALK
	ICON_ZIPPER
	ICON_TASTE
	ICON_ITEM
	ICON_INVENTORY
	ICON_CONTROL
	ICON_HELP
)

; Leisure Suit Larry framework scripts
(define DEBUG 800) ;removed from game
(define DISPOSE 801)
(define SPEED 803)
(define ABOUT 811)
(define SPRAY 812)
(define LLROOM 813)
(define LLINV 814)
(define LLEGO 815)
(define LLINIT 816)

;Event flags
(define fGambling 1)
(define fRanOutOfMoney	103)

; Verbs
(enum
	verbNone
	verbWalk
	verbDo
	verb3
	verbTalk
	verb5
	verbTaste
	verb7
	verbZipper	
)