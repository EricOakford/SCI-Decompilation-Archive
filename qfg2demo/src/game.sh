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

;Script defines
(define INTRO 1)
(define INN 2)
(define ALLEY 4)
(define MONEY 5)
(define HAREM 6)
(define DESERT 7)
(define SCORP 8)
(define ROPE 10)
(define RITUAL 11)
(define IBLIS 12)
(define DANCE 75)
(define FLAME 77)
(define FULL 78)
(define SPEED 99)
(define DEBUG 800)

;Views
(define vIcons 999)