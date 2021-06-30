;;; Sierra Script 1.0 - (do not remove this comment)
;
; * SCI Game Header
; *************************************************************************
; * Put all the defines specific to your game in here

(include system.sh) (include sci2.sh)
(include verbs.sh) (include talkers.sh)

;Script defines
(define LOCKJAW_STUFF	838)
(define TWINIT			879)
(define TWABOUT			884)
(define SCROLLINV		885)
(define TWCONTROLS		888)
(define TWINV			894)
(define TWEGO			895)
(define TWROOM			896)
(define COLORINIT		897)
(define DISPOSE			898)

;Inventory items
(enum
	iDogHarness		;0
	iHerbCluster	;1
	iHerbPacket		;2
	iBabySwaddling	;3
	iPebbles		;4
	iFleas			;5
)

;Event flags
(define fIsVGA	1)
(define fCantSave	2)
(define fGotDrainPipe	4)
(define fPepperInBoyClothes 10)
(define fDemoAbout	172)
(define fPutHarnessOnLockjaw	200)

;Talker scripts
(define tlkPepper	2000)
(define tlkLockjaw	2004)
(define tlkPugh		2006)
(define tlkRichard	2012)
(define tlkIma		2018)
(define tlkPercy	2020)